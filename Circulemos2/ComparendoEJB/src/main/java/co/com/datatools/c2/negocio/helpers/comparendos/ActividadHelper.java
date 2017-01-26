package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ActividadDTO;
import co.com.datatools.c2.entidades.Actividad;
import co.com.datatools.c2.entidades.ProcesoNegocio;
import co.com.datatools.c2.negocio.helpers.v2.ProcesoNegocioHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Mar 14 17:57:05 COT 2016
 */
public class ActividadHelper {
    // --- to DTO
    public static ActividadDTO toLevel0DTO(Actividad entidad) {
        ActividadDTO dto = new ActividadDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static ActividadDTO toLevel1DTO(Actividad entidad) {
        ActividadDTO dto = toLevel0DTO(entidad);
        if (entidad.getProceso() != null)
            dto.setProceso(ProcesoNegocioHelper.toLevel0DTO(entidad.getProceso()));

        return dto;
    }

    public static List<ActividadDTO> toListLevel0DTO(List<Actividad> listEntidad) {
        List<ActividadDTO> listDto = new ArrayList<ActividadDTO>();
        for (Actividad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ActividadDTO> toListLevel1DTO(List<Actividad> listEntidad) {
        List<ActividadDTO> listDto = new ArrayList<ActividadDTO>();
        for (Actividad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Actividad toLevel0Entity(ActividadDTO dto, Actividad entidad) {
        if (null == entidad) {
            entidad = new Actividad();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static Actividad toLevel1Entity(ActividadDTO dto, Actividad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getProceso() != null) {
            entidad.setProceso(new ProcesoNegocio());
            entidad.getProceso().setId(dto.getProceso().getId());
        }

        return entidad;
    }

    public static List<Actividad> toListLevel0Entity(List<ActividadDTO> listDto) {
        List<Actividad> listEntidad = new ArrayList<Actividad>();
        for (ActividadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Actividad> toListLevel1Entity(List<ActividadDTO> listDto) {
        List<Actividad> listEntidad = new ArrayList<Actividad>();
        for (ActividadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
