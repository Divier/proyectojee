package co.com.datatools.c2.servicios;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.com.datatools.c2.servicios.excepciones.CapaServiciosException;
import co.com.datatools.c2.servicios.interfaces.IPruebaCapaServicios;
import co.com.datatools.c2.servicios.interfaces.IServiceLocator;

public class PruebaCapaServicios {
	
	public static void main(String[] args) throws NamingException, CapaServiciosException {
		 
		IServiceLocator ref = null;
	    ref = (IServiceLocator) lookupRemoteEJB();
	    IPruebaCapaServicios mensaje = (IPruebaCapaServicios) ref.obtenerNombreJNDI(IPruebaCapaServicios.class.getName());
	    System.out.println("*****************  " + mensaje.mensaje("objeto") );
	}
	
	
	private static Object lookupRemoteEJB() throws NamingException {

		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.PROVIDER_URL,"remote://localhost:4447");
 
        final Context context = new InitialContext(jndiProperties);
        System.out.println("Looking EJB via JNDI ");
 
        
        String name = "ejb:/CapaServiciosEJB-1.0.0-SNAPSHOT/ServiceLocatorEJB!co.com.datatools.c2.servicios.interfaces.IServiceLocator";
        
        return (Object) context.lookup(name);
    }

}
