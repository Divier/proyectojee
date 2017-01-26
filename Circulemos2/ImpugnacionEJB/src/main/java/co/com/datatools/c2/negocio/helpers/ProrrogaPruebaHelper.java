package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ProrrogaPruebaDTO;
import co.com.datatools.c2.entidades.ProrrogaPrueba;
import co.com.datatools.c2.entidades.SolicitudPruebasBack;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:07:41 COT 2016
 */
public class ProrrogaPruebaHelper {
    // --- to DTO
    public static ProrrogaPruebaDTO toLevel0DTO(ProrrogaPrueba entidad) {
        ProrrogaPruebaDTO dto = new ProrrogaPruebaDTO();
        dto.setId(entidad.getId());
        dto.setDiasProrroga(entidad.getDiasProrroga());
        dto.setFechaProrroga(entidad.getFechaProrroga());
        dto.setNumeroProrroga(entidad.getNumeroProrroga());

        return dto;
    }

    public static ProrrogaPruebaDTO toLevel1DTO(ProrrogaPrueba entidad) {
        ProrrogaPruebaDTO dto = toLevel0DTO(entidad);
        if (entidad.getSolicitudPruebasBack() != null)
            dto.setSolicitudPruebasBack(SolicitudPruebasBackHelper.toLevel0DTO(entidad.getSolicitudPruebasBack()));

        return dto;
    }

    public static List<ProrrogaPruebaDTO> toListLevel0DTO(List<ProrrogaPrueba> listEntidad) {
        List<ProrrogaPruebaDTO> listDto = new ArrayList<ProrrogaPruebaDTO>();
        for (ProrrogaPrueba entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ProrrogaPruebaDTO> toListLevel1DTO(List<ProrrogaPrueba> listEntidad) {
        List<ProrrogaPruebaDTO> listDto = new ArrayList<ProrrogaPruebaDTO>();
        for (ProrrogaPrueba entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ProrrogaPrueba toLevel0Entity(ProrrogaPruebaDTO dto, ProrrogaPrueba entidad) {
        if (null == entidad) {
            entidad = new ProrrogaPrueba();
        }
        entidad.setId(dto.getId());
        entidad.setDiasProrroga(dto.getDiasProrroga());
        entidad.setFechaProrroga(dto.getFechaProrroga());
        entidad.setNumeroProrroga(dto.getNumeroProrroga());

        return entidad;
    }

    public static ProrrogaPrueba toLevel1Entity(ProrrogaPruebaDTO dto, ProrrogaPrueba entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getSolicitudPruebasBack() != null) {
            entidad.setSolicitudPruebasBack(new SolicitudPruebasBack());
            entidad.getSolicitudPruebasBack().setId(dto.getSolicitudPruebasBack().getId());
        }

        return entidad;
    }

    public static List<ProrrogaPrueba> toListLevel0Entity(List<ProrrogaPruebaDTO> listDto) {
        List<ProrrogaPrueba> listEntidad = new ArrayList<ProrrogaPrueba>();
        for (ProrrogaPruebaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ProrrogaPrueba> toListLevel1Entity(List<ProrrogaPruebaDTO> listDto) {
        List<ProrrogaPrueba> listEntidad = new ArrayList<ProrrogaPrueba>();
        for (ProrrogaPruebaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
