package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.entidades.EstadoFormulario;
import co.com.datatools.c2.entidades.RelacionEstados;
import co.com.datatools.c2.entidades.TipoFormulario;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;

/**
 * @author Generated
 * @version 3.0 - Tue Jan 06 18:08:30 COT 2015
 */
public class RelacionEstadosHelper {
    // --- to DTO
    /**
     * Metodo que permite establecer el id de la entidad
     * RelacionEstados en el objeto dto correspondiente
     * 
     * @param entidad
     * @return
     */
    public static RelacionEstadosDTO toLevel0DTO(RelacionEstados entidad) {
        RelacionEstadosDTO dto = new RelacionEstadosDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    /**
     * Metodo que permite establecer el estado siguiente,
     * estado actual y tipo de formulario de la entidad
     * RelacionEstados en el objeto dto correspondiente
     * 
     * @param entidad
     * @return
     */
    public static RelacionEstadosDTO toLevel1DTO(RelacionEstados entidad) {
        RelacionEstadosDTO dto = toLevel0DTO(entidad);
        if (entidad.getEstadoFormularioSiguiente() != null)
            dto.setEstadoFormularioSiguiente(EstadoFormularioHelper.toLevel0DTO(entidad.getEstadoFormularioSiguiente()));
        if (entidad.getEstadoFormularioActual() != null)
            dto.setEstadoFormularioActual(EstadoFormularioHelper.toLevel0DTO(entidad.getEstadoFormularioActual()));
        if (entidad.getTipoFormulario() != null)
            dto.setTipoFormulario(TipoFormularioHelper.toLevel0DTO(entidad.getTipoFormulario()));

        return dto;
    }

    /**
     * Metodo que permite establecer los id de un conjunto entidades
     * RelacionEstados en una lista de objetos del tipo dto correspondiente
     * 
     * @param listEntidad
     * @return
     */
    public static List<RelacionEstadosDTO> toListLevel0DTO(List<RelacionEstados> listEntidad) {
        List<RelacionEstadosDTO> listDto = new ArrayList<RelacionEstadosDTO>();
        for (RelacionEstados entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    /**
     * Metodo que permite establecer el estado siguiente,
     * estado actual y tipo de formulario de un conjunto de entidades
     * RelacionEstados en una lista de objetos del tipo dto correspondiente
     * 
     * @param listEntidad
     * @return
     */
    public static List<RelacionEstadosDTO> toListLevel1DTO(List<RelacionEstados> listEntidad) {
        List<RelacionEstadosDTO> listDto = new ArrayList<RelacionEstadosDTO>();
        for (RelacionEstados entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    /**
     * Metodo que permite establecer el id del tipo dto
     * RelacionEstadosDTO en el objeto entidad correspondiente
     * 
     * @param dto
     * @param entidad
     * @return
     */
    public static RelacionEstados toLevel0Entity(RelacionEstadosDTO dto, RelacionEstados entidad) {
        if (null == entidad) {
            entidad = new RelacionEstados();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    /**
     * Metodo que permite establecer el estado siguiente,
     * estado actual, tipo de formulario y organismo del tipo dto
     * RelacionEstadosDTO en el objeto entidad correspondiente
     * 
     * @param dto
     * @param entidad
     * @return
     */
    public static RelacionEstados toLevel1Entity(RelacionEstadosDTO dto, RelacionEstados entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getEstadoFormularioSiguiente() != null) {
            entidad.setEstadoFormularioSiguiente(new EstadoFormulario());
            entidad.getEstadoFormularioSiguiente().setId(dto.getEstadoFormularioSiguiente().getId());
        }
        if (dto.getEstadoFormularioActual() != null) {
            entidad.setEstadoFormularioActual(new EstadoFormulario());
            entidad.getEstadoFormularioActual().setId(dto.getEstadoFormularioActual().getId());
        }
        if (dto.getTipoFormulario() != null) {
            entidad.setTipoFormulario(new TipoFormulario());
            entidad.getTipoFormulario().setId(dto.getTipoFormulario().getId());
        }
        if (dto.getOrganismoTranisto() != null) {
            entidad.setCodigoOrganismo(new OrganismoTransito());
            entidad.getCodigoOrganismo().setCodigoOrganismo(dto.getOrganismoTranisto().getCodigoOrganismo());
        }
        return entidad;
    }

    /**
     * Permite obtener a partir de una lista de objeto de tipo dto
     * RelacionEstadosDTO un listado de entidades del tipo RelacionEstados
     * con los campos id
     * 
     * @param listDto
     * @return
     */
    public static List<RelacionEstados> toListLevel0Entity(List<RelacionEstadosDTO> listDto) {
        List<RelacionEstados> listEntidad = new ArrayList<RelacionEstados>();
        for (RelacionEstadosDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    /**
     * Permite obtener a partir de una lista de objeto de tipo dto
     * RelacionEstadosDTO un listado de entidades del tipo RelacionEstados
     * con los campos estado siguiente, estado actual, tipo de formulario 
     * y organismo
     * 
     * @param listDto
     * @return
     */
    public static List<RelacionEstados> toListLevel1Entity(List<RelacionEstadosDTO> listDto) {
        List<RelacionEstados> listEntidad = new ArrayList<RelacionEstados>();
        for (RelacionEstadosDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
