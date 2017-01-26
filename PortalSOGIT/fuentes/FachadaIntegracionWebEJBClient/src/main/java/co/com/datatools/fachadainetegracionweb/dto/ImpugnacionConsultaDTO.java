package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ImpugnacionConsultaDTO")
public class ImpugnacionConsultaDTO implements Serializable {

    private static final long serialVersionUID = -1751976677232398557L;

    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String numeroProceso;
    private Integer anioProceso;

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public Integer getAnioProceso() {
        return anioProceso;
    }

    public void setAnioProceso(Integer anioProceso) {
        this.anioProceso = anioProceso;
    }
}
