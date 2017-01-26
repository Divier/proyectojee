package co.com.datatools.c2.dto.comparendo;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:39:25 COT 2015
 */
public class ErrorProcesamientoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String descripcion;
    private Boolean estado;
    private String nombre;
    private String sigla;
    private List<DetalleProcesamientoDTO> detalleProcesamientos;

    // --- Constructor
    public ErrorProcesamientoDTO() {
    }

    public ErrorProcesamientoDTO(Integer id) {
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

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleProcesamientoDTO> getDetalleProcesamientos() {
        if (this.detalleProcesamientos == null) {
            this.detalleProcesamientos = new java.util.ArrayList<>(1);
        }
        return this.detalleProcesamientos;
    }

    public void setDetalleProcesamientos(List<DetalleProcesamientoDTO> detalleProcesamientos) {
        this.detalleProcesamientos = detalleProcesamientos;
    }

}
