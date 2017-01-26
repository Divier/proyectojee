package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ErrorCargueCourierDTO;
import co.com.datatools.c2.entidades.ErrorCargueCourier;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:46 COT 2016
 */
public class ErrorCargueCourierHelper {
    // --- to DTO
    public static ErrorCargueCourierDTO toLevel0DTO(ErrorCargueCourier entidad) {
        ErrorCargueCourierDTO dto = new ErrorCargueCourierDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ErrorCargueCourierDTO toLevel1DTO(ErrorCargueCourier entidad) {
        ErrorCargueCourierDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ErrorCargueCourierDTO> toListLevel0DTO(List<ErrorCargueCourier> listEntidad) {
        List<ErrorCargueCourierDTO> listDto = new ArrayList<ErrorCargueCourierDTO>();
        for (ErrorCargueCourier entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ErrorCargueCourierDTO> toListLevel1DTO(List<ErrorCargueCourier> listEntidad) {
        List<ErrorCargueCourierDTO> listDto = new ArrayList<ErrorCargueCourierDTO>();
        for (ErrorCargueCourier entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ErrorCargueCourier toLevel0Entity(ErrorCargueCourierDTO dto, ErrorCargueCourier entidad) {
        if (null == entidad) {
            entidad = new ErrorCargueCourier();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ErrorCargueCourier toLevel1Entity(ErrorCargueCourierDTO dto, ErrorCargueCourier entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ErrorCargueCourier> toListLevel0Entity(List<ErrorCargueCourierDTO> listDto) {
        List<ErrorCargueCourier> listEntidad = new ArrayList<ErrorCargueCourier>();
        for (ErrorCargueCourierDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ErrorCargueCourier> toListLevel1Entity(List<ErrorCargueCourierDTO> listDto) {
        List<ErrorCargueCourier> listEntidad = new ArrayList<ErrorCargueCourier>();
        for (ErrorCargueCourierDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
