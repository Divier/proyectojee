package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contiene el resultado de la consulta de comparendos por notificaci�n.
 * 
 * @author julio.pinzon
 * 
 */
public class ResultadoConsultaNotificacionComparendoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    /**
     * C�digo de la infracci�n del comparendo.
     */
    private String codigoInfraccion;

    /**
     * Contiene el estado del comparendo.
     */
    private String estadoComparendo;

    /**
     * Corresponde al cicomparendo.
     */
    private long idComparendo;

    /**
     * Contiene el primer nombre y primer apellido del infractor.
     */
    private String nombreInfractor;

    /**
     * N�mero del comparendo
     */
    private String numeroComparendo;

    /**
     * Contiene el n�mero de documento del infractor.
     */
    private String numeroDocumentoInfractor;

    /**
     * Tipo de documento del infractor.
     */
    private String tipoDocumentoInfractor;

    /**
     * Contiene el valor de la infracci�n.
     */
    private BigDecimal valorInfraccion;

    /**
     * fechaInfraccion
     */
    private Date fechaInfraccion;

    private String tipoNotificacion;

    // --- Constructor
    public ResultadoConsultaNotificacionComparendoDTO() {
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public String getEstadoComparendo() {
        return estadoComparendo;
    }

    public void setEstadoComparendo(String estadoComparendo) {
        this.estadoComparendo = estadoComparendo;
    }

    public long getIdComparendo() {
        return idComparendo;
    }

    public void setIdComparendo(long idComparendo) {
        this.idComparendo = idComparendo;
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

    public String getNumeroDocumentoInfractor() {
        return numeroDocumentoInfractor;
    }

    public void setNumeroDocumentoInfractor(String numeroDocumentoInfractor) {
        this.numeroDocumentoInfractor = numeroDocumentoInfractor;
    }

    public String getTipoDocumentoInfractor() {
        return tipoDocumentoInfractor;
    }

    public void setTipoDocumentoInfractor(String tipoDocumentoInfractor) {
        this.tipoDocumentoInfractor = tipoDocumentoInfractor;
    }

    public BigDecimal getValorInfraccion() {
        return valorInfraccion;
    }

    public void setValorInfraccion(BigDecimal valorInfraccion) {
        this.valorInfraccion = valorInfraccion;
    }

    public Date getFechaInfraccion() {
        return fechaInfraccion;
    }

    public void setFechaInfraccion(Date fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

}