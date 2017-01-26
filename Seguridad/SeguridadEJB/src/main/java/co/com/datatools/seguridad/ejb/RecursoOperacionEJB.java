package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.entidades.Aplicacion;
import co.com.datatools.seguridad.entidades.Operacion;
import co.com.datatools.seguridad.entidades.PermisoRecursoOperacion;
import co.com.datatools.seguridad.entidades.Recurso;
import co.com.datatools.seguridad.entidades.RecursoMenu;
import co.com.datatools.seguridad.entidades.RecursoOperacion;
import co.com.datatools.seguridad.entidades.Rol;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.helper.OperacionHelper;
import co.com.datatools.seguridad.helper.RecursoHelper;
import co.com.datatools.seguridad.interfaces.IRRecursoOperacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.util.GenericDao;

/**
 * EJB que provee los metodos para realizar las operaciones CRUD sobre las entidades de Recurso y Operacion
 * 
 * @author claudia.rodriguez
 * 
 */
@Stateless(name = "RecursoOperacionEJB")
@LocalBean
public class RecursoOperacionEJB implements IRRecursoOperacion {

    private final static Logger logger = Logger.getLogger(RecursoOperacionEJB.class.getName());

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    public RecursoOperacionEJB() {
        super();
        logger.debug("RecursoOperacionEJB.RecursoOperacionEJB");
    }

    @Override
    public List<RecursoDto> consultarRecursos(RecursoDto recursoFiltros, boolean conOperaciones) {
        List<RecursoDto> recursosDto = new ArrayList<>();
        Map<String, Object> params = new HashMap<>(3);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT(r) FROM Recurso r ");
        if (conOperaciones)
            sql.append(" JOIN r.recursoOperacionList ro");

        sql.append(" WHERE 1=1");
        if (StringUtils.isNotBlank(recursoFiltros.getNombreRecurso())) {
            sql.append(" AND r.nombre LIKE :nombre");
            params.put("nombre", "%" + recursoFiltros.getNombreRecurso() + "%");
        }
        if (StringUtils.isNotBlank(recursoFiltros.getDescripcion())) {
            sql.append(" AND r.descripcion LIKE :descripcion");
            params.put("descripcion", "%" + recursoFiltros.getDescripcion() + "%");
        }
        if (StringUtils.isNotBlank(recursoFiltros.getIdAplicacion())) {
            sql.append(" AND r.aplicacion.idAplicacion = :idApp");
            params.put("idApp", Integer.valueOf(recursoFiltros.getIdAplicacion()));
        }

        final GenericDao<Recurso> recursoDao = new GenericDao<>(Recurso.class, em);
        List<Recurso> recursos = recursoDao.buildAndExecuteQuery(sql, params);
        if (recursos != null) {
            recursosDto = new RecursoHelper().toListDto(recursos);
        }
        logger.debug("Se consultaron: " + recursosDto.size() + " recursos");
        return recursosDto;
    }

    @Override
    public RecursoDetalleDto consultarRecurso(final Integer idRecurso) {
        // Consultar el recurso
        Recurso recurso = em.find(Recurso.class, idRecurso);
        logger.debug("Se encontró el Recurso con Id: " + idRecurso + " -" + recurso.getNombre());
        // Asignar el recurso padre al detalleDto
        RecursoHelper helper = new RecursoHelper();
        RecursoDetalleDto recursoDetalle = new RecursoDetalleDto();
        helper.toDto(recurso, recursoDetalle);
        if (recurso.getRecursoPadre() != null)
            recursoDetalle.setPadre(helper.toDto(recurso.getRecursoPadre(), null));
        // Asignar los recursos hijos al detalleDto
        TypedQuery<Recurso> query = em.createNamedQuery(Recurso.SQ_HIJOS_RECURSO, Recurso.class);
        query.setParameter("idPadre", idRecurso);
        List<Recurso> resultado = query.getResultList();
        List<RecursoDetalleDto> hijos = new ArrayList<>();
        for (Recurso recEnt : resultado) {
            hijos.add((RecursoDetalleDto) helper.toDto(recEnt, new RecursoDetalleDto()));
        }
        recursoDetalle.setHijos(hijos);
        // Asignar las operaciones al recurso, incluyendo la operacion ingresar
        List<OperacionDto> operacionesDto = new ArrayList<>();
        List<RecursoOperacion> recursoOperaciones = recurso.getRecursoOperacionList();
        OperacionHelper operacionHelper = new OperacionHelper();
        for (RecursoOperacion recursoOperacion : recursoOperaciones) {
            // if (!recursoOperacion.getOperacion().getNombre().equals(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO))
            operacionesDto.add(operacionHelper.toDto(recursoOperacion.getOperacion(), null));
        }
        recursoDetalle.setOperaciones(operacionesDto);
        return recursoDetalle;
    }

