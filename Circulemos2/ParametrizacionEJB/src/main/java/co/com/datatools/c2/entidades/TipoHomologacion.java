package co.com.datatools.c2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the tipo_homologacion database table.
 * 
 */
@Entity
@Table(name = "tipo_homologacion")
@NamedQuery(name = "TipoHomologacion.findAll", query = "SELECT t FROM TipoHomologacion t")
public class TipoHomologacion implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_tipo_homologacion")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nombre")
    private String nombre;

    // bi-directional many-to-one association to ServicioHomologacion
    @ManyToOne
    @JoinColumn(name = "id_servicio_homologacion")
    private ServicioHomologacion servicioHomologacion;

    public TipoHomologacion() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ServicioHomologacion getServicioHomologacion() {
        return this.servicioHomologacion;
    }

    public void setServicioHomologacion(ServicioHomologacion servicioHomologacion) {
        this.servicioHomologacion = servicioHomologacion;
    }

}