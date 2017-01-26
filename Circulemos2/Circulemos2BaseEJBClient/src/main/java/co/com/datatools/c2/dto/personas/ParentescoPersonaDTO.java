package co.com.datatools.c2.dto.personas;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class ParentescoPersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Date fechaInicio;
    private Date fechaFin;
    private TipoParentescoPersonaDTO tipoParentesco;
    private PersonaDTO principal;
    private PersonaDTO pariente;

    // --- Constructor
    public ParentescoPersonaDTO() {
    }

    public ParentescoPersonaDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public TipoParentescoPersonaDTO getTipoParentesco() {
        return this.tipoParentesco;
    }

    public void setTipoParentesco(TipoParentescoPersonaDTO tipoParentesco) {
        this.tipoParentesco = tipoParentesco;
    }

    public PersonaDTO getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(PersonaDTO principal) {
        this.principal = principal;
    }

    public PersonaDTO getPariente() {
        return this.pariente;
    }

    public void setPariente(PersonaDTO pariente) {
        this.pariente = pariente;
    }

}