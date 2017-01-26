package co.com.datatools.datatimer.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * No Autogenerar
 */
public class JobDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2955524609092402398L;
    private String nombreJob;
    private int IdJob;
    private EstadoJobDTO estadoJobDTO;
    final private List<TriggerDTO> listTriggers;
    private String grupoJob;

    public List<TriggerDTO> getListTriggers() {
        return new ArrayList<TriggerDTO>(listTriggers);
    }

    public void addTrigger(TriggerDTO triggerDTO) {
        listTriggers.add(triggerDTO);
    }

    /*
     * public void setListTriggers(List<TriggerDTO> listTriggers) { if (listTriggers == null) { throw new IllegalArgumentException(
     * "Lista de Triggers no puede ser null"); } else if (listTriggers.size() == 0) { throw new IllegalArgumentException(
     * "Lista de Triggers se encuentra vacia"); } this.listTriggers = listTriggers; }
     */

    public String getGrupoJob() {
        return grupoJob;
    }

    public void setGrupoJob(String grupoJob) {
        this.grupoJob = grupoJob;
    }

    public String getNombreJob() {
        return nombreJob;
    }

    public void setNombreJob(String nameJob) {
        this.nombreJob = nameJob;
    }

    public int getIdJob() {
        return IdJob;
    }

    public void setIdJob(int idJob) {
        IdJob = idJob;
    }

    public EstadoJobDTO getEstadoJobDTO() {
        return estadoJobDTO;
    }

    public void setEstadoJobDTO(EstadoJobDTO estadoJobDTO) {
        this.estadoJobDTO = estadoJobDTO;
    }

    public JobDTO() {
        // TODO Auto-generated constructor stub
        listTriggers = new ArrayList<TriggerDTO>();
    }

    @Override
    public String toString() {
        StringBuilder infoTriggers = new StringBuilder();
        for (TriggerDTO triggerDTO : listTriggers) {
            infoTriggers.append(triggerDTO);
            infoTriggers.append("\n");
        }
        return "Id Job [" + this.IdJob + "] Nombre Job [" + this.nombreJob + "] Estado Job [" + this.estadoJobDTO
                + "] Triggers:\n" + infoTriggers;
    }
}
