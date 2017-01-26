package co.com.datatools.c2.managed_bean.comparendo.configuracion.agentes;

import java.util.List;

import co.com.datatools.c2.dto.MotivoVigenciaAgenteDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.DetalleHistoricoAgenteDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class AdminAgentesFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "adminAgentesFL";
    private AgenteDTO agente;
    private String numeroFirma;
    private boolean traePersona = false;
    private List<DetalleHistoricoAgenteDTO> detalleHistoricoAgenteDTOs;
    private boolean agenteVigente = false;

    public AdminAgentesFL() {
        agente = new AgenteDTO();
        PersonaDTO persona = new PersonaDTO();
        persona.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
        agente.setPersona(persona);
        agente.setMotivoVigenciaAgente(new MotivoVigenciaAgenteDTO());
    }

    public AgenteDTO getAgente() {
        return agente;
    }

    public void setAgente(AgenteDTO agente) {
        this.agente = agente;
    }

    public String getNumeroFirma() {
        return numeroFirma;
    }

    public void setNumeroFirma(String numeroFirma) {
        this.numeroFirma = numeroFirma;
    }

    public boolean isTraePersona() {
        return traePersona;
    }

    public void setTraePersona(boolean traePersona) {
        this.traePersona = traePersona;
    }

    public List<DetalleHistoricoAgenteDTO> getDetalleHistoricoAgenteDTOs() {
        return detalleHistoricoAgenteDTOs;
    }

    public void setDetalleHistoricoAgenteDTOs(List<DetalleHistoricoAgenteDTO> detalleHistoricoAgenteDTOs) {
        this.detalleHistoricoAgenteDTOs = detalleHistoricoAgenteDTOs;
    }

    public boolean isAgenteVigente() {
        return agenteVigente;
    }

    public void setAgenteVigente(boolean agenteVigente) {
        this.agenteVigente = agenteVigente;
    }
}