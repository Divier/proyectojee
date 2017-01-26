package co.com.datatools.seguridad.dto.autorizacion;

import java.io.Serializable;

/**
 * Informacion basica de grupo, dto a utilizar al retornar consultas masivas
 * 
 * @author Felipe Martinez
 */
public class GrupoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idGrupo;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private String clase;

    /**
     * Constructor sin argumentos, llama a super
     */
    public GrupoDto() {
        super();
    }

    /**
     * Invoca al constructor sin argumentos y asigna los valores enviados a los atributos id y nombres
     * 
     * @param id
     * @param nombre
     */
    public GrupoDto(Integer id, String nombre) {
        super();
        this.idGrupo = id;
        this.nombre = nombre;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

}
