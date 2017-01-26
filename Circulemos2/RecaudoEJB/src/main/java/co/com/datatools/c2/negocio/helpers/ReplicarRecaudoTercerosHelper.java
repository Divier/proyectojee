package co.com.datatools.c2.negocio.helpers;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.RecaudoRechazoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;

/**
 * Helper para WS de replicar pago
 * 
 * @author julio.pinzon(2016-05-05)
 * 
 */
public class ReplicarRecaudoTercerosHelper {

    /**
     * Convierte Recaudo de terceros a un pago
     * 
     * @param recaudoDTO
     * @return Pago
     * @author julio.pinzon(2016-05-05)
     */
    public static PagoDTO toPagoDTO(ItRecaudoDTO recaudoDTO) {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setOrganismoTransito(new OrganismoTransitoDTO(recaudoDTO.getCodigoOrganismo()));
        pagoDTO.setNumeroCuenta(recaudoDTO.getNumeroCuenta());

        // Convierte la fecha y hora de transaccion
        Calendar fechaTransaccion = Calendar.getInstance();
        fechaTransaccion.setTime(recaudoDTO.getFechaTransaccion());
        Calendar fechaHoraTransaccion = Calendar.getInstance();
        fechaHoraTransaccion.setTime(recaudoDTO.getHoraTransaccion());
        fechaHoraTransaccion.set(fechaTransaccion.get(Calendar.YEAR), fechaTransaccion.get(Calendar.MONTH),
                fechaTransaccion.get(Calendar.DAY_OF_MONTH));
        pagoDTO.setFechaTransaccion(fechaHoraTransaccion.getTime());

        pagoDTO.setNumeroDocumento(recaudoDTO.getNumeroIdentificacion());
        if (StringUtils.isNotBlank(recaudoDTO.getCodigoTipoIdentificacion())) {
            pagoDTO.setTipoIdentificacionPersona(new TipoIdentificacionPersonaDTO());
        }

        pagoDTO.setNumeroPago(recaudoDTO.getNumeroRecaudo());
        pagoDTO.setTotalCheque(recaudoDTO.getTotalCheque());
        pagoDTO.setTotalEfectivo(recaudoDTO.getTotalEfectivo());
        pagoDTO.setTotalRecaudo(recaudoDTO.getTotalRecaudo());
        return pagoDTO;
    }

    /**
     * Convierte Recaudo de terceros a un rechazo
     * 
     * @param recaudo
     * @return ItRecaudoRechazoDTO
     * @author julio.pinzon(2016-05-06)
     */
    public static RecaudoRechazoDTO toRecaudoRechazoDTO(ItRecaudoDTO recaudo) {
        RecaudoRechazoDTO rechazo = new RecaudoRechazoDTO();
        rechazo.setIdRecaudo(recaudo.getIdRecaudo());
        rechazo.setCodigoBanco(recaudo.getCodigoBanco());
        rechazo.setCodigoOrganismo(recaudo.getCodigoOrganismo());
        rechazo.setCodigoTipoCuenta(recaudo.getCodigoTipoCuenta());
        rechazo.setCodigoTipoIdentificacion(recaudo.getCodigoTipoIdentificacion());
        rechazo.setEstadoLectura(recaudo.getEstadoLectura());
        rechazo.setFechaTransaccion(recaudo.getFechaTransaccion());
        rechazo.setHoraTransaccion(recaudo.getHoraTransaccion());
        rechazo.setNumeroCuenta(recaudo.getNumeroCuenta());
        rechazo.setNumeroIdentificacion(recaudo.getNumeroIdentificacion());
        rechazo.setNumeroRecaudo(recaudo.getNumeroRecaudo());
        rechazo.setTotalCheque(recaudo.getTotalCheque());
        rechazo.setTotalEfectivo(recaudo.getTotalEfectivo());
        rechazo.setTotalRecaudo(recaudo.getTotalRecaudo());
        rechazo.setCodigoTipoRecaudo(recaudo.getCodigoTipoRecaudo());
        rechazo.setNumeroCuota(recaudo.getNumeroCuota());
        rechazo.setNumeroObligacion(recaudo.getNumeroObligacion());
        rechazo.setValorObligacion(recaudo.getValorObligacion());
        rechazo.setFechaCreacion(recaudo.getFechaCreacion());
        rechazo.setUsuario(recaudo.getUsuario());

        return rechazo;
    }
}
