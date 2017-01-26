package co.com.datatools.c2.managed_bean.coactivo.administracion.bien;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

public class DisponerEmbargoFL extends AbstractSwfManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "disponerEmbargoFL";

    private CoactivoDTO coactivoDTO;

    public CoactivoDTO getCoactivoDTO() {
        return coactivoDTO;
    }

    public void setCoactivoDTO(CoactivoDTO coactivoDTO) {
        this.coactivoDTO = coactivoDTO;
    }

}
