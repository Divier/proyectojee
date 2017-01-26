package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
import java.util.Date;

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

@Entity
@Table(name = "obligacion_financiacion")
@NamedQuery(name = "ObligacionFinanciacion.findAll", query = "SELECT o FROM ObligacionFinanciacion o")
public class ObligacionFinanciacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obligacion_financiacion")
    private Long id;

    @Column(name = "codigo_tipo_obligacion")
    private Integer codigoTipoObligacion;

    @Column(name = "numero_obligacion")
    private String numeroObligacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_financiacion")
    private Financiacion financiacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_obligacion")
    private Date fechaObligacion;

    @Column(name = "valor_costas_procesales")
    private BigDecimal valorCostasProcesales;

    @Column(name = "valor_obligacion")
    private BigDecimal valorObligacion;

    @Column(name = "valor_interes_moratorios")
    private BigDecimal valorInteresMoratorios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoTipoObligacion() {
        return codigoTipoObligacion;
    }

    public void setCodigoTipoObligacion(Integer codigoTipoObligacion) {
        this.codigoTipoObligacion = codigoTipoObligacion;
    }

    public String getNumeroObligacion() {
        return numeroObligacion;
    }

    public void setNumeroObligacion(String numeroObligacion) {
        this.numeroObligacion = numeroObligacion;
    }

    public Financiacion getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(Financiacion financiacion) {
        this.financiacion = financiacion;
    }

    public Date getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(Date fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public BigDecimal getValorCostasProcesales() {
        return valorCostasProcesales;
    }

    public void setValorCostasProcesales(BigDecimal valorCostasProcesales) {
        this.valorCostasProcesales = valorCostasProcesales;
    }

    public BigDecimal getValorObligacion() {
        return valorObligacion;
    }

    public void setValorObligacion(BigDecimal valorObligacion) {
        this.valorObligacion = valorObligacion;
    }

    public BigDecimal getValorInteresMoratorios() {
        return valorInteresMoratorios;
    }

    public void setValorInteresMoratorios(BigDecimal valorInteresMoratorios) {
        this.valorInteresMoratorios = valorInteresMoratorios;
    }

}