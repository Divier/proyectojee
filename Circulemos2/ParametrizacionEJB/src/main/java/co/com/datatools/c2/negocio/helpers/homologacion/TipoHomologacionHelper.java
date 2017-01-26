package co.com.datatools.c2.negocio.helpers.homologacion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.homologacion.TipoHomologacionDTO;
import co.com.datatools.c2.entidades.ServicioHomologacion;
import co.com.datatools.c2.entidades.TipoHomologacion;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:48:35 COT 2015
 */
public class TipoHomologacionHelper {
    // --- to DTO
    public static TipoHomologacionDTO toLevel0DTO(TipoHomologacion entidad) {
        TipoHomologacionDTO dto = new TipoHomologacionDTO();
        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoHomologacionDTO toLevel1DTO(TipoHomologacion entidad) {
        TipoHomologacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getServicioHomologacion() != null)
            dto.setServicioHomologacion(ServicioHomologacionHelper.toLevel0DTO(entidad.getServicioHomologacion()));

        return dto;
    }

    public static List<TipoHomologacionDTO> toListLevel0DTO(List<TipoHomologacion> listEntidad) {
        List<TipoHomologacionDTO> listDto = new ArrayList<TipoHomologacionDTO>();
        for (TipoHomologacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoHomologacionDTO> toListLevel1DTO(List<TipoHomologacion> listEntidad) {
        List<TipoHomologacionDTO> listDto = new ArrayList<TipoHomologacionDTO>();
        for (TipoHomologacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoHomologacion toLevel0Entity(TipoHomologacionDTO dto, TipoHomologacion entidad) {
        if (null == entidad) {
            entidad = new TipoHomologacion();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoHomologacion toLevel1Entity(TipoHomologacionDTO dto, TipoHomologacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getServicioHomologacion() != null) {
            entidad.setServicioHomologacion(new ServicioHomologacion());
            entidad.getServicioHomologacion().setId(dto.getServicioHomologacion().getId());
        }

        return entidad;
    }

    public static List<TipoHomologacion> toListLevel0Entity(List<TipoHomologacionDTO> listDto) {
        List<TipoHomologacion> listEntidad = new ArrayList<TipoHomologacion>();
        for (TipoHomologacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoHomologacion> toListLevel1Entity(List<TipoHomologacionDTO> listDto) {
        List<TipoHomologacion> listEntidad = new ArrayList<TipoHomologacion>();
        for (TipoHomologacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
