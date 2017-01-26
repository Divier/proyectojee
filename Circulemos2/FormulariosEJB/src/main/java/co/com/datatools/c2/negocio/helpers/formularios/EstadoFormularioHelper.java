package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.entidades.EstadoFormulario;

/**
 * @author Generated
 * @version 3.0 - Tue Jan 06 18:07:41 COT 2015
 */
public class EstadoFormularioHelper {
    // --- to DTO
    public static EstadoFormularioDTO toLevel0DTO(EstadoFormulario entidad) {
        EstadoFormularioDTO dto = new EstadoFormularioDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

    public static EstadoFormularioDTO toLevel1DTO(EstadoFormulario entidad) {
        EstadoFormularioDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<EstadoFormularioDTO> toListLevel0DTO(List<EstadoFormulario> listEntidad) {
        List<EstadoFormularioDTO> listDto = new ArrayList<EstadoFormularioDTO>();
        for (EstadoFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EstadoFormularioDTO> toListLevel1DTO(List<EstadoFormulario> listEntidad) {
        List<EstadoFormularioDTO> listDto = new ArrayList<EstadoFormularioDTO>();
        for (EstadoFormulario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static EstadoFormulario toLevel0Entity(EstadoFormularioDTO dto, EstadoFormulario entidad) {
        if (null == entidad) {
            entidad = new EstadoFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getActivo());

        return entidad;
    }

    public static EstadoFormulario toLevel1Entity(EstadoFormularioDTO dto, EstadoFormulario entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<EstadoFormulario> toListLevel0Entity(List<EstadoFormularioDTO> listDto) {
        List<EstadoFormulario> listEntidad = new ArrayList<EstadoFormulario>();
        for (EstadoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<EstadoFormulario> toListLevel1Entity(List<EstadoFormularioDTO> listDto) {
        List<EstadoFormulario> listEntidad = new ArrayList<EstadoFormulario>();
        for (EstadoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
