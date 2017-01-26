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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "trazabilidad_comparendo")
@NamedQueries({
        @NamedQuery(name = "TrazabilidadComparendo.findAll", query = "SELECT t FROM TrazabilidadComparendo t") })
public class TrazabilidadComparendo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_trazabilidad_comparendo")
    private Long id;

    @Basic(optional = false)
    @Column(name = "fecha_ejecucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEjecucion;

    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Column(name = "usuario_externo")
    private String usuarioExterno;

    @Column(name = "numero_acto_administrativo")
    private String numeroActoAdministrativo;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioPersona usuario;

    @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Actividad actividad;

    @JoinColumn(name = "cicomparendo", referencedColumnName = "cicomparendo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Comparendo comparendo;

    @JoinColumn(name = "id_estado_comparendo", referencedColumnName = "id_estado_comparendo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoComparendo estadoComparendo;

    public TrazabilidadComparendo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuarioExterno() {
        return usuarioExterno;
    }

    public void setUsuarioExterno(String usuarioExterno) {
        this.usuarioExterno = usuarioExterno;
    }

    public String getNumeroActoAdministrativo() {
        return this.numeroActoAdministrativo;
    }

    public void setNumeroActoAdministrativo(String numeroActoAdministrativo) {
        this.numeroActoAdministrativo = numeroActoAdministrativo;
    }

    public UsuarioPersona getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPersona usuario) {
        this.usuario = usuario;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Comparendo getComparendo() {
        return this.comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public EstadoComparendo getEstadoComparendo() {
        return estadoComparendo;
    }

    public void setEstadoComparendo(EstadoComparendo estadoComparendo) {
        this.estadoComparendo = estadoComparendo;
    }

}