package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class LogSolicitudComparendosTerceros implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Integer organismoTransito;
    private Date fechaHoraEjecucion;
    private Integer totalComparendosLeidos;
    private Integer totalComparendosRecibidos;
    private Integer totalComparendosNoRecibidos;

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

    public Integer getTotalComparendosLeidos() {
        return totalComparendosLeidos;
    }

    public void setTotalComparendosLeidos(Integer totalComparendosLeidos) {
        this.totalComparendosLeidos = totalComparendosLeidos;
    }

    public Integer getTotalComparendosRecibidos() {
        return totalComparendosRecibidos;
    }

    public void setTotalComparendosRecibidos(Integer totalComparendosRecibidos) {
        this.totalComparendosRecibidos = totalComparendosRecibidos;
    }

    public Integer getTotalComparendosNoRecibidos() {
        return totalComparendosNoRecibidos;
    }

    public void setTotalComparendosNoRecibidos(Integer totalComparendosNoRecibidos) {
        this.totalComparendosNoRecibidos = totalComparendosNoRecibidos;
    }

}
