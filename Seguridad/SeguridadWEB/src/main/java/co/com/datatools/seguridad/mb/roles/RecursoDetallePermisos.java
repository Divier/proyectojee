package co.com.datatools.seguridad.mb.roles;

import java.io.Serializable;

import co.com.datatools.seguridad.dto.autorizacion.RecursoDetalleDto;
import co.com.datatools.seguridad.dto.autorizacion.RolDetalleDto;

/**
 * Bean con los atributos necesarios para visualizar los datos del detalle del historico del Rol
 * 
 * @author claudia.rodriguez
 * 
 */
public class RecursoDetallePermisos implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Contiene los datos asocidos al recurso sobre el cual se tiene permiso
     */
    private RecursoDetalleDto recursoDetalleDto;
    /**
     * Nombre de la aplicacion de recurso
     */
    private String aplicacion;
    /**
     * Rol del cual se hereda el recurso, en caso de que se tengan padres
     */
    private RolDetalleDto rolHerencia;
    /**
     * Nombre de las operaciones separadas por coma
     */
    private String operaciones;

    public RecursoDetallePermisos() {
    }

    public RecursoDetalleDto getRecursoDetalleDto() {
        return recursoDetalleDto;
    }

    public void setRecursoDetalleDto(RecursoDetalleDto recursoDetalleDto) {
        this.recursoDetalleDto = recursoDetalleDto;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public RolDetalleDto getRolHerencia() {
        return rolHerencia;
    }

    public void setRolHerencia(RolDetalleDto rolHerencia) {
        this.rolHerencia = rolHerencia;
    }

    public String getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(String operaciones) {
        this.operaciones = operaciones;
    }

}
