package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EstadoResolucionDTO;
import co.com.datatools.c2.entidades.EstadoResolucion;

/**
 * @author Generated
 * @version 3.0 - Fri Jan 29 11:04:50 COT 2016
 */
public class EstadoResolucionHelper {
    // --- to DTO
    public static EstadoResolucionDTO toLevel0DTO(EstadoResolucion entidad) {
        EstadoResolucionDTO dto = new EstadoResolucionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static EstadoResolucionDTO toLevel1DTO(EstadoResolucion entidad) {
        EstadoResolucionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoResolucionDTO> toListLevel0DTO(List<EstadoResolucion> listEntidad) {
        List<EstadoResolucionDTO> listDto = new ArrayList<EstadoResolucionDTO>();
        for (EstadoResolucion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoResolucionDTO> toListLevel1DTO(List<EstadoResolucion> listEntidad) {
        List<EstadoResolucionDTO> listDto = new ArrayList<EstadoResolucionDTO>();
        for (EstadoResolucion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoResolucion toLevel0Entity(EstadoResolucionDTO dto, EstadoResolucion entidad) {
        if (null == entidad) {
            entidad = new EstadoResolucion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static EstadoResolucion toLevel1Entity(EstadoResolucionDTO dto, EstadoResolucion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoResolucion> toListLevel0Entity(List<EstadoResolucionDTO> listDto) {
        List<EstadoResolucion> listEntidad = new ArrayList<EstadoResolucion>();
        for (EstadoResolucionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoResolucion> toListLevel1Entity(List<EstadoResolucionDTO> listDto) {
        List<EstadoResolucion> listEntidad = new ArrayList<EstadoResolucion>();
        for (EstadoResolucionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
