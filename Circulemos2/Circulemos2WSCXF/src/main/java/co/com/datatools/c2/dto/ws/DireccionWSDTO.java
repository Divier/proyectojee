package co.com.datatools.c2.dto.ws;

import co.com.datatools.c2.dto.AbstractDTO;

/**
 * Datos de la direccion del comparendo recibidos a traves del WebService
 * 
 * @author luis.forero(2015-12-03) giovanni.velandia (mod 25-11-2016)
 */
public class DireccionWSDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    private String direccionBasica;
    private String codigoPais;
    private String codigoDepartamento;
    private String codigoMunicipio;
    private String codigoLocalidad;

    // Campos para el pais de ecuardor
    private String codigoProvincia;
    private String codigoCanton;
    private String codigoParroquia;

    // Inicio - SET y GET modificados
    // ** Provincia = Departamento
    public String getCodigoProvincia() {
        codigoProvincia = codigoDepartamento;
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoDepartamento = codigoProvincia;
    }

    // ** Canton = Municipio
    public String getCodigoCanton() {
        codigoCanton = codigoMunicipio;
        return codigoCanton;
    }

    public void setCodigoCanton(String codigoCanton) {
        this.codigoMunicipio = codigoCanton;
    }

    // ** Parroquia = Localidad
    public String getCodigoParroquia() {
        codigoParroquia = codigoLocalidad;
        return codigoParroquia;
    }

    public void setCodigoParroquia(String codigoParroquia) {
        this.codigoLocalidad = codigoParroquia;
    }
    // Fin - SET y GET modificados

    public String getDireccionBasica() {
        return direccionBasica;
    }

    public void setDireccionBasica(String direccionBasica) {
        this.direccionBasica = direccionBasica;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(String codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }
}