package co.com.datatools.c2.managed_bean.formularios.responsable;

import java.io.Serializable;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;

public class ResponsableFormularioFL implements Serializable {

    private static final long serialVersionUID = 1L;

    // GENERAL
    /**
     * Atributo que permite determinar el responsable
     */
    private ResponsableFormularioDTO responsableFormulario;
    /**
     * Atributo que permite determinar la persona o organismo u otra entidad relacionada con el responsable de formulario.
     */
    private UnificacionResponsableDTO unificacionResponsableDTO;
    /**
     * Atributo que identifica el organismo
     */
    private OrganismoTransitoDTO organismoTransitoDTO;

    // CAMPOS DE REGISTRO

    /**
     * Atributo que identifica el tipo de responsable
     */
    private Integer idTipoResponsableFormulario;

    /**
     * Atributo para identificar el tipo de identificación del responsable a consultar y asignar
     */
    private Integer idTipoIdentiResponsable;
    /**
     * Atributo para identificar el numero de documento o codigo de organismo del responsable unificado
     */
    private String identificacionResponsable;
    /**
     * Permite visualizar en nombre de la empresa o de la persona unificada del responsable
     */
    private String nombre;
    /**
     * Atributo que permite identificar por el id la persona o el organismo seleccionado
     */
    private Object idPersonaOrganismo;

    private Long idResponsable;

    public ResponsableFormularioFL() {
        initDatosRegistro();
    }

    public ResponsableFormularioFL(UnificacionResponsableDTO unificacionResponsable) {
        initDatosRegistro(unificacionResponsable);
    }

    public void initDatosRegistro(UnificacionResponsableDTO unificacionResponsableDTO) {
        this.responsableFormulario = unificacionResponsableDTO.getResponsableFormulario();
        this.idResponsable = unificacionResponsableDTO.getResponsableFormulario().getId();
        this.organismoTransitoDTO = unificacionResponsableDTO.getResponsableFormulario().getOrganismoTransito();
        this.idTipoResponsableFormulario = this.responsableFormulario.getTipoResponsable().getId();

        setUnificacionResponsable(unificacionResponsableDTO);
    }

    /**
     * 
     * @param unificacionResponsableDTO
     */
    public void setUnificacionResponsable(UnificacionResponsableDTO unificacionResponsableDTO) {
        if (unificacionResponsableDTO != null) {
            this.unificacionResponsableDTO = unificacionResponsableDTO;
            if (unificacionResponsableDTO.getOrganismoTransito() != null
                    && unificacionResponsableDTO.getOrganismoTransito().getCodigoOrganismo() != null) {
                this.identificacionResponsable = String.valueOf(unificacionResponsableDTO.getOrganismoTransito()
                        .getCodigoOrganismo());
                this.idPersonaOrganismo = unificacionResponsableDTO.getOrganismoTransito().getCodigoOrganismo();
                this.nombre = unificacionResponsableDTO.getOrganismoTransito().getNombreOrganismo();
            }
            PersonaDTO persona = unificacionResponsableDTO.getPersona();
            if (persona != null) {
                this.idPersonaOrganismo = persona.getId();
                this.identificacionResponsable = persona.getNumeroIdentificacion();
                if (persona instanceof PersonaJuridicaDTO) {
                    this.nombre = ((PersonaJuridicaDTO) persona).getNombreComercial();
                } else {
                    this.nombre = persona.getNombreCompleto();
                }
            }
        } else {
            this.identificacionResponsable = null;
            this.idPersonaOrganismo = null;
            this.nombre = null;
            this.unificacionResponsableDTO = null;
        }

    }

    public void initDatosRegistro() {
        this.responsableFormulario = new ResponsableFormularioDTO();
        this.unificacionResponsableDTO = new UnificacionResponsableDTO();

        this.organismoTransitoDTO = null;

        this.idTipoResponsableFormulario = null;

        this.idTipoIdentiResponsable = null;
        this.identificacionResponsable = null;
        this.idPersonaOrganismo = null;
        this.nombre = null;
    }

    public ResponsableFormularioDTO getResponsableFormulario() {
        return responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormularioDTO responsableFormulario) {
        this.responsableFormulario = responsableFormulario;
    }

