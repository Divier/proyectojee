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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;
/**
 * The persistent class for the notificacion_aviso database table.
 * 
 */
@Entity
@Table(name = "notificacion_aviso")
@NamedQuery(name = "NotificacionAviso.findAll", query = "SELECT n FROM NotificacionAviso n")
public class NotificacionAviso implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_aviso")
    private Long id;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_ejecucion_notificacion")
    private Date fechaEjecucionNotificacion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;

    @Basic(optional = false)
    @Column(name = "notificado")
    private Boolean notificado;

    @Column(name = "id_archivo_generado")
    private Long idArchivoGenerado;

    @Basic(optional = false)
    @Column(name = "cantidad_comparendos")
    private Integer cantidadComparendos;

    @Basic(optional = false)
    @Column(name = "consecutivo")
    private String consecutivo;

    // bi-directional many-to-many association to Comparendo
    @ManyToMany
    @JoinTable(name = "notificacion_aviso_comparendo", joinColumns = { @JoinColumn(
            name = "id_notificacion_aviso",
            referencedColumnName = "id_notificacion_aviso") }, inverseJoinColumns = { @JoinColumn(
            name = "cicomparendo",
            referencedColumnName = "cicomparendo") })
    private List<Comparendo> comparendos;

    public NotificacionAviso() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganismoTransito getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaEjecucionNotificacion() {
        return this.fechaEjecucionNotificacion;
    }

    public void setFechaEjecucionNotificacion(Date fechaEjecucionNotificacion) {
        this.fechaEjecucionNotificacion = fechaEjecucionNotificacion;
    }

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Boolean getNotificado() {
        return this.notificado;
    }

    public void setNotificado(Boolean notificado) {
        this.notificado = notificado;
    }

    public Long getIdArchivoGenerado() {
        return this.idArchivoGenerado;
    }

    public void setIdArchivoGenerado(Long idArchivoGenerado) {
        this.idArchivoGenerado = idArchivoGenerado;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getCantidadComparendos() {
        return this.cantidadComparendos;
    }

    public void setCantidadComparendos(Integer cantidadComparendos) {
        this.cantidadComparendos = cantidadComparendos;
    }

    public List<Comparendo> getComparendos() {
        return this.comparendos;
    }

    public void setComparendos(List<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }

}