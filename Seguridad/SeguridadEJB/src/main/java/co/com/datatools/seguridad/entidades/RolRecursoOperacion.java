package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Vista en la base de datos con el fin de simplificar la consulta de las operaciones de un rol para validacion de autorizaciones
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "rol_recurso_operacion")
@IdClass(RolRecursoOperacion.class)
public class RolRecursoOperacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_aplicacion")
    private int idAplicacion;

    @Id
    @Column(name = "nombre_aplicacion")
    private String nombreAplicacion;

    @Id
    @Column(name = "id_rol")
    private int idRol;

    @Id
    @Column(name = "rol_nombre")
    private String rolNombre;

    @Id
    @Column(name = "id_recurso")
    private int idRecurso;

    @Id
    @Column(name = "recurso_nombre")
    private String recursoNombre;

    @Id
    @Column(name = "recurso_descripcion")
    private String recursoDescripcion;

    @Id
    @Column(name = "operacion_nombre")
    private String operacionNombre;

    @Id
    @Column(name = "id_recurso_operacion")
    private int idRecursoOperacion;

    public RolRecursoOperacion() {
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(int idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getRecursoNombre() {
        return recursoNombre;
    }

    public void setRecursoNombre(String recursoNombre) {
        this.recursoNombre = recursoNombre;
    }

    public String getOperacionNombre() {
        return operacionNombre;
    }

    public void setOperacionNombre(String operacionNombre) {
        this.operacionNombre = operacionNombre;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    public String getRecursoDescripcion() {
        return recursoDescripcion;
    }

    public void setRecursoDescripcion(String recursoDescripcion) {
        this.recursoDescripcion = recursoDescripcion;
    }

    public int getIdRecursoOperacion() {
        return idRecursoOperacion;
    }

    public void setIdRecursoOperacion(int idRecursoOperacion) {
        this.idRecursoOperacion = idRecursoOperacion;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getIdAplicacion();
        result = prime * result + ((getNombreAplicacion() == null) ? 0 : getNombreAplicacion().hashCode());
        result = prime * result + getIdRol();
        result = prime * result + ((getOperacionNombre() == null) ? 0 : getOperacionNombre().hashCode());
        result = prime * result + ((getRecursoNombre() == null) ? 0 : getRecursoNombre().hashCode());
        result = prime * result + ((getRecursoDescripcion() == null) ? 0 : getRecursoDescripcion().hashCode());
        result = prime * result + ((getRolNombre() == null) ? 0 : getRolNombre().hashCode());
        result = prime * result + getIdRecursoOperacion();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RolRecursoOperacion other = (RolRecursoOperacion) obj;
        if (getIdAplicacion() != other.getIdAplicacion())
            return false;
        if (getIdRol() != other.getIdRol())
            return false;
        if (getOperacionNombre() == null) {
            if (other.getOperacionNombre() != null)
                return false;
        } else if (!getOperacionNombre().equals(other.getOperacionNombre()))
            return false;
        if (getRecursoNombre() == null) {
            if (other.getRecursoNombre() != null)
                return false;
        } else if (!getRecursoNombre().equals(other.getRecursoNombre()))
            return false;
        if (getRolNombre() == null) {
            if (other.getRolNombre() != null)
                return false;
        } else if (!getRolNombre().equals(other.getRolNombre()))
            return false;
        return true;
    }

}
