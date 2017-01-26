package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class LogRecibirGestionCobroSac implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Integer organismoTransito;
    private Date fechaHoraEjecucion;
    private Integer totalLeidos;
    private Integer totalRecibidos;
    private Integer totalNoRecibidos;

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

    public Integer getTotalLeidos() {
        return totalLeidos;
    }

    public void setTotalLeidos(Integer totalLeidos) {
        this.totalLeidos = totalLeidos;
    }

    public Integer getTotalRecibidos() {
        return totalRecibidos;
    }

    public void setTotalRecibidos(Integer totalRecibidos) {
        this.totalRecibidos = totalRecibidos;
    }

    public Integer getTotalNoRecibidos() {
        return totalNoRecibidos;
    }

    public void setTotalNoRecibidos(Integer totalNoRecibidos) {
        this.totalNoRecibidos = totalNoRecibidos;
    }

}