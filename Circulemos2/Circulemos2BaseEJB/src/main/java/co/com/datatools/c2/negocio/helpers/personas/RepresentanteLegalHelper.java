package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.PersonaJuridica;
import co.com.datatools.c2.entidades.personas.RepresentanteLegal;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class RepresentanteLegalHelper {
    // --- to DTO
    public static RepresentanteLegalDTO toLevel0DTO(RepresentanteLegal entidad) {
        RepresentanteLegalDTO dto = new RepresentanteLegalDTO();
        dto.setIdRepresentante(entidad.getId());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());
        // Adicion de campos para traza de representante
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());

        return dto;
    }

    public static RepresentanteLegalDTO toLevel1DTO(RepresentanteLegal entidad) {
        RepresentanteLegalDTO dto = toLevel0DTO(entidad);
        // TODO FIX Helper
        // if (entidad.getPersona() != null)
        // dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getPersonaJuridica() != null)
            dto.setPersonaJuridica(PersonaJuridicaHelper.toLevel0DTO(entidad.getPersonaJuridica()));
        // Adicion de campos para traza de representante
        if (entidad.getUsuarioRegistro() != null) {
            dto.setUsuarioRegistro(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistro()));
        }
        if (entidad.getUsuarioActualiza() != null) {
            dto.setUsuarioActualiza(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioActualiza()));
        }

        return dto;
    }

    public static List<RepresentanteLegalDTO> toListLevel0DTO(List<RepresentanteLegal> listEntidad) {
        List<RepresentanteLegalDTO> listDto = new ArrayList<RepresentanteLegalDTO>();
        for (RepresentanteLegal entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<RepresentanteLegalDTO> toListLevel1DTO(List<RepresentanteLegal> listEntidad) {
        List<RepresentanteLegalDTO> listDto = new ArrayList<RepresentanteLegalDTO>();
        for (RepresentanteLegal entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static RepresentanteLegal toLevel0Entity(RepresentanteLegalDTO dto, RepresentanteLegal entidad) {
        if (null == entidad) {
            entidad = new RepresentanteLegal();
        }

        entidad.setId(dto.getIdRepresentante());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());
        // Adicion de campos para traza de representante
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaActualizacion(dto.getFechaActualizacion());

        return entidad;
    }

    public static RepresentanteLegal toLevel1Entity(RepresentanteLegalDTO dto, RepresentanteLegal entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getId() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getId());
        }
        if (dto.getPersonaJuridica() != null) {
            entidad.setPersonaJuridica(new PersonaJuridica());
            entidad.getPersonaJuridica().setIdPersonaJuridica(dto.getPersonaJuridica().getId());
        }
        // Adicion de campos para traza de representante
        if (dto.getUsuarioRegistro() != null && dto.getUsuarioRegistro().getUsuario() != null) {
            entidad.setUsuarioRegistro(new UsuarioPersona(dto.getUsuarioRegistro().getUsuario().getId()));
        }
        if (dto.getUsuarioActualiza() != null && dto.getUsuarioActualiza().getUsuario() != null) {
            entidad.setUsuarioActualiza(new UsuarioPersona(dto.getUsuarioActualiza().getUsuario().getId()));
        }

        return entidad;
    }

    public static List<RepresentanteLegal> toListLevel0Entity(List<RepresentanteLegalDTO> listDto) {
        List<RepresentanteLegal> listEntidad = new ArrayList<RepresentanteLegal>();
        for (RepresentanteLegalDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<RepresentanteLegal> toListLevel1Entity(List<RepresentanteLegalDTO> listDto) {
        List<RepresentanteLegal> listEntidad = new ArrayList<RepresentanteLegal>();
        for (RepresentanteLegalDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
