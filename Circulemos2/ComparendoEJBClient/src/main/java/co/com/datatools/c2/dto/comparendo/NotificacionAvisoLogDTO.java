package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class NotificacionAvisoLogDTO implements ILogueable {
    private Date fechaActualDelSistema;
    private Integer organismoTransito;
    private Integer cantidadDeComparendosNotificados;

    public Date getFechaActualDelSistema() {
        return fechaActualDelSistema;
    }

    public void setFechaActualDelSistema(Date fechaActualDelSistema) {
        this.fechaActualDelSistema = fechaActualDelSistema;
    }

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Integer getCantidadDeComparendosNotificados() {
        return cantidadDeComparendosNotificados;
    }

    public void setCantidadDeComparendosNotificados(Integer cantidadDeComparendosNotificados) {
        this.cantidadDeComparendosNotificados = cantidadDeComparendosNotificados;
    }

}
