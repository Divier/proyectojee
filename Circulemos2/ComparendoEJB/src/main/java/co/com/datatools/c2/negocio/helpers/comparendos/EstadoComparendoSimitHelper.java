package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.EstadoComparendoSimitDTO;
import co.com.datatools.c2.entidades.EstadoComparendoSimit;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:46:51 COT 2015
 */
public class EstadoComparendoSimitHelper {
    // --- to DTO
    public static EstadoComparendoSimitDTO toLevel0DTO(EstadoComparendoSimit entidad) {
        EstadoComparendoSimitDTO dto = new EstadoComparendoSimitDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static EstadoComparendoSimitDTO toLevel1DTO(EstadoComparendoSimit entidad) {
        EstadoComparendoSimitDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoComparendoSimitDTO> toListLevel0DTO(List<EstadoComparendoSimit> listEntidad) {
        List<EstadoComparendoSimitDTO> listDto = new ArrayList<EstadoComparendoSimitDTO>();
        for (EstadoComparendoSimit entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoComparendoSimitDTO> toListLevel1DTO(List<EstadoComparendoSimit> listEntidad) {
        List<EstadoComparendoSimitDTO> listDto = new ArrayList<EstadoComparendoSimitDTO>();
        for (EstadoComparendoSimit entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoComparendoSimit toLevel0Entity(EstadoComparendoSimitDTO dto, EstadoComparendoSimit entidad) {
        if (null == entidad) {
            entidad = new EstadoComparendoSimit();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static EstadoComparendoSimit toLevel1Entity(EstadoComparendoSimitDTO dto, EstadoComparendoSimit entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoComparendoSimit> toListLevel0Entity(List<EstadoComparendoSimitDTO> listDto) {
        List<EstadoComparendoSimit> listEntidad = new ArrayList<EstadoComparendoSimit>();
        for (EstadoComparendoSimitDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoComparendoSimit> toListLevel1Entity(List<EstadoComparendoSimitDTO> listDto) {
        List<EstadoComparendoSimit> listEntidad = new ArrayList<EstadoComparendoSimit>();
        for (EstadoComparendoSimitDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
