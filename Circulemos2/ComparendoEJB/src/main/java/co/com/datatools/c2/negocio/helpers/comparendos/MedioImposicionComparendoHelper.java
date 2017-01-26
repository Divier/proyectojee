package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.MedioImposicionComparendoDTO;
import co.com.datatools.c2.entidades.MedioImposicionComparendo;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 13 08:51:14 COT 2015
 */
public class MedioImposicionComparendoHelper {
    // --- to DTO
    public static MedioImposicionComparendoDTO toLevel0DTO(MedioImposicionComparendo entidad) {
        MedioImposicionComparendoDTO dto = new MedioImposicionComparendoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setActivo(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static MedioImposicionComparendoDTO toLevel1DTO(MedioImposicionComparendo entidad) {
        MedioImposicionComparendoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<MedioImposicionComparendoDTO> toListLevel0DTO(List<MedioImposicionComparendo> listEntidad) {
        List<MedioImposicionComparendoDTO> listDto = new ArrayList<MedioImposicionComparendoDTO>();
        for (MedioImposicionComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<MedioImposicionComparendoDTO> toListLevel1DTO(List<MedioImposicionComparendo> listEntidad) {
        List<MedioImposicionComparendoDTO> listDto = new ArrayList<MedioImposicionComparendoDTO>();
        for (MedioImposicionComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static MedioImposicionComparendo toLevel0Entity(MedioImposicionComparendoDTO dto,
            MedioImposicionComparendo entidad) {
        if (null == entidad) {
            entidad = new MedioImposicionComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getActivo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static MedioImposicionComparendo toLevel1Entity(MedioImposicionComparendoDTO dto,
            MedioImposicionComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<MedioImposicionComparendo> toListLevel0Entity(List<MedioImposicionComparendoDTO> listDto) {
        List<MedioImposicionComparendo> listEntidad = new ArrayList<MedioImposicionComparendo>();
        for (MedioImposicionComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<MedioImposicionComparendo> toListLevel1Entity(List<MedioImposicionComparendoDTO> listDto) {
        List<MedioImposicionComparendo> listEntidad = new ArrayList<MedioImposicionComparendo>();
        for (MedioImposicionComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}