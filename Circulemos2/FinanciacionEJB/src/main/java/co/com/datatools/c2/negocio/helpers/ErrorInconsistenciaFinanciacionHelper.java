package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ErrorInconsistenciaFinanciacionDTO;
import co.com.datatools.c2.entidades.ErrorInconsistenciaFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 10:59:51 COT 2016
 */
public class ErrorInconsistenciaFinanciacionHelper {
    // --- to DTO
    public static ErrorInconsistenciaFinanciacionDTO toLevel0DTO(ErrorInconsistenciaFinanciacion entidad) {
        ErrorInconsistenciaFinanciacionDTO dto = new ErrorInconsistenciaFinanciacionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ErrorInconsistenciaFinanciacionDTO toLevel1DTO(ErrorInconsistenciaFinanciacion entidad) {
        ErrorInconsistenciaFinanciacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ErrorInconsistenciaFinanciacionDTO> toListLevel0DTO(
            List<ErrorInconsistenciaFinanciacion> listEntidad) {
        List<ErrorInconsistenciaFinanciacionDTO> listDto = new ArrayList<ErrorInconsistenciaFinanciacionDTO>();
        for (ErrorInconsistenciaFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ErrorInconsistenciaFinanciacionDTO> toListLevel1DTO(
            List<ErrorInconsistenciaFinanciacion> listEntidad) {
        List<ErrorInconsistenciaFinanciacionDTO> listDto = new ArrayList<ErrorInconsistenciaFinanciacionDTO>();
        for (ErrorInconsistenciaFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ErrorInconsistenciaFinanciacion toLevel0Entity(ErrorInconsistenciaFinanciacionDTO dto,
            ErrorInconsistenciaFinanciacion entidad) {
        if (null == entidad) {
            entidad = new ErrorInconsistenciaFinanciacion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ErrorInconsistenciaFinanciacion toLevel1Entity(ErrorInconsistenciaFinanciacionDTO dto,
            ErrorInconsistenciaFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ErrorInconsistenciaFinanciacion> toListLevel0Entity(
            List<ErrorInconsistenciaFinanciacionDTO> listDto) {
        List<ErrorInconsistenciaFinanciacion> listEntidad = new ArrayList<ErrorInconsistenciaFinanciacion>();
        for (ErrorInconsistenciaFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ErrorInconsistenciaFinanciacion> toListLevel1Entity(
            List<ErrorInconsistenciaFinanciacionDTO> listDto) {
        List<ErrorInconsistenciaFinanciacion> listEntidad = new ArrayList<ErrorInconsistenciaFinanciacion>();
        for (ErrorInconsistenciaFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
