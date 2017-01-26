package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.entidades.RespuestaWebService;
import co.com.datatools.c2.entidades.TipoRespuestaWebService;
import co.com.datatools.c2.entidades.WebService;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 06 17:52:12 COT 2016
 */
public class RespuestaWebServiceHelper {
    // --- to DTO
    public static RespuestaWebServiceDTO toLevel0DTO(RespuestaWebService entidad) {
        RespuestaWebServiceDTO dto = new RespuestaWebServiceDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static RespuestaWebServiceDTO toLevel1DTO(RespuestaWebService entidad) {
        RespuestaWebServiceDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoRespuestaWebService() != null)
            dto.setTipoRespuestaWebService(TipoRespuestaWebServiceHelper.toLevel0DTO(entidad
                    .getTipoRespuestaWebService()));
        if (entidad.getWebService() != null)
            dto.setWebService(WebServiceHelper.toLevel0DTO(entidad.getWebService()));

        return dto;
    }

    public static List<RespuestaWebServiceDTO> toListLevel0DTO(List<RespuestaWebService> listEntidad) {
        List<RespuestaWebServiceDTO> listDto = new ArrayList<RespuestaWebServiceDTO>();
        for (RespuestaWebService entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RespuestaWebServiceDTO> toListLevel1DTO(List<RespuestaWebService> listEntidad) {
        List<RespuestaWebServiceDTO> listDto = new ArrayList<RespuestaWebServiceDTO>();
        for (RespuestaWebService entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RespuestaWebService toLevel0Entity(RespuestaWebServiceDTO dto, RespuestaWebService entidad) {
        if (null == entidad) {
            entidad = new RespuestaWebService();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static RespuestaWebService toLevel1Entity(RespuestaWebServiceDTO dto, RespuestaWebService entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoRespuestaWebService() != null) {
            entidad.setTipoRespuestaWebService(new TipoRespuestaWebService());
            entidad.getTipoRespuestaWebService().setId(dto.getTipoRespuestaWebService().getId());
        }
        if (dto.getWebService() != null) {
            entidad.setWebService(new WebService());
            entidad.getWebService().setId(dto.getWebService().getId());
        }

        return entidad;
    }

    public static List<RespuestaWebService> toListLevel0Entity(List<RespuestaWebServiceDTO> listDto) {
        List<RespuestaWebService> listEntidad = new ArrayList<RespuestaWebService>();
        for (RespuestaWebServiceDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RespuestaWebService> toListLevel1Entity(List<RespuestaWebServiceDTO> listDto) {
        List<RespuestaWebService> listEntidad = new ArrayList<RespuestaWebService>();
        for (RespuestaWebServiceDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
