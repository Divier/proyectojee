package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.RectificaEvidenciaDTO;
import co.com.datatools.c2.entidades.RectificaComparendo;
import co.com.datatools.c2.entidades.RectificaEvidencia;

/**
 * @author Generated
 * @version 3.0 - Mon Feb 08 15:28:34 COT 2016
 */
public class RectificaEvidenciaHelper {
    // --- to DTO
    public static RectificaEvidenciaDTO toLevel0DTO(RectificaEvidencia entidad) {
        RectificaEvidenciaDTO dto = new RectificaEvidenciaDTO();
        dto.setId(entidad.getId());
        dto.setCodigoTipoEvidencia(entidad.getCodigoTipoEvidencia());
        dto.setIdDocumento(entidad.getIdDocumento());

        return dto;
    }

    public static RectificaEvidenciaDTO toLevel1DTO(RectificaEvidencia entidad) {
        RectificaEvidenciaDTO dto = toLevel0DTO(entidad);
        if (entidad.getRectificaComparendo() != null)
            dto.setRectificaComparendo(RectificaComparendoHelper.toLevel0DTO(entidad.getRectificaComparendo()));

        return dto;
    }

    public static List<RectificaEvidenciaDTO> toListLevel0DTO(List<RectificaEvidencia> listEntidad) {
        List<RectificaEvidenciaDTO> listDto = new ArrayList<RectificaEvidenciaDTO>();
        for (RectificaEvidencia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RectificaEvidenciaDTO> toListLevel1DTO(List<RectificaEvidencia> listEntidad) {
        List<RectificaEvidenciaDTO> listDto = new ArrayList<RectificaEvidenciaDTO>();
        for (RectificaEvidencia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RectificaEvidencia toLevel0Entity(RectificaEvidenciaDTO dto, RectificaEvidencia entidad) {
        if (null == entidad) {
            entidad = new RectificaEvidencia();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoTipoEvidencia(dto.getCodigoTipoEvidencia());
        entidad.setIdDocumento(dto.getIdDocumento());

        return entidad;
    }

    public static RectificaEvidencia toLevel1Entity(RectificaEvidenciaDTO dto, RectificaEvidencia entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getRectificaComparendo() != null) {
            entidad.setRectificaComparendo(new RectificaComparendo());
            entidad.getRectificaComparendo().setId(dto.getRectificaComparendo().getId());
        }

        return entidad;
    }

    public static List<RectificaEvidencia> toListLevel0Entity(List<RectificaEvidenciaDTO> listDto) {
        List<RectificaEvidencia> listEntidad = new ArrayList<RectificaEvidencia>();
        for (RectificaEvidenciaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RectificaEvidencia> toListLevel1Entity(List<RectificaEvidenciaDTO> listDto) {
        List<RectificaEvidencia> listEntidad = new ArrayList<RectificaEvidencia>();
        for (RectificaEvidenciaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
