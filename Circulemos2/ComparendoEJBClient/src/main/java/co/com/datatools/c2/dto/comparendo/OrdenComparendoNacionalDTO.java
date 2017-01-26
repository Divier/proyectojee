package co.com.datatools.c2.dto.comparendo;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class OrdenComparendoNacionalDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long cicomparendo;
    private String numeroComparendo;
    private ComparendoDTO comparendo;
    private OrganismoTransitoDTO organismoTransito;

    // --- Constructor
    public OrdenComparendoNacionalDTO() {
    }

    public OrdenComparendoNacionalDTO(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    // --- Getters-Setters
    public Long getCicomparendo() {
        return this.cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public String getNumeroComparendo() {
        return this.numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public ComparendoDTO getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(ComparendoDTO comparendo) {
        this.comparendo = comparendo;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

}