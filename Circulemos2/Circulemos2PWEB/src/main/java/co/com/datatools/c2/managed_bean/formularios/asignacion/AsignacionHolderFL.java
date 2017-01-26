package co.com.datatools.c2.managed_bean.formularios.asignacion;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.formularios.ResultadoConsultaDetalleCambioEstado;
import co.com.datatools.c2.dto.formularios.SeguimientoFormularioDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class AsignacionHolderFL extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "asignacionHolderFL";

    private Integer idEstadoFormulario;
    private String nombreTipoFormulario;
    private Integer idTipoDocResponsable;
    private String numeroDocResponsable;
    private String placaResponsable;
    private String numeroInicial;
    private String numeroFinal;
    private Integer idAreaResp;
    private Date fechaAsignacion;
    private Integer idTipoAsignacion;

    private List<ResultadoConsultaDetalleCambioEstado> resultadoConsulta;
    private ResultadoConsultaDetalleCambioEstado cambioEstadoSeleccionado;

    private List<SeguimientoFormularioDTO> detalleFormularios;

    public AsignacionHolderFL() {

    }

    public Integer getIdEstadoFormulario() {
        return idEstadoFormulario;
    }

    public void setIdEstadoFormulario(Integer idEstadoFormulario) {
        this.idEstadoFormulario = idEstadoFormulario;
    }

    public Integer getIdTipoDocResponsable() {
        return idTipoDocResponsable;
    }

    public void setIdTipoDocResponsable(Integer idTipoDocResponsable) {
        this.idTipoDocResponsable = idTipoDocResponsable;
    }

    public String getNumeroDocResponsable() {
        return numeroDocResponsable;
    }

    public void setNumeroDocResponsable(String numeroDocResponsable) {
        this.numeroDocResponsable = numeroDocResponsable;
    }

    public String getPlacaResponsable() {
        return placaResponsable;
    }

    public void setPlacaResponsable(String placaResponsable) {
        this.placaResponsable = placaResponsable;
    }

    public String getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public Integer getIdAreaResp() {
        return idAreaResp;
    }

    public void setIdAreaResp(Integer idAreaResp) {
        this.idAreaResp = idAreaResp;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getNombreTipoFormulario() {
        return nombreTipoFormulario;
    }

    public void setNombreTipoFormulario(String nombreTipoFormulario) {
        this.nombreTipoFormulario = nombreTipoFormulario;
    }

    public List<ResultadoConsultaDetalleCambioEstado> getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(List<ResultadoConsultaDetalleCambioEstado> resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

    public ResultadoConsultaDetalleCambioEstado getCambioEstadoSeleccionado() {
        return cambioEstadoSeleccionado;
    }

    public void setCambioEstadoSeleccionado(ResultadoConsultaDetalleCambioEstado cambioEstadoSeleccionado) {
        this.cambioEstadoSeleccionado = cambioEstadoSeleccionado;
    }

    public List<SeguimientoFormularioDTO> getDetalleFormularios() {
        return detalleFormularios;
    }

    public void setDetalleFormularios(List<SeguimientoFormularioDTO> detalleFormularios) {
        this.detalleFormularios = detalleFormularios;
    }

    public String getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public Integer getIdTipoAsignacion() {
        return idTipoAsignacion;
    }

    public void setIdTipoAsignacion(Integer idTipoAsignacion) {
        this.idTipoAsignacion = idTipoAsignacion;
    }

}
