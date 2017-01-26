package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.DocumentoFormularioDTO;
import co.com.datatools.c2.entidades.DocumentoFormulario;

/**
 * @author Generated
 * @version 3.0 - Wed Sep 02 10:20:38 COT 2015
 */
public class DocumentoFormularioHelper {
    // --- to DTO
    public static DocumentoFormularioDTO toLevel0DTO(DocumentoFormulario entidad) {
        DocumentoFormularioDTO dto = new DocumentoFormularioDTO();
        dto.setId(entidad.getId());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());
        dto.setIdDocumento(entidad.getIdDocumento());

        return dto;
    }

    public static DocumentoFormularioDTO toLevel1DTO(DocumentoFormulario entidad) {
        DocumentoFormularioDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<DocumentoFormularioDTO> toListLevel0DTO(List<DocumentoFormulario> listEntidad) {
        List<DocumentoFormularioDTO> listDto = new ArrayList<DocumentoFormularioDTO>();
        for (DocumentoFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DocumentoFormularioDTO> toListLevel1DTO(List<DocumentoFormulario> listEntidad) {
        List<DocumentoFormularioDTO> listDto = new ArrayList<DocumentoFormularioDTO>();
        for (DocumentoFormulario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DocumentoFormulario toLevel0Entity(DocumentoFormularioDTO dto, DocumentoFormulario entidad) {
        if (null == entidad) {
            entidad = new DocumentoFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());
        entidad.setIdDocumento(dto.getIdDocumento());

        return entidad;
    }

    public static DocumentoFormulario toLevel1Entity(DocumentoFormularioDTO dto, DocumentoFormulario entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<DocumentoFormulario> toListLevel0Entity(List<DocumentoFormularioDTO> listDto) {
        List<DocumentoFormulario> listEntidad = new ArrayList<DocumentoFormulario>();
        for (DocumentoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DocumentoFormulario> toListLevel1Entity(List<DocumentoFormularioDTO> listDto) {
        List<DocumentoFormulario> listEntidad = new ArrayList<DocumentoFormulario>();
        for (DocumentoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
