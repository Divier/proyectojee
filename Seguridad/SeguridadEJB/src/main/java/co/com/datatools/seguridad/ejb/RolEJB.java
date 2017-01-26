package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.infinispan.CacheImpl;
import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoRolDto;
import co.com.datatools.seguridad.dto.autorizacion.AplicacionDTO;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.ejb.historico.HistoricoRolEJB;
import co.com.datatools.seguridad.entidades.Aplicacion;
import co.com.datatools.seguridad.entidades.Grupo;
import co.com.datatools.seguridad.entidades.HistoricoRol;
import co.com.datatools.seguridad.entidades.HistoricoUsuario;
import co.com.datatools.seguridad.entidades.Operacion;
import co.com.datatools.seguridad.entidades.PermisoRecursoOperacion;
import co.com.datatools.seguridad.entidades.Recurso;
import co.com.datatools.seguridad.entidades.RecursoOperacion;
import co.com.datatools.seguridad.entidades.Rol;
import co.com.datatools.seguridad.entidades.RolRecursoOperacion;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.helper.DatosSesionHelper;
import co.com.datatools.seguridad.helper.GrupoHelper;
import co.com.datatools.seguridad.helper.RecursoHelper;
import co.com.datatools.seguridad.helper.RolesHelper;
import co.com.datatools.seguridad.interfaces.IRRol;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.ObjectToXML;

/**
 * Implementacion de operaciones asociadas a Roles
 * 
 * @author Felipe Martinez
 */
@Stateless(name = "RolEJB")
@LocalBean
public class RolEJB implements IRRol {
    private final static Logger logger = Logger.getLogger(RolEJB.class.getName());

    private static final String DL_PERMISOS_ROL = "DELETE PermisoRecursoOperacion pro WHERE pro.rol.idRol = :idRol";
    private static final String DL_PERMISOS_ROL_PRM1 = "idRol";

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    @Resource(lookup = "java:jboss/infinispan/container/seguridad")
    private org.infinispan.manager.CacheContainer seguridadContainer;

    private org.infinispan.Cache<Object, Object> cacheRoles;

    @EJB
    private HistoricoRolEJB historicoRolEJB;

    @EJB
    private ParametrosSeguridadEJB parametrosSeguridadEJB;

    @EJB
    private CatalogosSeguridadEJB catalogoSeguridadEjb;

    public RolEJB() {
        super();
        logger.debug("RolEJB.RolEJB()");
    }

    @PostConstruct
    public void start() {
        final StringBuilder strb = new StringBuilder("RolEJB - Obteniendo Cache Container ");
        if (seguridadContainer != null) {
            strb.append("--> se encuentra Cache Container ");
            this.cacheRoles = this.seguridadContainer.getCache(ConstantesSeguridad.NOMBRE_CACHE_ROLES);
        }

        if (this.cacheRoles == null) {
            strb.append("--> NO se encuentra Cache: " + ConstantesSeguridad.NOMBRE_CACHE_ROLES
                    + " Se crea instancia manual");
            cacheRoles = new CacheImpl<Object, Object>(ConstantesSeguridad.NOMBRE_CACHE_ROLES);
        } else {
            strb.append("--> Se encuentra instancia de Cache: " + ConstantesSeguridad.NOMBRE_CACHE_ROLES
                    + " administrada por el contenedor");
        }
        logger.debug(strb);
    }

    @Override
    public RolDetalleDto consultarPerfilCompletoAplicacion(String idAplicacion) {
        // Dentro del dto a retornar debe traer los recursos de primer nivel (Sin Padre) para cada aplicacion
        // Dentro de cada uno de ellos vienen los niveles hijos
        // El ultimo nivel de recursos contiene las operaciones con el id de la tabla recurso_operacion

        RolDetalleDto rolDetalleDto = new RolDetalleDto();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT(rec) FROM Recurso rec");
        sql.append(" LEFT JOIN FETCH rec.recursoOperacionList  recoper");
        sql.append(" LEFT JOIN FETCH recoper.operacion o");
        sql.append(" WHERE 1=1");
        if (idAplicacion != null)
            sql.append(" AND rec.aplicacion.idAplicacion= :idApp");
        sql.append(" ORDER BY rec.recursoPadre");

        TypedQuery<Recurso> query = em.createQuery(sql.toString(), Recurso.class);
        if (idAplicacion != null)
            query.setParameter("idApp", Integer.valueOf(idAplicacion));
        List<Recurso> resultados = query.getResultList();

        // Obtener los RecursoDetalleDto de recursos de primer nivel(sin padre)
        List<RecursoDetalleDto> recursosNivel1 = new ArrayList<>();
        for (Recurso recurso : resultados) {
            if (recurso.getRecursoPadre() == null) {
                RecursoDetalleDto recDetalleNivel1 = obtenerRecursoDetalle(recurso, resultados);
                recursosNivel1.add(recDetalleNivel1);
            }
        }
        // Recorrer los recurso de primer nivel y separarlos por aplicacion
        Map<String, List<RecursoDetalleDto>> recursosAplicacion = new HashMap<>();

        for (RecursoDetalleDto recPadre : recursosNivel1) {
            List<RecursoDetalleDto> recursosApp = recursosAplicacion.get(recPadre.getNombreAplicacion());
            if (recursosApp == null) {
                List<RecursoDetalleDto> listaRecursoDetalleDto = new ArrayList<>();
                listaRecursoDetalleDto.add(recPadre);
                recursosAplicacion.put(recPadre.getNombreAplicacion(), listaRecursoDetalleDto);
            } else {
                recursosAplicacion.get(recPadre.getNombreAplicacion()).add(recPadre);
            }
        }

        rolDetalleDto.setRecursosAplicacion(recursosAplicacion);
        return rolDetalleDto;
    }

    /**
     * Obtiene un Recurso de la lista cuyo identificador conincida con el del recurso enviado como parametro
     * 
     * @param resultados
     *            Lista en la cual se busca el recurso
     * @param recursoBuscar
     *            Recurso cuyo identificador se va a buscar en la lista
     * @return recurso de la lista cuyo identificador sea el del recurso enviado, null si no encuentra el recurso en la lista
     */
    private Recurso obtenerRecursoLista(List<Recurso> resultados, Recurso recursoBuscar) {
        for (Recurso recurso : resultados) {
            if (recurso.getIdRecurso().equals(recursoBuscar.getIdRecurso())) {
                return recurso;
            }
        }
        return null;
    }

