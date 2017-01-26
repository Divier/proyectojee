package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleDocumentoMasivoDTO;
import co.com.datatools.c2.entidades.DetalleDocumentoMasivo;
import co.com.datatools.c2.entidades.DocumentoMasivo;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 16:06:56 COT 2016
 */
public class DetalleDocumentoMasivoHelper {
    // --- to DTO
    public static DetalleDocumentoMasivoDTO toLevel0DTO(DetalleDocumentoMasivo entidad) {
        DetalleDocumentoMasivoDTO dto = new DetalleDocumentoMasivoDTO();
        dto.setId(entidad.getId());
        dto.setIdDocumento(entidad.getIdDocumento());

        return dto;
    }

    public static DetalleDocumentoMasivoDTO toLevel1DTO(DetalleDocumentoMasivo entidad) {
        DetalleDocumentoMasivoDTO dto = toLevel0DTO(entidad);
        if (entidad.getDocumentoMasivo() != null)
            dto.setDocumentoMasivo(DocumentoMasivoHelper.toLevel0DTO(entidad.getDocumentoMasivo()));

        return dto;
    }

    public static List<DetalleDocumentoMasivoDTO> toListLevel0DTO(List<DetalleDocumentoMasivo> listEntidad) {
        List<DetalleDocumentoMasivoDTO> listDto = new ArrayList<DetalleDocumentoMasivoDTO>();
        for (DetalleDocumentoMasivo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleDocumentoMasivoDTO> toListLevel1DTO(List<DetalleDocumentoMasivo> listEntidad) {
        List<DetalleDocumentoMasivoDTO> listDto = new ArrayList<DetalleDocumentoMasivoDTO>();
        for (DetalleDocumentoMasivo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleDocumentoMasivo toLevel0Entity(DetalleDocumentoMasivoDTO dto, DetalleDocumentoMasivo entidad) {
        if (null == entidad) {
            entidad = new DetalleDocumentoMasivo();
        }
        entidad.setId(dto.getId());
        entidad.setIdDocumento(dto.getIdDocumento());

        return entidad;
    }

    public static DetalleDocumentoMasivo toLevel1Entity(DetalleDocumentoMasivoDTO dto, DetalleDocumentoMasivo entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDocumentoMasivo() != null) {
            entidad.setDocumentoMasivo(new DocumentoMasivo());
            entidad.getDocumentoMasivo().setId(dto.getDocumentoMasivo().getId());
        }

        return entidad;
    }

    public static List<DetalleDocumentoMasivo> toListLevel0Entity(List<DetalleDocumentoMasivoDTO> listDto) {
        List<DetalleDocumentoMasivo> listEntidad = new ArrayList<DetalleDocumentoMasivo>();
        for (DetalleDocumentoMasivoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleDocumentoMasivo> toListLevel1Entity(List<DetalleDocumentoMasivoDTO> listDto) {
        List<DetalleDocumentoMasivo> listEntidad = new ArrayList<DetalleDocumentoMasivo>();
        for (DetalleDocumentoMasivoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
