package co.com.datatools.seguridad.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.autenticacion.HistoricoRolDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;

/**
 * Interfaz remota EJB Rol
 * 
 * @author Felipe Martinez
 */
@Remote
public interface IRRol {

    /**
     * Permite obtener el arbol de recursos base utilizados para crear roles de una aplicacion
     * 
     * @param idAplicacion
     *            identificador de aplicacion a obtener su listado de roles, si viene nulo consulta los recursos de todas las aplicaciones
     * 
     * @return El dto contine un mapa con los recursos-operaciones que se pueden realizar por cada aplicacion, null safe
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-28)
     */
    RolDetalleDto consultarPerfilCompletoAplicacion(String idAplicacion);

    /**
     * Permite hacer la consulta general de todos los roles segun los criterios enviados, no consulta sus relaciones
     * 
     * @param rolDetalleDto
     *            Dto con los filtros de la consulta
     * 
     * @return listado de roles encontradados para la aplicacion, si no encuentra resultados retorna lista vacia, null safe
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-27)
     */
    List<RolDetalleDto> consultarRoles(RolDetalleDto rolDetalleDto);

    /**
     * Consulta los roles existentes en el sistema, excepto los roles: PUBLICO Y SUPER-ADMIN
     * 
     * @param rolActivo
     *            si es igual a true, solo trae los roles que estan activos
     * @return Lista de roles consultados
     * @author claudia.rodriguez
     * 
     */
    List<RolDetalleDto> consultarRoles(boolean rolActivo);

    /**
     * Consulta un rol con todo su detalle
     * 
     * @param idRol
     *            identificador del rol
     * 
     * @return objeto Rol encontrado con sus relaciones/detalles, null si no existe
     * 
     * @author Felipe Martinez <br>
     *         claudia.rodriguez(mod 2014-04-09)
     */
    RolDetalleDto consultarRol(final Integer idRol);

    /**
     * Permite crear un rol con sus permisos siempre y cuando no exista ya otro rol con el mismo nombre
     * 
     * @param rol
     *            informacion del rol a crear
     * 
     * @return identificador del rol creado
     * 
     * @throws SeguridadException
     *             RL0001: Ya existe un Rol con el mismo nombre enviado
     * 
     * @author Felipe Martinez <br>
     *         claudia.rodriguez(mod 2014-04-09)
     */
    Integer registrarRol(final RolDetalleDto rol) throws SeguridadException;

    /**
     * Elimina el rol especificado
     * 
     * @param idRol
     *            identificador del rol a eliminar
     * 
     * @throws SeguridadException
     *             RL0002:El Rol PUBLICO no se puede eliminar,<br>
     *             RL0003:Rol esta asociado a usuarios,<br>
     *             RL0004:Rol esta asociado como padre de otros roles,<br>
     *             RL0005:Rol esta asociado a ingresos de usuario,<br>
     *             RL0006:Rol estuvo asociado como padre de otros roles(en historico_rol)<br>
     *             RL0007:Rol estuvo asociado a usuarios(en historico_rol_usuario)
     * 
     * @author Felipe Martinez <br>
     *         claudia.rodriguez(mod 2014-04-11)
     */
    void eliminarRol(final Integer idRol) throws SeguridadException;

    /**
     * Permite actualizar la informacion de un rol registrado en el sistema
     * 
     * @param rol
     *            informacion a actualizar del rol
     * 
     * @throws SeguridadException
     *             RL0001: Ya existe un Rol con el mismo nombre enviado
     * 
     * @author Felipe Martinez <br>
     *         claudia.rodriguez(mod 2014-04-12)
     */
    void actualizarRol(final RolDetalleDto rol) throws SeguridadException;

    /**
     * Permite obtener los recursos-operaciones asociados a un rol
     * 
     * @param nombreRol
     *            nombre unico del rol
     * @return informacion de los recursos-permisos del rol para cada aplicacion, null si no encuentra permisos asociados
     * 
     * @author Felipe Martinez
     */
    RolDetalleDto consultarPermisosRol(final String nombreRol);

    /**
     * Consulta los historicos de cambios de un rol por Id del rol y un rango de fechas
     * 
     * @param idRol
     *            Id del rol al cual se le consulta el historico
     * @param fechaInicial
     *            Fecha desde la cual se consulta el historico
     * @param fechaFinal
     *            Fecha hasta la cual se consulta el historico
     * @return Listado con los historicos del rol
     * 
     * @author claudia.rodriguez
     * 
     */
    List<HistoricoRolDto> consultarDetalleHistoricoRol(int idRol, Date fechaInicial, Date fechaFinal);

    /**
     * Consulta el ultimo registro de historico del Rol para el Id de rol enviado que sea inferior o igual a la fecha de referencia enviada
     * 
     * @param idRol
     *            Identificador del rol al cual se le consultara el ultimo historico inferior a la fecha enviada
     * @param fechaReferencia
     *            fecha de referencia para la cual se consulta le historico mas cercano o igual a dicha fecha
     * @return Ultimo historico encontrado, nulo si no encuentra resultado
     * @author claudia.rodriguez
     */
    HistoricoRolDto consultarUltimoHistoricoRol(int idRol, Date fechaReferencia);

    /**
     * Obtiene la fecha de creacion de un rol consultando la fecha de inicio del primer historico del rol
     * 
     * @param idRol
     *            Id del rol a consultar la fecha de creacion
     * @return Fecha de creacion del rol cuyo id es enviado como parametro
     */
    Date consultarFechaCreacionRol(Integer idRol);

}
