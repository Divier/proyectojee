package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jul 14 11:48:34 COT 2016
 */
public class DocumentoComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TrazabilidadComparendoDTO trazabilidadComparendo;
    private String numeroDocumento;

    // --- Constructor
    public DocumentoComparendoDTO() {
    }

    public DocumentoComparendoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadComparendoDTO getTrazabilidadComparendo() {
        return this.trazabilidadComparendo;
    }

    public void setTrazabilidadComparendo(TrazabilidadComparendoDTO trazabilidadComparendo) {
        this.trazabilidadComparendo = trazabilidadComparendo;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

}
