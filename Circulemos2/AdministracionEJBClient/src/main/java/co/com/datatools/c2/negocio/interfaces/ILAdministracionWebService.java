package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.WebServiceDTO;

/**
 * @see IRAdministracionWebService
 */
@Local
public interface ILAdministracionWebService {

    /**
     * @see IRAdministracionWebService#consultarWebServices(Integer)
     */
    public WebServiceDTO consultarWebServices(Integer id);

    /**
     * @see IRAdministracionWebService#consultarRespuestasWebService(Integer)
     */
    public List<RespuestaWebServiceDTO> consultarRespuestasWebService(Integer idWebService);

    /**
     * @see IRAdministracionWebService#registrarLogWS(LogEjecucionWSDTO)
     */
    public LogEjecucionWSDTO registrarLogWS(LogEjecucionWSDTO log);
}
