package co.com.datatools.c2.managed_bean.administracionnegocio.configuracion.interes;

import java.util.List;

import co.com.datatools.c2.dto.ClaseInteresDTO;
import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.dto.PeriodoInteresDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo interes-flow CU_CIR20_DAT_ADM_016
 * 
 * @author Dixon.Alvarez
 * 
 */
public class InteresHolderFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "interesHolderFL";

    private InteresDTO interesFiltro;
    private List<InteresDTO> intereses;
    private InteresDTO interesSeleccionado;
    private boolean visiblePopupEliminar;

    public InteresHolderFL() {
        super();
        interesFiltro = new InteresDTO();
        interesFiltro.setClaseInteres(new ClaseInteresDTO());
        interesFiltro.setPeriodoInteres(new PeriodoInteresDTO());
    }

    public List<InteresDTO> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<InteresDTO> intereses) {
        this.intereses = intereses;
    }

    public InteresDTO getInteresFiltro() {
        return interesFiltro;
    }

    public void setInteresFiltro(InteresDTO interesFiltro) {
        this.interesFiltro = interesFiltro;
    }

    public InteresDTO getInteresSeleccionado() {
        return interesSeleccionado;
    }

    public void setInteresSeleccionado(InteresDTO interesSeleccionado) {
        this.interesSeleccionado = interesSeleccionado;
    }

    public boolean isVisiblePopupEliminar() {
        return visiblePopupEliminar;
    }

    public void setVisiblePopupEliminar(boolean visiblePopupEliminar) {
        this.visiblePopupEliminar = visiblePopupEliminar;
    }
}
