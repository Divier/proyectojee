package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoPruebaDTO;
import co.com.datatools.c2.entidades.TipoPrueba;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class TipoPruebaHelper {
    // --- to DTO
    public static TipoPruebaDTO toLevel0DTO(TipoPrueba entidad) {
        TipoPruebaDTO dto = new TipoPruebaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoPruebaDTO toLevel1DTO(TipoPrueba entidad) {
        TipoPruebaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoPruebaDTO> toListLevel0DTO(List<TipoPrueba> listEntidad) {
        List<TipoPruebaDTO> listDto = new ArrayList<TipoPruebaDTO>();
        for (TipoPrueba entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoPruebaDTO> toListLevel1DTO(List<TipoPrueba> listEntidad) {
        List<TipoPruebaDTO> listDto = new ArrayList<TipoPruebaDTO>();
        for (TipoPrueba entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoPrueba toLevel0Entity(TipoPruebaDTO dto, TipoPrueba entidad) {
        if (null == entidad) {
            entidad = new TipoPrueba();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoPrueba toLevel1Entity(TipoPruebaDTO dto, TipoPrueba entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoPrueba> toListLevel0Entity(List<TipoPruebaDTO> listDto) {
        List<TipoPrueba> listEntidad = new ArrayList<TipoPrueba>();
        for (TipoPruebaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoPrueba> toListLevel1Entity(List<TipoPruebaDTO> listDto) {
        List<TipoPrueba> listEntidad = new ArrayList<TipoPrueba>();
        for (TipoPruebaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
