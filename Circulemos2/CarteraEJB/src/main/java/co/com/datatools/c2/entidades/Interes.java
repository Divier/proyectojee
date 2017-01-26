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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 7
 */
@Entity
@Table(name = "interes")
@NamedQueries({ @NamedQuery(name = "Interes.findAll", query = "SELECT i FROM Interes i") })
public class Interes implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interes")
    private Long id;

    @Basic(optional = false)
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @Basic(optional = false)
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Basic(optional = false)
    @Column(name = "porcentaje_interes_diario")
    private BigDecimal porcentajeInteresDiario;

    @Basic(optional = false)
    @Column(name = "porcentaje_tasa_interes")
    private BigDecimal porcentajeTasaInteres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clase_interes")
    private ClaseInteres claseInteres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo_interes")
    private PeriodoInteres periodoInteres;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    public Interes() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public BigDecimal getPorcentajeInteresDiario() {
        return porcentajeInteresDiario;
    }

    public void setPorcentajeInteresDiario(BigDecimal porcentajeInteresDiario) {
        this.porcentajeInteresDiario = porcentajeInteresDiario;
    }

    public BigDecimal getPorcentajeTasaInteres() {
        return porcentajeTasaInteres;
    }

    public void setPorcentajeTasaInteres(BigDecimal porcentajeTasaInteres) {
        this.porcentajeTasaInteres = porcentajeTasaInteres;
    }

    public ClaseInteres getClaseInteres() {
        return claseInteres;
    }

    public void setClaseInteres(ClaseInteres claseInteres) {
        this.claseInteres = claseInteres;
    }

    public PeriodoInteres getPeriodoInteres() {
        return periodoInteres;
    }

    public void setPeriodoInteres(PeriodoInteres periodoInteres) {
        this.periodoInteres = periodoInteres;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}