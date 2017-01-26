package co.com.datatools.c2.entidades;

import java.io.Serializable;
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

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "historico_agente")
@NamedQuery(name = "HistoricoAgente.findAll", query = "SELECT a FROM HistoricoAgente a")
public class HistoricoAgente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_agente")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agente")
    private Agente agente;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin_vigencia")
    private Date fechaFinVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio_vigencia")
    private Date fechaInicioVigencia;

    @Column(name = "id_entidad_agente_transito")
    private Integer entidadAgenteTransito;

    @Column(name = "id_persona")
    private Long persona;

    @Column(name = "placa_agente_transito")
    private String placa;

    @Column(name = "codigo_organismo")
    private Integer organismoTransito;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @Column(name = "id_usuario_actualizacion")
    private Integer usuarioActualizacion;

    @Column(name = "id_motivo_vigencia_agente")
    private Integer motivoVigenciaAgente;

    public HistoricoAgente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Integer getEntidadAgenteTransito() {
        return entidadAgenteTransito;
    }

    public void setEntidadAgenteTransito(Integer entidadAgenteTransito) {
        this.entidadAgenteTransito = entidadAgenteTransito;
    }

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(Integer usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Integer getMotivoVigenciaAgente() {
        return motivoVigenciaAgente;
    }

    public void setMotivoVigenciaAgente(Integer motivoVigenciaAgente) {
        this.motivoVigenciaAgente = motivoVigenciaAgente;
    }

}