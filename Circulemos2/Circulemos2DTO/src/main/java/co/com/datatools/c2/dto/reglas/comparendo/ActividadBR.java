package co.com.datatools.c2.dto.reglas.comparendo;

import java.io.Serializable;

import co.com.datatools.c2.dto.reglas.ReglaOrganismoBR;

/**
 * @author robert.bautista - 15/08/2014
 */
public abstract class ActividadBR extends ReglaOrganismoBR implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Parametro de entrada
     */
    private Integer codigoProceso;

    /**
     * Parametro de salida
     */
    private Integer codigoActividad;

    public Integer getCodigoProceso() {
        return this.codigoProceso;
    }

    public void setCodigoProceso(Integer codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public Integer getCodigoActividad() {
        return this.codigoActividad;
    }

    public void setCodigoActividad(Integer codigoActividad) {
        this.codigoActividad = codigoActividad;
    }
}
