package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the novedad_sac database table.
 * 
 */
@Entity
@Table(name = "novedad_sac")
@NamedQuery(name = "NovedadSac.findAll", query = "SELECT n FROM NovedadSac n")
public class NovedadSac implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_novedad_sac")
    private Long idNovedadSac;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_novedad")
    private Date fechaNovedad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "id_estado_transaccion_sac")
    private Integer idEstadoTransaccionSac;

    @Column(name = "id_tipo_novedad_sac")
    private Integer idTipoNovedadSac;

    @Column(name = "soporte_novedad")
    private String soporteNovedad;

    @Column(name = "valor_novedad")
    private BigDecimal valorNovedad;

    // bi-directional many-to-one association to ObligacionSac
    @ManyToOne
    @JoinColumn(name = "id_obligacion_sac")
    private ObligacionSac obligacionSac;

    public NovedadSac() {
    }

    public Long getIdNovedadSac() {
        return this.idNovedadSac;
    }

    public void setIdNovedadSac(Long idNovedadSac) {
        this.idNovedadSac = idNovedadSac;
    }

    public Date getFechaNovedad() {
        return this.fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdEstadoTransaccionSac() {
        return this.idEstadoTransaccionSac;
    }

    public void setIdEstadoTransaccionSac(Integer idEstadoTransaccionSac) {
        this.idEstadoTransaccionSac = idEstadoTransaccionSac;
    }

    public Integer getIdTipoNovedadSac() {
        return this.idTipoNovedadSac;
    }

    public void setIdTipoNovedadSac(Integer idTipoNovedadSac) {
        this.idTipoNovedadSac = idTipoNovedadSac;
    }

    public String getSoporteNovedad() {
        return this.soporteNovedad;
    }

    public void setSoporteNovedad(String soporteNovedad) {
        this.soporteNovedad = soporteNovedad;
    }

    public BigDecimal getValorNovedad() {
        return this.valorNovedad;
    }

    public void setValorNovedad(BigDecimal valorNovedad) {
        this.valorNovedad = valorNovedad;
    }

    public ObligacionSac getObligacionSac() {
        return this.obligacionSac;
    }

    public void setObligacionSac(ObligacionSac obligacionSac) {
        this.obligacionSac = obligacionSac;
    }

}