    @Override
    public Integer registrarRecurso(final RecursoDetalleDto recurso) throws SeguridadException {
        boolean tieneOperaciones = false;
        // Si tiene operaciones validar que se haya seleccionado la operacion de ingreso, sino agregarla
        if (!CollectionUtils.isEmpty(recurso.getOperaciones())) {
            tieneOperaciones = true;
            boolean tieneOperIngreso = false;
            for (OperacionDto operacion : recurso.getOperaciones()) {
                if (operacion.getNombreOperacion().equals(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO)) {
                    tieneOperIngreso = true;
                    break;
                }
            }
            if (!tieneOperIngreso) {
                OperacionDto operIngreso = new OperacionDto();
                operIngreso.setNombreOperacion(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO);
                operIngreso.setIdOperacion(consultarIdOperacion(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO));
                recurso.getOperaciones().add(operIngreso);
            }
        }

        // Validar q no exista otro recurso con el mismo nombre para la misma aplicacion
        RecursoDto recursoConsultado = consultarRecurso(Integer.valueOf(recurso.getIdAplicacion()),
                recurso.getNombreRecurso());
        if (recursoConsultado != null) {
            logger.debug("No se pudo crear el recurso" + recurso.getNombreRecurso() + " -"
                    + CatalogoError.RECURSO_YA_EXISTE.getDescError());
            throw new SeguridadException(CatalogoError.RECURSO_YA_EXISTE);
        }
        RecursoHelper helper = new RecursoHelper();
        Recurso entidad = helper.toEntity(recurso, null);
        if (recurso.getPadre() != null && !recurso.getPadre().getIdRecurso().equals(0))
            entidad.setRecursoPadre(em.find(Recurso.class, recurso.getPadre().getIdRecurso()));

        entidad.setAplicacion(em.find(Aplicacion.class, Integer.valueOf(recurso.getIdAplicacion())));

        List<RecursoOperacion> operaciones = new ArrayList<>();
        if (tieneOperaciones) {
            for (OperacionDto operacion : recurso.getOperaciones()) {
                RecursoOperacion recursoOperacion = new RecursoOperacion();
                recursoOperacion.setOperacion(em.find(Operacion.class, operacion.getIdOperacion()));
                recursoOperacion.setRecurso(entidad);
                operaciones.add(recursoOperacion);
            }
            entidad.setRecursoOperacionList(operaciones);
        }
        em.persist(entidad);

        if (tieneOperaciones) {

            GenericDao<Rol> rolDao = new GenericDao<>(Rol.class, em);
            Rol rolSuperAdmin = rolDao.findUniqueByAttribute("nombre", ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);

            for (RecursoOperacion recursoOperacionPersistido : entidad.getRecursoOperacionList()) {
                PermisoRecursoOperacion permisoRecursoOperacion = new PermisoRecursoOperacion();
                permisoRecursoOperacion.setRecursoOperacion(recursoOperacionPersistido);
                permisoRecursoOperacion.setRol(rolSuperAdmin);
                rolSuperAdmin.getPermisoRecursosOperaciones().add(permisoRecursoOperacion);
            }
            // em.merge(rolSuperAdmin);
        }
        logger.debug("Se crea el Recurso con Id: " + entidad.getIdRecurso() + " -" + entidad.getNombre() + " con "
                + operaciones.size() + " operaciones");
        return entidad.getIdRecurso();
    }

