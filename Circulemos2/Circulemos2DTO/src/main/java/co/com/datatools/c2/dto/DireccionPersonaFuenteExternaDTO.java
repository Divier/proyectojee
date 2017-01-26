package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Nov 19 11:24:56 COT 2014
 */
public class DireccionPersonaFuenteExternaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
    private DireccionDTO direccion;
    private PersonaFuenteExternaDTO personaFuenteExterna;

    // --- Constructor
    public DireccionPersonaFuenteExternaDTO() {
    }

    public DireccionPersonaFuenteExternaDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public DireccionDTO getDireccion() {
        return this.direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public PersonaFuenteExternaDTO getPersonaFuenteExterna() {
        return this.personaFuenteExterna;
    }

    public void setPersonaFuenteExterna(PersonaFuenteExternaDTO personaFuenteExterna) {
        this.personaFuenteExterna = personaFuenteExterna;
    }

}
