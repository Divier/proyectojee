package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.ubicabilidad.DireccionPersona;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ScoreUbicabilidadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DireccionPersonaHelper {
    // --- to DTO
    public static DireccionPersonaDTO toLevel0DTO(DireccionPersona entidad) {
        DireccionPersonaDTO dto = new DireccionPersonaDTO();
        dto.setId(entidad.getId());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setEstado(entidad.getEstado());
        dto.setPrioridad(entidad.getPrioridad());
        dto.setFechaValidacion(entidad.getFechaValidacion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setAutorizadoNotificacion(entidad.getAutorizadoNotificacion());

        return dto;
    }

    public static DireccionPersonaDTO toLevel1DTO(DireccionPersona entidad) {
        DireccionPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
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
        if (entidad.getUsuarioActualiza() != null)
            dto.setUsuarioActualiza(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioActualiza()));

        return dto;
    }

    public static List<DireccionPersonaDTO> toListLevel0DTO(List<DireccionPersona> listEntidad) {
        List<DireccionPersonaDTO> listDto = new ArrayList<DireccionPersonaDTO>();
        for (DireccionPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DireccionPersonaDTO> toListLevel1DTO(List<DireccionPersona> listEntidad) {
        List<DireccionPersonaDTO> listDto = new ArrayList<DireccionPersonaDTO>();
        for (DireccionPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DireccionPersona toLevel0Entity(DireccionPersonaDTO dto, DireccionPersona entidad) {
        if (null == entidad) {
            entidad = new DireccionPersona();
        }
        entidad.setId(dto.getId());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setEstado(dto.getEstado());
        entidad.setPrioridad(dto.getPrioridad());
        entidad.setFechaValidacion(dto.getFechaValidacion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setAutorizadoNotificacion(dto.getAutorizadoNotificacion());

        return entidad;
    }

    public static DireccionPersona toLevel1Entity(DireccionPersonaDTO dto, DireccionPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getTipoFuenteInformacion() != null) {
            entidad.setTipoFuenteInformacion(new TipoFuenteInformacion());
            entidad.getTipoFuenteInformacion().setId(dto.getTipoFuenteInformacion().getId());
        }
        if (dto.getScoreUbicabilidad() != null) {
            entidad.setScoreUbicabilidad(new ScoreUbicabilidad(dto.getScoreUbicabilidad().getId()));
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

    public static List<DireccionPersona> toListLevel0Entity(List<DireccionPersonaDTO> listDto) {
        List<DireccionPersona> listEntidad = new ArrayList<DireccionPersona>();
        for (DireccionPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DireccionPersona> toListLevel1Entity(List<DireccionPersonaDTO> listDto) {
        List<DireccionPersona> listEntidad = new ArrayList<DireccionPersona>();
        for (DireccionPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
