package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.SeguimientoFormularioDTO;
import co.com.datatools.c2.entidades.DetalleCambioEstado;
import co.com.datatools.c2.entidades.EstadoFormulario;
import co.com.datatools.c2.entidades.Formulario;
import co.com.datatools.c2.entidades.ResponsableFormulario;
import co.com.datatools.c2.entidades.SeguimientoFormulario;

/**
 * @author Generated
 * @version 3.0 - Thu Sep 10 09:05:43 COT 2015
 */
public class SeguimientoFormularioHelper {
    // --- to DTO
    public static SeguimientoFormularioDTO toLevel0DTO(SeguimientoFormulario entidad) {
        SeguimientoFormularioDTO dto = new SeguimientoFormularioDTO();
        dto.setId(entidad.getId());
        dto.setFechaMovimiento(entidad.getFechaMovimiento());
        dto.setUsuarioRegistro(entidad.getUsuarioRegistro());
        dto.setFechaAplicacionEstado(entidad.getFechaAplicacionEstado());
        return dto;
    }

    public static SeguimientoFormularioDTO toLevel1DTO(SeguimientoFormulario entidad) {
        SeguimientoFormularioDTO dto = toLevel0DTO(entidad);
        if (entidad.getResponsableFormulario() != null)
            dto.setResponsableFormulario(ResponsableFormularioHelper.toLevel0DTO(entidad.getResponsableFormulario()));
        if (entidad.getEstadoFormulario() != null)
            dto.setEstadoFormulario(EstadoFormularioHelper.toLevel0DTO(entidad.getEstadoFormulario()));
        if (entidad.getFormulario() != null)
            dto.setFormulario(FormularioHelper.toLevel0DTO(entidad.getFormulario()));
        if (entidad.getDetalleCambioEstado() != null)
            dto.setDetalleCambioEstado(DetalleCambioEstadoHelper.toLevel0DTO(entidad.getDetalleCambioEstado()));
        return dto;
    }

    public static List<SeguimientoFormularioDTO> toListLevel0DTO(List<SeguimientoFormulario> listEntidad) {
        List<SeguimientoFormularioDTO> listDto = new ArrayList<SeguimientoFormularioDTO>();
        for (SeguimientoFormulario entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<SeguimientoFormularioDTO> toListLevel1DTO(List<SeguimientoFormulario> listEntidad) {
        List<SeguimientoFormularioDTO> listDto = new ArrayList<SeguimientoFormularioDTO>();
        for (SeguimientoFormulario entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static SeguimientoFormulario toLevel0Entity(SeguimientoFormularioDTO dto, SeguimientoFormulario entidad) {
        if (null == entidad) {
            entidad = new SeguimientoFormulario();
        }
        entidad.setId(dto.getId());
        entidad.setFechaMovimiento(dto.getFechaMovimiento());
        entidad.setUsuarioRegistro(dto.getUsuarioRegistro());
        entidad.setFechaAplicacionEstado(dto.getFechaAplicacionEstado());

        return entidad;
    }

    public static SeguimientoFormulario toLevel1Entity(SeguimientoFormularioDTO dto, SeguimientoFormulario entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getResponsableFormulario() != null) {
            entidad.setResponsableFormulario(new ResponsableFormulario());
            entidad.getResponsableFormulario().setId(dto.getResponsableFormulario().getId());
        }
        if (dto.getEstadoFormulario() != null) {
            entidad.setEstadoFormulario(new EstadoFormulario());
            entidad.getEstadoFormulario().setId(dto.getEstadoFormulario().getId());
        }
        if (dto.getFormulario() != null) {
            entidad.setFormulario(new Formulario());
            entidad.getFormulario().setId(dto.getFormulario().getId());
        }
        if (dto.getDetalleCambioEstado() != null) {
            entidad.setDetalleCambioEstado(new DetalleCambioEstado());
            entidad.getDetalleCambioEstado().setId(dto.getDetalleCambioEstado().getId());
        }

        return entidad;
    }

    public static List<SeguimientoFormulario> toListLevel0Entity(List<SeguimientoFormularioDTO> listDto) {
        List<SeguimientoFormulario> listEntidad = new ArrayList<SeguimientoFormulario>();
        for (SeguimientoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<SeguimientoFormulario> toListLevel1Entity(List<SeguimientoFormularioDTO> listDto) {
        List<SeguimientoFormulario> listEntidad = new ArrayList<SeguimientoFormulario>();
        for (SeguimientoFormularioDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}