package co.com.datatools.datatimer.c2.utilidades;

import co.com.datatools.c2.log.ILogueable;

public class LogGeneracionInconsistenciasRecaudo implements ILogueable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    private Integer totalInconsistencias;

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getTotalInconsistencias() {
        return totalInconsistencias;
    }

    public void setTotalInconsistencias(Integer totalInconsistencias) {
        this.totalInconsistencias = totalInconsistencias;
    }

}
