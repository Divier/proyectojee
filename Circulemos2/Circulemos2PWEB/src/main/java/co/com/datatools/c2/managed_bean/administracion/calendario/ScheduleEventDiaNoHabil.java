package co.com.datatools.c2.managed_bean.administracion.calendario;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.ScheduleEvent;

import co.com.datatools.c2.dto.DiaNoHabilDTO;

/**
 * @author luis.forero
 */
public class ScheduleEventDiaNoHabil implements ScheduleEvent, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Date fecha;
    private DiaNoHabilDTO diaNoHabilDTO;

    public ScheduleEventDiaNoHabil() {
        diaNoHabilDTO = new DiaNoHabilDTO();
    }

    public ScheduleEventDiaNoHabil(DiaNoHabilDTO diaNoHabilDTO) {
        this.fecha = diaNoHabilDTO.getFecha();
        this.id = String.valueOf(diaNoHabilDTO.getId());
        this.diaNoHabilDTO = diaNoHabilDTO;
    }

    @Override
    public Object getData() {
        return diaNoHabilDTO;
    }

    @Override
    public Date getEndDate() {
        return fecha;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date getStartDate() {
        return fecha;
    }

    @Override
    public String getStyleClass() {
        return "diaNoHabil";
    }

    @Override
    public String getTitle() {
        return "Día No Hábil";
    }

    @Override
    public boolean isAllDay() {
        return true;
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public void setId(String arg0) {
        id = arg0;
    }

    // [PF5] Actualizacion
    @Override
    public String getDescription() {
        return null;
    }

}
