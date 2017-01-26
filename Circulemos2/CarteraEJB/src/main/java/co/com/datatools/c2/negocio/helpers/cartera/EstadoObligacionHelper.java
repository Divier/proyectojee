package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.EstadoObligacionDTO;
import co.com.datatools.c2.entidades.EstadoObligacion;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:27:05 COT 2015
 */
public class EstadoObligacionHelper {
    // --- to DTO
    public static EstadoObligacionDTO toLevel0DTO(EstadoObligacion entidad) {
        EstadoObligacionDTO dto = new EstadoObligacionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static EstadoObligacionDTO toLevel1DTO(EstadoObligacion entidad) {
        EstadoObligacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoObligacionDTO> toListLevel0DTO(List<EstadoObligacion> listEntidad) {
        List<EstadoObligacionDTO> listDto = new ArrayList<EstadoObligacionDTO>();
        for (EstadoObligacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoObligacionDTO> toListLevel1DTO(List<EstadoObligacion> listEntidad) {
        List<EstadoObligacionDTO> listDto = new ArrayList<EstadoObligacionDTO>();
        for (EstadoObligacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoObligacion toLevel0Entity(EstadoObligacionDTO dto, EstadoObligacion entidad) {
        if (null == entidad) {
            entidad = new EstadoObligacion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static EstadoObligacion toLevel1Entity(EstadoObligacionDTO dto, EstadoObligacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoObligacion> toListLevel0Entity(List<EstadoObligacionDTO> listDto) {
        List<EstadoObligacion> listEntidad = new ArrayList<EstadoObligacion>();
        for (EstadoObligacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoObligacion> toListLevel1Entity(List<EstadoObligacionDTO> listDto) {
        List<EstadoObligacion> listEntidad = new ArrayList<EstadoObligacion>();
        for (EstadoObligacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
