package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ws.TipoMapeoDTO;
import co.com.datatools.c2.entidades.ws.TipoMapeo;
import co.com.datatools.c2.entidades.ws.WebServiceExpuesto;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 17 15:13:15 COT 2015
 */
public class TipoMapeoHelper {
    // --- to DTO
    public static TipoMapeoDTO toLevel0DTO(TipoMapeo entidad) {
        TipoMapeoDTO dto = new TipoMapeoDTO();

        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoMapeoDTO toLevel1DTO(TipoMapeo entidad) {
        TipoMapeoDTO dto = toLevel0DTO(entidad);
        if (entidad.getWebServiceExpuesto() != null)
            dto.setWebServiceExpuesto(WebServiceExpuestoHelper.toLevel0DTO(entidad.getWebServiceExpuesto()));

        return dto;
    }

    public static List<TipoMapeoDTO> toListLevel0DTO(List<TipoMapeo> listEntidad) {
        List<TipoMapeoDTO> listDto = new ArrayList<TipoMapeoDTO>();
        for (TipoMapeo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoMapeoDTO> toListLevel1DTO(List<TipoMapeo> listEntidad) {
        List<TipoMapeoDTO> listDto = new ArrayList<TipoMapeoDTO>();
        for (TipoMapeo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoMapeo toLevel0Entity(TipoMapeoDTO dto, TipoMapeo entidad) {
        if (null == entidad) {
            entidad = new TipoMapeo();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoMapeo toLevel1Entity(TipoMapeoDTO dto, TipoMapeo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getWebServiceExpuesto() != null) {
            entidad.setWebServiceExpuesto(new WebServiceExpuesto());
            entidad.getWebServiceExpuesto().setId(dto.getWebServiceExpuesto().getId());
        }

        return entidad;
    }

    public static List<TipoMapeo> toListLevel0Entity(List<TipoMapeoDTO> listDto) {
        List<TipoMapeo> listEntidad = new ArrayList<TipoMapeo>();
        for (TipoMapeoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoMapeo> toListLevel1Entity(List<TipoMapeoDTO> listDto) {
        List<TipoMapeo> listEntidad = new ArrayList<TipoMapeo>();
        for (TipoMapeoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
