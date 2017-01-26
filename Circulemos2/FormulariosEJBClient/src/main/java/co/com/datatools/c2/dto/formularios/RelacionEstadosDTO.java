package co.com.datatools.c2.dto.formularios;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author divier.casas
 */
public class RelacionEstadosDTO implements EntidadDtoC2 {
    
	private static final long serialVersionUID = 1L;
    // --- Atributos
	private Long id;
	private EstadoFormularioDTO estadoFormularioActual;
	private EstadoFormularioDTO estadoFormularioSiguiente;
    private TipoFormularioDTO tipoFormulario;
    private OrganismoTransitoDTO organismoTranisto;
    
    // --- Constructor
    public RelacionEstadosDTO() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoFormularioDTO getEstadoFormularioActual() {
		return estadoFormularioActual;
	}

	public void setEstadoFormularioActual(EstadoFormularioDTO estadoFormularioActual) {
		this.estadoFormularioActual = estadoFormularioActual;
	}

	public EstadoFormularioDTO getEstadoFormularioSiguiente() {
		return estadoFormularioSiguiente;
	}

	public void setEstadoFormularioSiguiente(
			EstadoFormularioDTO estadoFormularioSiguiente) {
		this.estadoFormularioSiguiente = estadoFormularioSiguiente;
	}

	public TipoFormularioDTO getTipoFormulario() {
		return tipoFormulario;
	}

	public void setTipoFormulario(TipoFormularioDTO tipoFormulario) {
		this.tipoFormulario = tipoFormulario;
	}

	public OrganismoTransitoDTO getOrganismoTranisto() {
		return organismoTranisto;
	}

	public void setOrganismoTranisto(OrganismoTransitoDTO organismoTranisto) {
		this.organismoTranisto = organismoTranisto;
	}
}
