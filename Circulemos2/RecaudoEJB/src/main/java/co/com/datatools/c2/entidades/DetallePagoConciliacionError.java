package co.com.datatools.c2.entidades;

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

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the detalle_pago_conci_error database table.
 * 
 */
@Entity
@Table(name = "detalle_pago_conci_error")
@NamedQuery(name = "DetallePagoConciError.findAll", query = "SELECT d FROM DetallePagoConciliacionError d")
public class DetallePagoConciliacionError implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detal_pago_conci_error")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_detalle_pago")
    private DetallePago detallePago;

    @ManyToOne
    @JoinColumn(name = "id_error_conciliacion_pago")
    private ErrorConciliacionPago errorConciliacionPago;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    public DetallePagoConciliacionError() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetallePago getDetallePago() {
        return this.detallePago;
    }

    public void setDetallePago(DetallePago detallePago) {
        this.detallePago = detallePago;
    }

    public ErrorConciliacionPago getErrorConciliacionPago() {
        return this.errorConciliacionPago;
    }

    public void setErrorConciliacionPago(ErrorConciliacionPago errorConciliacionPago) {
        this.errorConciliacionPago = errorConciliacionPago;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}