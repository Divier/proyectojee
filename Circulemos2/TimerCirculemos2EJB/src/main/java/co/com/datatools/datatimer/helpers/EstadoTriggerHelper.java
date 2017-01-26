package co.com.datatools.datatimer.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.datatimer.c2.entidades.EstadoTrigger;
import co.com.datatools.datatimer.dto.EstadoTriggerDTO;

/**
 * No volver a autogenerar
 * 
 * @author Generated
 * @version 3.0 - Thu Apr 28 17:54:30 COT 2016
 */
public class EstadoTriggerHelper {
    // --- to DTO
    public static EstadoTriggerDTO toLevel0DTO(EstadoTrigger entidad) {
        EstadoTriggerDTO dto = new EstadoTriggerDTO();
        dto.setId(entidad.getId());
        dto.setNombreEstadoTrigger(entidad.getNombreEstadoTrigger());

        return dto;
    }

    public static EstadoTriggerDTO toLevel1DTO(EstadoTrigger entidad) {
        EstadoTriggerDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoTriggerDTO> toListLevel0DTO(List<EstadoTrigger> listEntidad) {
        List<EstadoTriggerDTO> listDto = new ArrayList<EstadoTriggerDTO>();
        for (EstadoTrigger entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoTriggerDTO> toListLevel1DTO(List<EstadoTrigger> listEntidad) {
        List<EstadoTriggerDTO> listDto = new ArrayList<EstadoTriggerDTO>();
        for (EstadoTrigger entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoTrigger toLevel0Entity(EstadoTriggerDTO dto, EstadoTrigger entidad) {
        if (null == entidad) {
            entidad = new EstadoTrigger();
        }
        entidad.setId(dto.getId());
        entidad.setNombreEstadoTrigger(dto.getNombreEstadoTrigger());

        return entidad;
    }

    public static EstadoTrigger toLevel1Entity(EstadoTriggerDTO dto, EstadoTrigger entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoTrigger> toListLevel0Entity(List<EstadoTriggerDTO> listDto) {
        List<EstadoTrigger> listEntidad = new ArrayList<EstadoTrigger>();
        for (EstadoTriggerDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoTrigger> toListLevel1Entity(List<EstadoTriggerDTO> listDto) {
        List<EstadoTrigger> listEntidad = new ArrayList<EstadoTrigger>();
        for (EstadoTriggerDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}