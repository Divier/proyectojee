package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;

public class LiquidarTarifaInfraccionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaLiquidacion;
    private Integer gradoAlcoholemia;
    private Integer idClaseServicio;
    private String codigoInfraccion;
    private Boolean niegaPruebaAlcoholemia;
    private Integer numeroReincidencias;

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public Integer getGradoAlcoholemia() {
        return gradoAlcoholemia;
    }

    public void setGradoAlcoholemia(Integer gradoAlcoholemia) {
        this.gradoAlcoholemia = gradoAlcoholemia;
    }

    public Integer getIdClaseServicio() {
        return idClaseServicio;
    }

    public void setIdClaseServicio(Integer idClaseServicio) {
        this.idClaseServicio = idClaseServicio;
    }

    public Boolean getNiegaPruebaAlcoholemia() {
        return niegaPruebaAlcoholemia;
    }

    public void setNiegaPruebaAlcoholemia(Boolean niegaPruebaAlcoholemia) {
        this.niegaPruebaAlcoholemia = niegaPruebaAlcoholemia;
    }

    public Integer getNumeroReincidencias() {
        return numeroReincidencias;
    }

    public void setNumeroReincidencias(Integer numeroReincidencias) {
        this.numeroReincidencias = numeroReincidencias;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

}
