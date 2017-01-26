package co.com.datatools.seguridad.ejb;

import co.com.datatools.seguridad.excepciones.ErrorWrapper;

/**
 * Clase que mantiene el catalogo de errores de negocio para los diferentes modulos de la aplicacion
 * 
 * @author Felipe Martinez
 * 
 */
public abstract class CatalogoError {
    // --
    protected enum Modulo {
        GR, // Grupos
        RL, // Roles
        RC, // Recursos
        US, // Usuarios
        OP, // Operaciones
        IN, // Ingreso
    }

    // -- Error Grupo
    public static final ErrorWrapper GRUPO_VS_ROL = new ErrorWrapper(Modulo.GR + "0001", "Grupo en uso por Rol");
    public static final ErrorWrapper GRUPO_VS_USUARIO = new ErrorWrapper(Modulo.GR + "0002", "Grupo en uso por Usuario");
    public static final ErrorWrapper GRUPO_YA_EXISTE = new ErrorWrapper(Modulo.GR + "0003",
            "Ya existe un grupo con el mismo nombre");
    public static final ErrorWrapper GRUPO_VS_USUARIO_MODIFICACION = new ErrorWrapper(Modulo.GR + "0004",
            "El grupo ya esta asociado a usuarios");
    public static final ErrorWrapper GRUPO_VS_ROL_MODIFICACION = new ErrorWrapper(Modulo.GR + "0005",
            "El grupo ya esta asociado a roles");

    // -- Error Rol
    public static final ErrorWrapper ROL_YA_EXISTE = new ErrorWrapper(Modulo.RL + "0001", "Rol ya existe");
    public static final ErrorWrapper ROL_PUBLICO_NO_ELIMINABLE = new ErrorWrapper(Modulo.RL + "0002",
            "Rol PUBLICO no eliminable");
    public static final ErrorWrapper ROL_VS_USUARIO = new ErrorWrapper(Modulo.RL + "0003", "Rol asociado usuario");
    public static final ErrorWrapper ROL_VS_ROL_PADRE = new ErrorWrapper(Modulo.RL + "0004", "Rol asociado como padre");
    public static final ErrorWrapper ROL_VS_INGRESO_USUARIO = new ErrorWrapper(Modulo.RL + "0005",
            "Rol asociado a ingreso");
    public static final ErrorWrapper ROL_VS_HISTORICO_ROL_PADRE = new ErrorWrapper(Modulo.RL + "0006",
            "Rol asociado como padre en historico");
    public static final ErrorWrapper ROL_VS_HISTORICO_USUARIO = new ErrorWrapper(Modulo.RL + "0007",
            "Rol asociado a usuario en historico");
    public static final ErrorWrapper ERROR_VALIDACION_XML_HISTORICO_ROL = new ErrorWrapper(Modulo.RL + "0008",
            "Error validando el xml del historico");
    public static final ErrorWrapper ROL_ACTIVO_TIENE_HIJOS = new ErrorWrapper(Modulo.RL + "0009",
            "Rol activo tiene hijo(s)");

    // -- Error Recurso
    public static final ErrorWrapper RECURSO_VS_ROL = new ErrorWrapper(Modulo.RC + "0001", "Recurso asociado a rol");
    public static final ErrorWrapper RECURSO_YA_EXISTE = new ErrorWrapper(Modulo.RC + "0002", "Recurso ya existe");
    public static final ErrorWrapper RECURSO_VS_RECURSOS_HIJOS = new ErrorWrapper(Modulo.RC + "0003",
            "Recurso es padre de otros recursos");
    public static final ErrorWrapper RECURSO_OPERACION_VS_ROL = new ErrorWrapper(Modulo.RC + "0004",
            "Recurso-operacion asociada a rol");
    public static final ErrorWrapper RECURSO_VS_MENU = new ErrorWrapper(Modulo.RC + "0005",
            "Recurso asociado a recurso_menu");

