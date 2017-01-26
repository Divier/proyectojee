package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.InconsistenciaDetalleFinanciacionDTO;
import co.com.datatools.c2.entidades.InconsistenciaDetalleFinanciacion;
import co.com.datatools.c2.entidades.InconsistenciaFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 11:29:19 COT 2016
 */
public class InconsistenciaDetalleFinanciacionHelper {
    // --- to DTO
    public static InconsistenciaDetalleFinanciacionDTO toLevel0DTO(InconsistenciaDetalleFinanciacion entidad) {
        InconsistenciaDetalleFinanciacionDTO dto = new InconsistenciaDetalleFinanciacionDTO();
        dto.setIdDetalleFinanciacion(entidad.getIdDetalleFinanciacion());
        dto.setFechaObligacion(entidad.getFechaObligacion());
        dto.setObligacion(entidad.getObligacion());
        dto.setTipoObligacion(entidad.getTipoObligacion());
        dto.setValorObligacion(entidad.getValorObligacion());

        return dto;
    }

    public static InconsistenciaDetalleFinanciacionDTO toLevel1DTO(InconsistenciaDetalleFinanciacion entidad) {
        InconsistenciaDetalleFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getInconsistenciaFinanciacion() != null)
            dto.setInconsistenciaFinanciacion(InconsistenciaFinanciacionHelper.toLevel0DTO(entidad
                    .getInconsistenciaFinanciacion()));

        return dto;
    }

    public static List<InconsistenciaDetalleFinanciacionDTO> toListLevel0DTO(
            List<InconsistenciaDetalleFinanciacion> listEntidad) {
        List<InconsistenciaDetalleFinanciacionDTO> listDto = new ArrayList<InconsistenciaDetalleFinanciacionDTO>();
        for (InconsistenciaDetalleFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<InconsistenciaDetalleFinanciacionDTO> toListLevel1DTO(
            List<InconsistenciaDetalleFinanciacion> listEntidad) {
        List<InconsistenciaDetalleFinanciacionDTO> listDto = new ArrayList<InconsistenciaDetalleFinanciacionDTO>();
        for (InconsistenciaDetalleFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static InconsistenciaDetalleFinanciacion toLevel0Entity(InconsistenciaDetalleFinanciacionDTO dto,
            InconsistenciaDetalleFinanciacion entidad) {
        if (null == entidad) {
            entidad = new InconsistenciaDetalleFinanciacion();
        }
        entidad.setIdDetalleFinanciacion(dto.getIdDetalleFinanciacion());
        entidad.setFechaObligacion(dto.getFechaObligacion());
        entidad.setObligacion(dto.getObligacion());
        entidad.setTipoObligacion(dto.getTipoObligacion());
        entidad.setValorObligacion(dto.getValorObligacion());

        return entidad;
    }

    public static InconsistenciaDetalleFinanciacion toLevel1Entity(InconsistenciaDetalleFinanciacionDTO dto,
            InconsistenciaDetalleFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getInconsistenciaFinanciacion() != null) {
            entidad.setInconsistenciaFinanciacion(new InconsistenciaFinanciacion());
            entidad.getInconsistenciaFinanciacion().setId(dto.getInconsistenciaFinanciacion().getId());
        }

        return entidad;
    }

    public static List<InconsistenciaDetalleFinanciacion> toListLevel0Entity(
            List<InconsistenciaDetalleFinanciacionDTO> listDto) {
        List<InconsistenciaDetalleFinanciacion> listEntidad = new ArrayList<InconsistenciaDetalleFinanciacion>();
        for (InconsistenciaDetalleFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<InconsistenciaDetalleFinanciacion> toListLevel1Entity(
            List<InconsistenciaDetalleFinanciacionDTO> listDto) {
        List<InconsistenciaDetalleFinanciacion> listEntidad = new ArrayList<InconsistenciaDetalleFinanciacion>();
        for (InconsistenciaDetalleFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
