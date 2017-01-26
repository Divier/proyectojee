package co.com.datatools.c2.managed_bean.financiacion.administracion.simulacion;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;
import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.ErrorFinanciacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RespuestaFinanciacionDTO;
import co.com.datatools.c2.dto.SimulacionFinanciacionDTO;
import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.dto.common.ConsultaObligacionesDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.enumeracion.financiacion.EnumVariableCondicionFinanciacion;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorFinanciacion.EnumErroresResultadoFinanciacion;
import co.com.datatools.c2.negocio.interfaces.ILFinanciacion;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.util.date.UtilFecha;

@ManagedBean
@SessionScoped
public class SimulacionFinanciacionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger logger = Logger.getLogger(SimulacionFinanciacionMB.class.getName());
    // Variables de objetos spring web flow
    private static final String BUNDLE_SIMULACION_FINANCIACION = "labelSimulacionFinanciacion";

    private static final String CONTENT_TYPE = "application/pdf";
    private final String NOMBRE_ARCHIVO = "{0}.pdf";

    // Documento
    private StreamedContent documentoFinanciacion;

    @EJB
    private IRComparendo iRComparendo;

    @EJB
    private ILFinanciacion iFinanciacion;

    @EJB
    private IRAdministracionFinanciacion iRAdministracionFinanciacion;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private IRPersona iRPersona;

    @EJB
    private ILSeguridadCirculemos seguridadCirculemosEJB;

    @PostConstruct
    public void init() {
        cargarCondicionFinanciacion();
    }

    private ConfiguracionFinanciacionDTO cargarCondicionFinanciacion() {
        logger.debug(SimulacionFinanciacionMB.class.getName().concat("::cargarCondicionFinanciacion()"));
        OrganismoTransitoDTO organismoTransitoDTOSesion = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);

        ConfiguracionFinanciacionDTO configuracion = new ConfiguracionFinanciacionDTO();
        configuracion.setOrganismoTransito(organismoTransitoDTOSesion);

        List<ConfiguracionFinanciacionDTO> consultarConfiguracionFinanciacion = iRAdministracionFinanciacion
                .consultarConfiguracionFinanciacion(configuracion, true);

        if (consultarConfiguracionFinanciacion == null || consultarConfiguracionFinanciacion.isEmpty()) {
            addErrorMessage("labelSimulacionFinanciacion", "err_msg_no_existe_configuracion_vigente");
            return null;
        } else {
            return consultarConfiguracionFinanciacion.get(0);
        }
    }

    public void limpiarDatosSimulacion() { // Donde llaman este metodo
        logger.debug(SimulacionFinanciacionMB.class.getName().concat("::limpiarDatosSimulacion()"));
        SimulacionFinanciacionHolderFL holderFL = findFlowObject(SimulacionFinanciacionHolderFL.class,
                SimulacionFinanciacionHolderFL.SIMULACION_FINANCIACION_HOLDER_FL);
        holderFL.getSimulacionDTO().setNumeroCuotas(null);
        holderFL.getSimulacionDTO().setValorCuotaInicial(null);
    }

    public void limpiarDatosBusqueda() {
        logger.debug(SimulacionFinanciacionMB.class.getName().concat("::limpiarDatosBusqueda()"));
        SimulacionFinanciacionHolderFL holderFL = findFlowObject(SimulacionFinanciacionHolderFL.class,
                SimulacionFinanciacionHolderFL.SIMULACION_FINANCIACION_HOLDER_FL);
        holderFL.getListaResultadoInfractor().clear();
        holderFL.setInfractorDTO(new PersonaDTO());
    }

    public void consultarObligacionInfractor() {
        logger.debug(SimulacionFinanciacionMB.class.getName().concat("::consultarObligacionInfractor()"));
        SimulacionFinanciacionHolderFL holderFL = findFlowObject(SimulacionFinanciacionHolderFL.class,
                SimulacionFinanciacionHolderFL.SIMULACION_FINANCIACION_HOLDER_FL);
        if (holderFL.getInfractorDTO() != null) {
            List<ConsultaObligacionesDTO> listaResultadoConsulta = new ArrayList<ConsultaObligacionesDTO>();
            try {
                List<Integer> estadosComparendo = new ArrayList<>();
                estadosComparendo.add(EnumEstadoComparendo.REGISTRADO.getCodigo());
                estadosComparendo.add(EnumEstadoComparendo.VIGENTE.getCodigo());
                // Se consultan obligaciones para comparendo
                listaResultadoConsulta = iRComparendo.consultarComparendosSimulacionFinanciacion(
                        holderFL.getInfractorDTO(), estadosComparendo);
                List<ValorCondicionFinanciacionDTO> va = iRAdministracionFinanciacion
                        .consultarValorCondicionFinanciacion(EnumVariableCondicionFinanciacion.FINANCIAR_OBLIGACIONES_NO_NOTIFICADAS
                                .getValue());

                if (listaResultadoConsulta != null && !listaResultadoConsulta.isEmpty()) {
                    if (va.get(0).getValor() != null) {
                        boolean valorConfi = Boolean.parseBoolean(va.get(0).getValor());
                        if (!valorConfi) {
                            List<ConsultaObligacionesDTO> listaObligaciones = new ArrayList<ConsultaObligacionesDTO>();
                            for (ConsultaObligacionesDTO consulta : listaResultadoConsulta) {

                                if (consulta.getFechaNotificacion() != null) {
                                    listaObligaciones.add(consulta);
                                }
                            }
                            if (listaObligaciones != null && !listaObligaciones.isEmpty()) {
                                holderFL.setListaResultadoInfractor(listaObligaciones);
                            } else {
                                addInfoMessage(BUNDLE_SIMULACION_FINANCIACION, "inf_busqueda_infracciones");
                            }
                        } else {
                            holderFL.setListaResultadoInfractor(listaResultadoConsulta);
                        }
                    } else {
                        holderFL.setListaResultadoInfractor(listaResultadoConsulta);
                    }

                } else {
                    addInfoMessage(BUNDLE_SIMULACION_FINANCIACION, "inf_busqueda_infracciones");
                }
            } catch (CirculemosNegocioException e) {
                addErrorMessage(BUNDLE_SIMULACION_FINANCIACION, "err_busqueda_infracciones");
            }
        }
    }

    /**
     * Consulta la persona en el sistema.
     * 
     * @return true si la persona existe en el sistema, false de lo contrario
     * @author luis.forero
     */
    public boolean consultarPersona() {
        logger.debug(SimulacionFinanciacionMB.class.getName().concat("::consultarPersona()"));
        final SimulacionFinanciacionHolderFL holderFL = findFlowObject(SimulacionFinanciacionHolderFL.class,
                SimulacionFinanciacionHolderFL.SIMULACION_FINANCIACION_HOLDER_FL);
        PersonaDTO personaDTO = iRPersona.consultarPersona(getCodigoOrganismoTransito(), holderFL.getInfractorDTO()
                .getTipoIdentificacion().getId(), holderFL.getInfractorDTO().getNumeroIdentificacion());
        if (personaDTO == null) {
            addInfoMessage(BUNDLE_SIMULACION_FINANCIACION, "inf_busqueda_persona");
            personaDTO = new PersonaDTO();
            personaDTO.setTipoIdentificacion(holderFL.getInfractorDTO().getTipoIdentificacion());
            personaDTO.setNumeroIdentificacion(holderFL.getInfractorDTO().getNumeroIdentificacion());
            holderFL.setInfractorDTO(personaDTO);
            holderFL.setListaResultadoInfractor(new ArrayList<ConsultaObligacionesDTO>(1));
            return false;
        }
        holderFL.setInfractorDTO(personaDTO);
        holderFL.setListaResultadoInfractor(new ArrayList<ConsultaObligacionesDTO>(1));

        return true;
    }

    /**
     * Lleva a cabo la simulacion
     * 
     * @return true si la simulacion fue exitosa o false de lo contrario
     * @author luis.forero
     */
    public boolean realizarSimulacion() {
        logger.debug(SimulacionFinanciacionMB.class.getName().concat("::realizarSimulacion()"));
        SimulacionFinanciacionHolderFL holderFL = findFlowObject(SimulacionFinanciacionHolderFL.class,
                SimulacionFinanciacionHolderFL.SIMULACION_FINANCIACION_HOLDER_FL);
        // Se lleva a cabo la asignacion de las obligaciones encontradas a la simulacion para llevarla a cabo
        SimulacionFinanciacionDTO simulacionFinanciacionDTO = new SimulacionFinanciacionDTO();
        simulacionFinanciacionDTO.setObligacionesInfractor(holderFL.getListaResultadoInfractor());

        SimulacionFinanciacionFL simulacionFinanciacionFL = findFlowObject(SimulacionFinanciacionFL.class,
                SimulacionFinanciacionFL.SIMULACION_FINANCIACION_FL);
        simulacionFinanciacionFL.setInfractor(holderFL.getInfractorDTO());

        simulacionFinanciacionDTO.setDeudor(holderFL.getInfractorDTO());
        ProcesoDTO procesoDTO = new ProcesoDTO();
        procesoDTO.setFechaInicio(new Date());
        simulacionFinanciacionDTO.setProceso(procesoDTO);
        documentoFinanciacion = null;
        return calcularFinanciacion(simulacionFinanciacionDTO);
    }

    /**
     * Calcula los valores de la simulacion
     * 
     * @param simulacionFinanciacionDTO
     * @return simulacion de la financiacion
     * @author luis.forero
     */
    public boolean calcularFinanciacion(SimulacionFinanciacionDTO simulacionFinanciacionDTO) {
        logger.debug(SimulacionFinanciacionMB.class.getName().concat(
                "::calcularFinanciacion(SimulacionFinanciacionDTO)"));
        // Se lleva a cabo la simulacion
        try {
            simulacionFinanciacionDTO = iFinanciacion.calcularSimulacionFinanciacion(simulacionFinanciacionDTO);

            SimulacionFinanciacionFL simulacionFinanciacionFL = findFlowObject(SimulacionFinanciacionFL.class,
                    SimulacionFinanciacionFL.SIMULACION_FINANCIACION_FL);
            simulacionFinanciacionFL.setSimulacionFinanciacionDTO(simulacionFinanciacionDTO);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            logger.error(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Registra la simulacion como una financiacion en el sistema y muestra los documentos generados.
     * 
     * @author luis.forero
     */
    public void registrarSimulacion() {
        logger.debug(SimulacionFinanciacionMB.class.getName().concat("::registrarSimulacion()"));
        SimulacionFinanciacionFL simulacionFinanciacionFL = findFlowObject(SimulacionFinanciacionFL.class,
                SimulacionFinanciacionFL.SIMULACION_FINANCIACION_FL);
        try {
            SimulacionFinanciacionDTO simulacionFinanciacionDTO = simulacionFinanciacionFL
                    .getSimulacionFinanciacionDTO();
            // Calcula nuevamente la financiacion en caso de cualquier cambio en el numero de cuotas o valor cuota inicial
            calcularFinanciacion(simulacionFinanciacionDTO);
            simulacionFinanciacionDTO.setOrganismoTransito(getOrganismoTransito());
            simulacionFinanciacionDTO.setAnio(UtilFecha.yearFromDate(new Date()));
            simulacionFinanciacionDTO.setFirma(simulacionFinanciacionFL.getFirma());

            simulacionFinanciacionDTO.setCantidadObligaciones(simulacionFinanciacionDTO.getObligacionesInfractor()
                    .size());
            simulacionFinanciacionDTO
                    .setTasaInteres(simulacionFinanciacionDTO.getInteresFinanciacion().doubleValue() * 100);
            simulacionFinanciacionDTO.setValorMora(BigDecimal.ZERO);
            simulacionFinanciacionDTO.setId(null);
            for (DetalleFinanciacionDTO detalle : simulacionFinanciacionDTO.getDetallesFinanciacion()) {
                detalle.setId(null);
            }
            for (ObligacionFinanciacionDTO obligacion : simulacionFinanciacionDTO.getObligacionesFinanciacion()) {
                obligacion.setId(null);
            }

            RespuestaFinanciacionDTO rspSimulacionFinanciacion = iFinanciacion
                    .registrarSimulacionFinanciacion(simulacionFinanciacionDTO);
            if (rspSimulacionFinanciacion.getCodigoError().equals(
                    EnumErroresResultadoFinanciacion.RESULTADO_VALIDACION_EXITOSO.getValue())) {
                FinanciacionDTO financiacion = rspSimulacionFinanciacion.getFinanciacion();
                DetalleFinanciacionDTO primeraCuota = financiacion.getDetallesFinanciacion().get(0);
                List<Long> documentoList = new ArrayList<>();
                documentoList.add(rspSimulacionFinanciacion.getIdDocumento());
                documentoList.add(primeraCuota.getIdDocumento());
                byte[] documento = iRDocumentosCirculemos.consultarDocumentosPDF(documentoList);
                if (documento != null) {
                    documentoFinanciacion = new DefaultStreamedContent(new ByteArrayInputStream(documento),
                            CONTENT_TYPE, MessageFormat.format(NOMBRE_ARCHIVO,
                                    String.valueOf(financiacion.getNumeroFinanciacion())));
                    simulacionFinanciacionFL.setConfirmarFinanciacion(true);
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('popUpConfirFinanciacion').show();");
                }
            } else {
                List<ErrorFinanciacionDTO> errores = rspSimulacionFinanciacion.getErrores();
                StringBuilder error = new StringBuilder();
                for (ErrorFinanciacionDTO errorFinanciacionDTO : errores) {
                    error.append(errorFinanciacionDTO.getDescripcion()).append("\n");
                }
                String pattern = getBundle(BUNDLE_SIMULACION_FINANCIACION).getString("err_msg_error_validacion");
                String detail = MessageFormat.format(pattern, error.toString());
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(rspSimulacionFinanciacion
                                .getCodigoError()), detail));
            }
        } catch (CirculemosNegocioException e) {
            logger.error(e.getMessage());
            CirculemosErrorHandler.handleException(e);
        } catch (CirculemosAlertaException e) {
            logger.error(e.getMessage());
            CirculemosErrorHandler.handleException(e);
        }
    }

    public StreamedContent getDocumentoFinanciacion() {
        return documentoFinanciacion;
    }

    public void setDocumentoFinanciacion(StreamedContent documentoFinanciacion) {
        this.documentoFinanciacion = documentoFinanciacion;
    }

}
