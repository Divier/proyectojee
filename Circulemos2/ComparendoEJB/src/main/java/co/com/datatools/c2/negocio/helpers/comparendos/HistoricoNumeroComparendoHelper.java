package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.HistoricoNumeroComparendoDTO;
import co.com.datatools.c2.entidades.HistoricoNumeroComparendo;

/**
 * @author Generated
 * @version 3.0 - Thu Jul 14 11:53:32 COT 2016
 */
public class HistoricoNumeroComparendoHelper {
    // --- to DTO
    public static HistoricoNumeroComparendoDTO toLevel0DTO(HistoricoNumeroComparendo entidad) {
        HistoricoNumeroComparendoDTO dto = new HistoricoNumeroComparendoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroComparendoAntiguo(entidad.getNumeroComparendoAntiguo());
        dto.setNumeroComparendoNuevo(entidad.getNumeroComparendoNuevo());
        dto.setFechaCambio(entidad.getFechaCambio());

        return dto;
    }

    public static HistoricoNumeroComparendoDTO toLevel1DTO(HistoricoNumeroComparendo entidad) {
        HistoricoNumeroComparendoDTO dto = toLevel0DTO(entidad);

        if (entidad.getComparendo() != null) {
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        }

        return dto;
    }

    public static List<HistoricoNumeroComparendoDTO> toListLevel0DTO(List<HistoricoNumeroComparendo> listEntidad) {
        List<HistoricoNumeroComparendoDTO> listDto = new ArrayList<HistoricoNumeroComparendoDTO>();
        for (HistoricoNumeroComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<HistoricoNumeroComparendoDTO> toListLevel1DTO(List<HistoricoNumeroComparendo> listEntidad) {
        List<HistoricoNumeroComparendoDTO> listDto = new ArrayList<HistoricoNumeroComparendoDTO>();
        for (HistoricoNumeroComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static HistoricoNumeroComparendo toLevel0Entity(HistoricoNumeroComparendoDTO dto,
            HistoricoNumeroComparendo entidad) {
        if (null == entidad) {
            entidad = new HistoricoNumeroComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroComparendoAntiguo(dto.getNumeroComparendoAntiguo());
        entidad.setNumeroComparendoNuevo(dto.getNumeroComparendoNuevo());
        entidad.setFechaCambio(dto.getFechaCambio());

        return entidad;
    }

    public static HistoricoNumeroComparendo toLevel1Entity(HistoricoNumeroComparendoDTO dto,
            HistoricoNumeroComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(ComparendoHelper.toLevel0Entity(dto.getComparendo(), null));
        }

        return entidad;
    }

    public static List<HistoricoNumeroComparendo> toListLevel0Entity(List<HistoricoNumeroComparendoDTO> listDto) {
        List<HistoricoNumeroComparendo> listEntidad = new ArrayList<HistoricoNumeroComparendo>();
        for (HistoricoNumeroComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<HistoricoNumeroComparendo> toListLevel1Entity(List<HistoricoNumeroComparendoDTO> listDto) {
        List<HistoricoNumeroComparendo> listEntidad = new ArrayList<HistoricoNumeroComparendo>();
        for (HistoricoNumeroComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
