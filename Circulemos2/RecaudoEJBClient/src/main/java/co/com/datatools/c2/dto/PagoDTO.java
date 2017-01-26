package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Apr 28 15:48:04 COT 2016
 */
public class PagoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaTransaccion;
    private Integer idBanco;
    private Integer idTipoCuenta;
    private Integer idUsuario;
    private String numeroCuenta;
    private String numeroPago;
    private BigDecimal totalCheque;
    private BigDecimal totalEfectivo;
    private BigDecimal totalRecaudo;
    private OrganismoTransitoDTO organismoTransito;
    private TipoIdentificacionPersonaDTO tipoIdentificacionPersona;
    private String numeroDocumento;
    private Date fechaRegistro;
    private List<DetallePagoDTO> detallesPago;
    private List<ErrorConciliacionPagoDTO> errorConciliacionPagos;

    // --- Constructor
    public PagoDTO() {
    }

    public PagoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public OrganismoTransitoDTO getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public TipoIdentificacionPersonaDTO getTipoIdentificacionPersona() {
        return this.tipoIdentificacionPersona;
    }

    public void setTipoIdentificacionPersona(TipoIdentificacionPersonaDTO tipoIdentificacionPersona) {
        this.tipoIdentificacionPersona = tipoIdentificacionPersona;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetallePagoDTO> getDetallesPago() {
        if (this.detallesPago == null) {
            this.detallesPago = new java.util.ArrayList<>(1);
        }
        return this.detallesPago;
    }

    public void setDetallesPago(List<DetallePagoDTO> detallesPago) {
        this.detallesPago = detallesPago;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ErrorConciliacionPagoDTO> getErrorConciliacionPagos() {
        if (this.errorConciliacionPagos == null) {
            this.errorConciliacionPagos = new java.util.ArrayList<>(1);
        }
        return this.errorConciliacionPagos;
    }

    public void setErrorConciliacionPagos(List<ErrorConciliacionPagoDTO> errorConciliacionPagos) {
        this.errorConciliacionPagos = errorConciliacionPagos;
    }

}
