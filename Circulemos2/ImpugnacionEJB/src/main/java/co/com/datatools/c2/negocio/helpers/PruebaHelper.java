package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.PruebaDTO;
import co.com.datatools.c2.entidades.Prueba;
import co.com.datatools.c2.entidades.SolicitudPruebasBack;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:07:25 COT 2016
 */
public class PruebaHelper {
    // --- to DTO
    public static PruebaDTO toLevel0DTO(Prueba entidad) {
        PruebaDTO dto = new PruebaDTO();
        dto.setId(entidad.getId());
        dto.setFechaCarga(entidad.getFechaCarga());
        dto.setNombreArchivo(entidad.getNombreArchivo());
        dto.setUrlPrueba(entidad.getUrlPrueba());
        dto.setNumeroArchivo(entidad.getNumeroArchivo());

        return dto;
    }

    public static PruebaDTO toLevel1DTO(Prueba entidad) {
        PruebaDTO dto = toLevel0DTO(entidad);
        if (entidad.getSolicitudPruebasBack() != null)
            dto.setSolicitudPruebasBack(SolicitudPruebasBackHelper.toLevel0DTO(entidad.getSolicitudPruebasBack()));

        return dto;
    }

    public static List<PruebaDTO> toListLevel0DTO(List<Prueba> listEntidad) {
        List<PruebaDTO> listDto = new ArrayList<PruebaDTO>();
        for (Prueba entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PruebaDTO> toListLevel1DTO(List<Prueba> listEntidad) {
        List<PruebaDTO> listDto = new ArrayList<PruebaDTO>();
        for (Prueba entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Prueba toLevel0Entity(PruebaDTO dto, Prueba entidad) {
        if (null == entidad) {
            entidad = new Prueba();
        }
        entidad.setId(dto.getId());
        entidad.setFechaCarga(dto.getFechaCarga());
        entidad.setNombreArchivo(dto.getNombreArchivo());
        entidad.setUrlPrueba(dto.getUrlPrueba());
        entidad.setNumeroArchivo(dto.getNumeroArchivo());

        return entidad;
    }

    public static Prueba toLevel1Entity(PruebaDTO dto, Prueba entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getSolicitudPruebasBack() != null) {
            entidad.setSolicitudPruebasBack(new SolicitudPruebasBack());
            entidad.getSolicitudPruebasBack().setId(dto.getSolicitudPruebasBack().getId());
        }

        return entidad;
    }

    public static List<Prueba> toListLevel0Entity(List<PruebaDTO> listDto) {
        List<Prueba> listEntidad = new ArrayList<Prueba>();
        for (PruebaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Prueba> toListLevel1Entity(List<PruebaDTO> listDto) {
        List<Prueba> listEntidad = new ArrayList<Prueba>();
        for (PruebaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
