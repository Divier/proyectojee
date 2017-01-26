package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.TipoResponsableFormularioDTO;
import co.com.datatools.c2.entidades.TipoResponsableFormulario;

/**
 * @author Generated
 * @version 3.0 - Wed Aug 19 15:05:34 COT 2015
 */
public class TipoResponsableFormularioHelper {
    // --- to DTO
    public static TipoResponsableFormularioDTO toLevel0DTO(TipoResponsableFormulario entidad) {
        TipoResponsableFormularioDTO dto = new TipoResponsableFormularioDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setCodigo(entidad.getCodigo());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    public static TipoResponsableFormularioDTO toLevel1DTO(TipoResponsableFormulario entidad) {
        TipoResponsableFormularioDTO dto = toLevel0DTO(entidad);
        dto.setEstado(entidad.getActivo());
        return dto;
    }

    public static List<TipoResponsableFormularioDTO> toListLevel0DTO(List<TipoResponsableFormulario> listEntidad) {
        List<TipoResponsableFormularioDTO> listDto = new ArrayList<TipoResponsableFormularioDTO>();
        for (TipoResponsableFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TipoResponsableFormularioDTO> toListLevel1DTO(List<TipoResponsableFormulario> listEntidad) {
        List<TipoResponsableFormularioDTO> listDto = new ArrayList<TipoResponsableFormularioDTO>();
        for (TipoResponsableFormulario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TipoResponsableFormulario toLevel0Entity(TipoResponsableFormularioDTO dto,
            TipoResponsableFormulario entidad) {
        if (null == entidad) {
            entidad = new TipoResponsableFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setCodigo(dto.getCodigo());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }

    public static TipoResponsableFormulario toLevel1Entity(TipoResponsableFormularioDTO dto,
            TipoResponsableFormulario entidad) {
        entidad = toLevel0Entity(dto, entidad);
        entidad.setActivo(dto.getEstado());
        return entidad;
    }

    public static List<TipoResponsableFormulario> toListLevel0Entity(List<TipoResponsableFormularioDTO> listDto) {
        List<TipoResponsableFormulario> listEntidad = new ArrayList<TipoResponsableFormulario>();
        for (TipoResponsableFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TipoResponsableFormulario> toListLevel1Entity(List<TipoResponsableFormularioDTO> listDto) {
        List<TipoResponsableFormulario> listEntidad = new ArrayList<TipoResponsableFormulario>();
        for (TipoResponsableFormularioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
