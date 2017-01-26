package co.com.datatools.seguridad.mb.general;

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

import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.util.ConstantesManagedBean;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * MangedBean encargado de gestionar la pagina de cambio de contraseña en la autenticacion y la modificacion de cuenta del usuario
 * 
 * @author claudia.rodriguez
 * @author giovanni.velandia (mod 08-03-2016)
 * 
 */
@ManagedBean
@SessionScoped
public class CambioContrasenaMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(CambioContrasenaMB.class.getName());

    private static final String NOMBRE_BUNDLE_INGRESO = "mensajesIngreso";

    @EJB
    private IRAutenticacion autenticacionEjb;

    @EJB
    private IRParametrosSeguridad parametrosSeguridadEjb;

    private String pwActual;
    private String pwNuevo;
    private String confirmacionPwNuevo;
    private String login;
    private boolean pwVencido;
    private List<String> politicas;
    private boolean mostrarFormulario = true;
    private String mensajeCambioExitoso;

    public CambioContrasenaMB() {
        logger.debug("CambioContrasenaMB::CambioContrasenaMB");

    }

    /**
     * Consulta las politicas de seguridad parametrizadas para la contraseña y las agrega a una lista para ser mostradas en la interfaz cuando se va a
     * ingresar una nueva contraseña
     */
    @PostConstruct
    public void cargarPoliticasPw() {
        politicas = new ArrayList<String>();
        // Cargar politicas del password
        // Politica de longitud
        String longitudMinima = parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_LONGITUD_MIN);
        String longitudMaxima = parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_LONGITUD_MAX);
        String politicaLongitud = getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString(
                "politica_pw_longitud");
        politicas.add(MessageFormat.format(politicaLongitud, longitudMinima, longitudMaxima));
        // Contener letras
        String obligaLetras = parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_LETRAS);
        if (obligaLetras.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
            politicas.add(getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("politica_pw_letras"));
        }
        // Politica obliga mayusculas
        String obligaMayusculas = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_MAYUSCULAS);
        if (obligaMayusculas.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
            politicas.add(getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("politica_pw_mayusculas"));
        }
        // Politica obliga numeros
        String obligaNumeros = parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_NUMEROS);
        if (obligaNumeros.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
            politicas.add(getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("politica_pw_numeros"));
        }
        // Politica obliga caracteres especiales
        String obligaCarEspeciales = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_CARACTERES_ESPECIALES);
        if (obligaCarEspeciales.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI)) {
            politicas.add(getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString("politica_pw_especiales"));
            String politicaCaracteres = getBundle(ConstantesManagedBean.NOMBRE_BUNDLE_GENERAL).getString(
                    "pw_caracteres_especiales");
            String carEspeciales = parametrosSeguridadEjb
                    .consultarValorParametroSeguridad(EnumParametro.PW_CARACTERES_ESPECIALES);
            politicas.add(MessageFormat.format(politicaCaracteres, carEspeciales));
        }
    }

    /**
     * Invoca al EJB que realiza el cambio de password de un usuario
     */
    public boolean cambiarPassword() {
        boolean modificaPw = false;
        try {
            // Valida que la Nueva contraseña y la Confirmacion de la nueva contraseña sean exactamente iguales(FA3)
            if (!pwNuevo.equals(confirmacionPwNuevo)) {
                addErrorMessage(NOMBRE_BUNDLE_INGRESO, "mensaje_confirmacion_pw");
            } else {
                UsuarioDto usuarioDto = new UsuarioDto();
                usuarioDto.setLogin(login);
                usuarioDto.setPassword(pwActual);
                autenticacionEjb.cambiarPassword(login, pwActual, pwNuevo);
                // mensajeCambioExitoso = getBundle(NOMBRE_BUNDLE_INGRESO).getString("mensaje_password_cambiado");
                addInfoMessage(NOMBRE_BUNDLE_INGRESO, "mensaje_password_cambiado");

                mostrarFormulario = false;
                pwVencido = false;
                modificaPw = true;
            }
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
        return modificaPw;
    }

    /**
     * Me todo que se encarga de cerrar la sesion en jaas
     * 
     * @author giovanni.velandia
     */
    public void logout() {
        logger.debug("CambioContrasenaMB::logout()");
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

    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }

    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }

    public String getMensajeCambioExitoso() {
        return mensajeCambioExitoso;
    }

    public void setMensajeCambioExitoso(String mensajeCambioExitoso) {
        this.mensajeCambioExitoso = mensajeCambioExitoso;
    }

}
