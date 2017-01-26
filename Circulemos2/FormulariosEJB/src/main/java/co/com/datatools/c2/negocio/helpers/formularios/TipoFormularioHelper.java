package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.entidades.TipoFormulario;

/**
 * @author Generated
 * @version 3.0 - Tue Jan 06 18:09:11 COT 2015
 */
public class TipoFormularioHelper {
    // --- to DTO
    public static TipoFormularioDTO toLevel0DTO(TipoFormulario entidad) {
        TipoFormularioDTO dto = new TipoFormularioDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setActivo(entidad.getActivo());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static List<TipoFormularioDTO> toListLevel0DTO(List<TipoFormulario> listEntidad) {
        List<TipoFormularioDTO> listDto = new ArrayList<TipoFormularioDTO>();
        for (TipoFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoFormulario toLevel0Entity(TipoFormularioDTO dto, TipoFormulario entidad) {
        if (null == entidad) {
            entidad = new TipoFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setActivo(dto.getActivo());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static List<TipoFormulario> toListLevel0Entity(List<TipoFormularioDTO> listDto) {
        List<TipoFormulario> listEntidad = new ArrayList<TipoFormulario>();
        for (TipoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    // --- fin to Entidad
}
