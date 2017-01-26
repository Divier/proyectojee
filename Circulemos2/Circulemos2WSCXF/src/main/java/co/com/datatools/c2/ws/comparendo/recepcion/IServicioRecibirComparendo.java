package co.com.datatools.c2.ws.comparendo.recepcion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import co.com.datatools.c2.dto.ws.ComparendoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaRecibirComparendoWSDTO;

/**
 * Interface que expone el servicio de recibir comparendo para clientes externos a la aplicacion.
 * 
 * @author luis.forero(2015-11-12)
 * 
 */
@WebService(
        name = "IServicioRecibirComparendo",
        targetNamespace = "http://recepcion.comparendo.ws.c2.datatools.com.co/")
public interface IServicioRecibirComparendo {

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

}