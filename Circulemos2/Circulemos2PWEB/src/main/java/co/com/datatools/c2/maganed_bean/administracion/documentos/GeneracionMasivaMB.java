package co.com.datatools.c2.maganed_bean.administracion.documentos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.DocumentoMasivoDTO;
import co.com.datatools.c2.dto.RespuestaConsultaMasivaDocumentosDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoDocumentoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comun.UtilidadMB;
import co.com.datatools.c2.negocio.interfaces.IRDocumentoProceso;
import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;

/**
 * Se encarga de controlar la consulta de generacion masiva de documentos
 * 
 * @author divier.casas
 * 
 */
@ManagedBean
@SessionScoped
public class GeneracionMasivaMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(GeneracionMasivaMB.class.getName());
    private static final String NOMBRE_BUNDLE = "labelSolicitarDocumentos";

    @EJB
    private IRDocumentoProceso iRDocumentoProceso;

    @ManagedProperty(value = "#{utilidadMB}")
    private UtilidadMB utilidadMB;

    /**
     * Consulta masiva de documentos
     * 
     * @author divier.casas
     */
    public void consultarDocumentos() {
        LOGGER.debug("GeneracionMasivaMB::consultarDocumentos()");
        GeneracionMasivaHolderFL genMasivaHolderFL = findFlowObject(GeneracionMasivaHolderFL.class,
                GeneracionMasivaHolderFL.NOMBRE_BEAN);

        // Validacion codigo organismo
        if (genMasivaHolderFL.getFiltros().getCodigoOrganismo() == null) {
            getFacesContext().addMessage("form-contenido:selOrganismoTransito",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }
        // Validacion proceso
        if (genMasivaHolderFL.getFiltros().getIdTipoProceso() == null) {
            getFacesContext().addMessage("form-contenido:selProceso",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }
        // Validacion paso
        if (genMasivaHolderFL.getFiltros().getIdPaso() == null) {
            getFacesContext().addMessage("form-contenido:selPasoDocumento",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }
        // Validacion documento
        if (genMasivaHolderFL.getFiltros().getIdDocumento() == null) {
            getFacesContext().addMessage("form-contenido:selDocumento",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }

        // Validacion pareja fechas proceso
        if (genMasivaHolderFL.getFiltros().getFechaProcIni() != null) {
            if (genMasivaHolderFL.getFiltros().getFechaProcFin() == null) {
                getFacesContext().addMessage("form-contenido:fechaProcFin",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        if (genMasivaHolderFL.getFiltros().getFechaProcIni() == null) {
            if (genMasivaHolderFL.getFiltros().getFechaProcFin() != null) {
                getFacesContext().addMessage("form-contenido:fechaProcIni",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }
        // Validacion rango fecha proceso
        if (genMasivaHolderFL.getFiltros().getFechaProcIni() != null
                && genMasivaHolderFL.getFiltros().getFechaProcFin() != null && genMasivaHolderFL.getFiltros()
                        .getFechaProcIni().compareTo(genMasivaHolderFL.getFiltros().getFechaProcFin()) > 0) {
            addWarningMessage(NOMBRE_BUNDLE, "msg_rango_fecha_proceso");
            return;
        }
        // Validacion pareja fechas paso
        if (genMasivaHolderFL.getFiltros().getFechaPasoIni() != null) {
            if (genMasivaHolderFL.getFiltros().getFechaPasoFin() == null) {
                getFacesContext().addMessage("form-contenido:fechaPasoFin",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        if (genMasivaHolderFL.getFiltros().getFechaPasoIni() == null) {
            if (genMasivaHolderFL.getFiltros().getFechaPasoFin() != null) {
                getFacesContext().addMessage("form-contenido:fechaPasoIni",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }
        // Validacion rango fecha paso
        if (genMasivaHolderFL.getFiltros().getFechaPasoIni() != null
                && genMasivaHolderFL.getFiltros().getFechaPasoFin() != null && genMasivaHolderFL.getFiltros()
                        .getFechaPasoIni().compareTo(genMasivaHolderFL.getFiltros().getFechaPasoFin()) > 0) {
            addWarningMessage(NOMBRE_BUNDLE, "msg_rango_fecha_paso");
            return;
        }
        // Validacion de campos opcionales
        if ((genMasivaHolderFL.getFiltros().getNumProc() == null)
                && (genMasivaHolderFL.getFiltros().getFechaProcIni() == null
                        && genMasivaHolderFL.getFiltros().getFechaProcFin() == null)
                && (genMasivaHolderFL.getFiltros().getFechaPasoIni() == null
                        && genMasivaHolderFL.getFiltros().getFechaPasoFin() == null)) {
            addWarningMessage(NOMBRE_BUNDLE, "msg_filtros");
            return;
        }

        List<RespuestaConsultaMasivaDocumentosDTO> lsRespuesta = iRDocumentoProceso
                .consultarDocumentos(genMasivaHolderFL.getFiltros());
        if (lsRespuesta == null || lsRespuesta.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            genMasivaHolderFL.setLsRespuesta(new ArrayList<RespuestaConsultaMasivaDocumentosDTO>(0));
            return;
        } else {
            genMasivaHolderFL.setLsRespuesta(lsRespuesta);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lsRespuesta.size());
        }
    }

    /**
     * Procesamiento de documentos
     * 
     * @author divier.casas
     */
    public void generarDocumentos() {
        LOGGER.debug("GeneracionMasivaMB::generarDocumentos()");
        GeneracionMasivaHolderFL genMasivaHolderFL = findFlowObject(GeneracionMasivaHolderFL.class,
                GeneracionMasivaHolderFL.NOMBRE_BEAN);

        if (genMasivaHolderFL.getLsRespuestaSelec().size() < genMasivaHolderFL.getFiltros().getCantMaxDocs()) {
            addWarningMessage(NOMBRE_BUNDLE, "msg_validacion_agrupamiento");
            return;
        }

        DocumentoMasivoDTO docMasivoDTO = new DocumentoMasivoDTO();
        docMasivoDTO.setFechaSolicitud(UtilFecha.buildCalendar().getTime());
        docMasivoDTO.setLimiteDocumentos(genMasivaHolderFL.getFiltros().getCantMaxDocs());
        docMasivoDTO.setCantidadDocumentos(genMasivaHolderFL.getLsRespuestaSelec().size());

        Long idArchivo = iRDocumentoProceso.registrarDocumentoMasivo(docMasivoDTO,
                genMasivaHolderFL.getLsRespuestaSelec());
        try {
            iRDocumentoProceso.procesarDocumentoMasivo(idArchivo);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
        genMasivaHolderFL.setConsecutivo(idArchivo);
        genMasivaHolderFL.setRegistradaSolicitud(true);
    }

    public void seleccionarTodo() {
        GeneracionMasivaHolderFL genMasivaHolderFL = findFlowObject(GeneracionMasivaHolderFL.class,
                GeneracionMasivaHolderFL.NOMBRE_BEAN);
        genMasivaHolderFL.setLsRespuestaSelec(genMasivaHolderFL.getLsRespuesta());
    }

    public void desmarcarTodo() {
        GeneracionMasivaHolderFL genMasivaHolderFL = findFlowObject(GeneracionMasivaHolderFL.class,
                GeneracionMasivaHolderFL.NOMBRE_BEAN);
        genMasivaHolderFL.setLsRespuestaSelec(new ArrayList<RespuestaConsultaMasivaDocumentosDTO>(0));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ContenidoReporte exportar() {
        LOGGER.debug("GeneracionMasivaMB::exportar()");
        GeneracionMasivaHolderFL genMasivaHolderFL = findFlowObject(GeneracionMasivaHolderFL.class,
                GeneracionMasivaHolderFL.NOMBRE_BEAN);

        ContenidoReporte contenido = new ContenidoReporte();
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;

        for (RespuestaConsultaMasivaDocumentosDTO respuestaDTO : genMasivaHolderFL.getLsRespuesta()) {
            registros = new ArrayList<Object>();

            // Proceso
            if (respuestaDTO.getProcesoDTO().getTipoProceso().getNombre() != null) {
                registros.add(respuestaDTO.getProcesoDTO().getTipoProceso().getNombre());
            } else {
                registros.add(null);
            }

            // Paso del proceso
            if (respuestaDTO.getTrazaDTO().getEstadoProceso().getNombre() != null) {
                registros.add(respuestaDTO.getTrazaDTO().getEstadoProceso().getNombre());
            } else {
                registros.add(null);
            }

            // Documento a descargar
            if (respuestaDTO.getDocumentoDTO().getTipoDocumento().getNombre() != null) {
                registros.add(respuestaDTO.getDocumentoDTO().getTipoDocumento().getNombre());
            } else {
                registros.add(null);
            }

            // Numero del proceso
            if (respuestaDTO.getProcesoDTO().getNumeroProceso() != null) {
                registros.add(respuestaDTO.getProcesoDTO().getNumeroProceso());
            } else {
                registros.add(null);
            }

            // Fecha del proceso
            if (respuestaDTO.getProcesoDTO().getFechaInicio() != null) {
                registros.add(UtilFecha.dateToString(respuestaDTO.getProcesoDTO().getFechaInicio(),
                        utilidadMB.getFormatoFecha()));
            } else {
                registros.add(null);
            }

            // Fecha del paso
            if (respuestaDTO.getTrazaDTO().getFechaInicio() != null) {
                registros.add(UtilFecha.dateToString(respuestaDTO.getTrazaDTO().getFechaInicio(),
                        utilidadMB.getFormatoFecha()));
            } else {
                registros.add(null);
            }

            // Tipo de documento del deudor
            if (respuestaDTO.getPartProcesoDTO().getPersona().getTipoIdentificacion().getNombre() != null) {
                registros.add(respuestaDTO.getPartProcesoDTO().getPersona().getTipoIdentificacion().getNombre());
            } else {
                registros.add(null);
            }

            // Numero de documento del deudor
            if (respuestaDTO.getPartProcesoDTO().getPersona().getNumeroIdentificacion() != null) {
                registros.add(respuestaDTO.getPartProcesoDTO().getPersona().getNumeroIdentificacion());
            } else {
                registros.add(null);
            }

            // Nombre del deudor
            if (respuestaDTO.getPartProcesoDTO().getPersona().getNombreCompleto() != null) {
                registros.add(respuestaDTO.getPartProcesoDTO().getPersona().getNombreCompleto());
            } else {
                registros.add(null);
            }

            // Direccion del deudor
            if (respuestaDTO.getDireccionPersonaDTO().getDireccion().getComplemento() != null) {
                registros.add(respuestaDTO.getDireccionPersonaDTO().getDireccion().getComplemento());
            } else {
                registros.add(null);
            }
            listaContenido.add(registros);
        }
        contenido.setContenido(listaContenido);
        super.obtenerEncabezadoReporte(contenido);

        List listFiltros = new ArrayList<>();

        // Organismo de transito
        listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_organismo_transito"),
                getOrganismoTransito().getCodigoOrganismo() + " - " + getOrganismoTransito().getNombreOrganismo()));

        // Proceso
        if (genMasivaHolderFL.getFiltros().getIdTipoProceso() != null) {
            EnumTipoProceso nomTipoProceso = null;
            for (EnumTipoProceso tipoProceso : EnumTipoProceso.values()) {
                if (tipoProceso.getValue().equals(genMasivaHolderFL.getFiltros().getIdTipoProceso())) {
                    nomTipoProceso = tipoProceso;
                    break;
                }
            }
            listFiltros.add(
                    Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_proceso"), nomTipoProceso.getNombre()));
        }

        // Paso del documento a descargar
        if (genMasivaHolderFL.getFiltros().getIdPaso() != null) {
            EnumEstadoProceso nomEstadoProceso = null;
            for (EnumEstadoProceso estadoProceso : EnumEstadoProceso.values()) {
                if (estadoProceso.getValue().equals(genMasivaHolderFL.getFiltros().getIdPaso())) {
                    nomEstadoProceso = estadoProceso;
                    break;
                }
            }
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_paso_documento"),
                    nomEstadoProceso.getNombre()));
        }

        // Documento a descargar
        if (genMasivaHolderFL.getFiltros().getIdDocumento() != null) {
            EnumTipoDocumentoProceso nomTipDocProceso = null;
            for (EnumTipoDocumentoProceso tipDocProceso : EnumTipoDocumentoProceso.values()) {
                if (tipDocProceso.getValue().equals(genMasivaHolderFL.getFiltros().getIdDocumento())) {
                    nomTipDocProceso = tipDocProceso;
                    break;
                }
            }
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_documento_descargar"),
                    nomTipDocProceso.getNombre()));
        }

        // Numero de proceso
        if (genMasivaHolderFL.getFiltros().getNumProc() != null) {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_num_proceso"),
                    genMasivaHolderFL.getFiltros().getNumProc()));
        }

        // Fecha de proceso inicial
        if (genMasivaHolderFL.getFiltros().getFechaProcIni() != null) {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_fecha_proceso_inicial"),
                    genMasivaHolderFL.getFiltros().getFechaProcIni()));
        }

        // Fecha de proceso final
        if (genMasivaHolderFL.getFiltros().getFechaProcFin() != null) {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_fecha_proceso_final"),
                    genMasivaHolderFL.getFiltros().getFechaProcFin()));
        }

        // Fecha de paso inicial
        if (genMasivaHolderFL.getFiltros().getFechaPasoIni() != null) {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_fecha_paso_inicial"),
                    genMasivaHolderFL.getFiltros().getFechaPasoIni()));
        }

        // Fecha de paso final
        if (genMasivaHolderFL.getFiltros().getFechaPasoFin() != null) {
            listFiltros.add(Arrays.asList(getBundle(NOMBRE_BUNDLE).getString("label_fecha_paso_final"),
                    genMasivaHolderFL.getFiltros().getFechaPasoFin()));
        }
        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, listFiltros);
        return contenido;
    }

    public Date getFechaActual() {
        return UtilFecha.currentZeroTimeDate();
    }

    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

    /**
     * Permite identificar si fue registrada la solicitud de generacion masiva de documentos
     * 
     * @return true si debe mostrar popup con la informacion de la solicitud, false en caso contrario
     */
    public boolean mostrarPopUpProcesamiento() {
        GeneracionMasivaHolderFL genMasivaHolderFL = findFlowObject(GeneracionMasivaHolderFL.class,
                GeneracionMasivaHolderFL.NOMBRE_BEAN);
        return genMasivaHolderFL.isRegistradaSolicitud();
    }

    public UtilidadMB getUtilidadMB() {
        return utilidadMB;
    }

    public void setUtilidadMB(UtilidadMB utilidadMB) {
        this.utilidadMB = utilidadMB;
    }
}