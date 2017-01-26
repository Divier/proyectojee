/**
 * 
 */
package co.com.datatools.c2.ws.sac.servicebroker;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.GestionCobroSacDTO;
import co.com.datatools.c2.dto.ws.GestionCobroWSDTO;
import co.com.datatools.c2.dto.ws.RespuestaGestionCobroWSDTO;
import co.com.datatools.c2.enumeracion.EnumLogSistema;
import co.com.datatools.c2.log.ILog;
import co.com.datatools.c2.log.LoggerC2;
import co.com.datatools.c2.negocio.interfaces.ILGestionCobro;
import co.com.datatools.c2.ws.comparendo.servicebroker.SolicitarNumeroComparendoWS;
import co.com.datatools.c2.ws.comparendo.utilidades.LogGestionCobroSAC;

/**
 * @author javier.fajardo
 *
 */
@Stateless(name = "GestionCobroWS")
@LocalBean
public class GestionCobroWS implements ILGestionCobroWS {

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILGestionCobro gestionCobroSac;

    private static final ILog logger = LoggerC2.getLogger(EnumLogSistema.GESTION_COBRO_SAC);
    private static final Logger loggerServer = Logger.getLogger(SolicitarNumeroComparendoWS.class.getName());

    @Override
    public RespuestaGestionCobroWSDTO procesarGestionCobro(GestionCobroWSDTO gestionCobro) {
        // TODO Auto-generated method stub
        loggerServer.debug("GestionCobroWS.procesarGestionCobro(GestionCobroWSDTO)");

        LogGestionCobroSAC logGestionCobroSAC = new LogGestionCobroSAC();
        RespuestaGestionCobroWSDTO respuestaGestionCobroWSDTO = new RespuestaGestionCobroWSDTO();

        logGestionCobroSAC.setNumeroNotificacionCobro("15");

        gestionCobroSac.registrarGestionCobro(new GestionCobroSacDTO());

        logger.escribir("admin", logGestionCobroSAC);
        return respuestaGestionCobroWSDTO;
    }

}
