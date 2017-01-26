package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.SaldoCarteraDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.SaldoCartera;
import co.com.datatools.c2.entidades.TipoSaldo;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:27:45 COT 2015
 */
public class SaldoCarteraHelper {
    // --- to DTO
    public static SaldoCarteraDTO toLevel0DTO(SaldoCartera entidad) {
        SaldoCarteraDTO dto = new SaldoCarteraDTO();
        dto.setId(entidad.getId());
        dto.setFechaCalculo(entidad.getFechaCalculo());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setSaldo(entidad.getSaldo());

        return dto;
    }

    public static SaldoCarteraDTO toLevel1DTO(SaldoCartera entidad) {
        SaldoCarteraDTO dto = toLevel0DTO(entidad);
        if (entidad.getCartera() != null)
            dto.setCartera(CarteraHelper.toLevel0DTO(entidad.getCartera()));
        if (entidad.getTipoSaldo() != null)
            dto.setTipoSaldo(TipoSaldoHelper.toLevel0DTO(entidad.getTipoSaldo()));

        return dto;
    }

    public static List<SaldoCarteraDTO> toListLevel0DTO(List<SaldoCartera> listEntidad) {
        List<SaldoCarteraDTO> listDto = new ArrayList<SaldoCarteraDTO>();
        for (SaldoCartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<SaldoCarteraDTO> toListLevel1DTO(List<SaldoCartera> listEntidad) {
        List<SaldoCarteraDTO> listDto = new ArrayList<SaldoCarteraDTO>();
        for (SaldoCartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static SaldoCartera toLevel0Entity(SaldoCarteraDTO dto, SaldoCartera entidad) {
        if (null == entidad) {
            entidad = new SaldoCartera();
        }
        entidad.setId(dto.getId());
        entidad.setFechaCalculo(dto.getFechaCalculo());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setSaldo(dto.getSaldo());

        return entidad;
    }

    public static SaldoCartera toLevel1Entity(SaldoCarteraDTO dto, SaldoCartera entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getCartera() != null) {
            entidad.setCartera(new Cartera());
            entidad.getCartera().setId(dto.getCartera().getId());
        }
        if (dto.getTipoSaldo() != null) {
            entidad.setTipoSaldo(new TipoSaldo());
            entidad.getTipoSaldo().setId(dto.getTipoSaldo().getId());
        }

        return entidad;
    }

    public static List<SaldoCartera> toListLevel0Entity(List<SaldoCarteraDTO> listDto) {
        List<SaldoCartera> listEntidad = new ArrayList<SaldoCartera>();
        for (SaldoCarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<SaldoCartera> toListLevel1Entity(List<SaldoCarteraDTO> listDto) {
        List<SaldoCartera> listEntidad = new ArrayList<SaldoCartera>();
        for (SaldoCarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
