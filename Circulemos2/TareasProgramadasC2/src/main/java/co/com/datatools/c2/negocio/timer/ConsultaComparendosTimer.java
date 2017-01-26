package co.com.datatools.c2.negocio.timer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumTipoComparendo;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.timer.AbstractOrganismoTimer;
import co.com.datatools.c2.timer.OrganismoTimerInfo;
import co.com.datatools.c2.util.BeanLocatorC2;

/**
 * Tarea programada de ejemplo de consulta de comparendos
 * 
 * @author julio.pinzon
 */
@Singleton
@Startup
public class ConsultaComparendosTimer extends AbstractOrganismoTimer {

    private final static Logger logger = Logger.getLogger(ConsultaComparendosTimer.class.getName());

    private IRComparendo comparendoEjb = BeanLocatorC2.locate(IRComparendo.class,
            BeanLocatorC2.Beans.IRComparendo.toString());

    public ConsultaComparendosTimer() {
        super(EnumParametro.CRON_GENERAR_CARTE_COMPA);
    }

    @PostConstruct
    public void init() {
        logger.info(comparendoEjb.toString());
    }

    @Override
    @Timeout
    public void ejecutar(Timer timer) {
        OrganismoTimerInfo info = (OrganismoTimerInfo) timer.getInfo();
        logger.info("GenerarCarteraComparendoTimer.ejecutar() - Ejecutando Timer Info: " + info);
        try {
            ConsultaComparendoDTO consultaComparendoDTO = new ConsultaComparendoDTO();
            consultaComparendoDTO.setCodigoOrganismo(info.getCodigoOrganismo());
            consultaComparendoDTO.setIdTipoComparendo(EnumTipoComparendo.COMPARENDO_NACIONAL.getCodigo());
            consultaComparendoDTO.setEsPolca(false);
            consultaComparendoDTO.setPlacaVehiculo("ven285");
            List<ComparendoDTO> comparendos = comparendoEjb.consultarComparendos(consultaComparendoDTO);
            logger.info("Organismo: " + info.getCodigoOrganismo() + " Comparendos " + comparendos.size());
        } catch (Exception e) {
            logger.error(e);
        }
    }
}