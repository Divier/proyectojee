package co.com.datatools.c2.negocio.helpers.v2;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.PersonaFuenteExternaDTO;
import co.com.datatools.c2.entidades.personas.EstadoCivil;
import co.com.datatools.c2.entidades.personas.NivelEducativo;
import co.com.datatools.c2.entidades.personas.PersonaFuenteExterna;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.entidades.personas.TipoVivienda;
import co.com.datatools.c2.negocio.helpers.personas.EstadoCivilHelper;
import co.com.datatools.c2.negocio.helpers.personas.NivelEducativoHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoFuenteInformacionHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoIdentificacionPersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoViviendaHelper;
import co.com.datatools.c2.util.Utilidades;

/**
 * @author luis.forero (mod 2014-11-19)
 * @version 3.0 - Wed Nov 19 11:28:21 COT 2014
 */
public class PersonaFuenteExternaHelper {
    // --- to DTO
    public static PersonaFuenteExternaDTO toLevel0DTO(PersonaFuenteExterna entidad) {
        PersonaFuenteExternaDTO dto = new PersonaFuenteExternaDTO();
        dto.setId(entidad.getId());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setFechaUltimaActualizacion(entidad.getFechaUltimaActualizacion());
        dto.setFechaExpedicionDocumento(entidad.getFechaExpedicionDocumento());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        if (entidad.getFechaNacimiento() != null) {
            dto.setEdad(Utilidades.calcularEdad(entidad.getFechaNacimiento()));
        }
        dto.setFechaFallecimiento(entidad.getFechaFallecimiento());
        dto.setNumeroTelefonico(entidad.getNumeroTelefonico());
        dto.setGenero(entidad.getGenero());
        dto.setApellido1(entidad.getApellido1());
        dto.setApellido2(entidad.getApellido2());
        dto.setNombre1(entidad.getNombre1());
        dto.setNombre2(entidad.getNombre2());
        dto.setCorreoElectronico(entidad.getCorreoElectronico());
        dto.setNumeroCelular(entidad.getNumeroCelular());
        dto.setNombreEmpresaLabora(entidad.getNombreEmpresaLabora());
        dto.setNotificaDirEmpresa(entidad.getNotificaDirEmpresa());
        dto.setCargoEmpresaLabora(entidad.getCargoEmpresaLabora());
        dto.setFaxEmpresaLabora(entidad.getFaxEmpresaLabora());
        dto.setHuellaDigital(entidad.getHuellaDigital());
        dto.setFoto(entidad.getFoto());

        return dto;
    }

    public static PersonaFuenteExternaDTO toLevel1DTO(PersonaFuenteExterna entidad) {
        PersonaFuenteExternaDTO dto = toLevel0DTO(entidad);
        if (entidad.getTipoIdentificacion() != null)
            dto.setTipoIdentificacion(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacion()));
        if (entidad.getFuenteInformacion() != null)
            dto.setFuenteInformacion(TipoFuenteInformacionHelper.toLevel0DTO(entidad.getFuenteInformacion()));
        if (entidad.getEstadoCivil() != null)
            dto.setEstadoCivil(EstadoCivilHelper.toLevel0DTO(entidad.getEstadoCivil()));
        if (entidad.getTipoVivienda() != null)
            dto.setTipoVivienda(TipoViviendaHelper.toLevel0DTO(entidad.getTipoVivienda()));
        if (entidad.getNivelEducativo() != null)
            dto.setNivelEducativo(NivelEducativoHelper.toLevel0DTO(entidad.getNivelEducativo()));

        return dto;
    }

    public static List<PersonaFuenteExternaDTO> toListLevel0DTO(List<PersonaFuenteExterna> listEntidad) {
        List<PersonaFuenteExternaDTO> listDto = new ArrayList<PersonaFuenteExternaDTO>();
        for (PersonaFuenteExterna entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PersonaFuenteExternaDTO> toListLevel1DTO(List<PersonaFuenteExterna> listEntidad) {
        List<PersonaFuenteExternaDTO> listDto = new ArrayList<PersonaFuenteExternaDTO>();
        for (PersonaFuenteExterna entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static PersonaFuenteExterna toLevel0Entity(PersonaFuenteExternaDTO dto, PersonaFuenteExterna entidad) {
        if (null == entidad) {
            entidad = new PersonaFuenteExterna();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entidad.setFechaUltimaActualizacion(dto.getFechaUltimaActualizacion());
        entidad.setFechaExpedicionDocumento(dto.getFechaExpedicionDocumento());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        entidad.setFechaFallecimiento(dto.getFechaFallecimiento());
        entidad.setNumeroTelefonico(dto.getNumeroTelefonico());
        entidad.setGenero(dto.getGenero());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setCorreoElectronico(dto.getCorreoElectronico());
        entidad.setNumeroCelular(dto.getNumeroCelular());
        entidad.setNombreEmpresaLabora(dto.getNombreEmpresaLabora());
        entidad.setNotificaDirEmpresa(dto.getNotificaDirEmpresa());
        entidad.setCargoEmpresaLabora(dto.getCargoEmpresaLabora());
        entidad.setFaxEmpresaLabora(dto.getFaxEmpresaLabora());
        entidad.setHuellaDigital(dto.getHuellaDigital());
        entidad.setFoto(dto.getFoto());

        return entidad;
    }

    public static PersonaFuenteExterna toLevel1Entity(PersonaFuenteExternaDTO dto, PersonaFuenteExterna entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getTipoIdentificacion() != null) {
            entidad.setTipoIdentificacion(new TipoIdentificacionPersona());
            entidad.getTipoIdentificacion().setId(dto.getTipoIdentificacion().getId());
        }
        if (dto.getFuenteInformacion() != null) {
            entidad.setFuenteInformacion(new TipoFuenteInformacion());
            entidad.getFuenteInformacion().setCodigo(dto.getFuenteInformacion().getCodigo());
        }
        if (dto.getEstadoCivil() != null) {
            entidad.setEstadoCivil(new EstadoCivil());
            entidad.getEstadoCivil().setId(dto.getEstadoCivil().getId());
        }
        if (dto.getTipoVivienda() != null) {
            entidad.setTipoVivienda(new TipoVivienda());
            entidad.getTipoVivienda().setCodigo(dto.getTipoVivienda().getCodigo());
        }
        if (dto.getNivelEducativo() != null) {
            entidad.setNivelEducativo(new NivelEducativo());
            entidad.getNivelEducativo().setCodigo(dto.getNivelEducativo().getCodigo());
        }

        return entidad;
    }

    public static List<PersonaFuenteExterna> toListLevel0Entity(List<PersonaFuenteExternaDTO> listDto) {
        List<PersonaFuenteExterna> listEntidad = new ArrayList<PersonaFuenteExterna>();
        for (PersonaFuenteExternaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<PersonaFuenteExterna> toListLevel1Entity(List<PersonaFuenteExternaDTO> listDto) {
        List<PersonaFuenteExterna> listEntidad = new ArrayList<PersonaFuenteExterna>();
        for (PersonaFuenteExternaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
