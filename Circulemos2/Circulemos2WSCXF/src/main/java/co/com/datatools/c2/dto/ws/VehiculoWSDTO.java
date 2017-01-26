package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Datos del vehiculo retornados por el web service
 * 
 * @author diego.fonseca
 * 
 */
public class VehiculoWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;
    private String claseServicio;
    private Integer codigoOrganismoMatricula;
    private String identificadorVehiculo;
    private String modalidad;
    private String numeroDocumentoEmpresa;//
    private String placaVehiculo;
    private String radioAccion;
    private String razonSocialEmpresa;
    private Integer tarjetaOperacion;
    private String tipoDocumentoEmpresa;
    private String tipoTransportePasajeros;//
    private String tipoVehiculo;

    public String getClaseServicio() {
        return claseServicio;
    }

    public void setClaseServicio(String claseServicio) {
        this.claseServicio = claseServicio;
    }

    public Integer getCodigoOrganismoMatricula() {
        return codigoOrganismoMatricula;
    }

    public void setCodigoOrganismoMatricula(Integer codigoOrganismoMatricula) {
        this.codigoOrganismoMatricula = codigoOrganismoMatricula;
    }

    public String getIdentificadorVehiculo() {
        return identificadorVehiculo;
    }

    public void setIdentificadorVehiculo(String identificadorVehiculo) {
        this.identificadorVehiculo = identificadorVehiculo;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getNumeroDocumentoEmpresa() {
        return numeroDocumentoEmpresa;
    }

    public void setNumeroDocumentoEmpresa(String numeroDocumentoEmpresa) {
        this.numeroDocumentoEmpresa = numeroDocumentoEmpresa;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getRadioAccion() {
        return radioAccion;
    }

    public void setRadioAccion(String radioAccion) {
        this.radioAccion = radioAccion;
    }

    public String getRazonSocialEmpresa() {
        return razonSocialEmpresa;
    }

    public void setRazonSocialEmpresa(String razonSocialEmpresa) {
        this.razonSocialEmpresa = razonSocialEmpresa;
    }

    public Integer getTarjetaOperacion() {
        return tarjetaOperacion;
    }

    public void setTarjetaOperacion(Integer tarjetaOperacion) {
        this.tarjetaOperacion = tarjetaOperacion;
    }

    public String getTipoDocumentoEmpresa() {
        return tipoDocumentoEmpresa;
    }

    public void setTipoDocumentoEmpresa(String tipoDocumentoEmpresa) {
        this.tipoDocumentoEmpresa = tipoDocumentoEmpresa;
    }

    public String getTipoTransportePasajeros() {
        return tipoTransportePasajeros;
    }

    public void setTipoTransportePasajeros(String tipoTransportePasajeros) {
        this.tipoTransportePasajeros = tipoTransportePasajeros;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

}
