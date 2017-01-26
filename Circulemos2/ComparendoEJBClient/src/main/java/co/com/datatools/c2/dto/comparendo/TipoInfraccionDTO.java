package co.com.datatools.c2.dto.comparendo;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jan 28 08:28:47 COT 2016
 */
public class TipoInfraccionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String nombre;
    private String sigla;
    private String descripcion;
    private Boolean estado;
    private List<InfraccionDTO> infraccionList;

    // --- Constructor
    public TipoInfraccionDTO() {
    }

    public TipoInfraccionDTO(Integer id) {
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

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<InfraccionDTO> getInfraccionList() {
        if (this.infraccionList == null) {
            this.infraccionList = new java.util.ArrayList<>(1);
        }
        return this.infraccionList;
    }

    public void setInfraccionList(List<InfraccionDTO> infraccionList) {
        this.infraccionList = infraccionList;
    }
}