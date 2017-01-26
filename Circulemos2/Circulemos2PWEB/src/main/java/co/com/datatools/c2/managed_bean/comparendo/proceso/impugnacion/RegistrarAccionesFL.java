package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.List;

import co.com.datatools.c2.dto.AccionImpugnacionBackDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * FL para el manejo de registro de acciones HU_CIR20_DAT_JUR_008
 * 
 * @author dixon.alvarez 2016-06-10
 * 
 */
public class RegistrarAccionesFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "registrarAccionesFL";

    private List<AccionImpugnacionBackDTO> acciones;
    private AccionImpugnacionBackDTO accionRegistrar;
    private AccionImpugnacionBackDTO accionSeleccionada;
    private Long idProceso;
    private boolean registroNuevo;
    private boolean habilitarCampos;

    public List<AccionImpugnacionBackDTO> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<AccionImpugnacionBackDTO> acciones) {
        this.acciones = acciones;
    }

    public AccionImpugnacionBackDTO getAccionRegistrar() {
        return accionRegistrar;
    }

    public void setAccionRegistrar(AccionImpugnacionBackDTO accionRegistrar) {
        this.accionRegistrar = accionRegistrar;
    }

    public AccionImpugnacionBackDTO getAccionSeleccionada() {
        return accionSeleccionada;
    }

    public void setAccionSeleccionada(AccionImpugnacionBackDTO accionSeleccionada) {
        this.accionSeleccionada = accionSeleccionada;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public boolean isRegistroNuevo() {
        return registroNuevo;
    }

    public void setRegistroNuevo(boolean registroNuevo) {
        this.registroNuevo = registroNuevo;
    }

    public boolean isHabilitarCampos() {
        return habilitarCampos;
    }

    public void setHabilitarCampos(boolean habilitarCampos) {
        this.habilitarCampos = habilitarCampos;
    }

}
