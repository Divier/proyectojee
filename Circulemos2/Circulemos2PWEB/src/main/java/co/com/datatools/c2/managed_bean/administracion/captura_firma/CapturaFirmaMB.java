package co.com.datatools.c2.managed_bean.administracion.captura_firma;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRFirma;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Se encarga de la sdministracion de la captura de la firma digital
 * 
 * @author giovanni.velandia
 *
 */
@ManagedBean
@SessionScoped
public class CapturaFirmaMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(CapturaFirmaMB.class.getName());
    private static final String NOMBRE_BUNDLE = "labelCapturaFirma";

    private CapturaFirmaFL capturaFirmaFL;

    @EJB
    private IRFirma iRFirma;
    @EJB
    private IRParametro parametroEjb;

    public CapturaFirmaMB() {
        capturaFirmaFL = new CapturaFirmaFL();
    }

    /**
     * Consulta el parametro que define si se requiere la captura de la firma
     * 
     * @author giovanni.velandia
     * @return
     */
    public boolean consultarParametro() {
        LOGGER.debug("CapturaFirmaMB::consultarParametro()");

        // Validar que el parametro PAIS_INSTALACION sea igual a (pais Colombia) id 47
        ValorParametroDTO valorParametroDTO = parametroEjb.consultarParametro(
                EnumParametro.CAPT_INFO_PERSONA_BIOMETRICO, getOrganismoTransito().getCodigoOrganismo(), true);
        return valorParametroDTO.getValorParamBoolean();
    }

    /**
     * Ingresa la persona para asociarlo con la firma
     * 
     * @author giovanni.velandia
     */
    public void capturarFirmaDigital() {
        LOGGER.debug("CapturaFirmaMB::capturarFirmaDigital()");
        capturaFirmaFL.setCapturarFirmaDigital(true);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('capturaFirma').show();");
    }

    /**
     * Ingresa la persona para asociarlo con la firma
     * 
     * @author giovanni.velandia
     */
    public void capturarFirmaDigital(PersonaDTO personaDTO) {
        LOGGER.debug("CapturaFirmaMB::capturarFirmaDigital(PersonaDTO)");
        capturaFirmaFL.setCapturarFirmaDigital(true);
        capturaFirmaFL.getCapturaFirmaDTO().setPersonaDTO(personaDTO);
        // Validamos la persona
        if (capturaFirmaFL.getCapturaFirmaDTO().getPersonaDTO() == null) {
            addErrorMessage(NOMBRE_BUNDLE, "msg_error_persona");
            return;
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('capturaFirma').show();");
    }

    /**
     * Se encarga de cerrar la captura de la firma digital
     * 
     * @author giovanni.velandia
     */
    public void cerrarCapturaFirmaDigital() {
        LOGGER.debug("CapturaFirmaMB::cerrarCapturaFirmaDigital()");
        capturaFirmaFL.setCapturarFirmaDigital(false);
    }

    /**
     * Meotodo que se encarga de guardar la firma para una persona
     * 
     * @author giovanni.velandia
     */
    public boolean asociarFirma() {
        LOGGER.debug("CapturaFirmaMB::guardarFirma()");
        @SuppressWarnings("static-access")
        String imagen = getFacesContext().getCurrentInstance().getExternalContext().getRequestParameterMap()
                .get("form-captura-firma:imgdata");
        capturaFirmaFL.getCapturaFirmaDTO().setFirma(imagen);

        // Validamos la firma de la persona
        if (capturaFirmaFL.getCapturaFirmaDTO().getFirma() == null
                || capturaFirmaFL.getCapturaFirmaDTO().getFirma().isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE, "msg_error_firma");
            return false;
        }

        capturaFirmaFL.setCapturarFirmaDigital(false);

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('capturaFirma').hide();");

        // Mensaje de guardado exitoso
        addInfoMessage(NOMBRE_BUNDLE, "msg_satisfactorio");

        return true;
    }

    /**
     * Meotodo que se encarga de guardar la firma para una persona
     * 
     * @author giovanni.velandia
     */
    public boolean guardarFirma() {
        LOGGER.debug("CapturaFirmaMB::guardarFirma()");
        @SuppressWarnings("static-access")
        String imagen = getFacesContext().getCurrentInstance().getExternalContext().getRequestParameterMap()
                .get("form-captura-firma:imgdata");
        capturaFirmaFL.getCapturaFirmaDTO().setFirma(imagen);

        // Validamos la firma de la persona
        if (capturaFirmaFL.getCapturaFirmaDTO().getFirma() == null
                || capturaFirmaFL.getCapturaFirmaDTO().getFirma().isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE, "msg_error_firma");
            return false;
        }

        try {
            iRFirma.registrarFirma(capturaFirmaFL.getCapturaFirmaDTO(), true);

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        capturaFirmaFL.setCapturarFirmaDigital(false);

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('capturaFirma').hide();");

        // Mensaje de guardado exitoso
        addInfoMessage(NOMBRE_BUNDLE, "msg_satisfactorio");

        return true;
    }

    public CapturaFirmaFL getCapturaFirmaFL() {
        return capturaFirmaFL;
    }

    public void setCapturaFirmaFL(CapturaFirmaFL capturaFirmaFL) {
        this.capturaFirmaFL = capturaFirmaFL;
    }

}
