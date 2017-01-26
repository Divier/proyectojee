package co.com.datatools.c2.negocio.helpers.personas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.personas.ArchivoPersonaDTO;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.ArchivoPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.ubicabilidad.TipoArchivoPersona;
import co.com.datatools.c2.negocio.helpers.comun.UsuarioPersonaHelper;
import co.com.datatools.c2.negocio.helpers.ubicabilidad.TipoArchivoPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 12:15:44 COT 2016
 */
public class ArchivoPersonaHelper {
    // --- to DTO
    public static ArchivoPersonaDTO toLevel0DTO(ArchivoPersona entidad) {
        ArchivoPersonaDTO dto = new ArchivoPersonaDTO();
        dto.setId(entidad.getId());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setNumeroArchivo(entidad.getNumeroArchivo());
        dto.setPagina(entidad.getPagina());

        return dto;
    }

    public static ArchivoPersonaDTO toLevel1DTO(ArchivoPersona entidad) {
        ArchivoPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoArchivoPersona() != null)
            dto.setTipoArchivoPersona(TipoArchivoPersonaHelper.toLevel0DTO(entidad.getTipoArchivoPersona()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getTipoFuenteInformacion() != null)
            dto.setTipoFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getTipoFuenteInformacion()));
        if (entidad.getUsuarioRegistro() != null)
            dto.setUsuarioRegistro(UsuarioPersonaHelper.toLevel0DTO(entidad.getUsuarioRegistro()));

        return dto;
    }

    public static List<ArchivoPersonaDTO> toListLevel0DTO(List<ArchivoPersona> listEntidad) {
        List<ArchivoPersonaDTO> listDto = new ArrayList<ArchivoPersonaDTO>();
        for (ArchivoPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ArchivoPersonaDTO> toListLevel1DTO(List<ArchivoPersona> listEntidad) {
        List<ArchivoPersonaDTO> listDto = new ArrayList<ArchivoPersonaDTO>();
        for (ArchivoPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ArchivoPersona toLevel0Entity(ArchivoPersonaDTO dto, ArchivoPersona entidad) {
        if (null == entidad) {
            entidad = new ArchivoPersona();
        }
        entidad.setId(dto.getId());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setNumeroArchivo(dto.getNumeroArchivo());
        entidad.setPagina(dto.getPagina());

        return entidad;
    }

    public static ArchivoPersona toLevel1Entity(ArchivoPersonaDTO dto, ArchivoPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoArchivoPersona() != null) {
            entidad.setTipoArchivoPersona(new TipoArchivoPersona());
            entidad.getTipoArchivoPersona().setId(dto.getTipoArchivoPersona().getId());
        }
        if (dto.getTipoFuenteInformacion() != null) {
            entidad.setTipoFuenteInformacion(new TipoFuenteInformacion());
            entidad.getTipoFuenteInformacion().setId(dto.getTipoFuenteInformacion().getId());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getUsuarioRegistro() != null) {
            entidad.setUsuarioRegistro(new UsuarioPersona());
            entidad.getUsuarioRegistro().setIdUsuario(dto.getUsuarioRegistro().getUsuario().getId());
        }

        return entidad;
    }

    public static List<ArchivoPersona> toListLevel0Entity(List<ArchivoPersonaDTO> listDto) {
        List<ArchivoPersona> listEntidad = new ArrayList<ArchivoPersona>();
        for (ArchivoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ArchivoPersona> toListLevel1Entity(List<ArchivoPersonaDTO> listDto) {
        List<ArchivoPersona> listEntidad = new ArrayList<ArchivoPersona>();
        for (ArchivoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
