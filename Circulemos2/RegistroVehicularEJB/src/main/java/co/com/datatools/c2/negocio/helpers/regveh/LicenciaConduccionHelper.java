package co.com.datatools.c2.negocio.helpers.regveh;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.regveh.LicenciaConduccionDTO;
import co.com.datatools.c2.entidades.EstadoLicencia;
import co.com.datatools.c2.entidades.LicenciaConduccion;
import co.com.datatools.c2.entidades.TipoCategLicenciaConduccion;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.personas.PersonaHelper;
import co.com.datatools.c2.negocio.helpers.v2.TipoCategLicenciaConduccionHelper;

/**
 * @author Generated
 * @version 3.0 - Fri Feb 27 17:52:03 COT 2015
 */
public class LicenciaConduccionHelper {
    // --- to DTO
    public static LicenciaConduccionDTO toLevel0DTO(LicenciaConduccion entidad) {
        LicenciaConduccionDTO dto = new LicenciaConduccionDTO();
        dto.setId(entidad.getId());
        dto.setLicenciaConduccion(entidad.getLicenciaConduccion());
        dto.setFechaExpedicionLicencia(entidad.getFechaExpedicionLicencia());
        dto.setFechaVencimientoLicencia(entidad.getFechaVencimientoLicencia());

        return dto;
    }

    public static LicenciaConduccionDTO toLevel1DTO(LicenciaConduccion entidad) {
        LicenciaConduccionDTO dto = toLevel0DTO(entidad);
        if (entidad.getPersona() != null)
            dto.setPersona(PersonaHelper.toLevel0DTO(entidad.getPersona()));
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getEstadoLicencia() != null)
            dto.setEstadoLicencia(EstadoLicenciaHelper.toLevel0DTO(entidad.getEstadoLicencia()));
        if (entidad.getCategoria() != null)
            dto.setCategoria(TipoCategLicenciaConduccionHelper.toLevel0DTO(entidad.getCategoria()));

        return dto;
    }

    public static List<LicenciaConduccionDTO> toListLevel0DTO(List<LicenciaConduccion> listEntidad) {
        List<LicenciaConduccionDTO> listDto = new ArrayList<LicenciaConduccionDTO>();
        for (LicenciaConduccion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<LicenciaConduccionDTO> toListLevel1DTO(List<LicenciaConduccion> listEntidad) {
        List<LicenciaConduccionDTO> listDto = new ArrayList<LicenciaConduccionDTO>();
        for (LicenciaConduccion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static LicenciaConduccion toLevel0Entity(LicenciaConduccionDTO dto, LicenciaConduccion entidad) {
        if (null == entidad) {
            entidad = new LicenciaConduccion();
        }
        entidad.setId(dto.getId());
        entidad.setLicenciaConduccion(dto.getLicenciaConduccion());
        entidad.setFechaExpedicionLicencia(dto.getFechaExpedicionLicencia());
        entidad.setFechaVencimientoLicencia(dto.getFechaVencimientoLicencia());

        return entidad;
    }

    public static LicenciaConduccion toLevel1Entity(LicenciaConduccionDTO dto, LicenciaConduccion entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPersona() != null) {
            entidad.setPersona(new Persona());
            entidad.getPersona().setId(dto.getPersona().getId());
        }
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getEstadoLicencia() != null) {
            entidad.setEstadoLicencia(new EstadoLicencia());
            entidad.getEstadoLicencia().setId(dto.getEstadoLicencia().getId());
        }
        if (dto.getCategoria() != null) {
            entidad.setCategoria(new TipoCategLicenciaConduccion());
            entidad.getCategoria().setId(dto.getCategoria().getId());
        }

        return entidad;
    }

    public static List<LicenciaConduccion> toListLevel0Entity(List<LicenciaConduccionDTO> listDto) {
        List<LicenciaConduccion> listEntidad = new ArrayList<LicenciaConduccion>();
        for (LicenciaConduccionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<LicenciaConduccion> toListLevel1Entity(List<LicenciaConduccionDTO> listDto) {
        List<LicenciaConduccion> listEntidad = new ArrayList<LicenciaConduccion>();
        for (LicenciaConduccionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
