package co.com.datatools.c2.dto.comun;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Fri Oct 09 15:57:23 COT 2015
 */
public class ClaseVehiculoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private String sigla;

    // --- Constructor
    public ClaseVehiculoDTO() {
    }

    public ClaseVehiculoDTO(Integer id) {
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

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public ClaseVehiculoDTO clone() {
        ClaseVehiculoDTO claseVehiculoDTO = new ClaseVehiculoDTO();

        claseVehiculoDTO.setId(this.id != null ? new Integer(this.id) : null);
        claseVehiculoDTO.setCodigo(this.codigo != null ? new String(this.codigo) : null);
        claseVehiculoDTO.setNombre(this.nombre != null ? new String(this.nombre) : null);
        claseVehiculoDTO.setDescripcion(this.descripcion != null ? new String(this.descripcion) : null);
        claseVehiculoDTO.setActivo(this.activo != null ? new Boolean(this.activo) : null);
        claseVehiculoDTO.setSigla(this.sigla != null ? new String(this.sigla) : null);

        return claseVehiculoDTO;
    }
}