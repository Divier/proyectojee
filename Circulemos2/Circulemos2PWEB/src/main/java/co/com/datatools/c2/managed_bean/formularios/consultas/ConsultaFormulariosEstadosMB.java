package co.com.datatools.c2.managed_bean.formularios.consultas;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaFormulariosEstadosMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(ConsultaFormulariosEstadosMB.class.getName());
    // Variables de objetos spring web flow
    private static final String CONSULTA_FORMULARIOS_ESTADOS_HOLDER_FL = "consultaFormulariosEstadosHolderFL";

    private static final String BUNDLE_GENERAL = "labelGeneral";

    @EJB
    private IRFormulario iRFormulario;

    public ConsultaFormulariosEstadosMB() {
    }

    /**
     * Consulta formularios por filtros ingresados
     * 
     * @author giovanni.velandia
     */
    public void consultarFormulariosEstados() {
        LOGGER.debug(ConsultaFormulariosEstadosMB.class.getName().concat("::consultarFormulariosEstados()"));

        final ConsultaFormulariosEstadosHolderFL consultaformulariosEstadosHolderFL = findFlowObject(
                ConsultaFormulariosEstadosHolderFL.class, CONSULTA_FORMULARIOS_ESTADOS_HOLDER_FL);

        /*
         * Validaciones de requeridos para el ingreso de los numero de rango de formulario
         */
        // Inicial
        if (consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getNumeroInicialFormulario() != null
                && !consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getNumeroInicialFormulario()
                        .isEmpty()) {
            if (consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getNumeroFinalFormulario() == null
                    || consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO()
                            .getNumeroFinalFormulario().isEmpty()) {
                addLocatedErrorMessage(BUNDLE_GENERAL, "val_campo_requerido", "form-contenido:txtFinForm");
                return;
            }
        }
        // Final
        if (consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getNumeroFinalFormulario() != null
                && !consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getNumeroFinalFormulario()
                        .isEmpty()) {
            if (consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getNumeroInicialFormulario() == null
                    || consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO()
                            .getNumeroInicialFormulario().isEmpty()) {
                addLocatedErrorMessage(BUNDLE_GENERAL, "val_campo_requerido", "form-contenido:txtIniForm");
                return;
            }
        }

        /*
         * Validaciones de requeridos para el ingreso de los numero de rango de formulario
         */
        // Inicial
        /*
         * if (consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getFechaInicialCambioEstado() != null) { if
         * (consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getFechaFinalCambioEstado() == null) {
         * addLocatedErrorMessage(BUNDLE_GENERAL, "val_campo_requerido", "form-contenido:calFinalEstado"); return; } } // Final if
         * (consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getFechaFinalCambioEstado() != null) { if
         * (consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().getFechaInicialCambioEstado() == null) {
         * addLocatedErrorMessage(BUNDLE_GENERAL, "val_campo_requerido", "form-contenido:calInicioEstado"); return; } }
         * 
         * try { consultaformulariosEstadosHolderFL.setFormularioDTOs(iRFormulario .consultarSeguimientoFormulario(consultaformulariosEstadosHolderFL
         * .getConsultaSegAgrupFormuFiltroDTO()));
         * 
         * if (consultaformulariosEstadosHolderFL.getFormularioDTOs().isEmpty()) { CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio(); }
         * else { CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(consultaformulariosEstadosHolderFL .getFormularioDTOs().size()); } } catch
         * (CirculemosNegocioException e) { CirculemosErrorHandler.handleException(e); }
         */
    }

    /**
     * Se encarga de validar que tipo de responsable es para ocultar o mostrar campos
     * 
     * @author giovanni.velandia
     */
    public void validarTipoResponsable() {
        LOGGER.debug(ConsultaFormulariosEstadosMB.class.getName().concat("::validarTipoResponsable()"));

        final ConsultaFormulariosEstadosHolderFL consultaformulariosEstadosHolderFL = findFlowObject(
                ConsultaFormulariosEstadosHolderFL.class, CONSULTA_FORMULARIOS_ESTADOS_HOLDER_FL);
        if (EnumTipoResponsableFormulario.EMPRESA.getValue().equals(
                consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO()
                        .getTipoResponsableFormularioDTO().getId())) {
            consultaformulariosEstadosHolderFL.setEsEmpresa(true);
            consultaformulariosEstadosHolderFL.setEsOrgTransito(false);
            consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().setTipoIdentificacionPersonaDTO(
                    getCatalogosApp().getTipoIdentificacionEmpresa(getPais().getId()));
        } else {
            consultaformulariosEstadosHolderFL.setEsEmpresa(false);
            consultaformulariosEstadosHolderFL.setEsOrgTransito(true);
            consultaformulariosEstadosHolderFL.getConsultaSegAgrupFormuFiltroDTO().setTipoIdentificacionPersonaDTO(
                    new TipoIdentificacionPersonaDTO());
        }
    }
}
