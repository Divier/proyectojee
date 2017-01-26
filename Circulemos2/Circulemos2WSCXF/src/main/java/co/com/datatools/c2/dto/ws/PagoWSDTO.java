package co.com.datatools.c2.dto.ws;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Datos del comparendo recibidos a traves del WebService.
 * 
 * @author julio.pinzon(2016-04-21)
 */
public class PagoWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    // ENCABEZADO
    /**
     * Código de organismo de tránsito que reporta el recaudo.
     */
    private Integer codigoOrganismoTransito;

    /**
     * Fecha contable de la transacción
     */
    private XMLGregorianCalendar fechaTransaccion;

    /**
     * Hora de la transacción
     */
    private XMLGregorianCalendar horaTransaccion;

    /**
     * Número de la consignación o registro de recaudo con que se realizó el pago.
     */
    private String numeroRecaudo;

    /**
     * Número de la cuenta que reporta el movimiento
     */
    private String cuenta;

    /**
     * Tipo de cuenta del recaudo, Catálogo del sistema Posibles valores: - Ahorros - Corriente
     */
    private String tipoCuenta;

    /**
     * Corresponde al banco donde se realiza el recaudo
     */
    private String bancoRecaudo;

    /**
     * Es el recaudado en efectivo
     */
    private BigDecimal totalEfectivo;

    /**
     * Es el valor recaudado en Cheque
     */
    private BigDecimal totalCheque;

    /**
     * Es el total recaudado en el pago a reportar.
     */
    private BigDecimal totalRecaudo;

    /**
     * Código del tipo de Documento del infractor.
     */
    private String codigoTipoIdentificacion;

    /**
     * Número de identificación del infractor
     */
    private String numeroIdentificacion;

    // DETALLE
    /**
     * Detalle del pago
     */
    private DetallePagoWSDTO detalles[];

    public Integer getCodigoOrganismoTransito() {
        return codigoOrganismoTransito;
    }

    public void setCodigoOrganismoTransito(Integer codigoOrganismoTransito) {
        this.codigoOrganismoTransito = codigoOrganismoTransito;
    }

    public XMLGregorianCalendar getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(XMLGregorianCalendar fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public XMLGregorianCalendar getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(XMLGregorianCalendar horaTransaccion) {
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

    public String getBancoRecaudo() {
        return bancoRecaudo;
    }

    public void setBancoRecaudo(String bancoRecaudo) {
        this.bancoRecaudo = bancoRecaudo;
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

    public String getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

    public void setCodigoTipoIdentificacion(String codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public DetallePagoWSDTO[] getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallePagoWSDTO[] detalles) {
        this.detalles = detalles;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

}