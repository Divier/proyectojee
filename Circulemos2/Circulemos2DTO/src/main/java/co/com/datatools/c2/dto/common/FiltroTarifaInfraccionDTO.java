package co.com.datatools.c2.dto.common;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * Parametro de consulta para tarifas de infracciones
 * 
 * @author rodrigo.cruz
 * 
 */
public class FiltroTarifaInfraccionDTO implements EntidadDtoC2 {

    private static final long serialVersionUID = 1L;

    private List<String> codigosInfraccion;
    private Date fechaFinalVigencia;
    private Date fechaInicialVigencia;
    private List<String> tiposInfraccion;

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<String> getCodigosInfraccion() {
        if (this.codigosInfraccion == null) {
            this.codigosInfraccion = new java.util.ArrayList<>(1);
        }
        return this.codigosInfraccion;
    }

    public void setCodigosInfraccion(List<String> codigosInfraccion) {
        this.codigosInfraccion = codigosInfraccion;
    }

    public Date getFechaFinalVigencia() {
        return fechaFinalVigencia;
    }

    public void setFechaFinalVigencia(Date fechaFinalVigencia) {
        this.fechaFinalVigencia = fechaFinalVigencia;
    }

    public Date getFechaInicialVigencia() {
        return fechaInicialVigencia;
    }

    public void setFechaInicialVigencia(Date fechaInicialVigencia) {
        this.fechaInicialVigencia = fechaInicialVigencia;
    }

    /**
     * Retorna la lista de objetos, si es nula crea una nueva, la asigna y la retorna
     */
    public List<String> getTiposInfraccion() {
        if (this.tiposInfraccion == null) {
            this.tiposInfraccion = new java.util.ArrayList<>(1);
        }
        return this.tiposInfraccion;
    }

    public void setTiposInfraccion(List<String> tiposInfraccion) {
        this.tiposInfraccion = tiposInfraccion;
    }

}
