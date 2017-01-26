package co.com.datatools.c2.entidades.ubicabilidad;

import java.util.Date;
import java.util.List;

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

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.PersonaHistorico;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the telefono_persona database table.
 * 
 */
@Entity
@Table(name = "telefono_persona_historico")
@NamedQuery(name = "TelefonoPersonaHistorico.findAll", query = "SELECT t FROM TelefonoPersonaHistorico t")
public class TelefonoPersonaHistorico implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefono_persona_historico")
    private Long id;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "autorizado_notificacion")
    private Boolean autorizadoNotificacion;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Column(name = "numero_telefono")
    private String numeroTelefono;

    // bi-directional many-to-one association to TipoFuenteValidacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuente_validacion")
    private TipoFuenteInformacion tipoFuenteValidacion;

    @Column(name = "prioridad")
    private Integer prioridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_valida")
    private UsuarioPersona usuarioValida;

    @Column(name = "fecha_validacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaValidacion;

    // bi-directional many-to-many association to PersonaHistorico
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "perso_histo_telef_perso_histo",
            joinColumns = { @JoinColumn(name = "id_telefono_persona_historico") },
            inverseJoinColumns = { @JoinColumn(name = "id_persona_historico") })
    private List<PersonaHistorico> personaHistoricos;

    // bi-directional many-to-one association to ScoreUbicabilidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_score_ubicabilidad")
    private ScoreUbicabilidad scoreUbicabilidad;

    // bi-directional many-to-one association to TipoTelefono
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_telefono")
    private TipoTelefono tipoTelefono;

    // bi-directional many-to-one association to TipoFuenteInformacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_fuente_informacion")
    private TipoFuenteInformacion tipoFuenteInformacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_registro")
    private UsuarioPersona usuarioRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_telefono_persona")
    private TelefonoPersona telefonoPersona;

    public TelefonoPersonaHistorico() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroTelefono() {
        return this.numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public List<PersonaHistorico> getPersonaHistoricos() {
        return this.personaHistoricos;
    }

    public void setPersonaHistoricos(List<PersonaHistorico> personaHistoricos) {
        this.personaHistoricos = personaHistoricos;
    }

    public ScoreUbicabilidad getScoreUbicabilidad() {
        return this.scoreUbicabilidad;
    }

    public void setScoreUbicabilidad(ScoreUbicabilidad scoreUbicabilidad) {
        this.scoreUbicabilidad = scoreUbicabilidad;
    }

    public TipoTelefono getTipoTelefono() {
        return this.tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public TipoFuenteInformacion getTipoFuenteInformacion() {
        return this.tipoFuenteInformacion;
    }

    public void setTipoFuenteInformacion(TipoFuenteInformacion tipoFuenteInformacion) {
        this.tipoFuenteInformacion = tipoFuenteInformacion;
    }

    public TipoFuenteInformacion getTipoFuenteValidacion() {
        return tipoFuenteValidacion;
    }

    public void setTipoFuenteValidacion(TipoFuenteInformacion tipoFuenteValidacion) {
        this.tipoFuenteValidacion = tipoFuenteValidacion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public UsuarioPersona getUsuarioValida() {
        return usuarioValida;
    }

    public void setUsuarioValida(UsuarioPersona usuarioValida) {
        this.usuarioValida = usuarioValida;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public UsuarioPersona getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(UsuarioPersona usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Boolean getAutorizadoNotificacion() {
        return autorizadoNotificacion;
    }

    public void setAutorizadoNotificacion(Boolean autorizadoNotificacion) {
        this.autorizadoNotificacion = autorizadoNotificacion;
    }

    public TelefonoPersona getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(TelefonoPersona telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

}