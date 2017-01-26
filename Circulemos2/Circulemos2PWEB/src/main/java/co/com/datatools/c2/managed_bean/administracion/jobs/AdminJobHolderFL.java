package co.com.datatools.c2.managed_bean.administracion.jobs;

import java.util.List;

import co.com.datatools.c2.dto.TareaAutomaticaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * Objeto para el manejo de la informacion de consulta de jobs
 * 
 * @author divier.casas
 * 
 */
public class AdminJobHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "adminJobHolderFL";

    /**
     * Listado de jobs
     */
    private List<TareaAutomaticaDTO> lsTareaAutDTO;

    /**
     * Estado del job seleccionado
     */
    private TareaAutomaticaDTO tarAutSeleccionada;
    private String expresionCron;

    public List<TareaAutomaticaDTO> getLsTareaAutDTO() {
        return lsTareaAutDTO;
    }

    public void setLsTareaAutDTO(List<TareaAutomaticaDTO> lsTareaAutDTO) {
        this.lsTareaAutDTO = lsTareaAutDTO;
    }

    public TareaAutomaticaDTO getTarAutSeleccionada() {
        return tarAutSeleccionada;
    }

    public void setTarAutSeleccionada(TareaAutomaticaDTO tarAutSeleccionada) {
        this.tarAutSeleccionada = tarAutSeleccionada;
    }

    public String getExpresionCron() {
        return expresionCron;
    }

    public void setExpresionCron(String expresionCron) {
        this.expresionCron = expresionCron;
    }
}