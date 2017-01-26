package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProcesoImpugnacionOtDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigoOrganismo;
    private List<Integer> listIdActividades;

    public ProcesoImpugnacionOtDTO() {
        listIdActividades = new ArrayList<Integer>(0);
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public List<Integer> getListIdActividades() {
        return listIdActividades;
    }

    public void setListIdActividades(List<Integer> listIdActividades) {
        this.listIdActividades = listIdActividades;
    }

}
