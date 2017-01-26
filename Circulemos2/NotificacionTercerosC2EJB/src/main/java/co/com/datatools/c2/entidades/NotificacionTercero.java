package co.com.datatools.c2.entidades;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the notificacion_tercero database table.
 * 
 */
@Entity
@Table(name = "notificacion_tercero")
@NamedQuery(name = "NotificacionTercero.findAll", query = "SELECT n FROM NotificacionTercero n")
public class NotificacionTercero implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_tercero")
    private Long id;

    @Basic(optional = false)
    @Column(name = "cicomparendo")
    private Long cicomparendo;

    @Basic(optional = false)
    @Column(name = "codigo_organismo")
    private Integer codigoOrganismo;

    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_recibo")
    private Date fechaRecibo;

    // bi-directional many-to-one association to EnvioNotificacionTercero
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notificacionTercero", fetch = FetchType.LAZY)
    private List<EnvioNotificacionTercero> envioNotificacionTerceros;

    // bi-directional many-to-one association to Tercero
    @ManyToOne
    @JoinColumn(name = "id_tercero")
    private Tercero tercero;

    // bi-directional many-to-one association to OrigenNotificacionTercero
    @ManyToOne
    @JoinColumn(name = "id_origen_notificacion_tercero")
    private OrigenNotificacionTercero origenNotificacionTercero;

    public NotificacionTercero() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCicomparendo() {
        return this.cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public Integer getCodigoOrganismo() {
        return this.codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaRecibo() {
        return this.fechaRecibo;
    }

    public void setFechaRecibo(Date fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public List<EnvioNotificacionTercero> getEnvioNotificacionTerceros() {
        return this.envioNotificacionTerceros;
    }

    public void setEnvioNotificacionTerceros(List<EnvioNotificacionTercero> envioNotificacionTerceros) {
        this.envioNotificacionTerceros = envioNotificacionTerceros;
    }

    public Tercero getTercero() {
        return this.tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public OrigenNotificacionTercero getOrigenNotificacionTercero() {
        return this.origenNotificacionTercero;
    }

    public void setOrigenNotificacionTercero(OrigenNotificacionTercero origenNotificacionTercero) {
        this.origenNotificacionTercero = origenNotificacionTercero;
    }

}