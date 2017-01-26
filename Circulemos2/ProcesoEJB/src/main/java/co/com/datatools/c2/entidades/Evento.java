package co.com.datatools.c2.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.eventos.ConfiguracionHorario;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the cargue_archivo database table.
 * 
 */
@Entity
@Table(name = "evento")
@NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
public class Evento implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id;

    @Column(name = "nombre_evento")
    private String nombreEvento;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "numero_referencia")
    private String numeroReferencia;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;

    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_proceso")
    private TipoProcesoEvento tipoProcesoEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_evento")
    private TipoEvento tipoEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuaro")
    private UsuarioPersona usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_evento")
    private EstadoEvento estadoEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_horario")
    private ConfiguracionHorario configuracionHorario;

    public void setConfiguracionHorario(ConfiguracionHorario configuracionHorario) {
        this.configuracionHorario = configuracionHorario;
    }

    @Column(name = "observacion")
    private String observacion;

    public Long getId() {
        return id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public UsuarioPersona getUsuario() {
        return usuario;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public ConfiguracionHorario getConfiguracionHorario() {
        return configuracionHorario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public void setUsuario(UsuarioPersona usuario) {
        this.usuario = usuario;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    // public void setConfiguracionHorario(ConfiguracionHorario configuracionHorario) {
    // this.configuracionHorario = configuracionHorario;
    // }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public TipoProcesoEvento getTipoProcesoEvento() {
        return tipoProcesoEvento;
    }

    public void setTipoProcesoEvento(TipoProcesoEvento tipoProcesoEvento) {
        this.tipoProcesoEvento = tipoProcesoEvento;
    }

}