package co.com.datatools.c2.negocio.helpers.patios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.patios.PatioDTO;
import co.com.datatools.c2.entidades.Patio;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class PatioHelper {
    // --- to DTO
    public static PatioDTO toLevel0DTO(Patio entidad) {
        PatioDTO dto = new PatioDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static PatioDTO toLevel1DTO(Patio entidad) {
        PatioDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));

        return dto;
    }

    public static List<PatioDTO> toListLevel0DTO(List<Patio> listEntidad) {
        List<PatioDTO> listDto = new ArrayList<PatioDTO>();
        for (Patio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PatioDTO> toListLevel1DTO(List<Patio> listEntidad) {
        List<PatioDTO> listDto = new ArrayList<PatioDTO>();
        for (Patio entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Patio toLevel0Entity(PatioDTO dto, Patio entidad) {
        if (null == entidad) {
            entidad = new Patio();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static Patio toLevel1Entity(PatioDTO dto, Patio entidad) {
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

    public static List<Patio> toListLevel0Entity(List<PatioDTO> listDto) {
        List<Patio> listEntidad = new ArrayList<Patio>();
        for (PatioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Patio> toListLevel1Entity(List<PatioDTO> listDto) {
        List<Patio> listEntidad = new ArrayList<Patio>();
        for (PatioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
