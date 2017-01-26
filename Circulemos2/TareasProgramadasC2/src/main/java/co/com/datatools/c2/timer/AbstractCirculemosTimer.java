package co.com.datatools.c2.timer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.resource.NotSupportedException;

import org.jboss.logging.Logger;

/**
 * Clase basica para extender una tarea programada del Circulemos, define los metodos basicos para las implementaciones concretas e implementa los
 * comportamientos genericos para crear Timers
 * 
 * @author felipe.martinez
 * 
 * @param <T>
 *            Tipo de objeto q se usara como llave-informacion adicional de los timers, este objeto se enviara al metodo anotado con {@link Timeout}
 *            para q tenga informacion contextualizada de la tarea a ejecutar
 */
public abstract class AbstractCirculemosTimer<T extends CirculemosTimerInfo> {
    private final static Logger logger = Logger.getLogger(AbstractCirculemosTimer.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @Resource
    private TimerService timerService;

    final private boolean multiplesTimers;

    /**
     * Construye el objeto para procesar una tarea programada
     * 
     * @param multiplesTimers
     *            Indica al constructor de tareas programadas sin la tarea programada tiene multiples Timers ó uno solo
     */
    public AbstractCirculemosTimer(boolean multiplesTimers) {
        super();
        this.multiplesTimers = multiplesTimers;
    }

    /**
     * Metodo a definir por la clase concreta q permite obtener un unico timer para el bean, llamado en la creacion del timer cuando multiplesTimers =
     * false
     * 
     * @return Pareja de valores:
     *         <ul>
     *         <li>K: objeto serializable q sirve como llave de este horario</li>
     *         <li>V: configuracion del horario de ejecucion</li>
     *         </ul>
     */
    abstract public T confTimer() throws NotSupportedException;

    /**
     * Metodo a definir por la clase concreta q permite obtener multiples timers para el bean, llamado en la creacion del timer cuando multiplesTimers
     * = true
     * 
     * @return Mapa con parejas de valores:
     *         <ul>
     *         <li>K: objeto serializable q sirve como llave de este horario</li>
     *         <li>V: configuracion del horario de ejecucion</li>
     *         </ul>
     */
    abstract public List<T> confMultiplesTimers() throws NotSupportedException;

    /**
     * Metodo q debe llevar la anotacion {@link Timeout} en la clase concreta, es el metodo q se dispara en la ejecucion del timer
     * 
     * @param timer
     *            informacion del timer configurado en la programacion
     */
    abstract public void ejecutar(final Timer timer);

    /**
     * Metodo con la anotacion {@link PostConstruct} q llama los metodos {@link #confTimer()} si <b>multiplesTimers = false</b> ó
     * {@link #confMultiplesTimers()} si <b>multiplesTimers = true</b> para obtener la configuracion de los timer a crear
     */
    @SuppressWarnings("unchecked")
    @PostConstruct
    final public void programar() {
        List<T> nuevasConf;
        try {
            if (multiplesTimers) {
                nuevasConf = confMultiplesTimers();
            } else {
                nuevasConf = Arrays.asList(confTimer());
            }
        } catch (NotSupportedException e) {
            logger.error("Error configuracion Timers", e);
            return;
        }

        Collection<Timer> timersActuales = timerService.getTimers();
        for (Timer timerProgramado : timersActuales) {

            final T nuevaConf = cancelarTimer(nuevasConf, timerProgramado);
            if (nuevaConf != null) {
                // Si la nueva programacion es diferente a la actual, re programa el timer
                if (!nuevaConf.equalsContent((T) timerProgramado.getInfo())) {
                    timerProgramado.cancel();
                    programarConfiguracion(nuevaConf.asScheduleExpression(), nuevaConf);
                }
                nuevasConf.remove(nuevaConf);
            }
        }
        // Crea los nuevos Timer para las configuraciones restantes (nuevas)
        for (T nuevaConf : nuevasConf) {
            programarConfiguracion(nuevaConf.asScheduleExpression(), nuevaConf);
        }
    }

    /**
     * Si no encuentra la configuracion para timerProgramado dentro de las nuevasConf, lo cancela.<br>
     * Si lo encuentra, retorna la nueva configuracion q debe usar
     * 
     * @param nuevasConf
     *            informacion de configuracion de los nuevos timer a programar
     * @param timerProgramado
     *            timer programado actualmente
     * @return si encuentra una nueva configuracion para timerProgramado la retorna, si no, retorna null
     */
    @SuppressWarnings("unchecked")
    final private T cancelarTimer(List<T> nuevasConf, Timer timerProgramado) {
        for (T nuevoTimer : nuevasConf)
            if (nuevoTimer.equalsKey((T) timerProgramado.getInfo()))
                return nuevoTimer;
        timerProgramado.cancel();
        return null;
    }

    final private void programarConfiguracion(final ScheduleExpression expression, final T info) {
        if (!info.isValid()) {
            logger.infov("Se encontro configuracion invalida para tarea programada, no se crea programacion para: {0}",
                    info);
            return;
        }
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(info);
        timerConfig.setPersistent(false);
        final Timer timer = timerService.createCalendarTimer(expression, timerConfig);
        logger.info("Creado " + timer + " para procesamiento; siguiente ejecucion es: " + timer.getNextTimeout()
                + " llave del timer: " + info);
    }

    final public boolean isMultiplesTimers() {
        return multiplesTimers;
    }

    final protected TimerService getTimerService() {
        return timerService;
    }

}
