package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;

public class DireccionPersonaHelperExtend extends DireccionPersonaHelper {

    public static DireccionPersonaDTO toLevel2DTO(DireccionPersona entidad) {
        DireccionPersonaDTO dto = toLevel1DTO(entidad);
        if (entidad.getDireccion() != null) {
            dto.setDireccion(DireccionHelper.toLevel1DTO(entidad.getDireccion()));
        }
        return dto;
    }

    public static List<DireccionPersonaDTO> toListLevel2DTO(List<DireccionPersona> listEntidad) {
        List<DireccionPersonaDTO> listDto = new ArrayList<DireccionPersonaDTO>();
        for (DireccionPersona entidad : listEntidad) {
            listDto.add(toLevel2DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DireccionPersona toLevel2Entity(DireccionPersonaDTO dto, DireccionPersona entidad) {
        entidad = toLevel1Entity(dto, entidad);
        if (dto.getDireccion() != null) {
            entidad.setDireccion(DireccionHelper.toLevel1Entity(dto.getDireccion(), null));
        }
        return entidad;
    }

    public static List<DireccionPersona> toListLevel2Entity(List<DireccionPersonaDTO> listDto) {
        List<DireccionPersona> listEntidad = new ArrayList<DireccionPersona>();
        for (DireccionPersonaDTO dto : listDto) {
            listEntidad.add(toLevel2Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
