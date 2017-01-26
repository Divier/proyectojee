package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Clase para el motor de reglas que obtiene el concepto de cartera por el cual se crea una cartera para un comparendo por organismo de transito, el
 * nombre de la clase es la breviacion de tres siglas para "ConsultarConceptoCreacionCarteraComparendoOrganismoTransito"
 * 
 * @author pedro.moncada
 * 
 */
public class ConsConcCreaCartCompOrgaTranDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;

    @NotNull
    private Integer codigoConcepto;

    public ConsConcCreaCartCompOrgaTranDTO() {
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
