package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ws.ValorMapeoDTO;
import co.com.datatools.c2.entidades.ws.TipoMapeo;
import co.com.datatools.c2.entidades.ws.ValorMapeo;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 17 15:12:42 COT 2015
 */
public class ValorMapeoHelper {
    // --- to DTO
    public static ValorMapeoDTO toLevel0DTO(ValorMapeo entidad) {
        ValorMapeoDTO dto = new ValorMapeoDTO();

        dto.setId(entidad.getId());
        dto.setValorFinal(entidad.getValorFinal());
        dto.setValorOrigen(entidad.getValorOrigen());

        return dto;
    }

    public static ValorMapeoDTO toLevel1DTO(ValorMapeo entidad) {
        ValorMapeoDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoMapeo() != null)
            dto.setTipoMapeo(TipoMapeoHelper.toLevel0DTO(entidad.getTipoMapeo()));

        return dto;
    }

    public static List<ValorMapeoDTO> toListLevel0DTO(List<ValorMapeo> listEntidad) {
        List<ValorMapeoDTO> listDto = new ArrayList<ValorMapeoDTO>();
        for (ValorMapeo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ValorMapeoDTO> toListLevel1DTO(List<ValorMapeo> listEntidad) {
        List<ValorMapeoDTO> listDto = new ArrayList<ValorMapeoDTO>();
        for (ValorMapeo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ValorMapeo toLevel0Entity(ValorMapeoDTO dto, ValorMapeo entidad) {
        if (null == entidad) {
            entidad = new ValorMapeo();
        }
        entidad.setId(dto.getId());
        entidad.setValorFinal(dto.getValorFinal());
        entidad.setValorOrigen(dto.getValorOrigen());

        return entidad;
    }

    public static ValorMapeo toLevel1Entity(ValorMapeoDTO dto, ValorMapeo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoMapeo() != null) {
            entidad.setTipoMapeo(new TipoMapeo());
            entidad.getTipoMapeo().setId(dto.getTipoMapeo().getId());
        }

        return entidad;
    }

    public static List<ValorMapeo> toListLevel0Entity(List<ValorMapeoDTO> listDto) {
        List<ValorMapeo> listEntidad = new ArrayList<ValorMapeo>();
        for (ValorMapeoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ValorMapeo> toListLevel1Entity(List<ValorMapeoDTO> listDto) {
        List<ValorMapeo> listEntidad = new ArrayList<ValorMapeo>();
        for (ValorMapeoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
