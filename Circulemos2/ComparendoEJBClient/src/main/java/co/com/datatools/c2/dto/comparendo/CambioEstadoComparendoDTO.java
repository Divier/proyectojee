package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;

import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;

/**
 * DTO utilitario para cambiar estado de cartera - CU_CIR20_DAT_CAR_031
 * 
 * @author rodrigo.cruz
 * 
 */
public class CambioEstadoComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date fechaCambio;
    private EnumActividad actividad;
    private EnumEstadoComparendo estadoComparendo;
    private int codigoOrganismo;
    private String numeroComparendo;
    private boolean actualizarToAnterior;

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public EnumActividad getActividad() {
        return actividad;
    }

    public void setActividad(EnumActividad actividad) {
        this.actividad = actividad;
    }

    public EnumEstadoComparendo getEstadoComparendo() {
        return estadoComparendo;
    }

    public void setEstadoComparendo(EnumEstadoComparendo estadoComparendo) {
        this.estadoComparendo = estadoComparendo;
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public boolean isActualizarToAnterior() {
        return actualizarToAnterior;
    }

    public void setActualizarToAnterior(boolean actualizarToAnterior) {
        this.actualizarToAnterior = actualizarToAnterior;
    }
}