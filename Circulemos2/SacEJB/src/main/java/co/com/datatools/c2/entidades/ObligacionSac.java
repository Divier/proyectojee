package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the obligacion_sac database table.
 * 
 */
@Entity
@Table(name = "obligacion_sac")
@NamedQuery(name = "ObligacionSac.findAll", query = "SELECT o FROM ObligacionSac o")
public class ObligacionSac implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obligacion_sac")
    private Long id;

    @Column(name = "anio_factura")
    private Integer anioFactura;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_factura")
    private Date fechaFactura;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pago_oportuna")
    private Date fechaPagoOportuna;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_factura")
    private Date horaFactura;

    @Column(name = "id_cartera")
    private Long idCartera;

    @Column(name = "id_estado_proceso_multa")
    private Integer idEstadoProcesoMulta;

    @Column(name = "id_estado_transaccion_sac")
    private Integer idEstadoTransaccionSac;

    @Column(name = "id_origen_factura_sac")
    private Integer idOrigenFacturaSac;

    @Column(name = "id_tipo_documento_sac")
    private Integer idTipoDocumentoSac;

    @Column(name = "id_tipo_gestion_sac")
    private Integer idTipoGestionSac;

    @Column(name = "id_tipo_notificacion_sac")
    private Integer idTipoNotificacionSac;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "numero_documento_deudor")
    private String numeroDocumentoDeudor;

    @Column(name = "numero_factura")
    private String numeroFactura;

    @Column(name = "primer_apellido_deudor")
    private String primerApellidoDeudor;

    @Column(name = "primer_nombre_deudor")
    private String primerNombreDeudor;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "saldo_total_deuda")
    private BigDecimal saldoTotalDeuda;

    @Column(name = "segundo_apellido_deudor")
    private String segundoApellidoDeudor;

    @Column(name = "segundo_nombre_deudor")
    private String segundoNombreDeudor;

    @Column(name = "valor_cobrar")
    private BigDecimal valorCobrar;

    @Column(name = "valor_interes_financiacion")
    private BigDecimal valorInteresFinanciacion;

    @Column(name = "valor_recargo_generado")
    private BigDecimal valorRecargoGenerado;

    @Column(name = "valor_tasa_generada")
    private BigDecimal valorTasaGenerada;

    @Column(name = "numero_citacion")
    private String numeroCitacion;

    // bi-directional many-to-one association to NovedadSac
    @OneToMany(mappedBy = "obligacionSac")
    private List<NovedadSac> novedadSacs;

    // bi-directional many-to-one association to EvidenciaObligacionSac
    @OneToMany(mappedBy = "obligacionSac")
    private List<EvidenciaObligacionSac> evidenciaObligacionSacs;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_cambio")
    private Date fechaCambio;

    public ObligacionSac() {
    }

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
        return horaFactura;
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

    public List<NovedadSac> getNovedadSacs() {
        return this.novedadSacs;
    }

    public void setNovedadSacs(List<NovedadSac> novedadSacs) {
        this.novedadSacs = novedadSacs;
    }

    public NovedadSac addNovedadSac(NovedadSac novedadSac) {
        getNovedadSacs().add(novedadSac);
        novedadSac.setObligacionSac(this);

        return novedadSac;
    }

    public NovedadSac removeNovedadSac(NovedadSac novedadSac) {
        getNovedadSacs().remove(novedadSac);
        novedadSac.setObligacionSac(null);

        return novedadSac;
    }

    public List<EvidenciaObligacionSac> getEvidenciaObligacionSacs() {
        return evidenciaObligacionSacs;
    }

    public void setEvidenciaObligacionSacs(List<EvidenciaObligacionSac> evidenciaObligacionSacs) {
        this.evidenciaObligacionSacs = evidenciaObligacionSacs;
    }

    public EvidenciaObligacionSac addEvidenciaObligacionSac(EvidenciaObligacionSac evidenciaObligacionSac) {
        getEvidenciaObligacionSacs().add(evidenciaObligacionSac);
        evidenciaObligacionSac.setObligacionSac(this);

        return evidenciaObligacionSac;
    }

    public EvidenciaObligacionSac removeEvidenciaObligacionSac(EvidenciaObligacionSac evidenciaObligacionSac) {
        getEvidenciaObligacionSacs().remove(evidenciaObligacionSac);
        evidenciaObligacionSac.setObligacionSac(null);

        return evidenciaObligacionSac;
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