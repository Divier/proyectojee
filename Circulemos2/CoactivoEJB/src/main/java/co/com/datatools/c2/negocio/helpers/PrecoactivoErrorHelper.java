package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.PrecoactivoErrorDTO;
import co.com.datatools.c2.entidades.ErrorGeneraCoactivo;
import co.com.datatools.c2.entidades.Precoactivo;
import co.com.datatools.c2.entidades.PrecoactivoError;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:42 COT 2016
 */
public class PrecoactivoErrorHelper {
    // --- to DTO
    public static PrecoactivoErrorDTO toLevel0DTO(PrecoactivoError entidad) {
        PrecoactivoErrorDTO dto = new PrecoactivoErrorDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static PrecoactivoErrorDTO toLevel1DTO(PrecoactivoError entidad) {
        PrecoactivoErrorDTO dto = toLevel0DTO(entidad);
        if (entidad.getErrorGeneraCoactivo() != null)
            dto.setErrorGeneraCoactivo(ErrorGeneraCoactivoHelper.toLevel0DTO(entidad.getErrorGeneraCoactivo()));
        if (entidad.getPrecoactivo() != null)
            dto.setPrecoactivo(PrecoactivoHelper.toLevel0DTO(entidad.getPrecoactivo()));

        return dto;
    }

    public static List<PrecoactivoErrorDTO> toListLevel0DTO(List<PrecoactivoError> listEntidad) {
        List<PrecoactivoErrorDTO> listDto = new ArrayList<PrecoactivoErrorDTO>();
        for (PrecoactivoError entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PrecoactivoErrorDTO> toListLevel1DTO(List<PrecoactivoError> listEntidad) {
        List<PrecoactivoErrorDTO> listDto = new ArrayList<PrecoactivoErrorDTO>();
        for (PrecoactivoError entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static PrecoactivoError toLevel0Entity(PrecoactivoErrorDTO dto, PrecoactivoError entidad) {
        if (null == entidad) {
            entidad = new PrecoactivoError();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static PrecoactivoError toLevel1Entity(PrecoactivoErrorDTO dto, PrecoactivoError entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getErrorGeneraCoactivo() != null) {
            entidad.setErrorGeneraCoactivo(new ErrorGeneraCoactivo());
            entidad.getErrorGeneraCoactivo().setId(dto.getErrorGeneraCoactivo().getId());
        }
        if (dto.getPrecoactivo() != null) {
            entidad.setPrecoactivo(new Precoactivo());
            entidad.getPrecoactivo().setId(dto.getPrecoactivo().getId());
        }

        return entidad;
    }

    public static List<PrecoactivoError> toListLevel0Entity(List<PrecoactivoErrorDTO> listDto) {
        List<PrecoactivoError> listEntidad = new ArrayList<PrecoactivoError>();
        for (PrecoactivoErrorDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<PrecoactivoError> toListLevel1Entity(List<PrecoactivoErrorDTO> listDto) {
        List<PrecoactivoError> listEntidad = new ArrayList<PrecoactivoError>();
        for (PrecoactivoErrorDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
