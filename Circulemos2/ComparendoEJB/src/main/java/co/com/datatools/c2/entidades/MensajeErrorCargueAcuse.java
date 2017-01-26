package co.com.datatools.c2.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the mensaje_error_cargue_acuse database table.
 * 
 */
@Entity
@Table(name = "mensaje_error_cargue_acuse")
@NamedQuery(name = "MensajeErrorCargueAcuse.findAll", query = "SELECT m FROM MensajeErrorCargueAcuse m")
public class MensajeErrorCargueAcuse implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_mensaje_error_cargue_acuse")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    public MensajeErrorCargueAcuse() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}