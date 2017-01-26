package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ComparendoReplicaDTO")
public class ComparendoReplicaDTO implements Serializable {

    private static final long serialVersionUID = -7800142172984543129L;

    private String numeroFactura;
    private int tipoCitacion;
    private String nombreTipoCitacion;
    private String estadoCitacion;
    private String numeroCitacion;
    private String placaVehiculo;
    private Date fechaImposicion;
    private BigDecimal valorTarifa;
    private BigDecimal valorIntereses;
    private BigDecimal valorComparendo;
    private BigDecimal saldoComparendo;

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

    public BigDecimal getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(BigDecimal valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public BigDecimal getValorIntereses() {
        return valorIntereses;
    }

    public void setValorIntereses(BigDecimal valorIntereses) {
        this.valorIntereses = valorIntereses;
    }

    public BigDecimal getValorComparendo() {
        return valorComparendo;
    }

    public void setValorComparendo(BigDecimal valorComparendo) {
        this.valorComparendo = valorComparendo;
    }

    public BigDecimal getSaldoComparendo() {
        return saldoComparendo;
    }

    public void setSaldoComparendo(BigDecimal saldoComparendo) {
        this.saldoComparendo = saldoComparendo;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNombreTipoCitacion() {
        return nombreTipoCitacion;
    }

    public void setNombreTipoCitacion(String nombreTipoCitacion) {
        this.nombreTipoCitacion = nombreTipoCitacion;
    }
}
