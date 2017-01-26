package co.com.datatools.c2.managed_bean.financiacion.administracion.proceso_financiacion;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.ConsultaDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion.VisualizarDocumentoMB;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRProceso;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Maneja la informacion de negocio de detalle de una financiacion
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class DetalleFinanciacionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(DetalleFinanciacionMB.class.getName());

    @EJB
    private IRFinanciacion financiacionEJB;
    @EJB
    private IRProceso iRProceso;
    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    IRDocumentosCirculemos documentosCirculemosEJB;

    @ManagedProperty(value = "#{visualizarDocumentoMB}")
    private VisualizarDocumentoMB visualizarDocumentoMB;

    // Documento recibo de pago
    private StreamedContent streamedContentRP;
    private final String NOMBRE_ARCHIVO_RP = "RECIBO DE PAGO.pdf";
    // Documento cuadro de amortizacion
    private StreamedContent streamedContentCA;
    private final String NOMBRE_ARCHIVO_CA = "CUADRO DE AMORTIZACION.pdf";
    // Documento radicado
    private StreamedContent documentoPonerFirmeFinanciacion;
    private static final String CONTENT_TYPE = "application/pdf";
    private final String NOMBRE_ARCHIVO = "CONVENIO POR MULTAS DE TRÁNSITO AMORTIZACIÓN MENSUAL.pdf";
    private final String NOMBRE_ARCHIVO_DETALLE = "Detalle Documento.pdf";

    /**
     * Metodo que se encarga de consultar una financiacion con su respectivo detalle
     * 
     * @author giovanni.velandia
     */
    public void consultarDetalleFinanciacion() {
        LOGGER.debug("DetalleFinanciacionMB::consultarDetalleFinanciacion()");
        ConsultaFinanciacionHolderFL consultaFinanciacionHolderFL = findFlowObject(ConsultaFinanciacionHolderFL.class,
                ConsultaFinanciacionHolderFL.NOMBRE_BEAN);
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);

        detalleFinanciacionFL.setConsultaDetalleFinanciacionSelDTO(null);
        detalleFinanciacionFL.setBtnDejarFirme(false);

        detalleFinanciacionFL.setConsultaFinanciacionDTO(financiacionEJB.consultaDetalleFinanciacion(
                consultaFinanciacionHolderFL.getConsultaFinanciacionSelecDTO().getNumeroFinanciacion()));

        // Permiso dejar en firme
        if (detalleFinanciacionFL.getConsultaFinanciacionDTO().getIdEstadoFinanciacion()
                .equals(EnumEstadoProceso.ECUADOR_FINANCIACION_PREFINANCIADO.getId()) || verificarPagoRealCuotaUno()) {
            detalleFinanciacionFL.setDejarFirme(true);
        }

    }

    /**
     * Opciones para botones de detalle fianciacion
     * 
     * @author giovanni.velandia
     */
    public void opcionesDetalleFinanciacion() {
        LOGGER.debug("DetalleFinanciacionMB::opcionesDetalleFinanciacion()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        if (detalleFinanciacionFL.getConsultaDetalleFinanciacionSelDTO() != null) {
            detalleFinanciacionFL.setBtnDejarFirme(true);
        } else {
            detalleFinanciacionFL.setBtnDejarFirme(false);
        }
    }

    /**
     * Consulta el documento del proceso
     * 
     * @author giovanni.velandia
     */
    public void consultarDocumentoDetalle() {
        LOGGER.debug("DetalleFinanciacionMB::consultarDocumentoDetalle()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        try {
            List<Long> documentoList = new ArrayList<>();
            for (DocumentoProcesoDTO documento : detalleFinanciacionFL.getTrazabilidadProcesoSelDTO().getDocumentos()) {
                documentoList.add(documento.getNumeroDocumento());
            }
            byte[] documento = iRDocumentosCirculemos.consultarDocumentosPDF(documentoList);
            if (documento != null) {
                visualizarDocumentoMB.setVisualizar(true);
                visualizarDocumentoMB.visualizarDocumento(documento, NOMBRE_ARCHIVO_DETALLE, null, null);
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Se encarga de verificar si la primera cuota del detalle esta con fecha de pago real
     * 
     * @return
     */
    private boolean verificarPagoRealCuotaUno() {
        LOGGER.debug("DetalleFinanciacionMB::verificarPagoRealCuotaUno()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);

        for (ConsultaDetalleFinanciacionDTO consultaDetalleFinanciacionDTO : detalleFinanciacionFL
                .getConsultaFinanciacionDTO().getConsultaDetalleFinanciacionDTOs()) {
            if (consultaDetalleFinanciacionDTO.getNumeroCuota() == 1) {
                if (consultaDetalleFinanciacionDTO.getFechaRealPago() != null) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    /**
     * Se encarga de mostrar la informacion de las obligaciones
     * 
     * @author giovanni.velandia
     */
    public void verObligaciones() {
        LOGGER.debug("DetalleFinanciacionMB::verObligaciones()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        detalleFinanciacionFL.setObligaciones(true);
    }

    /**
     * Se encarga de mostrar la informacion de los seguimientos
     * 
     * @author giovanni.velandia
     */
    public void verSeguimiento() {
        LOGGER.debug("DetalleFinanciacionMB::verSeguimiento()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);

        TrazabilidadProcesoDTO trazabilidadProcesoDTO = new TrazabilidadProcesoDTO();
        ProcesoDTO procesoDTO = new ProcesoDTO();
        procesoDTO.setId(detalleFinanciacionFL.getConsultaFinanciacionDTO().getIdProceso());
        trazabilidadProcesoDTO.setProceso(procesoDTO);
        detalleFinanciacionFL.getConsultaFinanciacionDTO()
                .setTrazabilidadProcesoDTOs(iRProceso.consultarTrazabilidad(trazabilidadProcesoDTO));
        detalleFinanciacionFL.setSeguimiento(true);
    }

    /**
     * Imprime el documento en pantalla del recibo de pago
     * 
     * @author giovanni.velandia
     */
    public void imprimirReciboPago() {
        LOGGER.debug("DetalleFinanciacionMB::imprimirReciboPago()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        try {
            streamedContentRP = new DefaultStreamedContent(
                    new ByteArrayInputStream(financiacionEJB.imprimirReciboPago(
                            detalleFinanciacionFL.getConsultaDetalleFinanciacionSelDTO().getIdDetalleFinanciacion())),
                    CONTENT_TYPE, NOMBRE_ARCHIVO_RP);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
        detalleFinanciacionFL.setImprimirReciboPago(true);
    }

    /**
     * Imprime el documento en pantalla de cuadro de amortizacion
     * 
     * @author giovanni.velandia
     */
    public void imprimirCuadroAmortizacion() {
        LOGGER.debug("DetalleFinanciacionMB::imprimirCuadroAmortizacion()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);

        try {
            streamedContentCA = new DefaultStreamedContent(
                    new ByteArrayInputStream(financiacionEJB.imprimirCuadroAmortizacion(
                            detalleFinanciacionFL.getConsultaFinanciacionDTO().getIdFinanciacion())),
                    CONTENT_TYPE, NOMBRE_ARCHIVO_CA);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
        detalleFinanciacionFL.setImprimirCuadroAmortizacion(true);
    }

    /**
     * Se encarga de cerrar la informacion de las obligaciones
     * 
     * @author giovanni.velandia
     */
    public void cerrarObligaciones() {
        LOGGER.debug("DetalleFinanciacionMB::cerrarObligaciones()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        detalleFinanciacionFL.setObligaciones(false);
    }

    /**
     * Se encarga de cerrar la informacion de los seguimientos
     * 
     * @author giovanni.velandia
     */
    public void cerrarSeguimiento() {
        LOGGER.debug("DetalleFinanciacionMB::cerrarSeguimiento()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        detalleFinanciacionFL.setSeguimiento(false);
    }

    /**
     * Cierra el documento en pantalla del recibo de pago
     * 
     * @author giovanni.velandia
     */
    public void cerrarImprimirReciboPago() {
        LOGGER.debug("DetalleFinanciacionMB::cerrarImprimirReciboPago()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        detalleFinanciacionFL.setImprimirReciboPago(false);
    }

    /**
     * Cierra el documento en pantalla de cuadro de amortizacion
     * 
     * @author giovanni.velandia
     */
    public void cerrarImprimirCuadroAmortizacion() {
        LOGGER.debug("DetalleFinanciacionMB::cerrarImprimirCuadroAmortizacion()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        detalleFinanciacionFL.setImprimirCuadroAmortizacion(false);
    }

    /**
     * deja en firme la financiación seleccionada
     * 
     * @author diego.fonseca
     * 
     */
    public void dejarFirmeFinanciacion() {
        LOGGER.debug("DetalleFinanciacionMB::dejarFirmeFinanciacion()");
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);
        try {

            Long idDocumento = financiacionEJB.dejarFirmeFinanciacion(detalleFinanciacionFL.getDejarFirmeDTO());

            List<Long> idDocumentos = new ArrayList<>();
            idDocumentos.add(idDocumento);
            byte[] archivo = documentosCirculemosEJB.consultarDocumentosPDF(idDocumentos);

            if (archivo != null) {
                visualizarDocumentoMB.setVisualizar(true);
                visualizarDocumentoMB.visualizarDocumento(archivo, NOMBRE_ARCHIVO, null, null);
            }

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        consultarDetalleFinanciacion();
    }

    public StreamedContent getDocumentoPonerFirmeFinanciacion() {
        LOGGER.debug("DetalleFinanciacionMB::getDocumentoPonerFirmeFinanciacion()");
        return documentoPonerFirmeFinanciacion;
    }

    public VisualizarDocumentoMB getVisualizarDocumentoMB() {
        return visualizarDocumentoMB;
    }

    public void setVisualizarDocumentoMB(VisualizarDocumentoMB visualizarDocumentoMB) {
        this.visualizarDocumentoMB = visualizarDocumentoMB;
    }

    public StreamedContent getStreamedContentRP() {
        return streamedContentRP;
    }

    public void setStreamedContentRP(StreamedContent streamedContentRP) {
        this.streamedContentRP = streamedContentRP;
    }

    public StreamedContent getStreamedContentCA() {
        return streamedContentCA;
    }

    public void setStreamedContentCA(StreamedContent streamedContentCA) {
        this.streamedContentCA = streamedContentCA;
    }

    /**
     * valida si los datos de la financiación estan completos
     * 
     * @return falso o verdadero dependiendo si los datos son correctos
     * @author Jeison.Rodriguez
     * @throws CirculemosNegocioException
     */
    public boolean validarFirmeFinanciacion() {
        LOGGER.debug("DetalleFinanciacionMB::validarFirmeFinanciacion()");
        ConsultaFinanciacionHolderFL consultaFinanciacionHolderFL = findFlowObject(ConsultaFinanciacionHolderFL.class,
                ConsultaFinanciacionHolderFL.NOMBRE_BEAN);
        DetalleFinanciacionFL detalleFinanciacionFL = findFlowObject(DetalleFinanciacionFL.class,
                DetalleFinanciacionFL.NOMBRE_BEAN);

        detalleFinanciacionFL.getDejarFirmeDTO()
                .setIdFinanciacion(consultaFinanciacionHolderFL.getConsultaFinanciacionSelecDTO().getIdFinanciacion());
        try {
            return financiacionEJB.validarDejarEnFirme(detalleFinanciacionFL.getDejarFirmeDTO());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

    }

}
