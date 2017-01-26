package co.com.datatools.c2.managed_bean.coactivo.radicarExcepciones;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RadicarExcepcionDTO;
import co.com.datatools.c2.dto.RegistroArchivoExcepcionDTO;
import co.com.datatools.c2.dto.RegistroRadicarExcepcionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.coactivo.administracion.seguimiento.ConsultaSeguimientoHolderFL;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * SE encarga de la administracion de radiar excepciones
 * 
 * @author giovanni.velandia
 *
 */
@ManagedBean
@SessionScoped
public class RadicarExcepcionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RadicarExcepcionMB.class.getName());

    @EJB
    private IRCoactivo iRCoactivo;
    @EJB
    private IRParametro parametroEjb;
    @EJB
    private IRRepositorioArchivo repositorioArchivoEJB;

    private int tamanioMaximoArch;
    private int consecutivo = 0;

    private Date fechaActual;

    /**
     * Atributo que permite identificar que tipos de archivos son permitidos
     */
    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(pdf)$/";
    private static final String NOMBRE_BUNDLE = "labelCoactivo";

    private StreamedContent streamedContent;

    @PostConstruct
    public void init() {
        LOGGER.debug("RadicarExcepcionMB::init");

        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
        streamedContent = null;

        fechaActual = Calendar.getInstance().getTime();
    }

    /**
     * Radicar excepciones
     * 
     * @author giovanni.velandia
     */
    public boolean radicarExcepcion() {
        LOGGER.debug("RadicarExcepcionMB::radicarExcepcion()");
        RadicarExcepcionFL radicarExcepcionFL = findFlowObject(RadicarExcepcionFL.class,
                RadicarExcepcionFL.NOMBRE_BEAN);
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        radicarExcepcionFL.getRegistroRadicarExcepcionDTO().getRadicarExcepcionDTO()
                .setCoactivoDTO(new CoactivoDTO(consultaSegHolderFL.getSegSeleccionado().getIdCoactivo()));
        radicarExcepcionFL.getRegistroRadicarExcepcionDTO().getRadicarExcepcionDTO().getCoactivoDTO()
                .setProceso(new ProcesoDTO(consultaSegHolderFL.getSegSeleccionado().getIdProceso()));

        try {
            iRCoactivo.radicarExcepcion(radicarExcepcionFL.getRegistroRadicarExcepcionDTO());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();
        return true;
    }

    /**
     * Radicar excepciones
     * 
     * @author giovanni.velandia
     */
    public boolean falloExcepcion() {
        LOGGER.debug("RadicarExcepcionMB::radicarExcepcion()");
        RadicarExcepcionFL radicarExcepcionFL = findFlowObject(RadicarExcepcionFL.class,
                RadicarExcepcionFL.NOMBRE_BEAN);
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        radicarExcepcionFL.getRegistroRadicarExcepcionDTO().getRadicarExcepcionDTO()
                .setCoactivoDTO(new CoactivoDTO(consultaSegHolderFL.getSegSeleccionado().getIdCoactivo()));
        radicarExcepcionFL.getRegistroRadicarExcepcionDTO().getRadicarExcepcionDTO().getCoactivoDTO()
                .setProceso(new ProcesoDTO(consultaSegHolderFL.getSegSeleccionado().getIdProceso()));

        try {
            iRCoactivo.falloExcepcion(radicarExcepcionFL.getRegistroRadicarExcepcionDTO());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
        return true;
    }

    /**
     * Se encarga de consultar una radicacion excepcion
     * 
     * @author giovanni.velandia
     */
    public void consultarRadicarExcepcion() {
        LOGGER.debug("RadicarExcepcionMB::consultarRadicarExcepcion()");
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        RadicarExcepcionFL radicarExcepcionFL = findFlowObject(RadicarExcepcionFL.class,
                RadicarExcepcionFL.NOMBRE_BEAN);
        radicarExcepcionFL.getRegistroRadicarExcepcionDTO().setRadicarExcepcionDTO(
                iRCoactivo.consultarRadicarExcepcion(consultaSegHolderFL.getSegSeleccionado().getIdCoactivo()));
    }

    /**
     * Valida si se puede agregar excepiones a ese coactivo
     * 
     * @author giovanni.velandia
     */
    public boolean validarRadicacionExcepciones() {
        LOGGER.debug("RadicarExcepcionMB::validarRadicacionExcepciones()");
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        try {
            iRCoactivo.validarRadicacionExcepciones(consultaSegHolderFL.getSegSeleccionado().getIdCoactivo());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        // Limpiar datos
        limpiarDatos();
        return true;
    }

    /**
     * @author giovanni.velandia
     */
    public void limpiarDatos() {
        RadicarExcepcionFL radicarExcepcionFL = findFlowObject(RadicarExcepcionFL.class,
                RadicarExcepcionFL.NOMBRE_BEAN);

        radicarExcepcionFL.setRegistroRadicarExcepcionDTO(new RegistroRadicarExcepcionDTO());
        radicarExcepcionFL.getRegistroRadicarExcepcionDTO().setRadicarExcepcionDTO(new RadicarExcepcionDTO());
        radicarExcepcionFL.getRegistroRadicarExcepcionDTO()
                .setRegistroArchivoExcepcionDTOs(new ArrayList<RegistroArchivoExcepcionDTO>());
    }

    /**
     * Valida si se puede fallar excepiones a ese coactivo
     * 
     * @author giovanni.velandia
     */
    public boolean validarFalloExcepciones() {
        LOGGER.debug("RadicarExcepcionMB::validarFalloExcepciones()");
        ConsultaSeguimientoHolderFL consultaSegHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        try {
            iRCoactivo.validarFalloExcepciones(consultaSegHolderFL.getSegSeleccionado().getIdCoactivo());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        // Limpiar datos
        limpiarDatos();
        return true;
    }

    /**
     * Permite cargar un archivo al listado de documents
     * 
     * @param event
     * @author giovanni.velandia
     */
    public void cargaArchivo(FileUploadEvent event) {
        LOGGER.debug("RadicarExcepcionMB::cargaArchivo(FileUploadEvent)");
        RadicarExcepcionFL radicarExcepcionFL = findFlowObject(RadicarExcepcionFL.class,
                RadicarExcepcionFL.NOMBRE_BEAN);
        final UploadedFile file = event.getFile();
        RegistroArchivoExcepcionDTO registroArchivoExcepcionDTO = new RegistroArchivoExcepcionDTO();
        ArchivoTransportableDTO archivoTransportableDTO = new ArchivoTransportableDTO();
        archivoTransportableDTO.setContenido(file.getContents());
        archivoTransportableDTO.setNombre(file.getFileName());
        registroArchivoExcepcionDTO.setArchivoTransportableDTO(archivoTransportableDTO);
        consecutivo++;
        registroArchivoExcepcionDTO.setConsecutivo(consecutivo);
        radicarExcepcionFL.getRegistroRadicarExcepcionDTO().getRegistroArchivoExcepcionDTOs()
                .add(registroArchivoExcepcionDTO);
    }

    /**
     * Consulta la anexo
     * 
     * @return Contenido del archivo
     * @author giovanni.velandia
     */
    public void verAnexoRad() {
        LOGGER.debug("RadicarExcepcionMB::verAnexoRad()");
        ConsultaSeguimientoHolderFL consultaSeguimientoHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        if (consultaSeguimientoHolderFL.getArchivoExcepcionRadSelDTO() != null
                && consultaSeguimientoHolderFL.getArchivoExcepcionRadSelDTO().getNumeroArchivo() != null) {
            verAnexo(consultaSeguimientoHolderFL.getArchivoExcepcionRadSelDTO().getNumeroArchivo());
        }
        consultaSeguimientoHolderFL.setAnexo(true);
    }

    /**
     * Consultar anexo
     * 
     * @return Contenido del archivo
     * @author giovanni.velandia
     */
    public void verAnexoFallo() {
        LOGGER.debug("RadicarExcepcionMB::verAnexoFallo()");
        ConsultaSeguimientoHolderFL consultaSeguimientoHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);

        if (consultaSeguimientoHolderFL.getArchivoExcepcionFalloSelDTO() != null
                && consultaSeguimientoHolderFL.getArchivoExcepcionFalloSelDTO().getNumeroArchivo() != null) {
            verAnexo(consultaSeguimientoHolderFL.getArchivoExcepcionFalloSelDTO().getNumeroArchivo());
        }
        consultaSeguimientoHolderFL.setAnexo(true);
    }

    /**
     * @author giovanni.velandia
     * @param archivoTransportableDTO
     */
    private void verAnexo(String numeroArchivo) {
        LOGGER.debug("RadicarExcepcionMB::verAnexo(String)");
        streamedContent = null;

        try {
            ArchivoTransportableDTO archivoTransportableDTO = repositorioArchivoEJB.consultarDocumento(numeroArchivo);

            streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                    archivoTransportableDTO.getNombre());

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }
    }

    /**
     * Cancelar ver anexos
     * 
     * @author giovanni.velandia
     */
    public void verCancelar() {
        LOGGER.debug("RadicarExcepcionMB::verCancelar()");
        ConsultaSeguimientoHolderFL consultaSeguimientoHolderFL = findFlowObject(ConsultaSeguimientoHolderFL.class,
                ConsultaSeguimientoHolderFL.NOMBRE_BEAN);
        consultaSeguimientoHolderFL.setArchivoExcepcionRadSelDTO(null);
        consultaSeguimientoHolderFL.setArchivoExcepcionFalloSelDTO(null);
        consultaSeguimientoHolderFL.setAnexo(false);
    }

    /**
     * Se encarga de retirar el documento de la lista de anexos
     * 
     * @author giovanni.velandia
     */
    public void eliminarDocumento() {
        LOGGER.debug("RadicarExcepcionMB::eliminarDocumento()");
        RadicarExcepcionFL radicarExcepcionFL = findFlowObject(RadicarExcepcionFL.class,
                RadicarExcepcionFL.NOMBRE_BEAN);

        radicarExcepcionFL.getRegistroRadicarExcepcionDTO().getRegistroArchivoExcepcionDTOs()
                .remove(radicarExcepcionFL.getRegistroArchivoExcepcionSelDTO());
    }

    /**
     * Tipos de archivos recibidos en el registro de documentos para la accidentalidad
     * 
     * @author giovanni.velandia
     * @return
     */
    public String getTiposArchivosPermitidos() {
        LOGGER.debug("RadicarExcepcionMB::getTiposArchivosPermitidos()");
        return TIPOS_ARCHIVOS_PERMITIDOS;
    }

    public String getMesErrLimitSize() {
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE).getString("msg_error_tamanio_archivo"), tamanioMaximoArch);
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public void setTamanioMaximoArch(int tamanioMaximoArch) {
        this.tamanioMaximoArch = tamanioMaximoArch;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }
}
