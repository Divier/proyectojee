package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.seguridad.utilidades.EnumClaseGrupo;
import co.com.datatools.util.dto.EntidadC2;

/**
 * Grupos configurados en la aplicacion
 * 
 * @author Felipe Martinez <br>
 *         rodrigo.cruz (mod: 21/07/2015) - Auditoria
 */
@Entity
@Table(name = "grupo")
// @Audited
@NamedQueries({ @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
        @NamedQuery(name = "Grupo.findByNombre", query = "SELECT g FROM Grupo g WHERE g.nombre = :nombre") })
public class Grupo implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    public static final String SQ_GRUPO_FIND_ALL = "Grupo.findAll";
    public static final String SQ_GRUPO_BY_NOMBRE = "Grupo.findByNombre";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Integer idGrupo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "activo")
    // @Type(type = "numeric_boolean")
    private boolean activo;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "contexto_aplica")
    private EnumClaseGrupo clase;

    public Grupo() {
    }

    public Integer getIdGrupo() {
        return this.idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EnumClaseGrupo getClase() {
        return clase;
    }

    public void setClase(EnumClaseGrupo clase) {
        this.clase = clase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdGrupo() != null ? getIdGrupo().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.getIdGrupo() == null && other.getIdGrupo() != null)
                || (this.getIdGrupo() != null && !this.getIdGrupo().equals(other.getIdGrupo()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grupo {idGrupo: " + getIdGrupo() + ", nombre: " + getNombre() + "}";
    }

}
