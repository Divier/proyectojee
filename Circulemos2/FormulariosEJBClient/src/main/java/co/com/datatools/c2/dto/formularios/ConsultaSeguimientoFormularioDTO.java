package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Consulta seguimiento formulario
 * 
 * @author giovanni.velandia
 * 
 */
public class ConsultaSeguimientoFormularioDTO implements EntidadDtoC2 {
    
	private static final long serialVersionUID = 1L;

    private TipoFormularioDTO tipoFormularioDTO;
    private String NumeroFormulario;
    private OrganismoTransitoDTO organismoTransito;

    public ConsultaSeguimientoFormularioDTO() {
        // TODO Auto-generated constructor stub
    }

    public TipoFormularioDTO getTipoFormularioDTO() {
        return tipoFormularioDTO;
    }

    public void setTipoFormularioDTO(TipoFormularioDTO tipoFormularioDTO) {
        this.tipoFormularioDTO = tipoFormularioDTO;
    }

    public String getNumeroFormulario() {
        return NumeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        NumeroFormulario = numeroFormulario;
    }

	public OrganismoTransitoDTO getOrganismoTransito() {
		return organismoTransito;
	}

	public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
		this.organismoTransito = organismoTransito;
	}
}