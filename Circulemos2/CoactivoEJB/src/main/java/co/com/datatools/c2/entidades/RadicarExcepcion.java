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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @author giovanni.velandia
 */
@Entity
@Table(name = "radicar_excepcion")
public class RadicarExcepcion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_radicar_excepcion")
    private Long idRadicarExcepcion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coactivo")
    private Coactivo coactivo;

    @Column(name = "observaciones")
    private String observaciones;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_excepcion")
    private Date fechaExcepcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fallo_excepcion")
    private Date fechaFalloExcepcion;

    @Column(name = "observaciones_fallo")
    private String observacionesFallo;

    @Column(name = "fallo_a_favor")
    private Boolean falloAFavor;

    @OneToMany(mappedBy = "radicarExcepcion")
    private List<ArchivoExcepcion> archivoExcepcions;

    public RadicarExcepcion() {
    }

    public Coactivo getCoactivo() {
        return coactivo;
    }

    public void setCoactivo(Coactivo coactivo) {
        this.coactivo = coactivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getIdRadicarExcepcion() {
        return idRadicarExcepcion;
    }

    public void setIdRadicarExcepcion(Long idRadicarExcepcion) {
        this.idRadicarExcepcion = idRadicarExcepcion;
    }

    public Date getFechaExcepcion() {
        return fechaExcepcion;
    }

    public void setFechaExcepcion(Date fechaExcepcion) {
        this.fechaExcepcion = fechaExcepcion;
    }

    public Date getFechaFalloExcepcion() {
        return fechaFalloExcepcion;
    }

    public void setFechaFalloExcepcion(Date fechaFalloExcepcion) {
        this.fechaFalloExcepcion = fechaFalloExcepcion;
    }

    public String getObservacionesFallo() {
        return observacionesFallo;
    }

    public void setObservacionesFallo(String observacionesFallo) {
        this.observacionesFallo = observacionesFallo;
    }

    public Boolean getFalloAFavor() {
        return falloAFavor;
    }

    public void setFalloAFavor(Boolean falloAFavor) {
        this.falloAFavor = falloAFavor;
    }

    public List<ArchivoExcepcion> getArchivoExcepcions() {
        return archivoExcepcions;
    }

    public void setArchivoExcepcions(List<ArchivoExcepcion> archivoExcepcions) {
        this.archivoExcepcions = archivoExcepcions;
    }

}