package co.com.datatools.ingreso;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.jboss.security.PicketBoxLogger;
import org.jboss.security.PicketBoxMessages;
import org.jboss.security.auth.spi.AbstractServerLoginModule;

import co.com.datatools.ingreso.excepcion.AutenticacionExcepcion;
import co.com.datatools.ingreso.excepcion.ErrorAutenticacion;
import co.com.datatools.ingreso.objetos.EnumEstadoIngreso;
import co.com.datatools.ingreso.objetos.Usuario;
import co.com.datatools.ingreso.utilidades.Cifrador;
import co.com.datatools.ingreso.utilidades.ConstantesAutenticacion;
import co.com.datatools.ingreso.utilidades.EnumAlgoritmo;
import co.com.datatools.ingreso.utilidades.EnumCodificador;
import co.com.datatools.ingreso.utilidades.EnumMetodo;
import co.com.datatools.ingreso.utilidades.Propiedades;

/**
 * M�dulo de implementaci�n personalizada de elementos JAAS para el ingreso de la aplicaci�n Circulemos
 * 
 * @author sergio.torres
 * 
 */
public class ModuloIngreso extends AbstractServerLoginModule {

    private static final Logger LOGGER = Logger.getLogger(ModuloIngreso.class);

    private Principal identity;

    private char[] credential;

    private String nombreUsuario;

    private String ipPeticion;

    private String nombreAplicacion;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {
        LOGGER.info("LOGIN_DOCUMENTOS::initialize");
        super.initialize(subject, callbackHandler, sharedState, options);
    }

    /**
     * giovanni.velandia (mod 2015-08-12)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean login() throws LoginException {
        LOGGER.info("ModuloIngreso::login");
        ConexionAutenticacion cx = ConexionAutenticacion.obtenerInstancia();
        String[] info = getUsernameAndPassword();
        String userName = info[0];
        validarUsuario(userName);
        String contrasenaUsuario = info[1];
        // Validar si existe el usuario, si el usuario no existe en el sistema se despliega una excepcion de autenticacion
        Usuario usuario = cx.consultarUsuario(nombreUsuario);
        // Validar si el usuario se encuentra activo
        if (!usuario.getEstadoUsuario().equals(Usuario.EstadoUsuario.ACTIVO)) {
            cx.registrarIngresoUsuario(usuario, EnumEstadoIngreso.FALLO_USUARIO_NO_ACTIVO, ipPeticion, nombreAplicacion);
            throw new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_102);
        }

        // Realizar autenticacion contra la base de datos
        super.loginOk = false;

        String algoritmo = Propiedades.obtenerInstancia()
                .obtenerPropiedadString(ConstantesAutenticacion.ALGORITMO_HASH);
        String codificador = Propiedades.obtenerInstancia().obtenerPropiedadString(ConstantesAutenticacion.CODIFICADOR);
        String metodo = Propiedades.obtenerInstancia().obtenerPropiedadString(ConstantesAutenticacion.METODO_ENCODER);

        if (identity == null) {
            try {
                identity = createIdentity(ipPeticion + "#" + nombreUsuario);
            } catch (Exception e) {
                LoginException le = PicketBoxMessages.MESSAGES.failedToCreatePrincipal(e.getLocalizedMessage());
                le.initCause(e);
                throw le;
            }
        }

        /***/
        boolean autenticacionExitosa = false;
        // validar metodo de autenticacion
        if (!usuario.isLdap()) {
            // validar estado de contrasenna
            if (!usuario.getEstadoPassword().equals(Usuario.EstadoPassword.ACTIVO)
                    && !usuario.getEstadoPassword().equals(Usuario.EstadoPassword.TEMPORAL)) {
                // Validacion del bloqueo de la contrase�a
                // Validamos si la contrase�a tiene un parametro de desbloqueo automatico si es asi lo dejamos pasar como OK
                if (cx.validarVigenciaBloqueo(usuario)) {
                    cx.registrarIngresoUsuario(usuario, usuario.getEstadoPassword().getEstadoIngreso(), ipPeticion,
                            nombreAplicacion);
                    // Se despliega la excepcion de usuario inactivo ya que dar detalles del estado del password podr�a ser una brecha de seguridad
                    throw new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_105);
                }
            }

