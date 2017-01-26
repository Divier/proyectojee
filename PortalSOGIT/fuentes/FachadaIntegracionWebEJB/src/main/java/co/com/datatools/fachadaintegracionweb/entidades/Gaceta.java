package co.com.datatools.fachadaintegracionweb.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "gaceta")
@NamedQueries({ @NamedQuery(name = "Gaceta.findAll", query = "SELECT e FROM Gaceta e") })
public class Gaceta implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gaceta")
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_publicacion")
    private Date fechaPublicacion;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_periodo_inicio")
    private Date fechaPeriodoInicio;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_periodo_fin")
    private Date fechaPeriodoFin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gaceta", fetch = FetchType.LAZY)
    private List<DetalleGaceta> detallesGaceta;

    public Gaceta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public List<DetalleGaceta> getDetallesGaceta() {
        return detallesGaceta;
    }

    public void setDetallesGaceta(List<DetalleGaceta> detallesGaceta) {
        this.detallesGaceta = detallesGaceta;
    }

    public Date getFechaPeriodoInicio() {
        return fechaPeriodoInicio;
    }

    public void setFechaPeriodoInicio(Date fechaPeriodoInicio) {
        this.fechaPeriodoInicio = fechaPeriodoInicio;
    }

    public Date getFechaPeriodoFin() {
        return fechaPeriodoFin;
    }

    public void setFechaPeriodoFin(Date fechaPeriodoFin) {
        this.fechaPeriodoFin = fechaPeriodoFin;
    }

}
