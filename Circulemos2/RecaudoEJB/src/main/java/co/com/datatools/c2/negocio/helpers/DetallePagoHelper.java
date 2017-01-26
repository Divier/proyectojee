package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.DetallePagoDTO;
import co.com.datatools.c2.entidades.DetallePago;
import co.com.datatools.c2.entidades.EstadoPago;
import co.com.datatools.c2.entidades.Pago;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 15:55:18 COT 2016
 */
public class DetallePagoHelper {
    // --- to DTO
    public static DetallePagoDTO toLevel0DTO(DetallePago entidad) {
        DetallePagoDTO dto = new DetallePagoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroCuota(entidad.getNumeroCuota());
        dto.setNumeroObligacion(entidad.getNumeroObligacion());
        dto.setValorObligacion(entidad.getValorObligacion());
        dto.setIdConceptoCartera(entidad.getIdConceptoCartera());
        dto.setIdTipoRecaudo(entidad.getIdTipoRecaudo());
        dto.setNumeroIntentos(entidad.getNumeroIntentos());
        dto.setFechaGeneracionReporte(entidad.getFechaGeneracionReporte());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setFechaAplicacionPago(entidad.getFechaAplicacionPago());

        return dto;
    }

    public static DetallePagoDTO toLevel1DTO(DetallePago entidad) {
        DetallePagoDTO dto = toLevel0DTO(entidad);
        if (entidad.getPago() != null)
            dto.setPago(PagoHelper.toLevel0DTO(entidad.getPago()));
        if (entidad.getEstadoPago() != null)
            dto.setEstadoPago(EstadoPagoHelper.toLevel0DTO(entidad.getEstadoPago()));

        return dto;
    }

    public static List<DetallePagoDTO> toListLevel0DTO(List<DetallePago> listEntidad) {
        List<DetallePagoDTO> listDto = new ArrayList<DetallePagoDTO>();
        for (DetallePago entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<DetallePagoDTO> toListLevel1DTO(List<DetallePago> listEntidad) {
        List<DetallePagoDTO> listDto = new ArrayList<DetallePagoDTO>();
        for (DetallePago entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static DetallePago toLevel0Entity(DetallePagoDTO dto, DetallePago entidad) {
        if (null == entidad) {
            entidad = new DetallePago();
        }
        entidad.setId(dto.getId());
        entidad.setNumeroCuota(dto.getNumeroCuota());
        entidad.setNumeroObligacion(dto.getNumeroObligacion());
        entidad.setValorObligacion(dto.getValorObligacion());
        entidad.setIdConceptoCartera(dto.getIdConceptoCartera());
        entidad.setIdTipoRecaudo(dto.getIdTipoRecaudo());
        entidad.setNumeroIntentos(dto.getNumeroIntentos());
        entidad.setFechaGeneracionReporte(dto.getFechaGeneracionReporte());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setFechaAplicacionPago(dto.getFechaAplicacionPago());

        return entidad;
    }

    public static DetallePago toLevel1Entity(DetallePagoDTO dto, DetallePago entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getPago() != null) {
            entidad.setPago(new Pago());
            entidad.getPago().setId(dto.getPago().getId());
        }
        if (dto.getEstadoPago() != null) {
            entidad.setEstadoPago(new EstadoPago());
            entidad.getEstadoPago().setId(dto.getEstadoPago().getId());
        }

        return entidad;
    }

    public static List<DetallePago> toListLevel0Entity(List<DetallePagoDTO> listDto) {
        List<DetallePago> listEntidad = new ArrayList<DetallePago>();
        for (DetallePagoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<DetallePago> toListLevel1Entity(List<DetallePagoDTO> listDto) {
        List<DetallePago> listEntidad = new ArrayList<DetallePago>();
        for (DetallePagoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
