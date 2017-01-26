package co.com.datatools.seguridad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Entidad no utilizada en la logica de negocio
 * 
 * @author Felipe Martinez
 */
@Entity
@Table(name = "tipo_restriccion_rol")
public class TipoRestriccionRol implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_restriccion")
    private Integer idTipoRestriccion;

    @Column(name = "nombre_tipo_restriccion")
    private String nombreTipoRestriccion;

    public TipoRestriccionRol() {
    }

    public Integer getIdTipoRestriccion() {
        return this.idTipoRestriccion;
    }

    public void setIdTipoRestriccion(Integer idTipoRestriccion) {
        this.idTipoRestriccion = idTipoRestriccion;
    }

    public String getNombreTipoRestriccion() {
        return this.nombreTipoRestriccion;
    }

    public void setNombreTipoRestriccion(String nombreTipoRestriccion) {
        this.nombreTipoRestriccion = nombreTipoRestriccion;
    }

}
