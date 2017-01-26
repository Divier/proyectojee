package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.entidades.Financiacion;
import co.com.datatools.c2.entidades.ObligacionFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Fri May 06 12:05:02 COT 2016
 */
public class ObligacionFinanciacionHelper {
    // --- to DTO
    public static ObligacionFinanciacionDTO toLevel0DTO(ObligacionFinanciacion entidad) {
        ObligacionFinanciacionDTO dto = new ObligacionFinanciacionDTO();
        dto.setId(entidad.getId());
        dto.setCodigoTipoObligacion(entidad.getCodigoTipoObligacion());
        dto.setNumeroObligacion(entidad.getNumeroObligacion());
        dto.setFechaObligacion(entidad.getFechaObligacion());
        dto.setValorCostasProcesales(entidad.getValorCostasProcesales());
        dto.setValorObligacion(entidad.getValorObligacion());
        dto.setValorInteresMoratorios(entidad.getValorInteresMoratorios());

        return dto;
    }

    public static ObligacionFinanciacionDTO toLevel1DTO(ObligacionFinanciacion entidad) {
        ObligacionFinanciacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getFinanciacion() != null)
            dto.setFinanciacion(FinanciacionHelper.toLevel0DTO(entidad.getFinanciacion()));

        return dto;
    }

    public static List<ObligacionFinanciacionDTO> toListLevel0DTO(List<ObligacionFinanciacion> listEntidad) {
        List<ObligacionFinanciacionDTO> listDto = new ArrayList<ObligacionFinanciacionDTO>();
        for (ObligacionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ObligacionFinanciacionDTO> toListLevel1DTO(List<ObligacionFinanciacion> listEntidad) {
        List<ObligacionFinanciacionDTO> listDto = new ArrayList<ObligacionFinanciacionDTO>();
        for (ObligacionFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ObligacionFinanciacion toLevel0Entity(ObligacionFinanciacionDTO dto, ObligacionFinanciacion entidad) {
        if (null == entidad) {
            entidad = new ObligacionFinanciacion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoTipoObligacion(dto.getCodigoTipoObligacion());
        entidad.setNumeroObligacion(dto.getNumeroObligacion());
        entidad.setFechaObligacion(dto.getFechaObligacion());
        entidad.setValorCostasProcesales(dto.getValorCostasProcesales());
        entidad.setValorObligacion(dto.getValorObligacion());
        entidad.setValorInteresMoratorios(dto.getValorInteresMoratorios());

        return entidad;
    }

    public static ObligacionFinanciacion toLevel1Entity(ObligacionFinanciacionDTO dto, ObligacionFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getFinanciacion() != null) {
            entidad.setFinanciacion(new Financiacion());
            entidad.getFinanciacion().setId(dto.getFinanciacion().getId());
        }

        return entidad;
    }

    public static List<ObligacionFinanciacion> toListLevel0Entity(List<ObligacionFinanciacionDTO> listDto) {
        List<ObligacionFinanciacion> listEntidad = new ArrayList<ObligacionFinanciacion>();
        for (ObligacionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ObligacionFinanciacion> toListLevel1Entity(List<ObligacionFinanciacionDTO> listDto) {
        List<ObligacionFinanciacion> listEntidad = new ArrayList<ObligacionFinanciacion>();
        for (ObligacionFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
