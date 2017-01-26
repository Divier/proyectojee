package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.financiacion.ItDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.entidades.financiacion.ItDetalleCuotasFinanciacion;
import co.com.datatools.c2.entidades.financiacion.ItFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Tue May 17 16:42:11 COT 2016
 */
public class ItDetalleCuotasFinanciacionHelper {
    // --- to DTO
    public static ItDetalleCuotasFinanciacionDTO toLevel0DTO(ItDetalleCuotasFinanciacion entidad) {
        ItDetalleCuotasFinanciacionDTO dto = new ItDetalleCuotasFinanciacionDTO();
        dto.setIdDetalleCuotasFinanciacion(entidad.getIdDetalleCuotasFinanciacion());
        dto.setNumeroCuota(entidad.getNumeroCuota());
        dto.setFechaPagoOportuno(entidad.getFechaPagoOportuno());
        dto.setValorCuota(entidad.getValorCuota());
        dto.setRecargoCuota(entidad.getRecargoCuota());

        return dto;
    }

    public static ItDetalleCuotasFinanciacionDTO toLevel1DTO(ItDetalleCuotasFinanciacion entidad) {
        ItDetalleCuotasFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getItFinanciacion() != null)
            dto.setItFinanciacion(ItFinanciacionHelper.toLevel0DTO(entidad.getItFinanciacion()));

        return dto;
    }

    public static List<ItDetalleCuotasFinanciacionDTO> toListLevel0DTO(List<ItDetalleCuotasFinanciacion> listEntidad) {
        List<ItDetalleCuotasFinanciacionDTO> listDto = new ArrayList<ItDetalleCuotasFinanciacionDTO>();
        for (ItDetalleCuotasFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ItDetalleCuotasFinanciacionDTO> toListLevel1DTO(List<ItDetalleCuotasFinanciacion> listEntidad) {
        List<ItDetalleCuotasFinanciacionDTO> listDto = new ArrayList<ItDetalleCuotasFinanciacionDTO>();
        for (ItDetalleCuotasFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ItDetalleCuotasFinanciacion toLevel0Entity(ItDetalleCuotasFinanciacionDTO dto,
            ItDetalleCuotasFinanciacion entidad) {
        if (null == entidad) {
            entidad = new ItDetalleCuotasFinanciacion();
        }
        entidad.setIdDetalleCuotasFinanciacion(dto.getIdDetalleCuotasFinanciacion());
        entidad.setNumeroCuota(dto.getNumeroCuota());
        entidad.setFechaPagoOportuno(dto.getFechaPagoOportuno());
        entidad.setValorCuota(dto.getValorCuota());
        entidad.setRecargoCuota(dto.getRecargoCuota());

        return entidad;
    }

    public static ItDetalleCuotasFinanciacion toLevel1Entity(ItDetalleCuotasFinanciacionDTO dto,
            ItDetalleCuotasFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getItFinanciacion() != null) {
            entidad.setItFinanciacion(new ItFinanciacion());
            entidad.getItFinanciacion().setNumeroFinanciacion(dto.getItFinanciacion().getNumeroFinanciacion());
        }

        return entidad;
    }

    public static List<ItDetalleCuotasFinanciacion> toListLevel0Entity(List<ItDetalleCuotasFinanciacionDTO> listDto) {
        List<ItDetalleCuotasFinanciacion> listEntidad = new ArrayList<ItDetalleCuotasFinanciacion>();
        for (ItDetalleCuotasFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ItDetalleCuotasFinanciacion> toListLevel1Entity(List<ItDetalleCuotasFinanciacionDTO> listDto) {
        List<ItDetalleCuotasFinanciacion> listEntidad = new ArrayList<ItDetalleCuotasFinanciacion>();
        for (ItDetalleCuotasFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
