package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.EventoDTO;
import co.com.datatools.c2.entidades.EstadoEvento;
import co.com.datatools.c2.entidades.Evento;
import co.com.datatools.c2.entidades.TipoEvento;
import co.com.datatools.c2.entidades.TipoProceso;
import co.com.datatools.c2.entidades.TipoProcesoEvento;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.eventos.ConfiguracionHorario;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.eventos.ConfiguracionHorarioHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Dec 14 15:37:58 COT 2016
 */
public class EventoHelper {
    // --- to DTO
    public static EventoDTO toLevel0DTO(Evento entidad) {
        EventoDTO dto = new EventoDTO();
        dto.setId(entidad.getId());
        dto.setNombreEvento(entidad.getNombreEvento());
        dto.setLugar(entidad.getLugar());
        dto.setNumeroReferencia(entidad.getNumeroReferencia());
        dto.setFecha(entidad.getFecha());
        dto.setHoraInicio(entidad.getHoraInicio());
        dto.setHoraFin(entidad.getHoraFin());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setObservacion(entidad.getObservacion());

        return dto;
    }

    public static EventoDTO toLevel1DTO(Evento entidad) {
        EventoDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoProcesoEvento() != null)
            dto.setTipoProcesoEvento(TipoProcesoEventoHelper.toLevel0DTO(entidad.getTipoProcesoEvento()));
        if (entidad.getTipoEvento() != null)
            dto.setTipoEvento(TipoEventoHelper.toLevel0DTO(entidad.getTipoEvento()));
        if (entidad.getUsuario() != null)
            dto.setUsuario(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuario()));
        if (entidad.getEstadoEvento() != null)
            dto.setEstadoEvento(EstadoEventoHelper.toLevel0DTO(entidad.getEstadoEvento()));
        if (entidad.getConfiguracionHorario() != null)
            dto.setConfiguracionHorario(ConfiguracionHorarioHelper.toLevel0DTO(entidad.getConfiguracionHorario()));

        return dto;
    }

    public static List<EventoDTO> toListLevel0DTO(List<Evento> listEntidad) {
        List<EventoDTO> listDto = new ArrayList<EventoDTO>();
        for (Evento entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<EventoDTO> toListLevel1DTO(List<Evento> listEntidad) {
        List<EventoDTO> listDto = new ArrayList<EventoDTO>();
        for (Evento entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Evento toLevel0Entity(EventoDTO dto, Evento entidad) {
        if (null == entidad) {
            entidad = new Evento();
        }
        entidad.setId(dto.getId());
        entidad.setNombreEvento(dto.getNombreEvento());
        entidad.setLugar(dto.getLugar());
        entidad.setNumeroReferencia(dto.getNumeroReferencia());
        entidad.setFecha(dto.getFecha());
        entidad.setHoraInicio(dto.getHoraInicio());
        entidad.setHoraFin(dto.getHoraFin());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setObservacion(dto.getObservacion());

        return entidad;
    }

    public static Evento toLevel1Entity(EventoDTO dto, Evento entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoProcesoEvento() != null) {
            entidad.setTipoProcesoEvento(new TipoProcesoEvento());
            entidad.getTipoProcesoEvento().setId(dto.getTipoProcesoEvento().getId());
        }
        if (dto.getTipoEvento() != null) {
            entidad.setTipoEvento(new TipoEvento());
            entidad.getTipoEvento().setId(dto.getTipoEvento().getId());
        }
        if (dto.getUsuario() != null) {
            entidad.setUsuario(new UsuarioPersona());
            entidad.getUsuario().setIdUsuario(dto.getUsuario().getUsuario().getId());
        }
        if (dto.getEstadoEvento() != null) {
            entidad.setEstadoEvento(new EstadoEvento());
            entidad.getEstadoEvento().setId(dto.getEstadoEvento().getId());
        }
        if (dto.getConfiguracionHorario() != null) {
            entidad.setConfiguracionHorario(new ConfiguracionHorario());
            entidad.getConfiguracionHorario()
                    .setIdConfiguracionHorario(dto.getConfiguracionHorario().getIdConfiguracionHorario());
        }

        return entidad;
    }

    public static List<Evento> toListLevel0Entity(List<EventoDTO> listDto) {
        List<Evento> listEntidad = new ArrayList<Evento>();
        for (EventoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Evento> toListLevel1Entity(List<EventoDTO> listDto) {
        List<Evento> listEntidad = new ArrayList<Evento>();
        for (EventoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
