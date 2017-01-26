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
 * The persistent class for the documento_proceso database table.
 * 
 */
@Entity
@Table(name = "motivacion_impugnacion")
@NamedQuery(name = "MotivacionImpugnacion.findAll", query = "SELECT d FROM MotivacionImpugnacion d")
public class MotivacionImpugnacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motivacion_impugnacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trazabilidad_proceso")
    private TrazabilidadProceso trazabilidadProceso;

    @Basic(optional = false)
    @Column(name = "descripcion_hechos")
    private String descripcionHechos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_peticion")
    private TipoPeticion tipoPeticion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origen_impugnacion")
    private OrigenImpugnacion origenImpugnacion;

    @Column(name = "numero_archivo_escrito")
    private String numeroArchivoEscrito;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_cargue_archivo")
    private Date fechaCargueArchivo;

    public MotivacionImpugnacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadProceso getTrazabilidadProceso() {
        return trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProceso trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public String getDescripcionHechos() {
        return descripcionHechos;
    }

    public void setDescripcionHechos(String descripcionHechos) {
        this.descripcionHechos = descripcionHechos;
    }

    public TipoPeticion getTipoPeticion() {
        return tipoPeticion;
    }

    public void setTipoPeticion(TipoPeticion tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

    public OrigenImpugnacion getOrigenImpugnacion() {
        return origenImpugnacion;
    }

    public void setOrigenImpugnacion(OrigenImpugnacion origenImpugnacion) {
        this.origenImpugnacion = origenImpugnacion;
    }

    public String getNumeroArchivoEscrito() {
        return numeroArchivoEscrito;
    }

    public void setNumeroArchivoEscrito(String numeroArchivoEscrito) {
        this.numeroArchivoEscrito = numeroArchivoEscrito;
    }

    public Date getFechaCargueArchivo() {
        return fechaCargueArchivo;
    }

    public void setFechaCargueArchivo(Date fechaCargueArchivo) {
        this.fechaCargueArchivo = fechaCargueArchivo;
    }
}