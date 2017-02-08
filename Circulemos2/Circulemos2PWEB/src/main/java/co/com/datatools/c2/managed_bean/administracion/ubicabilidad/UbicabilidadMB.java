package co.com.datatools.c2.managed_bean.administracion.ubicabilidad;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.comun.DepartamentoDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.LocalidadDTO;
import co.com.datatools.c2.dto.comun.MunicipioDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaHistoricoDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.RepresentanteLegalDTO;
import co.com.datatools.c2.dto.personas.TipoFuenteInformacionDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TelefonoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.TipoTelefonoDTO;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion.vo.RegistroTablaVO;
import co.com.datatools.c2.managed_bean.comun.FachadaCatalogosMB;
import co.com.datatools.c2.managed_bean.comun.UtilidadMB;
import co.com.datatools.c2.negocio.error.ErrorAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRDireccion;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRSeguridadCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRUbicabilidad;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

@ManagedBean
@SessionScoped
public class UbicabilidadMB extends AbstractC2ManagedBean {

    // Logger
    private static final Logger LOGGER = Logger.getLogger(UbicabilidadMB.class.getName());

    private static final String CONTENT_TYPE = "application/pdf";
    private final String NOMBRE_ARCHIVO = "Documento ubicabilidad.pdf";
    private static final String NOMBRE_BUNDLE_UBICABILIDAD = "labelUbicabilidad";
    private static final int TIPO_DATO_DIRECCION = 1;
    private static final int TIPO_DATO_TELEFONO = 2;
    private static final int TIPO_DATO_CORREO = 3;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @EJB
    private IRPersona iRPersona;
    @EJB
    private IRParametro iRParametro;
    @EJB
    private IRDireccion iRDireccion;
    @EJB
    private IRUbicabilidad iRUbicabilidad;

    // Documento radicado
    private StreamedContent documentoUbicabilidad;

    @ManagedProperty(value = "#{utilidadMB}")
    private UtilidadMB utilidadMB;

    @ManagedProperty(value = "#{fachadaCatalogosMB}")
    private FachadaCatalogosMB fachadaCatalogosMB;

    @EJB
    private IRAdministracion administracionEjb;

    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;

    @EJB
    private IRSeguridadCirculemos iRSeguridadCirculemos;

    /**
     * Tipo de identificacion juridico
     */
    private TipoIdentificacionPersonaDTO tipoIdentificacionEmpresa;

    /**
     * Usuario que ingresa a ubicabilidad
     */
    private UsuarioPersonaDTO usuarioSesion;
    private Map<Integer, String> tiposTelefono;
    private Map<Integer, String> departamentos;
    private Map<Integer, String> municipios;
    private Map<Integer, String> localidades;
    private Map<Integer, String> tiposDireccion;

    private String patternNombre;
    private String patternNombreComercial;

    @PostConstruct
    public void init() {
        LOGGER.debug("UbicabilidadMB::init()");
        documentoUbicabilidad = null;

        // Tipo de identificacion para empresa
        tipoIdentificacionEmpresa = administracionEjb.obtenerTipoIdentificacionEmpresaJuridicaPais(getPais().getId());
        // limpiarFormulario();
    }

    // public UbicabilidadMB() {
    // limpiarFormulario();
    // }

    /**
     * Inicializa los datos del formulario de ubicabilidad dependiendo si es edicion o creacion
     * 
     * @param idTipoDocumentoInfractor
     * @param numeroDocumento
     * @param soloGuardar
     * @param ciudadanoPresente
     * @author Dixon.Alvarez
     */
    public void inicializarUbicabilidad(Integer idTipoDocumentoInfractor, String numeroDocumento, boolean soloGuardar,
            Boolean ciudadanoPresente) {
        LOGGER.debug("UbicabilidadMB::consultarPersona(Integer,String)");
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        limpiarFormulario();
        actualizarDatosUbicabilidadFL.setNumeroDocumento(numeroDocumento);
        actualizarDatosUbicabilidadFL.setIdTipoIdentificacion(idTipoDocumentoInfractor);
        actualizarDatosUbicabilidadFL.setEditar(false);
        actualizarDatosUbicabilidadFL.setSoloGuardar(soloGuardar);
        actualizarDatosUbicabilidadFL.setCiudadanoPresente(ciudadanoPresente);
        actualizarDatosUbicabilidadFL.setCorreoGuardar(new RegistroTablaVO<CorreoPersonaDTO>(new CorreoPersonaDTO()));
        actualizarDatosUbicabilidadFL.setCorreosPersona(new ArrayList<RegistroTablaVO<CorreoPersonaDTO>>());
        actualizarDatosUbicabilidadFL
                .setTelefonoGuardar(new RegistroTablaVO<TelefonoPersonaDTO>(new TelefonoPersonaDTO()));
        actualizarDatosUbicabilidadFL.setTelefonosPersona(new ArrayList<RegistroTablaVO<TelefonoPersonaDTO>>());
        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setTipoTelefono(new TipoTelefonoDTO());
        actualizarDatosUbicabilidadFL.setDireccionesPersona(new ArrayList<RegistroTablaVO<DireccionPersonaDTO>>());
        RepresentanteLegalDTO representanteLegalDTO = new RepresentanteLegalDTO();
        representanteLegalDTO.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());

        actualizarDatosUbicabilidadFL.setRepresentante(representanteLegalDTO);
        actualizarDatosUbicabilidadFL.setExisteRepresentante(false);

