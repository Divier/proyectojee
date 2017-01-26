package co.com.datatools.c2.managed_bean.formularios.consultas;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class ConsultarSeguimientoFormularioHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "consultarSeguimientoFormularioHolderFL";

    private Integer idTipoFormulario;
    private String numeroFormulario;

    private String nomTipoFormulario;

    /**
     * listado de relaciones consultadas
     */
    private List<SeguimientoFormularioFL> lstSeguimientos;

    /**
     * Estado del seguimiento seleccionado
     */
    private SeguimientoFormularioFL seguimientoSeleccionado;

    public ConsultarSeguimientoFormularioHolderFL() {
        lstSeguimientos = new ArrayList<>();
    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public List<SeguimientoFormularioFL> getLstSeguimientos() {
        return lstSeguimientos;
    }

    public void setLstSeguimientos(List<SeguimientoFormularioFL> lstSeguimientos) {
        this.lstSeguimientos = lstSeguimientos;
    }

    public String getNomTipoFormulario() {
        return nomTipoFormulario;
    }

    public void setNomTipoFormulario(String nomTipoFormulario) {
        this.nomTipoFormulario = nomTipoFormulario;
    }

    public SeguimientoFormularioFL getSeguimientoSeleccionado() {
        return seguimientoSeleccionado;
    }

    public void setSeguimientoSeleccionado(SeguimientoFormularioFL seguimientoSeleccionado) {
        this.seguimientoSeleccionado = seguimientoSeleccionado;
    }

}
