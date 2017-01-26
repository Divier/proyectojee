package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Filtros que recibe el web service
 * 
 * @author diego.fonseca
 * 
 */
public abstract class SolicitudNumeroComparendoAbstractDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer CodigoOrganismo;
    private XMLGregorianCalendar fechaImposicion;
    private XMLGregorianCalendar horaImposicion;
    private String identificadorVehiculo;
    private String numeroDocumento;
    private String placaAgente;
    private String placaVehiculo;
    private String tipoDocumento;
    private String tipoResponsable;

    public Integer getCodigoOrganismo() {
        return CodigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        CodigoOrganismo = codigoOrganismo;
    }

    public XMLGregorianCalendar getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(XMLGregorianCalendar fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public XMLGregorianCalendar getHoraImposicion() {
        return horaImposicion;
    }

    public void setHoraImposicion(XMLGregorianCalendar horaImposicion) {
        this.horaImposicion = horaImposicion;
    }

    public String getIdentificadorVehiculo() {
        return identificadorVehiculo;
    }

    public void setIdentificadorVehiculo(String identificadorVehiculo) {
        this.identificadorVehiculo = identificadorVehiculo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPlacaAgente() {
        return placaAgente;
    }

    public void setPlacaAgente(String placaAgente) {
        this.placaAgente = placaAgente;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(String tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

}
