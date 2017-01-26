package co.com.datatools.c2.managed_bean.comparendo.compraendo_proceso;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.adaptador.dto.ComparendoProcesoTrazabilidadDTO;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class ComparendoProcesoFL implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "comparendoProcesoFL";

    private Long cicomparendo;
    private List<ComparendoProcesoTrazabilidadDTO> comparendoProcesoTrazabilidadDTOs;

    public Long getCicomparendo() {
        return cicomparendo;
    }

    public void setCicomparendo(Long cicomparendo) {
        this.cicomparendo = cicomparendo;
    }

    public List<ComparendoProcesoTrazabilidadDTO> getComparendoProcesoTrazabilidadDTOs() {
        return comparendoProcesoTrazabilidadDTOs;
    }

    public void setComparendoProcesoTrazabilidadDTOs(
            List<ComparendoProcesoTrazabilidadDTO> comparendoProcesoTrazabilidadDTOs) {
        this.comparendoProcesoTrazabilidadDTOs = comparendoProcesoTrazabilidadDTOs;
    }

}
