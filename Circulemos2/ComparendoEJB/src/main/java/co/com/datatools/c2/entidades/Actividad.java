package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author luis.forero
 * @version Sprint 12 (2016-03-01)
 */
@Entity
@Table(name = "actividad")
@NamedQueries({ @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a") })
public class Actividad implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividad")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "codigo_actividad")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    @JoinColumn(name = "codigo_proceso", referencedColumnName = "codigo_proceso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProcesoNegocio proceso;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "actividad_organismo",
            joinColumns = { @JoinColumn(name = "id_actividad") },
            inverseJoinColumns = { @JoinColumn(name = "codigo_organismo") })
    private List<OrganismoTransito> actividadOrganismo;

    public Actividad() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public ProcesoNegocio getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoNegocio proceso) {
        this.proceso = proceso;
    }

    public List<OrganismoTransito> getActividadOrganismo() {
        return actividadOrganismo;
    }

    public void setActividadOrganismo(List<OrganismoTransito> actividadOrganismo) {
        this.actividadOrganismo = actividadOrganismo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
