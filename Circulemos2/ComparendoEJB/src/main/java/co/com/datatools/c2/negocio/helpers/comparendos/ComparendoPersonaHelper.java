package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.entidades.Comparendo;
import co.com.datatools.c2.entidades.ComparendoPersona;
import co.com.datatools.c2.entidades.TipoCategLicenciaConduccion;
import co.com.datatools.c2.entidades.TipoPersonaComparendo;
import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.negocio.helpers.comun.DireccionHelper;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoIdentificacionPersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoCategLicenciaConduccionHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoPersonaComparendoHelper;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:27:26 COT 2015
 */
public class ComparendoPersonaHelper {
    // --- to DTO
    public static ComparendoPersonaDTO toLevel0DTO(ComparendoPersona entidad) {
        ComparendoPersonaDTO dto = new ComparendoPersonaDTO();
        dto.setId(entidad.getId());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setDigitoVerificacionNit(entidad.getDigitoVerificacionNit());
        dto.setNumeroLicencia(entidad.getNumeroLicencia());
        dto.setFechaExpedicionLicenCondu(entidad.getFechaExpedicionLicenCondu());
        dto.setFechaVencimientoLicenCondu(entidad.getFechaVencimientoLicenCondu());
        dto.setApellido1(entidad.getApellido1());
        dto.setApellido2(entidad.getApellido2());
        dto.setNombre1(entidad.getNombre1());
        dto.setNombre2(entidad.getNombre2());
        dto.setRazonSocial(entidad.getRazonSocial());
        dto.setTelefonoFijo(entidad.getTelefonoFijo());
        dto.setTelefonoMovil(entidad.getTelefonoMovil());
        dto.setEdad(entidad.getEdad());
        dto.setEmail(entidad.getEmail());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaFin(entidad.getFechaFin());

        return dto;
    }

    public static ComparendoPersonaDTO toLevel1DTO(ComparendoPersona entidad) {
        ComparendoPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getComparendo() != null)
            dto.setComparendo(ComparendoHelper.toLevel0DTO(entidad.getComparendo()));
        if (entidad.getTipoPersonaComparendo() != null)
            dto.setTipoPersonaComparendo(TipoPersonaComparendoHelper.toLevel0DTO(entidad.getTipoPersonaComparendo()));
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getTipoIdentificacion() != null)
            dto.setTipoIdentificacion(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad.getTipoIdentificacion()));
        if (entidad.getCategoriaLicencia() != null)
            dto.setCategoriaLicencia(TipoCategLicenciaConduccionHelper.toLevel0DTO(entidad.getCategoriaLicencia()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getDireccion() != null)
            dto.setDireccion(DireccionHelper.toLevel0DTO(entidad.getDireccion()));

        return dto;
    }

    public static List<ComparendoPersonaDTO> toListLevel0DTO(List<ComparendoPersona> listEntidad) {
        List<ComparendoPersonaDTO> listDto = new ArrayList<ComparendoPersonaDTO>();
        for (ComparendoPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ComparendoPersonaDTO> toListLevel1DTO(List<ComparendoPersona> listEntidad) {
        List<ComparendoPersonaDTO> listDto = new ArrayList<ComparendoPersonaDTO>();
        for (ComparendoPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ComparendoPersona toLevel0Entity(ComparendoPersonaDTO dto, ComparendoPersona entidad) {
        if (null == entidad) {
            entidad = new ComparendoPersona();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entidad.setDigitoVerificacionNit(dto.getDigitoVerificacionNit());
        entidad.setNumeroLicencia(dto.getNumeroLicencia());
        entidad.setFechaExpedicionLicenCondu(dto.getFechaExpedicionLicenCondu());
        entidad.setFechaVencimientoLicenCondu(dto.getFechaVencimientoLicenCondu());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setRazonSocial(dto.getRazonSocial());
        entidad.setTelefonoFijo(dto.getTelefonoFijo());
        entidad.setTelefonoMovil(dto.getTelefonoMovil());
        entidad.setEdad(dto.getEdad());
        entidad.setEmail(dto.getEmail());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaFin(dto.getFechaFin());

        return entidad;
    }

    public static ComparendoPersona toLevel1Entity(ComparendoPersonaDTO dto, ComparendoPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getComparendo() != null) {
            entidad.setComparendo(new Comparendo());
            entidad.getComparendo().setCicomparendo(dto.getComparendo().getCicomparendo());
        }
        if (dto.getTipoPersonaComparendo() != null) {
            entidad.setTipoPersonaComparendo(new TipoPersonaComparendo());
            entidad.getTipoPersonaComparendo().setCodigo(dto.getTipoPersonaComparendo().getCodigo());
        }
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getTipoIdentificacion() != null) {
            entidad.setTipoIdentificacion(new TipoIdentificacionPersona());
            entidad.getTipoIdentificacion().setId(dto.getTipoIdentificacion().getId());
        }
        if (dto.getCategoriaLicencia() != null) {
            entidad.setCategoriaLicencia(new TipoCategLicenciaConduccion());
            entidad.getCategoriaLicencia().setId(dto.getCategoriaLicencia().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getDireccion() != null) {
            entidad.setDireccion(new Direccion());
            entidad.getDireccion().setId(dto.getDireccion().getId());
        }

        return entidad;
    }

    public static List<ComparendoPersona> toListLevel0Entity(List<ComparendoPersonaDTO> listDto) {
        List<ComparendoPersona> listEntidad = new ArrayList<ComparendoPersona>();
        for (ComparendoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ComparendoPersona> toListLevel1Entity(List<ComparendoPersonaDTO> listDto) {
        List<ComparendoPersona> listEntidad = new ArrayList<ComparendoPersona>();
        for (ComparendoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
