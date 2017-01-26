package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

public class FormatoCodigoBarrasCIA implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    private String formatoCodigoBarras;

    public FormatoCodigoBarrasCIA() {
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getFormatoCodigoBarras() {
        return formatoCodigoBarras;
    }

    public void setFormatoCodigoBarras(String formatoCodigoBarras) {
        this.formatoCodigoBarras = formatoCodigoBarras;
    }
}
