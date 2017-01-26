package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.TipoTransportePasajeroDTO;
import co.com.datatools.c2.entidades.TipoTransportePasajero;

/**
 * @author Generated
 * @version 3.0 - Fri Oct 09 17:20:06 COT 2015
 */
public class TipoTransportePasajeroHelper {
    // --- to DTO
    public static TipoTransportePasajeroDTO toLevel0DTO(TipoTransportePasajero entidad) {
        TipoTransportePasajeroDTO dto = new TipoTransportePasajeroDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

    public static TipoTransportePasajeroDTO toLevel1DTO(TipoTransportePasajero entidad) {
        TipoTransportePasajeroDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoTransportePasajeroDTO> toListLevel0DTO(List<TipoTransportePasajero> listEntidad) {
        List<TipoTransportePasajeroDTO> listDto = new ArrayList<TipoTransportePasajeroDTO>();
        for (TipoTransportePasajero entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoTransportePasajeroDTO> toListLevel1DTO(List<TipoTransportePasajero> listEntidad) {
        List<TipoTransportePasajeroDTO> listDto = new ArrayList<TipoTransportePasajeroDTO>();
        for (TipoTransportePasajero entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoTransportePasajero toLevel0Entity(TipoTransportePasajeroDTO dto, TipoTransportePasajero entidad) {
        if (null == entidad) {
            entidad = new TipoTransportePasajero();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getActivo());

        return entidad;
    }

    public static TipoTransportePasajero toLevel1Entity(TipoTransportePasajeroDTO dto, TipoTransportePasajero entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoTransportePasajero> toListLevel0Entity(List<TipoTransportePasajeroDTO> listDto) {
        List<TipoTransportePasajero> listEntidad = new ArrayList<TipoTransportePasajero>();
        for (TipoTransportePasajeroDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoTransportePasajero> toListLevel1Entity(List<TipoTransportePasajeroDTO> listDto) {
        List<TipoTransportePasajero> listEntidad = new ArrayList<TipoTransportePasajero>();
        for (TipoTransportePasajeroDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}