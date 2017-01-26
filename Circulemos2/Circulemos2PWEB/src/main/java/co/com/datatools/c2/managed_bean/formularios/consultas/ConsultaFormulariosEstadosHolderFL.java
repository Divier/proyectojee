package co.com.datatools.c2.managed_bean.formularios.consultas;

import java.util.List;

import co.com.datatools.c2.dto.formularios.ConsultaSeguimientoAgrupadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoResponsableFormularioDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Manejo objetos para formularios estados
 * 
 * @author giovanni.velandia
 * 
 */
public class ConsultaFormulariosEstadosHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Variable que determina si es un tipo empresa o organismo de transito
    private boolean esEmpresa;
    private boolean esOrgTransito;
    private ConsultaSeguimientoAgrupadoFormularioDTO consultaSegAgrupFormuFiltroDTO;
    private List<FormularioDTO> formularioDTOs;
    private FormularioDTO formularioSeleccionDTO;

    public ConsultaFormulariosEstadosHolderFL() {
        consultaSegAgrupFormuFiltroDTO = new ConsultaSeguimientoAgrupadoFormularioDTO();
        consultaSegAgrupFormuFiltroDTO.setTipoResponsableFormularioDTO(new TipoResponsableFormularioDTO());
        consultaSegAgrupFormuFiltroDTO.setTipoFormularioDTO(new TipoFormularioDTO());
        // consultaSegAgrupFormuFiltroDTO.setEstadoFormularioDTO(new EstadoFormularioDTO());
    }

    public ConsultaSeguimientoAgrupadoFormularioDTO getConsultaSegAgrupFormuFiltroDTO() {
        return consultaSegAgrupFormuFiltroDTO;
    }

    public void setConsultaSegAgrupFormuFiltroDTO(
            ConsultaSeguimientoAgrupadoFormularioDTO consultaSegAgrupFormuFiltroDTO) {
        this.consultaSegAgrupFormuFiltroDTO = consultaSegAgrupFormuFiltroDTO;
    }

    public boolean isEsEmpresa() {
        return esEmpresa;
    }

    public void setEsEmpresa(boolean esEmpresa) {
        this.esEmpresa = esEmpresa;
    }

    public boolean isEsOrgTransito() {
        return esOrgTransito;
    }

    public void setEsOrgTransito(boolean esOrgTransito) {
        this.esOrgTransito = esOrgTransito;
    }

    public List<FormularioDTO> getFormularioDTOs() {
        return formularioDTOs;
    }

    public void setFormularioDTOs(List<FormularioDTO> formularioDTOs) {
        this.formularioDTOs = formularioDTOs;
    }

    public FormularioDTO getFormularioSeleccionDTO() {
        return formularioSeleccionDTO;
    }

    public void setFormularioSeleccionDTO(FormularioDTO formularioSeleccionDTO) {
        this.formularioSeleccionDTO = formularioSeleccionDTO;
    }
}