    /**
     * Obtiene un RecursoDetalleDto a partir de un Recurso, construyendo recursivamente los RecursoDetalleDto de todos los hijos de niveles inferiores
     * 
     * @param recurso
     *            Entidad a partir de la cual de obtiene un RecursoDetalleDto
     * @param resultados
     *            Lista donde se buscaran los recursos hijos y que contiene recursos con los datos de hijos y operaciones
     * @return RecursoDetalleDto obtenido con todos los datos del recurso enviado
     */
    private RecursoDetalleDto obtenerRecursoDetalle(Recurso recurso, List<Recurso> resultados) {
        RecursoDetalleDto recDetalle = new RecursoDetalleDto();
        // Datos basicos
        recDetalle.setIdRecurso(recurso.getIdRecurso());
        recDetalle.setNombreRecurso(recurso.getNombre());
        recDetalle.setDescripcion(recurso.getDescripcion());
        recDetalle.setIdAplicacion(String.valueOf(recurso.getAplicacion().getIdAplicacion()));
        recDetalle.setNombreAplicacion(recurso.getAplicacion().getNombreAplicacion());

        // Hijos
        if (!CollectionUtils.isEmpty(recurso.getRecursoHijoList())) {
            List<RecursoDetalleDto> hijos = new ArrayList<>();
            for (Recurso recHijo : recurso.getRecursoHijoList()) {
                // Tomar el recurso hijo de la lista de resultados ya que trae todos sus datos
                Recurso recHijoCompleto = obtenerRecursoLista(resultados, recHijo);
                RecursoDetalleDto hijoDetalleDto = new RecursoDetalleDto();
                hijoDetalleDto = obtenerRecursoDetalle(recHijoCompleto, resultados);
                hijos.add(hijoDetalleDto);
            }
            recDetalle.setHijos(hijos);
        }
        // Padre
        if (recurso.getRecursoPadre() == null)
            recDetalle.setPadre(null);
        else {
            recDetalle.setPadre(new RecursoHelper().toDto(recurso.getRecursoPadre(), null));
        }
        // Operaciones
        if (!CollectionUtils.isEmpty(recurso.getRecursoOperacionList())) {
            List<OperacionDto> operaciones = new ArrayList<>();
            for (RecursoOperacion recOper : recurso.getRecursoOperacionList()) {
                OperacionDto operacionDto = new OperacionDto();
                operacionDto.setIdOperacion(recOper.getIdRecursoOperacion());
                operacionDto.setNombreOperacion(recOper.getOperacion().getNombre());
                operaciones.add(operacionDto);
            }
            Collections.sort(operaciones);
            recDetalle.setOperaciones(operaciones);
        }
        return recDetalle;

    }

    @Override
    public List<RolDetalleDto> consultarRoles(RolDetalleDto rolDetalleDto) {
        List<RolDetalleDto> rolesDto = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r FROM Rol r WHERE 1=1 AND r.nombre <> :nombreRolAdmin");
        if (rolDetalleDto.getNombre() != null && !rolDetalleDto.getNombre().equals("")) {
            sql.append(" AND r.nombre LIKE :nombre");
        }
        if (rolDetalleDto.getActivo() != null) {
            sql.append(" AND r.activo = :estado");
        }
        if (rolDetalleDto.getRolPadre() != null) {
            sql.append(" AND r.rolPadre.idRol = :idRolPadre");
        }

        // Aplicacion
        if (rolDetalleDto.getAplicacionDTO() != null) {
            sql.append(" AND r.aplicacion.idAplicacion = :idAplicacion");
        }
        sql.append(" ORDER BY(r.nombre)");

        TypedQuery<Rol> consulta = em.createQuery(sql.toString(), Rol.class);
        consulta.setParameter("nombreRolAdmin", ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);

        if (rolDetalleDto.getNombre() != null && !rolDetalleDto.getNombre().equals("")) {
            consulta.setParameter("nombre", "%" + rolDetalleDto.getNombre() + "%");
        }
        if (rolDetalleDto.getActivo() != null) {
            consulta.setParameter("estado", rolDetalleDto.getActivo());
        }
        if (rolDetalleDto.getRolPadre() != null) {
            consulta.setParameter("idRolPadre", rolDetalleDto.getRolPadre().getIdRol());
        }
        // Aplicacion
        if (rolDetalleDto.getAplicacionDTO() != null) {
            consulta.setParameter("idAplicacion", rolDetalleDto.getAplicacionDTO().getIdAplicacion());
        }
        List<Rol> roles = consulta.getResultList();
        if (roles != null) {
            RolesHelper helper = new RolesHelper();
            for (Rol rol : roles) {
                RolDetalleDto roldetalle = new RolDetalleDto();
                roldetalle.setIdRol(rol.getIdRol());
                roldetalle.setNombre(rol.getNombre());
                roldetalle.setActivo(rol.isActivo());
                roldetalle.setDescripcion(rol.getDescripcion());
                if (rol.getRolPadre() != null)
                    roldetalle.setRolPadre((RolDetalleDto) helper.toDto(rol.getRolPadre(), new RolDetalleDto()));
                rolesDto.add(roldetalle);
            }
        }
        logger.debug("Se consultaron: " + rolesDto.size() + " roles");
        return rolesDto;
    }

