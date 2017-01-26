package co.com.datatools.seguridad.utilidades;

/**
 * Clase que encapsula los diferentes valores constantes en el componente de seguridad
 * 
 * @author felipe.martinez
 * 
 */
public final class ConstantesSeguridad {

    private ConstantesSeguridad() {
    }

    public static final String NOMBRE_ROL_SUPER_ADMIN = "SUPER-ADMIN";

    public static final String NOMBRE_ROL_PUBLICO = "PUBLICO";

    public static final String NOMBRE_OPERACION_INGRESO = "ingresar";

    public static final String NOMBRE_PERSISTENCE_CTX = "Seguridad-PU";

    public static final String NOMBRE_CACHE_ROLES = "roles";
    public static final String NOMBRE_CACHE_AUTORIZACIONES = "autorizaciones";

    public static final String VALOR_SI = "SI";
    public static final String VALOR_NO = "NO";

    public static final String LOGIN_USUARIO_SUPER_ADMIN = "admin";

    public static final Integer ID_XSD_HISTORICO_ROL = 1;
    public static final Integer ID_XSD_HISTORICO_USUARIO = 2;
    public static final Integer ID_XSD_ACTIVIDAD_INGRESO = 3;

    public static final String RECURSO_MAIN = "main";
    public static final String RECURSO_MI_CUENTA = "cuenta";

    public static final String NOMBRE_PARAMETRO_KEY_RECUPERACION = "key";

    public static final String NOMBRE_ATRIBUTO_SESION_ID_INGRESO = "idIngreso";

    public static final String LOGIN_USUARIO_SISTEMA = "Sistema";

    /**
     * Pagina restablecer contraseña
     */
    public static final String PAGINA_RESTABLECER_PW = "publico/recuperapw.xhtml";

    /**
     * Prefijo de la URL para conexión JNDI al LDAP
     */
    public final static String PREFIJO_LDAP_JNDI = "ldap://";

    /**
     * Clase anidada con las constantes de nombres de manged beans
     * 
     * @author felipe.martinez
     * 
     */
    public static class NombresManagedBeans {

        private NombresManagedBeans() {

        }

        /**
         * Nombre del managed bean que tiene los servicios de autenticacion
         */
        public static final String AUTENTICACION_BEAN = "autenticacionSegMB";
        /**
         * Nombre del managed bean que tiene los servicios de autorizacion
         */
        public static final String AUTORIZACION_BEAN = "autorizacionSegMB";
        /**
         * Nombre del managed bean q procesa el menu de un usuario
         */
        public static final String MENU_SESION_BEAN = "menuSesionSegMB";
    }

    /**
     * Clase anidada con las constantes relacionados con propiedades de inicializacion de la aplicacion
     * 
     * @author felipe.martinez
     * 
     */
    public static class NombresPropiedades {

        private NombresPropiedades() {

        }

        /**
         * Nombre del parametro de contexto q define el nombre de clase q implementa la clase de {@link NodosAutorizables}
         */
        public static final String CLASE_NODOS_AUTORIZABLES_PROP = "co.com.datatools.seguridad.NODOS_AUTORIZABLES";
        /**
         * Nombre del parametro de contexto q define el identificador de aplicacion a usar en la logica de los metodos de seguridad
         */
        public static final String ID_APLICACION_PROP = "co.com.datatools.seguridad.ID_APLICACION";
        /**
         * Indica si el modulo de seguridad autorizara basado en los roles del usuario o basado un lista de roles definidos
         */
        public static final String MODO_AUTORIZACION_PROP = "co.com.datatools.seguridad.MODO_AUTORIZACION";

        /**
         * Eumeracion con los valores posibles para el modo de autorizacion
         * 
         * @author felipe.martinez
         * 
         */
        public enum ModoAutorizacion {
            ROLES, USUARIO;
        }

    }

}
