package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the novedad_sac database table.
 * 
 */
@Entity
@Table(name = "pago_novedad_sac")
public class PagoNovedadSac implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_novedad_sac")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pago_novedad")
    private Date fechaPagoNovedad;

    @Column(name = "id_novedad_sac")
    private Long idNovedadSac;

    @Column(name = "id_detalle_pago")
    private Long idDetallePago;

    public PagoNovedadSac() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPagoNovedad() {
        return fechaPagoNovedad;
    }

    public void setFechaPagoNovedad(Date fechaPagoNovedad) {
        this.fechaPagoNovedad = fechaPagoNovedad;
    }

    public Long getIdNovedadSac() {
        return idNovedadSac;
    }

    public void setIdNovedadSac(Long idNovedadSac) {
        this.idNovedadSac = idNovedadSac;
    }

    public Long getIdDetallePago() {
        return idDetallePago;
    }

    public void setIdDetallePago(Long idDetallePago) {
        this.idDetallePago = idDetallePago;
    }

}