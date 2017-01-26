package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the detalle_cargue_courier database table.
 * 
 */
@Entity
@Table(name = "detalle_cargue_courier")
@NamedQuery(name = "DetalleCargueCourier.findAll", query = "SELECT d FROM DetalleCargueCourier d")
public class DetalleCargueCourier implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_cargue_courier")
    private Long id;

    @Column(name = "numero_proceso")
    private String numeroProceso;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_proceso")
    private Date fechaProceso;

    private String respuesta;

    // bi-directional many-to-one association to CargueCourier
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargue_courier")
    private CargueCourier cargueCourier;

    // bi-directional many-to-one association to Coactivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coactivo")
    private Coactivo coactivo;

    // bi-directional many-to-one association to Direccion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;

    // bi-directional many-to-one association to DetalleCargueCourierError
    @OneToMany(mappedBy = "detalleCargueCourier", fetch = FetchType.LAZY)
    private List<DetalleCargueCourierError> detalleCargueCourierErrors;

    public DetalleCargueCourier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroProceso() {
        return this.numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public CargueCourier getCargueCourier() {
        return this.cargueCourier;
    }

    public void setCargueCourier(CargueCourier cargueCourier) {
        this.cargueCourier = cargueCourier;
    }

    public Coactivo getCoactivo() {
        return this.coactivo;
    }

    public void setCoactivo(Coactivo coactivo) {
        this.coactivo = coactivo;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<DetalleCargueCourierError> getDetalleCargueCourierErrors() {
        return this.detalleCargueCourierErrors;
    }

    public void setDetalleCargueCourierErrors(List<DetalleCargueCourierError> detalleCargueCourierErrors) {
        this.detalleCargueCourierErrors = detalleCargueCourierErrors;
    }

    public DetalleCargueCourierError addDetalleCargueCourierError(DetalleCargueCourierError detalleCargueCourierError) {
        getDetalleCargueCourierErrors().add(detalleCargueCourierError);
        detalleCargueCourierError.setDetalleCargueCourier(this);

        return detalleCargueCourierError;
    }

    public DetalleCargueCourierError removeDetalleCargueCourierError(
            DetalleCargueCourierError detalleCargueCourierError) {
        getDetalleCargueCourierErrors().remove(detalleCargueCourierError);
        detalleCargueCourierError.setDetalleCargueCourier(null);

        return detalleCargueCourierError;
    }

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

}