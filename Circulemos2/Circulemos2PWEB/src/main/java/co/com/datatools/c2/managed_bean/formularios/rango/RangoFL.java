package co.com.datatools.c2.managed_bean.formularios.rango;

import java.util.Date;
import java.util.Map;

import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class RangoFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "rangoFL";

    private String organismo;
    private Integer idTipoFormulario;
    private String numeroInicial;
    private String numeroFinal;
    private Date fechaAutorizacion;

    private RangoFormularioDTO rangoModificar;
    private Map<String, String> detalleFormularios;

    public RangoFL() {
        // TODO Auto-generated constructor stub
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public String getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public String getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public RangoFormularioDTO getRangoModificar() {
        return rangoModificar;
    }

    public void setRangoModificar(RangoFormularioDTO rangoModificar) {
        this.rangoModificar = rangoModificar;
    }

    public Map<String, String> getDetalleFormularios() {
        return detalleFormularios;
    }

    public void setDetalleFormularios(Map<String, String> detalleFormularios) {
        this.detalleFormularios = detalleFormularios;
    }

}
