package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoResultadoEnvioSimitDTO;
import co.com.datatools.c2.entidades.TipoResultadoEnvioSimit;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:49:13 COT 2015
 */
public class TipoResultadoEnvioSimitHelper {
    // --- to DTO
    public static TipoResultadoEnvioSimitDTO toLevel0DTO(TipoResultadoEnvioSimit entidad) {
        TipoResultadoEnvioSimitDTO dto = new TipoResultadoEnvioSimitDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoResultadoEnvioSimitDTO toLevel1DTO(TipoResultadoEnvioSimit entidad) {
        TipoResultadoEnvioSimitDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoResultadoEnvioSimitDTO> toListLevel0DTO(List<TipoResultadoEnvioSimit> listEntidad) {
        List<TipoResultadoEnvioSimitDTO> listDto = new ArrayList<TipoResultadoEnvioSimitDTO>();
        for (TipoResultadoEnvioSimit entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoResultadoEnvioSimitDTO> toListLevel1DTO(List<TipoResultadoEnvioSimit> listEntidad) {
        List<TipoResultadoEnvioSimitDTO> listDto = new ArrayList<TipoResultadoEnvioSimitDTO>();
        for (TipoResultadoEnvioSimit entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoResultadoEnvioSimit toLevel0Entity(TipoResultadoEnvioSimitDTO dto, TipoResultadoEnvioSimit entidad) {
        if (null == entidad) {
            entidad = new TipoResultadoEnvioSimit();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());

        return entidad;
    }

    public static TipoResultadoEnvioSimit toLevel1Entity(TipoResultadoEnvioSimitDTO dto, TipoResultadoEnvioSimit entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoResultadoEnvioSimit> toListLevel0Entity(List<TipoResultadoEnvioSimitDTO> listDto) {
        List<TipoResultadoEnvioSimit> listEntidad = new ArrayList<TipoResultadoEnvioSimit>();
        for (TipoResultadoEnvioSimitDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoResultadoEnvioSimit> toListLevel1Entity(List<TipoResultadoEnvioSimitDTO> listDto) {
        List<TipoResultadoEnvioSimit> listEntidad = new ArrayList<TipoResultadoEnvioSimit>();
        for (TipoResultadoEnvioSimitDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
