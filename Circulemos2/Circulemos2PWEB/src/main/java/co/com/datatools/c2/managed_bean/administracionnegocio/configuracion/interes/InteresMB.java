package co.com.datatools.c2.managed_bean.administracionnegocio.configuracion.interes;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

import co.com.datatools.c2.dto.InteresDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.cartera.IRInteres;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Managed Bean para manipulacion administracion de tasas de interes CU_CIR20_DAT_ADM_016
 * 
 * @author Dixon.Alvarez
 * 
 */
@ManagedBean
@SessionScoped
public class InteresMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(InteresMB.class.getName());

    private static final String BUNDLE_INTERES = "labelIntereses";

    @EJB
    private IRInteres irInteres;

    public void init() {
        logger.debug(InteresMB.class.getName().concat("init()"));
    }

    /**
     * Consulta las diferentes tasas de interes registradas en el sistema según los filtros
     */
    public void consultarTasasInteres() {
        logger.debug(InteresMB.class.getName().concat("consultarTasasInteres()"));
        InteresHolderFL interesHolderFL = findFlowObject(InteresHolderFL.class, InteresHolderFL.NOMBRE_BEAN);
        interesHolderFL.setVisiblePopupEliminar(false);
        if (interesHolderFL.getInteresFiltro().getClaseInteres().getId() == null
                && interesHolderFL.getInteresFiltro().getPeriodoInteres().getId() == null
                && interesHolderFL.getInteresFiltro().getFechaInicial() == null
                && interesHolderFL.getInteresFiltro().getFechaFinal() == null) {
            addErrorMessage(BUNDLE_INTERES, "msg_err_filtros_faltantes");
        } else {
            interesHolderFL.getInteresFiltro().setEstado(true);
            interesHolderFL.setIntereses(irInteres.consultarIntereses(interesHolderFL.getInteresFiltro(), true));
            if (interesHolderFL.getIntereses().isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(interesHolderFL.getIntereses().size());
            }
        }
        interesHolderFL.setInteresSeleccionado(null);
    }

    /**
     * Indica si puede ingresar a la opción de crear una nueva tasa de interés
     * 
     * @return CREAR
     */
    public String irGuardarTasaInteres() {
        logger.debug(InteresMB.class.getName().concat("irGuardarTasaInteres()"));
        InteresFL interesFL = findFlowObject(InteresFL.class, InteresFL.NOMBRE_BEAN);
        interesFL.setActualizar(false);
        return "CREAR";
    }

    /**
     * Guarda o actualiza la tasa de interés configurada por el usuario
     * 
     * @return ERROR si no pudo realizar con exito la operación, CREADO si persistio correctamente, ACTUALIZADO si actualizo correctamente
     */
    public String guardarTasaInteres() {
        logger.debug(InteresMB.class.getName().concat("guardarTasaInteres()"));
        String resultado = "ERROR";
        InteresFL interesFL = findFlowObject(InteresFL.class, InteresFL.NOMBRE_BEAN);
        InteresHolderFL interesHolderFL = findFlowObject(InteresHolderFL.class, InteresHolderFL.NOMBRE_BEAN);
        if (interesFL.getInteresRegistrar().getFechaFinal() == null || (interesFL.getInteresRegistrar().getFechaFinal()
                .after(interesFL.getInteresRegistrar().getFechaInicial())
                && interesFL.getInteresRegistrar().getFechaFinal().after(new Date()))) {
            interesFL.getInteresRegistrar().setEstado(true);
            try {
                if (interesFL.isActualizar()) {
                    irInteres.modificarInteres(interesFL.getInteresRegistrar());
                } else {
                    irInteres.registrarInteres(interesFL.getInteresRegistrar());
                    interesHolderFL.setInteresSeleccionado(null);
                    interesFL.setPorcentajeGuardado(MessageFormat.format(getLabel("msg_guardado_ok"),
                            interesFL.getInteresRegistrar().getPorcentajeTasaInteres()
                                    .multiply(BigDecimal.valueOf(100).setScale(3, BigDecimal.ROUND_FLOOR))));
                }
                resultado = "EXITOSO";
                interesFL.setVisiblePopupRegistro(true);
                RequestContext request = RequestContext.getCurrentInstance();
                request.update("frmConfirmaRegistro");
                request.execute("PF('popupConfirmaRegistro').show();");
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            }
        } else {
            addErrorMessage(BUNDLE_INTERES, "msg_err_fecha_final");
        }
        return resultado;
    }

    /**
     * Indica si puede o no editar el interes seleccionado
     * 
     * @return True, si puede editar
     */
    public boolean irActualizarTasaInteres() {
        logger.debug(InteresMB.class.getName().concat("irActualizarTasaInteres()"));
        boolean resultado = false;
        InteresFL interesFL = findFlowObject(InteresFL.class, InteresFL.NOMBRE_BEAN);
        InteresHolderFL interesHolderFL = findFlowObject(InteresHolderFL.class, InteresHolderFL.NOMBRE_BEAN);
        InteresDTO interesSeleccionado = interesHolderFL.getInteresSeleccionado();
        if (interesSeleccionado.getFechaFinal() == null || interesSeleccionado.getFechaFinal().after(new Date())) {
            InteresDTO interesRegistrar = new InteresDTO();
            interesRegistrar.setClaseInteres(interesSeleccionado.getClaseInteres());
            interesRegistrar.setFechaFinal(interesSeleccionado.getFechaFinal());
            interesRegistrar.setFechaInicial(interesSeleccionado.getFechaInicial());
            interesRegistrar.setId(interesSeleccionado.getId());
            interesRegistrar.setPeriodoInteres(interesSeleccionado.getPeriodoInteres());
            interesRegistrar.setPorcentajeInteresDiario(interesSeleccionado.getPorcentajeInteresDiario());
            interesRegistrar.setPorcentajeTasaInteres(interesSeleccionado.getPorcentajeTasaInteres());
            interesFL.setInteresRegistrar(interesRegistrar);
            actualizarMinFechaFinal();
            interesFL.setActualizar(true);
            resultado = true;
        } else {
            addErrorMessage(BUNDLE_INTERES, "msg_error_editar");
        }
        return resultado;
    }

    /**
     * Elimina el interes seleccionado por el usuario
     */
    public void eliminarTasaInteres() {
        logger.debug(InteresMB.class.getName().concat("eliminarTasaInteres()"));
        InteresHolderFL interesHolderFL = findFlowObject(InteresHolderFL.class, InteresHolderFL.NOMBRE_BEAN);
        if (interesHolderFL.getInteresSeleccionado().getFechaInicial().after(new Date())) {
            try {
                interesHolderFL.getInteresSeleccionado().setEstado(false);
                irInteres.modificarInteres(interesHolderFL.getInteresSeleccionado());
                interesHolderFL.setVisiblePopupEliminar(true);
                RequestContext request = RequestContext.getCurrentInstance();
                request.update("frmConfirmEliminacion");
                request.execute("PF('popupConfirmEliminacion').show();");
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            }
        } else {
            addErrorMessage(BUNDLE_INTERES, "msg_error_eliminar");
        }

    }

    /**
     * Actualiza el minimo para la fecha final del interes a configurar
     */
    public void actualizarMinFechaFinal() {
        logger.debug(InteresMB.class.getName().concat("actualizarMinFechaFinal()"));
        InteresFL interesFL = findFlowObject(InteresFL.class, InteresFL.NOMBRE_BEAN);
        Calendar minFechaFinal = Calendar.getInstance();
        minFechaFinal.setTime(interesFL.getInteresRegistrar().getFechaInicial());
        minFechaFinal.set(Calendar.DAY_OF_MONTH, minFechaFinal.get(Calendar.DAY_OF_MONTH) + 1);
        interesFL.setMinFechaFinal(minFechaFinal.getTime());
    }

    public String aceptarConfirmacion() {
        logger.debug(InteresMB.class.getName().concat("aceptarConfirmacion()"));
        String resultado = null;
        InteresFL interesFL = findFlowObject(InteresFL.class, InteresFL.NOMBRE_BEAN);
        interesFL.setVisiblePopupRegistro(false);
        if (interesFL.isActualizar()) {
            consultarTasasInteres();
            resultado = "ACTUALIZADO";
        } else {
            resultado = "CREADO";
        }
        return resultado;
    }

    public String getLabel(String label) {
        ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication()
                .getResourceBundle(FacesContext.getCurrentInstance(), BUNDLE_INTERES);
        return bundle.getString(label);
    }
}
