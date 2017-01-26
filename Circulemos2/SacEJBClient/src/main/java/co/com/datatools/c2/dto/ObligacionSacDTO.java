package co.com.datatools.c2.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed May 18 18:22:16 COT 2016
 */
public class ObligacionSacDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Integer anioFactura;
    private Date fechaFactura;
    private Date fechaNotificacion;
    private Date fechaPagoOportuna;
    private Date fechaRegistro;
    private Date horaFactura;
    private Long idCartera;
    private Integer idEstadoProcesoMulta;
    private Integer idEstadoTransaccionSac;
    private Integer idOrigenFacturaSac;
    private Integer idTipoDocumentoSac;
    private Integer idTipoGestionSac;
    private Integer idTipoNotificacionSac;
    private Integer numeroCuota;
    private String numeroDocumentoDeudor;
    private String numeroFactura;
    private String primerApellidoDeudor;
    private String primerNombreDeudor;
    private String razonSocial;
    private BigDecimal saldoTotalDeuda;
    private String segundoApellidoDeudor;
    private String segundoNombreDeudor;
    private BigDecimal valorCobrar;
    private BigDecimal valorInteresFinanciacion;
    private BigDecimal valorRecargoGenerado;
    private BigDecimal valorTasaGenerada;
    private List<NovedadSacDTO> novedadSacs;
    private List<EvidenciaObligacionSacDTO> evidenciaObligacionSacs;
    private Date fechaCambio;
    private String numeroCitacion;

    // --- Constructor
    public ObligacionSacDTO() {
    }

    public ObligacionSacDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnioFactura() {
        return this.anioFactura;
    }

    public void setAnioFactura(Integer anioFactura) {
        this.anioFactura = anioFactura;
    }

    public Date getFechaFactura() {
        return this.fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaPagoOportuna() {
        return this.fechaPagoOportuna;
    }

    public void setFechaPagoOportuna(Date fechaPagoOportuna) {
        this.fechaPagoOportuna = fechaPagoOportuna;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getHoraFactura() {
        return this.horaFactura;
    }

    public void setHoraFactura(Date horaFactura) {
        this.horaFactura = horaFactura;
    }

    public Long getIdCartera() {
        return this.idCartera;
    }

    public void setIdCartera(Long idCartera) {
        this.idCartera = idCartera;
    }

    public Integer getIdEstadoProcesoMulta() {
        return this.idEstadoProcesoMulta;
    }

    public void setIdEstadoProcesoMulta(Integer idEstadoProcesoMulta) {
        this.idEstadoProcesoMulta = idEstadoProcesoMulta;
    }

    public Integer getIdEstadoTransaccionSac() {
        return this.idEstadoTransaccionSac;
    }

    public void setIdEstadoTransaccionSac(Integer idEstadoTransaccionSac) {
        this.idEstadoTransaccionSac = idEstadoTransaccionSac;
    }

    public Integer getIdOrigenFacturaSac() {
        return this.idOrigenFacturaSac;
    }

    public void setIdOrigenFacturaSac(Integer idOrigenFacturaSac) {
        this.idOrigenFacturaSac = idOrigenFacturaSac;
    }

    public Integer getIdTipoDocumentoSac() {
        return this.idTipoDocumentoSac;
    }

    public void setIdTipoDocumentoSac(Integer idTipoDocumentoSac) {
        this.idTipoDocumentoSac = idTipoDocumentoSac;
    }

    public Integer getIdTipoGestionSac() {
        return this.idTipoGestionSac;
    }

    public void setIdTipoGestionSac(Integer idTipoGestionSac) {
        this.idTipoGestionSac = idTipoGestionSac;
    }

    public Integer getIdTipoNotificacionSac() {
        return this.idTipoNotificacionSac;
    }

    public void setIdTipoNotificacionSac(Integer idTipoNotificacionSac) {
        this.idTipoNotificacionSac = idTipoNotificacionSac;
    }

    public Integer getNumeroCuota() {
        return this.numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getNumeroDocumentoDeudor() {
        return this.numeroDocumentoDeudor;
    }

    public void setNumeroDocumentoDeudor(String numeroDocumentoDeudor) {
        this.numeroDocumentoDeudor = numeroDocumentoDeudor;
    }

    public String getNumeroFactura() {
        return this.numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getPrimerApellidoDeudor() {
        return this.primerApellidoDeudor;
    }

    public void setPrimerApellidoDeudor(String primerApellidoDeudor) {
        this.primerApellidoDeudor = primerApellidoDeudor;
    }

    public String getPrimerNombreDeudor() {
        return this.primerNombreDeudor;
    }

    public void setPrimerNombreDeudor(String primerNombreDeudor) {
        this.primerNombreDeudor = primerNombreDeudor;
    }

    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public BigDecimal getSaldoTotalDeuda() {
        return this.saldoTotalDeuda;
    }

    public void setSaldoTotalDeuda(BigDecimal saldoTotalDeuda) {
        this.saldoTotalDeuda = saldoTotalDeuda;
    }

    public String getSegundoApellidoDeudor() {
        return this.segundoApellidoDeudor;
    }

    public void setSegundoApellidoDeudor(String segundoApellidoDeudor) {
        this.segundoApellidoDeudor = segundoApellidoDeudor;
    }

    public String getSegundoNombreDeudor() {
        return this.segundoNombreDeudor;
    }

    public void setSegundoNombreDeudor(String segundoNombreDeudor) {
        this.segundoNombreDeudor = segundoNombreDeudor;
    }

    public BigDecimal getValorCobrar() {
        return this.valorCobrar;
    }

    public void setValorCobrar(BigDecimal valorCobrar) {
        this.valorCobrar = valorCobrar;
    }

    public BigDecimal getValorInteresFinanciacion() {
        return this.valorInteresFinanciacion;
    }

    public void setValorInteresFinanciacion(BigDecimal valorInteresFinanciacion) {
        this.valorInteresFinanciacion = valorInteresFinanciacion;
    }

    public BigDecimal getValorRecargoGenerado() {
        return this.valorRecargoGenerado;
    }

    public void setValorRecargoGenerado(BigDecimal valorRecargoGenerado) {
        this.valorRecargoGenerado = valorRecargoGenerado;
    }

    public BigDecimal getValorTasaGenerada() {
        return this.valorTasaGenerada;
    }

    public void setValorTasaGenerada(BigDecimal valorTasaGenerada) {
        this.valorTasaGenerada = valorTasaGenerada;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<NovedadSacDTO> getNovedadSacs() {
        if (this.novedadSacs == null) {
            this.novedadSacs = new java.util.ArrayList<>(1);
        }
        return this.novedadSacs;
    }

    public void setNovedadSacs(List<NovedadSacDTO> novedadSacs) {
        this.novedadSacs = novedadSacs;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<EvidenciaObligacionSacDTO> getEvidenciaObligacionSacs() {
        if (this.evidenciaObligacionSacs == null) {
            this.evidenciaObligacionSacs = new java.util.ArrayList<>(1);
        }
        return this.evidenciaObligacionSacs;
    }

    public void setEvidenciaObligacionSacs(List<EvidenciaObligacionSacDTO> evidenciaObligacionSacs) {
        this.evidenciaObligacionSacs = evidenciaObligacionSacs;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }
}
