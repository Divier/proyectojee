package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.EntidadOficioDTO;
import co.com.datatools.c2.entidades.comun.EntidadOficio;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 03 18:59:19 COT 2016
 */
public class EntidadOficioHelper {
    // --- to DTO
    public static EntidadOficioDTO toLevel0DTO(EntidadOficio entidad) {
        EntidadOficioDTO dto = new EntidadOficioDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getEstado());
        dto.setSigla(entidad.getSigla());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static EntidadOficioDTO toLevel1DTO(EntidadOficio entidad) {
        EntidadOficioDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EntidadOficioDTO> toListLevel0DTO(List<EntidadOficio> listEntidad) {
        List<EntidadOficioDTO> listDto = new ArrayList<EntidadOficioDTO>();
        for (EntidadOficio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EntidadOficioDTO> toListLevel1DTO(List<EntidadOficio> listEntidad) {
        List<EntidadOficioDTO> listDto = new ArrayList<EntidadOficioDTO>();
        for (EntidadOficio entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EntidadOficio toLevel0Entity(EntidadOficioDTO dto, EntidadOficio entidad) {
        if (null == entidad) {
            entidad = new EntidadOficio();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEstado(dto.getEstado());
        entidad.setSigla(dto.getSigla());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static EntidadOficio toLevel1Entity(EntidadOficioDTO dto, EntidadOficio entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EntidadOficio> toListLevel0Entity(List<EntidadOficioDTO> listDto) {
        List<EntidadOficio> listEntidad = new ArrayList<EntidadOficio>();
        for (EntidadOficioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EntidadOficio> toListLevel1Entity(List<EntidadOficioDTO> listDto) {
        List<EntidadOficio> listEntidad = new ArrayList<EntidadOficio>();
        for (EntidadOficioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
