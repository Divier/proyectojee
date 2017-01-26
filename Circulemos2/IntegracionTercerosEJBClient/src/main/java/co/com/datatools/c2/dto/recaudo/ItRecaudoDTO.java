package co.com.datatools.c2.dto.recaudo;

import java.math.BigDecimal;
import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed May 04 16:49:32 COT 2016
 */
public class ItRecaudoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long idRecaudo;
    private String codigoBanco;
    private Integer codigoOrganismo;
    private String codigoTipoCuenta;
    private String codigoTipoIdentificacion;
    private Short estadoLectura;
    private Date fechaTransaccion;
    private Date horaTransaccion;
    private String numeroCuenta;
    private String numeroIdentificacion;
    private String numeroRecaudo;
    private BigDecimal totalCheque;
    private BigDecimal totalEfectivo;
    private BigDecimal totalRecaudo;
    private String codigoTipoRecaudo;
    private Integer numeroCuota;
    private String numeroObligacion;
    private BigDecimal valorObligacion;
    private Date fechaCreacion;
    private String usuario;

    // --- Constructor
    public ItRecaudoDTO() {
    }

    public ItRecaudoDTO(Long idRecaudo) {
        this.idRecaudo = idRecaudo;

    }

    // --- Getters-Setters
    public Long getIdRecaudo() {
        return this.idRecaudo;
    }

    public void setIdRecaudo(Long idRecaudo) {
        this.idRecaudo = idRecaudo;
    }

    public String getCodigoBanco() {
        return this.codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public Integer getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getCodigoTipoCuenta() {
        return this.codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(String codigoTipoCuenta) {
        this.codigoTipoCuenta = codigoTipoCuenta;
    }

    public String getCodigoTipoIdentificacion() {
        return this.codigoTipoIdentificacion;
    }

    public void setCodigoTipoIdentificacion(String codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public Short getEstadoLectura() {
        return this.estadoLectura;
    }

    public void setEstadoLectura(Short estadoLectura) {
        this.estadoLectura = estadoLectura;
    }

    public Date getFechaTransaccion() {
        return this.fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Date getHoraTransaccion() {
        return this.horaTransaccion;
    }

    public void setHoraTransaccion(Date horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroIdentificacion() {
        return this.numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNumeroRecaudo() {
        return this.numeroRecaudo;
    }

    public void setNumeroRecaudo(String numeroRecaudo) {
        this.numeroRecaudo = numeroRecaudo;
    }

    public BigDecimal getTotalCheque() {
        return this.totalCheque;
    }

    public void setTotalCheque(BigDecimal totalCheque) {
        this.totalCheque = totalCheque;
    }

    public BigDecimal getTotalEfectivo() {
        return this.totalEfectivo;
    }

    public void setTotalEfectivo(BigDecimal totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public BigDecimal getTotalRecaudo() {
        return this.totalRecaudo;
    }

    public void setTotalRecaudo(BigDecimal totalRecaudo) {
        this.totalRecaudo = totalRecaudo;
    }

    public String getCodigoTipoRecaudo() {
        return codigoTipoRecaudo;
    }

    public void setCodigoTipoRecaudo(String codigoTipoRecaudo) {
        this.codigoTipoRecaudo = codigoTipoRecaudo;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
