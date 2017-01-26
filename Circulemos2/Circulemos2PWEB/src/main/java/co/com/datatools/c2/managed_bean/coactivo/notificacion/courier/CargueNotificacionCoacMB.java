package co.com.datatools.c2.managed_bean.coactivo.notificacion.courier;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.CargueNotificaCoactivoResulDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.notificacion_correo.NotificarComparendoViaCorreoMB;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

@ManagedBean
@SessionScoped
public class CargueNotificacionCoacMB  extends AbstractC2ManagedBean {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(NotificarComparendoViaCorreoMB.class.getName());
	
	private static final String NOMBRE_BUNDLE_CARGUE_NOTIF_COAC = "labelCargueNotificacionCoactivos";
	public static final String RUTA_MANUALES_FILE_SYSTEM = "PATH_MANUALES";
    private final String UBICACION_SERVER = System.getProperty(RUTA_MANUALES_FILE_SYSTEM);
    private StreamedContent instructivo;
	
	@EJB
	private IRParametro parametroEJB;
	
	@EJB
	private IRCoactivo coactivoEJB;
	
	private int tamanioMaximoArch;
	private boolean archivoProcesado = false;
	public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(csv)$/";
	
	@PostConstruct
    public void init() {
        logger.debug("AdminComparendoMB::init()");
        ValorParametroDTO valTamMaxAdjArch = parametroEJB.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
        
        Path pathManual = Paths.get(UBICACION_SERVER, "MANUAL_ARCHIVO_CSV.pdf");
        try (InputStream stream = new ByteArrayInputStream(Files.readAllBytes(pathManual))) {
            instructivo = new DefaultStreamedContent(stream, "pdf/application", "instructivo.pdf");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
	
	public void cargarArchivoAcuse(FileUploadEvent event) {
        logger.debug(NotificarComparendoViaCorreoMB.class.getName().concat("::cargarArchivoAcuse()"));
        
        CargueNotificacionCoacHolderFL cargueHolderFL = findFlowObject(CargueNotificacionCoacHolderFL.class,
        		CargueNotificacionCoacHolderFL.NOMBRE_BEAN);

        final UploadedFile file = event.getFile();
        final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(), file.getContents());

        cargueHolderFL.setArchivo(archivo);
        try{
        	CargueNotificaCoactivoResulDTO respuestaCargue = coactivoEJB.procesarArchivoNotificacionesCoactivos(archivo);
        	cargueHolderFL.setRespuestaCargue(respuestaCargue);
        	this.archivoProcesado  = true;
        }catch(CirculemosNegocioException ex){
        	this.archivoProcesado = false;
        	CirculemosErrorHandler.handleException(ex);
        	return;
        }
        mostrarPopUpResumen();
	}

	public int getTamanioMaximoArch() {
		return tamanioMaximoArch;
	}

	public void setTamanioMaximoArch(int tamanioMaximoArch) {
		this.tamanioMaximoArch = tamanioMaximoArch;
	}

	public String getTiposArchivosPermitidos() {
		return TIPOS_ARCHIVOS_PERMITIDOS;
	}
	
	public String getMensajeDescArchivo(){
		String mensajearchivo = getBundle(NOMBRE_BUNDLE_CARGUE_NOTIF_COAC).getString("msg_info_archivo");
		mensajearchivo = MessageFormat.format(mensajearchivo, Utilidades.convertirBytesToMegabytes(this.tamanioMaximoArch));
		return mensajearchivo;
	}
	
	public StreamedContent descargarArchivoIncosistencias(){
		logger.debug(NotificarComparendoViaCorreoMB.class.getName().concat("::descargarArchivoIncosistencias()"));
		StreamedContent streamedContent = null;
		CargueNotificacionCoacHolderFL cargueHolderFL = findFlowObject(CargueNotificacionCoacHolderFL.class,
				CargueNotificacionCoacHolderFL.NOMBRE_BEAN);
		ArchivoTransportableDTO archivoTransportableDTO = cargueHolderFL.getRespuestaCargue().getReporteInconsistencias();
		//streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null, archivoTransportableDTO.getNombre());
		InputStream inputStream = new ByteArrayInputStream(archivoTransportableDTO.getContenido());
		streamedContent = new DefaultStreamedContent(inputStream, "text/csv", archivoTransportableDTO.getNombre(), StandardCharsets.UTF_8.name());
		return streamedContent;
	}

	public boolean isArchivoProcesado() {
		return archivoProcesado;
	}

	public void setArchivoProcesado(boolean archivoProcesado) {
		this.archivoProcesado = archivoProcesado;
	}
	
	public void mostrarPopUpResumen(){
		if(archivoProcesado){
			RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('popUpRespuestaProcesoCargue').show();");
		}
	}

	public StreamedContent getInstructivo() {
		return instructivo;
	}
}
