package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ConceptoPagoComparendoDescuentoPorCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    @NotNull
    private Integer codigoConcepto;

    public ConceptoPagoComparendoDescuentoPorCurso() {
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getCodigoConcepto() {
        return codigoConcepto;
    }

    public void setCodigoConcepto(Integer codigoConcepto) {
        this.codigoConcepto = codigoConcepto;
    }
}
