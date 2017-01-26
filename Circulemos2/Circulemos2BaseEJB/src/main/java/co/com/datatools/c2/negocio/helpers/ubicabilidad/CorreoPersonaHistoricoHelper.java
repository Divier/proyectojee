package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaHistoricoDTO;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersona;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersonaHistorico;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ScoreUbicabilidadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 15 11:05:22 COT 2016
 */
public class CorreoPersonaHistoricoHelper {
    // --- to DTO
    public static CorreoPersonaHistoricoDTO toLevel0DTO(CorreoPersonaHistorico entidad) {
        CorreoPersonaHistoricoDTO dto = new CorreoPersonaHistoricoDTO();
        dto.setId(entidad.getId());
        dto.setEstado(entidad.getEstado());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setPrioridad(entidad.getPrioridad());
        dto.setFechaValidacion(entidad.getFechaValidacion());
        dto.setAutorizadoNotificacion(entidad.getAutorizadoNotificacion());

        return dto;
    }

    public static CorreoPersonaHistoricoDTO toLevel1DTO(CorreoPersonaHistorico entidad) {
        CorreoPersonaHistoricoDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoFuenteValidacion() != null)
            dto.setTipoFuenteValidacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteValidacion()));
        if (entidad.getUsuarioValida() != null)
            dto.setUsuarioValida(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioValida()));
        if (entidad.getScoreUbicabilidad() != null)
            dto.setScoreUbicabilidad(ScoreUbicabilidadHelper.toLevel0DTO(entidad.getScoreUbicabilidad()));
        if (entidad.getTipoFuenteInformacion() != null)
            dto.setTipoFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteInformacion()));
        if (entidad.getUsuarioRegistro() != null)
            dto.setUsuarioRegistro(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistro()));
        if (entidad.getCorreoPersona() != null)
            dto.setCorreoPersona(CorreoPersonaHelper.toLevel0DTO(entidad.getCorreoPersona()));

        return dto;
    }

    public static List<CorreoPersonaHistoricoDTO> toListLevel0DTO(List<CorreoPersonaHistorico> listEntidad) {
        List<CorreoPersonaHistoricoDTO> listDto = new ArrayList<CorreoPersonaHistoricoDTO>();
        for (CorreoPersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CorreoPersonaHistoricoDTO> toListLevel1DTO(List<CorreoPersonaHistorico> listEntidad) {
        List<CorreoPersonaHistoricoDTO> listDto = new ArrayList<CorreoPersonaHistoricoDTO>();
        for (CorreoPersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CorreoPersonaHistorico toLevel0Entity(CorreoPersonaHistoricoDTO dto, CorreoPersonaHistorico entidad) {
        if (null == entidad) {
            entidad = new CorreoPersonaHistorico();
        }
        entidad.setId(dto.getId());
        entidad.setEstado(dto.getEstado());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setPrioridad(dto.getPrioridad());
        entidad.setFechaValidacion(dto.getFechaValidacion());
        entidad.setAutorizadoNotificacion(dto.getAutorizadoNotificacion());

        return entidad;
    }

    public static CorreoPersonaHistorico toLevel1Entity(CorreoPersonaHistoricoDTO dto, CorreoPersonaHistorico entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoFuenteValidacion() != null) {
            entidad.setTipoFuenteValidacion(new TipoFuenteInformacion());
            entidad.getTipoFuenteValidacion().setId(dto.getTipoFuenteValidacion().getId());
        }
        if (dto.getScoreUbicabilidad() != null) {
            entidad.setScoreUbicabilidad(new ScoreUbicabilidad());
            entidad.getScoreUbicabilidad().setId(dto.getScoreUbicabilidad().getId());
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
        if (dto.getCorreoPersona() != null) {
            entidad.setCorreoPersona(new CorreoPersona());
            entidad.getCorreoPersona().setId(dto.getCorreoPersona().getId());
        }

        return entidad;
    }

    public static List<CorreoPersonaHistorico> toListLevel0Entity(List<CorreoPersonaHistoricoDTO> listDto) {
        List<CorreoPersonaHistorico> listEntidad = new ArrayList<CorreoPersonaHistorico>();
        for (CorreoPersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CorreoPersonaHistorico> toListLevel1Entity(List<CorreoPersonaHistoricoDTO> listDto) {
        List<CorreoPersonaHistorico> listEntidad = new ArrayList<CorreoPersonaHistorico>();
        for (CorreoPersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
