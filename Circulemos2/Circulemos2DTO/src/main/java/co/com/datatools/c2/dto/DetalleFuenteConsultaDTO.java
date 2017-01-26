package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 13 14:33:40 COT 2016
 */
public class DetalleFuenteConsultaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer prioridad;
    private TipoFuenteConsultaDTO tipoFuenteConsulta;
    private FuenteConsultaDTO fuenteConsulta;
    private OrganismoTransitoDTO organismoTransito;

    // --- Constructor
    public DetalleFuenteConsultaDTO() {
    }

    public DetalleFuenteConsultaDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public TipoFuenteConsultaDTO getTipoFuenteConsulta() {
        return this.tipoFuenteConsulta;
    }

    public void setTipoFuenteConsulta(TipoFuenteConsultaDTO tipoFuenteConsulta) {
        this.tipoFuenteConsulta = tipoFuenteConsulta;
    }

    public FuenteConsultaDTO getFuenteConsulta() {
        return this.fuenteConsulta;
    }

    public void setFuenteConsulta(FuenteConsultaDTO fuenteConsulta) {
        this.fuenteConsulta = fuenteConsulta;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

}
