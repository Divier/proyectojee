package co.com.datatools.c2.dto.comparendo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * HU_CIR20_DAT_COM_066: Contiene la informacion necesaria de un comparendo para calcular reincidencias.
 * 
 * @author rodrigo.cruz
 * 
 */
public class HistoricoComparendoDTO {

    private String numeroComparendo;
    private Integer codigoOrganismo;
    private Date fechaImposicion;
    private String nombreInfractor;
    private String codigoInfraccion;
    private Integer idInfraccion;
    private BigDecimal valorInfraccion;

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
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

    public String getNombreInfractor() {
        return nombreInfractor;
    }

    public void setNombreInfractor(String nombreInfractor) {
        this.nombreInfractor = nombreInfractor;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public Integer getIdInfraccion() {
        return idInfraccion;
    }

    public void setIdInfraccion(Integer idInfraccion) {
        this.idInfraccion = idInfraccion;
    }

    public BigDecimal getValorInfraccion() {
        return valorInfraccion;
    }

    public void setValorInfraccion(BigDecimal valorInfraccion) {
        this.valorInfraccion = valorInfraccion;
    }

}
