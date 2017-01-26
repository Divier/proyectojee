package co.com.datatools.c2.managed_bean.formularios.responsable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.ResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoResponsableFormularioDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * Managed Bean que permite administrar los formularios implementando la logica de negocio del caso de uso <b>CU_CIR20_DAT_ADM_031 'Administrar
 * Responsables Formualarios'</b>
 * 
 * @author luis.forero (2015-01-13)
 * 
 */
@ManagedBean
@SessionScoped
public class ResponsablesFormulariosMB extends AbstractC2ManagedBean {

    private static final String RESPONSABLE_FORMULARIO_HOLDER_FL = "responsableFormularioHolderFL";

    private static final Class<ResponsableFormularioHolderFL> OBJ_RESPONSABLE_FORMULARIO_HOLDER_FL = ResponsableFormularioHolderFL.class;

    private static final String BUNDLE_RESPONSABLE_FORMULARIO = "labelResponsableFormulario";

    private static final String RESPONSABLE_FORMULARIO_FL = "responsableFormularioFL";

    private static final Class<ResponsableFormularioFL> OBJ_RESPONSABLE_FORMULARIO_FL = ResponsableFormularioFL.class;

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(ResponsablesFormulariosMB.class.getName());

    private static final Class<ConsultaResponsableFormularioFL> OBJ_CONSULTA_RESPONSABLE_FORMULARIO = ConsultaResponsableFormularioFL.class;

    private static final String CONSULTA_RESPONSABLE_FORMULARIO = "consultaResponsableFormularioFL";

    @EJB
    private IRAdministracionFormularios administracionFormulariosEjb;
    @EJB
    private IRAdministracion administracionEJB;

    @EJB
    private IRPersona personaEjb;

    @PostConstruct
    public void init() {
        final ResponsableFormularioHolderFL responsableFormularioHolderFL = findFlowObject(
                OBJ_RESPONSABLE_FORMULARIO_HOLDER_FL, RESPONSABLE_FORMULARIO_HOLDER_FL);
        OrganismoTransitoDTO organismoTransitoDTO = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
        responsableFormularioHolderFL.setOrganismoTransitoDTO(organismoTransitoDTO);
        final ResponsableFormularioFL responsableFormularioFL = findFlowObject(OBJ_RESPONSABLE_FORMULARIO_FL,
                RESPONSABLE_FORMULARIO_FL);
        responsableFormularioFL.setOrganismoTransitoDTO(organismoTransitoDTO);
    }

    /**
     * Permite llevar a cabo la consulta de los responsables
     * 
     * @author luis.forero
     */
    public void consultar() {
        logger.debug(ResponsablesFormulariosMB.class.getName().concat("::consultar()"));
        final ResponsableFormularioHolderFL responsableFormularioHolderFL = findFlowObject(
                OBJ_RESPONSABLE_FORMULARIO_HOLDER_FL, RESPONSABLE_FORMULARIO_HOLDER_FL);
        responsableFormularioHolderFL.setRespFormSeleccionado(null);
        ResponsableFormularioDTO filtroRespFormularioDTO = new ResponsableFormularioDTO();

        Integer filTipoResponsable = responsableFormularioHolderFL.getFilTipoResponsable();
        if (filTipoResponsable != null) {
            filtroRespFormularioDTO.setTipoResponsable(new TipoResponsableFormularioDTO(filTipoResponsable));
        } else {
            addErrorMessage(BUNDLE_RESPONSABLE_FORMULARIO, "msg_err_tipo_responsable_vacio");
            return;
        }
        UnificacionResponsableDTO unificacionConsultar = new UnificacionResponsableDTO();
        if (filTipoResponsable.equals(EnumTipoResponsableFormulario.EMPRESA.getValue())) {
            PersonaDTO personaDTO = new PersonaDTO();
            if (responsableFormularioHolderFL.getFilTipoDocumento() != null) {
                personaDTO.setTipoIdentificacion(new TipoIdentificacionPersonaDTO(responsableFormularioHolderFL
                        .getFilTipoDocumento()));
            }
            if (StringUtils.isNotBlank(responsableFormularioHolderFL.getFilNumeroDocumento())) {
                personaDTO.setNumeroIdentificacion(responsableFormularioHolderFL.getFilNumeroDocumento());
            }
            unificacionConsultar.setPersona(personaDTO);
        } else if (filTipoResponsable.equals(EnumTipoResponsableFormulario.ORGANISMO_TRANSITO.getValue())) {
            OrganismoTransitoDTO organismoTransitoDTO = new OrganismoTransitoDTO();
            if (responsableFormularioHolderFL.getFilCodigoOrganismo() != null) {
                organismoTransitoDTO.setCodigoOrganismo(responsableFormularioHolderFL.getFilCodigoOrganismo());
            }
            unificacionConsultar.setOrganismoTransito(organismoTransitoDTO);
        }
        unificacionConsultar.setResponsableFormulario(filtroRespFormularioDTO);

        List<UnificacionResponsableDTO> response = administracionFormulariosEjb
                .consultarResponsablesFormularios(unificacionConsultar);

        responsableFormularioHolderFL.getLstResponsablesFormularios().clear();
        if (!response.isEmpty()) {
            for (UnificacionResponsableDTO responsableFormularioDTO : response) {
                ResponsableFormularioFL responsableFormularioFL = new ResponsableFormularioFL(responsableFormularioDTO);

                responsableFormularioHolderFL.getLstResponsablesFormularios().add(responsableFormularioFL);
            }

            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(response.size());
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        }
    }

