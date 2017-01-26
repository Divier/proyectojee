package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

public class TareaAutomaticaDTO implements EntidadDtoC2 {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer idJob;
    private String nombreJob;
    private String grupoJob;
    private String nombreTrigger;
    private String grupoTrigger;
    private Date ejecucion;
    private String expresionTiempo;
    private String estadoTrigger;

    public Integer getIdJob() {
        return idJob;
    }

    public void setIdJob(Integer idJob) {
        this.idJob = idJob;
    }

    public String getNombreJob() {
        return nombreJob;
    }

    public void setNombreJob(String nombreJob) {
        this.nombreJob = nombreJob;
    }

    public String getGrupoJob() {
        return grupoJob;
    }

    public void setGrupoJob(String grupoJob) {
        this.grupoJob = grupoJob;
    }

    public String getNombreTrigger() {
        return nombreTrigger;
    }

    public void setNombreTrigger(String nombreTrigger) {
        this.nombreTrigger = nombreTrigger;
    }

    public String getGrupoTrigger() {
        return grupoTrigger;
    }

    public void setGrupoTrigger(String grupoTrigger) {
        this.grupoTrigger = grupoTrigger;
    }

    public Date getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(Date ejecucion) {
        this.ejecucion = ejecucion;
    }

    public String getExpresionTiempo() {
        return expresionTiempo;
    }

    public void setExpresionTiempo(String expresionTiempo) {
        this.expresionTiempo = expresionTiempo;
    }

    public String getEstadoTrigger() {
        return estadoTrigger;
    }

    public void setEstadoTrigger(String estadoTrigger) {
        this.estadoTrigger = estadoTrigger;
    }
}