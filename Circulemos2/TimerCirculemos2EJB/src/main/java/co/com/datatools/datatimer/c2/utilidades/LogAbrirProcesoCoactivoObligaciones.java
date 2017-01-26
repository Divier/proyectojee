package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class LogAbrirProcesoCoactivoObligaciones implements ILogueable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer organismoTransito;
    private Date fechaHoraEjecucion;
    private Integer totalProcesosAbiertos;

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

    public Integer getTotalProcesosAbiertos() {
        return totalProcesosAbiertos;
    }

    public void setTotalProcesosAbiertos(Integer totalProcesosAbiertos) {
        this.totalProcesosAbiertos = totalProcesosAbiertos;
    }

}