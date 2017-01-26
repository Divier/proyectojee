package co.com.datatools.c2.adaptador.dto;

import java.io.Serializable;
import java.util.List;

import co.com.datatools.c2.dto.ProcesoDTO;

/**
 * Se encarga de devolver toda la trazabilidiad de los procesos para un comparendo
 * 
 * @author giovanni.velandia
 * 
 */
public class TipoProcesoTrazabilidadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ProcesoDTO> procesoDTOs;

    public List<ProcesoDTO> getProcesoDTOs() {
        return procesoDTOs;
    }

    public void setProcesoDTOs(List<ProcesoDTO> procesoDTOs) {
        this.procesoDTOs = procesoDTOs;
    }
}
