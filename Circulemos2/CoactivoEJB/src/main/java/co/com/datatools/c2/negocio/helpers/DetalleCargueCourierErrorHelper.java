package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleCargueCourierErrorDTO;
import co.com.datatools.c2.entidades.DetalleCargueCourier;
import co.com.datatools.c2.entidades.DetalleCargueCourierError;
import co.com.datatools.c2.entidades.ErrorCargueCourier;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:47 COT 2016
 */
public class DetalleCargueCourierErrorHelper {
    // --- to DTO
    public static DetalleCargueCourierErrorDTO toLevel0DTO(DetalleCargueCourierError entidad) {
        DetalleCargueCourierErrorDTO dto = new DetalleCargueCourierErrorDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static DetalleCargueCourierErrorDTO toLevel1DTO(DetalleCargueCourierError entidad) {
        DetalleCargueCourierErrorDTO dto = toLevel0DTO(entidad);
        if (entidad.getDetalleCargueCourier() != null)
            dto.setDetalleCargueCourier(DetalleCargueCourierHelper.toLevel0DTO(entidad.getDetalleCargueCourier()));
        if (entidad.getErrorCargueCourier() != null)
            dto.setErrorCargueCourier(ErrorCargueCourierHelper.toLevel0DTO(entidad.getErrorCargueCourier()));

        return dto;
    }

    public static List<DetalleCargueCourierErrorDTO> toListLevel0DTO(List<DetalleCargueCourierError> listEntidad) {
        List<DetalleCargueCourierErrorDTO> listDto = new ArrayList<DetalleCargueCourierErrorDTO>();
        for (DetalleCargueCourierError entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleCargueCourierErrorDTO> toListLevel1DTO(List<DetalleCargueCourierError> listEntidad) {
        List<DetalleCargueCourierErrorDTO> listDto = new ArrayList<DetalleCargueCourierErrorDTO>();
        for (DetalleCargueCourierError entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleCargueCourierError toLevel0Entity(DetalleCargueCourierErrorDTO dto,
            DetalleCargueCourierError entidad) {
        if (null == entidad) {
            entidad = new DetalleCargueCourierError();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static DetalleCargueCourierError toLevel1Entity(DetalleCargueCourierErrorDTO dto,
            DetalleCargueCourierError entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDetalleCargueCourier() != null) {
            entidad.setDetalleCargueCourier(new DetalleCargueCourier());
            entidad.getDetalleCargueCourier().setId(dto.getDetalleCargueCourier().getId());
        }
        if (dto.getErrorCargueCourier() != null) {
            entidad.setErrorCargueCourier(new ErrorCargueCourier());
            entidad.getErrorCargueCourier().setId(dto.getErrorCargueCourier().getId());
        }

        return entidad;
    }

    public static List<DetalleCargueCourierError> toListLevel0Entity(List<DetalleCargueCourierErrorDTO> listDto) {
        List<DetalleCargueCourierError> listEntidad = new ArrayList<DetalleCargueCourierError>();
        for (DetalleCargueCourierErrorDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleCargueCourierError> toListLevel1Entity(List<DetalleCargueCourierErrorDTO> listDto) {
        List<DetalleCargueCourierError> listEntidad = new ArrayList<DetalleCargueCourierError>();
        for (DetalleCargueCourierErrorDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
