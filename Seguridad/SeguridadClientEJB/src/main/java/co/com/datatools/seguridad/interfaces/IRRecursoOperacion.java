package co.com.datatools.seguridad.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.autorizacion.OperacionDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RecursoDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;

/**
 * Interfaz remota EJB Recurso
 * 
 * @author Felipe Martinez
 */
@Remote
public interface IRRecursoOperacion {

    /**
     * Permite hacer la consulta general de todos los recursos, no consulta sus relaciones
     * 
     * @param recursoFiltros
     *            Objeto con los filtros para realizar la consulta, si no trae datos consulta todos los recursos
     * @param conOperaciones
     *            Indica si solo se deben consultar los recursos que tengan operaciones asignadas o de lo contrario se consultan los recursos sin
     *            importar si tienen o no operaciones
     * @return listado de recursos encontradados, si no encuentra nada retorna lista vacia, null safe
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-09-23)
     */
    List<RecursoDto> consultarRecursos(RecursoDto recursoFiltros, boolean conOperaciones);

    /**
     * Consulta un recurso con todo su detalle
     * 
     * @param idRecurso
     *            identificador del recurso
     * @return objeto Recurso encontrado con sus relaciones/detalles, null si no existe
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-17)
     */
    RecursoDetalleDto consultarRecurso(final Integer idRecurso);

    /**
     * Permite crear un recurso con sus relaciones asociado a una aplicacion *
     * 
     * @param recurso
     *            informacion del recurso a crear
     * @return identificador del recurso creado
     * @throws SeguridadException
     *             RC0002:Ya existe un recurso con el mismo nombre enviado
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-19)
     * 
     */
    Integer registrarRecurso(final RecursoDetalleDto recurso) throws SeguridadException;

    /**
     * Elimina el recurso especificado siempre y cuando el recurso no sea padre de otro recurso o este asociado a roles
     * 
     * @param idRecurso
     *            identificador del recurso
     * @throws SeguridadException
     *             RC0001:El recurso ya esta asignado a roles,<br>
     *             RC0003:El recurso es padre de otros recurso,<br>
     *             RC0005:El recurso ya esta asociado a opciones de menú
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-20)
     */
    void eliminarRecurso(final Integer idRecurso) throws SeguridadException;

    /**
     * Permite actualizar la informacion de un recurso registrado en el sistema
     * 
     * @param recurso
     *            informacion a actualizar del recurso
     * @throws SeguridadException
     *             RC0002:Ya existe un recurso con el mismo nombre enviado,<br>
     *             RC0004:Algunos recurso-operacion eliminados ya estan asociados a roles
     * @author claudia.rodriguez
     */
    void actualizarRecurso(final RecursoDetalleDto recurso) throws SeguridadException;

    /**
     * Consulta el listado de operaciones del sistema excluyendo la operacion "ingresar"
     * 
     * @return listado de operaciones encontradas, si no encuentra nada retorna lista vacia, null safe
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-03-20)
     */
    List<OperacionDto> consultarOperaciones();

    /**
     * Consulta las operaciones que cumplan con el nombre/parte del nombre enviado, si se envia nulo o vacio, se consultan todas las operaciones
     * 
     * @param nombre
     *            Nombre de la operacion a consultar
     * @return Operaciones consultadas, si no encuentra resultados retorna la lista vacia
     * 
     * @author claudia.rodriguez
     * 
     */
    List<OperacionDto> consultarOperaciones(String nombre);

    /**
     * Consulta la operacion que tenga el identificador enviado
     * 
     * @param id
     *            Identificador de la operacion a consultar
     * @return Operacion encontrada o null si no la encuentra
     * 
     * @author claudia.rodriguez
     */
    OperacionDto consultarOperacion(Integer id);

    /**
     * Permite crear una operacion. Lanza error si la operacion no puede ser creada porque ya existe otra operacion con el mismo nombre
     * 
     * @param operacion
     *            informacion de la operacion a crear
     * @return identificador de la operacion creada
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-04-14)
     * 
     * @throws SeguridadException
     *             OP0001:Ya existe una operacion con el mismo nombre enviado
     */
    Integer registrarOperacion(final OperacionDto operacion) throws SeguridadException;

    /**
     * Elimina la operacion especificada. Lanza error si la operacion no puede ser eliminada porque es la operación "ingresar" o tiene relacion con
     * recursos en el sistema
     * 
     * @param idOperacion
     *            identificador de la operacion
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-04-14)
     * 
     * @throws SeguridadException
     *             OP0003: Operacion "ingresar" no se puede editar ni eliminar,<br>
     *             OP0002: Operacion esta asociada a recursos
     */
    void eliminarOperacion(final Integer idOperacion) throws SeguridadException;

    /**
     * Permite actualizar la informacion de una operacion en el sistema. Lanza error si la operacion no puede ser actualizada porque ya existe otra
     * operacion con el mismo nombre
     * 
     * @param operacionDto
     *            Dto con los datos a modificar
     * @author claudia.rodriguez
     * 
     * @throws SeguridadException
     *             OP0001:Ya existe una operacion con el mismo nombre enviado,<br>
     *             OP0003:Operacion "ingresar" no se puede editar ni eliminar
     */
    void actualizarOperacion(OperacionDto operacionDto) throws SeguridadException;

    /**
     * Consulta un recurso por nombre(tecnico) y por el id de la aplicacion a la cual pertenece
     * 
     * @param idAplicacion
     *            Identificador de la aplicacion a la que pertenece el recurso
     * @param nombreRecurso
     *            Nombre tecnico del recurso que se desea consultar
     * @return Recurso encontrado si no lo encuentra retorna null
     */
    RecursoDto consultarRecurso(Integer idAplicacion, String nombreRecurso);
}
