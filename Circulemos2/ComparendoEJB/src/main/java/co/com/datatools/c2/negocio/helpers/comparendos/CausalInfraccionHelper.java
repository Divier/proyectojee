package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.CausalInfraccionDTO;
import co.com.datatools.c2.entidades.CausalInfraccion;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:43 COT 2014
 */
public class CausalInfraccionHelper {
    // --- to DTO
    public static CausalInfraccionDTO toLevel0DTO(CausalInfraccion entidad) {
        CausalInfraccionDTO dto = new CausalInfraccionDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static List<CausalInfraccionDTO> toListLevel0DTO(List<CausalInfraccion> listEntidad) {
        List<CausalInfraccionDTO> listDto = new ArrayList<CausalInfraccionDTO>();
        for (CausalInfraccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CausalInfraccion toLevel0Entity(CausalInfraccionDTO dto, CausalInfraccion entidad) {
        if (null == entidad) {
            entidad = new CausalInfraccion();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static List<CausalInfraccion> toListLevel0Entity(List<CausalInfraccionDTO> listDto) {
        List<CausalInfraccion> listEntidad = new ArrayList<CausalInfraccion>();
        for (CausalInfraccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    // --- fin to Entidad
}
