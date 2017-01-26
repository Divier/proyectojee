package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoBienDTO;
import co.com.datatools.c2.entidades.TipoBien;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:57:11 COT 2016
 */
public class TipoBienHelper {
    // --- to DTO
    public static TipoBienDTO toLevel0DTO(TipoBien entidad) {
        TipoBienDTO dto = new TipoBienDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoBienDTO toLevel1DTO(TipoBien entidad) {
        TipoBienDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoBienDTO> toListLevel0DTO(List<TipoBien> listEntidad) {
        List<TipoBienDTO> listDto = new ArrayList<TipoBienDTO>();
        for (TipoBien entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoBienDTO> toListLevel1DTO(List<TipoBien> listEntidad) {
        List<TipoBienDTO> listDto = new ArrayList<TipoBienDTO>();
        for (TipoBien entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoBien toLevel0Entity(TipoBienDTO dto, TipoBien entidad) {
        if (null == entidad) {
            entidad = new TipoBien();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoBien toLevel1Entity(TipoBienDTO dto, TipoBien entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoBien> toListLevel0Entity(List<TipoBienDTO> listDto) {
        List<TipoBien> listEntidad = new ArrayList<TipoBien>();
        for (TipoBienDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoBien> toListLevel1Entity(List<TipoBienDTO> listDto) {
        List<TipoBien> listEntidad = new ArrayList<TipoBien>();
        for (TipoBienDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
