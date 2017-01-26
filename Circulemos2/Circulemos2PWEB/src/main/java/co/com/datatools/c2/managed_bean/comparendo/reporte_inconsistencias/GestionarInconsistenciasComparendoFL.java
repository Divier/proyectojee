package co.com.datatools.c2.managed_bean.comparendo.reporte_inconsistencias;

import java.util.List;

import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.managed_bean.comparendo.administracion.colombia.AdminComparendoFL;

/**
 * 
 * @author giovanni.velandia
 * 
 */
public class GestionarInconsistenciasComparendoFL extends AdminComparendoFL {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String NOMBRE_BEAN = "gestionarInconsistenciasComparendoFL";

    // Errores del procesamiento
    private List<DetalleProcesamientoDTO> detalleProcesamientos;

    public GestionarInconsistenciasComparendoFL() {
    }

    public List<DetalleProcesamientoDTO> getDetalleProcesamientos() {
        return detalleProcesamientos;
    }

    public void setDetalleProcesamientos(List<DetalleProcesamientoDTO> detalleProcesamientos) {
        this.detalleProcesamientos = detalleProcesamientos;
    }
}
