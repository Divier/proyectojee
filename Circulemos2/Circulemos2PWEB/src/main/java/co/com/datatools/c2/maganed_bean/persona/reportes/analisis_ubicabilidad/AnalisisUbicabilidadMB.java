package co.com.datatools.c2.maganed_bean.persona.reportes.analisis_ubicabilidad;

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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.constantes.ConstantesCargaArchivos;
import co.com.datatools.c2.dto.ConsultaAnalisisUbicResulDTO;
import co.com.datatools.c2.dto.cargue.CargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.dto.cargue.EstadoCargueArchivoDTO;
import co.com.datatools.c2.dto.cargue.TipoCargueArchivoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumEstadoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoCargueArchivo;
import co.com.datatools.c2.enumeracion.EnumTipoDocumentoResulCargue;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRCargueMasivo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.FormatoReporte;
import co.com.datatools.c2.reporte.GeneradorReporte;
import co.com.datatools.c2.reporte.data.AbstractConectorPlantillaReporte;
import co.com.datatools.c2.reporte.var.EnumEncabezadoEspecial;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Managed Bean para manipulación administración de personas CU_CIR20_UBI_002
 * 
 * @author ricardo.chavarro
 * 
 */
@ManagedBean
@SessionScoped
public class AnalisisUbicabilidadMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AnalisisUbicabilidadMB.class.getName());

    private static final String BUNDLE_PERSONA = "labelAnalisisUbicabilidad";

    public static final String RUTA_MANUALES_FILE_SYSTEM = "PATH_MANUALES";

    private final String UBICACION_SERVER = System.getProperty(RUTA_MANUALES_FILE_SYSTEM);

    private StreamedContent instructivo;

    private int tamanioMaximoArch;

    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(csv)$/";

    private static final String ID_REPORTE_ANALISIS_UBIC_DIRECCIONES = "REPORTE_ANALISIS_UBICABILIDAD_DIRECCIONES";

    private static final String ID_REPORTE_ANALISIS_UBIC_TELEFONOS = "REPORTE_ANALISIS_UBICABILIDAD_TELEFONOS";

    private static final String ID_REPORTE_ANALISIS_UBIC_CORREOS = "REPORTE_ANALISIS_UBICABILIDAD_CORREOS";

    private static final String NOMBRE_OBJ_CONECTOR_PLANTILLA = "REPORTES_CONECTOR_PLANTILLAS";

    private static final String NOMBRE_ARCHIVO_REPORTE_ANALISIS_UBIC = "REPORTE_ANALISIS_UBICABILIDAD";

    private static final String ID_REPORTE_INCOSISTENCIAS = "REPORTE_INCONSISTENCIAS_ANALISIS_UBICABILIDAD";

    @EJB
    private IRPersona personaEJB;

    @EJB
    private IRParametro parametroEJB;

    private Integer progress = null;

    private Future<ConsultaAnalisisUbicResulDTO> controlThread;

    @EJB
    private IRCargueMasivo iRCargueMasivo;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    @EJB
    private IRRepositorioArchivo irRepositorioArchivo;

    @PostConstruct
    public void init() {
        logger.debug(AnalisisUbicabilidadMB.class.getName().concat("init()"));

        ValorParametroDTO valTamMaxAdjArch = parametroEJB.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
    }

    public boolean consultar() {
        logger.debug(AnalisisUbicabilidadMB.class.getName().concat("consultar()"));
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        ArchivoTransportableDTO archivo = analisisUbicHolderFL.getArchivoCSVConsulta();
        if (!analisisUbicHolderFL.isConsultaDireccion() && !analisisUbicHolderFL.isConsultaTelefono()
                && !analisisUbicHolderFL.isConsultaCorreo()) {
            getFacesContext().addMessage("form-contenido:campoConsulta", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString()));
            return false;
        }
        try {
            analisisUbicHolderFL.setCargueArchivo(personaEJB.validarAnalisisUbicabilidad(archivo));
            controlThread = personaEJB.consultarAnalisisUbicabilidad(archivo, analisisUbicHolderFL.getCargueArchivo(),
                    analisisUbicHolderFL.isConsultaDireccion(), analisisUbicHolderFL.isConsultaTelefono(),
                    analisisUbicHolderFL.isConsultaCorreo());
            if (controlThread.isDone()) {
                analisisUbicHolderFL.setResultadoConsulta(controlThread.get());
            }
            analisisUbicHolderFL.setArchivoCSVConsulta(null);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            analisisUbicHolderFL.setArchivoCSVConsulta(null);
            return false;
        } catch (InterruptedException | ExecutionException e) {
            CirculemosErrorHandler.handleException(
                    new CirculemosNegocioException(ErrorAdministracion.ConsultaAnalisisCartera.ADM_082005));
            analisisUbicHolderFL.setArchivoCSVConsulta(null);
            return false;
        }
        return true;
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public String getTiposArchivosPermitidos() {
        return TIPOS_ARCHIVOS_PERMITIDOS;
    }

    public String getMensajeUbicabilidad() {
        String mensaje = getBundle(BUNDLE_PERSONA).getString("msg_info_archivo");
        return mensaje;
    }

    public StreamedContent getInstructivo() {
        Path pathManual = Paths.get(UBICACION_SERVER, "MANUAL_ARCHIVO_CSV_UBI_001.pdf");
        try (InputStream stream = new ByteArrayInputStream(Files.readAllBytes(pathManual))) {
            instructivo = new DefaultStreamedContent(stream, "pdf/application", "instructivo.pdf");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return instructivo;
    }

    public void cargarArchivoConsulta(FileUploadEvent event) {
        logger.debug(AnalisisUbicabilidadMB.class.getName().concat("::cargarArchivoConsulta()"));
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        final UploadedFile file = event.getFile();
        final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(), file.getContents());

        analisisUbicHolderFL.setArchivoCSVConsulta(archivo);
        if (consultar()) {
            try {
                HttpServletRequest httpRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();
                HttpServletResponse httpResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                        .getExternalContext().getResponse();
                String url = httpRequest.getContextPath() + "/app/persona/reportes/analisisUbicabilidad";
                httpResponse.sendRedirect(url);
                // return "/app/persona/reportes/analisisUbicabilidad";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // return null;
    }

    private ContenidoReporte getContenidoUbicabilidadDirecciones() {
        logger.debug("AnalisisUbicabilidadMB::getContenidoInformacionUbicabilidad()");
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        ContenidoReporte contenido = new ContenidoReporte();

        List<DocumentoResultadoCargueDTO> documentos = iRCargueMasivo
                .consultarDocumentosResultadoCargue(analisisUbicHolderFL.getCargueArchivo());
        ArchivoTransportableDTO archivoDirecciones = null;
        if (documentos != null && documentos.size() > 0) {
            for (DocumentoResultadoCargueDTO documentoResul : documentos) {
                if (documentoResul.getTipoDocumentoResultadoCargue()
                        .getId() == EnumTipoDocumentoResulCargue.UBICABILIDAD_DIRECCION.getValue()) {
                    try {
                        archivoDirecciones = irRepositorioArchivo
                                .consultarDocumento(documentoResul.getIdDocumentoResultado().toString());
                    } catch (CirculemosAlertaException e) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(getBundle(BUNDLE_PERSONA).getString("msg_error_descarga_archivo")));
                    }
                    break;
                }
            }
        } else {
            return contenido;
        }

        String contenidoStr = new String(archivoDirecciones.getContenido());
        String[] lineas = contenidoStr.split(ConstantesCargaArchivos.getSeparadorLineaSistema(), -1);
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;
        for (String linea : lineas) {
            String[] informacion = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR, -1);
            if (informacion.length < 16) {
                continue;
            }
            registros = new ArrayList<Object>();
            registros.add(informacion[0]);
            registros.add(informacion[1]);
            registros.add(informacion[2]);
            registros.add(informacion[3]);
            registros.add(informacion[4]);
            registros.add(informacion[5]);
            registros.add(informacion[6]);

            registros.add(informacion[7]);
            registros.add(informacion[8]);
            registros.add(informacion[9]);
            registros.add(informacion[10]);
            registros.add(informacion[11]);
            registros.add(informacion[12]);
            registros.add(informacion[13]);
            registros.add(informacion[14]);
            registros.add(informacion[15]);
            listaContenido.add(registros);
        }

        contenido.setContenido(listaContenido);
        super.obtenerEncabezadoReporte(contenido);
        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, new ArrayList<>());
        return contenido;

    }

    public ConsultaAnalisisUbicResulDTO getResultadoConsulta() {
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        return analisisUbicHolderFL.getResultadoConsulta();
    }

    public DefaultStreamedContent descargarUbicabilidadDirecciones() {
        logger.debug("AnalisisUbicabilidadMB::descargarArchivoUbicabilidad()");

        ContenidoReporte contenido = getContenidoUbicabilidadDirecciones();
        contenido.setCodigo(ID_REPORTE_ANALISIS_UBIC_DIRECCIONES);

        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        final GeneradorReporte generadorReporte = new GeneradorReporte(
                (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

        return new DefaultStreamedContent(
                new ByteArrayInputStream(
                        generadorReporte.asBytes(FormatoReporte.XLSX, contenido, ID_REPORTE_ANALISIS_UBIC_DIRECCIONES)),
                FormatoReporte.XLSX.contentType, NOMBRE_ARCHIVO_REPORTE_ANALISIS_UBIC + "." + FormatoReporte.XLSX);
    }

    public StreamedContent descargarArchivoIncosistencias() {
        logger.debug("AnalisisUbicabilidadMB::descargarArchivoIncosistencias()");
        StreamedContent streamedContent = null;

        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        ArchivoTransportableDTO archivoDescarga;

        List<DocumentoResultadoCargueDTO> documentos = iRCargueMasivo
                .consultarDocumentosResultadoCargue(analisisUbicHolderFL.getCargueArchivo());
        if (documentos != null && documentos.size() > 0) {
            for (DocumentoResultadoCargueDTO documentoResultado : documentos) {
                if (documentoResultado.getTipoDocumentoResultadoCargue()
                        .getId() == EnumTipoDocumentoResulCargue.UBICABILIDAD_INCONSISTENCIAS.getValue()) {
                    try {
                        archivoDescarga = irRepositorioArchivo
                                .consultarDocumento(documentoResultado.getIdDocumentoResultado().toString());
                    } catch (CirculemosAlertaException e) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(getBundle(BUNDLE_PERSONA).getString("msg_error_descarga_archivo")));
                        return null;
                    }

                    ContenidoReporte contenido = new ContenidoReporte();
                    List<Object> listaContenido = new ArrayList<Object>();
                    List<Object> registros;

                    try (ByteArrayInputStream fis = new ByteArrayInputStream(archivoDescarga.getContenido());
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
                        logger.error("Error al procesar el analisis de ubicabilidad", e);
                    }

                    contenido.setContenido(listaContenido);
                    contenido.setCodigo(ID_REPORTE_INCOSISTENCIAS);

                    super.obtenerEncabezadoReporte(contenido);
                    contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, new ArrayList<>());

                    final ServletContext context = (ServletContext) FacesContext.getCurrentInstance()
                            .getExternalContext().getContext();

                    final GeneradorReporte generadorReporte = new GeneradorReporte(
                            (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

                    ArchivoTransportableDTO archivo = new ArchivoTransportableDTO();
                    archivo.setContenido(
                            generadorReporte.asBytes(FormatoReporte.XLSX, contenido, ID_REPORTE_INCOSISTENCIAS));
                    archivo.setNombre(archivoDescarga.getNombre() + "." + FormatoReporte.XLSX);

                    InputStream inputStream = new ByteArrayInputStream(archivo.getContenido());
                    streamedContent = new DefaultStreamedContent(inputStream, FormatoReporte.XLSX.contentType,
                            archivo.getNombre());
                    break;
                }
            }

        }
        return streamedContent;
    }

    public StreamedContent descargarArchivoCargue() {
        logger.debug("AnalisisUbicabilidadMB::descargarArchivoCargue()");

        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        ArchivoTransportableDTO archivoConsulta;

        try {
            archivoConsulta = irRepositorioArchivo
                    .consultarDocumento(analisisUbicHolderFL.getCargueArchivo().getIdDocumentoCargue().toString());
        } catch (CirculemosAlertaException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(getBundle(BUNDLE_PERSONA).getString("msg_error_descarga_archivo")));
            return null;
        }

        StreamedContent streamedContent = null;

        InputStream inputStream = new ByteArrayInputStream(archivoConsulta.getContenido());
        streamedContent = new DefaultStreamedContent(inputStream, "text/csv", archivoConsulta.getNombre(),
                StandardCharsets.UTF_8.name());
        return streamedContent;
    }

    public DefaultStreamedContent descargarUbicabilidadTelefonos() {
        logger.debug("AnalisisUbicabilidadMB::descargarUbicabilidadTelefonos()");

        ContenidoReporte contenido = getContenidoUbicabilidadTelefonos();
        contenido.setCodigo(ID_REPORTE_ANALISIS_UBIC_TELEFONOS);

        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        final GeneradorReporte generadorReporte = new GeneradorReporte(
                (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

        return new DefaultStreamedContent(
                new ByteArrayInputStream(
                        generadorReporte.asBytes(FormatoReporte.XLSX, contenido, ID_REPORTE_ANALISIS_UBIC_TELEFONOS)),
                FormatoReporte.XLSX.contentType, ID_REPORTE_ANALISIS_UBIC_TELEFONOS + "." + FormatoReporte.XLSX);
    }

    private ContenidoReporte getContenidoUbicabilidadTelefonos() {
        logger.debug("AnalisisUbicabilidadMB::getContenidoUbicabilidadTelefonos()");

        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        ContenidoReporte contenido = new ContenidoReporte();

        List<DocumentoResultadoCargueDTO> documentos = iRCargueMasivo
                .consultarDocumentosResultadoCargue(analisisUbicHolderFL.getCargueArchivo());
        ArchivoTransportableDTO archivoDirecciones = null;
        if (documentos != null && documentos.size() > 0) {
            for (DocumentoResultadoCargueDTO documentoResul : documentos) {
                if (documentoResul.getTipoDocumentoResultadoCargue()
                        .getId() == EnumTipoDocumentoResulCargue.UBICABILIDAD_TELEFONO.getValue()) {
                    try {
                        archivoDirecciones = irRepositorioArchivo
                                .consultarDocumento(documentoResul.getIdDocumentoResultado().toString());
                    } catch (CirculemosAlertaException e) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(getBundle(BUNDLE_PERSONA).getString("msg_error_descarga_archivo")));
                    }
                    break;
                }
            }
        } else {
            return contenido;
        }

        String contenidoStr = new String(archivoDirecciones.getContenido());
        String[] lineas = contenidoStr.split(ConstantesCargaArchivos.getSeparadorLineaSistema(), -1);
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;
        for (String linea : lineas) {
            String[] informacion = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR, -1);
            if (informacion.length < 13) {
                continue;
            }
            registros = new ArrayList<Object>();
            registros.add(informacion[0]);
            registros.add(informacion[1]);
            registros.add(informacion[2]);
            registros.add(informacion[3]);
            registros.add(informacion[4]);
            registros.add(informacion[5]);
            registros.add(informacion[6]);

            registros.add(informacion[7]);
            registros.add(informacion[8]);
            registros.add(informacion[9]);
            registros.add(informacion[10]);
            registros.add(informacion[11]);
            registros.add(informacion[12]);
            listaContenido.add(registros);
        }

        contenido.setContenido(listaContenido);
        super.obtenerEncabezadoReporte(contenido);
        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, new ArrayList<>());
        return contenido;
    }

    public DefaultStreamedContent descargarUbicabilidadCorreos() {
        logger.debug("AnalisisUbicabilidadMB::descargarUbicabilidadCorreos()");

        ContenidoReporte contenido = getContenidoUbicabilidadCorreos();
        contenido.setCodigo(ID_REPORTE_ANALISIS_UBIC_CORREOS);

        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        final GeneradorReporte generadorReporte = new GeneradorReporte(
                (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

        return new DefaultStreamedContent(
                new ByteArrayInputStream(
                        generadorReporte.asBytes(FormatoReporte.XLSX, contenido, ID_REPORTE_ANALISIS_UBIC_CORREOS)),
                FormatoReporte.XLSX.contentType, ID_REPORTE_ANALISIS_UBIC_CORREOS + "." + FormatoReporte.XLSX);
    }

    private ContenidoReporte getContenidoUbicabilidadCorreos() {
        logger.debug("AnalisisUbicabilidadMB::getContenidoUbicabilidadCorreos()");
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        ContenidoReporte contenido = new ContenidoReporte();

        List<DocumentoResultadoCargueDTO> documentos = iRCargueMasivo
                .consultarDocumentosResultadoCargue(analisisUbicHolderFL.getCargueArchivo());
        ArchivoTransportableDTO archivoDirecciones = null;
        if (documentos != null && documentos.size() > 0) {
            for (DocumentoResultadoCargueDTO documentoResul : documentos) {
                if (documentoResul.getTipoDocumentoResultadoCargue()
                        .getId() == EnumTipoDocumentoResulCargue.UBICABILIDAD_CORREO.getValue()) {
                    try {
                        archivoDirecciones = irRepositorioArchivo
                                .consultarDocumento(documentoResul.getIdDocumentoResultado().toString());
                    } catch (CirculemosAlertaException e) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(getBundle(BUNDLE_PERSONA).getString("msg_error_descarga_archivo")));
                    }
                    break;
                }
            }
        } else {
            return contenido;
        }

        String contenidoStr = new String(archivoDirecciones.getContenido());
        String[] lineas = contenidoStr.split(ConstantesCargaArchivos.getSeparadorLineaSistema(), -1);
        List<Object> listaContenido = new ArrayList<Object>();
        List<Object> registros;
        for (String linea : lineas) {
            String[] informacion = linea.split(ConstantesCargaArchivos.CSV_SEPARADOR, -1);
            if (informacion.length < 12) {
                continue;
            }
            registros = new ArrayList<Object>();
            registros.add(informacion[0]);
            registros.add(informacion[1]);
            registros.add(informacion[2]);
            registros.add(informacion[3]);
            registros.add(informacion[4]);
            registros.add(informacion[5]);
            registros.add(informacion[6]);

            registros.add(informacion[7]);
            registros.add(informacion[8]);
            registros.add(informacion[9]);
            registros.add(informacion[10]);
            registros.add(informacion[11]);
            listaContenido.add(registros);
        }

        contenido.setContenido(listaContenido);
        super.obtenerEncabezadoReporte(contenido);
        contenido.getVariablesEncabezado().put(EnumEncabezadoEspecial.filtros, new ArrayList<>());
        return contenido;
    }

    public void finalizarConsulta() {
        this.progress = null;
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        if (analisisUbicHolderFL.getCargueArchivo() != null) {
            analisisUbicHolderFL.getCargueArchivo().setUsuarioCargue(iRSeguridadCirculemos.obtenerUsuarioDto());
            iRCargueMasivo.notificarCargueArchivo(analisisUbicHolderFL.getCargueArchivo());
        }
        analisisUbicHolderFL.setPopUpVisible(false);
        analisisUbicHolderFL.setConsultaDireccion(false);
        analisisUbicHolderFL.setConsultaTelefono(false);
        analisisUbicHolderFL.setConsultaCorreo(false);
    }

    public Integer getProgresoConsulta() {
        progress = 0;
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        CargueArchivoDTO cargue = new CargueArchivoDTO();
        cargue.setId(analisisUbicHolderFL.getCargueArchivo().getId());
        List<CargueArchivoDTO> cargues = iRCargueMasivo.consultarCargueArchivo(cargue);
        if (cargues != null && cargues.size() > 0) {
            CargueArchivoDTO cargueActivo = cargues.get(0);
            progress = (int) ((((float) cargueActivo.getTotalLeidos() + cargueActivo.getTotalInconsistencias())
                    / cargueActivo.getTotalRegistros()) * 100);

            if (cargueActivo.getEstadoCargueArchivo().getId() == EnumEstadoCargueArchivo.PROCESADO.getValue()) {
                progress = 100;
            }

            if (progress == 100
                    && cargueActivo.getEstadoCargueArchivo().getId() != EnumEstadoCargueArchivo.PROCESADO.getValue()) {
                progress = 99;
            }
            analisisUbicHolderFL.setCargueArchivo(cargueActivo);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(getBundle(BUNDLE_PERSONA).getString("msg_error_progreso_cargue")));
            return null;
        }
        return progress;
    }

    public void consultaCompleta() {
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        ConsultaAnalisisUbicResulDTO resultado = new ConsultaAnalisisUbicResulDTO();
        resultado.setCargueArchivo(analisisUbicHolderFL.getCargueArchivo());

        List<DocumentoResultadoCargueDTO> documentos = iRCargueMasivo
                .consultarDocumentosResultadoCargue(analisisUbicHolderFL.getCargueArchivo());
        if (documentos != null && documentos.size() > 0) {
            for (DocumentoResultadoCargueDTO documentoDTO : documentos) {
                if (documentoDTO.getTipoDocumentoResultadoCargue()
                        .getId() == EnumTipoDocumentoResulCargue.UBICABILIDAD_DIRECCION.getValue()) {
                    resultado.setDocDireccionesPersonas(documentoDTO);
                }
                if (documentoDTO.getTipoDocumentoResultadoCargue()
                        .getId() == EnumTipoDocumentoResulCargue.UBICABILIDAD_TELEFONO.getValue()) {
                    resultado.setDocTelefonoPersonas(documentoDTO);
                }
                if (documentoDTO.getTipoDocumentoResultadoCargue()
                        .getId() == EnumTipoDocumentoResulCargue.UBICABILIDAD_CORREO.getValue()) {
                    resultado.setDocCorreoPersonas(documentoDTO);
                }
                if (documentoDTO.getTipoDocumentoResultadoCargue()
                        .getId() == EnumTipoDocumentoResulCargue.UBICABILIDAD_INCONSISTENCIAS.getValue()) {
                    resultado.setDocInconsistencias(documentoDTO);
                }
            }
        }

        analisisUbicHolderFL.setResultadoConsulta(resultado);
    }

    public boolean isCargueActivo() {
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        CargueArchivoDTO cargeProgresoDto = new CargueArchivoDTO();
        cargeProgresoDto
                .setEstadoCargueArchivo(new EstadoCargueArchivoDTO(EnumEstadoCargueArchivo.EN_PROCESO.getValue()));
        cargeProgresoDto
                .setTipoCargueArchivo(new TipoCargueArchivoDTO(EnumTipoCargueArchivo.CONSULTA_UBICABILIDAD.getValue()));
        // cargeProgresoDto.setUsuarioCargue(iRSeguridadCirculemos.obtenerUsuarioDto());

        List<CargueArchivoDTO> carguesProceso = iRCargueMasivo.consultarCargueArchivo(cargeProgresoDto);
        if (carguesProceso != null && carguesProceso.size() > 0) {
            analisisUbicHolderFL.setCargueArchivo(carguesProceso.get(0));
        }

        CargueArchivoDTO cargeSinNotifDto = new CargueArchivoDTO();
        cargeSinNotifDto
                .setEstadoCargueArchivo(new EstadoCargueArchivoDTO(EnumEstadoCargueArchivo.PROCESADO.getValue()));
        cargeSinNotifDto.setUsuarioCargue(iRSeguridadCirculemos.obtenerUsuarioDto());
        cargeSinNotifDto
                .setTipoCargueArchivo(new TipoCargueArchivoDTO(EnumTipoCargueArchivo.CONSULTA_UBICABILIDAD.getValue()));

        List<CargueArchivoDTO> carguesProcesados = iRCargueMasivo.consultarCargueArchivo(cargeSinNotifDto);
        if (carguesProcesados != null && carguesProcesados.size() > 0) {
            analisisUbicHolderFL.setCargueArchivo(carguesProcesados.get(0));
        }

        return analisisUbicHolderFL.isCargueActivo();
    }

    public String getMensajeCargueActivo() {
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        if (analisisUbicHolderFL.getCargueArchivo().getUsuarioCargue().getLogin()
                .equals(iRSeguridadCirculemos.obtenerUsuarioDto().getLogin())) {
            return "";
        }
        String mensajeCargue = getBundle(BUNDLE_PERSONA).getString("msg_cargue_activo_usuario");
        mensajeCargue = MessageFormat.format(mensajeCargue,
                analisisUbicHolderFL.getCargueArchivo().getUsuarioCargue().getLogin(),
                Utilidades.dateToStringFormatApp(analisisUbicHolderFL.getCargueArchivo().getFechaCargue(), true));
        return mensajeCargue;
    }

    public void validarPopUpResultado() {
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        RequestContext context = RequestContext.getCurrentInstance();
        if (iRSeguridadCirculemos.obtenerUsuarioDto().getLogin()
                .equals(analisisUbicHolderFL.getCargueArchivo().getUsuarioCargue().getLogin())) {
            consultaCompleta();
            context.execute("PF('popUpRespuestaConsultaUbi').show()");
        } else {
            context.execute("PF('popUpConsultaUsuFin').show()");
        }
    }

    public void inciarCargaProceso() {
        AnalisisUbicabilidadHolderFL analisisUbicHolderFL = findFlowObject(AnalisisUbicabilidadHolderFL.class,
                AnalisisUbicabilidadHolderFL.NOMBRE_BEAN);
        if (!analisisUbicHolderFL.isPopUpVisible()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('pbAjax').start();");
            analisisUbicHolderFL.setPopUpVisible(true);
        }
    }
}
