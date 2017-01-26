package co.com.datatools.c2.dto.reglas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto para regla q consulta el listado de id actividades q dan de baja un comparendo (derogan, exoneran)
 * 
 * @author felipe.martinez
 */
public class ConsActividadesBajaComparendoDTO implements Serializable {

    private static final long serialVersionUID = 2538428259180652064L;

    /**
     * Parametro Entrada
     */
    private Integer codigoOrganismo;

    /**
     * Elemento de salida de drools
     */
    private List<Integer> listIdActividad;

    public ConsActividadesBajaComparendoDTO() {
        super();
        listIdActividad = new ArrayList<>(0);
    }

    public ConsActividadesBajaComparendoDTO(Integer codigoOrganismo) {
        this();
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(Integer codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public List<Integer> getListIdActividad() {
        return listIdActividad;
    }

    public void setListIdActividad(List<Integer> listIdActividad) {
        this.listIdActividad = listIdActividad;
    }

}
