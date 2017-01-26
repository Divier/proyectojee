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

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "detalle_notificacion")
public class DetalleNotificacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_notificacion")
    private Long id;

    @JoinColumn(name = "id_log_envio_correo", referencedColumnName = "id_log_envio_correo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LogEnvioCorreo logEnvioCorreo;

    @JoinColumn(name = "id_log_ejecucion_ws", referencedColumnName = "id_log_ejecucion_ws")
    @ManyToOne(fetch = FetchType.LAZY)
    private LogEjecucionWS logEjecucionWS;

    @JoinColumn(name = "id_notificacion", referencedColumnName = "id_notificacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Notificacion notificacion;

    @Column(name = "codigo_seguimiento_externo")
    private String codigoSeguimientoExt;

    @Basic(optional = false)
    @Column(name = "codigo_seguimiento_interno")
    private String codigoSeguimientoInt;

    @Basic(optional = false)
    @Column(name = "enviado_externo")
    private Integer enviadoExt;

    @Column(name = "estado_externo")
    private String estadoExt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_envio_tercero")
    private Date fechaEnvioTercero;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioPersona usuario;

    public DetalleNotificacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogEnvioCorreo getLogEnvioCorreo() {
        return logEnvioCorreo;
    }

    public void setLogEnvioCorreo(LogEnvioCorreo logEnvioCorreo) {
        this.logEnvioCorreo = logEnvioCorreo;
    }

    public LogEjecucionWS getLogEjecucionWS() {
        return logEjecucionWS;
    }

    public void setLogEjecucionWS(LogEjecucionWS logEjecucionWS) {
        this.logEjecucionWS = logEjecucionWS;
    }

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    public String getCodigoSeguimientoExt() {
        return codigoSeguimientoExt;
    }

    public void setCodigoSeguimientoExt(String codigoSeguimientoExt) {
        this.codigoSeguimientoExt = codigoSeguimientoExt;
    }

    public String getCodigoSeguimientoInt() {
        return codigoSeguimientoInt;
    }

    public void setCodigoSeguimientoInt(String codigoSeguimientoInt) {
        this.codigoSeguimientoInt = codigoSeguimientoInt;
    }

    public Integer getEnviadoExt() {
        return enviadoExt;
    }

    public void setEnviadoExt(Integer enviadoExt) {
        this.enviadoExt = enviadoExt;
    }

    public String getEstadoExt() {
        return estadoExt;
    }

    public void setEstadoExt(String estadoExt) {
        this.estadoExt = estadoExt;
    }

    public Date getFechaEnvioTercero() {
        return fechaEnvioTercero;
    }

    public void setFechaEnvioTercero(Date fechaEnvioTercero) {
        this.fechaEnvioTercero = fechaEnvioTercero;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public UsuarioPersona getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersona usuario) {
        this.usuario = usuario;
    }
}