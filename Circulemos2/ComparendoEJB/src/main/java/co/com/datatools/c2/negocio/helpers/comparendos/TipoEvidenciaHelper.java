package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoEvidenciaDTO;
import co.com.datatools.c2.entidades.TipoEvidencia;

/**
 * @author Generated
 * @version 3.0 - Thu Dec 10 16:26:23 COT 2015
 */
public class TipoEvidenciaHelper {
    // --- to DTO
    public static TipoEvidenciaDTO toLevel0DTO(TipoEvidencia entidad) {
        TipoEvidenciaDTO dto = new TipoEvidenciaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoEvidenciaDTO toLevel1DTO(TipoEvidencia entidad) {
        TipoEvidenciaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoEvidenciaDTO> toListLevel0DTO(List<TipoEvidencia> listEntidad) {
        List<TipoEvidenciaDTO> listDto = new ArrayList<TipoEvidenciaDTO>();
        for (TipoEvidencia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoEvidenciaDTO> toListLevel1DTO(List<TipoEvidencia> listEntidad) {
        List<TipoEvidenciaDTO> listDto = new ArrayList<TipoEvidenciaDTO>();
        for (TipoEvidencia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoEvidencia toLevel0Entity(TipoEvidenciaDTO dto, TipoEvidencia entidad) {
        if (null == entidad) {
            entidad = new TipoEvidencia();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoEvidencia toLevel1Entity(TipoEvidenciaDTO dto, TipoEvidencia entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoEvidencia> toListLevel0Entity(List<TipoEvidenciaDTO> listDto) {
        List<TipoEvidencia> listEntidad = new ArrayList<TipoEvidencia>();
        for (TipoEvidenciaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoEvidencia> toListLevel1Entity(List<TipoEvidenciaDTO> listDto) {
        List<TipoEvidencia> listEntidad = new ArrayList<TipoEvidencia>();
        for (TipoEvidenciaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
