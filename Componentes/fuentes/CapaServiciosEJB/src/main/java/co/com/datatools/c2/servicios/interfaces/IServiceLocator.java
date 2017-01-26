package co.com.datatools.c2.servicios.interfaces;

import javax.ejb.Remote;

@Remote
public interface IServiceLocator {
	
/**
	 * Este metodo dado un nombre de servicio asocia y busca el nombre del servicio JNDI 
	 * @param nombreServicio
	 * @return Objeto JNDI del servicio solicitado
	 */
	public Object obtenerNombreJNDI(String nombreServicio);

}
