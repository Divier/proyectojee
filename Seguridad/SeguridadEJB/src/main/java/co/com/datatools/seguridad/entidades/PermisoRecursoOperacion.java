package co.com.datatools.seguridad.entidades;

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
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Relaciona los [recurso,operacion] asociados a un rol
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "permiso_recurso_operacion")
@NamedQueries({
        @NamedQuery(name = "PermisoRecursoOperacion.findAll", query = "SELECT p FROM PermisoRecursoOperacion p"),
        @NamedQuery(
                name = "PermisoRecursoOperacion.findByRecursoAll",
                query = "SELECT p FROM PermisoRecursoOperacion p JOIN p.recursoOperacion ro WHERE ro.recurso.idRecurso=:idRecurso"),
        @NamedQuery(
                name = "PermisoRecursoOperacion.findByRecurso",
                query = "SELECT p FROM PermisoRecursoOperacion p JOIN p.recursoOperacion ro WHERE ro.recurso.idRecurso=:idRecurso AND p.rol.nombre <> :nombreRolSuperAdmin"),
        @NamedQuery(
                name = "PermisoRecursoOperacion.findByRecursoOperacion",
                query = "SELECT p FROM PermisoRecursoOperacion p JOIN p.rol r WHERE p.recursoOperacion.idRecursoOperacion= :idRecursoOperacion and r.nombre <> :nombreRolSuperAdmin "),
        @NamedQuery(
                name = "PermisoRecursoOperacion.findByRecursoOperacionRol",
                query = "SELECT p FROM PermisoRecursoOperacion p JOIN p.rol r WHERE p.recursoOperacion.idRecursoOperacion= :idRecursoOperacion and r.idRol = :idRol ") })
public class PermisoRecursoOperacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_PERMISO_BY_RECURSO = "PermisoRecursoOperacion.findByRecurso";
    public static final String SQ_PERMISO_BY_RECURSO_ALL = "PermisoRecursoOperacion.findByRecursoAll";
    public static final String SQ_PERMISO_BY_RECURSO_OPERACION = "PermisoRecursoOperacion.findByRecursoOperacion";
    /**
     * Consulta de los PermisoRecursoOperacion por idRecursoOperacion e idRol
     */
    public static final String SQ_PERMISO_BY_RECURSO_OPERACION_ROL = "PermisoRecursoOperacion.findByRecursoOperacionRol";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso_recurso_operacion")
    private Integer idPermisoRecursoOperacion;

    @JoinColumn(name = "id_recurso_operacion", referencedColumnName = "id_recurso_operacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RecursoOperacion recursoOperacion;

    @JoinColumn(name = "id_rol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rol;

    public PermisoRecursoOperacion() {
    }

    public PermisoRecursoOperacion(RecursoOperacion recursoOperacion, Rol rol) {
        super();
        this.recursoOperacion = recursoOperacion;
        this.rol = rol;
    }

    public PermisoRecursoOperacion(Integer idPermisoRecursoOperacion) {
        this.idPermisoRecursoOperacion = idPermisoRecursoOperacion;
    }

    public Integer getIdPermisoRecursoOperacion() {
        return idPermisoRecursoOperacion;
    }

    public void setIdPermisoRecursoOperacion(Integer idPermisoRecursoOperacion) {
        this.idPermisoRecursoOperacion = idPermisoRecursoOperacion;
    }

    public RecursoOperacion getRecursoOperacion() {
        return recursoOperacion;
    }

    public void setRecursoOperacion(RecursoOperacion recursoOperacion) {
        this.recursoOperacion = recursoOperacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdPermisoRecursoOperacion() != null ? getIdPermisoRecursoOperacion().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PermisoRecursoOperacion)) {
            return false;
        }
        PermisoRecursoOperacion other = (PermisoRecursoOperacion) object;
        if ((this.getIdPermisoRecursoOperacion() == null && other.getIdPermisoRecursoOperacion() != null)
                || (this.getIdPermisoRecursoOperacion() != null && !this.getIdPermisoRecursoOperacion().equals(
                        other.getIdPermisoRecursoOperacion()))) {
            return false;
        }
        return true;
    }
}
