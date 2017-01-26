package co.com.datatools.seguridad.mb.usuarios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.model.DualListModel;

import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.interfaces.IRGrupo;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.interfaces.IRRol;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.seguridad.mb.general.AutenticacionWebMB;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumClaseGrupo;
import co.com.datatools.seguridad.utilidades.EnumEstadoPassword;
import co.com.datatools.seguridad.utilidades.EnumEstadoUsuario;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * ManagedBean para los flujos relacionados con el CRUD de los Usuarios, asigna y obtiene valores de los Dtos: UsuariosFl, CrearUsuarioFL y
 * ModificarUsuarioFl
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class UsuariosMB extends AbstractSwfManagedBean {

    private static final String NOMBRE_BUNDLE_USUARIO = "mensajesUsuario";

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(UsuariosMB.class.getName());

    @EJB
    private IRUsuario usuarioEjb;

    @EJB
    private IRRol rolEjb;

    @EJB
    private IRGrupo grupoEjb;

    @EJB
    private IRParametrosSeguridad parametroEjb;

    @EJB
    private IRCatalogosSeguridad catalogoEjb;

    @EJB
    private IRAutenticacion autenticacionEjb;

    @ManagedProperty(value = "#{autenticacionWebMB}")
    private AutenticacionWebMB autenticacionWebMB;

    private String estadoInicialUsuario;

    private Map<String, String> lEstadosUsuario;

    private List<GrupoDto> gruposActivos;

    public UsuariosMB() {
        logger.debug("UsuariosMB::UsuariosMB");
    }

    /**
     * Valida si se permite creacion de usuarios con LDAP e inicializa las listas de estados, roles y grupos disponibles
     */
    public void inicializarComponentesInterfaz() {
        logger.debug("UsuariosMB::inicializarComponentesInterfaz");
        // validar si el sistema tiene configurado que permite autenticacion por ldap o no
        validarPermiteLdap();

        // Consultar grupos activos
        GrupoDto grupoFiltro = new GrupoDto();
        grupoFiltro.setActivo(Boolean.TRUE);
        grupoFiltro.setClase(EnumClaseGrupo.Usuarios.name());
        gruposActivos = grupoEjb.consultarGrupos(grupoFiltro);
        // cargar Listas estados
        cargarListaEstados();

        // Cargar lista de filtro de roles
        cargarFiltroRoles();
    }

    /**
     * Valida el parametro que define si se permite autenticacion con LDAP y lo asigna al Dto:CrearUsuarioFL
     */
    public void validarPermiteLdap() {
        logger.debug("UsuariosMB::validarPermiteLdap");
        CrearUsuarioFL crearUsuarioFl = findFlowObject(CrearUsuarioFL.class, CrearUsuarioFL.NOMBRE_BEAN);
        if (!crearUsuarioFl.isLdapValidado()) {
            String permiteLdap = parametroEjb
                    .consultarValorParametroSeguridad(EnumParametro.PERMITE_AUTENTICACION_LDAP);
            if (permiteLdap.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
                crearUsuarioFl.setPermiteLdap(true);
            }
            crearUsuarioFl.setLdapValidado(true);
        }
    }

    public void cargarFiltroRoles() {
        UsuariosFL usuarioFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        if (usuarioFl.getRolesDisponibles().size() == 0) {
            usuarioFl.setRolesDisponibles(rolEjb.consultarRoles(true));
        }
    }

    /**
     * Consulta los roles activos en el sistema y los carga en la lista de roles disponibles para la creacion del usuario
     */
    public void cargarListaRolesActivos() {
        logger.debug("UsuariosMB::cargarRolesActivos");
        CrearUsuarioFL crearUsuarioFl = findFlowObject(CrearUsuarioFL.class, CrearUsuarioFL.NOMBRE_BEAN);
        if (crearUsuarioFl.getRoles() == null) {
            List<RolDto> source = new ArrayList<RolDto>();
            List<RolDto> target = new ArrayList<RolDto>();
            // Consultar roles activos
            List<RolDetalleDto> rolesActivos = rolEjb.consultarRoles(true);
            for (RolDto rolDto : rolesActivos) {
                source.add(rolDto);
            }
            crearUsuarioFl.setRoles(new DualListModel<RolDto>(source, target));
        }
    }

    /**
     * Consulta los grupos activos en el sistema y los carga en la lista de grupos disponibles para la creacion del usuario
     */
    public void cargarListaGruposActivos() {
        logger.debug("UsuariosMB::cargarGruposActivos");
        CrearUsuarioFL crearUsuarioFl = findFlowObject(CrearUsuarioFL.class, CrearUsuarioFL.NOMBRE_BEAN);
        if (crearUsuarioFl.getGrupos() == null) {
            List<GrupoDto> source = new ArrayList<GrupoDto>();
            List<GrupoDto> target = new ArrayList<GrupoDto>();
            for (GrupoDto grupoDto : gruposActivos) {
                source.add(grupoDto);
            }
            crearUsuarioFl.setGrupos(new DualListModel<GrupoDto>(source, target));
        }
    }

    /**
     * Carga la lista de estados del usuario
     */
    public void cargarListaEstados() {
        logger.debug("UsuariosMB::cargarListaEstados");
        lEstadosUsuario = new HashMap<>();
        lEstadosUsuario = catalogoEjb.consultarEstadosUsuario();
    }

    /**
     * Consulta los usuarios de acuerdo a los filtros ingresados y asigna el resultado al Dto del flujo de consulta de Usuarios. Si no encuentra
     * resultados se muestra un mensaje
     */
    public void consultarUsuarios() {
        logger.debug("UsuariosMB::consultarUsuarios");
        UsuariosFL usuarioFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        usuarioFl.setConsultaRealizada(false);
        // Verificar los filtros
        UsuarioDetalleDto usuarioFiltro = new UsuarioDetalleDto();
        usuarioFiltro.setNombres(usuarioFl.getNombres());
        usuarioFiltro.setApellidos(usuarioFl.getApellidos());
        usuarioFiltro.setLogin(usuarioFl.getLogin());

        if (StringUtils.isNotBlank(usuarioFl.getEstadoSeleccionado())) {
            usuarioFiltro.setEstadoUsuario(usuarioFl.getEstadoSeleccionado());
        }

        List<RolDto> rolesDto = new ArrayList<RolDto>();
        for (RolDetalleDto rolDetalleDto : usuarioFl.getRolesSeleccionados()) {
            rolesDto.add(rolDetalleDto);
        }
        usuarioFiltro.setRoles(rolesDto);

        List<UsuarioDto> resultados = new ArrayList<>();
        resultados = usuarioEjb.consultarUsuarios(usuarioFiltro, null);
        usuarioFl.setResultadoConsulta(resultados);
        usuarioFl.setConsultaRealizada(true);
    }

    /**
     * Consulta el usuario en el LDAP de acuerdo al valor del campo de busqueda ingresado por el usuario, si encuentra un resultado y no tiene usuario
     * en el sistema permite la creacion, de lo contrario muestra el respectivo error
     */
    public void consultarUsuarioLdap() {
        logger.debug("UsuariosMB::consultarUsuarioLdap");
        CrearUsuarioFL crearUsuarioFl = findFlowObject(CrearUsuarioFL.class, CrearUsuarioFL.NOMBRE_BEAN);
        crearUsuarioFl.setPedirDatos(false);
        try {
            UsuarioDto usuarioEnLdap = usuarioEjb.consultarUsuarioLdap(crearUsuarioFl.getValorCampoBusquedaLdap());
            if (usuarioEnLdap == null) {
                // mostrar que no encontro resultados
                addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_no_resultados_ldap");
            } else {
                // encontro un resultado y debe mostrar los datos del usuario encontrado, valida que no haya un usuario creado con ese login
                String loginEncontrado = usuarioEnLdap.getLogin();
                if (usuarioEjb.validarExisteLogin(loginEncontrado)) {
                    getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            getBundle(NOMBRE_BUNDLE_USUARIO).getString("msg_login_ldap_ya_existe") + loginEncontrado));
                } else {
                    crearUsuarioFl.setLogin(loginEncontrado);
                    crearUsuarioFl.setNombres(usuarioEnLdap.getNombres());
                    crearUsuarioFl.setApellidos(usuarioEnLdap.getApellidos());
                    crearUsuarioFl.setEmail(usuarioEnLdap.getEmail());
                    crearUsuarioFl.setPedirDatos(true);
                }
            }
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
    }

    /**
     * Guarda el usuario con los datos ingresados, validando seleccion de roles y validez del login. Invoca al metodo del ejb que persiste el usuario
     */
    public boolean crearUsuario() {
        logger.debug("UsuariosMB::crearUsuario");
        CrearUsuarioFL creaUsuarioFl = findFlowObject(CrearUsuarioFL.class, CrearUsuarioFL.NOMBRE_BEAN);
        boolean creaUsuario = false;
        // Verificar el login
        if (usuarioEjb.validarExisteLogin(creaUsuarioFl.getLogin())) {
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_login_ya_existe");
        } else {
            // Validar que haya seleccinado al menos un rol
            if (creaUsuarioFl.getRoles().getTarget().size() == 0) {
                addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_seleccione_rol");
            } else {
                // Dto del detalle del usuario
                UsuarioDetalleDto usuarioDetalle = new UsuarioDetalleDto();
                usuarioDetalle.setApellidos(creaUsuarioFl.getApellidos());
                usuarioDetalle.setEmail(creaUsuarioFl.getEmail());
                usuarioDetalle.setAutenticacionConLdap(creaUsuarioFl.getLdap());
                usuarioDetalle.setLogin(creaUsuarioFl.getLogin());
                usuarioDetalle.setNombres(creaUsuarioFl.getNombres());
                usuarioDetalle.setRoles(creaUsuarioFl.getRoles().getTarget());
                usuarioDetalle.setGrupos(creaUsuarioFl.getGrupos().getTarget());

                try {
                    usuarioEjb.registrarUsuario(usuarioDetalle);
                    addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_guardado");
                    creaUsuario = true;
                } catch (SeguridadException e) {
                    SeguridadErrorHandler.handleException(e);
                }
            }
        }
        return creaUsuario;
    }

    /**
     * Elimina el usuario seleccionado en la interfaz y muestra un mensaje de eliminacion exitosa o de error en caso de que no se pueda eliminar por
     * integridad referencial con IngresoUsuario
     */
    public void eliminarUsuario() {
        logger.debug("UsuariosMB::eliminarUsuario");
        UsuarioDto usuarioSeleccionado = ((ModificarUsuarioFL) findFlowObject(ModificarUsuarioFL.class,
                ModificarUsuarioFL.NOMBRE_BEAN)).getUsuarioSeleccionado();
        // if (autenticacionWebMB.getUsuarioDto().getId().equals(usuarioSeleccionado.getId())) {
        addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_no_eliminable");
        // } else {
        try {
            usuarioEjb.eliminarUsuario(usuarioSeleccionado.getId());
            addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_eliminado");
            final UsuariosFL usuariosFl = (UsuariosFL) findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
            usuariosFl.getResultadoConsulta().remove(usuarioSeleccionado);
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
        // }
    }

    /**
     * Metodo invocado al cambiar el valor de la lista que define si el usuario se autentica o no con LDAP y establece en el Dto:CrearUsuarioFL si
     * debe solicitar o no los datos de creacion de usuario
     */
    public void seleccionarLdap() {
        logger.debug("UsuariosMB::seleccionarLdap");
        CrearUsuarioFL crearUsuarioFl = findFlowObject(CrearUsuarioFL.class, CrearUsuarioFL.NOMBRE_BEAN);
        if (crearUsuarioFl.getLdap()) {
            crearUsuarioFl.setPedirDatos(false);
        } else {
            crearUsuarioFl.setPedirDatos(true);
        }
    }

    /**
     * Invoca al metodo ejb que genera el login para el usuario enviando los datos de nombres y apellidos ingresados
     */
    public void generarLogin() {
        logger.debug("UsuariosMB::generarLogin");
        CrearUsuarioFL crearUsuarioFl = findFlowObject(CrearUsuarioFL.class, CrearUsuarioFL.NOMBRE_BEAN);
        String loginGenerado = usuarioEjb.generarLogin(crearUsuarioFl.getNombres(), crearUsuarioFl.getApellidos());
        crearUsuarioFl.setLogin(loginGenerado);
    }

    /**
     * Verifica si el login ya existe, en cuyo caso muestra un mensaje de error
     */
    public void validarLoginIngresado() {
        logger.debug("UsuariosMB::validarLoginIngresado");
        CrearUsuarioFL crearUsuarioFl = findFlowObject(CrearUsuarioFL.class, CrearUsuarioFL.NOMBRE_BEAN);
        String loginIngresado = crearUsuarioFl.getLogin();
        if (usuarioEjb.validarExisteLogin(loginIngresado)) {
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_login_ya_existe");
        }
    }

    /**
     * Consulta el detalle de grupos y roles del usuario seleccionado en la interfaz, asigna este detalle al DTO: usuariosFL para que pueda ser
     * visualizado desde la opción "ver detalle"
     */
    public void verDetalle() {
        logger.debug("UsuariosMB::verDetalle");
        ModificarUsuarioFL modificarUsuarioFL = findFlowObject(ModificarUsuarioFL.class,
                ModificarUsuarioFL.NOMBRE_BEAN);
        UsuarioDetalleDto detalleUsuario = usuarioEjb
                .consultarUsuario(modificarUsuarioFL.getUsuarioSeleccionado().getLogin(), true);
        UsuariosFL usuariosFL = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        usuariosFL.setDetalleUsuario(detalleUsuario);
    }

    /**
     * Guarda el usuario con los datos modificados, validando seleccion de roles y creando el historico del cambio, luego invoca al metodo del ejb que
     * actualiza el usuario
     */
    public void actualizarUsuario() {
        logger.debug("UsuariosMB::actualizarUsuario");
        ModificarUsuarioFL modificaUsuarioFl = findFlowObject(ModificarUsuarioFL.class, ModificarUsuarioFL.NOMBRE_BEAN);

        // Validar que haya seleccinado al menos un rol
        if (modificaUsuarioFl.getRolesUsuario().getTarget().size() == 0) {
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_seleccione_rol");
        } else {
            if (autenticacionWebMB.getUsuario().equals(modificaUsuarioFl.getUsuarioModificar().getLogin())
                    && !estadoInicialUsuario.equals(modificaUsuarioFl.getUsuarioModificar().getIdEstadoUsuario())) {
                addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_no_editable");
            } else {
                UsuarioDetalleDto usuarioDetalle = modificaUsuarioFl.getUsuarioModificar();
                usuarioDetalle.setRoles(modificaUsuarioFl.getRolesUsuario().getTarget());
                usuarioDetalle.setGrupos(modificaUsuarioFl.getGruposUsuario().getTarget());
                try {
                    usuarioEjb.actualizarUsuario(usuarioDetalle);
                    addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_guardado");
                    modificaUsuarioFl.setUsuarioSeleccionado(usuarioDetalle);
                } catch (SeguridadException e) {
                    SeguridadErrorHandler.handleException(e);
                }
            }
        }
    }

    /**
     * Metodo que carga los datos del usuario seleccionado en la pagina de modificacion y es invocado cuando entra al view-state de modificacion de
     * usuario
     */
    public void cargarDetalleUsuarioSel() {
        logger.debug("UsuariosMB::cargarDetalleUsuarioSel");
        ModificarUsuarioFL modificaUsuarioFl = findFlowObject(ModificarUsuarioFL.class, ModificarUsuarioFL.NOMBRE_BEAN);
        estadoInicialUsuario = modificaUsuarioFl.getUsuarioSeleccionado().getIdEstadoUsuario();
        if ((modificaUsuarioFl.getUsuarioSeleccionado().getIdEstadoUsuario())
                .equals(String.valueOf(EnumEstadoUsuario.CANCELADO.getId()))) {
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_cancelado");
            modificaUsuarioFl.setPermiteModificar(false);
        } else {
            modificaUsuarioFl.setPermiteModificar(true);
            if (modificaUsuarioFl.isPrimerIngreso()) {
                UsuarioDto usuarioSeleccion = modificaUsuarioFl.getUsuarioSeleccionado();
                // Consultar grupos y roles del usuario
                UsuarioDetalleDto detalleUsuario = usuarioEjb.consultarUsuario(usuarioSeleccion.getLogin(), true);
                modificaUsuarioFl.setUsuarioModificar(detalleUsuario);
                // Evaluar los grupos disponibles y asignados
                List<GrupoDto> sourceGr = new ArrayList<GrupoDto>();
                List<GrupoDto> targetGr = new ArrayList<GrupoDto>();
                if (detalleUsuario.getGrupos() != null && detalleUsuario.getGrupos().size() > 0) {
                    targetGr = detalleUsuario.getGrupos();
                }
                boolean grupoEnUsuario = false;
                for (GrupoDto grupoDto : gruposActivos) {
                    grupoEnUsuario = false;
                    if (detalleUsuario.getGrupos() != null && detalleUsuario.getGrupos().size() > 0) {
                        for (GrupoDto grupoDtoUs : detalleUsuario.getGrupos()) {
                            if (grupoDto.getIdGrupo().equals(grupoDtoUs.getIdGrupo())) {
                                grupoEnUsuario = true;
                                break;
                            }
                        }
                    }
                    if (!grupoEnUsuario)
                        sourceGr.add(grupoDto);
                }

                modificaUsuarioFl.setGruposUsuario(new DualListModel<GrupoDto>(sourceGr, targetGr));

                // Evaluar los roles disponibles y asignados
                List<RolDto> sourceRol = new ArrayList<RolDto>();
                List<RolDto> targetRol = new ArrayList<RolDto>();
                if (detalleUsuario.getRoles() != null && detalleUsuario.getRoles().size() > 0) {
                    targetRol = detalleUsuario.getRoles();
                }

                boolean rolEnUsuario = false;
                // Consultar roles activos
                List<RolDetalleDto> rolesActivos = rolEjb.consultarRoles(true);
                for (RolDto rolDto : rolesActivos) {
                    rolEnUsuario = false;
                    if (detalleUsuario.getRoles() != null && detalleUsuario.getRoles().size() > 0) {
                        for (RolDto rolDtoUs : detalleUsuario.getRoles()) {
                            if (rolDto.getIdRol().equals(rolDtoUs.getIdRol())) {
                                rolEnUsuario = true;
                                break;
                            }
                        }
                    }
                    if (!rolEnUsuario)
                        sourceRol.add(rolDto);
                }
                // Verificar password bloqueado
                if (detalleUsuario.getEstadoPassword().equalsIgnoreCase(EnumEstadoPassword.BLOQUEADO.getNombre())) {
                    modificaUsuarioFl.setPwBloqueado(true);
                }
                modificaUsuarioFl.setRolesUsuario(new DualListModel<RolDto>(sourceRol, targetRol));
                modificaUsuarioFl.setPrimerIngreso(false);
            }
        }
    }

    /**
     * Metodo que obtiene el usuario seleccionado e invoca al metodo del ejb que cambia la contraseña de un usuario
     */
    public void actualizarPw() {
        logger.debug("UsuariosMB::actualizarPw");
        ModificarUsuarioFL modificaUsuarioFl = findFlowObject(ModificarUsuarioFL.class, ModificarUsuarioFL.NOMBRE_BEAN);
        usuarioEjb.actualizarPwUsuario(modificaUsuarioFl.getUsuarioModificar().getLogin());
        addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_pw_modificado");
    }

    public void desbloquearPw() {
        logger.debug("UsuariosMB::desbloquearPw");
        ModificarUsuarioFL modificaUsuarioFl = findFlowObject(ModificarUsuarioFL.class, ModificarUsuarioFL.NOMBRE_BEAN);
        usuarioEjb.actualizarEstadoPwUsuario(modificaUsuarioFl.getUsuarioModificar().getLogin(),
                EnumEstadoPassword.ACTIVO, getLogin());
        modificaUsuarioFl.setPwBloqueado(false);
        addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_pw_desbloqueado");
    }

    /**
     * Resuelve los valores que son enviados al subflujo de consulta de historico
     */
    public void cargarParametrosHistorico() {
        logger.debug("UsuariosMB::cargarParametrosHistorico");
        UsuariosFL usuariosFL = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        ModificarUsuarioFL modificarUsuarioFL = findFlowObject(ModificarUsuarioFL.class,
                ModificarUsuarioFL.NOMBRE_BEAN);
        Date fechaCreacionUsuario = usuarioEjb
                .consultarFechaCreacionUsuario(modificarUsuarioFL.getUsuarioSeleccionado().getId());

        Calendar fin = UtilFecha.buildCalendar(null);

        Calendar inicio = UtilFecha.buildCalendar(fin.getTime());

        long milisegundosDia = (24 * 60 * 60 * 1000);
        int diasDiferencia = (int) ((fin.getTimeInMillis() - fechaCreacionUsuario.getTime()) / milisegundosDia);
        int diasMax = Integer
                .valueOf(parametroEjb.consultarValorParametroSeguridad(EnumParametro.CANTIDAD_DIAS_CONSULTA_USUARIOS));

        if (diasMax > diasDiferencia) {
            usuariosFL.setFechaInicioHistorico(fechaCreacionUsuario);
        } else {
            inicio.add(Calendar.DAY_OF_MONTH, -diasMax);
            usuariosFL.setFechaInicioHistorico(inicio.getTime());
        }

        usuariosFL.setFechaFinHistorico(fin.getTime());
    }

    public Map<String, String> getlEstadosUsuario() {
        return lEstadosUsuario;
    }

    public void setlEstadosUsuario(Map<String, String> lEstadosUsuario) {
        this.lEstadosUsuario = lEstadosUsuario;
    }

    public AutenticacionWebMB getAutenticacionWebMB() {
        return autenticacionWebMB;
    }

    public void setAutenticacionWebMB(AutenticacionWebMB autenticacionWebMB) {
        this.autenticacionWebMB = autenticacionWebMB;
    }

}
