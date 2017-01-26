package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TipoNotificacionComparendoDTO;
import co.com.datatools.c2.entidades.TipoNotificacionComparendo;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoNotificacionComparendoHelper {
    // --- to DTO
    public static TipoNotificacionComparendoDTO toLevel0DTO(TipoNotificacionComparendo entidad) {
        TipoNotificacionComparendoDTO dto = new TipoNotificacionComparendoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static List<TipoNotificacionComparendoDTO> toListLevel0DTO(List<TipoNotificacionComparendo> listEntidad) {
        List<TipoNotificacionComparendoDTO> listDto = new ArrayList<TipoNotificacionComparendoDTO>();
        for (TipoNotificacionComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoNotificacionComparendo toLevel0Entity(TipoNotificacionComparendoDTO dto,
            TipoNotificacionComparendo entidad) {
        if (null == entidad) {
            entidad = new TipoNotificacionComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static List<TipoNotificacionComparendo> toListLevel0Entity(List<TipoNotificacionComparendoDTO> listDto) {
        List<TipoNotificacionComparendo> listEntidad = new ArrayList<TipoNotificacionComparendo>();
        for (TipoNotificacionComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    // --- fin to Entidad
}
