package co.com.datatools.c2.negocio.timer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;

import org.jboss.logging.Logger;

import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.negocio.interfaces.IRNotificacionComparendoSimit;
import co.com.datatools.c2.timer.AbstractOrganismoTimer;
import co.com.datatools.c2.timer.OrganismoTimerInfo;
import co.com.datatools.c2.util.BeanLocatorC2;

/**
 * CU_CIR20_DAT_SIM_007 Tarea programada para generacion de archivos para envio a SIMIT
 * 
 * @author Rodrigo Cruz
 */
@Singleton
@Startup
public class GeneraArchivoPlanoSimitTimer extends AbstractOrganismoTimer {

    private final static Logger logger = Logger.getLogger(GeneraArchivoPlanoSimitTimer.class.getName());

    private IRNotificacionComparendoSimit simitEJB = BeanLocatorC2.locate(IRNotificacionComparendoSimit.class,
            BeanLocatorC2.Beans.IRNotificacionComparendoSimit.toString());

    public GeneraArchivoPlanoSimitTimer() {
        super(EnumParametro.CRON_GENERAR_ARCHIVO_COMPA_SIMIT);
    }

    @PostConstruct
    public void init() {
        logger.info(simitEJB.toString());
    }

    @Override
    @Timeout
    public void ejecutar(Timer timer) {
        OrganismoTimerInfo info = (OrganismoTimerInfo) timer.getInfo();
        logger.info("GenerarArchivoPlanoSimitTimer.ejecutar() - Ejecutando Timer Info: " + info);
        try {
            simitEJB.registrarArchivoNotificacionComparendoAutomatico(info.getCodigoOrganismo());
            logger.info("GenerarArchivoPlanoSimitTimer.ejecutar() - Archivo(s) registrado(s)");
        } catch (Exception e) {
            logger.error(e);
        }
    }

}