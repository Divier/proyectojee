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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 5
 * 
 */
@Entity
@Table(name = "trazabilidad_cartera")
@NamedQuery(name = "TrazabilidadCartera.findAll", query = "SELECT t FROM TrazabilidadCartera t")
public class TrazabilidadCartera implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trazabilidad_cartera")
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Basic(optional = false)
    @Column(name = "login_usuario")
    private String loginUsuario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_estado_obligacion")
    private EstadoObligacion estadoObligacion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartera")
    private Cartera cartera;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad_cartera")
    private ActividadCartera actividadCartera;

    public TrazabilidadCartera() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public EstadoObligacion getEstadoObligacion() {
        return this.estadoObligacion;
    }

    public void setEstadoObligacion(EstadoObligacion estadoObligacion) {
        this.estadoObligacion = estadoObligacion;
    }

    public Cartera getCartera() {
        return this.cartera;
    }

    public void setCartera(Cartera cartera) {
        this.cartera = cartera;
    }

    public ActividadCartera getActividadCartera() {
        return this.actividadCartera;
    }

    public void setActividadCartera(ActividadCartera actividadCartera) {
        this.actividadCartera = actividadCartera;
    }

}