    // -- Error Usuario
    public static final ErrorWrapper CONEXION_LDAP_FALLIDA = new ErrorWrapper(Modulo.US + "0001",
            "Error conexion al ldap");
    public static final ErrorWrapper USUARIO_YA_EXISTE = new ErrorWrapper(Modulo.US + "0002",
            "Ya existe el usuario con el login enviado");
    public static final ErrorWrapper USUARIO_VS_INGRESOS = new ErrorWrapper(Modulo.US + "0003",
            "Usuario tiene ingresos");
    public static final ErrorWrapper CORREO_USUARIO_YA_EXISTE = new ErrorWrapper(Modulo.US + "0004",
            "Correo ya existe con otro usuario");
    public static final ErrorWrapper MODIFICA_USUARIO_PROPIO = new ErrorWrapper(Modulo.US + "0005",
            "Intenta cambiar estado al propio usuario");
    public static final ErrorWrapper VARIOS_RESULTADOS_LDAP = new ErrorWrapper(Modulo.US + "0006",
            "Encuentra varios usuarios en el LDAP");
    public static final ErrorWrapper ERROR_VALIDACION_XML_HISTORICO_USUARIO = new ErrorWrapper(Modulo.US + "0007",
            "Error validando el xml del historico");
    public static final ErrorWrapper ERROR_VALIDACION_XML_ACTIVIDAD_INGRESO_USUARIO = new ErrorWrapper(Modulo.US
            + "0008", "Error validando el xml de la actividad de ingreso");
    public static final ErrorWrapper USUARIO_VS_SOLICITUDES_RECUPERACION_PW = new ErrorWrapper(Modulo.US + "0009",
            "Usuario tiene solicitudes de recuperación de password");

    // --Error operaciones
    public static final ErrorWrapper OPERACION_YA_EXISTE = new ErrorWrapper(Modulo.OP + "0001", "Operacion ya existe");
    public static final ErrorWrapper OPERACION_VS_RECURSO = new ErrorWrapper(Modulo.OP + "0002",
            "Operacion asociado a recurso");
    public static final ErrorWrapper OPERACION_INGRESO_NO_EDITABLE = new ErrorWrapper(Modulo.OP + "0003",
            "Operacion ingreso no es editable");

    // --Error ingreso cambio de contraseña
    public static final ErrorWrapper PW_ACTUAL_NO_COINCIDE = new ErrorWrapper(Modulo.IN + "0001",
            "Contraseña actual no coincide");
    public static final ErrorWrapper PW_NO_CUMPLE_POLITICAS = new ErrorWrapper(Modulo.IN + "0002",
            "Contraseña no cumple politicas");
    public static final ErrorWrapper PW_NUEVO_IGUAL_ANTERIOR = new ErrorWrapper(Modulo.IN + "0003",
            "Contraseña nueva es igual a anterior");

    // --Error ingreso recuperacion de contraseña
    public static final ErrorWrapper USUARIO_NO_EXISTE = new ErrorWrapper(Modulo.IN + "0004", "Usuario no existe");
    public static final ErrorWrapper USUARIO_NO_ACTIVO = new ErrorWrapper(Modulo.IN + "0005", "Usuario no activo");
    public static final ErrorWrapper USUARIO_PW_BLOQUEADO = new ErrorWrapper(Modulo.IN + "0006",
            "Usuario con password bloqueado");
    public static final ErrorWrapper USUARIO_CON_LDPA = new ErrorWrapper(Modulo.IN + "0007",
            "El usuario tiene definida la autenticación con LDAP y no se puede realizar la recuperación de la contraseña");
    public static final ErrorWrapper SOLICITUD_RECUPERACION_PW_ACTIVA = new ErrorWrapper(Modulo.IN + "0008",
            "El usuario tiene otra solicitud de recuperacion activa");
    public static final ErrorWrapper CORREO_NO_COINCIDE = new ErrorWrapper(Modulo.IN + "0009", "Correo no coincide");
    public static final ErrorWrapper ERROR_ENVIO_CORREO_RECUPERACION_PW = new ErrorWrapper(Modulo.IN + "0010",
            "Error enviando correo de recuperacion de contraseña");
    public static final ErrorWrapper ERROR_PARAMETRO_URL_APLICACION = new ErrorWrapper(Modulo.IN + "0011",
            "EL parametro URL de la aplicación no esta configurado correctamente");
    public static final ErrorWrapper ERROR_USUARIO_RECUPERACION_PW = new ErrorWrapper(Modulo.IN + "0012",
            "El usuario ingresado no es válido para la solicitud de recuperación de contraseña");

}
