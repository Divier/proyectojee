package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.AccidentalidadDTO;
import co.com.datatools.c2.entidades.Accidentalidad;
import co.com.datatools.c2.entidades.Delegacion;
import co.com.datatools.c2.entidades.EstadoAccidentalidad;
import co.com.datatools.c2.entidades.Prevencion;
import co.com.datatools.c2.entidades.TipoAccidente;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * Helper no auto generado
 * 
 * @author giovanni.velandia
 * @version 3.0 - Wed Oct 05 14:32:16 COT 2016
 */
public class AccidentalidadHelper {
    // --- to DTO
    public static AccidentalidadDTO toLevel0DTO(Accidentalidad entidad) {
        AccidentalidadDTO dto = new AccidentalidadDTO();
        dto.setId(entidad.getId());
        dto.setConsecutivo(entidad.getConsecutivo());
        dto.setConsecuencias(entidad.getConsecuencias());
        dto.setUbicacionGps(entidad.getUbicacionGps());
        dto.setParteFinalizado(entidad.getParteFinalizado());
        dto.setLugarAccidente(entidad.getLugarAccidente());
        dto.setFechaAccidente(entidad.getFechaAccidente());
        dto.setFechaAviso(entidad.getFechaAviso());
        dto.setReferenciaHechos(entidad.getReferenciaHechos());
        dto.setAgente(entidad.getAgente());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());

        return dto;
    }

    public static AccidentalidadDTO toLevel1DTO(Accidentalidad entidad) {
        AccidentalidadDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoAccidente() != null)
            dto.setTipoAccidente(TipoAccidenteHelper.toLevel0DTO(entidad.getTipoAccidente()));
        if (entidad.getPrevencion() != null)
            dto.setPrevencion(PrevencionHelper.toLevel0DTO(entidad.getPrevencion()));
        if (entidad.getDelegacion() != null)
            dto.setDelegacion(DelegacionHelper.toLevel0DTO(entidad.getDelegacion()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));
        if (entidad.getEstadoAccidentalidad() != null)
            dto.setEstadoAccidentalidad(EstadoAccidentalidadHelper.toLevel0DTO(entidad.getEstadoAccidentalidad()));

        return dto;
    }

    public static List<AccidentalidadDTO> toListLevel0DTO(List<Accidentalidad> listEntidad) {
        List<AccidentalidadDTO> listDto = new ArrayList<AccidentalidadDTO>();
        for (Accidentalidad entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<AccidentalidadDTO> toListLevel1DTO(List<Accidentalidad> listEntidad) {
        List<AccidentalidadDTO> listDto = new ArrayList<AccidentalidadDTO>();
        for (Accidentalidad entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Accidentalidad toLevel0Entity(AccidentalidadDTO dto, Accidentalidad entidad) {
        if (null == entidad) {
            entidad = new Accidentalidad();
        }
        entidad.setId(dto.getId());
        entidad.setConsecutivo(dto.getConsecutivo());
        entidad.setConsecuencias(dto.getConsecuencias());
        entidad.setUbicacionGps(dto.getUbicacionGps());
        entidad.setParteFinalizado(dto.getParteFinalizado());
        entidad.setLugarAccidente(dto.getLugarAccidente());
        entidad.setFechaAccidente(dto.getFechaAccidente());
        entidad.setFechaAviso(dto.getFechaAviso());
        entidad.setReferenciaHechos(dto.getReferenciaHechos());
        entidad.setAgente(dto.getAgente());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());

        return entidad;
    }

    public static Accidentalidad toLevel1Entity(AccidentalidadDTO dto, Accidentalidad entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoAccidente() != null) {
            entidad.setTipoAccidente(new TipoAccidente());
            entidad.getTipoAccidente().setId(dto.getTipoAccidente().getId());
        }
        if (dto.getPrevencion() != null && dto.getPrevencion().getId() != null) {
            entidad.setPrevencion(new Prevencion());
            entidad.getPrevencion().setId(dto.getPrevencion().getId());
        }
        if (dto.getDelegacion() != null) {
            entidad.setDelegacion(new Delegacion());
            entidad.getDelegacion().setId(dto.getDelegacion().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            // entidad.getUsuario().setIdUsuario(dto.getUsuario().getIdUsuario());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }
        if (dto.getEstadoAccidentalidad() != null) {
            entidad.setEstadoAccidentalidad(new EstadoAccidentalidad());
            entidad.getEstadoAccidentalidad().setId(dto.getEstadoAccidentalidad().getId());
        }

        return entidad;
    }

    public static List<Accidentalidad> toListLevel0Entity(List<AccidentalidadDTO> listDto) {
        List<Accidentalidad> listEntidad = new ArrayList<Accidentalidad>();
        for (AccidentalidadDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Accidentalidad> toListLevel1Entity(List<AccidentalidadDTO> listDto) {
        List<Accidentalidad> listEntidad = new ArrayList<Accidentalidad>();
        for (AccidentalidadDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
