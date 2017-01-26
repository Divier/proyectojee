package co.com.datatools.c2.negocio.helpers.formularios;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.entidades.CausalCambioEstado;
import co.com.datatools.c2.entidades.DetalleCambioEstado;
import co.com.datatools.c2.entidades.DocumentoFormulario;
import co.com.datatools.c2.entidades.EstadoFormulario;
import co.com.datatools.c2.entidades.RangoFormulario;
import co.com.datatools.c2.entidades.ResponsableFormulario;

/**
 * @author Generated
 * @version 3.0 - Thu Sep 03 17:15:13 COT 2015
 */
public class DetalleCambioEstadoHelper {
    // --- to DTO
    public static DetalleCambioEstadoDTO toLevel0DTO(DetalleCambioEstado entidad) {
        DetalleCambioEstadoDTO dto = new DetalleCambioEstadoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroInicial(entidad.getNumeroInicial());
        dto.setNumeroFinal(entidad.getNumeroFinal());
        dto.setFechaMovimiento(entidad.getFechaMovimiento());
        dto.setCantidadFormularios(entidad.getCantidadFormularios());
        dto.setFolio(entidad.getFolio());
        dto.setTrama(entidad.getTrama());
        dto.setFechaAplicacionEstado(entidad.getFechaAplicacionEstado());
        dto.setObservaciones(entidad.getObservaciones());
        dto.setEsEvento(entidad.getEsEvento());

        return dto;
    }

    public static DetalleCambioEstadoDTO toLevel1DTO(DetalleCambioEstado entidad) {
        DetalleCambioEstadoDTO dto = toLevel0DTO(entidad);
        if (entidad.getRangoFormulario() != null)
            dto.setRangoFormulario(RangoFormularioHelper.toLevel0DTO(entidad.getRangoFormulario()));
        if (entidad.getResponsableFormulario() != null)
            dto.setResponsableFormulario(ResponsableFormularioHelper.toLevel0DTO(entidad.getResponsableFormulario()));
        if (entidad.getDocumentoFormulario() != null)
            dto.setDocumentoFormulario(DocumentoFormularioHelper.toLevel0DTO(entidad.getDocumentoFormulario()));
        if (entidad.getCausalCambioEstado() != null)
            dto.setCausalCambioEstado(CausalCambioEstadoHelper.toLevel0DTO(entidad.getCausalCambioEstado()));
        if (entidad.getEstadoFormulario() != null)
            dto.setEstadoFormulario(EstadoFormularioHelper.toLevel0DTO(entidad.getEstadoFormulario()));

        return dto;
    }

    public static List<DetalleCambioEstadoDTO> toListLevel0DTO(List<DetalleCambioEstado> listEntidad) {
        List<DetalleCambioEstadoDTO> listDto = new ArrayList<DetalleCambioEstadoDTO>();
        for (DetalleCambioEstado entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetalleCambioEstadoDTO> toListLevel1DTO(List<DetalleCambioEstado> listEntidad) {
        List<DetalleCambioEstadoDTO> listDto = new ArrayList<DetalleCambioEstadoDTO>();
        for (DetalleCambioEstado entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetalleCambioEstado toLevel0Entity(DetalleCambioEstadoDTO dto, DetalleCambioEstado entidad) {
        if (null == entidad) {
            entidad = new DetalleCambioEstado();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroInicial(dto.getNumeroInicial());
        entidad.setNumeroFinal(dto.getNumeroFinal());
        entidad.setFechaMovimiento(dto.getFechaMovimiento());
        entidad.setCantidadFormularios(dto.getCantidadFormularios());
        entidad.setFolio(dto.getFolio());
        entidad.setTrama(dto.getTrama());
        entidad.setFechaAplicacionEstado(dto.getFechaAplicacionEstado());
        entidad.setObservaciones(dto.getObservaciones());
        entidad.setEsEvento(dto.isEsEvento());

        return entidad;
    }

    public static DetalleCambioEstado toLevel1Entity(DetalleCambioEstadoDTO dto, DetalleCambioEstado entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getRangoFormulario() != null) {
            entidad.setRangoFormulario(new RangoFormulario());
            entidad.getRangoFormulario().setId(dto.getRangoFormulario().getId());
        }
        if (dto.getResponsableFormulario() != null) {
            entidad.setResponsableFormulario(new ResponsableFormulario());
            entidad.getResponsableFormulario().setId(dto.getResponsableFormulario().getId());
        }
        if (dto.getDocumentoFormulario() != null) {
            entidad.setDocumentoFormulario(new DocumentoFormulario());
            entidad.getDocumentoFormulario().setId(dto.getDocumentoFormulario().getId());
        }
        if (dto.getCausalCambioEstado() != null) {
            entidad.setCausalCambioEstado(new CausalCambioEstado());
            entidad.getCausalCambioEstado().setId(dto.getCausalCambioEstado().getId());
        }
        if (dto.getEstadoFormulario() != null) {
            entidad.setEstadoFormulario(new EstadoFormulario());
            entidad.getEstadoFormulario().setId(dto.getEstadoFormulario().getId());
        }

        return entidad;
    }

    public static List<DetalleCambioEstado> toListLevel0Entity(List<DetalleCambioEstadoDTO> listDto) {
        List<DetalleCambioEstado> listEntidad = new ArrayList<DetalleCambioEstado>();
        for (DetalleCambioEstadoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetalleCambioEstado> toListLevel1Entity(List<DetalleCambioEstadoDTO> listDto) {
        List<DetalleCambioEstado> listEntidad = new ArrayList<DetalleCambioEstado>();
        for (DetalleCambioEstadoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
