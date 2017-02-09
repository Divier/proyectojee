package co.com.datatools.c2.managed_bean.financiacion.administracion.proceso_financiacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.dto.ConsultaFinanciacionDTO;
import co.com.datatools.c2.dto.FiltroConsultaFinanciacionDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;

/**
 * 
 * Objeto para el manejo de la informacion de consulta de financiancion
 * 
 * @author giovanni.velandia
 * 
 */
public class ConsultaFinanciacionHolderFL implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "consultaFinanciacionHolderFL";

    private ConsultaFinanciacionDTO consultaFinanciacionSelecDTO;
    private FiltroConsultaFinanciacionDTO filtroConsultaFinanciacionDTO;
    private List<ConsultaFinanciacionDTO> consultaFinanciacionDTOs;

    // Persona filtro
    private PersonaDTO personaFiltroDTO;
    private boolean detalle;

    private List<SelectItem> anios;

    public ConsultaFinanciacionHolderFL() {
        filtroConsultaFinanciacionDTO = new FiltroConsultaFinanciacionDTO();
        consultaFinanciacionDTOs = new ArrayList<ConsultaFinanciacionDTO>();
    }

    public FiltroConsultaFinanciacionDTO getFiltroConsultaFinanciacionDTO() {
        return filtroConsultaFinanciacionDTO;
    }

    public void setFiltroConsultaFinanciacionDTO(FiltroConsultaFinanciacionDTO filtroConsultaFinanciacionDTO) {
        this.filtroConsultaFinanciacionDTO = filtroConsultaFinanciacionDTO;
    }

    public List<ConsultaFinanciacionDTO> getConsultaFinanciacionDTOs() {
        return consultaFinanciacionDTOs;
    }

    public void setConsultaFinanciacionDTOs(List<ConsultaFinanciacionDTO> consultaFinanciacionDTOs) {
        this.consultaFinanciacionDTOs = consultaFinanciacionDTOs;
    }

    public ConsultaFinanciacionDTO getConsultaFinanciacionSelecDTO() {
        return consultaFinanciacionSelecDTO;
    }

    public void setConsultaFinanciacionSelecDTO(ConsultaFinanciacionDTO consultaFinanciacionSelecDTO) {
        this.consultaFinanciacionSelecDTO = consultaFinanciacionSelecDTO;
    }

    public PersonaDTO getPersonaFiltroDTO() {
        return personaFiltroDTO;
    }

    public void setPersonaFiltroDTO(PersonaDTO personaFiltroDTO) {
        this.personaFiltroDTO = personaFiltroDTO;
    }

    public boolean isDetalle() {
        return detalle;
    }

    public void setDetalle(boolean detalle) {
        this.detalle = detalle;
    }

    public List<SelectItem> getAnios() {
        return anios;
    }

    public void setAnios(List<SelectItem> anios) {
        this.anios = anios;
    }

}
