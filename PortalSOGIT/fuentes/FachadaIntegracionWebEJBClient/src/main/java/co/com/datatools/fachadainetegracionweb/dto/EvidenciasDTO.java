package co.com.datatools.fachadainetegracionweb.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author giovanni.velandia
 *
 */
@XmlRootElement(name = "DetalleGacetaDTO")
public class EvidenciasDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ruta;

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
