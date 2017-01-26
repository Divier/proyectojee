package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.ScoreUbicabilidadDTO;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:52 COT 2016
 */
public class ScoreUbicabilidadHelper {
    // --- to DTO
    public static ScoreUbicabilidadDTO toLevel0DTO(ScoreUbicabilidad entidad) {
        ScoreUbicabilidadDTO dto = new ScoreUbicabilidadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static ScoreUbicabilidadDTO toLevel1DTO(ScoreUbicabilidad entidad) {
        ScoreUbicabilidadDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ScoreUbicabilidadDTO> toListLevel0DTO(List<ScoreUbicabilidad> listEntidad) {
        List<ScoreUbicabilidadDTO> listDto = new ArrayList<ScoreUbicabilidadDTO>();
        for (ScoreUbicabilidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ScoreUbicabilidadDTO> toListLevel1DTO(List<ScoreUbicabilidad> listEntidad) {
        List<ScoreUbicabilidadDTO> listDto = new ArrayList<ScoreUbicabilidadDTO>();
        for (ScoreUbicabilidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ScoreUbicabilidad toLevel0Entity(ScoreUbicabilidadDTO dto, ScoreUbicabilidad entidad) {
        if (null == entidad) {
            entidad = new ScoreUbicabilidad();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static ScoreUbicabilidad toLevel1Entity(ScoreUbicabilidadDTO dto, ScoreUbicabilidad entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ScoreUbicabilidad> toListLevel0Entity(List<ScoreUbicabilidadDTO> listDto) {
        List<ScoreUbicabilidad> listEntidad = new ArrayList<ScoreUbicabilidad>();
        for (ScoreUbicabilidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ScoreUbicabilidad> toListLevel1Entity(List<ScoreUbicabilidadDTO> listDto) {
        List<ScoreUbicabilidad> listEntidad = new ArrayList<ScoreUbicabilidad>();
        for (ScoreUbicabilidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
