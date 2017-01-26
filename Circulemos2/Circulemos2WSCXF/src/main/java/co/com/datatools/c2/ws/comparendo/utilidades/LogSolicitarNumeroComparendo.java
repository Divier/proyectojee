package co.com.datatools.c2.ws.comparendo.utilidades;

import co.com.datatools.c2.log.ILogueable;

public class LogSolicitarNumeroComparendo implements ILogueable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer organismoTransito;
    private String estadoTransaccion;

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public String getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEstadoTransaccion(String estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

}
