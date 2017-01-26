package co.com.datatools.c2.managed_bean.coactivo.administracion.bien;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

@ManagedBean
@SessionScoped
public class DisponerEmbargoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AdministracionBienMB.class.getName());

    @EJB
    IRCoactivo iRCoactivo;

    public void consultarCoactivo() {

        AdministracionBienHolderFL administracionBienHolderFL = findFlowObject(AdministracionBienHolderFL.class,
                AdministracionBienHolderFL.NOMBRE_BEAN);

        DisponerEmbargoFL disponerEmbargoFL = findFlowObject(DisponerEmbargoFL.class, DisponerEmbargoFL.NOMBRE_BEAN);

        CoactivoDTO coactivoDTO = new CoactivoDTO();
        coactivoDTO.setId(administracionBienHolderFL.getRespuestaCoactivoDTOSel().getIdCoactivo());
        List<CoactivoDTO> coactivoDTOs = iRCoactivo.consultarCoactivos(coactivoDTO);

        disponerEmbargoFL.setCoactivoDTO(coactivoDTOs.get(0));
    }
}
