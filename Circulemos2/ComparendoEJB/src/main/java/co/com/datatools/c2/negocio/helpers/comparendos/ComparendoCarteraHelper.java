package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoCarteraDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoCartera;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 16 11:03:39 COT 2015
 */
public class ComparendoCarteraHelper {
    // --- to DTO
    public static ComparendoCarteraDTO toLevel0DTO(ComparendoCartera entidad) {
        ComparendoCarteraDTO dto = new ComparendoCarteraDTO();
        dto.setIdComparendoCartera(entidad.getIdComparendoCartera());
        dto.setIdCartera(entidad.getIdCartera());

        return dto;
    }

    public static ComparendoCarteraDTO toLevel1DTO(ComparendoCartera entidad) {
        ComparendoCarteraDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));

        return dto;
    }

    public static List<ComparendoCarteraDTO> toListLevel0DTO(List<ComparendoCartera> listEntidad) {
        List<ComparendoCarteraDTO> listDto = new ArrayList<ComparendoCarteraDTO>();
        for (ComparendoCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ComparendoCarteraDTO> toListLevel1DTO(List<ComparendoCartera> listEntidad) {
        List<ComparendoCarteraDTO> listDto = new ArrayList<ComparendoCarteraDTO>();
        for (ComparendoCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ComparendoCartera toLevel0Entity(ComparendoCarteraDTO dto, ComparendoCartera entidad) {
        if (null == entidad) {
            entidad = new ComparendoCartera();
        }
        entidad.setIdComparendoCartera(dto.getIdComparendoCartera());
        entidad.setIdCartera(dto.getIdCartera());

        return entidad;
    }

    public static ComparendoCartera toLevel1Entity(ComparendoCarteraDTO dto, ComparendoCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }

        return entidad;
    }

    public static List<ComparendoCartera> toListLevel0Entity(List<ComparendoCarteraDTO> listDto) {
        List<ComparendoCartera> listEntidad = new ArrayList<ComparendoCartera>();
        for (ComparendoCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ComparendoCartera> toListLevel1Entity(List<ComparendoCarteraDTO> listDto) {
        List<ComparendoCartera> listEntidad = new ArrayList<ComparendoCartera>();
        for (ComparendoCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
