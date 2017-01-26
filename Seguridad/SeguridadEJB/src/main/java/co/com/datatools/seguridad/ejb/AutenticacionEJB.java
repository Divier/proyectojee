package co.com.datatools.seguridad.ejb;

import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoUsuarioDto;
import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto;
import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto.EstadoAutenticacion;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.comun.InfoVinculoRecuperacionDto;
import co.com.datatools.seguridad.ejb.historico.HistoricoUsuarioEJB;
import co.com.datatools.seguridad.entidades.Aplicacion;
import co.com.datatools.seguridad.entidades.EstadoIngreso;
import co.com.datatools.seguridad.entidades.EstadoPassword;
import co.com.datatools.seguridad.entidades.HistoricoUsuario;
import co.com.datatools.seguridad.entidades.IngresoUsuario;
import co.com.datatools.seguridad.entidades.Rol;
import co.com.datatools.seguridad.entidades.SolicitudCambioPassword;
import co.com.datatools.seguridad.entidades.Usuario;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.excepciones.SeguridadRuntimeException;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumEstadoIngresoUsuario;
import co.com.datatools.seguridad.utilidades.EnumEstadoPassword;
import co.com.datatools.seguridad.utilidades.EnumEstadoUsuario;
import co.com.datatools.seguridad.utilidades.EnumObsHistoricoUsuario;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.cifrado.Digester;
import co.com.datatools.util.cifrado.Digester.EnumAlgoritmo;
import co.com.datatools.util.ldap.LDAP;
import co.com.datatools.util.ldap.LdapContexto;
import co.com.datatools.util.ldap.LdapContexto.EnumLdapParams;

/**
 * EJB que contiene los metodos relacionado con la autenticacion de los usuarios
 * 
 * @author Felipe.Martinez
 * 
 */
@Stateless(name = "AutenticacionEJB")
@LocalBean
public class AutenticacionEJB implements IRAutenticacion {

    private final static Logger logger = Logger.getLogger(AutenticacionEJB.class.getName());

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    @EJB
    private ParametrosSeguridadEJB parametrosSeguridadEjb;

    @EJB
    private UsuarioEJB usuarioEjb;

    @EJB
    private HistoricoUsuarioEJB historicoUsuarioEjb;

    @EJB
    private CatalogosSeguridadEJB catalogosSeguridadEJB;

    private static final String LETRAS_MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    public static final String HTML_MEDIA_TYPE = "text/html";

    @EJB
    private TrazabilidadAutorizacionEJB trazabilidadAutorizacionEJB;

    @EJB
    private SeguridadMailSenderEJB seguridadMailSenderEJB;

    @Override
    public ResultadoAutenticacionDto validarEstadoPassword(final String login, final String ipIngreso,
            final String aplicacion) {
        ResultadoAutenticacionDto respuesta = new ResultadoAutenticacionDto();
        respuesta.setEstadoAutenticacion(EstadoAutenticacion.OK);
        logger.debug("AutenticacionEJB.autenticarUsuario()");
        UsuarioDto usuarioDto = usuarioEjb.consultarUsuario(login, true);
        respuesta.setUsuario(usuarioDto);

        // Si contraseña esta bloqueada es porque JAAS lo dejo pasar porque el bloqueo es automatico y ya debe ser levantado
        if (usuarioDto.getEstadoPassword().equals(EnumEstadoPassword.BLOQUEADO.getNombre())) {
            usuarioEjb.actualizarEstadoPwUsuario(login, EnumEstadoPassword.ACTIVO,
                    ConstantesSeguridad.LOGIN_USUARIO_SISTEMA);
        }
        // Validar si esta vencida
        boolean pwVigente = validarPasswordVigente(login, usuarioDto.getFechaModPass(), ipIngreso, aplicacion);
        if (!pwVigente) {
            respuesta.setEstadoAutenticacion(EstadoAutenticacion.PASS_VENCIDO);
        }
        // Verificar contraseña temporal
        if (usuarioDto.getEstadoPassword().equalsIgnoreCase(EnumEstadoPassword.TEMPORAL.getNombre())) {
            respuesta.setEstadoAutenticacion(EstadoAutenticacion.PASS_TEMPORAL);
            registrarIngresoUsuario(login, EnumEstadoIngresoUsuario.FALLIDO_CAMBIO_PW, ipIngreso, aplicacion);
        }
        return respuesta;
    }

    @Override
    public Integer registrarIngresoExitoso(String login, String ipIngreso, String aplicacion) {
        List<IngresoUsuario> sesionAbierta = validarSesionAbierta(login, aplicacion);
        if (sesionAbierta != null && !sesionAbierta.isEmpty()) {
            for (IngresoUsuario ingresoUsuario : sesionAbierta) {
                trazabilidadAutorizacionEJB.registrarSolicitudesAutorizacion(ingresoUsuario.getIdIngresoUsuario());
            }
        }
        Integer idIngreso = registrarIngresoUsuario(login, EnumEstadoIngresoUsuario.EXITOSO, ipIngreso, aplicacion);
        trazabilidadAutorizacionEJB.adicionarSolicitudAutorizacion(idIngreso, null);
        return idIngreso;
    }

