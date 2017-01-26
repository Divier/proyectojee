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
 * Catalogo de aplicaciones administradas bajo el modulo de seguridad
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "aplicacion")
@NamedQueries({ @NamedQuery(name = "Aplicacion.findAll", query = "SELECT a FROM Aplicacion a") })
public class Aplicacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aplicacion")
    private Integer idAplicacion;

    @Column(name = "nombre_aplicacion")
    private String nombreAplicacion;

    public Aplicacion() {
    }

    public Aplicacion(Integer idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public Aplicacion(Integer idAplicacion, String nombreAplicacion) {
        this.idAplicacion = idAplicacion;
        this.nombreAplicacion = nombreAplicacion;
    }

    public Integer getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Integer idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdAplicacion() != null ? getIdAplicacion().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Aplicacion)) {
            return false;
        }
        Aplicacion other = (Aplicacion) object;
        if ((this.getIdAplicacion() == null && other.getIdAplicacion() != null)
                || (this.getIdAplicacion() != null && !this.getIdAplicacion().equals(other.getIdAplicacion()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aplicacion {idAplicacion: " + getIdAplicacion() + ", nombreAplicacion: " + getNombreAplicacion() + "}";
    }

}
