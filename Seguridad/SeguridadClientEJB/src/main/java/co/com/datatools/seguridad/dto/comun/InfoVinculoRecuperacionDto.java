package co.com.datatools.seguridad.dto.comun;

import java.io.Serializable;

/**
 * Objeto que encapsula los datos necesarios para hacer el envio del vinculo de recuperacion de contraseņa
 * 
 * @author claudia.rodriguez
 * 
 */
public class InfoVinculoRecuperacionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Login del usuario que solicita la recuperacion de la contraseņa
     */
    private String login;

    /**
     * Nombre(id) de la aplicacion cliente
     */
    private String aplicacion;

    /**
     * Ruta(relativa) que identifica al flujo de swf para recuperacion de la contraseņa(Ejemplo: app/recuperacionPw)
     */
    private String flujoRecuperacionPw;

    /**
     * Nombre del parametro enviado en el vinculo(URL) de recuperacion cuyo valor corresponde a la llave de recuperacion de la contraseņa
     */
    private String keyRecuperacion;

    /**
     * Constructor con la informacion del vinculo de recuperacion
     * 
     * @param login
     *            Login del usuario que solicita la recuperacion de la contraseņa
     * @param aplicacion
     *            Nombre(id) de la aplicacion cliente
     * @param flujoRecuperacionPw
     *            Ruta(relativa) que identifica al flujo de swf para recuperacion de la contraseņa
     * @param keyRecuperacion
     *            Nombre del parametro enviado en el vinculo(URL) de recuperacion cuyo valor corresponde a la llave de recuperacion de la contraseņa
     */
    public InfoVinculoRecuperacionDto(String login, String aplicacion, String flujoRecuperacionPw,
            String keyRecuperacion) {
        this.login = login;
        this.aplicacion = aplicacion;
        this.flujoRecuperacionPw = flujoRecuperacionPw;
        this.keyRecuperacion = keyRecuperacion;
    }

    public InfoVinculoRecuperacionDto() {
        super();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getFlujoRecuperacionPw() {
        return flujoRecuperacionPw;
    }

    public void setFlujoRecuperacionPw(String flujoRecuperacionPw) {
        this.flujoRecuperacionPw = flujoRecuperacionPw;
    }

    public String getKeyRecuperacion() {
        return keyRecuperacion;
    }

    public void setKeyRecuperacion(String keyRecuperacion) {
        this.keyRecuperacion = keyRecuperacion;
    }

}
