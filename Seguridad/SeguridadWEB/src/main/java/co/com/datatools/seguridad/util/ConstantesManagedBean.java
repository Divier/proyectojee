package co.com.datatools.seguridad.util;

/**
 * Clase de constantes utilizadas en la capa web
 * 
 * @author Felipe Martinez
 */
public final class ConstantesManagedBean {

    private ConstantesManagedBean() {
    }

    /**
     * Nombre del bundle de mensajes general
     */
    public static final String NOMBRE_BUNDLE_GENERAL = "mensajesGeneral";

    /**
     * Nombre del atributo de sesion que mantiene la URL inicial de seguridad
     */
    public static final String NOMBRE_OBJ_URL_INICIAL = "URL_INICIAL";

    /**
     * Flujo de recuperacion de password
     */
    public static final String NOMBRE_PAGINA_RESTABLECER_PW = "recuperapw";

    /**
     * URL inicial de seguridad
     */
    public static final String URL_MAIN = "/app/main";

    /**
     * Nombre del atributo de aplicacion que indica si la pantalla de instalacion fue ejecutada
     */
    public static final String NOMBRE_OBJ_FLAG_INSTALADOR = "FLAG_INSTALADOR";

    /**
     * URL del controlador de instalacion de seguridad
     */
    public static final String URL_INSTALADOR = "/extra/instalador";
}