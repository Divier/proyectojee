package co.com.datatools.c2.dto;

import java.io.Serializable;

import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;

/**
 * 
 * @author Generated
 * @version 2.0
 **/
public class EntidadRecaudadoraDTO implements Serializable {

    // Attributes Declaration

    private static final long serialVersionUID = 1L;
    private Integer idEntidadRecaudadora;
    private String codigoEntidad;
    private Integer codigoOrganismo;
    private String nombre;
    private PersonaJuridicaDTO personaJuridicaDTO;

    // Constructors Declaration

    public EntidadRecaudadoraDTO() {

    }

    // Start Methods Declaration

    public Integer getIdEntidadRecaudadora() {
        return idEntidadRecaudadora;
    }

    public void setIdEntidadRecaudadora(Integer idEntidadRecaudadora) {
        this.idEntidadRecaudadora = idEntidadRecaudadora;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PersonaJuridicaDTO getPersonaJuridicaDTO() {
        return personaJuridicaDTO;
    }

    public void setPersonaJuridicaDTO(PersonaJuridicaDTO personaJuridicaDTO) {
        this.personaJuridicaDTO = personaJuridicaDTO;
    }

    // Finish the class
}