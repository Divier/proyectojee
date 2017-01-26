package co.com.datatools.c2.managed_bean.administracion.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.model.DualListModel;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.TipoUbicabilidadDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeracion.EnumTipoUbicabilidad;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.administracion.direccion.DireccionMB;
import co.com.datatools.c2.managed_bean.comun.CatalogoGeneralMB;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRCatalogosSeguridad;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumEstadoPassword;
import co.com.datatools.seguridad.utilidades.EnumEstadoUsuario;
import co.com.datatools.seguridad.utilidades.EnumParametro;

/**
 * Managed Bean de sesion que soporta las paginas relacionadas con la administracion de usuarios
 * 
 * @author claudia.rodriguez
 * 
 */
@ManagedBean
@SessionScoped
public class UsuariosMB extends AbstractC2ManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(UsuariosMB.class.getName());

    private static final String NOMBRE_BUNDLE_USUARIO = "labelUsuarios";

    @EJB
    private IRSeguridadCirculemos usuarioAppEjb;

    @EJB
    private IRCatalogosSeguridad catalogoSegEjb;

    @EJB
    private IRParametrosSeguridad parametroEjb;

    private Map<String, String> lEstadosUsuario;

    @EJB
    private IRPersona personaEjb;

    @ManagedProperty(value = "#{catalogoGeneralMB}")
    private CatalogoGeneralMB catalogoGeneralMB;

    @ManagedProperty(value = "#{direccionMB}")
    private DireccionMB direccionMB;

    private OrganismoTransitoDTO organismoTransito;

    private Long idPersona;

    private String estadoInicialUsuario;

    public UsuariosMB() {
        logger.debug("UsuariosMB::UsuariosMB()");
        organismoTransito = this.findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
    }

    // ****************************************NUEVO*************************************************************

    /**
     * Verifica el login con sus respectivos escenarios con la integracion de seguridad
     * 
     * @author giovanni.velandia
     */
    public void validarLoginIngresado() {
        logger.debug("UsuariosMB::validarLoginIngresado()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);

        usuariosFl.setValidoUsuario(true);
        // Inicializamos los datos del usuario persona
        usuariosFl.setUsuarioPersonaDTO(new UsuarioPersonaDTO());
        usuariosFl.getUsuarioPersonaDTO().setUsuario(new UsuarioDetalleDto());
        usuariosFl.getUsuarioPersonaDTO().setPersona(new PersonaDTO());
        usuariosFl.getUsuarioPersonaDTO().getPersona().setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
        usuariosFl.getUsuarioPersonaDTO().getUsuario().setLogin(usuariosFl.getLogin());

        /*
         * 1. Escenario el usuario existe en circulemos
         */
        List<UsuarioPersonaDTO> usuarioPersonaDTOs = usuarioAppEjb
                .consultarUsuarioPersona(usuariosFl.getUsuarioPersonaDTO());

        if (usuarioPersonaDTOs != null && !usuarioPersonaDTOs.isEmpty()) {
            usuariosFl.setGuardarUsuario(false);
            usuariosFl.setUsuarioPersonaDTO(usuarioPersonaDTOs.get(0));
            usuarioPersonaDTOs.get(0)
                    .setPersona(personaEjb.consultarPersona(usuariosFl.getUsuarioPersonaDTO().getPersona().getId()));
            usuariosFl.setUsuarioPersona(true);

            validarTipoPersona();
        } else {
            usuariosFl.setUsuarioPersona(false);
            usuariosFl.setGuardarUsuario(true);
            /*
             * 2. Escenario el usuario existe en el modulo de seguridad pero no esta asociado a una persona de circulemos
             */
            UsuarioDetalleDto usuarioDetalleDto = usuarioAppEjb
                    .consultarUsuario(usuariosFl.getUsuarioPersonaDTO().getUsuario().getLogin());

            if (usuarioDetalleDto != null) {
                // Ingresamos los datos del usuario para la creacion de la persona estos datos son opcionales
                usuariosFl.getUsuarioPersonaDTO().setUsuario(usuarioDetalleDto);
                // Correo electronio
                usuariosFl.getUsuarioPersonaDTO().getPersona().setCorreoElectronico(usuarioDetalleDto.getEmail());
            } else {

            }
        }

        // addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_login_ya_existe");
    }

    /**
     * Valida el tipo de prsona si es juiridica o natural
     * 
     * @author giovanni.velandia
     */
    public void validarTipoPersona() {
        logger.debug("UsuariosMB::validarLoginIngresado()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        if (usuariosFl.getUsuarioPersonaDTO().getPersona().getTipoIdentificacion().getId()
                .equals(catalogoGeneralMB.getTipoDocumentoEmpresa())) {
            usuariosFl.setPersonaNatural(false);
        } else {
            usuariosFl.setPersonaNatural(true);
        }
        validarPersona();
    }

    /**
     * Valida si la persona con tipo y numero de identificacion ingresados existe en el sistema
     * 
     * @author giovanni.velandia
     */
    public void validarPersona() {
        logger.debug("UsuariosMB::validarPersona()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);

        // Validacion numero de documento del infractor
        if (usuariosFl.getUsuarioPersonaDTO().getPersona().getTipoIdentificacion() != null
                && usuariosFl.getUsuarioPersonaDTO().getPersona().getTipoIdentificacion().getId() != null) {
            if (usuariosFl.getUsuarioPersonaDTO().getPersona().getNumeroIdentificacion() == null) {
                getFacesContext().addMessage("form-ingreso:txtNumeroIdentificacion",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        // Validacion tipo de documento del infractor
        if (usuariosFl.getUsuarioPersonaDTO().getPersona().getNumeroIdentificacion() != null) {
            if (usuariosFl.getUsuarioPersonaDTO().getPersona().getTipoIdentificacion() == null
                    || usuariosFl.getUsuarioPersonaDTO().getPersona().getTipoIdentificacion().getId() == null) {
                getFacesContext().addMessage("form-ingreso:selOneMen",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        PersonaDTO personaDTO = personaEjb.consultarPersona(organismoTransito.getCodigoOrganismo(),
                usuariosFl.getUsuarioPersonaDTO().getPersona().getTipoIdentificacion().getId(),
                usuariosFl.getUsuarioPersonaDTO().getPersona().getNumeroIdentificacion());

        if (personaDTO != null) {
            // Existe la persona
            usuariosFl.getUsuarioPersonaDTO().setPersona(personaDTO);
        }
    }

    /**
     * Registro de la tabla de ususario con persona en circulemos 2
     * 
     * @author giovanni.velandia mod julio.pinzon 2016-08-29
     */
    public void registrarUsuarioPersona() {
        logger.debug("UsuariosMB::registrarUsuarioPersona()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);

        try {
            // Registar Usuario persona
            // Dto usuarioPersona
            usuariosFl.getUsuarioPersonaDTO()
                    .setUsuario(crearUsuarioDetalle(usuariosFl.getUsuarioPersonaDTO().getPersona()));
            usuariosFl.getUsuarioPersonaDTO().setLogin(usuariosFl.getLogin());
            // Asignada datos a la persona
            generarPersona(usuariosFl.getUsuarioPersonaDTO().getPersona());
            usuarioAppEjb.registrarUsuario(usuariosFl.getUsuarioPersonaDTO());

            // registrarUsuarioPersona(usuarioPersonaDTO);
            addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_per_guardado");
        } catch (SeguridadException e) {
            logger.error("", e);
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, e.getErrorInfo().getCodigoError());
        } catch (CirculemosNegocioException ex) {
            CirculemosErrorHandler.handleException(ex);
        }

    }

    /**
     * Limpia campos de la logica de negocio
     */
    public void limpiarCampos() {
        logger.debug(UsuariosMB.class.getName().concat("::limpiarCampos()"));

        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        usuariosFl.setUsuarioPersonaDTO(new UsuarioPersonaDTO());
        usuariosFl.getUsuarioPersonaDTO().setUsuario(new UsuarioDetalleDto());
        usuariosFl.getUsuarioPersonaDTO().setPersona(new PersonaDTO());
        usuariosFl.getUsuarioPersonaDTO().getPersona().setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
        usuariosFl.setLogin(null);
    }

    // ****************************************ANTIGUO*************************************************************
    /**
     * Invoca al metodo que inicializa la listas de estados de usuario para realizar la consulta
     */
    @PostConstruct
    public void inicializarComponentesInterfaz() {
        logger.debug("UsuariosMB::inicializarComponentesInterfaz()");
        cargarListaEstados();
    }

    /**
     * Valida el parametro que define si se permite autenticacion con LDAP y lo asigna al UsuariosFL
     */
    public void validarPermiteLdap() {
        logger.debug("UsuariosMB::validarPermiteLdap()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        if (!usuariosFl.isLdapValidado()) {
            String permiteLdap = parametroEjb
                    .consultarValorParametroSeguridad(EnumParametro.PERMITE_AUTENTICACION_LDAP);
            if (permiteLdap.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
                usuariosFl.setPermiteLdap(true);
            }
            usuariosFl.setLdapValidado(true);
        }
    }

    /**
     * Consulta los roles disponibles y los asigna a la lista que actua como filtro de la consulta
     */
    public void cargarListaRolesFiltro() {
        // Cargar lista de roles para filtrar consulta
        UsuariosHolderFL usuariosHolderFl = getUsuariosHolderFL();
        if (usuariosHolderFl.getRolesFiltro().isEmpty()) {
            usuariosHolderFl.setRolesFiltro(usuarioAppEjb.consultarRoles(false));
        }

    }

    /**
     * Carga la lista de estados del usuario
     */
    public void cargarListaEstados() {
        logger.debug("UsuariosMB::cargarListaEstados()");
        lEstadosUsuario = new HashMap<>();
        lEstadosUsuario = catalogoSegEjb.consultarEstadosUsuario();
    }

    /**
     * Consulta el detalle de roles del usuario seleccionado en la interfaz, asigna este detalle al DTO: UsuariosHolderFL para que pueda ser
     * visualizado desde la opción "ver detalle"
     */
    public void verDetalle() {
        logger.debug("UsuariosMB::verDetalle()");
        UsuariosHolderFL usuariosHolderFL = findFlowObject(UsuariosHolderFL.class, UsuariosHolderFL.NOMBRE_BEAN);
        UsuarioDetalleDto detalleUsuario = usuarioAppEjb
                .consultarUsuario(usuariosHolderFL.getUsuarioSeleccionado().getUsuario().getLogin(), true);
        usuariosHolderFL.setDetalleUsuario(detalleUsuario);
    }

    /**
     * Invoca al EJB que registra un nuevo usuario en el sistema, validando primero los datos ingresados
     * 
     * @return registrado
     */
    public boolean registrar() {
        logger.debug("UsuariosMB::registrar()");
        boolean registrado = false;
        UsuariosFL usuariosFL = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);

        if (usuariosFL.isPermiteRegistrarPersona()) {
            // z persona
            generarPersona(usuariosFL.getPersonaDTO());
            // Registar Usuario persona
            // Dto usuarioPersona
            UsuarioPersonaDTO usuarioPersonaDTO = new UsuarioPersonaDTO();
            usuarioPersonaDTO.setUsuario(crearUsuarioDetalle(usuariosFL.getPersonaDTO()));
            usuarioPersonaDTO.setPersona(usuariosFL.getPersonaDTO());

            // registrarUsuarioPersona(usuarioPersonaDTO);
            addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_per_guardado");
            registrado = true;
        } else {
            if (usuarioAppEjb.validarExisteLogin(usuariosFL.getUsuarioPersonaDTO().getLogin())) {
                addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_login_ya_existe");
            } else {
                // Validar que haya seleccinado al menos un rol
                if (usuariosFL.getRolesUsuario().getTarget().isEmpty()) {
                    addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_seleccione_rol");
                } else {
                    PersonaDTO personaDTO = usuariosFL.getPersonaDTO();

                    if (usuariosFL.isPermiteRegistrarPersona()) {
                        if (!usuariosFL.isPersonaNatural()) {
                            PersonaJuridicaDTO personaJuridicaDTO = new PersonaJuridicaDTO();
                            personaJuridicaDTO.setSigla(((PersonaJuridicaDTO) usuariosFL.getPersonaDTO()).getSigla());
                            personaJuridicaDTO.setNombreComercial(
                                    ((PersonaJuridicaDTO) usuariosFL.getPersonaDTO()).getNombreComercial());
                            personaDTO = personaJuridicaDTO;
                        }

                        personaDTO.setValidarCamposMinimos(true);
                        List<DireccionPersonaDTO> direccionPersonaList = new ArrayList<DireccionPersonaDTO>();
                        personaDTO.setOrganismoTransito(organismoTransito);

                        // Direccion
                        DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
                        TipoUbicabilidadDTO tipoUbicabilidad = new TipoUbicabilidadDTO();
                        tipoUbicabilidad.setId(EnumTipoUbicabilidad.RESIDENCIA.getPk());
                        usuariosFL.getDireccion().setTipoUbicabilidad(tipoUbicabilidad);
                        direccionPersonaDTO.setDireccion(usuariosFL.getDireccion());

                        Integer codigo = EnumTipoFuenteInformacion.ADMINISTRACION.getValue();
                        TipoFuenteInformacionDTO tipoFuenteInformacion = new TipoFuenteInformacionDTO(codigo);
                        direccionPersonaDTO.setTipoFuenteInformacion(tipoFuenteInformacion);
                        personaDTO.setFuenteInformacion(tipoFuenteInformacion);

                        direccionPersonaList.add(direccionPersonaDTO);
                        personaDTO.setDireccionPersonaList(direccionPersonaList);
                    } else {
                        personaDTO.setId(idPersona);
                    }

                    // Registro de usuario
                    // Dto usuarioPersona
                    usuariosFL.getUsuarioPersonaDTO().setUsuario(crearUsuarioDetalle(usuariosFL.getPersonaDTO()));
                    usuariosFL.getUsuarioPersonaDTO().setPersona(personaDTO);

                    // registrarUsuarioPersona(usuariosFL.getUsuarioPersonaDTO());

                    addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_guardado");
                    registrado = true;

                }

            }

        }

        return registrado;
    }

    /**
     * Crea el objeto de detalle del usuario persona
     * 
     * @author giovanni.velandia mod julio.pinzon 2016-08-29
     * @return
     */
    private UsuarioDetalleDto crearUsuarioDetalle(PersonaDTO persona) {
        UsuariosFL usuariosFL = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);

        // Dto del detalle del usuario
        UsuarioDetalleDto usuarioDetalle = new UsuarioDetalleDto();
        String apellidos;
        String nombres;
        if (usuariosFL.isPersonaNatural()) {
            apellidos = persona.getApellido1();
            nombres = persona.getNombre1();
            if (StringUtils.isNotBlank(persona.getApellido2())) {
                apellidos = apellidos.concat(" " + persona.getApellido2());
            }
            if (StringUtils.isNotBlank(persona.getNombre2())) {
                nombres = nombres.concat(" " + persona.getNombre2());
            }
            usuarioDetalle.setApellidos(apellidos);
            usuarioDetalle.setNombres(nombres);
        } else {
            usuarioDetalle.setNombres(((PersonaJuridicaDTO) persona).getNombreComercial());
        }

        usuarioDetalle.setEmail(persona.getCorreoElectronico());
        usuarioDetalle.setAutenticacionConLdap(usuariosFL.isLdap());
        usuarioDetalle.setLogin(usuariosFL.getUsuarioPersonaDTO().getLogin());
        if (usuariosFL.getRolesUsuario() != null && usuariosFL.getRolesUsuario().getTarget() != null) {
            usuarioDetalle.setRoles(usuariosFL.getRolesUsuario().getTarget());
        }
        usuarioDetalle.setUsuarioRealizaCambio(
                findSessionObject(ConstantesManagedBean.CLASE_OBJ_LOGIN, ConstantesManagedBean.NOMBRE_OBJ_LOGIN));

        usuarioDetalle.setLogin(usuariosFL.getLogin());
        return usuarioDetalle;
    }

    /**
     * Metodo que se encarga de generar los datos de la pesona en circulemos 2
     * 
     * @author giovanni.velandia
     */
    private void generarPersona(PersonaDTO personaDTO) {
        // Ingresamos el oreganismo de transito
        personaDTO.setOrganismoTransito(getOrganismoTransito());
        TipoFuenteInformacionDTO tipoFuenteInformacionDTO = new TipoFuenteInformacionDTO();
        tipoFuenteInformacionDTO.setId(EnumTipoFuenteInformacion.CIRCULEMOS2.getValue());
        personaDTO.setFuenteInformacion(tipoFuenteInformacionDTO);
    }

    /**
     * Invoca al EJB que realiza la consulta de los usuarios de acuerdo a los filtros ingresados en la interfaz y asigna los resultados en
     * UsuariosHolderFL
     */
    public void consultar() {
        logger.debug("UsuariosMB::consultar()");
        UsuariosHolderFL usuariosFl = findFlowObject(UsuariosHolderFL.class, UsuariosHolderFL.NOMBRE_BEAN);
        usuariosFl.setUsuarioSeleccionado(null);
        UsuarioPersonaDTO usuarioPersonaFiltro = new UsuarioPersonaDTO();

        if (StringUtils.isNotBlank(usuariosFl.getNumeroIdentificacionPersona())
                || StringUtils.isNotBlank(usuariosFl.getIdTipoIdentificacionPersona())) {
            PersonaDTO personaDto = new PersonaDTO();
            if (StringUtils.isNotBlank(usuariosFl.getNumeroIdentificacionPersona())) {
                personaDto.setNumeroIdentificacion(usuariosFl.getNumeroIdentificacionPersona().trim());
            }
            if (StringUtils.isNotBlank(usuariosFl.getIdTipoIdentificacionPersona())) {
                TipoIdentificacionPersonaDTO tipoIdentificacion = new TipoIdentificacionPersonaDTO(
                        Integer.valueOf(usuariosFl.getIdTipoIdentificacionPersona()));
                personaDto.setTipoIdentificacion(tipoIdentificacion);
            }
            usuarioPersonaFiltro.setPersona(personaDto);
        }

        UsuarioDetalleDto usuarioFiltro = new UsuarioDetalleDto();
        usuarioFiltro.setNombres(usuariosFl.getNombres());
        usuarioFiltro.setApellidos(usuariosFl.getApellidos());
        usuarioFiltro.setLogin(usuariosFl.getLogin());

        if (StringUtils.isNotBlank(usuariosFl.getEstadoSeleccionado())) {
            usuarioFiltro.setEstadoUsuario(usuariosFl.getEstadoSeleccionado());
        }

        List<RolDto> rolesDto = new ArrayList<RolDto>();
        for (RolDetalleDto rolDetalleDto : usuariosFl.getRolesSeleccionados()) {
            rolesDto.add(rolDetalleDto);
        }
        usuarioFiltro.setRoles(rolesDto);
        usuarioPersonaFiltro.setUsuario(usuarioFiltro);
        if (usuarioPersonaFiltro.getPersona() == null)
            usuarioPersonaFiltro.setPersona(new PersonaDTO());
        List<UsuarioPersonaDTO> resultados = usuarioAppEjb.consultarUsuarios(usuarioPersonaFiltro);

        usuariosFl.setResultadoConsulta(resultados);
        if (resultados.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(resultados.size());
        }

    }

    /**
     * 
     * eliminado remueve el objeto de la lista de resultados en UsuariosHolderFL
     */
    public void eliminar() {
        logger.debug("UsuariosMB::eliminar()");
        UsuariosHolderFL usuariosHolderFl = (UsuariosHolderFL) findFlowObject(UsuariosHolderFL.class,
                UsuariosHolderFL.NOMBRE_BEAN);
        UsuarioPersonaDTO usuarioSeleccionado = usuariosHolderFl.getUsuarioSeleccionado();

        try {
            // if (autenticacionMB.getAutenticador().getUsuario().getId().equals(usuarioSeleccionado.getUsuario().getId())) {
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_no_eliminable");
            // } else {
            usuarioAppEjb.eliminarUsuario(usuarioSeleccionado.getUsuario().getId());
            CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
            usuariosHolderFl.getResultadoConsulta().remove(usuarioSeleccionado);
            // }
        } catch (SeguridadException e) {
            logger.error("", e);
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, e.getErrorInfo().getCodigoError());
        }
    }

    /**
     * Metodo que carga los datos del usuario seleccionado en la pagina de modificacion y es invocado cuando entra al view-state de modificacion de
     * usuario
     */
    public boolean cargarDetalleUsuarioSel() {
        logger.debug("UsuariosMB::cargarDetalleUsuarioSel()");
        boolean cargaDetalle = false;
        UsuariosHolderFL usuariosHolderFL = findFlowObject(UsuariosHolderFL.class, UsuariosHolderFL.NOMBRE_BEAN);
        UsuariosFL usuariosFL = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        estadoInicialUsuario = usuariosHolderFL.getUsuarioSeleccionado().getUsuario().getIdEstadoUsuario();
        if ((usuariosHolderFL.getUsuarioSeleccionado().getUsuario().getIdEstadoUsuario())
                .equals(String.valueOf(EnumEstadoUsuario.CANCELADO.getId()))) {
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_cancelado");
        } else {
            cargaDetalle = true;
            UsuarioPersonaDTO usuarioSeleccion = usuariosHolderFL.getUsuarioSeleccionado();
            // Consultar grupos y roles del usuario
            UsuarioDetalleDto detalleUsuario = usuarioAppEjb.consultarUsuario(usuarioSeleccion.getUsuario().getLogin(),
                    true);
            usuariosFL.setUsuarioModificar(detalleUsuario);

            // Evaluar los roles disponibles y asignados
            List<RolDto> sourceRol = new ArrayList<RolDto>();
            List<RolDto> targetRol = new ArrayList<RolDto>();
            if (detalleUsuario.getRoles() != null && !detalleUsuario.getRoles().isEmpty()) {
                targetRol = detalleUsuario.getRoles();
            }

            boolean rolEnUsuario = false;
            // Consultar roles activos
            List<RolDetalleDto> rolesActivos = usuarioAppEjb.consultarRoles(true);
            for (RolDto rolDto : rolesActivos) {
                rolEnUsuario = false;
                if (detalleUsuario.getRoles() != null && !detalleUsuario.getRoles().isEmpty()) {
                    for (RolDto rolDtoUs : detalleUsuario.getRoles()) {
                        if (rolDto.getIdRol().equals(rolDtoUs.getIdRol())) {
                            rolEnUsuario = true;
                            break;
                        }
                    }
                }
                if (!rolEnUsuario) {
                    sourceRol.add(rolDto);
                }

            }
            // Verificar password bloqueado
            if (detalleUsuario.getEstadoPassword().equalsIgnoreCase(EnumEstadoPassword.BLOQUEADO.getNombre())) {
                usuariosFL.setPwBloqueado(true);
            }

            usuariosFL.setRolesUsuario(new DualListModel<RolDto>(sourceRol, targetRol));
        }
        return cargaDetalle;
    }

    /**
     * 
     * seleccion de roles
     */
    public void modificar() {
        logger.debug("UsuariosMB::modificar()");
        UsuariosFL usuariosFL = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        UsuariosHolderFL usuariosHolderFL = findFlowObject(UsuariosHolderFL.class, UsuariosHolderFL.NOMBRE_BEAN);

        // Validar que haya seleccinado al menos un rol
        if (usuariosFL.getRolesUsuario().getTarget().isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_seleccione_rol");
        } else {
            // Dto del detalle del usuario
            UsuarioDetalleDto usuarioDetalle = usuariosFL.getUsuarioModificar();
            // if (autenticacionMB.getAutenticador().getUsuario().getId().equals(usuarioDetalle.getId())
            // && !estadoInicialUsuario.equals(usuarioDetalle.getIdEstadoUsuario())) {
            // addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_no_editable");
            // } else {
            usuarioDetalle.setRoles(usuariosFL.getRolesUsuario().getTarget());
            try {
                usuarioDetalle.setUsuarioRealizaCambio(findSessionObject(ConstantesManagedBean.CLASE_OBJ_LOGIN,
                        ConstantesManagedBean.NOMBRE_OBJ_LOGIN));
                usuarioAppEjb.actualizarUsuario(usuarioDetalle);
                addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_usuario_guardado");
                usuariosHolderFL.getUsuarioSeleccionado().setUsuario(usuarioDetalle);
            } catch (SeguridadException e) {
                UsuariosMB.logger.trace(e);
                addErrorMessage(NOMBRE_BUNDLE_USUARIO, e.getErrorInfo().getCodigoError());
            }
            // }
        }
    }

    /**
     * Metodo que obtiene el usuario seleccionado e invoca al metodo del EJB que cambia la contraseña del usuario
     */
    public void modificarPw() {
        logger.debug("UsuariosMB::modificarPw()");
        UsuariosFL modificaUsuarioFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        String usuarioAutenticado = findSessionObject(ConstantesManagedBean.CLASE_OBJ_LOGIN,
                ConstantesManagedBean.NOMBRE_OBJ_LOGIN);
        usuarioAppEjb.actualizarPwUsuario(modificaUsuarioFl.getUsuarioModificar().getLogin(), usuarioAutenticado);
        addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_pw_modificado");
    }

    /**
     * Metodo que obtiene el usuario seleccionado e invoca al metodo del EJB que realiza el desbloqueo del usuario
     */
    public void desbloquearPw() {
        logger.debug("UsuariosMB::desbloquearPw");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        String usuarioAutenticado = findSessionObject(ConstantesManagedBean.CLASE_OBJ_LOGIN,
                ConstantesManagedBean.NOMBRE_OBJ_LOGIN);
        usuarioAppEjb.actualizarEstadoPwUsuario(usuariosFl.getUsuarioModificar().getLogin(), EnumEstadoPassword.ACTIVO,
                usuarioAutenticado);
        usuariosFl.setPwBloqueado(false);
        addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_pw_desbloqueado");
    }

    /**
     * Consulta los roles activos en el sistema y los carga en la lista de roles disponibles para la creacion del usuario
     */
    public void cargarListaRolesActivos() {
        logger.debug("UsuariosMB::cargarRolesActivos()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        if (usuariosFl.getRolesUsuario() == null) {
            List<RolDto> source = new ArrayList<RolDto>();
            List<RolDto> target = new ArrayList<RolDto>();
            // Consultar roles activos
            List<RolDetalleDto> rolesActivos = usuarioAppEjb.consultarRoles(true);
            for (RolDto rolDto : rolesActivos) {
                source.add(rolDto);
            }
            usuariosFl.setRolesUsuario(new DualListModel<RolDto>(source, target));
        }
    }

    /**
     * Registra un usuario persona
     * 
     * @author giovanni.velandia
     */
    public boolean adicionarUsuario() {
        logger.debug("UsuariosMB::adicionarUsuario()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        usuariosFl.getUsuarioPersonaDTO().setUsuario(new UsuarioDetalleDto());

        if (usuarioAppEjb.validarExisteLogin(usuariosFl.getUsuarioPersonaDTO().getLogin())) {
            usuarioAppEjb.registrarUsuarioPersona(usuariosFl.getUsuarioPersonaDTO());
        } else {
            // Login
            usuariosFl.getUsuarioPersonaDTO().getUsuario().setLogin(usuariosFl.getUsuarioPersonaDTO().getLogin());
            // Nombre
            if (usuariosFl.getPersonaDTO().getNombre2() != null) {
                usuariosFl.getUsuarioPersonaDTO().getUsuario().setNombres(
                        usuariosFl.getPersonaDTO().getNombre1() + " " + usuariosFl.getPersonaDTO().getNombre2());
            } else {
                usuariosFl.getUsuarioPersonaDTO().getUsuario().setNombres(usuariosFl.getPersonaDTO().getNombre1());
            }
            // Apellido
            if (usuariosFl.getPersonaDTO().getApellido2() != null) {
                usuariosFl.getUsuarioPersonaDTO().getUsuario().setApellidos(usuariosFl.getPersonaDTO().getApellido1());
            } else {
                usuariosFl.getUsuarioPersonaDTO().getUsuario().setApellidos(
                        usuariosFl.getPersonaDTO().getApellido1() + " " + usuariosFl.getPersonaDTO().getApellido2());
            }
            // Email
            usuariosFl.getUsuarioPersonaDTO().getUsuario().setEmail(usuariosFl.getPersonaDTO().getCorreoElectronico());

            usuariosFl.getUsuarioPersonaDTO().setPersona(usuariosFl.getPersonaDTO());
            // registrarUsuarioPersona(usuariosFl.getUsuarioPersonaDTO());
        }
        return true;
    }

    /**
     * Invoca al metodo EJB que genera el login para el usuario envianda la PersonaDTO
     */
    public void generarLogin() {
        logger.debug("UsuariosMB::generarLogin()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        String loginGenerado = "";
        PersonaDTO persona = null;
        boolean generacion = true;
        if (usuariosFl.isPersonaNatural()) {
            persona = new PersonaDTO();
            persona.setNombre1(usuariosFl.getPersonaDTO().getNombre1().trim());
            persona.setApellido1(usuariosFl.getPersonaDTO().getApellido1().trim());
            if (StringUtils.isNotBlank(usuariosFl.getPersonaDTO().getNombre2())) {
                persona.setNombre2(usuariosFl.getPersonaDTO().getNombre2().trim());
            }

            if (StringUtils.isNotBlank(usuariosFl.getPersonaDTO().getApellido2())) {
                persona.setApellido2(usuariosFl.getPersonaDTO().getApellido2().trim());
            }

        } else {
            PersonaJuridicaDTO personaJuridica = new PersonaJuridicaDTO();
            if (StringUtils.isBlank(((PersonaJuridicaDTO) usuariosFl.getPersonaDTO()).getSigla())) {
                generacion = false;
                addInfoMessage(NOMBRE_BUNDLE_USUARIO, "msg_ingrese_sigla");
            } else {
                personaJuridica.setSigla(((PersonaJuridicaDTO) usuariosFl.getPersonaDTO()).getSigla().trim());
                persona = personaJuridica;
            }
        }
        if (generacion) {
            loginGenerado = usuarioAppEjb.generarLogin(persona);
            usuariosFl.getUsuarioPersonaDTO().setLogin(loginGenerado);
        }
    }

    /**
     * Valida la seleccion de la opcion ¿Autenticacion con LDAP? y de acuerdo a esta habilita le ingreso de los datos del usuario en la interfaz
     */
    public void seleccionarLdap() {
        logger.debug("UsuariosMB::seleccionarLdap()");
        UsuariosFL usuariosFl = findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
        if (usuariosFl.isLdap()) {
            usuariosFl.setPedirDatosUsuario(false);
        } else {
            usuariosFl.setPedirDatosUsuario(true);
        }
    }

    /**
     * Consulta el usuario en el LDAP de acuerdo al valor del campo de busqueda ingresado por el usuario, si encuentra un resultado y no tiene usuario
     * en el sistema permite la creacion, de lo contrario muestra el respectivo error
     */
    public void consultarUsuarioLdap() {
        logger.debug("UsuariosMB::consultarUsuarioLdap()");
        UsuariosFL usuariosFL = getUsuariosFL();
        usuariosFL.setPedirDatosUsuario(false);
        try {
            UsuarioDto usuarioEnLdap = usuarioAppEjb.consultarUsuarioLdap(usuariosFL.getValorCampoBusquedaLdap());
            if (usuarioEnLdap == null) {
                // mostrar que no encontro resultados
                addErrorMessage(NOMBRE_BUNDLE_USUARIO, "msg_no_resultados_ldap");
            } else {
                // encontro un resultado y debe mostrar los datos del usuario encontrado, valida que no haya un usuario creado con ese login
                String loginEncontrado = usuarioEnLdap.getLogin();
                if (usuarioAppEjb.validarExisteLogin(loginEncontrado)) {
                    getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            getBundle(NOMBRE_BUNDLE_USUARIO).getString("msg_login_ldap_ya_existe") + loginEncontrado));
                } else {
                    usuariosFL.getUsuarioPersonaDTO().setLogin(loginEncontrado);
                    usuariosFL.setPedirDatosUsuario(true);
                }
            }
        } catch (SeguridadException e) {
            UsuariosMB.logger.trace(e);
            addErrorMessage(NOMBRE_BUNDLE_USUARIO, e.getErrorInfo().getCodigoError());
        }

    }

    public Map<String, String> getlEstadosUsuario() {
        return lEstadosUsuario;
    }

    public void setlEstadosUsuario(Map<String, String> lEstadosUsuario) {
        this.lEstadosUsuario = lEstadosUsuario;
    }

    public CatalogoGeneralMB getCatalogoGeneralMB() {
        return catalogoGeneralMB;
    }

    public void setCatalogoGeneralMB(CatalogoGeneralMB catalogoGeneralMB) {
        this.catalogoGeneralMB = catalogoGeneralMB;
    }

    public DireccionMB getDireccionMB() {
        return direccionMB;
    }

    public void setDireccionMB(DireccionMB direccionMB) {
        this.direccionMB = direccionMB;
    }

    public UsuariosFL getUsuariosFL() {
        return findFlowObject(UsuariosFL.class, UsuariosFL.NOMBRE_BEAN);
    }

    public UsuariosHolderFL getUsuariosHolderFL() {
        return findFlowObject(UsuariosHolderFL.class, UsuariosHolderFL.NOMBRE_BEAN);
    }

    public String getEstadoInicialUsuario() {
        return estadoInicialUsuario;
    }

    public void setEstadoInicialUsuario(String estadoInicialUsuario) {
        this.estadoInicialUsuario = estadoInicialUsuario;
    }

}
