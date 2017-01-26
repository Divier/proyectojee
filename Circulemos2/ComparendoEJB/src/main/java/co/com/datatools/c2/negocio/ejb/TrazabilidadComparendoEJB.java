package co.com.datatools.c2.negocio.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import co.com.datatools.c2.entidades.ProcesoNegocio;
import co.com.datatools.c2.entidades.TrazabilidadComparendo;
import co.com.datatools.c2.negocio.interfaces.ILTrazabilidadComparendo;
import co.com.datatools.c2.negocio.interfaces.IRTrazabilidadComparendo;

@Stateless(name = "TrazabilidadComparendoEJB")
@LocalBean
public class TrazabilidadComparendoEJB implements ILTrazabilidadComparendo, IRTrazabilidadComparendo {

    private final static Logger logger = Logger.getLogger(TrazabilidadComparendoEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public boolean existeProceso(long cicomparendo, Integer codigoProceso) {
        logger.debug("TrazabilidadComparendoEJB::existeProceso(long,Integer)");
        boolean existeProceso = false;
        StringBuilder jpql = new StringBuilder();

        jpql.append("SELECT tc FROM TrazabilidadComparendo tc");
        jpql.append(" WHERE tc.comparendo.cicomparendo = :cicomparendo");

        Query query = em.createQuery(jpql.toString());

        /*
         * Parametros
         */
        // Cicomparendo
        query.setParameter("cicomparendo", cicomparendo);

        @SuppressWarnings("unchecked")
        List<TrazabilidadComparendo> trazabilidadComparendos = query.getResultList();
        for (TrazabilidadComparendo trazabilidadComparendo : trazabilidadComparendos) {
            if (consultarProcesoTrazabilidad(trazabilidadComparendo.getActividad().getProceso(), codigoProceso)) {
                existeProceso = true;
                break;
            }
        }

        return existeProceso;
    }

    /**
     * Verifica el proceso en cada trazabilidad
     * 
     * @param procesoTrazabilidad
     * @param codigoProceso
     * @return
     * @author giovanni.velandia
     */
    private boolean consultarProcesoTrazabilidad(ProcesoNegocio procesoTrazabilidad, int codigoProceso) {
        if (procesoTrazabilidad.getId().equals(codigoProceso)) {
            return true;
        } else if (procesoTrazabilidad.getProcesoPadre() == null) {
            return false;
        } else {
            return consultarProcesoTrazabilidad(procesoTrazabilidad.getProcesoPadre(), codigoProceso);
        }
    }
}
