package co.com.datatools.c2.entidades;

import java.io.Serializable;
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

/**
 * The persistent class for the accion_impugnacion_back database table.
 * 
 */
@Entity
@Table(name = "accion_impugnacion_back")
@NamedQuery(name = "AccionImpugnacionBack.findAll", query = "SELECT a FROM AccionImpugnacionBack a")
public class AccionImpugnacionBack implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accion_impugnacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trazabilidad_proceso")
    private TrazabilidadProceso trazabilidadProceso;

    // bi-directional many-to-one association to TipoAccionBack
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_accion_back")
    private TipoAccionBack tipoAccionBack;

    // bi-directional many-to-one association to CaracteristicaPrueba
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caracteristica_prueba")
    private CaracteristicaPrueba caracteristicaPrueba;

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_accion")
    private Date fechaAccion;

    public AccionImpugnacionBack() {
    }

    public TipoAccionBack getTipoAccionBack() {
        return this.tipoAccionBack;
    }

    public void setTipoAccionBack(TipoAccionBack tipoAccionBack) {
        this.tipoAccionBack = tipoAccionBack;
    }

    public CaracteristicaPrueba getCaracteristicaPrueba() {
        return this.caracteristicaPrueba;
    }

    public void setCaracteristicaPrueba(CaracteristicaPrueba caracteristicaPrueba) {
        this.caracteristicaPrueba = caracteristicaPrueba;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

}