package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.DocumentoComparendoDTO;
import co.com.datatools.c2.entidades.DocumentoComparendo;
import co.com.datatools.c2.entidades.TrazabilidadComparendo;

/**
 * @author Generated
 * @version 3.0 - Thu Jul 14 11:52:49 COT 2016
 */
public class DocumentoComparendoHelper {
    // --- to DTO
    public static DocumentoComparendoDTO toLevel0DTO(DocumentoComparendo entidad) {
        DocumentoComparendoDTO dto = new DocumentoComparendoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());

        return dto;
    }

    public static DocumentoComparendoDTO toLevel1DTO(DocumentoComparendo entidad) {
        DocumentoComparendoDTO dto = toLevel0DTO(entidad);
        if (entidad.getTrazabilidadComparendo() != null)
            dto.setTrazabilidadComparendo(
                    TrazabilidadComparendoHelper.toLevel0DTO(entidad.getTrazabilidadComparendo()));

        return dto;
    }

    public static List<DocumentoComparendoDTO> toListLevel0DTO(List<DocumentoComparendo> listEntidad) {
        List<DocumentoComparendoDTO> listDto = new ArrayList<DocumentoComparendoDTO>();
        for (DocumentoComparendo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DocumentoComparendoDTO> toListLevel1DTO(List<DocumentoComparendo> listEntidad) {
        List<DocumentoComparendoDTO> listDto = new ArrayList<DocumentoComparendoDTO>();
        for (DocumentoComparendo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DocumentoComparendo toLevel0Entity(DocumentoComparendoDTO dto, DocumentoComparendo entidad) {
        if (null == entidad) {
            entidad = new DocumentoComparendo();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());

        return entidad;
    }

    public static DocumentoComparendo toLevel1Entity(DocumentoComparendoDTO dto, DocumentoComparendo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTrazabilidadComparendo() != null) {
            entidad.setTrazabilidadComparendo(new TrazabilidadComparendo());
            entidad.getTrazabilidadComparendo().setId(dto.getTrazabilidadComparendo().getId());
        }

        return entidad;
    }

    public static List<DocumentoComparendo> toListLevel0Entity(List<DocumentoComparendoDTO> listDto) {
        List<DocumentoComparendo> listEntidad = new ArrayList<DocumentoComparendo>();
        for (DocumentoComparendoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DocumentoComparendo> toListLevel1Entity(List<DocumentoComparendoDTO> listDto) {
        List<DocumentoComparendo> listEntidad = new ArrayList<DocumentoComparendo>();
        for (DocumentoComparendoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
