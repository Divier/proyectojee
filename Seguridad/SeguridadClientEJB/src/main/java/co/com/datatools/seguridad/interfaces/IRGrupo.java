package co.com.datatools.seguridad.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.seguridad.dto.autorizacion.GrupoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.GrupoDto;
import co.com.datatools.seguridad.excepciones.SeguridadException;

/**
 * Interfaz remota EJB Grupo
 * 
 * @author Felipe Martinez
 */
@Remote
public interface IRGrupo {

    /**
     * Consulta los grupos con los parametros enviados
     * 
     * @param grupoDto
     *            Dto con los filtros por nombre y estado de grupo
     * 
     * @return Listado de los grupos consultados, si no encuentra resultados se retorna la lista vacia
     * 
     * @author claudia.rodriguez
     */
    List<GrupoDto> consultarGrupos(GrupoDto grupoDto);

    /**
     * Consulta un grupo con todo su detalle
     * 
     * @param idGrupo
     *            identificador del grupo
     * @return objeto Grupo encontrado con sus relaciones/detalles, null si no existe
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-02-17)
     */
    GrupoDto consultarGrupo(final Integer idGrupo);

    /**
     * Permite crear un grupo con sus relaciones
     * 
     * @param grupo
     *            informacion del grupo a crear
     * 
     * @return identificador del grupo creado
     * @throws SeguridadException
     *             GR0003:Ya existe un grupo con el mismo nombre enviado
     * 
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-02-17)
     */
    Integer registrarGrupo(final GrupoDetalleDto grupo) throws SeguridadException;

    /**
     * Elimina el grupo especificado
     * 
     * @param idGrupo
     *            identificador del grupo
     * @throws SeguridadException
     *             GR0001:El Grupo esta asociado a Roles,<br>
     *             GR0002:El Grupo esta asociado a Usuarios
     * 
     * @author claudia.rodriguez<br>
     * 
     */
    void eliminarGrupo(final Integer idGrupo) throws SeguridadException;

    /**
     * Pertime actualizar la informacion de un grupo registrado en el sistema
     * 
     * @param grupo
     *            informacion a actualizar del grupo
     * @throws SeguridadException
     *             GR0003:Ya existe un grupo con el mismo nombre enviado
     * @author Felipe Martinez<br>
     *         claudia.rodriguez(mod 2014-02-17)
     */
    void actualizarGrupo(final GrupoDetalleDto grupo) throws SeguridadException;

}
