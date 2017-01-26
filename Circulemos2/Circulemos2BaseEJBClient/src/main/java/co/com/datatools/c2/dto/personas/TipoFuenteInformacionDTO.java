package co.com.datatools.c2.dto.personas;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 06 17:38:48 COT 2015
 */
public class TipoFuenteInformacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombre;
    private String codigo;
    private String sigla;
    private String descripcion;
    private Boolean estado;
    private ScoreUbicabilidadDTO scoreUbicabilidad;

    // --- Constructor
    public TipoFuenteInformacionDTO() {
    }

    public TipoFuenteInformacionDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public ScoreUbicabilidadDTO getScoreUbicabilidad() {
        return scoreUbicabilidad;
    }

    public void setScoreUbicabilidad(ScoreUbicabilidadDTO scoreUbicabilidad) {
        this.scoreUbicabilidad = scoreUbicabilidad;
    }

}