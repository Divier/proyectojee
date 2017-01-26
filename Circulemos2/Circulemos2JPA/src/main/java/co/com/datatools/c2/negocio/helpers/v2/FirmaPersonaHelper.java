package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.FirmaPersonaDTO;
import co.com.datatools.c2.entidades.FirmaPersona;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class FirmaPersonaHelper {
    // --- to DTO
    public static FirmaPersonaDTO toLevel0DTO(FirmaPersona entidad) {
        FirmaPersonaDTO dto = new FirmaPersonaDTO();
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setId(entidad.getId());
        dto.setNumeroImagen(entidad.getNumeroImagen());

        return dto;
    }

    public static FirmaPersonaDTO toLevel1DTO(FirmaPersona entidad) {
        FirmaPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));

        return dto;
    }

    public static List<FirmaPersonaDTO> toListLevel0DTO(List<FirmaPersona> listEntidad) {
        List<FirmaPersonaDTO> listDto = new ArrayList<FirmaPersonaDTO>();
        for (FirmaPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<FirmaPersonaDTO> toListLevel1DTO(List<FirmaPersona> listEntidad) {
        List<FirmaPersonaDTO> listDto = new ArrayList<FirmaPersonaDTO>();
        for (FirmaPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to FirmaPersona
    public static FirmaPersona toLevel0Entity(FirmaPersonaDTO dto, FirmaPersona entidad) {
        if (null == entidad) {
            entidad = new FirmaPersona();
        }
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setId(dto.getId());
        entidad.setNumeroImagen(dto.getNumeroImagen());

        return entidad;
    }

    public static FirmaPersona toLevel1Entity(FirmaPersonaDTO dto, FirmaPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPersona() != null)
            entidad.setPersona(PersonaHelper.toLevel1Entity(dto.getPersona(), null));

        return entidad;
    }

    public static List<FirmaPersona> toListLevel0Entity(List<FirmaPersonaDTO> listDto) {
        List<FirmaPersona> listEntidad = new ArrayList<FirmaPersona>();
        for (FirmaPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<FirmaPersona> toListLevel1Entity(List<FirmaPersonaDTO> listDto) {
        List<FirmaPersona> listEntidad = new ArrayList<FirmaPersona>();
        for (FirmaPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
