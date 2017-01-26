package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EstadoPrecoactivoDTO;
import co.com.datatools.c2.entidades.EstadoPrecoactivo;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 28 16:48:45 COT 2016
 */
public class EstadoPrecoactivoHelper {
    // --- to DTO
    public static EstadoPrecoactivoDTO toLevel0DTO(EstadoPrecoactivo entidad) {
        EstadoPrecoactivoDTO dto = new EstadoPrecoactivoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static EstadoPrecoactivoDTO toLevel1DTO(EstadoPrecoactivo entidad) {
        EstadoPrecoactivoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoPrecoactivoDTO> toListLevel0DTO(List<EstadoPrecoactivo> listEntidad) {
        List<EstadoPrecoactivoDTO> listDto = new ArrayList<EstadoPrecoactivoDTO>();
        for (EstadoPrecoactivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoPrecoactivoDTO> toListLevel1DTO(List<EstadoPrecoactivo> listEntidad) {
        List<EstadoPrecoactivoDTO> listDto = new ArrayList<EstadoPrecoactivoDTO>();
        for (EstadoPrecoactivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoPrecoactivo toLevel0Entity(EstadoPrecoactivoDTO dto, EstadoPrecoactivo entidad) {
        if (null == entidad) {
            entidad = new EstadoPrecoactivo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static EstadoPrecoactivo toLevel1Entity(EstadoPrecoactivoDTO dto, EstadoPrecoactivo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoPrecoactivo> toListLevel0Entity(List<EstadoPrecoactivoDTO> listDto) {
        List<EstadoPrecoactivo> listEntidad = new ArrayList<EstadoPrecoactivo>();
        for (EstadoPrecoactivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoPrecoactivo> toListLevel1Entity(List<EstadoPrecoactivoDTO> listDto) {
        List<EstadoPrecoactivo> listEntidad = new ArrayList<EstadoPrecoactivo>();
        for (EstadoPrecoactivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
