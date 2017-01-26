package co.com.datatools.datatimer.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.datatimer.c2.entidades.EstadoTrigger;
import co.com.datatools.datatimer.c2.entidades.Job;
import co.com.datatools.datatimer.c2.entidades.TriggerJob;
import co.com.datatools.datatimer.dto.TriggerDTO;

/**
 * No volver a autogenerar
 * 
 * @author Generated
 * @version 3.0 - Thu Apr 28 17:57:23 COT 2016
 */
public class TriggerJobHelper {
    // --- to DTO
    public static TriggerDTO toLevel0DTO(TriggerJob entidad) {
        TriggerDTO dto = new TriggerDTO();
        dto.setIdTrigger(entidad.getId());
        dto.setNombre(entidad.getNombreTrigger());
        dto.setGrupo(entidad.getGrupoTrigger());
        dto.setEsAutomatico(entidad.getEsAutomatico());
        dto.setExpresionCron(entidad.getExpresionCron());

        return dto;
    }

    public static TriggerDTO toLevel1DTO(TriggerJob entidad) {
        TriggerDTO dto = toLevel0DTO(entidad);
        if (entidad.getJob() != null)
            dto.setJobDTO(JobHelper.toLevel0DTO(entidad.getJob()));
        if (entidad.getEstadoTrigger() != null)
            dto.setEstadoTriggerDTO(EstadoTriggerHelper.toLevel0DTO(entidad.getEstadoTrigger()));

        return dto;
    }

    public static List<TriggerDTO> toListLevel0DTO(List<TriggerJob> listEntidad) {
        List<TriggerDTO> listDto = new ArrayList<TriggerDTO>();
        for (TriggerJob entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TriggerDTO> toListLevel1DTO(List<TriggerJob> listEntidad) {
        List<TriggerDTO> listDto = new ArrayList<TriggerDTO>();
        for (TriggerJob entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TriggerJob toLevel0Entity(TriggerDTO dto, TriggerJob entidad) {
        if (null == entidad) {
            entidad = new TriggerJob();
        }
        entidad.setId(dto.getIdTrigger());
        entidad.setNombreTrigger(dto.getNombre());
        entidad.setGrupoTrigger(dto.getGrupo());
        entidad.setEsAutomatico(dto.getEsAutomatico());
        entidad.setExpresionCron(dto.getExpresionCron());

        return entidad;
    }

    public static TriggerJob toLevel1Entity(TriggerDTO dto, TriggerJob entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getJobDTO() != null) {
            entidad.setJob(new Job());
            entidad.getJob().setId(dto.getJobDTO().getIdJob());
        }
        if (dto.getEstadoTriggerDTO() != null) {
            entidad.setEstadoTrigger(new EstadoTrigger());
            entidad.getEstadoTrigger().setId(dto.getEstadoTriggerDTO().getId());
        }

        return entidad;
    }

    public static List<TriggerJob> toListLevel0Entity(List<TriggerDTO> listDto) {
        List<TriggerJob> listEntidad = new ArrayList<TriggerJob>();
        for (TriggerDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TriggerJob> toListLevel1Entity(List<TriggerDTO> listDto) {
        List<TriggerJob> listEntidad = new ArrayList<TriggerJob>();
        for (TriggerDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}