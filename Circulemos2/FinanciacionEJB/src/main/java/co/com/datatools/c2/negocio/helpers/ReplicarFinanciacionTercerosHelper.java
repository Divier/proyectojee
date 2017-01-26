package co.com.datatools.c2.negocio.helpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.financiacion.ItDetalleCuotasFinanciacionDTO;
import co.com.datatools.c2.dto.financiacion.ItDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.financiacion.ItFinanciacionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;

/**
 * Helper para WS de replicar pago
 * 
 * @author julio.pinzon(2016-05-05)
 * 
 */
public class ReplicarFinanciacionTercerosHelper {

    /**
     * Convierte Recaudo de terceros a un pago
     * 
     * @param financiacion
     * @return Pago
     * @author julio.pinzon(2016-05-05)
     */
    public static FinanciacionDTO toFinanciacionDTO(ItFinanciacionDTO financiacion) {
        FinanciacionDTO dto = new FinanciacionDTO();

        dto.setNumeroFinanciacion(financiacion.getNumeroFinanciacion() + "");
        dto.setNumeroCuotas(financiacion.getNumeroCuotaFinanciacion());
        dto.setFechaFinanciacion(financiacion.getFechaFinanciacion());
        dto.setValorTotal(financiacion.getValorTotalFinanciado());
        dto.setOrganismoTransito(new OrganismoTransitoDTO(financiacion.getCodigoOrganismo()));
        dto.setFuenteInformacion(new TipoFuenteInformacionDTO(EnumTipoFuenteInformacion.AXIS.getValue()));
        dto.setCantidadObligaciones(financiacion.getDetalleFinanciacionList().size());
        dto.setValorTotalCostasProcesales(BigDecimal.ZERO);
        dto.setTasaInteres(BigDecimal.ZERO.doubleValue());
        dto.setValorMora(BigDecimal.ZERO);
        dto.setValorTotalFinanciarInteres(financiacion.getValorTotalFinanciado());
        dto.setValorTotalSaldoCapitalObligaciones(financiacion.getValorTotalFinanciado());
        dto.setValorTotalInteresesMoratorios(BigDecimal.ZERO);

        if (StringUtils.isNotBlank(financiacion.getRazonSocial())) {
            PersonaJuridicaDTO deudor = new PersonaJuridicaDTO();
            deudor.setNombreComercial(financiacion.getRazonSocial());
            deudor.setNumeroIdentificacion(financiacion.getNumeroIdentificacionDeudor());
            deudor.setOrganismoTransito(dto.getOrganismoTransito());
            TipoIdentificacionPersonaDTO tipoId = new TipoIdentificacionPersonaDTO();
            tipoId.setCodigo(financiacion.getCodigoTipoIdentificacionDeudor());
            deudor.setTipoIdentificacion(tipoId);
            dto.setDeudor(deudor);
        } else {
            PersonaDTO deudor = new PersonaDTO();
            deudor.setApellido1(financiacion.getPrimerApellidoDeudor());
            deudor.setApellido2(financiacion.getSegundoApellidoDeudor());
            deudor.setNombre1(financiacion.getPrimerNombreDeudor());
            deudor.setNombre2(financiacion.getSegundoNombreDeudor());
            deudor.setNumeroIdentificacion(financiacion.getNumeroIdentificacionDeudor());
            deudor.setOrganismoTransito(dto.getOrganismoTransito());
            // TODO: CAMBIO DISENIO UBICABILIDAD
            // deudor.setCorreoElectronico(financiacion.getEmailDeudor());
            // deudor.setNumeroTelefonico(financiacion.getTelefonoDeudor());
            TipoIdentificacionPersonaDTO tipoId = new TipoIdentificacionPersonaDTO();
            tipoId.setCodigo(financiacion.getCodigoTipoIdentificacionDeudor());
            deudor.setTipoIdentificacion(tipoId);
            dto.setDeudor(deudor);
        }

        if (StringUtils.isNotBlank(financiacion.getCodigoTipoIdentificacionCodeudor())
                && StringUtils.isNotBlank(financiacion.getNumeroIdentificacionCodeudor())) {
            PersonaDTO codeudor = new PersonaDTO();
            codeudor.setNumeroIdentificacion(financiacion.getNumeroIdentificacionCodeudor());
            codeudor.setApellido1(financiacion.getPrimerApellidoCodeudor());
            codeudor.setApellido2(financiacion.getSegundoApellidoCodeudor());
            codeudor.setNombre1(financiacion.getPrimerNombreCodeudor());
            codeudor.setNombre2(financiacion.getSegundoNombreCodeudor());
            codeudor.setOrganismoTransito(dto.getOrganismoTransito());
            // TODO: CAMBIO DISENIO UBICABILIDAD
            // codeudor.setCorreoElectronico(financiacion.getEmailCodeudor());
            // codeudor.setNumeroTelefonico(financiacion.getTelefonoCodeudor());
            TipoIdentificacionPersonaDTO tipoIdCodeudor = new TipoIdentificacionPersonaDTO();
            tipoIdCodeudor.setCodigo(financiacion.getCodigoTipoIdentificacionCodeudor());
            codeudor.setTipoIdentificacion(tipoIdCodeudor);
            dto.setCodeudor(codeudor);
        }

        List<DetalleFinanciacionDTO> detallesFinanciacion = new ArrayList<DetalleFinanciacionDTO>();
        BigDecimal saldo = financiacion.getValorTotalFinanciado();
        if (financiacion.getDetalleCuotasFinanciacionList() != null) {
            Collections.sort(financiacion.getDetalleCuotasFinanciacionList(),
                    new Comparator<ItDetalleCuotasFinanciacionDTO>() {
                        @Override
                        public int compare(ItDetalleCuotasFinanciacionDTO obj1, ItDetalleCuotasFinanciacionDTO obj2) {
                            return obj1.getNumeroCuota().compareTo(obj2.getNumeroCuota());
                        }
                    });
            for (ItDetalleCuotasFinanciacionDTO dtoCuota : financiacion.getDetalleCuotasFinanciacionList()) {
                saldo = saldo.subtract(dtoCuota.getValorCuota());
                DetalleFinanciacionDTO detalleCuotas = new DetalleFinanciacionDTO();
                detalleCuotas.setNumeroCuota(dtoCuota.getNumeroCuota());
                detalleCuotas.setValorTotal(dtoCuota.getValorCuota());
                detalleCuotas.setValorCapital(dtoCuota.getValorCuota());
                detalleCuotas.setValorIntereses(BigDecimal.ZERO);
                detalleCuotas.setValorSaldoObligacion(saldo);
                detalleCuotas.setFechaPagoOportuno(dtoCuota.getFechaPagoOportuno());
                detallesFinanciacion.add(detalleCuotas);
            }
        }
        dto.setDetallesFinanciacion(detallesFinanciacion);

        BigDecimal valorTotalCapitalObligaciones = BigDecimal.ZERO;
        List<ObligacionFinanciacionDTO> detallesCuotas = new ArrayList<ObligacionFinanciacionDTO>();
        if (financiacion.getDetalleFinanciacionList() != null) {
            for (ItDetalleFinanciacionDTO dtoObligacion : financiacion.getDetalleFinanciacionList()) {
                ObligacionFinanciacionDTO detalleObligacion = new ObligacionFinanciacionDTO();
                detalleObligacion.setNumeroObligacion(dtoObligacion.getObligacion().toString());
                detalleObligacion.setFechaObligacion(dtoObligacion.getFechaObligacion());
                detalleObligacion.setValorObligacion(dtoObligacion.getValorObligacion());
                detalleObligacion.setValorInteresMoratorios(BigDecimal.ZERO);
                detalleObligacion.setValorCostasProcesales(BigDecimal.ZERO);
                detallesCuotas.add(detalleObligacion);
                valorTotalCapitalObligaciones = valorTotalCapitalObligaciones
                        .add(detalleObligacion.getValorObligacion());
            }
        }
        dto.setValorTotalSaldoCapitalObligaciones(valorTotalCapitalObligaciones);
        dto.setObligacionesFinanciacion(detallesCuotas);
        return dto;
    }
}
