package co.com.datatools.c2.dto.formularios;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 09 08:43:13 COT 2015
 */
public class SeguimientoFormularioDTO implements EntidadDtoC2 {
	private static final long serialVersionUID = 1L;
	// --- Atributos
	private Long id;
	private Date fechaMovimiento;
	private Date fechaRegistro;
	private String usuarioRegistro;
	private ResponsableFormularioDTO responsableFormulario;
	private EstadoFormularioDTO estadoFormulario;
	private FormularioDTO formulario;
	private DetalleCambioEstadoDTO detalleCambioEstado;
	private Date fechaAplicacionEstado;

	/**
     * Atributo utilizado en modulo de cambio estado masivo 'NUM_004' para determinar si es o no seleccionado el determinado formulario
     */
    private boolean seleccionado;
	
	// --- Constructor
	public SeguimientoFormularioDTO() {
	}

	public SeguimientoFormularioDTO(Long id) {
		this.id = id;
	}

	// --- Getters-Setters
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaMovimiento() {
		return this.fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public ResponsableFormularioDTO getResponsableFormulario() {
		return this.responsableFormulario;
	}

	public void setResponsableFormulario(ResponsableFormularioDTO responsableFormulario) {
		this.responsableFormulario = responsableFormulario;
	}

	public EstadoFormularioDTO getEstadoFormulario() {
		return this.estadoFormulario;
	}

	public void setEstadoFormulario(EstadoFormularioDTO estadoFormulario) {
		this.estadoFormulario = estadoFormulario;
	}

	public FormularioDTO getFormulario() {
		return this.formulario;
	}

	public void setFormulario(FormularioDTO formulario) {
		this.formulario = formulario;
	}

	public DetalleCambioEstadoDTO getDetalleCambioEstado() {
		return this.detalleCambioEstado;
	}

	public void setDetalleCambioEstado(DetalleCambioEstadoDTO detalleCambioEstado) {
		this.detalleCambioEstado = detalleCambioEstado;
	}

	public Date getFechaAplicacionEstado() {
		return this.fechaAplicacionEstado;
	}

	public void setFechaAplicacionEstado(Date fechaAplicacionEstado) {
		this.fechaAplicacionEstado = fechaAplicacionEstado;
	}
	
	public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((fechaAplicacionEstado == null) ? 0 : fechaAplicacionEstado
						.hashCode());
		result = prime * result
				+ ((fechaMovimiento == null) ? 0 : fechaMovimiento.hashCode());
		result = prime * result
				+ ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result
				+ ((formulario == null) ? 0 : formulario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (seleccionado ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeguimientoFormularioDTO other = (SeguimientoFormularioDTO) obj;
		if (fechaAplicacionEstado == null) {
			if (other.fechaAplicacionEstado != null)
				return false;
		} else if (!fechaAplicacionEstado.equals(other.fechaAplicacionEstado))
			return false;
		if (fechaMovimiento == null) {
			if (other.fechaMovimiento != null)
				return false;
		} else if (!fechaMovimiento.equals(other.fechaMovimiento))
			return false;
		if (fechaRegistro == null) {
			if (other.fechaRegistro != null)
				return false;
		} else if (!fechaRegistro.equals(other.fechaRegistro))
			return false;
		if (formulario == null) {
			if (other.formulario != null)
				return false;
		} else if (!formulario.equals(other.formulario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (seleccionado != other.seleccionado)
			return false;
		return true;
	}
}