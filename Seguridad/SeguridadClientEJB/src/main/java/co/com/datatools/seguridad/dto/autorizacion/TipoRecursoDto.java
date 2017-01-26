package co.com.datatools.seguridad.dto.autorizacion;

import java.io.Serializable;

/**
 * Dto para encapsular los datos de un TipoRecurso
 * 
 * @author Felipe Martinez
 */
public class TipoRecursoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idRecurso;
    private String nombre;

    /**
     * Invoca a super
     */
    public TipoRecursoDto() {
        super();
    }

    /**
     * Invoca a super y asigna los valores enviados en los atributos id y nombre
     * 
     * @param id
     * @param nombre
     */
    public TipoRecursoDto(Integer id, String nombre) {
        super();
        this.idRecurso = id;
        this.nombre = nombre;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
