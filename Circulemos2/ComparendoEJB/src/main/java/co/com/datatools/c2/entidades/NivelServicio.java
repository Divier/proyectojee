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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.Pais;
import co.com.datatools.util.dto.EntidadC2;

/**
 * @version Sprint 4
 */
@Entity
@Table(name = "nivel_servicio")
@NamedQueries({ @NamedQuery(name = "NivelServicio.findAll", query = "SELECT n FROM NivelServicio n") })
public class NivelServicio implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel_servicio")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "codigo_nivel_servicio")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "nombre_nivel_servicio")
    private String nombre;

    @Column(name = "descripcion_nivel_servicio")
    private String descripcion;

    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pais pais;

    @OneToMany(mappedBy = "nivelServicio")
    private List<ComparendoVehiculo> comparendoVehiculos;

    public NivelServicio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<ComparendoVehiculo> getComparendoVehiculos() {
        return comparendoVehiculos;
    }

    public void setComparendoVehiculos(List<ComparendoVehiculo> comparendoVehiculos) {
        this.comparendoVehiculos = comparendoVehiculos;
    }

}
