package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ErrorInconsistenciaPagoDTO;
import co.com.datatools.c2.entidades.ErrorInconsistenciaPago;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 27 12:13:59 COT 2016
 */
public class ErrorInconsistenciaPagoHelper {
    // --- to DTO
    public static ErrorInconsistenciaPagoDTO toLevel0DTO(ErrorInconsistenciaPago entidad) {
        ErrorInconsistenciaPagoDTO dto = new ErrorInconsistenciaPagoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ErrorInconsistenciaPagoDTO toLevel1DTO(ErrorInconsistenciaPago entidad) {
        ErrorInconsistenciaPagoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ErrorInconsistenciaPagoDTO> toListLevel0DTO(List<ErrorInconsistenciaPago> listEntidad) {
        List<ErrorInconsistenciaPagoDTO> listDto = new ArrayList<ErrorInconsistenciaPagoDTO>();
        for (ErrorInconsistenciaPago entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ErrorInconsistenciaPagoDTO> toListLevel1DTO(List<ErrorInconsistenciaPago> listEntidad) {
        List<ErrorInconsistenciaPagoDTO> listDto = new ArrayList<ErrorInconsistenciaPagoDTO>();
        for (ErrorInconsistenciaPago entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ErrorInconsistenciaPago toLevel0Entity(ErrorInconsistenciaPagoDTO dto, ErrorInconsistenciaPago entidad) {
        if (null == entidad) {
            entidad = new ErrorInconsistenciaPago();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ErrorInconsistenciaPago toLevel1Entity(ErrorInconsistenciaPagoDTO dto, ErrorInconsistenciaPago entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ErrorInconsistenciaPago> toListLevel0Entity(List<ErrorInconsistenciaPagoDTO> listDto) {
        List<ErrorInconsistenciaPago> listEntidad = new ArrayList<ErrorInconsistenciaPago>();
        for (ErrorInconsistenciaPagoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ErrorInconsistenciaPago> toListLevel1Entity(List<ErrorInconsistenciaPagoDTO> listDto) {
        List<ErrorInconsistenciaPago> listEntidad = new ArrayList<ErrorInconsistenciaPago>();
        for (ErrorInconsistenciaPagoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
