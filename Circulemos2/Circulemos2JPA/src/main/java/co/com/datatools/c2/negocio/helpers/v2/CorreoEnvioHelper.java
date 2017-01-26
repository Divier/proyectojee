package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.CorreoEnvioDTO;
import co.com.datatools.c2.entidades.ConfiguracionEmail;
import co.com.datatools.c2.entidades.CorreoEnvio;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Jul 14 16:18:51 COT 2014
 */
public class CorreoEnvioHelper {
    // --- to DTO
    public static CorreoEnvioDTO toLevel0DTO(CorreoEnvio entidad) {
        CorreoEnvioDTO dto = new CorreoEnvioDTO();
        dto.setId(entidad.getId());
        dto.setEmail(entidad.getEmail());

        return dto;
    }

    public static CorreoEnvioDTO toLevel1DTO(CorreoEnvio entidad) {
        CorreoEnvioDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getConfiguracionEmail() != null)
            dto.setConfiguracionEmail(ConfiguracionEmailHelper.toLevel0DTO(entidad.getConfiguracionEmail()));

        return dto;
    }

    public static List<CorreoEnvioDTO> toListLevel0DTO(List<CorreoEnvio> listEntidad) {
        List<CorreoEnvioDTO> listDto = new ArrayList<CorreoEnvioDTO>();
        for (CorreoEnvio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CorreoEnvioDTO> toListLevel1DTO(List<CorreoEnvio> listEntidad) {
        List<CorreoEnvioDTO> listDto = new ArrayList<CorreoEnvioDTO>();
        for (CorreoEnvio entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CorreoEnvio toLevel0Entity(CorreoEnvioDTO dto, CorreoEnvio entidad) {
        if (null == entidad) {
            entidad = new CorreoEnvio();
        }
        entidad.setId(dto.getId());
        entidad.setEmail(dto.getEmail());

        return entidad;
    }

    public static CorreoEnvio toLevel1Entity(CorreoEnvioDTO dto, CorreoEnvio entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getConfiguracionEmail() != null) {
            entidad.setConfiguracionEmail(new ConfiguracionEmail());
            entidad.getConfiguracionEmail().setId(dto.getConfiguracionEmail().getId());
        }

        return entidad;
    }

    public static List<CorreoEnvio> toListLevel0Entity(List<CorreoEnvioDTO> listDto) {
        List<CorreoEnvio> listEntidad = new ArrayList<CorreoEnvio>();
        for (CorreoEnvioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CorreoEnvio> toListLevel1Entity(List<CorreoEnvioDTO> listDto) {
        List<CorreoEnvio> listEntidad = new ArrayList<CorreoEnvio>();
        for (CorreoEnvioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
