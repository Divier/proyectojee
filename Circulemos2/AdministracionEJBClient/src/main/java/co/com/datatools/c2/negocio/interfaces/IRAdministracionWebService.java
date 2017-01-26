package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.WebServiceDTO;

/**
 * Interface que ofrece los servicios para la administraciOn de WebServices
 * 
 * @author luis.forero(2016-03-30)
 * 
 */
@Remote
public interface IRAdministracionWebService {

    /**
     * Consulta la informacion del web services con el identificador indicado.
     * 
     * @param id
     *            identificador del webservice.
     * @return Retorna el la informacion del web services con el identificador indicado.
     * 
     * @author luis.forero(2016-03-30)
     */
    public WebServiceDTO consultarWebServices(Integer id);

    /**
     * Consulta las respuestas posibles que retorna un web service
     * 
     * @param idWebService
     *            identificador del web service a obtener las respuestas posibles
     * @return Retorna un listado de respuestas correspondientes a un determinado web service configurado en el sistema.
     * @author luis.forero(2016-04-06)
     */
    public List<RespuestaWebServiceDTO> consultarRespuestasWebService(Integer idWebService);

    /**
     * Registra el log de la ejecucion de un Web Service
     * 
     * @param log
     * @return
     */
    public LogEjecucionWSDTO registrarLogWS(LogEjecucionWSDTO log);
}
