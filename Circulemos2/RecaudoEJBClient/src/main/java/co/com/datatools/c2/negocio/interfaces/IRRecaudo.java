package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.DetallePagoDTO;
import co.com.datatools.c2.dto.PagoDTO;
import co.com.datatools.c2.dto.RespuestaConciliarPagoDTO;
import co.com.datatools.c2.dto.RespuestaHomologarCatalogoRecaudoDTO;
import co.com.datatools.c2.dto.RespuestaProcesarRecaudoDTO;
import co.com.datatools.c2.dto.RespuestaReplicarPagoDTO;
import co.com.datatools.c2.dto.recaudo.ItRecaudoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRRecaudo {

    /**
     * Recibe informacion de un pago, realizando las validaciones de negocio necesarias y el cruce de pagos con cartera. Dependiendo el resultado de
     * estas operaciones registra el pago como inconsistente, no conciliado o aplicado. Es decir, realiza el procesamiento del pago.
     * 
     * @param pagoDTO
     * @return
     * @author diego.lozano
     * @throws CirculemosNegocioException
     */
    public RespuestaProcesarRecaudoDTO procesarRecaudo(PagoDTO pagoDTO) throws CirculemosNegocioException;

    /**
     * Recibe el codigo de organismo para realizar la tarea de replicar pagos utilizando funcionalidad de la integracion con terceros
     * 
     * @param codigoOrganismo
     * @return Respuesta de la ejecucion de la replica
     * @author julio.pinzon (2016-05-04)
     */
    public RespuestaReplicarPagoDTO replicarPagoTerceros(Integer codigoOrganismo);

    /**
     * Homologa y asigna el valor correspondiente de cada uno de los catalogos entrantes
     * 
     * @param recaudo
     *            objeto convertido con los datos de entrada y los catalogos
     * @param pago
     *            datos del comparendo entrante
     * @return Lista de catalogos con error y pago actualizado
     * @author julio.pinzon (2016-05-05)
     */
    public RespuestaHomologarCatalogoRecaudoDTO homologarCatalogosReplicarPago(PagoDTO pagoDTO, ItRecaudoDTO recaudo);

    /**
     * Consulta pagos los filtros enviados
     * 
     * @param pagoDTO
     *            Filtros de encabezado de pago
     * @param detallePagoDTO
     *            Filtros de detalle de pago
     * @return Lista de pagos, null si no existen resultados
     * @author rodrigo.cruz
     */
    public List<PagoDTO> consultarPagos(PagoDTO pagoDTO, DetallePagoDTO detallePagoDTO);

    /**
     * Realiza las validaciones de conciliacion. Aplica para pagos nuevos con un solo detalle de pago y pagos registrados con detalles de pago no
     * conciliados.
     * 
     * @return Numero de detalles aplicados exitosamente
     * @author julio.pinzon 2016-07-26
     */
    public RespuestaConciliarPagoDTO realizarConciliacionRecaudo();

}
