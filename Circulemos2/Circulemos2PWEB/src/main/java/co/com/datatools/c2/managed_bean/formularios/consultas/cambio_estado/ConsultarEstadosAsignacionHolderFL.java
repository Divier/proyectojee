package co.com.datatools.c2.managed_bean.formularios.consultas.cambio_estado;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioAsignacionDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class ConsultarEstadosAsignacionHolderFL extends AbstractSwfManagedBean {

    private OrganismoTransitoDTO organismoTransitoDTO;

    private EstadoFormularioAsignacionDTO estadoFormularioAsignacionDTO;

    private Integer tipoFormulario;

    private Long idResponsable;

    private PersonaJuridicaDTO personaJuridica;

    public PersonaJuridicaDTO getPersonaJuridica() {
        return personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridicaDTO personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }

    public EstadoFormularioAsignacionDTO getEstadoFormularioAsignacionDTO() {
        return estadoFormularioAsignacionDTO;
    }

    public void setEstadoFormularioAsignacionDTO(EstadoFormularioAsignacionDTO estadoFormularioAsignacionDTO) {
        this.estadoFormularioAsignacionDTO = estadoFormularioAsignacionDTO;
    }

    public Integer getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(Integer tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public Long getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Long idResponsable) {
        this.idResponsable = idResponsable;
    }

}
