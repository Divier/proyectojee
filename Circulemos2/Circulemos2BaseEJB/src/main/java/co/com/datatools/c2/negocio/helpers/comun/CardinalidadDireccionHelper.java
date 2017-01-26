package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.CardinalidadDireccionDTO;
import co.com.datatools.c2.entidades.comun.CardinalidadDireccion;
import co.com.datatools.c2.entidades.comun.Pais;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:43 COT 2014
 */
public class CardinalidadDireccionHelper {
    // --- to DTO
    public static CardinalidadDireccionDTO toLevel0DTO(CardinalidadDireccion entidad) {
        CardinalidadDireccionDTO dto = new CardinalidadDireccionDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setAbreviatura(entidad.getAbreviatura());

        return dto;
    }

    public static CardinalidadDireccionDTO toLevel1DTO(CardinalidadDireccion entidad) {
        CardinalidadDireccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getPais() != null)
            dto.setPais(PaisHelper.toLevel0DTO(entidad.getPais()));

        return dto;
    }

    public static List<CardinalidadDireccionDTO> toListLevel0DTO(List<CardinalidadDireccion> listEntidad) {
        List<CardinalidadDireccionDTO> listDto = new ArrayList<CardinalidadDireccionDTO>();
        for (CardinalidadDireccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CardinalidadDireccionDTO> toListLevel1DTO(List<CardinalidadDireccion> listEntidad) {
        List<CardinalidadDireccionDTO> listDto = new ArrayList<CardinalidadDireccionDTO>();
        for (CardinalidadDireccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CardinalidadDireccion toLevel0Entity(CardinalidadDireccionDTO dto, CardinalidadDireccion entidad) {
        if (null == entidad) {
            entidad = new CardinalidadDireccion();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setAbreviatura(dto.getAbreviatura());

        return entidad;
    }

    public static CardinalidadDireccion toLevel1Entity(CardinalidadDireccionDTO dto, CardinalidadDireccion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPais() != null) {
            entidad.setPais(new Pais());
            entidad.getPais().setId(dto.getPais().getId());
        }

        return entidad;
    }

    public static List<CardinalidadDireccion> toListLevel0Entity(List<CardinalidadDireccionDTO> listDto) {
        List<CardinalidadDireccion> listEntidad = new ArrayList<CardinalidadDireccion>();
        for (CardinalidadDireccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CardinalidadDireccion> toListLevel1Entity(List<CardinalidadDireccionDTO> listDto) {
        List<CardinalidadDireccion> listEntidad = new ArrayList<CardinalidadDireccion>();
        for (CardinalidadDireccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
