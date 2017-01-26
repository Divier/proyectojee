package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Se encarga de devolver toda la trazabilidiad de los procesos para un comparendo
 * 
 * @author giovanni.velandia
 * 
 */
public class ComparendoProcesoTrazabilidadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<TipoProcesoTrazabilidadDTO> tipoProcesoTrazabilidadDTOs;

    public List<TipoProcesoTrazabilidadDTO> getTipoProcesoTrazabilidadDTOs() {
        return tipoProcesoTrazabilidadDTOs;
    }

    public void setTipoProcesoTrazabilidadDTOs(List<TipoProcesoTrazabilidadDTO> tipoProcesoTrazabilidadDTOs) {
        this.tipoProcesoTrazabilidadDTOs = tipoProcesoTrazabilidadDTOs;
    }
}
