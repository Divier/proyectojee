package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.PagoInconsistenciaDTO;
import co.com.datatools.c2.entidades.PagoInconsistencia;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 27 10:38:30 COT 2016
 */
public class PagoInconsistenciaHelper {
    // --- to DTO
    public static PagoInconsistenciaDTO toLevel0DTO(PagoInconsistencia entidad) {
        PagoInconsistenciaDTO dto = new PagoInconsistenciaDTO();
        dto.setId(entidad.getId());
        dto.setCodigoOrganismo(entidad.getCodigoOrganismo());
        dto.setFechaTransaccion(entidad.getFechaTransaccion());
        dto.setIdBanco(entidad.getIdBanco());
        dto.setIdTipoCuenta(entidad.getIdTipoCuenta());
        dto.setNumeroCuenta(entidad.getNumeroCuenta());
        dto.setNumeroPago(entidad.getNumeroPago());
        dto.setTotalCheque(entidad.getTotalCheque());
        dto.setTotalEfectivo(entidad.getTotalEfectivo());
        dto.setTotalRecaudo(entidad.getTotalRecaudo());
        dto.setFechaGeneracionReporte(entidad.getFechaGeneracionReporte());
        dto.setTipoIdentificacion(entidad.getTipoIdentificacion());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());
        return dto;
    }

    public static PagoInconsistenciaDTO toLevel1DTO(PagoInconsistencia entidad) {
        PagoInconsistenciaDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<PagoInconsistenciaDTO> toListLevel0DTO(List<PagoInconsistencia> listEntidad) {
        List<PagoInconsistenciaDTO> listDto = new ArrayList<PagoInconsistenciaDTO>();
        for (PagoInconsistencia entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PagoInconsistenciaDTO> toListLevel1DTO(List<PagoInconsistencia> listEntidad) {
        List<PagoInconsistenciaDTO> listDto = new ArrayList<PagoInconsistenciaDTO>();
        for (PagoInconsistencia entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static PagoInconsistencia toLevel0Entity(PagoInconsistenciaDTO dto, PagoInconsistencia entidad) {
        if (null == entidad) {
            entidad = new PagoInconsistencia();
        }
        entidad.setId(dto.getId());
        entidad.setCodigoOrganismo(dto.getCodigoOrganismo());
        entidad.setFechaTransaccion(dto.getFechaTransaccion());
        entidad.setIdBanco(dto.getIdBanco());
        entidad.setIdTipoCuenta(dto.getIdTipoCuenta());
        entidad.setNumeroCuenta(dto.getNumeroCuenta());
        entidad.setNumeroPago(dto.getNumeroPago());
        entidad.setTotalCheque(dto.getTotalCheque());
        entidad.setTotalEfectivo(dto.getTotalEfectivo());
        entidad.setTotalRecaudo(dto.getTotalRecaudo());
        entidad.setFechaGeneracionReporte(dto.getFechaGeneracionReporte());
        entidad.setTipoIdentificacion(dto.getTipoIdentificacion());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());
        return entidad;
    }

    public static PagoInconsistencia toLevel1Entity(PagoInconsistenciaDTO dto, PagoInconsistencia entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<PagoInconsistencia> toListLevel0Entity(List<PagoInconsistenciaDTO> listDto) {
        List<PagoInconsistencia> listEntidad = new ArrayList<PagoInconsistencia>();
        for (PagoInconsistenciaDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<PagoInconsistencia> toListLevel1Entity(List<PagoInconsistenciaDTO> listDto) {
        List<PagoInconsistencia> listEntidad = new ArrayList<PagoInconsistencia>();
        for (PagoInconsistenciaDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
