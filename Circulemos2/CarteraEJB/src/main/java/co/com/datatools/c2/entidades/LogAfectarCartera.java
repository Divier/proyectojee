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

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 5
 * 
 */
@Entity
@Table(name = "log_afectar_cartera")
@NamedQuery(name = "LogAfectarCartera.findAll", query = "SELECT l FROM LogAfectarCartera l")
public class LogAfectarCartera implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log_afectar_cartera")
    private Long id;

    @Column(name = "codigo_excepcion")
    private Integer codigoExcepcion;

    @Column(name = "descripcion_excepcion")
    private String descripcionExcepcion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento;

    @Basic(optional = false)
    @Column(name = "valor_movimiento")
    private BigDecimal valorMovimiento;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartera")
    private Cartera cartera;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concepto")
    private ConceptoCartera conceptoCartera;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioPersona usuarioPersona;

    public LogAfectarCartera() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoExcepcion() {
        return this.codigoExcepcion;
    }

    public void setCodigoExcepcion(Integer codigoExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
    }

    public String getDescripcionExcepcion() {
        return this.descripcionExcepcion;
    }

    public void setDescripcionExcepcion(String descripcionExcepcion) {
        this.descripcionExcepcion = descripcionExcepcion;
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

    public UsuarioPersona getUsuarioPersona() {
        return this.usuarioPersona;
    }

    public void setUsuarioPersona(UsuarioPersona usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public BigDecimal getValorMovimiento() {
        return this.valorMovimiento;
    }

    public void setValorMovimiento(BigDecimal valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public Cartera getCartera() {
        return this.cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }

    public ConceptoCartera getConceptoCartera() {
        return this.conceptoCartera;
    }

    public void setConceptoCartera(ConceptoCartera conceptoCartera) {
        this.conceptoCartera = conceptoCartera;
    }

}