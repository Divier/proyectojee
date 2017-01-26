package co.com.datatools.c2.mail.dto;

import java.io.Serializable;

/**
 * Esta clase sirve para pasar un documento adjunto en un Mensaje de Correo
 * 
 * @see MensajeCorreoDTO
 */
public class DocAdjuntoDTO implements Serializable {

    private static final long serialVersionUID = -618357588150375682L;

    /**
     * Nombre del documento adjunto
     */
    private String nombre;
    /**
     * Contenido del documento adjunto
     */
    byte[] byteArray;

    private String alias;
    /**
     * Valida si el adjunto tiene una imagen embebida
     */
    private boolean imagenEmbebida;

    public DocAdjuntoDTO() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setImagenEmbebida(boolean imagenEmbebida) {
        this.imagenEmbebida = imagenEmbebida;
    }

    public boolean isImagenEmbebida() {
        return imagenEmbebida;
    }
}
