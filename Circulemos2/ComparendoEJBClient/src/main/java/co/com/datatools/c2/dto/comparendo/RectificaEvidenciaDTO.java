package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Feb 08 15:12:55 COT 2016
 */
public class RectificaEvidenciaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer codigoTipoEvidencia;
    private String idDocumento;
    private RectificaComparendoDTO rectificaComparendo;

    // --- Constructor
    public RectificaEvidenciaDTO() {
    }

    public RectificaEvidenciaDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoEvidencia() {
        return this.codigoTipoEvidencia;
    }

    public void setCodigoTipoEvidencia(Integer codigoTipoEvidencia) {
        this.codigoTipoEvidencia = codigoTipoEvidencia;
    }

    public String getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public RectificaComparendoDTO getRectificaComparendo() {
        return this.rectificaComparendo;
    }

    public void setRectificaComparendo(RectificaComparendoDTO rectificaComparendo) {
        this.rectificaComparendo = rectificaComparendo;
    }

}
