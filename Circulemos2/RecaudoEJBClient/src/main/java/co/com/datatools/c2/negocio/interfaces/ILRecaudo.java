package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.DetallePagoDTO;
import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.RespuestaConciliarPagoDTO;
import co.com.datatools.c2.dto.RespuestaHomologarCatalogoRecaudoDTO;
import co.com.datatools.c2.dto.RespuestaProcesarRecaudoDTO;
import co.com.datatools.c2.dto.RespuestaReplicarPagoDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILRecaudo {

    /**
     * @see IRRecaudo#procesarRecaudo(PagoDTO)
     */
    public RespuestaProcesarRecaudoDTO procesarRecaudo(PagoDTO pagoDTO) throws CirculemosNegocioException;

    /**
     * @see IRRecaudo#replicarPago(int)
     */
    public RespuestaReplicarPagoDTO replicarPagoTerceros(Integer codigoOrganismo);

    /**
     * Recibe el recaudo consultado por terceros y lo envia a procesar
     * 
     * @param recaudoDTO
     * @param loginUsuario
     * @return true si recibio el recaudo, false en caso contrario
     * @author julio.pinzon (2016-05-05)
     */
    public boolean recibirRecaudoTerceros(ItRecaudoDTO recaudoDTO, String loginUsuario);

    /**
     * @see IRRecaudo#homologarCatalogosReplicarPago(PagoDTO, ItRecaudoDTO)
     */
    public RespuestaHomologarCatalogoRecaudoDTO homologarCatalogosReplicarPago(PagoDTO pagoDTO, ItRecaudoDTO recaudo);

    /**
     * @see IRRecaudo#consultarPagos(PagoDTO, DetallePagoDTO)
     */
    public List<PagoDTO> consultarPagos(PagoDTO pagoDTO, DetallePagoDTO detallePagoDTO);

    /**
     * @see IRRecaudo#realizarConciliacionRecaudo(PagoDTO)
     */
    public RespuestaConciliarPagoDTO realizarConciliacionRecaudo();

    /**
     * Aplica para pagos nuevos con un solo detalle de pago y pagos registrados con detalles de pago no conciliados. <br>
     * 
     * CASOS:
     * <ul>
     * <li>1. Pago nuevo con detalle de pago nuevo (registrar pago y detalle de pago)
     * <li>2. Pago existente con detalle de pago nuevo (registrar detalle de pago)
     * <li>3. Pago existente con detalle de pago existente (procesar detalle de pago)
     * </ul>
     * 
     * @param pago
     *            El pago con reglas de negocio y forma validadas
     * @author julio.pinzon(2016-07-26)
     * @throws CirculemosNegocioException
     */
    public int conciliarRecaudo(PagoDTO pago) throws CirculemosNegocioException;

}