    /**
     * 
     */
    public void cambioFilTipoResponsable() {
        final ResponsableFormularioHolderFL responsableFormularioHolderFL = findFlowObject(
                OBJ_RESPONSABLE_FORMULARIO_HOLDER_FL, RESPONSABLE_FORMULARIO_HOLDER_FL);
        Integer filTipoResponsable = responsableFormularioHolderFL.getFilTipoResponsable();
        if (filTipoResponsable != null) {
            if (filTipoResponsable.equals(EnumTipoResponsableFormulario.EMPRESA.getValue())) {
                TipoIdentificacionPersonaDTO tipPersJuridica = getCatalogosApp().getTipoIdentificacionEmpresa(
                        getPais().getId());
                responsableFormularioHolderFL.setFilTipoDocumento(tipPersJuridica.getId());
                responsableFormularioHolderFL.setStrTipoDocumento(tipPersJuridica.getNombre());
                responsableFormularioHolderFL.setFilCodigoOrganismo(null);
                responsableFormularioHolderFL.setPorOrganismo(false);
                return;
            } else if (filTipoResponsable.equals(EnumTipoResponsableFormulario.ORGANISMO_TRANSITO.getValue())) {
                responsableFormularioHolderFL.setPorOrganismo(true);
            }
        }
        responsableFormularioHolderFL.setFilNumeroDocumento(null);
        responsableFormularioHolderFL.setFilTipoDocumento(null);
        responsableFormularioHolderFL.setStrTipoDocumento(null);

    }

    /**
     * Carga la consulta de una persona o organismo de transito.
     * 
     * @return true si la carga fue exitosa, false de encontrar un error de validacion
     */
    public boolean cargarConsultaPersonaResponsable() {
        final ResponsableFormularioFL responsableFormularioFL = findFlowObject(OBJ_RESPONSABLE_FORMULARIO_FL,
                RESPONSABLE_FORMULARIO_FL);
        Integer idTipoResponsable = responsableFormularioFL.getIdTipoResponsableFormulario();
        if (idTipoResponsable == null) {
            addErrorMessage(BUNDLE_RESPONSABLE_FORMULARIO, "msg_err_tipo_responsable_vacio");
            return false;
        }
        final ConsultaResponsableFormularioFL consultaResponsableFormularioFL = findFlowObject(
                OBJ_CONSULTA_RESPONSABLE_FORMULARIO, CONSULTA_RESPONSABLE_FORMULARIO);
        EnumTipoResponsableFormulario tipoResponsableFormulario = Utilidades.buscarElemEnum(
                EnumTipoResponsableFormulario.class, idTipoResponsable);
        if (tipoResponsableFormulario.equals(EnumTipoResponsableFormulario.EMPRESA)) {
            TipoIdentificacionPersonaDTO tipIdPersonaJuridica = getCatalogosApp().getTipoIdentificacionEmpresa(
                    getPais().getId());
            consultaResponsableFormularioFL.setIdTipoDocumento(tipIdPersonaJuridica.getId());
            consultaResponsableFormularioFL.setStrTipoDocumento(tipIdPersonaJuridica.getNombre());
        }

        consultaResponsableFormularioFL.setTipoResponsableFormulario(tipoResponsableFormulario);

        return true;
    }

