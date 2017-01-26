package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.NotificacionComparendoTerceroDTO;

/**
 * Ofrece el servicio de notificacion a terceros de las acciones realizadas a un comparendo. Almacena la notificacion a realizar y en caso de tener
 * fallo almacena el fallo para realizarlo nuevamente en caso de ser requerido.
 * 
 * @author rodrigo.cruz
 * 
 */
@Remote
public interface IRNotificacionComparendoTercero {

    /**
     * Realiza la notificacion del comparendo recibido a los terceros requeridos.
     * 
     * @param notificacion
     */
    void notificarComparendo(NotificacionComparendoTerceroDTO notificacion);

}
