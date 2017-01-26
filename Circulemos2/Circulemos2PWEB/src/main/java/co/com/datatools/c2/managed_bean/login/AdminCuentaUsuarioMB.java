package co.com.datatools.c2.managed_bean.login;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * MangedBean encargado de gestionar la pagina de cambio de password en la autenticacion y la modificacion de cuenta del usuario
 * 
 * @author claudia.rodriguez
 * @author giovanni.velandia (mod 08-03-2016)
 */
@ManagedBean
@SessionScoped
public class AdminCuentaUsuarioMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(AdminCuentaUsuarioMB.class.getName());

    private static final String NOMBRE_BUNDLE_LOGIN = "mensajesIngreso";

    private String pwActual;
    private String pwNuevo;
    private String confirmacionPwNuevo;
    private String login;
    private boolean pwVencido;
    private boolean ldap;
    private boolean datosCargados;

    private String email;
    private String confirmacionEmail;

    @EJB
    private IRAutenticacion autenticacionEjb;

    /**
     * Interfaz del EJB ParametrosSeguridadEJB necesario para consultar los parametros relacionados con las politicas de la contraseña
     */
    @EJB
    private IRParametrosSeguridad parametrosSeguridadEjb;

    private List<String> politicas;

    private boolean mostrarFormulario = true;

    private UsuarioDto usuarioDto;

    public AdminCuentaUsuarioMB() {
        logger.debug("AdminCuentaUsuarioMB::AdminCuentaUsuarioMB()");
    }

    @PostConstruct
    public void init() {
        logger.debug("AdminCuentaUsuarioMB::init()");
        cargarPoliticasPw();
    }

    /**
     * Consulta las politicas de seguridad parametrizadas para la contraseña y las agrega a una lista para ser mostradas en la interfaz cuando se va a
     * ingresar una nueva contraseña
     */
    public void cargarPoliticasPw() {
        logger.debug("AdminCuentaUsuarioMB::cargarPoliticasPw()");
        politicas = new ArrayList<String>();
        // Cargar politicas del password
        // Politica de longitud
        String longitudMinima = parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_LONGITUD_MIN);
        String longitudMaxima = parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_LONGITUD_MAX);
        String politicaLongitud = getBundle(NOMBRE_BUNDLE_LOGIN).getString("politica_pw_longitud");
        politicas.add(MessageFormat.format(politicaLongitud, longitudMinima, longitudMaxima));
        // Contener letras
        String obligaLetras = parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_LETRAS);
        if (obligaLetras.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
            politicas.add(getBundle(NOMBRE_BUNDLE_LOGIN).getString("politica_pw_letras"));
        }
        // Politica obliga mayusculas
        String obligaMayusculas = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_MAYUSCULAS);
        if (obligaMayusculas.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
            politicas.add(getBundle(NOMBRE_BUNDLE_LOGIN).getString("politica_pw_mayusculas"));
        }
        // Politica obliga numeros
        String obligaNumeros = parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_NUMEROS);
        if (obligaNumeros.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
            politicas.add(getBundle(NOMBRE_BUNDLE_LOGIN).getString("politica_pw_numeros"));
        }
        // Politica obliga caracteres especiales
        String obligaCarEspeciales = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_CARACTERES_ESPECIALES);
        if (obligaCarEspeciales.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
            politicas.add(getBundle(NOMBRE_BUNDLE_LOGIN).getString("politica_pw_especiales"));
            String politicaCaracteres = getBundle(NOMBRE_BUNDLE_LOGIN).getString("pw_caracteres_especiales");
            String carEspeciales = parametrosSeguridadEjb
                    .consultarValorParametroSeguridad(EnumParametro.PW_CARACTERES_ESPECIALES);
            politicas.add(MessageFormat.format(politicaCaracteres, carEspeciales));
        }
    }

    /**
     * Invoca al AutenticacionEjb para realizar el cambio de password de un usuario
     */
    public boolean modificarPassword() {
        logger.debug("AdminCuentaUsuarioMB::modificarPassword()");
        boolean modificaPw = false;
        try {
            // Valida que la Nueva contraseña y la Confirmacion de la nueva contraseña sean exactamente iguales(FA3)
            if (!pwNuevo.equals(confirmacionPwNuevo)) {
                addErrorMessage(NOMBRE_BUNDLE_LOGIN, "mensaje_confirmacion_pw");
            } else {
                autenticacionEjb.cambiarPassword(login, pwActual, pwNuevo);
                addInfoMessage(NOMBRE_BUNDLE_LOGIN, "mensaje_password_cambiado");
                modificaPw = true;
                mostrarFormulario = false;
            }
        } catch (SeguridadException e) {
            addErrorMessage(NOMBRE_BUNDLE_LOGIN, e.getErrorInfo().getCodigoError());
        }
        return modificaPw;
    }

    /**
     * Invoca al AutenticacionEjb para realizar la modificacion del correo electronico del usuario
     */
    public void modificarCorreo() {
        logger.debug("AdminCuentaUsuarioMB::modificarCorreo()");
        try {
            if (!confirmacionEmail.equals(email)) {
                addErrorMessage(NOMBRE_BUNDLE_LOGIN, "mensaje_correo_diferente");
            } else {
                autenticacionEjb.actualizarCorreoElectronico(login, email);
                addInfoMessage(NOMBRE_BUNDLE_LOGIN, "mensaje_correo_cambiado");
            }

        } catch (SeguridadException e) {
            addErrorMessage(NOMBRE_BUNDLE_LOGIN, e.getErrorInfo().getCodigoError());
        }

        // Se limpia los datos
        email = null;
        confirmacionEmail = null;
    }

    /**
     * Obtiene del managed bean: AutenticacionMB, los datos del usuario autenticado para realizar la modificacion de la cuenta del usuario
     */
    public void cargarDatosUsuario() {
        logger.debug("AdminCuentaUsuarioMB::cargarDatosUsuario()");
        if (!datosCargados) {

            UsuarioPersonaDTO usuarioPersonaDTO = findSessionObject(
                    ConstantesManagedBean.CLASE_OBJ_USUARIO_PERSONA_AUTENTICADA,
                    ConstantesManagedBean.OBJ_USUARIO_PERSONA_AUTENTICA);
            login = super.getLogin();
            ldap = usuarioPersonaDTO.getUsuario().isAutenticacionConLdap();
            datosCargados = true;
        }
    }

    /**
     * Me todo que se encarga de cerrar la sesion en jaas
     * 
     * @author giovanni.velandia
     */
    public void logout() {
        logger.debug("AdminCuentaUsuarioMB::logout()");
        HttpServletRequest httpRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        try {
            httpRequest.logout();
        } catch (ServletException e) {
            logger.error("Error ServletException logout: " + e);
        }
    }

    public String getPwActual() {
        return pwActual;
    }

    public void setPwActual(String pwActual) {
        this.pwActual = pwActual;
    }

    public String getPwNuevo() {
        return pwNuevo;
    }

    public void setPwNuevo(String pwNuevo) {
        this.pwNuevo = pwNuevo;
    }

    public String getConfirmacionPwNuevo() {
        return confirmacionPwNuevo;
    }

    public void setConfirmacionPwNuevo(String confirmacionPwNuevo) {
        this.confirmacionPwNuevo = confirmacionPwNuevo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isPwVencido() {
        return pwVencido;
    }

    public void setPwVencido(boolean pwVencido) {
        this.pwVencido = pwVencido;
    }

    public List<String> getPoliticas() {
        cargarPoliticasPw();
        return politicas;
    }

    public void setPoliticas(List<String> politicas) {
        this.politicas = politicas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLdap() {
        return ldap;
    }

    public void setLdap(boolean ldap) {
        this.ldap = ldap;
    }

    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }

    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }

    public String getConfirmacionEmail() {
        return confirmacionEmail;
    }

    public void setConfirmacionEmail(String confirmacionEmail) {
        this.confirmacionEmail = confirmacionEmail;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

}
