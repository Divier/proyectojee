package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 5
 * 
 */
@Entity
@Table(name = "saldo_cartera")
@NamedQuery(name = "SaldoCartera.findAll", query = "SELECT s FROM SaldoCartera s")
public class SaldoCartera implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_saldo_cartera")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_calculo")
    private Date fechaCalculo;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Basic(optional = false)
    @Column(name = "saldo")
    private BigDecimal saldo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartera")
    private Cartera cartera;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_saldo")
    private TipoSaldo tipoSaldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movimiento_cartera")
    private MovimientosCartera movimientoCartera;

    public SaldoCartera() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCalculo() {
        return this.fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cartera getCartera() {
        return this.cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }

    public TipoSaldo getTipoSaldo() {
        return this.tipoSaldo;
    }

    public void setTipoSaldo(TipoSaldo tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

    public MovimientosCartera getMovimientoCartera() {
        return movimientoCartera;
    }

    public void setMovimientoCartera(MovimientosCartera movimientoCartera) {
        this.movimientoCartera = movimientoCartera;
    }

}