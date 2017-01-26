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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "archivo_excepcion")
public class ArchivoExcepcion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo_excepcion")
    private Integer id;

    @JoinColumn(name = "id_radicar_excepcion", referencedColumnName = "id_radicar_excepcion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RadicarExcepcion radicarExcepcion;

    @Basic(optional = false)
    @Column(name = "numero_archivo")
    private String numeroArchivo;

    @Basic(optional = false)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fallo_excepcion")
    private Boolean falloExcepcion;

    public ArchivoExcepcion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroArchivo() {
        return numeroArchivo;
    }

    public void setNumeroArchivo(String numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public RadicarExcepcion getRadicarExcepcion() {
        return radicarExcepcion;
    }

    public void setRadicarExcepcion(RadicarExcepcion radicarExcepcion) {
        this.radicarExcepcion = radicarExcepcion;
    }

    public Boolean getFalloExcepcion() {
        return falloExcepcion;
    }

    public void setFalloExcepcion(Boolean falloExcepcion) {
        this.falloExcepcion = falloExcepcion;
    }

}
