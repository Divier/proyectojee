package co.com.datatools.c2.negocio.helpers.cargue;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cargue.DocumentoResultadoCargueDTO;
import co.com.datatools.c2.entidades.cargue.CargueArchivo;
import co.com.datatools.c2.entidades.cargue.DocumentoResultadoCargue;
import co.com.datatools.c2.entidades.cargue.TipoDocumentoResultadoCargue;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 22 17:56:50 COT 2016
 */
public class DocumentoResultadoCargueHelper {
    // --- to DTO
    public static DocumentoResultadoCargueDTO toLevel0DTO(DocumentoResultadoCargue entidad) {
        DocumentoResultadoCargueDTO dto = new DocumentoResultadoCargueDTO();
        dto.setId(entidad.getId());
        dto.setIdDocumentoResultado(entidad.getIdDocumentoResultado());

        return dto;
    }

    public static DocumentoResultadoCargueDTO toLevel1DTO(DocumentoResultadoCargue entidad) {
        DocumentoResultadoCargueDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoDocumentoResultadoCargue() != null)
            dto.setTipoDocumentoResultadoCargue(
                    TipoDocumentoResultadoCargueHelper.toLevel0DTO(entidad.getTipoDocumentoResultadoCargue()));
        if (entidad.getCargueArchivo() != null)
            dto.setCargueArchivo(CargueArchivoHelper.toLevel0DTO(entidad.getCargueArchivo()));

        return dto;
    }

    public static List<DocumentoResultadoCargueDTO> toListLevel0DTO(List<DocumentoResultadoCargue> listEntidad) {
        List<DocumentoResultadoCargueDTO> listDto = new ArrayList<DocumentoResultadoCargueDTO>();
        for (DocumentoResultadoCargue entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DocumentoResultadoCargueDTO> toListLevel1DTO(List<DocumentoResultadoCargue> listEntidad) {
        List<DocumentoResultadoCargueDTO> listDto = new ArrayList<DocumentoResultadoCargueDTO>();
        for (DocumentoResultadoCargue entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DocumentoResultadoCargue toLevel0Entity(DocumentoResultadoCargueDTO dto,
            DocumentoResultadoCargue entidad) {
        if (null == entidad) {
            entidad = new DocumentoResultadoCargue();
        }
        entidad.setId(dto.getId());
        entidad.setIdDocumentoResultado(dto.getIdDocumentoResultado());

        return entidad;
    }

    public static DocumentoResultadoCargue toLevel1Entity(DocumentoResultadoCargueDTO dto,
            DocumentoResultadoCargue entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoDocumentoResultadoCargue() != null) {
            entidad.setTipoDocumentoResultadoCargue(new TipoDocumentoResultadoCargue());
            entidad.getTipoDocumentoResultadoCargue().setId(dto.getTipoDocumentoResultadoCargue().getId());
        }
        if (dto.getCargueArchivo() != null) {
            entidad.setCargueArchivo(new CargueArchivo());
            entidad.getCargueArchivo().setId(dto.getCargueArchivo().getId());
        }

        return entidad;
    }

    public static List<DocumentoResultadoCargue> toListLevel0Entity(List<DocumentoResultadoCargueDTO> listDto) {
        List<DocumentoResultadoCargue> listEntidad = new ArrayList<DocumentoResultadoCargue>();
        for (DocumentoResultadoCargueDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DocumentoResultadoCargue> toListLevel1Entity(List<DocumentoResultadoCargueDTO> listDto) {
        List<DocumentoResultadoCargue> listEntidad = new ArrayList<DocumentoResultadoCargue>();
        for (DocumentoResultadoCargueDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
