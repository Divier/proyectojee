package co.com.datatools.c2.timer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.resource.NotSupportedException;

import org.jboss.logging.Logger;

import co.com.datatools.c2.entidades.ParametroOrganismo;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.util.date.UtilFecha;

/**
 * Implementacion generica de modelo de tareas programadas asociadas a un parametro en la base de datos para cada organismo de transito, donde se
 * define una expresion CRON de la programacion
 * 
 * @author felipe.martinez
 */
public abstract class AbstractOrganismoTimer extends AbstractCirculemosTimer<OrganismoTimerInfo> {

    private final static Logger logger = Logger.getLogger(AbstractOrganismoTimer.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    /**
     * Parameto donde se define la expresion de la tarea programada para los organismos
     */
    final protected EnumParametro scheduleExpressionParam;

    public AbstractOrganismoTimer(EnumParametro scheduleExpression) {
        super(true);
        this.scheduleExpressionParam = scheduleExpression;
    }

    @Override
    public OrganismoTimerInfo confTimer() throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public List<OrganismoTimerInfo> confMultiplesTimers() throws NotSupportedException {
        final TypedQuery<ParametroOrganismo> query = em.createNamedQuery(ParametroOrganismo.SQ_VIGENTES_BY_CODIGO,
                ParametroOrganismo.class);
        query.setParameter("codigoParametro", scheduleExpressionParam.getValue()).setParameter("fechaActual",
                UtilFecha.buildCalendar().getTime());

        final List<ParametroOrganismo> resultList = query.getResultList();
        OrganismoTimerInfo info = null;
        List<OrganismoTimerInfo> holder = new ArrayList<>(resultList.size());
        logger.infov("Se encontraron {0} expresiones para programar la tarea {1}", resultList.size(), this.getClass()
                .getName());
        for (ParametroOrganismo prmOrg : resultList) {
            info = new OrganismoTimerInfo(prmOrg.getOrganismoTransito().getCodigoOrganismo(), prmOrg.getValor());
            if (info.isValid()) {
                holder.add(info);
            } else {
                logger.infov(
                        "Se encontro configuracion invalida para tarea programada, no se crea programacion para: {0}",
                        info);
            }
        }
        return holder;
    }
}
