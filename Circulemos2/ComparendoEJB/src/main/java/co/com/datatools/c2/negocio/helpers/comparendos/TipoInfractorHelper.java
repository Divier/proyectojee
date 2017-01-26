package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TipoInfractorDTO;
import co.com.datatools.c2.entidades.TipoInfractor;

/**
 * @author Generated
 * @version 3.0 - Fri Oct 09 17:44:23 COT 2015
 */
public class TipoInfractorHelper {
    // --- to DTO
    public static TipoInfractorDTO toLevel0DTO(TipoInfractor entidad) {
        TipoInfractorDTO dto = new TipoInfractorDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

    public static TipoInfractorDTO toLevel1DTO(TipoInfractor entidad) {
        TipoInfractorDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoInfractorDTO> toListLevel0DTO(List<TipoInfractor> listEntidad) {
        List<TipoInfractorDTO> listDto = new ArrayList<TipoInfractorDTO>();
        for (TipoInfractor entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoInfractorDTO> toListLevel1DTO(List<TipoInfractor> listEntidad) {
        List<TipoInfractorDTO> listDto = new ArrayList<TipoInfractorDTO>();
        for (TipoInfractor entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoInfractor toLevel0Entity(TipoInfractorDTO dto, TipoInfractor entidad) {
        if (null == entidad) {
            entidad = new TipoInfractor();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getActivo());

        return entidad;
    }

    public static TipoInfractor toLevel1Entity(TipoInfractorDTO dto, TipoInfractor entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoInfractor> toListLevel0Entity(List<TipoInfractorDTO> listDto) {
        List<TipoInfractor> listEntidad = new ArrayList<TipoInfractor>();
        for (TipoInfractorDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoInfractor> toListLevel1Entity(List<TipoInfractorDTO> listDto) {
        List<TipoInfractor> listEntidad = new ArrayList<TipoInfractor>();
        for (TipoInfractorDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}