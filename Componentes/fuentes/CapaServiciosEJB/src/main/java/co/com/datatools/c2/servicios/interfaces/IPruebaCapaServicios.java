package co.com.datatools.c2.servicios.interfaces;

import javax.ejb.Remote;

@Remote
public interface IPruebaCapaServicios {
	
	public String mensaje(String mensaje);

	/**
	 * Definicion del nombre de servicio
	 */
	public static final String NOMBRE_SERVICIO = "IPruebaCapaServicios";
}