        usuarioSesion = iRSeguridadCirculemos.obtenerUsuarioDto();
        // llenamos Map con lista de tipos de telefono para obtener el nombre de los mismos
        tiposTelefono = new HashMap<Integer, String>();
        for (SelectItem item : fachadaCatalogosMB.catTipoTelefono()) {
            tiposTelefono.put((Integer) item.getValue(), item.getLabel());
        }
        // llenamos Map con lista de departamentos para obtener el nombre de los mismos
        List<DepartamentoDTO> departamentoDTOs = administracionEjb.consultarDepartamento(null);
        departamentos = new HashMap<Integer, String>();
        for (DepartamentoDTO departamentoDTO : departamentoDTOs) {
            departamentos.put(departamentoDTO.getId(), departamentoDTO.getNombre());
        }
        // llenamos Map con lista de municipios para obtener el nombre de los mismos
        List<MunicipioDTO> municipioDTOs = administracionEjb.consultarMunicipio(null);
        municipios = new HashMap<Integer, String>();
        for (MunicipioDTO municipioDTO : municipioDTOs) {
            municipios.put(municipioDTO.getId(), municipioDTO.getNombre());
        }
        // llenamos Map con lista de localidades para obtener el nombre de los mismos
        List<LocalidadDTO> localidadDTOs = administracionEjb.consultarLocalidad(null);
        localidades = new HashMap<Integer, String>();
        for (LocalidadDTO localidadDTO : localidadDTOs) {
            localidades.put(localidadDTO.getId(), localidadDTO.getNombre());
        }
        // llenamos Map con lista de tipos de telefono para obtener el nombre de los mismos
        tiposDireccion = new HashMap<Integer, String>();
        for (SelectItem item : fachadaCatalogosMB.catTipoDireccion()) {
            tiposDireccion.put((Integer) item.getValue(), item.getLabel());
        }

        // Generamos lista de tipos de documento que puede seleccionar un representante legal
        actualizarDatosUbicabilidadFL.setTiposDocumentoRepresentante(new ArrayList<SelectItem>());
        for (SelectItem item : fachadaCatalogosMB.catTipoIdentificacion()) {
            if (((Integer) item.getValue()).intValue() != tipoIdentificacionEmpresa.getId().intValue()) {
                actualizarDatosUbicabilidadFL.getTiposDocumentoRepresentante().add(item);
            }
        }

