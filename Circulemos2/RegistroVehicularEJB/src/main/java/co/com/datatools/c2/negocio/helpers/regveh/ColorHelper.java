package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ColorDTO;
import co.com.datatools.c2.entidades.Color;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 09:05:18 COT 2015
 */
public class ColorHelper {
    // --- to DTO
    public static ColorDTO toLevel0DTO(Color entidad) {
        ColorDTO dto = new ColorDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static ColorDTO toLevel1DTO(Color entidad) {
        ColorDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ColorDTO> toListLevel0DTO(List<Color> listEntidad) {
        List<ColorDTO> listDto = new ArrayList<ColorDTO>();
        for (Color entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ColorDTO> toListLevel1DTO(List<Color> listEntidad) {
        List<ColorDTO> listDto = new ArrayList<ColorDTO>();
        for (Color entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Color toLevel0Entity(ColorDTO dto, Color entidad) {
        if (null == entidad) {
            entidad = new Color();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static Color toLevel1Entity(ColorDTO dto, Color entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<Color> toListLevel0Entity(List<ColorDTO> listDto) {
        List<Color> listEntidad = new ArrayList<Color>();
        for (ColorDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Color> toListLevel1Entity(List<ColorDTO> listDto) {
        List<Color> listEntidad = new ArrayList<Color>();
        for (ColorDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
