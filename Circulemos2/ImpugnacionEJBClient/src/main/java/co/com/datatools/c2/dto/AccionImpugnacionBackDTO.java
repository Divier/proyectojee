package co.com.datatools.c2.dto;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Thu Jun 30 17:54:17 COT 2016
 */
public class AccionImpugnacionBackDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TrazabilidadProcesoDTO trazabilidadProceso;
    private TipoAccionBackDTO tipoAccionBack;
    private CaracteristicaPruebaDTO caracteristicaPrueba;
    private String descripcion;
    private Date fechaAccion;

    // --- Constructor
    public AccionImpugnacionBackDTO() {
    }

    public AccionImpugnacionBackDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrazabilidadProcesoDTO getTrazabilidadProceso() {
        return this.trazabilidadProceso;
    }

    public void setTrazabilidadProceso(TrazabilidadProcesoDTO trazabilidadProceso) {
        this.trazabilidadProceso = trazabilidadProceso;
    }

    public TipoAccionBackDTO getTipoAccionBack() {
        return this.tipoAccionBack;
    }

    public void setTipoAccionBack(TipoAccionBackDTO tipoAccionBack) {
        this.tipoAccionBack = tipoAccionBack;
    }

    public CaracteristicaPruebaDTO getCaracteristicaPrueba() {
        return this.caracteristicaPrueba;
    }

    public void setCaracteristicaPrueba(CaracteristicaPruebaDTO caracteristicaPrueba) {
        this.caracteristicaPrueba = caracteristicaPrueba;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

}
