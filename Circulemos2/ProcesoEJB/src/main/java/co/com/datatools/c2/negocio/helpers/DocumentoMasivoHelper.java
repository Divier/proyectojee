package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DocumentoMasivoDTO;
import co.com.datatools.c2.entidades.DocumentoMasivo;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class DocumentoMasivoHelper {
    // --- to DTO
    public static DocumentoMasivoDTO toLevel0DTO(DocumentoMasivo entidad) {
        DocumentoMasivoDTO dto = new DocumentoMasivoDTO();
        dto.setId(entidad.getId());
        dto.setFechaSolicitud(entidad.getFechaSolicitud());
        dto.setLimiteDocumentos(entidad.getLimiteDocumentos());
        dto.setCantidadDocumentos(entidad.getCantidadDocumentos());
        dto.setRutaGeneracion(entidad.getRutaGeneracion());
        dto.setFechaProcesado(entidad.getFechaProcesado());
        dto.setIdArchivoGenerado(entidad.getIdArchivoGenerado());

        return dto;
    }

    public static DocumentoMasivoDTO toLevel1DTO(DocumentoMasivo entidad) {
        DocumentoMasivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));

        return dto;
    }

    public static List<DocumentoMasivoDTO> toListLevel0DTO(List<DocumentoMasivo> listEntidad) {
        List<DocumentoMasivoDTO> listDto = new ArrayList<DocumentoMasivoDTO>();
        for (DocumentoMasivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DocumentoMasivoDTO> toListLevel1DTO(List<DocumentoMasivo> listEntidad) {
        List<DocumentoMasivoDTO> listDto = new ArrayList<DocumentoMasivoDTO>();
        for (DocumentoMasivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DocumentoMasivo toLevel0Entity(DocumentoMasivoDTO dto, DocumentoMasivo entidad) {
        if (null == entidad) {
            entidad = new DocumentoMasivo();
        }
        entidad.setId(dto.getId());
        entidad.setFechaSolicitud(dto.getFechaSolicitud());
        entidad.setLimiteDocumentos(dto.getLimiteDocumentos());
        entidad.setCantidadDocumentos(dto.getCantidadDocumentos());
        entidad.setRutaGeneracion(dto.getRutaGeneracion());
        entidad.setFechaProcesado(dto.getFechaProcesado());
        entidad.setIdArchivoGenerado(dto.getIdArchivoGenerado());

        return entidad;
    }

    public static DocumentoMasivo toLevel1Entity(DocumentoMasivoDTO dto, DocumentoMasivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getUsuario() != null) {
            entidad.setUsuario(UsuarioPersonaHelper.toLevel0Entity(dto.getUsuario(), null));
        }

        return entidad;
    }

    public static List<DocumentoMasivo> toListLevel0Entity(List<DocumentoMasivoDTO> listDto) {
        List<DocumentoMasivo> listEntidad = new ArrayList<DocumentoMasivo>();
        for (DocumentoMasivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DocumentoMasivo> toListLevel1Entity(List<DocumentoMasivoDTO> listDto) {
        List<DocumentoMasivo> listEntidad = new ArrayList<DocumentoMasivo>();
        for (DocumentoMasivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
