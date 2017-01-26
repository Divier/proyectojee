package co.com.datatools.c2.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the detalle_descuento database table.
 * 
 */
@Entity
@Table(name = "detalle_descuento")
@NamedQuery(name = "DetalleDescuento.findAll", query = "SELECT d FROM DetalleDescuento d")
public class DetalleDescuento implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento_detalle")
    private Integer id;

    @Column(name = "dias_fin")
    private short diasFin;

    @Column(name = "dias_inicio")
    private short diasInicio;

    @Column(name = "id_concepto")
    private int idConcepto;

    @Column(name = "porcentaje_descuento")
    private BigDecimal porcentajeDescuento;

    @ManyToOne
    @JoinColumn(name = "id_descuento", referencedColumnName = "id_descuento")
    private ConfiguracionDescuento configuracionDescuento;

    public DetalleDescuento() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getDiasFin() {
        return this.diasFin;
    }

    public void setDiasFin(short diasFin) {
        this.diasFin = diasFin;
    }

    public short getDiasInicio() {
        return this.diasInicio;
    }

    public void setDiasInicio(short diasInicio) {
        this.diasInicio = diasInicio;
    }

    public int getIdConcepto() {
        return this.idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public BigDecimal getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public ConfiguracionDescuento getConfiguracionDescuento() {
        return configuracionDescuento;
    }

    public void setConfiguracionDescuento(ConfiguracionDescuento configuracionDescuento) {
        this.configuracionDescuento = configuracionDescuento;
    }

}