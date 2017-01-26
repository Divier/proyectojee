package co.com.datatools.seguridad.mb.cuenta;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.seguridad.mb.general.CambioContrasenaMB;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Session Bean que gestiona el flujo para funcionalidad "Mi Cuenta"
 * 
 * @author claudia.rodriguez
 */
@ManagedBean
@SessionScoped
public class AdminCuentaMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AdminCuentaMB.class.getName());

    private static final String NOMBRE_BUNDLE_USUARIO = "mensajesUsuario";

    @EJB
    private IRAutenticacion autenticacionEjb;

    @EJB
    private IRUsuario usuarioEjb;

    @ManagedProperty(value = "#{cambioContrasenaMB}")
    private CambioContrasenaMB cambioContrasenaMB;

    private String loginUsuario;
    private String nombre;
    private String correo;
    private String confirmacionCorreo;
    private boolean permiteCambioPw;

    public AdminCuentaMB() {
        logger.debug("AdminCuentaMB::AdminCuentaMB");
    }

    /**
     * Invoca al EJB que realiza el cambio de la cuenta de correo del usuario
     */
    public boolean cambiarCorreo() {
        boolean cambioCorreo = false;
        try {
            if (!confirmacionCorreo.equals(correo)) {
                addErrorMessage(NOMBRE_BUNDLE_USUARIO, "mensaje_correo_diferente");
            } else {
                autenticacionEjb.actualizarCorreoElectronico(loginUsuario, correo);
                addInfoMessage(NOMBRE_BUNDLE_USUARIO, "mensaje_correo_cambiado");
                cambioCorreo = true;
                correo = "";
                confirmacionCorreo = "";
            }
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
        return cambioCorreo;
    }

    /**
     * Carga en la interfaz los datos de la cuenta del usuario que esta autenticado
     */
    @PostConstruct
    public void cargarDatosCuenta() {
        // consultar los datos del usuario autenticado por si ese usuario que esta en el MB en la sesion, ha sido modificado por Administracion de
        // usuarios o por administracion de cuenta
        UsuarioDetalleDto usuario = usuarioEjb.consultarUsuario(getLogin(), true);
        loginUsuario = usuario.getLogin();
        nombre = usuario.getNombres() + " " + usuario.getApellidos();
        cambioContrasenaMB.setLogin(loginUsuario);
        if (usuario.isAutenticacionConLdap())
            permiteCambioPw = false;
        else
            permiteCambioPw = true;
    }

    public boolean isPermiteCambioPw() {
        return permiteCambioPw;
    }

    public void setPermiteCambioPw(boolean permiteCambioPw) {
        this.permiteCambioPw = permiteCambioPw;
    }

    public String getloginUsuario() {
        return loginUsuario;
    }

    public void setloginUsuario(String login) {
        this.loginUsuario = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public CambioContrasenaMB getCambioContrasenaMB() {
        return cambioContrasenaMB;
    }

    public void setCambioContrasenaMB(CambioContrasenaMB cambioContrasenaMB) {
        this.cambioContrasenaMB = cambioContrasenaMB;
    }

    public String getConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public void setConfirmacionCorreo(String confirmacionCorreo) {
        this.confirmacionCorreo = confirmacionCorreo;
    }

}
