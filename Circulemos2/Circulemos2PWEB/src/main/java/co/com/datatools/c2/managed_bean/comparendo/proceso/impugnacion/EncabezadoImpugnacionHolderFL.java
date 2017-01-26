package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.Date;

import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * Managed Bean para el manejo del encabezado de impugnacion
 * 
 * @author dixon.alvarez 2016-06-10
 * 
 */
public class EncabezadoImpugnacionHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "encabezadoImpugnacionHolderFL";

    private String numeroProceso;
    private Date fechaApertura;
    private String numeroComparendo;
    private String codigoInfraccion;
    private String documentoInfractor;
    private String nombreInfractor;
    private String placa;
    private String descripcionInfraccion;
    private String numeroCitacion;

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public String getNombreInfractor() {
        return nombreInfractor;
    }

    public void setNombreInfractor(String nombreInfractor) {
        this.nombreInfractor = nombreInfractor;
    }

    public String getDocumentoInfractor() {
        return documentoInfractor;
    }

    public void setDocumentoInfractor(String documentoInfractor) {
        this.documentoInfractor = documentoInfractor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }
    
    

}
