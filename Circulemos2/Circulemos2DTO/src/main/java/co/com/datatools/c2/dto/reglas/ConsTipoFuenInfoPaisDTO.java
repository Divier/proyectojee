package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

public class ConsTipoFuenInfoPaisDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigoPais;
    private Integer codigoProceso;
    private Integer codigoFuenteInformacion;

    public ConsTipoFuenInfoPaisDTO() {
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Integer getCodigoFuenteInformacion() {
        return codigoFuenteInformacion;
    }

    public void setCodigoFuenteInformacion(Integer codigoFuenteInformacion) {
        this.codigoFuenteInformacion = codigoFuenteInformacion;
    }

    public Integer getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(Integer codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    @Override
    public String toString() {
        return "País: " + this.codigoPais + " -- Código proceso: " + this.codigoProceso
                + " -- Código fuente información: " + this.codigoFuenteInformacion;
    }
}
