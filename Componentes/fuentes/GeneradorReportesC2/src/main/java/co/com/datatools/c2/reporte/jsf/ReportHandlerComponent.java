package co.com.datatools.c2.reporte.jsf;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.reporte.ContenidoReporte;
import co.com.datatools.c2.reporte.FormatoReporte;
import co.com.datatools.c2.reporte.GeneradorReporte;
import co.com.datatools.c2.reporte.data.AbstractConectorPlantillaReporte;
import co.com.datatools.util.mail.EmailValidator;

import com.sun.faces.facelets.el.ContextualCompositeMethodExpression;

/**
 * Composite Component encargado de enmascarar el llamado a la generacion de reportes c2:reportHandler
 * 
 * @author felipe.martinez
 */
@FacesComponent(value = "reportHandler")
public class ReportHandlerComponent extends UINamingContainer {
    /**
     * Nombre del atributo a buscar en el ServletContext para obtener el objeto de tipo {@link AbstractConectorPlantillaReporte} para usar en la
     * aplicacion
     */
    public static final String NOMBRE_OBJ_CONECTOR_PLANTILLA = "REPORTES_CONECTOR_PLANTILLAS";
    private static final String NOMBRE_BUNDLE_REPORTE = "labelReporte";

    protected enum PropertyKeys {