    @Override
    public void eliminarRecurso(final Integer idRecurso) throws SeguridadException {
        // Validar que no este asociado a algun rol
        if (validarRecursoAsociadoRol(idRecurso)) {
            logger.info("No se pudo elimina el Recurso con Id= " + idRecurso + " - "
                    + CatalogoError.RECURSO_VS_ROL.getDescError());
            throw new SeguridadException(CatalogoError.RECURSO_VS_ROL);
        }
        // Validar que no sea padre de otro recurso
        Recurso recurso = em.find(Recurso.class, idRecurso);
        if (!CollectionUtils.isEmpty(recurso.getRecursoHijoList())) {
            logger.info("No se pudo elimina el Recurso con Id= " + idRecurso + " - "
                    + CatalogoError.RECURSO_VS_RECURSOS_HIJOS.getDescError());
            throw new SeguridadException(CatalogoError.RECURSO_VS_RECURSOS_HIJOS);
        }
        // Valida que no este asociado a una opcion de menu(recurso_menu)
        TypedQuery<RecursoMenu> query = em.createNamedQuery(RecursoMenu.SQ_RECURSOMENU_BY_RECURSO, RecursoMenu.class);
        query.setParameter("idRecurso", idRecurso);
        List<RecursoMenu> resultado = query.getResultList();
        if (!CollectionUtils.isEmpty(resultado)) {
            logger.info("No se pudo eliminar el Recurso con Id= " + idRecurso + " - " + CatalogoError.RECURSO_VS_MENU);
            throw new SeguridadException(CatalogoError.RECURSO_VS_MENU);
        }

        GenericDao<Rol> rolDao = new GenericDao<>(Rol.class, em);
        Rol rolSuperAdmin = rolDao.findUniqueByAttribute("nombre", ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);

        // Eliminar el permiso del rol super-admin
        for (RecursoOperacion recursoOper : recurso.getRecursoOperacionList()) {
            PermisoRecursoOperacion permisoRolAdmin = consultarPermisosXRecursoOperacionRol(
                    recursoOper.getIdRecursoOperacion(), rolSuperAdmin.getIdRol());
            if (permisoRolAdmin != null)
                em.remove(permisoRolAdmin);
        }

        em.remove(recurso);// eliminar recurso
        logger.debug("Se elimina el Recurso con Id: " + recurso.getIdRecurso() + " -" + recurso.getNombre());
    }

