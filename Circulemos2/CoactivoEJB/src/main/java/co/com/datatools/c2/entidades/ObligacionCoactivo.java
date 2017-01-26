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
 * The persistent class for the obligacion_coactivo database table.
 * 
 */
@Entity
@Table(name = "obligacion_coactivo")
@NamedQuery(name = "ObligacionCoactivo.findAll", query = "SELECT o FROM ObligacionCoactivo o")
public class ObligacionCoactivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obligacion_coactivo")
    private Long id;

    @Column(name = "codigo_tipo_obligacion")
    private Integer codigoTipoObligacion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_obligacion")
    private Date fechaObligacion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartera")
    private Cartera cartera;

    @Column(name = "numero_obligacion")
    private String numeroObligacion;

    @Column(name = "valor_costas_procesales")
    private BigDecimal valorCostasProcesales;

    @Column(name = "valor_interes_moratorios")
    private BigDecimal valorInteresMoratorios;

    @Column(name = "valor_obligacion")
    private BigDecimal valorObligacion;

    // bi-directional many-to-one association to Coactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coactivo")
    private Coactivo coactivo;

    public ObligacionCoactivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoObligacion() {
        return this.codigoTipoObligacion;
    }

    public void setCodigoTipoObligacion(Integer codigoTipoObligacion) {
        this.codigoTipoObligacion = codigoTipoObligacion;
    }

    public Date getFechaObligacion() {
        return this.fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public String getNumeroObligacion() {
        return this.numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public BigDecimal getValorCostasProcesales() {
        return this.valorCostasProcesales;
    }

    public void setValorCostasProcesales(BigDecimal valorCostasProcesales) {
        this.valorCostasProcesales = valorCostasProcesales;
    }

    public BigDecimal getValorInteresMoratorios() {
        return this.valorInteresMoratorios;
    }

    public void setValorInteresMoratorios(BigDecimal valorInteresMoratorios) {
        this.valorInteresMoratorios = valorInteresMoratorios;
    }

    public BigDecimal getValorObligacion() {
        return this.valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public Coactivo getCoactivo() {
        return this.coactivo;
    }

    public void setCoactivo(Coactivo coactivo) {
        this.coactivo = coactivo;
    }

    public Cartera getCartera() {
        return cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }

}