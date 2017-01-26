package co.com.datatools.c2.managed_bean.administracion.jobs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.TareaAutomaticaDTO;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionJobEJB;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;

/**
 * Se encarga de consultar los jobs de la aplicacion
 * 
 * @author divier.casas
 * 
 */
@ManagedBean
@SessionScoped
public class AdminJobMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AdminJobMB.class.getName());
    private static final String NOMBRE_BUNDLE = "labelAdminJobs";

    @EJB
    private IRAdministracionJobEJB iRAdminJob;

    public void consultarJobs() {
        LOGGER.debug("AdminJobMB::consultarJobs()");
        AdminJobHolderFL adminJobHolderFL = findFlowObject(AdminJobHolderFL.class, AdminJobHolderFL.NOMBRE_BEAN);
        adminJobHolderFL.setLsTareaAutDTO(null);
        adminJobHolderFL.setTarAutSeleccionada(null);

        List<TareaAutomaticaDTO> lsRespuesta = iRAdminJob.consultarJobs();

        if (lsRespuesta == null || lsRespuesta.isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE, "msg_error_no_ejecuciones");
            adminJobHolderFL.setLsTareaAutDTO(new ArrayList<TareaAutomaticaDTO>());
            return;
        } else {
            adminJobHolderFL.setLsTareaAutDTO(lsRespuesta);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lsRespuesta.size());
        }
    }

    public void cambiarEjecucion() {
        LOGGER.debug("AdminJobMB::cambiarEjecucion()");
        AdminJobHolderFL adminJobHolderFL = findFlowObject(AdminJobHolderFL.class, AdminJobHolderFL.NOMBRE_BEAN);

        if (adminJobHolderFL.getExpresionCron() != null) {
            boolean esValidaExpresion = iRAdminJob.validarExpresion(adminJobHolderFL.getExpresionCron());
            if (!esValidaExpresion) {
                addErrorMessage(NOMBRE_BUNDLE, "msg_error_expresion_invalida");
                return;
            }
        } else {
            addErrorMessage(NOMBRE_BUNDLE, "msg_error_expresion_invalida");
            return;
        }

        TareaAutomaticaDTO tareaAutomatica = adminJobHolderFL.getTarAutSeleccionada();
        tareaAutomatica.setExpresionTiempo(adminJobHolderFL.getExpresionCron());
        iRAdminJob.cambiarEjecucion(tareaAutomatica);
        addInfoMessage(NOMBRE_BUNDLE, "msg_expresion_valida");
    }

    public void detenerSiguienteEjecucion() {
        LOGGER.debug("AdminJobMB::detenerSiguienteEjecucion()");
        AdminJobHolderFL adminJobHolderFL = findFlowObject(AdminJobHolderFL.class, AdminJobHolderFL.NOMBRE_BEAN);

        TareaAutomaticaDTO tareaAutomatica = adminJobHolderFL.getTarAutSeleccionada();
        iRAdminJob.detenerSiguienteEjecucion(tareaAutomatica);
        addInfoMessage(NOMBRE_BUNDLE, "msg_detener_sig_ejecucion");
    }

    public void ejecutarInmediatamente() {
        LOGGER.debug("AdminJobMB::ejecutarInmediatamente()");
        AdminJobHolderFL adminJobHolderFL = findFlowObject(AdminJobHolderFL.class, AdminJobHolderFL.NOMBRE_BEAN);

        TareaAutomaticaDTO tareaAutomatica = adminJobHolderFL.getTarAutSeleccionada();
        iRAdminJob.ejecutarInmediatamente(tareaAutomatica);
        addInfoMessage(NOMBRE_BUNDLE, "msg_ejecucion_inmediata");
    }
}