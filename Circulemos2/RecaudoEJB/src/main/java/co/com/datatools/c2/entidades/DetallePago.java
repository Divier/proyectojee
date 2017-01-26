package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the detalle_pago database table.
 * 
 */
@Entity
@Table(name = "detalle_pago")
@NamedQuery(name = "DetallePago.findAll", query = "SELECT d FROM DetallePago d")
public class DetallePago implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pago")
    private Long id;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "numero_obligacion")
    private String numeroObligacion;

    @Column(name = "valor_obligacion")
    private BigDecimal valorObligacion;

    @Column(name = "id_concepto")
    private Integer idConceptoCartera;

    @Column(name = "id_tipo_recaudo")
    private Integer idTipoRecaudo;

    @Column(name = "numero_intentos")
    private Integer numeroIntentos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago")
    private Pago pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_pago")
    private EstadoPago estadoPago;

    @OneToMany(mappedBy = "detallePago", cascade = CascadeType.ALL)
    private List<DetallePagoConciliacionError> detallePagoConciliacionErrores;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_generacion_reporte")
    private Date fechaGeneracionReporte;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_aplicacion_pago")
    private Date fechaAplicacionPago;

    public DetallePago() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCuota() {
        return this.numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getNumeroObligacion() {
        return this.numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public BigDecimal getValorObligacion() {
        return this.valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public Integer getIdConceptoCartera() {
        return idConceptoCartera;
    }

    public void setIdConceptoCartera(Integer idConceptoCartera) {
        this.idConceptoCartera = idConceptoCartera;
    }

    public Integer getIdTipoRecaudo() {
        return idTipoRecaudo;
    }

    public void setIdTipoRecaudo(Integer idTipoRecaudo) {
        this.idTipoRecaudo = idTipoRecaudo;
    }

    public Integer getNumeroIntentos() {
        return numeroIntentos;
    }

    public void setNumeroIntentos(Integer numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public EstadoPago getEstadoPago() {
        return this.estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public List<DetallePagoConciliacionError> getDetallePagoConciliacionErrores() {
        if (detallePagoConciliacionErrores == null)
            detallePagoConciliacionErrores = new ArrayList<DetallePagoConciliacionError>();
        return detallePagoConciliacionErrores;
    }

    public void setDetallePagoConciliacionErrores(List<DetallePagoConciliacionError> detallePagoConciliacionErrores) {
        this.detallePagoConciliacionErrores = detallePagoConciliacionErrores;
    }

    public Date getFechaGeneracionReporte() {
        return fechaGeneracionReporte;
    }

    public void setFechaGeneracionReporte(Date fechaGeneracionReporte) {
        this.fechaGeneracionReporte = fechaGeneracionReporte;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaAplicacionPago() {
        return fechaAplicacionPago;
    }

    public void setFechaAplicacionPago(Date fechaAplicacionPago) {
        this.fechaAplicacionPago = fechaAplicacionPago;
    }

}