package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;

public class SolicitudNumeroComparendoDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer CodigoOrganismo;
    private Date fechaImposicion;
    private Date horaImposicion;
    private String identificadorVehiculo;
    private String numeroDocumento;
    private String placaAgente;
    private String placaVehiculo;
    private Integer tipoDocumento;
    private Integer tipoResponsable;

    public Integer getCodigoOrganismo() {
        return CodigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        CodigoOrganismo = codigoOrganismo;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public Date getHoraImposicion() {
        return horaImposicion;
    }

    public void setHoraImposicion(Date horaImposicion) {
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

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(Integer tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

}
