package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO de datos de aprobacion de coactivo
 * 
 * @author julio.pinzon 2016-09-30
 */
public class AprobacionPrecoactivoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idPrecoactivo;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombreCompleto;
    private String direccion;
    private String medioImposicion;
    private Long idFacturaAxis;
    private BigDecimal valorMulta;
    private String numeroCitacion;
    private Date fechaInfraccion;
    private String codigoInfraccion;
    private Date fechaNotificacion;
    private String descripcionInfraccion;

    // --- Constructor
    public AprobacionPrecoactivoDTO() {
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMedioImposicion() {
        return medioImposicion;
    }

    public void setMedioImposicion(String medioImposicion) {
        this.medioImposicion = medioImposicion;
    }

    public Long getIdFacturaAxis() {
        return idFacturaAxis;
    }

    public void setIdFacturaAxis(Long idFacturaAxis) {
        this.idFacturaAxis = idFacturaAxis;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public Date getFechaInfraccion() {
        return fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Long getIdPrecoactivo() {
        return idPrecoactivo;
    }

    public void setIdPrecoactivo(Long idPrecoactivo) {
        this.idPrecoactivo = idPrecoactivo;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

}