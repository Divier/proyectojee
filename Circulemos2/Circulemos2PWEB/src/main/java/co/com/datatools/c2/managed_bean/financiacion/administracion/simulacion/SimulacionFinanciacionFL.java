package co.com.datatools.c2.managed_bean.financiacion.administracion.simulacion;

import co.com.datatools.c2.dto.SimulacionFinanciacionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * 
 * @author luis.forero (2016-06-24)
 */
public class SimulacionFinanciacionFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String SIMULACION_FINANCIACION_FL = "simulacionFinanciacionFL";

    private SimulacionFinanciacionDTO simulacionFinanciacionDTO;

    private PersonaDTO infractor;

    private boolean confirmarFinanciacion;

    private String firma;

    public SimulacionFinanciacionDTO getSimulacionFinanciacionDTO() {
        return simulacionFinanciacionDTO;
    }

    public void setSimulacionFinanciacionDTO(SimulacionFinanciacionDTO simulacionFinanciacionDTO) {
        this.simulacionFinanciacionDTO = simulacionFinanciacionDTO;
    }

    public PersonaDTO getInfractor() {
        return infractor;
    }

    public void setInfractor(PersonaDTO infractor) {
        this.infractor = infractor;
    }

    public boolean isConfirmarFinanciacion() {
        return confirmarFinanciacion;
    }

    public void setConfirmarFinanciacion(boolean confirmarFinanciacion) {
        this.confirmarFinanciacion = confirmarFinanciacion;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

}
