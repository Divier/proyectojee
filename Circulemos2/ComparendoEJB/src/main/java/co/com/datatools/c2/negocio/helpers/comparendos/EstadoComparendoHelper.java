package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.EstadoComparendoDTO;
import co.com.datatools.c2.entidades.EstadoComparendo;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 19 15:56:17 COT 2015
 */
public class EstadoComparendoHelper {
    // --- to DTO
    public static EstadoComparendoDTO toLevel0DTO(EstadoComparendo entidad) {
        EstadoComparendoDTO dto = new EstadoComparendoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static EstadoComparendoDTO toLevel1DTO(EstadoComparendo entidad) {
        EstadoComparendoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoComparendoDTO> toListLevel0DTO(List<EstadoComparendo> listEntidad) {
        List<EstadoComparendoDTO> listDto = new ArrayList<EstadoComparendoDTO>();
        for (EstadoComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoComparendoDTO> toListLevel1DTO(List<EstadoComparendo> listEntidad) {
        List<EstadoComparendoDTO> listDto = new ArrayList<EstadoComparendoDTO>();
        for (EstadoComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoComparendo toLevel0Entity(EstadoComparendoDTO dto, EstadoComparendo entidad) {
        if (null == entidad) {
            entidad = new EstadoComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static EstadoComparendo toLevel1Entity(EstadoComparendoDTO dto, EstadoComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoComparendo> toListLevel0Entity(List<EstadoComparendoDTO> listDto) {
        List<EstadoComparendo> listEntidad = new ArrayList<EstadoComparendo>();
        for (EstadoComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoComparendo> toListLevel1Entity(List<EstadoComparendoDTO> listDto) {
        List<EstadoComparendo> listEntidad = new ArrayList<EstadoComparendo>();
        for (EstadoComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
