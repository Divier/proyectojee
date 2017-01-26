package co.com.datatools.datatimer.c2.utilidades;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class LogValidarFinanciacionCuotaPendiente implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Date fechaHoraEjecucion;
    private Integer organismoTransito;
    private Integer totalFinanValidadas;
    private Integer totalFinanAnuladas;

    public Date getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(Date fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
    }

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Integer getTotalFinanValidadas() {
        return totalFinanValidadas;
    }

    public void setTotalFinanValidadas(Integer totalFinanValidadas) {
        this.totalFinanValidadas = totalFinanValidadas;
    }

    public Integer getTotalFinanAnuladas() {
        return totalFinanAnuladas;
    }

    public void setTotalFinanAnuladas(Integer totalFinanAnuladas) {
        this.totalFinanAnuladas = totalFinanAnuladas;
    }
}