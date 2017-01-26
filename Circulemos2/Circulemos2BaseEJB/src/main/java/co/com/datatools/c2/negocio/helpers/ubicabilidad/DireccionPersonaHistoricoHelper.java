package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaHistoricoDTO;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersonaHistorico;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ScoreUbicabilidadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;

/**
 * @author Generated
 * @version 3.0 - Tue Nov 15 11:05:20 COT 2016
 */
public class DireccionPersonaHistoricoHelper {
    // --- to DTO
    public static DireccionPersonaHistoricoDTO toLevel0DTO(DireccionPersonaHistorico entidad) {
        DireccionPersonaHistoricoDTO dto = new DireccionPersonaHistoricoDTO();
        dto.setId(entidad.getId());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setEstado(entidad.getEstado());
        dto.setPrioridad(entidad.getPrioridad());
        dto.setFechaValidacion(entidad.getFechaValidacion());
        dto.setAutorizadoNotificacion(entidad.getAutorizadoNotificacion());

        return dto;
    }

    public static DireccionPersonaHistoricoDTO toLevel1DTO(DireccionPersonaHistorico entidad) {
        DireccionPersonaHistoricoDTO dto = toLevel0DTO(entidad);
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));
        if (entidad.getTipoFuenteInformacion() != null)
            dto.setTipoFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteInformacion()));
        if (entidad.getScoreUbicabilidad() != null)
            dto.setScoreUbicabilidad(ScoreUbicabilidadHelper.toLevel0DTO(entidad.getScoreUbicabilidad()));
        if (entidad.getTipoFuenteValidacion() != null)
            dto.setTipoFuenteValidacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteValidacion()));
        if (entidad.getUsuarioValida() != null)
            dto.setUsuarioValida(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioValida()));
        if (entidad.getUsuarioRegistro() != null)
            dto.setUsuarioRegistro(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistro()));
        if (entidad.getDireccionPersona() != null)
            dto.setDireccionPersona(DireccionPersonaHelper.toLevel0DTO(entidad.getDireccionPersona()));

        return dto;
    }

    public static List<DireccionPersonaHistoricoDTO> toListLevel0DTO(List<DireccionPersonaHistorico> listEntidad) {
        List<DireccionPersonaHistoricoDTO> listDto = new ArrayList<DireccionPersonaHistoricoDTO>();
        for (DireccionPersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DireccionPersonaHistoricoDTO> toListLevel1DTO(List<DireccionPersonaHistorico> listEntidad) {
        List<DireccionPersonaHistoricoDTO> listDto = new ArrayList<DireccionPersonaHistoricoDTO>();
        for (DireccionPersonaHistorico entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DireccionPersonaHistorico toLevel0Entity(DireccionPersonaHistoricoDTO dto,
            DireccionPersonaHistorico entidad) {
        if (null == entidad) {
            entidad = new DireccionPersonaHistorico();
        }
        entidad.setId(dto.getId());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setEstado(dto.getEstado());
        entidad.setPrioridad(dto.getPrioridad());
        entidad.setFechaValidacion(dto.getFechaValidacion());
        entidad.setAutorizadoNotificacion(dto.getAutorizadoNotificacion());

        return entidad;
    }

    public static DireccionPersonaHistorico toLevel1Entity(DireccionPersonaHistoricoDTO dto,
            DireccionPersonaHistorico entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }
        if (dto.getTipoFuenteInformacion() != null) {
            entidad.setTipoFuenteInformacion(new TipoFuenteInformacion());
            entidad.getTipoFuenteInformacion().setId(dto.getTipoFuenteInformacion().getId());
        }
        if (dto.getScoreUbicabilidad() != null) {
            entidad.setScoreUbicabilidad(new ScoreUbicabilidad());
            entidad.getScoreUbicabilidad().setId(dto.getScoreUbicabilidad().getId());
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
        if (dto.getDireccionPersona() != null) {
            entidad.setDireccionPersona(new DireccionPersona());
            entidad.getDireccionPersona().setId(dto.getDireccionPersona().getId());
        }

        return entidad;
    }

    public static List<DireccionPersonaHistorico> toListLevel0Entity(List<DireccionPersonaHistoricoDTO> listDto) {
        List<DireccionPersonaHistorico> listEntidad = new ArrayList<DireccionPersonaHistorico>();
        for (DireccionPersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DireccionPersonaHistorico> toListLevel1Entity(List<DireccionPersonaHistoricoDTO> listDto) {
        List<DireccionPersonaHistorico> listEntidad = new ArrayList<DireccionPersonaHistorico>();
        for (DireccionPersonaHistoricoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
