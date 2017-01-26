package co.com.datatools.c2.managed_bean.coactivo.administracion.bien;

import java.util.List;

import co.com.datatools.c2.dto.SolicitudOficioCoactivoDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class RegistrarRespuestaBienFL extends AbstractSwfManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "registrarRespuestaBienFL";

    private SolicitudOficioCoactivoDTO solicitudOficioCoactivoSelDTO;
    private List<SolicitudOficioCoactivoDTO> solicitudOficioCoactivoDTOs;
    private SolicitudOficioCoactivoDTO solicitudOficioCoactivoDTO;
    private boolean activarSolCoactivo;

    public List<SolicitudOficioCoactivoDTO> getSolicitudOficioCoactivoDTOs() {
        return solicitudOficioCoactivoDTOs;
    }

    public void setSolicitudOficioCoactivoDTOs(List<SolicitudOficioCoactivoDTO> solicitudOficioCoactivoDTOs) {
        this.solicitudOficioCoactivoDTOs = solicitudOficioCoactivoDTOs;
    }

    public boolean isActivarSolCoactivo() {
        return activarSolCoactivo;
    }

    public void setActivarSolCoactivo(boolean activarSolCoactivo) {
        this.activarSolCoactivo = activarSolCoactivo;
    }

    public SolicitudOficioCoactivoDTO getSolicitudOficioCoactivoSelDTO() {
        return solicitudOficioCoactivoSelDTO;
    }

    public void setSolicitudOficioCoactivoSelDTO(SolicitudOficioCoactivoDTO solicitudOficioCoactivoSelDTO) {
        this.solicitudOficioCoactivoSelDTO = solicitudOficioCoactivoSelDTO;
    }

    public SolicitudOficioCoactivoDTO getSolicitudOficioCoactivoDTO() {
        return solicitudOficioCoactivoDTO;
    }

    public void setSolicitudOficioCoactivoDTO(SolicitudOficioCoactivoDTO solicitudOficioCoactivoDTO) {
        this.solicitudOficioCoactivoDTO = solicitudOficioCoactivoDTO;
    }

}
