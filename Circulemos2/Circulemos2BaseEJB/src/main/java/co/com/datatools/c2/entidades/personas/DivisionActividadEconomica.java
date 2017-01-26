package co.com.datatools.c2.entidades.personas;

import javax.persistence.Basic;
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
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "division_actividad_economica")
@NamedQueries({ @NamedQuery(
        name = "DivisionActividadEconomica.findAll",
        query = "SELECT d FROM DivisionActividadEconomica d") })
public class DivisionActividadEconomica implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_division_actividad_econo")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "codigo_division_activ_econo")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "nombre_division_activ_econo")
    private String nombre;

    @JoinColumn(name = "id_seccion_actividad_econo", referencedColumnName = "id_seccion_actividad_econo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SeccionActividadEconomica seccionActividadEconomica;

    public DivisionActividadEconomica() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SeccionActividadEconomica getSeccionActividadEconomica() {
        return seccionActividadEconomica;
    }

    public void setSeccionActividadEconomica(SeccionActividadEconomica seccionActividadEconomica) {
        this.seccionActividadEconomica = seccionActividadEconomica;
    }

}
