package co.com.datatools.c2.entidades.ws;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tipo_mapeo database table.
 * 
 */
@Entity
@Table(name = "tipo_mapeo")
@NamedQuery(name = "TipoMapeo.findAll", query = "SELECT t FROM TipoMapeo t")
public class TipoMapeo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_tipo_mapeo")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private WebServiceExpuesto webServiceExpuesto;

    public TipoMapeo() {
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

    public WebServiceExpuesto getWebServiceExpuesto() {
        return this.webServiceExpuesto;
    }

    public void setWebServiceExpuesto(WebServiceExpuesto webServiceExpuesto) {
        this.webServiceExpuesto = webServiceExpuesto;
    }

}