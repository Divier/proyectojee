package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.financiacion.ItDetalleFinanciacionDTO;
import co.com.datatools.c2.entidades.financiacion.ItDetalleFinanciacion;
import co.com.datatools.c2.entidades.financiacion.ItFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Tue May 17 11:53:17 COT 2016
 */
public class ItDetalleFinanciacionHelper {
    // --- to DTO
    public static ItDetalleFinanciacionDTO toLevel0DTO(ItDetalleFinanciacion entidad) {
        ItDetalleFinanciacionDTO dto = new ItDetalleFinanciacionDTO();
        dto.setIdDetalleFinanciacion(entidad.getIdDetalleFinanciacion());
        dto.setFechaObligacion(entidad.getFechaObligacion());
        dto.setObligacion(entidad.getObligacion());
        dto.setTipoObligacion(entidad.getTipoObligacion());
        dto.setValorObligacion(entidad.getValorObligacion());

        return dto;
    }

    public static ItDetalleFinanciacionDTO toLevel1DTO(ItDetalleFinanciacion entidad) {
        ItDetalleFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getItFinanciacion() != null)
            dto.setItFinanciacion(ItFinanciacionHelper.toLevel0DTO(entidad.getItFinanciacion()));

        return dto;
    }

    public static List<ItDetalleFinanciacionDTO> toListLevel0DTO(List<ItDetalleFinanciacion> listEntidad) {
        List<ItDetalleFinanciacionDTO> listDto = new ArrayList<ItDetalleFinanciacionDTO>();
        for (ItDetalleFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ItDetalleFinanciacionDTO> toListLevel1DTO(List<ItDetalleFinanciacion> listEntidad) {
        List<ItDetalleFinanciacionDTO> listDto = new ArrayList<ItDetalleFinanciacionDTO>();
        for (ItDetalleFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ItDetalleFinanciacion toLevel0Entity(ItDetalleFinanciacionDTO dto, ItDetalleFinanciacion entidad) {
        if (null == entidad) {
            entidad = new ItDetalleFinanciacion();
        }
        entidad.setIdDetalleFinanciacion(dto.getIdDetalleFinanciacion());
        entidad.setFechaObligacion(dto.getFechaObligacion());
        entidad.setObligacion(dto.getObligacion());
        entidad.setTipoObligacion(dto.getTipoObligacion());
        entidad.setValorObligacion(dto.getValorObligacion());

        return entidad;
    }

    public static ItDetalleFinanciacion toLevel1Entity(ItDetalleFinanciacionDTO dto, ItDetalleFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getItFinanciacion() != null) {
            entidad.setItFinanciacion(new ItFinanciacion());
            entidad.getItFinanciacion().setNumeroFinanciacion(dto.getItFinanciacion().getNumeroFinanciacion());
        }

        return entidad;
    }

    public static List<ItDetalleFinanciacion> toListLevel0Entity(List<ItDetalleFinanciacionDTO> listDto) {
        List<ItDetalleFinanciacion> listEntidad = new ArrayList<ItDetalleFinanciacion>();
        for (ItDetalleFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ItDetalleFinanciacion> toListLevel1Entity(List<ItDetalleFinanciacionDTO> listDto) {
        List<ItDetalleFinanciacion> listEntidad = new ArrayList<ItDetalleFinanciacion>();
        for (ItDetalleFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