            contrasenaUsuario = codificarPassword(contrasenaUsuario, algoritmo, codificador, metodo);
            autenticacionExitosa = validarPassword(contrasenaUsuario, usuario.getPassword());
        } else {
            autenticacionExitosa = ConexionAutenticacion.obtenerInstancia().autenticarLdap(nombreUsuario,
                    contrasenaUsuario);
        }
        if (autenticacionExitosa) {
            // LOGGER.info("========================================SOPORTE PRUEBAS ()loginModule===========================================");
            // LOGGER.info("Principals created: " + identity.getName());
            // LOGGER.info("================================================================================================================");
            if (getUseFirstPass() == true) { // Add the principal and password to the shared state map
                sharedState.put("javax.security.auth.login.name", identity);
                sharedState.put("javax.security.auth.login.password", credential);
            }
            super.loginOk = true;
            PicketBoxLogger.LOGGER.traceEndLogin(super.loginOk);
        } else {
            if (!cx.validarIntentosFallidosUsuario(usuario)) {
                cx.actualizarPasswordUsuario(usuario);
                // Registrar intento fallido por password
                cx.registrarIngresoUsuario(usuario, EnumEstadoIngreso.FALLO_PW_ERRONEO, ipPeticion, nombreAplicacion);
                throw new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_104);
            }
            // Registrar intento fallido por password
            cx.registrarIngresoUsuario(usuario, EnumEstadoIngreso.FALLO_PW_ERRONEO, ipPeticion, nombreAplicacion);
            throw new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_101);
        }

        return super.loginOk;
    }

    /**
     * Metodo persinalizado que permite encriptar la contrase�a recibida usando el algoritmo y el encoder recibido
     * 
     */
    protected String codificarPassword(String password, String algoritmo, String codificador, String metodo)
            throws LoginException {

        try {
            return Cifrador.cifrarMensaje(password, EnumAlgoritmo.valueOf(algoritmo),
                    EnumCodificador.valueOf(codificador), EnumMetodo.valueOf(metodo));
        } catch (NoSuchAlgorithmException | IOException e) {
            AutenticacionExcepcion ae = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_001);
            ae.initCause(e);
            throw ae;
        } catch (IllegalArgumentException e) {
            AutenticacionExcepcion ae = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_001);
            ae.initCause(e);
            throw ae;
        }
    }

    protected boolean validarPassword(String password, String passwordEsperado) {
        return password.equals(passwordEsperado);
    }

    @Override
    protected Principal getIdentity() {
        return identity;
    }

    @Override
    protected Group[] getRoleSets() throws LoginException {
        LOGGER.info("ModuloIngreso::getRoleSets");
        return ConexionAutenticacion.obtenerInstancia().consultarRolesUsuario(nombreUsuario);
    }

    protected Object getCredentials() {
        return credential;
    }

    /**
     * Called by login() to acquire the username and password strings for authentication. This method does no validation of either.
     * 
     * @return String[], [0] = username, [1] = password
     * @exception LoginException
     *                thrown if CallbackHandler is not set or fails.
     */
    protected String[] getUsernameAndPassword() throws LoginException {
        String[] info = { null, null };
        // prompt for a username and password
        if (callbackHandler == null) {
            throw PicketBoxMessages.MESSAGES.noCallbackHandlerAvailable();
        }

        NameCallback nc = new NameCallback(PicketBoxMessages.MESSAGES.enterUsernameMessage(), "guest");
        PasswordCallback pc = new PasswordCallback(PicketBoxMessages.MESSAGES.enterPasswordMessage(), false);
        Callback[] callbacks = { nc, pc };
        String username = null;
        String password = null;
        try {
            callbackHandler.handle(callbacks);
            username = nc.getName();
            char[] tmpPassword = pc.getPassword();
            if (tmpPassword != null) {
                credential = new char[tmpPassword.length];
                System.arraycopy(tmpPassword, 0, credential, 0, tmpPassword.length);
                pc.clearPassword();
                password = new String(credential);
            }
        } catch (IOException e) {
            LoginException le = PicketBoxMessages.MESSAGES.failedToInvokeCallbackHandler();
            le.initCause(e);
            throw le;
        } catch (UnsupportedCallbackException e) {
            LoginException le = new LoginException();
            le.initCause(e);
            throw le;
        }
        info[0] = username;
        info[1] = password;
        return info;
    }

    /**
     * Se encarga de obtener e inicializar el nombre de usuario y la ip que vienen en el mismo campo
     * 
     * @param nombreUsuario
     * 
     * @author sergio.torres (27/05/2015) giovanni.velandia (mod 2015-08-12)
     */
    private void validarUsuario(String nombreUsuario) {
        try {
            /*
             * Identificamos la informacion de origen y el login
             */
            String login = nombreUsuario.substring(nombreUsuario.indexOf("#") + 1);
            this.nombreUsuario = login;

            String origen = nombreUsuario.substring(0, nombreUsuario.indexOf("#"));
            /*
             * Identificamos la ip y el nombre de la aplicacion
             */
            String[] valores = origen.split("@");

            this.nombreAplicacion = valores[0];
            this.ipPeticion = valores[1];
        } catch (IndexOutOfBoundsException ioob) {
            this.nombreUsuario = nombreUsuario;
        }
    }

}
