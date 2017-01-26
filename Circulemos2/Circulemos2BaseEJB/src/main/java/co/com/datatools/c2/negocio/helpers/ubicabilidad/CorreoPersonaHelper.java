package co.com.datatools.c2.negocio.helpers.ubicabilidad;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.ubicabilidad.CorreoPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.ScoreUbicabilidadHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:46 COT 2016
 */
public class CorreoPersonaHelper {
    // --- to DTO
    public static CorreoPersonaDTO toLevel0DTO(CorreoPersona entidad) {
        CorreoPersonaDTO dto = new CorreoPersonaDTO();
        dto.setId(entidad.getId());
        dto.setEstado(entidad.getEstado());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setPrioridad(entidad.getPrioridad());
        dto.setFechaValidacion(entidad.getFechaValidacion());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setAutorizadoNotificacion(entidad.getAutorizadoNotificacion());

        return dto;
    }

    public static CorreoPersonaDTO toLevel1DTO(CorreoPersona entidad) {
        CorreoPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getScoreUbicabilidad() != null)
            dto.setScoreUbicabilidad(ScoreUbicabilidadHelper.toLevel0DTO(entidad.getScoreUbicabilidad()));
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

    public static List<CorreoPersonaDTO> toListLevel0DTO(List<CorreoPersona> listEntidad) {
        List<CorreoPersonaDTO> listDto = new ArrayList<CorreoPersonaDTO>();
        for (CorreoPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<CorreoPersonaDTO> toListLevel1DTO(List<CorreoPersona> listEntidad) {
        List<CorreoPersonaDTO> listDto = new ArrayList<CorreoPersonaDTO>();
        for (CorreoPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static CorreoPersona toLevel0Entity(CorreoPersonaDTO dto, CorreoPersona entidad) {
        if (null == entidad) {
            entidad = new CorreoPersona();
        }
        entidad.setId(dto.getId());
        entidad.setEstado(dto.getEstado());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setPrioridad(dto.getPrioridad());
        entidad.setFechaValidacion(dto.getFechaValidacion());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());
        entidad.setAutorizadoNotificacion(dto.getAutorizadoNotificacion());

        return entidad;
    }

    public static CorreoPersona toLevel1Entity(CorreoPersonaDTO dto, CorreoPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getScoreUbicabilidad() != null) {
            entidad.setScoreUbicabilidad(new ScoreUbicabilidad());
            entidad.getScoreUbicabilidad().setId(dto.getScoreUbicabilidad().getId());
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

    public static List<CorreoPersona> toListLevel0Entity(List<CorreoPersonaDTO> listDto) {
        List<CorreoPersona> listEntidad = new ArrayList<CorreoPersona>();
        for (CorreoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<CorreoPersona> toListLevel1Entity(List<CorreoPersonaDTO> listDto) {
        List<CorreoPersona> listEntidad = new ArrayList<CorreoPersona>();
        for (CorreoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
