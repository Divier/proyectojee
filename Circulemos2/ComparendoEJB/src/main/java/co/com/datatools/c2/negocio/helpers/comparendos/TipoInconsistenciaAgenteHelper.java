package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoInconsistenciaAgenteDTO;
import co.com.datatools.c2.entidades.TipoInconsistenciaAgente;

/**
 * @author Generated
 * @version 3.0 - Thu Dec 10 16:26:23 COT 2015
 */
public class TipoInconsistenciaAgenteHelper {
    // --- to DTO
    public static TipoInconsistenciaAgenteDTO toLevel0DTO(TipoInconsistenciaAgente entidad) {
        TipoInconsistenciaAgenteDTO dto = new TipoInconsistenciaAgenteDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoInconsistenciaAgenteDTO toLevel1DTO(TipoInconsistenciaAgente entidad) {
        TipoInconsistenciaAgenteDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoInconsistenciaAgenteDTO> toListLevel0DTO(List<TipoInconsistenciaAgente> listEntidad) {
        List<TipoInconsistenciaAgenteDTO> listDto = new ArrayList<TipoInconsistenciaAgenteDTO>();
        for (TipoInconsistenciaAgente entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoInconsistenciaAgenteDTO> toListLevel1DTO(List<TipoInconsistenciaAgente> listEntidad) {
        List<TipoInconsistenciaAgenteDTO> listDto = new ArrayList<TipoInconsistenciaAgenteDTO>();
        for (TipoInconsistenciaAgente entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoInconsistenciaAgente toLevel0Entity(TipoInconsistenciaAgenteDTO dto,
            TipoInconsistenciaAgente entidad) {
        if (null == entidad) {
            entidad = new TipoInconsistenciaAgente();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoInconsistenciaAgente toLevel1Entity(TipoInconsistenciaAgenteDTO dto,
            TipoInconsistenciaAgente entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoInconsistenciaAgente> toListLevel0Entity(List<TipoInconsistenciaAgenteDTO> listDto) {
        List<TipoInconsistenciaAgente> listEntidad = new ArrayList<TipoInconsistenciaAgente>();
        for (TipoInconsistenciaAgenteDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoInconsistenciaAgente> toListLevel1Entity(List<TipoInconsistenciaAgenteDTO> listDto) {
        List<TipoInconsistenciaAgente> listEntidad = new ArrayList<TipoInconsistenciaAgente>();
        for (TipoInconsistenciaAgenteDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
