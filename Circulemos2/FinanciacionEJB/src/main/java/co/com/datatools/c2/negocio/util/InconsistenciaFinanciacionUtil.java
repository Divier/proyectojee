package co.com.datatools.c2.negocio.util;

import co.com.datatools.c2.dto.InconsistenciaDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.InconsistenciaFinanciacionDTO;
import co.com.datatools.c2.dto.financiacion.ItDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.dto.financiacion.ItDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.util.date.UtilFecha;

/**
 * 
 * @author diego.fonseca
 * 
 */

public class InconsistenciaFinanciacionUtil {

    /**
     * 
     * @param itFinanciacion
     * @return metodo que se encarga de transformar ItFinanciacionDTO a InconsistenciaFinanciacionDTO
     */
    public static InconsistenciaFinanciacionDTO cargarInconsistenciaFinanciacion(ItFinanciacionDTO itFinanciacion) {

        InconsistenciaFinanciacionDTO inconsistenciaFinanciacionDTO = new InconsistenciaFinanciacionDTO();
        inconsistenciaFinanciacionDTO.setNumeroFinanciacion(itFinanciacion.getNumeroFinanciacion());
        inconsistenciaFinanciacionDTO.setCodigoOrganismo(itFinanciacion.getCodigoOrganismo());
        inconsistenciaFinanciacionDTO.setFechaFinanciacion(itFinanciacion.getFechaFinanciacion());
        inconsistenciaFinanciacionDTO.setCodigoTipoIdentificacionDeudor(itFinanciacion
                .getCodigoTipoIdentificacionDeudor());
        inconsistenciaFinanciacionDTO.setNumeroIdentificacionDeudor(itFinanciacion.getNumeroIdentificacionDeudor());
        inconsistenciaFinanciacionDTO.setPrimerNombreDeudor(itFinanciacion.getPrimerNombreDeudor());
        inconsistenciaFinanciacionDTO.setSegundoNombreDeudor(itFinanciacion.getPrimerApellidoDeudor());
        inconsistenciaFinanciacionDTO.setPrimerApellidoDeudor(itFinanciacion.getPrimerApellidoDeudor());
        inconsistenciaFinanciacionDTO.setSegundoApellidoDeudor(itFinanciacion.getSegundoApellidoDeudor());
        inconsistenciaFinanciacionDTO.setRazonSocial(itFinanciacion.getRazonSocial());
        inconsistenciaFinanciacionDTO.setValorTotalFinanciado(itFinanciacion.getValorTotalFinanciado());
        inconsistenciaFinanciacionDTO.setSaldoFinanciacion(itFinanciacion.getSaldoFinanciacion());
        inconsistenciaFinanciacionDTO.setCodigoTipoIdentificacionCodeudor(itFinanciacion
                .getCodigoTipoIdentificacionCodeudor());
        inconsistenciaFinanciacionDTO.setNumeroIdentificacionCodeudor(itFinanciacion.getNumeroIdentificacionCodeudor());
        inconsistenciaFinanciacionDTO.setPrimerNombreCodeudor(itFinanciacion.getPrimerNombreCodeudor());
        inconsistenciaFinanciacionDTO.setSegundoNombreCodeudor(itFinanciacion.getSegundoNombreCodeudor());
        inconsistenciaFinanciacionDTO.setPrimerApellidoCodeudor(itFinanciacion.getPrimerApellidoCodeudor());
        inconsistenciaFinanciacionDTO.setSegundoApellidoCodeudor(itFinanciacion.getSegundoApellidoCodeudor());
        inconsistenciaFinanciacionDTO.setNumeroCuotaFinanciacion(itFinanciacion.getNumeroCuotaFinanciacion());
        inconsistenciaFinanciacionDTO.setUsuario(itFinanciacion.getUsuario());
        inconsistenciaFinanciacionDTO.setFechaCreacion(itFinanciacion.getFechaCreacion());
        inconsistenciaFinanciacionDTO.setEstadoLectura(itFinanciacion.getEstadoLectura());
        inconsistenciaFinanciacionDTO.setEmailCodeudor(itFinanciacion.getEmailCodeudor());
        inconsistenciaFinanciacionDTO.setEmailDeudor(itFinanciacion.getEmailDeudor());
        inconsistenciaFinanciacionDTO.setTelefonoCodeudor(itFinanciacion.getTelefonoCodeudor());
        inconsistenciaFinanciacionDTO.setTelefonoDeudor(itFinanciacion.getTelefonoDeudor());
        inconsistenciaFinanciacionDTO.setFechaRegistro(UtilFecha.buildCalendar().getTime());

        if (itFinanciacion.getDetalleFinanciacionList() != null) {
            for (ItDetalleFinanciacionDTO itDetalleFinanciacion : itFinanciacion.getDetalleFinanciacionList()) {
                InconsistenciaDetalleFinanciacionDTO inconsistenciaDetalleFinanciacion = new InconsistenciaDetalleFinanciacionDTO();
                inconsistenciaDetalleFinanciacion.setInconsistenciaFinanciacion(inconsistenciaFinanciacionDTO);
                inconsistenciaDetalleFinanciacion.setTipoObligacion(itDetalleFinanciacion.getTipoObligacion());
                inconsistenciaDetalleFinanciacion.setFechaObligacion(itDetalleFinanciacion.getFechaObligacion());
                inconsistenciaDetalleFinanciacion.setValorObligacion(itDetalleFinanciacion.getValorObligacion());
                inconsistenciaDetalleFinanciacion.setObligacion(itDetalleFinanciacion.getObligacion());
                inconsistenciaFinanciacionDTO.getDetalleFinanciacionList().add(inconsistenciaDetalleFinanciacion);
            }
        }

        if (itFinanciacion.getDetalleCuotasFinanciacionList() != null) {
            for (ItDetalleCuotasFinanciacionDTO itDetalleCuotasFinanciacionDTO : itFinanciacion
                    .getDetalleCuotasFinanciacionList()) {
                InconsistenciaDetalleCuotasFinanciacionDTO inconsistenciaDetalleCuotasFinanciacionDTO = new InconsistenciaDetalleCuotasFinanciacionDTO();
                inconsistenciaDetalleCuotasFinanciacionDTO.setInconsistenciaFinanciacion(inconsistenciaFinanciacionDTO);
                inconsistenciaDetalleCuotasFinanciacionDTO.setNumeroCuota(itDetalleCuotasFinanciacionDTO
                        .getNumeroCuota());
                inconsistenciaDetalleCuotasFinanciacionDTO.setFechaPagoOportuno(itDetalleCuotasFinanciacionDTO
                        .getFechaPagoOportuno());
                inconsistenciaDetalleCuotasFinanciacionDTO
                        .setValorCuota(itDetalleCuotasFinanciacionDTO.getValorCuota());
                inconsistenciaDetalleCuotasFinanciacionDTO.setRecargoCuota(itDetalleCuotasFinanciacionDTO
                        .getRecargoCuota());
                inconsistenciaFinanciacionDTO.getDetalleCuotasFinanciacionList().add(
                        inconsistenciaDetalleCuotasFinanciacionDTO);
            }
        }

        return inconsistenciaFinanciacionDTO;
    }
}
