package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.ParentescoPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.entidades.personas.ParentescoPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.TipoParentescoPersona;


/**
 * @author robert.bautista - 12/06/2014
 */
public class ParentescoPersonaHelperExtend {

    // --- to DTO
    public static ParentescoPersonaDTO toLevel0DTO(ParentescoPersona entidad) {
        ParentescoPersonaDTO dto = new ParentescoPersonaDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static ParentescoPersonaDTO toLevel1DTO(ParentescoPersona entidad) {
        ParentescoPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoParentesco() != null)
            dto.setTipoParentesco(TipoParentescoPersonaHelper.toLevel0DTO(entidad.getTipoParentesco()));
        if (entidad.getPrincipal() != null)
            dto.setPrincipal(PersonaHelper.toLevel0DTO(entidad.getPrincipal()));
        if (entidad.getPariente() != null)
            dto.setPariente(PersonaHelper.toLevel0DTO(entidad.getPariente()));

        return dto;
    }

    public static List<ParentescoPersonaDTO> toListLevel0DTO(List<ParentescoPersona> listEntidad) {
        List<ParentescoPersonaDTO> listDto = new ArrayList<ParentescoPersonaDTO>();
        for (ParentescoPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ParentescoPersona toLevel0Entity(ParentescoPersonaDTO dto, ParentescoPersona entidad) {
        if (null == entidad) {
            entidad = new ParentescoPersona();
        }
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static ParentescoPersona toLevel1Entity(ParentescoPersonaDTO dto, ParentescoPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoParentesco() != null) {
            entidad.setTipoParentesco(new TipoParentescoPersona());
            entidad.getTipoParentesco().setId(dto.getTipoParentesco().getId());
        }
        if (dto.getPrincipal() != null) {
            entidad.setPrincipal(new Persona());
            entidad.getPrincipal().setId(dto.getPrincipal().getId());
        }
        if (dto.getPariente() != null) {
            entidad.setPariente(new Persona());
            entidad.getPariente().setId(dto.getPariente().getId());
        }

        return entidad;
    }

    public static List<ParentescoPersona> toListLevel0Entity(List<ParentescoPersonaDTO> listDto) {
        List<ParentescoPersona> listEntidad = new ArrayList<ParentescoPersona>();
        for (ParentescoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ParentescoPersona> toListLevel1Entity(List<ParentescoPersonaDTO> listDto) {
        List<ParentescoPersona> listEntidad = new ArrayList<ParentescoPersona>();
        for (ParentescoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }

    // --- fin to Entidad

    public static List<ParentescoPersonaDTO> toListLevel1DTO(List<ParentescoPersona> listEntidad) {
        List<ParentescoPersonaDTO> listDto = new ArrayList<ParentescoPersonaDTO>(listEntidad.size());
        for (ParentescoPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }

        for (ParentescoPersona parentescoPersona : listEntidad) {
            for (ParentescoPersonaDTO parentescoPersonaDTO : listDto) {
                if (parentescoPersonaDTO.getId().equals(parentescoPersona.getId())) {
                    PersonaDTO parienteDTO = PersonaHelperExtend.toLevel1DTO(parentescoPersona.getPariente());
                    parentescoPersonaDTO.setPariente(parienteDTO);
                    break;
                }

            }

        }

        return listDto;
    }

}
