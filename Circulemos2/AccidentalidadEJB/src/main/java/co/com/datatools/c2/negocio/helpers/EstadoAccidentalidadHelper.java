package co.com.datatools.c2.negocio.helpers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.entidades.*;
import java.math.*;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 05 14:59:53 COT 2016
 */
public class EstadoAccidentalidadHelper {
    // --- to DTO
    public static EstadoAccidentalidadDTO toLevel0DTO(EstadoAccidentalidad entidad) {
        EstadoAccidentalidadDTO dto = new EstadoAccidentalidadDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static EstadoAccidentalidadDTO toLevel1DTO(EstadoAccidentalidad entidad) {
        EstadoAccidentalidadDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoAccidentalidadDTO> toListLevel0DTO(List<EstadoAccidentalidad> listEntidad) {
        List<EstadoAccidentalidadDTO> listDto = new ArrayList<EstadoAccidentalidadDTO>();
        for (EstadoAccidentalidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoAccidentalidadDTO> toListLevel1DTO(List<EstadoAccidentalidad> listEntidad) {
        List<EstadoAccidentalidadDTO> listDto = new ArrayList<EstadoAccidentalidadDTO>();
        for (EstadoAccidentalidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoAccidentalidad toLevel0Entity(EstadoAccidentalidadDTO dto, EstadoAccidentalidad entidad) {
        if (null == entidad) {
            entidad = new EstadoAccidentalidad();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static EstadoAccidentalidad toLevel1Entity(EstadoAccidentalidadDTO dto, EstadoAccidentalidad entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoAccidentalidad> toListLevel0Entity(List<EstadoAccidentalidadDTO> listDto) {
        List<EstadoAccidentalidad> listEntidad = new ArrayList<EstadoAccidentalidad>();
        for (EstadoAccidentalidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoAccidentalidad> toListLevel1Entity(List<EstadoAccidentalidadDTO> listDto) {
        List<EstadoAccidentalidad> listEntidad = new ArrayList<EstadoAccidentalidad>();
        for (EstadoAccidentalidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
