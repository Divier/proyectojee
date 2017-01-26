package co.com.datatools.datatimer.dto;

import java.io.Serializable;

import org.quartz.Trigger;

/**
 * No Autogenerar
 */
public class TriggerDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8894315416768475022L;
    private int idTrigger;
    private String nombre;
    private String grupo;
    private Boolean esAutomatico;
    private TipoScheduler tipoScheduler;
    private EstadoTriggerDTO estadoTriggerDTO;
    private String expresionCron;
    private JobDTO jobDTO;

    private String expresionScheduler;
    private Trigger triggerProgramatico;

    public int getIdTrigger() {
        return idTrigger;
    }

    public void setIdTrigger(int idTrigger) {
        this.idTrigger = idTrigger;
    }

    public Trigger getTriggerProgramatico() {
        return triggerProgramatico;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getEsAutomatico() {
        return esAutomatico;
    }

    public void setEsAutomatico(Boolean esAutomatico) {
        this.esAutomatico = esAutomatico;
    }

    public String getExpresionCron() {
        return expresionCron;
    }

    public void setExpresionCron(String expresionCron) {
        this.expresionCron = expresionCron;
    }

    public JobDTO getJobDTO() {
        return jobDTO;
    }

    public void setJobDTO(JobDTO jobDTO) {
        this.jobDTO = jobDTO;
    }

    private boolean isValidString(String dato, String nombreDato) {
        if (dato == null) {
            throw new IllegalArgumentException(nombreDato + " es null");
        } else if (dato.length() == 0) {
            throw new IllegalArgumentException(nombreDato + " es vacio");
        }
        return true;
    }

    public void setNombre(String nombre) {
        if (this.isValidString(nombre, "Nombre")) {
            this.nombre = nombre;
        }

    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        if (this.isValidString(grupo, "Grupo")) {
            this.grupo = grupo;
        }
    }

    public TipoScheduler getTipoScheduler() {
        return tipoScheduler;
    }

    public EstadoTriggerDTO getEstadoTriggerDTO() {
        return estadoTriggerDTO;
    }

    public void setEstadoTriggerDTO(EstadoTriggerDTO estadoTriggerDTO) {
        this.estadoTriggerDTO = estadoTriggerDTO;
    }

    public void setExpressionCronTrigger(String expressionScheduler) {

        if (this.isValidExpressionCron(expressionScheduler)) {
            this.tipoScheduler = TipoScheduler.Automatico;
            this.expresionScheduler = expressionScheduler;
        } else {
            throw new IllegalArgumentException("La expresión del Cron es incorrecta.");
        }

    }

    private boolean isValidExpressionCron(String expressionScheduler) {
        if (this.isValidString(expressionScheduler, "ExpressionScheduler")
                && org.quartz.CronExpression.isValidExpression(expressionScheduler)) {
            return true;
        } else {
            return false;
        }
    }

    public void setTriggerProgramatico(Trigger triggerProgramatico) {

        if (triggerProgramatico != null) {
            this.tipoScheduler = TipoScheduler.Programatico;
            this.triggerProgramatico = triggerProgramatico;
        } else {
            throw new IllegalArgumentException("Trigger programatico es null");
        }

    }

    public boolean isTriggerProgramatico() {
        if (this.tipoScheduler == TipoScheduler.Programatico)
            return true;
        else
            return false;
    }

    public boolean isTriggerAutomatico() {
        if (this.tipoScheduler == TipoScheduler.Automatico)
            return true;
        else
            return false;
    }

    public String getExpresionScheduler() {
        return expresionScheduler;
    }

    public TriggerDTO() {
        // TODO Auto-generated constructor stub
    }

    public enum TipoScheduler {
        Automatico, Programatico
    }

    @Override
    public boolean equals(Object object) {

        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            TriggerDTO trigger = (TriggerDTO) object;

            if (this.getNombre().equals(trigger.getNombre()) && this.getGrupo().equals(trigger.getGrupo())
                    && this.getTipoScheduler() == trigger.getTipoScheduler()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuffer infoTrigger = new StringBuffer();
        if (this.isTriggerAutomatico()) {
            infoTrigger.append(this.expresionScheduler);
        } else if (this.isTriggerProgramatico()) {
            infoTrigger.append(this.triggerProgramatico.toString());
        }
        return " Nombre [" + this.nombre + "] Grupo [" + this.grupo + "] Tipo [" + this.tipoScheduler + "] Estado ["
                + this.estadoTriggerDTO + "] Info [" + infoTrigger + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((tipoScheduler == null) ? 0 : tipoScheduler.hashCode());
        return result;
    }
}