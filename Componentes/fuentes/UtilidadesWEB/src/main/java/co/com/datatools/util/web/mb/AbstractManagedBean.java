package co.com.datatools.util.web.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Clase abstracta que funciona como interfaz comun de los Managed Bean de la aplicacion, provee metodos de acceso a los contextos web de session y
 * application
 * 
 * @author Felipe Martinez
 */
public abstract class AbstractManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    protected void invalidateSession() {
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        try {
            request.getSession().invalidate();
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the object bound with the specified name in this session, or null if no object is bound under the name.
     * 
     * @param objType
     *            returning object type
     * @param name
     *            a string specifying the name of the object
     * @return the object with the specified name, otherwise null
     */
    @SuppressWarnings("unchecked")
    protected <T> T findSessionObject(Class<T> objType, String name) {
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        final Object attribute = request.getSession().getAttribute(name);
        if (attribute != null) {
            return (T) attribute;
        }
        return null;
    }

    /**
     * Removes the object bound with the specified name from this session. If the session does not have an object bound with the specified name, this
     * method does nothing and return null.
     * 
     * @param name
     *            a string specifying the name of the object
     * @return the object with the specified name, otherwise null
     */
    protected Object removeSessionObject(String name) {
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        final Object attribute = request.getSession().getAttribute(name);
        request.getSession().removeAttribute(name);
        return attribute;
    }

    /**
     * Binds an object to this session, using the name specified. If an object of the same name is already bound to the session, the object is
     * replaced.
     * 
     * @param name
     *            the name to which the object is bound; cannot be null
     * @param value
     *            the object to be bound
     */
    protected void addSessionObject(String name, Object value) {
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        request.getSession().setAttribute(name, value);
    }

    /**
     * Returns the servlet container attribute with the given name, or null if there is no attribute by that name.
     * 
     * @param objType
     *            returning object type
     * @param name
     *            a String specifying the name of the attribute
     * @return an Object containing the value of the attribute, or null if no attribute exists matching the given name
     */
    @SuppressWarnings("unchecked")
    protected <T> T findApplicationObject(Class<T> objType, String name) {
        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        final Object attribute = context.getAttribute(name);
        if (attribute != null) {
            return (T) attribute;
        }
        return null;
    }

    /**
     * Removes the attribute with the given name from this ServletContext, or null if there is no attribute by that name.
     * 
     * @param name
     *            a String specifying the name of the attribute to be removed
     * @return an Object containing the value of the attribute, or null if no attribute exists matching the given name
     */
    protected Object removeApplicationObject(String name) {
        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        final Object attribute = context.getAttribute(name);
        context.removeAttribute(name);
        return attribute;
    }

    /**
     * Binds an object to a given attribute name in this ServletContext. If the name specified is already used for an attribute, this method will
     * replace the attribute with the new to the new attribute.
     * 
     * @param name
     *            a String specifying the name of the attribute
     * @param value
     *            an Object representing the attribute to be bound
     */
    protected void addApplicationObject(String name, Object value) {
        final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        context.setAttribute(name, value);
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

    /**
     * Obtener el {@link HttpServletRequest} actual a traves del {@link FacesContext}
     * 
     * @return Objeto HttpServletRequest
     */
    protected HttpServletRequest getHttpRequest() {
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        return request;
    }

    /**
     * Obtener el {@link ServletContext} actual a traves del {@link FacesContext}
     * 
     * @return Objeto ServletContext
     */
    protected ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     * Obtiene un recurso a traves del nombre
     * 
     * @param nombreArchivo
     *            nombre del recurso a buscar
     * @return recurso encontrado, null si no existe
     */
    public ResourceBundle getBundle(String nombreArchivo) {
        return FacesContext.getCurrentInstance().getApplication()
                .getResourceBundle(FacesContext.getCurrentInstance(), nombreArchivo);
    }

    /**
     * Obtiene el contexto de faces
     * 
     * @return instancia del contexto de faces
     */
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public String getLogin() {
        String login = getHttpRequest().getUserPrincipal().getName();
        return login.substring(login.indexOf("#") + 1);
    }

    /**
     * Agrega al FacesContext un mensaje Info sin titulo
     * 
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     */
    public void addInfoMessage(String nombreBundle, String nombreMensaje) {
        getFacesContext().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "", getBundle(nombreBundle).getString(nombreMensaje)));
    }

