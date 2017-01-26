package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoEntidadDTO;
import co.com.datatools.c2.entidades.TipoEntidad;

/**
 * @author Generated
 * @version 3.0 - Thu Dec 10 16:26:23 COT 2015
 */
public class TipoEntidadHelper {
    // --- to DTO
    public static TipoEntidadDTO toLevel0DTO(TipoEntidad entidad) {
        TipoEntidadDTO dto = new TipoEntidadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoEntidadDTO toLevel1DTO(TipoEntidad entidad) {
        TipoEntidadDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoEntidadDTO> toListLevel0DTO(List<TipoEntidad> listEntidad) {
        List<TipoEntidadDTO> listDto = new ArrayList<TipoEntidadDTO>();
        for (TipoEntidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoEntidadDTO> toListLevel1DTO(List<TipoEntidad> listEntidad) {
        List<TipoEntidadDTO> listDto = new ArrayList<TipoEntidadDTO>();
        for (TipoEntidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoEntidad toLevel0Entity(TipoEntidadDTO dto, TipoEntidad entidad) {
        if (null == entidad) {
            entidad = new TipoEntidad();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoEntidad toLevel1Entity(TipoEntidadDTO dto, TipoEntidad entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoEntidad> toListLevel0Entity(List<TipoEntidadDTO> listDto) {
        List<TipoEntidad> listEntidad = new ArrayList<TipoEntidad>();
        for (TipoEntidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoEntidad> toListLevel1Entity(List<TipoEntidadDTO> listDto) {
        List<TipoEntidad> listEntidad = new ArrayList<TipoEntidad>();
        for (TipoEntidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
