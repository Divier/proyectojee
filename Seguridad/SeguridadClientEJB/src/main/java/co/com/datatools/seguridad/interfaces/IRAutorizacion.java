package co.com.datatools.seguridad.interfaces;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.autorizacion.MenuDto;
import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.comun.InfoAutorizacion;

/**
 * Interfaz remota EJB Autorizacion
 * 
 * @author Felipe Martinez
 */
@Remote
public interface IRAutorizacion {
    /**
     * Lista los recursos nivel cero con sus subsequentes niveles en el orden q deben ser desplegados
     * 
     * @param nombreAplicacion
     *            objeto q indica aplicacion de la q se obtienen los recursos
     * @return Lista de recursos que representan niveles y subniveles del menu de una aplicacion, la lista viene ordenada en la manera q se deben
     *         desplegar, si no encuentra nada retorna lista vacia, null safe
     * 
     * @author Felipe Martinez
     */
    List<MenuDto> consultarRecursosMenu(String nombreAplicacion);

    /**
     * Lista las operaciones permitidas para el recurso q se puede ejecutar sin autenticacion en una aplicacion
     * 
     * @param nombreAplicacion
     *            objeto q permite identificar sobre cual aplicacion se realiza la validacion de permisos
     * @param nombreRecurso
     *            nombre unico del recurso q se esta ejecutando. ejem nombre del flujo
     * @return Lista las operaciones permitidas del recurso, si no encuentra nada retorna lista vacia, null safe
     * 
     * @author Felipe Martinez
     */
    Collection<OperacionDto> consultarOperacionesPermitidasSinAutenticacion(String nombreAplicacion,
            String nombreRecurso);

    /**
     * Lista las operaciones q puede realizar un usuario sobre un recurso de una aplicacion
     * 
     * @param nombreAplicacion
     *            objeto q permite identificar sobre cual aplicacion se realiza la validacion de permisos
     * @param login
     *            identificador del usuario q ejecuta la operacion
     * @param nombreRecurso
     *            nombre unico del recurso
     * @return Lista las operaciones permitidas del recurso para el usuario, si no encuentra nada retorna lista vacia, null safe
     * 
     * @author Felipe Martinez
     */
    Collection<OperacionDto> consultarOperacionesPermitidasUsuario(String nombreAplicacion, String login,
            String nombreRecurso);

    /**
     * Lista el conjunto de operaciones permitidas sobre un recurso para los roles indicados y para la jerarquia de herencia de dichos roles al nivel
     * que se encuentre parametrizado
     * 
     * @param nombreAplicacion
     *            objeto q permite identificar sobre cual aplicacion se realiza la validacion de permisos
     * @param nombresRol
     *            lista de roles sobre los q se realiza la validacion de permisos
     * @param nombreRecurso
     *            nombre unico del recurso del q se consultaran las operaciones dentro de cada rol
     * @return Conjunto de operaciones permitidas sobre el recurso de acuerdo a todos los roles
     * 
     * @author Felipe Martinez <br>
     *         claudia.rodriguez(mod 2014-05-08)
     */
    Collection<OperacionDto> consultarOperacionesPermitidasRoles(String nombreAplicacion, List<String> nombresRol,
            String nombreRecurso);

    /**
     * Permite validar si un recurso es permitido para usuario sin autenticar en la aplicacion
     * 
     * @param nombreAplicacion
     *            objeto q permite identificar sobre cual aplicacion se realiza la validacion de permisos
     * @param nombreRecurso
     *            nombre unico del recurso q se esta ejecutando. ejem nombre del flujo
     * @return permitido:true, no permitido: false
     * 
     * @author Felipe Martinez
     */
    boolean esRecursoPermitidoSinAutenticacion(String nombreAplicacion, String nombreRecurso);

    /**
     * Permite validar si un recurso es permitido para usuario en la aplicacion
     * 
     * @param nombreAplicacion
     *            objeto q permite identificar sobre cual aplicacion se realiza la validacion de permisos
     * @param login
     *            identificador del usuario q ejecuta la operacion
     * @param nombreRecurso
     *            nombre unico del recurso asociado al flujo
     * @return permitido:true, no permitido: false
     * 
     * @author Felipe Martinez
     */
    boolean esRecursoPermitidoUsuario(String nombreAplicacion, String login, String nombreRecurso);

    /**
     * Permite validar si un recurso es permitido para un conjunto de roles para una aplicacion verificando tambien en los permisos de la jerarquia de
     * herencia de dichos roles al nivel que se encuentre parametrizado
     * 
     * @param nombreAplicacion
     *            objeto q permite identificar sobre cual aplicacion se realiza la validacion de permisos
     * @param nombresRol
     *            lista de roles sobre los q se realiza la validacion de permisos
     * @param nombreRecurso
     *            nombre unico del recurso q se validara si es permitido
     * @return permitido:true, no permitido: false
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-05-08)
     */
    boolean esRecursoPermitidoRoles(String nombreAplicacion, List<String> nombresRol, String nombreRecurso);

    /**
     * Registra una autorizacion realizada asociada a un registro de ingreso
     * 
     * @param idIngresoUsuario
     *            identificador del ingreso al que va a asociada la autorizacion
     * @param infoAutorizacion
     *            informacion de la autorizacion
     * @author Felipe Martinez
     */
    void registrarSolicitudAutorizacion(Integer idIngresoUsuario, InfoAutorizacion infoAutorizacion);

    /**
     * Metodo que se encarga de consultar un Ingreso de usuario en el cache por idIngresoUsuario
     * 
     * @param idIngresoUsuario
     * @author giovanni.velandia
     */
    boolean consultarCacheAutorizaionXIdIngresoUsuario(Integer idIngresoUsuario);

}
