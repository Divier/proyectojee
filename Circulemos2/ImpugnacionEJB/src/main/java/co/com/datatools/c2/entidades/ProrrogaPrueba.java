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
 * The persistent class for the prorroga_prueba database table.
 * 
 */
@Entity
@Table(name = "prorroga_prueba")
@NamedQuery(name = "ProrrogaPrueba.findAll", query = "SELECT p FROM ProrrogaPrueba p")
public class ProrrogaPrueba implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prorroga_prueba")
    private Long id;

    @Column(name = "dias_prorroga")
    private Integer diasProrroga;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_prorroga")
    private Date fechaProrroga;

    @Column(name = "numero_prorroga")
    private Integer numeroProrroga;

    // bi-directional many-to-one association to SolicitudPruebasBack
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud_pruebas_back")
    private SolicitudPruebasBack solicitudPruebasBack;

    public ProrrogaPrueba() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiasProrroga() {
        return diasProrroga;
    }

    public void setDiasProrroga(Integer diasProrroga) {
        this.diasProrroga = diasProrroga;
    }

    public Date getFechaProrroga() {
        return fechaProrroga;
    }

    public void setFechaProrroga(Date fechaProrroga) {
        this.fechaProrroga = fechaProrroga;
    }

    public Integer getNumeroProrroga() {
        return numeroProrroga;
    }

    public void setNumeroProrroga(Integer numeroProrroga) {
        this.numeroProrroga = numeroProrroga;
    }

    public SolicitudPruebasBack getSolicitudPruebasBack() {
        return solicitudPruebasBack;
    }

    public void setSolicitudPruebasBack(SolicitudPruebasBack solicitudPruebasBack) {
        this.solicitudPruebasBack = solicitudPruebasBack;
    }

}