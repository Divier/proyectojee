package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Catalogo de posibles estados de un registro de ingreso a la aplicacion
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "estado_ingreso")
@NamedQueries({ @NamedQuery(name = "EstadoIngreso.findAll", query = "SELECT e FROM EstadoIngreso e") })
public class EstadoIngreso implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_ingreso")
    private Integer idEstadoIngreso;

    @Column(name = "nombre")
    private String nombre;

    public EstadoIngreso() {
    }

    public EstadoIngreso(Integer idEstadoIngreso) {
        this.idEstadoIngreso = idEstadoIngreso;
    }

    public EstadoIngreso(Integer idEstadoIngreso, String nombre) {
        this.idEstadoIngreso = idEstadoIngreso;
        this.nombre = nombre;
    }

    public Integer getIdEstadoIngreso() {
        return this.idEstadoIngreso;
    }

    public void setIdEstadoIngreso(Integer idEstadoIngreso) {
        this.idEstadoIngreso = idEstadoIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdEstadoIngreso() != null ? getIdEstadoIngreso().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EstadoIngreso)) {
            return false;
        }
        EstadoIngreso other = (EstadoIngreso) object;
        if ((this.getIdEstadoIngreso() == null && other.getIdEstadoIngreso() != null)
                || (this.getIdEstadoIngreso() != null && !this.getIdEstadoIngreso().equals(other.getIdEstadoIngreso()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstadoIngreso {idEstadoIngreso: " + getIdEstadoIngreso() + ", nombre: " + getNombre() + "}";
    }

}
