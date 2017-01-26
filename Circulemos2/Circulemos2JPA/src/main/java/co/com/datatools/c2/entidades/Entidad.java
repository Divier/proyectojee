package co.com.datatools.c2.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "entidad")
@NamedQueries({ @NamedQuery(name = "Entidad.findAll", query = "SELECT e FROM Entidad e") })
public class Entidad implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "codigo_entidad")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad", fetch = FetchType.LAZY)
    private List<CampoEntidad> campoEntidadList;

    public Entidad() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CampoEntidad> getCampoEntidadList() {
        return campoEntidadList;
    }

    public void setCampoEntidadList(List<CampoEntidad> campoEntidadList) {
        this.campoEntidadList = campoEntidadList;
    }

}
