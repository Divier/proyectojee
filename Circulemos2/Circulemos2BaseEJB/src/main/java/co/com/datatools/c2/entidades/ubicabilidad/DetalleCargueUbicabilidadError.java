package co.com.datatools.c2.entidades.ubicabilidad;

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

/**
 * The persistent class for the detalle_cargue_ubicabilidad_error database table.
 * 
 */
@Entity
@Table(name = "detalle_cargue_ubicabilidad_error")
@NamedQuery(name = "DetalleCargueUbicabilidadError.findAll", query = "SELECT d FROM DetalleCargueUbicabilidadError d")
public class DetalleCargueUbicabilidadError implements co.com.datatools.util.dto.EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_cargue_ubicabilidad_error")
    private Long id;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    // bi-directional many-to-one association to DetalleCargueUbicabilidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_cargue_ubicabilidad")
    private DetalleCargueUbicabilidad detalleCargueUbicabilidad;

    // bi-directional many-to-one association to ErrorCargueUbicabilidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_error_cargue_ubicabilidad")
    private ErrorCargueUbicabilidad errorCargueUbicabilidad;

    public DetalleCargueUbicabilidadError() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleCargueUbicabilidad getDetalleCargueUbicabilidad() {
        return this.detalleCargueUbicabilidad;
    }

    public void setDetalleCargueUbicabilidad(DetalleCargueUbicabilidad detalleCargueUbicabilidad) {
        this.detalleCargueUbicabilidad = detalleCargueUbicabilidad;
    }

    public ErrorCargueUbicabilidad getErrorCargueUbicabilidad() {
        return this.errorCargueUbicabilidad;
    }

    public void setErrorCargueUbicabilidad(ErrorCargueUbicabilidad errorCargueUbicabilidad) {
        this.errorCargueUbicabilidad = errorCargueUbicabilidad;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}