package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class LogNotificacionReplicaPago implements ILogueable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer organismoTransito;
    private Date fechaHoraEjecucion;
    private Integer totalRecaudosLeidos;
    private Integer totalRecaudosRecibidos;
    private Integer totalRecaudosNoRecibidos;

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

    public Integer getTotalRecaudosNoRecibidos() {
        return totalRecaudosNoRecibidos;
    }

    public void setTotalRecaudosNoRecibidos(Integer totalRecaudosNoRecibidos) {
        this.totalRecaudosNoRecibidos = totalRecaudosNoRecibidos;
    }

    public Integer getTotalRecaudosLeidos() {
        return totalRecaudosLeidos;
    }

    public void setTotalRecaudosLeidos(Integer totalRecaudosLeidos) {
        this.totalRecaudosLeidos = totalRecaudosLeidos;
    }

    public Integer getTotalRecaudosRecibidos() {
        return totalRecaudosRecibidos;
    }

    public void setTotalRecaudosRecibidos(Integer totalRecaudosRecibidos) {
        this.totalRecaudosRecibidos = totalRecaudosRecibidos;
    }

}