    @Override
    public void actualizarRecurso(final RecursoDetalleDto recurso) throws SeguridadException {
        // Validar que no exista otro recurso con el mismo nombre
        GenericDao<Rol> rolDao = new GenericDao<>(Rol.class, em);
        Rol rolSuperAdmin = rolDao.findUniqueByAttribute("nombre", ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);

        RecursoDto recursoConsultado = consultarRecurso(Integer.valueOf(recurso.getIdAplicacion()),
                recurso.getNombreRecurso());
        if (recursoConsultado != null) {
            if (!recursoConsultado.getIdRecurso().equals(recurso.getIdRecurso())) {
                logger.info("No se pudo modificar el Recurso con Id= " + recurso.getIdRecurso() + " - "
                        + CatalogoError.RECURSO_YA_EXISTE.getDescError());
                throw new SeguridadException(CatalogoError.RECURSO_YA_EXISTE);
            }
        }

        boolean tieneOperaciones = false;
        if (!CollectionUtils.isEmpty(recurso.getOperaciones())) {
            tieneOperaciones = true;
        }
        Integer idOperacionIngresar = consultarIdOperacion(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO);
        Recurso entidad = em.find(Recurso.class, recurso.getIdRecurso());

        // Validar las operaciones que ya no estan en el recurso
        boolean continuaEnRecurso = false;
        List<RecursoOperacion> operEliminar = new ArrayList<>();
        List<RecursoOperacion> recOperacionesEntidad = entidad.getRecursoOperacionList();
        for (RecursoOperacion recursoOperacion : recOperacionesEntidad) {
            if (recursoOperacion.getOperacion().getIdOperacion().equals(idOperacionIngresar) && tieneOperaciones)
                continuaEnRecurso = true;
            else {
                continuaEnRecurso = false;
                for (OperacionDto operacion : recurso.getOperaciones()) {
                    if (recursoOperacion.getOperacion().getIdOperacion().equals(operacion.getIdOperacion())) {
                        continuaEnRecurso = true;
                        break;
                    }
                }
            }
            if (!continuaEnRecurso) {
                // Agregar a las operaciones a eliminar, siempre y cuando no sea "ingresar" para cuando tiene operaciones
                operEliminar.add(recursoOperacion);
            }
        }
        // Eliminar las operaciones que ya no tiene el recurso siempre y cuando no estes asociadas a un rol que no sea el SUPER-ADMIN
        for (RecursoOperacion recEliminar : operEliminar) {
            TypedQuery<PermisoRecursoOperacion> queryRecOperacion = em.createNamedQuery(
                    PermisoRecursoOperacion.SQ_PERMISO_BY_RECURSO_OPERACION, PermisoRecursoOperacion.class);
            queryRecOperacion.setParameter("idRecursoOperacion", recEliminar.getIdRecursoOperacion());
            queryRecOperacion.setParameter("nombreRolSuperAdmin", ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);
            List<PermisoRecursoOperacion> resultado1 = queryRecOperacion.getResultList();
            if (!CollectionUtils.isEmpty(resultado1)) {
                logger.info("No se pudo eliminar el RecursoOperacion con Id= " + recEliminar.getIdRecursoOperacion()
                        + " - " + CatalogoError.RECURSO_OPERACION_VS_ROL.getDescError());
                throw new SeguridadException(CatalogoError.RECURSO_OPERACION_VS_ROL);
            } else {
                // Eliminar el permiso del rol SUPER-ADMIN
                PermisoRecursoOperacion permisoRolAdmin = consultarPermisosXRecursoOperacionRol(
                        recEliminar.getIdRecursoOperacion(), rolSuperAdmin.getIdRol());
                if (permisoRolAdmin != null)
                    em.remove(permisoRolAdmin);

                em.remove(recEliminar);// Eliminar el recurso operacion
                entidad.getRecursoOperacionList().remove(recEliminar);
                logger.debug("Se elimina el RecursoOperacion con Id: " + recEliminar.getIdRecursoOperacion());
            }
        }

        RecursoHelper helper = new RecursoHelper();
        helper.toEntity(recurso, entidad);
        entidad.setAplicacion(entidad.getAplicacion());
        if (recurso.getPadre() != null && !recurso.getPadre().getIdRecurso().equals(0))
            entidad.setRecursoPadre(em.find(Recurso.class, recurso.getPadre().getIdRecurso()));
        else
            entidad.setRecursoPadre(null);

        // Valida las operaciones nuevas que se hayan asignado al recurso (para no volver a agregar aquellas que ya estaban asignadas antes de
        // modificar el recurso)
        boolean estaRecurso = false;
        boolean entidadTieneOperIngresar = false;
        boolean traeOperIngresar = false;
        List<RecursoOperacion> recursoOperacionList = new ArrayList<>();

        for (OperacionDto operacion : recurso.getOperaciones()) {
            estaRecurso = false;
            for (RecursoOperacion recursoOperacion : entidad.getRecursoOperacionList()) {
                if (recursoOperacion.getOperacion().getIdOperacion().equals(idOperacionIngresar)) {
                    entidadTieneOperIngresar = true;
                }
                if (recursoOperacion.getOperacion().getIdOperacion().equals(operacion.getIdOperacion())) {
                    estaRecurso = true;
                    break;
                }
            }
            if (!estaRecurso) {
                RecursoOperacion recursoOper = new RecursoOperacion();
                recursoOper.setOperacion(em.find(Operacion.class, operacion.getIdOperacion()));
                recursoOper.setRecurso(entidad);
                recursoOperacionList.add(recursoOper);
                if (operacion.getIdOperacion().equals(idOperacionIngresar)) {// Validar si ya trae la operacion ingresar asignada
                    traeOperIngresar = true;
                }
            }
        }

        // Si el recurso tiene operaciones, y no le seleccionaron la operacion ingresar y la entidad no la tenia: agregarsela
        if (tieneOperaciones && !traeOperIngresar && !entidadTieneOperIngresar) {
            RecursoOperacion recursoOper = new RecursoOperacion();
            recursoOper.setOperacion(em.find(Operacion.class, idOperacionIngresar));
            recursoOper.setRecurso(entidad);
            recursoOperacionList.add(recursoOper);
        }

        // Asignar las operaciones nuevas del recurso
        for (RecursoOperacion recursoOperacion : recursoOperacionList) {
            entidad.getRecursoOperacionList().add(recursoOperacion);
        }
        em.merge(entidad);

        // Para los recurso-operacion nuevos, agregarlos en los permisos del rol SUPER-ADMIN
        for (RecursoOperacion recursoOperacionNuevo : recursoOperacionList) {
            for (RecursoOperacion recursoOperacion : entidad.getRecursoOperacionList()) {
                if ((recursoOperacionNuevo.getRecurso().getIdRecurso().equals(recursoOperacion.getRecurso()
                        .getIdRecurso()))
                        && (recursoOperacionNuevo.getOperacion().getIdOperacion().equals(recursoOperacion
                                .getOperacion().getIdOperacion()))) {
                    rolSuperAdmin.getPermisoRecursosOperaciones().add(
                            new PermisoRecursoOperacion(recursoOperacion, rolSuperAdmin));
                    break;
                }
            }
        }
        // em.merge(rolSuperAdmin);
        logger.debug("Se modifica el Recurso con Id: " + entidad.getIdRecurso() + " -" + entidad.getNombre());
    }

