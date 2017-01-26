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
@Table(name = "evidencia_notificacion")
public class EvidenciaNotificacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evidencia_notificacion")
    private Long id;

    @JoinColumn(name = "id_detalle_notificacion", referencedColumnName = "id_detalle_notificacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleNotificacion detalleNotificacion;

    @Basic(optional = false)
    @Column(name = "codigo_evidencia")
    private String codigoEvidencia;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioPersona usuario;

    @Basic(optional = false)
    @Column(name = "id_evidencia_externo")
    private String idEvidenciaExterno;

    @Basic(optional = false)
    @Column(name = "tipo_evidencia")
    private Integer tipoEvidencia;

    public EvidenciaNotificacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleNotificacion getDetalleNotificacion() {
        return detalleNotificacion;
    }

    public void setDetalleNotificacion(DetalleNotificacion detalleNotificacion) {
        this.detalleNotificacion = detalleNotificacion;
    }

    public String getCodigoEvidencia() {
        return codigoEvidencia;
    }

    public void setCodigoEvidencia(String codigoEvidencia) {
        this.codigoEvidencia = codigoEvidencia;
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

    public String getIdEvidenciaExterno() {
        return idEvidenciaExterno;
    }

    public void setIdEvidenciaExterno(String idEvidenciaExterno) {
        this.idEvidenciaExterno = idEvidenciaExterno;
    }

    public Integer getTipoEvidencia() {
        return tipoEvidencia;
    }

    public void setTipoEvidencia(Integer tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }
}