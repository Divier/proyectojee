package co.com.datatools.c2.entidades;

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
@Table(name = "tipo_variable")
@NamedQueries({ @NamedQuery(name = "TipoVariable.findAll", query = "SELECT t FROM TipoVariable t") })
public class TipoVariable implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_tipo_variable")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nombre_tipo_variable")
    private String nombre;

    @Column(name = "descripcion_tipo_variable")
    private String descripcion;

    public TipoVariable() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