    /**
     * Valida en la base de datos si un usuario tiene una sesion de la aplicacion abierta; es decir:algun ingreso exitoso al sistema sin fecha de
     * cierre
     * 
     * @param login
     *            Login del usuario que se va a validar
     * @param aplicacion
     * @return True si el usuario tiene alguna sesion abierta, de lo contrario false
     */
    private List<IngresoUsuario> validarSesionAbierta(String login, String aplicacion) {

        TypedQuery<IngresoUsuario> queryIngresoAbiertos = em
                .createNamedQuery(IngresoUsuario.SQ_INGRESOS_ABIERTOS_BY_USUARIO, IngresoUsuario.class);
        queryIngresoAbiertos.setParameter("login", login);
        queryIngresoAbiertos.setParameter("idEstadoIngreso", EnumEstadoIngresoUsuario.EXITOSO.getId());
        Aplicacion app = em.find(Aplicacion.class,
                Integer.valueOf(catalogosSeguridadEJB.consultarIdAplicacion(aplicacion)));
        queryIngresoAbiertos.setParameter("idApp", app.getIdAplicacion());
        List<IngresoUsuario> ingresosExitososAbiertos = queryIngresoAbiertos.getResultList();

        return ingresosExitososAbiertos;
    }

    /**
     * Registra un nuevo intento fallido del usuario y valida si el usuario ha alcanzado el numero de intentos fallidos parametrizados. Es invocado
     * desde el metodo autenticar luego de que el usuario realiza un nuevo intento fallido. Adicionalmente si alcanza el numero de intentos realiza el
     * bloqueo del usuario
     * 
     * @param login
     *            Login del usuario que realiza el intento fallido
     * @param ipIngreso
     *            Numero de direccion IP del equipo cliente desde el cual se realiza el ingreso
     * @return
     */
    /*
     * private boolean validarAlcanzaIntentosFallidos(String login, String ipIngreso, String aplicacion) { boolean alcanzaIntentos = false; // Validar
     * intentos para bloqueo del usuario int cantidadIntentosPermitidos = Integer.valueOf(parametrosSeguridadEjb
     * .consultarValorParametroSeguridad(EnumParametro.CANTIDAD_INTENTOS_FALLIDOS_PW)); int cantidadHorasValidaIntentos =
     * Integer.valueOf(parametrosSeguridadEjb .consultarValorParametroSeguridad(EnumParametro.CANTIDAD_HORAS_VALIDA_INTENTOS_FALLIDOS_PW));
     * 
     * Date fechaActual = new Date(); Calendar fechaInicioValidacion = Calendar.getInstance(); fechaInicioValidacion.setTime(fechaActual);
     * fechaInicioValidacion.add(Calendar.HOUR_OF_DAY, -cantidadHorasValidaIntentos); Date fechaInicioVal = fechaInicioValidacion.getTime();
     * 
     * TypedQuery<IngresoUsuario> queryIngresoExitoso = em.createNamedQuery( IngresoUsuario.SQ_CONTEO_ULTIMO_INGRESO_BY_USUARIO_FECHAS,
     * IngresoUsuario.class); queryIngresoExitoso.setParameter("login", login); queryIngresoExitoso.setParameter("idEstadoIngreso",
     * EnumEstadoIngresoUsuario.EXITOSO.getId()); queryIngresoExitoso.setParameter("fechaInicio", fechaInicioVal);
     * queryIngresoExitoso.setParameter("fechaFin", fechaActual);
     * 
     * List<IngresoUsuario> ultimosIngresosExitosos = queryIngresoExitoso.getResultList();
     * 
     * IngresoUsuario ultimoIngresoExitoso = null; if (!CollectionUtils.isEmpty(ultimosIngresosExitosos)) { ultimoIngresoExitoso =
     * ultimosIngresosExitosos.get(0); }
     * 
     * Query queryIngresosFallidos = em.createNamedQuery(IngresoUsuario.SQ_CONTEO_INGRESOS_BY_USUARIO_FECHAS);
     * queryIngresosFallidos.setParameter("login", login); queryIngresosFallidos.setParameter("idEstadoIngreso",
     * EnumEstadoIngresoUsuario.FALLIDO_PW_ERRONEO.getId()); queryIngresosFallidos.setParameter("fechaFin", fechaActual);
     * 
     * if (ultimoIngresoExitoso != null) { queryIngresosFallidos.setParameter("fechaInicio", ultimoIngresoExitoso.getFechaInicio()); } else {
     * queryIngresosFallidos.setParameter("fechaInicio", fechaInicioVal); } long intentosFallidos = (Long) queryIngresosFallidos.getSingleResult();
     * 
     * // Registrar el nuevo intento fallido registrarIngresoUsuario(login, EnumEstadoIngresoUsuario.FALLIDO_PW_ERRONEO, ipIngreso, aplicacion); if
     * (intentosFallidos + 1 == cantidadIntentosPermitidos) { // Hay que bloquear al usuario alcanzaIntentos = true; } else if (intentosFallidos + 1 >
     * cantidadIntentosPermitidos) { // El usuario estuvo bloqueado, pero lo desbloquearon, por tanto debe validar los intentos restando los intentos
     * de esos desbloqueos long numeroDesbloqueos = intentosFallidos / cantidadIntentosPermitidos; long numeroIntentoReal = (intentosFallidos + 1) -
     * (numeroDesbloqueos * cantidadIntentosPermitidos);
     * 
     * if (numeroIntentoReal == cantidadIntentosPermitidos) { // Hay que bloquear al usuario alcanzaIntentos = true; } } if (alcanzaIntentos)
     * usuarioEjb.actualizarEstadoPwUsuario(login, EnumEstadoPassword.BLOQUEADO, ConstantesSeguridad.LOGIN_USUARIO_SISTEMA);
     * 
     * return alcanzaIntentos; }
     */
    /**
     * Valida si la contraseña del usuario se encuentra vigente; es decir, no ha alcanzado la fecha de vencimiento
     * 
     * @param login
     *            Login del usuario al que se le valida el vencimiento de la contraseña
     * @param ultimaModificacionPass
     *            Fecha de la ultima modificacion de la contraseña del usuario
     * @param ipIngreso
     *            Numero de direccion IP del equipo cliente desde el cual se realiza el ingreso
     * @return True si la contraseña se encuentra vigente, de lo contrario false si ya esta vencida
     */
    private boolean validarPasswordVigente(String login, Date ultimaModificacionPass, String ipIngreso,
            String aplicacion) {
        boolean pwVigente = true;
        Date fechaActual = new Date();
        Date fechaVencimiento = null;
        if (ultimaModificacionPass != null) {
            int diasVigencia = Integer.valueOf(
                    parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_CANTIDAD_DIAS_VIGENCIA));
            Calendar fechaVencimientoCal = Calendar.getInstance();
            fechaVencimientoCal.setTime(ultimaModificacionPass);
            fechaVencimientoCal.add(Calendar.DAY_OF_MONTH, diasVigencia);
            fechaVencimiento = fechaVencimientoCal.getTime();

            // Verificar contraseña vencida
            if (fechaActual.compareTo(fechaVencimiento) > 0) {
                // Esta vencida la contrasena, se registra ingreso
                pwVigente = false;
                registrarIngresoUsuario(login, EnumEstadoIngresoUsuario.FALLIDO_PW_VENCIDO, ipIngreso, aplicacion);
            }
        }
        return pwVigente;
    }

    /**
     * Realiza la utenticacion de un usuario en el LDAP con las credenciales de autenticación: login y contraseña indicadas
     * 
     * @param login
     *            Login del usuario que realiza la autenticacion
     * @param password
     *            Contraseña con la cual el usuario que realiza la autenticacion
     * @return True si las credenciales de autenticacion son validas en el LDAP, de lo contrario false
     */
    public boolean autenticarUsuarioLDAP(final String login, final String password) {

        LDAP ldapConector = new LDAP();
        // Establecer los datos de conexion
        LdapContexto ldapContext = new LdapContexto();
        ldapContext.putParam(EnumLdapParams.INITIAL_CONTEXT_FACTORY,
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.INITIAL_CONTEXT_FACTORY));
        ldapContext.putParam(EnumLdapParams.PROVIDER_URL, ConstantesSeguridad.PREFIJO_LDAP_JNDI
                + parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PROVIDER_URL));
        ldapContext.putParam(EnumLdapParams.SECURITY_AUTHENTICATION,
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.SECURITY_AUTHENTICATION));
        ldapContext.putParam(EnumLdapParams.SECURITY_PRINCIPAL, login);
        ldapContext.putParam(EnumLdapParams.SECURITY_CREDENTIALS, password);
        return ldapConector.autenticar(ldapContext);

    }

    @Override
    public void cerrarSession(final String login, final Integer idIngresoUsuario) {
        logger.info("AutenticacionEJB::cerrarSession para el idIngreso= " + idIngresoUsuario);
        if (idIngresoUsuario != null) {
            trazabilidadAutorizacionEJB.registrarSolicitudesAutorizacion(idIngresoUsuario);
        } else {
            logger.info("AutenticacionEJB::cerrarSession, no se tiene id de ingreso");
            // se busca el ultimo ingreso del usuario q se encuentre abierto y se cierra

            List<IngresoUsuario> ultimoIngresoAbierto = consultarUltimoIngresoExitosoAbierto(login);
            if (!CollectionUtils.isEmpty(ultimoIngresoAbierto)) {
                trazabilidadAutorizacionEJB
                        .registrarSolicitudesAutorizacion(ultimoIngresoAbierto.get(0).getIdIngresoUsuario());
                logger.infov("AutenticacionEJB::cerrarSession, cierra la sesion del ultimo ingreso con id={0} ",
                        ultimoIngresoAbierto.get(0).getIdIngresoUsuario());
            }
        }
    }

    @Override
    public void cambiarPassword(final String login, final String passwordActual, final String passwordNuevo)
            throws SeguridadException {

        GenericDao<Usuario> usrDao = new GenericDao<>(Usuario.class, em);
        Usuario usuarioBD = usrDao.findUniqueByAttribute("login", login);

        try {
            // Valida que la contrasena actual ingresada coincida con la contrasena almacenada en el sistema para el usuario (FA2)
            if (passwordActual != null) {
                if (!usuarioBD.getPassword().equals(Digester.digest(passwordActual, EnumAlgoritmo.SHA512))) {
                    logger.info("No coincide la contraseña actual ingresada");
                    throw new SeguridadException(CatalogoError.PW_ACTUAL_NO_COINCIDE);
                }
            }
            // Valida que la nueva contrasena cumpla con las politicas de seguridad parametrizadas para la estructura de las contrasenas (FA4)
            if (!validarCumplePoliticasPassword(passwordNuevo)) {
                logger.info("La contraseña no cumple las politicas");
                throw new SeguridadException(CatalogoError.PW_NO_CUMPLE_POLITICAS);
            }
            // Valida que la nueva contrasena no sea igual a contrasenas anteriores segun el parametro
            Integer cantidadPwHistorico = Integer.valueOf(
                    parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.CANTIDAD_PW_HISTORICO));

            String nuevoPwEncritado = Digester.digest(passwordNuevo, EnumAlgoritmo.SHA512);
            boolean pwIgualAnterior = validarConstrasenaContraHistorico(cantidadPwHistorico, nuevoPwEncritado,
                    usuarioBD.getIdUsuario());
            if (pwIgualAnterior) {
                logger.info("La nueva contraseña es igual a la anterior");
                throw new SeguridadException(CatalogoError.PW_NUEVO_IGUAL_ANTERIOR);
            }

            // Actualizar pw del usuario
            usuarioBD.setPassword(nuevoPwEncritado);
            Date fechaActual = new Date();
            usuarioBD.setFechaModificaUsuario(fechaActual);
            usuarioBD.setFechaModificaPassword(fechaActual);

            // Cambiar estado de contraseña a activa
            EstadoPassword estadoPw = new EstadoPassword();
            estadoPw.setIdEstado(EnumEstadoPassword.ACTIVO.getId());
            usuarioBD.setEstadoPassword(estadoPw);
            // Actualizar el usuario
            em.merge(usuarioBD);

            logger.debug("Se modificó la contrasena del usuario de login= " + usuarioBD.getLogin());
            // Crear el historico de la modificacion del password
            UsuarioDetalleDto usuarioDto = usuarioEjb.consultarUsuario(usuarioBD.getLogin(), false);
            usuarioDto.setPassword(usuarioBD.getPassword());

            HistoricoUsuarioDto historicoUsuarioDto = new HistoricoUsuarioDto();
            historicoUsuarioDto.setUsuarioDto(usuarioDto);
            historicoUsuarioDto.setCierraHistoricoAnterior(true);
            StringBuilder desCambios = new StringBuilder();
            desCambios.append(EnumObsHistoricoUsuario.CAMBIO_CONTRASENA.toString());
            desCambios.append(",");
            desCambios.append(EnumObsHistoricoUsuario.CAMBIO_ESTADO_CONTRASENA);
            historicoUsuarioDto.setDescripcionCambio(desCambios.toString());
            historicoUsuarioDto.setUsuarioRealizaCambio(login);// El cambio de la contraseña lo hace el propio usuario

            historicoUsuarioEjb.crearHistorico(historicoUsuarioDto);
        } catch (NoSuchAlgorithmException e) {
            throw new SeguridadRuntimeException(e);
        }
    }

    /**
     * Valida si un password cumple con las politicas de seguridad parametrizadas
     * 
     * @param password
     *            Password plano(sin cifrar) que se va a validar
     * @return True si el password enviado cumple con las politicas, de lo contrario false
     */
    private boolean validarCumplePoliticasPassword(String password) {
        int longitudMinima;
        int longitudMaxima;
        Pattern patronLetras = Pattern.compile("[a-zA-Z]+");
        Pattern patronMayusculas = Pattern.compile("[A-Z]+");
        Pattern patronNumeros = Pattern.compile("[0-9]+");

        longitudMinima = Integer
                .valueOf(parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_LONGITUD_MIN));
        // Validar si cumple politicas de longitud
        if (password.length() < longitudMinima) {
            return false;
        } else {
            longitudMaxima = Integer
                    .valueOf(parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_LONGITUD_MAX));
            if (password.length() > longitudMaxima)
                return false;
        }
        // Validar si cumple politica que contenga letras
        final String obligaLetrasParam = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_LETRAS);
        if (procesarParametroBool(obligaLetrasParam)) {
            Matcher matcherLetras = patronLetras.matcher(password);
            if (!matcherLetras.find()) {
                return false;
            }
        }
        //
        final String obligaMayusculasParam = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_MAYUSCULAS);
        if (procesarParametroBool(obligaMayusculasParam)) {
            Matcher matcherMayusculas = patronMayusculas.matcher(password);
            if (!matcherMayusculas.find()) {
                return false;
            }
        }
        //
        final String obligaNumerosParam = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_NUMEROS);
        if (procesarParametroBool(obligaNumerosParam)) {
            Matcher matcherNumeros = patronNumeros.matcher(password);
            if (!matcherNumeros.find()) {
                return false;
            }
        }
        //
        final String obligaCaracEspecialesParam = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_CARACTERES_ESPECIALES);
        if (procesarParametroBool(obligaCaracEspecialesParam)) {
            // Consultar los caracteres especiales permitidos
            final String especiales = parametrosSeguridadEjb
                    .consultarValorParametroSeguridad(EnumParametro.PW_CARACTERES_ESPECIALES);
            boolean contieneEspeciales = false;
            for (int i = 0; i < password.length(); i++) {
                if (especiales.contains("" + password.charAt(i))) {
                    contieneEspeciales = true;
                    break;
                }
            }
            if (!contieneEspeciales) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valida si el password es valido para el usuario indicado
     * 
     * @param login
     *            identificador del usuario
     * @param password
     *            password a validar
     * @return true si corresponde, false si no corresponde
     * @throws NoSuchAlgorithmException
     */
    private boolean validarPassword(final String login, final String password) throws NoSuchAlgorithmException {
        GenericDao<Usuario> usrDao = new GenericDao<>(Usuario.class, em);
        Usuario usuario = usrDao.findUniqueByAttribute("login", login);
        if (usuario.getPassword().equals(Digester.digest(password, EnumAlgoritmo.SHA512))) {
            return true;
        }
        return false;

    }

    @Override
    public boolean enviarVinculoRecuperacion(InfoVinculoRecuperacionDto infoVinculoRecuperacionDto)
            throws SeguridadException {
        TypedQuery<Usuario> query = em.createNamedQuery(Usuario.SQ_USUARIO_BY_LOGIN, Usuario.class);
        query.setParameter("login", infoVinculoRecuperacionDto.getLogin());
        List<Usuario> usuarios = query.getResultList();
        if (!CollectionUtils.isEmpty(usuarios)) {
            Usuario usuario = usuarios.get(0);
            // Validar autenticacion por LDAP
            if (usuario.isLdap()) {
                logger.info("No se puede recuperar la contraseña- Usuario con LDAP");
                throw new SeguridadException(CatalogoError.USUARIO_CON_LDPA);
            } else {
                // Validar que el usuario este activo
                if (!usuario.getEstadoUsuario().getIdEstado().equals(EnumEstadoUsuario.ACTIVO.getId())) {
                    logger.info("No se puede recuperar la contraseña- Usuario no esta activo");
                    throw new SeguridadException(CatalogoError.USUARIO_NO_ACTIVO);
                } else {
                    // Validar que la contraseña no este bloqueada
                    if (usuario.getEstadoPassword().getIdEstado().equals(EnumEstadoPassword.BLOQUEADO.getId())) {
                        logger.info("No se puede recuperar la contraseña- Usuario con password bloqueado");
                        throw new SeguridadException(CatalogoError.USUARIO_PW_BLOQUEADO);
                    } else {
                        // Validar que no existe otra solicitud de recuperación de contraseña vigente, generada por el mismo usuario
                        TypedQuery<SolicitudCambioPassword> querySolCambio = em.createNamedQuery(
                                SolicitudCambioPassword.SQ_SOLICITUD_ACTIVA_BY_USUARIO, SolicitudCambioPassword.class);
                        querySolCambio.setParameter("idUsuario", usuario.getIdUsuario());
                        querySolCambio.setParameter("activo", true);
                        List<SolicitudCambioPassword> resultadoQuerySolCambio = querySolCambio.getResultList();
                        if (!CollectionUtils.isEmpty(resultadoQuerySolCambio)) {
                            // Hay solicitudes activas para el usuario, verificar si la url de dicha solicitud no esta vencida
                            // Si esta vencida inactivarla y permitir la nueva solicitud de recuperacion, si esta activa y vigente retornar mensaje
                            SolicitudCambioPassword solicitudActiva = resultadoQuerySolCambio.get(0);

                            String vigencia = parametrosSeguridadEjb.consultarValorParametroSeguridad(
                                    EnumParametro.CANTIDAD_HORAS_VIGENCIA_VINCULO_TEMPORAL);

                            Calendar fechaVencimiento = Calendar.getInstance();
                            fechaVencimiento.setTime(solicitudActiva.getFechaSolicitud());
                            fechaVencimiento.add(Calendar.HOUR_OF_DAY, Integer.valueOf(vigencia));

                            Date fechaActual = new Date();
                            if (fechaActual.compareTo(fechaVencimiento.getTime()) > 0) {
                                // Inactivar la solicitud abierta
                                actualizarSolicitudCambioPassword(solicitudActiva.getIdSolicitudCambioPass(), false,
                                        true);
                            } else {
                                logger.info("No se puede recuperar la contraseña- Hay otra solicitud sin cerrar");
                                throw new SeguridadException(CatalogoError.SOLICITUD_RECUPERACION_PW_ACTIVA);
                            }
                        }
                        // Registrar la nueva solicitud
                        String key = RandomStringUtils.randomAlphanumeric(25);
                        try {
                            String urlApp = parametrosSeguridadEjb.consultarValorParametroUrlAplicacion(
                                    EnumParametro.URL_APLICACION, infoVinculoRecuperacionDto.getAplicacion());
                            if (StringUtils.isBlank(urlApp)) {
                                throw new SeguridadException(CatalogoError.ERROR_PARAMETRO_URL_APLICACION);
                            }

                            String urlPaginaRestablecer = urlApp + "/"
                                    + infoVinculoRecuperacionDto.getFlujoRecuperacionPw() + "?"
                                    + infoVinculoRecuperacionDto.getKeyRecuperacion() + "=" + key;

                            enviarCorreoRecuperacion(infoVinculoRecuperacionDto.getLogin(), urlPaginaRestablecer);
                            registrarSolicitudCambioPassword(infoVinculoRecuperacionDto.getLogin(), key);
                        } catch (MessagingException e) {
                            logger.info("No se pudo enviar el correo", e);
                            throw new SeguridadException(CatalogoError.ERROR_ENVIO_CORREO_RECUPERACION_PW);
                        }
                    }
                }
            }
        } else {
            logger.info("Usuario no existe");
            throw new SeguridadException(CatalogoError.USUARIO_NO_EXISTE);
        }
        return false;
    }

    /**
     * Invoca a la utilidad de envio de correo para enviar al correo del usuario, el mensaje con el vinculo de restablecimiento de la contraseña
     * 
     * @param login
     *            Login del usuario al que se le envia el correo
     * @param urlPaginaRestablecer
     *            Vinculo de restablecimiento generado
     * @throws NamingException
     *             Error en el envio del correo
     * @throws MessagingException
     *             Error en el envio del correo
     */
    private void enviarCorreoRecuperacion(String login, String urlPaginaRestablecer) throws MessagingException {
        String vigencia = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.CANTIDAD_HORAS_VIGENCIA_VINCULO_TEMPORAL);

        StringBuilder cuerpoMail = new StringBuilder();
        cuerpoMail.append(
                "<div style='font-size: 0.87em !important;font-family: Verdana !important;margin-left: auto !important;margin-right: auto !important;'>");
        String asuntoMail = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.ASUNTO_CORREO_RECUPERACION_PW);
        String cuerpo = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.CUERPO_CORREO_RECUPERACION_PW);
        String href = "<a href=\"" + urlPaginaRestablecer + "\" target=\"_blank\">" + urlPaginaRestablecer + "</a>";
        cuerpo = MessageFormat.format(cuerpo, href, vigencia);
        cuerpoMail.append(cuerpo);
        cuerpoMail.append("</div>");

        // Obtener correo usuario
        GenericDao<Usuario> solicitudDao = new GenericDao<>(Usuario.class, em);
        Usuario usuarioBD = solicitudDao.findUniqueByAttribute("login", login);
        logger.debug("Usuario envio de correo de recuperacion= " + login + " correo: " + usuarioBD.getEmail());
        String[] direccionesEnvio = { usuarioBD.getEmail() };

        seguridadMailSenderEJB.publicarCorreo(direccionesEnvio, asuntoMail, cuerpoMail.toString(), null);

        logger.debug("Se invoca envio de correo para recuperacion de contraseña usuario= " + login);

    }

    @Override
    public String generarPassword() {
        int longitud;
        final StringBuilder charSet = new StringBuilder();

        longitud = Integer
                .valueOf(parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PW_LONGITUD_MIN));

        final String obligaLetrasParam = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_LETRAS);
        if (procesarParametroBool(obligaLetrasParam))
            charSet.append(LETRAS_MINUSCULAS);
        //
        final String obligaMayusculasParam = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_MAYUSCULAS);
        if (procesarParametroBool(obligaMayusculasParam))
            charSet.append(LETRAS_MAYUSCULAS);
        //
        final String obligaNumerosParam = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_NUMEROS);
        if (procesarParametroBool(obligaNumerosParam))
            charSet.append(NUMEROS);
        //
        final String obligaCaracEspecialesParam = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.PW_OBLIGA_CARACTERES_ESPECIALES);
        if (procesarParametroBool(obligaCaracEspecialesParam)) {
            // Consultar los caracteres especiales permitidos
            final String especiales = parametrosSeguridadEjb
                    .consultarValorParametroSeguridad(EnumParametro.PW_CARACTERES_ESPECIALES);
            charSet.append(especiales);
        }
        // Si no tiene ningun parametro activo los pone todos
        if (charSet.length() == 0) {
            charSet.append(LETRAS_MINUSCULAS);
            charSet.append(LETRAS_MAYUSCULAS);
            charSet.append(NUMEROS);
            // Consultar los caracteres especiales permitidos
            final String especiales = parametrosSeguridadEjb
                    .consultarValorParametroSeguridad(EnumParametro.PW_CARACTERES_ESPECIALES);
            charSet.append(especiales);
        }
        String password = RandomStringUtils.random(longitud, charSet.toString());

        return password;
    }

    public boolean procesarParametroBool(String valorParametro) {
        return valorParametro.equalsIgnoreCase(ConstantesSeguridad.VALOR_SI);
    }

    /**
     * Guarda en la base de datos un registro de ingreso de usuario
     * 
     * @param login
     *            Login del usuario que realiza le ingreso a registrar
     * @param enumEstadoIngreso
     *            Enumerador del estado del ingreso realizado
     * @param ipAddress
     *            Numero de direccion IP del equipo cliente desde el cual se realiza el ingreso
     * @return Identificador con el cual el registro fue almacenado
     */
    private Integer registrarIngresoUsuario(String login, EnumEstadoIngresoUsuario enumEstadoIngreso, String ipAddress,
            String aplicacion) {
        IngresoUsuario ingreso = new IngresoUsuario();
        EstadoIngreso estadoIngreso = new EstadoIngreso();
        estadoIngreso.setIdEstadoIngreso(enumEstadoIngreso.getId());
        ingreso.setEstadoIngreso(estadoIngreso);
        ingreso.setFechaInicio(new Date());
        ingreso.setIpEquipo(ipAddress);
        ingreso.setAplicacion(
                em.find(Aplicacion.class, Integer.valueOf(catalogosSeguridadEJB.consultarIdAplicacion(aplicacion))));
        // Asignar el usuario
        TypedQuery<Usuario> queryUsuario = em.createNamedQuery(Usuario.SQ_USUARIO_BY_LOGIN, Usuario.class);
        queryUsuario.setParameter("login", login);
        ingreso.setUsuario(queryUsuario.getSingleResult());
        // Asignar los roles de ingreso del usuario
        TypedQuery<Rol> queryRoles = em.createNamedQuery(Usuario.SQ_ROLES_USUARIO, Rol.class);
        queryRoles.setParameter("login", login);
        ingreso.setRolList(queryRoles.getResultList());
        em.persist(ingreso);
        logger.debug("Se registró el ingreso con ID= " + ingreso.getIdIngresoUsuario() + " del usuario: " + login
                + "con estado= " + enumEstadoIngreso.getNombre());
        return ingreso.getIdIngresoUsuario();
    }

    @Override
    public void actualizarCorreoElectronico(String login, String nuevoCorreo) throws SeguridadException {
        // Validar que no exista otro usuario con el mismo correo enviado
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_USUARIO_BY_EMAIL, Usuario.class);
        q.setParameter("email", nuevoCorreo.toUpperCase());
        List<Usuario> usuariosConsultados = q.getResultList();
        if (!CollectionUtils.isEmpty(usuariosConsultados) && !usuariosConsultados.get(0).getLogin().equals(login)) {
            logger.info("No se pudo actualizar el correo del usuario con login= " + login + " -"
                    + CatalogoError.CORREO_USUARIO_YA_EXISTE.getCodigoError());
            throw new SeguridadException(CatalogoError.CORREO_USUARIO_YA_EXISTE);
        }
        GenericDao<Usuario> usrDao = new GenericDao<>(Usuario.class, em);
        Usuario usuario = usrDao.findUniqueByAttribute("login", login);
        Date fechaActual = new Date();
        usuario.setFechaModificaUsuario(fechaActual);
        usuario.setEmail(nuevoCorreo);
        // Actualizar el usuario
        em.merge(usuario);
        logger.debug("Se modificó el correo del usuario de login= " + usuario.getLogin() + " a: " + nuevoCorreo);
    }

    /**
     * Actualiza una solicitud de cambio de contraseña con el estado enviado
     * 
     * @param idSolicitud
     *            identificador de la solicitud a actualizar
     * @param estado
     *            Estado que sera asignado a la solicitud
     * @param cierraSolicitud
     *            Indica si se debe cerrar o no la solicitud, si se cierra se asigna la fecha del sistema como fecha de cierre
     */
    private void actualizarSolicitudCambioPassword(Long idSolicitud, boolean estado, boolean cierraSolicitud) {
        SolicitudCambioPassword solicitud = em.find(SolicitudCambioPassword.class, idSolicitud);
        solicitud.setSolicitudActiva(estado);
        if (cierraSolicitud) {
            solicitud.setFechaCierreSolicitud(new Date());
        }
        em.merge(solicitud);
        logger.debug("Actualiza la solicitud de cambio de password con Id= " + idSolicitud + " estado: " + estado
                + " cierra oslicitud?: " + cierraSolicitud);
    }

    /**
     * Guarda una solicitud de cambio de contraseña para un usuario con el codigo de verificacion enviado
     * 
     * @param login
     *            Login del usuario que realiza la solicitud
     * @param codigoVerificacion
     *            Codigo de verificacion de la solicitud
     */
    private void registrarSolicitudCambioPassword(String login, String codigoVerificacion) {
        SolicitudCambioPassword solicitud = new SolicitudCambioPassword();
        solicitud.setCodigoVerificacion(codigoVerificacion);
        solicitud.setFechaSolicitud(new Date());
        solicitud.setSolicitudActiva(true);
        GenericDao<Usuario> usrDao = new GenericDao<>(Usuario.class, em);
        solicitud.setUsuario(usrDao.findUniqueByAttribute("login", login));
        em.persist(solicitud);
        logger.debug("Se registra la solicitud de cambio de contraseña con ID= " + solicitud.getIdSolicitudCambioPass()
                + " para el usuario de login: " + login);

    }

    @Override
    public void restablecerPassword(String login, String keyRecuperacion, String correo, String passwordNuevo)
            throws SeguridadException {
        TypedQuery<Usuario> queryUsuarioSolicitud = em.createNamedQuery(SolicitudCambioPassword.SQ_USUARIO_SOLICITUD,
                Usuario.class);
        queryUsuarioSolicitud.setParameter("codigo", keyRecuperacion);
        List<Usuario> usuarios = queryUsuarioSolicitud.getResultList();
        Usuario usuarioBD = usuarios.get(0);
        // Valida que el usuario enviado corresponda con el de la solicitud de recuperacion
        if (!usuarioBD.getLogin().equals(login)) {
            throw new SeguridadException(CatalogoError.ERROR_USUARIO_RECUPERACION_PW);
        }
        // Validamos que el ususario se encuentre activo
        if (!usuarioBD.getEstadoUsuario().getIdEstado().equals(EnumEstadoUsuario.ACTIVO.getId())) {
            logger.info("El usuario no se encuentra activo");
            throw new SeguridadException(CatalogoError.USUARIO_NO_ACTIVO);
        }

        if (!usuarioBD.getEmail().equalsIgnoreCase(correo)) {
            logger.info("No coincide el correo ingresado con el almacenado para el usuario");
            throw new SeguridadException(CatalogoError.CORREO_NO_COINCIDE);
        }
        try {
            // Valida que la nueva contrasena cumpla con las politicas de seguridad parametrizadas para la estructura de las contrasenas
            if (!validarCumplePoliticasPassword(passwordNuevo)) {
                logger.info("La contraseña no cumple las politicas");
                throw new SeguridadException(CatalogoError.PW_NO_CUMPLE_POLITICAS);
            }
            // Valida que la nueva contrasena no sea igual a contrasenas anteriores segun el parametro
            Integer cantidadPwHistorico = Integer.valueOf(
                    parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.CANTIDAD_PW_HISTORICO));

            String nuevoPwEncritado = Digester.digest(passwordNuevo, EnumAlgoritmo.SHA512);
            boolean pwIgualAnterior = validarConstrasenaContraHistorico(cantidadPwHistorico, nuevoPwEncritado,
                    usuarioBD.getIdUsuario());
            if (pwIgualAnterior) {
                logger.info("La nueva contraseña es igual a la anterior");
                throw new SeguridadException(CatalogoError.PW_NUEVO_IGUAL_ANTERIOR);
            }

            // Actualizar pw del usuario
            usuarioBD.setPassword(nuevoPwEncritado);
            Date fechaActual = new Date();
            usuarioBD.setFechaModificaUsuario(fechaActual);
            usuarioBD.setFechaModificaPassword(fechaActual);

            // Cambiar estado de contraseña a activa
            EstadoPassword estadoPw = new EstadoPassword();
            estadoPw.setIdEstado(EnumEstadoPassword.ACTIVO.getId());
            usuarioBD.setEstadoPassword(estadoPw);
            // Actualizar el usuario
            em.merge(usuarioBD);
            logger.debug("Se modificó la contrasena del usuario de login= " + usuarioBD.getLogin());
            // Cerrar la solicitud de recuperacion
            TypedQuery<SolicitudCambioPassword> querySolicitud = em
                    .createNamedQuery(SolicitudCambioPassword.SQ_SOLICITUD_BY_CODIGO, SolicitudCambioPassword.class);
            querySolicitud.setParameter("codigo", keyRecuperacion);
            List<SolicitudCambioPassword> solicitudes = querySolicitud.getResultList();
            SolicitudCambioPassword solicitud = solicitudes.get(0);
            actualizarSolicitudCambioPassword(solicitud.getIdSolicitudCambioPass(), false, true);

            logger.debug("Se cerró la solicitud de recuperacion con ID= " + solicitud.getIdSolicitudCambioPass());
            // Crear el historico de la modificacion del password
            UsuarioDetalleDto usuarioDto = usuarioEjb.consultarUsuario(usuarioBD.getLogin(), false);
            usuarioDto.setPassword(usuarioBD.getPassword());

            HistoricoUsuarioDto historicoUsuarioDto = new HistoricoUsuarioDto();
            historicoUsuarioDto.setUsuarioDto(usuarioDto);
            historicoUsuarioDto.setCierraHistoricoAnterior(true);
            StringBuilder desCambios = new StringBuilder();
            desCambios.append(EnumObsHistoricoUsuario.CAMBIO_CONTRASENA.toString());
            desCambios.append(",");
            desCambios.append(EnumObsHistoricoUsuario.CAMBIO_ESTADO_CONTRASENA);
            historicoUsuarioDto.setDescripcionCambio(desCambios.toString());
            historicoUsuarioDto.setUsuarioRealizaCambio(usuarioBD.getLogin());// El restablecimiento de la contraseña lo hace el propio usuario

            historicoUsuarioEjb.crearHistorico(historicoUsuarioDto);
        } catch (NoSuchAlgorithmException e) {
            throw new SeguridadRuntimeException(e);
        }

    }

    private boolean validarConstrasenaContraHistorico(int cantidadPwHistorico, String nuevoPwEncritado,
            Integer idUsuario) {
        Query query = em.createNamedQuery(HistoricoUsuario.SQ_HISTORICO_PASSWORD_BY_USUARIO);
        query.setParameter("idUsuario", idUsuario);
        @SuppressWarnings("unchecked")
        List<Object[]> resultado = query.setMaxResults(cantidadPwHistorico).getResultList();

        if (!CollectionUtils.isEmpty(resultado)) {
            for (Object[] pwAnterior : resultado) {
                String pw = (String) pwAnterior[0];
                if (pw.equalsIgnoreCase(nuevoPwEncritado)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean verificarSolicitudRecuperacionAbierta(String keyRecuperacion) {
        // Verifica si el codigo de verificacion corresponde a una solicitud de recuperacion valida
        boolean esValido = true;
        TypedQuery<SolicitudCambioPassword> querySolicitudesCodigo = em
                .createNamedQuery(SolicitudCambioPassword.SQ_SOLICITUD_BY_CODIGO, SolicitudCambioPassword.class);
        querySolicitudesCodigo.setParameter("codigo", keyRecuperacion);
        List<SolicitudCambioPassword> solicitudes = querySolicitudesCodigo.getResultList();
        if (!CollectionUtils.isEmpty(solicitudes)) {
            // Verifica que el vinculo de recuperacion no haya sido utilizado previamente por el usuario para restablecer la contraseña
            if (solicitudes.get(0).getFechaCierreSolicitud() != null) {
                // Ya ha sido cerrado
                esValido = false;
            }
        } else {
            esValido = false;
        }
        return esValido;
    }

    @Override
    public boolean verificarSolicitudRecuperacionVencida(String keyRecuperacion) {
        boolean solicitudVencida = false;
        String vigencia = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.CANTIDAD_HORAS_VIGENCIA_VINCULO_TEMPORAL);

        TypedQuery<SolicitudCambioPassword> querySolicitudesCodigo = em
                .createNamedQuery(SolicitudCambioPassword.SQ_SOLICITUD_BY_CODIGO, SolicitudCambioPassword.class);
        querySolicitudesCodigo.setParameter("codigo", keyRecuperacion);
        List<SolicitudCambioPassword> solicitudes = querySolicitudesCodigo.getResultList();
        SolicitudCambioPassword solicitud = solicitudes.get(0);

        Calendar fechaVencimiento = Calendar.getInstance();
        fechaVencimiento.setTime(solicitud.getFechaSolicitud());
        fechaVencimiento.add(Calendar.HOUR_OF_DAY, Integer.valueOf(vigencia));
        Date fechaActual = new Date();
        if (fechaActual.compareTo(fechaVencimiento.getTime()) > 0) {
            // La solicitud esta vencida, si aun esta activa cambiarla a inactiva
            if (solicitud.getSolicitudActiva()) {
                actualizarSolicitudCambioPassword(solicitud.getIdSolicitudCambioPass(), false, true);
            }
            solicitudVencida = true;
        }
        return solicitudVencida;
    }

    @Override
    public boolean probarConexionLDAP(String ipServidor, String login, String password) {
        LDAP ldapConector = new LDAP();
        // Establecer los datos de conexion
        LdapContexto ldapContext = new LdapContexto();
        ldapContext.putParam(EnumLdapParams.INITIAL_CONTEXT_FACTORY,
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.INITIAL_CONTEXT_FACTORY));
        ldapContext.putParam(EnumLdapParams.PROVIDER_URL, ConstantesSeguridad.PREFIJO_LDAP_JNDI + ipServidor);
        ldapContext.putParam(EnumLdapParams.SECURITY_AUTHENTICATION,
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.SECURITY_AUTHENTICATION));
        ldapContext.putParam(EnumLdapParams.SECURITY_PRINCIPAL, login);
        ldapContext.putParam(EnumLdapParams.SECURITY_CREDENTIALS, password);

        return ldapConector.autenticar(ldapContext);
    }

    private List<IngresoUsuario> consultarUltimoIngresoExitosoAbierto(String login) {
        TypedQuery<IngresoUsuario> queryIngresoAbiertos = em
                .createNamedQuery(IngresoUsuario.SQ_ULTIMO_INGRESO_BY_USUARIO, IngresoUsuario.class);
        queryIngresoAbiertos.setParameter("login", login);
        queryIngresoAbiertos.setParameter("idEstadoIngreso", EnumEstadoIngresoUsuario.EXITOSO.getId());
        List<IngresoUsuario> ultimoIngresoAbierto = queryIngresoAbiertos.getResultList();
        return ultimoIngresoAbierto;
    }

}
