package co.com.datatools.c2.managed_bean.coactivo.administracion.seguimiento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.ArchivoExcepcionDTO;
import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.ConsultaSeguimientoCoactivoDTO;
import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.ObligacionCoactivoDTO;
import co.com.datatools.c2.dto.RadicarExcepcionDTO;
import co.com.datatools.c2.dto.RespuestaSeguimientoDTO;
import co.com.datatools.c2.dto.RespuestaTrazabilidadDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion.VisualizarDocumentoMB;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;

/**
 * Se encarga de consultar los seguimientos de coactivos
 * 
 * @author divier.casas
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaSeguimientoMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ConsultaSeguimientoMB.class.getName());
    private static final String NOMBRE_BUNDLE = "labelCoactivo";

    @EJB
    private IRCoactivo iRCoactivo;

    @EJB
    private IRComparendo iRComparendo;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    // Documento consulta seguimeinto
    private StreamedContent documentoSeg;
    private final String NOMBRE_ARCHIVO = "Documento Seguimiento.pdf";

    @ManagedProperty(value = "#{visualizarDocumentoMB}")
    private VisualizarDocumentoMB visualizarDocumentoMB;

    /**
     * Consulta los seguimientos de coactivos dependiendo de los filtros ingresados
     * 
     * @author divier.casas
     */
    public void consultarSeguimientoCoactivo() {
        LOGGER.debug("ConsultaSeguimientoMB::consultarSeguimientoCoactivo()");
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);

        consultaSegHolderFL.setSegSeleccionado(null);

        // Validacion fecha inicio proceso
        if (consultaSegHolderFL.getConsultaSegCoactivo().getFechaFinProceso() != null) {
            if (consultaSegHolderFL.getConsultaSegCoactivo().getFechaIniProceso() == null) {
                getFacesContext().addMessage("form-contenido:fechaInicialProceso",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }
        // Validacion fecha fin proceso
        if (consultaSegHolderFL.getConsultaSegCoactivo().getFechaIniProceso() != null) {
            if (consultaSegHolderFL.getConsultaSegCoactivo().getFechaFinProceso() == null) {
                getFacesContext().addMessage("form-contenido:fechaFinalProceso",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }
        // Validacion rango fechas
        if (consultaSegHolderFL.getConsultaSegCoactivo().getFechaIniProceso() != null
                && consultaSegHolderFL.getConsultaSegCoactivo().getFechaFinProceso() != null
                && consultaSegHolderFL.getConsultaSegCoactivo().getFechaIniProceso()
                        .compareTo(consultaSegHolderFL.getConsultaSegCoactivo().getFechaFinProceso()) > 0) {
            addWarningMessage(NOMBRE_BUNDLE, "msg_rango_fecha");
            return;
        }
        // Validacion fechas superior actual
        if ((consultaSegHolderFL.getConsultaSegCoactivo().getFechaIniProceso() != null
                && consultaSegHolderFL.getConsultaSegCoactivo().getFechaFinProceso() != null)
                && (consultaSegHolderFL.getConsultaSegCoactivo().getFechaIniProceso()
                        .compareTo(UtilFecha.buildCalendar().getTime()) > 0
                        || consultaSegHolderFL.getConsultaSegCoactivo().getFechaFinProceso()
                                .compareTo(UtilFecha.buildCalendar().getTime()) > 0)) {
            addWarningMessage(NOMBRE_BUNDLE, "msg_fecha_superior_actual");
            return;
        }
        // // Validacion numero proceso
        // if (consultaSegHolderFL.getConsultaSegCoactivo().getFechaIniProceso() != null) {
        // if (consultaSegHolderFL.getConsultaSegCoactivo().getFechaFinProceso() != null) {
        // if (consultaSegHolderFL.getConsultaSegCoactivo().getNumeroProceso() == null) {
        // getFacesContext().addMessage("form-contenido:numeroProceso", new FacesMessage(
        // FacesMessage.SEVERITY_ERROR, null,
        // getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
        // CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
        // return;
        // }
        // }
        // }
        // Validacion tipo de documento del deudor
        if (consultaSegHolderFL.getConsultaSegCoactivo().getNumeroDocDeudor() != null) {
            if (consultaSegHolderFL.getConsultaSegCoactivo().getTipoDocDeudor() == null) {
                getFacesContext().addMessage("form-contenido:tipoDocDeudor",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }
        // Validacion numero de documento del deudor
        if (consultaSegHolderFL.getConsultaSegCoactivo().getTipoDocDeudor() != null) {
            if (consultaSegHolderFL.getConsultaSegCoactivo().getNumeroDocDeudor() == null) {
                getFacesContext().addMessage("form-contenido:numeroDocDeudor",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        if (new ConsultaSeguimientoCoactivoDTO().equals(consultaSegHolderFL.getConsultaSegCoactivo())) {
            addWarningMessage(NOMBRE_BUNDLE, "msg_filtros");
            return;
        }

        try {
            List<RespuestaSeguimientoDTO> lsRespuestaSeg = iRCoactivo
                    .consultarSeguimientosCoactivo(consultaSegHolderFL.getConsultaSegCoactivo());

            if (lsRespuestaSeg == null || lsRespuestaSeg.isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
                consultaSegHolderFL.setLstSeguimientos(new ArrayList<RespuestaSeguimientoDTO>(0));
                return;
            } else {
                consultaSegHolderFL.setLstSeguimientos(lsRespuestaSeg);
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lsRespuestaSeg.size());
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * @author giovanni.velandia
     */
    public void consultarDetalleCoactivo() {
        LOGGER.debug("ConsultaSeguimientoMB::consultarDetalleCoactivo()");
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        ConsultaTrazaSeguimientoHolderFL cTrazaSegHolderFL = findFlowObject(ConsultaTrazaSeguimientoHolderFL.class,
                ConsultaTrazaSeguimientoHolderFL.NOMBRE_BEAN);

        // Consultar obligaciones del coactivo
        ObligacionCoactivoDTO obligacionCoactivoDTO = new ObligacionCoactivoDTO();
        obligacionCoactivoDTO.setCoactivo(new CoactivoDTO(consultaSegHolderFL.getSegSeleccionado().getIdCoactivo()));
        cTrazaSegHolderFL.setLstObligaciones(iRCoactivo.consultarObligacionesCoactivo(obligacionCoactivoDTO));
        // Completa el medio de imposicion
        for (ObligacionCoactivoDTO obligacion : cTrazaSegHolderFL.getLstObligaciones()) {
            ComparendoDTO comparendo = iRComparendo.consultarComparendo(obligacion.getNumeroObligacion(),
                    getCodigoOrganismoTransito());
            obligacion.setNombreTipoObligacion(comparendo.getMedioImposicion().getNombre());
            obligacion.setNumeroCitacion(comparendo.getNumeroCitacion());
        }
    }

    /**
     * 
     * @author divier.casas
     */
    public void consultarTrazaSeguimiento() {
        LOGGER.debug("ConsultaSeguimientoMB::consultarTrazaSeguimiento()");
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        ConsultaTrazaSeguimientoHolderFL cTrazaSegHolderFL = findFlowObject(ConsultaTrazaSeguimientoHolderFL.class,
                ConsultaTrazaSeguimientoHolderFL.NOMBRE_BEAN);

        // Limpia los campos de excepciones
        limpiarCamposExcepciones();
        // Detalle
        consultarDetalleCoactivo();

        List<RespuestaTrazabilidadDTO> lsRespuestaTrzSeg = iRCoactivo
                .consultarTrazaSeguimiento(consultaSegHolderFL.getSegSeleccionado().getIdProceso());

        RadicarExcepcionDTO radicarExcepcionDTO = iRCoactivo
                .consultarRadicarExcepcion(consultaSegHolderFL.getSegSeleccionado().getIdCoactivo());
        if (radicarExcepcionDTO != null) {
            consultaSegHolderFL.setExcepciones(true);
            consultaSegHolderFL.setRadicarExcepcionDTO(radicarExcepcionDTO);

            if (radicarExcepcionDTO.getFechaFalloExcepcion() != null) {
                consultaSegHolderFL.setFalloAFavor(radicarExcepcionDTO.getFalloAFavor());
                consultaSegHolderFL.setFallo(true);
            }

            // Agrupacion de documentos
            if (radicarExcepcionDTO.getArchivoExcepcionDTOs() != null
                    && !radicarExcepcionDTO.getArchivoExcepcionDTOs().isEmpty()) {
                consultaSegHolderFL.setArchivoExcepcionRadDTOs(new ArrayList<ArchivoExcepcionDTO>());
                consultaSegHolderFL.setArchivoExcepcionFalloDTOs(new ArrayList<ArchivoExcepcionDTO>());

                for (ArchivoExcepcionDTO archivoExcepcionDTO : radicarExcepcionDTO.getArchivoExcepcionDTOs()) {
                    if (archivoExcepcionDTO.getFalloExcepcion()) {
                        consultaSegHolderFL.getArchivoExcepcionFalloDTOs().add(archivoExcepcionDTO);
                    } else {
                        consultaSegHolderFL.getArchivoExcepcionRadDTOs().add(archivoExcepcionDTO);
                    }
                }
            }
        }

        if (lsRespuestaTrzSeg == null || lsRespuestaTrzSeg.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            cTrazaSegHolderFL.setLstTrazabilidades(new ArrayList<RespuestaTrazabilidadDTO>(0));
            return;
        } else {
            cTrazaSegHolderFL.setLstTrazabilidades(lsRespuestaTrzSeg);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lsRespuestaTrzSeg.size());
        }
    }

    /**
     * LImpia los campos de excepciones
     * 
     * @author giovanni.velandia
     */
    private void limpiarCamposExcepciones() {
        LOGGER.debug("ConsultaSeguimientoMB::limpiarCamposExcepciones()");
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);

        consultaSegHolderFL.setExcepciones(false);
        consultaSegHolderFL.setRadicarExcepcionDTO(null);
        consultaSegHolderFL.setArchivoExcepcionRadSelDTO(null);
        consultaSegHolderFL.setArchivoExcepcionFalloSelDTO(null);
        consultaSegHolderFL.setFalloAFavor(false);
        consultaSegHolderFL.setFallo(false);
        consultaSegHolderFL.setArchivoExcepcionRadDTOs(null);
        consultaSegHolderFL.setArchivoExcepcionFalloDTOs(null);
        consultaSegHolderFL.setAnexo(false);

    }

    /**
     * Obtiene el documento enviado
     * 
     * @author mod julio.pinzon 2016-10-25
     */
    public void obtenerDocumento() {
        LOGGER.debug("ConsultaSeguimientoMB::generarDocumento()");
        ConsultaTrazaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaTrazaSeguimientoHolderFL.class,
                ConsultaTrazaSeguimientoHolderFL.NOMBRE_BEAN);
        try {
            List<Long> documentoList = new ArrayList<>();
            if (consultaSegHolderFL.getDocSeleccionado() != null) {
                documentoList.add(consultaSegHolderFL.getDocSeleccionado().getNumeroDocumento());
            } else {
                for (DocumentoProcesoDTO documentoProc : consultaSegHolderFL.getTrazaSeleccionada()
                        .getLsDocumentosProc()) {
                    documentoList.add(documentoProc.getNumeroDocumento());
                }
            }

            if (documentoList.size() > 1) {
                // Abre popup
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('popUpDocSeguimiento').show();");
                context.update(":frmDocSeguimiento");
                consultaSegHolderFL.setVerDocumento(true);
                consultaSegHolderFL.setLstDocumentos(consultaSegHolderFL.getTrazaSeleccionada().getLsDocumentosProc());
            } else {
                byte[] documento = iRDocumentosCirculemos.consultarDocumentosPDF(documentoList);
                if (documento != null) {
                    visualizarDocumentoMB.setVisualizar(true);
                    visualizarDocumentoMB.visualizarDocumento(documento, NOMBRE_ARCHIVO, null, null);
                }
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public Date getFechaActual() {
        LOGGER.debug("ConsultaSeguimientoMB::getFechaActual()");
        return UtilFecha.currentZeroTimeDate();
    }

    public StreamedContent getDocumentoSeg() {
        return documentoSeg;
    }

    public void setDocumentoSeg(StreamedContent documentoSeg) {
        this.documentoSeg = documentoSeg;
    }

    public VisualizarDocumentoMB getVisualizarDocumentoMB() {
        return visualizarDocumentoMB;
    }

    public void setVisualizarDocumentoMB(VisualizarDocumentoMB visualizarDocumentoMB) {
        this.visualizarDocumentoMB = visualizarDocumentoMB;
    }
}