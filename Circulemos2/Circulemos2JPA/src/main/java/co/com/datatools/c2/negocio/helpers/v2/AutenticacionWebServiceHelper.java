package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.AutenticacionWebServiceDTO;
import co.com.datatools.c2.entidades.AutenticacionWebService;
import co.com.datatools.c2.entidades.WebService;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;

/**
 * @author Generated
 * @version 3.0 - Thu Mar 31 10:04:49 COT 2016
 */
public class AutenticacionWebServiceHelper {
    // --- to DTO
    public static AutenticacionWebServiceDTO toLevel0DTO(AutenticacionWebService entidad) {
        AutenticacionWebServiceDTO dto = new AutenticacionWebServiceDTO();
        dto.setId(entidad.getId());
        dto.setClave(entidad.getClave());
        dto.setUsuario(entidad.getUsuario());
        dto.setParametro(entidad.getParametro());
        dto.setObservacion(entidad.getObservacion());
        return dto;
    }

    public static AutenticacionWebServiceDTO toLevel1DTO(AutenticacionWebService entidad) {
        AutenticacionWebServiceDTO dto = toLevel0DTO(entidad);
        if (entidad.getWebService() != null)
            dto.setWebService(WebServiceHelper.toLevel0DTO(entidad.getWebService()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));

        return dto;
    }

    public static List<AutenticacionWebServiceDTO> toListLevel0DTO(List<AutenticacionWebService> listEntidad) {
        List<AutenticacionWebServiceDTO> listDto = new ArrayList<AutenticacionWebServiceDTO>();
        for (AutenticacionWebService entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<AutenticacionWebServiceDTO> toListLevel1DTO(List<AutenticacionWebService> listEntidad) {
        List<AutenticacionWebServiceDTO> listDto = new ArrayList<AutenticacionWebServiceDTO>();
        for (AutenticacionWebService entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static AutenticacionWebService toLevel0Entity(AutenticacionWebServiceDTO dto,
            AutenticacionWebService entidad) {
        if (null == entidad) {
            entidad = new AutenticacionWebService();
        }
        entidad.setId(dto.getId());
        entidad.setClave(dto.getClave());
        entidad.setUsuario(dto.getUsuario());
        entidad.setParametro(dto.getParametro());
        entidad.setObservacion(dto.getObservacion());
        return entidad;
    }

    public static AutenticacionWebService toLevel1Entity(AutenticacionWebServiceDTO dto,
            AutenticacionWebService entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getWebService() != null) {
            entidad.setWebService(new WebService());
            entidad.getWebService().setId(dto.getWebService().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }

        return entidad;
    }

    public static List<AutenticacionWebService> toListLevel0Entity(List<AutenticacionWebServiceDTO> listDto) {
        List<AutenticacionWebService> listEntidad = new ArrayList<AutenticacionWebService>();
        for (AutenticacionWebServiceDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<AutenticacionWebService> toListLevel1Entity(List<AutenticacionWebServiceDTO> listDto) {
        List<AutenticacionWebService> listEntidad = new ArrayList<AutenticacionWebService>();
        for (AutenticacionWebServiceDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
