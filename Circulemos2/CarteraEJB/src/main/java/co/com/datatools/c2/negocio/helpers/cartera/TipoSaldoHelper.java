package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.TipoSaldoDTO;
import co.com.datatools.c2.entidades.TipoSaldo;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:28:23 COT 2015
 */
public class TipoSaldoHelper {
    // --- to DTO
    public static TipoSaldoDTO toLevel0DTO(TipoSaldo entidad) {
        TipoSaldoDTO dto = new TipoSaldoDTO();
        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setNombre(entidad.getNombre());

        return dto;
    }

    public static TipoSaldoDTO toLevel1DTO(TipoSaldo entidad) {
        TipoSaldoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<TipoSaldoDTO> toListLevel0DTO(List<TipoSaldo> listEntidad) {
        List<TipoSaldoDTO> listDto = new ArrayList<TipoSaldoDTO>();
        for (TipoSaldo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoSaldoDTO> toListLevel1DTO(List<TipoSaldo> listEntidad) {
        List<TipoSaldoDTO> listDto = new ArrayList<TipoSaldoDTO>();
        for (TipoSaldo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoSaldo toLevel0Entity(TipoSaldoDTO dto, TipoSaldo entidad) {
        if (null == entidad) {
            entidad = new TipoSaldo();
        }
        entidad.setId(dto.getId());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static TipoSaldo toLevel1Entity(TipoSaldoDTO dto, TipoSaldo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<TipoSaldo> toListLevel0Entity(List<TipoSaldoDTO> listDto) {
        List<TipoSaldo> listEntidad = new ArrayList<TipoSaldo>();
        for (TipoSaldoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoSaldo> toListLevel1Entity(List<TipoSaldoDTO> listDto) {
        List<TipoSaldo> listEntidad = new ArrayList<TipoSaldo>();
        for (TipoSaldoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
