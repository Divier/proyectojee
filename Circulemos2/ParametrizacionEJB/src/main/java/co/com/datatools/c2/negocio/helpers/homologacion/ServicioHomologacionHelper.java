package co.com.datatools.c2.negocio.helpers.homologacion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.homologacion.ServicioHomologacionDTO;
import co.com.datatools.c2.entidades.ServicioHomologacion;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:47:46 COT 2015
 */
public class ServicioHomologacionHelper {
    // --- to DTO
    public static ServicioHomologacionDTO toLevel0DTO(ServicioHomologacion entidad) {
        ServicioHomologacionDTO dto = new ServicioHomologacionDTO();
        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static ServicioHomologacionDTO toLevel1DTO(ServicioHomologacion entidad) {
        ServicioHomologacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ServicioHomologacionDTO> toListLevel0DTO(List<ServicioHomologacion> listEntidad) {
        List<ServicioHomologacionDTO> listDto = new ArrayList<ServicioHomologacionDTO>();
        for (ServicioHomologacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ServicioHomologacionDTO> toListLevel1DTO(List<ServicioHomologacion> listEntidad) {
        List<ServicioHomologacionDTO> listDto = new ArrayList<ServicioHomologacionDTO>();
        for (ServicioHomologacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ServicioHomologacion toLevel0Entity(ServicioHomologacionDTO dto, ServicioHomologacion entidad) {
        if (null == entidad) {
            entidad = new ServicioHomologacion();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static ServicioHomologacion toLevel1Entity(ServicioHomologacionDTO dto, ServicioHomologacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ServicioHomologacion> toListLevel0Entity(List<ServicioHomologacionDTO> listDto) {
        List<ServicioHomologacion> listEntidad = new ArrayList<ServicioHomologacion>();
        for (ServicioHomologacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ServicioHomologacion> toListLevel1Entity(List<ServicioHomologacionDTO> listDto) {
        List<ServicioHomologacion> listEntidad = new ArrayList<ServicioHomologacion>();
        for (ServicioHomologacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
