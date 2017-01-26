package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.Date;

import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumTipoProceso;
import co.com.datatools.c2.numeraciones.EnumConsecutivo;

public class RegistraProcesoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // --- Atributos
    private String observacion;
    private EnumTipoProceso tipoProceso;
    private EnumEstadoProceso estado;
    private EnumConsecutivo consecutivo;
    private Date fechaInicio;

    // --- Constructor
    public RegistraProcesoDTO() {
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public EnumTipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(EnumTipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public EnumEstadoProceso getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoProceso estado) {
        this.estado = estado;
    }

    public EnumConsecutivo getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(EnumConsecutivo consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

}