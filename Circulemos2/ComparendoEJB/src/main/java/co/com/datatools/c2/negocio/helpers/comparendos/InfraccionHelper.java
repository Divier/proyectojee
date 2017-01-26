package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.entidades.Infraccion;
import co.com.datatools.c2.entidades.TipoComparendo;
import co.com.datatools.c2.entidades.TipoInfraccion;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 28 08:26:49 COT 2016
 */
public class InfraccionHelper {
    // --- to DTO
    public static InfraccionDTO toLevel0DTO(Infraccion entidad) {
        InfraccionDTO dto = new InfraccionDTO();
        dto.setId(entidad.getId());
        dto.setNumeral(entidad.getNumeral());
        dto.setCodigo(entidad.getCodigo());

        return dto;
    }

    public static InfraccionDTO toLevel1DTO(Infraccion entidad) {
        InfraccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoInfraccion() != null)
            dto.setTipoInfraccion(TipoInfraccionHelper.toLevel0DTO(entidad.getTipoInfraccion()));
        if (entidad.getTipoComparendo() != null)
            dto.setTipoComparendo(TipoComparendoHelper.toLevel0DTO(entidad.getTipoComparendo()));

        return dto;
    }

    public static List<InfraccionDTO> toListLevel0DTO(List<Infraccion> listEntidad) {
        List<InfraccionDTO> listDto = new ArrayList<InfraccionDTO>();
        for (Infraccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<InfraccionDTO> toListLevel1DTO(List<Infraccion> listEntidad) {
        List<InfraccionDTO> listDto = new ArrayList<InfraccionDTO>();
        for (Infraccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Infraccion toLevel0Entity(InfraccionDTO dto, Infraccion entidad) {
        if (null == entidad) {
            entidad = new Infraccion();
        }
        entidad.setId(dto.getId());
        entidad.setNumeral(dto.getNumeral());
        entidad.setCodigo(dto.getCodigo());

        return entidad;
    }

    public static Infraccion toLevel1Entity(InfraccionDTO dto, Infraccion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoInfraccion() != null) {
            entidad.setTipoInfraccion(new TipoInfraccion());
            entidad.getTipoInfraccion().setId(dto.getTipoInfraccion().getId());
        }
        if (dto.getTipoComparendo() != null) {
            entidad.setTipoComparendo(new TipoComparendo());
            entidad.getTipoComparendo().setId(dto.getTipoComparendo().getId());
        }

        return entidad;
    }

    public static List<Infraccion> toListLevel0Entity(List<InfraccionDTO> listDto) {
        List<Infraccion> listEntidad = new ArrayList<Infraccion>();
        for (InfraccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Infraccion> toListLevel1Entity(List<InfraccionDTO> listDto) {
        List<Infraccion> listEntidad = new ArrayList<Infraccion>();
        for (InfraccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}