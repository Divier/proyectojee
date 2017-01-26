package co.com.datatools.seguridad.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Registro de solicitudes de cambio de password realizadas por un usuario
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "solicitud_cambio_pass")
@NamedQueries({
        @NamedQuery(name = "SolicitudCambioPass.findAll", query = "SELECT s FROM SolicitudCambioPassword s"),
        @NamedQuery(
                name = "SolicitudCambioPass.usuarioSolicitud",
                query = "SELECT s.usuario FROM SolicitudCambioPassword s WHERE s.codigoVerificacion=:codigo"),
        @NamedQuery(
                name = "SolicitudCambioPass.findByCodigo",
                query = "SELECT s FROM SolicitudCambioPassword s WHERE s.codigoVerificacion=:codigo"),
        @NamedQuery(
                name = "SolicitudCambioPass.findActivaByUsuario",
                query = "SELECT s FROM SolicitudCambioPassword s WHERE s.usuario.idUsuario=:idUsuario AND s.solicitudActiva=:activo"),
        @NamedQuery(
                name = "SolicitudCambioPass.findByUsuario",
                query = "SELECT s FROM SolicitudCambioPassword s WHERE s.usuario.idUsuario=:idUsuario") })
public class SolicitudCambioPassword implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_SOLICITUD_BY_USUARIO = "SolicitudCambioPass.findByUsuario";
    public static final String SQ_SOLICITUD_ACTIVA_BY_USUARIO = "SolicitudCambioPass.findActivaByUsuario";
    public static final String SQ_SOLICITUD_BY_CODIGO = "SolicitudCambioPass.findByCodigo";
    public static final String SQ_USUARIO_SOLICITUD = "SolicitudCambioPass.usuarioSolicitud";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_cambio_pass")
    private Long idSolicitudCambioPass;

    @Column(name = "codigo_verificacion")
    private String codigoVerificacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_cierre_solicitud")
    private Date fechaCierreSolicitud;

    @Column(name = "solicitud_activa")
    private boolean solicitudActiva;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public SolicitudCambioPassword() {
    }

    public Long getIdSolicitudCambioPass() {
        return this.idSolicitudCambioPass;
    }

    public void setIdSolicitudCambioPass(Long idSolicitudCambioPass) {
        this.idSolicitudCambioPass = idSolicitudCambioPass;
    }

    public String getCodigoVerificacion() {
        return this.codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public Date getFechaCierreSolicitud() {
        return this.fechaCierreSolicitud;
    }

    public void setFechaCierreSolicitud(Date fechaCierreSolicitud) {
        this.fechaCierreSolicitud = fechaCierreSolicitud;
    }

    public Date getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public boolean getSolicitudActiva() {
        return this.solicitudActiva;
    }

    public void setSolicitudActiva(boolean solicitudActiva) {
        this.solicitudActiva = solicitudActiva;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}