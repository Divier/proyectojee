package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.TipoServicioDTO;
import co.com.datatools.c2.entidades.TipoServicio;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class TipoServicioHelper {
    // --- to DTO
    public static TipoServicioDTO toLevel0DTO(TipoServicio entidad) {
        TipoServicioDTO dto = new TipoServicioDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static List<TipoServicioDTO> toListLevel0DTO(List<TipoServicio> listEntidad) {
        List<TipoServicioDTO> listDto = new ArrayList<TipoServicioDTO>();
        for (TipoServicio entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoServicio toLevel0Entity(TipoServicioDTO dto, TipoServicio entidad) {
        if (null == entidad) {
            entidad = new TipoServicio();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

    public static List<TipoServicio> toListLevel0Entity(List<TipoServicioDTO> listDto) {
        List<TipoServicio> listEntidad = new ArrayList<TipoServicio>();
        for (TipoServicioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    // --- fin to Entidad
}
