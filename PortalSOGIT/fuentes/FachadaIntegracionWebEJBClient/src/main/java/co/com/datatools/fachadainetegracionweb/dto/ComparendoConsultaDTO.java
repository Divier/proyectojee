package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ComparendoConsultaDTO")
public class ComparendoConsultaDTO implements Serializable {

    private static final long serialVersionUID = -7800142172984543129L;

    private String codigoIdentificacion;
    private Integer idIdentificacion;
    private String identificacion;
    private String numeroCitacion;
    private Integer anioInfraccion;
    private String placaVehiculo;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNumeroCitacion() {
        return numeroCitacion;
    }

    public void setNumeroCitacion(String numeroCitacion) {
        this.numeroCitacion = numeroCitacion;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }

    public void setCodigoIdentificacion(String codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }

    public Integer getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(Integer idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public Integer getAnioInfraccion() {
        return anioInfraccion;
    }

    public void setAnioInfraccion(Integer anioInfraccion) {
        this.anioInfraccion = anioInfraccion;
    }
}
