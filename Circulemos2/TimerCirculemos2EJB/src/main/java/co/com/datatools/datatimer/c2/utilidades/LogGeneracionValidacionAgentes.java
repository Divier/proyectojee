package co.com.datatools.datatimer.c2.utilidades;

import co.com.datatools.c2.log.ILogueable;

public class LogGeneracionValidacionAgentes implements ILogueable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer citacionesSinAgente;
    private Integer agentesNoRegistrados;
    private Integer agentesNoVigentes;
    private Integer totalDeInconsistencias;

    public Integer getCitacionesSinAgente() {
        return citacionesSinAgente;
    }

    public void setCitacionesSinAgente(Integer citacionesSinAgente) {
        this.citacionesSinAgente = citacionesSinAgente;
    }

    public Integer getAgentesNoRegistrados() {
        return agentesNoRegistrados;
    }

    public void setAgentesNoRegistrados(Integer agentesNoRegistrados) {
        this.agentesNoRegistrados = agentesNoRegistrados;
    }

    public Integer getTotalDeInconsistencias() {
        return totalDeInconsistencias;
    }

    public void setTotalDeInconsistencias(Integer totalDeInconsistencias) {
        this.totalDeInconsistencias = totalDeInconsistencias;
    }

    public Integer getAgentesNoVigentes() {
        return agentesNoVigentes;
    }

    public void setAgentesNoVigentes(Integer agentesNoVigentes) {
        this.agentesNoVigentes = agentesNoVigentes;
    }

}