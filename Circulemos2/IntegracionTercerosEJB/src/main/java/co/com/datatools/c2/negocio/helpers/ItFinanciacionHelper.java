package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.entidades.financiacion.ItFinanciacion;

/**
 * @author Generated
 * @version 3.0 - Tue May 17 11:53:38 COT 2016
 */
public class ItFinanciacionHelper {
    // --- to DTO
    public static ItFinanciacionDTO toLevel0DTO(ItFinanciacion entidad) {
        ItFinanciacionDTO dto = new ItFinanciacionDTO();
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
        dto.setUsuario(entidad.getUsuario());
        dto.setValorTotalFinanciado(entidad.getValorTotalFinanciado());
        dto.setEmailDeudor(entidad.getEmailDeudor());
        dto.setEmailCodeudor(entidad.getEmailCodeudor());
        dto.setTelefonoDeudor(entidad.getTelefonoDeudor());
        dto.setTelefonoCodeudor(entidad.getTelefonoCodeudor());

        return dto;
    }

    public static ItFinanciacionDTO toLevel1DTO(ItFinanciacion entidad) {
        ItFinanciacionDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ItFinanciacionDTO> toListLevel0DTO(List<ItFinanciacion> listEntidad) {
        List<ItFinanciacionDTO> listDto = new ArrayList<ItFinanciacionDTO>();
        for (ItFinanciacion entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ItFinanciacionDTO> toListLevel1DTO(List<ItFinanciacion> listEntidad) {
        List<ItFinanciacionDTO> listDto = new ArrayList<ItFinanciacionDTO>();
        for (ItFinanciacion entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ItFinanciacion toLevel0Entity(ItFinanciacionDTO dto, ItFinanciacion entidad) {
        if (null == entidad) {
            entidad = new ItFinanciacion();
        }
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
        entidad.setUsuario(dto.getUsuario());
        entidad.setValorTotalFinanciado(dto.getValorTotalFinanciado());
        entidad.setEmailDeudor(dto.getEmailDeudor());
        entidad.setEmailCodeudor(dto.getEmailCodeudor());
        entidad.setTelefonoDeudor(dto.getTelefonoDeudor());
        entidad.setTelefonoCodeudor(dto.getTelefonoCodeudor());

        return entidad;
    }

    public static ItFinanciacion toLevel1Entity(ItFinanciacionDTO dto, ItFinanciacion entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ItFinanciacion> toListLevel0Entity(List<ItFinanciacionDTO> listDto) {
        List<ItFinanciacion> listEntidad = new ArrayList<ItFinanciacion>();
        for (ItFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ItFinanciacion> toListLevel1Entity(List<ItFinanciacionDTO> listDto) {
        List<ItFinanciacion> listEntidad = new ArrayList<ItFinanciacion>();
        for (ItFinanciacionDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
