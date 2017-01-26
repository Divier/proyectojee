package co.com.datatools.datatimer.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.datatimer.c2.entidades.EstadoJob;
import co.com.datatools.datatimer.dto.EstadoJobDTO;

/**
 * No volver a autogenerar
 * 
 * @author Generated
 * @version 3.0 - Wed Apr 27 14:08:32 COT 2016
 */
public class EstadoJobHelper {
    // --- to DTO
    public static EstadoJobDTO toLevel0DTO(EstadoJob entidad) {
        EstadoJobDTO dto = new EstadoJobDTO();
        dto.setId(entidad.getId());
        dto.setNombreEstadoJob(entidad.getNombreEstadoJob());

        return dto;
    }

    public static EstadoJobDTO toLevel1DTO(EstadoJob entidad) {
        EstadoJobDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoJobDTO> toListLevel0DTO(List<EstadoJob> listEntidad) {
        List<EstadoJobDTO> listDto = new ArrayList<EstadoJobDTO>();
        for (EstadoJob entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoJobDTO> toListLevel1DTO(List<EstadoJob> listEntidad) {
        List<EstadoJobDTO> listDto = new ArrayList<EstadoJobDTO>();
        for (EstadoJob entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoJob toLevel0Entity(EstadoJobDTO dto, EstadoJob entidad) {
        if (null == entidad) {
            entidad = new EstadoJob();
        }
        entidad.setId(dto.getId());
        entidad.setNombreEstadoJob(dto.getNombreEstadoJob());

        return entidad;
    }

    public static EstadoJob toLevel1Entity(EstadoJobDTO dto, EstadoJob entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoJob> toListLevel0Entity(List<EstadoJobDTO> listDto) {
        List<EstadoJob> listEntidad = new ArrayList<EstadoJob>();
        for (EstadoJobDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoJob> toListLevel1Entity(List<EstadoJobDTO> listDto) {
        List<EstadoJob> listEntidad = new ArrayList<EstadoJob>();
        for (EstadoJobDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}