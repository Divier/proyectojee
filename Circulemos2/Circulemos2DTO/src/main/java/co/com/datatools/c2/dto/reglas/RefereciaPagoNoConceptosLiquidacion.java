package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author pedro.moncada
 * 
 */
public class RefereciaPagoNoConceptosLiquidacion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer codigoOrganismo;
    private List<Integer> listIdConceptos;

    public RefereciaPagoNoConceptosLiquidacion() {
        listIdConceptos = new ArrayList<Integer>(0);
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public List<Integer> getListIdConceptos() {
        return listIdConceptos;
    }

    public void setListIdConceptos(List<Integer> listIdConceptos) {
        this.listIdConceptos = listIdConceptos;
    }

}
