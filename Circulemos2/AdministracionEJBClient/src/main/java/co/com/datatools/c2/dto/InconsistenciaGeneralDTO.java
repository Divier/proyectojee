package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Inconsistencia de documentos cargados
 * @author ricardo.chavarro
 *
 */
public class InconsistenciaGeneralDTO implements EntidadDtoC2{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String inconsistencia;
	
	public String getInconsistencia() {
		return inconsistencia;
	}
	public void setInconsistencia(String inconsistencia) {
		this.inconsistencia = inconsistencia;
	}
}
