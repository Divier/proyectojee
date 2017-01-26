package co.com.datatools.c2.negocio.helpers.comparendos;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.entidades.ProcesaComparendo;
import co.com.datatools.c2.entidades.ProcesaComparendoPersona;
import co.com.datatools.c2.entidades.ProcesaDireccion;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:59:03 COT 2015
 */
public class ProcesaComparendoPersonaHelper {
    // --- to DTO
    public static ProcesaComparendoPersonaDTO toLevel0DTO(ProcesaComparendoPersona entidad) {
        ProcesaComparendoPersonaDTO dto = new ProcesaComparendoPersonaDTO();
        dto.setId(entidad.getId());
        dto.setCodigoTipoPersonaComparendo(entidad.getCodigoTipoPersonaComparendo());
        dto.setIdTipoIdentificacion(entidad.getIdTipoIdentificacion());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setDigitoVerificacionNit(entidad.getDigitoVerificacionNit());
        dto.setNumeroLicencia(entidad.getNumeroLicencia());
        dto.setFechaExpedicionLicenCondu(entidad.getFechaExpedicionLicenCondu());
        dto.setFechaVencimientoLicenCondu(entidad.getFechaVencimientoLicenCondu());
        dto.setIdCategoriaLicenciaCondu(entidad.getIdCategoriaLicenciaCondu());
        dto.setCodigoOrganismoLicencia(entidad.getCodigoOrganismoLicencia());
        dto.setApellido1(entidad.getApellido1());
        dto.setApellido2(entidad.getApellido2());
        dto.setNombre1(entidad.getNombre1());
        dto.setNombre2(entidad.getNombre2());
        dto.setRazonSocial(entidad.getRazonSocial());
        dto.setEdad(entidad.getEdad());
        dto.setEmail(entidad.getEmail());
        dto.setTelefonoFijo(entidad.getTelefonoFijo());
        dto.setTelefonoMovil(entidad.getTelefonoMovil());

        return dto;
    }

    public static ProcesaComparendoPersonaDTO toLevel1DTO(ProcesaComparendoPersona entidad) {
        ProcesaComparendoPersonaDTO dto = toLevel0DTO(entidad);
        if (entidad.getProcesaDireccion() != null)
            dto.setProcesaDireccion(ProcesaDireccionHelper.toLevel0DTO(entidad.getProcesaDireccion()));
        if (entidad.getProcesaComparendo() != null)
            dto.setProcesaComparendo(ProcesaComparendoHelper.toLevel0DTO(entidad.getProcesaComparendo()));

        return dto;
    }

    public static List<ProcesaComparendoPersonaDTO> toListLevel0DTO(List<ProcesaComparendoPersona> listEntidad) {
        List<ProcesaComparendoPersonaDTO> listDto = new ArrayList<ProcesaComparendoPersonaDTO>();
        for (ProcesaComparendoPersona entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ProcesaComparendoPersonaDTO> toListLevel1DTO(List<ProcesaComparendoPersona> listEntidad) {
        List<ProcesaComparendoPersonaDTO> listDto = new ArrayList<ProcesaComparendoPersonaDTO>();
        for (ProcesaComparendoPersona entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ProcesaComparendoPersona toLevel0Entity(ProcesaComparendoPersonaDTO dto,
            ProcesaComparendoPersona entidad) {
        if (null == entidad) {
            entidad = new ProcesaComparendoPersona();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoTipoPersonaComparendo(dto.getCodigoTipoPersonaComparendo());
        entidad.setIdTipoIdentificacion(dto.getIdTipoIdentificacion());
        entidad.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entidad.setDigitoVerificacionNit(dto.getDigitoVerificacionNit());
        entidad.setNumeroLicencia(dto.getNumeroLicencia());
        entidad.setFechaExpedicionLicenCondu(dto.getFechaExpedicionLicenCondu());
        entidad.setFechaVencimientoLicenCondu(dto.getFechaVencimientoLicenCondu());
        entidad.setIdCategoriaLicenciaCondu(dto.getIdCategoriaLicenciaCondu());
        entidad.setCodigoOrganismoLicencia(dto.getCodigoOrganismoLicencia());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setRazonSocial(dto.getRazonSocial());
        entidad.setEdad(dto.getEdad());
        entidad.setEmail(dto.getEmail());
        entidad.setTelefonoFijo(dto.getTelefonoFijo());
        entidad.setTelefonoMovil(dto.getTelefonoMovil());

        return entidad;
    }

    public static ProcesaComparendoPersona toLevel1Entity(ProcesaComparendoPersonaDTO dto,
            ProcesaComparendoPersona entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getProcesaDireccion() != null) {
            entidad.setProcesaDireccion(new ProcesaDireccion());
            entidad.getProcesaDireccion().setId(dto.getProcesaDireccion().getId());
        }
        if (dto.getProcesaComparendo() != null) {
            entidad.setProcesaComparendo(new ProcesaComparendo());
            entidad.getProcesaComparendo().setId(dto.getProcesaComparendo().getId());
        }

        return entidad;
    }

    public static List<ProcesaComparendoPersona> toListLevel0Entity(List<ProcesaComparendoPersonaDTO> listDto) {
        List<ProcesaComparendoPersona> listEntidad = new ArrayList<ProcesaComparendoPersona>();
        for (ProcesaComparendoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ProcesaComparendoPersona> toListLevel1Entity(List<ProcesaComparendoPersonaDTO> listDto) {
        List<ProcesaComparendoPersona> listEntidad = new ArrayList<ProcesaComparendoPersona>();
        for (ProcesaComparendoPersonaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
