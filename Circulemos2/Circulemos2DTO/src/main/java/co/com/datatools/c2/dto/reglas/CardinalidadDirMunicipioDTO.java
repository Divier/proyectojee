package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

public class CardinalidadDirMunicipioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigoCardinalidad;
    private String codigoMunicipio;
    private Integer existeCardinalidad;

    public CardinalidadDirMunicipioDTO() {
        super();
    }

    public Integer getCodigoCardinalidad() {
        return codigoCardinalidad;
    }

    public void setCodigoCardinalidad(Integer codigoCardinalidad) {
        this.codigoCardinalidad = codigoCardinalidad;
    }

    public Integer getExisteCardinalidad() {
        return existeCardinalidad;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public void setExisteCardinalidad(Integer existeCardinalidad) {
        this.existeCardinalidad = existeCardinalidad;
    }

}
