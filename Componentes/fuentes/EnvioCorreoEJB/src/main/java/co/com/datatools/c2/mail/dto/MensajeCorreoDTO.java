package co.com.datatools.c2.mail.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Esta clase es usada para configurar el envio de un mensaje de correo. La clase la soporta la parametrizacion pasandole los parametros de
 * configuracion o ya teniendo configurada previamente la cuenta con el subsistema de mail de Jboss EAP 6.2
 * 
 * @author luis.martinez
 * 
 */
public class MensajeCorreoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Direccion origen desde donde se envia el email
     */
    private String direccionRemitente;
    /**
     * Direcciones destino a donde se envia el email
     */
    private String[] direccionesEnvio;
    /**
     * Contiene la direcciones a donde se envia el email como copia
     */
    private String[] direccionesCC;
    /**
     * Asunto del email
     */
    private String asunto;
    /**
     * Contenido o cuerpo del email
     */
    private String textoCorreo;
    /**
     * Formato de extensiones multiproposito de correo. Ejemplo text/plain, text/html
     */
    private String mimeType;
    /**
     * Lista de archivos que se van adjuntar a un email
     */
    private List<DocAdjuntoDTO> docAdjuntosList;
    /**
     * Objeto que contiene los parametros de cuenta de correo usada para el envio de correo como usuario, contraseña, protocolo y puerto de envio de
     * correo
     */
    private ParametrosCorreoDTO parametrosCorreo;

    /**
     * Crea un mensaje de correo con parametros dinamicos. La cuenta de correo origen usada para el envio de correo se obtiene de la informacion
     * configurada dentro del subsistema de mail de JBoss
     * 
     * @param direccionesEnvio
     *            String[] que contiene las direcciones de envio.
     * @param asunto
     *            String que contiene el asunto del correo
     * @param textoCorreo
     *            String que contiene el cuerpo del correo.
     * @param mimeType
     *            String que contiene el tipo de MIME.
     */
    public MensajeCorreoDTO(String[] direccionesEnvio, String asunto, String textoCorreo, String mimeType) {
        docAdjuntosList = new ArrayList<DocAdjuntoDTO>();
        this.direccionesEnvio = direccionesEnvio;
        this.asunto = asunto;
        this.textoCorreo = textoCorreo;
        this.mimeType = mimeType;
    }

    /**
     * Crea un mensaje de correo con parametros dinamicos. La cuenta de correo origen usada para el envio de correo se obtiene de los parametos
     * ingresados a traves del objeto ParametrosCorreoDTO contenido dentro de MensajeCorreoDTO
     * 
     * @param direccionRemitente
     *            String que contiene la direccion del remitente.
     * @param direccionesEnvio
     *            String[] que contiene las direcciones de envio.
     * @param asunto
     *            String que contiene el asunto del correo
     * @param textoCorreo
     *            String que contiene el cuerpo del correo.
     * @param mimeType
     *            String que contiene el tipo de MIME.
     * @param parametrosCorreo
     */
    public MensajeCorreoDTO(String direccionRemitente, String[] direccionesEnvio, String asunto, String textoCorreo,
            String mimeType, ParametrosCorreoDTO parametrosCorreo) {
        docAdjuntosList = new ArrayList<DocAdjuntoDTO>(0);
        this.direccionRemitente = direccionRemitente;
        this.direccionesEnvio = direccionesEnvio;
        this.asunto = asunto;
        this.textoCorreo = textoCorreo;
        this.mimeType = mimeType;
        this.parametrosCorreo = parametrosCorreo;
    }

    public String getDireccionRemitente() {
        return direccionRemitente;
    }

    public void setDireccionRemitente(String direccionRemitente) {
        this.direccionRemitente = direccionRemitente;
    }

    public String[] getDireccionesEnvio() {
        return direccionesEnvio;
    }

    public void setDireccionesEnvio(String[] direccionesEnvio) {
        this.direccionesEnvio = direccionesEnvio;
    }

    public String[] getDireccionesCC() {
        return direccionesCC;
    }

    public void setDireccionesCC(String[] direccionesCC) {
        this.direccionesCC = direccionesCC;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTextoCorreo() {
        return textoCorreo;
    }

    public void setTextoCorreo(String textoCorreo) {
        this.textoCorreo = textoCorreo;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void addDocAdjunto(DocAdjuntoDTO doc) {
        if (docAdjuntosList == null) {
            docAdjuntosList = new ArrayList<DocAdjuntoDTO>();
        }

        this.docAdjuntosList.add(doc);
    }

    public void removeDocAdjunto(int posicionDoc) {
        this.docAdjuntosList.remove(posicionDoc);
    }

    public void removeDocAdjunto(DocAdjuntoDTO doc) {
        this.docAdjuntosList.remove(doc);
    }

    public void clearDocAdjuntolist() {
        this.docAdjuntosList.clear();
    }

    public List<DocAdjuntoDTO> getDocAdjuntosList() {
        return this.docAdjuntosList;
    }

    public ParametrosCorreoDTO getParametrosCorreo() {
        return parametrosCorreo;
    }

    public void setParametrosCorreo(ParametrosCorreoDTO parametrosCorreo) {
        this.parametrosCorreo = parametrosCorreo;
    }

    @Override
    public String toString() {
        return "MensajeCorreoDTO [direccionRemitente=" + direccionRemitente + ", direccionesEnvio="
                + Arrays.toString(direccionesEnvio) + ", direccionesCC=" + Arrays.toString(direccionesCC) + ", asunto="
                + asunto + ", textoCorreo=" + textoCorreo + ", mimeType=" + mimeType + ", docAdjuntosList="
                + docAdjuntosList + ", parametrosCorreo=" + parametrosCorreo + "]";
    }

}
