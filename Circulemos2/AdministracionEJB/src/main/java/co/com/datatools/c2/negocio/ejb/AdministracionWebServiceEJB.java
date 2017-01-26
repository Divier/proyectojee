package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.AutenticacionWebServiceDTO;
import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.RespuestaWebServiceDTO;
import co.com.datatools.c2.dto.WebServiceDTO;
import co.com.datatools.c2.entidades.AutenticacionWebService;
import co.com.datatools.c2.entidades.LogEjecucionWS;
import co.com.datatools.c2.entidades.RespuestaWebService;
import co.com.datatools.c2.entidades.WebService;
import co.com.datatools.c2.negocio.helpers.v2.AutenticacionWebServiceHelper;
import co.com.datatools.c2.negocio.helpers.v2.LogEjecucionWSHelper;
import co.com.datatools.c2.negocio.helpers.v2.RespuestaWebServiceHelper;
import co.com.datatools.c2.negocio.helpers.v2.WebServiceHelper;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionWebService;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionWebService;

/**
 * Implementa los servicios expuestos por IAdministracionWebService
 * 
 * @author luis.forero(2016-03-30)
 * 
 */
@Stateless(name = "AdministracionWebServiceEJB")
@LocalBean
public class AdministracionWebServiceEJB implements ILAdministracionWebService, IRAdministracionWebService {

    private final static Logger logger = Logger.getLogger(ConsecutivoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    EntityManager em;

    @Override
    public WebServiceDTO consultarWebServices(Integer id) {
        logger.debug(AdministracionWebServiceEJB.class.getName().concat("::consultarWebServices(Integer)"));
        TypedQuery<WebService> query = em.createNamedQuery(WebService.SQ_FIND_BY_ID_WEB_SERVICE, WebService.class);
        query.setParameter("pIdWebSer", id);

        List<WebService> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }

        WebService webService = resultList.get(0);
        WebServiceDTO webServiceDTO = WebServiceHelper.toLevel1DTO(webService);
        if (!webService.getAutenticacionWebServices().isEmpty()) {
            for (AutenticacionWebService autenticacionWebService : webService.getAutenticacionWebServices()) {
                AutenticacionWebServiceDTO autenticacionWebServiceDTO = AutenticacionWebServiceHelper
                        .toLevel1DTO(autenticacionWebService);
                webServiceDTO.getAutenticacionWebServices().add(autenticacionWebServiceDTO);
            }
        }
        return webServiceDTO;
    }

    @Override
    public List<RespuestaWebServiceDTO> consultarRespuestasWebService(Integer idWebService) {
        logger.debug(AdministracionWebServiceEJB.class.getName().concat("::consultarRespuestasWebService(Integer)"));
        TypedQuery<RespuestaWebService> query = em.createNamedQuery(RespuestaWebService.SQ_FIND_BY_ID_WEB_SERVICE,
                RespuestaWebService.class);
        query.setParameter("pIdWebServ", idWebService);

        List<RespuestaWebService> resultList = query.getResultList();
        List<RespuestaWebServiceDTO> lstResultado = new ArrayList<RespuestaWebServiceDTO>(0);
        if (!resultList.isEmpty()) {
            lstResultado = RespuestaWebServiceHelper.toListLevel1DTO(resultList);
        }
        return lstResultado;
    }

    @Override
    public LogEjecucionWSDTO registrarLogWS(LogEjecucionWSDTO log) {
        logger.debug(AdministracionWebServiceEJB.class.getName().concat("::registrarLogWS(LogEjecucionWSDTO)"));
        LogEjecucionWS logEntity = LogEjecucionWSHelper.toLevel1Entity(log, null);
        em.persist(logEntity);
        log.setId(logEntity.getId());
        return log;
    }
}
