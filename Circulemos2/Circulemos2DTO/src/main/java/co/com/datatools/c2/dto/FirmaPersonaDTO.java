package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class FirmaPersonaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    // --- Atributos
    private Integer id;
    private PersonaDTO persona;
    private Date fechaRegistro;
    private Long numeroImagen;

    // --- Constructor
    public FirmaPersonaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getNumeroImagen() {
        return numeroImagen;
    }

    public void setNumeroImagen(Long numeroImagen) {
        this.numeroImagen = numeroImagen;
    }

}