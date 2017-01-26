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
 * Operaciones de recursos configuradas en la aplicacion
 * 
 * @author Felipe Martinez <br>
 *         rodrigo.cruz (mod: 21/07/2015) - Auditoria
 */
@Entity
@Table(name = "operacion")
// @Audited
@NamedQueries({
        @NamedQuery(name = "Operacion.findAll", query = "SELECT o FROM Operacion o"),
        @NamedQuery(name = "Operacion.findByNombre", query = "SELECT o FROM Operacion o WHERE o.nombre = :nombre"),
        @NamedQuery(
                name = "Operacion.findNoIngresar",
                query = "SELECT o FROM Operacion o WHERE o.nombre <> :nombreOpIngresar") })
public class Operacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_OPERACION_ALL = "Operacion.findAll";
    public static final String SQ_OPERACION_BY_NOMBRE = "Operacion.findByNombre";
    public static final String SQ_OPERACION_NO_INGRESAR = "Operacion.findNoIngresar";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion")
    private Integer idOperacion;

    @Column(name = "nombre")
    private String nombre;

    public Operacion() {
    }

    public Operacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Operacion(Integer idOperacion, String nombre) {
        this.idOperacion = idOperacion;
        this.nombre = nombre;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
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
        hash += (getIdOperacion() != null ? getIdOperacion().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Operacion)) {
            return false;
        }
        Operacion other = (Operacion) object;
        if ((this.getIdOperacion() == null && other.getIdOperacion() != null)
                || (this.getIdOperacion() != null && !this.getIdOperacion().equals(other.getIdOperacion()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Operacion {idOperacion: " + getIdOperacion() + ", nombre: " + getNombre() + "}";
    }

}
