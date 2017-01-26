package co.com.datatools.c2.entidades.ws;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the web_service_expuesto database table.
 * 
 */
@Entity
@Table(name = "web_service_expuesto")
@NamedQuery(name = "WebServiceExpuesto.findAll", query = "SELECT w FROM WebServiceExpuesto w")
public class WebServiceExpuesto implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_servicio")
    private int id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "nombre")
    private String nombre;

    public WebServiceExpuesto() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}