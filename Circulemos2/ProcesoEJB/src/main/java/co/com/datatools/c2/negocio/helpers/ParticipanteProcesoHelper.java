package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.entidades.ParticipanteProceso;
import co.com.datatools.c2.entidades.Proceso;
import co.com.datatools.c2.entidades.TipoParticipante;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 11:57:28 COT 2016
 */
public class ParticipanteProcesoHelper {
    // --- to DTO
    public static ParticipanteProcesoDTO toLevel0DTO(ParticipanteProceso entidad) {
        ParticipanteProcesoDTO dto = new ParticipanteProcesoDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static ParticipanteProcesoDTO toLevel1DTO(ParticipanteProceso entidad) {
        ParticipanteProcesoDTO dto = toLevel0DTO(entidad);
        if (entidad.getProceso() != null)
            dto.setProceso(ProcesoHelper.toLevel0DTO(entidad.getProceso()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getTipoParticipante() != null)
            dto.setTipoParticipante(TipoParticipanteHelper.toLevel0DTO(entidad.getTipoParticipante()));

        return dto;
    }

    public static List<ParticipanteProcesoDTO> toListLevel0DTO(List<ParticipanteProceso> listEntidad) {
        List<ParticipanteProcesoDTO> listDto = new ArrayList<ParticipanteProcesoDTO>();
        for (ParticipanteProceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ParticipanteProcesoDTO> toListLevel1DTO(List<ParticipanteProceso> listEntidad) {
        List<ParticipanteProcesoDTO> listDto = new ArrayList<ParticipanteProcesoDTO>();
        for (ParticipanteProceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ParticipanteProceso toLevel0Entity(ParticipanteProcesoDTO dto, ParticipanteProceso entidad) {
        if (null == entidad) {
            entidad = new ParticipanteProceso();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static ParticipanteProceso toLevel1Entity(ParticipanteProcesoDTO dto, ParticipanteProceso entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getProceso() != null) {
            entidad.setProceso(new Proceso());
            entidad.getProceso().setId(dto.getProceso().getId());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getTipoParticipante() != null) {
            entidad.setTipoParticipante(new TipoParticipante());
            entidad.getTipoParticipante().setId(dto.getTipoParticipante().getId());
        }

        return entidad;
    }

    public static List<ParticipanteProceso> toListLevel0Entity(List<ParticipanteProcesoDTO> listDto) {
        List<ParticipanteProceso> listEntidad = new ArrayList<ParticipanteProceso>();
        for (ParticipanteProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ParticipanteProceso> toListLevel1Entity(List<ParticipanteProcesoDTO> listDto) {
        List<ParticipanteProceso> listEntidad = new ArrayList<ParticipanteProceso>();
        for (ParticipanteProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
