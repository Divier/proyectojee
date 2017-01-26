package co.com.datatools.seguridad.mb.general;

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
 * @author Felipe Martinez
 */
@ManagedBean
@SessionScoped
public class AutenticacionWebMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(AutenticacionWebMB.class.getName());

    private static final String SECURITY_JBOSS_EXCEPTION = "org.jboss.security.exception";
    private static final String URL_PRINCIPAL = "/protegido/main?faces-redirect=true";
    private static final String URL_RECUPERAR_PASS = "/publico/envioVinculoPw.xhtml";

    private String ip;
    private String usuario;
    private String password;

    public AutenticacionWebMB() {
        super();
        logger.debug("AutenticacionWebMB.AutenticacionWebMB()");
    }

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

    public void validarLoginException() {
        Exception ex = (Exception) SecurityContextAssociation.getContextInfo(SECURITY_JBOSS_EXCEPTION);
        if (ex != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.fillInStackTrace().getMessage()));
        }
    }

    /**
     * Invalida la session e invoca al ManagedBean(en modulo EJB: SeguridadClientEJB) para limpiar los datos del usuario logueado
     * 
     * @param event
     */
    public void cerrarSesion() {
        invalidateSession();
        return;
    }

    /**
     * Se encarga de redirigir al flujo para solicitar una contraseña
     */
    public String recuperarContraseña() {
        return URL_RECUPERAR_PASS;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
