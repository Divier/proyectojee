package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.OrdenComparendoNacionalDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.OrdenComparendoNacional;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class OrdenComparendoNacionalHelper {
    // --- to DTO
    public static OrdenComparendoNacionalDTO toLevel0DTO(OrdenComparendoNacional entidad) {
        OrdenComparendoNacionalDTO dto = new OrdenComparendoNacionalDTO();
        dto.setCicomparendo(entidad.getCicomparendo());
        dto.setNumeroComparendo(entidad.getNumeroComparendo());

        return dto;
    }

    public static OrdenComparendoNacionalDTO toLevel1DTO(OrdenComparendoNacional entidad) {
        OrdenComparendoNacionalDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<OrdenComparendoNacionalDTO> toListLevel0DTO(List<OrdenComparendoNacional> listEntidad) {
        List<OrdenComparendoNacionalDTO> listDto = new ArrayList<OrdenComparendoNacionalDTO>();
        for (OrdenComparendoNacional entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<OrdenComparendoNacionalDTO> toListLevel1DTO(List<OrdenComparendoNacional> listEntidad) {
        List<OrdenComparendoNacionalDTO> listDto = new ArrayList<OrdenComparendoNacionalDTO>();
        for (OrdenComparendoNacional entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static OrdenComparendoNacional toLevel0Entity(OrdenComparendoNacionalDTO dto, OrdenComparendoNacional entidad) {
        if (null == entidad) {
            entidad = new OrdenComparendoNacional();
        }
        entidad.setCicomparendo(dto.getCicomparendo());
        entidad.setNumeroComparendo(dto.getNumeroComparendo());

        return entidad;
    }

    public static OrdenComparendoNacional toLevel1Entity(OrdenComparendoNacionalDTO dto, OrdenComparendoNacional entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getCicomparendo());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<OrdenComparendoNacional> toListLevel0Entity(List<OrdenComparendoNacionalDTO> listDto) {
        List<OrdenComparendoNacional> listEntidad = new ArrayList<OrdenComparendoNacional>();
        for (OrdenComparendoNacionalDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<OrdenComparendoNacional> toListLevel1Entity(List<OrdenComparendoNacionalDTO> listDto) {
        List<OrdenComparendoNacional> listEntidad = new ArrayList<OrdenComparendoNacional>();
        for (OrdenComparendoNacionalDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
