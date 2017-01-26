package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class DetallePagoInconsistenciaResDTO implements Serializable {

    private BigInteger idPagoInconsistencia;
    private int organismoTransito;
    private String nombreOrganismo;
    private String fechaTransaccion;
    private String horaTransaccion;
    private String numeroRecaudo;
    private String cuenta;
    private String codigoTipoCuenta;
    private String codigoBanco;
    private BigDecimal totalEfectivo;
    private BigDecimal totalCheque;
    private BigDecimal totalRecaudo;
    private String codigoTipoDocumento;
    private String numeroIdentificacion;
    private String obligacionPagada;
    private BigDecimal valorObligacion;
    private String codigoConcepto;
    private String codigoTipoRecaudo;
    private int numeroCuota;
    private String inconsistencias;

    public BigInteger getIdPagoInconsistencia() {
        return idPagoInconsistencia;
    }

    public void setIdPagoInconsistencia(BigInteger idPagoInconsistencia) {
        this.idPagoInconsistencia = idPagoInconsistencia;
    }

    public int getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(int organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getNumeroRecaudo() {
        return numeroRecaudo;
    }

    public void setNumeroRecaudo(String numeroRecaudo) {
        this.numeroRecaudo = numeroRecaudo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCodigoTipoCuenta() {
        return codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(String codigoTipoCuenta) {
        this.codigoTipoCuenta = codigoTipoCuenta;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public BigDecimal getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(BigDecimal totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public BigDecimal getTotalCheque() {
        return totalCheque;
    }

    public void setTotalCheque(BigDecimal totalCheque) {
        this.totalCheque = totalCheque;
    }

    public BigDecimal getTotalRecaudo() {
        return totalRecaudo;
    }

    public void setTotalRecaudo(BigDecimal totalRecaudo) {
        this.totalRecaudo = totalRecaudo;
    }

    public String getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(String codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getObligacionPagada() {
        return obligacionPagada;
    }

    public void setObligacionPagada(String obligacionPagada) {
        this.obligacionPagada = obligacionPagada;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public String getCodigoConcepto() {
        return codigoConcepto;
    }

    public void setCodigoConcepto(String codigoConcepto) {
        this.codigoConcepto = codigoConcepto;
    }

    public String getCodigoTipoRecaudo() {
        return codigoTipoRecaudo;
    }

    public void setCodigoTipoRecaudo(String codigoTipoRecaudo) {
        this.codigoTipoRecaudo = codigoTipoRecaudo;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public String getInconsistencias() {
        return inconsistencias;
    }

    public void setInconsistencias(String inconsistencias) {
        this.inconsistencias = inconsistencias;
    }

}
