/**
 * 
 */
package co.com.datatools.datatimer.c2.utilidades;

import co.com.datatools.c2.log.ILogueable;

/**
 * @author javier.fajardo
 *
 */
public class LogNotificacionENotifica implements ILogueable {

    private static final long serialVersionUID = 1L;
    private Integer solicitudesNotificacion;
    private Integer cantNotificados;
    private Integer cantNoNotificados;

    public Integer getSolicitudesNotificacion() {
        return solicitudesNotificacion;
    }

    public void setSolicitudesNotificacion(Integer solicitudesNotificacion) {
        this.solicitudesNotificacion = solicitudesNotificacion;
    }

    public Integer getCantNotificados() {
        return cantNotificados;
    }

    public void setCantNotificados(Integer cantNotificados) {
        this.cantNotificados = cantNotificados;
    }

    public Integer getCantNoNotificados() {
        return cantNoNotificados;
    }

    public void setCantNoNotificados(Integer cantNoNotificados) {
        this.cantNoNotificados = cantNoNotificados;
    }

}
