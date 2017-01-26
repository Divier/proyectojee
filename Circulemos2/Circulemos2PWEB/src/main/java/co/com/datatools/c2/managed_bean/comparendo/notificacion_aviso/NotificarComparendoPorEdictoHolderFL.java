package co.com.datatools.c2.managed_bean.comparendo.notificacion_aviso;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comparendo.NotificacionAvisoDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * 
 * @author diego.fonseca
 * 
 */
public class NotificarComparendoPorEdictoHolderFL extends AbstractSwfManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = -8353602600426471999L;

    private int codigoOrganismo;

    private boolean requiereFecha;

    private Date fechaInicialGeneracion;

    private Date fechaFinalGeneracion;

    private Date fechaActual = new Date();

    private List<NotificacionAvisoDTO> notificacionAvisoList;

    NotificacionAvisoDTO notificacionAvisoSel;

    boolean visible;

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public boolean isRequiereFecha() {
        return requiereFecha;
    }

    public void setRequiereFecha(boolean requiereFecha) {
        this.requiereFecha = requiereFecha;
    }

    public Date getFechaInicialGeneracion() {
        return fechaInicialGeneracion;
    }

    public void setFechaInicialGeneracion(Date fechaInicialGeneracion) {
        this.fechaInicialGeneracion = fechaInicialGeneracion;
    }

    public Date getFechaFinalGeneracion() {
        return fechaFinalGeneracion;
    }

    public void setFechaFinalGeneracion(Date fechaFinalGeneracion) {
        this.fechaFinalGeneracion = fechaFinalGeneracion;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public List<NotificacionAvisoDTO> getNotificacionAvisoList() {
        return notificacionAvisoList;
    }

    public void setNotificacionAvisoList(List<NotificacionAvisoDTO> notificacionAvisoList) {
        this.notificacionAvisoList = notificacionAvisoList;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public NotificacionAvisoDTO getNotificacionAvisoSel() {
        return notificacionAvisoSel;
    }

    public void setNotificacionAvisoSel(NotificacionAvisoDTO notificacionAvisoSel) {
        this.notificacionAvisoSel = notificacionAvisoSel;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
