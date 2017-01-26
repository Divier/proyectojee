package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.TipoPropietarioDTO;
import co.com.datatools.c2.entidades.TipoPropietario;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 09:09:21 COT 2015
 */
public class TipoPropietarioHelper {
    // --- to DTO
    public static TipoPropietarioDTO toLevel0DTO(TipoPropietario entidad) {
        TipoPropietarioDTO dto = new TipoPropietarioDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());
        dto.setCodigo(entidad.getCodigo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoPropietarioDTO toLevel1DTO(TipoPropietario entidad) {
        TipoPropietarioDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoPropietarioDTO> toListLevel0DTO(List<TipoPropietario> listEntidad) {
        List<TipoPropietarioDTO> listDto = new ArrayList<TipoPropietarioDTO>();
        for (TipoPropietario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoPropietarioDTO> toListLevel1DTO(List<TipoPropietario> listEntidad) {
        List<TipoPropietarioDTO> listDto = new ArrayList<TipoPropietarioDTO>();
        for (TipoPropietario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoPropietario toLevel0Entity(TipoPropietarioDTO dto, TipoPropietario entidad) {
        if (null == entidad) {
            entidad = new TipoPropietario();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoPropietario toLevel1Entity(TipoPropietarioDTO dto, TipoPropietario entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoPropietario> toListLevel0Entity(List<TipoPropietarioDTO> listDto) {
        List<TipoPropietario> listEntidad = new ArrayList<TipoPropietario>();
        for (TipoPropietarioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoPropietario> toListLevel1Entity(List<TipoPropietarioDTO> listDto) {
        List<TipoPropietario> listEntidad = new ArrayList<TipoPropietario>();
        for (TipoPropietarioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
