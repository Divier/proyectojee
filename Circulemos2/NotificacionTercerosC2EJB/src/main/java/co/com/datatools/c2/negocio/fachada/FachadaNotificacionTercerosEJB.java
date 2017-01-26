package co.com.datatools.c2.negocio.fachada;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.EnvioNotificacionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.negocio.interfaces.ILNotificacionCorreo;
import co.com.datatools.c2.negocio.interfaces.IRFachadaNotificacionTerceros;

@Stateless(name = "FachadaNotificacionTercerosEJB")
@LocalBean
public class FachadaNotificacionTercerosEJB implements IRFachadaNotificacionTerceros {

    private static final Logger logger = Logger.getLogger(FachadaNotificacionTercerosEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private ILNotificacionCorreo iLNotificacion;

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public Integer[] enviaNotificaciones(EnvioNotificacionDTO notificaciones) {
        logger.debug(FachadaNotificacionTercerosEJB.class.getName()
                .concat("::enviaNotificaciones(List<ConsultarNotificacionesDTO>)"));
        return iLNotificacion.enviarNotificaciones(notificaciones);
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public Integer[] consultarNotificaciones() {
        logger.debug(FachadaNotificacionTercerosEJB.class.getName().concat("::consultarNotificaciones()"));
        return iLNotificacion.consultarNotificaciones();
    }

    @Override
    public ValorParametroDTO consultarParametroEnvioNotificaciones(int codigoOrganismo) {
        logger.debug(
                FachadaNotificacionTercerosEJB.class.getName().concat("::consultarParametroEnvioNotificaciones(int)"));
        return iLNotificacion.consultarParametroEnvioNotificaciones(codigoOrganismo);
    }
}