package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.CausalCambioEstadoDTO;
import co.com.datatools.c2.entidades.CausalCambioEstado;
import co.com.datatools.c2.entidades.EstadoFormulario;

/**
 * @author Generated
 * @version 3.0 - Thu Sep 03 17:16:24 COT 2015
 */
public class CausalCambioEstadoHelper {
    // --- to DTO
    public static CausalCambioEstadoDTO toLevel0DTO(CausalCambioEstado entidad) {
        CausalCambioEstadoDTO dto = new CausalCambioEstadoDTO();
        dto.setId(entidad.getId());
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setSigla(entidad.getSigla());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getActivo());

        return dto;
    }

    public static CausalCambioEstadoDTO toLevel1DTO(CausalCambioEstado entidad) {
        CausalCambioEstadoDTO dto = toLevel0DTO(entidad);
        if (entidad.getEstadoFormulario() != null)
            dto.setEstadoFormulario(EstadoFormularioHelper.toLevel0DTO(entidad.getEstadoFormulario()));

        return dto;
    }

    public static List<CausalCambioEstadoDTO> toListLevel0DTO(List<CausalCambioEstado> listEntidad) {
        List<CausalCambioEstadoDTO> listDto = new ArrayList<CausalCambioEstadoDTO>();
        for (CausalCambioEstado entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CausalCambioEstadoDTO> toListLevel1DTO(List<CausalCambioEstado> listEntidad) {
        List<CausalCambioEstadoDTO> listDto = new ArrayList<CausalCambioEstadoDTO>();
        for (CausalCambioEstado entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CausalCambioEstado toLevel0Entity(CausalCambioEstadoDTO dto, CausalCambioEstado entidad) {
        if (null == entidad) {
            entidad = new CausalCambioEstado();
        }
        entidad.setId(dto.getId());
        entidad.setCodigo(dto.getCodigo());
        entidad.setNombre(dto.getNombre());
        entidad.setSigla(dto.getSigla());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setActivo(dto.getEstado());

        return entidad;
    }

    public static CausalCambioEstado toLevel1Entity(CausalCambioEstadoDTO dto, CausalCambioEstado entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getEstadoFormulario() != null) {
            entidad.setEstadoFormulario(new EstadoFormulario());
            entidad.getEstadoFormulario().setId(dto.getEstadoFormulario().getId());
        }

        return entidad;
    }

    public static List<CausalCambioEstado> toListLevel0Entity(List<CausalCambioEstadoDTO> listDto) {
        List<CausalCambioEstado> listEntidad = new ArrayList<CausalCambioEstado>();
        for (CausalCambioEstadoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CausalCambioEstado> toListLevel1Entity(List<CausalCambioEstadoDTO> listDto) {
        List<CausalCambioEstado> listEntidad = new ArrayList<CausalCambioEstado>();
        for (CausalCambioEstadoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
