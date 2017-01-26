package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "evidencia")
@NamedQueries({ @NamedQuery(name = "Evidencia.findAll", query = "SELECT e FROM Evidencia e") })
public class Evidencia implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evidencia")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "fecha_evidencia")
    @Temporal(TemporalType.DATE)
    private Date fechaEvidencia;

    @Column(name = "id_documento")
    private String idDocumento;

    @JoinColumn(name = "cicomparendo", referencedColumnName = "cicomparendo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Comparendo comparendo;

    @JoinColumn(name = "codigo_tipo_evidencia", referencedColumnName = "codigo_tipo_evidencia")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoEvidencia tipoEvidencia;

    @Column(name = "url")
    private String url;

    @Column(name = "nombre_evidencia")
    private String nombre_evidencia;

    public Evidencia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEvidencia() {
        return fechaEvidencia;
    }

    public void setFechaEvidencia(Date fechaEvidencia) {
        this.fechaEvidencia = fechaEvidencia;
    }

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public TipoEvidencia getTipoEvidencia() {
        return tipoEvidencia;
    }

    public void setTipoEvidencia(TipoEvidencia tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre_evidencia() {
        return nombre_evidencia;
    }

    public void setNombre_evidencia(String nombre_evidencia) {
        this.nombre_evidencia = nombre_evidencia;
    }

}
