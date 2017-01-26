package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author pedro.moncada
 * 
 */
public class ConsConcDescTotaOrgaTran implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    @NotNull
    private Integer idConcepto;

    public ConsConcDescTotaOrgaTran() {
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
