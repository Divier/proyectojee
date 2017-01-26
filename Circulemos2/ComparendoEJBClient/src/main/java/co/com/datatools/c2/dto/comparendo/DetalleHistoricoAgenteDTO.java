package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

import co.com.datatools.c2.dto.MotivoVigenciaAgenteDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;

public class DetalleHistoricoAgenteDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private HistoricoAgenteDTO historicoAgenteDTO;
    private PersonaDTO personaDTO;
    private MotivoVigenciaAgenteDTO motivoVigenciaAgenteDTO;

    public HistoricoAgenteDTO getHistoricoAgenteDTO() {
        return historicoAgenteDTO;
    }

    public void setHistoricoAgenteDTO(HistoricoAgenteDTO historicoAgenteDTO) {
        this.historicoAgenteDTO = historicoAgenteDTO;
    }

    public PersonaDTO getPersonaDTO() {
        return personaDTO;
    }

    public void setPersonaDTO(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
    }

    public MotivoVigenciaAgenteDTO getMotivoVigenciaAgenteDTO() {
        return motivoVigenciaAgenteDTO;
    }

    public void setMotivoVigenciaAgenteDTO(MotivoVigenciaAgenteDTO motivoVigenciaAgenteDTO) {
        this.motivoVigenciaAgenteDTO = motivoVigenciaAgenteDTO;
    }

}
