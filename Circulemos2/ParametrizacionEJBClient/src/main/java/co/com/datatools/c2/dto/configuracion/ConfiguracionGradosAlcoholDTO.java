package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * DTO que se encarga de representar la configuracion para el codigo 006 - Rango de etanol para un grado de alcoholemia en una fecha de vigencia
 * 
 * @author rodrigo.cruz (2015-10-09)
 * 
 */
public class ConfiguracionGradosAlcoholDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Entrada
     */
    // <codigo>codigoOrganismo</codigo>
    // <descripcion>Codigo del organismo de transito</descripcion>
    private List<String> codigoOrganismo;
    // <codigo>gradoAlcohol</codigo>
    // <descripcion>Grado de alcoholemia</descripcion>
    private BigDecimal gradoAlcohol;
    // <codigo>inicioPeriodoFecha</codigo>
    // <descripcion>Fecha desde cuando rige la configuracion para el grado de alcohol ingresado</descripcion>
    private Date inicioPeriodoFecha;
    // <codigo>finPeriodoFecha</codigo>
    // <descripcion>Fecha hasta cuando rige la configuracion para el grado de alcohol ingresado</descripcion>
    private Date finPeriodoFecha;

    /*
     * Salida
     */
    // <codigo>minimo</codigo>
    // <descripcion>Cantidad minima de miligramos de etanol que pertenecen al grado indicado</descripcion>
    private BigDecimal minimo;
    // <codigo>maximo</codigo>
    // <descripcion>Cantidad maxima de miligramos de etanol que pertenecen al grado indicado</descripcion>
    private BigDecimal maximo;

    public List<String> getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(List<String> codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public BigDecimal getGradoAlcohol() {
        return gradoAlcohol;
    }

    public void setGradoAlcohol(BigDecimal gradoAlcohol) {
        this.gradoAlcohol = gradoAlcohol;
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

    public BigDecimal getMinimo() {
        return minimo;
    }

    public void setMinimo(BigDecimal minimo) {
        this.minimo = minimo;
    }

    public BigDecimal getMaximo() {
        return maximo;
    }

    public void setMaximo(BigDecimal maximo) {
        this.maximo = maximo;
    }

}
