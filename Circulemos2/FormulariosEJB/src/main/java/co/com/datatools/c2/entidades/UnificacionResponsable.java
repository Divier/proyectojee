/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.datatools.c2.entidades;

import java.io.Serializable;

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

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.c2.entidades.personas.Persona;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "unificacion_responsable")
@NamedQueries({ @NamedQuery(name = "UnificacionResponsable.findAll", query = "SELECT u FROM UnificacionResponsable u"),
        @NamedQuery(
                name = "UnificacionResponsable.findByIdUnificacionResponsable",
                query = "SELECT u FROM UnificacionResponsable u WHERE u.id = :pIdUniResp"),
        @NamedQuery(
                name = "UnificacionResponsable.findByIdResponsableFormulario",
                query = "SELECT u FROM UnificacionResponsable AS u JOIN FETCH u.responsableFormulario AS res WHERE res.id = :pIdRespForm"),
        @NamedQuery(
                name = "UnificacionResponsable.countByCodigoOrganismo",
                query = "SELECT COUNT(u) FROM UnificacionResponsable AS u WHERE u.organismoTransito.codigoOrganismo = :pCodOrg"),
        @NamedQuery(
                name = "UnificacionResponsable.countByPersonaJuridica",
                query = "SELECT COUNT(u) FROM UnificacionResponsable AS u WHERE u.persona.tipoIdentificacion.id = :pIdTipIdent AND u.persona.numeroIdentificacion = :pNumIdent") })
public class UnificacionResponsable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Consulta la unificacion del responsable por su id. <br>
     * SELECT u FROM UnificacionResponsable u JOIN FETCH u.responsableFormulario AS res WHERE res.id = :pIdRespForm
     * 
     * @author luis.forero
     */
    public static final String SQ_FIND_BY_ID_RESPONSABLE = "UnificacionResponsable.findByIdResponsableFormulario";

    /**
     * Cuenta el numero de responsables existentes que tienen un codigo de organismo.<br/>
     * 
     * Consulta: <br/>
     * "SELECT COUNT(u) FROM UnificacionResponsable AS u WHERE u.organismoTransito.codigoOrganismo = :pCodOrg"
     */
    public static final String SQ_COUNT_BY_CODIGO_ORGANISMO = "UnificacionResponsable.countByCodigoOrganismo";

    /**
     * Cuenta el numero de responsables existentes con el numero de identificacion y el numero de persona
     * 
     * <br/>
     * Consulta:<br/>
     * "SELECT COUNT(u) FROM UnificacionResponsable AS u WHERE COUNT(u).persona.tipoIdentificacion.id = :pIdTipIdent AND
     * u.persona.numeroIdentificacion = :pNumIdent
     */
    public static final String SQ_COUNT_BY_TIP_NUMERO_DOC_PERSONA = "UnificacionResponsable.countByPersonaJuridica";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unificacion_responsable")
    private Long id;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;

    @JoinColumn(name = "id_responsable_formulario", referencedColumnName = "id_responsable_formulario")
    @OneToOne(fetch = FetchType.LAZY)
    private ResponsableFormulario responsableFormulario;

    public UnificacionResponsable() {
    }

    public UnificacionResponsable(Long id) {
        this.id = id;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnificacionResponsable)) {
            return false;
        }
        UnificacionResponsable other = (UnificacionResponsable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication2.UnificacionResponsable[ id=" + id + " ]";
    }

    public ResponsableFormulario getResponsableFormulario() {
        return responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormulario responsableFormulario) {
        this.responsableFormulario = responsableFormulario;
    }

}