        if (idTipoDocumentoInfractor != null && StringUtils.isNotBlank(numeroDocumento)) {
            consultarPersona();
            actualizarDatosUbicabilidadFL.setEditar(true);
        }
    }

    /**
     * Consulta una persona por tipo y numero de identificacion para cargarlo en la vista
     */
    public void consultarPersona() {
        LOGGER.debug("UbicabilidadMB::consultarPersona()");
        limpiarFormulario();

        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);

        actualizarDatosUbicabilidadFL.setEsPersonaJuridica(false);
        if (actualizarDatosUbicabilidadFL.getIdTipoIdentificacion() != null) {
            // Si es de tipo juridico para que solo muestre la razon social
            if (actualizarDatosUbicabilidadFL.getIdTipoIdentificacion() != null && tipoIdentificacionEmpresa.getId()
                    .equals(actualizarDatosUbicabilidadFL.getIdTipoIdentificacion())) {
                actualizarDatosUbicabilidadFL.setEsPersonaJuridica(true);
            }
        }
        if (actualizarDatosUbicabilidadFL.getIdTipoIdentificacion() != null
                && StringUtils.isNotBlank(actualizarDatosUbicabilidadFL.getNumeroDocumento())) {
            PersonaDTO personaDTO = iRPersona.consultarPersona(getCodigoOrganismoTransito(),
                    actualizarDatosUbicabilidadFL.getIdTipoIdentificacion(),
                    actualizarDatosUbicabilidadFL.getNumeroDocumento());

            if (personaDTO != null) {
                if (personaDTO instanceof PersonaJuridicaDTO) {
                    actualizarDatosUbicabilidadFL.setEsPersonaJuridica(true);
                    actualizarDatosUbicabilidadFL.setPersonaJuridicaDTO((PersonaJuridicaDTO) personaDTO);
                    actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO()
                            .setRepresentanteLegalList(iRPersona.consultarRepresentastesLegales(personaDTO.getId()));
                    if (!actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO().getRepresentanteLegalList().isEmpty()) {
                        actualizarDatosUbicabilidadFL.setExisteRepresentante(true);
                        actualizarDatosUbicabilidadFL.setRepresentante(actualizarDatosUbicabilidadFL
                                .getPersonaJuridicaDTO().getRepresentanteLegalList().get(actualizarDatosUbicabilidadFL
                                        .getPersonaJuridicaDTO().getRepresentanteLegalList().size() - 1));
                    }
                } else {
                    actualizarDatosUbicabilidadFL.setPersona(personaDTO);
                    actualizarDatosUbicabilidadFL.setEsPersonaJuridica(false);
                }
                // Consulta todos los datos de la persona
                consultarPersonaHistorico();
                consultarCorreos();
                consultarDirecciones();
                consultarTelefonos();
            }
        }
    }

    /**
     * Limpia los datos basicos del formulario de registro de ubicacion
     */
    private void limpiarFormulario() {
        LOGGER.debug("UbicabilidadMB::limpiarFormulario()");
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        actualizarDatosUbicabilidadFL.setPersona(new PersonaDTO());
        actualizarDatosUbicabilidadFL.setPersonaJuridicaDTO(new PersonaJuridicaDTO());
        actualizarDatosUbicabilidadFL.setEsPersonaJuridica(false);
        actualizarDatosUbicabilidadFL.setCorreosPersona(new ArrayList<RegistroTablaVO<CorreoPersonaDTO>>());
        actualizarDatosUbicabilidadFL.setTelefonosPersona(new ArrayList<RegistroTablaVO<TelefonoPersonaDTO>>());
        actualizarDatosUbicabilidadFL.setDireccionesPersona(new ArrayList<RegistroTablaVO<DireccionPersonaDTO>>());
    }

    /**
     * Consulta el historico de la persona
     */
    private void consultarPersonaHistorico() {
        LOGGER.debug("UbicabilidadMB::consultarPersonaHistorico()");
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);

        PersonaHistoricoDTO personaHistoricoDTO = new PersonaHistoricoDTO();
        OrganismoTransitoDTO organismo = new OrganismoTransitoDTO();
        organismo.setCodigoOrganismo(getCodigoOrganismoTransito());
        personaHistoricoDTO.setOrganismoTransito(organismo);
        TipoIdentificacionPersonaDTO tipoIdentificacion = new TipoIdentificacionPersonaDTO();
        tipoIdentificacion.setId(actualizarDatosUbicabilidadFL.getIdTipoIdentificacion());
        personaHistoricoDTO.setTipoIdentificacion(tipoIdentificacion);
        personaHistoricoDTO.setNumeroIdentificacion(actualizarDatosUbicabilidadFL.getNumeroDocumento());
        List<PersonaHistoricoDTO> personaHistoricoDTOList = iRPersona.consultarHistoricoPersona(personaHistoricoDTO);

        if (!personaHistoricoDTOList.isEmpty()) {
            actualizarDatosUbicabilidadFL.setHistoricoPersonas(personaHistoricoDTOList);
        }
    }

    /**
     * Meotodo que se encarga de validar que la informacion de la persona esta correcta para poder guardar
     * 
     * @return TRUE datos correctos FALSE datos incorrectos
     * @author Dixon.Alvarez
     */
    public boolean validarUbicabilidad() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("validarUbicabilidad()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        boolean ubicabilidadValida = true;
        if (actualizarDatosUbicabilidadFL.getDireccionesPersona() == null
                || actualizarDatosUbicabilidadFL.getDireccionesPersona().isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_no_direccion");
            ubicabilidadValida = false;
        } else {
            if (actualizarDatosUbicabilidadFL.isEsPersonaJuridica()) {
                // En caso de que sea persona juridica debe tener un representante legal registrado en el sistema
                if (actualizarDatosUbicabilidadFL.getRepresentante().getId() == null) {
                    addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_sin_representante");
                    ubicabilidadValida = false;
                }
                // En caso de que sea persona juridica la fecha inicio vigencia debe ser menor a fecha fin vigencia
                else if (actualizarDatosUbicabilidadFL.getRepresentante().getFechaFin() != null) {
                    if (actualizarDatosUbicabilidadFL.getRepresentante().getFechaInicio()
                            .compareTo(actualizarDatosUbicabilidadFL.getRepresentante().getFechaFin()) >= 0) {
                        addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_fecha_vigencia");
                        ubicabilidadValida = false;
                    }
                }
            }
        }
        // Validamos que todos las direcciones adicionadas esten validadas
        if (actualizarDatosUbicabilidadFL.getDireccionesPersona() != null
                && !actualizarDatosUbicabilidadFL.getDireccionesPersona().isEmpty() && ubicabilidadValida) {
            for (RegistroTablaVO<DireccionPersonaDTO> direccion : actualizarDatosUbicabilidadFL
                    .getDireccionesPersona()) {
                if (direccion.getDto().getId() == null) {
                    if (direccion.getDto().getPrioridad() == null || direccion.getDto().getEstado() == null) {
                        addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_direccion_sin_validacion");
                        ubicabilidadValida = false;
                        break;
                    }
                }
            }
        }

        // Validamos que todos los telefonos adicionados esten validados
        if (actualizarDatosUbicabilidadFL.getTelefonosPersona() != null
                && !actualizarDatosUbicabilidadFL.getTelefonosPersona().isEmpty() && ubicabilidadValida) {
            for (RegistroTablaVO<TelefonoPersonaDTO> telefono : actualizarDatosUbicabilidadFL.getTelefonosPersona()) {
                if (telefono.getDto().getId() == null) {
                    if (telefono.getDto().getPrioridad() == null || telefono.getDto().getEstado() == null) {
                        addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_telefono_sin_validacion");
                        ubicabilidadValida = false;
                        break;
                    }
                }
            }
        }
        // Validamos que todos los correos adicionados esten validados
        if (actualizarDatosUbicabilidadFL.getCorreosPersona() != null
                && !actualizarDatosUbicabilidadFL.getCorreosPersona().isEmpty() && ubicabilidadValida) {
            for (RegistroTablaVO<CorreoPersonaDTO> correo : actualizarDatosUbicabilidadFL.getCorreosPersona()) {
                if (correo.getDto().getId() == null) {
                    if (correo.getDto().getPrioridad() == null || correo.getDto().getEstado() == null) {
                        addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_correo_sin_validacion");
                        ubicabilidadValida = false;
                        break;
                    }
                }
            }
        }

        return ubicabilidadValida;
    }

    /**
     * Hace el llamado al metodo de negocio para guardar la persona
     * 
     * @return PersonaDTO persistida
     * @author Dixon.Alvarez
     */
    public PersonaDTO guardarUbicabilidad() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("guardarUbicabilidad()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        actualizarDatosUbicabilidadFL.setConfirmarUbicabilidad(false);

        TipoFuenteInformacionDTO tipoFuenteInformacionDTO = obtenerFuenteInformacion(
                actualizarDatosUbicabilidadFL.getCiudadanoPresente());
        PersonaDTO persona = null;
        // Verifica si es persona natural o juridica
        if (!actualizarDatosUbicabilidadFL.isEsPersonaJuridica()) {
            persona = actualizarDatosUbicabilidadFL.getPersona();

        } else {
            if (actualizarDatosUbicabilidadFL.getRepresentante().getIdRepresentante() != null) {
                // El ultimo representante registrado para la persona juridica es el actual
                actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO().getRepresentanteLegalList().set(
                        actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO().getRepresentanteLegalList().size() - 1,
                        actualizarDatosUbicabilidadFL.getRepresentante());
            } else {
                actualizarDatosUbicabilidadFL.getRepresentante()
                        .setPersonaJuridica(actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO());
                actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO().getRepresentanteLegalList()
                        .add(actualizarDatosUbicabilidadFL.getRepresentante());
            }
            persona = actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO();
        }
        // Valida si es registro
        if (persona.getId() == null) {
            persona.setOrganismoTransito(new OrganismoTransitoDTO(getCodigoOrganismoTransito()));
            persona.setNumeroIdentificacion(actualizarDatosUbicabilidadFL.getNumeroDocumento());
            persona.setTipoIdentificacion(
                    new TipoIdentificacionPersonaDTO(actualizarDatosUbicabilidadFL.getIdTipoIdentificacion()));
        }

        try {
            // Fuente de informacion C2
            persona.setFuenteInformacion(tipoFuenteInformacionDTO);

            // Llenamos lista de direcciones
            persona.setDireccionPersonaList(new ArrayList<DireccionPersonaDTO>());
            for (RegistroTablaVO<DireccionPersonaDTO> direccion : actualizarDatosUbicabilidadFL
                    .getDireccionesPersona()) {
                persona.getDireccionPersonaList().add(direccion.getDto());
            }
            // Llenamos lista de correos
            persona.setCorreoPersonaList(new ArrayList<CorreoPersonaDTO>());
            for (RegistroTablaVO<CorreoPersonaDTO> correo : actualizarDatosUbicabilidadFL.getCorreosPersona()) {
                persona.getCorreoPersonaList().add(correo.getDto());
            }
            // Llenamos lista de telefonos
            persona.setTelefonoPersonaDTOs(new ArrayList<TelefonoPersonaDTO>());
            for (RegistroTablaVO<TelefonoPersonaDTO> telefono : actualizarDatosUbicabilidadFL.getTelefonosPersona()) {
                persona.getTelefonoPersonaDTOs().add(telefono.getDto());
            }

            actualizarDatosUbicabilidadFL.getPersonaUbicabilidadDTO().setPersonaDTO(persona);
            actualizarDatosUbicabilidadFL.getPersonaUbicabilidadDTO()
                    .setSoloGuardar(actualizarDatosUbicabilidadFL.isSoloGuardar());

            persona = iRPersona.guardarPersonaConDocumento(actualizarDatosUbicabilidadFL.getPersonaUbicabilidadDTO());

            if (!actualizarDatosUbicabilidadFL.isSoloGuardar()) {
                List<Long> documentoList = new ArrayList<>();
                documentoList.add(persona.getIdDocumento());
                byte[] documento = iRDocumentosCirculemos.consultarDocumentosPDF(documentoList);
                if (documento != null) {
                    documentoUbicabilidad = new DefaultStreamedContent(new ByteArrayInputStream(documento),
                            CONTENT_TYPE, NOMBRE_ARCHIVO);
                    actualizarDatosUbicabilidadFL.setConfirmarUbicabilidad(true);
                }
            }
            addInfoMessage("labelProcesoImpugnacion", "persona_actualizada");
            if (persona.getIdDocumento() != null) {
                iRPersona.enviarCorreoUbicabilidad(persona);
            }
        } catch (CirculemosNegocioException e) {
            if (e.getErrorInfo().getCodigoError()
                    .equals(ErrorAdministracion.Ubicabilidad.COB_015079.getCodigoError())) {
                actualizarDatosUbicabilidadFL.setMensajeAdv(e.getErrorInfo().getDescError());
                actualizarDatosUbicabilidadFL.setVerMensajeAdv(true);
            } else {
                CirculemosErrorHandler.handleException(e);
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
        return persona;
    }

    /**
     * Consulta los telefonos de la persona
     */
    private void consultarTelefonos() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("consultarTelefonos()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        TelefonoPersonaDTO telefonoPersonaDTO = new TelefonoPersonaDTO();
        telefonoPersonaDTO.setPersona(actualizarDatosUbicabilidadFL.getPersona());
        if (actualizarDatosUbicabilidadFL.isEsPersonaJuridica()) {
            telefonoPersonaDTO.setPersona(actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO());
        }
        for (TelefonoPersonaDTO telefono : iRUbicabilidad.consultarTelefonos(telefonoPersonaDTO)) {
            actualizarDatosUbicabilidadFL.getTelefonosPersona().add(new RegistroTablaVO<TelefonoPersonaDTO>(telefono));
        }
    }

    /**
     * Consulta las direcciones que tiene la persona
     */
    private void consultarDirecciones() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("consultarDirecciones()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
        direccionPersonaDTO.setPersona(actualizarDatosUbicabilidadFL.getPersona());
        if (actualizarDatosUbicabilidadFL.isEsPersonaJuridica()) {
            direccionPersonaDTO.setPersona(actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO());
        }
        List<String> array = new ArrayList<>();
        for (DireccionPersonaDTO direccion : iRUbicabilidad.consultarDireccionPersona(direccionPersonaDTO)) {
            String direccionActual = direccion.getDireccion().getComplemento().toLowerCase();
            if (!array.contains(direccionActual)) {
                array.add(direccionActual);
                actualizarDatosUbicabilidadFL.getDireccionesPersona()
                        .add(new RegistroTablaVO<DireccionPersonaDTO>(direccion));
            }
        }
    }

    /**
     * Consulta los correos electronicos que tiene la persona
     */
    private void consultarCorreos() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("consultarCorreos()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
        correoPersonaDTO.setPersona(actualizarDatosUbicabilidadFL.getPersona());
        if (actualizarDatosUbicabilidadFL.isEsPersonaJuridica()) {
            correoPersonaDTO.setPersona(actualizarDatosUbicabilidadFL.getPersonaJuridicaDTO());
        }
        for (CorreoPersonaDTO correo : iRUbicabilidad.consultarCorreos(correoPersonaDTO)) {
            actualizarDatosUbicabilidadFL.getCorreosPersona().add(new RegistroTablaVO<CorreoPersonaDTO>(correo));
        }
    }

    /**
     * despliega popup para adicionar correo
     */
    public void irAdicionarCorreo() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("irActualizarCorreo()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        actualizarDatosUbicabilidadFL.setCorreoGuardar(new RegistroTablaVO<CorreoPersonaDTO>(new CorreoPersonaDTO()));
        actualizarDatosUbicabilidadFL
                .setTituloPopUpCorreo(getBundle(NOMBRE_BUNDLE_UBICABILIDAD).getString("tit_adicionar_correo"));
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("PF('popupAdicionarCorreo').show();");
    }

    /**
     * Valida el correo y lo adiciona a la lista de correos desplegada en la pantalla de ubicabilidad
     */
    public void adicionarCorreo() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("adicionarCorreo()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        // Si se esta actualizando un registro
        if (actualizarDatosUbicabilidadFL.isActualizarCorreo()) {
            // Validamos que no se repita una calificacion igual a las que ya existen en la lista de correos
            boolean calificacionValida = true;
            TipoFuenteInformacionDTO tipoFuente = obtenerFuenteInformacion(
                    actualizarDatosUbicabilidadFL.getCiudadanoPresente());
            for (RegistroTablaVO<CorreoPersonaDTO> correo : actualizarDatosUbicabilidadFL.getCorreosPersona()) {
                if (!actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getRowKey().equals(correo.getRowKey())) {
                    if (actualizarDatosUbicabilidadFL.getCalificacion().equals(correo.getDto().getPrioridad())
                            && tipoFuente.getId().intValue() == correo.getDto().getTipoFuenteInformacion().getId()
                                    .intValue()) {
                        calificacionValida = false;
                        break;
                    }
                }
            }
            if (calificacionValida) {
                actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto()
                        .setPrioridad(actualizarDatosUbicabilidadFL.getCalificacion());
                actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto()
                        .setEstado(actualizarDatosUbicabilidadFL.getValidacion());
                actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().setTipoFuenteInformacion(
                        obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().setTipoFuenteValidacion(
                        obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().setUsuarioActualiza(usuarioSesion);
                actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().setUsuarioValida(usuarioSesion);
                actualizarDatosUbicabilidadFL.setActualizarCorreo(false);
                RequestContext.getCurrentInstance().execute("PF('popupValidarCalificar').hide();");

            } else {
                addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_calificacion");
            }
        } else { // Si se esta creando un nuevo registro
            // Validamos que la direccion de correo electronico cumpla con las condiciones
            if (StringUtils.isNotBlank(actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().getCorreoElectronico())
                    && !actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().getCorreoElectronico()
                            .matches(ExpresionesRegulares.REGEX_EMAIL)) {
                getFacesContext().addMessage("form-correo:idCorreoNuevo",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                ResourceBundle
                                        .getBundle("co.com.datatools.c2.bundle.util.Mensajes", Locale.getDefault())
                                        .getString("msg_err_email_invalido")));
            } else {
                // Validamos que no se repita un correo igual a los que ya existen en la lista de correos
                boolean correoNoRepetido = true;
                for (RegistroTablaVO<CorreoPersonaDTO> correo : actualizarDatosUbicabilidadFL.getCorreosPersona()) {
                    if (!actualizarDatosUbicabilidadFL.getCorreoGuardar().getRowKey().equals(correo.getRowKey())) {
                        if (actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().getCorreoElectronico()
                                .equalsIgnoreCase(correo.getDto().getCorreoElectronico())) {
                            correoNoRepetido = false;
                            break;
                        }
                    }
                }
                if (correoNoRepetido) {
                    if (!actualizarDatosUbicabilidadFL.isActualizarCorreo()) {
                        actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto()
                                .setPersona(actualizarDatosUbicabilidadFL.getPersona());
                        actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().setFechaActualizacion(new Date());
                        actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().setFechaRegistro(new Date());
                        actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().setTipoFuenteInformacion(
                                obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                        actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().setTipoFuenteValidacion(
                                obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                        actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().setUsuarioActualiza(usuarioSesion);
                        actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().setUsuarioValida(usuarioSesion);
                        actualizarDatosUbicabilidadFL.getCorreoGuardar().getDto().setUsuarioRegistro(usuarioSesion);
                        actualizarDatosUbicabilidadFL.getCorreosPersona()
                                .add(actualizarDatosUbicabilidadFL.getCorreoGuardar());
                        RequestContext request = RequestContext.getCurrentInstance();
                        request.execute("PF('popupAdicionarCorreo').hide();");
                    }
                } else {
                    addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_correo_repetido");
                }
            }

        }
    }

    /**
     * Quita de la lista el correo seleccionado
     */
    public void eliminarCorreo() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("eliminarCorreo()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        if (actualizarDatosUbicabilidadFL.getCorreoSeleccionado() != null) {
            if (actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().getId() == null) {
                actualizarDatosUbicabilidadFL.getCorreosPersona()
                        .remove(actualizarDatosUbicabilidadFL.getCorreoSeleccionado());
                actualizarDatosUbicabilidadFL.setCorreoSeleccionado(null);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_eliminar_registro");
            }
        }
    }

    /**
     * despliega popup para adicionar telefono
     */
    public void irAdicionarTelefono() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("irActualizarTelefono()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        actualizarDatosUbicabilidadFL.setActualizarTelefono(false);
        actualizarDatosUbicabilidadFL
                .setTelefonoGuardar(new RegistroTablaVO<TelefonoPersonaDTO>(new TelefonoPersonaDTO()));
        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setTipoTelefono(new TipoTelefonoDTO());
        actualizarDatosUbicabilidadFL
                .setTituloPopUpTelefono(getBundle(NOMBRE_BUNDLE_UBICABILIDAD).getString("tit_adicionar_telefono"));
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("PF('popupAdicionarTelefono').show();");
    }

    /**
     * Valida el telefono y lo adiciona a la lista de telefonos desplegada en la pantalla de ubicabilidad
     */
    public void adicionarTelefono() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("adicionarTelefono()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        // Si se va actualizar un registro de telefono
        if (actualizarDatosUbicabilidadFL.isActualizarTelefono()) {
            // Validamos que no se repita una calificacion igual a las que ya existen en la lista de telefonos
            boolean calificacionValida = true;
            TipoFuenteInformacionDTO tipoFuente = obtenerFuenteInformacion(
                    actualizarDatosUbicabilidadFL.getCiudadanoPresente());
            for (RegistroTablaVO<TelefonoPersonaDTO> telefono : actualizarDatosUbicabilidadFL.getTelefonosPersona()) {
                if (!actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getRowKey().equals(telefono.getRowKey())) {
                    if (actualizarDatosUbicabilidadFL.getCalificacion().equals(telefono.getDto().getPrioridad())
                            && tipoFuente.getId().intValue() == telefono.getDto().getTipoFuenteInformacion().getId()
                                    .intValue()) {
                        calificacionValida = false;
                        break;
                    }
                }
            }
            if (calificacionValida) {
                actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto()
                        .setPrioridad(actualizarDatosUbicabilidadFL.getCalificacion());
                actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto()
                        .setEstado(actualizarDatosUbicabilidadFL.getValidacion());
                actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().setTipoFuenteInformacion(
                        obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().setTipoFuenteValidacion(
                        obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().setUsuarioActualiza(usuarioSesion);
                actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().setUsuarioValida(usuarioSesion);
                actualizarDatosUbicabilidadFL.setActualizarCorreo(false);
                RequestContext.getCurrentInstance().execute("PF('popupValidarCalificar').hide();");

            } else {
                addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_calificacion");
            }
        } else { // Si se va crear un nuevo registro de telefono
            // Validamos que la direccion de telefono electronico cumpla con las condiciones
            if (StringUtils.isNotBlank(actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().getNumeroTelefono())
                    && !actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().getNumeroTelefono()
                            .matches(ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO)) {
                getFacesContext().addMessage("form-telefono:idTelefonoNuevo",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(NOMBRE_BUNDLE_UBICABILIDAD).getString("msg_formato_numero_telefonico")));
            } else {
                // Validamos que no se repita un telefono igual a los que ya existen en la lista de telefonos
                boolean telefonoNoRepetido = true;
                for (RegistroTablaVO<TelefonoPersonaDTO> telefono : actualizarDatosUbicabilidadFL
                        .getTelefonosPersona()) {
                    if (!actualizarDatosUbicabilidadFL.getTelefonoGuardar().getRowKey().equals(telefono.getRowKey())) {
                        if (actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().getNumeroTelefono()
                                .equalsIgnoreCase(telefono.getDto().getNumeroTelefono())) {
                            telefonoNoRepetido = false;
                            break;
                        }
                    }
                }
                if (telefonoNoRepetido) {
                    if (!actualizarDatosUbicabilidadFL.isActualizarTelefono()) {
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto()
                                .setPersona(actualizarDatosUbicabilidadFL.getPersona());
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setFechaActualizacion(new Date());
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setFechaRegistro(new Date());
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setTipoFuenteInformacion(
                                obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setTipoFuenteValidacion(
                                obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setUsuarioActualiza(usuarioSesion);
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setUsuarioValida(usuarioSesion);
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().setUsuarioRegistro(usuarioSesion);
                        actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto().getTipoTelefono()
                                .setNombre(tiposTelefono.get(actualizarDatosUbicabilidadFL.getTelefonoGuardar().getDto()
                                        .getTipoTelefono().getId()));
                        actualizarDatosUbicabilidadFL.getTelefonosPersona()
                                .add(actualizarDatosUbicabilidadFL.getTelefonoGuardar());
                        RequestContext request = RequestContext.getCurrentInstance();
                        request.execute("PF('popupAdicionarTelefono').hide();");
                    }
                } else {
                    addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_telefono_repetido");
                }
            }
        }

    }

    /**
     * Quita de la lista el telefono seleccionado
     */
    public void eliminarTelefono() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("eliminarTelefono()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        if (actualizarDatosUbicabilidadFL.getTelefonoSeleccionado() != null) {
            if (actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().getId() == null) {
                actualizarDatosUbicabilidadFL.getTelefonosPersona()
                        .remove(actualizarDatosUbicabilidadFL.getTelefonoSeleccionado());
                actualizarDatosUbicabilidadFL.setTelefonoSeleccionado(null);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_eliminar_registro");
            }
        }
    }

    /**
     * Quita de la lista la direccion seleccionada
     */
    public void eliminarDireccion() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("eliminarDireccion()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        if (actualizarDatosUbicabilidadFL.getDireccionSeleccionado() != null) {
            if (actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().getId() == null) {
                actualizarDatosUbicabilidadFL.getDireccionesPersona()
                        .remove(actualizarDatosUbicabilidadFL.getDireccionSeleccionado());
                actualizarDatosUbicabilidadFL.setDireccionSeleccionado(null);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_eliminar_registro");
            }
        }
    }

    /**
     * Actualiza la direccion seleccionada
     */
    public void actualizarDireccion() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("actualizarDireccion()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        if (actualizarDatosUbicabilidadFL.isActualizarDireccion()
                && actualizarDatosUbicabilidadFL.getDireccionSeleccionado() != null) {
            boolean calificacionValida = true;
            TipoFuenteInformacionDTO tipoFuente = obtenerFuenteInformacion(
                    actualizarDatosUbicabilidadFL.getCiudadanoPresente());
            for (RegistroTablaVO<DireccionPersonaDTO> direccion : actualizarDatosUbicabilidadFL
                    .getDireccionesPersona()) {
                if (!actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getRowKey()
                        .equals(direccion.getRowKey())) {
                    if (actualizarDatosUbicabilidadFL.getCalificacion().equals(direccion.getDto().getPrioridad())
                            && tipoFuente.getId().intValue() == direccion.getDto().getTipoFuenteInformacion().getId()
                                    .intValue()) {
                        calificacionValida = false;
                        break;
                    }
                }
            }
            if (calificacionValida) {
                actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto()
                        .setPrioridad(Integer.valueOf(actualizarDatosUbicabilidadFL.getCalificacion()));
                actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto()
                        .setEstado(Boolean.valueOf(actualizarDatosUbicabilidadFL.getValidacion()));
                actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().setTipoFuenteInformacion(
                        obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().setTipoFuenteValidacion(
                        obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
                actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().setUsuarioActualiza(usuarioSesion);
                actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().setUsuarioValida(usuarioSesion);
                actualizarDatosUbicabilidadFL.setActualizarDireccion(false);
                RequestContext.getCurrentInstance().execute("PF('popupValidarCalificar').hide();");
            } else {
                addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_calificacion");
            }
        }
    }

    /**
     * Adiciona una direccion a la lista de direcciones de la persona
     * 
     * @param direccionDTO
     *            Corresponde a la direccion que se va adicionar
     */
    public void adicionarDireccion(DireccionDTO direccionDTO) {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("adicionarDireccion()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        DireccionPersonaDTO direccionGuardar = new DireccionPersonaDTO();
        direccionDTO.getDepartamento().setNombre(departamentos.get(direccionDTO.getDepartamento().getId()));
        direccionDTO.getMunicipio().setNombre(municipios.get(direccionDTO.getMunicipio().getId()));
        direccionDTO.getLocalidad().setNombre(localidades.get(direccionDTO.getLocalidad().getId()));
        direccionDTO.getTipoUbicabilidad().setNombre(tiposDireccion.get(direccionDTO.getTipoUbicabilidad().getId()));
        direccionGuardar.setDireccion(direccionDTO);
        direccionGuardar.setPersona(actualizarDatosUbicabilidadFL.getPersona());
        direccionGuardar.setFechaActualizacion(new Date());
        direccionGuardar.setFechaRegistro(new Date());
        direccionGuardar.setTipoFuenteInformacion(
                obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
        direccionGuardar.setTipoFuenteValidacion(
                obtenerFuenteInformacion(actualizarDatosUbicabilidadFL.getCiudadanoPresente()));
        direccionGuardar.setUsuarioActualiza(usuarioSesion);
        direccionGuardar.setUsuarioValida(usuarioSesion);
        direccionGuardar.setUsuarioRegistro(usuarioSesion);
        actualizarDatosUbicabilidadFL.getDireccionesPersona()
                .add(new RegistroTablaVO<DireccionPersonaDTO>(direccionGuardar));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form-contenido");
    }

    /**
     * Despliega popup que permite dar la calificacion y validacion del registro seleccionado teniendo en cuenta el tipo de dato a modificar de la
     * persona (CORREO, TELEFONO, DIRECCION)
     * 
     * @param tipoDatoValidar
     *            CORREO(3), TELEFONO(2), DIRECCION(1)
     */
    public void irValidarCalificar(int tipoDatoValidar) {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("validarCalificar(int)"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        actualizarDatosUbicabilidadFL.setTipoDatoValidado(tipoDatoValidar);
        Boolean validacion = null;
        Integer calificacion = null;
        switch (tipoDatoValidar) {
        case TIPO_DATO_DIRECCION:
            if (actualizarDatosUbicabilidadFL.getDireccionSeleccionado() != null) {
                if (actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().getEstado() != null) {
                    validacion = Boolean
                            .valueOf(actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().getEstado());
                }
                if (actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().getPrioridad() != null) {
                    calificacion = Integer
                            .valueOf(actualizarDatosUbicabilidadFL.getDireccionSeleccionado().getDto().getPrioridad());
                }
                actualizarDatosUbicabilidadFL.setActualizarDireccion(true);
            }
            break;
        case TIPO_DATO_TELEFONO:
            if (actualizarDatosUbicabilidadFL.getTelefonoSeleccionado() != null) {
                if (actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().getEstado() != null) {
                    validacion = Boolean
                            .valueOf(actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().getEstado());
                }
                if (actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().getPrioridad() != null) {
                    calificacion = Integer
                            .valueOf(actualizarDatosUbicabilidadFL.getTelefonoSeleccionado().getDto().getPrioridad());
                }
                actualizarDatosUbicabilidadFL.setActualizarTelefono(true);
            }
            break;
        case TIPO_DATO_CORREO:
            if (actualizarDatosUbicabilidadFL.getCorreoSeleccionado() != null) {
                if (actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().getEstado() != null) {
                    validacion = Boolean
                            .valueOf(actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().getEstado());
                }
                if (actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().getPrioridad() != null) {
                    calificacion = Integer
                            .valueOf(actualizarDatosUbicabilidadFL.getCorreoSeleccionado().getDto().getPrioridad());
                }
                actualizarDatosUbicabilidadFL.setActualizarCorreo(true);
            }
            break;
        default:
            break;
        }
        actualizarDatosUbicabilidadFL.setValidacion(validacion);
        actualizarDatosUbicabilidadFL.setCalificacion(calificacion);
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("PF('popupValidarCalificar').show();");
    }

    /**
     * Guarda los cambios en el respectivo registro seleccionado que se valido
     */
    public void confirmarValidacion() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("confirmarValidacion()"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        switch (actualizarDatosUbicabilidadFL.getTipoDatoValidado()) {
        case TIPO_DATO_DIRECCION:
            actualizarDireccion();
            break;
        case TIPO_DATO_TELEFONO:
            adicionarTelefono();
            break;
        case TIPO_DATO_CORREO:
            adicionarCorreo();
            break;
        default:
            break;
        }
    }

    /**
     * Omite las validaciones que se iban a realizar sobre el registro
     */
    public void cancelarValidacion() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("concatenarFecha(String, Date)"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        actualizarDatosUbicabilidadFL.setActualizarCorreo(false);
        actualizarDatosUbicabilidadFL.setActualizarDireccion(false);
        actualizarDatosUbicabilidadFL.setActualizarTelefono(false);
    }

    /**
     * Genera una cadena de caracteres concatendando los dos parametros ingresados
     * 
     * @param label
     * @param fecha
     * @return
     */
    public String concatenarFecha(String label, Date fecha) {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("concatenarFecha(String, Date)"));
        SimpleDateFormat format = new SimpleDateFormat(utilidadMB.getFormatoFechaCompleta());
        if (fecha != null) {
            return label + " - " + format.format(fecha).toString();
        } else {
            return label + " - ";
        }
    }

    /**
     * Modifica los valores dependiendo si el ciudadano esta presente o no
     * 
     * @param presente
     */
    public void ciudadanoPresente(boolean presente) {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("ciudadanoPresente(boolean)"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        actualizarDatosUbicabilidadFL.setCiudadanoPresente(presente);
        actualizarDatosUbicabilidadFL.setSoloGuardar(!presente);
    }

    /**
     * Consulta si existe un representante legal como persona en el sistema
     */
    public void consultarRepresentante() {
        LOGGER.debug(UbicabilidadMB.class.getName().concat("consultarRepresentante)"));
        ActualizarDatosUbicabilidadFL actualizarDatosUbicabilidadFL = findFlowObject(
                ActualizarDatosUbicabilidadFL.class, ActualizarDatosUbicabilidadFL.NOMBRE_BEAN);
        PersonaDTO personaDTO = iRPersona.consultarPersona(getCodigoOrganismoTransito(),
                actualizarDatosUbicabilidadFL.getRepresentante().getTipoIdentificacion().getId(),
                actualizarDatosUbicabilidadFL.getRepresentante().getNumeroIdentificacion());
        if (personaDTO != null) {
            actualizarDatosUbicabilidadFL.setExisteRepresentante(true);
            actualizarDatosUbicabilidadFL.getRepresentante().setId(personaDTO.getId());
            actualizarDatosUbicabilidadFL.getRepresentante().setApellido1(personaDTO.getApellido1());
            actualizarDatosUbicabilidadFL.getRepresentante().setNombre1(personaDTO.getNombre1());
            actualizarDatosUbicabilidadFL.getRepresentante().setApellido2(personaDTO.getApellido2());
            actualizarDatosUbicabilidadFL.getRepresentante().setNombre2(personaDTO.getNombre2());
        } else {
            actualizarDatosUbicabilidadFL.setExisteRepresentante(false);
            addErrorMessage(NOMBRE_BUNDLE_UBICABILIDAD, "msg_error_no_existe_representante");
        }
    }

    /**
     * Retorna un TipoFuenteInformacionDTO dependiendo de si el ciudadano esta presente
     * 
     * @param presente
     *            Indica si el ciudadano esta presente
     * @return TipoFuenteInformacionDTO
     */
    private TipoFuenteInformacionDTO obtenerFuenteInformacion(Boolean presente) {
        if (presente) {
            return new TipoFuenteInformacionDTO(EnumTipoFuenteInformacion.CIUDADANO.getValue());
        } else {
            return new TipoFuenteInformacionDTO(EnumTipoFuenteInformacion.PROCESO_MANUAL.getValue());
        }
    }

    /**
     * Retorna el valor que tenga en el estado, si es null retorna vacio
     * 
     * @param dto
     *            Contiene la informacion a validar
     * @return
     */
    public String validacion(Object dto) {
        Boolean validacion = null;
        String valor = null;
        if (dto instanceof DireccionPersonaDTO) {
            validacion = ((DireccionPersonaDTO) dto).getEstado();
        } else if (dto instanceof TelefonoPersonaDTO) {
            validacion = ((TelefonoPersonaDTO) dto).getEstado();
        } else if (dto instanceof CorreoPersonaDTO) {
            validacion = ((CorreoPersonaDTO) dto).getEstado();
        }
        if (validacion == null) {
            valor = "";
        } else if (validacion.equals(false)) {
            valor = "No";
        } else {
            valor = "Si";
        }
        return valor;
    }

    public StreamedContent getDocumentoUbicabilidad() {
        LOGGER.debug("UbicabilidadMB::getDocumentoUbicabilidad");
        return documentoUbicabilidad;
    }

    public void setDocumentoUbicabilidad(StreamedContent documentoUbicabilidad) {
        LOGGER.debug("UbicabilidadMB::setDocumentoUbicabilidad(StreamedContent)");
        this.documentoUbicabilidad = documentoUbicabilidad;
    }

    public String getPatternNombre() {
        this.patternNombre = ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_DOS_UBICABILIDAD;
        return patternNombre;
    }

    public String getPatternNombreComercial() {
        this.patternNombreComercial = ExpresionesRegulares.REGEX_NOMBRE_COMERCIAL;
        return patternNombreComercial;
    }

    public UtilidadMB getUtilidadMB() {
        return utilidadMB;
    }

    public void setUtilidadMB(UtilidadMB utilidadMB) {
        this.utilidadMB = utilidadMB;
    }

    public FachadaCatalogosMB getFachadaCatalogosMB() {
        return fachadaCatalogosMB;
    }

    public void setFachadaCatalogosMB(FachadaCatalogosMB fachadaCatalogosMB) {
        this.fachadaCatalogosMB = fachadaCatalogosMB;
    }

}