    /**
     * Agrega al FacesContext un mensaje Error sin titulo
     * 
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     */
    public void addErrorMessage(String nombreBundle, String nombreMensaje) {
        getFacesContext().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getBundle(nombreBundle).getString(nombreMensaje)));
    }

    /**
     * Agrega al FacesContext un mensaje de error que se desea mostrar para un componente con ID enviado
     * 
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     * @param idComponente
     *            id del componente para el cual se desplegara el mensaje
     */
    public void addLocatedErrorMessage(String nombreBundle, String nombreMensaje, String idComponente) {
        getFacesContext().addMessage(idComponente,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getBundle(nombreBundle).getString(nombreMensaje)));
    }

    /**
     * Agrega al FacesContext un mensaje Warning sin titulo
     * 
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     */
    public void addWarningMessage(String nombreBundle, String nombreMensaje) {
        getFacesContext().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "", getBundle(nombreBundle).getString(nombreMensaje)));
    }

    /**
     * Agrega al FacesContext un mensaje Info con titulo
     * 
     * @param nombreBundleTitulo
     *            nombre del bundle donde esta el titulo del mensaje
     * @param nombreMensajeTitulo
     *            id del titulo dentro del bundle
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     */
    public void addInfoMessage(String nombreBundleTitulo, String nombreMensajeTitulo, String nombreBundle,
            String nombreMensaje) {
        getFacesContext().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle(nombreBundleTitulo).getString(
                        nombreMensajeTitulo), getBundle(nombreBundle).getString(nombreMensaje)));
    }

    /**
     * Agrega al FacesContext un mensaje Error con titulo
     * 
     * @param nombreBundleTitulo
     *            nombre del bundle donde esta el titulo del mensaje
     * @param nombreMensajeTitulo
     *            id del titulo dentro del bundle
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     */
    public void addErrorMessage(String nombreBundleTitulo, String nombreMensajeTitulo, String nombreBundle,
            String nombreMensaje) {
        getFacesContext().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundle(nombreBundleTitulo).getString(
                        nombreMensajeTitulo), getBundle(nombreBundle).getString(nombreMensaje)));
    }

    /**
     * Agrega al FacesContext un mensaje Warning con titulo
     * 
     * @param nombreBundleTitulo
     *            nombre del bundle donde esta el titulo del mensaje
     * @param nombreMensajeTitulo
     *            id del titulo dentro del bundle
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     */
    public void addWarningMessage(String nombreBundleTitulo, String nombreMensajeTitulo, String nombreBundle,
            String nombreMensaje) {
        getFacesContext().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, getBundle(nombreBundleTitulo).getString(
                        nombreMensajeTitulo), getBundle(nombreBundle).getString(nombreMensaje)));
    }

    /**
     * Agrega al FacesContext un mensaje Info con titulo y mensaje con parametros
     * 
     * @param nombreBundleTitulo
     *            nombre del bundle donde esta el titulo del mensaje
     * @param nombreMensajeTitulo
     *            id del titulo dentro del bundle
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     * @param Object
     *            serie de parametros q se agregan a un mensaje formateado {@link MessageFormat#format(String, Object...)}
     */
    public void addInfoMessage(String nombreBundleTitulo, String nombreMensajeTitulo, String nombreBundle,
            String nombreMensaje, Object... argumentos) {
        getFacesContext().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle(nombreBundleTitulo).getString(
                        nombreMensajeTitulo), MessageFormat.format(getBundle(nombreBundle).getString(nombreMensaje),
                        argumentos)));
    }

    /**
     * Agrega al FacesContext un mensaje Error con titulo y mensaje con parametros
     * 
     * @param nombreBundleTitulo
     *            nombre del bundle donde esta el titulo del mensaje
     * @param nombreMensajeTitulo
     *            id del titulo dentro del bundle
     * @param nombreBundle
     *            nombre del bundle donde esta el mensaje
     * @param nombreMensaje
     *            id del mensaje dentro del bundle
     * @param Object
     *            serie de parametros q se agregan a un mensaje formateado {@link MessageFormat#format(String, Object...)}
     */
    public void addErrorMessage(String nombreBundleTitulo, String nombreMensajeTitulo, String nombreBundle,
            String nombreMensaje, Object... argumentos) {
        getFacesContext().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundle(nombreBundleTitulo).getString(
                        nombreMensajeTitulo), MessageFormat.format(getBundle(nombreBundle).getString(nombreMensaje),
                        argumentos)));
    }

    /**
     * Obtiene el Locale configurado en el contexto de faces, si no se encuentra contexto para la vista actual, retorna el default locale del sistema
     * 
     * @return locale de faces o default del sistema
     */
    public Locale getContextLocale() {
        final FacesContext fc = FacesContext.getCurrentInstance();
        UIViewRoot root;
        Locale locale;
        Locale defaultLocale = Locale.getDefault();
        locale = defaultLocale;
        if (null != (root = fc.getViewRoot())) {
            if (null == (locale = root.getLocale())) {
                locale = defaultLocale;
            }
        }
        assert (null != locale);
        return locale;
    }
}
