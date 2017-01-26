/**
 * 
 */
package co.com.datatools.c2.ws.comparendo.servicebroker;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ws.ComparendoWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaRecibirComparendoWSDTO;

/**
 * Interfaz que proporciona los servicios para procesar la peticion del WS de recibir comparendo
 * 
 * @author luis.forero(2015-11-12)
 */
@Local
public interface ILRecibirComparendoWS {

    /**
     * Recibe la informacion de un comparendo por WS, procesa campos obligatorios, estructura, registra proceso en BD y retorna los resultados
     * obtenido del procesamiento
     * 
     * @param comparendo
     *            datos del comparendo
     * @return respuesta con las descripciones de los resultados de procesamiento
     * @author luis.forero (2015-11-12)
     */
    RespuestaRecibirComparendoWSDTO procesarComparendoWs(ComparendoWSDTO comparendo);
}
