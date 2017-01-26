package co.com.datatools.c2.dto;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Oct 07 11:11:32 COT 2015
 */
public class TipoResponsableSolidarioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigoResponsable;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private String sigla;

    // --- Constructor
    public TipoResponsableSolidarioDTO() {
    }

    public TipoResponsableSolidarioDTO(Integer codigoResponsable) {
        this.codigoResponsable = codigoResponsable;

    }

    // --- Getters-Setters
    public Integer getCodigoResponsable() {
        return this.codigoResponsable;
    }

    public void setCodigoResponsable(Integer codigoResponsable) {
        this.codigoResponsable = codigoResponsable;
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

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
