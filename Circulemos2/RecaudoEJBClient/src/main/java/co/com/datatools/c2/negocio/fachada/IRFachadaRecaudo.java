package co.com.datatools.c2.negocio.fachada;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.RespuestaConciliarPagoDTO;
import co.com.datatools.c2.dto.RespuestaReplicarPagoDTO;

/**
 * Fachada de servicios de recaudo
 * 
 * @author diego.lozano (2016-05-05)
 * 
 */
@Remote
public interface IRFachadaRecaudo {

    /**
     * Metodo que consulta los recaudos rechazados y notifica via email
     * 
     * @param organismoDTO
     * @return
     */
    public Integer enviarRechazosRecaudos(Integer codigoOrganismo);

    /**
     * Recibe el codigo de organismo para realizar la tarea de replicar pagos utilizando funcionalidad de la integracion con terceros
     * 
     * @param codigoOrganismo
     * @return Respuesta de la ejecucion de la replica
     * @author julio.pinzon (2016-05-04)
     */
    public RespuestaReplicarPagoDTO replicarPagoTerceros(Integer codigoOrganismo);

    /**
     * Metodo que consulta los recaudos inconsistentes y notifica via email
     * 
     * @param organismoDTO
     * @return
     */
    public Integer enviarInconsistencias(Integer codigoOrganismo);

    /**
     * Envia inconsistencias de conciliación de recaudo vía correo electronico, y retorna la cantidad de registros inconsistentes
     * 
     * @return
     */
    public Integer enviarInconsistenciasConciliacion(Integer codigoOrganismo);

    /**
     * Realiza las validaciones de conciliacion. Aplica para pagos nuevos con un solo detalle de pago y pagos registrados con detalles de pago no
     * conciliados.
     * 
     * @return Numero de detalles aplicados exitosamente
     * @author julio.pinzon 2016-07-26
     */
    public RespuestaConciliarPagoDTO realizarConciliacionRecaudo();

}
