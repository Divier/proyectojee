package co.com.datatools.c2.entidades.personas;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "tipo_parentesco_persona")
@NamedQueries({ @NamedQuery(name = "TipoParentescoPersona.findAll", query = "SELECT t FROM TipoParentescoPersona t") })
public class TipoParentescoPersona implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_parentesco_persona")
    private Integer id;

    @Column(name = "nombre_parentesco_persona")
    private String nombre;

    public TipoParentescoPersona() {
    }

    public Integer getId() {
        return id;
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

}
