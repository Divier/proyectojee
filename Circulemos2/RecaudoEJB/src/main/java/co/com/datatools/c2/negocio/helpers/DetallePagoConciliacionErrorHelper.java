package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetallePagoConciliacionErrorDTO;
import co.com.datatools.c2.entidades.DetallePago;
import co.com.datatools.c2.entidades.DetallePagoConciliacionError;
import co.com.datatools.c2.entidades.ErrorConciliacionPago;

/**
 * @author Generated
 * @version 3.0 - Tue May 10 16:43:07 COT 2016
 */
public class DetallePagoConciliacionErrorHelper {
    // --- to DTO
    public static DetallePagoConciliacionErrorDTO toLevel0DTO(DetallePagoConciliacionError entidad) {
        DetallePagoConciliacionErrorDTO dto = new DetallePagoConciliacionErrorDTO();
        dto.setId(entidad.getId());
        dto.setFechaRegistro(entidad.getFechaRegistro());

        return dto;
    }

    public static DetallePagoConciliacionErrorDTO toLevel1DTO(DetallePagoConciliacionError entidad) {
        DetallePagoConciliacionErrorDTO dto = toLevel0DTO(entidad);
        if (entidad.getDetallePago() != null)
            dto.setDetallePago(DetallePagoHelper.toLevel0DTO(entidad.getDetallePago()));
        if (entidad.getErrorConciliacionPago() != null)
            dto.setErrorConciliacionPago(ErrorConciliacionPagoHelper.toLevel0DTO(entidad.getErrorConciliacionPago()));

        return dto;
    }

    public static List<DetallePagoConciliacionErrorDTO> toListLevel0DTO(
            List<DetallePagoConciliacionError> listEntidad) {
        List<DetallePagoConciliacionErrorDTO> listDto = new ArrayList<DetallePagoConciliacionErrorDTO>();
        for (DetallePagoConciliacionError entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetallePagoConciliacionErrorDTO> toListLevel1DTO(
            List<DetallePagoConciliacionError> listEntidad) {
        List<DetallePagoConciliacionErrorDTO> listDto = new ArrayList<DetallePagoConciliacionErrorDTO>();
        for (DetallePagoConciliacionError entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetallePagoConciliacionError toLevel0Entity(DetallePagoConciliacionErrorDTO dto,
            DetallePagoConciliacionError entidad) {
        if (null == entidad) {
            entidad = new DetallePagoConciliacionError();
        }
        entidad.setId(dto.getId());
        entidad.setFechaRegistro(dto.getFechaRegistro());

        return entidad;
    }

    public static DetallePagoConciliacionError toLevel1Entity(DetallePagoConciliacionErrorDTO dto,
            DetallePagoConciliacionError entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDetallePago() != null) {
            entidad.setDetallePago(new DetallePago());
            entidad.getDetallePago().setId(dto.getDetallePago().getId());
        }
        if (dto.getErrorConciliacionPago() != null) {
            entidad.setErrorConciliacionPago(new ErrorConciliacionPago());
            entidad.getErrorConciliacionPago().setId(dto.getErrorConciliacionPago().getId());
        }

        return entidad;
    }

    public static List<DetallePagoConciliacionError> toListLevel0Entity(List<DetallePagoConciliacionErrorDTO> listDto) {
        List<DetallePagoConciliacionError> listEntidad = new ArrayList<DetallePagoConciliacionError>();
        for (DetallePagoConciliacionErrorDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetallePagoConciliacionError> toListLevel1Entity(List<DetallePagoConciliacionErrorDTO> listDto) {
        List<DetallePagoConciliacionError> listEntidad = new ArrayList<DetallePagoConciliacionError>();
        for (DetallePagoConciliacionErrorDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
