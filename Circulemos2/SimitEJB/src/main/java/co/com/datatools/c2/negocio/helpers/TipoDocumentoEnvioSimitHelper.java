package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoDocumentoEnvioSimitDTO;
import co.com.datatools.c2.entidades.TipoDocumentoEnvioSimit;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:48:11 COT 2015
 */
public class TipoDocumentoEnvioSimitHelper {
    // --- to DTO
    public static TipoDocumentoEnvioSimitDTO toLevel0DTO(TipoDocumentoEnvioSimit entidad) {
        TipoDocumentoEnvioSimitDTO dto = new TipoDocumentoEnvioSimitDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoDocumentoEnvioSimitDTO toLevel1DTO(TipoDocumentoEnvioSimit entidad) {
        TipoDocumentoEnvioSimitDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoDocumentoEnvioSimitDTO> toListLevel0DTO(List<TipoDocumentoEnvioSimit> listEntidad) {
        List<TipoDocumentoEnvioSimitDTO> listDto = new ArrayList<TipoDocumentoEnvioSimitDTO>();
        for (TipoDocumentoEnvioSimit entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoDocumentoEnvioSimitDTO> toListLevel1DTO(List<TipoDocumentoEnvioSimit> listEntidad) {
        List<TipoDocumentoEnvioSimitDTO> listDto = new ArrayList<TipoDocumentoEnvioSimitDTO>();
        for (TipoDocumentoEnvioSimit entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoDocumentoEnvioSimit toLevel0Entity(TipoDocumentoEnvioSimitDTO dto, TipoDocumentoEnvioSimit entidad) {
        if (null == entidad) {
            entidad = new TipoDocumentoEnvioSimit();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoDocumentoEnvioSimit toLevel1Entity(TipoDocumentoEnvioSimitDTO dto, TipoDocumentoEnvioSimit entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoDocumentoEnvioSimit> toListLevel0Entity(List<TipoDocumentoEnvioSimitDTO> listDto) {
        List<TipoDocumentoEnvioSimit> listEntidad = new ArrayList<TipoDocumentoEnvioSimit>();
        for (TipoDocumentoEnvioSimitDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoDocumentoEnvioSimit> toListLevel1Entity(List<TipoDocumentoEnvioSimitDTO> listDto) {
        List<TipoDocumentoEnvioSimit> listEntidad = new ArrayList<TipoDocumentoEnvioSimit>();
        for (TipoDocumentoEnvioSimitDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
