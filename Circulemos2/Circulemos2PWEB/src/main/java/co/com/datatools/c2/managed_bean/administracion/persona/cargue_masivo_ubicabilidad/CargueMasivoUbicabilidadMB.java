package co.com.datatools.c2.managed_bean.administracion.persona.cargue_masivo_ubicabilidad;

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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.constantes.ConstantesCargaArchivos;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.RespuestaValidaCargueArchivoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.ubicabilidad.CargueMasivoUbicabilidadDTO;
import co.com.datatools.c2.enumeracion.EnumCategoriaDocumento;
import co.com.datatools.c2.enumeracion.EnumEstadoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRUbicabilidad;
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

/**
 * Managed Bean para manipulación administración de personas CU_CIR20_UBI_006
 * 
 * @author ricardo.chavarro
 * 
 */
@ManagedBean
@SessionScoped
public class CargueMasivoUbicabilidadMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(CargueMasivoUbicabilidadMB.class.getName());

    private static final String BUNDLE_CARGUE = "labelCargueMasivoUbicabilidad";

    private StreamedContent instructivo;

    private StreamedContent formatoCargueDireccion;
    private StreamedContent formatoCargueTelefono;
    private StreamedContent formatoCargueCorreo;

    public static final String FORMATO_CARGUE_UBI_DIRECCION = "FORMATO_CARGUE_UBI_DIRECCION.csv";
    public static final String FORMATO_CARGUE_UBI_TELEFONO = "FORMATO_CARGUE_UBI_TELEFONO.csv";
    public static final String FORMATO_CARGUE_UBI_CORREO = "FORMATO_CARGUE_UBI_CORREO.csv";
    private static final String REPORT_ID_INCONSISTENCIAS_DIRECCIONES = "REPORTE_INCONSISTENCIAS_CARGUE_DIRECCIONES";
    private static final String REPORT_ID_INCONSISTENCIAS_CORREOS = "REPORTE_INCONSISTENCIAS_CARGUE_CORREOS";
    private static final String REPORT_ID_INCONSISTENCIAS_TELEFONOS = "REPORTE_INCONSISTENCIAS_CARGUE_TELEFONOS";
    private static final String NOMBRE_REPORTE_INCONSISTENCIAS_DIRECCIONES = "Inconsistencias_direcciones";
    private static final String NOMBRE_REPORTE_INCONSISTENCIAS_TELEFONOS = "Inconsistencias_telefonos";
    private static final String NOMBRE_REPORTE_INCONSISTENCIAS_CORREOS = "Inconsistencias_correos";

    /**
     * Nombre del atributo a buscar en el ServletContext para obtener el objeto de tipo {@link AbstractConectorPlantillaReporte} para usar en la
     * aplicacion
     */
    private static final String NOMBRE_OBJ_CONECTOR_PLANTILLA = "REPORTES_CONECTOR_PLANTILLAS";

    private int tamanioMaximoArch;

    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(csv)$/";

    private String mensajeInfoArchivo;
    private String mensajeCargueActivo;

    private List<SelectItem> catTipoFuenteInformacion;
    private List<SelectItem> catTipoCargueArchivo;

    @EJB
    private IRUbicabilidad ubicabilidadEJB;

    @EJB
    private IRParametro parametroEJB;

    @EJB
    private IRCargueMasivo iRCargueMasivo;

    @EJB
    private IRRepositorioArchivo irRepositorioArchivo;

    @EJB
    private IRCatalogo catalogoEJB;

    /**
     * inicializa valores
     * 
     * @author manuel.chavarro
     */
    @PostConstruct
    public void init() {
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("init()"));

        ValorParametroDTO valTamMaxAdjArch = parametroEJB.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();

        catTipoFuenteInformacion = new ArrayList<>();
        List<ItemCatalogoDTO> tiposFuente = catalogoEJB
                .consultarItemsCatalogo(EnumCatalogo.TipoFuenteInformacion.name(), null);
        for (ItemCatalogoDTO tipoFuente : tiposFuente) {
            if (EnumTipoFuenteInformacion.COURIER.getValue().equals(tipoFuente.getId())
                    || EnumTipoFuenteInformacion.CIUDADANO.getValue().equals(tipoFuente.getId())
                    || EnumTipoFuenteInformacion.AXIS.getValue().equals(tipoFuente.getId())
                    || EnumTipoFuenteInformacion.AXIS_EXTERNOS.getValue().equals(tipoFuente.getId())
                    || EnumTipoFuenteInformacion.PROCESOS_CONTRAVENCIONALES.getValue().equals(tipoFuente.getId())) {
                catTipoFuenteInformacion.add(new SelectItem(tipoFuente.getId(), tipoFuente.getNombre()));
            }
        }

        catTipoCargueArchivo = new ArrayList<>();
        List<ItemCatalogoDTO> tiposCargue = catalogoEJB.consultarItemsCatalogo(EnumCatalogo.TipoCargueArchivo.name(),
                null);
        for (ItemCatalogoDTO tipoCargue : tiposCargue) {
            if (EnumTipoCargueArchivo.MASIVO_CORREO.getValue().equals(tipoCargue.getId())
                    || EnumTipoCargueArchivo.MASIVO_DIRECCION.getValue().equals(tipoCargue.getId())
                    || EnumTipoCargueArchivo.MASIVO_TELEFONO.getValue().equals(tipoCargue.getId())) {
                catTipoCargueArchivo.add(new SelectItem(tipoCargue.getId(), tipoCargue.getNombre()));
            }
        }
        // Carga valores por defecto para el tipo de fuente informacion y tipo cargue
        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);
        if (!catTipoCargueArchivo.isEmpty()) {
            cargueMasivoUbicHolderFL.setIdTipoCargueArchivo((Integer) catTipoCargueArchivo.get(0).getValue());
        }
        if (!catTipoFuenteInformacion.isEmpty()) {
            cargueMasivoUbicHolderFL.setIdTipoFuenteInformacion((Integer) catTipoFuenteInformacion.get(0).getValue());
        }
    }

    /**
     * Inicializa datos para identificar que se debe mostrar
     * 
     * @author julio.pinzon 2016-11-23
     */
    public void inicializar() {
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("inicializar()"));
        reiniciarValores();

        ValorParametroDTO numMaxLineasParam = parametroEJB
                .consultarParametro(EnumParametro.NUM_MAX_LINEAS_CARGUE_ARCHIVO, getCodigoOrganismoTransito(), true);
        int numMaxLineas = numMaxLineasParam.getValorParamInt();

        mensajeInfoArchivo = MessageFormat.format(getBundle(BUNDLE_CARGUE).getString("msg_info_archivo_2"),
                numMaxLineas);

        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);
        cargueMasivoUbicHolderFL.setSinCargueActivo(true);

        validarCargue(cargueMasivoUbicHolderFL);
    }

    /**
     * Realiza el cargue del archivo masivo
     * 
     * @author julio.pinzon
     */
    public void cargarArchivo(FileUploadEvent event) {
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("cargarArchivo(FileUploadEvent)"));
        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);
        final UploadedFile file = event.getFile();

        // Validar campos obligatorios
        if (cargueMasivoUbicHolderFL.getIdTipoFuenteInformacion() == null) {
            getFacesContext().addMessage(":form-contenido:fuenteUbica", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle("labelGeneral").getString("val_campo_requerido")));
        } else if (cargueMasivoUbicHolderFL.getIdTipoCargueArchivo() == null) {
            getFacesContext().addMessage(":form-contenido:tipoUbic", new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                    getBundle("labelGeneral").getString("val_campo_requerido")));
        } else if (file.getFileName().length() > ConstantesCargaArchivos.LONGITUD_NOMBRE_ARCHIVO) {
            String mensajeError = MessageFormat.format(
                    getBundle(BUNDLE_CARGUE).getString("msg_error_longitud_nombre_archivo"),
                    ConstantesCargaArchivos.LONGITUD_NOMBRE_ARCHIVO);
            getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensajeError));
        } else {
            final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(), file.getContents());

            cargueMasivoUbicHolderFL.setArchivoCSVCargue(archivo);

            CargueMasivoUbicabilidadDTO cargueMasivo = new CargueMasivoUbicabilidadDTO();
            cargueMasivo.setArchivoCSVCargue(cargueMasivoUbicHolderFL.getArchivoCSVCargue());
            cargueMasivo.setIdTipoCargueArchivo(cargueMasivoUbicHolderFL.getIdTipoCargueArchivo());
            cargueMasivo.setIdTipoFuenteInformacion(cargueMasivoUbicHolderFL.getIdTipoFuenteInformacion());

            cargueMasivoUbicHolderFL.setArchivoCargado(true);
            cargueMasivoUbicHolderFL.setArchivoProcesado(false);
            cargueMasivoUbicHolderFL.setArchivoEnProceso(false);
            try {
                validarCargue(cargueMasivoUbicHolderFL);
                if (cargueMasivoUbicHolderFL.isSinCargueActivo()) {
                    CargueArchivoDTO cargueArchivo = iRCargueMasivo.registrarCargueMasivo(archivo,
                            EnumTipoCargueArchivo.obtenerPorValor(cargueMasivoUbicHolderFL.getIdTipoCargueArchivo()),
                            EnumCategoriaDocumento.CARGUE_MASIVO_UBICABILIDAD,
                            EnumConsecutivo.NUMERO_CARGUE_MASIVO_UBIC);
                    cargueMasivoUbicHolderFL.setCargue(new CargueArchivoDTO(cargueArchivo.getId()));
                    Future<CargueArchivoDTO> resultado = ubicabilidadEJB.procesarCargueMasivoUbic(cargueArchivo,
                            cargueMasivo);
                    if (resultado.isDone()) {
                        cargueMasivoUbicHolderFL.setCargue(resultado.get());
                        cargueMasivoUbicHolderFL.setArchivoProcesado(true);
                        cargueMasivoUbicHolderFL.setSinCargueActivo(false);
                    } else {
                        cargueMasivoUbicHolderFL.setArchivoEnProceso(true);
                        cargueMasivoUbicHolderFL.setSinCargueActivo(false);
                    }
                    cargueMasivoUbicHolderFL.setArchivoCargado(false);
                } else {
                    getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            getBundle(BUNDLE_CARGUE).getString("msg_error_cargue_activo")));
                }
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            } catch (InterruptedException e) {
                logger.error("Error en cargue masivo asincrono", e);
            } catch (ExecutionException e) {
                logger.error("Error en cargue masivo asincrono", e);
            }
        }
    }

    /**
     * Descarga de archivo original del cargue
     * 
     * @author julio.pinzon 2016-11-23
     */
    public StreamedContent descargarArchivoUbicabilidad() {
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("descargarArchivoUbicabilidad()"));
        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);
        StreamedContent streamedContent = null;
        try {
            if (cargueMasivoUbicHolderFL.getArchivoCSVCargue() == null) {
                cargueMasivoUbicHolderFL.setArchivoCSVCargue(irRepositorioArchivo
                        .consultarDocumento(cargueMasivoUbicHolderFL.getCargue().getIdDocumentoCargue().toString()));
            }

            streamedContent = new DefaultStreamedContent(
                    new ByteArrayInputStream(cargueMasivoUbicHolderFL.getArchivoCSVCargue().getContenido()),
                    ConstantesCargaArchivos.CONTENT_TYPE_CSV,
                    cargueMasivoUbicHolderFL.getArchivoCSVCargue().getNombre(), StandardCharsets.UTF_8.name());
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        return streamedContent;
    }

    /**
     * Descarga de archivo de inconsistencias
     * 
     * @author julio.pinzon 2016-11-23
     */
    public StreamedContent descargarArchivoIncosistencias() {
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("descargarArchivoIncosistencias()"));
        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);
        StreamedContent streamedContent = null;
        try {
            if (cargueMasivoUbicHolderFL.getArchivoInconsistencias() == null) {
                ArchivoTransportableDTO inconsistencias = irRepositorioArchivo.consultarDocumento(
                        cargueMasivoUbicHolderFL.getCargue().getIdDocumentoInconsistencias().toString());

                ContenidoReporte contenido = new ContenidoReporte();
                List<Object> listaContenido = new ArrayList<Object>();
                List<Object> registros;

                try (ByteArrayInputStream fis = new ByteArrayInputStream(inconsistencias.getContenido());
                        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                        BufferedReader br = new BufferedReader(isr)) {
                    String linea = br.readLine();
                    while ((linea = br.readLine()) != null) {
                        Object[] campos = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR);
                        registros = new ArrayList<Object>();
                        for (Object campo : campos) {
                            registros.add(campo);
                        }
                        listaContenido.add(registros);
                    }
                } catch (IOException e) {
                    logger.error("Error al procesar el cargue masivo de ubicabilidad", e);
                }
                contenido.setContenido(listaContenido);
                super.obtenerEncabezadoReporte(contenido);
                contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, new ArrayList<>());

                String reportId = null;
                String nombreReporte = null;

                if (cargueMasivoUbicHolderFL.getIdTipoCargueArchivo()
                        .equals(EnumTipoCargueArchivo.MASIVO_DIRECCION.getValue())) {
                    reportId = REPORT_ID_INCONSISTENCIAS_DIRECCIONES;
                    nombreReporte = NOMBRE_REPORTE_INCONSISTENCIAS_DIRECCIONES;
                } else if (cargueMasivoUbicHolderFL.getIdTipoCargueArchivo()
                        .equals(EnumTipoCargueArchivo.MASIVO_CORREO.getValue())) {
                    reportId = REPORT_ID_INCONSISTENCIAS_CORREOS;
                    nombreReporte = NOMBRE_REPORTE_INCONSISTENCIAS_CORREOS;
                } else if (cargueMasivoUbicHolderFL.getIdTipoCargueArchivo()
                        .equals(EnumTipoCargueArchivo.MASIVO_TELEFONO.getValue())) {
                    reportId = REPORT_ID_INCONSISTENCIAS_TELEFONOS;
                    nombreReporte = NOMBRE_REPORTE_INCONSISTENCIAS_TELEFONOS;
                }

                contenido.setCodigo(reportId);

                final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                        .getContext();

                final GeneradorReporte generadorReporte = new GeneradorReporte(
                        (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

                inconsistencias.setContenido(generadorReporte.asBytes(FormatoReporte.XLSX, contenido, reportId));
                inconsistencias.setNombre(nombreReporte + "." + FormatoReporte.XLSX);
                cargueMasivoUbicHolderFL.setArchivoInconsistencias(inconsistencias);
            }
            streamedContent = new DefaultStreamedContent(
                    new ByteArrayInputStream(cargueMasivoUbicHolderFL.getArchivoInconsistencias().getContenido()),
                    FormatoReporte.XLSX.contentType, cargueMasivoUbicHolderFL.getArchivoInconsistencias().getNombre());

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        return streamedContent;
    }

    /**
     * Se reinicia todo
     * 
     * @author julio.pinzon 2016-11-23
     */
    public void reiniciarValores() {
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("reiniciarValores()"));
        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);
        cargueMasivoUbicHolderFL.setArchivoProcesado(false);
        cargueMasivoUbicHolderFL.setArchivoCargado(false);
        cargueMasivoUbicHolderFL.setArchivoCSVCargue(null);
        cargueMasivoUbicHolderFL.setArchivoCargado(false);
        cargueMasivoUbicHolderFL.setArchivoEnProceso(false);
        cargueMasivoUbicHolderFL.setCargue(null);
        cargueMasivoUbicHolderFL.setSinCargueActivo(true);
        cargueMasivoUbicHolderFL.setArchivoEnProcesoOtroUsuario(false);
        cargueMasivoUbicHolderFL.setArchivoInconsistencias(null);
        cargueMasivoUbicHolderFL.setProgresoIniciado(false);
    }

    /**
     * cancelar
     * 
     * @author julio.pinzon 2016-11-23
     */
    public void cancelar() {
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("cancelar()"));
        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);
        iRCargueMasivo.notificarCargueArchivo(cargueMasivoUbicHolderFL.getCargue());
    }

    /**
     * Listener para progreso de la barra
     * 
     * @author julio.pinzon 2016-11-23
     */
    public Integer getProgresoCargue() {
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("getProgresoCargue()"));
        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);

        int progress = 0;
        if (cargueMasivoUbicHolderFL.isArchivoEnProceso()) {
            List<CargueArchivoDTO> cargues = iRCargueMasivo
                    .consultarCargueArchivo(new CargueArchivoDTO(cargueMasivoUbicHolderFL.getCargue().getId()));
            if (cargues != null && cargues.size() > 0) {
                CargueArchivoDTO cargueActivo = cargues.get(0);
                cargueMasivoUbicHolderFL.setCargue(cargueActivo);
                if (cargueActivo.getEstadoCargueArchivo().getId() == EnumEstadoCargueArchivo.PROCESADO.getValue()) {
                    progress = 100;
                } else {
                    progress = (int) ((((float) cargueActivo.getTotalLeidos() + cargueActivo.getTotalInconsistencias())
                            / cargueActivo.getTotalRegistros()) * 100);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(getBundle(BUNDLE_CARGUE).getString("msg_error_progreso_cargue")));
                return null;
            }

            if (!cargueMasivoUbicHolderFL.isProgresoIniciado()) {
                RequestContext.getCurrentInstance().execute("PF('pbAjax').start();");
                cargueMasivoUbicHolderFL.setProgresoIniciado(true);
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
        logger.debug(CargueMasivoUbicabilidadMB.class.getName().concat("cargueCompleto()"));
        CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL = findFlowObject(
                CargueMasivoUbicabilidadHolderFL.class, CargueMasivoUbicabilidadHolderFL.NOMBRE_BEAN);
        cargueMasivoUbicHolderFL.setArchivoEnProceso(false);
        cargueMasivoUbicHolderFL.setArchivoEnProcesoOtroUsuario(false);
        cargueMasivoUbicHolderFL.setArchivoProcesado(false);
        cargueMasivoUbicHolderFL.setSinCargueActivo(true);
        if (cargueMasivoUbicHolderFL.getCargue().getUsuarioCargue().getLogin().equals(getLogin())) {
            cargueMasivoUbicHolderFL.setArchivoProcesado(true);
            RequestContext.getCurrentInstance().execute("PF('popUpResultadoCargue').show();");
        }
    }

    public String getMensajeCargueActivo() {
        return mensajeCargueActivo;
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public String getTiposArchivosPermitidos() {
        return TIPOS_ARCHIVOS_PERMITIDOS;
    }

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

    public StreamedContent getFormatoDireccion() {
        Path pathInstructivoDirec = Paths.get(ConstantesCargaArchivos.UBICACION_SERVER, FORMATO_CARGUE_UBI_DIRECCION);
        try (InputStream stream = new ByteArrayInputStream(Files.readAllBytes(pathInstructivoDirec))) {
            formatoCargueDireccion = new DefaultStreamedContent(stream, ConstantesCargaArchivos.CONTENT_TYPE_CSV,
                    FORMATO_CARGUE_UBI_DIRECCION);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return formatoCargueDireccion;
    }

    public StreamedContent getFormatoTelefono() {
        Path pathInstructivoTel = Paths.get(ConstantesCargaArchivos.UBICACION_SERVER, FORMATO_CARGUE_UBI_TELEFONO);
        try (InputStream stream = new ByteArrayInputStream(Files.readAllBytes(pathInstructivoTel))) {
            formatoCargueTelefono = new DefaultStreamedContent(stream, ConstantesCargaArchivos.CONTENT_TYPE_CSV,
                    FORMATO_CARGUE_UBI_TELEFONO);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return formatoCargueTelefono;
    }

    public StreamedContent getFormatoCorreo() {
        Path pathInstructivoCorreo = Paths.get(ConstantesCargaArchivos.UBICACION_SERVER, FORMATO_CARGUE_UBI_CORREO);
        try (InputStream stream = new ByteArrayInputStream(Files.readAllBytes(pathInstructivoCorreo))) {
            formatoCargueCorreo = new DefaultStreamedContent(stream, ConstantesCargaArchivos.CONTENT_TYPE_CSV,
                    FORMATO_CARGUE_UBI_CORREO);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return formatoCargueCorreo;
    }

    /**
     * Validar cargue activo
     * 
     * @author julio.pinzon 2016-12-16
     */
    private void validarCargue(CargueMasivoUbicabilidadHolderFL cargueMasivoUbicHolderFL) {
        logger.debug(
                CargueMasivoUbicabilidadMB.class.getName().concat("validarCargue(CargueMasivoUbicabilidadHolderFL)"));

        List<EnumTipoCargueArchivo> tiposCargue = new ArrayList<>();
        tiposCargue.add(EnumTipoCargueArchivo.MASIVO_CORREO);
        tiposCargue.add(EnumTipoCargueArchivo.MASIVO_DIRECCION);
        tiposCargue.add(EnumTipoCargueArchivo.MASIVO_TELEFONO);
        // Valida cargues activos
        RespuestaValidaCargueArchivoDTO respuesta = iRCargueMasivo.validarCargueActivo(tiposCargue);
        cargueMasivoUbicHolderFL.setArchivoEnProcesoOtroUsuario(respuesta.isArchivoEnProcesoOtroUsuario());
        cargueMasivoUbicHolderFL.setArchivoEnProceso(respuesta.isArchivoEnProceso());
        cargueMasivoUbicHolderFL.setSinCargueActivo(respuesta.isSinCargueActivo());
        cargueMasivoUbicHolderFL.setArchivoProcesado(respuesta.isArchivoProcesado());
        cargueMasivoUbicHolderFL.setCargue(respuesta.getCargue());
        if (cargueMasivoUbicHolderFL.isArchivoEnProcesoOtroUsuario()) {
            mensajeCargueActivo = MessageFormat.format(getBundle(BUNDLE_CARGUE).getString("msg_cargue_otro_usuario"),
                    cargueMasivoUbicHolderFL.getCargue().getUsuarioCargue().getLogin(),
                    Utilidades.dateToStringFormatApp(cargueMasivoUbicHolderFL.getCargue().getFechaCargue(), true));
        }
    }

    public String getMensajeInfoArchivo() {
        return mensajeInfoArchivo;
    }

    public List<SelectItem> getCatTipoFuenteInformacion() {
        return catTipoFuenteInformacion;
    }

    public List<SelectItem> getCatTipoCargueArchivo() {
        return catTipoCargueArchivo;
    }

}
