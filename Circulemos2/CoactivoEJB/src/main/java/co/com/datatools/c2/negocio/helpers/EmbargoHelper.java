package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EmbargoDTO;
import co.com.datatools.c2.entidades.Embargo;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class EmbargoHelper {
    // --- to DTO
    public static EmbargoDTO toLevel0DTO(Embargo entidad) {
        EmbargoDTO dto = new EmbargoDTO();
        dto.setId(entidad.getId());
        dto.setValorEmbargo(entidad.getValorEmbargo());

        return dto;
    }

    public static EmbargoDTO toLevel1DTO(Embargo entidad) {
        EmbargoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EmbargoDTO> toListLevel0DTO(List<Embargo> listEntidad) {
        List<EmbargoDTO> listDto = new ArrayList<EmbargoDTO>();
        for (Embargo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EmbargoDTO> toListLevel1DTO(List<Embargo> listEntidad) {
        List<EmbargoDTO> listDto = new ArrayList<EmbargoDTO>();
        for (Embargo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Embargo toLevel0Entity(EmbargoDTO dto, Embargo entidad) {
        if (null == entidad) {
            entidad = new Embargo();
        }
        entidad.setId(dto.getId());
        entidad.setValorEmbargo(dto.getValorEmbargo());

        return entidad;
    }

    public static Embargo toLevel1Entity(EmbargoDTO dto, Embargo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Embargo> toListLevel0Entity(List<EmbargoDTO> listDto) {
        List<Embargo> listEntidad = new ArrayList<Embargo>();
        for (EmbargoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Embargo> toListLevel1Entity(List<EmbargoDTO> listDto) {
        List<Embargo> listEntidad = new ArrayList<Embargo>();
        for (EmbargoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
