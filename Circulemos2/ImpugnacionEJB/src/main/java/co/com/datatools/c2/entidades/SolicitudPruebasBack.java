package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the solicitud_pruebas_back database table.
 * 
 */
@Entity
@Table(name = "solicitud_pruebas_back")
@NamedQuery(name = "SolicitudPruebasBack.findAll", query = "SELECT s FROM SolicitudPruebasBack s")
public class SolicitudPruebasBack implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_pruebas_back")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_limite")
    private Date fechaLimite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_prueba")
    private TipoPrueba tipoPrueba;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trazabilidad_proceso")
    private TrazabilidadProceso trazabilidadProceso;

    // bi-directional many-to-one association to Prueba
    @OneToMany(mappedBy = "solicitudPruebasBack")
    private List<Prueba> pruebas;

    // bi-directional many-to-one association to ImpulsoPrueba
    @OneToMany(mappedBy = "solicitudPruebasBack")
    private List<ImpulsoPrueba> impulsoPruebas;

    // bi-directional many-to-one association to ProrrogaPrueba
    @OneToMany(mappedBy = "solicitudPruebasBack")
    private List<ProrrogaPrueba> prorrogaPruebas;

    // bi-directional many-to-one association to CaracteristicaPrueba
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caracteristica_prueba")
    private CaracteristicaPrueba caracteristicaPrueba;

    // bi-directional many-to-one association to OrigenPrueba
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origen_prueba")
    private OrigenPrueba origenPrueba;

    // @Basic(optional = false)
    // @Column(name = "para")
    // private String para;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_destino_prueba")
    private TipoDestinoPruebaImpug tipoDestinoPruebaImpug;

    @Column(name = "destino_prueba_otro")
    private String destinoPruebaOtro;

    @Column(name = "consecutivo_documento")
    private String consecutivoDocumento;

    public SolicitudPruebasBack() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public TrazabilidadProceso getTrazabilidadProceso() {
        return trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProceso trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public List<Prueba> getPruebas() {
        return pruebas;
    }

    public void setPruebas(List<Prueba> pruebas) {
        this.pruebas = pruebas;
    }

    public List<ImpulsoPrueba> getImpulsoPruebas() {
        return impulsoPruebas;
    }

    public void setImpulsoPruebas(List<ImpulsoPrueba> impulsoPruebas) {
        this.impulsoPruebas = impulsoPruebas;
    }

    public List<ProrrogaPrueba> getProrrogaPruebas() {
        return prorrogaPruebas;
    }

    public void setProrrogaPruebas(List<ProrrogaPrueba> prorrogaPruebas) {
        this.prorrogaPruebas = prorrogaPruebas;
    }

    public CaracteristicaPrueba getCaracteristicaPrueba() {
        return caracteristicaPrueba;
    }

    public void setCaracteristicaPrueba(CaracteristicaPrueba caracteristicaPrueba) {
        this.caracteristicaPrueba = caracteristicaPrueba;
    }

    public OrigenPrueba getOrigenPrueba() {
        return origenPrueba;
    }

    public void setOrigenPrueba(OrigenPrueba origenPrueba) {
        this.origenPrueba = origenPrueba;
    }

    public TipoDestinoPruebaImpug getTipoDestinoPruebaImpug() {
        return tipoDestinoPruebaImpug;
    }

    public void setTipoDestinoPruebaImpug(TipoDestinoPruebaImpug tipoDestinoPruebaImpug) {
        this.tipoDestinoPruebaImpug = tipoDestinoPruebaImpug;
    }

    public String getDestinoPruebaOtro() {
        return destinoPruebaOtro;
    }

    public void setDestinoPruebaOtro(String destinoPruebaOtro) {
        this.destinoPruebaOtro = destinoPruebaOtro;
    }

    public String getConsecutivoDocumento() {
        return consecutivoDocumento;
    }

    public void setConsecutivoDocumento(String consecutivoDocumento) {
        this.consecutivoDocumento = consecutivoDocumento;
    }

    // public String getPara() {
    // return para;
    // }
    //
    // public void setPara(String para) {
    // this.para = para;
    // }
}