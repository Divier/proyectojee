package co.com.datatools.c2.managed_bean.coactivo.administracion.bien;

import java.util.List;

import co.com.datatools.c2.dto.FiltroCoactivoDTO;
import co.com.datatools.c2.dto.RespuestaCoactivoDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class AdministracionBienHolderFL extends AbstractSwfManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "administracionBienHolderFL";

    private FiltroCoactivoDTO filtroCoactivoDTO;
    private List<RespuestaCoactivoDTO> respuestaCoactivoDTOs;
    private RespuestaCoactivoDTO respuestaCoactivoDTOSel;

    public AdministracionBienHolderFL() {
        filtroCoactivoDTO = new FiltroCoactivoDTO();
    }

    public FiltroCoactivoDTO getFiltroCoactivoDTO() {
        return filtroCoactivoDTO;
    }

    public void setFiltroCoactivoDTO(FiltroCoactivoDTO filtroCoactivoDTO) {
        this.filtroCoactivoDTO = filtroCoactivoDTO;
    }

    public List<RespuestaCoactivoDTO> getRespuestaCoactivoDTOs() {
        return respuestaCoactivoDTOs;
    }

    public void setRespuestaCoactivoDTOs(List<RespuestaCoactivoDTO> respuestaCoactivoDTOs) {
        this.respuestaCoactivoDTOs = respuestaCoactivoDTOs;
    }

    public RespuestaCoactivoDTO getRespuestaCoactivoDTOSel() {
        return respuestaCoactivoDTOSel;
    }

    public void setRespuestaCoactivoDTOSel(RespuestaCoactivoDTO respuestaCoactivoDTOSel) {
        this.respuestaCoactivoDTOSel = respuestaCoactivoDTOSel;
    }

}
