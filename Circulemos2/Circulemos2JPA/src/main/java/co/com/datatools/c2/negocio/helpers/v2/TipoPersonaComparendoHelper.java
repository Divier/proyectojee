package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoPersonaComparendoDTO;
import co.com.datatools.c2.entidades.TipoPersonaComparendo;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoPersonaComparendoHelper {
    // --- to DTO
    public static TipoPersonaComparendoDTO toLevel0DTO(TipoPersonaComparendo entidad) {
        TipoPersonaComparendoDTO dto = new TipoPersonaComparendoDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoPersonaComparendoDTO toLevel1DTO(TipoPersonaComparendo entidad) {
        TipoPersonaComparendoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoPersonaComparendoDTO> toListLevel0DTO(List<TipoPersonaComparendo> listEntidad) {
        List<TipoPersonaComparendoDTO> listDto = new ArrayList<TipoPersonaComparendoDTO>();
        for (TipoPersonaComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoPersonaComparendoDTO> toListLevel1DTO(List<TipoPersonaComparendo> listEntidad) {
        List<TipoPersonaComparendoDTO> listDto = new ArrayList<TipoPersonaComparendoDTO>();
        for (TipoPersonaComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoPersonaComparendo toLevel0Entity(TipoPersonaComparendoDTO dto, TipoPersonaComparendo entidad) {
        if (null == entidad) {
            entidad = new TipoPersonaComparendo();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoPersonaComparendo toLevel1Entity(TipoPersonaComparendoDTO dto, TipoPersonaComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoPersonaComparendo> toListLevel0Entity(List<TipoPersonaComparendoDTO> listDto) {
        List<TipoPersonaComparendo> listEntidad = new ArrayList<TipoPersonaComparendo>();
        for (TipoPersonaComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoPersonaComparendo> toListLevel1Entity(List<TipoPersonaComparendoDTO> listDto) {
        List<TipoPersonaComparendo> listEntidad = new ArrayList<TipoPersonaComparendo>();
        for (TipoPersonaComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
