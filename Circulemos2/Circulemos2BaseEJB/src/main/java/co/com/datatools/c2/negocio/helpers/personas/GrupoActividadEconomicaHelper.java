package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.GrupoActividadEconomicaDTO;
import co.com.datatools.c2.entidades.personas.DivisionActividadEconomica;
import co.com.datatools.c2.entidades.personas.GrupoActividadEconomica;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class GrupoActividadEconomicaHelper {
    // --- to DTO
    public static GrupoActividadEconomicaDTO toLevel0DTO(GrupoActividadEconomica entidad) {
        GrupoActividadEconomicaDTO dto = new GrupoActividadEconomicaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static GrupoActividadEconomicaDTO toLevel1DTO(GrupoActividadEconomica entidad) {
        GrupoActividadEconomicaDTO dto = toLevel0DTO(entidad);
        if (entidad.getDivisionActividadEconomica() != null)
            dto.setDivisionActividadEconomica(DivisionActividadEconomicaHelper.toLevel0DTO(entidad
                    .getDivisionActividadEconomica()));

        return dto;
    }

    public static List<GrupoActividadEconomicaDTO> toListLevel0DTO(List<GrupoActividadEconomica> listEntidad) {
        List<GrupoActividadEconomicaDTO> listDto = new ArrayList<GrupoActividadEconomicaDTO>();
        for (GrupoActividadEconomica entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<GrupoActividadEconomicaDTO> toListLevel1DTO(List<GrupoActividadEconomica> listEntidad) {
        List<GrupoActividadEconomicaDTO> listDto = new ArrayList<GrupoActividadEconomicaDTO>();
        for (GrupoActividadEconomica entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static GrupoActividadEconomica toLevel0Entity(GrupoActividadEconomicaDTO dto, GrupoActividadEconomica entidad) {
        if (null == entidad) {
            entidad = new GrupoActividadEconomica();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static GrupoActividadEconomica toLevel1Entity(GrupoActividadEconomicaDTO dto, GrupoActividadEconomica entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDivisionActividadEconomica() != null) {
            entidad.setDivisionActividadEconomica(new DivisionActividadEconomica());
            entidad.getDivisionActividadEconomica().setId(dto.getDivisionActividadEconomica().getId());
        }

        return entidad;
    }

    public static List<GrupoActividadEconomica> toListLevel0Entity(List<GrupoActividadEconomicaDTO> listDto) {
        List<GrupoActividadEconomica> listEntidad = new ArrayList<GrupoActividadEconomica>();
        for (GrupoActividadEconomicaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<GrupoActividadEconomica> toListLevel1Entity(List<GrupoActividadEconomicaDTO> listDto) {
        List<GrupoActividadEconomica> listEntidad = new ArrayList<GrupoActividadEconomica>();
        for (GrupoActividadEconomicaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
