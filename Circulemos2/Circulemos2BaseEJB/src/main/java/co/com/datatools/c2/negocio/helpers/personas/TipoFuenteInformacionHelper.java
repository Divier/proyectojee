package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 06 17:40:01 COT 2015
 */
public class TipoFuenteInformacionHelper {
    // --- to DTO
    public static TipoFuenteInformacionDTO toLevel0DTO(TipoFuenteInformacion entidad) {
        TipoFuenteInformacionDTO dto = new TipoFuenteInformacionDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setCodigo(entidad.getCodigo());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    public static TipoFuenteInformacionDTO toLevel1DTO(TipoFuenteInformacion entidad) {
        TipoFuenteInformacionDTO dto = toLevel0DTO(entidad);

        if (entidad.getScoreUbicabilidad() != null)
            dto.setScoreUbicabilidad(ScoreUbicabilidadHelper.toLevel0DTO(entidad.getScoreUbicabilidad()));
        return dto;
    }

    public static List<TipoFuenteInformacionDTO> toListLevel0DTO(List<TipoFuenteInformacion> listEntidad) {
        List<TipoFuenteInformacionDTO> listDto = new ArrayList<TipoFuenteInformacionDTO>();
        for (TipoFuenteInformacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoFuenteInformacionDTO> toListLevel1DTO(List<TipoFuenteInformacion> listEntidad) {
        List<TipoFuenteInformacionDTO> listDto = new ArrayList<TipoFuenteInformacionDTO>();
        for (TipoFuenteInformacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoFuenteInformacion toLevel0Entity(TipoFuenteInformacionDTO dto, TipoFuenteInformacion entidad) {
        if (null == entidad) {
            entidad = new TipoFuenteInformacion();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setCodigo(dto.getCodigo());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());

        return entidad;
    }

    public static TipoFuenteInformacion toLevel1Entity(TipoFuenteInformacionDTO dto, TipoFuenteInformacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        if (dto.getScoreUbicabilidad() != null) {
            entidad.setScoreUbicabilidad(new ScoreUbicabilidad(dto.getScoreUbicabilidad().getId()));
        }
        return entidad;
    }

    public static List<TipoFuenteInformacion> toListLevel0Entity(List<TipoFuenteInformacionDTO> listDto) {
        List<TipoFuenteInformacion> listEntidad = new ArrayList<TipoFuenteInformacion>();
        for (TipoFuenteInformacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoFuenteInformacion> toListLevel1Entity(List<TipoFuenteInformacionDTO> listDto) {
        List<TipoFuenteInformacion> listEntidad = new ArrayList<TipoFuenteInformacion>();
        for (TipoFuenteInformacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}