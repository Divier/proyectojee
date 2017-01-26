package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class LogReplicarFinanciaciones implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Integer organismoTransito;
    private Date fechaHoraEjecucion;
    private Integer totalFinanciacionesLeidos;
    private Integer totalFinanciacionesRecibidos;
    private Integer totalFinanciacionesNoRecibidos;

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(Date fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
    }

    public Integer getTotalFinanciacionesLeidos() {
        return totalFinanciacionesLeidos;
    }

    public void setTotalFinanciacionesLeidos(Integer totalFinanciacionesLeidos) {
        this.totalFinanciacionesLeidos = totalFinanciacionesLeidos;
    }

    public Integer getTotalFinanciacionesRecibidos() {
        return totalFinanciacionesRecibidos;
    }

    public void setTotalFinanciacionesRecibidos(Integer totalFinanciacionesRecibidos) {
        this.totalFinanciacionesRecibidos = totalFinanciacionesRecibidos;
    }

    public Integer getTotalFinanciacionesNoRecibidos() {
        return totalFinanciacionesNoRecibidos;
    }

    public void setTotalFinanciacionesNoRecibidos(Integer totalFinanciacionesNoRecibidos) {
        this.totalFinanciacionesNoRecibidos = totalFinanciacionesNoRecibidos;
    }

}
