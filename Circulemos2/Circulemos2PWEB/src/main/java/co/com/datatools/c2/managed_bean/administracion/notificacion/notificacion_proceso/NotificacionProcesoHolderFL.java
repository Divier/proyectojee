package co.com.datatools.c2.managed_bean.administracion.notificacion.notificacion_proceso;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.comun.NotificacionProcesoDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioFiltrosDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioRespuestaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class NotificacionProcesoHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "notificacionProcesoHolderFL";

    NotificacionProcesoDTO notificacionProcesoDTO;

    List<NotificacionProcesoDTO> notificaciones;

    NotificacionProcesoDTO notificacionProcesoDTOSel;

    public NotificacionProcesoHolderFL() {
        notificacionProcesoDTO = new NotificacionProcesoDTO();
        notificaciones = new ArrayList<>();
    }

    public NotificacionProcesoDTO getNotificacionProcesoDTO() {
        return notificacionProcesoDTO;
    }

    public void setNotificacionProcesoDTO(NotificacionProcesoDTO notificacionProcesoDTO) {
        this.notificacionProcesoDTO = notificacionProcesoDTO;
    }

    public List<NotificacionProcesoDTO> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<NotificacionProcesoDTO> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public NotificacionProcesoDTO getNotificacionProcesoDTOSel() {
        return notificacionProcesoDTOSel;
    }

    public void setNotificacionProcesoDTOSel(NotificacionProcesoDTO notificacionProcesoDTOSel) {
        this.notificacionProcesoDTOSel = notificacionProcesoDTOSel;
    }

}
