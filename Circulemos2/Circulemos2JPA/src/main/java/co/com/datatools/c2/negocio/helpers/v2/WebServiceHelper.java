package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.WebServiceDTO;
import co.com.datatools.c2.entidades.TipoWebService;
import co.com.datatools.c2.entidades.WebService;

/**
 * @author Generated
 * @version 3.0 - Thu Mar 31 10:03:50 COT 2016
 */
public class WebServiceHelper {
    // --- to DTO
    public static WebServiceDTO toLevel0DTO(WebService entidad) {
        WebServiceDTO dto = new WebServiceDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setUrlPrimaria(entidad.getUrlPrimaria());
        dto.setUrlSecundaria(entidad.getUrlSecundaria());

        return dto;
    }

    public static WebServiceDTO toLevel1DTO(WebService entidad) {
        WebServiceDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoWebService() != null)
            dto.setTipoWebService(TipoWebServiceHelper.toLevel0DTO(entidad.getTipoWebService()));

        return dto;
    }

    public static List<WebServiceDTO> toListLevel0DTO(List<WebService> listEntidad) {
        List<WebServiceDTO> listDto = new ArrayList<WebServiceDTO>();
        for (WebService entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<WebServiceDTO> toListLevel1DTO(List<WebService> listEntidad) {
        List<WebServiceDTO> listDto = new ArrayList<WebServiceDTO>();
        for (WebService entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static WebService toLevel0Entity(WebServiceDTO dto, WebService entidad) {
        if (null == entidad) {
            entidad = new WebService();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setUrlPrimaria(dto.getUrlPrimaria());
        entidad.setUrlSecundaria(dto.getUrlSecundaria());

        return entidad;
    }

    public static WebService toLevel1Entity(WebServiceDTO dto, WebService entidad) {
        entidad = toLevel0Entity(dto, entidad);

        if (dto.getTipoWebService() != null) {
            entidad.setTipoWebService(new TipoWebService());
            entidad.getTipoWebService().setId(dto.getTipoWebService().getId());
        }

        return entidad;
    }

    public static List<WebService> toListLevel0Entity(List<WebServiceDTO> listDto) {
        List<WebService> listEntidad = new ArrayList<WebService>();
        for (WebServiceDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<WebService> toListLevel1Entity(List<WebServiceDTO> listDto) {
        List<WebService> listEntidad = new ArrayList<WebService>();
        for (WebServiceDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
