package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TipoOrigenComparendoDTO;
import co.com.datatools.c2.entidades.TipoOrigenComparendo;

/**
 * @author Generated
 * @version 3.0 - Thu Dec 10 16:30:39 COT 2015
 */
public class TipoOrigenComparendoHelper {
    // --- to DTO
    public static TipoOrigenComparendoDTO toLevel0DTO(TipoOrigenComparendo entidad) {
        TipoOrigenComparendoDTO dto = new TipoOrigenComparendoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoOrigenComparendoDTO toLevel1DTO(TipoOrigenComparendo entidad) {
        TipoOrigenComparendoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoOrigenComparendoDTO> toListLevel0DTO(List<TipoOrigenComparendo> listEntidad) {
        List<TipoOrigenComparendoDTO> listDto = new ArrayList<TipoOrigenComparendoDTO>();
        for (TipoOrigenComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoOrigenComparendoDTO> toListLevel1DTO(List<TipoOrigenComparendo> listEntidad) {
        List<TipoOrigenComparendoDTO> listDto = new ArrayList<TipoOrigenComparendoDTO>();
        for (TipoOrigenComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoOrigenComparendo toLevel0Entity(TipoOrigenComparendoDTO dto, TipoOrigenComparendo entidad) {
        if (null == entidad) {
            entidad = new TipoOrigenComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoOrigenComparendo toLevel1Entity(TipoOrigenComparendoDTO dto, TipoOrigenComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        return entidad;
    }

    public static List<TipoOrigenComparendo> toListLevel0Entity(List<TipoOrigenComparendoDTO> listDto) {
        List<TipoOrigenComparendo> listEntidad = new ArrayList<TipoOrigenComparendo>();
        for (TipoOrigenComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoOrigenComparendo> toListLevel1Entity(List<TipoOrigenComparendoDTO> listDto) {
        List<TipoOrigenComparendo> listEntidad = new ArrayList<TipoOrigenComparendo>();
        for (TipoOrigenComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
