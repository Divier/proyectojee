package co.com.datatools.c2.negocio.helpers.comun;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto;

/**
 * Este Helper no se puede modificar <br/>
 * 
 * Clase que permite la transformación entre objetos DTO y Entidad para UsuarioPersona
 * 
 * @author giovanni.velandia (mod 30-03-2016)
 * @version 3.0 - Mon Jul 14 11:24:14 COT 2014
 */
public class UsuarioPersonaHelper {
    // --- to DTO
    /**
     * Transforma una entidad UsuarioPersona a su UsuarioPersonaDTO asignando el id y login a su DTO:UsuarioDetalleDto
     * 
     * @param entidad
     *            UsuarioPersona a transformar
     * @return UsuarioPersonaDTO con los datos de la entidad
     */
    public static UsuarioPersonaDTO toLevel0DTO(UsuarioPersona entidad) {
        UsuarioPersonaDTO dto = new UsuarioPersonaDTO();
        dto.setLogin(entidad.getLogin());
        UsuarioDetalleDto usuarioDetalleDto = new UsuarioDetalleDto();
        usuarioDetalleDto.setId(entidad.getIdUsuario());
        usuarioDetalleDto.setLogin(entidad.getLogin());
        dto.setUsuario(usuarioDetalleDto);
        return dto;
    }

    /**
     * Transforma una entidad UsuarioPersona a su UsuarioPersonaDTO invocando primero a toLevel0DTO asignando el id y login a su
     * DTO:UsuarioDetalleDto, luego invoca a PersonaHelper.toLevel0DTO para cargar los datos de la persona a la persona
     * 
     * @param entidad
     *            Entidad UsuarioPersona a transformar
     * @return UsuarioPersonaDTO con los datos de UsuarioPersona
     */
    public static UsuarioPersonaDTO toLevel1DTO(UsuarioPersona entidad) {
        UsuarioPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersona() != null) {
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        }
        return dto;
    }

    /**
     * Transforma una lista de entidades UsuarioPersona a una lista con sus correspondientes iterando para invocar a toLevel0DTO
     * 
     * @param listEntidad
     *            Lista de UsuarioPersona a transformar
     * @return Lista de UsuarioPersonaDTO con los datos de las entidades
     */
    public static List<UsuarioPersonaDTO> toListLevel0DTO(List<UsuarioPersona> listEntidad) {
        List<UsuarioPersonaDTO> listDto = new ArrayList<UsuarioPersonaDTO>();
        for (UsuarioPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    /**
     * Transforma una lista de entidades UsuarioPersona a una lista con sus correspondientes UsuarioPersonaDTO iterando para invocar a toLevel1DTO
     * 
     * @param listEntidad
     *            Lisat de entidades UsuarioPersona a transformar
     * @return Lista con los UsuarioPersonaDTO de las entidades recibidas
     */
    public static List<UsuarioPersonaDTO> toListLevel1DTO(List<UsuarioPersona> listEntidad) {
        List<UsuarioPersonaDTO> listDto = new ArrayList<UsuarioPersonaDTO>();
        for (UsuarioPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static UsuarioPersona toLevel0Entity(UsuarioPersonaDTO dto, UsuarioPersona entidad) {
        if (null == entidad) {
            entidad = new UsuarioPersona();
        }
        entidad.setIdUsuario(dto.getUsuario().getId());
        entidad.setLogin(dto.getLogin());

        return entidad;
    }

    public static UsuarioPersona toLevel1Entity(UsuarioPersonaDTO dto, UsuarioPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }

        return entidad;
    }

    public static List<UsuarioPersona> toListLevel0Entity(List<UsuarioPersonaDTO> listDto) {
        List<UsuarioPersona> listEntidad = new ArrayList<UsuarioPersona>();
        for (UsuarioPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<UsuarioPersona> toListLevel1Entity(List<UsuarioPersonaDTO> listDto) {
        List<UsuarioPersona> listEntidad = new ArrayList<UsuarioPersona>();
        for (UsuarioPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
