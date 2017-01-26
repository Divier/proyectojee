package co.com.datatools.c2.ws.recaudo.utilidades.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.dto.ws.DetallePagoWSDTO;
import co.com.datatools.c2.dto.ws.PagoWSDTO;

/**
 * Helper para WS de replicar pago
 * 
 * @author julio.pinzon
 * 
 */
public class ReplicarPagoHelper {

    public static PagoDTO toPagoDTO(PagoWSDTO pagoWSWSDTO) {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setOrganismoTransito(new OrganismoTransitoDTO(pagoWSWSDTO.getCodigoOrganismoTransito()));
        pagoDTO.setNumeroCuenta(pagoWSWSDTO.getCuenta());

        // Convierte la fecha y hora de transaccion
        Calendar fechaTransaccion = Calendar.getInstance();
        if (pagoWSWSDTO.getFechaTransaccion() != null) {
            fechaTransaccion.setTime(getDateXMLGregorianCalendar(pagoWSWSDTO.getFechaTransaccion()));
            Calendar fechaHoraTransaccion = Calendar.getInstance();
            if (pagoWSWSDTO.getHoraTransaccion() != null) {
                fechaHoraTransaccion.setTime(getDateXMLGregorianCalendar(pagoWSWSDTO.getHoraTransaccion()));
                fechaHoraTransaccion.set(fechaTransaccion.get(Calendar.YEAR), fechaTransaccion.get(Calendar.MONTH),
                        fechaTransaccion.get(Calendar.DAY_OF_MONTH));
                pagoDTO.setFechaTransaccion(fechaHoraTransaccion.getTime());
            }
        }

        // Genera la persona
        pagoDTO.setTipoIdentificacionPersona(new TipoIdentificacionPersonaDTO());
        pagoDTO.setNumeroDocumento(pagoWSWSDTO.getNumeroIdentificacion());

        pagoDTO.setNumeroPago(pagoWSWSDTO.getNumeroRecaudo());
        pagoDTO.setTotalCheque(pagoWSWSDTO.getTotalCheque());
        pagoDTO.setTotalEfectivo(pagoWSWSDTO.getTotalEfectivo());
        pagoDTO.setTotalRecaudo(pagoWSWSDTO.getTotalRecaudo());
        return pagoDTO;
    }

    private static Date getDateXMLGregorianCalendar(XMLGregorianCalendar fecha) {
        Date fechaD = null;
        if (fecha != null) {
            fechaD = fecha.toGregorianCalendar().getTime();
        }
        return fechaD;
    }

    public static List<ItRecaudoDTO> toItRecaudoDTO(PagoWSDTO entidad) {
        List<ItRecaudoDTO> dtos = new ArrayList<ItRecaudoDTO>();
        ItRecaudoDTO dto = new ItRecaudoDTO();
        for (DetallePagoWSDTO detalle : entidad.getDetalles()) {
            dto.setCodigoBanco(entidad.getBancoRecaudo());
            dto.setCodigoOrganismo(entidad.getCodigoOrganismoTransito());
            dto.setCodigoTipoCuenta(entidad.getTipoCuenta());
            dto.setCodigoTipoIdentificacion(entidad.getCodigoTipoIdentificacion());
            dto.setFechaTransaccion(getDateXMLGregorianCalendar(entidad.getFechaTransaccion()));
            dto.setHoraTransaccion(getDateXMLGregorianCalendar(entidad.getHoraTransaccion()));
            dto.setNumeroCuenta(entidad.getCuenta());
            dto.setNumeroIdentificacion(entidad.getNumeroIdentificacion());
            dto.setNumeroRecaudo(entidad.getNumeroRecaudo());
            dto.setTotalCheque(entidad.getTotalCheque());
            dto.setTotalEfectivo(entidad.getTotalEfectivo());
            dto.setTotalRecaudo(entidad.getTotalRecaudo());
            dto.setCodigoTipoRecaudo(detalle.getTipoRecaudo());
            dto.setNumeroCuota(detalle.getNumeroCuota());
            dto.setNumeroObligacion(detalle.getObligacionPagada());
            dto.setValorObligacion(detalle.getValorObligacion());
        }
        dtos.add(dto);
        return dtos;
    }
}
