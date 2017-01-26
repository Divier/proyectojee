package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoFalloDTO;
import co.com.datatools.c2.entidades.TipoFallo;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 13:52:10 COT 2016
 */
public class TipoFalloHelper {
    // --- to DTO
    public static TipoFalloDTO toLevel0DTO(TipoFallo entidad) {
        TipoFalloDTO dto = new TipoFalloDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getActivo());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoFalloDTO toLevel1DTO(TipoFallo entidad) {
        TipoFalloDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoFalloDTO> toListLevel0DTO(List<TipoFallo> listEntidad) {
        List<TipoFalloDTO> listDto = new ArrayList<TipoFalloDTO>();
        for (TipoFallo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoFalloDTO> toListLevel1DTO(List<TipoFallo> listEntidad) {
        List<TipoFalloDTO> listDto = new ArrayList<TipoFalloDTO>();
        for (TipoFallo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoFallo toLevel0Entity(TipoFalloDTO dto, TipoFallo entidad) {
        if (null == entidad) {
            entidad = new TipoFallo();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setActivo(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoFallo toLevel1Entity(TipoFalloDTO dto, TipoFallo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoFallo> toListLevel0Entity(List<TipoFalloDTO> listDto) {
        List<TipoFallo> listEntidad = new ArrayList<TipoFallo>();
        for (TipoFalloDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoFallo> toListLevel1Entity(List<TipoFalloDTO> listDto) {
        List<TipoFallo> listEntidad = new ArrayList<TipoFallo>();
        for (TipoFalloDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
