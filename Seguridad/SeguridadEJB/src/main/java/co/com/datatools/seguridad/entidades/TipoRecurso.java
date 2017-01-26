package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Catalogo de tipos de recurso de la aplicacion
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "tipo_recurso")
public class TipoRecurso implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_tipo_recurso")
    private Integer idTipoRecurso;

    @Column(name = "nombre")
    private String nombre;

    public TipoRecurso() {
    }

    public TipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public TipoRecurso(Integer idTipoRecurso, String nombre) {
        this.idTipoRecurso = idTipoRecurso;
        this.nombre = nombre;
    }

    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
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
        hash += (getIdTipoRecurso() != null ? getIdTipoRecurso().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof TipoRecurso)) {
            return false;
        }
        TipoRecurso other = (TipoRecurso) object;
        if ((this.getIdTipoRecurso() == null && other.getIdTipoRecurso() != null)
                || (this.getIdTipoRecurso() != null && !this.getIdTipoRecurso().equals(other.getIdTipoRecurso()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoRecurso {idTipoRecurso: " + getIdTipoRecurso() + ", nombre: " + getNombre() + "}";
    }

}
