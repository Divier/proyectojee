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

/**
 * The persistent class for the detalle_cargue_courier_error database table.
 * 
 */
@Entity
@Table(name = "detalle_cargue_courier_error")
@NamedQuery(name = "DetalleCargueCourierError.findAll", query = "SELECT d FROM DetalleCargueCourierError d")
public class DetalleCargueCourierError implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_cargue_courier_error")
    private Long id;

    // bi-directional many-to-one association to DetalleCargueCourier
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_cargue_courier")
    private DetalleCargueCourier detalleCargueCourier;

    // bi-directional many-to-one association to ErrorCargueCourier
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_error_cargue_courier")
    private ErrorCargueCourier errorCargueCourier;

    public DetalleCargueCourierError() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleCargueCourier getDetalleCargueCourier() {
        return this.detalleCargueCourier;
    }

    public void setDetalleCargueCourier(DetalleCargueCourier detalleCargueCourier) {
        this.detalleCargueCourier = detalleCargueCourier;
    }

    public ErrorCargueCourier getErrorCargueCourier() {
        return this.errorCargueCourier;
    }

    public void setErrorCargueCourier(ErrorCargueCourier errorCargueCourier) {
        this.errorCargueCourier = errorCargueCourier;
    }

}