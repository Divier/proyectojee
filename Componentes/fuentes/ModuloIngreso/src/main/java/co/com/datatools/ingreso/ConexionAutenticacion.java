package co.com.datatools.ingreso;

import java.security.Principal;
import java.security.acl.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;

import co.com.datatools.ingreso.excepcion.AutenticacionExcepcion;
import co.com.datatools.ingreso.excepcion.ErrorAutenticacion;
import co.com.datatools.ingreso.objetos.EnumEstadoIngreso;
import co.com.datatools.ingreso.objetos.Rol;
import co.com.datatools.ingreso.objetos.Usuario;
import co.com.datatools.ingreso.utilidades.ConstantesAutenticacion;
import co.com.datatools.ingreso.utilidades.Propiedades;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.ldap.LDAP;
import co.com.datatools.util.ldap.LdapContexto;
import co.com.datatools.util.ldap.LdapContexto.EnumLdapParams;

/**
 * Clase encargada de realizar las consultas para el modulo de autenticaci�n
 * 
 * @author sergio.torres
 * 
 */
public class ConexionAutenticacion {

    private static final Logger LOGGER = Logger.getLogger(ConexionAutenticacion.class);

    private static ConexionAutenticacion instancia;

    private DataSource datasource;
    private Propiedades propiedades;

    private ConexionAutenticacion() throws AutenticacionExcepcion {
        propiedades = Propiedades.obtenerInstancia();
    }

