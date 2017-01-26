package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ws.WebServiceExpuestoDTO;
import co.com.datatools.c2.entidades.ws.WebServiceExpuesto;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 17 15:12:13 COT 2015
 */
public class WebServiceExpuestoHelper {
    // --- to DTO
    public static WebServiceExpuestoDTO toLevel0DTO(WebServiceExpuesto entidad) {
        WebServiceExpuestoDTO dto = new WebServiceExpuestoDTO();

        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static WebServiceExpuestoDTO toLevel1DTO(WebServiceExpuesto entidad) {
        WebServiceExpuestoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<WebServiceExpuestoDTO> toListLevel0DTO(List<WebServiceExpuesto> listEntidad) {
        List<WebServiceExpuestoDTO> listDto = new ArrayList<WebServiceExpuestoDTO>();
        for (WebServiceExpuesto entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<WebServiceExpuestoDTO> toListLevel1DTO(List<WebServiceExpuesto> listEntidad) {
        List<WebServiceExpuestoDTO> listDto = new ArrayList<WebServiceExpuestoDTO>();
        for (WebServiceExpuesto entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static WebServiceExpuesto toLevel0Entity(WebServiceExpuestoDTO dto, WebServiceExpuesto entidad) {
        if (null == entidad) {
            entidad = new WebServiceExpuesto();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static WebServiceExpuesto toLevel1Entity(WebServiceExpuestoDTO dto, WebServiceExpuesto entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<WebServiceExpuesto> toListLevel0Entity(List<WebServiceExpuestoDTO> listDto) {
        List<WebServiceExpuesto> listEntidad = new ArrayList<WebServiceExpuesto>();
        for (WebServiceExpuestoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<WebServiceExpuesto> toListLevel1Entity(List<WebServiceExpuestoDTO> listDto) {
        List<WebServiceExpuesto> listEntidad = new ArrayList<WebServiceExpuesto>();
        for (WebServiceExpuestoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
