package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.InconsistenciaFinanciacionDTO;
import co.com.datatools.c2.entidades.InconsistenciaFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Tue Aug 02 11:28:48 COT 2016
 */
public class InconsistenciaFinanciacionHelper {
    // --- to DTO
    public static InconsistenciaFinanciacionDTO toLevel0DTO(InconsistenciaFinanciacion entidad) {
        InconsistenciaFinanciacionDTO dto = new InconsistenciaFinanciacionDTO();
        dto.setId(entidad.getId());
        dto.setNumeroFinanciacion(entidad.getNumeroFinanciacion());
        dto.setCodigoOrganismo(entidad.getCodigoOrganismo());
        dto.setCodigoTipoIdentificacionCodeudor(entidad.getCodigoTipoIdentificacionCodeudor());
        dto.setCodigoTipoIdentificacionDeudor(entidad.getCodigoTipoIdentificacionDeudor());
        dto.setEstadoLectura(entidad.getEstadoLectura());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setFechaFinanciacion(entidad.getFechaFinanciacion());
        dto.setNumeroCuotaFinanciacion(entidad.getNumeroCuotaFinanciacion());
        dto.setNumeroIdentificacionCodeudor(entidad.getNumeroIdentificacionCodeudor());
        dto.setNumeroIdentificacionDeudor(entidad.getNumeroIdentificacionDeudor());
        dto.setPrimerApellidoCodeudor(entidad.getPrimerApellidoCodeudor());
        dto.setPrimerApellidoDeudor(entidad.getPrimerApellidoDeudor());
        dto.setPrimerNombreCodeudor(entidad.getPrimerNombreCodeudor());
        dto.setPrimerNombreDeudor(entidad.getPrimerNombreDeudor());
        dto.setRazonSocial(entidad.getRazonSocial());
        dto.setSaldoFinanciacion(entidad.getSaldoFinanciacion());
        dto.setSegundoApellidoCodeudor(entidad.getSegundoApellidoCodeudor());
        dto.setSegundoApellidoDeudor(entidad.getSegundoApellidoDeudor());
        dto.setSegundoNombreCodeudor(entidad.getSegundoNombreCodeudor());
        dto.setSegundoNombreDeudor(entidad.getSegundoNombreDeudor());
        dto.setEmailCodeudor(entidad.getEmailCodeudor());
        dto.setEmailDeudor(entidad.getEmailDeudor());
        dto.setTelefonoCodeudor(entidad.getTelefonoCodeudor());
        dto.setTelefonoDeudor(entidad.getTelefonoDeudor());
        dto.setUsuario(entidad.getUsuario());
        dto.setValorTotalFinanciado(entidad.getValorTotalFinanciado());
        dto.setFechaRegistro(entidad.getFechaRegistro());

        return dto;
    }

    public static InconsistenciaFinanciacionDTO toLevel1DTO(InconsistenciaFinanciacion entidad) {
        InconsistenciaFinanciacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<InconsistenciaFinanciacionDTO> toListLevel0DTO(List<InconsistenciaFinanciacion> listEntidad) {
        List<InconsistenciaFinanciacionDTO> listDto = new ArrayList<InconsistenciaFinanciacionDTO>();
        for (InconsistenciaFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<InconsistenciaFinanciacionDTO> toListLevel1DTO(List<InconsistenciaFinanciacion> listEntidad) {
        List<InconsistenciaFinanciacionDTO> listDto = new ArrayList<InconsistenciaFinanciacionDTO>();
        for (InconsistenciaFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static InconsistenciaFinanciacion toLevel0Entity(InconsistenciaFinanciacionDTO dto,
            InconsistenciaFinanciacion entidad) {
        if (null == entidad) {
            entidad = new InconsistenciaFinanciacion();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroFinanciacion(dto.getNumeroFinanciacion());
        entidad.setCodigoOrganismo(dto.getCodigoOrganismo());
        entidad.setCodigoTipoIdentificacionCodeudor(dto.getCodigoTipoIdentificacionCodeudor());
        entidad.setCodigoTipoIdentificacionDeudor(dto.getCodigoTipoIdentificacionDeudor());
        entidad.setEstadoLectura(dto.getEstadoLectura());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setFechaFinanciacion(dto.getFechaFinanciacion());
        entidad.setNumeroCuotaFinanciacion(dto.getNumeroCuotaFinanciacion());
        entidad.setNumeroIdentificacionCodeudor(dto.getNumeroIdentificacionCodeudor());
        entidad.setNumeroIdentificacionDeudor(dto.getNumeroIdentificacionDeudor());
        entidad.setPrimerApellidoCodeudor(dto.getPrimerApellidoCodeudor());
        entidad.setPrimerApellidoDeudor(dto.getPrimerApellidoDeudor());
        entidad.setPrimerNombreCodeudor(dto.getPrimerNombreCodeudor());
        entidad.setPrimerNombreDeudor(dto.getPrimerNombreDeudor());
        entidad.setRazonSocial(dto.getRazonSocial());
        entidad.setSaldoFinanciacion(dto.getSaldoFinanciacion());
        entidad.setSegundoApellidoCodeudor(dto.getSegundoApellidoCodeudor());
        entidad.setSegundoApellidoDeudor(dto.getSegundoApellidoDeudor());
        entidad.setSegundoNombreCodeudor(dto.getSegundoNombreCodeudor());
        entidad.setSegundoNombreDeudor(dto.getSegundoNombreDeudor());
        entidad.setEmailCodeudor(dto.getEmailCodeudor());
        entidad.setEmailDeudor(dto.getEmailDeudor());
        entidad.setTelefonoCodeudor(dto.getTelefonoCodeudor());
        entidad.setTelefonoDeudor(dto.getTelefonoDeudor());
        entidad.setUsuario(dto.getUsuario());
        entidad.setValorTotalFinanciado(dto.getValorTotalFinanciado());
        entidad.setFechaRegistro(dto.getFechaRegistro());

        return entidad;
    }

    public static InconsistenciaFinanciacion toLevel1Entity(InconsistenciaFinanciacionDTO dto,
            InconsistenciaFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<InconsistenciaFinanciacion> toListLevel0Entity(List<InconsistenciaFinanciacionDTO> listDto) {
        List<InconsistenciaFinanciacion> listEntidad = new ArrayList<InconsistenciaFinanciacion>();
        for (InconsistenciaFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<InconsistenciaFinanciacion> toListLevel1Entity(List<InconsistenciaFinanciacionDTO> listDto) {
        List<InconsistenciaFinanciacion> listEntidad = new ArrayList<InconsistenciaFinanciacion>();
        for (InconsistenciaFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
