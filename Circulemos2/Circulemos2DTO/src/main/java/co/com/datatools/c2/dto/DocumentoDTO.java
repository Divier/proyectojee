package co.com.datatools.c2.dto;

import java.io.Serializable;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class DocumentoDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Long ciDocumento;
    private String contenidoDocumento;
    private String descripcionDocumento;

    public Long getCiDocumento() {
        return ciDocumento;
    }

    public void setCiDocumento(Long ciDocumento) {
        this.ciDocumento = ciDocumento;
    }

    public String getContenidoDocumento() {
        return contenidoDocumento;
    }

    public void setContenidoDocumento(String contenidoDocumento) {
        this.contenidoDocumento = contenidoDocumento;
    }

    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }
}