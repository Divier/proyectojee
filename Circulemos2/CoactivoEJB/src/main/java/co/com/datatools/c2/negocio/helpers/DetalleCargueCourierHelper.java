package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleCargueCourierDTO;
import co.com.datatools.c2.entidades.CargueCourier;
import co.com.datatools.c2.entidades.Coactivo;
import co.com.datatools.c2.entidades.DetalleCargueCourier;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:47 COT 2016
 */
public class DetalleCargueCourierHelper {
    // --- to DTO
    public static DetalleCargueCourierDTO toLevel0DTO(DetalleCargueCourier entidad) {
        DetalleCargueCourierDTO dto = new DetalleCargueCourierDTO();
        dto.setId(entidad.getId());
        dto.setNumeroProceso(entidad.getNumeroProceso());
        dto.setRespuesta(entidad.getRespuesta());
        dto.setFechaProceso(entidad.getFechaProceso());
        return dto;
    }

    public static DetalleCargueCourierDTO toLevel1DTO(DetalleCargueCourier entidad) {
        DetalleCargueCourierDTO dto = toLevel0DTO(entidad);
        if (entidad.getCargueCourier() != null)
            dto.setCargueCourier(CargueCourierHelper.toLevel0DTO(entidad.getCargueCourier()));
        if (entidad.getCoactivo() != null)
            dto.setCoactivo(CoactivoHelper.toLevel0DTO(entidad.getCoactivo()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));

        return dto;
    }

    public static List<DetalleCargueCourierDTO> toListLevel0DTO(List<DetalleCargueCourier> listEntidad) {
        List<DetalleCargueCourierDTO> listDto = new ArrayList<DetalleCargueCourierDTO>();
        for (DetalleCargueCourier entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleCargueCourierDTO> toListLevel1DTO(List<DetalleCargueCourier> listEntidad) {
        List<DetalleCargueCourierDTO> listDto = new ArrayList<DetalleCargueCourierDTO>();
        for (DetalleCargueCourier entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleCargueCourier toLevel0Entity(DetalleCargueCourierDTO dto, DetalleCargueCourier entidad) {
        if (null == entidad) {
            entidad = new DetalleCargueCourier();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroProceso(dto.getNumeroProceso());
        entidad.setRespuesta(dto.getRespuesta());
        entidad.setFechaProceso(dto.getFechaProceso());
        return entidad;
    }

    public static DetalleCargueCourier toLevel1Entity(DetalleCargueCourierDTO dto, DetalleCargueCourier entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCargueCourier() != null) {
            entidad.setCargueCourier(new CargueCourier());
            entidad.getCargueCourier().setId(dto.getCargueCourier().getId());
        }
        if (dto.getCoactivo() != null) {
            entidad.setCoactivo(new Coactivo());
            entidad.getCoactivo().setId(dto.getCoactivo().getId());
        }
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }

        return entidad;
    }

    public static List<DetalleCargueCourier> toListLevel0Entity(List<DetalleCargueCourierDTO> listDto) {
        List<DetalleCargueCourier> listEntidad = new ArrayList<DetalleCargueCourier>();
        for (DetalleCargueCourierDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleCargueCourier> toListLevel1Entity(List<DetalleCargueCourierDTO> listDto) {
        List<DetalleCargueCourier> listEntidad = new ArrayList<DetalleCargueCourier>();
        for (DetalleCargueCourierDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
