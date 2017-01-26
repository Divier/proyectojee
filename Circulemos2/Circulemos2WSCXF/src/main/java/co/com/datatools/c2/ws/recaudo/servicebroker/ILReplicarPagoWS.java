/**
 * 
 */
package co.com.datatools.c2.ws.recaudo.servicebroker;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ws.PagoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaWSDTO;

/**
 * Interfaz que proporciona los servicios para procesar la peticion del WS de replicar pago
 * 
 * @author julio.pinzon(2016-04-22)
 */
@Local
public interface ILReplicarPagoWS {

    /**
     * Recibe la informacion de un pago por WS, procesa campos obligatorios, estructura, registra proceso en BD y retorna los resultados obtenido del
     * procesamiento
     * 
     * @param pago
     *            datos del pago
     * @return respuesta con las descripciones de los resultados de procesamiento
     * @author julio.pinzon (2016-04-22)
     */
    public RespuestaWSDTO replicarPagoWs(PagoWSDTO pago);
}
