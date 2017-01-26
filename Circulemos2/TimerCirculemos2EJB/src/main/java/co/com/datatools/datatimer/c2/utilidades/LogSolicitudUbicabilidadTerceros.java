package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class LogSolicitudUbicabilidadTerceros implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Date fechaHoraEjecucion;
    private Integer totalRegUbicLeidos;
    private Integer totalRegUbicRecibidos;
    private Integer totalRegUbicNoRecibidos;

    public Date getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(Date fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
    }

    public Integer getTotalRegUbicLeidos() {
        return totalRegUbicLeidos;
    }

    public void setTotalRegUbicLeidos(Integer totalRegUbicLeidos) {
        this.totalRegUbicLeidos = totalRegUbicLeidos;
    }

    public Integer getTotalRegUbicRecibidos() {
        return totalRegUbicRecibidos;
    }

    public void setTotalRegUbicRecibidos(Integer totalRegUbicRecibidos) {
        this.totalRegUbicRecibidos = totalRegUbicRecibidos;
    }

    public Integer getTotalRegUbicNoRecibidos() {
        return totalRegUbicNoRecibidos;
    }

    public void setTotalRegUbicNoRecibidos(Integer totalRegUbicNoRecibidos) {
        this.totalRegUbicNoRecibidos = totalRegUbicNoRecibidos;
    }
}