package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.DireccionOrganismoDTO;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.DireccionOrganismo;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 08 18:17:15 COT 2015
 */
public class DireccionOrganismoHelper {
    // --- to DTO
    public static DireccionOrganismoDTO toLevel0DTO(DireccionOrganismo entidad) {
        DireccionOrganismoDTO dto = new DireccionOrganismoDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static DireccionOrganismoDTO toLevel1DTO(DireccionOrganismo entidad) {
        DireccionOrganismoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));

        return dto;
    }

    public static List<DireccionOrganismoDTO> toListLevel0DTO(List<DireccionOrganismo> listEntidad) {
        List<DireccionOrganismoDTO> listDto = new ArrayList<DireccionOrganismoDTO>();
        for (DireccionOrganismo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DireccionOrganismoDTO> toListLevel1DTO(List<DireccionOrganismo> listEntidad) {
        List<DireccionOrganismoDTO> listDto = new ArrayList<DireccionOrganismoDTO>();
        for (DireccionOrganismo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DireccionOrganismo toLevel0Entity(DireccionOrganismoDTO dto, DireccionOrganismo entidad) {
        if (null == entidad) {
            entidad = new DireccionOrganismo();
        }
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static DireccionOrganismo toLevel1Entity(DireccionOrganismoDTO dto, DireccionOrganismo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }

        return entidad;
    }

    public static List<DireccionOrganismo> toListLevel0Entity(List<DireccionOrganismoDTO> listDto) {
        List<DireccionOrganismo> listEntidad = new ArrayList<DireccionOrganismo>();
        for (DireccionOrganismoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DireccionOrganismo> toListLevel1Entity(List<DireccionOrganismoDTO> listDto) {
        List<DireccionOrganismo> listEntidad = new ArrayList<DireccionOrganismo>();
        for (DireccionOrganismoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
