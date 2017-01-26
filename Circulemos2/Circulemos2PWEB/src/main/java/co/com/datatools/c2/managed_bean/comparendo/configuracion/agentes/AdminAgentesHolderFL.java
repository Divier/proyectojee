package co.com.datatools.c2.managed_bean.comparendo.configuracion.agentes;

import java.util.List;

import co.com.datatools.c2.dto.MotivoVigenciaAgenteDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class AdminAgentesHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "adminAgentesHolderFL";

    private AgenteDTO agente;

    /**
     * Atributo que define el modelo para el manejo de los registros encontrados
     */
    private List<AgenteDTO> lsAgentes;
    /**
     * Atributo para manejar un registro del modelo seleccionado.
     */
    private AgenteDTO selAgente;

    /**
     * Atributo el cual identifica el organismo de transito con el cual se encuentra autenticado
     */
    private OrganismoTransitoDTO organismoTransito;

    public AdminAgentesHolderFL() {
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

    public List<AgenteDTO> getLsAgentes() {
        return lsAgentes;
    }

    public void setLsAgentes(List<AgenteDTO> lsAgentes) {
        this.lsAgentes = lsAgentes;
    }

    public AgenteDTO getSelAgente() {
        return selAgente;
    }

    public void setSelAgente(AgenteDTO selAgente) {
        this.selAgente = selAgente;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

}