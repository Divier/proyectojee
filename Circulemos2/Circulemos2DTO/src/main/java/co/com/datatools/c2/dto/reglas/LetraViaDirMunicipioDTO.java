package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

public class LetraViaDirMunicipioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigoMunicipio;
    private String letraViaRegEx;

    public LetraViaDirMunicipioDTO() {
        super();
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getLetraViaRegEx() {
        return letraViaRegEx;
    }

    public void setLetraViaRegEx(String letraViaRegEx) {
        this.letraViaRegEx = letraViaRegEx;
    }

}
