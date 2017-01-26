package co.com.datatools.c2.negocio.helpers.cartera;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.cartera.CarteraDTO;
import co.com.datatools.c2.entidades.Cartera;
import co.com.datatools.c2.entidades.EstadoObligacion;
import co.com.datatools.c2.entidades.TipoObligacion;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 21 09:26:31 COT 2015
 */
public class CarteraHelper {
    // --- to DTO
    public static CarteraDTO toLevel0DTO(Cartera entidad) {
        CarteraDTO dto = new CarteraDTO();
        dto.setId(entidad.getId());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setIdDeudor(entidad.getIdDeudor());
        dto.setNombre(entidad.getNombre());
        dto.setSaldoCapital(entidad.getSaldoCapital());
        dto.setSaldoInteres(entidad.getSaldoInteres());
        dto.setSaldoCostasProcesales(entidad.getSaldoCostasProcesales());

        return dto;
    }

    public static CarteraDTO toLevel1DTO(Cartera entidad) {
        CarteraDTO dto = toLevel0DTO(entidad);
        if (entidad.getEstadoObligacion() != null)
            dto.setEstadoObligacion(EstadoObligacionHelper.toLevel0DTO(entidad.getEstadoObligacion()));
        if (entidad.getTipoObligacion() != null)
            dto.setTipoObligacion(TipoObligacionHelper.toLevel0DTO(entidad.getTipoObligacion()));

        return dto;
    }

    public static List<CarteraDTO> toListLevel0DTO(List<Cartera> listEntidad) {
        List<CarteraDTO> listDto = new ArrayList<CarteraDTO>();
        for (Cartera entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CarteraDTO> toListLevel1DTO(List<Cartera> listEntidad) {
        List<CarteraDTO> listDto = new ArrayList<CarteraDTO>();
        for (Cartera entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Cartera toLevel0Entity(CarteraDTO dto, Cartera entidad) {
        if (null == entidad) {
            entidad = new Cartera();
        }
        entidad.setId(dto.getId());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setIdDeudor(dto.getIdDeudor());
        entidad.setNombre(dto.getNombre());
        entidad.setSaldoCapital(dto.getSaldoCapital());
        entidad.setSaldoInteres(dto.getSaldoInteres());
        entidad.setSaldoCostasProcesales(dto.getSaldoCostasProcesales());

        return entidad;
    }

    public static Cartera toLevel1Entity(CarteraDTO dto, Cartera entidad) {
        entidad = toLevel0Entity(dto, entidad);

        if (dto.getEstadoObligacion() != null) {
            entidad.setEstadoObligacion(new EstadoObligacion());
            entidad.getEstadoObligacion().setId(dto.getEstadoObligacion().getId());
        }
        if (dto.getTipoObligacion() != null) {
            entidad.setTipoObligacion(new TipoObligacion());
            entidad.getTipoObligacion().setId(dto.getTipoObligacion().getId());
        }

        return entidad;
    }

    public static List<Cartera> toListLevel0Entity(List<CarteraDTO> listDto) {
        List<Cartera> listEntidad = new ArrayList<Cartera>();
        for (CarteraDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Cartera> toListLevel1Entity(List<CarteraDTO> listDto) {
        List<Cartera> listEntidad = new ArrayList<Cartera>();
        for (CarteraDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
