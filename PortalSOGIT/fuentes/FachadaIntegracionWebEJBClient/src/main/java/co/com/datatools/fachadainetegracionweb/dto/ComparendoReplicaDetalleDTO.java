package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ComparendoReplicaDetalleDTO")
public class ComparendoReplicaDetalleDTO implements Serializable {

    private static final long serialVersionUID = -7800142172984543129L;

    private int tipoCitacion;
    private String nombreTipoCitacion;
    private String estadoCitacion;
    private String numeroCitacion;
    private String placaVehiculo;
    private Date fechaImposicion;
    private String direccionInfraccion;
    private String idIdentificacion;
    private String nombreTipoIdentificacion;
    private String identificacion;
    private String nombreInfractor;
    private String codigoTipoNotifica;
    private Date fechaNotificacion;
    private String codigoInfraccion;
    private String descripcionInfraccion;
    private BigDecimal saldoComparendo;
    private BigDecimal valorIntereses;
    private BigDecimal totalSaldo;
    private Date fechaPago;

    public int getTipoCitacion() {
        return tipoCitacion;
    }

    public void setTipoCitacion(int tipoCitacion) {
        this.tipoCitacion = tipoCitacion;
    }

    public String getEstadoCitacion() {
        return estadoCitacion;
    }

    public void setEstadoCitacion(String estadoCitacion) {
        this.estadoCitacion = estadoCitacion;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public String getDireccionInfraccion() {
        return direccionInfraccion;
    }

    public void setDireccionInfraccion(String direccionInfraccion) {
        this.direccionInfraccion = direccionInfraccion;
    }

    public String getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(String idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreInfractor() {
        return nombreInfractor;
    }

    public void setNombreInfractor(String nombreInfractor) {
        this.nombreInfractor = nombreInfractor;
    }

    public String getCodigoTipoNotifica() {
        return codigoTipoNotifica;
    }

    public void setCodigoTipoNotifica(String codigoTipoNotifica) {
        this.codigoTipoNotifica = codigoTipoNotifica;
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

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public BigDecimal getValorIntereses() {
        return valorIntereses;
    }

    public void setValorIntereses(BigDecimal valorIntereses) {
        this.valorIntereses = valorIntereses;
    }

    public BigDecimal getSaldoComparendo() {
        return saldoComparendo;
    }

    public void setSaldoComparendo(BigDecimal saldoComparendo) {
        this.saldoComparendo = saldoComparendo;
    }

    public BigDecimal getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(BigDecimal totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    public String getNombreTipoCitacion() {
        return nombreTipoCitacion;
    }

    public void setNombreTipoCitacion(String nombreTipoCitacion) {
        this.nombreTipoCitacion = nombreTipoCitacion;
    }

    public String getNombreTipoIdentificacion() {
        return nombreTipoIdentificacion;
    }

    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
}
