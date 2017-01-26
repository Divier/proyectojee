package co.com.datatools.c2.dto.personas;

import co.com.datatools.c2.dto.comun.PaisDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class SeccionActividadEconomicaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String nombre;
    private PaisDTO pais;

    // --- Constructor
    public SeccionActividadEconomicaDTO() {
    }

    public SeccionActividadEconomicaDTO(Integer id) {
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

    public PaisDTO getPais() {
        return this.pais;
    }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }

}