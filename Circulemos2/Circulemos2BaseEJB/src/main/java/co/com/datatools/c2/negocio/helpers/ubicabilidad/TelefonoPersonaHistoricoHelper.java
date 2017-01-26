package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaHistoricoDTO;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.TelefonoPersonaHistorico;
import co.com.datatools.c2.entidades.ubicabilidad.TipoTelefono;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ScoreUbicabilidadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 15 11:05:23 COT 2016
 */
public class TelefonoPersonaHistoricoHelper {
    // --- to DTO
    public static TelefonoPersonaHistoricoDTO toLevel0DTO(TelefonoPersonaHistorico entidad) {
        TelefonoPersonaHistoricoDTO dto = new TelefonoPersonaHistoricoDTO();
        dto.setId(entidad.getId());
        dto.setEstado(entidad.getEstado());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setNumeroTelefono(entidad.getNumeroTelefono());
        dto.setPrioridad(entidad.getPrioridad());
        dto.setFechaValidacion(entidad.getFechaValidacion());
        dto.setAutorizadoNotificacion(entidad.getAutorizadoNotificacion());

        return dto;
    }

    public static TelefonoPersonaHistoricoDTO toLevel1DTO(TelefonoPersonaHistorico entidad) {
        TelefonoPersonaHistoricoDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoFuenteValidacion() != null)
            dto.setTipoFuenteValidacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteValidacion()));
        if (entidad.getUsuarioValida() != null)
            dto.setUsuarioValida(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioValida()));
        if (entidad.getScoreUbicabilidad() != null)
            dto.setScoreUbicabilidad(ScoreUbicabilidadHelper.toLevel0DTO(entidad.getScoreUbicabilidad()));
        if (entidad.getTipoTelefono() != null)
            dto.setTipoTelefono(TipoTelefonoHelper.toLevel0DTO(entidad.getTipoTelefono()));
        if (entidad.getTipoFuenteInformacion() != null)
            dto.setTipoFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteInformacion()));
        if (entidad.getUsuarioRegistro() != null)
            dto.setUsuarioRegistro(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistro()));
        if (entidad.getTelefonoPersona() != null)
            dto.setTelefonoPersona(TelefonoPersonaHelper.toLevel0DTO(entidad.getTelefonoPersona()));

        return dto;
    }

    public static List<TelefonoPersonaHistoricoDTO> toListLevel0DTO(List<TelefonoPersonaHistorico> listEntidad) {
        List<TelefonoPersonaHistoricoDTO> listDto = new ArrayList<TelefonoPersonaHistoricoDTO>();
        for (TelefonoPersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<TelefonoPersonaHistoricoDTO> toListLevel1DTO(List<TelefonoPersonaHistorico> listEntidad) {
        List<TelefonoPersonaHistoricoDTO> listDto = new ArrayList<TelefonoPersonaHistoricoDTO>();
        for (TelefonoPersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static TelefonoPersonaHistorico toLevel0Entity(TelefonoPersonaHistoricoDTO dto,
            TelefonoPersonaHistorico entidad) {
        if (null == entidad) {
            entidad = new TelefonoPersonaHistorico();
        }
        entidad.setId(dto.getId());
        entidad.setEstado(dto.getEstado());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setNumeroTelefono(dto.getNumeroTelefono());
        entidad.setPrioridad(dto.getPrioridad());
        entidad.setFechaValidacion(dto.getFechaValidacion());
        entidad.setAutorizadoNotificacion(dto.getAutorizadoNotificacion());

        return entidad;
    }

    public static TelefonoPersonaHistorico toLevel1Entity(TelefonoPersonaHistoricoDTO dto,
            TelefonoPersonaHistorico entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoFuenteValidacion() != null) {
            entidad.setTipoFuenteValidacion(new TipoFuenteInformacion());
            entidad.getTipoFuenteValidacion().setId(dto.getTipoFuenteValidacion().getId());
        }
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
        if (dto.getUsuarioValida() != null) {
            entidad.setUsuarioValida(new UsuarioPersona());
            entidad.getUsuarioValida().setIdUsuario(dto.getUsuarioValida().getUsuario().getId());
        }
        if (dto.getUsuarioRegistro() != null) {
            entidad.setUsuarioRegistro(new UsuarioPersona());
            entidad.getUsuarioRegistro().setIdUsuario(dto.getUsuarioRegistro().getUsuario().getId());
        }

        if (dto.getTelefonoPersona() != null) {
            entidad.setTelefonoPersona(new TelefonoPersona());
            entidad.getTelefonoPersona().setId(dto.getTelefonoPersona().getId());
        }

        return entidad;
    }

    public static List<TelefonoPersonaHistorico> toListLevel0Entity(List<TelefonoPersonaHistoricoDTO> listDto) {
        List<TelefonoPersonaHistorico> listEntidad = new ArrayList<TelefonoPersonaHistorico>();
        for (TelefonoPersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<TelefonoPersonaHistorico> toListLevel1Entity(List<TelefonoPersonaHistoricoDTO> listDto) {
        List<TelefonoPersonaHistorico> listEntidad = new ArrayList<TelefonoPersonaHistorico>();
        for (TelefonoPersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
