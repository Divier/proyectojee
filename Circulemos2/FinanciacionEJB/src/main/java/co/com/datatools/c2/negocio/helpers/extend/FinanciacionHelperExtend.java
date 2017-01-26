package co.com.datatools.c2.negocio.helpers.extend;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConsultaDetalleFinanciacionDTO;
import co.com.datatools.c2.dto.ConsultaFinanciacionDTO;
import co.com.datatools.c2.dto.ConsultaObligacionesFinanciacionDTO;
import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.FinanciacionCarteraDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;
import co.com.datatools.c2.entidades.Financiacion;
import co.com.datatools.c2.negocio.helpers.DetalleFinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.FinanciacionCarteraHelper;
import co.com.datatools.c2.negocio.helpers.FinanciacionHelper;
import co.com.datatools.c2.negocio.helpers.ObligacionFinanciacionHelper;

/**
 * 
 * @author giovanni.velandia
 */
public class FinanciacionHelperExtend extends FinanciacionHelper {

    /**
     * Se encarga de transformar un FinanciacionDTO en una respuesta para el proceso de ConsultaFinanciacionDTO
     * 
     * @author giovanni.velandia
     * @param financiacionDTO
     * @return
     */
    public static ConsultaFinanciacionDTO toConsultaFinanciacion(FinanciacionDTO financiacionDTO) {
        ConsultaFinanciacionDTO consultaFinanciacionDTO = new ConsultaFinanciacionDTO();
        // Id Proceso
        consultaFinanciacionDTO.setIdProceso(financiacionDTO.getProceso().getId());
        // Id financiacion
        consultaFinanciacionDTO.setIdFinanciacion(financiacionDTO.getId());
        // Numero financiacion
        consultaFinanciacionDTO.setNumeroFinanciacion(financiacionDTO.getNumeroFinanciacion());
        // Fecha Solicitud
        consultaFinanciacionDTO.setFechaSolicitud(financiacionDTO.getProceso().getFechaInicio());
        // Cantidad obligaciones
        consultaFinanciacionDTO.setCantidadObligaciones(financiacionDTO.getCantidadObligaciones());
        // Total saldo capital de obligaciones
        consultaFinanciacionDTO
                .setTotalSaldoCapitalObligaciones(financiacionDTO.getValorTotalSaldoCapitalObligaciones());
        // Valor total de intereses moratorios, para Guayaquil es "Valor total de recargo".
        consultaFinanciacionDTO.setValorTotalRecargo(financiacionDTO.getValorTotalInteresesMoratorios());
        // Valor total Costas procesales
        consultaFinanciacionDTO.setValorTotalCostasProcesales(financiacionDTO.getValorTotalCostasProcesales());
        // Valor total
        consultaFinanciacionDTO.setValorTotal(financiacionDTO.getValorTotal());
        // Valor total financiar con intereses
        consultaFinanciacionDTO.setValorTotalFinanciarInteres(financiacionDTO.getValorTotalFinanciarInteres());
        // Valor mora
        consultaFinanciacionDTO.setValorMora(financiacionDTO.getValorMora());
        // id tramite
        consultaFinanciacionDTO.setIdTramite(financiacionDTO.getIdTramite());
        // origen financiacion
        if (financiacionDTO.getFuenteInformacion() != null) {
            consultaFinanciacionDTO.setOrigenFinanciacion(financiacionDTO.getFuenteInformacion().getNombre());
        }
        return consultaFinanciacionDTO;
    }

