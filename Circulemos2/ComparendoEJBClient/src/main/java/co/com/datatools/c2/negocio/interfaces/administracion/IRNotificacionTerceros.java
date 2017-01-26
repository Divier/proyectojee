package co.com.datatools.c2.negocio.interfaces.administracion;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.EnvioNotificacionDTO;

/**
 * Ofrece el servicio de notificacion a terceros de las acciones realizadas a un comparendo. Almacena la notificacion a realizar y en caso de tener
 * fallo almacena el fallo para realizarlo nuevamente en caso de ser requerido.
 * 
 * @author
 * 
 */
@Remote
public interface IRNotificacionTerceros {

    /**
     * Realiza la consulta que trae las citaciones que se pueden notificar por correo electronico.
     * 
     * @param notificacion
     */
    public EnvioNotificacionDTO consultarCitacionesNotifica(int codOrganismo);

    public Integer[] enviarNotificaciones(int codOrganismo);
}