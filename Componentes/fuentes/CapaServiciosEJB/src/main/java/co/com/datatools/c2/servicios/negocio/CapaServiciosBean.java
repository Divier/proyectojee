package co.com.datatools.c2.servicios.negocio;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import co.com.datatools.c2.servicios.interfaces.IServiceLocator;
import co.com.datatools.c2.servicios.utilidades.ConstantesCapaServicios;

/**
 * Este clase es usada para que los proyectos puedan obtener la referencia de la interface remota de la capa de Servicios y a traves de ella puedan acceder a todos los 
 * servicios del proyecto Circulemos2 que requieran comunicacion remota
 * @author luis.martinez
 *
 */
public class CapaServiciosBean {

	private final static Logger logger = Logger
			.getLogger(CapaServiciosBean.class.getName());

	/**
	 * Ejb con los servicios de autenticacion del usuario
	 */
	private static transient IServiceLocator serviceLocatorEjb;

	private static String ubicacionJarSeguridadEjb;

	/**
	 * Este metodo obtiene el nombre JNDI armado de forma dinamica basado en la
	 * siguiente estructura
	 * CapaServiciosEJB-1.0.0-SNAPSHOT/PruebaServiciosEJB!co
	 * .com.datatools.c2.servicios.interfaces.IPruebaCapaServicios";
	 */
	private static void obtenerNombreJNDICapaServicios() {
		try {
			Properties propiedades = new Properties();
			propiedades.load(CapaServiciosBean.class
					.getResourceAsStream("/config.properties"));

			String version = propiedades.getProperty("version");

			ubicacionJarSeguridadEjb = "/"
					+ ConstantesCapaServicios.NOMBRE_MODULO_CAPA_SERVICIOS
					+ "-" + version + "/"
					+ ServiceLocatorEJB.class.getSimpleName() + "!"
					+ IServiceLocator.class.getName();

		} catch (IOException e) {
			logger.info("Error cargando el archivo de propiedades "
					+ e.getMessage());
		}
	}

	/**
	 * Este metodo obtiene el nombre JNDI de la capa de servicios
	 * ejb:/CapaServiciosEJB
	 * -1.0.0-SNAPSHOT/ServiceLocatorEJB!co.com.datatools.c2
	 * .servicios.interfaces.IServiceLocator
	 * 
	 * @param jndiName
	 * @return Objeto con el nombre JNDI
	 * @throws NamingException
	 */
	private static Object lookupRemoteEJB(String jndiName)
			throws NamingException {

		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		//jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");

		final Context context = new InitialContext(jndiProperties);

		return (Object) context.lookup("ejb:" + jndiName);
	}
	
	/**
	 * Este obtiene la referencia del nombre JNDI del objeto IServiceLocator
	 * @return IServiceLocator
	 */
	public static IServiceLocator getServiceLocatorEjb() {
		if (serviceLocatorEjb == null) {
			try {
				obtenerNombreJNDICapaServicios();
				serviceLocatorEjb = (IServiceLocator) lookupRemoteEJB(ubicacionJarSeguridadEjb);
			} catch (NamingException ex) {
				throw new IllegalStateException("Cannot connect to bean: "
						+ serviceLocatorEjb + " Reason: " + ex, ex.getCause());
			}
		}
		return serviceLocatorEjb;
	}

}
