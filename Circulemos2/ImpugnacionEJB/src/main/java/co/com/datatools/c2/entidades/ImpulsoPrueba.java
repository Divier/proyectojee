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
 * The persistent class for the impulso_pruebas database table.
 * 
 */
@Entity
@Table(name = "impulso_pruebas")
@NamedQuery(name = "ImpulsoPrueba.findAll", query = "SELECT i FROM ImpulsoPrueba i")
public class ImpulsoPrueba implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_impulso_pruebas")
    private Long id;
    @Column(name = "contenido_impulso")
    private String contenidoImpulso;

    @Column(name = "definitivo")
    private Boolean definitivo;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    @Column(name = "numero_documento")
    private Long numeroDocumento;

    // bi-directional many-to-one association to SolicitudPruebasBack
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud_pruebas_back")
    private SolicitudPruebasBack solicitudPruebasBack;

    public ImpulsoPrueba() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenidoImpulso() {
        return contenidoImpulso;
    }

    public void setContenidoImpulso(String contenidoImpulso) {
        this.contenidoImpulso = contenidoImpulso;
    }

    public Boolean getDefinitivo() {
        return definitivo;
    }

    public void setDefinitivo(Boolean definitivo) {
        this.definitivo = definitivo;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public SolicitudPruebasBack getSolicitudPruebasBack() {
        return solicitudPruebasBack;
    }

    public void setSolicitudPruebasBack(SolicitudPruebasBack solicitudPruebasBack) {
        this.solicitudPruebasBack = solicitudPruebasBack;
    }

}