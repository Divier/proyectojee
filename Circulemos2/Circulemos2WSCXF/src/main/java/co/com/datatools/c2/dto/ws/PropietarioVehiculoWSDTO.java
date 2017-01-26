package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Datos del propietario retornados por el web service
 * 
 * @author diego.fonseca
 * 
 */
public class PropietarioVehiculoWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismoLicenciaTransito;
    private String numeroDocumento;
    private String numeroLicenciaTransito;
    private String primerApellido;
    private String primerNombre;
    private String RazonSocial;
    private String segundoApellido;
    private String segundoNombre;
    private String tipoDocumento;

    public Integer getCodigoOrganismoLicenciaTransito() {
        return codigoOrganismoLicenciaTransito;
    }

    public void setCodigoOrganismoLicenciaTransito(Integer codigoOrganismoLicenciaTransito) {
        this.codigoOrganismoLicenciaTransito = codigoOrganismoLicenciaTransito;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroLicenciaTransito() {
        return numeroLicenciaTransito;
    }

    public void setNumeroLicenciaTransito(String numeroLicenciaTransito) {
        this.numeroLicenciaTransito = numeroLicenciaTransito;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
