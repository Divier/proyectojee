package co.com.datatools.seguridad.ejb;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.xml.sax.SAXException;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoUsuarioDto;
import co.com.datatools.seguridad.dto.autenticacion.IngresoDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.seguridad.dto.comun.ConsultaIngresoDto;
import co.com.datatools.seguridad.dto.comun.InfoAutorizacion;
import co.com.datatools.seguridad.ejb.historico.HistoricoUsuarioEJB;
import co.com.datatools.seguridad.entidades.EstadoPassword;
import co.com.datatools.seguridad.entidades.EstadoUsuario;
import co.com.datatools.seguridad.entidades.Grupo;
import co.com.datatools.seguridad.entidades.HistoricoUsuario;
import co.com.datatools.seguridad.entidades.IngresoUsuario;
import co.com.datatools.seguridad.entidades.Rol;
import co.com.datatools.seguridad.entidades.SolicitudCambioPassword;
import co.com.datatools.seguridad.entidades.Usuario;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.excepciones.SeguridadRuntimeException;
import co.com.datatools.seguridad.helper.GrupoHelper;
import co.com.datatools.seguridad.helper.RolesHelper;
import co.com.datatools.seguridad.helper.UsuarioHelper;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.seguridad.util.ResultadoLdap;
import co.com.datatools.seguridad.util.XmlSchemaValidatorUtil;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumEstadoPassword;
import co.com.datatools.seguridad.utilidades.EnumEstadoUsuario;
import co.com.datatools.seguridad.utilidades.EnumObsHistoricoUsuario;
import co.com.datatools.seguridad.utilidades.EnumParametro;
import co.com.datatools.util.GenericDao;
import co.com.datatools.util.ObjectToXML.Customized;
import co.com.datatools.util.cifrado.Digester;
import co.com.datatools.util.cifrado.Digester.EnumAlgoritmo;
import co.com.datatools.util.ldap.LDAP;
import co.com.datatools.util.ldap.LdapContexto;
import co.com.datatools.util.ldap.LdapContexto.EnumLdapParams;

/**
 * EJB que provee los metodos para realizar las operaciones CRUD sobre la entidad Usuario
 * 
 * @author claudia.rodriguez
 */
@Stateless(name = "UsuarioEJB")
@LocalBean
public class UsuarioEJB implements IRUsuario {

    private final static Logger logger = Logger.getLogger(UsuarioEJB.class.getName());

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    @EJB
    private AutenticacionEJB autenticacionEjb;

    @EJB
    private CatalogosSeguridadEJB catalogoEjb;

    @EJB
    private HistoricoUsuarioEJB historicoUsuarioEjb;

    @EJB
    private RolEJB rolEjb;

    @EJB
    private ParametrosSeguridadEJB parametrosSeguridadEjb;

    @EJB
    private RecursoOperacionEJB recursoOperacionEJB;

    @EJB
    private TrazabilidadAutorizacionEJB trazabilidadAutorizacionEJB;

    @EJB
    private SeguridadMailSenderEJB seguridadMailSenderEJB;

    @Resource
    private EJBContext context;

    public static final String HTML_MEDIA_TYPE = "text/html";

    public UsuarioEJB() {
        logger.debug("UsuarioEJB::UsuarioEJB");
    }

    @Override
    public List<UsuarioDto> consultarUsuarios(UsuarioDetalleDto usuarioDto, List<Integer> idsUsuario) {
        System.out.println(context.getCallerPrincipal().getName());
        List<Integer> idsRoles = new ArrayList<Integer>();
        List<UsuarioDto> resultado = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        GenericDao<Usuario> usuarioDao = new GenericDao<>(Usuario.class, em);
        Map<String, Object> params = new HashMap<>(5);
        sql.append("SELECT DISTINCT u FROM Usuario u");
        boolean fitroRoles = false;
        if (!CollectionUtils.isEmpty(usuarioDto.getRoles())) {
            fitroRoles = true;
        }
        if (fitroRoles) {
            sql.append(" JOIN u.rolList r ");
            // cargar los ids de los roles en la lista
            for (RolDto rolFiltro : usuarioDto.getRoles()) {
                idsRoles.add(rolFiltro.getIdRol());
            }
        }
        sql.append(" WHERE 1=1");
        if (!CollectionUtils.isEmpty(idsUsuario)) {
            sql.append(" AND u.idUsuario IN(:idsUsuario)");
            params.put("idsUsuario", idsUsuario);
        }

        if (fitroRoles) {
            sql.append(" AND r.idRol IN(:idRoles)");
            params.put("idRoles", idsRoles);
        }
        if (StringUtils.isNotBlank(usuarioDto.getLogin())) {
            sql.append(" AND u.login LIKE :login");
            params.put("login", "%" + usuarioDto.getLogin() + "%");
        }
        if (StringUtils.isNotBlank(usuarioDto.getNombres())) {
            sql.append(" AND u.nombre LIKE :nombres");
            params.put("nombres", "%" + usuarioDto.getNombres() + "%");
        }
        if (StringUtils.isNotBlank(usuarioDto.getApellidos())) {
            sql.append(" AND u.apellido LIKE :apellidos");
            params.put("apellidos", "%" + usuarioDto.getApellidos() + "%");
        }
        if (StringUtils.isNotBlank(usuarioDto.getEstadoUsuario())) {
            sql.append(" AND u.estadoUsuario.idEstado = :idEstado");
            params.put("idEstado", Integer.valueOf(usuarioDto.getEstadoUsuario()));
        }
        if (StringUtils.isNotBlank(usuarioDto.getEmail())) {
            sql.append(" AND u.email LIKE :email");
            params.put("email", usuarioDto.getEmail());
        }

        List<Usuario> resultadoConsulta = usuarioDao.buildAndExecuteQuery(sql.toString(), params);
        UsuarioHelper usuarioHelper = new UsuarioHelper();
        for (Usuario usuario : resultadoConsulta) {
            if (!usuario.getLogin().toUpperCase().equals(ConstantesSeguridad.LOGIN_USUARIO_SUPER_ADMIN.toUpperCase()))
                resultado.add(usuarioHelper.toDto(usuario, new UsuarioDetalleDto()));
        }

        logger.debug("Se consultaron " + resultado.size() + " usuarios");
        return resultado;
    }

