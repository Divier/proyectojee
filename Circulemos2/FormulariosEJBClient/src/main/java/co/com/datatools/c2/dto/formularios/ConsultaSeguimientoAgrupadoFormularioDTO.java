package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Consulta seguimiento formularios agrupado
 * 
 * @author giovanni.velandia
 * 
 */
public class ConsultaSeguimientoAgrupadoFormularioDTO extends ConsultaSeguimientoFormularioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private TipoResponsableFormularioDTO tipoResponsableFormularioDTO;
    private String numeroInicialFormulario;
    private String numeroFinalFormulario;
    private TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO;
    private String numeroIdentificacion;
    private Integer codigoOrganismoResponsable;

    public ConsultaSeguimientoAgrupadoFormularioDTO() {
        // TODO Auto-generated constructor stub
    }

    public TipoResponsableFormularioDTO getTipoResponsableFormularioDTO() {
        return tipoResponsableFormularioDTO;
    }

    public void setTipoResponsableFormularioDTO(TipoResponsableFormularioDTO tipoResponsableFormularioDTO) {
        this.tipoResponsableFormularioDTO = tipoResponsableFormularioDTO;
    }

    public String getNumeroInicialFormulario() {
        return numeroInicialFormulario;
    }

    public void setNumeroInicialFormulario(String numeroInicialFormulario) {
        this.numeroInicialFormulario = numeroInicialFormulario;
    }

    public String getNumeroFinalFormulario() {
        return numeroFinalFormulario;
    }

    public void setNumeroFinalFormulario(String numeroFinalFormulario) {
        this.numeroFinalFormulario = numeroFinalFormulario;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacionPersonaDTO() {
        return tipoIdentificacionPersonaDTO;
    }

    public void setTipoIdentificacionPersonaDTO(TipoIdentificacionPersonaDTO tipoIdentificacionPersonaDTO) {
        this.tipoIdentificacionPersonaDTO = tipoIdentificacionPersonaDTO;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Integer getCodigoOrganismoResponsable() {
        return codigoOrganismoResponsable;
    }

    public void setCodigoOrganismoResponsable(Integer codigoOrganismoResponsable) {
        this.codigoOrganismoResponsable = codigoOrganismoResponsable;
    }

}
