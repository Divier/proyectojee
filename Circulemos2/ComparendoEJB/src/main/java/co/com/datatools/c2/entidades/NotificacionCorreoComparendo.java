package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * The persistent class for the notificacion_correo_comparendo database table.
 * 
 */
@Entity
@Table(name = "notificacion_correo_comparendo")
@NamedQuery(name = "NotificacionCorreoComparendo.findAll", query = "SELECT n FROM NotificacionCorreoComparendo n")
public class NotificacionCorreoComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_correo_compa")
    private Long id;

    @Column(name = "id_documento_notificacion")
    private Long idDocumentoNotificacion;

    @Column(name = "notificado")
    private Boolean notificado;

    @Column(name = "observacion")
    private String observacion;

    // bi-directional many-to-one association to NotificacionCorreo
    @ManyToOne
    @JoinColumn(name = "id_notificacion_correo")
    private NotificacionCorreo notificacionCorreo;

    // bi-directional many-to-one association to Comparendo
    @ManyToOne
    @JoinColumn(name = "cicomparendo")
    private Comparendo comparendo;

    @Column(name = "consecutivo")
    private String consecutivo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_cargue")
    private Date fechaCargue;

    public NotificacionCorreoComparendo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDocumentoNotificacion() {
        return this.idDocumentoNotificacion;
    }

    public void setIdDocumentoNotificacion(Long idDocumentoNotificacion) {
        this.idDocumentoNotificacion = idDocumentoNotificacion;
    }

    public Boolean getNotificado() {
        return this.notificado;
    }

    public void setNotificado(Boolean notificado) {
        this.notificado = notificado;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public NotificacionCorreo getNotificacionCorreo() {
        return this.notificacionCorreo;
    }

    public void setNotificacionCorreo(NotificacionCorreo notificacionCorreo) {
        this.notificacionCorreo = notificacionCorreo;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFechaCargue() {
        return this.fechaCargue;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

}
