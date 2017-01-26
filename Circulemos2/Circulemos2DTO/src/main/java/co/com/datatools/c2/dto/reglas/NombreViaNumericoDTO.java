package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

public class NombreViaNumericoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigoTipoVia;
    private String codigoMunicipio;
    private Integer nombreViaNumerico;

    public NombreViaNumericoDTO() {
        super();
    }

    public Integer getCodigoTipoVia() {
        return codigoTipoVia;
    }

    public void setCodigoTipoVia(Integer codigoTipoVia) {
        this.codigoTipoVia = codigoTipoVia;
    }

    public Integer getNombreViaNumerico() {
        return nombreViaNumerico;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public void setNombreViaNumerico(Integer nombreViaNumerico) {
        this.nombreViaNumerico = nombreViaNumerico;
    }

}
