package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoAccionBackDTO;
import co.com.datatools.c2.entidades.TipoAccionBack;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:06:23 COT 2016
 */
public class TipoAccionBackHelper {
    // --- to DTO
    public static TipoAccionBackDTO toLevel0DTO(TipoAccionBack entidad) {
        TipoAccionBackDTO dto = new TipoAccionBackDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoAccionBackDTO toLevel1DTO(TipoAccionBack entidad) {
        TipoAccionBackDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoAccionBackDTO> toListLevel0DTO(List<TipoAccionBack> listEntidad) {
        List<TipoAccionBackDTO> listDto = new ArrayList<TipoAccionBackDTO>();
        for (TipoAccionBack entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoAccionBackDTO> toListLevel1DTO(List<TipoAccionBack> listEntidad) {
        List<TipoAccionBackDTO> listDto = new ArrayList<TipoAccionBackDTO>();
        for (TipoAccionBack entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoAccionBack toLevel0Entity(TipoAccionBackDTO dto, TipoAccionBack entidad) {
        if (null == entidad) {
            entidad = new TipoAccionBack();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoAccionBack toLevel1Entity(TipoAccionBackDTO dto, TipoAccionBack entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoAccionBack> toListLevel0Entity(List<TipoAccionBackDTO> listDto) {
        List<TipoAccionBack> listEntidad = new ArrayList<TipoAccionBack>();
        for (TipoAccionBackDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoAccionBack> toListLevel1Entity(List<TipoAccionBackDTO> listDto) {
        List<TipoAccionBack> listEntidad = new ArrayList<TipoAccionBack>();
        for (TipoAccionBackDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
