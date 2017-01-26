package co.com.datatools.c2.dto.formularios;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * 
 * @author diego.fonseca
 * 
 */
public class CifrasControlEstadoFormularioAsignacionDTO implements EntidadDtoC2 {
    private int cantidad;
    private Date fechaAplicacion;
    private Long idDetalleCambioEstado;
    private int idEstado;
    private String nombreEstado;

    public CifrasControlEstadoFormularioAsignacionDTO() {

    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public void setIdDetalleCambioEstado(Long idDetalleCambioEstado) {
        this.idDetalleCambioEstado = idDetalleCambioEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public Long getIdDetalleCambioEstado() {
        return idDetalleCambioEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }
}
