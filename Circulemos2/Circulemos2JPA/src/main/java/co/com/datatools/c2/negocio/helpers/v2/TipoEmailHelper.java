package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoEmailDTO;
import co.com.datatools.c2.entidades.TipoEmail;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoEmailHelper {
    // --- to DTO
    public static TipoEmailDTO toLevel0DTO(TipoEmail entidad) {
        TipoEmailDTO dto = new TipoEmailDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoEmailDTO toLevel1DTO(TipoEmail entidad) {
        TipoEmailDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoEmailDTO> toListLevel0DTO(List<TipoEmail> listEntidad) {
        List<TipoEmailDTO> listDto = new ArrayList<TipoEmailDTO>();
        for (TipoEmail entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoEmailDTO> toListLevel1DTO(List<TipoEmail> listEntidad) {
        List<TipoEmailDTO> listDto = new ArrayList<TipoEmailDTO>();
        for (TipoEmail entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoEmail toLevel0Entity(TipoEmailDTO dto, TipoEmail entidad) {
        if (null == entidad) {
            entidad = new TipoEmail();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoEmail toLevel1Entity(TipoEmailDTO dto, TipoEmail entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoEmail> toListLevel0Entity(List<TipoEmailDTO> listDto) {
        List<TipoEmail> listEntidad = new ArrayList<TipoEmail>();
        for (TipoEmailDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoEmail> toListLevel1Entity(List<TipoEmailDTO> listDto) {
        List<TipoEmail> listEntidad = new ArrayList<TipoEmail>();
        for (TipoEmailDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
