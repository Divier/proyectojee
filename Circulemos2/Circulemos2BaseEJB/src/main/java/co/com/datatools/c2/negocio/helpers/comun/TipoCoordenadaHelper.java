package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.TipoCoordenadaDTO;
import co.com.datatools.c2.entidades.comun.TipoCoordenada;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoCoordenadaHelper {
    // --- to DTO
    public static TipoCoordenadaDTO toLevel0DTO(TipoCoordenada entidad) {
        TipoCoordenadaDTO dto = new TipoCoordenadaDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoCoordenadaDTO toLevel1DTO(TipoCoordenada entidad) {
        TipoCoordenadaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoCoordenadaDTO> toListLevel0DTO(List<TipoCoordenada> listEntidad) {
        List<TipoCoordenadaDTO> listDto = new ArrayList<TipoCoordenadaDTO>();
        for (TipoCoordenada entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoCoordenadaDTO> toListLevel1DTO(List<TipoCoordenada> listEntidad) {
        List<TipoCoordenadaDTO> listDto = new ArrayList<TipoCoordenadaDTO>();
        for (TipoCoordenada entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoCoordenada toLevel0Entity(TipoCoordenadaDTO dto, TipoCoordenada entidad) {
        if (null == entidad) {
            entidad = new TipoCoordenada();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoCoordenada toLevel1Entity(TipoCoordenadaDTO dto, TipoCoordenada entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoCoordenada> toListLevel0Entity(List<TipoCoordenadaDTO> listDto) {
        List<TipoCoordenada> listEntidad = new ArrayList<TipoCoordenada>();
        for (TipoCoordenadaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoCoordenada> toListLevel1Entity(List<TipoCoordenadaDTO> listDto) {
        List<TipoCoordenada> listEntidad = new ArrayList<TipoCoordenada>();
        for (TipoCoordenadaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
