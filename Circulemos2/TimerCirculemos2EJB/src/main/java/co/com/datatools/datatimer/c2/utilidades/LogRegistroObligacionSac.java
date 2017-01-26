/**
 * 
 */
package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

/**
 * @author javier.fajardo
 *
 */
public class LogRegistroObligacionSac implements ILogueable {

	private static final long serialVersionUID = 1L;
	private Integer organismoTransito;
    private Date fechaHoraEjecucion;
    private Integer numeroExitosos;
    private Integer numeroFallidos;
    
	public Integer getOrganismoTransito() {
		return organismoTransito;
	}
	
	public void setOrganismoTransito(Integer organismoTransito) {
		this.organismoTransito = organismoTransito;
	}
	
	public Date getFechaHoraEjecucion() {
		return fechaHoraEjecucion;
	}
	
	public void setFechaHoraEjecucion(Date fechaHoraEjecucion) {
		this.fechaHoraEjecucion = fechaHoraEjecucion;
	}

	public Integer getNumeroExitosos() {
		return numeroExitosos;
	}

	public void setNumeroExitosos(Integer numeroExitosos) {
		this.numeroExitosos = numeroExitosos;
	}

	public Integer getNumeroFallidos() {
		return numeroFallidos;
	}

	public void setNumeroFallidos(Integer numeroFallidos) {
		this.numeroFallidos = numeroFallidos;
	}
}
