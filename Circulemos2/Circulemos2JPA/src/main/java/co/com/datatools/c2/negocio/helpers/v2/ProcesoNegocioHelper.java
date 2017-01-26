package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ProcesoNegocioDTO;
import co.com.datatools.c2.entidades.Modulo;
import co.com.datatools.c2.entidades.ProcesoNegocio;

/**
 * @author Generated
 * @version 3.0 - Mon Mar 14 17:54:26 COT 2016
 */
public class ProcesoNegocioHelper {
    // --- to DTO
    public static ProcesoNegocioDTO toLevel0DTO(ProcesoNegocio entidad) {
        ProcesoNegocioDTO dto = new ProcesoNegocioDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static ProcesoNegocioDTO toLevel1DTO(ProcesoNegocio entidad) {
        ProcesoNegocioDTO dto = toLevel0DTO(entidad);
        if (entidad.getModulo() != null)
            dto.setModulo(ModuloHelper.toLevel0DTO(entidad.getModulo()));
        if (entidad.getProcesoPadre() != null)
            dto.setProcesoPadre(ProcesoNegocioHelper.toLevel0DTO(entidad.getProcesoPadre()));

        return dto;
    }

    public static List<ProcesoNegocioDTO> toListLevel0DTO(List<ProcesoNegocio> listEntidad) {
        List<ProcesoNegocioDTO> listDto = new ArrayList<ProcesoNegocioDTO>();
        for (ProcesoNegocio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ProcesoNegocioDTO> toListLevel1DTO(List<ProcesoNegocio> listEntidad) {
        List<ProcesoNegocioDTO> listDto = new ArrayList<ProcesoNegocioDTO>();
        for (ProcesoNegocio entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ProcesoNegocio toLevel0Entity(ProcesoNegocioDTO dto, ProcesoNegocio entidad) {
        if (null == entidad) {
            entidad = new ProcesoNegocio();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static ProcesoNegocio toLevel1Entity(ProcesoNegocioDTO dto, ProcesoNegocio entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getModulo() != null) {
            entidad.setModulo(new Modulo());
            entidad.getModulo().setId(dto.getModulo().getId());
        }
        if (dto.getProcesoPadre() != null) {
            entidad.setProcesoPadre(new ProcesoNegocio());
            entidad.getProcesoPadre().setId(dto.getProcesoPadre().getId());
        }

        return entidad;
    }

    public static List<ProcesoNegocio> toListLevel0Entity(List<ProcesoNegocioDTO> listDto) {
        List<ProcesoNegocio> listEntidad = new ArrayList<ProcesoNegocio>();
        for (ProcesoNegocioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ProcesoNegocio> toListLevel1Entity(List<ProcesoNegocioDTO> listDto) {
        List<ProcesoNegocio> listEntidad = new ArrayList<ProcesoNegocio>();
        for (ProcesoNegocioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
