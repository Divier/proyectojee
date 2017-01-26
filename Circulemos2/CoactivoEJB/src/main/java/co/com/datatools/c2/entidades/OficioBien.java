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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the precoactivo database table.
 * 
 */
@Entity
@Table(name = "oficio_bien")
public class OficioBien implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficio_bien")
    private Long id;

    @Column(name = "numero_oficio")
    private String numeroOficio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud_bien_entidad")
    private SolicitudBienEntidad solicitudBienEntidad;

    @OneToMany(mappedBy = "oficioBien")
    private List<CoactivoOficioBien> coactivoOficioBiens;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    public OficioBien() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public List<CoactivoOficioBien> getCoactivoOficioBiens() {
        return coactivoOficioBiens;
    }

    public void setCoactivoOficioBiens(List<CoactivoOficioBien> coactivoOficioBiens) {
        this.coactivoOficioBiens = coactivoOficioBiens;
    }

    public SolicitudBienEntidad getSolicitudBienEntidad() {
        return solicitudBienEntidad;
    }

    public void setSolicitudBienEntidad(SolicitudBienEntidad solicitudBienEntidad) {
        this.solicitudBienEntidad = solicitudBienEntidad;
    }
}