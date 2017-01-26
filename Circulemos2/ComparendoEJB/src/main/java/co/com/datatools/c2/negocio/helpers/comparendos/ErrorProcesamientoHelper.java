package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ErrorProcesamientoDTO;
import co.com.datatools.c2.entidades.ErrorProcesamiento;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 17 17:09:07 COT 2015
 */
public class ErrorProcesamientoHelper {
    // --- to DTO
    public static ErrorProcesamientoDTO toLevel0DTO(ErrorProcesamiento entidad) {
        ErrorProcesamientoDTO dto = new ErrorProcesamientoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ErrorProcesamientoDTO toLevel1DTO(ErrorProcesamiento entidad) {
        ErrorProcesamientoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ErrorProcesamientoDTO> toListLevel0DTO(List<ErrorProcesamiento> listEntidad) {
        List<ErrorProcesamientoDTO> listDto = new ArrayList<ErrorProcesamientoDTO>();
        for (ErrorProcesamiento entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ErrorProcesamientoDTO> toListLevel1DTO(List<ErrorProcesamiento> listEntidad) {
        List<ErrorProcesamientoDTO> listDto = new ArrayList<ErrorProcesamientoDTO>();
        for (ErrorProcesamiento entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ErrorProcesamiento toLevel0Entity(ErrorProcesamientoDTO dto, ErrorProcesamiento entidad) {
        if (null == entidad) {
            entidad = new ErrorProcesamiento();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ErrorProcesamiento toLevel1Entity(ErrorProcesamientoDTO dto, ErrorProcesamiento entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ErrorProcesamiento> toListLevel0Entity(List<ErrorProcesamientoDTO> listDto) {
        List<ErrorProcesamiento> listEntidad = new ArrayList<ErrorProcesamiento>();
        for (ErrorProcesamientoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ErrorProcesamiento> toListLevel1Entity(List<ErrorProcesamientoDTO> listDto) {
        List<ErrorProcesamiento> listEntidad = new ArrayList<ErrorProcesamiento>();
        for (ErrorProcesamientoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
