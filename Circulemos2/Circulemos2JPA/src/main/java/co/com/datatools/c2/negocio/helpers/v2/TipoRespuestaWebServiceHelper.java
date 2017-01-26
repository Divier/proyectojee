package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoRespuestaWebServiceDTO;
import co.com.datatools.c2.entidades.TipoRespuestaWebService;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 06 17:55:03 COT 2016
 */
public class TipoRespuestaWebServiceHelper {
    // --- to DTO
    public static TipoRespuestaWebServiceDTO toLevel0DTO(TipoRespuestaWebService entidad) {
        TipoRespuestaWebServiceDTO dto = new TipoRespuestaWebServiceDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoRespuestaWebServiceDTO toLevel1DTO(TipoRespuestaWebService entidad) {
        TipoRespuestaWebServiceDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoRespuestaWebServiceDTO> toListLevel0DTO(List<TipoRespuestaWebService> listEntidad) {
        List<TipoRespuestaWebServiceDTO> listDto = new ArrayList<TipoRespuestaWebServiceDTO>();
        for (TipoRespuestaWebService entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoRespuestaWebServiceDTO> toListLevel1DTO(List<TipoRespuestaWebService> listEntidad) {
        List<TipoRespuestaWebServiceDTO> listDto = new ArrayList<TipoRespuestaWebServiceDTO>();
        for (TipoRespuestaWebService entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoRespuestaWebService toLevel0Entity(TipoRespuestaWebServiceDTO dto, TipoRespuestaWebService entidad) {
        if (null == entidad) {
            entidad = new TipoRespuestaWebService();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoRespuestaWebService toLevel1Entity(TipoRespuestaWebServiceDTO dto, TipoRespuestaWebService entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoRespuestaWebService> toListLevel0Entity(List<TipoRespuestaWebServiceDTO> listDto) {
        List<TipoRespuestaWebService> listEntidad = new ArrayList<TipoRespuestaWebService>();
        for (TipoRespuestaWebServiceDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoRespuestaWebService> toListLevel1Entity(List<TipoRespuestaWebServiceDTO> listDto) {
        List<TipoRespuestaWebService> listEntidad = new ArrayList<TipoRespuestaWebService>();
        for (TipoRespuestaWebServiceDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
