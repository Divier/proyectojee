package co.com.datatools.c2.servicios.negocio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.com.datatools.c2.servicios.interfaces.IPruebaCapaServicios;

@Stateless(name = "PruebaServiciosEJB")
@LocalBean
public class PruebaServiciosEJB implements IPruebaCapaServicios {

	@Override
	public String mensaje(String mensaje) {
		String valor ="Esto es una prueba de comunicacion capa de servicios " + mensaje;
		return valor;
	}

}