    @Override
    public RolDetalleDto consultarRol(final Integer idRol) {
        RolDetalleDto rolDetalleDto = new RolDetalleDto();
        Rol entidad = em.find(Rol.class, idRol);
        if (entidad == null) {
            logger.info("No se encontró el rol con Id= " + idRol);
            return null;
        }
        logger.debug("Se encontró el rol con Id= " + idRol + " -" + entidad.getNombre());
        rolDetalleDto.setIdRol(entidad.getIdRol());
        rolDetalleDto.setActivo(entidad.isActivo());
        rolDetalleDto.setDescripcion(entidad.getDescripcion());
        rolDetalleDto.setNombre(entidad.getNombre());

        // Aplicacion
        AplicacionDTO aplicacionDTO = new AplicacionDTO();
        aplicacionDTO.setIdAplicacion(entidad.getAplicacion().getIdAplicacion());
        aplicacionDTO.setNombreAplicacion(entidad.getAplicacion().getNombreAplicacion());
        rolDetalleDto.setAplicacionDTO(aplicacionDTO);

        if (entidad.getRolPadre() != null)
            rolDetalleDto.setRolPadre((RolDetalleDto) new RolesHelper().toDto(entidad.getRolPadre(),
                    new RolDetalleDto()));

        // Obtener Grupos del rol
        List<GrupoDto> gruposRol = new ArrayList<>();
        TypedQuery<Grupo> queryGr = em.createNamedQuery(Rol.SQ_GRUPOS_ROL, Grupo.class);
        queryGr.setParameter("idRol", idRol);
        List<Grupo> resultGr = queryGr.getResultList();
        if (!CollectionUtils.isEmpty(resultGr)) {
            gruposRol = new GrupoHelper().toListDto(resultGr);
        }
        rolDetalleDto.setGrupos(gruposRol);

        // Obtener los permisos del rol
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT per.recursoOperacion");
        sql.append(" FROM PermisoRecursoOperacion per");
        sql.append(" JOIN FETCH per.recursoOperacion.recurso rec");
        sql.append(" JOIN FETCH per.recursoOperacion.operacion");
        sql.append(" JOIN FETCH rec.aplicacion");
        sql.append(" WHERE per.rol.idRol=:idRol");

        TypedQuery<RecursoOperacion> query = em.createQuery(sql.toString(), RecursoOperacion.class);
        query.setParameter("idRol", idRol);
        List<RecursoOperacion> result = query.getResultList();
        Map<String, List<RecursoDetalleDto>> recursosAplicacionMap = new HashMap<>();

        List<RecursoDetalleDto> recursosDetaOperaciones = new ArrayList<>();
        boolean estaEnArreglo;
        for (RecursoOperacion recursoOperacion : result) {
            Recurso recurso = recursoOperacion.getRecurso();
            Operacion operacion = recursoOperacion.getOperacion();
            estaEnArreglo = false;
            for (RecursoDetalleDto recursoDetalleDto : recursosDetaOperaciones) {
                if (recurso.getIdRecurso().equals(recursoDetalleDto.getIdRecurso())) {
                    estaEnArreglo = true;
                    OperacionDto oper = new OperacionDto();
                    oper.setIdOperacion(recursoOperacion.getIdRecursoOperacion());
                    oper.setNombreOperacion(operacion.getNombre());
                    recursoDetalleDto.getOperaciones().add(oper);
                    break;
                }
            }
            if (!estaEnArreglo) {
                // Agregarlo
                RecursoDetalleDto recursoDetalle = new RecursoDetalleDto();
                recursoDetalle.setIdRecurso(recurso.getIdRecurso());
                recursoDetalle.setNombreRecurso(recurso.getNombre());
                recursoDetalle.setNombreAplicacion(recurso.getAplicacion().getNombreAplicacion());
                List<OperacionDto> operacionesList = new ArrayList<>();
                OperacionDto oper = new OperacionDto();
                oper.setIdOperacion(recursoOperacion.getIdRecursoOperacion());
                oper.setNombreOperacion(operacion.getNombre());
                operacionesList.add(oper);
                recursoDetalle.setOperaciones(operacionesList);
                recursosDetaOperaciones.add(recursoDetalle);
            }

        }

        // Separar los recursos por aplicacion
        for (RecursoDetalleDto recursoDetalleDto : recursosDetaOperaciones) {
            List<RecursoDetalleDto> recursosDetalleList = recursosAplicacionMap.get(recursoDetalleDto
                    .getNombreAplicacion());
            if (recursosDetalleList == null) {
                List<RecursoDetalleDto> recursosList = new ArrayList<>();
                recursosList.add(recursoDetalleDto);
                recursosAplicacionMap.put(recursoDetalleDto.getNombreAplicacion(), recursosList);
            } else {
                recursosDetalleList.add(recursoDetalleDto);
            }
        }
        rolDetalleDto.setRecursosAplicacion(recursosAplicacionMap);

        return rolDetalleDto;
    }

