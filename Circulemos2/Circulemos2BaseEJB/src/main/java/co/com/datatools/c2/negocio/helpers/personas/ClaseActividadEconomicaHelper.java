package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.ClaseActividadEconomicaDTO;
import co.com.datatools.c2.entidades.personas.ClaseActividadEconomica;
import co.com.datatools.c2.entidades.personas.GrupoActividadEconomica;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:43 COT 2014
 */
public class ClaseActividadEconomicaHelper {
    // --- to DTO
    public static ClaseActividadEconomicaDTO toLevel0DTO(ClaseActividadEconomica entidad) {
        ClaseActividadEconomicaDTO dto = new ClaseActividadEconomicaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static ClaseActividadEconomicaDTO toLevel1DTO(ClaseActividadEconomica entidad) {
        ClaseActividadEconomicaDTO dto = toLevel0DTO(entidad);
        if (entidad.getGrupoActividadEconomica() != null)
            dto.setGrupoActividadEconomica(GrupoActividadEconomicaHelper.toLevel0DTO(entidad
                    .getGrupoActividadEconomica()));

        return dto;
    }

    public static List<ClaseActividadEconomicaDTO> toListLevel0DTO(List<ClaseActividadEconomica> listEntidad) {
        List<ClaseActividadEconomicaDTO> listDto = new ArrayList<ClaseActividadEconomicaDTO>();
        for (ClaseActividadEconomica entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ClaseActividadEconomicaDTO> toListLevel1DTO(List<ClaseActividadEconomica> listEntidad) {
        List<ClaseActividadEconomicaDTO> listDto = new ArrayList<ClaseActividadEconomicaDTO>();
        for (ClaseActividadEconomica entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ClaseActividadEconomica toLevel0Entity(ClaseActividadEconomicaDTO dto, ClaseActividadEconomica entidad) {
        if (null == entidad) {
            entidad = new ClaseActividadEconomica();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static ClaseActividadEconomica toLevel1Entity(ClaseActividadEconomicaDTO dto, ClaseActividadEconomica entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getGrupoActividadEconomica() != null) {
            entidad.setGrupoActividadEconomica(new GrupoActividadEconomica());
            entidad.getGrupoActividadEconomica().setId(dto.getGrupoActividadEconomica().getId());
        }

        return entidad;
    }

    public static List<ClaseActividadEconomica> toListLevel0Entity(List<ClaseActividadEconomicaDTO> listDto) {
        List<ClaseActividadEconomica> listEntidad = new ArrayList<ClaseActividadEconomica>();
        for (ClaseActividadEconomicaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ClaseActividadEconomica> toListLevel1Entity(List<ClaseActividadEconomicaDTO> listDto) {
        List<ClaseActividadEconomica> listEntidad = new ArrayList<ClaseActividadEconomica>();
        for (ClaseActividadEconomicaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
