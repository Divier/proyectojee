package co.com.datatools.datatimer.c2.utilidades;

import co.com.datatools.c2.log.ILogueable;

public class LogReplicarUbicabilidadSac implements ILogueable {

    private static final long serialVersionUID = 1L;

    private Integer organismoTransito;
    private Integer totalConsultas;
    private Integer numeroExitosos;
    private Integer numeroFallidos;

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Integer getTotalConsultas() {
        return totalConsultas;
    }

    public void setTotalConsultas(Integer totalConsultas) {
        this.totalConsultas = totalConsultas;
    }

    public Integer getNumeroExitosos() {
        return numeroExitosos;
    }

    public void setNumeroExitosos(Integer numeroExitosos) {
        this.numeroExitosos = numeroExitosos;
    }

    public Integer getNumeroFallidos() {
        return numeroFallidos;
    }

    public void setNumeroFallidos(Integer numeroFallidos) {
        this.numeroFallidos = numeroFallidos;
    }

}