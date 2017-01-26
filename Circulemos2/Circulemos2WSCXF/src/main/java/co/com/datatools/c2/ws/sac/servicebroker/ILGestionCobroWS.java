package co.com.datatools.c2.ws.sac.servicebroker;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ws.GestionCobroWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaGestionCobroWSDTO;

/**
 * Interfaz que proporciona los servicios para procesar la notioficaicon de Cobro de SAC.
 * 
 * @author javier.fajardo(2016-04-25)
 */
@Local
public interface ILGestionCobroWS {

	/**
     * Recibe la informacion de la notificacionde cobro por WS, procesa la estructura y registra proceso en BD y retorna los resultados
     * obtenido del procesamiento
     * 
     * @param gestion de cobro de SAC.
     * @return respuesta con las descripciones de los resultados de procesamiento
     * @author javier.fajardo(2016-04-25)
     */
	RespuestaGestionCobroWSDTO procesarGestionCobro(GestionCobroWSDTO gestionCobro); 
}
