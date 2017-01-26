package co.com.datatools.c2.dto.personas;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:43 COT 2014
 */
public class ClaseActividadEconomicaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String nombre;
    private GrupoActividadEconomicaDTO grupoActividadEconomica;

    // --- Constructor
    public ClaseActividadEconomicaDTO() {
    }

    public ClaseActividadEconomicaDTO(Integer id) {
        this.id = id;
    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GrupoActividadEconomicaDTO getGrupoActividadEconomica() {
        return this.grupoActividadEconomica;
    }

    public void setGrupoActividadEconomica(GrupoActividadEconomicaDTO grupoActividadEconomica) {
        this.grupoActividadEconomica = grupoActividadEconomica;
    }

}