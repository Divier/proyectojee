package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.TipoParentescoPersonaDTO;
import co.com.datatools.c2.entidades.personas.TipoParentescoPersona;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoParentescoPersonaHelper {
    // --- to DTO
    public static TipoParentescoPersonaDTO toLevel0DTO(TipoParentescoPersona entidad) {
        TipoParentescoPersonaDTO dto = new TipoParentescoPersonaDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoParentescoPersonaDTO toLevel1DTO(TipoParentescoPersona entidad) {
        TipoParentescoPersonaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoParentescoPersonaDTO> toListLevel0DTO(List<TipoParentescoPersona> listEntidad) {
        List<TipoParentescoPersonaDTO> listDto = new ArrayList<TipoParentescoPersonaDTO>();
        for (TipoParentescoPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoParentescoPersonaDTO> toListLevel1DTO(List<TipoParentescoPersona> listEntidad) {
        List<TipoParentescoPersonaDTO> listDto = new ArrayList<TipoParentescoPersonaDTO>();
        for (TipoParentescoPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoParentescoPersona toLevel0Entity(TipoParentescoPersonaDTO dto, TipoParentescoPersona entidad) {
        if (null == entidad) {
            entidad = new TipoParentescoPersona();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoParentescoPersona toLevel1Entity(TipoParentescoPersonaDTO dto, TipoParentescoPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoParentescoPersona> toListLevel0Entity(List<TipoParentescoPersonaDTO> listDto) {
        List<TipoParentescoPersona> listEntidad = new ArrayList<TipoParentescoPersona>();
        for (TipoParentescoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoParentescoPersona> toListLevel1Entity(List<TipoParentescoPersonaDTO> listDto) {
        List<TipoParentescoPersona> listEntidad = new ArrayList<TipoParentescoPersona>();
        for (TipoParentescoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
