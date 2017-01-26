package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 19 15:21:28 COT 2015
 */
public class UnificacionResponsableDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private OrganismoTransitoDTO organismoTransito;
    private PersonaDTO persona;
    private ResponsableFormularioDTO responsableFormulario;

    // --- Constructor
    public UnificacionResponsableDTO() {
    }

    /**
     * Extrae el nombre de la persona unificada
     * 
     * @return Nombre completo de la persona, empresa o organismo de transito
     * @author luis.forero (2015-10-20)
     */
    public String getNombre() {
        if (persona != null) {
            return persona.getNombreCompleto();
        } else if (organismoTransito != null) {
            return organismoTransito.getNombreOrganismo();
        }
        return null;
    }

    public UnificacionResponsableDTO(Long id) {
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public ResponsableFormularioDTO getResponsableFormulario() {
        return responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormularioDTO responsableFormulario) {
        this.responsableFormulario = responsableFormulario;
    }

}
