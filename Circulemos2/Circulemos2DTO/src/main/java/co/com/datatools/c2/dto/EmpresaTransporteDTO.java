package co.com.datatools.c2.dto;

import java.util.List;

import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 14:38:18 COT 2015
 */
public class EmpresaTransporteDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private PersonaJuridicaDTO personaJuridica;
    private List<RutaDTO> rutas;

    // --- Constructor
    public EmpresaTransporteDTO() {
    }

    public EmpresaTransporteDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonaJuridicaDTO getPersonaJuridica() {
        return this.personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridicaDTO personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<RutaDTO> getRutas() {
        if (this.rutas == null) {
            this.rutas = new java.util.ArrayList<>(1);
        }
        return this.rutas;
    }

    public void setRutas(List<RutaDTO> rutas) {
        this.rutas = rutas;
    }

}
