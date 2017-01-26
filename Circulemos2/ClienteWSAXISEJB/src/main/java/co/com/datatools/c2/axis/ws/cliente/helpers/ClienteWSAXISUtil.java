package co.com.datatools.c2.axis.ws.cliente.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.com.datatools.c2.axis.ws.cliente.vo.coactivo.CoactivoVO;
import co.com.datatools.c2.axis.ws.cliente.vo.coactivo.InfraccionCoactivoVO;
import co.com.datatools.c2.axis.ws.cliente.vo.financiacion.CuotaVO;
import co.com.datatools.c2.axis.ws.cliente.vo.financiacion.FinanciacionVO;
import co.com.datatools.c2.axis.ws.cliente.vo.financiacion.InfraccionVO;
import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.DetalleFinanciacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.ObligacionCoactivoDTO;
import co.com.datatools.c2.dto.ObligacionFinanciacionDTO;

/**
 * Utilidades utilizadas en el cliente de AXIS
 * 
 * @author julio.pinzon(2016-08-16)
 * 
 */
public class ClienteWSAXISUtil {

    public static String EMPRESA = "01";
    public static String USUARIO = "DATATOOLS";

    /**
     * Convierte un dto de financiacion en un vo que se utilizará como parametro del servicio de registro en axis
     * 
     * @param dto
     * @return Vo de financiamiento
     * @author julio.pinzon 2016-08-16
     */
    public static FinanciacionVO homologarFinanciamiento(FinanciacionDTO dto) {
        FinanciacionVO vo = new FinanciacionVO();
        vo.setConvenioReferencia(Long.valueOf(dto.getNumeroReferenciaTerceros()));
        vo.setTipoIdentificacion(dto.getDeudor().getTipoIdentificacion().getCodigo());
        vo.setIdentificacion(dto.getDeudor().getNumeroIdentificacion());
        vo.setEmpresa(EMPRESA);
        vo.setUsuario(USUARIO);
        vo.setFechaConvenio(dto.getProceso().getFechaInicio());
        vo.setDeudaAFinanciar(dto.getValorTotal());
        vo.setPlazo(dto.getNumeroCuotas());
        if (!dto.getDetallesFinanciacion().isEmpty()) {
            vo.setValorCuotaIncial(dto.getDetallesFinanciacion().get(0).getValorCapital());
        }

        vo.setInteresesTotal(
                dto.getValorTotalFinanciarInteres().subtract(dto.getValorTotal()).setScale(2, RoundingMode.FLOOR));
        vo.setPorcentajeInteres(new BigDecimal(dto.getTasaInteres()).setScale(3, RoundingMode.FLOOR));
        // cuotas

        List<CuotaVO> cuotasVO = new ArrayList<>();
        int numeroCuota = 0;
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.setTime(dto.getProceso().getFechaInicio());
        for (DetalleFinanciacionDTO cuotaDTO : dto.getDetallesFinanciacion()) {
            CuotaVO cuotaVO = new CuotaVO();
            cuotaVO.setNroCuota(numeroCuota);
            cuotaVO.setMonto(cuotaDTO.getValorCapital());
            cuotaVO.setInteres(cuotaDTO.getValorIntereses());
            cuotaVO.setFechaInicio(fechaInicio.getTime());
            cuotaVO.setFechaVencimiento(cuotaDTO.getFechaPagoOportuno());
            cuotasVO.add(cuotaVO);
            numeroCuota++;
            fechaInicio.setTime(cuotaDTO.getFechaPagoOportuno());
            fechaInicio.add(Calendar.DAY_OF_YEAR, 1);
        }
        vo.setCuotas(cuotasVO);

        // infracciones
        List<InfraccionVO> infraccionesVO = new ArrayList<>();
        for (ObligacionFinanciacionDTO infraccionDTO : dto.getObligacionesFinanciacion()) {

            InfraccionVO infraccionVO = new InfraccionVO();
            infraccionVO.setIdfactura(Long.valueOf(infraccionDTO.getNumeroObligacion()));
            infraccionVO.setMonto(infraccionDTO.getValorObligacion());
            infraccionVO.setInteres(infraccionDTO.getValorInteresMoratorios());
            infraccionesVO.add(infraccionVO);
        }
        vo.setInfracciones(infraccionesVO);

        return vo;
    }

    /**
     * Convierte un dto de coactivo en un vo que se utilizará como parametro del servicio de registro en axis
     * 
     * @param coactivoDTO
     * @return vo de coactivo
     * @author Jeison.Rodriguez (2016-09-22)
     */
    public static CoactivoVO homologarCoactivo(CoactivoDTO coactivoDTO) {
        CoactivoVO coactivo = new CoactivoVO();

        coactivo.setTipoIdentificacion(coactivoDTO.getPersona().getTipoIdentificacion().getCodigo());
        coactivo.setIdentificacion(coactivoDTO.getPersona().getNumeroIdentificacion());
        coactivo.setFechaCoactiva(coactivoDTO.getProceso().getFechaInicio());
        coactivo.setMontoCoactiva(coactivoDTO.getValorTotalObligaciones());
        coactivo.setCostoJudicial(coactivoDTO.getValorTotalCostasProcesales());
        coactivo.setGastoAdmin(BigDecimal.ZERO);
        coactivo.setObservacion(coactivoDTO.getProceso().getObservacion());
        coactivo.setEmpresa(EMPRESA);
        coactivo.setUsuario(USUARIO);

        List<InfraccionCoactivoVO> infraccionCoactivo = new ArrayList<>();
        InfraccionCoactivoVO infraccionVO;
        for (ObligacionCoactivoDTO infraccionDTO : coactivoDTO.getObligacionCoactivos()) {
            infraccionVO = new InfraccionCoactivoVO();
            infraccionVO.setIdTipoDocumento("FAC");
            infraccionVO.setIdDocumento(Integer.valueOf(infraccionDTO.getNumeroObligacion()));
            infraccionVO.setMonto(infraccionDTO.getValorObligacion());
            infraccionVO.setInteres(infraccionDTO.getValorInteresMoratorios());
            infraccionCoactivo.add(infraccionVO);
        }
        coactivo.setInfracciones(infraccionCoactivo);
        return coactivo;
    }
}
