package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.entidades.Pago;
import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.TipoIdentificacionPersona;
import co.com.datatools.c2.negocio.helpers.comun.OrganismoTransitoHelper;
import co.com.datatools.c2.negocio.helpers.personas.TipoIdentificacionPersonaHelper;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 15:55:36 COT 2016
 */
public class PagoHelper {
    // --- to DTO
    public static PagoDTO toLevel0DTO(Pago entidad) {
        PagoDTO dto = new PagoDTO();
        dto.setId(entidad.getId());
        dto.setFechaTransaccion(entidad.getFechaTransaccion());
        dto.setIdBanco(entidad.getIdBanco());
        dto.setIdTipoCuenta(entidad.getIdTipoCuenta());
        dto.setIdUsuario(entidad.getIdUsuario());
        dto.setNumeroCuenta(entidad.getNumeroCuenta());
        dto.setNumeroPago(entidad.getNumeroPago());
        dto.setTotalCheque(entidad.getTotalCheque());
        dto.setTotalEfectivo(entidad.getTotalEfectivo());
        dto.setTotalRecaudo(entidad.getTotalRecaudo());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        return dto;
    }

    public static PagoDTO toLevel1DTO(Pago entidad) {
        PagoDTO dto = toLevel0DTO(entidad);
        if (entidad.getOrganismoTransito() != null)
            dto.setOrganismoTransito(OrganismoTransitoHelper.toLevel0DTO(entidad.getOrganismoTransito()));
        if (entidad.getTipoIdentificacionPersona() != null)
            dto.setTipoIdentificacionPersona(TipoIdentificacionPersonaHelper.toLevel0DTO(entidad
                    .getTipoIdentificacionPersona()));
        return dto;
    }

    public static List<PagoDTO> toListLevel0DTO(List<Pago> listEntidad) {
        List<PagoDTO> listDto = new ArrayList<PagoDTO>();
        for (Pago entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<PagoDTO> toListLevel1DTO(List<Pago> listEntidad) {
        List<PagoDTO> listDto = new ArrayList<PagoDTO>();
        for (Pago entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static Pago toLevel0Entity(PagoDTO dto, Pago entidad) {
        if (null == entidad) {
            entidad = new Pago();
        }
        entidad.setId(dto.getId());
        entidad.setFechaTransaccion(dto.getFechaTransaccion());
        entidad.setIdBanco(dto.getIdBanco());
        entidad.setIdTipoCuenta(dto.getIdTipoCuenta());
        entidad.setIdUsuario(dto.getIdUsuario());
        entidad.setNumeroCuenta(dto.getNumeroCuenta());
        entidad.setNumeroPago(dto.getNumeroPago());
        entidad.setTotalCheque(dto.getTotalCheque());
        entidad.setTotalEfectivo(dto.getTotalEfectivo());
        entidad.setTotalRecaudo(dto.getTotalRecaudo());
        entidad.setNumeroDocumento(dto.getNumeroDocumento());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        return entidad;
    }

    public static Pago toLevel1Entity(PagoDTO dto, Pago entidad) {
        entidad = toLevel0Entity(dto, entidad);
        if (dto.getOrganismoTransito() != null) {
            entidad.setOrganismoTransito(new OrganismoTransito());
            entidad.getOrganismoTransito().setCodigoOrganismo(dto.getOrganismoTransito().getCodigoOrganismo());
        }
        if (dto.getTipoIdentificacionPersona() != null) {
            entidad.setTipoIdentificacionPersona(new TipoIdentificacionPersona());
            entidad.getTipoIdentificacionPersona().setId(dto.getTipoIdentificacionPersona().getId());
        }
        return entidad;
    }

    public static List<Pago> toListLevel0Entity(List<PagoDTO> listDto) {
        List<Pago> listEntidad = new ArrayList<Pago>();
        for (PagoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<Pago> toListLevel1Entity(List<PagoDTO> listDto) {
        List<Pago> listEntidad = new ArrayList<Pago>();
        for (PagoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
