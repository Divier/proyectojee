package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FinanciacionConsultaDTO")
public class FinanciacionConsultaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigoIdentificacion;
    private Integer idIdentificacion;
    private String identificacion;
    private Long numeroFinanciacion;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public Long getNumeroFinanciacion() {
        return numeroFinanciacion;
    }

    public void setNumeroFinanciacion(Long numeroFinanciacion) {
        this.numeroFinanciacion = numeroFinanciacion;
    }

}
