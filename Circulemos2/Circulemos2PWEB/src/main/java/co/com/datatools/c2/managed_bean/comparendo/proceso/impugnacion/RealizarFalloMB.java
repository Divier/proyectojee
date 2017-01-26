package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesDocumentosC2;
import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.PlantillaDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.ProcesoPlantillaDTO;
import co.com.datatools.c2.dto.TipoFalloDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumProcesoPlantilla;
import co.com.datatools.c2.enumeraciones.EnumTipoFallo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRImpugnacion;
import co.com.datatools.c2.negocio.interfaces.IRProceso;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;

/**
 * Managed Bean para el manejo de registro de fallo de impugnacion
 * 
 * @author julio.pinzon 2016-06-10
 * 
 */
@ManagedBean
@SessionScoped
public class RealizarFalloMB extends AbstractC2ManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(RealizarFalloMB.class.getName());

    private final String NOMBRE_BUNDLE = "labelProcesoImpugnacion";

    @EJB
    private IRImpugnacion impugnacionEJB;

    @EJB
    private IRProceso procesoEJB;

    @EJB
    private IRDocumentosCirculemos irDocumentos;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    private final String NOMBRE_ARCHIVO = "Resolución de fallo de proceso de impugnación de comparendo.pdf";

    @ManagedProperty(value = "#{encabezadoImpugnacionMB}")
    private EncabezadoImpugnacionMB encabezadoImpugnacion;

    @ManagedProperty(value = "#{visualizarDocumentoMB}")
    private VisualizarDocumentoMB visualizarDocumentoMB;

    private final Double CERO = 0.0;
    private final Double CIEN = 100.0;

    /**
     * Inicializa datos de realizar fallo
     * 
     * @return True si termino correctamente
     * @author julio.pinzon 2016-06-13
     */
    public boolean inicializar() {
        logger.debug("RealizarFalloMB::inicializar()");
        RealizarFalloFL realizarFalloFL = findFlowObject(RealizarFalloFL.class, RealizarFalloFL.NOMBRE_BEAN);
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        // Verifica el estado del proceso
        if (!EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO.getValue()
                .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())
                && !EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO.getValue()
                        .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())
                && !EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO_ACONTINUACION.getValue()
                        .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())
                && !EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION.getValue()
                        .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())
                && !EnumEstadoProceso.ECUADOR_IMPUGNACION_CIERRE_PRUEBAS.getValue()
                        .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())) {
            addErrorMessage(NOMBRE_BUNDLE, "error_estado_no_permitido");
            return false;
        }
        // obtiene el proceso seleccionado
        if (impugnacionHolderFL.getComparendoSeleccionado() != null
                && impugnacionHolderFL.getComparendoSeleccionado().getIdProceso() != null) {
            realizarFalloFL.setIdProceso(impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());
            realizarFalloFL.setCicomparendo(impugnacionHolderFL.getComparendoSeleccionado().getIdComparendo());
            realizarFalloFL.setValorObligacion(impugnacionHolderFL.getComparendoSeleccionado().getValor());
            // Valida si viene de estado rechazado
            if (EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO.getValue()
                    .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())
                    || EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO_ACONTINUACION.getValue()
                            .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())) {
                // Poner datos del anterior fallo de impugnacion
                FalloImpugnacionDTO fallo = impugnacionEJB.consultarUltimoFalloImpugnacion(
                        impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());
                if (fallo != null) {
                    realizarFalloFL.setCodigoPlantilla(fallo.getCodigoPlantilla());
                    realizarFalloFL.setIdTipoFallo(fallo.getTipoFallo().getId());
                    realizarFalloFL.setMotivoFallo(fallo.getMotivo());
                    realizarFalloFL.setPorcentaje(fallo.getPorcentaje());
                    realizarFalloFL.setPuntos(fallo.getPuntos());

                    // Por tipo de fallo habilita o deshabilita unas opciones
                    if (EnumTipoFallo.CONDENATORIO_PARCIAL.getValue().equals(fallo.getTipoFallo().getId())) {
                        realizarFalloFL.setMostrarPorcentaje(true);
                        realizarFalloFL.setMostrarPuntos(true);
                    } else if (realizarFalloFL.getIdTipoFallo() != null
                            && realizarFalloFL.getIdTipoFallo().equals(EnumTipoFallo.CONDENATORIO.getValue())) {
                        realizarFalloFL.setMostrarPuntos(true);
                    } else {
                        realizarFalloFL.setMostrarPuntos(false);
                    }
                }
            }
        } else {
            addErrorMessage(NOMBRE_BUNDLE, "error_no_proceso");
            return false;
        }
        // Llena lista de plantillas
        if (realizarFalloFL.getLstPlantillas() == null) {
            List<SelectItem> lstPlantillas = new ArrayList<>();
            try {
                List<PlantillaDTO> plantillasDocumentos = irDocumentos
                        .consultarPlantillasPorProceso(EnumProcesoPlantilla.IMPUGNACION_FALLO.getCodigo());
                for (PlantillaDTO plantillaDTO : plantillasDocumentos) {
                    lstPlantillas
                            .add(new SelectItem(plantillaDTO.getCodigoPlantilla(), plantillaDTO.getNombrePlantilla()));
                }
            } catch (CirculemosAlertaException e) {
                CirculemosErrorHandler.handleException(e);
            }
            realizarFalloFL.setLstPlantillas(lstPlantillas);
        }
        encabezadoImpugnacion.consultarEncabezado(realizarFalloFL.getIdProceso());
        return true;
    }

    /**
     * Guarda el fallo registrado
     * 
     * @author julio.pinzon 2016-06-13
     */
    public boolean guardar() {
        logger.debug("RealizarFalloMB::guardar()");
        RealizarFalloFL realizarFalloFL = findFlowObject(RealizarFalloFL.class, RealizarFalloFL.NOMBRE_BEAN);
        FalloImpugnacionDTO fallo = new FalloImpugnacionDTO();
        fallo.setMotivo(realizarFalloFL.getMotivoFallo());
        fallo.setPorcentaje(realizarFalloFL.getPorcentaje());
        fallo.setPuntos(realizarFalloFL.getPuntos());
        fallo.setTipoFallo(new TipoFalloDTO(realizarFalloFL.getIdTipoFallo()));
        fallo.setTrazabilidadProceso(new TrazabilidadProcesoDTO());
        fallo.getTrazabilidadProceso().setProceso(new ProcesoDTO(realizarFalloFL.getIdProceso()));
        fallo.setCodigoPlantilla(realizarFalloFL.getCodigoPlantilla());
        fallo.setCicomparendo(realizarFalloFL.getCicomparendo());
        fallo.setValorDescuento(null);
        fallo.setValorObligacion(null);
        try {
            fallo = impugnacionEJB.registrarFallo(fallo);
            // Mensaje de guardado exitoso
            addInfoMessage(NOMBRE_BUNDLE, "msg_fallo_guardado");
            return true;
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }
        return false;
    }

    public void cancelar() {
        logger.debug("RealizarFalloMB::cancelar()");
    }

    /**
     * Evento cuando cambia el tipo de fallo
     * 
     * @author julio.pinzon 2016-06-13
     */
    public void onTipoFallo() {
        logger.debug("RealizarFalloMB::guardar()");
        RealizarFalloFL realizarFalloFL = findFlowObject(RealizarFalloFL.class, RealizarFalloFL.NOMBRE_BEAN);
        realizarFalloFL.setMostrarPorcentaje(false);
        realizarFalloFL.setMostrarPuntos(false);
        if (realizarFalloFL.getIdTipoFallo() != null
                && realizarFalloFL.getIdTipoFallo().equals(EnumTipoFallo.CONDENATORIO_PARCIAL.getValue())) {
            realizarFalloFL.setMostrarPorcentaje(true);
            realizarFalloFL.setMostrarPuntos(true);
            realizarFalloFL.setPorcentaje(null);
        } else if (realizarFalloFL.getIdTipoFallo() != null
                && realizarFalloFL.getIdTipoFallo().equals(EnumTipoFallo.ABSOLUTORIO.getValue())) {
            realizarFalloFL.setPorcentaje(CERO);
            realizarFalloFL.setPuntos(null);
        } else {
            realizarFalloFL.setPorcentaje(CIEN);
            realizarFalloFL.setMostrarPuntos(true);
        }
    }

    /**
     * Genera vista preliminar del documento a generar
     * 
     * @author julio.pinzon 2016-06-27
     */
    public void generarVistaPreliminar() {
        logger.debug("RealizarFalloMB::generarVistaPreliminar()");
        RealizarFalloFL realizarFalloFL = findFlowObject(RealizarFalloFL.class, RealizarFalloFL.NOMBRE_BEAN);
        try {
            // Arma objeto para generar vista preliminar del documento
            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
            generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
            ProcesoPlantillaDTO proceso = new ProcesoPlantillaDTO();
            proceso.setEnumProcesoPlantilla(EnumProcesoPlantilla.IMPUGNACION_FALLO);
            for (SelectItem item : realizarFalloFL.getLstPlantillas()) {
                if (item.getValue().equals(realizarFalloFL.getCodigoPlantilla())) {
                    proceso.setCodigoPlantilla(item.getValue().toString());
                    proceso.setDescripcion(item.getLabel());
                    break;
                }
            }
            generaDocumento.setIdTipoDocumentoGenerado(proceso);
            Object[] valoresParametros = { realizarFalloFL.getIdProceso() };
            generaDocumento.setValoresParametros(valoresParametros);
            Map<String, Object> valoresVistaPreliminar = new HashMap<>();
            valoresVistaPreliminar.put(ConstantesDocumentosC2.REGISTRO_FALLO, realizarFalloFL.getMotivoFallo());
            generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
            ArchivoTransportableDTO documento = iRDocumentosCirculemos.generarDocumentoPreliminar(generaDocumento);
            if (documento != null) {
                visualizarDocumentoMB.setVisualizar(true);
                visualizarDocumentoMB.visualizarDocumento(documento.getContenido(), NOMBRE_ARCHIVO, null,
                        getBundle(NOMBRE_BUNDLE).getString("documento_fallo"));
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public EncabezadoImpugnacionMB getEncabezadoImpugnacion() {
        return encabezadoImpugnacion;
    }

    public void setEncabezadoImpugnacion(EncabezadoImpugnacionMB encabezadoImpugnacion) {
        this.encabezadoImpugnacion = encabezadoImpugnacion;
    }

    public VisualizarDocumentoMB getVisualizarDocumentoMB() {
        return visualizarDocumentoMB;
    }

    public void setVisualizarDocumentoMB(VisualizarDocumentoMB visualizarDocumentoMB) {
        this.visualizarDocumentoMB = visualizarDocumentoMB;
    }

}
