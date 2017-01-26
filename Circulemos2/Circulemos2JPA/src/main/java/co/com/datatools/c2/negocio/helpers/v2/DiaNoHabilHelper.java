package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DiaNoHabilDTO;
import co.com.datatools.c2.entidades.DiaNoHabil;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed May 28 16:59:37 COT 2014
 */
public class DiaNoHabilHelper {
    // --- to DTO
    public static DiaNoHabilDTO toLevel0DTO(DiaNoHabil entidad) {
        DiaNoHabilDTO dto = new DiaNoHabilDTO();
        dto.setId(entidad.getId());
        dto.setFecha(entidad.getFecha());
        dto.setObservacion(entidad.getObservacion());

        return dto;
    }

    public static DiaNoHabilDTO toLevel1DTO(DiaNoHabil entidad) {
        DiaNoHabilDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<DiaNoHabilDTO> toListLevel0DTO(List<DiaNoHabil> listEntidad) {
        List<DiaNoHabilDTO> listDto = new ArrayList<DiaNoHabilDTO>();
        for (DiaNoHabil entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DiaNoHabilDTO> toListLevel1DTO(List<DiaNoHabil> listEntidad) {
        List<DiaNoHabilDTO> listDto = new ArrayList<DiaNoHabilDTO>();
        for (DiaNoHabil entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DiaNoHabil toLevel0Entity(DiaNoHabilDTO dto, DiaNoHabil entidad) {
        if (null == entidad) {
            entidad = new DiaNoHabil();
        }
        entidad.setId(dto.getId());
        entidad.setFecha(dto.getFecha());
        entidad.setObservacion(dto.getObservacion());

        return entidad;
    }

    public static DiaNoHabil toLevel1Entity(DiaNoHabilDTO dto, DiaNoHabil entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<DiaNoHabil> toListLevel0Entity(List<DiaNoHabilDTO> listDto) {
        List<DiaNoHabil> listEntidad = new ArrayList<DiaNoHabil>();
        for (DiaNoHabilDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DiaNoHabil> toListLevel1Entity(List<DiaNoHabilDTO> listDto) {
        List<DiaNoHabil> listEntidad = new ArrayList<DiaNoHabil>();
        for (DiaNoHabilDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