    public Integer getIdTipoResponsableFormulario() {
        return idTipoResponsableFormulario;
    }

    public void setIdTipoResponsableFormulario(Integer idTipoResponsableFormulario) {
        this.idTipoResponsableFormulario = idTipoResponsableFormulario;
    }

    public Integer getIdTipoIdentiResponsable() {
        return idTipoIdentiResponsable;
    }

    public void setIdTipoIdentiResponsable(Integer idTipoIdentiResponsable) {
        this.idTipoIdentiResponsable = idTipoIdentiResponsable;
    }

    public String getIdentificacionResponsable() {
        return identificacionResponsable;
    }

    public void setIdentificacionResponsable(String identificacionResponsable) {
        this.identificacionResponsable = identificacionResponsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Long idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Object getIdPersonaOrganismo() {
        return idPersonaOrganismo;
    }

    public void setIdPersonaOrganismo(Object idPersonaOrganismo) {
        this.idPersonaOrganismo = idPersonaOrganismo;
    }

    public UnificacionResponsableDTO getUnificacionResponsableDTO() {
        return unificacionResponsableDTO;
    }

    public void setUnificacionResponsableDTO(UnificacionResponsableDTO unificacionResponsableDTO) {
        this.unificacionResponsableDTO = unificacionResponsableDTO;
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPersonaOrganismo == null) ? 0 : idPersonaOrganismo.hashCode());
        result = prime * result + ((idResponsable == null) ? 0 : idResponsable.hashCode());
        result = prime * result + ((idTipoIdentiResponsable == null) ? 0 : idTipoIdentiResponsable.hashCode());
        result = prime * result + ((idTipoResponsableFormulario == null) ? 0 : idTipoResponsableFormulario.hashCode());
        result = prime * result + ((identificacionResponsable == null) ? 0 : identificacionResponsable.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime
                * result
                + ((organismoTransitoDTO != null && organismoTransitoDTO.getCodigoOrganismo() != null) ? organismoTransitoDTO
                        .getCodigoOrganismo().hashCode() : 0);
        result = prime
                * result
                + ((responsableFormulario != null && responsableFormulario.getId() != null) ? responsableFormulario
                        .getId().hashCode() : 0);
        result = prime
                * result
                + ((unificacionResponsableDTO != null && unificacionResponsableDTO.getId() != null) ? unificacionResponsableDTO
                        .getId().hashCode() : 0);
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
        ResponsableFormularioFL other = (ResponsableFormularioFL) obj;
        if (idPersonaOrganismo == null) {
            if (other.idPersonaOrganismo != null) {
                return false;
            }
        } else if (!idPersonaOrganismo.equals(other.idPersonaOrganismo)) {
            return false;
        }
        if (idResponsable == null) {
            if (other.idResponsable != null) {
                return false;
            }
        } else if (!idResponsable.equals(other.idResponsable)) {
            return false;
        }
        if (idTipoIdentiResponsable == null) {
            if (other.idTipoIdentiResponsable != null) {
                return false;
            }
        } else if (!idTipoIdentiResponsable.equals(other.idTipoIdentiResponsable)) {
            return false;
        }
        if (idTipoResponsableFormulario == null) {
            if (other.idTipoResponsableFormulario != null) {
                return false;
            }
        } else if (!idTipoResponsableFormulario.equals(other.idTipoResponsableFormulario)) {
            return false;
        }
        if (identificacionResponsable == null) {
            if (other.identificacionResponsable != null) {
                return false;
            }
        } else if (!identificacionResponsable.equals(other.identificacionResponsable)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (organismoTransitoDTO == null) {
            if (other.organismoTransitoDTO != null) {
                return false;
            }
        } else if (!organismoTransitoDTO.equals(other.organismoTransitoDTO)) {
            return false;
        }
        if (responsableFormulario == null) {
            if (other.responsableFormulario != null) {
                return false;
            }
        } else if (!responsableFormulario.equals(other.responsableFormulario)) {
            return false;
        }
        if (unificacionResponsableDTO == null) {
            if (other.unificacionResponsableDTO != null) {
                return false;
            }
        } else if (!unificacionResponsableDTO.equals(other.unificacionResponsableDTO)) {
            return false;
        }
        return true;
    }

}
