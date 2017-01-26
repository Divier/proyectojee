package co.com.datatools.c2.negocio.ejb;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.drools.time.impl.CronExpression;
import org.jboss.logging.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import co.com.datatools.c2.dto.TareaAutomaticaDTO;
import co.com.datatools.c2.entidades.Parametro;
import co.com.datatools.c2.entidades.ParametroOrganismo;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.interfaces.ILAdministracionJobEJB;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionJobEJB;
import co.com.datatools.datatimer.enumeraciones.EnumJob;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class AdministracionEJB
 */
@Stateless(name = "AdministracionJobEJB")
@LocalBean
public class AdministracionJobEJB implements IRAdministracionJobEJB, ILAdministracionJobEJB {

    private final static Logger logger = Logger.getLogger(AdministracionJobEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Override
    public List<TareaAutomaticaDTO> consultarJobs() {
        logger.debug("AdministracionJobEJB::consultarJobs()");
        List<TareaAutomaticaDTO> lsRespuesta = null;
        try {
            Collection<Scheduler> cllScheduler = new StdSchedulerFactory().getAllSchedulers();
            lsRespuesta = new ArrayList<>();
            int identificadorJob = 0;
            for (Scheduler scheduler : cllScheduler) {
                TareaAutomaticaDTO tarea = new TareaAutomaticaDTO();
                for (String groupName : scheduler.getJobGroupNames()) {
                    for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                        ++identificadorJob;
                        tarea.setIdJob(identificadorJob);
                        tarea.setNombreJob(jobKey.getName());
                        tarea.setGrupoJob(jobKey.getGroup());
                        List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                        if (!triggers.isEmpty()) {
                            tarea.setEjecucion(triggers.get(0).getNextFireTime());
                            if (triggers.get(0) instanceof CronTrigger) {
                                CronTrigger cronTrigger = (CronTrigger) triggers.get(0);
                                tarea.setExpresionTiempo(cronTrigger.getCronExpression());
                            }
                        }
                    }
                }
                for (String groupTriggers : scheduler.getTriggerGroupNames()) {
                    for (TriggerKey triggerKey : scheduler
                            .getTriggerKeys(GroupMatcher.triggerGroupEquals(groupTriggers))) {
                        ++identificadorJob;
                        tarea.setNombreTrigger(triggerKey.getName());
                        tarea.setGrupoTrigger(triggerKey.getGroup());
                        tarea.setEstadoTrigger(scheduler.getTriggerState(triggerKey).name());
                    }
                }
                lsRespuesta.add(tarea);
            }

            if (lsRespuesta != null && !lsRespuesta.isEmpty()) {
                Collections.sort(lsRespuesta, new Comparator<TareaAutomaticaDTO>() {
                    @Override
                    public int compare(TareaAutomaticaDTO obj1, TareaAutomaticaDTO obj2) {
                        return obj1.getGrupoJob().compareTo(obj2.getGrupoJob());
                    }
                });
            }
        } catch (SchedulerException e) {
            throw new CirculemosRuntimeException("");
        }
        return lsRespuesta;
    }

    @Override
    public void cambiarEjecucion(TareaAutomaticaDTO tareaAutomatica) {
        logger.debug("AdministracionJobEJB::cambiarEjecucion(TareaAutomaticaDTO)");

        try {
            Collection<Scheduler> cllScheduler = new StdSchedulerFactory().getAllSchedulers();
            Trigger oldTrigger;
            Trigger newTrigger;

            for (Scheduler scheduler : cllScheduler) {
                for (String groupName : scheduler.getJobGroupNames()) {
                    for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                        if (jobKey.getName().equals(tareaAutomatica.getNombreJob())
                                && jobKey.getGroup().equals(tareaAutomatica.getGrupoJob())) {
                            oldTrigger = scheduler.getTrigger(
                                    triggerKey(tareaAutomatica.getNombreTrigger(), tareaAutomatica.getGrupoTrigger()));
                            newTrigger = newTrigger()
                                    .withIdentity(tareaAutomatica.getNombreTrigger(), tareaAutomatica.getGrupoTrigger())
                                    .withSchedule(cronSchedule(tareaAutomatica.getExpresionTiempo())).build();
                            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
                            actualizarParametroEjecucion(tareaAutomatica);
                        }
                    }
                }
            }
        } catch (SchedulerException e) {
            throw new CirculemosRuntimeException("");
        }
    }

    private void actualizarParametroEjecucion(TareaAutomaticaDTO tareaAutomatica) {
        logger.debug("AdministracionJobEJB::actualizarParametroEjecucion(TareaAutomaticaDTO)");

        for (EnumJob eJob : EnumJob.values()) {
            if (eJob.getNombreJob().equals(tareaAutomatica.getNombreJob())) {
                Parametro parametro = em.find(Parametro.class, eJob.getParametroExpresion());
                parametro.setValorDefecto(tareaAutomatica.getExpresionTiempo());
                em.merge(parametro);

                TypedQuery<ParametroOrganismo> tq = em.createNamedQuery(ParametroOrganismo.SQ_VIGENTES_BY_CODIGO,
                        ParametroOrganismo.class);
                tq.setParameter("codigoParametro", eJob.getParametroExpresion());
                tq.setParameter("fechaActual", UtilFecha.buildCalendar().getTime());
                ParametroOrganismo parametroOrg = tq.getSingleResult();
                parametroOrg.setValor(tareaAutomatica.getExpresionTiempo());
                em.merge(parametroOrg);
                break;
            }
        }
    }

    @Override
    public boolean validarExpresion(String expresionCron) {
        return CronExpression.isValidExpression(expresionCron);
    }

    @Override
    public void detenerSiguienteEjecucion(TareaAutomaticaDTO tareaAutomatica) {
        logger.debug("AdministracionJobEJB::detenerSiguienteEjecucion(TareaAutomaticaDTO)");
        try {
            Collection<Scheduler> cllScheduler = new StdSchedulerFactory().getAllSchedulers();
            for (Scheduler scheduler : cllScheduler) {
                for (String groupName : scheduler.getJobGroupNames()) {
                    for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                        if (jobKey.getName().equals(tareaAutomatica.getNombreJob())
                                && jobKey.getGroup().equals(tareaAutomatica.getGrupoJob())) {
                            scheduler.pauseTrigger(
                                    triggerKey(tareaAutomatica.getNombreTrigger(), tareaAutomatica.getGrupoTrigger()));
                        }
                    }
                }
            }
        } catch (SchedulerException e) {
            throw new CirculemosRuntimeException("");
        }
    }

    @Override
    public void ejecutarInmediatamente(TareaAutomaticaDTO tareaAutomatica) {
        logger.debug("AdministracionJobEJB::ejecutarInmediatamente(TareaAutomaticaDTO)");
        try {
            Collection<Scheduler> cllScheduler = new StdSchedulerFactory().getAllSchedulers();
            for (Scheduler scheduler : cllScheduler) {
                for (String groupName : scheduler.getJobGroupNames()) {
                    for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                        if (jobKey.getName().equals(tareaAutomatica.getNombreJob())
                                && jobKey.getGroup().equals(tareaAutomatica.getGrupoJob())) {
                            scheduler.triggerJob(jobKey);
                        }
                    }
                }
            }
        } catch (SchedulerException e) {
            throw new CirculemosRuntimeException("");
        }
    }
}