package co.com.datatools.c2.negocio.helpers;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.entidades.ItRecaudo;

/**
 * @author Generated
 * @version 3.0 - Wed May 04 16:58:10 COT 2016
 */
public class ItRecaudoHelper {
    // --- to DTO
    public static ItRecaudoDTO toLevel0DTO(ItRecaudo entidad) {
        ItRecaudoDTO dto = new ItRecaudoDTO();
        dto.setIdRecaudo(entidad.getIdRecaudo());
        dto.setCodigoBanco(entidad.getCodigoBanco());
        dto.setCodigoOrganismo(entidad.getCodigoOrganismo());
        dto.setCodigoTipoCuenta(entidad.getCodigoTipoCuenta());
        dto.setCodigoTipoIdentificacion(entidad.getCodigoTipoIdentificacion());
        dto.setEstadoLectura(entidad.getEstadoLectura());
        dto.setFechaTransaccion(entidad.getFechaTransaccion());
        dto.setHoraTransaccion(entidad.getHoraTransaccion());
        dto.setNumeroCuenta(entidad.getNumeroCuenta());
        dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
        dto.setNumeroRecaudo(entidad.getNumeroRecaudo());
        dto.setTotalCheque(entidad.getTotalCheque());
        dto.setTotalEfectivo(entidad.getTotalEfectivo());
        dto.setTotalRecaudo(entidad.getTotalRecaudo());
        dto.setCodigoTipoRecaudo(entidad.getCodigoTipoRecaudo());
        dto.setNumeroCuota(entidad.getNumeroCuota());
        dto.setNumeroObligacion(entidad.getNumeroObligacion());
        dto.setValorObligacion(entidad.getValorObligacion());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setUsuario(entidad.getUsuario());

        return dto;
    }

    public static ItRecaudoDTO toLevel1DTO(ItRecaudo entidad) {
        ItRecaudoDTO dto = toLevel0DTO(entidad);

        return dto;
    }

    public static List<ItRecaudoDTO> toListLevel0DTO(List<ItRecaudo> listEntidad) {
        List<ItRecaudoDTO> listDto = new ArrayList<ItRecaudoDTO>();
        for (ItRecaudo entidad : listEntidad) {
            listDto.add(toLevel0DTO(entidad));
        }
        return listDto;
    }

    public static List<ItRecaudoDTO> toListLevel1DTO(List<ItRecaudo> listEntidad) {
        List<ItRecaudoDTO> listDto = new ArrayList<ItRecaudoDTO>();
        for (ItRecaudo entidad : listEntidad) {
            listDto.add(toLevel1DTO(entidad));
        }
        return listDto;
    }

    // --- fin to DTO
    // --- to Entidad
    public static ItRecaudo toLevel0Entity(ItRecaudoDTO dto, ItRecaudo entidad) {
        if (null == entidad) {
            entidad = new ItRecaudo();
        }
        entidad.setIdRecaudo(dto.getIdRecaudo());
        entidad.setCodigoBanco(dto.getCodigoBanco());
        entidad.setCodigoOrganismo(dto.getCodigoOrganismo());
        entidad.setCodigoTipoCuenta(dto.getCodigoTipoCuenta());
        entidad.setCodigoTipoIdentificacion(dto.getCodigoTipoIdentificacion());
        entidad.setEstadoLectura(dto.getEstadoLectura());
        entidad.setFechaTransaccion(dto.getFechaTransaccion());
        entidad.setHoraTransaccion(dto.getHoraTransaccion());
        entidad.setNumeroCuenta(dto.getNumeroCuenta());
        entidad.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        entidad.setNumeroRecaudo(dto.getNumeroRecaudo());
        entidad.setTotalCheque(dto.getTotalCheque());
        entidad.setTotalEfectivo(dto.getTotalEfectivo());
        entidad.setTotalRecaudo(dto.getTotalRecaudo());
        entidad.setCodigoTipoRecaudo(dto.getCodigoTipoRecaudo());
        entidad.setNumeroCuota(dto.getNumeroCuota());
        entidad.setNumeroObligacion(dto.getNumeroObligacion());
        entidad.setValorObligacion(dto.getValorObligacion());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setUsuario(dto.getUsuario());

        return entidad;
    }

    public static ItRecaudo toLevel1Entity(ItRecaudoDTO dto, ItRecaudo entidad) {
        entidad = toLevel0Entity(dto, entidad);

        return entidad;
    }

    public static List<ItRecaudo> toListLevel0Entity(List<ItRecaudoDTO> listDto) {
        List<ItRecaudo> listEntidad = new ArrayList<ItRecaudo>();
        for (ItRecaudoDTO dto : listDto) {
            listEntidad.add(toLevel0Entity(dto, null));
        }
        return listEntidad;
    }

    public static List<ItRecaudo> toListLevel1Entity(List<ItRecaudoDTO> listDto) {
        List<ItRecaudo> listEntidad = new ArrayList<ItRecaudo>();
        for (ItRecaudoDTO dto : listDto) {
            listEntidad.add(toLevel1Entity(dto, null));
        }
        return listEntidad;
    }
    // --- fin to Entidad
}
