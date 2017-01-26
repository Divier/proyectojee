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
@Table(name = "solicitud_oficio_bien")
public class SolicitudOficioBien implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_oficio_bien")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coactivo")
    private Coactivo coactivo;

    @Column(name = "genera_oficio")
    private Boolean generaOficio;

    @OneToMany(mappedBy = "solicitudOficioBien")
    private List<SolicitudBienEntidad> solicitudBienEntidads;

    public SolicitudOficioBien() {
    }

    public Coactivo getCoactivo() {
        return coactivo;
    }

    public void setCoactivo(Coactivo coactivo) {
        this.coactivo = coactivo;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getGeneraOficio() {
        return generaOficio;
    }

    public void setGeneraOficio(Boolean generaOficio) {
        this.generaOficio = generaOficio;
    }

    public List<SolicitudBienEntidad> getSolicitudBienEntidads() {
        return solicitudBienEntidads;
    }

    public void setSolicitudBienEntidads(List<SolicitudBienEntidad> solicitudBienEntidads) {
        this.solicitudBienEntidads = solicitudBienEntidads;
    }

}