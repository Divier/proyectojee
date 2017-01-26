package co.com.datatools.c2.dto.formularios;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class ResultadoConsultaDetalleCambioEstado implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BigInteger idDetalleCambioEstado;
    private Integer idTipoAsignacion;
    private String nombreTipoAsignacion;
    private Integer idTipoFormulario;
    private String nombreTipoFormulario;
    private BigInteger idResponsable;
    private Integer idArea;
    private String nombreArea;
    private Integer idTipoIdentificacion;
    private String nombreTipoIdentificacion;
    private String numeroIdentificacion;
    private String nombreResponsable;
    private String folio;
    private String numeroInicial;
    private String numeroFinal;
    private Integer cantidadFormularios;
    private Date fechaMovimiento;
    private String observaciones;
    private String placa;
    private String nombreEstadoFormulario;
    private Integer idEstadoFormulario;

    public ResultadoConsultaDetalleCambioEstado() {
    }

    public BigInteger getIdDetalleCambioEstado() {
        return idDetalleCambioEstado;
    }

    public void setIdDetalleCambioEstado(BigInteger idDetalleCambioEstado) {
        this.idDetalleCambioEstado = idDetalleCambioEstado;
    }

    public Integer getIdTipoAsignacion() {
        return idTipoAsignacion;
    }

    public void setIdTipoAsignacion(Integer idTipoAsignacion) {
        this.idTipoAsignacion = idTipoAsignacion;
    }

    public String getNombreTipoAsignacion() {
        return nombreTipoAsignacion;
    }

    public void setNombreTipoAsignacion(String nombreTipoAsignacion) {
        this.nombreTipoAsignacion = nombreTipoAsignacion;
    }

    public Integer getIdTipoFormulario() {
        return idTipoFormulario;
    }

    public void setIdTipoFormulario(Integer idTipoFormulario) {
        this.idTipoFormulario = idTipoFormulario;
    }

    public String getNombreTipoFormulario() {
        return nombreTipoFormulario;
    }

    public void setNombreTipoFormulario(String nombreTipoFormulario) {
        this.nombreTipoFormulario = nombreTipoFormulario;
    }

    public BigInteger getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(BigInteger idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public Integer getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
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

    public Integer getCantidadFormularios() {
        return cantidadFormularios;
    }

    public void setCantidadFormularios(Integer cantidadFormularios) {
        this.cantidadFormularios = cantidadFormularios;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNombreTipoIdentificacion() {
        return nombreTipoIdentificacion;
    }

    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }

    public String getNombreEstadoFormulario() {
        return nombreEstadoFormulario;
    }

    public void setNombreEstadoFormulario(String nombreEstadoFormulario) {
        this.nombreEstadoFormulario = nombreEstadoFormulario;
    }

    public Integer getIdEstadoFormulario() {
        return idEstadoFormulario;
    }

    public void setIdEstadoFormulario(Integer idEstadoFormulario) {
        this.idEstadoFormulario = idEstadoFormulario;
    }

}
