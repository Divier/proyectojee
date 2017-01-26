package co.com.datatools.c2.negocio.ejb;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.comun.PaisHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.interfaces.ILPersona;
import co.com.datatools.c2.negocio.interfaces.ILSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;
import co.com.datatools.seguridad.interfaces.IRRol;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.seguridad.utilidades.EnumEstadoPassword;
import co.com.datatools.util.GenericDao;

/**
 * EJB con los servicios de administracion de usuarios
 * 
 * @author claudia.rodriguez giovanni.velandia (mod 2015-08-12)
 */
@Stateless(name = "SeguridadCirculemosEJB")
@LocalBean
public class SeguridadCirculemosEJB implements IRSeguridadCirculemos, ILSeguridadCirculemos {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(SeguridadCirculemosEJB.class.getName());

    @EJB
    private IRUsuario usuarioEjb;

    @EJB
    private IRRol rolEjb;

    @EJB
    private ILPersona personaEjb;

    @Resource
    private EJBContext context;

    @Override
    public UsuarioPersonaDTO obtenerUsuarioDto() {
        UsuarioPersonaDTO usuarioPersonaDTO = new UsuarioPersonaDTO();
        UsuarioDetalleDto usuarioDetalleDto = new UsuarioDetalleDto();
        usuarioDetalleDto.setLogin(context.getCallerPrincipal().getName()
                .substring(context.getCallerPrincipal().getName().indexOf("#") + 1));
        usuarioPersonaDTO.setUsuario(usuarioDetalleDto);
        List<UsuarioPersonaDTO> usuarioPersonaDtos = consultarUsuarioPersona(usuarioPersonaDTO);
        if (usuarioPersonaDtos != null && !usuarioPersonaDtos.isEmpty()) {
            return usuarioPersonaDtos.get(0);
        } else {
            return usuarioPersonaDTO;
        }
    }

    @Override
    public UsuarioPersonaDTO obtenerUsuarioDto(String login) {
        UsuarioPersonaDTO usuarioPersonaDto = null;
        TypedQuery<UsuarioPersona> query = em.createNamedQuery(UsuarioPersona.SQ_USUARIO_PERSONA_BY_LOGIN_USUARIO,
                UsuarioPersona.class);
        query.setParameter("login", login);
        try {
            UsuarioPersona usuarioPersona = query.getSingleResult();
            usuarioPersonaDto = UsuarioPersonaHelper.toLevel1DTO(usuarioPersona);
        } catch (NoResultException e) {
            return usuarioPersonaDto;
        }
        return usuarioPersonaDto;
    }

    @Override
    public OrganismoTransitoDTO obtenerOrganismoTransitoUsuario() {
        OrganismoTransitoDTO organismoTransitoDTO = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ot FROM UsuarioPersona up");
        sql.append(" INNER JOIN up.persona p");
        sql.append(" INNER JOIN p.organismoTransito ot");
        sql.append(" WHERE up.login = :login");

        Query query = em.createQuery(sql.toString());

        query.setParameter(
                "login",
                context.getCallerPrincipal().getName()
                        .substring(context.getCallerPrincipal().getName().indexOf("#") + 1));

        OrganismoTransito organismoTransito = (OrganismoTransito) query.getSingleResult();
        if (organismoTransito != null) {
            organismoTransitoDTO = OrganismoTransitoHelper.toLevel1DTO(organismoTransito);
        }
        return organismoTransitoDTO;
    }

    @Override
    public PaisDTO obtenerPais() {
        PaisDTO paisDTO = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ot FROM UsuarioPersona up");
        sql.append(" INNER JOIN up.persona p");
        sql.append(" INNER JOIN p.organismoTransito ot");
        sql.append(" WHERE up.login = :login");

        Query query = em.createQuery(sql.toString());

        query.setParameter(
                "login",
                context.getCallerPrincipal().getName()
                        .substring(context.getCallerPrincipal().getName().indexOf("#") + 1));

        OrganismoTransito organismoTransito = (OrganismoTransito) query.getSingleResult();

        if (organismoTransito != null) {
            if (organismoTransito.getDepartamento() != null) {
                paisDTO = PaisHelper.toLevel0DTO(organismoTransito.getDepartamento().getPais());
            } else {
                paisDTO = PaisHelper.toLevel0DTO(organismoTransito.getMunicipio().getDepartamento().getPais());
            }
        }

        return paisDTO;
    }

    @Override
    public List<RolDetalleDto> consultarRoles(boolean rolActivo) {
        return rolEjb.consultarRoles(rolActivo);
    }

    @Override
    public List<String> consultarRolesUsuario(String login) {
        return usuarioEjb.consultarRolesUsuario(login);
    }

    @Override
    public UsuarioDetalleDto consultarUsuario(String login) {
        return usuarioEjb.consultarUsuario(login);
    }

