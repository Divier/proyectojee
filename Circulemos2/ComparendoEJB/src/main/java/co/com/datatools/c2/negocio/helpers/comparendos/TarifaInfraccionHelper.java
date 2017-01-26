package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.TarifaInfraccionDTO;
import co.com.datatools.c2.entidades.Infraccion;
import co.com.datatools.c2.entidades.TarifaInfraccion;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 24 10:18:54 COT 2015
 */
public class TarifaInfraccionHelper {
    // --- to DTO
    public static TarifaInfraccionDTO toLevel0DTO(TarifaInfraccion entidad) {
        TarifaInfraccionDTO dto = new TarifaInfraccionDTO();
        dto.setIdTarifaInfraccion(entidad.getIdTarifaInfraccion());
        dto.setFechaConfirmacion(entidad.getFechaConfirmacion());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setFechaFinalVigencia(entidad.getFechaFinalVigencia());
        dto.setFechaInicioVigencia(entidad.getFechaInicioVigencia());
        dto.setPorcentajeDescuento(entidad.getPorcentajeDescuento());
        dto.setTarifaConfirmada(entidad.getTarifaConfirmada());
        dto.setValorCia(entidad.getValorCia());
        dto.setValorComparendo(entidad.getValorComparendo());
        dto.setValorDescuento(entidad.getValorDescuento());
        dto.setValorInfraccion(entidad.getValorInfraccion());

        return dto;
    }

    public static TarifaInfraccionDTO toLevel1DTO(TarifaInfraccion entidad) {
        TarifaInfraccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getInfraccion() != null)
            dto.setInfraccion(InfraccionHelper.toLevel0DTO(entidad.getInfraccion()));

        return dto;
    }

    public static List<TarifaInfraccionDTO> toListLevel0DTO(List<TarifaInfraccion> listEntidad) {
        List<TarifaInfraccionDTO> listDto = new ArrayList<TarifaInfraccionDTO>();
        for (TarifaInfraccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TarifaInfraccionDTO> toListLevel1DTO(List<TarifaInfraccion> listEntidad) {
        List<TarifaInfraccionDTO> listDto = new ArrayList<TarifaInfraccionDTO>();
        for (TarifaInfraccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TarifaInfraccion toLevel0Entity(TarifaInfraccionDTO dto, TarifaInfraccion entidad) {
        if (null == entidad) {
            entidad = new TarifaInfraccion();
        }
        entidad.setIdTarifaInfraccion(dto.getIdTarifaInfraccion());
        entidad.setFechaConfirmacion(dto.getFechaConfirmacion());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setFechaFinalVigencia(dto.getFechaFinalVigencia());
        entidad.setFechaInicioVigencia(dto.getFechaInicioVigencia());
        entidad.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        entidad.setTarifaConfirmada(dto.getTarifaConfirmada());
        entidad.setValorCia(dto.getValorCia());
        entidad.setValorComparendo(dto.getValorComparendo());
        entidad.setValorDescuento(dto.getValorDescuento());
        entidad.setValorInfraccion(dto.getValorInfraccion());

        return entidad;
    }

    public static TarifaInfraccion toLevel1Entity(TarifaInfraccionDTO dto, TarifaInfraccion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getInfraccion() != null) {
            entidad.setInfraccion(new Infraccion());
            entidad.getInfraccion().setId(dto.getInfraccion().getId());
        }

        return entidad;
    }

    public static List<TarifaInfraccion> toListLevel0Entity(List<TarifaInfraccionDTO> listDto) {
        List<TarifaInfraccion> listEntidad = new ArrayList<TarifaInfraccion>();
        for (TarifaInfraccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TarifaInfraccion> toListLevel1Entity(List<TarifaInfraccionDTO> listDto) {
        List<TarifaInfraccion> listEntidad = new ArrayList<TarifaInfraccion>();
        for (TarifaInfraccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
