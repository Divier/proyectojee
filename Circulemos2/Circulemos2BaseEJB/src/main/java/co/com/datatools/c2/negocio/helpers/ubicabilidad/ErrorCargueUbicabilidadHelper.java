package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.ErrorCargueUbicabilidadDTO;
import co.com.datatools.c2.entidades.ubicabilidad.ErrorCargueUbicabilidad;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:51 COT 2016
 */
public class ErrorCargueUbicabilidadHelper {
    // --- to DTO
    public static ErrorCargueUbicabilidadDTO toLevel0DTO(ErrorCargueUbicabilidad entidad) {
        ErrorCargueUbicabilidadDTO dto = new ErrorCargueUbicabilidadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ErrorCargueUbicabilidadDTO toLevel1DTO(ErrorCargueUbicabilidad entidad) {
        ErrorCargueUbicabilidadDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ErrorCargueUbicabilidadDTO> toListLevel0DTO(List<ErrorCargueUbicabilidad> listEntidad) {
        List<ErrorCargueUbicabilidadDTO> listDto = new ArrayList<ErrorCargueUbicabilidadDTO>();
        for (ErrorCargueUbicabilidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ErrorCargueUbicabilidadDTO> toListLevel1DTO(List<ErrorCargueUbicabilidad> listEntidad) {
        List<ErrorCargueUbicabilidadDTO> listDto = new ArrayList<ErrorCargueUbicabilidadDTO>();
        for (ErrorCargueUbicabilidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ErrorCargueUbicabilidad toLevel0Entity(ErrorCargueUbicabilidadDTO dto,
            ErrorCargueUbicabilidad entidad) {
        if (null == entidad) {
            entidad = new ErrorCargueUbicabilidad();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ErrorCargueUbicabilidad toLevel1Entity(ErrorCargueUbicabilidadDTO dto,
            ErrorCargueUbicabilidad entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ErrorCargueUbicabilidad> toListLevel0Entity(List<ErrorCargueUbicabilidadDTO> listDto) {
        List<ErrorCargueUbicabilidad> listEntidad = new ArrayList<ErrorCargueUbicabilidad>();
        for (ErrorCargueUbicabilidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ErrorCargueUbicabilidad> toListLevel1Entity(List<ErrorCargueUbicabilidadDTO> listDto) {
        List<ErrorCargueUbicabilidad> listEntidad = new ArrayList<ErrorCargueUbicabilidad>();
        for (ErrorCargueUbicabilidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
