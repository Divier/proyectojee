package co.com.datatools.datatimer.c2.utilidades;

import co.com.datatools.c2.log.ILogueable;

public class LogGeneracionInconsistenciasConciliacionRecaudo implements ILogueable {

    private Integer organismoTransito;
    private Integer totalInconsistencias;

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Integer getTotalInconsistencias() {
        return totalInconsistencias;
    }

    public void setTotalInconsistencias(Integer totalInconsistencias) {
        this.totalInconsistencias = totalInconsistencias;
    }

}
