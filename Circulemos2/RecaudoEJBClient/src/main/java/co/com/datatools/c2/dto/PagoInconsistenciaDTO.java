package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Apr 27 10:29:29 COT 2016
 */
public class PagoInconsistenciaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer codigoOrganismo;
    private Date fechaTransaccion;
    private Integer idBanco;
    private Integer idTipoCuenta;
    private String numeroCuenta;
    private String numeroPago;
    private String placaVehiculo;
    private BigDecimal totalCheque;
    private BigDecimal totalEfectivo;
    private BigDecimal totalRecaudo;
    private Date fechaGeneracionReporte;
    private Integer tipoIdentificacion;
    private String numeroDocumento;
    private List<DetallePagoInconsistenciaDTO> detallePagoInconsistencias;
    private List<ErrorInconsistenciaPagoDTO> errorInconsistenciaPagos;

    // --- Constructor
    public PagoInconsistenciaDTO() {
    }

    public PagoInconsistenciaDTO(Long id) {
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

    public Date getFechaTransaccion() {
        return this.fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Integer getIdBanco() {
        return this.idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdTipoCuenta() {
        return this.idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroPago() {
        return this.numeroPago;
    }

    public void setNumeroPago(String numeroPago) {
        this.numeroPago = numeroPago;
    }

    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
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

    public Date getFechaGeneracionReporte() {
        return this.fechaGeneracionReporte;
    }

    public void setFechaGeneracionReporte(Date fechaGeneracionReporte) {
        this.fechaGeneracionReporte = fechaGeneracionReporte;
    }

    public Integer getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetallePagoInconsistenciaDTO> getDetallePagoInconsistencias() {
        if (this.detallePagoInconsistencias == null) {
            this.detallePagoInconsistencias = new java.util.ArrayList<>(1);
        }
        return this.detallePagoInconsistencias;
    }

    public void setDetallePagoInconsistencias(List<DetallePagoInconsistenciaDTO> detallePagoInconsistencias) {
        this.detallePagoInconsistencias = detallePagoInconsistencias;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ErrorInconsistenciaPagoDTO> getErrorInconsistenciaPagos() {
        if (this.errorInconsistenciaPagos == null) {
            this.errorInconsistenciaPagos = new java.util.ArrayList<>(1);
        }
        return this.errorInconsistenciaPagos;
    }

    public void setErrorInconsistenciaPagos(List<ErrorInconsistenciaPagoDTO> errorInconsistenciaPagos) {
        this.errorInconsistenciaPagos = errorInconsistenciaPagos;
    }

}
