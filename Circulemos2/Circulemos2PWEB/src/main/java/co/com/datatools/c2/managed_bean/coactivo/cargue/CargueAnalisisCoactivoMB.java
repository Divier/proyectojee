package co.com.datatools.c2.managed_bean.coactivo.cargue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.constantes.ConstantesCargaArchivos;
import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.cargue.RespuestaValidaCargueArchivoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumEstadoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumTipoDocumentoResulCargue;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;
import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.FormatoReporte;
import co.com.datatools.c2.reporte.GeneradorReporte;
import co.com.datatools.c2.reporte.data.AbstractConectorPlantillaReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.file.FileValidator;

/**
 * Managed Bean para manipulacion de cargue de coactivos
 * 
 * @author Dixon.Alvarez
 * 
 */
@ManagedBean
@SessionScoped
public class CargueAnalisisCoactivoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(CargueAnalisisCoactivoMB.class.getName());

    private static final String BUNDLE_CARGUE_COACTIVO = "labelCargueCoactivo";

    private static final String REPORT_ID_MULTAS_NO_VALIDAS = "REPORTE_MULTAS_NO_VALIDAS_COACTIVO";
    private static final String REPORT_ID_MULTAS_VALIDAS = "REPORTE_MULTAS_VALIDAS_COACTIVO";
    private static final String NOMBRE_REPORTE_MULTAS_NO_VALIDAS = "Multas_no_validas";
    private static final String NOMBRE_REPORTE_MULTAS_VALIDAS = "Multas_validas";

    /**
     * Nombre del atributo a buscar en el ServletContext para obtener el objeto de tipo {@link AbstractConectorPlantillaReporte} para usar en la
     * aplicacion
     */
    private static final String NOMBRE_OBJ_CONECTOR_PLANTILLA = "REPORTES_CONECTOR_PLANTILLAS";

    private StreamedContent instructivo;
    private String mensajeErrorTamanioArchivo;
    private int tamanioMaximoArchivo;

    private String mensajeInfoArchivo;
    private String mensajeCargueActivo;

    /**
     * EJB para consulta y manejo de logica de las configuraciones de condiciones de coactivo
     */
    @EJB
    private IRAdministracionCoactivo iRAdministracionCoactivo;
    /**
     * EJB para consulta de variables y valores de condiciones de coactivo
     */
    @EJB
    private IRCoactivo iRCoactivo;

    @EJB
    private IRCatalogo iRCatalogo;

    @EJB
    private IRParametro parametroEjb;

    @EJB
    private IRCargueMasivo iRCargueMasivo;

    @EJB
    private IRRepositorioArchivo irRepositorioArchivo;

    public CargueAnalisisCoactivoMB() {
        super();
    }

    @PostConstruct
    public void init() {
        /*
         * Carga de parametro tamaño maximo archivo adjuntar
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArchivo = valTamMaxAdjArch.getValorParamInt();
        mensajeErrorTamanioArchivo = MessageFormat.format(getLabel("msg_error_limite_tamanio"), tamanioMaximoArchivo);
    }

    /**
     * Inicializa datos para identificar que se debe mostrar
     * 
     * @author julio.pinzon 2016-11-23
     */
    public void inicializar() {
        logger.debug(CargueAnalisisCoactivoMB.class.getName().concat("inicializar()"));
        reiniciarValores();

        ValorParametroDTO numMaxLineasParam = parametroEjb
                .consultarParametro(EnumParametro.NUM_MAX_LINEAS_CARGUE_COACTIVO, getCodigoOrganismoTransito(), true);
        int numMaxLineas = numMaxLineasParam.getValorParamInt();

        mensajeInfoArchivo = MessageFormat.format(getLabel("lbl_encabezado_cargue"), numMaxLineas);

        CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL = findFlowObject(
                CargueAnalisisCoactivoHolderFL.class, CargueAnalisisCoactivoHolderFL.NOMBRE_BEAN);
        cargueAnalisisCoactivoHolderFL.setSinCargueActivo(true);

        validarCargue(cargueAnalisisCoactivoHolderFL);
    }

    /**
     * Metodo encargado de cargar el archivo y reaizar las validaciones respectivas
     * 
     * @author Dixon.Alvarez
     */
    public void cargarArchivo(FileUploadEvent event) {
        logger.debug(CargueAnalisisCoactivoMB.class.getName().concat("::cargarArchivo()"));

        final UploadedFile file = event.getFile();

        if (file.getFileName().length() > ConstantesCargaArchivos.LONGITUD_NOMBRE_ARCHIVO) {
            String mensajeError = MessageFormat.format(getLabel("msg_error_longitud_nombre_archivo"),
                    ConstantesCargaArchivos.LONGITUD_NOMBRE_ARCHIVO);
            getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensajeError));
        } else {

            ConfiguracionCoactivoDTO configuracionCoactivoDTO = null;
            List<ConfiguracionCoactivoDTO> configuraciones = iRAdministracionCoactivo
                    .consultarConfiguracionCoactivo(null, true);
            CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL = findFlowObject(
                    CargueAnalisisCoactivoHolderFL.class, CargueAnalisisCoactivoHolderFL.NOMBRE_BEAN);

            if (configuraciones.isEmpty()) {
                addErrorMessage(BUNDLE_CARGUE_COACTIVO, "msg_error_configuracion_coactivo_vigente");
            } else {
                configuracionCoactivoDTO = configuraciones.get(0);
                cargueAnalisisCoactivoHolderFL.setConfiguracionCoactivoDTO(configuracionCoactivoDTO);

                cargueAnalisisCoactivoHolderFL.setArchivoCargado(true);
                cargueAnalisisCoactivoHolderFL.setArchivoProcesado(false);
                cargueAnalisisCoactivoHolderFL.setArchivoEnProceso(false);
                try {
                    if (FileValidator.checkFileType(file.getInputstream(), file.getFileName(), "csv")) {

                        validarCargue(cargueAnalisisCoactivoHolderFL);
                        if (cargueAnalisisCoactivoHolderFL.isSinCargueActivo()) {
                            // insertar nombre y contenido en el transportadorDTO
                            ArchivoTransportableDTO archivoTransportableDTO = new ArchivoTransportableDTO();

                            archivoTransportableDTO.setNombre(file.getFileName());
                            archivoTransportableDTO.setContenido(file.getContents());
                            cargueAnalisisCoactivoHolderFL.setArchivoCargadoAnalisis(archivoTransportableDTO);

                            CargueArchivoDTO cargueArchivo = iRCargueMasivo.registrarCargueMasivo(
                                    archivoTransportableDTO, EnumTipoCargueArchivo.ANALISIS_CARTERA,
                                    EnumCategoriaDocumento.ARCHIVO_CARGUE_COACTIVOS,
                                    EnumConsecutivo.NUMERO_CARGUE_ARCHIVO_COACTIVOS);

                            cargueAnalisisCoactivoHolderFL.setCargue(new CargueArchivoDTO(cargueArchivo.getId()));
                            Future<CargueArchivoDTO> resultado = iRCoactivo.cargarArchivoCoactivos(cargueArchivo,
                                    archivoTransportableDTO, configuracionCoactivoDTO);
                            if (resultado.isDone()) {
                                cargueAnalisisCoactivoHolderFL.setCargue(new CargueArchivoDTO(resultado.get().getId()));
                                cargueAnalisisCoactivoHolderFL.setArchivoProcesado(true);
                                cargueAnalisisCoactivoHolderFL.setSinCargueActivo(false);
                                colocarMensajes(cargueAnalisisCoactivoHolderFL);
                            } else {
                                cargueAnalisisCoactivoHolderFL.setArchivoEnProceso(true);
                                cargueAnalisisCoactivoHolderFL.setSinCargueActivo(false);
                            }
                            cargueAnalisisCoactivoHolderFL.setArchivoCargado(false);
                        } else {
                            getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                    getLabel("msg_error_cargue_activo")));
                        }
                    } else {
                        addErrorMessage(BUNDLE_CARGUE_COACTIVO, "msg_error_tipo_dato");
                    }

                } catch (CirculemosNegocioException e) {
                    CirculemosErrorHandler.handleException(e);
                } catch (IOException e) {
                    addErrorMessage(BUNDLE_CARGUE_COACTIVO, "msg_error_tipo_dato");
                } catch (InterruptedException e) {
                    logger.error("Error en cargue masivo asincrono", e);
                } catch (ExecutionException e) {
                    logger.error("Error en cargue masivo asincrono", e);
                }
            }
        }
    }

    /**
     * Finaliza el cargue
     * 
     * @return
     * @author julio.pinzon
     */
    public void finalizarCargueCoactivos(boolean aceptar) {
        logger.debug(CargueAnalisisCoactivoMB.class.getName().concat("::registrarPrecoactivos()"));
        CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL = findFlowObject(
                CargueAnalisisCoactivoHolderFL.class, CargueAnalisisCoactivoHolderFL.NOMBRE_BEAN);
        iRCoactivo.modificarCarguePrecoactivos(cargueAnalisisCoactivoHolderFL.getCargue(), aceptar);
        iRCargueMasivo.notificarCargueArchivo(cargueAnalisisCoactivoHolderFL.getCargue());
        if (aceptar) {
            getFacesContext().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            MessageFormat.format(getLabel("msg_actualizacion_multas"),
                                    cargueAnalisisCoactivoHolderFL.getCargue().getTotalLeidos())));

        }
    }

    /**
     * Descarga las multas invalidas
     * 
     * @return
     * @author julio.pinzon
     */
    public StreamedContent generarArchivoMultasInvalidas() {
        logger.debug(CargueAnalisisCoactivoMB.class.getName().concat("::generarArchivoMultasInvalidas()"));
        CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL = findFlowObject(
                CargueAnalisisCoactivoHolderFL.class, CargueAnalisisCoactivoHolderFL.NOMBRE_BEAN);
        StreamedContent streamedContent = null;
        try {
            if (cargueAnalisisCoactivoHolderFL.getArchivoInconsistencias() == null) {
                ArchivoTransportableDTO inconsistencias = irRepositorioArchivo.consultarDocumento(
                        cargueAnalisisCoactivoHolderFL.getCargue().getIdDocumentoInconsistencias().toString());

                ContenidoReporte contenido = new ContenidoReporte();
                List<Object> listaContenido = new ArrayList<Object>();
                List<Object> registros;

                try (ByteArrayInputStream fis = new ByteArrayInputStream(inconsistencias.getContenido());
                        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                        BufferedReader br = new BufferedReader(isr)) {
                    String linea = null;
                    while ((linea = br.readLine()) != null) {
                        Object[] campos = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR);
                        registros = new ArrayList<Object>();
                        for (Object campo : campos) {
                            registros.add(campo);
                        }
                        listaContenido.add(registros);
                    }
                } catch (IOException e) {
                    logger.error("Error al procesar el cargue analisis cartera", e);
                }
                contenido.setContenido(listaContenido);
                super.obtenerEncabezadoReporte(contenido);
                contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, new ArrayList<>());

                contenido.setCodigo(REPORT_ID_MULTAS_NO_VALIDAS);

                final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                        .getContext();

                final GeneradorReporte generadorReporte = new GeneradorReporte(
                        (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

                inconsistencias.setContenido(
                        generadorReporte.asBytes(FormatoReporte.XLSX, contenido, REPORT_ID_MULTAS_NO_VALIDAS));
                inconsistencias.setNombre(NOMBRE_REPORTE_MULTAS_NO_VALIDAS + "." + FormatoReporte.XLSX);
                cargueAnalisisCoactivoHolderFL.setArchivoInconsistencias(inconsistencias);
            }
            streamedContent = new DefaultStreamedContent(
                    new ByteArrayInputStream(cargueAnalisisCoactivoHolderFL.getArchivoInconsistencias().getContenido()),
                    FormatoReporte.XLSX.contentType,
                    cargueAnalisisCoactivoHolderFL.getArchivoInconsistencias().getNombre());

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        return streamedContent;
    }

    /**
     * Descarga las multas validas
     * 
     * @return
     * @author julio.pinzon
     */
    public StreamedContent generarArchivoMultasValidas() {
        logger.debug(CargueAnalisisCoactivoMB.class.getName().concat("::generarArchivoMultasValidas()"));
        CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL = findFlowObject(
                CargueAnalisisCoactivoHolderFL.class, CargueAnalisisCoactivoHolderFL.NOMBRE_BEAN);
        StreamedContent streamedContent = null;
        try {
            cargueAnalisisCoactivoHolderFL.setArchivoValidas(null);
            if (cargueAnalisisCoactivoHolderFL.getArchivoValidas() == null) {
                // Poner Archivo de resultados
                List<DocumentoResultadoCargueDTO> documentos = iRCargueMasivo
                        .consultarDocumentosResultadoCargue(cargueAnalisisCoactivoHolderFL.getCargue());
                ArchivoTransportableDTO archivoValidas = null;
                if (documentos != null && documentos.size() > 0) {
                    for (DocumentoResultadoCargueDTO documentoResul : documentos) {
                        if (documentoResul.getTipoDocumentoResultadoCargue()
                                .getId() == EnumTipoDocumentoResulCargue.SIN_IDENTIFICAR.getValue()) {
                            archivoValidas = irRepositorioArchivo
                                    .consultarDocumento(documentoResul.getIdDocumentoResultado().toString());
                            break;
                        }
                    }
                }
                cargueAnalisisCoactivoHolderFL.setArchivoValidas(archivoValidas);

                ContenidoReporte contenido = new ContenidoReporte();
                List<Object> listaContenido = new ArrayList<Object>();
                List<Object> registros;

                try (ByteArrayInputStream fis = new ByteArrayInputStream(
                        cargueAnalisisCoactivoHolderFL.getArchivoValidas().getContenido());
                        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                        BufferedReader br = new BufferedReader(isr)) {
                    String linea = null;
                    while ((linea = br.readLine()) != null) {
                        Object[] campos = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR);
                        registros = new ArrayList<Object>();
                        for (Object campo : campos) {
                            registros.add(campo);
                        }
                        listaContenido.add(registros);
                    }
                } catch (IOException e) {
                    logger.error("Error al procesar el cargue analisis cartera", e);
                }
                contenido.setContenido(listaContenido);
                super.obtenerEncabezadoReporte(contenido);
                contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, new ArrayList<>());

                contenido.setCodigo(REPORT_ID_MULTAS_VALIDAS);

                final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                        .getContext();

                final GeneradorReporte generadorReporte = new GeneradorReporte(
                        (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

                archivoValidas.setContenido(
                        generadorReporte.asBytes(FormatoReporte.XLSX, contenido, REPORT_ID_MULTAS_VALIDAS));
                archivoValidas.setNombre(NOMBRE_REPORTE_MULTAS_VALIDAS + "." + FormatoReporte.XLSX);
            }
            streamedContent = new DefaultStreamedContent(
                    new ByteArrayInputStream(cargueAnalisisCoactivoHolderFL.getArchivoValidas().getContenido()),
                    FormatoReporte.XLSX.contentType, cargueAnalisisCoactivoHolderFL.getArchivoValidas().getNombre());

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        return streamedContent;
    }

    /**
     * Obtiene label del bundle
     * 
     * @param label
     * @return
     * @author dixon.alvarez
     */
    public String getLabel(String label) {
        ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication()
                .getResourceBundle(FacesContext.getCurrentInstance(), BUNDLE_CARGUE_COACTIVO);
        return bundle.getString(label);
    }

    /**
     * Descarga instructivo
     * 
     * @return
     * @author dixon.alvarez
     */
    public StreamedContent getInstructivo() {
        Path pathManual = Paths.get(ConstantesCargaArchivos.UBICACION_SERVER,
                ConstantesCargaArchivos.NOMBRE_INSTRUCTIVO);
        try (InputStream stream = new ByteArrayInputStream(Files.readAllBytes(pathManual))) {
            instructivo = new DefaultStreamedContent(stream, "pdf/application",
                    ConstantesCargaArchivos.NOMBRE_INSTRUCTIVO);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return instructivo;
    }

    /**
     * Se reinicia todo
     * 
     * @author julio.pinzon 2016-11-23
     */
    public void reiniciarValores() {
        logger.debug(CargueAnalisisCoactivoMB.class.getName().concat("reiniciarValores()"));
        CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL = findFlowObject(
                CargueAnalisisCoactivoHolderFL.class, CargueAnalisisCoactivoHolderFL.NOMBRE_BEAN);
        cargueAnalisisCoactivoHolderFL.setArchivoProcesado(false);
        cargueAnalisisCoactivoHolderFL.setArchivoCargado(false);
        cargueAnalisisCoactivoHolderFL.setArchivoCargadoAnalisis(null);
        cargueAnalisisCoactivoHolderFL.setArchivoCargado(false);
        cargueAnalisisCoactivoHolderFL.setArchivoEnProceso(false);
        cargueAnalisisCoactivoHolderFL.setCargue(null);
        cargueAnalisisCoactivoHolderFL.setSinCargueActivo(true);
        cargueAnalisisCoactivoHolderFL.setArchivoEnProcesoOtroUsuario(false);
        cargueAnalisisCoactivoHolderFL.setArchivoInconsistencias(null);
        cargueAnalisisCoactivoHolderFL.setProgresoIniciado(false);
    }

    /**
     * Listener para progreso de la barra
     * 
     * @author julio.pinzon 2016-11-23
     */
    public Integer getProgresoCargue() {
        logger.debug(CargueAnalisisCoactivoMB.class.getName().concat("getProgresoCargue()"));
        CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL = findFlowObject(
                CargueAnalisisCoactivoHolderFL.class, CargueAnalisisCoactivoHolderFL.NOMBRE_BEAN);

        int progress = 0;
        if (cargueAnalisisCoactivoHolderFL.isArchivoEnProceso()) {
            List<CargueArchivoDTO> cargues = iRCargueMasivo
                    .consultarCargueArchivo(new CargueArchivoDTO(cargueAnalisisCoactivoHolderFL.getCargue().getId()));
            if (cargues != null && cargues.size() > 0) {
                CargueArchivoDTO cargueActivo = cargues.get(0);
                cargueAnalisisCoactivoHolderFL.setCargue(cargueActivo);
                if (cargueActivo.getEstadoCargueArchivo().getId() == EnumEstadoCargueArchivo.PROCESADO.getValue()) {
                    progress = 100;
                } else {
                    progress = (int) ((((float) cargueActivo.getTotalLeidos() + cargueActivo.getTotalInconsistencias())
                            / cargueActivo.getTotalRegistros()) * 100);
                    // No completa hasta que el cargue se encuentre procesado
                    if (progress == 100) {
                        progress = 99;
                    }
                }
            } else {
                addErrorMessage(BUNDLE_CARGUE_COACTIVO, "msg_error_progreso_cargue");
                return null;
            }

            if (!cargueAnalisisCoactivoHolderFL.isProgresoIniciado()) {
                RequestContext.getCurrentInstance().execute("PF('pbAjax').start();");
                cargueAnalisisCoactivoHolderFL.setProgresoIniciado(true);
            }
        }
        return progress;
    }

    /**
     * Listener cuando esta el cargue completo
     * 
     * @author julio.pinzon 2016-11-23
     */
    public void cargueCompleto() {
        logger.debug(CargueAnalisisCoactivoMB.class.getName().concat("cargueCompleto()"));
        CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL = findFlowObject(
                CargueAnalisisCoactivoHolderFL.class, CargueAnalisisCoactivoHolderFL.NOMBRE_BEAN);
        cargueAnalisisCoactivoHolderFL.setArchivoEnProceso(false);
        cargueAnalisisCoactivoHolderFL.setArchivoEnProcesoOtroUsuario(false);
        cargueAnalisisCoactivoHolderFL.setArchivoProcesado(false);
        cargueAnalisisCoactivoHolderFL.setSinCargueActivo(true);
        if (cargueAnalisisCoactivoHolderFL.getCargue().getUsuarioCargue().getLogin().equals(getLogin())) {
            colocarMensajes(cargueAnalisisCoactivoHolderFL);
            cargueAnalisisCoactivoHolderFL.setArchivoProcesado(true);
            RequestContext.getCurrentInstance().execute("PF('popUpResultadoCargue').show();");
        }
    }

    /**
     * Coloca mensajes de resultado
     * 
     * @param cargueAnalisisCoactivoHolderFL
     * @author julio.pinzon 2016-11-23
     */
    private void colocarMensajes(CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL) {
        logger.debug(
                CargueAnalisisCoactivoMB.class.getName().concat("colocarMensajes(CargueAnalisisCoactivoHolderFL)"));
        cargueAnalisisCoactivoHolderFL.setCantidadMultasArchivo(MessageFormat.format(
                getLabel("cantidad_multas_archivo"), cargueAnalisisCoactivoHolderFL.getCargue().getTotalRegistros()));
        cargueAnalisisCoactivoHolderFL
                .setMultasNoValidasCoactivo(MessageFormat.format(getLabel("multas_no_validas_coactivo"),
                        cargueAnalisisCoactivoHolderFL.getCargue().getTotalInconsistencias()));
        cargueAnalisisCoactivoHolderFL.setMultasValidasCoactivo(MessageFormat.format(
                getLabel("multas_validas_coactivo"), cargueAnalisisCoactivoHolderFL.getCargue().getTotalLeidos()));

    }

    /**
     * Validar cargue activo
     * 
     * @author julio.pinzon 2016-12-16
     */
    private void validarCargue(CargueAnalisisCoactivoHolderFL cargueAnalisisCoactivoHolderFL) {
        logger.debug(
                CargueAnalisisCoactivoMB.class.getName().concat("validarCargue(CargueMasivoUbicabilidadHolderFL)"));

        List<EnumTipoCargueArchivo> tiposCargue = new ArrayList<>();
        tiposCargue.add(EnumTipoCargueArchivo.ANALISIS_CARTERA);
        // Valida cargues activos
        RespuestaValidaCargueArchivoDTO respuesta = iRCargueMasivo.validarCargueActivo(tiposCargue);
        cargueAnalisisCoactivoHolderFL.setArchivoEnProcesoOtroUsuario(respuesta.isArchivoEnProcesoOtroUsuario());
        cargueAnalisisCoactivoHolderFL.setArchivoEnProceso(respuesta.isArchivoEnProceso());
        cargueAnalisisCoactivoHolderFL.setSinCargueActivo(respuesta.isSinCargueActivo());
        cargueAnalisisCoactivoHolderFL.setArchivoProcesado(respuesta.isArchivoProcesado());
        cargueAnalisisCoactivoHolderFL.setCargue(respuesta.getCargue());
        if (cargueAnalisisCoactivoHolderFL.isArchivoEnProcesoOtroUsuario()) {
            mensajeCargueActivo = MessageFormat.format(getLabel("msg_cargue_otro_usuario"),
                    cargueAnalisisCoactivoHolderFL.getCargue().getUsuarioCargue().getLogin(), Utilidades
                            .dateToStringFormatApp(cargueAnalisisCoactivoHolderFL.getCargue().getFechaCargue(), true));
        } else if (cargueAnalisisCoactivoHolderFL.isArchivoProcesado()) {
            colocarMensajes(cargueAnalisisCoactivoHolderFL);
        }
    }

    public String getMensajeErrorTamanioArchivo() {
        return mensajeErrorTamanioArchivo;
    }

    public void setMensajeErrorTamanioArchivo(String mensajeErrorTamanioArchivo) {
        this.mensajeErrorTamanioArchivo = mensajeErrorTamanioArchivo;
    }

    public int getTamanioMaximoArchivo() {
        return tamanioMaximoArchivo;
    }

    public void setTamanioMaximoArchivo(int tamanioMaximoArchivo) {
        this.tamanioMaximoArchivo = tamanioMaximoArchivo;
    }

    public String getMensajeCargueActivo() {
        return mensajeCargueActivo;
    }

    public String getMensajeInfoArchivo() {
        return mensajeInfoArchivo;
    }

}
