package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author robert.bautista
 */
public class ConsEstadCivilConyPais implements Serializable {
    private static final long serialVersionUID = 1L;

    private String pais;

    private List<Integer> estadosCiviles;

    public ConsEstadCivilConyPais() {
        this.estadosCiviles = new ArrayList<Integer>(0);
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Integer> getEstadosCiviles() {
        return this.estadosCiviles;
    }

    public void setEstadosCiviles(List<Integer> estadosCiviles) {
        this.estadosCiviles = estadosCiviles;
    }

    @Override
    public String toString() {
        return "Pais: " + this.pais + " -- Estados civiles : " + this.estadosCiviles;
    }
}
