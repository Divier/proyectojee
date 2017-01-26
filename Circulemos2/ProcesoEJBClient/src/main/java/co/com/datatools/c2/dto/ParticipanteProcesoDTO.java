package co.com.datatools.c2.dto;

import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 11:58:50 COT 2016
 */
public class ParticipanteProcesoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ProcesoDTO proceso;
    private PersonaDTO persona;
    private TipoParticipanteDTO tipoParticipante;

    // --- Constructor
    public ParticipanteProcesoDTO() {
    }

    public ParticipanteProcesoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcesoDTO getProceso() {
        return this.proceso;
    }

    public void setProceso(ProcesoDTO proceso) {
        this.proceso = proceso;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public TipoParticipanteDTO getTipoParticipante() {
        return this.tipoParticipante;
    }

    public void setTipoParticipante(TipoParticipanteDTO tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }

}
