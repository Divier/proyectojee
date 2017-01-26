package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.entidades.DocumentoProceso;
import co.com.datatools.c2.entidades.FirmaPersona;
import co.com.datatools.c2.entidades.TipoDocumentoProceso;
import co.com.datatools.c2.entidades.TrazabilidadProceso;
import co.com.datatools.c2.negocio.helpers.v2.FirmaPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class DocumentoProcesoHelper {
    // --- to DTO
    public static DocumentoProcesoDTO toLevel0DTO(DocumentoProceso entidad) {
        DocumentoProcesoDTO dto = new DocumentoProcesoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());
        dto.setIdComparendoProceso(entidad.getIdComparendoProceso());
        dto.setResponsableGeneracion(entidad.getResponsableGeneracion());

        return dto;
    }

    public static DocumentoProcesoDTO toLevel1DTO(DocumentoProceso entidad) {
        DocumentoProcesoDTO dto = toLevel0DTO(entidad);
        if (entidad.getTrazabilidadProceso() != null)
            dto.setTrazabilidadProceso(TrazabilidadProcesoHelper.toLevel0DTO(entidad.getTrazabilidadProceso()));
        if (entidad.getTipoDocumento() != null)
            dto.setTipoDocumento(TipoDocumentoProcesoHelper.toLevel0DTO(entidad.getTipoDocumento()));
        if (entidad.getFirma() != null)
            dto.setFirma(FirmaPersonaHelper.toLevel0DTO(entidad.getFirma()));

        return dto;
    }

    public static List<DocumentoProcesoDTO> toListLevel0DTO(List<DocumentoProceso> listEntidad) {
        List<DocumentoProcesoDTO> listDto = new ArrayList<DocumentoProcesoDTO>();
        for (DocumentoProceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DocumentoProcesoDTO> toListLevel1DTO(List<DocumentoProceso> listEntidad) {
        List<DocumentoProcesoDTO> listDto = new ArrayList<DocumentoProcesoDTO>();
        for (DocumentoProceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DocumentoProceso toLevel0Entity(DocumentoProcesoDTO dto, DocumentoProceso entidad) {
        if (null == entidad) {
            entidad = new DocumentoProceso();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());
        entidad.setIdComparendoProceso(dto.getIdComparendoProceso());
        entidad.setResponsableGeneracion(dto.getResponsableGeneracion());

        return entidad;
    }

    public static DocumentoProceso toLevel1Entity(DocumentoProcesoDTO dto, DocumentoProceso entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTrazabilidadProceso() != null) {
            entidad.setTrazabilidadProceso(new TrazabilidadProceso());
            entidad.getTrazabilidadProceso().setId(dto.getTrazabilidadProceso().getId());
        }
        if (dto.getTipoDocumento() != null) {
            entidad.setTipoDocumento(new TipoDocumentoProceso());
            entidad.getTipoDocumento().setId(dto.getTipoDocumento().getId());
        }
        if (dto.getFirma() != null) {
            entidad.setFirma(new FirmaPersona());
            entidad.getFirma().setId(dto.getFirma().getId());
        }

        return entidad;
    }

    public static List<DocumentoProceso> toListLevel0Entity(List<DocumentoProcesoDTO> listDto) {
        List<DocumentoProceso> listEntidad = new ArrayList<DocumentoProceso>();
        for (DocumentoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DocumentoProceso> toListLevel1Entity(List<DocumentoProcesoDTO> listDto) {
        List<DocumentoProceso> listEntidad = new ArrayList<DocumentoProceso>();
        for (DocumentoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
