package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleFuenteConsultaDTO;
import co.com.datatools.c2.entidades.DetalleFuenteConsulta;
import co.com.datatools.c2.entidades.FuenteConsulta;
import co.com.datatools.c2.entidades.TipoFuenteConsulta;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 13 14:35:13 COT 2016
 */
public class DetalleFuenteConsultaHelper {
    // --- to DTO
    public static DetalleFuenteConsultaDTO toLevel0DTO(DetalleFuenteConsulta entidad) {
        DetalleFuenteConsultaDTO dto = new DetalleFuenteConsultaDTO();
        dto.setId(entidad.getId());
        dto.setPrioridad(entidad.getPrioridad());

        return dto;
    }

    public static DetalleFuenteConsultaDTO toLevel1DTO(DetalleFuenteConsulta entidad) {
        DetalleFuenteConsultaDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoFuenteConsulta() != null)
            dto.setTipoFuenteConsulta(TipoFuenteConsultaHelper.toLevel0DTO(entidad.getTipoFuenteConsulta()));
        if (entidad.getFuenteConsulta() != null)
            dto.setFuenteConsulta(FuenteConsultaHelper.toLevel0DTO(entidad.getFuenteConsulta()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<DetalleFuenteConsultaDTO> toListLevel0DTO(List<DetalleFuenteConsulta> listEntidad) {
        List<DetalleFuenteConsultaDTO> listDto = new ArrayList<DetalleFuenteConsultaDTO>();
        for (DetalleFuenteConsulta entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleFuenteConsultaDTO> toListLevel1DTO(List<DetalleFuenteConsulta> listEntidad) {
        List<DetalleFuenteConsultaDTO> listDto = new ArrayList<DetalleFuenteConsultaDTO>();
        for (DetalleFuenteConsulta entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleFuenteConsulta toLevel0Entity(DetalleFuenteConsultaDTO dto, DetalleFuenteConsulta entidad) {
        if (null == entidad) {
            entidad = new DetalleFuenteConsulta();
        }
        entidad.setId(dto.getId());
        entidad.setPrioridad(dto.getPrioridad());

        return entidad;
    }

    public static DetalleFuenteConsulta toLevel1Entity(DetalleFuenteConsultaDTO dto, DetalleFuenteConsulta entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoFuenteConsulta() != null) {
            entidad.setTipoFuenteConsulta(new TipoFuenteConsulta());
            entidad.getTipoFuenteConsulta().setId(dto.getTipoFuenteConsulta().getId());
        }
        if (dto.getFuenteConsulta() != null) {
            entidad.setFuenteConsulta(new FuenteConsulta());
            entidad.getFuenteConsulta().setId(dto.getFuenteConsulta().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<DetalleFuenteConsulta> toListLevel0Entity(List<DetalleFuenteConsultaDTO> listDto) {
        List<DetalleFuenteConsulta> listEntidad = new ArrayList<DetalleFuenteConsulta>();
        for (DetalleFuenteConsultaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleFuenteConsulta> toListLevel1Entity(List<DetalleFuenteConsultaDTO> listDto) {
        List<DetalleFuenteConsulta> listEntidad = new ArrayList<DetalleFuenteConsulta>();
        for (DetalleFuenteConsultaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
