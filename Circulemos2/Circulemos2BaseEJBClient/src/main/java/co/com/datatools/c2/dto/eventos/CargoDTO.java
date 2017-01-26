package co.com.datatools.c2.dto.eventos;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @version
 * @author giovanni.velandia
 */
public class CargoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private Boolean estado;
    private String sigla;
    private String codigo;
    private String descripcion;

    public CargoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Boolean getActivo() {
        return estado;
    }

    public void setActivo(Boolean activo) {
        this.estado = activo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
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

}
