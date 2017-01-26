package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Inconsistencia de los documentos cargados para la consulta de analisis de ubicabilidad
 * @author ricardo.chavarro
 *
 */
public class InconsistenciaAnalisisUbicDTO extends InconsistenciaGeneralDTO implements EntidadDtoC2{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
}
