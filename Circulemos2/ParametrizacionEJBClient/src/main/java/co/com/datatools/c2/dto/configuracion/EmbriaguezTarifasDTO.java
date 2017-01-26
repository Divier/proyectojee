package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * DTO que se encarga de representar la configuracion para el codigo 012 - Embriaguez - Tarifas
 * 
 * @author giovanni.velandia 2015-12-14
 * 
 */
public class EmbriaguezTarifasDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * Entrada
     */
    // <codigo>tipoServicio</codigo>
    // Tipo de servicio del automotor
    private List<String> tipoServicio;
    // <codigo>codigoInfraccion</codigo>
    // Código Infracción
    private String codigoInfraccion;
    // <codigo>gradoAlcohol</codigo>
    // Grado de alcoholemia
    private BigDecimal gradoAlcohol;
    // <codigo>numeroReincidencia</codigo>
    // Número de veces que ha cometido la infracción
    private BigDecimal numeroReincidencia;
    // <codigo>inicioPeriodoFecha</codigo>
    // Fecha desde cuando rige la tarifa para la configuración
    private Date inicioPeriodoFecha;
    // <codigo>finPeriodoFecha</codigo>
    // Fecha hasta cuando rige la tarifa para la configuración
    private Date finPeriodoFecha;

    /*
     * Salida
     */
    // Valor a pagar por la infracción relacionada con embriaguez.
    private BigDecimal tarifa;

    public List<String> getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(List<String> tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public BigDecimal getGradoAlcohol() {
        return gradoAlcohol;
    }

    public void setGradoAlcohol(BigDecimal gradoAlcohol) {
        this.gradoAlcohol = gradoAlcohol;
    }

    public BigDecimal getNumeroReincidencia() {
        return numeroReincidencia;
    }

    public void setNumeroReincidencia(BigDecimal numeroReincidencia) {
        this.numeroReincidencia = numeroReincidencia;
    }

    public Date getInicioPeriodoFecha() {
        return inicioPeriodoFecha;
    }

    public void setInicioPeriodoFecha(Date inicioPeriodoFecha) {
        this.inicioPeriodoFecha = inicioPeriodoFecha;
    }

    public Date getFinPeriodoFecha() {
        return finPeriodoFecha;
    }

    public void setFinPeriodoFecha(Date finPeriodoFecha) {
        this.finPeriodoFecha = finPeriodoFecha;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

}
