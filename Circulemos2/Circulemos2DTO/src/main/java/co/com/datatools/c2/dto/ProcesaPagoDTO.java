package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed May 28 12:00:18 COT 2014
 */
public class ProcesaPagoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer codigoOrganismo;
    private Date fechaRecepcion;
    private String login;
    private BigInteger referenciaPago;
    private Date fechaPago;
    private BigDecimal valor;
    private Integer codigoMedioPago;
    private BigInteger numeroOperacion;
    private BigInteger numeroAutorizacion;
    private Integer idBanco;
    private Integer codigoProcedenciaPago;
    private Integer codigoSucursal;
    private Integer numeroTransaccion;

    // --- Constructor
    public ProcesaPagoDTO() {
    }

    public ProcesaPagoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaRecepcion() {
        return this.fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BigInteger getReferenciaPago() {
        return this.referenciaPago;
    }

    public void setReferenciaPago(BigInteger referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public Date getFechaPago() {
        return this.fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getCodigoMedioPago() {
        return this.codigoMedioPago;
    }

    public void setCodigoMedioPago(Integer codigoMedioPago) {
        this.codigoMedioPago = codigoMedioPago;
    }

    public BigInteger getNumeroOperacion() {
        return this.numeroOperacion;
    }

    public void setNumeroOperacion(BigInteger numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public BigInteger getNumeroAutorizacion() {
        return this.numeroAutorizacion;
    }

    public void setNumeroAutorizacion(BigInteger numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public Integer getIdBanco() {
        return this.idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getCodigoProcedenciaPago() {
        return this.codigoProcedenciaPago;
    }

    public void setCodigoProcedenciaPago(Integer codigoProcedenciaPago) {
        this.codigoProcedenciaPago = codigoProcedenciaPago;
    }

    public Integer getCodigoSucursal() {
        return this.codigoSucursal;
    }

    public void setCodigoSucursal(Integer codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public Integer getNumeroTransaccion() {
        return this.numeroTransaccion;
    }

    public void setNumeroTransaccion(Integer numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }

}
