package co.com.datatools.c2.dto.comparendo;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 13 08:49:02 COT 2015
 */
public class MedioImposicionComparendoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String descripcion;
    private Boolean activo;
    private String nombre;
    private String sigla;
    private List<ComparendoDTO> comparendos;

    // --- Constructor
    public MedioImposicionComparendoDTO() {
    }

    public MedioImposicionComparendoDTO(Integer id) {
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

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return this.activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<ComparendoDTO> getComparendos() {
        if (this.comparendos == null) {
            this.comparendos = new java.util.ArrayList<>(1);
        }
        return this.comparendos;
    }

    public void setComparendos(List<ComparendoDTO> comparendos) {
        this.comparendos = comparendos;
    }
}