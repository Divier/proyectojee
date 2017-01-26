package co.com.datatools.c2.entidades.personas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "clase_actividad_economica")
public class ClaseActividadEconomica implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase_actividad_economica")
    private Integer id;

    @Column(name = "codigo_clase_actividad_econo")
    private String codigo;

    @Column(name = "nombre_clase_actividad_econo")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_grupo_actividad_economica")
    private GrupoActividadEconomica grupoActividadEconomica;

    public ClaseActividadEconomica() {
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

    public GrupoActividadEconomica getGrupoActividadEconomica() {
        return grupoActividadEconomica;
    }

    public void setGrupoActividadEconomica(GrupoActividadEconomica grupoActividadEconomica) {
        this.grupoActividadEconomica = grupoActividadEconomica;
    }

}