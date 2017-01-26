package co.com.datatools.seguridad.web.mb;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto;
import co.com.datatools.seguridad.dto.autenticacion.ResultadoAutenticacionDto.EstadoAutenticacion;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDto;
import co.com.datatools.seguridad.interfaces.IRAutenticacion;
import co.com.datatools.seguridad.interfaces.IRUsuario;
import co.com.datatools.seguridad.utilidades.ConstantesSeguridad;
import co.com.datatools.util.web.mb.AbstractManagedBean;

/**
 * Recurso de AUTENTICACION<br>
 * Sesion Bean encargado de controlar la informacion y servicios asociados a la autenticacion de un usuario
 * 
 * @author Felipe Martinez
 * 
 */
@ManagedBean(name = ConstantesSeguridad.NombresManagedBeans.AUTENTICACION_BEAN)
@SessionScoped
public class AutenticacionBean extends AbstractManagedBean {
    private static final String HTTP_XFF_HEADER = "X-FORWARDED-FOR";
    private final static Logger logger = Logger.getLogger(AutenticacionBean.class.getName());
    private static final long serialVersionUID = 1L;

    private static final String AUTENTICACION_EJB = "AutenticacionEJB!co.com.datatools.seguridad.interfaces.IRAutenticacion";

    private static final String USUARIO_EJB = "UsuarioEJB!co.com.datatools.seguridad.interfaces.IRUsuario";
    /**
     * Ejb con los servicios de autenticacion del usuario
     */
    private transient IRAutenticacion autenticacionEjb;
    /**
     * Ejb de acceso a usuarios
     */
    private transient IRUsuario usuarioEjb;
    /**
     * Informacion del usuario autenticado en la session, es diferente de null despues de q haya ocurrido una autenticacion exitosa
     */
    private UsuarioDto usuario;
    /**
     * Login del usuario que fue autenticado por JAAS
     */
    private String login;
    /**
     * Password utilizado por el usuario para la autenticacion, es diferente a null solo en el momento realizar la autenticacion
     */
    private String password;
    /**
     * Almacena el resultado de la autenticacion realizada sobre esta session
     */
    private ResultadoAutenticacionDto resultadoAutenticacion;

    /**
     * Almacena el nombre de la aplicacion sobre la cual se realiza la autenticacion, debe ser asignado por el cliente
     * 
     */
    private String aplicacion;

    private Integer idIngreso;

    private String ubicacionJarSeguridadEjb;

    /**
     * NUEVO
     */
    private List<String> rolesUsuario;

    public AutenticacionBean() {
        super();
        logger.info("Nueva Instancia del Managed Bean:" + AutenticacionBean.class.getName());
        try {
            Properties propiedades = new Properties();
            propiedades.load(AutenticacionBean.class.getResourceAsStream("/artefacto.properties"));
            String version = propiedades.getProperty("version");
            logger.debug("Version= " + version);

            ubicacionJarSeguridadEjb = "SeguridadEAR-" + version + "/" + "SeguridadEJB-" + version + "/";

        } catch (IOException e) {
            logger.info("Error cargando el archivo de propiedades " + e.getMessage());
        }
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResultadoAutenticacionDto getResultadoAutenticacion() {
        return resultadoAutenticacion;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * Valida despues de una autenticacion exitosa por JAAS si el password esta vencido o temporal, tambien si se encuentra bloqueado es porque debe
     * realizarse desbloqueo
     * 
     * @param nombreAplicacion
     *            Nombre de la aplicacion sobre la cual se realiza la autenticacion
     * @param nombreUsuario
     *            Login del usuario autenticado
     * @param ipPeticion
     *            Direccion IP de la maquina donde se hizo la autenticacion
     * @return Dto con el resultado de la autenticacion y los datos del usuario autenticado
     * @author claudia.rodriguez
     * 
     */
    public ResultadoAutenticacionDto validarEstadoPassword(String nombreAplicacion, String nombreUsuario,
            String ipPeticion) {
        rolesUsuario = null;
        resultadoAutenticacion = getAutenticacionEjb().validarEstadoPassword(nombreUsuario, ipPeticion,
                nombreAplicacion);
        if (resultadoAutenticacion.getEstadoAutenticacion().equals(EstadoAutenticacion.OK)) {
            setUsuario(resultadoAutenticacion.getUsuario());
            rolesUsuario = getUsuarioEjb().consultarRolesUsuario(nombreUsuario);
            resultadoAutenticacion.setRoles(rolesUsuario);
            setUsuario(resultadoAutenticacion.getUsuario());
            idIngreso = getAutenticacionEjb().registrarIngresoExitoso(nombreUsuario, ipPeticion, nombreAplicacion);
            addSessionObject(ConstantesSeguridad.NOMBRE_ATRIBUTO_SESION_ID_INGRESO, idIngreso);
            logger.info("Sube a sesion el Id de ingreso= " + idIngreso);
        }
        return resultadoAutenticacion;
    }

    /**
     * Limpia los datos de autenticacion ya ctualiza el menu
     * 
     */
    public void cerrarSession() {
        getAutenticacionEjb().cerrarSession(getUsuario().getLogin(), idIngreso);
        setUsuario(null);
        setLogin(null);
        setPassword(null);
    }

    private IRUsuario getUsuarioEjb() {
        if (usuarioEjb == null) {
            usuarioEjb = lookupEjb(IRUsuario.class, ubicacionJarSeguridadEjb + USUARIO_EJB);
        }
        return usuarioEjb;
    }

    private IRAutenticacion getAutenticacionEjb() {
        if (autenticacionEjb == null) {
            autenticacionEjb = lookupEjb(IRAutenticacion.class, ubicacionJarSeguridadEjb + AUTENTICACION_EJB);
        }
        return autenticacionEjb;
    }

    public List<String> getRolesUsuario() {
        return rolesUsuario;
    }

    public void setRolesUsuario(List<String> rolesUsuario) {
        this.rolesUsuario = rolesUsuario;
    }

}