        reportId, fileName, contentSrc, emailHandler;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
        }

    }

    private String format;
    private String formats;
    private List<SelectItem> formatosReporte;
    private String direccionesDestino;
    private String asunto;
    private String cuerpoCorreo;

    public ReportHandlerComponent() {
        formatosReporte = new ArrayList<SelectItem>(1);
        formatosReporte.add(new SelectItem(FormatoReporte.XLS.toString(), FormatoReporte.XLS.toString()));
        formatosReporte.add(new SelectItem(FormatoReporte.XLSX.toString(), FormatoReporte.XLSX.toString()));
    }

    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    public StreamedContent downloadReport() {
        final String reportId = (String) getAttributes().get(PropertyKeys.reportId.toString());

        final String fileName = StringUtils.defaultString((String) getAttributes()
                .get(PropertyKeys.fileName.toString()), reportId);

        FormatoReporte enumFormat = obtenerFormatoReporte();

        return new DefaultStreamedContent(new ByteArrayInputStream(reporteAsBytes(enumFormat)), enumFormat.contentType,
                fileName + "." + enumFormat);
    }

    /**
     * Modifica la lista de elementos de tipos de formato asignando lo formatos validos parametrizados
     * 
     * @return Lista de formatos
     */
    public List<SelectItem> formatosReportes() {
        List<SelectItem> formatos = new ArrayList<SelectItem>();
        if (StringUtils.isNotBlank(formats)) {
            for (String s : formats.split(",")) {
                try {
                    final FormatoReporte validFormat = FormatoReporte.valueOf(StringUtils.upperCase(StringUtils
                            .trimToEmpty(s)));
                    formatos.add(new SelectItem(validFormat.toString(), validFormat.toString()));
                } catch (Exception e) {
                    throw new RuntimeException("No debe suceder", e);
                }
            }
        }
        return formatos;
    }

    /**
     * Genera el reporte como byte[]
     * 
     * @return byte[] de reporte generado
     */
    public byte[] reporteAsBytes(FormatoReporte formato) {
        final String reportId = (String) getAttributes().get(PropertyKeys.reportId.toString());

        ContextualCompositeMethodExpression ccme = (ContextualCompositeMethodExpression) getAttributes().get(
                PropertyKeys.contentSrc.toString());
        ELContext elc = FacesContext.getCurrentInstance().getELContext();
        final ContenidoReporte contentSrc = (ContenidoReporte) ccme.invoke(elc, null);

        FormatoReporte enumFormat = obtenerFormatoReporte();

        contentSrc.setCodigo(reportId);

        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();

        final GeneradorReporte generadorReporte = new GeneradorReporte(
                (AbstractConectorPlantillaReporte) context.getAttribute(NOMBRE_OBJ_CONECTOR_PLANTILLA));

        return generadorReporte.asBytes(enumFormat, contentSrc, reportId);
    }

    /**
     * Envia correo con los datos ingresados en popUpEnviarCorreo (Direcciones correo destinatarios, asunto, cuerpo de correo, reporte como archivo
     * adjunto)
     */
    public void enviarCorreo() {
        final String reportId = (String) getAttributes().get(PropertyKeys.reportId.toString());

        FormatoReporte enumFormat = obtenerFormatoReporte();

        StringTokenizer st = new StringTokenizer(direccionesDestino, ",");
        List<String> destinatarios = new ArrayList<>();
        List<String> destinatariosError = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String direccion = st.nextToken().trim();
            if (EmailValidator.validate(direccion)) {
                destinatarios.add(direccion);
            } else {
                destinatariosError.add(direccion);
            }
        }

        if (destinatariosError.size() > 0) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", getBundle(NOMBRE_BUNDLE_REPORTE).getString(
                            "msg_error_direcciones_correo")
                            + destinatariosError.toString()));
            RequestContext.getCurrentInstance().update("msg_enviar_correo");
        } else if (destinatarios.size() > Integer.valueOf(getBundle(NOMBRE_BUNDLE_REPORTE).getString(
                "max_num_direcciones"))) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", getBundle(NOMBRE_BUNDLE_REPORTE).getString(
                            "msg_maximo_direcciones")
                            + destinatariosError.toString()));
            RequestContext.getCurrentInstance().update("msg_enviar_correo");
        } else {
            String fileName = StringUtils.defaultString((String) getAttributes().get(PropertyKeys.fileName.toString()),
                    reportId) + "." + enumFormat;

            ((EmailProcessor) getAttributes().get(PropertyKeys.emailHandler.toString())).enviarCorreoReporte(
                    destinatarios.toArray(new String[0]), asunto, StringUtils.defaultString(cuerpoCorreo), fileName,
                    reporteAsBytes(enumFormat));
            RequestContext.getCurrentInstance().execute("PF('popUpEnviarCorreo').hide()");
            asunto = null;
            direccionesDestino = null;
            cuerpoCorreo = null;
            format = formats.split(",")[0].toUpperCase().trim();
        }
    }

    /**
     * Obtiene el formato de reporte seleccionado para generar el reporte
     * 
     * @return FormatoReporte seleccionado
     */
    public FormatoReporte obtenerFormatoReporte() {
        FormatoReporte enumFormat = null;

        if (StringUtils.isNotEmpty(format)) {
            enumFormat = FormatoReporte.valueOf(format);
        }

        if (enumFormat == null) {
            throw new RuntimeException("");
        }
        return enumFormat;
    }

    /**
     * Obtiene un recurso a traves del nombre
     * 
     * @param nombreArchivo
     *            nombre del recurso a buscar
     * @return recurso encontrado, null si no existe
     */
    public ResourceBundle getBundle(String nombreArchivo) {
        return FacesContext.getCurrentInstance().getApplication()
                .getResourceBundle(FacesContext.getCurrentInstance(), nombreArchivo);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<SelectItem> getFormatosReporte() {
        return formatosReporte;
    }

    public void setFormatosReporte(List<SelectItem> formatosReporte) {
        this.formatosReporte = formatosReporte;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
        formatosReporte = formatosReportes();
    }

    public String getDireccionesDestino() {
        return direccionesDestino;
    }

    public void setDireccionesDestino(String direccionesDestino) {
        this.direccionesDestino = direccionesDestino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpoCorreo() {
        return cuerpoCorreo;
    }

    public void setCuerpoCorreo(String cuerpoCorreo) {
        this.cuerpoCorreo = cuerpoCorreo;
    }

}