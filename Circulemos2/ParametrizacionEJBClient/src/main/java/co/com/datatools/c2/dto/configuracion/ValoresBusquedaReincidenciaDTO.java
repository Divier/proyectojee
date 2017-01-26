package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO que se encarga de representar la configuracion para el codigo 013 - Cantidad de dias para consultar reincidencia e Infracciones a evaluar
 * reincidencia
 * 
 * @author rodrigo.cruz
 */
public class ValoresBusquedaReincidenciaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Entrada
     */
    // <codigo>codigoOrganismo</codigo>
    // <descripcion>Codigo del organismo de transito</descripcion>
    private List<String> codigoOrganismo;
    // <codigo>tipoReincidencia</codigo>
    // <descripcion>Tipo de reincidencia a calcular</descripcion>
    private List<String> tipoReincidencia;

    /*
     * Salida
     */
    // <codigo>cantidadDias</codigo>
    // <descripcion>Cantidad de dias calendario para la consulta de reincidencia</descripcion>
    private BigDecimal cantidadDias;
    // <codigo>codigoInfraccion</codigo>
    // <descripcion>Codigo de la infraccion a configurar el incremento</descripcion>
    private List<String> codigoInfraccion;

    public List<String> getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(List<String> codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public List<String> getTipoReincidencia() {
        return tipoReincidencia;
    }

    public void setTipoReincidencia(List<String> tipoReincidencia) {
        this.tipoReincidencia = tipoReincidencia;
    }

    public BigDecimal getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(BigDecimal cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public List<String> getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(List<String> codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

}
