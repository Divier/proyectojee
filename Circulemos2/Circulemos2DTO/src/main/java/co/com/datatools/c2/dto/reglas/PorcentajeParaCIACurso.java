package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class PorcentajeParaCIACurso implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    @NotNull
    private Double porcentajeCIA;

    public PorcentajeParaCIACurso() {
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Double getPorcentajeCIA() {
        return porcentajeCIA;
    }

    public void setPorcentajeCIA(Double porcentajeCIA) {
        this.porcentajeCIA = porcentajeCIA;
    }
}
