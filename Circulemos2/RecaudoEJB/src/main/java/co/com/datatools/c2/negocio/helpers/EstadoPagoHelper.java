package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EstadoPagoDTO;
import co.com.datatools.c2.entidades.EstadoPago;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 15:56:46 COT 2016
 */
public class EstadoPagoHelper {
    // --- to DTO
    public static EstadoPagoDTO toLevel0DTO(EstadoPago entidad) {
        EstadoPagoDTO dto = new EstadoPagoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static EstadoPagoDTO toLevel1DTO(EstadoPago entidad) {
        EstadoPagoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoPagoDTO> toListLevel0DTO(List<EstadoPago> listEntidad) {
        List<EstadoPagoDTO> listDto = new ArrayList<EstadoPagoDTO>();
        for (EstadoPago entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoPagoDTO> toListLevel1DTO(List<EstadoPago> listEntidad) {
        List<EstadoPagoDTO> listDto = new ArrayList<EstadoPagoDTO>();
        for (EstadoPago entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoPago toLevel0Entity(EstadoPagoDTO dto, EstadoPago entidad) {
        if (null == entidad) {
            entidad = new EstadoPago();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static EstadoPago toLevel1Entity(EstadoPagoDTO dto, EstadoPago entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoPago> toListLevel0Entity(List<EstadoPagoDTO> listDto) {
        List<EstadoPago> listEntidad = new ArrayList<EstadoPago>();
        for (EstadoPagoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoPago> toListLevel1Entity(List<EstadoPagoDTO> listDto) {
        List<EstadoPago> listEntidad = new ArrayList<EstadoPago>();
        for (EstadoPagoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
