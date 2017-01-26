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
@Table(name = "incon_detal_cuota_finan")
@NamedQuery(
        name = "InconsistenciaDetalleCuotasFinanciacion.findAll",
        query = "SELECT i FROM InconsistenciaDetalleCuotasFinanciacion i")
public class InconsistenciaDetalleCuotasFinanciacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_cuotas_financiacion")
    private Long idDetalleCuotasFinanciacion;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pago_oportuno")
    private Date fechaPagoOportuno;

    @Column(name = "valor_cuota")
    private BigDecimal valorCuota;

    @Column(name = "recargo_cuota")
    private BigDecimal recargoCuota;

    // bi-directional many-to-one association to ItFinanciacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_financiacion")
    private InconsistenciaFinanciacion inconsistenciaFinanciacion;

    public InconsistenciaDetalleCuotasFinanciacion() {
    }

    public Long getIdDetalleCuotasFinanciacion() {
        return idDetalleCuotasFinanciacion;
    }

    public void setIdDetalleCuotasFinanciacion(Long idDetalleCuotasFinanciacion) {
        this.idDetalleCuotasFinanciacion = idDetalleCuotasFinanciacion;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaPagoOportuno() {
        return fechaPagoOportuno;
    }

    public void setFechaPagoOportuno(Date fechaPagoOportuno) {
        this.fechaPagoOportuno = fechaPagoOportuno;
    }

    public BigDecimal getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(BigDecimal valorCuota) {
        this.valorCuota = valorCuota;
    }

    public BigDecimal getRecargoCuota() {
        return recargoCuota;
    }

    public void setRecargoCuota(BigDecimal recargoCuota) {
        this.recargoCuota = recargoCuota;
    }

    public InconsistenciaFinanciacion getInconsistenciaFinanciacion() {
        return inconsistenciaFinanciacion;
    }

    public void setInconsistenciaFinanciacion(InconsistenciaFinanciacion inconsistenciaFinanciacion) {
        this.inconsistenciaFinanciacion = inconsistenciaFinanciacion;
    }

}
