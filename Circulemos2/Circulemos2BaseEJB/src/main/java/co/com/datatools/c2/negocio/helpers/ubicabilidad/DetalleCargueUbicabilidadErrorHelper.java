package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.DetalleCargueUbicabilidadErrorDTO;
import co.com.datatools.c2.entidades.ubicabilidad.DetalleCargueUbicabilidad;
import co.com.datatools.c2.entidades.ubicabilidad.DetalleCargueUbicabilidadError;
import co.com.datatools.c2.entidades.ubicabilidad.ErrorCargueUbicabilidad;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:49 COT 2016
 */
public class DetalleCargueUbicabilidadErrorHelper {
    // --- to DTO
    public static DetalleCargueUbicabilidadErrorDTO toLevel0DTO(DetalleCargueUbicabilidadError entidad) {
        DetalleCargueUbicabilidadErrorDTO dto = new DetalleCargueUbicabilidadErrorDTO();
        dto.setId(entidad.getId());
        dto.setFechaRegistro(entidad.getFechaRegistro());

        return dto;
    }

    public static DetalleCargueUbicabilidadErrorDTO toLevel1DTO(DetalleCargueUbicabilidadError entidad) {
        DetalleCargueUbicabilidadErrorDTO dto = toLevel0DTO(entidad);
        if (entidad.getDetalleCargueUbicabilidad() != null)
            dto.setDetalleCargueUbicabilidad(
                    DetalleCargueUbicabilidadHelper.toLevel0DTO(entidad.getDetalleCargueUbicabilidad()));
        if (entidad.getErrorCargueUbicabilidad() != null)
            dto.setErrorCargueUbicabilidad(
                    ErrorCargueUbicabilidadHelper.toLevel0DTO(entidad.getErrorCargueUbicabilidad()));

        return dto;
    }

    public static List<DetalleCargueUbicabilidadErrorDTO> toListLevel0DTO(
            List<DetalleCargueUbicabilidadError> listEntidad) {
        List<DetalleCargueUbicabilidadErrorDTO> listDto = new ArrayList<DetalleCargueUbicabilidadErrorDTO>();
        for (DetalleCargueUbicabilidadError entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleCargueUbicabilidadErrorDTO> toListLevel1DTO(
            List<DetalleCargueUbicabilidadError> listEntidad) {
        List<DetalleCargueUbicabilidadErrorDTO> listDto = new ArrayList<DetalleCargueUbicabilidadErrorDTO>();
        for (DetalleCargueUbicabilidadError entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleCargueUbicabilidadError toLevel0Entity(DetalleCargueUbicabilidadErrorDTO dto,
            DetalleCargueUbicabilidadError entidad) {
        if (null == entidad) {
            entidad = new DetalleCargueUbicabilidadError();
        }
        entidad.setId(dto.getId());
        entidad.setFechaRegistro(dto.getFechaRegistro());

        return entidad;
    }

    public static DetalleCargueUbicabilidadError toLevel1Entity(DetalleCargueUbicabilidadErrorDTO dto,
            DetalleCargueUbicabilidadError entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDetalleCargueUbicabilidad() != null) {
            entidad.setDetalleCargueUbicabilidad(new DetalleCargueUbicabilidad());
            entidad.getDetalleCargueUbicabilidad().setId(dto.getDetalleCargueUbicabilidad().getId());
        }
        if (dto.getErrorCargueUbicabilidad() != null) {
            entidad.setErrorCargueUbicabilidad(new ErrorCargueUbicabilidad());
            entidad.getErrorCargueUbicabilidad().setId(dto.getErrorCargueUbicabilidad().getId());
        }

        return entidad;
    }

    public static List<DetalleCargueUbicabilidadError> toListLevel0Entity(
            List<DetalleCargueUbicabilidadErrorDTO> listDto) {
        List<DetalleCargueUbicabilidadError> listEntidad = new ArrayList<DetalleCargueUbicabilidadError>();
        for (DetalleCargueUbicabilidadErrorDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleCargueUbicabilidadError> toListLevel1Entity(
            List<DetalleCargueUbicabilidadErrorDTO> listDto) {
        List<DetalleCargueUbicabilidadError> listEntidad = new ArrayList<DetalleCargueUbicabilidadError>();
        for (DetalleCargueUbicabilidadErrorDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
