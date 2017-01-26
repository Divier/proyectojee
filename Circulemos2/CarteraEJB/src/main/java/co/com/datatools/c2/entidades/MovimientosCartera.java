package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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
 * @version Sprint 5
 * 
 */
@Entity
@Table(name = "movimientos_cartera")
@NamedQuery(name = "MovimientosCartera.findAll", query = "SELECT m FROM MovimientosCartera m")
public class MovimientosCartera implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento_cartera")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento;

    @Basic(optional = false)
    @Column(name = "login_usuario")
    private String loginUsuario;

    @Column(name = "observaciones")
    private String observaciones;

    @Basic(optional = false)
    @Column(name = "valor_movimiento")
    private BigDecimal valorMovimiento;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concepto")
    private ConceptoCartera conceptoCartera;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartera")
    private Cartera cartera;

    @OneToMany(mappedBy = "movimientoCartera", fetch = FetchType.LAZY)
    private List<SaldoCartera> saldoCarteras;

    public MovimientosCartera() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaMovimiento() {
        return this.fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getValorMovimiento() {
        return this.valorMovimiento;
    }

    public void setValorMovimiento(BigDecimal valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public ConceptoCartera getConceptoCartera() {
        return this.conceptoCartera;
    }

    public void setConceptoCartera(ConceptoCartera conceptoCartera) {
        this.conceptoCartera = conceptoCartera;
    }

    public Cartera getCartera() {
        return this.cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }

    public List<SaldoCartera> getSaldoCarteras() {
        return saldoCarteras;
    }

    public void setSaldoCarteras(List<SaldoCartera> saldoCarteras) {
        this.saldoCarteras = saldoCarteras;
    }

}