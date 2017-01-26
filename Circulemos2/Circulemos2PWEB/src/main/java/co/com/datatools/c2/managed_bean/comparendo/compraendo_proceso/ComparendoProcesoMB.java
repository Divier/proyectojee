package co.com.datatools.c2.managed_bean.comparendo.compraendo_proceso;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.adaptador.dto.ComparendoProcesoTrazabilidadDTO;
import co.com.datatools.c2.adaptador.proceso.IRProcesoComparendo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author giovanni.velandia
 *
 */
@ManagedBean
@SessionScoped
public class ComparendoProcesoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger LOGGER = Logger.getLogger(ComparendoProcesoMB.class.getName());

    @EJB
    private IRProcesoComparendo irProcesoComparendo;

    /**
     * Consulta la trazabilidad de los procesos de un comparendo
     * 
     * @author giovanni.velandia
     */
    public void consultarTrazabilidadProcesos() {
        LOGGER.debug("consultarTrazabilidadProcesos()");
        ComparendoProcesoFL comparendoProcesoFL = findFlowObject(ComparendoProcesoFL.class,
                ComparendoProcesoFL.NOMBRE_BEAN);
        comparendoProcesoFL.setComparendoProcesoTrazabilidadDTOs(new ArrayList<ComparendoProcesoTrazabilidadDTO>());
        comparendoProcesoFL.setComparendoProcesoTrazabilidadDTOs(
                irProcesoComparendo.consultarTrazabilidadProcesos(comparendoProcesoFL.getCicomparendo()));
    }

}
