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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the proceso_negocio database table.
 * 
 */
@Entity
@Table(name = "proceso_negocio")
@NamedQuery(name = "ProcesoNegocio.findAll", query = "SELECT p FROM ProcesoNegocio p")
public class ProcesoNegocio implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_proceso")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    @JoinColumn(name = "codigo_modulo", referencedColumnName = "codigo_modulo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modulo modulo;

    @JoinColumn(name = "codigo_proceso_padre", referencedColumnName = "codigo_proceso")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProcesoNegocio procesoPadre;

    @OneToMany(mappedBy = "procesoPadre", fetch = FetchType.LAZY)
    private List<ProcesoNegocio> procesoHijoList;

    public ProcesoNegocio() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public ProcesoNegocio getProcesoPadre() {
        return procesoPadre;
    }

    public void setProcesoPadre(ProcesoNegocio procesoPadre) {
        this.procesoPadre = procesoPadre;
    }

    public List<ProcesoNegocio> getProcesoHijoList() {
        return procesoHijoList;
    }

    public void setProcesoHijoList(List<ProcesoNegocio> procesoHijoList) {
        this.procesoHijoList = procesoHijoList;
    }

}