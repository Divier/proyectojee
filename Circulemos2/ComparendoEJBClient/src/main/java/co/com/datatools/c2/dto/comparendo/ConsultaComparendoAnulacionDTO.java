package co.com.datatools.c2.dto.comparendo;

import java.io.Serializable;
import java.util.Date;

public class ConsultaComparendoAnulacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer codigoOrganismo;
    private Date fechaInicial;
    private Date fechaFinal;
    private String direccion;

    // --- Constructor
    public ConsultaComparendoAnulacionDTO() {
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
