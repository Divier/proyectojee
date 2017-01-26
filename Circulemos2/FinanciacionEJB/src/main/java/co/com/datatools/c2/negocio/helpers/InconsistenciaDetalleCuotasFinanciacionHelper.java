package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.InconsistenciaDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.entidades.InconsistenciaDetalleCuotasFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 11:29:50 COT 2016
 */
public class InconsistenciaDetalleCuotasFinanciacionHelper {
    // --- to DTO
    public static InconsistenciaDetalleCuotasFinanciacionDTO toLevel0DTO(InconsistenciaDetalleCuotasFinanciacion entidad) {
        InconsistenciaDetalleCuotasFinanciacionDTO dto = new InconsistenciaDetalleCuotasFinanciacionDTO();
        dto.setIdDetalleCuotasFinanciacion(entidad.getIdDetalleCuotasFinanciacion());
        dto.setNumeroCuota(entidad.getNumeroCuota());
        dto.setFechaPagoOportuno(entidad.getFechaPagoOportuno());
        dto.setValorCuota(entidad.getValorCuota());
        dto.setRecargoCuota(entidad.getRecargoCuota());

        return dto;
    }

    public static InconsistenciaDetalleCuotasFinanciacionDTO toLevel1DTO(InconsistenciaDetalleCuotasFinanciacion entidad) {
        InconsistenciaDetalleCuotasFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getInconsistenciaFinanciacion() != null)
            dto.setInconsistenciaFinanciacion(InconsistenciaFinanciacionHelper.toLevel0DTO(entidad
                    .getInconsistenciaFinanciacion()));

        return dto;
    }

    public static List<InconsistenciaDetalleCuotasFinanciacionDTO> toListLevel0DTO(
            List<InconsistenciaDetalleCuotasFinanciacion> listEntidad) {
        List<InconsistenciaDetalleCuotasFinanciacionDTO> listDto = new ArrayList<InconsistenciaDetalleCuotasFinanciacionDTO>();
        for (InconsistenciaDetalleCuotasFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<InconsistenciaDetalleCuotasFinanciacionDTO> toListLevel1DTO(
            List<InconsistenciaDetalleCuotasFinanciacion> listEntidad) {
        List<InconsistenciaDetalleCuotasFinanciacionDTO> listDto = new ArrayList<InconsistenciaDetalleCuotasFinanciacionDTO>();
        for (InconsistenciaDetalleCuotasFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static InconsistenciaDetalleCuotasFinanciacion toLevel0Entity(
            InconsistenciaDetalleCuotasFinanciacionDTO dto, InconsistenciaDetalleCuotasFinanciacion entidad) {
        if (null == entidad) {
            entidad = new InconsistenciaDetalleCuotasFinanciacion();
        }
        entidad.setIdDetalleCuotasFinanciacion(dto.getIdDetalleCuotasFinanciacion());
        entidad.setNumeroCuota(dto.getNumeroCuota());
        entidad.setFechaPagoOportuno(dto.getFechaPagoOportuno());
        entidad.setValorCuota(dto.getValorCuota());
        entidad.setRecargoCuota(dto.getRecargoCuota());

        return entidad;
    }

    public static InconsistenciaDetalleCuotasFinanciacion toLevel1Entity(
            InconsistenciaDetalleCuotasFinanciacionDTO dto, InconsistenciaDetalleCuotasFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getInconsistenciaFinanciacion() != null) {
            entidad.setInconsistenciaFinanciacion(new InconsistenciaFinanciacion());
            entidad.getInconsistenciaFinanciacion().setId(dto.getInconsistenciaFinanciacion().getId());
        }

        return entidad;
    }

    public static List<InconsistenciaDetalleCuotasFinanciacion> toListLevel0Entity(
            List<InconsistenciaDetalleCuotasFinanciacionDTO> listDto) {
        List<InconsistenciaDetalleCuotasFinanciacion> listEntidad = new ArrayList<InconsistenciaDetalleCuotasFinanciacion>();
        for (InconsistenciaDetalleCuotasFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<InconsistenciaDetalleCuotasFinanciacion> toListLevel1Entity(
            List<InconsistenciaDetalleCuotasFinanciacionDTO> listDto) {
        List<InconsistenciaDetalleCuotasFinanciacion> listEntidad = new ArrayList<InconsistenciaDetalleCuotasFinanciacion>();
        for (InconsistenciaDetalleCuotasFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