    /**
     * Consulta las posibles personas a asociar al responsable.
     * 
     * @author luis.forero
     */
    public void consultarPersonaOrganismo() {
        final ConsultaResponsableFormularioFL consultaResponsableFormularioFL = findFlowObject(
                OBJ_CONSULTA_RESPONSABLE_FORMULARIO, CONSULTA_RESPONSABLE_FORMULARIO);
        List<UnificacionResponsableFL> listConsulta = new ArrayList<>(0);
        if (consultaResponsableFormularioFL.getTipoResponsableFormulario()
                .equals(EnumTipoResponsableFormulario.EMPRESA)) {
            PersonaDTO consulta = new PersonaDTO();
            consulta.setTipoIdentificacion(new TipoIdentificacionPersonaDTO(consultaResponsableFormularioFL
                    .getIdTipoDocumento()));
            consulta.setNumeroIdentificacion(consultaResponsableFormularioFL.getNumeroIdentificacion());
            consulta.setOrganismoTransito(getOrganismoTransito());
            List<PersonaDTO> lstPersonas = personaEjb.consultarPersonas(consulta);
            for (PersonaDTO persona : lstPersonas) {
                UnificacionResponsableFL fl = new UnificacionResponsableFL(persona);
                listConsulta.add(fl);
            }
        } else {
            List<OrganismoTransitoDTO> lstOrganismos = administracionEJB
                    .consultarOrganismoTransito(consultaResponsableFormularioFL.getNumeroIdentificacion());
            for (OrganismoTransitoDTO organismoTransito : lstOrganismos) {
                UnificacionResponsableFL fl = new UnificacionResponsableFL(organismoTransito);
                listConsulta.add(fl);
            }

        }
        if (listConsulta.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(listConsulta.size());
        }
        consultaResponsableFormularioFL.setListResultado(listConsulta);
    }

    /**
     * Lleva a cabo la validacion de la persona ingresada en la pagina perimitiendo asignarla al responsable de formularios
     * 
     * @author luis.forero (2015-09-07)
     */
    public void seleccionarUnificado() {
        logger.debug(ResponsablesFormulariosMB.class.getName().concat("::ingresarUnificado()"));
        final ConsultaResponsableFormularioFL consultaResponsableFormularioFL = findFlowObject(
                OBJ_CONSULTA_RESPONSABLE_FORMULARIO, CONSULTA_RESPONSABLE_FORMULARIO);

        UnificacionResponsableFL responsableSeleccionado = consultaResponsableFormularioFL
                .getUnificacionResponsableSeleccionado();
        if (responsableSeleccionado != null) {
            ResponsableFormularioFL responsableFormularioFL = findFlowObject(OBJ_RESPONSABLE_FORMULARIO_FL,
                    RESPONSABLE_FORMULARIO_FL);
            UnificacionResponsableDTO unificacionResponsableDTO = new UnificacionResponsableDTO();
            if (responsableSeleccionado.getOrganismoTransitoDTO() != null) {
                unificacionResponsableDTO.setOrganismoTransito(responsableSeleccionado.getOrganismoTransitoDTO());
            } else {
                unificacionResponsableDTO.setPersona(responsableSeleccionado.getPersonaDTO());
            }
            responsableFormularioFL.setUnificacionResponsable(unificacionResponsableDTO);
        }
    }

