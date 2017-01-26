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
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the detalle_fuente_consulta database table.
 * 
 */
@Entity
@Table(name = "detalle_fuente_consulta")
@NamedQuery(name = "DetalleFuenteConsulta.findAll", query = "SELECT d FROM DetalleFuenteConsulta d")
public class DetalleFuenteConsulta implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_fuente_consulta")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "prioridad")
    private Integer prioridad;

    // bi-directional many-to-one association to TipoFuenteConsulta
    @ManyToOne
    @JoinColumn(name = "id_tipo_fuente_consulta")
    private TipoFuenteConsulta tipoFuenteConsulta;

    // bi-directional many-to-one association to FuenteConsulta
    @ManyToOne
    @JoinColumn(name = "id_fuente_consulta")
    private FuenteConsulta fuenteConsulta;

    // bi-directional many-to-one association to OrganismoTransito
    @ManyToOne
    @JoinColumn(name = "codigo_organismo")
    private OrganismoTransito organismoTransito;

    public DetalleFuenteConsulta() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public TipoFuenteConsulta getTipoFuenteConsulta() {
        return this.tipoFuenteConsulta;
    }

    public void setTipoFuenteConsulta(TipoFuenteConsulta tipoFuenteConsulta) {
        this.tipoFuenteConsulta = tipoFuenteConsulta;
    }

    public FuenteConsulta getFuenteConsulta() {
        return this.fuenteConsulta;
    }

    public void setFuenteConsulta(FuenteConsulta fuenteConsulta) {
        this.fuenteConsulta = fuenteConsulta;
    }

    public OrganismoTransito getOrganismoTransito() {
        return this.organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

}