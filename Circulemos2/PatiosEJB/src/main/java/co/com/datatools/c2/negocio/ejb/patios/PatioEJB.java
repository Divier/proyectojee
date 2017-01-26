package co.com.datatools.c2.negocio.ejb.patios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.patios.PatioDTO;
import co.com.datatools.c2.entidades.Patio;
import co.com.datatools.c2.negocio.helpers.patios.PatioHelper;
import co.com.datatools.c2.negocio.interfaces.patios.ILPatio;
import co.com.datatools.c2.negocio.interfaces.patios.IRPatio;
import co.com.datatools.util.GenericDao;

/**
 * @author luis.forero(2015-10-26)
 */
@Stateless(name = "PatioEJB")
@LocalBean
public class PatioEJB implements IRPatio, ILPatio {

    private final static Logger logger = Logger.getLogger(PatioEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<PatioDTO> consultarPatios(PatioDTO patioDTO) {
        logger.debug("PatioEJB::consultarPatios(PatioDTO)");
        List<PatioDTO> resultado = new ArrayList<>(0);

        List<Patio> resultList = null;
        if (patioDTO == null) {
            TypedQuery<Patio> query = em.createNamedQuery(Patio.SQ_FIND_ALL, Patio.class);
            resultList = query.getResultList();
        } else {

            GenericDao<Patio> dao = new GenericDao<>(Patio.class, em);
            Map<String, Object> filtros = new HashMap<>(0);
            if (patioDTO.getId() != null) {
                filtros.put("id", patioDTO.getId());
            } else {
                if (StringUtils.isNotBlank(patioDTO.getCodigo())) {
                    filtros.put("codigo", patioDTO.getCodigo());
                }
                if (patioDTO.getOrganismoTransito() != null
                        && patioDTO.getOrganismoTransito().getCodigoOrganismo() != null) {
                    filtros.put("organismoTransito.codigoOrganismo", patioDTO.getOrganismoTransito()
                            .getCodigoOrganismo());
                }
            }

            resultList = dao.findByAttributes(filtros);
        }
        if (!resultList.isEmpty()) {
            resultado = PatioHelper.toListLevel0DTO(resultList);
        }

        return resultado;
    }
}
