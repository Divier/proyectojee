package co.com.datatools.seguridad.interfaces;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto;
import co.com.datatools.seguridad.dto.comun.InfoVinculoRecuperacionDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;

/**
 * Interfaz remota EJB Autenticacion
 * 
 * @author Felipe Martinez
 */
@Remote
public interface IRAutenticacion {

    /**
     * Indica al modulo q el usuario realizo cierre de sesion y que se debe guardar el log de solicitudes de autorizacion de ingreso de usuario
     * indicado
     * 
     * @param login
     *            Login del usuario del ingreso
     * @param idIngresoUsuario
     *            identificador del ingreso se quiere cerrar, si es <b>null</b>, se busca el ultimo ingreso del usuario q se encuentre abierto y se
     *            cierra
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-05-14)
     */

    void cerrarSession(final String login, final Integer idIngresoUsuario);

    /**
     * Cambia el password actual del usuario si passwordActual es el q se encuentra almacenado y passwordNuevo cumple con las politicas de seguridad
     * parametrizadas
     * 
     * @param login
     *            Login del usuario al que se le cambiara el password
     * @param passwordActual
     *            password actual del usuario
     * @param passwordNuevo
     *            password nuevo
     * 
     * @throws SeguridadException
     *             IN0001:Contraseña actual no coincide,<br>
     *             IN0002:Contraseña no cumple politicas,<br>
     *             IN0003:Contraseña nueva es igual a anterior
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-05-13)
     */
    void cambiarPassword(final String login, final String passwordActual, final String passwordNuevo)
            throws SeguridadException;

    /**
     * Cambia el password de un usuario si el correo corresponde al usuario y si passwordNuevo cumple con las politicas de seguridad parametrizadas
     * 
     * @param login
     *            del usuario que restablece la contraseña
     * @param keyRecuperacion
     *            Codigo de verificacion de la solicitud de recuperacion de contraseña
     * @param correo
     *            Correo electronico que se valida contra el correo almacenado del usuario
     * @param passwordNuevo
     *            Nuevo password del usuario
     * @throws SeguridadException
     *             IN0002: Contraseña no cumple politicas,<br>
     *             IN0003: Contraseña nueva es igual a anterior,<br>
     *             IN0009: Correo enviado no coincide con el registrado para el usuario *
     * @author claudia.rodriguez
     */
    void restablecerPassword(final String login, final String keyRecuperacion, final String correo,
            final String passwordNuevo) throws SeguridadException;

    /**
     * Genera una URL de recuperacion de contraseña y la envia al correo del usuario
     * 
     * @param infoVinculoRecuperacionDto
     *            Datos necesarios para realizar el envio del vinculo de recuperacion
     * @return Indica si se genero el vinculo correctamente
     * 
     * @throws SeguridadException
     *             IN0004: Usuario no existe,<br>
     *             IN0005: Usuario no activo,<br>
     *             IN0006: Usuario con password bloqueado,<br>
     *             IN0007: El usuario tiene definida la autenticación con LDAP<br>
     *             IN0008: El usuario tiene otra solicitud de recuperacion activa,<br>
     *             IN0010: Error enviando correo de recuperacion de contraseña,<br>
     *             IN0011: Error en la configuración del parámetro de seguridad: url de la aplicación,<br>
     *             IN0012: Error en el usuario ingresado no es válido para la solicitud de recuperación de contraseña
     * 
     * @author claudia.rodriguez
     */
    boolean enviarVinculoRecuperacion(InfoVinculoRecuperacionDto infoVinculoRecuperacionDto) throws SeguridadException;

    /**
     * Genera una contraseña de acuerdo a los parametros de estructura de contraseña
     * 
     * @return Contraseña generada sin cifrar
     * 
     * @author claudia.rodriguez<br>
     *         Felipe Martinez(mod)<br>
     *         claudia.rodriguez(mod 2014-06-03)
     */
    String generarPassword();

    /**
     * Actualiza el correo electronico de un usuario
     * 
     * @param login
     *            Login del usuario al que se actualiza el correo
     * @param nuevoCorreo
     *            Nuevo correo electronico del usuario
     * 
     * @throws SeguridadException
     *             US0004: Correo electronico ya existe con otro usuario *
     * @author claudia.rodriguez
     */
    void actualizarCorreoElectronico(String login, String nuevoCorreo) throws SeguridadException;

    /**
     * Valida si un codigo de verificacion corresponde con una solicitud de recuperacion de contraseña valida(solicitud que exista y no haya sido
     * previamente cerrada)
     * 
     * @param keyRecuperacion
     *            Codigo a verificar
     * @return True si el codigo corresponde a una solicitud valida, de lo contrario false
     * @author claudia.rodriguez
     */
    boolean verificarSolicitudRecuperacionAbierta(String keyRecuperacion);

    /**
     * Valida si un codigo de verificacion corresponde con una solicitud de recuperacion de contraseña que no se encuentre vencida
     * 
     * @param keyRecuperacion
     *            Codigo a verificar
     * @return True si el codigo corresponde a una solicitud vencida, de lo contrario false *
     * @author claudia.rodriguez
     */
    boolean verificarSolicitudRecuperacionVencida(String keyRecuperacion);

    /**
     * Realiza una prueba de conexión al LDAP con los datos enviados
     * 
     * @param ipServidor
     * @param login
     * @return
     */
    boolean probarConexionLDAP(String ipServidor, String login, String password);

    /**
     * Valida despues de una autenticacion exitosa por JAAS si el password esta vencido o temporal, tambien si se encuentra bloqueado es porque debe
     * realizarse desbloqueo
     * 
     * @param login
     *            Login del usuario autenticado
     * @param ipIngreso
     *            Direccion IP de la maquina donde se hizo la autenticacion
     * @param aplicacion
     *            Nombre de la aplicación sobre la cual se realiza la autenticacion
     * @return Dto con el resultado de la autenticacion y los datos del usuario autenticado
     * 
     * @author claudia.rodriguez
     */
    ResultadoAutenticacionDto validarEstadoPassword(final String login, final String ipIngreso, final String aplicacion);

    /**
     * Registra una autenticacion exitosa de un usuario a la aplicacion, cerrando otras sesiones exitosas que se encuentren abiertas
     * 
     * @param login
     *            Usuario que se autentica exitosamente
     * @param ipIngresoDdireccion
     *            ip de la maquina donde se autentica el usuario
     * @param aplicacion
     *            Nombre de la aplicacion en la cual se autentica el usuario
     * @return ID del ingreso exitoso registraso @ author claudia.rodriguez
     */
    Integer registrarIngresoExitoso(String login, String ipIngreso, String aplicacion);

}
