package co.com.datatools.c2.negocio.timer;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;

import org.jboss.logging.Logger;

import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.timer.AbstractOrganismoTimer;
import co.com.datatools.c2.timer.OrganismoTimerInfo;
import co.com.datatools.c2.util.BeanLocatorC2;

/**
 * Clase encargarda de ejecutar la tarea de generación de resolución de sanción.
 * 
 * @author julio.pinzon
 */
@Singleton
@Startup
public class GenerarResolucionSancionTimer extends AbstractOrganismoTimer {

    private final static Logger logger = Logger.getLogger(GenerarResolucionSancionTimer.class.getName());

    private IRComparendo comparendoEjb = BeanLocatorC2.locate(IRComparendo.class,
            BeanLocatorC2.Beans.IRComparendo.toString());

    public GenerarResolucionSancionTimer() {
        super(EnumParametro.CRON_GENERAR_RESOL_SANC);
    }

    @PostConstruct
    public void init() {
        logger.info(comparendoEjb.toString());
    }

    @Override
    @Timeout
    public void ejecutar(Timer timer) {
        OrganismoTimerInfo info = (OrganismoTimerInfo) timer.getInfo();
        logger.info("GenerarResolucionSancionTimer.ejecutar() - Ejecutando Timer Info: " + info);
        try {
            comparendoEjb.generarResolucionSancionAutomatico(info.getCodigoOrganismo(), new Date());
        } catch (CirculemosNegocioException e) {
            logger.error("Error al generar las resoluciones de sancion automaticas", e);
        }
    }
}