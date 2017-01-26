package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Basic;
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
 * The persistent class for the envio_notificacion_tercero database table.
 * 
 */
@Entity
@Table(name = "envio_notificacion_tercero")
@NamedQuery(name = "EnvioNotificacionTercero.findAll", query = "SELECT e FROM EnvioNotificacionTercero e")
public class EnvioNotificacionTercero implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio_notificacion_tercero")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;

    @Basic(optional = false)
    @Column(name = "resultado")
    private Boolean resultado;

    // bi-directional many-to-one association to NotificacionTercero
    @ManyToOne
    @JoinColumn(name = "id_notificacion_tercero")
    private NotificacionTercero notificacionTercero;

    public EnvioNotificacionTercero() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaNotificacion() {
        return this.fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Boolean getResultado() {
        return this.resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public NotificacionTercero getNotificacionTercero() {
        return this.notificacionTercero;
    }

    public void setNotificacionTercero(NotificacionTercero notificacionTercero) {
        this.notificacionTercero = notificacionTercero;
    }

}