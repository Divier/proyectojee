package co.com.datatools.fachadainetegracionweb.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author giovanni.velandia
 *
 */
@XmlRootElement(name = "GacetaDTO")
public class GacetaDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaPublicacion;
    private String numero;
    private Date fechaPeriodoInicio;
    private Date fechaPeriodoFin;

    // --- Constructor
    public GacetaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaPeriodoInicio() {
        return fechaPeriodoInicio;
    }

    public void setFechaPeriodoInicio(Date fechaPeriodoInicio) {
        this.fechaPeriodoInicio = fechaPeriodoInicio;
    }

    public Date getFechaPeriodoFin() {
        return fechaPeriodoFin;
    }

    public void setFechaPeriodoFin(Date fechaPeriodoFin) {
        this.fechaPeriodoFin = fechaPeriodoFin;
    }
}
