package co.com.datatools.c2.dto.configuracion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO que se encarga de representar la configuracion para el codigo 009 - Configuración de tipos de documento de identidad y formatos para sus
 * números de identificación
 * 
 * @author giovanni.velandia 09-11-2015
 * @author mod julio.pinzon 11-03-2016
 * 
 */
public class AsociaDocumentoIdentidadFormatoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /*
     * Entrada
     */
    // <codigo>tipoIdentificacion</codigo>
    private List<String> tipoIdentificacion;
    // <codigo>inicioPeriodoFecha</codigo>
    // <descripcion>Fecha desde cuando rige el formato del documento</descripcion>
    private Date inicioPeriodoVigencia;
    // <codigo>finPeriodoFecha</codigo>
    // <descripcion>Fecha hasta cuando rige el formato del documento</descripcion>
    private Date finPeriodoVigencia;

    /*
     * Salida
     */
    // <codigo>longitudMinima</codigo>
    // <descripcion>Valor mímino de caracteres que debe traer el tipo de documento de identificación</descripcion>
    private BigDecimal longitudMinima;
    // <codigo>longitudMaxima</codigo>
    // <descripcion>Valor máximo de caracteres que debe traer el tipo de documento de identificación</descripcion>
    private BigDecimal longitudMaxima;
    // <codigo>tipoFormato</codigo>
    // <descripcion>Formato empleado para el número de documento de identidad.</descripcion>
    private List<String> tipoFormato;

    public List<String> getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(List<String> tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public List<String> getTipoFormato() {
        return tipoFormato;
    }

    public void setTipoFormato(List<String> tipoFormato) {
        this.tipoFormato = tipoFormato;
    }

    public Date getInicioPeriodoVigencia() {
        return inicioPeriodoVigencia;
    }

    public void setInicioPeriodoVigencia(Date inicioPeriodoVigencia) {
        this.inicioPeriodoVigencia = inicioPeriodoVigencia;
    }

    public Date getFinPeriodoVigencia() {
        return finPeriodoVigencia;
    }

    public void setFinPeriodoVigencia(Date finPeriodoVigencia) {
        this.finPeriodoVigencia = finPeriodoVigencia;
    }

    public BigDecimal getLongitudMinima() {
        return longitudMinima;
    }

    public void setLongitudMinima(BigDecimal longitudMinima) {
        this.longitudMinima = longitudMinima;
    }

    public BigDecimal getLongitudMaxima() {
        return longitudMaxima;
    }

    public void setLongitudMaxima(BigDecimal longitudMaxima) {
        this.longitudMaxima = longitudMaxima;
    }

}
