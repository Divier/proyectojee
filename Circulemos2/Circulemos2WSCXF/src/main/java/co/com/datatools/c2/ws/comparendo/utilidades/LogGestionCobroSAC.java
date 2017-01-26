/**
 * 
 */
package co.com.datatools.c2.ws.comparendo.utilidades;

import co.com.datatools.c2.log.ILogueable;

/**
 * 
 * Estructura del log para la notificacion de la gestion de cobro SAC.
 *  
 * @author javier.fajardo
 *
 */
public class LogGestionCobroSAC implements ILogueable {

	private static final long serialVersionUID = 1L;
	
	private String numeroNotificacionCobro;
	
	public LogGestionCobroSAC() { }

	public String getNumeroNotificacionCobro() {
		return numeroNotificacionCobro;
	}

	public void setNumeroNotificacionCobro(String numeroNotificacionCobro) {
		this.numeroNotificacionCobro = numeroNotificacionCobro;
	}
}
