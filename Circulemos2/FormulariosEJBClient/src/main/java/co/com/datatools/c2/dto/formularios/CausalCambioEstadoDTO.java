package co.com.datatools.c2.dto.formularios;

import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Sep 03 16:47:20 COT 2015
 */
public class CausalCambioEstadoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String nombre;
    private String sigla;
    private String descripcion;
    private Boolean estado;
    private List<DetalleCambioEstadoDTO> detalleCambioEstadoList;
    private EstadoFormularioDTO estadoFormulario;

    // --- Constructor
    public CausalCambioEstadoDTO() {
    }

    public CausalCambioEstadoDTO(Integer id) {
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

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<DetalleCambioEstadoDTO> getDetalleCambioEstadoList() {
        if (this.detalleCambioEstadoList == null) {
            this.detalleCambioEstadoList = new java.util.ArrayList<>(1);
        }
        return this.detalleCambioEstadoList;
    }

    public void setDetalleCambioEstadoList(List<DetalleCambioEstadoDTO> detalleCambioEstadoList) {
        this.detalleCambioEstadoList = detalleCambioEstadoList;
    }

    public EstadoFormularioDTO getEstadoFormulario() {
        return this.estadoFormulario;
    }

    public void setEstadoFormulario(EstadoFormularioDTO estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
