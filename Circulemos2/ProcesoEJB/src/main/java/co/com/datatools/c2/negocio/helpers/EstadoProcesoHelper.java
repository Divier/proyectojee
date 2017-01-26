package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EstadoProcesoDTO;
import co.com.datatools.c2.entidades.EstadoProceso;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class EstadoProcesoHelper {
    // --- to DTO
    public static EstadoProcesoDTO toLevel0DTO(EstadoProceso entidad) {
        EstadoProcesoDTO dto = new EstadoProcesoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static EstadoProcesoDTO toLevel1DTO(EstadoProceso entidad) {
        EstadoProcesoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoProcesoDTO> toListLevel0DTO(List<EstadoProceso> listEntidad) {
        List<EstadoProcesoDTO> listDto = new ArrayList<EstadoProcesoDTO>();
        for (EstadoProceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoProcesoDTO> toListLevel1DTO(List<EstadoProceso> listEntidad) {
        List<EstadoProcesoDTO> listDto = new ArrayList<EstadoProcesoDTO>();
        for (EstadoProceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoProceso toLevel0Entity(EstadoProcesoDTO dto, EstadoProceso entidad) {
        if (null == entidad) {
            entidad = new EstadoProceso();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static EstadoProceso toLevel1Entity(EstadoProcesoDTO dto, EstadoProceso entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoProceso> toListLevel0Entity(List<EstadoProcesoDTO> listDto) {
        List<EstadoProceso> listEntidad = new ArrayList<EstadoProceso>();
        for (EstadoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoProceso> toListLevel1Entity(List<EstadoProcesoDTO> listDto) {
        List<EstadoProceso> listEntidad = new ArrayList<EstadoProceso>();
        for (EstadoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
