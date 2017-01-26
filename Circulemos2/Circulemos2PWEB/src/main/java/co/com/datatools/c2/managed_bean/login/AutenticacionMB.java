package co.com.datatools.c2.managed_bean.login;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.jboss.security.SecurityContextAssociation;

import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Clase encargada de gestionar las operaciones relacionadas con la autenticacion
 * 
 * @author claudia.rodriguez giovanni.velandia (mod 2015-08-12)
 */
@ManagedBean
@SessionScoped
public class AutenticacionMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(AutenticacionMB.class.getName());

    private static final String SECURITY_JBOSS_EXCEPTION = "org.jboss.security.exception";
    private static final String URL_PRINCIPAL = "/protegido/main?faces-redirect=true";
    private static final String URL_RECUPERAR_PASS = "/publico/enviarVinculoPw.xhtml";

    private String ip;
    private String usuario;
    private String password;

    public AutenticacionMB() {
        super();
        logger.debug("AutenticacionMB::AutenticacionMB()");
    }

    @PostConstruct
    public void init() {
        logger.debug("AutenticacionMB::init()");
    }

    /**
     * Metodo para autenticarse por JAAS login
     * 
     * @return
     */
    public String login() {
        HttpServletRequest request = getHttpRequest();
        ip = request.getRemoteAddr();
        try {

            ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                    .getContext();

            String nombreAplicacion = context
                    .getInitParameter(ConstantesSeguridad.NombresPropiedades.ID_APLICACION_PROP);
            request.login(nombreAplicacion + "@" + ip + "#" + usuario, password);
            return URL_PRINCIPAL;
        } catch (ServletException e) {
            validarLoginException();
            return null;
        }

    }

    /**
     * Manejo de la excepciones desde el modulo de JBoos security
     */
    public void validarLoginException() {
        Exception ex = (Exception) SecurityContextAssociation.getContextInfo(SECURITY_JBOSS_EXCEPTION);
        if (ex != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.fillInStackTrace().getMessage()));
        }
    }

    /**
     * Ivalida la session e invoca al ManagedBean(en modulo EJB: SeguridadClientEJB) para limpiar los datos del usuario logueado
     * 
     */
    public void cerrarSesion() {
        logger.debug("AutenticacionMB::cerrarSesion()");
        invalidateSession();
        return;
    }

    /**
     * Se encarga de redirigir al flujo para solicitar una contraseña
     */
    public String recuperarContraseña() {
        return URL_RECUPERAR_PASS;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static String getUrlPrincipal() {
        return URL_PRINCIPAL;
    }

    public static String getSecurityJbossException() {
        return SECURITY_JBOSS_EXCEPTION;
    }
}