    @Override
    public List<UsuarioPersonaDTO> consultarUsuarios(UsuarioPersonaDTO usuarioPersonaDTO) {
        checkNotNull(usuarioPersonaDTO, "Se esperaba dto de entrada");
        checkNotNull(usuarioPersonaDTO.getPersona(), "Se esperaba dto de persona en el dto de entrada");
        checkNotNull(usuarioPersonaDTO.getUsuario(), "Se esperaba dto de usuario en el dto de entrada");

        List<UsuarioPersonaDTO> resultado = new ArrayList<UsuarioPersonaDTO>();
        ArrayList<Integer> idsUsuario = new ArrayList<Integer>();

        // Consultar los usuarios_persona que cumplan con los filtros de persona
        final List<UsuarioPersonaDTO> usuarioPersonas = consultarUsuarioPersona(usuarioPersonaDTO);
        if (usuarioPersonas.size() > 0) {
            // Consultar los usuarios de los usuarios_persona encontrados
            for (UsuarioPersonaDTO usuPerDTO : usuarioPersonas) {
                idsUsuario.add(usuPerDTO.getUsuario().getId());
            }

            final List<UsuarioDto> usuariosSeguridad = usuarioEjb.consultarUsuarios(usuarioPersonaDTO.getUsuario(),
                    idsUsuario);
            for (UsuarioDto usuarioDto : usuariosSeguridad) {
                UsuarioPersona usuarioPersona = consultarUsuarioPersona(usuarioDto.getId());
                PersonaDTO personaDto = personaEjb.consultarPersona(usuarioPersona.getPersona().getId());
                UsuarioPersonaDTO usuPerDTO = new UsuarioPersonaDTO();
                usuPerDTO.setUsuario((UsuarioDetalleDto) usuarioDto);
                usuPerDTO.setPersona(personaDto);
                resultado.add(usuPerDTO);
            }
        }
        return resultado;
    }

    @Override
    public List<UsuarioPersonaDTO> consultarUsuarioPersona(UsuarioPersonaDTO usuarioPersonaDTO) {
        List<UsuarioPersonaDTO> resultado = new ArrayList<UsuarioPersonaDTO>();
        final StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT up FROM UsuarioPersona up WHERE 1=1");
        final GenericDao<UsuarioPersona> usuarioDao = new GenericDao<>(UsuarioPersona.class, em);
        final Map<String, Object> params = new HashMap<>();

        if (usuarioPersonaDTO != null) {
            if (usuarioPersonaDTO.getPersona() != null) {
                if (usuarioPersonaDTO.getPersona().getTipoIdentificacion() != null
                        && StringUtils.isNotBlank(usuarioPersonaDTO.getPersona().getNumeroIdentificacion())) {
                    sqlBuilder.append(" AND up.persona.tipoIdentificacion.id= :idTipoIdentificacion");
                    params.put("idTipoIdentificacion", usuarioPersonaDTO.getPersona().getTipoIdentificacion().getId());
                    sqlBuilder.append(" AND up.persona.numeroIdentificacion= :numeroIdentificacion");
                    params.put("numeroIdentificacion", usuarioPersonaDTO.getPersona().getNumeroIdentificacion());
                }
            }
            if (usuarioPersonaDTO.getUsuario() != null) {
                if (usuarioPersonaDTO.getUsuario().getId() != null) {
                    sqlBuilder.append(" AND up.idUsuario= :idUsuario");
                    params.put("idUsuario", usuarioPersonaDTO.getUsuario().getId().intValue());
                }
                if (StringUtils.isNotBlank(usuarioPersonaDTO.getUsuario().getLogin())) {
                    sqlBuilder.append(" AND up.login= :login");
                    params.put("login", usuarioPersonaDTO.getUsuario().getLogin());
                }
            }

        }

        final List<UsuarioPersona> resultadoConsulta = usuarioDao.buildAndExecuteQuery(sqlBuilder.toString(), params);

        for (UsuarioPersona usuarioPersona : resultadoConsulta) {
            resultado.add(UsuarioPersonaHelper.toLevel1DTO(usuarioPersona));

        }
        return resultado;
    }

    @Override
    public UsuarioDetalleDto consultarUsuario(String login, boolean soloRolesEditables) {
        return usuarioEjb.consultarUsuario(login, soloRolesEditables);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) throws SeguridadException {
        usuarioEjb.eliminarUsuario(idUsuario);
        // Eliminar la relacion usuario_persona
        UsuarioPersona usuarioPersona = consultarUsuarioPersona(idUsuario);
        em.remove(usuarioPersona);
    }

    @Override
    public void actualizarPwUsuario(String login, String usuarioRealizaCambio) {
        usuarioEjb.actualizarPwUsuario(login);
    }

