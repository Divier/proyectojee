package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.List;

import co.com.datatools.c2.dto.RechazoImpugnacionDTO;
import co.com.datatools.c2.dto.RespuestaImpugnacionDTO;
import co.com.datatools.c2.dto.TipoRechazoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * FL para el manejo de rechazo de fallo
 * 
 * @author divier.casas 2016-06-09
 *
 */
public class RechazarFalloFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "rechazarFalloFL";

    private RechazoImpugnacionDTO rechazoImpugnacionDTO;
    private List<RespuestaImpugnacionDTO> procesosImpugnacion;

    public RechazarFalloFL() {
        rechazoImpugnacionDTO = new RechazoImpugnacionDTO();
        rechazoImpugnacionDTO.setTipoRechazo(new TipoRechazoDTO());
    }

    public List<RespuestaImpugnacionDTO> getProcesosImpugnacion() {
        return procesosImpugnacion;
    }

    public void setProcesosImpugnacion(List<RespuestaImpugnacionDTO> procesosImpugnacion) {
        this.procesosImpugnacion = procesosImpugnacion;
    }

    public RechazoImpugnacionDTO getRechazoImpugnacionDTO() {
        return rechazoImpugnacionDTO;
    }

    public void setRechazoImpugnacionDTO(RechazoImpugnacionDTO rechazoImpugnacionDTO) {
        this.rechazoImpugnacionDTO = rechazoImpugnacionDTO;
    }
}