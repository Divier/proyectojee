package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.log.ILogueable;

public class NotificacionCompaCorreoLogDTO implements ILogueable {

    Date fechaHoraGeneracionArchivo;
    Integer organismoTransito;
    Integer actividadComparendo;
    String resultadoProceso;

    public Date getFechaHoraGeneracionArchivo() {
        return fechaHoraGeneracionArchivo;
    }

    public void setFechaHoraGeneracionArchivo(Date fechaHoraGeneracionArchivo) {
        this.fechaHoraGeneracionArchivo = fechaHoraGeneracionArchivo;
    }

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Integer getActividadComparendo() {
        return actividadComparendo;
    }

    public void setActividadComparendo(Integer actividadComparendo) {
        this.actividadComparendo = actividadComparendo;
    }

    public String getResultadoProceso() {
        return resultadoProceso;
    }

    public void setResultadoProceso(String resultadoProceso) {
        this.resultadoProceso = resultadoProceso;
    }

}
