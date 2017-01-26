package co.com.datatools.seguridad.dto.autorizacion;

import java.io.Serializable;
import java.util.Date;

/**
 * Informacion basica de rol, dto a utilizar al retornar consultas masivas
 * 
 * @author Felipe Martinez
 */
public class RolDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idRol;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean activo;
    private AplicacionDTO aplicacionDTO;

    private String usuarioRealizaCambio;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    /**
     * Dos roles son iguales si tienen el mismo nombre
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RolDto other = (RolDto) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RolDto {nombre=" + nombre + "}";
    }

    public String getUsuarioRealizaCambio() {
        return usuarioRealizaCambio;
    }

    public void setUsuarioRealizaCambio(String usuarioRealizaCambio) {
        this.usuarioRealizaCambio = usuarioRealizaCambio;
    }

    public AplicacionDTO getAplicacionDTO() {
        return aplicacionDTO;
    }

    public void setAplicacionDTO(AplicacionDTO aplicacionDTO) {
        this.aplicacionDTO = aplicacionDTO;
    }
}
