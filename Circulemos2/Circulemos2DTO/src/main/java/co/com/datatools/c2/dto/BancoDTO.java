package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed May 28 11:08:32 COT 2014
 */
public class BancoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Long codigo;
    private OrganismoTransitoDTO organismoTransito;
    private PersonaJuridicaDTO personaJuridica;

    // --- Constructor
    public BancoDTO() {
    }

    public BancoDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public PersonaJuridicaDTO getPersonaJuridica() {
        return this.personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridicaDTO personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

}
