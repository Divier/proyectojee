package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoDocumentoProcesoDTO;
import co.com.datatools.c2.entidades.TipoDocumentoProceso;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class TipoDocumentoProcesoHelper {
    // --- to DTO
    public static TipoDocumentoProcesoDTO toLevel0DTO(TipoDocumentoProceso entidad) {
        TipoDocumentoProcesoDTO dto = new TipoDocumentoProcesoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoDocumentoProcesoDTO toLevel1DTO(TipoDocumentoProceso entidad) {
        TipoDocumentoProcesoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoDocumentoProcesoDTO> toListLevel0DTO(List<TipoDocumentoProceso> listEntidad) {
        List<TipoDocumentoProcesoDTO> listDto = new ArrayList<TipoDocumentoProcesoDTO>();
        for (TipoDocumentoProceso entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoDocumentoProcesoDTO> toListLevel1DTO(List<TipoDocumentoProceso> listEntidad) {
        List<TipoDocumentoProcesoDTO> listDto = new ArrayList<TipoDocumentoProcesoDTO>();
        for (TipoDocumentoProceso entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoDocumentoProceso toLevel0Entity(TipoDocumentoProcesoDTO dto, TipoDocumentoProceso entidad) {
        if (null == entidad) {
            entidad = new TipoDocumentoProceso();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoDocumentoProceso toLevel1Entity(TipoDocumentoProcesoDTO dto, TipoDocumentoProceso entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoDocumentoProceso> toListLevel0Entity(List<TipoDocumentoProcesoDTO> listDto) {
        List<TipoDocumentoProceso> listEntidad = new ArrayList<TipoDocumentoProceso>();
        for (TipoDocumentoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoDocumentoProceso> toListLevel1Entity(List<TipoDocumentoProcesoDTO> listDto) {
        List<TipoDocumentoProceso> listEntidad = new ArrayList<TipoDocumentoProceso>();
        for (TipoDocumentoProcesoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