    @Override
    public UsuarioDetalleDto consultarUsuario(String login) {
        logger.debug("UsuarioEJB.consultarUsuario(String)");
        UsuarioDetalleDto usuarioDetalleDto = null;

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT u FROM Usuario u");
        jpql.append(" WHERE u.login = :login");

        Query query = em.createQuery(jpql.toString());
        query.setParameter("login", login);

        @SuppressWarnings("unchecked")
        List<Usuario> usuarios = query.getResultList();
        if (usuarios != null && !usuarios.isEmpty()) {
            usuarioDetalleDto = (UsuarioDetalleDto) UsuarioHelper.toDto(usuarios.get(0), null);
        }
        return usuarioDetalleDto;
    }

    @Override
    public UsuarioDetalleDto consultarUsuario(final String login, boolean soloRolesEditables) {
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_USUARIO_BY_LOGIN, Usuario.class);
        q.setParameter("login", login);
        List<Usuario> resultUsr = q.getResultList();

        UsuarioDetalleDto detalle = new UsuarioDetalleDto();
        UsuarioHelper usuarioHelper = new UsuarioHelper();
        if (!CollectionUtils.isEmpty(resultUsr)) {
            Usuario usuarioConsultado = resultUsr.get(0);
            usuarioConsultado.setEstadoPassword(em.find(EstadoPassword.class, usuarioConsultado.getEstadoPassword()
                    .getIdEstado()));
            usuarioConsultado.setEstadoUsuario(em.find(EstadoUsuario.class, usuarioConsultado.getEstadoUsuario()
                    .getIdEstado()));
            usuarioHelper.toDto(usuarioConsultado, detalle);
        } else {
            logger.debug("No se encontró el usuario con login= " + login);
            return null;
        }

        List<RolDto> rolesUsuario = new ArrayList<>();
        TypedQuery<Rol> query = em.createNamedQuery(Usuario.SQ_ROLES_USUARIO, Rol.class);
        query.setParameter("login", login);
        List<Rol> result = query.getResultList();
        boolean incluirRol;
        if (!CollectionUtils.isEmpty(result)) {
            RolesHelper helper = new RolesHelper();
            for (Rol rol : result) {
                incluirRol = true;
                if (soloRolesEditables
                        && (rol.getNombre().equals(ConstantesSeguridad.NOMBRE_ROL_PUBLICO) || rol.getNombre().equals(
                                ConstantesSeguridad.NOMBRE_ROL_SUPER_ADMIN))) {
                    incluirRol = false;
                }
                if (incluirRol) {
                    RolDto rolDto = new RolDetalleDto();
                    helper.toDto(rol, rolDto);
                    rolesUsuario.add(rolDto);
                }
            }

        }
        detalle.setRoles(rolesUsuario);