    @Override
    public List<OperacionDto> consultarOperaciones() {
        List<OperacionDto> operacionesDto = new ArrayList<>();
        TypedQuery<Operacion> query = em.createNamedQuery(Operacion.SQ_OPERACION_ALL, Operacion.class);
        List<Operacion> resultado = query.getResultList();
        operacionesDto = new OperacionHelper().toListDto(resultado);
        logger.debug("Se consultaron: " + operacionesDto.size() + " operaciones");
        return operacionesDto;
    }

    @Override
    public Integer registrarOperacion(OperacionDto operacion) throws SeguridadException {
        // Validar que no exista otra operacion con el mismo nombre
        TypedQuery<Operacion> operacionNombre = em.createNamedQuery(Operacion.SQ_OPERACION_BY_NOMBRE, Operacion.class);
        operacionNombre.setParameter("nombre", operacion.getNombreOperacion());
        List<Operacion> resultados = operacionNombre.getResultList();
        if (!CollectionUtils.isEmpty(resultados)) {
            logger.info("No se pudo registrar la operacion: " + operacion.getNombreOperacion() + " - "
                    + CatalogoError.OPERACION_YA_EXISTE.getDescError());
            throw new SeguridadException(CatalogoError.OPERACION_YA_EXISTE);
        }
        OperacionHelper helper = new OperacionHelper();
        Operacion entidad = helper.toEntity(operacion, null);
        em.persist(entidad);
        logger.debug("Se crea la operación con Id= " + entidad.getIdOperacion() + " -" + entidad.getNombre());
        return entidad.getIdOperacion();
    }

    @Override
    public void eliminarOperacion(Integer idOperacion) throws SeguridadException {
        // Validar que no se quiere eliminar la operacion de nombre ingreso
        Operacion entidad = em.find(Operacion.class, idOperacion);
        if (entidad.getNombre().equals(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO)) {
            logger.info("No se pudo elimina la operacion con Id= " + idOperacion + " - "
                    + CatalogoError.OPERACION_INGRESO_NO_EDITABLE.getDescError());
            throw new SeguridadException(CatalogoError.OPERACION_INGRESO_NO_EDITABLE);
        }
        TypedQuery<RecursoOperacion> recursosOperacion = em.createNamedQuery(
                RecursoOperacion.SQ_RECURSO_OPERACION_BY_OPERACION, RecursoOperacion.class);
        recursosOperacion.setParameter("idOperacion", idOperacion);
        List<RecursoOperacion> resultados = recursosOperacion.getResultList();
        if (!CollectionUtils.isEmpty(resultados)) {
            logger.info("No se pudo elimina la operacion con Id= " + idOperacion + " - "
                    + CatalogoError.OPERACION_VS_RECURSO.getDescError());
            throw new SeguridadException(CatalogoError.OPERACION_VS_RECURSO);
        }
        em.remove(entidad);
        logger.debug("Se elimina la operación con Id=" + idOperacion + " -" + entidad.getNombre());
    }

    /**
     * Valida si un recurso se encuentra asociado a un rol distinto al super admin
     * 
     * @param idRecurso
     *            Identificador del recurso a eliminar
     * @return true si el recurso esta asociado a un rol, de lo contrario false
     */
    private boolean validarRecursoAsociadoRol(Integer idRecurso) {
        TypedQuery<PermisoRecursoOperacion> consulta = em.createNamedQuery(
                PermisoRecursoOperacion.SQ_PERMISO_BY_RECURSO, PermisoRecursoOperacion.class);
        consulta.setParameter("idRecurso", idRecurso);
        consulta.setParameter("nombreRolSuperAdmin", ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);
        List<PermisoRecursoOperacion> permisoRecursos = consulta.getResultList();
        if (!CollectionUtils.isEmpty(permisoRecursos)) {
            return true;
        }
        return false;

    }

    /**
     * Obtiene el identificador de la operacion cuyo nombre es enviado como parametro
     * 
     * @param nombre
     *            Nombre de la operacion a consultar para obtener el identificador
     * @return identificador de la operacion
     */
    private Integer consultarIdOperacion(String nombre) {
        TypedQuery<Operacion> query = em.createNamedQuery(Operacion.SQ_OPERACION_BY_NOMBRE, Operacion.class);
        query.setParameter("nombre", nombre);
        Operacion resultado = query.getSingleResult();
        return resultado.getIdOperacion();
    }

