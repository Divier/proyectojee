package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 06 18:27:47 COT 2015
 */
public class TipoIdentificacionPersonaHelper {
    // --- to DTO
    public static TipoIdentificacionPersonaDTO toLevel0DTO(TipoIdentificacionPersona entidad) {
        TipoIdentificacionPersonaDTO dto = new TipoIdentificacionPersonaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoIdentificacionPersonaDTO toLevel1DTO(TipoIdentificacionPersona entidad) {
        TipoIdentificacionPersonaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoIdentificacionPersonaDTO> toListLevel0DTO(List<TipoIdentificacionPersona> listEntidad) {
        List<TipoIdentificacionPersonaDTO> listDto = new ArrayList<TipoIdentificacionPersonaDTO>();
        for (TipoIdentificacionPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoIdentificacionPersonaDTO> toListLevel1DTO(List<TipoIdentificacionPersona> listEntidad) {
        List<TipoIdentificacionPersonaDTO> listDto = new ArrayList<TipoIdentificacionPersonaDTO>();
        for (TipoIdentificacionPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoIdentificacionPersona toLevel0Entity(TipoIdentificacionPersonaDTO dto,
            TipoIdentificacionPersona entidad) {
        if (null == entidad) {
            entidad = new TipoIdentificacionPersona();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoIdentificacionPersona toLevel1Entity(TipoIdentificacionPersonaDTO dto,
            TipoIdentificacionPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoIdentificacionPersona> toListLevel0Entity(List<TipoIdentificacionPersonaDTO> listDto) {
        List<TipoIdentificacionPersona> listEntidad = new ArrayList<TipoIdentificacionPersona>();
        for (TipoIdentificacionPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoIdentificacionPersona> toListLevel1Entity(List<TipoIdentificacionPersonaDTO> listDto) {
        List<TipoIdentificacionPersona> listEntidad = new ArrayList<TipoIdentificacionPersona>();
        for (TipoIdentificacionPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
