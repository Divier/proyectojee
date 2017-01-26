package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ImpulsoPruebaDTO;
import co.com.datatools.c2.entidades.ImpulsoPrueba;
import co.com.datatools.c2.entidades.SolicitudPruebasBack;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 18:08:26 COT 2016
 */
public class ImpulsoPruebaHelper {
    // --- to DTO
    public static ImpulsoPruebaDTO toLevel0DTO(ImpulsoPrueba entidad) {
        ImpulsoPruebaDTO dto = new ImpulsoPruebaDTO();
        dto.setId(entidad.getId());
        dto.setContenidoImpulso(entidad.getContenidoImpulso());
        dto.setDefinitivo(entidad.getDefinitivo());
        dto.setFechaGeneracion(entidad.getFechaGeneracion());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());

        return dto;
    }

    public static ImpulsoPruebaDTO toLevel1DTO(ImpulsoPrueba entidad) {
        ImpulsoPruebaDTO dto = toLevel0DTO(entidad);
        if (entidad.getSolicitudPruebasBack() != null)
            dto.setSolicitudPruebasBack(SolicitudPruebasBackHelper.toLevel0DTO(entidad.getSolicitudPruebasBack()));

        return dto;
    }

    public static List<ImpulsoPruebaDTO> toListLevel0DTO(List<ImpulsoPrueba> listEntidad) {
        List<ImpulsoPruebaDTO> listDto = new ArrayList<ImpulsoPruebaDTO>();
        for (ImpulsoPrueba entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ImpulsoPruebaDTO> toListLevel1DTO(List<ImpulsoPrueba> listEntidad) {
        List<ImpulsoPruebaDTO> listDto = new ArrayList<ImpulsoPruebaDTO>();
        for (ImpulsoPrueba entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ImpulsoPrueba toLevel0Entity(ImpulsoPruebaDTO dto, ImpulsoPrueba entidad) {
        if (null == entidad) {
            entidad = new ImpulsoPrueba();
        }
        entidad.setId(dto.getId());
        entidad.setContenidoImpulso(dto.getContenidoImpulso());
        entidad.setDefinitivo(dto.getDefinitivo());
        entidad.setFechaGeneracion(dto.getFechaGeneracion());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());

        return entidad;
    }

    public static ImpulsoPrueba toLevel1Entity(ImpulsoPruebaDTO dto, ImpulsoPrueba entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getSolicitudPruebasBack() != null) {
            entidad.setSolicitudPruebasBack(new SolicitudPruebasBack());
            entidad.getSolicitudPruebasBack().setId(dto.getSolicitudPruebasBack().getId());
        }

        return entidad;
    }

    public static List<ImpulsoPrueba> toListLevel0Entity(List<ImpulsoPruebaDTO> listDto) {
        List<ImpulsoPrueba> listEntidad = new ArrayList<ImpulsoPrueba>();
        for (ImpulsoPruebaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ImpulsoPrueba> toListLevel1Entity(List<ImpulsoPruebaDTO> listDto) {
        List<ImpulsoPrueba> listEntidad = new ArrayList<ImpulsoPrueba>();
        for (ImpulsoPruebaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