        List<GrupoDto> gruposUsuario = new ArrayList<>();
        TypedQuery<Grupo> queryGr = em.createNamedQuery(Usuario.SQ_GRUPOS_USUARIO, Grupo.class);
        queryGr.setParameter("login", login);
        List<Grupo> resultGr = queryGr.getResultList();
        if (!CollectionUtils.isEmpty(resultGr))
            gruposUsuario = new GrupoHelper().toListDto(resultGr);
        detalle.setGrupos(gruposUsuario);
        logger.debug("Se encontró el usuario con login= " + login + " y se consultó su detalle");
        return detalle;
    }

    @Override
    public Integer registrarUsuario(final UsuarioDetalleDto usuario) throws SeguridadException {
        // Se normaliza el login del usuario
        usuario.setLogin(usuario.getLogin().toLowerCase());
        // Validar que no exista otro usuario con el mismo correo enviado
        TypedQuery<Usuario> queryEmail = em.createNamedQuery(Usuario.SQ_USUARIO_BY_EMAIL, Usuario.class);
        queryEmail.setParameter("email", usuario.getEmail());
        List<Usuario> usuariosConsultados = queryEmail.getResultList();
        if (!CollectionUtils.isEmpty(usuariosConsultados)) {
            logger.info("No se pudo registrar el usuario con login= " + usuario.getLogin() + " -"
                    + CatalogoError.CORREO_USUARIO_YA_EXISTE.getCodigoError());
            throw new SeguridadException(CatalogoError.CORREO_USUARIO_YA_EXISTE);
        }
        if (validarExisteLogin(usuario.getLogin())) {
            logger.info("No se pudo registrar el usuario con login= " + usuario.getLogin() + " -"
                    + CatalogoError.USUARIO_YA_EXISTE);
            throw new SeguridadException(CatalogoError.USUARIO_YA_EXISTE);

        }

        // Transformar el Dto en Entidad
        Usuario usuarioCrear = new UsuarioHelper().toEntity(usuario, new Usuario());
        // Estados del usuario y del password
        EstadoPassword estadoPw = new EstadoPassword();
        String pw = "";
        if (!usuarioCrear.isLdap()) {
            // Generar password temporal
            estadoPw.setIdEstado(EnumEstadoPassword.TEMPORAL.getId());
            pw = autenticacionEjb.generarPassword();
            try {
                usuarioCrear.setPassword(Digester.digest(pw, EnumAlgoritmo.SHA512));
            } catch (NoSuchAlgorithmException e) {
                throw new SeguridadRuntimeException(e);
            }
        } else {
            estadoPw.setIdEstado(EnumEstadoPassword.ACTIVO.getId());
        }
        usuarioCrear.setEstadoPassword(estadoPw);
        EstadoUsuario estadoActivo = new EstadoUsuario();
        estadoActivo.setIdEstado(EnumEstadoUsuario.ACTIVO.getId());
        usuarioCrear.setEstadoUsuario(estadoActivo);

        // Fecha de inicio de activacion del usuario y fecha de modificacion: fecha del sistema
        Date fechaActual = new Date();
        usuarioCrear.setFechaModificaUsuario(fechaActual);
        usuarioCrear.setFechaInicioUsuario(fechaActual);

        // Asignar los roles a la entidad, por defecto siempre se asigna el rol PUBLICO
        List<Rol> rolList = new ArrayList<>();
        if (usuario.getRoles() != null) {
            for (RolDto rolDto : usuario.getRoles()) {
                Rol rolConsultado = em.find(Rol.class, rolDto.getIdRol());
                rolList.add(rolConsultado);
            }
        }
        TypedQuery<Rol> queryRolNombre = em.createNamedQuery(Rol.SQ_ROL_BY_NOMBRE, Rol.class);
        queryRolNombre.setParameter("nombre", ConstantesSeguridad.NOMBRE_ROL_PUBLICO);
        Rol rolPublico = queryRolNombre.getSingleResult();
        rolList.add(rolPublico);
        usuarioCrear.setRolList(rolList);

        // Asignar los grupos a la entidad
        List<Grupo> grupoList = new ArrayList<>();
        if (usuario.getGrupos() != null) {
            for (GrupoDto grupoDto : usuario.getGrupos()) {
                Grupo grupoConsultado = em.find(Grupo.class, grupoDto.getIdGrupo());
                grupoList.add(grupoConsultado);
            }
        }
        usuarioCrear.setGrupoList(grupoList);
        // Persistir usuario
        em.persist(usuarioCrear);
        logger.debug("Se registró el usuario con login= " + usuarioCrear.getLogin());
        // Persistir el historico del usuario
        UsuarioDetalleDto usuarioDto = consultarUsuario(usuarioCrear.getLogin(), false);
        usuarioDto.setPassword(usuarioCrear.getPassword());
        HistoricoUsuarioDto historicoUsuarioDto = new HistoricoUsuarioDto();
        historicoUsuarioDto.setUsuarioDto(usuarioDto);
        historicoUsuarioDto.setDescripcionCambio(EnumObsHistoricoUsuario.CREACION.toString());
        historicoUsuarioEjb.crearHistorico(historicoUsuarioDto);
        // Enviar correo de creacion del usuario
        enviarCorreoRegistroUsuario(usuario, pw);
        return usuarioCrear.getIdUsuario();
    }

    @Override
    public void eliminarUsuario(final Integer idUsuario) throws SeguridadException {
        // validar que el usuario no tenga ingresos en el sistema
        TypedQuery<IngresoUsuario> query = em.createNamedQuery(IngresoUsuario.SQ_INGRESO_BY_USUARIO,
                IngresoUsuario.class);
        query.setParameter("idUsuario", idUsuario);
        List<IngresoUsuario> ingresos = query.getResultList();
        if (!CollectionUtils.isEmpty(ingresos)) {
            logger.info("No se pudo eliminar el usuario con Id= " + idUsuario + " -"
                    + CatalogoError.USUARIO_VS_INGRESOS.getDescError());
            throw new SeguridadException(CatalogoError.USUARIO_VS_INGRESOS);
        }
        // Validar que el usuario no tenga solicitudes de recuperacion de contraseña
        TypedQuery<SolicitudCambioPassword> querySolicitudesPw = em.createNamedQuery(
                SolicitudCambioPassword.SQ_SOLICITUD_BY_USUARIO, SolicitudCambioPassword.class);
        querySolicitudesPw.setParameter("idUsuario", idUsuario);
        List<SolicitudCambioPassword> solicitudesPw = querySolicitudesPw.getResultList();
        if (!CollectionUtils.isEmpty(solicitudesPw)) {
            logger.info("No se pudo eliminar el usuario con Id= " + idUsuario + " -"
                    + CatalogoError.USUARIO_VS_SOLICITUDES_RECUPERACION_PW.getDescError());
            throw new SeguridadException(CatalogoError.USUARIO_VS_SOLICITUDES_RECUPERACION_PW);
        }

        Usuario entidadUsuario = em.find(Usuario.class, idUsuario);
        // Eliminar el historico
        historicoUsuarioEjb.eliminarHistoricoUsuario(entidadUsuario);
        // Eliminar el usuario
        em.remove(entidadUsuario);
        logger.debug("Se eliminó el usuario con Id= " + idUsuario);
    }

    @Override
    public void actualizarUsuario(final UsuarioDetalleDto usuarioDto) throws SeguridadException {
        // Validar que no exista otro usuario con el mismo correo enviado
        TypedQuery<Usuario> queryEmail = em.createNamedQuery(Usuario.SQ_USUARIO_BY_EMAIL, Usuario.class);
        queryEmail.setParameter("email", usuarioDto.getEmail());
        List<Usuario> usuariosConsultados = queryEmail.getResultList();
        if (!CollectionUtils.isEmpty(usuariosConsultados)
                && !usuariosConsultados.get(0).getIdUsuario().equals(usuarioDto.getId())) {
            logger.info("No se pudo actualizar el usuario con login= " + usuarioDto.getLogin() + " -"
                    + CatalogoError.CORREO_USUARIO_YA_EXISTE.getCodigoError());
            throw new SeguridadException(CatalogoError.CORREO_USUARIO_YA_EXISTE);
        }

        // Resolver la descripcion de todos los cambios del usuario para el historico
        String descripcionCambio = obtenerDescripcionCambiosUsuario(usuarioDto);

        Date fechaActual = new Date();
        // Transformar el DTO modificado a entidad, utilizando como base los datos basicos de la entidad consultada
        Usuario usuario = em.find(Usuario.class, usuarioDto.getId());
        UsuarioHelper helper = new UsuarioHelper();
        helper.toEntity(usuarioDto, usuario);
        // Asignar los estados a la entidad y la fecha de modificacion
        usuario.setFechaModificaUsuario(fechaActual);
        usuario.setEstadoUsuario(em.getReference(EstadoUsuario.class, Integer.valueOf(usuarioDto.getIdEstadoUsuario())));
        usuario.setEstadoPassword(usuario.getEstadoPassword());

        List<Rol> rolList = new ArrayList<>();
        for (RolDto rolDto : usuarioDto.getRoles()) {
            Rol rolConsultado = em.find(Rol.class, rolDto.getIdRol());
            rolList.add(rolConsultado);
        }
        // Volver a agregarle el rol PUBLICO
        TypedQuery<Rol> queryRolNombre = em.createNamedQuery(Rol.SQ_ROL_BY_NOMBRE, Rol.class);
        queryRolNombre.setParameter("nombre", ConstantesSeguridad.NOMBRE_ROL_PUBLICO);
        Rol rolPublico = queryRolNombre.getSingleResult();
        rolList.add(rolPublico);
        usuario.setRolList(rolList);

        // Datos de los grupos
        List<Grupo> grupoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(usuarioDto.getGrupos())) {
            for (GrupoDto grupoDto : usuarioDto.getGrupos()) {
                Grupo grupoConsultado = em.find(Grupo.class, grupoDto.getIdGrupo());
                grupoList.add(grupoConsultado);
            }
        }
        usuario.setGrupoList(grupoList);

        // Validar si el estado es cancelado y poner fecha fin al usuario
        if (usuario.getEstadoUsuario().getIdEstado() == EnumEstadoUsuario.CANCELADO.getId()) {
            usuario.setFechaFinUsuario(fechaActual);
        }

        // Actualizar el usuario
        em.merge(usuario);
        logger.debug("Se modificó el usuario con login= " + usuario.getLogin());

        UsuarioDetalleDto usuarioDtoHistorico = consultarUsuario(usuario.getLogin(), false);
        usuarioDtoHistorico.setPassword(usuario.getPassword()); // Asignar el password al dto para que quede en el historico
        HistoricoUsuarioDto historicoUsuarioDto = new HistoricoUsuarioDto();// Crear el historico de la actualizacion
        historicoUsuarioDto.setUsuarioDto(usuarioDtoHistorico);
        historicoUsuarioDto.setCierraHistoricoAnterior(true);
        historicoUsuarioDto.setDescripcionCambio(descripcionCambio);
        historicoUsuarioEjb.crearHistorico(historicoUsuarioDto);
    }

    private String obtenerDescripcionCambiosUsuario(UsuarioDetalleDto usuarioDtoModificado) {
        StringBuilder desCambios = new StringBuilder();
        Usuario usuario = em.find(Usuario.class, usuarioDtoModificado.getId());

        if (!usuarioDtoModificado.getIdEstadoUsuario().equals(usuario.getEstadoUsuario().getIdEstado().toString())) {// Cambio del estado del usuario
            desCambios.append(EnumObsHistoricoUsuario.CAMBIO_ESTADO_USUARIO.toString());
        }

        if ((!usuarioDtoModificado.getNombres().equals(usuario.getNombre()))
                || (!usuarioDtoModificado.getApellidos().equals(usuario.getApellido()))
                || (!usuarioDtoModificado.getEmail().equals(usuario.getEmail()))) {// Cambiaron los datos basicos
            if (desCambios.length() > 0) {
                desCambios.append(",");
            }
            desCambios.append(EnumObsHistoricoUsuario.CAMBIO_DATOS_BASICOS.toString());
        }

        List<Integer> idsGruposAnteriores = new ArrayList<Integer>();
        for (Grupo grupo : usuario.getGrupoList()) {
            idsGruposAnteriores.add(grupo.getIdGrupo());
        }
        List<Integer> idsGruposActuales = new ArrayList<Integer>();
        for (GrupoDto grupoDto : usuarioDtoModificado.getGrupos()) {
            idsGruposActuales.add(grupoDto.getIdGrupo());
        }

        Arrays.sort(idsGruposAnteriores.toArray());// Ordenar los ids de los grupos para poderlos comparar
        Arrays.sort(idsGruposActuales.toArray());

        if (!Arrays.equals(idsGruposAnteriores.toArray(), idsGruposActuales.toArray())) {// Comparar los arreglos de grupos
            if (desCambios.length() > 0) {
                desCambios.append(",");
            }
            desCambios.append(EnumObsHistoricoUsuario.CAMBIO_GRUPOS);
        }

        List<Integer> idsRolesAnteriores = new ArrayList<Integer>();
        for (Rol rol : usuario.getRolList()) {
            if (!rol.getNombre().equalsIgnoreCase(ConstantesSeguridad.NOMBRE_ROL_PUBLICO))
                idsRolesAnteriores.add(rol.getIdRol());
        }
        List<Integer> idsRolesActuales = new ArrayList<Integer>();
        for (RolDto rolDto : usuarioDtoModificado.getRoles()) {
            idsRolesActuales.add(rolDto.getIdRol());
        }

        Arrays.sort(idsRolesAnteriores.toArray());// Ordenar los ids de los roles para poderlos comparar
        Arrays.sort(idsRolesActuales.toArray());

        if (!Arrays.equals(idsRolesAnteriores.toArray(), idsRolesActuales.toArray())) {// Comparar los arreglos de los roles
            if (desCambios.length() > 0) {
                desCambios.append(",");
            }
            desCambios.append(EnumObsHistoricoUsuario.CAMBIO_ROLES);
        }
        return desCambios.toString();
    }

    @Override
    public List<String> consultarRolesUsuario(final String login) {
        TypedQuery<String> q = em.createNamedQuery(Usuario.SQ_ROL_BY_USUARIO, String.class);
        q.setParameter("login", login);
        List<String> result = q.getResultList();
        if (result == null) {
            result = new ArrayList<>(0);
        }
        logger.debug("Se consultaron: " + result.size() + " roles para el usuario con login= " + login);
        return result;
    }

    @Override
    public UsuarioDto consultarUsuarioLdap(String campoBusqueda) throws SeguridadException {
        UsuarioDto usuarioLdap = null;
        LDAP ldapConector = new LDAP();
        // Establecer los datos de conexion
        LdapContexto ldapContext = new LdapContexto();
        ldapContext.putParam(EnumLdapParams.INITIAL_CONTEXT_FACTORY,
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.INITIAL_CONTEXT_FACTORY));
        ldapContext.putParam(EnumLdapParams.PROVIDER_URL, ConstantesSeguridad.PREFIJO_LDAP_JNDI
                + parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.PROVIDER_URL));
        ldapContext.putParam(EnumLdapParams.SECURITY_AUTHENTICATION,
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.SECURITY_AUTHENTICATION));
        ldapContext.putParam(EnumLdapParams.SECURITY_PRINCIPAL,
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.SECURITY_PRINCIPAL));
        ldapContext.putParam(EnumLdapParams.SECURITY_CREDENTIALS,
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.SECURITY_CREDENTIALS));

        // Consultar los nombres de los campos de busqueda en el LDAP
        String atrGrupo = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.NOMBRE_ATRIBUTO_MIEMBRODE_LDAP);
        if (StringUtils.isNotEmpty(atrGrupo)) {
            ldapContext.putMatchingAttribute(atrGrupo,
                    parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.AUTHORIZED_GROUP_PATH));
        }

        ldapContext.putMatchingAttribute(
                parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.NOMBRE_ATRIBUTO_IDUSUARIO_LDAP),
                campoBusqueda);

        // Definir el path de busqueda de los usuarios en el directorio
        ldapContext
                .setSearchBasePath(parametrosSeguridadEjb.consultarValorParametroSeguridad(EnumParametro.USERS_PATH));

        // Definir los nombres de los atributos del usuario que se van a traer del LDAP
        List<String> attributesToReturn = new ArrayList<>();
        String nombreCampoLogin = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.NOMBRE_ATRIBUTO_LOGIN_LDAP);
        String nombreCampoNombres = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.NOMBRE_ATRIBUTO_NOMBRES_LDAP);
        String nombreCampoApellidos = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.NOMBRE_ATRIBUTO_APELLIDOS_LDAP);
        String nombreCampoCorreo = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.NOMBRE_ATRIBUTO_CORREO_LDAP);

        attributesToReturn.add(nombreCampoLogin);
        if (StringUtils.isNotBlank(nombreCampoNombres))
            attributesToReturn.add(nombreCampoNombres);
        if (StringUtils.isNotBlank(nombreCampoApellidos))
            attributesToReturn.add(nombreCampoApellidos);
        if (StringUtils.isNotBlank(nombreCampoCorreo))
            attributesToReturn.add(nombreCampoCorreo);
        ldapContext.setAttributesToReturn(attributesToReturn);

        try {
            List<ResultadoLdap> resultadoUsuariosLdap = ldapConector.consultarElementos(ldapContext,
                    ResultadoLdap.class);

            if (!CollectionUtils.isEmpty(resultadoUsuariosLdap)) {
                if (resultadoUsuariosLdap.size() > attributesToReturn.size()) {
                    // Encontro varios usuarios, por tanto lanzar excepcion de que se encontro mas de un usuario en el lDAP
                    throw new SeguridadException(CatalogoError.VARIOS_RESULTADOS_LDAP);
                } else {
                    usuarioLdap = new UsuarioDto();
                    for (ResultadoLdap resultadoLdap : resultadoUsuariosLdap) {
                        if (resultadoLdap.getNombre().equals(nombreCampoLogin)) {
                            usuarioLdap.setLogin(resultadoLdap.getValor());
                        } else if (resultadoLdap.getNombre().equals(nombreCampoApellidos)) {
                            usuarioLdap.setApellidos(resultadoLdap.getValor());
                        } else if (resultadoLdap.getNombre().equals(nombreCampoCorreo)) {
                            usuarioLdap.setEmail(resultadoLdap.getValor());
                        } else if (resultadoLdap.getNombre().equals(nombreCampoNombres)) {
                            usuarioLdap.setNombres(resultadoLdap.getValor());
                        }
                    }
                }
            }
            logger.debug("Se realizó consulta en el LDAP");
        } catch (InstantiationException | IllegalAccessException | NamingException e) {
            logger.info("No se pudo realizar la consulta en el LDAP");
            throw new SeguridadException(CatalogoError.CONEXION_LDAP_FALLIDA);
        }
        return usuarioLdap;

    }

    @Override
    public void actualizarPwUsuario(String login) {
        // Generar nueva clave para el usuario y asignar los datos de modificacion de usuario
        Date fechaActual = new Date();
        GenericDao<Usuario> usrDao = new GenericDao<>(Usuario.class, em);
        Usuario usuario = usrDao.findUniqueByAttribute("login", login);
        String pw = autenticacionEjb.generarPassword();
        try {
            usuario.setPassword(Digester.digest(pw, EnumAlgoritmo.SHA512));
        } catch (NoSuchAlgorithmException e) {
            throw new SeguridadRuntimeException(e);
        }
        usuario.setFechaModificaUsuario(fechaActual);
        usuario.setFechaModificaPassword(fechaActual);

        EstadoPassword estadoPassword = new EstadoPassword();
        estadoPassword.setIdEstado(EnumEstadoPassword.TEMPORAL.getId());
        estadoPassword.setNombre(EnumEstadoPassword.TEMPORAL.getNombre());
        usuario.setEstadoPassword(estadoPassword);

        // Actualizar el usuario
        em.merge(usuario);
        logger.debug("Se modificó la contrasena del usuario de login= " + usuario.getLogin());
        // Crear el historico de la modificacion del password
        UsuarioDetalleDto usuarioDto = consultarUsuario(login, false);
        usuarioDto.setPassword(usuario.getPassword());
        HistoricoUsuarioDto historicoUsuarioDto = new HistoricoUsuarioDto();
        historicoUsuarioDto.setUsuarioDto(usuarioDto);
        historicoUsuarioDto.setCierraHistoricoAnterior(true);
        StringBuilder desCambios = new StringBuilder();
        desCambios.append(EnumObsHistoricoUsuario.CAMBIO_CONTRASENA.toString() + ","
                + EnumObsHistoricoUsuario.CAMBIO_ESTADO_CONTRASENA);
        historicoUsuarioDto.setDescripcionCambio(desCambios.toString());
        historicoUsuarioEjb.crearHistorico(historicoUsuarioDto);
        enviarCorreoCambioPassword(usuario.getEmail(), pw);
    }

    @Override
    public boolean validarExisteLogin(String login) {
        TypedQuery<Long> query = em.createNamedQuery(Usuario.SQ_COUNT_USUARIO_BY_LOGIN, Long.class);
        query.setParameter("login", login);
        Long resultado = query.getSingleResult();
        if (resultado > 0) {
            logger.info("Ya existe el usuario con login= " + login);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String generarLogin(String nombresUsuario, String apellidosUsuario) {
        final String param = parametrosSeguridadEjb
                .consultarValorParametroSeguridad(EnumParametro.ALGORITMO_GENERAR_LOGIN);
        if (StringUtils.isBlank(param)) {
            throw new SeguridadRuntimeException("El algorimo de generacion de login no se encuentra parametrizado "
                    + EnumParametro.ALGORITMO_GENERAR_LOGIN);
        }
        StringBuilder jsCode = new StringBuilder(param);
        jsCode.append("({nombres:'" + nombresUsuario + "',apellidos:'" + apellidosUsuario + "'})");

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        List<String> eval;
        try {
            eval = (List<String>) engine.eval(jsCode.toString());
            for (String generado : eval) {
                if (validarExisteLogin(generado)) {
                    continue;
                } else {
                    return generado;
                }
            }
            return "";
        } catch (ScriptException e) {
            logger.info("No es posible ejecutar el algoritmo de generacion de login ", e);
            throw new SeguridadRuntimeException(e);
        }

    }

    @Override
    public void actualizarEstadoPwUsuario(String login, EnumEstadoPassword estadoPw, String usuarioRealizaCambio) {
        Date fechaActual = new Date();
        GenericDao<Usuario> usrDao = new GenericDao<>(Usuario.class, em);
        Usuario usuario = usrDao.findUniqueByAttribute("login", login);
        EstadoPassword estado = new EstadoPassword();
        estado.setIdEstado(estadoPw.getId());
        usuario.setEstadoPassword(estado);
        usuario.setFechaModificaUsuario(fechaActual);

        if (estadoPw.getNombre().equals(EnumEstadoPassword.BLOQUEADO.getNombre()))
            usuario.setFechaBloqueoPassword(fechaActual);

        // Actualizar el usuario
        em.merge(usuario);
        logger.debug("Se modificó el estado de la contrasena del usuario de login= " + usuario.getLogin() + " a: "
                + estadoPw.getNombre());
        // Crear el historico de la modificacion del estado del password
        UsuarioDetalleDto usuarioDto = consultarUsuario(login, false);
        usuarioDto.setPassword(usuario.getPassword());
        HistoricoUsuarioDto historicoUsuarioDto = new HistoricoUsuarioDto();
        historicoUsuarioDto.setUsuarioDto(usuarioDto);
        historicoUsuarioDto.setCierraHistoricoAnterior(true);
        historicoUsuarioDto.setDescripcionCambio(EnumObsHistoricoUsuario.CAMBIO_ESTADO_CONTRASENA.toString());
        if (StringUtils.isNotBlank(usuarioRealizaCambio))
            historicoUsuarioDto.setUsuarioRealizaCambio(usuarioRealizaCambio);
        historicoUsuarioEjb.crearHistorico(historicoUsuarioDto);
    }

    /**
     * Envia el correo de creacion del usuario,al destinatario indicado y con el login y password enviados. Si el parametro password esta vacio el
     * usuario creado es del LDAP
     * 
     * @param correoDestinatario
     * @param login
     * @param password
     */
    private void enviarCorreoRegistroUsuario(final UsuarioDetalleDto usuario, String password) {
        StringBuilder cuerpoMail = new StringBuilder();

        cuerpoMail
                .append("<div style='font-size: 0.87em !important;font-family: Verdana !important;margin-left: auto !important;margin-right: auto !important;'>");
        if (usuario.isAutenticacionConLdap()) {
            cuerpoMail.append("<br/>Ha sido creado el usuario " + usuario.getLogin()
                    + " en el sistema y podrá ingresar con sus credenciales de autenticación del LDAP." + "<br/><br/>");
        } else {
            cuerpoMail.append("<br/>Los datos de ingreso al sistema son:<br/><br/>");
            cuerpoMail.append("Usuario: " + usuario.getLogin() + "<br/>");
            cuerpoMail.append("Contraseña: " + password + "<br/><br/>");
        }
        cuerpoMail.append("</div>");

        String asunto = "Registro de usuario";
        String[] direccionesEnvio = { usuario.getEmail() };

        seguridadMailSenderEJB.publicarCorreo(direccionesEnvio, asunto, cuerpoMail.toString(), null);

        logger.debug("Se invoca el envio de correo de la creacion del usuario= " + usuario.getLogin());
    }

    /**
     * Envia al usuario el correo de cambio de contraseña cuando es hecha por el administrador
     * 
     * @param correoDestinatario
     * @param login
     * @param password
     */
    private void enviarCorreoCambioPassword(String correoDestinatario, String password) {
        StringBuilder cuerpoMail = new StringBuilder();

        cuerpoMail
                .append("<div style='font-size: 0.87em !important;font-family: Verdana !important;margin-left: auto !important;margin-right: auto !important;'>");
        cuerpoMail.append("<br/>Su nueva contraseña de acceso al sistema es :<br/><br/>");
        cuerpoMail.append(password + "<br/><br/>");
        cuerpoMail.append("</div>");

        String asunto = "Cambio de contraseña";
        String[] direccionesEnvio = { correoDestinatario };

        seguridadMailSenderEJB.publicarCorreo(direccionesEnvio, asunto, cuerpoMail.toString(), null);

        logger.debug("Se invoca el envio de correo por cambio de password, direccion de correo= " + correoDestinatario);
    }

    @Override
    public List<HistoricoUsuarioDto> consultarHistoricoUsuario(int idUsuario, Date fechaInicial, Date fechaFinal) {
        return historicoUsuarioEjb.consultarHistoricoUsuario(idUsuario, fechaInicial, fechaFinal);
    }

    @Override
    public Date consultarFechaCreacionUsuario(Integer idUsuario) {
        TypedQuery<HistoricoUsuario> query = em.createNamedQuery(HistoricoUsuario.SQ_HISTORICO_CREACION_BY_USUARIO,
                HistoricoUsuario.class);
        query.setParameter("idUsuario", idUsuario);
        HistoricoUsuario primerHistorico = query.getSingleResult();
        return primerHistorico.getFechaInicio();
    }

    @Override
    public List<IngresoDto> consultarHistoricoIngresoUsuario(ConsultaIngresoDto consultaIngresoDto) {
        return historicoUsuarioEjb.consultarHistoricoIngresoUsuario(consultaIngresoDto);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<InfoAutorizacion> obtenerDetalleActividadIngreso(IngresoDto ingresoDto) {
        List<InfoAutorizacion> actividadIngreso = new ArrayList<InfoAutorizacion>();
        XmlSchemaValidatorUtil xmlValidator = new XmlSchemaValidatorUtil();
        if (StringUtils.isNotBlank(ingresoDto.getXmlActividadIngreso())) {
            try {
                xmlValidator.validarXml(ingresoDto.getXmlActividadIngreso(), ingresoDto.getContenidoXsd());
                Customized customized = new Customized(trazabilidadAutorizacionEJB.obtenerXStremInfoAutorizacion());
                actividadIngreso = customized.xmlToObject(List.class, ingresoDto.getXmlActividadIngreso());
                for (InfoAutorizacion infoAutorizacion : actividadIngreso) {
                    // Obtener la descripcion del recurso utilizando el nombre y el id de la aplicacion
                    logger.trace("Se va a consultar la descripcion del recurso con nombre= "
                            + infoAutorizacion.getNombreRecurso() + " de la aplicacion con Id= "
                            + ingresoDto.getIdAplicacion());
                    RecursoDto recursoConsultado = recursoOperacionEJB.consultarRecurso(ingresoDto.getIdAplicacion(),
                            infoAutorizacion.getNombreRecurso());
                    if (recursoConsultado != null)
                        infoAutorizacion.setDescripcionRecurso(recursoConsultado.getDescripcion());
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new SeguridadRuntimeException(e);
            }
        }
        return actividadIngreso;
    }
}