    /**
     * Permite obtener la instancia de la clase singleton que controla la conexion y ejecucion de consultas de la autenticacion
     * 
     * @return
     * @throws AutenticacionExcepcion
     * 
     * @author sergio.torres (25/05/2015)
     */
    public static ConexionAutenticacion obtenerInstancia() throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::obtenerInstancia");
        if (instancia == null) {
            instancia = new ConexionAutenticacion();
        }
        return instancia;
    }

    /**
     * Se encarga de obtener los datos del usuario validando si el mismo existe
     * 
     * @return <strong>password</strong> del usuario en el sistema
     */
    public Usuario consultarUsuario(String nombreUsuario) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::consultarUsuario");
        String queryUsuario = propiedades.obtenerPropiedadString(ConstantesAutenticacion.QUERY_USUARIOS);
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(queryUsuario)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    // El usuario no existe en el sistema
                    throw new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_101);
                }
                // Capturar los datos del usuario
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString(1));
                usuario.setPassword(rs.getString(2));
                usuario.setEstadoUsuario(Usuario.EstadoUsuario.obtenerValor(rs.getInt(3)));
                usuario.setLdap(rs.getBoolean(4));
                usuario.setEstadoPassword(Usuario.EstadoPassword.obtenerValor(rs.getInt(5)));
                usuario.setId(rs.getInt(6));
                usuario.setFechaBloqueoPassword(rs.getTimestamp(7));
                return usuario;
            }
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_004);
            aex.initCause(e);
            throw aex;
        }
    }

    /**
     * Permite crear los grupos de roles asociados al usuario
     * 
     * @param nombreUsuario
     * @return
     * 
     * @author sergio.torres(22-mayo-2015) giovanni.velandia (mod 2015-08-12)
     * @throws AutenticacionExcepcion
     */
    public Group[] consultarRolesUsuario(String nombreUsuario) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::consultarUsuario");
        HashMap<String, Group> mapRoles = new HashMap<>();
        List<Rol> roles = consultarRolesUsuarioRecurrente(nombreUsuario);
        // Objeto para cuando no tiene asociado un grupo
        List<String> grupoDummyUsuario = new ArrayList<>();
        grupoDummyUsuario.add(ConstantesAutenticacion.GRUPO_ROLES);
        // Armar los grupos de acuerdo a los roles obtenidos del nivel jerarquico parametrizado
        for (Rol rol : roles) {

            /*
             * Excluimos el Rol publico de la autenticacion con JAAS ya que este rol es trasversal a todas las aplicaciones
             */
            if (rol.getIdRol() != ConstantesAutenticacion.CODIGO_ROL_PUBLICO) {
                rol.setGrupos(new ArrayList<String>());
                Group grupoRol;
                // Asociar al menos un grupo al rol
                if (rol.getGrupos().isEmpty())
                    rol.setGrupos(grupoDummyUsuario);
                for (String grupo : rol.getGrupos()) {
                    grupoRol = mapRoles.get(grupo);
                    if (grupoRol == null) {
                        // Aun no se ha creado el grupo debe crearse para asociar el rol al mismo
                        grupoRol = new SimpleGroup(grupo);
                    }
                    grupoRol.addMember(rol);
                    mapRoles.put(grupo, grupoRol);
                }
                // asociar el rol generico de cada aplicacion a la que tiene acceso el rol
                for (String aplicacion : rol.getAplicaciones()) {
                    String nombreGrupo = "Roles";
                    Group grupoGenerico;
                    grupoGenerico = mapRoles.get(nombreGrupo);
                    if (grupoGenerico == null) {
                        grupoGenerico = new SimpleGroup(nombreGrupo);
                    }
                    Principal p = new SimplePrincipal(ConstantesAutenticacion.PREFIJO_GENERICOS.concat(aplicacion));
                    grupoGenerico.addMember(p);
                    mapRoles.put(nombreGrupo, grupoGenerico);
                }
            }
        }
        Group[] retorno = new Group[mapRoles.size()];
        mapRoles.values().toArray(retorno);
        return retorno;
    }

    /**
     * Se encarga de resolver los roles asociados al usuario incluyendo los roles heredados
     * 
     * @param nombreUsuario
     * @return
     * @throws AutenticacionExcepcion
     * 
     * @author sergio.torres (22-05-2015)
     */
    private List<Rol> consultarRolesUsuarioRecurrente(String nombreUsuario) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::consultarRolesUsuarioRecurrente");
        List<Rol> rolesUsuario = new ArrayList<>();
        String queryRoles = propiedades.obtenerPropiedadString(ConstantesAutenticacion.QUERY_ROLES);
        int nivelJerarquico = Integer.parseInt(consultarParametroSistema(propiedades
                .obtenerPropiedadString(ConstantesAutenticacion.NOMBRE_PARAMETRO_NIVEL_JERARQUIA)));
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(queryRoles)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultados = statement.executeQuery()) {
                while (resultados.next()) {
                    Rol rol = new Rol();
                    rol.setIdRol(resultados.getInt(1));
                    rol.setNombre(resultados.getString(2));
                    rol.setGrupos(consultarGruposRol(rol.getIdRol()));
                    rol.setAplicaciones(consultarAplicacionRol(rol.getIdRol()));
                    rolesUsuario.add(rol);
                    consultarRolPadre(rolesUsuario, rol.getIdRol(), nivelJerarquico - 1);
                }
            }
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_004);
            aex.initCause(e);
            throw aex;
        }
        return rolesUsuario;
    }

    /**
     * Lamma y llena de manera recurrente los padres de cada rol hasta el nivel jerarquico parametrizados
     * 
     * @param roles
     *            referencia al objeto que debe ser llenado con roles que se encuentran
     * @param idRol
     *            - Rol al que se le va a consultar el padre
     * @param repeticion
     *            - numero de la iteracion qeu se lleva
     * @throws AutenticacionExcepcion
     * 
     * @author sergio.torres (22/05/2015)
     */
    private void consultarRolPadre(List<Rol> roles, int idRol, int repeticion) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::consultarRolPadre");
        String queryRolPadre = propiedades.obtenerPropiedadString(ConstantesAutenticacion.QUERY_ROL_PADRE);
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(queryRolPadre)) {
            statement.setInt(1, idRol);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt(1));
                    rol.setNombre(rs.getString(2));
                    rol.setGrupos(consultarGruposRol(rol.getIdRol()));
                    rol.setAplicaciones(consultarAplicacionRol(rol.getIdRol()));
                    roles.add(rol);
                    if (repeticion > 0) {
                        consultarRolPadre(roles, rol.getIdRol(), --repeticion);
                    }
                }
            }
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_004);
            aex.initCause(e);
            throw aex;
        }
    }

    /**
     * Permite consultar los grupos alos que pertenece un rol dado
     * 
     * @param idRol
     * @return
     * 
     * @author sergio.torres(22-05-2015)
     */
    private List<String> consultarGruposRol(int rol) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::consultarGruposRol");
        List<String> grupos = new ArrayList<>();
        String queryGrupos = propiedades.obtenerPropiedadString(ConstantesAutenticacion.QUERY_GRUPOS);
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(queryGrupos)) {
            statement.setInt(1, rol);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    grupos.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_004);
            aex.initCause(e);
            throw aex;
        }
        return grupos;
    }

    /**
     * Permite consultar las aplicaciones a las que pertenece un rol, para crear los correspondientes roles genericos para realizar la autorización
     * 
     * @param rol
     * @return Lista con las aplicaciones en las que aplica el rol
     * @throws AutenticacionExcepcion
     */
    private List<String> consultarAplicacionRol(int rol) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::consultarAplicacionRol");
        List<String> aplicaciones = new ArrayList<>();
        String queryAplicaciones = propiedades.obtenerPropiedadString(ConstantesAutenticacion.QUERY_APLICACION_ROL);
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(queryAplicaciones)) {
            statement.setInt(1, rol);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    aplicaciones.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_004);
            aex.initCause(e);
            throw aex;
        }
        return aplicaciones;
    }

    /**
     * Se encarga de consultar un parametro de sistema existente
     * 
     * @param nombreParametro
     * @return String - correspondiente al valor del parametro de sistema
     * 
     * @author sergio.torres (22-mayo-2015)
     * @throws AutenticacionExcepcion
     */
    private String consultarParametroSistema(String nombreParametro) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::consultarParametroSistema");
        String queryParametro = propiedades.obtenerPropiedadString(ConstantesAutenticacion.QUERY_PARAMETRO);
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(queryParametro)) {
            statement.setString(1, nombreParametro);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    // No existe el parametro
                    AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_103);
                    throw aex;
                }
                return rs.getString(1);
            }
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_004);
            aex.initCause(e);
            throw aex;
        }
    }

    /**
     * Se encarga de ejecutar la consulta dada con los par�metros
     * 
     * @param query
     * @param parametros
     * @return ResultSet - para iterar sobre los resultados de la consulta
     * @throws AutenticacionExcepcion
     */
    // private Statement realizarConsulta(String query, Object... parametros) throws AutenticacionExcepcion {
    // try (Connection conn = getDataSource().getConnection();
    // PreparedStatement statement = conn.prepareStatement(query)) {
    // int posicion = 1;
    // for (Object o : parametros) {
    // statement.setObject(posicion++, o);
    // }
    // return statement;
    // } catch (SQLException e) {
    // AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_004);
    // aex.initCause(e);
    // throw aex;
    // }
    // }

    /**
     * Obtiene e inicializa el datatsource para realizar las consultas
     * 
     * @return instancia del datasource utilizada
     * @throws AutenticacionExcepcion
     * 
     * @author sergio.torres (21/05/2015)
     * 
     */
    private DataSource getDataSource() throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::getDataSource");
        try {
            if (datasource == null) {
                InitialContext context = new InitialContext();
                datasource = (DataSource) context.lookup(propiedades
                        .obtenerPropiedadString(ConstantesAutenticacion.DATASOURCE));
            }
        } catch (NamingException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_002);
            aex.initCause(e);
            throw aex;
        }
        return datasource;
    }

    /**
     * 
     * 
     * @param usuario
     * @param contrasena
     * @return
     * @throws AutenticacionExcepcion
     */
    public boolean autenticarLdap(String usuario, String contrasena) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::autenticarLdap");
        // usuario = obtenernombreLdap(usuario);
        LDAP ldapConector = new LDAP();
        // Establecer los datos de conexion
        LdapContexto ldapContext = new LdapContexto();
        ldapContext.putParam(EnumLdapParams.INITIAL_CONTEXT_FACTORY, consultarParametroSistema(propiedades
                .obtenerPropiedadString(ConstantesAutenticacion.PARAMETRO_INITIAL_CONTEXT)));
        ldapContext.putParam(
                EnumLdapParams.PROVIDER_URL,
                ConstantesAutenticacion.PREFIJO_LDAP_JNDI
                        + consultarParametroSistema(propiedades
                                .obtenerPropiedadString(ConstantesAutenticacion.PARAMETRO_URL_PROVIDER)));
        ldapContext.putParam(EnumLdapParams.SECURITY_AUTHENTICATION, consultarParametroSistema(propiedades
                .obtenerPropiedadString(ConstantesAutenticacion.PARAMETRO_SECURITY_AUTHENTICATION)));
        ldapContext.putParam(EnumLdapParams.SECURITY_PRINCIPAL, usuario);
        ldapContext.putParam(EnumLdapParams.SECURITY_CREDENTIALS, contrasena);
        return ldapConector.autenticar(ldapContext);
    }

    /**
     * Se encarga de registrar las actividades de ingreso del usuario
     * 
     * @param usuario
     *            - Usuario que esta intentando ingresar a la aplicacion
     * @param estadoIngreso
     *            - Enumeracion que indica el resultado del ingreso el cual sdebe ser registrado
     * @param ip
     *            - IP del equipo desde el cual se esta intentando ingresar al sistema
     * 
     * @author sergio.torres (01/06/2015) giovanni.velandia (mod 2015-08-12)
     * @throws AutenticacionExcepcion
     * 
     */
    public void registrarIngresoUsuario(Usuario usuario, EnumEstadoIngreso estadoIngreso, String ip,
            String nombreAplicacion) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::registrarIngresoUsuario");
        String insertIngreso = propiedades.obtenerPropiedadString(ConstantesAutenticacion.INSERT_REGISTRO);
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(insertIngreso)) {
            Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTimeInMillis());
            int cont = 1;
            statement.setInt(cont++, usuario.getId());
            statement.setTimestamp(cont++, fechaActual);
            statement.setTimestamp(cont++, fechaActual);
            statement.setString(cont++, ip);
            statement.setInt(cont++, estadoIngreso.getidentificador());
            statement.setString(cont++, nombreAplicacion);
            statement.executeUpdate();
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_002);
            aex.initCause(e);
            throw aex;
        }
    }

    /**
     * Se encarga de validar si ya se cumplieron los intentos fallidos m�ximos para ingreso del usuario
     * 
     * @param usuario
     *            - al cual se le validan los intentos maximos
     * @return <strong>true</strong> cuando a�n no se ha alcanzado la cantidad m�xima de reintentos y se permite el ingreso<br/>
     *         <strong>false</strong> cuando se ha alcanzado la cantidad de intentos permitidos y no se permite el ingreso
     * @throws AutenticacionExcepcion
     * @throws NumberFormatException
     * 
     */
    public boolean validarIntentosFallidosUsuario(Usuario usuario) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::validarIntentosFallidosUsuario");
        // consultar par�metros de sistema
        int reintentosPermitidos = Integer.parseInt(consultarParametroSistema(propiedades
                .obtenerPropiedadString(ConstantesAutenticacion.PARAMETRO_CANTIDAD_INTENTOS_FALLIDOS)));
        int horasReintentos = Integer.parseInt(consultarParametroSistema(propiedades
                .obtenerPropiedadString(ConstantesAutenticacion.PARAMETRO_HORAS_INTENTOS_FALLIDOS)));

        Calendar fechaReintentos = Calendar.getInstance();
        fechaReintentos.add(Calendar.HOUR_OF_DAY, horasReintentos * -1);
        // Consultar la cantidad de ingresos fallidos desde el �ltimo exitoso registrado y cuya fecha sea mayor al calculo realizado con el
        // parametro
        // de sistema
        String insertIngreso = propiedades
                .obtenerPropiedadString(ConstantesAutenticacion.QUERY_CANTIDAD_INGRESOS_FALLIDOS);
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(insertIngreso)) {
            statement.setInt(1, usuario.getId());
            statement.setInt(2, EnumEstadoIngreso.EXITOSO.getidentificador());
            statement.setTimestamp(3, new Timestamp(fechaReintentos.getTimeInMillis()));
            statement.setInt(4, usuario.getId());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int reintentosFallidos = rs.getInt(1);
                    if (reintentosFallidos + 1 == reintentosPermitidos) {
                        // Hay que bloquear al usuario
                        LOGGER.info("ConexionAutenticacion::reintentosFallidos superados");
                        return false;
                    } else if (reintentosFallidos + 1 > reintentosPermitidos) {
                        // El usuario estuvo bloqueado, pero lo desbloquearon, por tanto debe validar los intentos restando los intentos de esos
                        // desbloqueos
                        long numeroDesbloqueos = reintentosFallidos / reintentosPermitidos;
                        long numeroIntentoReal = (reintentosFallidos + 1) - (numeroDesbloqueos * reintentosPermitidos);

                        if (numeroIntentoReal == reintentosPermitidos) {
                            // Hay que bloquear al usuario
                            LOGGER.info("ConexionAutenticacion::reintentosFallidos superados");
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_002);
            aex.initCause(e);
            throw aex;
        }
        return false;
    }

    /**
     * Metodo que se encarga de actualizar el estado del password del usuario
     * 
     * @param usuario
     * @throws AutenticacionExcepcion
     * @author giovanni.velandia
     */
    public void actualizarPasswordUsuario(Usuario usuario) throws AutenticacionExcepcion {
        LOGGER.info("ConexionAutenticacion::actualizarPassworUsuario");
        String modPassUsuario = propiedades.obtenerPropiedadString(ConstantesAutenticacion.MOD_PASS_USUARIO);
        try (Connection conn = getDataSource().getConnection();
                PreparedStatement statement = conn.prepareStatement(modPassUsuario)) {
            int cont = 1;
            statement.setTimestamp(cont++, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            statement.setInt(cont++, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            AutenticacionExcepcion aex = new AutenticacionExcepcion(ErrorAutenticacion.AUTENTICACION_002);
            aex.initCause(e);
            throw aex;
        }

    }

    /**
     * Metodo que se encarga de validar la vigencia de bloqueo del password
     * 
     * @return
     * @throws AutenticacionExcepcion
     */
    public boolean validarVigenciaBloqueo(Usuario usuario) throws AutenticacionExcepcion {

        boolean pwBloqueado = true;
        if (consultarParametroSistema(
                propiedades.obtenerPropiedadString(ConstantesAutenticacion.DESBLOQUEO_PW_AUTOMATICO)).equals(
                ConstantesAutenticacion.VALOR_SI)) {
            // Tiene desbloque por tiempo
            int horasBloqueo = Integer.valueOf(consultarParametroSistema(propiedades
                    .obtenerPropiedadString(ConstantesAutenticacion.CANTIDAD_HORAS_VIGENCIA_BLOQUEO_PW)));

            Calendar fechaInicioBloqueo = UtilFecha.buildCalendar(usuario.getFechaBloqueoPassword());
            fechaInicioBloqueo.add(Calendar.HOUR_OF_DAY, horasBloqueo);
            Date fechaFinBloqueo = fechaInicioBloqueo.getTime();
            Date fechaActual = new Date();
            if (fechaActual.compareTo(fechaFinBloqueo) > 0) {
                pwBloqueado = false;
            }
        }
        return pwBloqueado;
    }
}
