package co.com.datatools.c2.ws.recaudo.recepcion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import co.com.datatools.c2.dto.ws.PagoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaWSDTO;

/**
 * Interface que expone el servicio de replicar pago para clientes externos a la aplicacion.
 * 
 * @author julio.pinzon(2016-04-22)
 * 
 */
@WebService(name = "IServicioReplicarPago", targetNamespace = "http://recepcion.recaudo.ws.c2.datatools.com.co/")
public interface IServicioReplicarPago {

    /**
     * Servicio que permite validar, procesar y/o corregir un pago en el sistema.
     * 
     * @param pago
     *            campos de entrada del pago expuestos en el webservice
     * @return Retorna una respuesta con el codigo y los detalles de las validaciones y datos de procesamientos efectuados
     * @author julio.pinzon
     */
    @WebMethod(operationName = "replicarPago", action = "urn:ReplicarPago")
    public RespuestaWSDTO replicarPago(@WebParam(name = "pago") PagoWSDTO pago);

}