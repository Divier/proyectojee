package co.com.datatools.c2.entidades;

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

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "incon_finan_detal_cuota_finan")
@NamedQuery(
        name = "InconsistenciaFinanciacionDetalleCuotaFinanciacion.findAll",
        query = "SELECT o FROM InconsistenciaFinanciacionDetalleCuotaFinanciacion o")
public class InconsistenciaFinanciacionDetalleCuotaFinanciacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incon_finan_detal_cuota_finan")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_cuotas_financiacion")
    private InconsistenciaDetalleCuotasFinanciacion inconsistenciaDetalleCuotasFinanciacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_financiacion")
    private InconsistenciaFinanciacion inconsistenciaFinanciacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_financiacion")
    private InconsistenciaDetalleFinanciacion inconsistenciaDetalleFinanciacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_error_inconsistencia_finan")
    private ErrorInconsistenciaFinanciacion errorInconsistenciaFinanciacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InconsistenciaDetalleCuotasFinanciacion getInconsistenciaDetalleCuotasFinanciacion() {
        return inconsistenciaDetalleCuotasFinanciacion;
    }

    public void setInconsistenciaDetalleCuotasFinanciacion(
            InconsistenciaDetalleCuotasFinanciacion inconsistenciaDetalleCuotasFinanciacion) {
        this.inconsistenciaDetalleCuotasFinanciacion = inconsistenciaDetalleCuotasFinanciacion;
    }

    public InconsistenciaFinanciacion getInconsistenciaFinanciacion() {
        return inconsistenciaFinanciacion;
    }

    public void setInconsistenciaFinanciacion(InconsistenciaFinanciacion inconsistenciaFinanciacion) {
        this.inconsistenciaFinanciacion = inconsistenciaFinanciacion;
    }

    public InconsistenciaDetalleFinanciacion getInconsistenciaDetalleFinanciacion() {
        return inconsistenciaDetalleFinanciacion;
    }

    public void setInconsistenciaDetalleFinanciacion(InconsistenciaDetalleFinanciacion inconsistenciaDetalleFinanciacion) {
        this.inconsistenciaDetalleFinanciacion = inconsistenciaDetalleFinanciacion;
    }

    public ErrorInconsistenciaFinanciacion getErrorInconsistenciaFinanciacion() {
        return errorInconsistenciaFinanciacion;
    }

    public void setErrorInconsistenciaFinanciacion(ErrorInconsistenciaFinanciacion errorInconsistenciaFinanciacion) {
        this.errorInconsistenciaFinanciacion = errorInconsistenciaFinanciacion;
    }

}