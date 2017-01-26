package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.TipoCalculoCuotaDTO;
import co.com.datatools.c2.entidades.TipoCalculoCuota;

/**
 * @author Generated
 * @version 3.0 - Wed Jun 08 10:07:49 COT 2016
 */
public class TipoCalculoCuotaHelper {
    // --- to DTO
    public static TipoCalculoCuotaDTO toLevel0DTO(TipoCalculoCuota entidad) {
        TipoCalculoCuotaDTO dto = new TipoCalculoCuotaDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setEstado(entidad.getActivo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setSigla(entidad.getSigla());

        return dto;
    }

    public static TipoCalculoCuotaDTO toLevel1DTO(TipoCalculoCuota entidad) {
        TipoCalculoCuotaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoCalculoCuotaDTO> toListLevel0DTO(List<TipoCalculoCuota> listEntidad) {
        List<TipoCalculoCuotaDTO> listDto = new ArrayList<TipoCalculoCuotaDTO>();
        for (TipoCalculoCuota entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoCalculoCuotaDTO> toListLevel1DTO(List<TipoCalculoCuota> listEntidad) {
        List<TipoCalculoCuotaDTO> listDto = new ArrayList<TipoCalculoCuotaDTO>();
        for (TipoCalculoCuota entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoCalculoCuota toLevel0Entity(TipoCalculoCuotaDTO dto, TipoCalculoCuota entidad) {
        if (null == entidad) {
            entidad = new TipoCalculoCuota();
        }
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setId(dto.getId());
        entidad.setActivo(dto.getEstado());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setSigla(dto.getSigla());
        return entidad;
    }

    public static TipoCalculoCuota toLevel1Entity(TipoCalculoCuotaDTO dto, TipoCalculoCuota entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoCalculoCuota> toListLevel0Entity(List<TipoCalculoCuotaDTO> listDto) {
        List<TipoCalculoCuota> listEntidad = new ArrayList<TipoCalculoCuota>();
        for (TipoCalculoCuotaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoCalculoCuota> toListLevel1Entity(List<TipoCalculoCuotaDTO> listDto) {
        List<TipoCalculoCuota> listEntidad = new ArrayList<TipoCalculoCuota>();
        for (TipoCalculoCuotaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
