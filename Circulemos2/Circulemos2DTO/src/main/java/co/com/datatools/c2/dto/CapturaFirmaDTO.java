package co.com.datatools.c2.dto;

import java.io.Serializable;

import co.com.datatools.c2.dto.personas.PersonaDTO;

/**
 * Se encarga de registrar la captura de la firma
 * 
 * @author giovanni.velandia
 */
public class CapturaFirmaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // --- Atributos
    private String firma;
    private PersonaDTO personaDTO;
    private String placaAgente;
    private String cargo;

    // --- Constructor
    public CapturaFirmaDTO() {
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public PersonaDTO getPersonaDTO() {
        return personaDTO;
    }

    public void setPersonaDTO(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
    }

    public String getPlacaAgente() {
        return placaAgente;
    }

    public void setPlacaAgente(String placaAgente) {
        this.placaAgente = placaAgente;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}