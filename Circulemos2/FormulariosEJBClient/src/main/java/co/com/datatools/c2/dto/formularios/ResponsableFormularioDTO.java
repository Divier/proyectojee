package co.com.datatools.c2.dto.formularios;

import java.util.Date;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 19 17:03:59 COT 2015
 */
public class ResponsableFormularioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private OrganismoTransitoDTO organismoTransito;
    private Date fechaInicioVincula;
    private Date fechaFinVincula;
    private String correoResponsableFormulario;
    private TipoResponsableFormularioDTO tipoResponsable;

    // Atributos utilizados para transporte de objetos relacionados
    private UnificacionResponsableDTO unificacionResponsableDTO;

    // --- Constructor
    public ResponsableFormularioDTO() {
    }

    public ResponsableFormularioDTO(Long id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicioVincula() {
        return this.fechaInicioVincula;
    }

    public void setFechaInicioVincula(Date fechaInicioVincula) {
        this.fechaInicioVincula = fechaInicioVincula;
    }

    public Date getFechaFinVincula() {
        return this.fechaFinVincula;
    }

    public void setFechaFinVincula(Date fechaFinVincula) {
        this.fechaFinVincula = fechaFinVincula;
    }

    public String getCorreoResponsableFormulario() {
        return this.correoResponsableFormulario;
    }

    public void setCorreoResponsableFormulario(String correoResponsableFormulario) {
        this.correoResponsableFormulario = correoResponsableFormulario;
    }

    public TipoResponsableFormularioDTO getTipoResponsable() {
        return this.tipoResponsable;
    }

    public void setTipoResponsable(TipoResponsableFormularioDTO tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public UnificacionResponsableDTO getUnificacionResponsableDTO() {
        return unificacionResponsableDTO;
    }

    public void setUnificacionResponsableDTO(UnificacionResponsableDTO unificacionResponsableDTO) {
        this.unificacionResponsableDTO = unificacionResponsableDTO;
    }

}
