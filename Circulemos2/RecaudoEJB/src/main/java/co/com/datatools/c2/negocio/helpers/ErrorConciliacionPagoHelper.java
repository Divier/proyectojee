package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ErrorConciliacionPagoDTO;
import co.com.datatools.c2.entidades.ErrorConciliacionPago;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 14:45:13 COT 2016
 */
public class ErrorConciliacionPagoHelper {
    // --- to DTO
    public static ErrorConciliacionPagoDTO toLevel0DTO(ErrorConciliacionPago entidad) {
        ErrorConciliacionPagoDTO dto = new ErrorConciliacionPagoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ErrorConciliacionPagoDTO toLevel1DTO(ErrorConciliacionPago entidad) {
        ErrorConciliacionPagoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ErrorConciliacionPagoDTO> toListLevel0DTO(List<ErrorConciliacionPago> listEntidad) {
        List<ErrorConciliacionPagoDTO> listDto = new ArrayList<ErrorConciliacionPagoDTO>();
        for (ErrorConciliacionPago entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ErrorConciliacionPagoDTO> toListLevel1DTO(List<ErrorConciliacionPago> listEntidad) {
        List<ErrorConciliacionPagoDTO> listDto = new ArrayList<ErrorConciliacionPagoDTO>();
        for (ErrorConciliacionPago entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ErrorConciliacionPago toLevel0Entity(ErrorConciliacionPagoDTO dto, ErrorConciliacionPago entidad) {
        if (null == entidad) {
            entidad = new ErrorConciliacionPago();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ErrorConciliacionPago toLevel1Entity(ErrorConciliacionPagoDTO dto, ErrorConciliacionPago entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ErrorConciliacionPago> toListLevel0Entity(List<ErrorConciliacionPagoDTO> listDto) {
        List<ErrorConciliacionPago> listEntidad = new ArrayList<ErrorConciliacionPago>();
        for (ErrorConciliacionPagoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ErrorConciliacionPago> toListLevel1Entity(List<ErrorConciliacionPagoDTO> listDto) {
        List<ErrorConciliacionPago> listEntidad = new ArrayList<ErrorConciliacionPago>();
        for (ErrorConciliacionPagoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
