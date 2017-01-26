package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoResolucion;

/**
 * @author Generated
 * @version 3.0 - Tue Feb 02 12:27:46 COT 2016
 */
public class ComparendoResolucionHelper {
    // --- to DTO
    public static ComparendoResolucionDTO toLevel0DTO(ComparendoResolucion entidad) {
        ComparendoResolucionDTO dto = new ComparendoResolucionDTO();
        dto.setId(entidad.getId());
        dto.setIdResolucion(entidad.getIdResolucion());

        return dto;
    }

    public static ComparendoResolucionDTO toLevel1DTO(ComparendoResolucion entidad) {
        ComparendoResolucionDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));

        return dto;
    }

    public static List<ComparendoResolucionDTO> toListLevel0DTO(List<ComparendoResolucion> listEntidad) {
        List<ComparendoResolucionDTO> listDto = new ArrayList<ComparendoResolucionDTO>();
        for (ComparendoResolucion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ComparendoResolucionDTO> toListLevel1DTO(List<ComparendoResolucion> listEntidad) {
        List<ComparendoResolucionDTO> listDto = new ArrayList<ComparendoResolucionDTO>();
        for (ComparendoResolucion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ComparendoResolucion toLevel0Entity(ComparendoResolucionDTO dto, ComparendoResolucion entidad) {
        if (null == entidad) {
            entidad = new ComparendoResolucion();
        }
        entidad.setId(dto.getId());
        entidad.setIdResolucion(dto.getIdResolucion());

        return entidad;
    }

    public static ComparendoResolucion toLevel1Entity(ComparendoResolucionDTO dto, ComparendoResolucion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }

        return entidad;
    }

    public static List<ComparendoResolucion> toListLevel0Entity(List<ComparendoResolucionDTO> listDto) {
        List<ComparendoResolucion> listEntidad = new ArrayList<ComparendoResolucion>();
        for (ComparendoResolucionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ComparendoResolucion> toListLevel1Entity(List<ComparendoResolucionDTO> listDto) {
        List<ComparendoResolucion> listEntidad = new ArrayList<ComparendoResolucion>();
        for (ComparendoResolucionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
