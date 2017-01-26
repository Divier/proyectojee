package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class ConsecutivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer anio;
    private String consecutivo;
    private OrganismoTransitoDTO organismoTransito;
    private TipoConsecutivoDTO tipoConsecutivo;

    // --- Constructor
    public ConsecutivoDTO() {
    }

    public ConsecutivoDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public TipoConsecutivoDTO getTipoConsecutivo() {
        return this.tipoConsecutivo;
    }

    public void setTipoConsecutivo(TipoConsecutivoDTO tipoConsecutivo) {
        this.tipoConsecutivo = tipoConsecutivo;
    }

}