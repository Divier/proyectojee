package co.com.datatools.c2.dto.reglas;

public class PermiteCambioInfractorDTO {

    private Integer codigoOrganismo;
    private Integer codigoMedioImposicion;
    private boolean permiteCambioInfractor;

    public PermiteCambioInfractorDTO() {
        super();
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getCodigoMedioImposicion() {
        return codigoMedioImposicion;
    }

    public void setCodigoMedioImposicion(Integer codigoMedioImposicion) {
        this.codigoMedioImposicion = codigoMedioImposicion;
    }

    public boolean isPermiteCambioInfractor() {
        return permiteCambioInfractor;
    }

    public void setPermiteCambioInfractor(boolean permiteCambioInfractor) {
        this.permiteCambioInfractor = permiteCambioInfractor;
    }

}
