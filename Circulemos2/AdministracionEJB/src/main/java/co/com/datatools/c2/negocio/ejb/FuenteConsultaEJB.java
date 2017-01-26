package co.com.datatools.c2.negocio.ejb;

import static co.com.datatools.c2.util.Utilidades.safeList;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.DetalleFuenteConsultaDTO;
import co.com.datatools.c2.entidades.DetalleFuenteConsulta;
import co.com.datatools.c2.negocio.helpers.v2.DetalleFuenteConsultaHelper;
import co.com.datatools.c2.negocio.interfaces.ILFuenteConsulta;
import co.com.datatools.c2.negocio.interfaces.IRFuenteConsulta;

@Stateless(mappedName = "FuenteConsultaEJB")
@LocalBean
public class FuenteConsultaEJB implements IRFuenteConsulta, ILFuenteConsulta {

    private final static Logger LOGGER = Logger.getLogger(FuenteConsultaEJB.class.getName());

    @PersistenceContext(name = "Circulemos2JPA")
    private EntityManager em;

    public FuenteConsultaEJB() {
        LOGGER.debug("FuenteConsultaEJB()");
    }

    @Override
    public List<DetalleFuenteConsultaDTO> consultarDetallesFuenteConsulta(int codigoOrganismo, int idTipoFuenteConsulta) {
        LOGGER.debug("FuenteConsultaEJB::consultarFuentesConsulta(int, int)");

        StringBuilder q = new StringBuilder();
        q.append("SELECT d FROM DetalleFuenteConsulta d")
                .append(" WHERE d.organismoTransito.codigoOrganismo = :codigoOrganismo")
                .append(" AND d.tipoFuenteConsulta.id = :idTipoFuenteConsulta ORDER BY d.prioridad");

        TypedQuery<DetalleFuenteConsulta> query = em.createQuery(q.toString(), DetalleFuenteConsulta.class);
        query.setParameter("codigoOrganismo", codigoOrganismo);
        query.setParameter("idTipoFuenteConsulta", idTipoFuenteConsulta);

        List<DetalleFuenteConsulta> detalleFuenteConsultaList = safeList(query.getResultList());

        return DetalleFuenteConsultaHelper.toListLevel1DTO(detalleFuenteConsultaList);
    }

}
