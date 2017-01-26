package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.TipoTelefono;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ScoreUbicabilidadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:54 COT 2016
 */
public class TelefonoPersonaHelper {
    // --- to DTO
    public static TelefonoPersonaDTO toLevel0DTO(TelefonoPersona entidad) {
        TelefonoPersonaDTO dto = new TelefonoPersonaDTO();
        dto.setId(entidad.getId());
        dto.setEstado(entidad.getEstado());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setNumeroTelefono(entidad.getNumeroTelefono());
        dto.setPrioridad(entidad.getPrioridad());
        dto.setFechaValidacion(entidad.getFechaValidacion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setAutorizadoNotificacion(entidad.getAutorizadoNotificacion());

        return dto;
    }

    public static TelefonoPersonaDTO toLevel1DTO(TelefonoPersona entidad) {
        TelefonoPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getScoreUbicabilidad() != null)
            dto.setScoreUbicabilidad(ScoreUbicabilidadHelper.toLevel0DTO(entidad.getScoreUbicabilidad()));
        if (entidad.getTipoTelefono() != null)
            dto.setTipoTelefono(TipoTelefonoHelper.toLevel0DTO(entidad.getTipoTelefono()));
        if (entidad.getTipoFuenteInformacion() != null)
            dto.setTipoFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteInformacion()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getTipoFuenteValidacion() != null)
            dto.setTipoFuenteValidacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteValidacion()));
        if (entidad.getUsuarioValida() != null)
            dto.setUsuarioValida(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioValida()));
        if (entidad.getUsuarioRegistro() != null)
            dto.setUsuarioRegistro(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistro()));
        if (entidad.getUsuarioActualiza() != null)
            dto.setUsuarioActualiza(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioActualiza()));

        return dto;
    }

    public static List<TelefonoPersonaDTO> toListLevel0DTO(List<TelefonoPersona> listEntidad) {
        List<TelefonoPersonaDTO> listDto = new ArrayList<TelefonoPersonaDTO>();
        for (TelefonoPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TelefonoPersonaDTO> toListLevel1DTO(List<TelefonoPersona> listEntidad) {
        List<TelefonoPersonaDTO> listDto = new ArrayList<TelefonoPersonaDTO>();
        for (TelefonoPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TelefonoPersona toLevel0Entity(TelefonoPersonaDTO dto, TelefonoPersona entidad) {
        if (null == entidad) {
            entidad = new TelefonoPersona();
        }
        entidad.setId(dto.getId());
        entidad.setEstado(dto.getEstado());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setNumeroTelefono(dto.getNumeroTelefono());
        entidad.setPrioridad(dto.getPrioridad());
        entidad.setFechaValidacion(dto.getFechaValidacion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setAutorizadoNotificacion(dto.getAutorizadoNotificacion());

        return entidad;
    }

    public static TelefonoPersona toLevel1Entity(TelefonoPersonaDTO dto, TelefonoPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getScoreUbicabilidad() != null) {
            entidad.setScoreUbicabilidad(new ScoreUbicabilidad());
            entidad.getScoreUbicabilidad().setId(dto.getScoreUbicabilidad().getId());
        }
        if (dto.getTipoTelefono() != null) {
            entidad.setTipoTelefono(new TipoTelefono());
            entidad.getTipoTelefono().setId(dto.getTipoTelefono().getId());
        }
        if (dto.getTipoFuenteInformacion() != null) {
            entidad.setTipoFuenteInformacion(new TipoFuenteInformacion());
            entidad.getTipoFuenteInformacion().setId(dto.getTipoFuenteInformacion().getId());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getTipoFuenteValidacion() != null) {
            entidad.setTipoFuenteValidacion(new TipoFuenteInformacion());
            entidad.getTipoFuenteValidacion().setId(dto.getTipoFuenteValidacion().getId());
        }

        if (dto.getUsuarioValida() != null) {
            entidad.setUsuarioValida(new UsuarioPersona());
            entidad.getUsuarioValida().setIdUsuario(dto.getUsuarioValida().getUsuario().getId());
        }

        if (dto.getUsuarioRegistro() != null) {
            entidad.setUsuarioRegistro(new UsuarioPersona());
            entidad.getUsuarioRegistro().setIdUsuario(dto.getUsuarioRegistro().getUsuario().getId());
        }

        if (dto.getUsuarioActualiza() != null) {
            entidad.setUsuarioActualiza(new UsuarioPersona());
            entidad.getUsuarioActualiza().setIdUsuario(dto.getUsuarioActualiza().getUsuario().getId());
        }

        return entidad;
    }

    public static List<TelefonoPersona> toListLevel0Entity(List<TelefonoPersonaDTO> listDto) {
        List<TelefonoPersona> listEntidad = new ArrayList<TelefonoPersona>();
        for (TelefonoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TelefonoPersona> toListLevel1Entity(List<TelefonoPersonaDTO> listDto) {
        List<TelefonoPersona> listEntidad = new ArrayList<TelefonoPersona>();
        for (TelefonoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
