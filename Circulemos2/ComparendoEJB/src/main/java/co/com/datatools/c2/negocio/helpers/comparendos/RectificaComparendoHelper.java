package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.RectificaComparendoDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.RectificaComparendo;

/**
 * @author Generated
 * @version 3.0 - Mon Feb 08 15:29:01 COT 2016
 */
public class RectificaComparendoHelper {
    // --- to DTO
    public static RectificaComparendoDTO toLevel0DTO(RectificaComparendo entidad) {
        RectificaComparendoDTO dto = new RectificaComparendoDTO();
        dto.setId(entidad.getId());
        dto.setFecha(entidad.getFecha());

        return dto;
    }

    public static RectificaComparendoDTO toLevel1DTO(RectificaComparendo entidad) {
        RectificaComparendoDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));

        return dto;
    }

    public static List<RectificaComparendoDTO> toListLevel0DTO(List<RectificaComparendo> listEntidad) {
        List<RectificaComparendoDTO> listDto = new ArrayList<RectificaComparendoDTO>();
        for (RectificaComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RectificaComparendoDTO> toListLevel1DTO(List<RectificaComparendo> listEntidad) {
        List<RectificaComparendoDTO> listDto = new ArrayList<RectificaComparendoDTO>();
        for (RectificaComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RectificaComparendo toLevel0Entity(RectificaComparendoDTO dto, RectificaComparendo entidad) {
        if (null == entidad) {
            entidad = new RectificaComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setFecha(dto.getFecha());

        return entidad;
    }

    public static RectificaComparendo toLevel1Entity(RectificaComparendoDTO dto, RectificaComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }

        return entidad;
    }

    public static List<RectificaComparendo> toListLevel0Entity(List<RectificaComparendoDTO> listDto) {
        List<RectificaComparendo> listEntidad = new ArrayList<RectificaComparendo>();
        for (RectificaComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RectificaComparendo> toListLevel1Entity(List<RectificaComparendoDTO> listDto) {
        List<RectificaComparendo> listEntidad = new ArrayList<RectificaComparendo>();
        for (RectificaComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
