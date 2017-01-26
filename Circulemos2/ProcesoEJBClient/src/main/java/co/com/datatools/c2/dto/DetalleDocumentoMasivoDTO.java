package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Valores para documentos masivos
 * 
 */
public class DetalleDocumentoMasivoDTO implements EntidadDtoC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private DocumentoMasivoDTO documentoMasivo;
    private Long idDocumento;

    public DetalleDocumentoMasivoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocumentoMasivoDTO getDocumentoMasivo() {
        return documentoMasivo;
    }

    public void setDocumentoMasivo(DocumentoMasivoDTO documentoMasivo) {
        this.documentoMasivo = documentoMasivo;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

}