    @Override
    public List<OperacionDto> consultarOperaciones(String nombre) {
        List<OperacionDto> operacionesDto = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM Operacion o WHERE 1=1");
        if (StringUtils.isNotBlank(nombre))
            sql.append(" AND UPPER(o.nombre) LIKE :nombre");
        TypedQuery<Operacion> query = em.createQuery(sql.toString(), Operacion.class);
        if (StringUtils.isNotBlank(nombre))
            query.setParameter("nombre", "%" + nombre.toUpperCase() + "%");
        List<Operacion> resultado = query.getResultList();
        operacionesDto = new OperacionHelper().toListDto(resultado);
        logger.debug("Se consultaron: " + operacionesDto.size() + " recursos");
        return operacionesDto;
    }

    @Override
    public OperacionDto consultarOperacion(Integer id) {
        Operacion entidad = em.find(Operacion.class, id);
        if (entidad == null) {
            logger.info("No se encontró la operación con Id= " + id);
            return null;
        } else {
            OperacionHelper helper = new OperacionHelper();
            logger.debug("Se encontró la operación con Id= " + id + " -" + entidad.getNombre());
            return helper.toDto(entidad, null);
        }
    }

    @Override
    public void actualizarOperacion(OperacionDto operacionDto) throws SeguridadException {
        // Validar que no se quiere editar la operacion de nombre ingreso
        Operacion entidad = em.find(Operacion.class, operacionDto.getIdOperacion());
        if (entidad.getNombre().equals(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO)) {
            logger.info("No se pudo modificar la operación de Id= " + operacionDto.getIdOperacion() + " -"
                    + operacionDto.getNombreOperacion() + " - "
                    + CatalogoError.OPERACION_INGRESO_NO_EDITABLE.getDescError());
            throw new SeguridadException(CatalogoError.OPERACION_INGRESO_NO_EDITABLE);
        }
        // Validar que no exista otra operacion con el mismo nombre
        TypedQuery<Operacion> operacionNombre = em.createNamedQuery(Operacion.SQ_OPERACION_BY_NOMBRE, Operacion.class);
        operacionNombre.setParameter("nombre", operacionDto.getNombreOperacion());
        List<Operacion> resultados = operacionNombre.getResultList();
        if (!CollectionUtils.isEmpty(resultados)
                && !resultados.get(0).getIdOperacion().equals(operacionDto.getIdOperacion())) {
            logger.info("No se pudo modificar la operación de Id= " + operacionDto.getIdOperacion() + " -"
                    + operacionDto.getNombreOperacion() + " - " + CatalogoError.OPERACION_YA_EXISTE.getDescError());
            throw new SeguridadException(CatalogoError.OPERACION_YA_EXISTE);
        }

        OperacionHelper helper = new OperacionHelper();
        Operacion entidadModificada = helper.toEntity(operacionDto, entidad);
        em.merge(entidadModificada);
        logger.debug("Se modificó la operación con Id= " + entidadModificada.getIdOperacion() + " -"
                + entidadModificada.getNombre());
    }

    @Override
    public RecursoDto consultarRecurso(Integer idAplicacion, String nombreRecurso) {
        RecursoDto recursoDto = null;
        TypedQuery<Recurso> query = em.createNamedQuery(Recurso.SQ_RECURSO_BY_NOMBRE, Recurso.class);
        query.setParameter("nombre", nombreRecurso);
        query.setParameter("idApp", idAplicacion);
        List<Recurso> resultado = query.getResultList();
        if (CollectionUtils.isNotEmpty(resultado)) {
            recursoDto = new RecursoHelper().toDto(resultado.get(0), null);
        }
        return recursoDto;
    }

    private PermisoRecursoOperacion consultarPermisosXRecursoOperacionRol(Integer idRecursoOperacion, Integer idRol) {
        PermisoRecursoOperacion resultado = null;
        try {
            TypedQuery<PermisoRecursoOperacion> queryPermisos = em.createNamedQuery(
                    PermisoRecursoOperacion.SQ_PERMISO_BY_RECURSO_OPERACION_ROL, PermisoRecursoOperacion.class);
            queryPermisos.setParameter("idRecursoOperacion", idRecursoOperacion);
            queryPermisos.setParameter("idRol", idRol);
            resultado = queryPermisos.getSingleResult();
        } catch (NoResultException e) {
            logger.errorv("NO se encontraba asociado el recurso operacion de id= {0} al rol= {1} ", idRecursoOperacion,
                    idRol);
        }
        return resultado;
    }
}
