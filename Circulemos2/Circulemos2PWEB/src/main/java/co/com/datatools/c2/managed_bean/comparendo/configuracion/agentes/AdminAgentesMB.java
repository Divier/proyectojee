package co.com.datatools.c2.managed_bean.comparendo.configuracion.agentes;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.MotivoVigenciaAgenteDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.TipoEntidadAgenteTransitoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRAgente;
import co.com.datatools.c2.negocio.interfaces.IRFirma;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.util.date.UtilFecha;

@ManagedBean
@SessionScoped
public class AdminAgentesMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AdminAgentesMB.class.getName());
    private static final String NOMBRE_BUNDLE = "labelAdminAgentes";

    @EJB
    private IRAgente iRAgente;
    @EJB
    private IRPersona iRPersona;
    @EJB
    private IRFirma iRFirma;
    @EJB
    private IRRepositorioArchivo iReposArchivoEjb;
    @EJB
    private IRAdministracion iRAdministracion;

    private StreamedContent firma;

    public AdminAgentesMB() {
        super();
    }

    public Date getFechaActual() {
        return UtilFecha.currentZeroTimeDate();
    }

    public void inicializar() {
        AdminAgentesHolderFL adminAgenteHolderFL = findFlowObject(AdminAgentesHolderFL.class,
                AdminAgentesHolderFL.NOMBRE_BEAN);

        adminAgenteHolderFL.setLsAgentes(null);
    }

    public void limpiar() {
        AdminAgentesHolderFL adminAgenteHolderFL = findFlowObject(AdminAgentesHolderFL.class,
                AdminAgentesHolderFL.NOMBRE_BEAN);

        adminAgenteHolderFL.setAgente(null);
        adminAgenteHolderFL.setLsAgentes(null);
        adminAgenteHolderFL.setSelAgente(null);

        AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);
        adminAgenteFL.setAgente(null);

    }

    /**
     * Metodo que se encarga de consultar un agente de tránsito
     * 
     * @author divier.casas
     * 
     */
    public void consultar() {
        LOGGER.debug("AdminAgentesMB::consultar()");
        AdminAgentesHolderFL adminAgenteHolderFL = findFlowObject(AdminAgentesHolderFL.class,
                AdminAgentesHolderFL.NOMBRE_BEAN);

        boolean errorValidacion = false;

        if (adminAgenteHolderFL.getAgente().getPersona().getTipoIdentificacion().getId() == null
                && adminAgenteHolderFL.getAgente().getPersona().getNumeroIdentificacion() == null
                && adminAgenteHolderFL.getAgente().getPlaca() == null) {
            addInfoMessage(NOMBRE_BUNDLE, "msg_filtros");
            errorValidacion = true;
        }

        if (adminAgenteHolderFL.getAgente().getPersona().getTipoIdentificacion().getId() == null
                && adminAgenteHolderFL.getAgente().getPersona().getNumeroIdentificacion() != null) {
            getFacesContext().addMessage("form-contenido:selTipoDocumento",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        } else if (adminAgenteHolderFL.getAgente().getPersona().getTipoIdentificacion().getId() != null
                && adminAgenteHolderFL.getAgente().getPersona().getNumeroIdentificacion() == null) {
            getFacesContext().addMessage("form-contenido:numeroDocumento",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }

        List<AgenteDTO> lsRespuesta = null;
        if (!errorValidacion) {
            AgenteDTO agente = new AgenteDTO();
            PersonaDTO persona = new PersonaDTO();
            TipoIdentificacionPersonaDTO tipoIdentificacion = new TipoIdentificacionPersonaDTO();
            tipoIdentificacion.setId(adminAgenteHolderFL.getAgente().getPersona().getTipoIdentificacion().getId());
            persona.setTipoIdentificacion(tipoIdentificacion);
            persona.setNumeroIdentificacion(adminAgenteHolderFL.getAgente().getPersona().getNumeroIdentificacion());
            agente.setPlaca(adminAgenteHolderFL.getAgente().getPlaca());
            agente.setPersona(persona);
            OrganismoTransitoDTO organismo = new OrganismoTransitoDTO();
            organismo.setCodigoOrganismo(getCodigoOrganismoTransito());
            agente.setOrganismoTransito(organismo);
            lsRespuesta = iRAgente.consultarAgente(agente);
        }
        adminAgenteHolderFL.setSelAgente(null);

        if (lsRespuesta == null || lsRespuesta.isEmpty()) {
            if (!errorValidacion) {
                addWarningMessage(NOMBRE_BUNDLE, "msg_error_consulta");
            }
            adminAgenteHolderFL.setLsAgentes(new ArrayList<AgenteDTO>(0));
            inicializar();
            return;
        } else {

            adminAgenteHolderFL.setLsAgentes(lsRespuesta);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lsRespuesta.size());
        }
    }

    /**
     * Metodo que se encarga de cargar los datos del agente
     * 
     * @author divier.casas
     * 
     */
    public void irActualizarAgente() {
        LOGGER.debug("AdminAgentesMB::irActualizarAgente()");
        AdminAgentesHolderFL adminAgenteHolderFL = findFlowObject(AdminAgentesHolderFL.class,
                AdminAgentesHolderFL.NOMBRE_BEAN);
        AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);
        adminAgenteFL.setAgente(adminAgenteHolderFL.getSelAgente());

        if (adminAgenteFL.getAgente().getMotivoVigenciaAgente() != null) {
            adminAgenteFL.getAgente().setMotivoVigenciaAgente(adminAgenteFL.getAgente().getMotivoVigenciaAgente());
        } else {
            adminAgenteFL.getAgente().setMotivoVigenciaAgente(new MotivoVigenciaAgenteDTO());
        }

        if (agenteVigente(adminAgenteFL.getAgente())) {
            adminAgenteFL.setAgenteVigente(true);
        } else {
            adminAgenteFL.setAgenteVigente(false);
        }
    }

    /**
     * Metodo que se encarga de registrar el agente de tránsito
     * 
     * @author divier.casas
     * 
     */
    public void guardarAgente() {
        LOGGER.debug("AdminAgentesMB::guardarAgente()");
        try {
            AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);

            co.com.datatools.c2.dto.CapturaFirmaDTO capturaFirma = new co.com.datatools.c2.dto.CapturaFirmaDTO();
            capturaFirma.setFirma(adminAgenteFL.getNumeroFirma());
            capturaFirma.setPlacaAgente(adminAgenteFL.getAgente().getPlaca());
            String numeroFirma = iRFirma.registrarFirmaAgente(capturaFirma);

            AgenteDTO agente = new AgenteDTO();
            OrganismoTransitoDTO organismo = new OrganismoTransitoDTO();
            organismo.setCodigoOrganismo(getCodigoOrganismoTransito());
            agente.setOrganismoTransito(organismo);
            agente.setPlaca(adminAgenteFL.getAgente().getPlaca());
            agente.setFechaInicioVigencia(adminAgenteFL.getAgente().getFechaInicioVigencia());
            agente.setPersona(adminAgenteFL.getAgente().getPersona());
            TipoEntidadAgenteTransitoDTO entidadAgente = new TipoEntidadAgenteTransitoDTO();
            entidadAgente.setId(EnumTipoAgenteImpositor.ACT.getValue());
            agente.setEntidadAgenteTransito(entidadAgente);
            agente.setFechaActualizacion(UtilFecha.buildCalendar().getTime());
            UsuarioPersonaDTO usuarioPersonaDTO = findSessionObject(
                    ConstantesManagedBean.CLASE_OBJ_USUARIO_PERSONA_AUTENTICADA,
                    ConstantesManagedBean.OBJ_USUARIO_PERSONA_AUTENTICA);
            agente.setUsuarioActualizacion(usuarioPersonaDTO);
            // agente.setEstado(true);
            agente.setNumeroFirma(Long.valueOf(numeroFirma));
            iRAgente.registrarAgente(agente);

            String mensajeRegistro = getBundle(NOMBRE_BUNDLE).getString("msg_agente_registrado");
            mensajeRegistro = MessageFormat.format(mensajeRegistro, adminAgenteFL.getAgente().getPlaca());

            @SuppressWarnings("static-access")
            FacesContext fContext = getFacesContext().getCurrentInstance();
            fContext.getExternalContext().getFlash().setKeepMessages(true);
            fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    getBundle(NOMBRE_BUNDLE).getString("titulo_agente_reg"), mensajeRegistro));
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public boolean actualizarAgente() {
        LOGGER.debug("AdminAgentesMB::actualizarAgente()");
        try {
            AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);

            AgenteDTO agente = new AgenteDTO();
            agente.setId(adminAgenteFL.getAgente().getId());
            OrganismoTransitoDTO organismo = new OrganismoTransitoDTO();
            organismo.setCodigoOrganismo(getCodigoOrganismoTransito());
            agente.setOrganismoTransito(organismo);
            agente.setPlaca(adminAgenteFL.getAgente().getPlaca());
            agente.setFechaInicioVigencia(adminAgenteFL.getAgente().getFechaInicioVigencia());
            agente.setPersona(adminAgenteFL.getAgente().getPersona());
            TipoEntidadAgenteTransitoDTO entidadAgente = new TipoEntidadAgenteTransitoDTO();
            entidadAgente.setId(EnumTipoAgenteImpositor.ACT.getValue());
            agente.setEntidadAgenteTransito(entidadAgente);
            agente.setFechaActualizacion(UtilFecha.buildCalendar().getTime());
            UsuarioPersonaDTO usuarioPersonaDTO = findSessionObject(
                    ConstantesManagedBean.CLASE_OBJ_USUARIO_PERSONA_AUTENTICADA,
                    ConstantesManagedBean.OBJ_USUARIO_PERSONA_AUTENTICA);
            agente.setUsuarioActualizacion(usuarioPersonaDTO);

            agente.setFechaFinVigencia(adminAgenteFL.getAgente().getFechaFinVigencia());
            agente.setMotivoVigenciaAgente(adminAgenteFL.getAgente().getMotivoVigenciaAgente());

            // Estado del agente
            if (adminAgenteFL.isAgenteVigente()) {
                if (agente.getFechaFinVigencia() != null) {
                    if (agente.getFechaFinVigencia().before(agente.getFechaInicioVigencia())) {
                        getFacesContext().addMessage("form-contenido:fechaFin",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                        getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_fin")));
                        return false;
                    }
                    if (agente.getFechaFinVigencia().before(getFechaActual())) {
                        getFacesContext().addMessage("form-contenido:fechaFin",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                        getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha")));
                        return false;
                    }
                }
            } else {
                if (agente.getFechaFinVigencia() != null) {
                    if (agente.getFechaInicioVigencia().before(agente.getFechaFinVigencia())) {
                        getFacesContext().addMessage("form-contenido:fechaIni",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                        getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha_ini")));
                        return false;
                    }
                }

                if (agente.getFechaInicioVigencia().before(getFechaActual())) {
                    getFacesContext().addMessage("form-contenido:fechaIni", new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, null, getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha")));
                    return false;
                }

                agente.setFechaFinVigencia(null);
                agente.setMotivoVigenciaAgente(null);
            }

            agente.setNumeroFirma(adminAgenteFL.getAgente().getNumeroFirma());
            iRAgente.actualizarAgente(agente);

            // Mensaje de actualizar agente
            String mensajeRegistro = getBundle(NOMBRE_BUNDLE).getString("msg_agente_actualizado");
            mensajeRegistro = MessageFormat.format(mensajeRegistro, adminAgenteFL.getAgente().getPlaca());

            @SuppressWarnings("static-access")
            FacesContext fContext = getFacesContext().getCurrentInstance();
            fContext.getExternalContext().getFlash().setKeepMessages(true);
            fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    getBundle(NOMBRE_BUNDLE).getString("titulo_agente_act"), mensajeRegistro));
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        return true;
    }

    /**
     * Metodo que permite saber si una persona existe o no, en el registro de personas
     * 
     * @return true si la persona no existe, false en caso contrario
     * @author divier.casas
     */
    public boolean validarPersona() {
        LOGGER.debug("AdminAgentesMB::validarPersona()");
        AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);
        adminAgenteFL.setTraePersona(false);
        PersonaDTO persona = null;
        persona = iRPersona.consultarPersona(getCodigoOrganismoTransito(),
                adminAgenteFL.getAgente().getPersona().getTipoIdentificacion().getId(),
                adminAgenteFL.getAgente().getPersona().getNumeroIdentificacion());
        if (persona != null) {
            List<AgenteDTO> lsRespuesta = iRAgente.consultarAgente(adminAgenteFL.getAgente());
            if (lsRespuesta == null || lsRespuesta.isEmpty()) {
                adminAgenteFL.getAgente().setPersona(persona);
                adminAgenteFL.setTraePersona(true);
                return true;
            } else {
                addErrorMessage(NOMBRE_BUNDLE, "msg_persona_es_agente");
                adminAgenteFL.setTraePersona(false);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que se encarga de cargar la persona
     * 
     * @author divier.casas
     * 
     */
    public void cargarPersona(PersonaDTO personaDTO) {
        LOGGER.debug("AdminAgentesMB::cargarPersona(PersonaDTO)");
        AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);
        if (personaDTO != null) {
            adminAgenteFL.getAgente().setPersona(personaDTO);
            adminAgenteFL.setTraePersona(true);
        }
    }

    /**
     * Permite visualizar el detalle de la información de un agente de tránsito
     * 
     */
    public void visualizarDetalle() {
        LOGGER.debug("AdminAgentesMB::visualizarDetalle()");
        try {
            AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);
            AdminAgentesHolderFL adminAgenteHolderFL = findFlowObject(AdminAgentesHolderFL.class,
                    AdminAgentesHolderFL.NOMBRE_BEAN);

            adminAgenteFL.setAgente(adminAgenteHolderFL.getSelAgente());
            adminAgenteFL.setAgenteVigente(agenteVigente(adminAgenteFL.getAgente()));

            ArchivoTransportableDTO archivoFirma = iReposArchivoEjb
                    .consultarDocumento(adminAgenteFL.getAgente().getNumeroFirma().toString());
            DefaultStreamedContent firma = new DefaultStreamedContent(
                    new ByteArrayInputStream(archivoFirma.getContenido()), "image/png");
            setFirma(firma);

            adminAgenteFL.setDetalleHistoricoAgenteDTOs(iRAgente.consultarHistoricoAgente(
                    adminAgenteHolderFL.getSelAgente().getOrganismoTransito().getCodigoOrganismo(),
                    adminAgenteHolderFL.getSelAgente().getPlaca()));

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Cuando se cambia la placa del agente transito
     */
    public void onPlacaAgenteChange(AdminAgentesFL adminAgentesFL) {
        LOGGER.debug("AdminAgentesMB::onPlacaAgenteChange(AdminAgentesFL)");
        AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);

        AgenteDTO agenteDTO = new AgenteDTO();
        agenteDTO.setPlaca(adminAgenteFL.getAgente().getPlaca());
        List<AgenteDTO> lsRespuesta = iRAgente.consultarAgente(agenteDTO);

        if (lsRespuesta != null && !lsRespuesta.isEmpty()) {
            getFacesContext().addMessage("form-contenido:placaAgente", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE).getString("msg_placa_agente_existe")));
            adminAgenteFL.getAgente().setPlaca(null);
        }
    }

    /**
     * Validacion previa al registro de un agente de transito
     * 
     * @return
     */
    public boolean validarRegistroAgente() {
        LOGGER.debug("AdminAgentesMB::validarRegistroAgente()");
        AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);
        if (adminAgenteFL.getAgente().getFechaInicioVigencia().compareTo(getFechaActual()) < 0) {
            getFacesContext().addMessage("form-contenido:fechaIni", new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                    getBundle(NOMBRE_BUNDLE).getString("msg_val_fecha")));
            return false;
        }
        return true;
    }

    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

    public String getExpresionTextos() {
        return ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES;
    }

    public String getVigente() {
        return getBundle(NOMBRE_BUNDLE).getString("val_vigente");
    }

    public String getNoVigente() {
        return getBundle(NOMBRE_BUNDLE).getString("val_no_vigente");
    }

    public StreamedContent getFirma() {
        return firma;
    }

    public void setFirma(StreamedContent firma) {
        this.firma = firma;
    }

    public boolean agenteVigente(AgenteDTO agenteDTO) {
        return iRAgente.vigenciaAgente(agenteDTO);
    }

    /**
     * valida que el tipo de documento y el número de identificación sea válido
     * 
     * @return
     */
    public boolean validarDocumento() {
        LOGGER.debug("AdminAgentesMB::validarRegistroAgente()");

        AdminAgentesFL adminAgenteFL = findFlowObject(AdminAgentesFL.class, AdminAgentesFL.NOMBRE_BEAN);

        try {
            boolean valido = false;

            if (adminAgenteFL.getAgente().getPersona().getNumeroIdentificacion() != null
                    && adminAgenteFL.getAgente().getPersona().getTipoIdentificacion().getId() != null) {
                valido = iRAdministracion.validarNumeroDocumento(
                        adminAgenteFL.getAgente().getPersona().getNumeroIdentificacion(),
                        adminAgenteFL.getAgente().getPersona().getTipoIdentificacion().getId(), new Date());
            }

            if (!valido) {
                addErrorMessage(NOMBRE_BUNDLE, "msg_tipo_numero_doc");
            }

            return valido;
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
    }

    public boolean validarDocumentoConsulta() {
        AdminAgentesHolderFL adminAgenteHolderFL = findFlowObject(AdminAgentesHolderFL.class,
                AdminAgentesHolderFL.NOMBRE_BEAN);
        inicializar();
        try {
            boolean valido = true;

            if (adminAgenteHolderFL.getAgente().getPersona().getNumeroIdentificacion() != null
                    && adminAgenteHolderFL.getAgente().getPersona().getTipoIdentificacion().getId() != null) {
                valido = iRAdministracion.validarNumeroDocumento(
                        adminAgenteHolderFL.getAgente().getPersona().getNumeroIdentificacion(),
                        adminAgenteHolderFL.getAgente().getPersona().getTipoIdentificacion().getId(), new Date());
            }

            if (!valido) {
                addErrorMessage(NOMBRE_BUNDLE, "msg_tipo_numero_doc");
            }

            return valido;
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
    }
}