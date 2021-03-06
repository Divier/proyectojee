package co.com.datatools.seguridad.utilidades;

/**
 * Enumeracion con los diferentes valores posibles de nombres de parametros de seguridad
 * 
 * @author claudia.rodriguez
 * 
 */
public enum EnumParametro {

    PW_LONGITUD_MIN, //
    PW_LONGITUD_MAX, //
    PW_OBLIGA_NUMEROS, //
    PW_OBLIGA_LETRAS, //
    PW_OBLIGA_MAYUSCULAS, //
    PW_OBLIGA_CARACTERES_ESPECIALES, //
    PW_CARACTERES_ESPECIALES, //
    CANTIDAD_HORAS_VIGENCIA_BLOQUEO_PW, //
    PW_CANTIDAD_DIAS_VIGENCIA, //

    INITIAL_CONTEXT_FACTORY, //
    PROVIDER_URL, //
    SECURITY_AUTHENTICATION, //
    SECURITY_PRINCIPAL, //
    SECURITY_CREDENTIALS, //
    AUTHORIZED_GROUP_PATH, //
    USERS_PATH, //
    PERMITE_AUTENTICACION_LDAP, //
    NOMBRE_ATRIBUTO_MIEMBRODE_LDAP, //
    NOMBRE_ATRIBUTO_IDUSUARIO_LDAP, //
    NOMBRE_ATRIBUTO_LOGIN_LDAP, //
    ALGORITMO_GENERAR_LOGIN, //
    NIVELES_HERENCIA_ROLES, //
    DESBLOQUEO_PW_AUTOMATICO, //
    CANTIDAD_PW_HISTORICO, //
    CANTIDAD_INTENTOS_FALLIDOS_PW, //
    CANTIDAD_HORAS_VALIDA_INTENTOS_FALLIDOS_PW, //
    CANTIDAD_HORAS_VIGENCIA_VINCULO_TEMPORAL, //
    ASUNTO_CORREO_RECUPERACION_PW, //
    CUERPO_CORREO_RECUPERACION_PW, //
    NOMBRE_ATRIBUTO_NOMBRES_LDAP, //
    NOMBRE_ATRIBUTO_APELLIDOS_LDAP, //
    NOMBRE_ATRIBUTO_CORREO_LDAP, //
    URL_APLICACION, //
    CANTIDAD_DIAS_CONSULTA_INGRESOS, //
    CANTIDAD_DIAS_CONSULTA_ROLES, //
    CANTIDAD_DIAS_CONSULTA_USUARIOS, //

    INSTALADOR_EJECUTADO, //
    ;
}
