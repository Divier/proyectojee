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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the notificacion_correo database table.
 * 
 */
@Entity
@Table(name = "notificacion_correo")
@NamedQuery(name = "NotificacionCorreo.findAll", query = "SELECT n FROM NotificacionCorreo n")
public class NotificacionCorreo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_correo")
    private Long id;

    @Basic(optional = false)
    @Column(name = "consecutivo")
    private String consecutivo;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    @Basic(optional = false)
    @Column(name = "id_archivo_generado")
    private String idArchivoGenerado;

    @Basic(optional = false)
    @Column(name = "notificacion_generada")
    private Boolean notificacionGenerada;

    // bi-directional many-to-one association to NotificacionCorreoComparendo
    @OneToMany(mappedBy = "notificacionCorreo", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    private List<NotificacionCorreoComparendo> notificacionCorreoComparendos;

    public NotificacionCorreo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getIdArchivoGenerado() {
        return this.idArchivoGenerado;
    }

    public void setIdArchivoGenerado(String idArchivoGenerado) {
        this.idArchivoGenerado = idArchivoGenerado;
    }

    public Boolean getNotificacionGenerada() {
        return this.notificacionGenerada;
    }

    public void setNotificacionGenerada(Boolean notificacionGenerada) {
        this.notificacionGenerada = notificacionGenerada;
    }

    public List<NotificacionCorreoComparendo> getNotificacionCorreoComparendos() {
        return this.notificacionCorreoComparendos;
    }

    public void setNotificacionCorreoComparendos(List<NotificacionCorreoComparendo> notificacionCorreoComparendos) {
        this.notificacionCorreoComparendos = notificacionCorreoComparendos;
    }

}