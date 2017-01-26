package co.com.datatools.fachadainetegracionweb.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FechaPublicacionDTO")
public class FechaPublicacionDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;

    private Date fechaPublicacion;

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

}
