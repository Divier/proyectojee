package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TipoComparendoDTO;
import co.com.datatools.c2.entidades.TipoComparendo;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 06 09:12:32 COT 2015
 */
public class TipoComparendoHelper {
    // --- to DTO
    public static TipoComparendoDTO toLevel0DTO(TipoComparendo entidad) {
        TipoComparendoDTO dto = new TipoComparendoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setEstado(entidad.getEstado());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoComparendoDTO toLevel1DTO(TipoComparendo entidad) {
        TipoComparendoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoComparendoDTO> toListLevel0DTO(List<TipoComparendo> listEntidad) {
        List<TipoComparendoDTO> listDto = new ArrayList<TipoComparendoDTO>();
        for (TipoComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoComparendoDTO> toListLevel1DTO(List<TipoComparendo> listEntidad) {
        List<TipoComparendoDTO> listDto = new ArrayList<TipoComparendoDTO>();
        for (TipoComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoComparendo toLevel0Entity(TipoComparendoDTO dto, TipoComparendo entidad) {
        if (null == entidad) {
            entidad = new TipoComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setEstado(dto.getEstado());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoComparendo toLevel1Entity(TipoComparendoDTO dto, TipoComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoComparendo> toListLevel0Entity(List<TipoComparendoDTO> listDto) {
        List<TipoComparendo> listEntidad = new ArrayList<TipoComparendo>();
        for (TipoComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoComparendo> toListLevel1Entity(List<TipoComparendoDTO> listDto) {
        List<TipoComparendo> listEntidad = new ArrayList<TipoComparendo>();
        for (TipoComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}