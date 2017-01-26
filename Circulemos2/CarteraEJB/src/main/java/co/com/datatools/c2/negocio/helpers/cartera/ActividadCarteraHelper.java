package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.ActividadCarteraDTO;
import co.com.datatools.c2.entidades.ActividadCartera;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:26:15 COT 2015
 */
public class ActividadCarteraHelper {
    // --- to DTO
    public static ActividadCarteraDTO toLevel0DTO(ActividadCartera entidad) {
        ActividadCarteraDTO dto = new ActividadCarteraDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ActividadCarteraDTO toLevel1DTO(ActividadCartera entidad) {
        ActividadCarteraDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ActividadCarteraDTO> toListLevel0DTO(List<ActividadCartera> listEntidad) {
        List<ActividadCarteraDTO> listDto = new ArrayList<ActividadCarteraDTO>();
        for (ActividadCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ActividadCarteraDTO> toListLevel1DTO(List<ActividadCartera> listEntidad) {
        List<ActividadCarteraDTO> listDto = new ArrayList<ActividadCarteraDTO>();
        for (ActividadCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ActividadCartera toLevel0Entity(ActividadCarteraDTO dto, ActividadCartera entidad) {
        if (null == entidad) {
            entidad = new ActividadCartera();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ActividadCartera toLevel1Entity(ActividadCarteraDTO dto, ActividadCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ActividadCartera> toListLevel0Entity(List<ActividadCarteraDTO> listDto) {
        List<ActividadCartera> listEntidad = new ArrayList<ActividadCartera>();
        for (ActividadCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ActividadCartera> toListLevel1Entity(List<ActividadCarteraDTO> listDto) {
        List<ActividadCartera> listEntidad = new ArrayList<ActividadCartera>();
        for (ActividadCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
