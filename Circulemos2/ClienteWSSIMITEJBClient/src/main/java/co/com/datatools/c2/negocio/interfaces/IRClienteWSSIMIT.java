package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.HomologacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.common.RespuestaConsultaComparendoDTO;
import co.com.datatools.c2.dto.common.RespuestaNotificacionDTO;

/**
 * Interface que ofrece los servicios para consumir WebService de SIMIT
 * 
 * @author luis.forero(2016-03-29)
 * 
 */
@Remote
public interface IRClienteWSSIMIT {

    /**
     * Realiza la invocacion con el WebService para realizar la notificacion de un comparendo a SIMIT.
     * 
     * @param comparendo
     *            datos homologados del comparendo a ser enviados o notificados a SIMIT
     * @return respuesta del web service
     * @author luis.forero(2016-03-29)
     */
    RespuestaNotificacionDTO notificarComparendoSIMIT(HomologacionComparendoSIMITDTO comparendo);

    /**
     * Consulta el historico de los comparendos asociados para la persona con tipo y numero de documento indicado desde SIMIT.
     * 
     * @param idTipoIdentificacion
     *            identificador del tipo de identificacion de la persona enviado al web service para consultar
     * @param numeroIdentificacion
     *            numero de identificacion de la persona
     * @return Retorna RespuestaConsultaComparendoDTO acorde a los comparendos obtenidos. Con null en el campo idInfraccion de cada infracción,
     *         consultando de SIMIT
     * @author luis.forero(2016-04-14)
     */
    RespuestaConsultaComparendoDTO consultarComparendos(Integer idTipoIdentificacion, String numeroIdentificacion);
}
