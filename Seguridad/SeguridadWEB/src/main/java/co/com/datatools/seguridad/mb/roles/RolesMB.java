package co.com.datatools.seguridad.mb.roles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

import co.com.datatools.seguridad.dto.autorizacion.AplicacionDTO;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.interfaces.IRGrupo;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.interfaces.IRRol;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumClaseGrupo;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean de sesión para los flujos relacionados con el CRUD de Roles, asigna y obtiene valores de los DTOs: RolesFl, CrearRolFL y ModificarRolFl
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class RolesMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(RolesMB.class.getName());

    private static final String NOMBRE_BUNDLE_ROL = "mensajesRol";

    @EJB
    private IRRol rolEjb;

    @EJB
    private IRGrupo grupoEjb;

    @EJB
    private IRParametrosSeguridad parametroEjb;

    @EJB
    private IRCatalogosSeguridad catalogoSeguridadEjb;

    private boolean recursoSeleccionado;
    private List<SelectItem> lEstadosRol;

    public RolesMB() {
        logger.debug("RolesMB::RolesMB");
        cargarEstados();
    }

    /**
     * Consulta el mapa de todos los recursos-operaciones de las aplicaciones existentes y construye el listado de objetos WrapperAplicacion que
     * alimenta el componente treeTable que permite la selección de los permisos del rol
     */
    public void consultarPerfilCompletoAplicaciones() {
        logger.debug("RolesMB::consultarPerfilCompletoAplicaciones");
        CrearRolFL crearRolFl = findFlowObject(CrearRolFL.class, CrearRolFL.NOMBRE_BEAN);
        List<WrapperAplicacion> recursosApp = new ArrayList<>();
        RolDetalleDto rol = rolEjb.consultarPerfilCompletoAplicacion(crearRolFl.getIdAplicacion());
        for (Iterator<Entry<String, List<RecursoDetalleDto>>> iterator = rol.getRecursosAplicacion().entrySet()
                .iterator(); iterator.hasNext();) {
            Entry<String, List<RecursoDetalleDto>> app = iterator.next();
            recursosApp.add(new WrapperAplicacion(app));
        }
        crearRolFl.setRecursosApp(recursosApp);
    }

    /**
     * Se encarga de consultar la lista de aplicaciones que administra seguridad
     */
    public void consultarAplicaciones() {
        logger.debug("RecursosMB.consultarAplicaciones()");
        RolesFL rolesFl = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);
        CrearRolFL crearRolFl = findFlowObject(CrearRolFL.class, CrearRolFL.NOMBRE_BEAN);
        if (crearRolFl.getlAplicaciones() == null) {
            Map<String, String> lAplicaciones = new HashMap<>();
            lAplicaciones = catalogoSeguridadEjb.consultarAplicaciones();
            crearRolFl.setlAplicaciones(lAplicaciones);
        }
        if (rolesFl.getlAplicaciones() == null) {
            Map<String, String> lAplicaciones = new HashMap<>();
            lAplicaciones = catalogoSeguridadEjb.consultarAplicaciones();
            rolesFl.setlAplicaciones(lAplicaciones);
        }
    }

    /**
     * Carga la lista de filtro de rol padre de la consulta de roles
     */
    public void cargarListaRolPadre() {
        logger.debug("RolesMB::cargarListaRolPadre");
        List<RolDetalleDto> roles = rolEjb.consultarRoles(false);
        ArrayList<SelectItem> lRolesPadre = new ArrayList<>();
        for (RolDetalleDto rolDetalleDto : roles) {
            lRolesPadre.add(new SelectItem(rolDetalleDto.getIdRol(), rolDetalleDto.getNombre()));
        }
        RolesFL rolesFl = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);
        rolesFl.setlRolesPadre(lRolesPadre);
        consultarAplicaciones();
    }

    /**
     * Llena la lista de Estados del Rol con los valores posibles:Activo/Inactivo
     */
    public void cargarEstados() {
        logger.debug("RolesMB::cargarEstados");
        lEstadosRol = new ArrayList<>();
        lEstadosRol.add(new SelectItem(Boolean.TRUE, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString(
                "label_activo")));
        lEstadosRol.add(new SelectItem(Boolean.FALSE, getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString(
                "label_inactivo")));
    }

    public void inicializarComponentesCreacion() {
        cargarGruposActivos();
        consultarAplicaciones();
    }

    /**
     * Consulta los grupos activos en el sistema y los carga en la lista de grupos disponibles para la creacion del rol
     */
    public void cargarGruposActivos() {
        logger.debug("RolesMB::cargarGruposActivos");
        CrearRolFL crearRolFl = findFlowObject(CrearRolFL.class, CrearRolFL.NOMBRE_BEAN);

        if (crearRolFl.getGrupos() == null) {
            List<GrupoDto> source = new ArrayList<GrupoDto>();
            List<GrupoDto> target = new ArrayList<GrupoDto>();
            // Consultar grupos activos
            GrupoDto grupoFiltro = new GrupoDto();
            grupoFiltro.setActivo(Boolean.TRUE);
            grupoFiltro.setClase(EnumClaseGrupo.Roles.name());
            List<GrupoDto> gruposActivos = grupoEjb.consultarGrupos(grupoFiltro);
            for (GrupoDto grupoDto : gruposActivos) {
                source.add(grupoDto);
            }
            crearRolFl.setGrupos(new DualListModel<GrupoDto>(source, target));
        }

    }

    /**
     * Invoca al ejb para almacenar un nuevo rol, validando que se haya ingresado a menos un permiso para el rol
     */
    public boolean crearRol() {
        logger.debug("RolesMB::crearRol");
        RolDetalleDto rolPersistir = new RolDetalleDto();
        CrearRolFL crearRolFl = findFlowObject(CrearRolFL.class, CrearRolFL.NOMBRE_BEAN);
        boolean permiteContinuar = false;
        boolean creaRol = false;
        Map<String, List<RecursoDetalleDto>> recursosAplicacion = new HashMap<>();
        for (WrapperAplicacion wrapApp : crearRolFl.getRecursosApp()) {
            ArrayList<RecursoDetalleDto> recursosDetalle = new ArrayList<>();
            ArrayList<RecursoDetalleDto> recursosConPermisos = validarSeleccionados(wrapApp.getArbolRecursos(),
                    recursosDetalle);
            if (recursosConPermisos.size() > 0) {
                permiteContinuar = true;
                recursosAplicacion.put(wrapApp.getNombreAplicacion(), recursosConPermisos);
            }
        }
        if (!permiteContinuar) {
            addErrorMessage(NOMBRE_BUNDLE_ROL, "msg_rol_requiere_permiso");
        } else {
            rolPersistir.setRecursosAplicacion(recursosAplicacion);
            rolPersistir.setNombre(crearRolFl.getNombre());
            rolPersistir.setDescripcion(crearRolFl.getDescripcion());
            if (crearRolFl.getIdRolPadre() != null && !crearRolFl.getIdRolPadre().equals("")) {
                RolDetalleDto rolPadre = new RolDetalleDto();
                rolPadre.setIdRol(Integer.valueOf(crearRolFl.getIdRolPadre()));
                rolPersistir.setRolPadre(rolPadre);
            }
            if (crearRolFl.getGrupos().getTarget().size() > 0) {
                rolPersistir.setGrupos(crearRolFl.getGrupos().getTarget());
            }
            try {
                rolEjb.registrarRol(rolPersistir);
                addInfoMessage(NOMBRE_BUNDLE_ROL, "msg_rol_guardado");
                creaRol = true;
            } catch (SeguridadException e) {
                SeguridadErrorHandler.handleException(e);
            }
        }
        return creaRol;
    }

    /**
     * Recorre recursivamente los RecursoDetalleDtos que tiene configurados una aplicacion especifica(la aplicacion representada en un objeto
     * TreeNode) y obtiene un listado con aquellos que tienen operaciones seleccionadas
     * 
     * @param treeNode
     * @param recursosDetalle
     * @return
     */
    public ArrayList<RecursoDetalleDto> validarSeleccionados(TreeNode treeNode,
            ArrayList<RecursoDetalleDto> recursosDetalle) {
        logger.debug("RolesMB::validar seleccionados");
        List<TreeNode> children = treeNode.getChildren();
        if (children != null) {
            for (TreeNode treeNode2 : children) {
                WrapperRecurso wr = (WrapperRecurso) treeNode2.getData();
                logger.debug("Descripcion recurso validar == " + wr.getRecurso().getDescripcion());
                List<OperacionDto> selected = wr.getSeleccionOperacion();
                if (selected.size() > 0) {
                    // El rol tiene permisos sobre el recurso
                    RecursoDetalleDto recursoDetaDto = new RecursoDetalleDto();
                    recursoDetaDto.setIdRecurso(wr.getRecurso().getIdRecurso());
                    recursoDetaDto.setNombreRecurso(wr.getRecurso().getNombreRecurso());
                    recursoDetaDto.setDescripcion(wr.getRecurso().getDescripcion());
                    List<OperacionDto> operaciones = new ArrayList<>();

                    for (OperacionDto operDto : selected) {
                        logger.debug("** SELECTED == " + operDto.getIdOperacion() + " - "
                                + operDto.getNombreOperacion());
                        operaciones.add(new OperacionDto(operDto.getIdOperacion(), operDto.getNombreOperacion()));
                    }
                    recursoDetaDto.setOperaciones(operaciones);
                    recursosDetalle.add(recursoDetaDto);
                }
                validarSeleccionados(treeNode2, recursosDetalle);
            }
        }
        return recursosDetalle;
    }

    /**
     * Recorre recursivamente el TreeNode enviado como parametro hasta encontrar el Recurso con el id enviado, evalua si dicho recurso esta
     * seleccionado o no y de acuerdo a ello selecciona/deselecciona todas sus operaciones
     * 
     * @param treeNode
     * @param idRecurso
     */
    public void buscarSeleccionado(TreeNode treeNode, Integer idRecurso) {
        logger.debug("RolesMB::buscarSeleccionado");
        List<TreeNode> children = treeNode.getChildren();
        if (children != null) {
            for (TreeNode treeNode2 : children) {
                WrapperRecurso wr = (WrapperRecurso) treeNode2.getData();
                if (wr.getRecurso().getIdRecurso().equals(idRecurso)) {
                    logger.debug("Encuentra recurso en arbol");
                    logger.debug("Seleccionado? = " + wr.isSeleccionado());
                    if (wr.isSeleccionado()) {
                        seleccionarOperacionesRecurso(treeNode2);
                    } else {
                        desSeleccionarOperacionesRecurso(treeNode2);
                    }
                    break;
                }
                buscarSeleccionado(treeNode2, idRecurso);
            }
        }
    }

    /**
     * Selecciona el Recurso asociado al TreeNode enviado y todas sus operaciones. Adicionalmente evalua si tiene recursos hijos y los selecciona
     * recursivamente
     * 
     * @param treeNode
     *            TreeNode asociado al recurso que sera seleccionado
     */
    public void seleccionarOperacionesRecurso(TreeNode treeNode) {
        logger.debug("RolesMB::seleccionarOperacionesRecurso");
        WrapperRecurso wr = (WrapperRecurso) treeNode.getData();
        wr.setSeleccionado(true);
        wr.getSeleccionOperacion().clear();
        if (wr.getRecurso().getOperaciones().size() > 0) {
            for (OperacionDto operacionRec : wr.getRecurso().getOperaciones()) {
                wr.getSeleccionOperacion().add(operacionRec);
            }
        }
        List<TreeNode> children = treeNode.getChildren();
        if (children != null) {
            for (TreeNode treeNode2 : children) {
                seleccionarOperacionesRecurso(treeNode2);
            }
        }
    }

    /**
     * Deselecciona el Recurso asociado al TreeNode enviado y todas sus operaciones. Adicionalmente evalua si tiene recursos hijos y los deselecciona
     * recursivamente
     * 
     * @param treeNode
     *            TreeNode asociado al recurso que sera deseleccionado
     */
    public void desSeleccionarOperacionesRecurso(TreeNode treeNode) {
        logger.debug("RolesMB::desSeleccionarOperacionesRecurso");
        WrapperRecurso wr = (WrapperRecurso) treeNode.getData();
        wr.setSeleccionado(false);
        wr.getSeleccionOperacion().clear();
        List<TreeNode> children = treeNode.getChildren();
        if (children != null) {
            for (TreeNode treeNode2 : children) {
                desSeleccionarOperacionesRecurso(treeNode2);
            }
        }
    }

    /**
     * Invoca al ejb para eliminar el rol seleccionado, se envia a la interfaz el mensaje de operacion exitosa o de error en caso de que el rol no
     * pueda ser eliminado
     */
    public void eliminarRol() {
        logger.debug("RolesMB::eliminarRol");
        RolDetalleDto rolSeleccionado = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN).getRolSeleccionado();
        try {
            rolEjb.eliminarRol(rolSeleccionado.getIdRol());
            addInfoMessage(NOMBRE_BUNDLE_ROL, "msg_rol_eliminado");
            RolesFL rolesFL = (RolesFL) findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);
            rolesFL.getResultadoConsulta().remove(rolSeleccionado);
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }

    }

    /**
     * Invoca al ejb para consultar los roles de acuerdo a los filtros de consulta ingresados
     */
    public void consultarRoles() {
        logger.debug("RolesMB::consultarRoles");
        RolesFL rolesFl = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);
        rolesFl.setConsultaRealizada(false);
        // Verificar los filtros ingresados
        RolDetalleDto rolFiltros = new RolDetalleDto();
        rolFiltros.setNombre(rolesFl.getNombre());
        if (rolesFl.getEstadoSeleccionado() != null) {
            rolFiltros.setActivo(new Boolean(rolesFl.getEstadoSeleccionado()));
        }
        if (rolesFl.getRolPadreFiltro() != null && rolesFl.getRolPadreFiltro() != null) {
            RolDetalleDto rolPadreFiltro = new RolDetalleDto();
            rolPadreFiltro.setIdRol(Integer.valueOf(rolesFl.getRolPadreFiltro()));
            rolFiltros.setRolPadre(rolPadreFiltro);
        }
        // Aplicacion
        if (rolesFl.getIdAplicacion() != null) {
            AplicacionDTO aplicacionDTO = new AplicacionDTO();
            aplicacionDTO.setIdAplicacion(Integer.parseInt(rolesFl.getIdAplicacion()));
            rolFiltros.setAplicacionDTO(aplicacionDTO);
        }
        List<RolDetalleDto> resultadoConsulta = new ArrayList<>();
        resultadoConsulta = rolEjb.consultarRoles(rolFiltros);
        rolesFl.setResultadoConsulta(resultadoConsulta);
        rolesFl.setRolSeleccionado(null);
        rolesFl.setConsultaRealizada(true);
    }

    /**
     * Realiza la consulta de los recursos segun los filtros ingresados en la ventana emergente de busqueda de recursos para asignar un recurso padre
     */
    public void buscarRoles() {
        logger.debug("RolesMB::buscarRoles");
        RolesFL rolesFl = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);
        rolesFl.setConsultaPadreRealizada(false);
        // Verificar los filtros
        RolDetalleDto dtoFiltro = new RolDetalleDto();
        dtoFiltro.setNombre(rolesFl.getNombre());
        dtoFiltro.setActivo(true);

        List<RolDetalleDto> resultado = rolEjb.consultarRoles(dtoFiltro);
        // Si esta editando un rol, Eliminar del resultado el propio rol al que se le desea asignar padre
        if (rolesFl.getRolSeleccionado() != null) {
            List<RolDetalleDto> rolesEliminar = new ArrayList<RolDetalleDto>();
            for (RolDetalleDto rolDetalleDto : resultado) {
                if (rolDetalleDto.getIdRol().equals(rolesFl.getRolSeleccionado().getIdRol())
                        || rolDetalleDto.getNombre().equalsIgnoreCase(ConstantesSeguridad.NOMBRE_ROL_PUBLICO)) {
                    rolesEliminar.add(rolDetalleDto);
                }
            }

            for (RolDetalleDto rolDetalleDto : rolesEliminar) {
                resultado.remove(rolDetalleDto);
            }

        }
        rolesFl.setResultadoConsultaRolPadre(resultado);
        rolesFl.setRolPadreSeleccionado(null);
        rolesFl.setConsultaPadreRealizada(true);
    }

    /**
     * Asigna el rol padre seleccionado al rol que se esta creando o modificando
     */
    public void asignarRolPadre() {
        logger.debug("RolesMB::asignarRolPadre");
        RolesFL rolesFL = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);
        RolDto rolPadreSel = rolesFL.getRolPadreSeleccionado();
        RolDetalleDto detRolPadre = rolEjb.consultarRol(rolPadreSel.getIdRol());

        List<WrapperAplicacion> recursosApp;

        if (rolesFL.getRolSeleccionado() != null) {
            // Modifica rol
            ModificarRolFL modificarRolFL = findFlowObject(ModificarRolFL.class, ModificarRolFL.NOMBRE_BEAN);
            modificarRolFL.setIdRolPadre(String.valueOf(rolPadreSel.getIdRol()));
            modificarRolFL.setNombreRolPadre(rolPadreSel.getNombre());
            recursosApp = modificarRolFL.getRecursosAppRol();
        } else {
            // Crea rol
            CrearRolFL crearRolFL = findFlowObject(CrearRolFL.class, CrearRolFL.NOMBRE_BEAN);
            crearRolFL.setIdRolPadre(String.valueOf(rolPadreSel.getIdRol()));
            crearRolFL.setNombreRolPadre(rolPadreSel.getNombre());
            recursosApp = crearRolFL.getRecursosApp();
        }

        sincronizarOperacionesPadre(detRolPadre, recursosApp);
    }

    /**
     * Realiza la sincronizacion entre las operaciones del padre seleccionado con la informacion del rol q se esta creando o modificando
     * 
     * @param detRolPadre
     *            dto con la informacion de los recursos del padre
     * @param recursosApp
     *            informacion del rol actual, arbol de recursos
     */
    private void sincronizarOperacionesPadre(RolDetalleDto detRolPadre, List<WrapperAplicacion> recursosApp) {
        // Limpia la configuracion actual de las operaciones resaltadas
        for (WrapperAplicacion wrapperAplicacion : recursosApp) {
            final List<RecursoDetalleDto> recursos = wrapperAplicacion.getRecursos();
            for (RecursoDetalleDto rec : recursos) {
                limpiarOperacionesRecursoMarcadas(rec);
            }
        }
        // Recorre todo el arbol de operaciones para los recursos del rol actual
        for (WrapperAplicacion wAppActual : recursosApp) {
            final List<RecursoDetalleDto> recPadreList = detRolPadre.getRecursosAplicacion().get(
                    wAppActual.getNombreAplicacion());
            if (recPadreList != null) {
                final List<RecursoDetalleDto> recActualList = wAppActual.getRecursos();
                for (RecursoDetalleDto recActual : recActualList) {
                    marcarOperacionesPadre(recActual, recPadreList);
                }
            }

        }
    }

    private void limpiarOperacionesRecursoMarcadas(RecursoDetalleDto recurso) {
        for (OperacionDto ope : recurso.getOperaciones()) {
            WrapperOperacion wOpe = (WrapperOperacion) ope;
            wOpe.resetearEstilo();
        }
        for (RecursoDetalleDto rec : recurso.getHijos()) {
            limpiarOperacionesRecursoMarcadas(rec);
        }
    }

    private void marcarOperacionesPadre(RecursoDetalleDto recActual, List<RecursoDetalleDto> recPadreList) {
        // Busco el recurso actual dentro de los recursos del padre en caso de q tenga operaciones
        if (!recActual.getOperaciones().isEmpty()) {
            for (Iterator<RecursoDetalleDto> it = recPadreList.iterator(); it.hasNext();) {
                RecursoDetalleDto recPadre = it.next();
                if (recPadre.getIdRecurso().equals(recActual.getIdRecurso())) {
                    // Los recursos son iguales, sincronizo las operaciones
                    for (OperacionDto opActual : recActual.getOperaciones()) {
                        WrapperOperacion wOpActual = (WrapperOperacion) opActual;
                        if (recPadre.getOperaciones().contains(new OperacionDto(wOpActual.getNombreOperacion()))) {
                            wOpActual.aplicarEstiloResaltar();
                        }
                    }
                    it.remove();
                    break;
                }
            }
        }
        // Recursividad para los hijos del recurso actual
        for (RecursoDetalleDto recHijoActual : recActual.getHijos()) {
            marcarOperacionesPadre(recHijoActual, recPadreList);
        }
    }

    /**
     * Carga en la interfaz de la modificacion de rol, todos los datos del recurso que se ha seleccionado para modificar
     */
    public void cargarDetalleRol() {
        logger.debug("RolesMB::cargarDetalleRol");
        RolesFL rolesFL = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);
        RolDetalleDto rolDetalleSel = rolesFL.getRolSeleccionado();

        ModificarRolFL modificarRolFL = findFlowObject(ModificarRolFL.class, ModificarRolFL.NOMBRE_BEAN);
        if (modificarRolFL.isPrimerIngreso()) {
            RolDetalleDto detalleRol = rolEjb.consultarRol(rolDetalleSel.getIdRol());
            if (detalleRol != null) {
                // recursosAppRol = detalleRol.getRecursosAplicacion();
                modificarRolFL.setRolModificar(detalleRol);

                // Aplicacion
                modificarRolFL.setNombreAplicacion(detalleRol.getAplicacionDTO().getNombreAplicacion());

                // Evaluar los grupos disponibles y asignados
                List<GrupoDto> sourceGr = new ArrayList<GrupoDto>();
                List<GrupoDto> targetGr = new ArrayList<GrupoDto>();
                if (detalleRol.getGrupos() != null && detalleRol.getGrupos().size() > 0) {
                    targetGr = detalleRol.getGrupos();
                }
                // Consultar grupos activos en bd
                GrupoDto grupoFiltro = new GrupoDto();
                grupoFiltro.setActivo(Boolean.TRUE);
                grupoFiltro.setClase(EnumClaseGrupo.Roles.name());
                List<GrupoDto> gruposActivos = grupoEjb.consultarGrupos(grupoFiltro);

                boolean grupoEnUsuario = false;
                for (GrupoDto grupoDto : gruposActivos) {
                    grupoEnUsuario = false;
                    if (detalleRol.getGrupos() != null && detalleRol.getGrupos().size() > 0) {
                        for (GrupoDto grupoDtoRol : detalleRol.getGrupos()) {
                            if (grupoDto.getIdGrupo().equals(grupoDtoRol.getIdGrupo())) {
                                grupoEnUsuario = true;
                                break;
                            }
                        }
                    }
                    if (!grupoEnUsuario)
                        sourceGr.add(grupoDto);
                }
                modificarRolFL.setGruposRol(new DualListModel<GrupoDto>(sourceGr, targetGr));

                // Obtener todos los recursos de las aplicaciones
                List<WrapperAplicacion> recursosApp = new ArrayList<>();
                RolDetalleDto rol = rolEjb.consultarPerfilCompletoAplicacion(detalleRol.getAplicacionDTO()
                        .getIdAplicacion().toString());
                for (Iterator<Entry<String, List<RecursoDetalleDto>>> iterator = rol.getRecursosAplicacion().entrySet()
                        .iterator(); iterator.hasNext();) {
                    Entry<String, List<RecursoDetalleDto>> app = iterator.next();
                    recursosApp.add(new WrapperAplicacion(app));
                }
                // Recorrer el arbol y Seleccionar los permisos que ya tenga asignados el rol
                for (WrapperAplicacion wrapApp : recursosApp) {
                    recorrerArbol(wrapApp.getArbolRecursos(), wrapApp.getNombreAplicacion(),
                            detalleRol.getRecursosAplicacion());
                }

                modificarRolFL.setRecursosAppRol(recursosApp);
                if (detalleRol.getRolPadre() != null) {
                    modificarRolFL.setNombreRolPadre(detalleRol.getRolPadre().getNombre());
                    modificarRolFL.setIdRolPadre(String.valueOf(detalleRol.getRolPadre().getIdRol()));
                    RolDetalleDto detRolPadre = rolEjb.consultarRol(detalleRol.getRolPadre().getIdRol());
                    sincronizarOperacionesPadre(detRolPadre, modificarRolFL.getRecursosAppRol());
                }
                modificarRolFL.setPrimerIngreso(false);
            }
        }
    }

    /**
     * Recorre recursivamente el objeto TreeNode raiz de una aplicacion especifica y compara cada recurso del arbol contra los recursos y operaciones
     * que tenga permitidas el usuario, con el fin de seleccionar aquellas que ya tiene asignadas el usuario
     * 
     * @param treeNode
     * @param nombreApp
     */
    public void recorrerArbol(TreeNode treeNode, String nombreApp, Map<String, List<RecursoDetalleDto>> recursosAppRol) {
        logger.debug("RolesMB::recorrerArbol");
        List<RecursoDetalleDto> permisosAplicacionRol = recursosAppRol.get(nombreApp);
        if (permisosAplicacionRol != null) {
            List<TreeNode> children = treeNode.getChildren();
            if (children != null) {
                for (TreeNode treeNode2 : children) {
                    WrapperRecurso wr = (WrapperRecurso) treeNode2.getData();
                    logger.debug("Descripcion recurso == " + wr.getRecurso().getDescripcion());
                    if (wr.getRecurso().getOperaciones().size() > 0) {
                        // Buscar en los permisos del usuario, las operaciones permitidas para ese recurso
                        for (RecursoDetalleDto recursoDetallePermisoDto : permisosAplicacionRol) {
                            if (wr.getRecurso().getIdRecurso().equals(recursoDetallePermisoDto.getIdRecurso())) {
                                for (OperacionDto operPermisos : recursoDetallePermisoDto.getOperaciones()) {
                                    for (OperacionDto operRecurso : wr.getRecurso().getOperaciones()) {
                                        if (operPermisos.getIdOperacion().equals(operRecurso.getIdOperacion())) {
                                            wr.getSeleccionOperacion().add(operRecurso);
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                    List<OperacionDto> selected = wr.getSeleccionOperacion();
                    if (selected.size() > 0) {
                        if (selected.size() == wr.getRecurso().getOperaciones().size()) {
                            // Si todas las operaciones estan seleccionadas, el recurso debe aperecer seleccionado
                            wr.setSeleccionado(true);
                        }
                        expandirPadres(treeNode2);

                        for (OperacionDto operDto : selected) {
                            logger.debug("**SELECTED == " + operDto.getIdOperacion() + " - "
                                    + operDto.getNombreOperacion());
                        }
                    }
                    recorrerArbol(treeNode2, nombreApp, recursosAppRol);
                }
            }
        }
    }

    /**
     * Obtiene el padre del TreeNode enviado y lo expande, invoca recursivamente el metodo para expandir toda la jeraquia de nodos padre
     * 
     * @param treeNode
     *            Nodo cuyos nodos superiores/padres seran seleccionados
     */
    public void expandirPadres(TreeNode treeNode) {
        logger.debug("RolesMB::expandirPadres");
        if (treeNode.getParent() != null) {
            treeNode.getParent().setExpanded(true);
            expandirPadres(treeNode.getParent());
        }
    }

    /**
     * Invoca el ejb para modificar los datos del rol seleccionado validando primero que si se haya seleccionado al menos un permiso para el rol.
     * Envia a la interfaz,un mensaje de operacion exitosa o de eror en caso de que la operacion no haya sido realizada
     */
    public void actualizarRol() {
        logger.debug("RolesMB::actualizarRol");
        ModificarRolFL modificaRolFl = findFlowObject(ModificarRolFL.class, ModificarRolFL.NOMBRE_BEAN);
        boolean permiteContinuar = false;
        Map<String, List<RecursoDetalleDto>> recursosAplicacion = new HashMap<>();
        for (WrapperAplicacion wrapApp : modificaRolFl.getRecursosAppRol()) {
            ArrayList<RecursoDetalleDto> recursosDetalle = new ArrayList<>();
            ArrayList<RecursoDetalleDto> recursosConPermisos = validarSeleccionados(wrapApp.getArbolRecursos(),
                    recursosDetalle);
            if (recursosConPermisos.size() > 0) {
                permiteContinuar = true;
                recursosAplicacion.put(wrapApp.getNombreAplicacion(), recursosConPermisos);
            }
        }
        if (!permiteContinuar) {
            addErrorMessage(NOMBRE_BUNDLE_ROL, "msg_rol_requiere_permiso");
        } else {
            RolDetalleDto rolModificar = modificaRolFl.getRolModificar();
            // Asignar los recursos seleccionados por aplicacion
            rolModificar.setRecursosAplicacion(recursosAplicacion);
            // Asignar rol padre
            if (modificaRolFl.getIdRolPadre() != null && !modificaRolFl.getIdRolPadre().equals("")) {
                RolDetalleDto rolPadre = new RolDetalleDto();
                rolPadre.setIdRol(Integer.valueOf(modificaRolFl.getIdRolPadre()));
                rolModificar.setRolPadre(rolPadre);
            } else {
                rolModificar.setRolPadre(null);
            }
            // Asignar grupos
            if (modificaRolFl.getGruposRol().getTarget().size() > 0) {
                rolModificar.setGrupos(modificaRolFl.getGruposRol().getTarget());
            } else {
                rolModificar.setGrupos(null);
            }
            try {
                rolEjb.actualizarRol(rolModificar);
                addInfoMessage(NOMBRE_BUNDLE_ROL, "msg_rol_guardado");
            } catch (SeguridadException e) {
                SeguridadErrorHandler.handleException(e);
            }
        }
    }

    /**
     * Elimina la asociacion del rol padre con el rol que se esta creando o modificando
     */
    public void quitarRolPadre() {
        logger.debug("RolesMB::quitarRolPadre");
        RolesFL rolesFL = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);

        if (rolesFL.getRolSeleccionado() != null) {
            ModificarRolFL modificarRolFL = findFlowObject(ModificarRolFL.class, ModificarRolFL.NOMBRE_BEAN);
            modificarRolFL.setIdRolPadre(null);
            modificarRolFL.setNombreRolPadre(null);

        } else {
            CrearRolFL crearRolFL = findFlowObject(CrearRolFL.class, CrearRolFL.NOMBRE_BEAN);
            crearRolFL.setIdRolPadre(null);
            crearRolFL.setNombreRolPadre(null);
        }
    }

    /**
     * Selecciona o deselecciona todas las operaciones del recurso cuyo SelectBooleanCheckbox general para el recurso haya sido
     * seleccionado/deseleccionado, buscando primero,dicho recurso dentro de cada WrapperAplicacion que alimenta el componente TreeTable
     * 
     * @param id
     */
    public void seleccionarOperaciones(Integer id) {
        logger.debug("RolesMB::seleccionarOperaciones");
        RolesFL rolesFL = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);

        List<WrapperAplicacion> recursosApp = null;
        if (rolesFL.getRolSeleccionado() != null) {
            recursosApp = findFlowObject(ModificarRolFL.class, ModificarRolFL.NOMBRE_BEAN).getRecursosAppRol();
        } else {
            recursosApp = findFlowObject(CrearRolFL.class, CrearRolFL.NOMBRE_BEAN).getRecursosApp();
        }
        for (WrapperAplicacion wrapApp : recursosApp) {
            buscarSeleccionado(wrapApp.getArbolRecursos(), id);
        }

        /*
         * for (WrapperAplicacion wrapApp : findFlowObject(CrearRolFL.class, CrearRolFL.NOMBRE_BEAN).getRecursosApp()) {
         * validarSeleccionados(wrapApp.getArbolRecursos()); }
         */
    }

    /**
     * Resuelve los valores que son enviados al subflujo de consulta de historicos de roles
     */
    public void cargarParametrosHistorico() {
        logger.debug("RolesMB::cargarParametrosHistorico");

        RolesFL rolesFL = findFlowObject(RolesFL.class, RolesFL.NOMBRE_BEAN);

        Calendar fin = UtilFecha.buildCalendar(null);

        Calendar inicio = UtilFecha.buildCalendar(fin.getTime());
        int diasMax = Integer.valueOf(parametroEjb
                .consultarValorParametroSeguridad(EnumParametro.CANTIDAD_DIAS_CONSULTA_ROLES));
        long milisegundosDia = (24 * 60 * 60 * 1000);
        Date fechaCreacionRol = rolEjb.consultarFechaCreacionRol(rolesFL.getRolSeleccionado().getIdRol());
        rolesFL.setFechaCreacionRolSel(fechaCreacionRol);
        int diasDiferencia = (int) ((fin.getTimeInMillis() - rolesFL.getFechaCreacionRolSel().getTime()) / milisegundosDia);

        if (diasMax > diasDiferencia) {
            rolesFL.setFechaInicioHistorico(rolesFL.getFechaCreacionRolSel());
        } else {
            inicio.add(Calendar.DAY_OF_MONTH, -diasMax);
            rolesFL.setFechaInicioHistorico(inicio.getTime());
        }

        rolesFL.setFechaFinHistorico(fin.getTime());
    }

    public List<SelectItem> getlEstadosRol() {
        return lEstadosRol;
    }

    public void setlEstadosRol(List<SelectItem> lEstadosRol) {
        this.lEstadosRol = lEstadosRol;
    }

    public boolean isRecursoSeleccionado() {
        return recursoSeleccionado;
    }

    public void setRecursoSeleccionado(boolean recursoSeleccionado) {
        this.recursoSeleccionado = recursoSeleccionado;
    }

}