    /**
     * Se encarga de transformar un FinanciacionDTO en una respuesta para el proceso de ConsultaFinanciacionDTO con su respectivo detalle
     * 
     * @author giovanni.velandia
     * @param financiacionDTO
     * @return
     */
    public static ConsultaFinanciacionDTO toConsultaDetalleFinanciacion(FinanciacionDTO financiacionDTO) {
        ConsultaFinanciacionDTO consultaFinanciacionDTO = toConsultaFinanciacion(financiacionDTO);

        // Datos adicionales del detalle
        // Numero de documento
        consultaFinanciacionDTO.setNumeroDocumento(financiacionDTO.getDeudor().getNumeroIdentificacion());
        // Nombre y apellidos
        consultaFinanciacionDTO.setNombresApellidos(financiacionDTO.getDeudor().getNombreCompleto());
        // Tasa de interes
        consultaFinanciacionDTO.setTasaInteresFinanciacion(financiacionDTO.getTasaInteres());
        // Numero de cuotas
        consultaFinanciacionDTO.setNumeroCuotas(financiacionDTO.getNumeroCuotas());

        // Detalles
        consultaFinanciacionDTO.setConsultaDetalleFinanciacionDTOs(new ArrayList<ConsultaDetalleFinanciacionDTO>());
        consultaFinanciacionDTO
                .setConsultaObligacionesFinanciacionDTOs(new ArrayList<ConsultaObligacionesFinanciacionDTO>());
        for (DetalleFinanciacionDTO detalleFinanciacionDTO : financiacionDTO.getDetallesFinanciacion()) {
            ConsultaDetalleFinanciacionDTO consultaDetalleFinanciacionDTO = new ConsultaDetalleFinanciacionDTO();
            // id
            consultaDetalleFinanciacionDTO.setIdDetalleFinanciacion(detalleFinanciacionDTO.getId());
            // Fecha pago oportuno
            consultaDetalleFinanciacionDTO.setFechaPagoOportuno(detalleFinanciacionDTO.getFechaPagoOportuno());
            // Fecha real de pago
            consultaDetalleFinanciacionDTO.setFechaRealPago(detalleFinanciacionDTO.getFechaPago());
            // Numero cuota
            consultaDetalleFinanciacionDTO.setNumeroCuota(detalleFinanciacionDTO.getNumeroCuota());
            // Saldo obligacion
            consultaDetalleFinanciacionDTO.setSaldoObligacion(detalleFinanciacionDTO.getValorSaldoObligacion());
            // Valor capital
            consultaDetalleFinanciacionDTO.setValorCapital(detalleFinanciacionDTO.getValorCapital());
            // Valor interes
            consultaDetalleFinanciacionDTO.setValorInteres(detalleFinanciacionDTO.getValorIntereses());
            // Valor cuota total
            consultaDetalleFinanciacionDTO.setValorTotalCuota(detalleFinanciacionDTO.getValorTotal());
            consultaFinanciacionDTO.getConsultaDetalleFinanciacionDTOs().add(consultaDetalleFinanciacionDTO);
        }

        for (ObligacionFinanciacionDTO obligacionFinanciacionDTO : financiacionDTO.getObligacionesFinanciacion()) {
            ConsultaObligacionesFinanciacionDTO consultaObligacionesFinanciacionDTO = new ConsultaObligacionesFinanciacionDTO();
            // id
            consultaObligacionesFinanciacionDTO.setIdObligacion(obligacionFinanciacionDTO.getId());
            // Tipo de Obligacion
            consultaObligacionesFinanciacionDTO.setTipoObligacion(obligacionFinanciacionDTO.getCodigoTipoObligacion());
            // Numero de obligacion
            consultaObligacionesFinanciacionDTO.setNumeroObligacion(obligacionFinanciacionDTO.getNumeroObligacion());
            // Fecha de la obligacion
            consultaObligacionesFinanciacionDTO.setFechaObligacion(obligacionFinanciacionDTO.getFechaObligacion());
            // Valor obligacion
            consultaObligacionesFinanciacionDTO.setValorObligacion(obligacionFinanciacionDTO.getValorObligacion());
            // Valor recargo
            consultaObligacionesFinanciacionDTO.setValorRecargo(obligacionFinanciacionDTO.getValorInteresMoratorios());
            // Valor costas procesales
            consultaObligacionesFinanciacionDTO
                    .setValorCostasProcesales(obligacionFinanciacionDTO.getValorCostasProcesales());
            consultaFinanciacionDTO.getConsultaObligacionesFinanciacionDTOs().add(consultaObligacionesFinanciacionDTO);
        }
        return consultaFinanciacionDTO;
    }

    /**
     * Convierte una financiacion en un dto con las obligaciones y cuotas
     * 
     * @param entidad
     * @return dto de financiacion
     * @author julio.pinzon 2016-07-27
     */
    public static FinanciacionDTO toLevel2DTO(Financiacion entidad) {
        FinanciacionDTO dto = toLevel1DTO(entidad);

        List<DetalleFinanciacionDTO> detallesFinanciacionDTO = DetalleFinanciacionHelper
                .toListLevel1DTO(entidad.getDetallesFinanciacion());
        List<ObligacionFinanciacionDTO> obligacionesFinanciacionDTO = ObligacionFinanciacionHelper
                .toListLevel1DTO(entidad.getObligacionesFinanciacion());
        List<FinanciacionCarteraDTO> financiacionCarterasDTO = FinanciacionCarteraHelper
                .toListLevel1DTO(entidad.getFinanciacionCarteraList());
        dto.setDetallesFinanciacion(detallesFinanciacionDTO);
        dto.setObligacionesFinanciacion(obligacionesFinanciacionDTO);
        dto.setFinanciacionCarteraList(financiacionCarterasDTO);

        return dto;
    }

    /**
     * Se encarga de adicionar el detalle de la financiacion
     * 
     * @param List
     *            <Financiacion> lsFinanciacion
     * @return List<FinanciacionDTO>
     * @author divier.casas
     */
    public static List<FinanciacionDTO> toListLevel1DTOExtend(List<Financiacion> lsFinanciacion) {
        List<FinanciacionDTO> listFinanciacionDTO = new ArrayList<FinanciacionDTO>();
        FinanciacionDTO financiacionDTO = null;
        for (Financiacion financiacion : lsFinanciacion) {
            financiacionDTO = FinanciacionHelper.toLevel1DTO(financiacion);
            financiacionDTO.setDetallesFinanciacion(
                    DetalleFinanciacionHelper.toListLevel1DTO(financiacion.getDetallesFinanciacion()));
            listFinanciacionDTO.add(financiacionDTO);
        }
        return listFinanciacionDTO;
    }
}