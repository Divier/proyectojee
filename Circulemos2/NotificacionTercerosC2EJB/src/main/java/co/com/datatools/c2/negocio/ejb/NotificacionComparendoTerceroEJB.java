package co.com.datatools.c2.negocio.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.NotificacionComparendoSIMITDTO;
import co.com.datatools.c2.dto.NotificacionComparendoTerceroDTO;
import co.com.datatools.c2.entidades.EnvioNotificacionTercero;
import co.com.datatools.c2.entidades.NotificacionTercero;
import co.com.datatools.c2.entidades.OrigenNotificacionTercero;
import co.com.datatools.c2.entidades.Tercero;
import co.com.datatools.c2.enumeracion.EnumOrigenNotificacionTercero;
import co.com.datatools.c2.enumeracion.EnumTercero;
import co.com.datatools.c2.enumeraciones.EnumAccionComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.ILNotificacionComparendoTercero;
import co.com.datatools.c2.negocio.interfaces.IRNotificacionComparendoSimit;
import co.com.datatools.c2.negocio.interfaces.IRNotificacionComparendoTercero;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.util.date.UtilFecha;

/**
 * Session Bean implementation class NotificacionComparendoTerceroEJB
 */
@Stateless(mappedName = "NotificacionComparendoTerceroEJB")
@LocalBean
public class NotificacionComparendoTerceroEJB implements IRNotificacionComparendoTercero,
        ILNotificacionComparendoTercero {

    private final static Logger logger = Logger.getLogger(NotificacionComparendoTerceroEJB.class.getName());

    @PersistenceContext(unitName = "Circulemos2JPA")
    private EntityManager em;

    @EJB
    private IRNotificacionComparendoSimit iNotificacionComparendoSimit;
    @EJB
    private ILNotificacionComparendoTercero iNotificacionComparendoTercero;

    public NotificacionComparendoTerceroEJB() {

    }

    @Override
    public void notificarComparendo(NotificacionComparendoTerceroDTO notificacion) {
        logger.debugv("Notificar a terceros: codigo organismo {0}, cicomparendo {1}, origen notificacion {2}",
                notificacion.getCodigoOrganismo(), notificacion.getCicomparendo(), notificacion
                        .getOrigenNotificacionTercero().toString());

        NotificacionTercero notificacionTercero = null;

        try {
            notificacionTercero = crearNotificacionTercero(notificacion);
            notificacionTercero.setTercero(em.getReference(Tercero.class, EnumTercero.SIMIT.getId()));
            notificacionTercero.setEstado(iNotificacionComparendoTercero.notificarTerceroSimit(notificacion
                    .getCicomparendo(), notificacion.getCodigoOrganismo(), notificacionTercero
                    .getOrigenNotificacionTercero().getId()));
        } catch (Exception e) {
            notificacionTercero.setEstado(false);
        } finally {
            registrarNotificacion(notificacionTercero);
        }

        try {
            notificacionTercero = crearNotificacionTercero(notificacion);
            notificacionTercero.setTercero(em.getReference(Tercero.class, EnumTercero.CIRCULEMOS_1.getId()));
            notificacionTercero.setEstado(iNotificacionComparendoTercero.notificarTerceroCirculemos1()); // TODO Integracion Circulemos 1
        } catch (Exception e) {
            notificacionTercero.setEstado(false);
        } finally {
            registrarNotificacion(notificacionTercero);
        }
    }

    /**
     * Crea la entidad de {@link NotificacionTercero} a registrar con los argumentos enviados y agrega el {@link EnvioNotificacionTercero} indicando
     * el resultado de la transaccion
     * 
     * @param notificacion
     * @return
     */
    private NotificacionTercero crearNotificacionTercero(NotificacionComparendoTerceroDTO notificacion) {
        Long cicomparendo = notificacion.getCicomparendo();
        Integer codigoOrganismo = notificacion.getCodigoOrganismo();
        EnumOrigenNotificacionTercero origen = notificacion.getOrigenNotificacionTercero();

        NotificacionTercero notificacionTercero = new NotificacionTercero();
        notificacionTercero.setCicomparendo(cicomparendo);
        notificacionTercero.setCodigoOrganismo(codigoOrganismo);
        notificacionTercero.setEnvioNotificacionTerceros(new ArrayList<EnvioNotificacionTercero>());
        notificacionTercero.setFechaRecibo(UtilFecha.buildCalendar().getTime());
        notificacionTercero.setOrigenNotificacionTercero(em.getReference(OrigenNotificacionTercero.class,
                origen.getId()));

        EnvioNotificacionTercero envioNotificacionTercero = new EnvioNotificacionTercero();
        envioNotificacionTercero.setNotificacionTercero(notificacionTercero);

        notificacionTercero.getEnvioNotificacionTerceros().add(envioNotificacionTercero);

        return notificacionTercero;
    }

    /**
     * Registra la {@link NotificacionTercero} validando si debe asignar la fecha de notificacion
     * 
     * @param notificacionTercero
     */
    private void registrarNotificacion(NotificacionTercero notificacionTercero) {
        notificacionTercero.getEnvioNotificacionTerceros().get(0).setResultado(notificacionTercero.getEstado());
        notificacionTercero.getEnvioNotificacionTerceros().get(0)
                .setFechaNotificacion(notificacionTercero.getFechaRecibo());

        if (notificacionTercero.getEstado())
            notificacionTercero.setFechaNotificacion(notificacionTercero.getFechaRecibo());

        em.persist(notificacionTercero);
    }

    /**
     * Homologa los catalogos {@link EnumOrigenNotificacionTercero} y {@link EnumAccionComparendo}
     * 
     * @param origen
     * @return
     */
    private EnumAccionComparendo homologacionOrigenNotificacionTercero(Integer idOrigenNotificacionTercero) {
        EnumOrigenNotificacionTercero enumOrigen = Utilidades.buscarElemEnum(EnumOrigenNotificacionTercero.class,
                idOrigenNotificacionTercero);
        switch (enumOrigen) {
        case ACTUALIZAR:
            return EnumAccionComparendo.NOTIFICACION;
        case RECTIFICAR:
            return EnumAccionComparendo.RECTIFICACION;
        case REGISTRAR:
            return EnumAccionComparendo.REGISTRO;
        default:
            return null;
        }
    }

    // TERCEROS

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean notificarTerceroSimit(Long cicomparendo, Integer codigoOrganismo, Integer idOrigenNotificacionTercero)
            throws CirculemosNegocioException {
        return iNotificacionComparendoSimit.notificarComparendo(new NotificacionComparendoSIMITDTO(cicomparendo,
                codigoOrganismo, homologacionOrigenNotificacionTercero(idOrigenNotificacionTercero)));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean notificarTerceroCirculemos1() {
        return false;
    }

}
