package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RespuestaImpugnacionDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.fachada.IRFachadaProceso;
import co.com.datatools.c2.negocio.interfaces.IRImpugnacion;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Managed Bean para el manejo de rechazo o aprobacion de fallos
 * 
 * @author divier.casas 2016-06-10
 *
 */
@ManagedBean
@SessionScoped
public class RechazarFalloMB extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(RechazarFalloMB.class.getName());
    private static final String NOMBRE_BUNDLE_IMPUGNACION = "labelProcesoImpugnacion";

    @EJB
    private IRImpugnacion iRImpugnacion;

    @EJB
    private IRFachadaProceso iRFachadaProceso;

    public void rechazarFallo() {
        logger.debug("RechazarFalloMB::rechazarFallo()");
        RechazarFalloFL rechazarFalloFL = findFlowObject(RechazarFalloFL.class, RechazarFalloFL.NOMBRE_BEAN);
        List<RespuestaImpugnacionDTO> lsRespuestaImpugnacionDTO = rechazarFalloFL.getProcesosImpugnacion();

        for (RespuestaImpugnacionDTO respuestaImpugnacionDTO : lsRespuestaImpugnacionDTO) {
            ProcesoDTO procesoDTO = new ProcesoDTO();
            procesoDTO.setId(respuestaImpugnacionDTO.getIdProceso());
            List<ProcesoDTO> lsProcesoDTO = iRFachadaProceso.consultarProceso(procesoDTO);
            if (!lsProcesoDTO.isEmpty()) {
                ProcesoDTO proceso = lsProcesoDTO.get(0);
                iRImpugnacion.rechazarFallo(proceso, rechazarFalloFL.getRechazoImpugnacionDTO());
                addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_rechazo_fallo_satisfactorio");
            }
        }
    }

    public void aprobarFallo() {
        logger.debug("RechazarFalloMB::aprobarFallo()");
        RechazarFalloFL rechazarFalloFL = findFlowObject(RechazarFalloFL.class, RechazarFalloFL.NOMBRE_BEAN);
        List<RespuestaImpugnacionDTO> lsRespuestaImpugnacionDTO = rechazarFalloFL.getProcesosImpugnacion();

        for (RespuestaImpugnacionDTO respuestaImpugnacionDTO : lsRespuestaImpugnacionDTO) {
            ProcesoDTO procesoDTO = new ProcesoDTO();
            procesoDTO.setId(respuestaImpugnacionDTO.getIdProceso());
            List<ProcesoDTO> lsProcesoDTO = iRFachadaProceso.consultarProceso(procesoDTO);
            if (!lsProcesoDTO.isEmpty()) {
                ProcesoDTO proceso = lsProcesoDTO.get(0);
                try {
                    iRImpugnacion.aprobarImpugnacion(proceso, respuestaImpugnacionDTO.getIdComparendo());
                    addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_aprobacion_fallo_satisfactorio");
                } catch (CirculemosNegocioException e) {
                    CirculemosErrorHandler.handleError(e.getErrorInfo());
                }
            }
        }
    }
}
