package co.com.datatools.seguridad.dto.autorizacion;

import java.io.Serializable;

/**
 * Informacion basica de un recurso
 * 
 * @author Felipe Martinez
 */
public class RecursoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idRecurso;
    private String nombreRecurso;
    private String descripcion;
    private String idAplicacion;
    private String nombreAplicacion;

    public RecursoDto() {
        super();
    }

    public RecursoDto(String nombreRecurso) {
        this();
        this.nombreRecurso = nombreRecurso;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombreRecurso == null) ? 0 : nombreRecurso.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecursoDto other = (RecursoDto) obj;
        if (nombreRecurso == null) {
            if (other.nombreRecurso != null)
                return false;
        } else if (!nombreRecurso.equals(other.nombreRecurso))
            return false;
        return true;
    }

}
