package co.com.datatools.c2.entidades;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "responsable_formulario")
@NamedQueries({ @NamedQuery(name = "ResponsableFormulario.findAll", query = "SELECT r FROM ResponsableFormulario r"), })
public class ResponsableFormulario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_responsable_formulario")
    private Long id;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @Basic(optional = false)
    @Column(name = "fecha_inicio_vincula")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioVincula;

    @Column(name = "fecha_fin_vincula")
    @Temporal(TemporalType.DATE)
    private Date fechaFinVincula;

    @Basic(optional = false)
    @Column(name = "correo_responsable_formulario")
    private String correoResponsableFormulario;

    @JoinColumn(name = "id_tipo_responsable", referencedColumnName = "id_tipo_responsable")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoResponsableFormulario tipoResponsable;

    @OneToOne(mappedBy = "responsableFormulario", fetch = FetchType.LAZY)
    private UnificacionResponsable unificacionResponsable;

    public ResponsableFormulario() {
    }

    public ResponsableFormulario(Long idResponsableFormulario) {
        this.id = idResponsableFormulario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicioVincula() {
        return fechaInicioVincula;
    }

    public void setFechaInicioVincula(Date fechaInicioVincula) {
        this.fechaInicioVincula = fechaInicioVincula;
    }

    public Date getFechaFinVincula() {
        return fechaFinVincula;
    }

    public void setFechaFinVincula(Date fechaFinVincula) {
        this.fechaFinVincula = fechaFinVincula;
    }

    public String getCorreoResponsableFormulario() {
        return correoResponsableFormulario;
    }

    public void setCorreoResponsableFormulario(String correoResponsableFormulario) {
        this.correoResponsableFormulario = correoResponsableFormulario;
    }

    public TipoResponsableFormulario getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(TipoResponsableFormulario tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public UnificacionResponsable getUnificacionResponsable() {
        return unificacionResponsable;
    }

    public void setUnificacionResponsable(UnificacionResponsable unificacionResponsable) {
        this.unificacionResponsable = unificacionResponsable;
    }

}
