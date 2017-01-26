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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the prueba database table.
 * 
 */
@Entity
@Table(name = "prueba")
@NamedQuery(name = "Prueba.findAll", query = "SELECT p FROM Prueba p")
public class Prueba implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prueba")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_carga")
    private Date fechaCarga;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Column(name = "url_prueba")
    private String urlPrueba;

    @Column(name = "numero_archivo")
    private String numeroArchivo;

    // bi-directional many-to-one association to SolicitudPruebasBack
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud_pruebas_back")
    private SolicitudPruebasBack solicitudPruebasBack;

    public Prueba() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUrlPrueba() {
        return urlPrueba;
    }

    public void setUrlPrueba(String urlPrueba) {
        this.urlPrueba = urlPrueba;
    }

    public String getNumeroArchivo() {
        return numeroArchivo;
    }

    public void setNumeroArchivo(String numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

    public SolicitudPruebasBack getSolicitudPruebasBack() {
        return solicitudPruebasBack;
    }

    public void setSolicitudPruebasBack(SolicitudPruebasBack solicitudPruebasBack) {
        this.solicitudPruebasBack = solicitudPruebasBack;
    }

}