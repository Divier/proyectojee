package co.com.datatools.c2.dto.comun;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue May 27 18:03:44 COT 2014
 */
public class DepartamentoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String nombre;
    private PaisDTO pais;
    private List<MunicipioDTO> municipioList;

    // --- Constructor
    public DepartamentoDTO() {
    }

    public DepartamentoDTO(Integer id) {
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<MunicipioDTO> getMunicipioList() {
        if (this.municipioList == null) {
            this.municipioList = new java.util.ArrayList<>(1);
        }
        return this.municipioList;
    }

    public void setMunicipioList(List<MunicipioDTO> municipioList) {
        this.municipioList = municipioList;
    }

}