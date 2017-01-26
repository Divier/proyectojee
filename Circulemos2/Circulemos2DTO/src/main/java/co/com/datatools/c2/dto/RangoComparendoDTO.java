package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class RangoComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String numeroInicial;
    private String numeroFinal;
    private OrganismoTransitoDTO organismoTransito;
    private TipoMovimientoRangoDTO tipoMovimientoRango;

    // --- Constructor
    public RangoComparendoDTO() {
    }

    public RangoComparendoDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroInicial() {
        return this.numeroInicial;
    }

    public void setNumeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public String getNumeroFinal() {
        return this.numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public TipoMovimientoRangoDTO getTipoMovimientoRango() {
        return this.tipoMovimientoRango;
    }

    public void setTipoMovimientoRango(TipoMovimientoRangoDTO tipoMovimientoRango) {
        this.tipoMovimientoRango = tipoMovimientoRango;
    }

}