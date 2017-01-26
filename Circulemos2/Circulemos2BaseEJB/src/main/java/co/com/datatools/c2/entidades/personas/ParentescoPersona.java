package co.com.datatools.c2.entidades.personas;

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

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "parentesco_persona")
@NamedQueries({ @NamedQuery(name = "ParentescoPersona.findAll", query = "SELECT p FROM ParentescoPersona p") })
public class ParentescoPersona implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parentesco_persona")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "fecha_inicio_parentesco")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin_parentesco")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "id_tipo_parentesco_persona", referencedColumnName = "id_tipo_parentesco_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoParentescoPersona tipoParentesco;

    @JoinColumn(name = "id_persona_1", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona principal;

    @JoinColumn(name = "id_persona_2", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona pariente;

    public ParentescoPersona() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public TipoParentescoPersona getTipoParentesco() {
        return tipoParentesco;
    }

    public void setTipoParentesco(TipoParentescoPersona tipoParentesco) {
        this.tipoParentesco = tipoParentesco;
    }

    public Persona getPrincipal() {
        return principal;
    }

    public void setPrincipal(Persona principal) {
        this.principal = principal;
    }

    public Persona getPariente() {
        return pariente;
    }

    public void setPariente(Persona pariente) {
        this.pariente = pariente;
    }

}
