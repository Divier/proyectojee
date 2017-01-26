package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the pago_inconsistencia database table.
 * 
 */
@Entity
@Table(name = "pago_inconsistencia")
@NamedQuery(name = "PagoInconsistencia.findAll", query = "SELECT p FROM PagoInconsistencia p")
public class PagoInconsistencia implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_inconsistencia")
    private Long id;

    @Column(name = "codigo_organismo")
    private Integer codigoOrganismo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_transaccion")
    private Date fechaTransaccion;

    @Column(name = "id_banco")
    private Integer idBanco;

    @Column(name = "id_tipo_cuenta")
    private Integer idTipoCuenta;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "numero_pago")
    private String numeroPago;

    @Column(name = "total_cheque")
    private BigDecimal totalCheque;

    @Column(name = "total_efectivo")
    private BigDecimal totalEfectivo;

    @Column(name = "total_recaudo")
    private BigDecimal totalRecaudo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_generacion_reporte")
    private Date fechaGeneracionReporte;

    @Column(name = "id_tipo_identificacion")
    private Integer tipoIdentificacion;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @OneToMany(mappedBy = "pagoInconsistencia")
    private List<DetallePagoInconsistencia> detallePagoInconsistencias;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "pago_inconsistencia_error",
            joinColumns = { @JoinColumn(name = "id_pago_inconsistencia") },
            inverseJoinColumns = { @JoinColumn(name = "id_error_inconsistencia") })
    private List<ErrorInconsistenciaPago> errorInconsistenciaPagos;

    public PagoInconsistencia() {
    }

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

    public Date getFechaGeneracionReporte() {
        return fechaGeneracionReporte;
    }

    public void setFechaGeneracionReporte(Date fechaGeneracionReporte) {
        this.fechaGeneracionReporte = fechaGeneracionReporte;
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

    public List<DetallePagoInconsistencia> getDetallePagoInconsistencias() {
        return this.detallePagoInconsistencias;
    }

    public void setDetallePagoInconsistencias(List<DetallePagoInconsistencia> detallePagoInconsistencias) {
        this.detallePagoInconsistencias = detallePagoInconsistencias;
    }

    public List<ErrorInconsistenciaPago> getErrorInconsistenciaPagos() {
        return errorInconsistenciaPagos;
    }

    public void setErrorInconsistenciaPagos(List<ErrorInconsistenciaPago> errorInconsistenciaPagos) {
        this.errorInconsistenciaPagos = errorInconsistenciaPagos;
    }

    public Integer getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

}