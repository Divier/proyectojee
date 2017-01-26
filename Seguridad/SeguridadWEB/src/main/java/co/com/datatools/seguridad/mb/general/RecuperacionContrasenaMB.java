package co.com.datatools.seguridad.mb.general;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.comun.InfoVinculoRecuperacionDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.interfaces.IRParametrosSeguridad;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.seguridad.util.SeguridadErrorHandler;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * MangedBean encargado de gestionar la solicitud de un vinculo de recuperacion de contraseña y la recuperacion de contraseña
 * 
 * @author claudia.rodriguez
 * 
 */
@ManagedBean
@SessionScoped
public class RecuperacionContrasenaMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;

    private boolean mostrarFormulario;
    private boolean mostrarBtnContinuar;

    private String pwNuevo;
    private String confirmacionPwNuevo;
    private String mensaje;

    private String usuario;
    private String correo;

    private boolean vinculoValidado;

    private String keyRecuperacion;

    private String nombreUsuario;

    private final static Logger logger = Logger.getLogger(RecuperacionContrasenaMB.class.getName());

    private static final String NOMBRE_BUNDLE_INGRESO = "mensajesIngreso";
    private static final String URL_PRINCIPAL = "/protegido/main?faces-redirect=true";

    @EJB
    private IRParametrosSeguridad parametrosSeguridadEjb;

    @EJB
    private IRUsuario usuarioEjb;

    @EJB
    private IRAutenticacion autenticacionEjb;

    public RecuperacionContrasenaMB() {
        logger.debug("RecuperacionContrasenaMB::RecuperacionContrasenaMB");
        mostrarFormulario = true;
        vinculoValidado = false;
    }

    @PostConstruct
    public void init() {
        logger.debug("RecuperaContrasenaFL::init()");
        HttpServletRequest request = getHttpRequest();
        String key = request.getParameter(ConstantesSeguridad.NOMBRE_PARAMETRO_KEY_RECUPERACION);
        if (key != null)
            keyRecuperacion = key;
    }

    /**
     * Invoca a AutenticacionEjb para realizar la generacion y envio del vinculo de recuperacion de contraseña para el usuario
     */
    public String enviarVinculo() {
        logger.debug("RecuperacionContrasenaMB::enviarVinculo");
        try {

            String mensajeEnvioCorreo = null;
            InfoVinculoRecuperacionDto infoVinculoRecuperacionDto = new InfoVinculoRecuperacionDto();
            infoVinculoRecuperacionDto.setLogin(nombreUsuario);
            final ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                    .getContext();
            infoVinculoRecuperacionDto
                    .setAplicacion(context.getInitParameter(ConstantesSeguridad.NombresPropiedades.ID_APLICACION_PROP));
            infoVinculoRecuperacionDto.setFlujoRecuperacionPw(ConstantesSeguridad.PAGINA_RESTABLECER_PW);
            infoVinculoRecuperacionDto.setKeyRecuperacion(ConstantesSeguridad.NOMBRE_PARAMETRO_KEY_RECUPERACION);
            autenticacionEjb.enviarVinculoRecuperacion(infoVinculoRecuperacionDto);
            mostrarFormulario = false;

            mensajeEnvioCorreo = getBundle(NOMBRE_BUNDLE_INGRESO).getString("mensaje_envio_correo");
            String horasVigenciaVinculo = parametrosSeguridadEjb
                    .consultarValorParametroSeguridad(EnumParametro.CANTIDAD_HORAS_VIGENCIA_VINCULO_TEMPORAL);
            mensajeEnvioCorreo = MessageFormat.format(mensajeEnvioCorreo, horasVigenciaVinculo);

            @SuppressWarnings("static-access")
            FacesContext fContext = getFacesContext().getCurrentInstance();
            fContext.getExternalContext().getFlash().setKeepMessages(true);
            fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensajeEnvioCorreo));

            removeSessionObject("recuperacionContrasenaMB");

            return URL_PRINCIPAL;
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
        return null;
    }

    /**
     * Invoca a AutenticacionEjb para verificar si el vinculo de recuperacion es valido: es decir que no haya sido previamente utilizado y que no se
     * encuentre vencido
     */
    public void validarVinculo() {
        logger.debug("RecuperacionContrasenaMB::validarVinculo");
        if (!vinculoValidado) {
            mostrarFormulario = true;
            String key = keyRecuperacion;
            boolean solicitudAbierta = autenticacionEjb.verificarSolicitudRecuperacionAbierta(key);
            if (solicitudAbierta) {
                // Validar que no este vencida
                boolean vencida = autenticacionEjb.verificarSolicitudRecuperacionVencida(key);
                if (vencida) {
                    // Mostrar mensaje de solicitud vencida
                    mostrarFormulario = false;
                    mensaje = getBundle(NOMBRE_BUNDLE_INGRESO).getString("mensaje_vinculo_vencido");
                }
                // La solicitud es completamente valida, no debe mostrar mensaje,muestra el formulario de cambio de contraseña
            } else {
                // Mostrar mensaje de solicitud no valida
                mostrarFormulario = false;
                mensaje = getBundle(NOMBRE_BUNDLE_INGRESO).getString("mensaje_vinculo_invalido");
            }
            vinculoValidado = true;
        }
    }

    /**
     * Valida que la nueva contraseña y su confirmacion sean iguales e invoca a AutenticacionEjb para hacer la recuperacion de la contraseña
     */
    public void recuperarContrasena() {
        logger.info("RecuperacionContrasenaMB::recuperarContrasena");
        try {
            if (!pwNuevo.equals(confirmacionPwNuevo)) {
                addErrorMessage(NOMBRE_BUNDLE_INGRESO, "mensaje_confirmacion_pw");
            } else {
                autenticacionEjb.restablecerPassword(usuario, keyRecuperacion, correo, pwNuevo);
                mostrarFormulario = false;
                mensaje = getBundle(NOMBRE_BUNDLE_INGRESO).getString("mensaje_password_cambiado");
                mostrarBtnContinuar = true;
            }
        } catch (SeguridadException e) {
            SeguridadErrorHandler.handleException(e);
        }
    }

    /**
     * Se encarga de confirmar la contraseña reseteando los datos necesario para otro requerimiento de cambio
     * 
     * @author giovanni.velandia
     * @return
     */
    public String confirmacionCambioContraseña() {
        mostrarFormulario = true;
        vinculoValidado = false;
        return URL_PRINCIPAL;
    }

    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }

    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }

    public boolean isMostrarBtnContinuar() {
        return mostrarBtnContinuar;
    }

    public void setMostrarBtnContinuar(boolean mostrarBtnContinuar) {
        this.mostrarBtnContinuar = mostrarBtnContinuar;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isVinculoValidado() {
        return vinculoValidado;
    }

    public void setVinculoValidado(boolean vinculoValidado) {
        this.vinculoValidado = vinculoValidado;
    }

    public String getKeyRecuperacion() {
        return keyRecuperacion;
    }

    public void setKeyRecuperacion(String keyRecuperacion) {
        this.keyRecuperacion = keyRecuperacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public IRParametrosSeguridad getParametrosSeguridadEjb() {
        return parametrosSeguridadEjb;
    }

    public void setParametrosSeguridadEjb(IRParametrosSeguridad parametrosSeguridadEjb) {
        this.parametrosSeguridadEjb = parametrosSeguridadEjb;
    }

    public IRUsuario getUsuarioEjb() {
        return usuarioEjb;
    }

    public void setUsuarioEjb(IRUsuario usuarioEjb) {
        this.usuarioEjb = usuarioEjb;
    }

    public IRAutenticacion getAutenticacionEjb() {
        return autenticacionEjb;
    }

    public void setAutenticacionEjb(IRAutenticacion autenticacionEjb) {
        this.autenticacionEjb = autenticacionEjb;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static String getNombreBundleIngreso() {
        return NOMBRE_BUNDLE_INGRESO;
    }

}
