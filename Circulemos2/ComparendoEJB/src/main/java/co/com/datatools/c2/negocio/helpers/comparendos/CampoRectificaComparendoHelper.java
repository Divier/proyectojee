package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.CampoRectificaComparendoDTO;
import co.com.datatools.c2.entidades.CampoEntidad;
import co.com.datatools.c2.entidades.CampoRectificaComparendo;
import co.com.datatools.c2.entidades.RectificaComparendo;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 28 16:06:58 COT 2016
 */
public class CampoRectificaComparendoHelper {
    // --- to DTO
    public static CampoRectificaComparendoDTO toLevel0DTO(CampoRectificaComparendo entidad) {
        CampoRectificaComparendoDTO dto = new CampoRectificaComparendoDTO();
        dto.setId(entidad.getId());
        dto.setValorNuevo(entidad.getValorNuevo());
        dto.setValorOriginal(entidad.getValorOriginal());

        return dto;
    }

    public static CampoRectificaComparendoDTO toLevel1DTO(CampoRectificaComparendo entidad) {
        CampoRectificaComparendoDTO dto = toLevel0DTO(entidad);
        if (entidad.getRectificaComparendo() != null)
            dto.setRectificaComparendo(RectificaComparendoHelper.toLevel0DTO(entidad.getRectificaComparendo()));
        if (entidad.getCampoEntidad() != null)
            dto.setCampoEntidad(CampoEntidadHelper.toLevel0DTO(entidad.getCampoEntidad()));

        return dto;
    }

    public static List<CampoRectificaComparendoDTO> toListLevel0DTO(List<CampoRectificaComparendo> listEntidad) {
        List<CampoRectificaComparendoDTO> listDto = new ArrayList<CampoRectificaComparendoDTO>();
        for (CampoRectificaComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CampoRectificaComparendoDTO> toListLevel1DTO(List<CampoRectificaComparendo> listEntidad) {
        List<CampoRectificaComparendoDTO> listDto = new ArrayList<CampoRectificaComparendoDTO>();
        for (CampoRectificaComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CampoRectificaComparendo toLevel0Entity(CampoRectificaComparendoDTO dto,
            CampoRectificaComparendo entidad) {
        if (null == entidad) {
            entidad = new CampoRectificaComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setValorNuevo(dto.getValorNuevo());
        entidad.setValorOriginal(dto.getValorOriginal());

        return entidad;
    }

    public static CampoRectificaComparendo toLevel1Entity(CampoRectificaComparendoDTO dto,
            CampoRectificaComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getRectificaComparendo() != null) {
            entidad.setRectificaComparendo(new RectificaComparendo());
            entidad.getRectificaComparendo().setId(dto.getRectificaComparendo().getId());
        }
        if (dto.getCampoEntidad() != null) {
            entidad.setCampoEntidad(new CampoEntidad());
            entidad.getCampoEntidad().setCodigo(dto.getCampoEntidad().getCodigo());
        }

        return entidad;
    }

    public static List<CampoRectificaComparendo> toListLevel0Entity(List<CampoRectificaComparendoDTO> listDto) {
        List<CampoRectificaComparendo> listEntidad = new ArrayList<CampoRectificaComparendo>();
        for (CampoRectificaComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CampoRectificaComparendo> toListLevel1Entity(List<CampoRectificaComparendoDTO> listDto) {
        List<CampoRectificaComparendo> listEntidad = new ArrayList<CampoRectificaComparendo>();
        for (CampoRectificaComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
