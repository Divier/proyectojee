package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CargueCourierDTO;
import co.com.datatools.c2.entidades.CargueCourier;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:48 COT 2016
 */
public class CargueCourierHelper {
    // --- to DTO
    public static CargueCourierDTO toLevel0DTO(CargueCourier entidad) {
        CargueCourierDTO dto = new CargueCourierDTO();
        dto.setId(entidad.getId());
        dto.setConsecutivo(entidad.getConsecutivo());
        dto.setFechaCargue(entidad.getFechaCargue());
        dto.setIdDocumentoCargue(entidad.getIdDocumentoCargue());

        return dto;
    }

    public static CargueCourierDTO toLevel1DTO(CargueCourier entidad) {
        CargueCourierDTO dto = toLevel0DTO(entidad);
        if (entidad.getUsuarioCargue() != null)
            dto.setUsuarioCargue(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioCargue()));

        return dto;
    }

    public static List<CargueCourierDTO> toListLevel0DTO(List<CargueCourier> listEntidad) {
        List<CargueCourierDTO> listDto = new ArrayList<CargueCourierDTO>();
        for (CargueCourier entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CargueCourierDTO> toListLevel1DTO(List<CargueCourier> listEntidad) {
        List<CargueCourierDTO> listDto = new ArrayList<CargueCourierDTO>();
        for (CargueCourier entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CargueCourier toLevel0Entity(CargueCourierDTO dto, CargueCourier entidad) {
        if (null == entidad) {
            entidad = new CargueCourier();
        }
        entidad.setId(dto.getId());
        entidad.setConsecutivo(dto.getConsecutivo());
        entidad.setFechaCargue(dto.getFechaCargue());
        entidad.setIdDocumentoCargue(dto.getIdDocumentoCargue());

        return entidad;
    }

    public static CargueCourier toLevel1Entity(CargueCourierDTO dto, CargueCourier entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getUsuarioCargue() != null) {
            entidad.setUsuarioCargue(new UsuarioPersona());
            entidad.getUsuarioCargue().setIdUsuario(dto.getUsuarioCargue().getUsuario().getId());
        }

        return entidad;
    }

    public static List<CargueCourier> toListLevel0Entity(List<CargueCourierDTO> listDto) {
        List<CargueCourier> listEntidad = new ArrayList<CargueCourier>();
        for (CargueCourierDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CargueCourier> toListLevel1Entity(List<CargueCourierDTO> listDto) {
        List<CargueCourier> listEntidad = new ArrayList<CargueCourier>();
        for (CargueCourierDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
