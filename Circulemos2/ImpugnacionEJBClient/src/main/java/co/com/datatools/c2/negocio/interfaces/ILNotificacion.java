package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comun.NotificacionProcesoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILNotificacion {

    /**
     * @see IRNotificacion#consultaNotificacionProcesos(NotificacionProcesoDTO)
     */
    public List<NotificacionProcesoDTO> consultaNotificacionProcesos(
            NotificacionProcesoDTO filtrosNotificacionProcesoDTO);

    /**
     * @see IRNotificacion#prepararNotificacion(List<NotificacionProcesoDTO>)
     */
    public void prepararNotificacion(List<NotificacionProcesoDTO> lsNotificaciones);

    /**
     * @see IRNotificacion#notificar(NotificacionProcesoDTO)
     */
    public void notificar(NotificacionProcesoDTO notificacion) throws CirculemosNegocioException;
}