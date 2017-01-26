package co.com.datatools.c2.managed_bean.formularios.numeracion;

import java.util.List;

import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase que encapsula los datos para la consulta de numeraciones
 * 
 * @author claudia.rodriguez
 * 
 */
public class NumeracionHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "numeracionHolderFL";

    /**
     * Almacena el resulta de una consulta de numeraciones
     */
    private List<NumeracionFormularioDTO> resultadoConsulta;
    /**
     * Guarda la numeracion seleccionada en la tabla de resultados de consulta
     */
    private NumeracionFormularioDTO numeracionSeleccionada;
    /**
     * Contiene los datos de la lista de tipos de formularios
     */
    // private List<SelectItem> listaTiposFormulario;

    /**
     * Id del tipo de formulario que sirve de filtro de consulta de numeraciones
     */
    private Integer idTipoFormulario;
    /**
     * Estado(Activo/Inactivo) que sirve de filtro de consulta de numeraciones
     */
    private Boolean estado;

    public NumeracionHolderFL() {

    }

    public List<NumeracionFormularioDTO> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<NumeracionFormularioDTO> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public NumeracionFormularioDTO getNumeracionSeleccionada() {
        return numeracionSeleccionada;
    }

    public void setNumeracionSeleccionada(NumeracionFormularioDTO numeracionSeleccionada) {
        this.numeracionSeleccionada = numeracionSeleccionada;
    }

    // public List<SelectItem> getListaTiposFormulario() {
    // return listaTiposFormulario;
    // }
    //
    // public void setListaTiposFormulario(List<SelectItem> listaTiposFormulario) {
    // this.listaTiposFormulario = listaTiposFormulario;
    // }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
