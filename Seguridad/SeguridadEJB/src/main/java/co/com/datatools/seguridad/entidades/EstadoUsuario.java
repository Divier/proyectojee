package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Catalogo de posibles estados de un usuario
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "estado_usuario")
@NamedQuery(name = "EstadoUsuario.findAll", query = "SELECT e FROM EstadoUsuario e ORDER BY e.nombre")
public class EstadoUsuario implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_ESTADOUSU_ALL = "EstadoUsuario.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "nombre")
    private String nombre;

    public EstadoUsuario() {
    }

    public EstadoUsuario(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoUsuario(Integer idEstado, String nombre) {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
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
        hash += (getIdEstado() != null ? getIdEstado().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EstadoUsuario)) {
            return false;
        }
        EstadoUsuario other = (EstadoUsuario) object;
        if ((this.getIdEstado() == null && other.getIdEstado() != null)
                || (this.getIdEstado() != null && !this.getIdEstado().equals(other.getIdEstado()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstadoUsuario {idEstado=" + getIdEstado() + ", nombre=" + nombre + "}";
    }

}
