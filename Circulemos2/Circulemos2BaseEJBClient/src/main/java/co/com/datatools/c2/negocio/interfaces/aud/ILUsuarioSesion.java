package co.com.datatools.c2.negocio.interfaces.aud;

import javax.ejb.Local;

@Local
public interface ILUsuarioSesion {

    /**
     * @return Usuario de sesion o enviado por ws
     */
    String getUsuario();

    /**
     * @return IP de sesion o enviado por ws
     */
    String getIP();

    /**
     * AUDITORIA - METODO ENCARGADO DE CONTROLAR EL MAPA DE USUARIOS E IP'S DE ACUERDO A LAS SOLICITUDES
     * 
     * @param loginUsuario
     *            Login del usuario
     * @param ip
     *            IP del usuario logueado
     * @author julio.pinzon
     */
    public long almacenarUsuario(String loginUsuario, String ip);

    /**
     * Quita el usuario del mapa estatico de usuarios
     * 
     * @param idThread
     * @author julio.pinzon
     */
    void removerUsuario();

}
