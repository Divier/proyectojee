package co.com.datatools.c2.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contiene la información necesaria de un comparendo para calcular reincidencias.
 * 
 * @author luis.forero
 * @version 1.0
 * @created 14-abr-2016 11:09:35 a.m.
 */
public class HistoricoComparendoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Codigo de la infraccion
     */
    private String codigoInfraccion;
    /**
     * Codigo del organismo de transito
     */
    private Integer codigoOrganismo;
    /**
     * Fecha de imposicion del comparendo
     */
    private Date fechaImposicion;
    /**
     * Identificador de la infraccion
     */
    private Integer idInfraccion;
    /**
     * Concatenado los campos <i>Apellido 1 del Infractor</i> + <i>Apellido 2 del Infractor</i> + <i>Nombre 1 del Infractor</i> + <i>Nombre 2 del
     * Infractor</i>
     */
    private String nombreInfractor;
    /**
     * Numero del comparendo
     */
    private String numeroComparendo;
    /**
     * Valor de la infraccion
     */
    private BigDecimal valorInfraccion;

    public HistoricoComparendoDTO() {

    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public Integer getIdInfraccion() {
        return idInfraccion;
    }

    public void setIdInfraccion(Integer idInfraccion) {
        this.idInfraccion = idInfraccion;
    }

    public String getNombreInfractor() {
        return nombreInfractor;
    }

    public void setNombreInfractor(String nombreInfractor) {
        this.nombreInfractor = nombreInfractor;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public BigDecimal getValorInfraccion() {
        return valorInfraccion;
    }

    public void setValorInfraccion(BigDecimal valorInfraccion) {
        this.valorInfraccion = valorInfraccion;
    }

}