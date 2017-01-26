package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Valores para mostrar en la tabla de resultados de la consulta de impugnaciones
 * 
 * @author divier.casas
 * 
 */
public class RespuestaImpugnacionDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;

    private String tipoComparendo;
    private String numeroComparendo;
    private Date fechaImposicion;
    private Date fechaNotificacion;
    private String codigoInfraccion;
    private BigDecimal valor;
    private String estadoComparendo;
    private boolean impugnable;
    private Long idComparendo;
    private Long idProceso;
    private String proceso;
    private String estadoProceso;
    private Integer idEstadoProceso;
    private String numeroCitacion;
    private String placa;
    private String medioImposicion;
    private String detalleInfraccion;

    public String getTipoComparendo() {
        return tipoComparendo;
    }

    public void setTipoComparendo(String tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getEstadoComparendo() {
        return estadoComparendo;
    }

    public void setEstadoComparendo(String estadoComparendo) {
        this.estadoComparendo = estadoComparendo;
    }

    public boolean isImpugnable() {
        return impugnable;
    }

    public void setImpugnable(boolean impugnable) {
        this.impugnable = impugnable;
    }

    public Long getIdComparendo() {
        return idComparendo;
    }

    public void setIdComparendo(Long idComparendo) {
        this.idComparendo = idComparendo;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public Integer getIdEstadoProceso() {
        return idEstadoProceso;
    }

    public void setIdEstadoProceso(Integer idEstadoProceso) {
        this.idEstadoProceso = idEstadoProceso;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMedioImposicion() {
        return medioImposicion;
    }

    public void setMedioImposicion(String medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

    public String getDetalleInfraccion() {
        return detalleInfraccion;
    }

    public void setDetalleInfraccion(String detalleInfraccion) {
        this.detalleInfraccion = detalleInfraccion;
    }

}