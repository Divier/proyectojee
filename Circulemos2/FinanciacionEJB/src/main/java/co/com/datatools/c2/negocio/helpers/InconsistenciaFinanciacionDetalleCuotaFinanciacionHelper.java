package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO;
import co.com.datatools.c2.entidades.ErrorInconsistenciaFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaDetalleCuotasFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaDetalleFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaFinanciacionDetalleCuotaFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 11:30:32 COT 2016
 */
public class InconsistenciaFinanciacionDetalleCuotaFinanciacionHelper {
    // --- to DTO
    public static InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO toLevel0DTO(
            InconsistenciaFinanciacionDetalleCuotaFinanciacion entidad) {
        InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO dto = new InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO();
        dto.setId(entidad.getId());

        return dto;
    }

    public static InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO toLevel1DTO(
            InconsistenciaFinanciacionDetalleCuotaFinanciacion entidad) {
        InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getInconsistenciaDetalleCuotasFinanciacion() != null)
            dto.setInconsistenciaDetalleCuotasFinanciacion(InconsistenciaDetalleCuotasFinanciacionHelper
                    .toLevel0DTO(entidad.getInconsistenciaDetalleCuotasFinanciacion()));
        if (entidad.getInconsistenciaFinanciacion() != null)
            dto.setInconsistenciaFinanciacion(InconsistenciaFinanciacionHelper.toLevel0DTO(entidad
                    .getInconsistenciaFinanciacion()));
        if (entidad.getInconsistenciaDetalleFinanciacion() != null)
            dto.setInconsistenciaDetalleFinanciacion(InconsistenciaDetalleFinanciacionHelper.toLevel0DTO(entidad
                    .getInconsistenciaDetalleFinanciacion()));
        if (entidad.getErrorInconsistenciaFinanciacion() != null)
            dto.setErrorInconsistenciaFinanciacion(ErrorInconsistenciaFinanciacionHelper.toLevel0DTO(entidad
                    .getErrorInconsistenciaFinanciacion()));

        return dto;
    }

    public static List<InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO> toListLevel0DTO(
            List<InconsistenciaFinanciacionDetalleCuotaFinanciacion> listEntidad) {
        List<InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO> listDto = new ArrayList<InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO>();
        for (InconsistenciaFinanciacionDetalleCuotaFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO> toListLevel1DTO(
            List<InconsistenciaFinanciacionDetalleCuotaFinanciacion> listEntidad) {
        List<InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO> listDto = new ArrayList<InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO>();
        for (InconsistenciaFinanciacionDetalleCuotaFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static InconsistenciaFinanciacionDetalleCuotaFinanciacion toLevel0Entity(
            InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO dto,
            InconsistenciaFinanciacionDetalleCuotaFinanciacion entidad) {
        if (null == entidad) {
            entidad = new InconsistenciaFinanciacionDetalleCuotaFinanciacion();
        }
        entidad.setId(dto.getId());

        return entidad;
    }

    public static InconsistenciaFinanciacionDetalleCuotaFinanciacion toLevel1Entity(
            InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO dto,
            InconsistenciaFinanciacionDetalleCuotaFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getInconsistenciaDetalleCuotasFinanciacion() != null) {
            entidad.setInconsistenciaDetalleCuotasFinanciacion(new InconsistenciaDetalleCuotasFinanciacion());
            entidad.getInconsistenciaDetalleCuotasFinanciacion().setIdDetalleCuotasFinanciacion(
                    dto.getInconsistenciaDetalleCuotasFinanciacion().getIdDetalleCuotasFinanciacion());
        }
        if (dto.getInconsistenciaFinanciacion() != null) {
            entidad.setInconsistenciaFinanciacion(new InconsistenciaFinanciacion());
            entidad.getInconsistenciaFinanciacion().setId(dto.getInconsistenciaFinanciacion().getId());
        }
        if (dto.getInconsistenciaDetalleFinanciacion() != null) {
            entidad.setInconsistenciaDetalleFinanciacion(new InconsistenciaDetalleFinanciacion());
            entidad.getInconsistenciaDetalleFinanciacion().setIdDetalleFinanciacion(
                    dto.getInconsistenciaDetalleFinanciacion().getIdDetalleFinanciacion());
        }
        if (dto.getErrorInconsistenciaFinanciacion() != null) {
            entidad.setErrorInconsistenciaFinanciacion(new ErrorInconsistenciaFinanciacion());
            entidad.getErrorInconsistenciaFinanciacion().setId(dto.getErrorInconsistenciaFinanciacion().getId());
        }

        return entidad;
    }

    public static List<InconsistenciaFinanciacionDetalleCuotaFinanciacion> toListLevel0Entity(
            List<InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO> listDto) {
        List<InconsistenciaFinanciacionDetalleCuotaFinanciacion> listEntidad = new ArrayList<InconsistenciaFinanciacionDetalleCuotaFinanciacion>();
        for (InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<InconsistenciaFinanciacionDetalleCuotaFinanciacion> toListLevel1Entity(
            List<InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO> listDto) {
        List<InconsistenciaFinanciacionDetalleCuotaFinanciacion> listEntidad = new ArrayList<InconsistenciaFinanciacionDetalleCuotaFinanciacion>();
        for (InconsistenciaFinanciacionDetalleCuotaFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
