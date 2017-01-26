package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

import co.com.datatools.c2.dto.ImpulsoPruebaDTO;
import co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion.vo.RegistroTablaVO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * Managed Bean para el manejo de registro de impulso HU_CIR20_DAT_JUR_012
 * 
 * @author dixon.alvarez 2016-07-05
 * 
 */
@ManagedBean
@SessionScoped
public class RegistrarImpulsoPruebaMB extends AbstractSwfManagedBean {
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(RegistrarImpulsoPruebaMB.class.getName());

    private static final String NOMBRE_BUNDLE_IMPUGNACION = "labelProcesoImpugnacion";

    /**
     * Inicializa los datos que se utilizan en la GUI
     */
    public void inicializarDatos() {
        logger.debug("RegistrarImpulsoPruebaMB::inicializarDatos()");
        boolean exitoso = true;
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);
        if (registrarPruebaFL.getListaImpulsos() != null) {
            for (RegistroTablaVO<ImpulsoPruebaDTO> impulso : registrarPruebaFL.getListaImpulsos()) {
                if (impulso.getDto().getDefinitivo()) {
                    exitoso = false;
                }
            }
        }
        if (exitoso) {
            RegistrarImpulsoPruebaFL registarImpulsoPruebaFL = findFlowObject(RegistrarImpulsoPruebaFL.class,
                    RegistrarImpulsoPruebaFL.NOMBRE_BEAN);
            registarImpulsoPruebaFL = new RegistrarImpulsoPruebaFL();
            registarImpulsoPruebaFL.setConfirRegistrarImpulso(true);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('popUpRegistroImpulsoPruebas').show();");
        } else {
            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_error_impulso_definitivo");
        }
    }

    /**
     * Adiciona un impulso a la lista de impulsos
     */
    public void generarImpulso() {
        logger.debug("RegistrarImpulsoPruebaMB::generarImpulso()");
        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);
        RegistrarImpulsoPruebaFL registarImpulsoPruebaFL = findFlowObject(RegistrarImpulsoPruebaFL.class,
                RegistrarImpulsoPruebaFL.NOMBRE_BEAN);
        if (registrarPruebaFL.getListaImpulsos() == null) {
            registrarPruebaFL.setListaImpulsos(new ArrayList<RegistroTablaVO<ImpulsoPruebaDTO>>());
        }
        if (registarImpulsoPruebaFL.getImpulsoPruebaDTO() != null) {
            registarImpulsoPruebaFL.getImpulsoPruebaDTO().setFechaGeneracion(new Date());
            registrarPruebaFL.getListaImpulsos().add(
                    new RegistroTablaVO<ImpulsoPruebaDTO>(registarImpulsoPruebaFL.getImpulsoPruebaDTO()));
            registarImpulsoPruebaFL.setConfirRegistrarImpulso(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('popUpRegistroImpulsoPruebas').hide();");
        }
    }

    /**
     * Cancela la generacion de un impulso
     */
    public void cancelar() {
        logger.debug("RegistrarImpulsoPruebaMB::cancelar()");
        RegistrarImpulsoPruebaFL registarImpulsoPruebaFL = findFlowObject(RegistrarImpulsoPruebaFL.class,
                RegistrarImpulsoPruebaFL.NOMBRE_BEAN);
        registarImpulsoPruebaFL.setConfirRegistrarImpulso(false);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('popUpRegistroImpulsoPruebas').hide();");
    }

}
