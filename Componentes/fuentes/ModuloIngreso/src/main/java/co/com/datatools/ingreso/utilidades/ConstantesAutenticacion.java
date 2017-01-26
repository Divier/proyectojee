package co.com.datatools.ingreso.utilidades;

/**
 * Clase que almacena las constantes de autenticacion utilizadas en el sistema
 * 
 * @author sergio.torres (20/05/2015) giovanni.velandia (mod 2015-08-12)
 * 
 */
public class ConstantesAutenticacion {

    // Aplicacion
    public static final int CODIGO_APLICACION = 2;
    public static final String VALOR_SI = "SI";
    public static final int CODIGO_ROL_PUBLICO = 2;
    // Ingreso
    public static final String DATASOURCE = "nombre_datasource";
    public static final String QUERY_USUARIOS = "query_usuario";
    public static final String QUERY_ROLES = "query_roles";
    public static final String QUERY_ROL_PADRE = "query_rol_padre";
    public static final String QUERY_GRUPOS = "query_grupos";
    public static final String QUERY_APLICACION_ROL = "query_aplicacion_rol";
    public static final String QUERY_PARAMETRO = "query_parametro";
    public static final String NOMBRE_PARAMETRO_NIVEL_JERARQUIA = "nombre_parametro_jerarquia";
    public static final String ALGORITMO_HASH = "algoritmo_hashing";
    public static final String CODIFICADOR = "codificador";
    public static final String METODO_ENCODER = "metodo_encoder";
    public static final String INSERT_REGISTRO = "script_registrar_ingreso";
    public static final String MOD_PASS_USUARIO = "query_mod_password_usuario";
    public static final String PARAMETRO_CANTIDAD_INTENTOS_FALLIDOS = "nombre_parametro_cantidad_intentos_fallidos";
    public static final String PARAMETRO_HORAS_INTENTOS_FALLIDOS = "nombre_parametro_horas_intentos_fallidos";
    public static final String DESBLOQUEO_PW_AUTOMATICO = "desbloqueo_pw_automatico";
    public static final String CANTIDAD_HORAS_VIGENCIA_BLOQUEO_PW = "cantidad_horas_vigenciabloqueo_pw";
    public static final String QUERY_CANTIDAD_INGRESOS_FALLIDOS = "query_ingresos_fallidos";
    public static final String GRUPO_GENERICOS = "generico";
    public static final String PREFIJO_GENERICOS = "gen_";
    public static final String GRUPO_ROLES = "Roles";
    // LDAP
    public static final String PARAMETRO_PATH_USUARIOS = "path_usuarios";
    public static final String PARAMETRO_INITIAL_CONTEXT = "initial_context";
    public final static String PREFIJO_LDAP_JNDI = "ldap://";
    public final static String PARAMETRO_URL_PROVIDER = "provider_url";
    public final static String PARAMETRO_SECURITY_AUTHENTICATION = "security_authentication";

}
