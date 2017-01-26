package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.NotificacionComparendoTerceroDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILNotificacionComparendoTercero {

    /**
     * @see IRNotificacionComparendoTercero#notificarComparendo(NotificacionComparendoTerceroDTO)
     */
    void notificarComparendo(NotificacionComparendoTerceroDTO notificacion);

    /**
     * Transaccion para notificar a tercero SIMIT
     * 
     * @param cicomparendo
     * @param codigoOrganismo
     * @param idOrigenNotificacionTercero
     * @return {@code true} si fue notificado
     * @throws CirculemosNegocioException
     */
    boolean notificarTerceroSimit(Long cicomparendo, Integer codigoOrganismo, Integer idOrigenNotificacionTercero)
            throws CirculemosNegocioException;

    /**
     * Transaccion para notificar a tercero CIRCULEMOS 1
     * 
     * @return {@code true} si fue notificado
     */
    boolean notificarTerceroCirculemos1();

}
