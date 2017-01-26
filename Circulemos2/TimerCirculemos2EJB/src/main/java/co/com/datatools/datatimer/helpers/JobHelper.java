package co.com.datatools.datatimer.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.datatimer.c2.entidades.EstadoJob;
import co.com.datatools.datatimer.c2.entidades.Job;
import co.com.datatools.datatimer.c2.entidades.TriggerJob;
import co.com.datatools.datatimer.dto.JobDTO;

/**
 * No volver a autogenerar
 * 
 * @author Generated
 * @version 3.0 - Wed Apr 27 14:08:00 COT 2016
 */
public class JobHelper {
    // --- to DTO
    public static JobDTO toLevel0DTO(Job entidad) {
        JobDTO dto = new JobDTO();
        dto.setIdJob(entidad.getId());
        dto.setNombreJob(entidad.getNombre());
        dto.setGrupoJob(entidad.getGrupo());
        return dto;
    }

    public static JobDTO toLevel1DTO(Job entidad) {
        JobDTO dto = toLevel0DTO(entidad);
        if (entidad.getEstadoJob() != null) {
            dto.setEstadoJobDTO(EstadoJobHelper.toLevel0DTO(entidad.getEstadoJob()));
        }

        if (entidad.getTriggerJobList() != null) {
            for (TriggerJob triggerJob : entidad.getTriggerJobList()) {
                dto.addTrigger(TriggerJobHelper.toLevel1DTO(triggerJob));
            }
        }
        return dto;
    }

    public static List<JobDTO> toListLevel0DTO(List<Job> listEntidad) {
        List<JobDTO> listDto = new ArrayList<JobDTO>();
        for (Job entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<JobDTO> toListLevel1DTO(List<Job> listEntidad) {
        List<JobDTO> listDto = new ArrayList<JobDTO>();
        for (Job entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Job toLevel0Entity(JobDTO dto, Job entidad) {
        if (null == entidad) {
            entidad = new Job();
        }
        entidad.setId(dto.getIdJob());
        entidad.setNombre(dto.getNombreJob());
        entidad.setGrupo(dto.getGrupoJob());
        return entidad;
    }

    public static Job toLevel1Entity(JobDTO dto, Job entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getEstadoJobDTO() != null) {
            entidad.setEstadoJob(new EstadoJob());
            entidad.getEstadoJob().setId(dto.getEstadoJobDTO().getId());
        }

        return entidad;
    }

    public static List<Job> toListLevel0Entity(List<JobDTO> listDto) {
        List<Job> listEntidad = new ArrayList<Job>();
        for (JobDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Job> toListLevel1Entity(List<JobDTO> listDto) {
        List<Job> listEntidad = new ArrayList<Job>();
        for (JobDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}