    /**
     * permite llevar a cabo la accion de registro de un responsable de formulario tomando los datos de la interfaz
     * 
     * @return true si la operacion fue exitosa, false de lo contrario
     * @author luis.forero
     */
    public boolean registrarResponsableFormulario() {
        logger.debug(ResponsablesFormulariosMB.class.getName().concat("::registrarResponsableFormulario()"));
        UnificacionResponsableDTO responsableFormulario = procesarDatosResponsableFormulario();
        try {
            Long idResponsable = administracionFormulariosEjb.registrarResponsableFormularios(responsableFormulario);

            ResponsableFormularioFL responsableFormularioFL = findFlowObject(OBJ_RESPONSABLE_FORMULARIO_FL,
                    RESPONSABLE_FORMULARIO_FL);
            responsableFormularioFL.setIdResponsable(idResponsable);

            CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        return true;
    }

    /**
     * Permite llevar a cabo la actualizacion de un determinado responsable de formulario
     * 
     * @return true si la operacion fue exitosa, false de lo contrario
     * @author luis.forero
     */
    public boolean actualizarResponsableFormulario() {
        logger.debug(ResponsablesFormulariosMB.class.getName().concat("::actualizarResponsableFormulario()"));
        UnificacionResponsableDTO responsableFormulario = procesarDatosResponsableFormulario();
        try {
            administracionFormulariosEjb.actualizarResponsableFormularios(responsableFormulario);

            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        return true;
    }

    /**
     * Consulta el datalle de un responsable de formulario
     * 
     * @author luis.forero
     */
    public void consultarDetalle() {
        logger.debug(ResponsablesFormulariosMB.class.getName().concat("::consultarDetalle()"));
        final ResponsableFormularioHolderFL responsableFormularioHolderFL = findFlowObject(
                OBJ_RESPONSABLE_FORMULARIO_HOLDER_FL, RESPONSABLE_FORMULARIO_HOLDER_FL);
        ResponsableFormularioFL respFormSeleccionado = responsableFormularioHolderFL.getRespFormSeleccionado();

        if (respFormSeleccionado != null) {
            UnificacionResponsableDTO unificacionConsulta = new UnificacionResponsableDTO();
            unificacionConsulta.setResponsableFormulario(respFormSeleccionado.getResponsableFormulario());
            List<UnificacionResponsableDTO> consultarResponsablesFormularios = administracionFormulariosEjb
                    .consultarResponsablesFormularios(unificacionConsulta);

            if (!consultarResponsablesFormularios.isEmpty()) {
                final ResponsableFormularioFL responsableFormularioFL = findFlowObject(OBJ_RESPONSABLE_FORMULARIO_FL,
                        RESPONSABLE_FORMULARIO_FL);
                responsableFormularioFL.initDatosRegistro();
                responsableFormularioFL.initDatosRegistro(consultarResponsablesFormularios.get(0));
            }
        } else {
            CirculemosAccesoBundleGeneral.addMensajeNoSeleccionItem();
        }

    }

    /**
     * Procesa los datos ingresados del responsable de formulario.
     * 
     * @return objeto responsable formulario con su respectiva informaciOn.
     * @author luis.forero (mod 2015-09-09)
     */
    private UnificacionResponsableDTO procesarDatosResponsableFormulario() {
        logger.debug(ResponsablesFormulariosMB.class.getName().concat("::procesarDatosResponsableFormulario()"));
        final ResponsableFormularioFL responsableFormularioFL = findFlowObject(OBJ_RESPONSABLE_FORMULARIO_FL,
                RESPONSABLE_FORMULARIO_FL);
        ResponsableFormularioDTO responsableFormulario = responsableFormularioFL.getResponsableFormulario();
        responsableFormulario.setTipoResponsable(new TipoResponsableFormularioDTO(responsableFormularioFL
                .getIdTipoResponsableFormulario()));
        UnificacionResponsableDTO unificacionResponsableDTO = responsableFormularioFL.getUnificacionResponsableDTO();
        responsableFormulario.setOrganismoTransito(getOrganismoTransito());

        unificacionResponsableDTO.setResponsableFormulario(responsableFormulario);

        return unificacionResponsableDTO;
    }

    /**
     * Elimina un responsable seleccionado de la interfaz
     * 
     * @author luis.forero(mod 2015-09-14)
     */
    public boolean eliminarResponsableFormulario() {
        logger.debug(ResponsablesFormulariosMB.class.getName().concat("::eliminarResponsableFormulario()"));
        final ResponsableFormularioHolderFL responsableFormularioHolderFL = findFlowObject(
                OBJ_RESPONSABLE_FORMULARIO_HOLDER_FL, RESPONSABLE_FORMULARIO_HOLDER_FL);
        if (responsableFormularioHolderFL.getRespFormSeleccionado() != null) {
            try {
                administracionFormulariosEjb.eliminarResponsableFormularios(responsableFormularioHolderFL
                        .getRespFormSeleccionado().getResponsableFormulario().getId());
                CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
                return true;
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            }
        } else {
            CirculemosAccesoBundleGeneral.addMensajeNoSeleccionItem();
        }
        return false;
    }

    /**
     * Limpia el campo de responsable de formulario seleccionado en la interfaz de registro.
     * 
     * @author luis.forero(2015-11-05)
     */
    public void limpiarCampoResponsable() {
        final ResponsableFormularioFL responsableFormularioFL = findFlowObject(OBJ_RESPONSABLE_FORMULARIO_FL,
                RESPONSABLE_FORMULARIO_FL);
        responsableFormularioFL.setUnificacionResponsable(new UnificacionResponsableDTO());
    }
}
