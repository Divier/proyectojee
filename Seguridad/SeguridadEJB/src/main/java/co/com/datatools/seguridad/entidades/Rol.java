package co.com.datatools.seguridad.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Informacion de un rol de una aplicacion
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "rol")
@NamedQueries({
        @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
        @NamedQuery(name = "Rol.grupoRol", query = "SELECT gr FROM Rol r JOIN r.grupoList gr WHERE r.idRol = :idRol"),
        @NamedQuery(name = "Rol.findByPadre", query = "SELECT r FROM Rol r WHERE r.rolPadre.idRol = :idRolPadre"),
        @NamedQuery(
                name = "Rol.findByGrupo",
                query = "SELECT r FROM Rol r JOIN r.grupoList gr WHERE gr.idGrupo= :idGrupo"),
        @NamedQuery(name = "Rol.findByNombre", query = "SELECT r FROM Rol r WHERE r.nombre = :nombre"),
        @NamedQuery(
                name = "Rol.findPadresByNombre",
                query = "SELECT r.rolPadre.nombre FROM Rol r WHERE r.nombre IN :nombres") })
public class Rol implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_GRUPOS_ROL = "Rol.grupoRol";
    public static final String SQ_ROL_BY_PADRE = "Rol.findByPadre";
    public static final String SQ_ROL_BY_GRUPO = "Rol.findByGrupo";
    public static final String SQ_ROL_BY_NOMBRE = "Rol.findByNombre";
    public static final String SQ_ROLES_PADRE_BY_NOMBRE = "Rol.findPadresByNombre";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    @Type(type = "numeric_boolean")
    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol_padre")
    private Rol rolPadre;

    @OneToMany(mappedBy = "rolPadre", fetch = FetchType.LAZY)
    private List<Rol> rolHijoList;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermisoRecursoOperacion> permisoRecursosOperaciones;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rol_usuario",
            joinColumns = { @JoinColumn(name = "id_rol") },
            inverseJoinColumns = { @JoinColumn(name = "id_usuario") })
    private List<Usuario> usuarioList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grupo_rol", joinColumns = { @JoinColumn(name = "id_rol") }, inverseJoinColumns = { @JoinColumn(
            name = "id_grupo") })
    private List<Grupo> grupoList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ingreso_usuario_rol",
            joinColumns = { @JoinColumn(name = "id_rol") },
            inverseJoinColumns = { @JoinColumn(name = "id_ingreso_usuario") })
    private List<IngresoUsuario> ingresoUsuarioList;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<HistoricoRol> historicoRolList;

    @JoinColumn(name = "id_aplicacion", referencedColumnName = "id_aplicacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aplicacion aplicacion;

    public Rol() {
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<PermisoRecursoOperacion> getPermisoRecursosOperaciones() {
        return permisoRecursosOperaciones;
    }

    public void setPermisoRecursosOperaciones(List<PermisoRecursoOperacion> permisoRecursosOperaciones) {
        this.permisoRecursosOperaciones = permisoRecursosOperaciones;
    }

    public Rol getRolPadre() {
        return rolPadre;
    }

    public void setRolPadre(Rol rolPadre) {
        this.rolPadre = rolPadre;
    }

    public List<Rol> getRolHijoList() {
        return rolHijoList;
    }

    public void setRolHijoList(List<Rol> rolHijoList) {
        this.rolHijoList = rolHijoList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public List<IngresoUsuario> getIngresoUsuarioList() {
        return ingresoUsuarioList;
    }

    public void setIngresoUsuarioList(List<IngresoUsuario> ingresoUsuarioList) {
        this.ingresoUsuarioList = ingresoUsuarioList;
    }

    public List<HistoricoRol> getHistoricoRolList() {
        return historicoRolList;
    }

    public void setHistoricoRolList(List<HistoricoRol> historicoRolList) {
        this.historicoRolList = historicoRolList;
    }

    public Aplicacion getAplicacion() {
        return this.aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdRol() == null) ? 0 : getIdRol().hashCode());
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
        Rol other = (Rol) obj;
        if (getIdRol() == null) {
            if (other.getIdRol() != null)
                return false;
        } else if (!getIdRol().equals(other.getIdRol()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rol {idRol: " + getIdRol() + ", nombre: " + getNombre() + "}";
    }

    // --- Metodos de manejo de los atributos lista del rol
    /**
     * Agrega y asocia un rol a la lista de hijos
     * 
     * @param rol
     *            rol a agregar como hijo
     * @return rol con la referencia a this como padre
     */
    public Rol addRolHijo(Rol rol) {
        getRolHijoList().add(rol);
        rol.setRolPadre(this);

        return rol;
    }

    /**
     * Desasocia un rol de la lista de hijos
     * 
     * @param rol
     *            rol a remover de la lista de hijos
     * @return rol con la referencia padre en null
     */
    public Rol removeRolHijo(Rol rol) {
        getRolHijoList().remove(rol);
        rol.setRolPadre(null);
        return rol;
    }

    /**
     * Agrega permisos al rol
     * 
     * @param permisoRecursosOperacione
     *            permiso a asociar al rol
     * @return permiso con la rol asociado a this
     */
    public PermisoRecursoOperacion addPermisoRecursosOperacione(PermisoRecursoOperacion permisoRecursosOperacione) {
        getPermisoRecursosOperaciones().add(permisoRecursosOperacione);
        permisoRecursosOperacione.setRol(this);

        return permisoRecursosOperacione;
    }

    /**
     * Desasocia un permiso del rol
     * 
     * @param permisoRecursosOperacione
     *            permiso a desasociar del rol
     * @return permiso con la referencia a rol en null
     */
    public PermisoRecursoOperacion removePermisoRecursosOperacione(PermisoRecursoOperacion permisoRecursosOperacione) {
        getPermisoRecursosOperaciones().remove(permisoRecursosOperacione);
        permisoRecursosOperacione.setRol(null);

        return permisoRecursosOperacione;
    }

}
