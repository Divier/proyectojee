package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class LogValidarConciliacionRecaudo implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Date fechaHoraEjecucion;
    private Integer codigoOrganismo;
    private Integer totalRecaudosValidados;
    private Integer totalRecaudosNoConciliados;
    private Integer totalRecaudosRegistrados;

    public Date getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(Date fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getTotalRecaudosValidados() {
        return totalRecaudosValidados;
    }

    public void setTotalRecaudosValidados(Integer totalRecaudosValidados) {
        this.totalRecaudosValidados = totalRecaudosValidados;
    }

    public Integer getTotalRecaudosNoConciliados() {
        return totalRecaudosNoConciliados;
    }

    public void setTotalRecaudosNoConciliados(Integer totalRecaudosNoConciliados) {
        this.totalRecaudosNoConciliados = totalRecaudosNoConciliados;
    }

    public Integer getTotalRecaudosRegistrados() {
        return totalRecaudosRegistrados;
    }

    public void setTotalRecaudosRegistrados(Integer totalRecaudosRegistrados) {
        this.totalRecaudosRegistrados = totalRecaudosRegistrados;
    }

}