package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

/**
 * Clase abstracta que contiene el organismo de transito para que sea heredada por los dtos usados en las reglas
 * 
 * @author robert.bautista - 15/08/2014
 */
public abstract class ReglaOrganismoBR implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Parámetro de entrada
     */
    private Integer organismoTransito;

    public ReglaOrganismoBR() {
    }

    public Integer getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

}
