package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.io.ByteArrayInputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Managed Bean para manejo de visualizacion de PDF
 * 
 * @author julio.pinzon 2016-07-08
 *
 */
@ManagedBean
@SessionScoped
public class VisualizarDocumentoMB extends AbstractSwfManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(VisualizarDocumentoMB.class.getName());

    /**
     * Atributos para visualizar documentos
     */
    private static final String CONTENT_TYPE = "application/pdf";
    private final String NOMBRE_ARCHIVO = "Documento.pdf";

    private final String NOMBRE_BUNDLE = "labelProcesoImpugnacion";

    /**
     * Documento a visualizar
     */
    private StreamedContent documento;

    /**
     * Mensaje a mostrar
     */
    private String mensaje;

    /**
     * Titulo a mostrar
     */
    private String titulo;

    /**
     * 
     */
    private boolean visualizar;

    private boolean visible;

    private String mensajeNotificacion;
    private String tituloNotificacion;
    private boolean visualizarNotificacion;

    @PostConstruct
    public void init() {
        logger.debug("VisualizarDocumentoMB::init()");
    }

    /**
     * Inicializa informacion de la visualizacion
     * 
     * @author julio.pinzon 2016-08-30
     */
    public void inicializarDatos() {
        titulo = null;
        mensaje = null;
        documento = null;
        visible = false;
    }

    /**
     * Abre el popup para genera el documento
     * 
     * @param contenidoDoc
     * @param nombreArchivo
     * @author julio.pinzon 2016-07-08
     */
    public void visualizarDocumento(byte[] contenidoDoc, String nombreArchivo, String mensaje, String titulo) {
        logger.debug("EncabezadoImpugnacionMB::consultarEncabezado()");
        inicializarDatos();
        if (StringUtils.isNotBlank(mensaje)) {
            this.mensaje = mensaje;
        }
        if (StringUtils.isNotBlank(titulo)) {
            this.titulo = titulo;
        }
        if (contenidoDoc != null) {
            if (StringUtils.isBlank(nombreArchivo)) {
                nombreArchivo = NOMBRE_ARCHIVO;
            }
            documento = new DefaultStreamedContent(new ByteArrayInputStream(contenidoDoc), CONTENT_TYPE, nombreArchivo);
        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form-pdfviewer");
        context.execute("PF('dlgPdfviewer').show();");
        visible = true;
    }

    public StreamedContent getDocumento() {
        return documento;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTitulo() {
        if (StringUtils.isBlank(titulo)) {
            titulo = getBundle(NOMBRE_BUNDLE).getString("label_visualizar");
        }
        return titulo;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getMensajeNotificacion() {
        return mensajeNotificacion;
    }

    public void setMensajeNotificacion(String mensajeNotificacion) {
        this.mensajeNotificacion = mensajeNotificacion;
    }

    public String getTituloNotificacion() {
        return tituloNotificacion;
    }

    public void setTituloNotificacion(String tituloNotificacion) {
        this.tituloNotificacion = tituloNotificacion;
    }

    public boolean isVisualizarNotificacion() {
        return visualizarNotificacion;
    }

    public void setVisualizarNotificacion(boolean visualizarNotificacion) {
        this.visualizarNotificacion = visualizarNotificacion;
    }
}