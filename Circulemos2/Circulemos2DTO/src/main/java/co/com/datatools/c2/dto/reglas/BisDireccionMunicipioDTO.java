package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

public class BisDireccionMunicipioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigoMunicipio;
    private String complementoBisRegEx;

    public BisDireccionMunicipioDTO() {
        super();
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getComplementoBisRegEx() {
        return complementoBisRegEx;
    }

    public void setComplementoBisRegEx(String complementoBisRegEx) {
        this.complementoBisRegEx = complementoBisRegEx;
    }

}
