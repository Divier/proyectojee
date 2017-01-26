package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.GradoAlcoholemiaDTO;
import co.com.datatools.c2.entidades.GradoAlcoholemia;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 12:04:41 COT 2015
 */
public class GradoAlcoholemiaHelper {
    // --- to DTO
    public static GradoAlcoholemiaDTO toLevel0DTO(GradoAlcoholemia entidad) {
        GradoAlcoholemiaDTO dto = new GradoAlcoholemiaDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setValor(entidad.getValor());
        return dto;
    }

    public static GradoAlcoholemiaDTO toLevel1DTO(GradoAlcoholemia entidad) {
        GradoAlcoholemiaDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<GradoAlcoholemiaDTO> toListLevel0DTO(List<GradoAlcoholemia> listEntidad) {
        List<GradoAlcoholemiaDTO> listDto = new ArrayList<GradoAlcoholemiaDTO>();
        for (GradoAlcoholemia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<GradoAlcoholemiaDTO> toListLevel1DTO(List<GradoAlcoholemia> listEntidad) {
        List<GradoAlcoholemiaDTO> listDto = new ArrayList<GradoAlcoholemiaDTO>();
        for (GradoAlcoholemia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static GradoAlcoholemia toLevel0Entity(GradoAlcoholemiaDTO dto, GradoAlcoholemia entidad) {
        if (null == entidad) {
            entidad = new GradoAlcoholemia();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setValor(dto.getValor());
        return entidad;
    }

    public static GradoAlcoholemia toLevel1Entity(GradoAlcoholemiaDTO dto, GradoAlcoholemia entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<GradoAlcoholemia> toListLevel0Entity(List<GradoAlcoholemiaDTO> listDto) {
        List<GradoAlcoholemia> listEntidad = new ArrayList<GradoAlcoholemia>();
        for (GradoAlcoholemiaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<GradoAlcoholemia> toListLevel1Entity(List<GradoAlcoholemiaDTO> listDto) {
        List<GradoAlcoholemia> listEntidad = new ArrayList<GradoAlcoholemia>();
        for (GradoAlcoholemiaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
