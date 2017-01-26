package co.com.datatools.c2.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.c2.entidades.personas.Persona;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "agente")
@NamedQuery(name = "Agente.findAll", query = "SELECT a FROM Agente a")
public class Agente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agente")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin_vigencia")
    private Date fechaFinVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio_vigencia")
    private Date fechaInicioVigencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad_agente_transito")
    private TipoEntidadAgenteTransito entidadAgenteTransito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(name = "placa_agente_transito")
    private String placa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_organismo")
    private OrganismoTransito organismoTransito;

    @OneToMany(mappedBy = "agente", fetch = FetchType.LAZY)
    private List<ComparendoAgente> comparendoAgentes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_actualizacion")
    private UsuarioPersona usuarioActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motivo_vigencia_agente")
    private MotivoVigenciaAgente motivoVigenciaAgente;

    @Column(name = "numero_firma")
    private Long numeroFirma;

    public Agente() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaFinVigencia() {
        return this.fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public Date getFechaInicioVigencia() {
        return this.fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public TipoEntidadAgenteTransito getEntidadAgenteTransito() {
        return this.entidadAgenteTransito;
    }

    public void setEntidadAgenteTransito(TipoEntidadAgenteTransito entidadAgenteTransito) {
        this.entidadAgenteTransito = entidadAgenteTransito;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public OrganismoTransito getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public List<ComparendoAgente> getComparendoAgentes() {
        return this.comparendoAgentes;
    }

    public void setComparendoAgentes(List<ComparendoAgente> comparendoAgentes) {
        this.comparendoAgentes = comparendoAgentes;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UsuarioPersona getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(UsuarioPersona usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public MotivoVigenciaAgente getMotivoVigenciaAgente() {
        return motivoVigenciaAgente;
    }

    public void setMotivoVigenciaAgente(MotivoVigenciaAgente motivoVigenciaAgente) {
        this.motivoVigenciaAgente = motivoVigenciaAgente;
    }

    public Long getNumeroFirma() {
        return numeroFirma;
    }

    public void setNumeroFirma(Long numeroFirma) {
        this.numeroFirma = numeroFirma;
    }

}