package co.com.datatools.c2.managed_bean.formularios.rango;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Clase que encapsula los datos para la consulta de rangos
 * 
 * @author claudia.rodriguez
 * 
 */
public class RangoHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "rangoHolderFL";

    private Integer idTipoFormulario;
    private Integer idEstadoRango;

    private List<RangoFormularioDTO> resultadoConsulta;
    private RangoFormularioDTO rangoSeleccionado;

    private String numeroInicial;
    private String numeroFinal;
    private Date fechaAutorizacionInicial;
    private Date fechaAutorizacionFinal;

    SortedMap<String, String> detalleFormularios;

    public RangoHolderFL() {

    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public Integer getIdEstadoRango() {
        return idEstadoRango;
    }

    public void setIdEstadoRango(Integer idEstadoRango) {
        this.idEstadoRango = idEstadoRango;
    }

    public List<RangoFormularioDTO> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<RangoFormularioDTO> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public RangoFormularioDTO getRangoSeleccionado() {
        return rangoSeleccionado;
    }

    public void setRangoSeleccionado(RangoFormularioDTO rangoSeleccionado) {
        this.rangoSeleccionado = rangoSeleccionado;
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

    public Date getFechaAutorizacionInicial() {
        return fechaAutorizacionInicial;
    }

    public void setFechaAutorizacionInicial(Date fechaAutorizacionInicial) {
        this.fechaAutorizacionInicial = fechaAutorizacionInicial;
    }

    public Date getFechaAutorizacionFinal() {
        return fechaAutorizacionFinal;
    }

    public void setFechaAutorizacionFinal(Date fechaAutorizacionFinal) {
        this.fechaAutorizacionFinal = fechaAutorizacionFinal;
    }

    public SortedMap<String, String> getDetalleFormularios() {
        return detalleFormularios;
    }

    public void setDetalleFormularios(SortedMap<String, String> detalleFormularios) {
        this.detalleFormularios = detalleFormularios;
    }

}
