package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.OrigenImpugnacionDTO;
import co.com.datatools.c2.entidades.OrigenImpugnacion;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 13:52:10 COT 2016
 */
public class OrigenImpugnacionHelper {
    // --- to DTO
    public static OrigenImpugnacionDTO toLevel0DTO(OrigenImpugnacion entidad) {
        OrigenImpugnacionDTO dto = new OrigenImpugnacionDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getActivo());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static OrigenImpugnacionDTO toLevel1DTO(OrigenImpugnacion entidad) {
        OrigenImpugnacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<OrigenImpugnacionDTO> toListLevel0DTO(List<OrigenImpugnacion> listEntidad) {
        List<OrigenImpugnacionDTO> listDto = new ArrayList<OrigenImpugnacionDTO>();
        for (OrigenImpugnacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<OrigenImpugnacionDTO> toListLevel1DTO(List<OrigenImpugnacion> listEntidad) {
        List<OrigenImpugnacionDTO> listDto = new ArrayList<OrigenImpugnacionDTO>();
        for (OrigenImpugnacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static OrigenImpugnacion toLevel0Entity(OrigenImpugnacionDTO dto, OrigenImpugnacion entidad) {
        if (null == entidad) {
            entidad = new OrigenImpugnacion();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setActivo(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static OrigenImpugnacion toLevel1Entity(OrigenImpugnacionDTO dto, OrigenImpugnacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<OrigenImpugnacion> toListLevel0Entity(List<OrigenImpugnacionDTO> listDto) {
        List<OrigenImpugnacion> listEntidad = new ArrayList<OrigenImpugnacion>();
        for (OrigenImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<OrigenImpugnacion> toListLevel1Entity(List<OrigenImpugnacionDTO> listDto) {
        List<OrigenImpugnacion> listEntidad = new ArrayList<OrigenImpugnacion>();
        for (OrigenImpugnacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
