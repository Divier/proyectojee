package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.entidades.DetalleFinanciacion;
import co.com.datatools.c2.entidades.Financiacion;

/**
 * @author Generated
 * @version 3.0 - Fri May 06 12:04:48 COT 2016
 */
public class DetalleFinanciacionHelper {
    // --- to DTO
    public static DetalleFinanciacionDTO toLevel0DTO(DetalleFinanciacion entidad) {
        DetalleFinanciacionDTO dto = new DetalleFinanciacionDTO();
        dto.setId(entidad.getId());
        dto.setNumeroCuota(entidad.getNumeroCuota());
        dto.setValorTotal(entidad.getValorTotal());
        dto.setFechaPagoOportuno(entidad.getFechaPagoOportuno());
        dto.setValorCapital(entidad.getValorCapital());
        dto.setValorIntereses(entidad.getValorIntereses());
        dto.setIdDocumento(entidad.getIdDocumento());
        dto.setNumeroVolante(entidad.getNumeroVolante());
        dto.setFechaPago(entidad.getFechaPago());
        dto.setValorSaldoObligacion(entidad.getValorSaldoObligacion());

        return dto;
    }

    public static DetalleFinanciacionDTO toLevel1DTO(DetalleFinanciacion entidad) {
        DetalleFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getFinanciacion() != null)
            dto.setFinanciacion(FinanciacionHelper.toLevel0DTO(entidad.getFinanciacion()));

        return dto;
    }

    public static List<DetalleFinanciacionDTO> toListLevel0DTO(List<DetalleFinanciacion> listEntidad) {
        List<DetalleFinanciacionDTO> listDto = new ArrayList<DetalleFinanciacionDTO>();
        for (DetalleFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleFinanciacionDTO> toListLevel1DTO(List<DetalleFinanciacion> listEntidad) {
        List<DetalleFinanciacionDTO> listDto = new ArrayList<DetalleFinanciacionDTO>();
        for (DetalleFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleFinanciacion toLevel0Entity(DetalleFinanciacionDTO dto, DetalleFinanciacion entidad) {
        if (null == entidad) {
            entidad = new DetalleFinanciacion();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroCuota(dto.getNumeroCuota());
        entidad.setValorTotal(dto.getValorTotal());
        entidad.setFechaPagoOportuno(dto.getFechaPagoOportuno());
        entidad.setValorCapital(dto.getValorCapital());
        entidad.setValorIntereses(dto.getValorIntereses());
        entidad.setIdDocumento(dto.getIdDocumento());
        entidad.setNumeroVolante(dto.getNumeroVolante());
        entidad.setFechaPago(dto.getFechaPago());
        entidad.setValorSaldoObligacion(dto.getValorSaldoObligacion());

        return entidad;
    }

    public static DetalleFinanciacion toLevel1Entity(DetalleFinanciacionDTO dto, DetalleFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getFinanciacion() != null) {
            entidad.setFinanciacion(new Financiacion());
            entidad.getFinanciacion().setId(dto.getFinanciacion().getId());
        }

        return entidad;
    }

    public static List<DetalleFinanciacion> toListLevel0Entity(List<DetalleFinanciacionDTO> listDto) {
        List<DetalleFinanciacion> listEntidad = new ArrayList<DetalleFinanciacion>();
        for (DetalleFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleFinanciacion> toListLevel1Entity(List<DetalleFinanciacionDTO> listDto) {
        List<DetalleFinanciacion> listEntidad = new ArrayList<DetalleFinanciacion>();
        for (DetalleFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
