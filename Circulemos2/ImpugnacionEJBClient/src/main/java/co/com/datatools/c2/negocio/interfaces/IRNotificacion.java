package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comun.NotificacionProcesoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRNotificacion {

    /**
     * Metodo que se encarga de consultar la trazablidad de procesos para notificar
     * 
     * @author giovanni.velandia
     * @param filtrosNotificacionProcesoDTO
     * @return
     */
    public List<NotificacionProcesoDTO> consultaNotificacionProcesos(
            NotificacionProcesoDTO filtrosNotificacionProcesoDTO);

    /**
     * Permite preparar el envio de las notificaciones
     * 
     * @param lsNotificaciones
     */
    public void prepararNotificacion(List<NotificacionProcesoDTO> lsNotificaciones);

    /**
     * Permite enviar notificaciones pendientes de notificar
     * 
     * @param notificacion
     */
    public void notificar(NotificacionProcesoDTO notificacion) throws CirculemosNegocioException;
}