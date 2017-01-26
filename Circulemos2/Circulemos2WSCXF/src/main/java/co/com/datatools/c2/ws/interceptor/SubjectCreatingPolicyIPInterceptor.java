package co.com.datatools.c2.ws.interceptor;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.jboss.logging.Logger;
import org.jboss.wsf.spi.security.SecurityDomainContext;
import org.jboss.wsf.stack.cxf.security.authentication.SubjectCreatingPolicyInterceptor;

import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.ws.comparendo.utilidades.HelperProcesarMensaje;
import co.com.datatools.c2.ws.utilidades.enumeracion.EnumErrorWS;
import co.com.datatools.c2.ws.utilidades.enumeracion.EnumServicioWS;
import co.com.datatools.seguridad.interfaces.IRAutorizacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;

/**
 * 
 * @author luis.forero(2015-11-12)<br>
 *         luis.forero (mod 2016-01-20)
 */
public class SubjectCreatingPolicyIPInterceptor extends SubjectCreatingPolicyInterceptor {

    /**
     * Bundle donde carga los mensajes
     */
    private static final String BUNDLE_MENSAJES = "co.com.datatools.c2.ws.bundle.Mensajes";

    private static final Logger logger = Logger.getLogger(SubjectCreatingPolicyIPInterceptor.class.getName());

    private transient IRAutorizacion autorizacionEjb;

    public SubjectCreatingPolicyIPInterceptor() {
        super();
    }

    @Override
    protected Subject createSubject(SecurityDomainContext sdc, String name, String password, boolean isDigest,
            String nonce, String creationTime, Message msg) {
        HttpServletRequest request = (HttpServletRequest) msg.get(AbstractHTTPDestination.HTTP_REQUEST);
        String ipAddress = request.getRemoteAddr();
        Subject subject = null;
        ServletContext context = request.getServletContext();

        String nombreAplicacion = context.getInitParameter(ConstantesSeguridad.NombresPropiedades.ID_APLICACION_PROP);
        String nombreUsuario = name;
        msg.put("nombreUsuario", nombreUsuario);
        subject = super.createSubject(sdc, nombreAplicacion + "@" + ipAddress + "#" + name, password, isDigest, nonce,
                creationTime, msg);

        EnumServicioWS servicioWS = Utilidades.buscarElemEnum(EnumServicioWS.class, msg.getExchange().getService()
                .getName().getLocalPart());
        if (!getAutorizacionEjb().esRecursoPermitidoUsuario(nombreAplicacion, nombreUsuario,
                servicioWS.getNombreRecursoServicio())) {
            String errMsg = HelperProcesarMensaje.getMensaje(
                    ResourceBundle.getBundle(BUNDLE_MENSAJES, Locale.getDefault()),
                    EnumErrorWS.RECURSO_NO_PERMITIDO.getLlave());
            HelperProcesarMensaje.escribirLogServidor(nombreUsuario, msg, errMsg);
            // TODO manejo de locale para el cliente
            errMsg = HelperProcesarMensaje.getMensaje(ResourceBundle.getBundle(BUNDLE_MENSAJES, Locale.getDefault()),
                    EnumErrorWS.AUTENTICACION_FALLIDA.getLlave(), nombreUsuario);
            throw new SecurityException(new Throwable(errMsg));
        }

        return subject;

    }

    private static final String AUTORIZACION_EJB = "AutorizacionEJB!co.com.datatools.seguridad.interfaces.IRAutorizacion";
    private String ubicacionJarSeguridadEjb;

    public IRAutorizacion getAutorizacionEjb() {
        if (autorizacionEjb == null) {
            try {
                Properties propiedades = new Properties();
                propiedades.load(IRAutorizacion.class.getResourceAsStream("/artefacto.properties"));
                String version = propiedades.getProperty("version");
                logger.debug("Version= " + version);
                ubicacionJarSeguridadEjb = "SeguridadEAR-" + version + "/" + "SeguridadEJB-" + version + "/";

            } catch (IOException e) {
                logger.info("Error cargando el archivo de propiedades " + e.getMessage());
            }
            autorizacionEjb = lookupEjb(IRAutorizacion.class, ubicacionJarSeguridadEjb + AUTORIZACION_EJB);
        }
        return autorizacionEjb;
    }

    /**
     * Lookup JNDI un EJB
     * 
     * @param tipo
     *            Interfaz del ejb a buscar
     * @param jndiName
     *            nombre JNDI del ejb a buscar, ver formato en links adjuntos
     * @return objeto encontrado null si no encuentra nada
     * @see <a href="https://docs.jboss.org/author/display/AS71/Remote+EJB+invocations+via+JNDI+-+EJB+client+API+or+remote-naming+project">jBoss
     *      Doc</a><br>
     *      <a href="https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+server+instance">jBoss Doc</a>
     */
    protected <T> T lookupEjb(Class<T> tipo, String jndiName) {
        logger.info("Nueva Instancia del Managed Bean:" + SubjectCreatingPolicyIPInterceptor.class.getName());
        try {
            final Hashtable<String, String> props = new Hashtable<>();
            // setup the ejb: namespace URL factory
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            // create the InitialContext
            final Context context = new javax.naming.InitialContext(props);
            // lookup the bean
            @SuppressWarnings("unchecked")
            T interfazEjb = (T) context.lookup("ejb:" + jndiName);
            return interfazEjb;
        } catch (NamingException e) {
            throw new RuntimeException("Error localizando Jndi EJB", e);
        }
    }
}
