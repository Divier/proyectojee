package co.com.datatools.c2.entidades;

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
 * The persistent class for the pago_conciliacion_error database table.
 * 
 */
@Entity
@Table(name = "pago_conciliacion_error")
@NamedQuery(name = "PagoConciliacionError.findAll", query = "SELECT p FROM PagoConciliacionError p")
public class PagoConciliacionError implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_conci_error")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pago")
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "id_error_conciliacion_pago")
    private ErrorConciliacionPago errorConciliacionPago;

    public PagoConciliacionError() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pago getPago() {
        return this.pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public ErrorConciliacionPago getErrorConciliacionPago() {
        return this.errorConciliacionPago;
    }

    public void setErrorConciliacionPago(ErrorConciliacionPago errorConciliacionPago) {
        this.errorConciliacionPago = errorConciliacionPago;
    }

}