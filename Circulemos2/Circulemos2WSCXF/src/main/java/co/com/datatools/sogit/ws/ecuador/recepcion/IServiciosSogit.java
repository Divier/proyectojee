package co.com.datatools.sogit.ws.ecuador.recepcion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import co.com.datatools.c2.dto.ws.ComparendoWSDTO;
import co.com.datatools.c2.dto.ws.PagoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaRecibirComparendoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaWSDTO;

@WebService(name = "IServiciosSogit", targetNamespace = "http://recepcion.ecuador.ws.sogit.datatools.com.co/")
public interface IServiciosSogit {

    /**
     * Servicio que permite validar, procesar y/o corregir un comparendo en el sistema.
     * 
     * @param comparendo
     *            campos de entrada del comparendo expuestos en el webservice
     * @return Retorna una respuesta con el codigo y los detalles de las validaciones y datos de procesamientos efectuados
     * @author luis.forero
     */
    @WebMethod(operationName = "recibirComparendo", action = "urn:RecibirComparendo")
    public RespuestaRecibirComparendoWSDTO recibirComparendo(@WebParam(name = "comparendo") ComparendoWSDTO comparendo);

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
