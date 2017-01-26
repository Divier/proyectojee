package co.com.datatools.c2.managed_bean.comparendo.administracion.ecuador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumTipoComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.administracion.colombia.AdminComparendoMB;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * ManagedBean que gestiona las paginas de administracion de comparendos Colombia
 * 
 * @author julio.pinzon
 * 
 */
@ManagedBean
@SessionScoped
public class AdminComparendoEcuadorMB extends AdminComparendoMB {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(AdminComparendoEcuadorMB.class.getName());

    @EJB
    private IRParametro parametroEjb;

    @EJB
    private IRComparendo comparendoEjb;

    public static final String NOMBRE_BUNDLE_ADMIN_COMPARENDO = "labelAdminComparendo";

    public AdminComparendoEcuadorMB() {
        super();
    }

    @PostConstruct
    public void init() {
        logger.debug("AdminComparendoMB::init()");

        // Validar que el parametro PAIS_INSTALACION sea igual a (pais Colombia) id 47
        ValorParametroDTO valorParametroDTO = parametroEjb.consultarParametro(EnumParametro.PAIS_INSTALACION,
                getOrganismoTransito().getCodigoOrganismo(), true);
        // if (valorParametroDTO.getValorParamInt() != ConstantesComparendo.ID_PAIS_COLOMBIA) {
        if (valorParametroDTO.getValorParamInt() != getPais().getId()) {
            // throw new CirculemosRuntimeException("PAIS_INSTALACION no es igual a (pais Colombia) id 47");
        }
        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        super.setTamanioMaximoArch(valTamMaxAdjArch.getValorParamInt());
    }

    /**
     * Permite llamar la consulta de comparendos de acuerdo con los filtros de busqueda
     * 
     * @author divier.casas
     */
    @Override
    public void consultar() {
        logger.debug("AdminComparendoMB::consultar()");
        AdminComparendoEcuadorHolderFL adminComparendoEcuadorHolderFL = findFlowObject(
                AdminComparendoEcuadorHolderFL.class, AdminComparendoEcuadorHolderFL.NOMBRE_BEAN);

        adminComparendoEcuadorHolderFL.getLstComparendos().clear();

        adminComparendoEcuadorHolderFL.setComparendoSeleccionado(null);

        ConsultaComparendoDTO consultaComparendoDTO = new ConsultaComparendoDTO();
        consultaComparendoDTO.setCodigoOrganismo(getCodigoOrganismoTransito());
        consultaComparendoDTO.setIdTipoComparendo(EnumTipoComparendo.COMPARENDO_NACIONAL.getCodigo());

        int camposInvalidos = 0;

        if (adminComparendoEcuadorHolderFL.getNumeroCitacion() != null
                && adminComparendoEcuadorHolderFL.getAnioCitacion() == null) {
            getFacesContext().addMessage("form-contenido:anioProceso",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            camposInvalidos++;
        }

        if (adminComparendoEcuadorHolderFL.getNumeroCitacion() == null
                && adminComparendoEcuadorHolderFL.getAnioCitacion() != null) {
            getFacesContext().addMessage("form-contenido:numeroCitacion",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            camposInvalidos++;
        }
        consultaComparendoDTO.setNumeroCitacion(adminComparendoEcuadorHolderFL.getNumeroCitacion());
        consultaComparendoDTO.setAnioCitacion(adminComparendoEcuadorHolderFL.getAnioCitacion());

        if (adminComparendoEcuadorHolderFL.getIdTipoIdentificacionPersona() != null
                && adminComparendoEcuadorHolderFL.getNumeroDocumentoInfractor() == null) {
            getFacesContext().addMessage("form-contenido:numDocInfrac",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            camposInvalidos++;
        }

        if (adminComparendoEcuadorHolderFL.getNumeroDocumentoInfractor() != null
                && adminComparendoEcuadorHolderFL.getIdTipoIdentificacionPersona() == null) {

            getFacesContext().addMessage("form-contenido:tipDocInfrac",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            camposInvalidos++;
        }

        consultaComparendoDTO
                .setTipoDocumentoInfractor(adminComparendoEcuadorHolderFL.getIdTipoIdentificacionPersona());
        consultaComparendoDTO.setNumeroDocumentoInfractor(adminComparendoEcuadorHolderFL.getNumeroDocumentoInfractor());

        consultaComparendoDTO.setNumeroComparendo(adminComparendoEcuadorHolderFL.getNumeroComparendo());

        if (camposInvalidos > 0) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_campos_necesarios");
            return;
        }

        List<ComparendoDTO> lsComparendoDTO = new ArrayList<>();

        try {
            lsComparendoDTO = comparendoEjb.consultarComparendos(consultaComparendoDTO);

            if (lsComparendoDTO == null || lsComparendoDTO.isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
                adminComparendoEcuadorHolderFL.setLstComparendos(new ArrayList<ComparendoDTO>(0));
                return;
            } else {
                adminComparendoEcuadorHolderFL.setLstComparendos(lsComparendoDTO);
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lsComparendoDTO.size());
            }
        } catch (CirculemosNegocioException e) {
            adminComparendoEcuadorHolderFL.getLstComparendos().clear();
            CirculemosErrorHandler.handleException(e);
        }
    }

    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

}