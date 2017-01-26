package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the consecutivo database table.
 * 
 */
@Entity
@NamedQuery(name = "Consecutivo.findAll", query = "SELECT c FROM Consecutivo c")
public class Consecutivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consecutivo")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "consecutivo")
    private String consecutivo;

    // bi-directional many-to-one association to TipoConsecutivo
    @ManyToOne
    @JoinColumn(name = "id_tipo_consecutivo")
    private TipoConsecutivo tipoConsecutivo;

    // bi-directional many-to-one association to OrganismoTransito
    @ManyToOne
    @JoinColumn(name = "codigo_organismo")
    private OrganismoTransito organismoTransito;

    @Basic(optional = false)
    @Column(name = "anio")
    private Integer anio;

    public Consecutivo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public TipoConsecutivo getTipoConsecutivo() {
        return this.tipoConsecutivo;
    }

    public void setTipoConsecutivo(TipoConsecutivo tipoConsecutivo) {
        this.tipoConsecutivo = tipoConsecutivo;
    }

    public OrganismoTransito getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

}