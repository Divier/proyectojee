package co.com.datatools.c2.managed_bean.formularios.responsable;

import java.io.Serializable;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;

/**
 * Objeto que permite identificar la entidad de la unificacion del responsable
 * 
 * @author luis.forero(2015-09-02)
 * 
 */
public class UnificacionResponsableFL implements Serializable {

    private static final long serialVersionUID = 1L;

    private PersonaDTO personaDTO;
    private OrganismoTransitoDTO organismoTransitoDTO;

    private String numeroIdentificacion;
    private String nombre;

    public UnificacionResponsableFL() {
    }

    public UnificacionResponsableFL(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
        if (this.personaDTO instanceof PersonaJuridicaDTO) {
            nombre = ((PersonaJuridicaDTO) this.personaDTO).getNombreComercial();
        } else {
            nombre = this.personaDTO.getNombreCompleto();
        }
        this.numeroIdentificacion = this.personaDTO.getNumeroIdentificacion();
    }

    public UnificacionResponsableFL(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
        this.numeroIdentificacion = this.organismoTransitoDTO.getCodigoOrganismo().toString();
        this.nombre = this.organismoTransitoDTO.getNombreOrganismo();
    }

    public PersonaDTO getPersonaDTO() {
        return personaDTO;
    }

    public void setPersonaDTO(PersonaDTO personaDTO) {
        this.personaDTO = personaDTO;
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((numeroIdentificacion == null) ? 0 : numeroIdentificacion.hashCode());
        result = prime
                * result
                + ((organismoTransitoDTO != null && organismoTransitoDTO.getCodigoOrganismo() != null) ? organismoTransitoDTO
                        .getCodigoOrganismo().hashCode() : 0);
        result = prime * result
                + ((personaDTO != null && personaDTO.getId() != null) ? personaDTO.getId().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UnificacionResponsableFL other = (UnificacionResponsableFL) obj;
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (numeroIdentificacion == null) {
            if (other.numeroIdentificacion != null) {
                return false;
            }
        } else if (!numeroIdentificacion.equals(other.numeroIdentificacion)) {
            return false;
        }
        if (organismoTransitoDTO == null) {
            if (other.organismoTransitoDTO != null) {
                return false;
            }
        } else if (!organismoTransitoDTO.equals(other.organismoTransitoDTO)) {
            return false;
        }
        if (personaDTO == null) {
            if (other.personaDTO != null) {
                return false;
            }
        } else if (!personaDTO.equals(other.personaDTO)) {
            return false;
        }
        return true;
    }

}
