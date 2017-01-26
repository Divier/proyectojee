package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ConceptoIncrementoInfraccion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    @NotNull
    private Integer idConcepto;

    public ConceptoIncrementoInfraccion() {
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }
}
