package co.com.datatools.c2.negocio.helpers.homologacion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.homologacion.ValorHomologacionDTO;
import co.com.datatools.c2.entidades.TipoHomologacion;
import co.com.datatools.c2.entidades.ValorHomologacion;

/**
 * @author Generated
 * @version 3.0 - Mon Nov 30 12:49:56 COT 2015
 */
public class ValorHomologacionHelper {
    // --- to DTO
    public static ValorHomologacionDTO toLevel0DTO(ValorHomologacion entidad) {
        ValorHomologacionDTO dto = new ValorHomologacionDTO();
        dto.setId(entidad.getIdValorHomologacion());
        dto.setValorFinal(entidad.getValorFinal());
        dto.setValorOrigen(entidad.getValorOrigen());

        return dto;
    }

    public static ValorHomologacionDTO toLevel1DTO(ValorHomologacion entidad) {
        ValorHomologacionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoHomologacion() != null)
            dto.setTipoHomologacion(TipoHomologacionHelper.toLevel0DTO(entidad.getTipoHomologacion()));

        return dto;
    }

    public static List<ValorHomologacionDTO> toListLevel0DTO(List<ValorHomologacion> listEntidad) {
        List<ValorHomologacionDTO> listDto = new ArrayList<ValorHomologacionDTO>();
        for (ValorHomologacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ValorHomologacionDTO> toListLevel1DTO(List<ValorHomologacion> listEntidad) {
        List<ValorHomologacionDTO> listDto = new ArrayList<ValorHomologacionDTO>();
        for (ValorHomologacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ValorHomologacion toLevel0Entity(ValorHomologacionDTO dto, ValorHomologacion entidad) {
        if (null == entidad) {
            entidad = new ValorHomologacion();
        }
        entidad.setIdValorHomologacion(dto.getId());
        entidad.setValorFinal(dto.getValorFinal());
        entidad.setValorOrigen(dto.getValorOrigen());

        return entidad;
    }

    public static ValorHomologacion toLevel1Entity(ValorHomologacionDTO dto, ValorHomologacion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoHomologacion() != null) {
            entidad.setTipoHomologacion(new TipoHomologacion());
            entidad.getTipoHomologacion().setId(dto.getTipoHomologacion().getId());
        }

        return entidad;
    }

    public static List<ValorHomologacion> toListLevel0Entity(List<ValorHomologacionDTO> listDto) {
        List<ValorHomologacion> listEntidad = new ArrayList<ValorHomologacion>();
        for (ValorHomologacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ValorHomologacion> toListLevel1Entity(List<ValorHomologacionDTO> listDto) {
        List<ValorHomologacion> listEntidad = new ArrayList<ValorHomologacion>();
        for (ValorHomologacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
