package co.com.datatools.c2.negocio.ejb.administracion;

import static co.com.datatools.c2.util.Utilidades.safeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.entidades.ConfiguracionInfraccion;
import co.com.datatools.c2.entidades.Infraccion;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.helpers.extencion.ConfiguracionInfraccionHelperExtend;
import co.com.datatools.c2.negocio.helpers.extencion.InfraccionHelperExtend;
import co.com.datatools.c2.negocio.interfaces.administracion.ILInfraccion;
import co.com.datatools.c2.negocio.interfaces.administracion.IRInfraccion;
import co.com.datatools.c2.util.MapUtils;
import co.com.datatools.util.GenericDao;

/**
 * Session Bean implementation class InfraccionEJB
 */
@Stateless(name = "InfraccionEJB")
@LocalBean
public class InfraccionEJB implements IRInfraccion, ILInfraccion {

    private final static Logger logger = Logger.getLogger(InfraccionEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public ConfiguracionInfraccionDTO consultarInfraccion(String codigoAlfanumerico, Date fechaVigencia) {
        logger.debug("InfraccionEJB::consultarInfraccion");
        final List<ConfiguracionInfraccion> infrList = consultarInfraccionPorCodigoAlfaNum(
                Arrays.asList(codigoAlfanumerico), fechaVigencia);

        if (infrList.isEmpty()) {
            return null;
        } else if (infrList.size() > 1) {
            throw new CirculemosRuntimeException(
                    "Error de configuracion, se encontró mas de una infraccion con los siguientes parametros [ codigoAlfanumerico: {0}, fechaVigencia: {1} ]",
                    codigoAlfanumerico, fechaVigencia);
        } else {
            return ConfiguracionInfraccionHelperExtend.toLevel1DTOExtend(infrList.get(0));
        }
    }

    /**
     * Consulta la infraccion por codigo alfa numerico
     * 
     * @param codigosAlfanumerico
     * @param fechaVigencia
     * @return
     */
    private List<ConfiguracionInfraccion> consultarInfraccionPorCodigoAlfaNum(List<String> codigosAlfanumerico,
            Date fechaVigencia) {
        logger.debug("InfraccionEJB::consultarInfraccionPorCodigoAlfaNum(List<String>)");
        final GenericDao<ConfiguracionInfraccion> confInfrDao = new GenericDao<ConfiguracionInfraccion>(
                ConfiguracionInfraccion.class, em);
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT ci FROM ConfiguracionInfraccion AS ci ");
        jpql.append(" LEFT JOIN FETCH ci.infraccion AS inf");
        jpql.append(" LEFT JOIN FETCH inf.tipoInfraccion AS ti");
        jpql.append(" LEFT JOIN FETCH inf.tipoComparendo AS tc");
        jpql.append(" LEFT JOIN FETCH ci.normatividad AS nor");
        jpql.append(" LEFT JOIN FETCH ci.entidadAgenteTransito AS teat");
        jpql.append(" LEFT JOIN FETCH ci.causalInfraccion AS cai");
        jpql.append(" LEFT JOIN FETCH ci.ordenamientoPais AS op");
        jpql.append(" LEFT JOIN FETCH ci.tipoSancionList AS ts");
        jpql.append(" WHERE inf.codigo IN :listCodAlfaNum");
        jpql.append(" AND ci.fechaInicio <= :fechaVigente AND (ci.fechaFin IS NULL OR ci.fechaFin >= :fechaVigente) ");

        final Map<String, Object> paramVal = MapUtils.asMap("listCodAlfaNum", codigosAlfanumerico).entry(
                "fechaVigente", fechaVigencia);
        return confInfrDao.buildAndExecuteQuery(jpql.toString(), paramVal);
    }

    @Override
    public InfraccionDTO consultarInfraccion(String codigoAlfanumerico) {
        InfraccionDTO infDTO = null;

        TypedQuery<Infraccion> query = em.createNamedQuery(Infraccion.SQ_FIND_BY_CODIGO_INFRACCION, Infraccion.class);
        query.setParameter("pCodInf", codigoAlfanumerico);

        List<Infraccion> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            infDTO = InfraccionHelperExtend.toLevel1DTOExtend(resultList.get(0));
        }

        return infDTO;
    }

    @Override
    public List<ItemCatalogoDTO> consultarCodigosInfraccion() {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT i");
        jpql.append(" FROM Infraccion i");
        jpql.append(" LEFT JOIN FETCH i.tipoInfraccion ti");

        final TypedQuery<Infraccion> query = em.createQuery(jpql.toString(), Infraccion.class);
        final List<Infraccion> infracciones = safeList(query.getResultList());

        final List<ItemCatalogoDTO> catalogo = new ArrayList<>(infracciones.size());
        ItemCatalogoDTO item = null;
        for (Infraccion infraccion : infracciones) {
            item = new ItemCatalogoDTO();
            String tipoInfraccion = "";
            if (infraccion.getTipoInfraccion() != null) {
                tipoInfraccion = StringUtils.defaultIfBlank(infraccion.getTipoInfraccion().getNombre(), "");
            }
            item.setId(infraccion.getId());
            item.setNombre(tipoInfraccion + StringUtils.defaultIfBlank(infraccion.getNumeral(), ""));
            catalogo.add(item);
        }
        return catalogo;
    }
}
