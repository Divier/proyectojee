package co.com.datatools.c2.entidades;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

@Entity
@Table(name = "log_envio_correo")
public class LogEnvioCorreo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_log_envio_correo")
    private Long id;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @JoinColumn(name = "codigo_tipo_email", referencedColumnName = "codigo_tipo_email")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoEmail tipoEmail;

    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConfiguracionEmail configuracionEmail;

    @Basic(optional = false)
    @Column(name = "asunto_email")
    private String asuntoEmail;

    @Column(name = "cuerpo_email")
    private String cuerpoEmail;

    @Basic(optional = false)
    @Column(name = "adjunto")
    private Boolean adjunto;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioPersona usuario;

    @Basic(optional = false)
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @OneToMany(mappedBy = "logEnvioCorreo", fetch = FetchType.LAZY)
    private List<LogDireccionEnvioCorreo> logDireccionEnvioCorreo;

    @Column(name = "tabla_solicitud")
    private String tablaSolicitud;

    @Column(name = "id_tabla_solicitud")
    private Long idTablaSolicitud;

    @OneToMany(mappedBy = "logEnvioCorreo", fetch = FetchType.LAZY)
    private List<LogAdjuntoEnvioCorreo> logAdjuntoEnvioCorreo;

    public LogEnvioCorreo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public TipoEmail getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(TipoEmail tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public ConfiguracionEmail getConfiguracionEmail() {
        return configuracionEmail;
    }

    public void setConfiguracionEmail(ConfiguracionEmail configuracionEmail) {
        this.configuracionEmail = configuracionEmail;
    }

    public String getAsuntoEmail() {
        return asuntoEmail;
    }

    public void setAsuntoEmail(String asuntoEmail) {
        this.asuntoEmail = asuntoEmail;
    }

    public String getCuerpoEmail() {
        return cuerpoEmail;
    }

    public void setCuerpoEmail(String cuerpoEmail) {
        this.cuerpoEmail = cuerpoEmail;
    }

    public Boolean getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(Boolean adjunto) {
        this.adjunto = adjunto;
    }

    public UsuarioPersona getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersona usuario) {
        this.usuario = usuario;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public List<LogDireccionEnvioCorreo> getLogDireccionEnvioCorreo() {
        return logDireccionEnvioCorreo == null ? new ArrayList<LogDireccionEnvioCorreo>() : logDireccionEnvioCorreo;
    }

    public void setLogDireccionEnvioCorreo(List<LogDireccionEnvioCorreo> logDireccionEnvioCorreo) {
        this.logDireccionEnvioCorreo = logDireccionEnvioCorreo;
    }

    public String getTablaSolicitud() {
        return tablaSolicitud;
    }

    public void setTablaSolicitud(String tablaSolicitud) {
        this.tablaSolicitud = tablaSolicitud;
    }

    public Long getIdTablaSolicitud() {
        return idTablaSolicitud;
    }

    public void setIdTablaSolicitud(Long idTablaSolicitud) {
        this.idTablaSolicitud = idTablaSolicitud;
    }

    public List<LogAdjuntoEnvioCorreo> getLogAdjuntoEnvioCorreo() {
        return logAdjuntoEnvioCorreo == null ? new ArrayList<LogAdjuntoEnvioCorreo>() : logAdjuntoEnvioCorreo;
    }

    public void setLogAdjuntoEnvioCorreo(List<LogAdjuntoEnvioCorreo> logAdjuntoEnvioCorreo) {
        this.logAdjuntoEnvioCorreo = logAdjuntoEnvioCorreo;
    }

}