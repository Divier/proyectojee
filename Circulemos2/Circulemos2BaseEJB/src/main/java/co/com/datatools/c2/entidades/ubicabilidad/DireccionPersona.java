package co.com.datatools.c2.entidades.ubicabilidad;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.Direccion;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;
import co.com.datatools.c2.entidades.personas.ScoreUbicabilidad;
import co.com.datatools.c2.entidades.personas.TipoFuenteInformacion;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "direccion_persona")
@NamedQueries({ @NamedQuery(name = "DireccionPersona.findAll", query = "SELECT d FROM DireccionPersona d"),
        @NamedQuery(
                name = "DireccionPersona.findDireccionPersonaByPersona",
                query = "SELECT d FROM DireccionPersona d WHERE d.persona.id = :pIdPersona") })
public class DireccionPersona implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    /**
     * SELECT d FROM DireccionPersona d WHERE d.persona.id :pIdPersona
     */
    public static final String SQ_FIND_DIRECCION_BY_PERSONA = "DireccionPersona.findDireccionPersonaByPersona";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direccion_persona")
    private Long id;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Direccion direccion;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    @JoinColumn(name = "codigo_fuente_informacion", referencedColumnName = "codigo_fuente_informacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFuenteInformacion tipoFuenteInformacion;

    // bi-directional many-to-one association to ScoreUbicabilidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_score_ubicabilidad")
    private ScoreUbicabilidad scoreUbicabilidad;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "autorizado_notificacion")
    private Boolean autorizadoNotificacion;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_registro")
    private UsuarioPersona usuarioRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_actualiza")
    private UsuarioPersona usuarioActualiza;

    public DireccionPersona() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoFuenteInformacion getTipoFuenteInformacion() {
        return tipoFuenteInformacion;
    }

    public void setTipoFuenteInformacion(TipoFuenteInformacion tipoFuenteInformacion) {
        this.tipoFuenteInformacion = tipoFuenteInformacion;
    }

    public ScoreUbicabilidad getScoreUbicabilidad() {
        return scoreUbicabilidad;
    }

    public void setScoreUbicabilidad(ScoreUbicabilidad scoreUbicabilidad) {
        this.scoreUbicabilidad = scoreUbicabilidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    public UsuarioPersona getUsuarioActualiza() {
        return usuarioActualiza;
    }

    public void setUsuarioActualiza(UsuarioPersona usuarioActualiza) {
        this.usuarioActualiza = usuarioActualiza;
    }

    public Boolean getAutorizadoNotificacion() {
        return autorizadoNotificacion;
    }

    public void setAutorizadoNotificacion(Boolean autorizadoNotificacion) {
        this.autorizadoNotificacion = autorizadoNotificacion;
    }

}