    @Override
    public Integer registrarRol(final RolDetalleDto rolDetalle) throws SeguridadException {
        // validar que no exista otro rol con el mismo nombre
        TypedQuery<Rol> query = em.createNamedQuery(Rol.SQ_ROL_BY_NOMBRE, Rol.class);
        query.setParameter("nombre", rolDetalle.getNombre());
        List<Rol> roles = query.getResultList();

        if (!CollectionUtils.isEmpty(roles)) {
            logger.info("No se pudo crear el rol: " + rolDetalle.getNombre() + " -"
                    + CatalogoError.ROL_YA_EXISTE.getDescError());
            throw new SeguridadException(CatalogoError.ROL_YA_EXISTE);
        }

        RolesHelper helper = new RolesHelper();
        rolDetalle.setActivo(true);
        Rol entidad = helper.toEntity(rolDetalle, null);

        // Asignacion de la aplicacion
        Aplicacion aplicacion = new Aplicacion();

        String idAplicacion = null;
        for (Iterator<Entry<String, List<RecursoDetalleDto>>> iterator = rolDetalle.getRecursosAplicacion().entrySet()
                .iterator(); iterator.hasNext();) {
            Entry<String, List<RecursoDetalleDto>> app = iterator.next();
            idAplicacion = catalogoSeguridadEjb.consultarIdAplicacion(app.getKey());
        }

        aplicacion.setIdAplicacion(Integer.parseInt(idAplicacion));
        entidad.setAplicacion(aplicacion);

        // Rol padre

        if (rolDetalle.getRolPadre() != null && rolDetalle.getRolPadre().getIdRol() != 0) {
            entidad.setRolPadre(em.find(Rol.class, rolDetalle.getRolPadre().getIdRol()));

            // Asignar los datos del rol padre para el dto que genera el historico
            int nivelesHerencia = Integer.valueOf(parametrosSeguridadEJB
                    .consultarValorParametroSeguridad(EnumParametro.NIVELES_HERENCIA_ROLES));
            logger.debug("Estan parametrizados " + nivelesHerencia + " niveles de herencia para los roles");
            List<RolDetalleDto> listaPadres = new ArrayList<RolDetalleDto>();
            obtenerListaPadres(listaPadres, entidad, 0, nivelesHerencia);
            asignarPadresRecursivo(rolDetalle, 0, nivelesHerencia, listaPadres);
        } else {
            // Por si trae un objeto instanciado y sin id,ponerlo en null para que no quede el tag en el xml del historico
            rolDetalle.setRolPadre(null);
        }
        // Permisos del rol
        List<PermisoRecursoOperacion> permisoRecursosOperaciones = new ArrayList<>();
        for (Iterator<Entry<String, List<RecursoDetalleDto>>> iterator = rolDetalle.getRecursosAplicacion().entrySet()
                .iterator(); iterator.hasNext();) {
            Entry<String, List<RecursoDetalleDto>> app = iterator.next();
            List<RecursoDetalleDto> recursoDetalle = app.getValue();
            for (RecursoDetalleDto recursoDetalleDto : recursoDetalle) {
                // Asignar en null los hijos de cada RecursoDetalle para que el tag no quede en el XML de historico del rol
                List<OperacionDto> operaciones = recursoDetalleDto.getOperaciones();
                for (OperacionDto operacionDto : operaciones) {
                    logger.debug("Tiene el recurso-operacion id= " + operacionDto.getIdOperacion());
                    PermisoRecursoOperacion permisoRecOperacion = new PermisoRecursoOperacion();
                    RecursoOperacion recursoOperacion = em.find(RecursoOperacion.class, operacionDto.getIdOperacion());
                    permisoRecOperacion.setRecursoOperacion(recursoOperacion);
                    permisoRecOperacion.setRol(entidad);
                    permisoRecursosOperaciones.add(permisoRecOperacion);
                }
            }
        }
        entidad.setPermisoRecursosOperaciones(permisoRecursosOperaciones);

        // Crear operacion ingresar si no fue seleccionada en el recurso
        crearPermisoOperacionIngresar(rolDetalle.getRecursosAplicacion(), entidad);

        // Grupos del rol
        List<Grupo> grupoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rolDetalle.getGrupos())) {
            for (GrupoDto grupoDto : rolDetalle.getGrupos()) {
                Grupo grupoConsultado = em.find(Grupo.class, grupoDto.getIdGrupo());
                grupoList.add(grupoConsultado);
            }
        }
        entidad.setGrupoList(grupoList);
        // Persistir rol
        em.persist(entidad);
        logger.debug("Se crea el rol con Id= " + entidad.getIdRol() + " -" + entidad.getNombre());
        // Persistir historico del rol
        rolDetalle.setIdRol(entidad.getIdRol());
        historicoRolEJB.registrarHistorico(rolDetalle, null);
        return entidad.getIdRol();
    }

    @Override
    public void eliminarRol(final Integer idRol) throws SeguridadException {
        // Valida que no se va a eliminar el rol PUBLICO
        Rol entidad = em.find(Rol.class, idRol);
        if (entidad.getNombre().equals(ConstantesSeguridad.NOMBRE_ROL_PUBLICO)) {
            logger.info("No se pudo elimina el rol con Id= " + idRol + " - "
                    + CatalogoError.ROL_PUBLICO_NO_ELIMINABLE.getDescError());
            throw new SeguridadException(CatalogoError.ROL_PUBLICO_NO_ELIMINABLE);
        }
        // Valida que no existan usuarios con ese rol asignado
        if (!CollectionUtils.isEmpty(entidad.getUsuarioList())) {
            logger.info("No se pudo elimina el rol con Id= " + idRol + " - "
                    + CatalogoError.ROL_VS_USUARIO.getDescError());
            throw new SeguridadException(CatalogoError.ROL_VS_USUARIO);
        }
        // Valida que no hay registros de ingreso al sistema de algun usuario con ese rol
        if (!CollectionUtils.isEmpty(entidad.getIngresoUsuarioList())) {
            logger.info("No se pudo elimina el rol con Id= " + idRol + " - "
                    + CatalogoError.ROL_VS_INGRESO_USUARIO.getDescError());
            throw new SeguridadException(CatalogoError.ROL_VS_INGRESO_USUARIO);
        }
        // Valida que el rol no esta definido como padre de otros roles
        List<Rol> rolesHijos = consultarRolesPorPadre(idRol);
        if (!CollectionUtils.isEmpty(rolesHijos)) {
            logger.info("No se pudo elimina el rol con Id= " + idRol + " - "
                    + CatalogoError.ROL_VS_ROL_PADRE.getDescError());
            throw new SeguridadException(CatalogoError.ROL_VS_ROL_PADRE);
        }
        // Valida que el rol no haya estado definido como padre de otros roles(en el historico)
        TypedQuery<HistoricoRol> queryHistoricoRol = em.createNamedQuery(HistoricoRol.SQ_HISTORICO_BY_ROL_PADRE,
                HistoricoRol.class);
        queryHistoricoRol.setParameter("idRol", entidad.getIdRol());
        List<HistoricoRol> historico = queryHistoricoRol.getResultList();
        if (!CollectionUtils.isEmpty(historico)) {
            logger.info("No se pudo elimina el rol con Id= " + idRol + " - "
                    + CatalogoError.ROL_VS_HISTORICO_ROL_PADRE.getDescError());
            throw new SeguridadException(CatalogoError.ROL_VS_HISTORICO_ROL_PADRE);
        }
        // Valida que el rol no haya estado asociado a usuarios (en el historico)
        TypedQuery<HistoricoUsuario> queryHistoricoUsuario = em.createNamedQuery(HistoricoUsuario.SQ_HISTORICO_BY_ROL,
                HistoricoUsuario.class);
        queryHistoricoUsuario.setParameter("idRol", entidad.getIdRol());
        List<HistoricoUsuario> historicoUsuario = queryHistoricoUsuario.getResultList();
        if (!CollectionUtils.isEmpty(historicoUsuario)) {
            logger.info("No se pudo elimina el rol con Id= " + idRol + " - "
                    + CatalogoError.ROL_VS_HISTORICO_USUARIO.getDescError());
            throw new SeguridadException(CatalogoError.ROL_VS_HISTORICO_USUARIO);
        }

        // Eliminar el historico del rol
        historicoRolEJB.eliminarHistoricoRol(entidad);
        // Eliminar el rol
        em.remove(entidad);
        logger.debug("Se elimina el rol con Id= " + idRol + " -" + entidad.getNombre());
    }

    @Override
    public void actualizarRol(final RolDetalleDto rolDetalleDto) throws SeguridadException {
        // Validar que no exista otro rol con el mismo nombre
        TypedQuery<Rol> query = em.createNamedQuery(Rol.SQ_ROL_BY_NOMBRE, Rol.class);
        query.setParameter("nombre", rolDetalleDto.getNombre());
        List<Rol> roles = query.getResultList();
        if (!CollectionUtils.isEmpty(roles) && !roles.get(0).getIdRol().equals(rolDetalleDto.getIdRol())) {
            logger.info("No se pudo modificar el rol: " + rolDetalleDto.getNombre() + " -"
                    + CatalogoError.ROL_YA_EXISTE.getDescError());
            throw new SeguridadException(CatalogoError.ROL_YA_EXISTE);
        }
        Rol rolActual = em.find(Rol.class, rolDetalleDto.getIdRol());

        final GenericDao<Rol> rolDao = new GenericDao<>(Rol.class, em);
        final Query delPermisosQu = rolDao.buildQuery(DL_PERMISOS_ROL,
                GenericDao.buildMap(DL_PERMISOS_ROL_PRM1, rolDetalleDto.getIdRol()));

        logger.debug("Permisos borrados " + delPermisosQu.executeUpdate());
        em.refresh(rolActual);

        RolesHelper helper = new RolesHelper();
        helper.toEntity(rolDetalleDto, rolActual);

        // Crear operacion ingresar si no fue seleccionada en el recurso
        crearPermisoOperacionIngresar(rolDetalleDto.getRecursosAplicacion(), rolActual);

        // Lista de los recurso_operacion que quedaran asociados al rol
        final List<RecursoOperacion> recursosOperacion = procesarOperacionesRecurso(rolDetalleDto
                .getRecursosAplicacion());
        rolActual = crearPermisos(rolActual, recursosOperacion);

        // Grupos del rol
        List<Grupo> grupoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rolDetalleDto.getGrupos())) {
            for (GrupoDto grupoDto : rolDetalleDto.getGrupos()) {
                Grupo grupoConsultado = em.find(Grupo.class, grupoDto.getIdGrupo());
                grupoList.add(grupoConsultado);
            }
        } else {
            rolDetalleDto.setGrupos(new ArrayList<GrupoDto>());
        }
        rolActual.setGrupoList(grupoList);

        // Jerarquia de roles
        int nivelesHerencia = Integer.valueOf(parametrosSeguridadEJB
                .consultarValorParametroSeguridad(EnumParametro.NIVELES_HERENCIA_ROLES));
        logger.debug("Estan parametrizados " + nivelesHerencia + " niveles de herencia para los roles");

        // Rol padre
        List<RolDetalleDto> listaPadres = new ArrayList<RolDetalleDto>();
        if (rolDetalleDto.getRolPadre() != null && rolDetalleDto.getRolPadre().getIdRol() > 0) {
            rolActual.setRolPadre(em.find(Rol.class, rolDetalleDto.getRolPadre().getIdRol()));
            obtenerListaPadres(listaPadres, rolActual, 0, nivelesHerencia);// Obtener la lista con los padres hasta el nivel de herencia definido
            asignarPadresRecursivo(rolDetalleDto, 0, nivelesHerencia, listaPadres);// Asignar los datos del rol padre en el dto que genera el
                                                                                   // historico

        } else {
            rolActual.setRolPadre(null);
            rolDetalleDto.setRolPadre(null);
        }

        // Si rol tiene hijos no se puede inactivar
        List<Rol> rolesHijos = consultarRolesPorPadre(rolDetalleDto.getIdRol());
        if (!CollectionUtils.isEmpty(rolesHijos) && !rolDetalleDto.getActivo()) {
            logger.info("No se pudo modificar el rol con Id= " + rolDetalleDto.getIdRol() + " - "
                    + CatalogoError.ROL_ACTIVO_TIENE_HIJOS.getDescError());
            throw new SeguridadException(CatalogoError.ROL_ACTIVO_TIENE_HIJOS);
        }

        // Modificar rol
        em.merge(rolActual);
        logger.debug("Se modifica el rol con Id= " + rolActual.getIdRol() + " -" + rolActual.getNombre());
        cacheRoles.remove(rolDetalleDto.getNombre());
        // Crear historico de la modificacion
        HistoricoRol historicoPersistido = historicoRolEJB.registrarHistoricoPorModificacion(rolDetalleDto, null);

        // Evaluar si tiene roles hijos para que se agregue un nuevo historico a cada uno de ellos y hasta el nivel n de jerarquia
        rolDetalleDto.setRolPadre(null);// Limpiar el rol padre que tenga asignado para que al ser enviado al metodo que actualiza los hijos no se
                                        // cree esta informacion en el xml
        registrarHistoricoRolesHijos(rolDetalleDto, historicoPersistido, nivelesHerencia, listaPadres);
    }

    /**
     * Agregar operacion ingresar si no fue seleccionada en el recurso para crear permiso del rol
     * 
     * @param recursosAplicacion
     * @param rolActual
     */
    private void crearPermisoOperacionIngresar(Map<String, List<RecursoDetalleDto>> recursosAplicacion, Rol rolActual) {
        if (rolActual.getPermisoRecursosOperaciones() == null)
            rolActual.setPermisoRecursosOperaciones(new ArrayList<PermisoRecursoOperacion>());
        for (Iterator<Entry<String, List<RecursoDetalleDto>>> iterator = recursosAplicacion.entrySet().iterator(); iterator
                .hasNext();) {
            Entry<String, List<RecursoDetalleDto>> app = iterator.next();
            List<RecursoDetalleDto> recursoDetalle = app.getValue();
            // Consultar operacion ingresar
            boolean operacionIngresar = false;
            Integer idOpIngreso = null;
            RecursoOperacion recursoOpIngreso = null;
            for (RecursoDetalleDto recursoDetalleDto : recursoDetalle) {
                Recurso recursoIngresar = em.find(Recurso.class, recursoDetalleDto.getIdRecurso());
                for (RecursoOperacion recursoOperacion : recursoIngresar.getRecursoOperacionList()) {
                    Operacion operacion = em.getReference(Operacion.class, recursoOperacion.getOperacion()
                            .getIdOperacion());
                    if (operacion.getNombre().equals(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO)) {
                        idOpIngreso = operacion.getIdOperacion();
                        recursoOpIngreso = recursoOperacion;
                        break;
                    }
                }
                for (OperacionDto operacionDto : recursoDetalleDto.getOperaciones()) {
                    operacionIngresar = operacionDto.getIdOperacion().equals(idOpIngreso);
                }
                // Agregar operacion ingresar si no fue creada
                if (!operacionIngresar && recursoOpIngreso != null) {
                    PermisoRecursoOperacion permisoRecOperacion = new PermisoRecursoOperacion();
                    permisoRecOperacion.setRecursoOperacion(recursoOpIngreso);
                    permisoRecOperacion.setRol(rolActual);
                    rolActual.getPermisoRecursosOperaciones().add(permisoRecOperacion);
                }
            }
        }
    }

    /**
     * Obtiene los padres de un rol hasta el nivel de herencia definido y los agrega al listado recibido en el primer parametro
     * 
     * @param listaPadres
     *            Listado en el cual se van agregando los roles padres
     * @param rolHijo
     *            Rol para el cual se van a consultar los padres
     * @param iteracion
     *            Corresponde a la cantidad de veces que se ha llamado el metodo de manera recursiva
     * @param nivelesHerencia
     *            Numero de nivel de herencia parametrizado y hasta la cual se consultaran los padres. Por Ejemplo si nivelesHerencia=2 se consultara
     *            el padre y el abuelo del rol enviado en caso que los tenga
     */
    private void obtenerListaPadres(List<RolDetalleDto> listaPadres, Rol rolHijo, int iteracion, int nivelesHerencia) {
        if (listaPadres == null) {
            listaPadres = new ArrayList<RolDetalleDto>();
        }
        RolDetalleDto rolDetallePadre;
        if (iteracion < nivelesHerencia) {
            if (rolHijo.getRolPadre() != null) {
                Rol rolPadre = em.find(Rol.class, rolHijo.getRolPadre().getIdRol());
                rolDetallePadre = construirRolDtoCompleto(rolPadre);
                listaPadres.add(rolDetallePadre);
                obtenerListaPadres(listaPadres, rolPadre, iteracion + 1, nivelesHerencia);
            }
        }
    }

    /**
     * Asigna de manera recursiva los padres enviados en la lista, en el Dto enviado en el primer parametro
     * 
     * @param detalleRolPadre
     *            Dto al cual se le asignarán los padres recursivamente
     * @param iteracion
     *            Corresponde a la cantidad de veces que se ha llamado el metodo de manera recursiva
     * @param nivelHerencia
     *            Numero de nivel de herencia parametrizado y hasta el cual se asignaran los padres tomandolos de la posicion de la lista dada por la
     *            iteracion
     * @param listaPadres
     *            Listado con padres del rol y del cual se van tomando aquellos que son asignados de manera recursiva hast alcanzar el nivel de
     *            herencia indicado. Es un ArrayList ordenado, que en la posicion 0 tiene al padre directo, en la posicion 1 al padre del padre y asi
     *            sucesivamente
     */
    private void asignarPadresRecursivo(RolDetalleDto detalleRolPadre, int iteracion, int nivelHerencia,
            List<RolDetalleDto> listaPadres) {
        if (iteracion < nivelHerencia && iteracion < listaPadres.size()) {

            detalleRolPadre.setRolPadre(listaPadres.get(iteracion));
            asignarPadresRecursivo(listaPadres.get(iteracion), iteracion + 1, nivelHerencia, listaPadres);
        }

    }

    /**
     * Actualiza de manera recursiva el historico de todos los niveles de hijos que tenga el Dto enviado como parametro, utilizando la fecha de inicio
     * del historico de dicho padre para definir la fechas de inicio y fin de los historicos de cada hijo afectado
     * 
     * @param detalleRolPadre
     *            Dto del rol padre cuyos hijos seran actualizados en su historico
     * @param historicoPadre
     *            Entidad persistida del historico del rol padre
     * @param nivelesHerencia
     *            Numero de nivel de herencia parametrizado utilizado para saber hasta que nivel debe asignar los padres que van en el historico de
     *            cada rol hijo
     * @param listaPadres
     *            Listado con los padres del rol y de la cual se toman los padres que van en el historico hasta el nivel de herencia parametrizado.Es
     *            un ArrayList ordenado, que en la posicion 0 tiene al padre directo, en la posicion 1 al padre del padre y asi sucesivamente
     */
    private void registrarHistoricoRolesHijos(RolDetalleDto detalleRolPadre, HistoricoRol historicoPadre,
            int nivelesHerencia, List<RolDetalleDto> listaPadres) {
        if (detalleRolPadre.getRecursosAplicacion().isEmpty()) {// No tiene sus datos de detalle completos
            detalleRolPadre = construirRolDtoCompleto(em.find(Rol.class, detalleRolPadre.getIdRol()));
        }
        List<Rol> rolesHijos = consultarRolesPorPadre(detalleRolPadre.getIdRol());
        if (!CollectionUtils.isEmpty(rolesHijos)) {
            List<RolDetalleDto> listaPadresNueva = new ArrayList<RolDetalleDto>();
            for (RolDetalleDto padreDto : listaPadres) {
                padreDto.setRolPadre(null);// Limpiar relaciones de padres que hayan en los objetos
            }
            listaPadresNueva.addAll(listaPadres);
            listaPadresNueva.add(0, detalleRolPadre);// Agrega a la lista de padres de los antecesores, el detalle del rol padre actual para que haga
                                                     // parte del listado de roles padre de los hijos

            for (Rol rolHijo : rolesHijos) {
                RolDetalleDto rolDetalleUltimoHis = construirRolDtoCompleto(rolHijo);
                if (rolHijo.getGrupoList() != null) {
                    GrupoHelper helperGrupos = new GrupoHelper();
                    List<GrupoDto> gruposDto = new ArrayList<GrupoDto>();
                    for (Grupo grupo : rolHijo.getGrupoList()) {
                        gruposDto.add(helperGrupos.toDto(grupo, null));
                    }
                    rolDetalleUltimoHis.setGrupos(gruposDto);
                }

                asignarPadresRecursivo(rolDetalleUltimoHis, 0, nivelesHerencia, listaPadresNueva);// Asignar al Dto que representa el nuevo historico
                                                                                                  // del rol, los roles padre de manera recursiva
                                                                                                  // hasta el nivel de herencia definido
                rolDetalleUltimoHis.setFechaFin(null);
                rolDetalleUltimoHis.setFechaInicio(null);
                rolDetalleUltimoHis.setUsuarioRealizaCambio(new DatosSesionHelper().getUsuarioSesion());

                HistoricoRol historicoPersistido = historicoRolEJB.registrarHistoricoPorModificacion(
                        rolDetalleUltimoHis, historicoPadre.getFechaInicioRol());

                RolDetalleDto detalleRolPadreNuevo = new RolDetalleDto();
                detalleRolPadreNuevo.setIdRol(rolHijo.getIdRol());
                registrarHistoricoRolesHijos(detalleRolPadreNuevo, historicoPersistido, nivelesHerencia,
                        listaPadresNueva);// Por cada rol hijo actualizar a su vez a sus propios hijos
            }
        }
    }

    /**
     * Obtiene un DTO a partir de la entidad enviada asignandole el mapa de permisos sobre las diferentes aplicaciones, este DTO contiene los datos
     * necesarios para el historico XML
     * 
     * @param rol
     *            Entidad para a cua se genera el Dto
     * @return Dto con le detalle del rol
     */
    private RolDetalleDto construirRolDtoCompleto(Rol rol) {
        RolDetalleDto rolDetalle = new RolDetalleDto();
        RolesHelper helper = new RolesHelper();
        helper.toDto(rol, rolDetalle);
        rolDetalle.setRecursosAplicacion(consultarPermisosCompletosRol(rol.getNombre()));
        return rolDetalle;
    }

    private Rol crearPermisos(Rol rol, List<RecursoOperacion> recursosOperacion) {
        for (RecursoOperacion recursoOperacion : recursosOperacion) {
            rol.getPermisoRecursosOperaciones().add(new PermisoRecursoOperacion(recursoOperacion, rol));
        }
        return rol;
    }

    /**
     * De la informacion de entrada(creacion,actualizacion) de un rol obtiene los RecursoOperacion a los que va a tener permiso
     * 
     * @param recursosRol
     *            listado de recursos con los recursos operacion asociados
     * @return listado de recursos operacion a los que tendra permiso un rol
     */
    private List<RecursoOperacion> procesarOperacionesRecurso(Map<String, List<RecursoDetalleDto>> recursosRol) {
        final List<RecursoOperacion> recursosOperacion = new ArrayList<>();
        final Collection<List<RecursoDetalleDto>> values = recursosRol.values();
        for (List<RecursoDetalleDto> recs : values) {
            for (RecursoDetalleDto recurso : recs) {
                for (OperacionDto recOp : recurso.getOperaciones()) {
                    recursosOperacion.add(new RecursoOperacion(recOp.getIdOperacion()));
                }
            }
        }
        return recursosOperacion;
    }

    @Override
    public RolDetalleDto consultarPermisosRol(final String nombreRol) {
        RolDetalleDto rolDto = null;
        final Object object = cacheRoles.get(nombreRol);
        if (object == null) {
            logger.infov("RolEJB.consultarPermisosRol() - Rol {0} no se encuentra en el cache", nombreRol);
            final GenericDao<RolRecursoOperacion> rolRecDao = new GenericDao<>(RolRecursoOperacion.class, em);
            // Obtengo todos lo recursos-operaciones asociados a este rol
            List<RolRecursoOperacion> operacionesRec = rolRecDao.findByAttribute("rolNombre", nombreRol);

            if (operacionesRec.isEmpty()) {
                return null;
            }

            rolDto = new RolDetalleDto();
            rolDto.setIdRol(operacionesRec.get(0).getIdRol());
            rolDto.setNombre(nombreRol);

            Map<String, List<RecursoDetalleDto>> recursos = rolDto.getRecursosAplicacion();

            List<RecursoDetalleDto> recsApp = null;
            RecursoDetalleDto recursoDto = null;
            RolRecursoOperacion entidadOperacion = null;

            for (Iterator<RolRecursoOperacion> iterator = operacionesRec.iterator(); iterator.hasNext();) {
                entidadOperacion = iterator.next();
                // Recursos de la aplicacion
                recsApp = recursos.get("" + entidadOperacion.getIdAplicacion());
                if (recsApp == null) {
                    // Si no hay recursos para la aplicacion, crea el arreglo vacio
                    recsApp = new ArrayList<>();
                    recursos.put("" + entidadOperacion.getIdAplicacion(), recsApp);
                }
                // Busca si el recurso actual ya existe
                recursoDto = new RecursoDetalleDto(entidadOperacion.getRecursoNombre());
                int index = recsApp.indexOf(recursoDto);
                if (index != -1) {
                    recursoDto = recsApp.get(index);
                } else {
                    // Si no existe lo agrega
                    recsApp.add(recursoDto);
                }
                // Agrega la operacion al recurso nuevo o existente
                recursoDto.getOperaciones().add(new OperacionDto(entidadOperacion.getOperacionNombre()));
            }
            cacheRoles.put(nombreRol, ObjectToXML.objectToXml(rolDto));
            logger.infov("RolEJB.consultarPermisosRol() - Rol {0} se agrega al cache", nombreRol);
        } else {
            rolDto = ObjectToXML.xmlToObject(RolDetalleDto.class, (String) object);
        }
        return rolDto;
    }

    @Override
    public List<RolDetalleDto> consultarRoles(boolean rolActivo) {
        List<RolDetalleDto> rolesDto = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r FROM Rol r WHERE r.nombre NOT IN(:nombreRolAdmin,:nombreRolPublico)");
        if (rolActivo)
            sql.append(" AND r.activo= :activo");
        sql.append(" ORDER BY r.nombre");
        TypedQuery<Rol> query = em.createQuery(sql.toString(), Rol.class);
        query.setParameter("nombreRolAdmin", ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN);
        query.setParameter("nombreRolPublico", ConstantesSeguridad.NOMBRE_ROL_PUBLICO);
        if (rolActivo)
            query.setParameter("activo", true);
        List<Rol> roles = query.getResultList();
        for (Rol rol : roles) {
            RolDetalleDto roldetalle = new RolDetalleDto();
            roldetalle.setIdRol(rol.getIdRol());
            roldetalle.setNombre(rol.getNombre());
            roldetalle.setActivo(rol.isActivo());
            roldetalle.setDescripcion(rol.getDescripcion());
            rolesDto.add(roldetalle);
        }
        logger.debug("Se consultan  " + rolesDto.size() + " roles");
        return rolesDto;
    }

    /**
     * Consulta los nombres de los padres de los roles indicados
     * 
     * @param nombresRol
     *            Listado con los roles cuyos padres seran consultados
     * @return Listado con los nombres de los padres de los roles enviados como parametro o la lista vacia si no se encuentra ningun padre
     * 
     * @author claudia.rodriguez
     */
    public List<String> consultarHerenciaRoles(List<String> nombresRol) {
        if (!CollectionUtils.isEmpty(nombresRol)) {
            TypedQuery<String> query = em.createNamedQuery(Rol.SQ_ROLES_PADRE_BY_NOMBRE, String.class);
            query.setParameter("nombres", nombresRol);
            List<String> nombresPadres = query.getResultList();
            if (nombresPadres == null)
                return Arrays.asList();
            else
                return nombresPadres;
        } else {
            return Arrays.asList();
        }

    }

    /**
     * Agrega a una lista los nombres de los roles padres para los roles enviados iterando desde el valor enviado en el parametro iteracion hasta
     * alcanzar el valor en el nivel de herencia especificado
     * 
     * @param nombresRolesPadre
     *            Listado donde se agregan todos los nombres de los padres consultados
     * @param nombresRoles
     *            Listado de roles para los cuales se consultan los padres
     * @param iteracion
     *            Valor de la iteracion de consulta de los padres
     * @param nivelesHerencia
     *            Valor que indica el nivel de la herencia hasta el cual se deben consultar los padres de los roles
     * @author claudia.rodriguez
     */
    public void consultarHerenciaRolesCompleta(List<String> nombresRolesPadre, List<String> nombresRoles,
            int iteracion, int nivelesHerencia) {
        if (iteracion < nivelesHerencia) {
            List<String> nombresPadres = consultarHerenciaRoles(nombresRoles);
            if (!CollectionUtils.isEmpty(nombresPadres)) {
                nombresRolesPadre.addAll(nombresPadres);
                consultarHerenciaRolesCompleta(nombresRolesPadre, nombresPadres, iteracion + 1, nivelesHerencia);
            }
        }
    }

    /**
     * Consulta los recursos-operaciones del rol y los retorna en un mapa, clasificandolos por aplicacion. Si el rol no tienen algun permiso en alguna
     * aplicacion se retorna el mapa vacio
     * 
     * @param nombreRol
     *            NOmbre del rol a consultar los permisos
     * @return Mapa con Key: Nombre de la aplicacion y Value:Lista de RecursosDetalle, cada uno con sus respectivas operaciones permitidas para el rol
     */
    public Map<String, List<RecursoDetalleDto>> consultarPermisosCompletosRol(String nombreRol) {

        Map<String, List<RecursoDetalleDto>> permisos = new HashMap<String, List<RecursoDetalleDto>>();

        // Consulta de la vista RolRecursoOperacion
        final GenericDao<RolRecursoOperacion> rolRecDao = new GenericDao<>(RolRecursoOperacion.class, em);
        List<RolRecursoOperacion> listRolrecursoOperacion = rolRecDao.findByAttribute("rolNombre", nombreRol);

        List<RecursoDetalleDto> listRecursosDetalle = null;
        RecursoDetalleDto recursoDto = null;
        RolRecursoOperacion rolRecursoOperacion = null;

        for (Iterator<RolRecursoOperacion> iterator = listRolrecursoOperacion.iterator(); iterator.hasNext();) {
            rolRecursoOperacion = iterator.next();
            // Recursos de la aplicacion
            listRecursosDetalle = permisos.get("" + rolRecursoOperacion.getNombreAplicacion());
            if (listRecursosDetalle == null) {
                // Si no hay recursos para la aplicacion, crea el arreglo vacio
                listRecursosDetalle = new ArrayList<>();
                permisos.put("" + rolRecursoOperacion.getNombreAplicacion(), listRecursosDetalle);
            }
            // Busca si el recurso actual ya existe
            recursoDto = new RecursoDetalleDto(rolRecursoOperacion.getRecursoNombre());
            recursoDto.setIdRecurso(rolRecursoOperacion.getIdRecurso());
            recursoDto.setDescripcion(rolRecursoOperacion.getRecursoDescripcion());
            int index = listRecursosDetalle.indexOf(recursoDto);
            if (index != -1) {
                recursoDto = listRecursosDetalle.get(index);
            } else {
                // Si no existe lo agrega
                listRecursosDetalle.add(recursoDto);
            }
            // Agrega la operacion al recurso nuevo o existente
            recursoDto.getOperaciones().add(
                    new OperacionDto(rolRecursoOperacion.getIdRecursoOperacion(), rolRecursoOperacion
                            .getOperacionNombre()));
        }
        return permisos;
    }

    private List<Rol> consultarRolesPorPadre(Integer idRol) {
        TypedQuery<Rol> query = em.createNamedQuery(Rol.SQ_ROL_BY_PADRE, Rol.class);
        query.setParameter("idRolPadre", idRol);
        List<Rol> roles = query.getResultList();
        return roles;
    }

    @Override
    public List<HistoricoRolDto> consultarDetalleHistoricoRol(int idRol, Date fechaInicial, Date fechaFinal) {
        return historicoRolEJB.consultarDetalleHistoricoRol(idRol, fechaInicial, fechaFinal);
    }

    @Override
    public Date consultarFechaCreacionRol(Integer idRol) {
        TypedQuery<HistoricoRol> query = em.createNamedQuery(HistoricoRol.SQ_HISTORICO_CREACION_BY_ROL,
                HistoricoRol.class);
        query.setParameter("idRol", idRol);
        HistoricoRol primerHistorico = query.getSingleResult();
        return primerHistorico.getFechaInicioRol();
    }

    @Override
    public HistoricoRolDto consultarUltimoHistoricoRol(int idRol, Date fechaReferencia) {
        return historicoRolEJB.consultarUltimoHistoricoRol(idRol, fechaReferencia);
    }

}
