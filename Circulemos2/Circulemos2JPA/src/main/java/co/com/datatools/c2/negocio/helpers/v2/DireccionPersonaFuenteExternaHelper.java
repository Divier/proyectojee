package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DireccionPersonaFuenteExternaDTO;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.personas.DireccionPersonaFuenteExterna;
import co.com.datatools.c2.entidades.personas.PersonaFuenteExterna;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Nov 19 11:28:33 COT 2014
 */
public class DireccionPersonaFuenteExternaHelper {
    // --- to DTO
    public static DireccionPersonaFuenteExternaDTO toLevel0DTO(DireccionPersonaFuenteExterna entidad) {
        DireccionPersonaFuenteExternaDTO dto = new DireccionPersonaFuenteExternaDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static DireccionPersonaFuenteExternaDTO toLevel1DTO(DireccionPersonaFuenteExterna entidad) {
        DireccionPersonaFuenteExternaDTO dto = toLevel0DTO(entidad);
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));
        if (entidad.getPersonaFuenteExterna() != null)
            dto.setPersonaFuenteExterna(PersonaFuenteExternaHelper.toLevel0DTO(entidad.getPersonaFuenteExterna()));

        return dto;
    }

    public static List<DireccionPersonaFuenteExternaDTO> toListLevel0DTO(List<DireccionPersonaFuenteExterna> listEntidad) {
        List<DireccionPersonaFuenteExternaDTO> listDto = new ArrayList<DireccionPersonaFuenteExternaDTO>();
        for (DireccionPersonaFuenteExterna entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DireccionPersonaFuenteExternaDTO> toListLevel1DTO(List<DireccionPersonaFuenteExterna> listEntidad) {
        List<DireccionPersonaFuenteExternaDTO> listDto = new ArrayList<DireccionPersonaFuenteExternaDTO>();
        for (DireccionPersonaFuenteExterna entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DireccionPersonaFuenteExterna toLevel0Entity(DireccionPersonaFuenteExternaDTO dto,
            DireccionPersonaFuenteExterna entidad) {
        if (null == entidad) {
            entidad = new DireccionPersonaFuenteExterna();
        }
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static DireccionPersonaFuenteExterna toLevel1Entity(DireccionPersonaFuenteExternaDTO dto,
            DireccionPersonaFuenteExterna entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }
        if (dto.getPersonaFuenteExterna() != null) {
            entidad.setPersonaFuenteExterna(new PersonaFuenteExterna());
            entidad.getPersonaFuenteExterna().setId(dto.getPersonaFuenteExterna().getId());
        }

        return entidad;
    }

    public static List<DireccionPersonaFuenteExterna> toListLevel0Entity(List<DireccionPersonaFuenteExternaDTO> listDto) {
        List<DireccionPersonaFuenteExterna> listEntidad = new ArrayList<DireccionPersonaFuenteExterna>();
        for (DireccionPersonaFuenteExternaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DireccionPersonaFuenteExterna> toListLevel1Entity(List<DireccionPersonaFuenteExternaDTO> listDto) {
        List<DireccionPersonaFuenteExterna> listEntidad = new ArrayList<DireccionPersonaFuenteExterna>();
        for (DireccionPersonaFuenteExternaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
