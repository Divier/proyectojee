package co.com.datatools.seguridad.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.dto.comun.InfoAutorizacion;
import co.com.datatools.seguridad.interfaces.IRAutorizacion;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.seguridad.utilidades.EnumParametro;

/**
 * EJB que provee la implementacion de los metodos de autorizacion de los recursos
 * 
 * @author Felipe Martinez
 * 
 */
@Stateless(name = "AutorizacionEJB")
@LocalBean
public class AutorizacionEJB implements IRAutorizacion {

    private final static Logger logger = Logger.getLogger(AutorizacionEJB.class.getName());

    @PersistenceContext(unitName = ConstantesSeguridad.NOMBRE_PERSISTENCE_CTX)
    private EntityManager em;

    @EJB
    private UsuarioEJB usuariosEjb;

    @EJB
    private RolEJB rolEjb;

    @EJB
    private CatalogosSeguridadEJB catalogosEjb;

    @EJB
    private MenuEJB menuEjb;

    @EJB
    private ParametrosSeguridadEJB parametrosSeguridadEJB;

    @EJB
    private TrazabilidadAutorizacionEJB trazabilidadAutorizacionEJB;

    public AutorizacionEJB() {
        super();
        logger.debug("AutorizacionEJB.AutorizacionEJB()");
    }

    @PostConstruct
    public void start() {
        logger.debug("RolEJB.start()");
    }

    @Override
    public Collection<OperacionDto> consultarOperacionesPermitidasSinAutenticacion(String nombreAplicacion,
            String nombreRecurso) {
        return consultarOperacionesPermitidasRoles(nombreAplicacion,
                Arrays.asList(ConstantesSeguridad.NOMBRE_ROL_PUBLICO), nombreRecurso);
    }

    @Override
    public Collection<OperacionDto> consultarOperacionesPermitidasUsuario(final String nombreAplicacion,
            final String login, final String nombreRecurso) {
        List<String> rolesUsuario = usuariosEjb.consultarRolesUsuario(login);
        return consultarOperacionesPermitidasRoles(nombreAplicacion, rolesUsuario, nombreRecurso);
    }

    @Override
    public Collection<OperacionDto> consultarOperacionesPermitidasRoles(final String nombreAplicacion,
            final List<String> nombresRol, final String nombreRecurso) {
        final String idApp = catalogosEjb.consultarIdAplicacion(nombreAplicacion);
        // se crea un set para que la lista final no tenga operaciones repetidas
        Collection<OperacionDto> operaciones = new HashSet<>();
        RecursoDetalleDto recurso = new RecursoDetalleDto(nombreRecurso);
        RolDetalleDto rol = null;
        List<String> nombresRolesPadre = new ArrayList<String>();
        int nivelesHerencia = Integer.valueOf(parametrosSeguridadEJB
                .consultarValorParametroSeguridad(EnumParametro.NIVELES_HERENCIA_ROLES));
        rolEjb.consultarHerenciaRolesCompleta(nombresRolesPadre, nombresRol, 0, nivelesHerencia);
        nombresRol.addAll(nombresRolesPadre);
        for (String nombreRol : nombresRol) {
            // Consulta el rol
            rol = rolEjb.consultarPermisosRol(nombreRol);
            if (rol != null) {
                // Obtiene los recursos de la aplicacion indicada
                List<RecursoDetalleDto> recursosApp = rol.getRecursosAplicacion().get(idApp);
                if (recursosApp != null && recursosApp.contains(recurso)) {
                    int index = recursosApp.indexOf(recurso);
                    if (index != -1) {
                        // Agrega todas las operaciones del recurso del rol actual
                        operaciones.addAll(recursosApp.get(index).getOperaciones());
                    }
                }
            }
        }
        // retorna la union de todas las operaciones configuradas para el recurso en cada uno de los roles
        return operaciones;
    }

    @Override
    public boolean esRecursoPermitidoSinAutenticacion(String nombreAplicacion, String nombreRecurso) {
        return esRecursoPermitidoRoles(nombreAplicacion, Arrays.asList(ConstantesSeguridad.NOMBRE_ROL_PUBLICO),
                nombreRecurso);
    }

    @Override
    public boolean esRecursoPermitidoUsuario(final String nombreAplicacion, final String login,
            final String nombreRecurso) {
        List<String> rolesUsuario = usuariosEjb.consultarRolesUsuario(login);
        if (esRecursoPermitidoSinAutenticacion(nombreAplicacion, nombreRecurso)) {
            return true;
        } else {
            return esRecursoPermitidoRoles(nombreAplicacion, rolesUsuario, nombreRecurso);
        }
    }

    @Override
    public boolean esRecursoPermitidoRoles(final String nombreAplicacion, final List<String> nombresRol,
            final String nombreRecurso) {
        final String idApp = catalogosEjb.consultarIdAplicacion(nombreAplicacion);
        RecursoDetalleDto recurso = new RecursoDetalleDto(nombreRecurso);
        RolDetalleDto rol = null;
        // Consultar toda la jerarquia de padres de los roles en List<String> nombresRol
        List<String> nombresRolesPadre = new ArrayList<String>();
        int nivelesHerencia = Integer.valueOf(parametrosSeguridadEJB
                .consultarValorParametroSeguridad(EnumParametro.NIVELES_HERENCIA_ROLES));
        rolEjb.consultarHerenciaRolesCompleta(nombresRolesPadre, nombresRol, 0, nivelesHerencia);
        nombresRol.addAll(nombresRolesPadre);
        // Consultar recursos de cada rol
        for (String nombreRol : nombresRol) {
            rol = rolEjb.consultarPermisosRol(nombreRol);
            if (rol != null) {
                // Obtener recursos de la aplicacion definida
                List<RecursoDetalleDto> recursosApp = rol.getRecursosAplicacion().get(idApp);
                if (recursosApp != null) {
                    int index = recursosApp.indexOf(recurso);
                    if (index != -1) {
                        // Existe el recurso dentro del rol para aplicacion
                        recurso = recursosApp.get(index);
                        if (recurso.getOperaciones().contains(
                                new OperacionDto(ConstantesSeguridad.NOMBRE_OPERACION_INGRESO))) {
                            // Tiene el permiso de ingreso para el recurso
                            return true;
                        }
                    }
                }
            }
        }
        logger.info("El recurso: " + nombreRecurso + " de la aplicacion: " + nombreAplicacion + " NO esta permitido");
        return false;
    }

    @Override
    public List<MenuDto> consultarRecursosMenu(String nombreAplicacion) {
        String idAplicacion = catalogosEjb.consultarIdAplicacion(nombreAplicacion);
        List<MenuDto> opcionesMenu = menuEjb.consultarOpcionesMenu(Integer.valueOf(idAplicacion));
        return opcionesMenu;
    }

    @Override
    public void registrarSolicitudAutorizacion(Integer idIngresoUsuario, InfoAutorizacion infoAutorizacion) {
        trazabilidadAutorizacionEJB.adicionarSolicitudAutorizacion(idIngresoUsuario, infoAutorizacion);
    }

    @Override
    public boolean consultarCacheAutorizaionXIdIngresoUsuario(Integer idIngresoUsuario) {
        return trazabilidadAutorizacionEJB.consultarCacheAutorizaionXIdIngresoUsuario(idIngresoUsuario);
    }
}