    @Override
    public void actualizarEstadoPwUsuario(String login, EnumEstadoPassword estadoPw, String usuarioRealizaCambio) {
        usuarioEjb.actualizarEstadoPwUsuario(login, estadoPw, usuarioRealizaCambio);
    }

    @Override
    public void actualizarUsuario(UsuarioDetalleDto usuario) throws SeguridadException {
        usuarioEjb.actualizarUsuario(usuario);
    }

    @Override
    public String generarLogin(PersonaDTO personaDTO) {
        if (!(personaDTO instanceof PersonaJuridicaDTO)) {
            String nombres = personaDTO.getNombre1();
            if (StringUtils.isNotBlank(personaDTO.getNombre2()))
                nombres = nombres.concat(" " + personaDTO.getNombre2());

            String apellidos = personaDTO.getApellido1();
            if (StringUtils.isNotBlank(personaDTO.getApellido2()))
                apellidos = apellidos.concat(" " + personaDTO.getApellido2());
            return usuarioEjb.generarLogin(nombres, apellidos);
        } else {
            String loginGenerado = "";
            if (((PersonaJuridicaDTO) personaDTO).getSigla() != null) {
                loginGenerado = ((PersonaJuridicaDTO) personaDTO).getSigla();
                if (validarExisteLogin(loginGenerado))
                    loginGenerado = loginGenerado.concat(RandomStringUtils.randomNumeric(2));
            }

            return loginGenerado;
        }
    }

    @Override
    public boolean validarExisteLogin(String login) {
        checkArgument(StringUtils.isNotBlank(login), "Login es requerido");
        return usuarioEjb.validarExisteLogin(login);
    }

    @Override
    public void registrarUsuario(UsuarioPersonaDTO usuario) throws CirculemosNegocioException, SeguridadException {

        Long idPersona = null;
        Integer idUsuario = null;

        // Validacion de argumentos y reglas de negocio
        checkNotNull(usuario, "Se esperaba dto de entrada");
        checkNotNull(usuario.getPersona(), "Se esperaba dto de persona en el dto de entrada");
        checkNotNull(usuario.getUsuario(), "Se esperaba dto de usuario en el dto de entrada");

        checkArgument(StringUtils.isNotBlank(usuario.getUsuario().getLogin()), "Login es requerido");
        checkArgument(StringUtils.isNotBlank(usuario.getUsuario().getNombres()), "Nombre es requerido");
        checkArgument(StringUtils.isNotBlank(usuario.getUsuario().getEmail()), "Correo es requerido");

        // Validar que el usuario no exista, si no debe registrar usuario.
        if (usuario.getUsuario().getId() == null) {
            idUsuario = usuarioEjb.registrarUsuario(usuario.getUsuario());
        } else {
            idUsuario = usuario.getUsuario().getId();
        }

        // Validar que la persona exista, si no debe registrar persona.
        if (usuario.getPersona().getId() == null) {
            idPersona = personaEjb.registrarPersona(usuario.getPersona());
        } else {
            idPersona = usuario.getPersona().getId();
        }

        // Registrar el usuarioPersona
        UsuarioPersona usuarioPersona = new UsuarioPersona();
        Persona persona = new Persona();
        persona.setId(idPersona);
        usuarioPersona.setPersona(persona);
        usuarioPersona.setIdUsuario(idUsuario);
        usuarioPersona.setLogin(usuario.getUsuario().getLogin());
        em.persist(usuarioPersona);
    }

    @Override
    public void registrarUsuarioPersona(UsuarioPersonaDTO usuarioPersonaDTO) {
        UsuarioPersona usuarioPersona = new UsuarioPersona();
        // Persona
        Persona persona = new Persona();
        persona.setId(usuarioPersonaDTO.getPersona().getId());
        usuarioPersona.setPersona(persona);
        // Usuario
        usuarioPersona.setIdUsuario(usuarioPersonaDTO.getUsuario().getId());
        usuarioPersona.setLogin(usuarioPersonaDTO.getLogin());
        em.persist(usuarioPersona);
    }

    @Override
    public UsuarioDto consultarUsuarioLdap(String campoBusqueda) throws SeguridadException {
        return usuarioEjb.consultarUsuarioLdap(campoBusqueda);
    }

    private UsuarioPersona consultarUsuarioPersona(Integer idUsuario) {
        UsuarioPersona usuarioPersona = null;
        TypedQuery<UsuarioPersona> query = em.createNamedQuery(UsuarioPersona.SQ_USUARIO_PERSONA_BY_USUARIO,
                UsuarioPersona.class);
        query.setParameter("idUsuario", idUsuario);
        try {
            usuarioPersona = query.getSingleResult();
        } catch (NoResultException e) {
            return usuarioPersona;
        }
        return usuarioPersona;
    }

}
