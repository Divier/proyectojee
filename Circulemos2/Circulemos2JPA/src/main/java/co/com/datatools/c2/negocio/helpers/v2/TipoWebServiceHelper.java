package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoWebServiceDTO;
import co.com.datatools.c2.entidades.TipoWebService;

/**
 * @author Generated
 * @version 3.0 - Thu Mar 31 10:14:41 COT 2016
 */
public class TipoWebServiceHelper {
    // --- to DTO
    public static TipoWebServiceDTO toLevel0DTO(TipoWebService entidad) {
        TipoWebServiceDTO dto = new TipoWebServiceDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoWebServiceDTO toLevel1DTO(TipoWebService entidad) {
        TipoWebServiceDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoWebServiceDTO> toListLevel0DTO(List<TipoWebService> listEntidad) {
        List<TipoWebServiceDTO> listDto = new ArrayList<TipoWebServiceDTO>();
        for (TipoWebService entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoWebServiceDTO> toListLevel1DTO(List<TipoWebService> listEntidad) {
        List<TipoWebServiceDTO> listDto = new ArrayList<TipoWebServiceDTO>();
        for (TipoWebService entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoWebService toLevel0Entity(TipoWebServiceDTO dto, TipoWebService entidad) {
        if (null == entidad) {
            entidad = new TipoWebService();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoWebService toLevel1Entity(TipoWebServiceDTO dto, TipoWebService entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoWebService> toListLevel0Entity(List<TipoWebServiceDTO> listDto) {
        List<TipoWebService> listEntidad = new ArrayList<TipoWebService>();
        for (TipoWebServiceDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoWebService> toListLevel1Entity(List<TipoWebServiceDTO> listDto) {
        List<TipoWebService> listEntidad = new ArrayList<TipoWebService>();
        for (TipoWebServiceDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
