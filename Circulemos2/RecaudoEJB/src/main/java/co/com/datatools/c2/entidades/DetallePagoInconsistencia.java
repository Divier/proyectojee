package co.com.datatools.c2.entidades;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the detalle_pago_inconsistencia database table.
 * 
 */
@Entity
@Table(name = "detalle_pago_inconsistencia")
@NamedQuery(name = "DetallePagoInconsistencia.findAll", query = "SELECT d FROM DetallePagoInconsistencia d")
public class DetallePagoInconsistencia implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pago_inconsistencia")
    private String id;

    @Column(name = "id_concepto")
    private Integer idConceptoCartera;

    @Column(name = "id_tipo_recaudo")
    private Integer idTipoRecaudo;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "numero_obligacion")
    private String numeroObligacion;

    @Column(name = "valor_obligacion")
    private BigDecimal valorObligacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago_inconsistencia")
    private PagoInconsistencia pagoInconsistencia;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "detalle_pago_incon_error",
            joinColumns = { @JoinColumn(name = "id_detalle_pago_inconsistencia") },
            inverseJoinColumns = { @JoinColumn(name = "id_error_inconsistencia") })
    private List<ErrorInconsistenciaPago> errorInconsistenciaPagos;

    public DetallePagoInconsistencia() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdConcepto() {
        return this.idConceptoCartera;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConceptoCartera = idConcepto;
    }

    public Integer getIdTipoRecaudo() {
        return this.idTipoRecaudo;
    }

    public void setIdTipoRecaudo(Integer idTipoRecaudo) {
        this.idTipoRecaudo = idTipoRecaudo;
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

    public PagoInconsistencia getPagoInconsistencia() {
        return this.pagoInconsistencia;
    }

    public void setPagoInconsistencia(PagoInconsistencia pagoInconsistencia) {
        this.pagoInconsistencia = pagoInconsistencia;
    }

    public List<ErrorInconsistenciaPago> getErrorInconsistenciaPagos() {
        return errorInconsistenciaPagos;
    }

    public void setErrorInconsistenciaPagos(List<ErrorInconsistenciaPago> errorInconsistenciaPagos) {
        this.errorInconsistenciaPagos = errorInconsistenciaPagos;
    }

}