package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ConstantesDocumentosC2;
import co.com.datatools.c2.dto.EvaluarImpugnacionDTO;
import co.com.datatools.c2.dto.GeneraDocumentoDTO;
import co.com.datatools.c2.dto.JustificacionImpugnacionDTO;
import co.com.datatools.c2.dto.PlantillaDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.ProcesoPlantillaDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumProcesoPlantilla;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRImpugnacion;
import co.com.datatools.c2.negocio.interfaces.IRProceso;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Managed Bean para el manejo de evaluar expediente HU_CIR20_DAT_JUR_004
 * 
 * @author dixon.alvarez 2016-06-10
 * 
 */
@ManagedBean
@SessionScoped
public class EvaluarExpedienteMB extends AbstractSwfManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(EvaluarExpedienteMB.class.getName());

    private static final String NOMBRE_BUNDLE_IMPUGNACION = "labelProcesoImpugnacion";
    private static final String NOMBRE_ARCHIVO_EVALUACION = "Apertura de proceso de Impugnación.pdf";

    private static final String MENSAJE_ERROR_ENVIO_NOTIFICACION = "JUR_004003";

    @ManagedProperty(value = "#{encabezadoImpugnacionMB}")
    EncabezadoImpugnacionMB encabezadoImpugnacion;

    @EJB
    private IRProceso irProceso;

    @EJB
    private IRImpugnacion irImpugnacion;

    @EJB
    private IRDocumentosCirculemos irDocumentos;

    @ManagedProperty(value = "#{visualizarDocumentoMB}")
    private VisualizarDocumentoMB visualizarDocumentoMB;

    /**
     * Inicializa los datos que se utilizan en la GUI e indica si continua o no con la evaluacion de expediente
     * 
     * @return True, si cumple con las condiciones para evaluar expediente
     */
    public boolean inicializarDatos() {
        logger.debug("EvaluarExpedienteMB::inicializarDatos()");
        EvaluarExpedienteFL evaluarExpedienteFL = findFlowObject(EvaluarExpedienteFL.class,
                EvaluarExpedienteFL.NOMBRE_BEAN);
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        // obtiene el proceso seleccionado
        if (impugnacionHolderFL.getComparendoSeleccionado() == null
                || impugnacionHolderFL.getComparendoSeleccionado().getIdProceso() == null) {
            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_error_proceso_no_seleccionado");
            return false;
        }
        // Valida si el estado actual del proceso permite evaluar expediente
        if (!EnumEstadoProceso.ECUADOR_IMPUGNACION_IMPUGNADO.getValue()
                .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())) {
            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_error_estado_evaluar_expediente");
            return false;
        }
        // Llena lista de plantillas
        if (evaluarExpedienteFL.getLstPlantillas() == null) {
            List<SelectItem> lstPlantillas = new ArrayList<>();
            try {
                List<PlantillaDTO> plantillasDocumentos = irDocumentos.consultarPlantillasPorProceso(
                        EnumProcesoPlantilla.IMPUGNACION_SIN_SOLUCION_INMEDIATA.getCodigo());
                for (PlantillaDTO plantillaDTO : plantillasDocumentos) {
                    lstPlantillas
                            .add(new SelectItem(plantillaDTO.getCodigoPlantilla(), plantillaDTO.getNombrePlantilla()));
                }
            } catch (CirculemosAlertaException e) {
                CirculemosErrorHandler.handleException(e);
            }
            evaluarExpedienteFL.setLstPlantillas(lstPlantillas);
        }
        evaluarExpedienteFL.setIdProceso(impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());
        evaluarExpedienteFL.setCicomparendo(impugnacionHolderFL.getComparendoSeleccionado().getIdComparendo());
        encabezadoImpugnacion.consultarEncabezado(evaluarExpedienteFL.getIdProceso()); // Id del proceso seleccionado
        justificacionObligatoria();
        return true;
    }

    /**
     * Registra la evaluacion del expediente
     * 
     * @return ERROR_DATOS si se genera algun error al guardar, EVALUADO o ENVIADO_BACK teniendo en cuenta el estado al que se actualizo el proceso
     * @author giovanni.velandia (mod 10-11-2016)
     */
    public String guardar() {
        logger.debug("EvaluarExpedienteMB::guardar()");
        String resultado = "ERROR_DATOS";
        EvaluarExpedienteFL evaluarExpedienteFL = findFlowObject(EvaluarExpedienteFL.class,
                EvaluarExpedienteFL.NOMBRE_BEAN);
        EvaluarImpugnacionDTO evaluarExpedienteDTO = new EvaluarImpugnacionDTO();
        evaluarExpedienteDTO.setSolucionInmediata(evaluarExpedienteFL.isSolucionInmediata());
        if (evaluarExpedienteFL.getIdJustificacion() != null) {
            JustificacionImpugnacionDTO justificacionImpugnacionDTO = new JustificacionImpugnacionDTO();
            justificacionImpugnacionDTO.setId(evaluarExpedienteFL.getIdJustificacion());
            evaluarExpedienteDTO.setJustificacionImpugnacion(justificacionImpugnacionDTO);
        }
        TrazabilidadProcesoDTO trazabilidadProcesoDTO = new TrazabilidadProcesoDTO();
        ProcesoDTO procesoDTO = new ProcesoDTO();
        procesoDTO.setId(evaluarExpedienteFL.getIdProceso());
        trazabilidadProcesoDTO.setProceso(procesoDTO);

        evaluarExpedienteDTO.setTrazabilidadProceso(trazabilidadProcesoDTO);
        evaluarExpedienteDTO.setConsideracionJuridica(evaluarExpedienteFL.getConsideracionJuridica());
        evaluarExpedienteDTO.setCodigoPlantilla(evaluarExpedienteFL.getCodigoPlantilla());
        try {
            EvaluarImpugnacionDTO evaluarImpugnacionDTO = irImpugnacion.evaluarExpediente(evaluarExpedienteDTO);
            ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                    ImpugnacionHolderFL.NOMBRE_BEAN);

            String nombreArchivo = "ARCHIVO";
            for (Iterator iterator = evaluarExpedienteFL.getLstPlantillas().iterator(); iterator.hasNext();) {
                SelectItem item = (SelectItem) iterator.next();
                if (item.getValue().equals(evaluarExpedienteFL.getCodigoPlantilla())) {
                    nombreArchivo = item.getLabel();
                    break;
                }
            }

            Long idDocumento = null;
            if (evaluarImpugnacionDTO.getTrazabilidadProceso().getDocumentos() != null) {
                idDocumento = evaluarImpugnacionDTO.getTrazabilidadProceso().getDocumentos().get(0)
                        .getNumeroDocumento();

                List<Long> idDocumentos = new ArrayList<>();
                idDocumentos.add(idDocumento);
                byte[] archivo = irDocumentos.consultarDocumentosPDF(idDocumentos);

                if (archivo != null) {
                    visualizarDocumentoMB.setVisualizar(false);
                    visualizarDocumentoMB.visualizarDocumento(archivo, nombreArchivo, null, null);
                }
            }

            EnumEstadoProceso estadoActualizado = EnumEstadoProceso.ECUADOR_IMPUGNACION_IMPUGNADO;
            if (evaluarImpugnacionDTO.getSolucionInmediata().equals(true)) {
                estadoActualizado = EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO;
                resultado = "EVALUADO";
            } else {
                estadoActualizado = EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION;
                resultado = "ENVIADO_BACK";
            }
            impugnacionHolderFL.getComparendoSeleccionado().setIdEstadoProceso(estadoActualizado.getValue());
            // Mensaje de guardado exitoso
            addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_evaluacion_expediente_guardado");
            if (idDocumento != null) {
                irImpugnacion.enviarCorreoEvaluarImpugnacion(trazabilidadProcesoDTO.getProceso().getId(), idDocumento,
                        evaluarImpugnacionDTO.getTrazabilidadProceso().getId());
            }
        } catch (CirculemosNegocioException e) {
            if (e.getErrorInfo().getCodigoError().equals(MENSAJE_ERROR_ENVIO_NOTIFICACION)) {
                visualizarDocumentoMB
                        .setTituloNotificacion(getBundle(NOMBRE_BUNDLE_IMPUGNACION).getString("label_advertencia"));
                visualizarDocumentoMB.setMensajeNotificacion(e.getErrorInfo().getDescError());
                visualizarDocumentoMB.setVisualizarNotificacion(true);
            } else {
                CirculemosErrorHandler.handleException(e);
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        evaluarExpedienteFL.setResultadoGuardar(resultado);
        return resultado;
    }

    public String validarGuardado() {
        logger.debug("EvaluarExpedienteMB::validarGuardado()");
        EvaluarExpedienteFL evaluarExpedienteFL = findFlowObject(EvaluarExpedienteFL.class,
                EvaluarExpedienteFL.NOMBRE_BEAN);
        return evaluarExpedienteFL.getResultadoGuardar();
    }

    public void justificacionObligatoria() {
        EvaluarExpedienteFL evaluarExpedienteFL = findFlowObject(EvaluarExpedienteFL.class,
                EvaluarExpedienteFL.NOMBRE_BEAN);

        evaluarExpedienteFL.setCodigoPlantilla(null);
        if (evaluarExpedienteFL.isSolucionInmediata()) {
            evaluarExpedienteFL.setJustificacionObligatoria(false);
        } else {
            evaluarExpedienteFL.setJustificacionObligatoria(true);
        }
    }

    public void generarVistaPreliminar() {
        logger.debug("RealizarFalloMB::generarVistaPreliminar()");
        EvaluarExpedienteFL evaluarExpedienteFL = findFlowObject(EvaluarExpedienteFL.class,
                EvaluarExpedienteFL.NOMBRE_BEAN);
        try {
            // Arma objeto para generar vista preliminar del documento
            GeneraDocumentoDTO generaDocumento = new GeneraDocumentoDTO();
            generaDocumento.setFechaGeneracion(UtilFecha.buildCalendar().getTime());
            ProcesoPlantillaDTO proceso = new ProcesoPlantillaDTO();
            proceso.setEnumProcesoPlantilla(EnumProcesoPlantilla.IMPUGNACION_SIN_SOLUCION_INMEDIATA);
            for (SelectItem item : evaluarExpedienteFL.getLstPlantillas()) {
                if (item.getValue().equals(evaluarExpedienteFL.getCodigoPlantilla())) {
                    proceso.setCodigoPlantilla(item.getValue().toString());
                    proceso.setDescripcion(item.getLabel());
                    break;
                }
            }
            generaDocumento.setIdTipoDocumentoGenerado(proceso);
            Object[] valoresParametros = { evaluarExpedienteFL.getIdProceso() };
            generaDocumento.setValoresParametros(valoresParametros);
            Map<String, Object> valoresVistaPreliminar = new HashMap<>();
            valoresVistaPreliminar.put(ConstantesDocumentosC2.CONSIDERACION_JURIDICA,
                    evaluarExpedienteFL.getConsideracionJuridica());
            generaDocumento.setValoresVistaPreliminar(valoresVistaPreliminar);
            ArchivoTransportableDTO documento = irDocumentos.generarDocumentoPreliminar(generaDocumento);
            if (documento != null) {
                visualizarDocumentoMB.setVisualizar(true);
                visualizarDocumentoMB.visualizarDocumento(documento.getContenido(), NOMBRE_ARCHIVO_EVALUACION, null,
                        getBundle(NOMBRE_BUNDLE_IMPUGNACION).getString("documento_evaluacion_expediente"));
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
