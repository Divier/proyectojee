package co.com.datatools.c2.managed_bean.financiacion.administracion.simulacion;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConsultaSimulacionFinanciacionDTO;
import co.com.datatools.c2.dto.common.ConsultaObligacionesDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * @author javier.fajardo
 * 
 */
public class SimulacionFinanciacionHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String SIMULACION_FINANCIACION_HOLDER_FL = "simulacionFinanciacionHolderFL";

    private ConsultaSimulacionFinanciacionDTO simulacionDTO;
    private PersonaDTO infractorDTO;

    private List<ConsultaObligacionesDTO> listaResultadoInfractor = new ArrayList<ConsultaObligacionesDTO>();

    public SimulacionFinanciacionHolderFL() {
        super();
        infractorDTO = new PersonaDTO();
        infractorDTO.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
        simulacionDTO = new ConsultaSimulacionFinanciacionDTO();
    }

    public ConsultaSimulacionFinanciacionDTO getSimulacionDTO() {
        return simulacionDTO;
    }

    public void setSimulacionDTO(ConsultaSimulacionFinanciacionDTO simulacionDTO) {
        this.simulacionDTO = simulacionDTO;
    }

    public PersonaDTO getInfractorDTO() {
        return infractorDTO;
    }

    public void setInfractorDTO(PersonaDTO infractorDTO) {
        this.infractorDTO = infractorDTO;
    }

    public List<ConsultaObligacionesDTO> getListaResultadoInfractor() {
        return listaResultadoInfractor;
    }

    public void setListaResultadoInfractor(List<ConsultaObligacionesDTO> listaResultadoInfractor) {
        this.listaResultadoInfractor = listaResultadoInfractor;
    }

}
