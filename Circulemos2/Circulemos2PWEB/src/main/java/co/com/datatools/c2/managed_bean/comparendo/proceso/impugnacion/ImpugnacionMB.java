package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.AccionImpugnacionBackDTO;
import co.com.datatools.c2.dto.AdjuntosMotivacionImpugnacionDTO;
import co.com.datatools.c2.dto.DocumentoProcesoDTO;
import co.com.datatools.c2.dto.EvaluarImpugnacionBackDTO;
import co.com.datatools.c2.dto.ImpulsoPruebaDTO;
import co.com.datatools.c2.dto.JustificacionImpugnacionBackDTO;
import co.com.datatools.c2.dto.MotivacionImpugnacionDTO;
import co.com.datatools.c2.dto.OrigenImpugnacionDTO;
import co.com.datatools.c2.dto.ParticipanteProcesoDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.RespuestaImpugnacionDTO;
import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.dto.TipoParticipanteDTO;
import co.com.datatools.c2.dto.TipoPeticionDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.CorreoPersonaDTO;
import co.com.datatools.c2.dto.ubicabilidad.DireccionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoFuenteInformacion;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumEstadoComparendo;
import co.com.datatools.c2.enumeraciones.EnumEstadoProceso;
import co.com.datatools.c2.enumeraciones.EnumRolImpugnacion;
import co.com.datatools.c2.enumeraciones.EnumTipoParticipante;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRImpugnacion;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRProceso;
import co.com.datatools.c2.negocio.interfaces.IRPruebas;
import co.com.datatools.c2.negocio.interfaces.IRUbicabilidad;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.seguridad.dto.autorizacion.RolDto;
import co.com.datatools.util.date.UtilFecha;

/**
 * Manejo proceso de impugnacion
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class ImpugnacionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(ImpugnacionMB.class.getName());

    private static final String NOMBRE_BUNDLE_IMPUGNACION = "labelProcesoImpugnacion";

    private static final String CONTENT_TYPE = "application/pdf";
    private final String NOMBRE_ARCHIVO = "Documento radicado.pdf";
    private final String NOMBRE_ARCHIVO_DETALLE = "Acto Administrativo.pdf";
    private final String NOMBRE_ARCHIVO_SOLICITUD = "Solicitud Prueba.pdf";
    private final String NOMBRE_ARCHIVO_ESCRITO = "Escrito motivacion.pdf";

    /**
     * Atributo que permite identificar que tipos de archivos son permitidos
     */
    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(pdf)$/";
    private int tamanioMaximoArch;

    // Campos
    private boolean estadoProceso;
    private boolean fechaProceso;
    private boolean seleccion;
    private boolean seleccionMultiple;

    // Requeridos
    private boolean infoInfractor;
    private boolean numProceso;
    private boolean numComparendo;
    private boolean fechas;
    private boolean estProceso;
    private boolean estComparendo;

    // Botones de acciones sobre el proceso
    // Se encarga de controlar si se puede radicar o no el el porceso de impugnacion sobre el comparendo
    private boolean radicarExpediente;
    private boolean evaluarExpediente;
    private boolean realizarFallo;
    private boolean aprobarFallo;
    private boolean rechazarFallo;
    private boolean gestionPruebas;
    private boolean evaluarExpedienteContinuacion;
    private boolean registrarAcciones;
    private boolean realizarFalloContinuacion;
    private boolean verDetalle;
    private boolean hojaVida;

    // Documento radicado
    private StreamedContent documentoRadicado;

    // Campo rol
    private boolean exonerador;
    private boolean auxiliar;
    private boolean abogadoUno;
    private boolean abogadoDos;
    private boolean abogadoEspecialista;

    private List<SelectItem> estadosComparendo;
    private List<SelectItem> tipoParticipantes;

    @EJB
    private IRImpugnacion iRImpugnacion;
    @EJB
    private IRPersona iRPersona;
    @EJB
    private IRComparendo iRComparendo;
    @EJB
    private IRParametro iRParametro;
    @EJB
    private IRProceso iRProceso;
    @EJB
    private IRDocumentosCirculemos iRDocumentosCirculemos;
    @EJB
    private IRPruebas iRPruebas;
    @EJB
    private IRProceso procesoEJB;
    @EJB
    private IRRepositorioArchivo iReposArchivoEjb;
    @EJB
    private IRUbicabilidad iRUbicabilidad;

    // Documento a visulaizar del detalle de seguimiento
    private StreamedContent documentoSeguimiento;

    @ManagedProperty(value = "#{encabezadoImpugnacionMB}")
    EncabezadoImpugnacionMB encabezadoImpugnacion;

    @ManagedProperty(value = "#{visualizarDocumentoMB}")
    private VisualizarDocumentoMB visualizarDocumentoMB;

    private final String NOMBRE_BUNDLE = NOMBRE_BUNDLE_IMPUGNACION;

    @PostConstruct
    public void init() {
        LOGGER.debug("ImpugnacionMB::init()");
        estadosComparendo = new ArrayList<SelectItem>();
        tipoParticipantes = new ArrayList<SelectItem>();

        for (RolDto rolDto : getDetalleUsuarioSesion().getRoles()) {
            // Admin
            if (!rolDto.getIdRol().equals(EnumRolImpugnacion.AUXILIAR.getValue())
                    && !rolDto.getIdRol().equals(EnumRolImpugnacion.ABOGADO_PRIMERA_INSTANCIA.getValue())
                    && !rolDto.getIdRol().equals(EnumRolImpugnacion.AUTORIZADOR_EXONERACION.getValue())
                    && !rolDto.getIdRol().equals(EnumRolImpugnacion.ABOGADO_SEGUNDA_INSTANCIA.getValue())
                    && !rolDto.getIdRol().equals(EnumRolImpugnacion.ABOGADO_ESPECIALISTA.getValue())) {

                // Auxiliar
                auxiliar = true;
                abogadoUno = true;
                abogadoDos = true;
                estadoProceso = true;
                fechaProceso = false;
                seleccion = true;
                infoInfractor = true;
                numProceso = true;
                numComparendo = true;
                fechas = true;
                estProceso = true;
                estComparendo = false;
                estadosComparendo = getCatalogosApp().catEstadoComparendo();
            }

            // Visualizacion xhtml para el auxiliar
            if (rolDto.getIdRol().equals(EnumRolImpugnacion.AUXILIAR.getValue())) {
                auxiliar = true;
                estadoProceso = false;
                fechaProceso = false;
                seleccion = true;
                infoInfractor = true;
                numProceso = false;
                numComparendo = false;
                fechas = false;
                estProceso = false;
                estComparendo = false;

                estadosComparendo = getCatalogosApp().catEstadoComparendo();
            }
            // Visualizacion xhtml para el abogado primera instancia
            if (rolDto.getIdRol().equals(EnumRolImpugnacion.ABOGADO_PRIMERA_INSTANCIA.getValue())) {
                abogadoUno = true;
                estadoProceso = true;
                fechaProceso = false;
                seleccion = true;
                infoInfractor = false;
                numProceso = true;
                numComparendo = true;
                fechas = true;
                estProceso = true;
                estComparendo = false;

                for (SelectItem selectItem : getCatalogosApp().catEstadoComparendo()) {
                    if (selectItem.getValue().equals(EnumEstadoComparendo.VIGENTE.getValue())
                            || selectItem.getValue().equals(EnumEstadoComparendo.IMPUGNADO.getValue())) {
                        estadosComparendo.add(selectItem);
                    }
                }
            }
            // Visualizacion xhtml para la exoneracion
            if (rolDto.getIdRol().equals(EnumRolImpugnacion.AUTORIZADOR_EXONERACION.getValue())) {
                exonerador = true;
                estadoProceso = true;
                fechaProceso = false;
                seleccion = false;
                seleccionMultiple = true;
                infoInfractor = false;
                numProceso = true;
                numComparendo = true;
                fechas = true;
                estProceso = true;
                estComparendo = false;

                for (SelectItem selectItem : getCatalogosApp().catEstadoComparendo()) {
                    if (selectItem.getValue().equals(EnumEstadoComparendo.IMPUGNADO.getValue())) {
                        estadosComparendo.add(selectItem);
                    }
                }
            }
            // Visualizacion xhtml para el abogado continuaion
            if (rolDto.getIdRol().equals(EnumRolImpugnacion.ABOGADO_SEGUNDA_INSTANCIA.getValue())) {
                abogadoDos = true;
                estadoProceso = true;
                fechaProceso = false;
                seleccion = true;
                infoInfractor = false;
                numProceso = true;
                numComparendo = true;
                fechas = true;
                estProceso = true;
                estComparendo = false;

                for (SelectItem selectItem : getCatalogosApp().catEstadoComparendo()) {
                    if (selectItem.getValue().equals(EnumEstadoComparendo.IMPUGNADO.getValue())) {
                        estadosComparendo.add(selectItem);
                    }
                }
            }
            // Visualizacion xhtml para el abogado especialista
            if (rolDto.getIdRol().equals(EnumRolImpugnacion.ABOGADO_ESPECIALISTA.getValue())) {
                abogadoEspecialista = true;
                estadoProceso = true;
                fechaProceso = false;
                seleccion = true;
                infoInfractor = false;
                numProceso = true;
                numComparendo = true;
                fechas = true;
                estProceso = true;
                estComparendo = false;

                for (SelectItem selectItem : getCatalogosApp().catEstadoComparendo()) {
                    if (selectItem.getValue().equals(EnumEstadoComparendo.IMPUGNADO.getValue())) {
                        estadosComparendo.add(selectItem);
                    }
                }
            }
        }

        documentoRadicado = null;
        documentoSeguimiento = null;
        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = iRParametro.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
    }

    /**
     * Metodo que se encarga de inicializar los datos del encabezado
     */
    public boolean inicializarDatos() {
        LOGGER.debug("ImpugnacionMB::inicializarDatos()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        // obtiene el proceso seleccionado
        if (impugnacionHolderFL.getComparendoSeleccionado() == null
                || impugnacionHolderFL.getComparendoSeleccionado().getIdProceso() == null) {
            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_error_proceso_no_seleccionado");
            return false;
        }
        encabezadoImpugnacion.consultarEncabezado(impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());

        impugnacionFL.setEvaluarExpedienteEspecialista(true);
        return true;
    }

    public void inicializarDatosEvaluarExpediente() {
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        impugnacionFL.setEvaluarExpedienteEspecialista(true);
    }

    /**
     * Se encarga de cargar el filtro de organismo de transito
     * 
     * @author giovanni.velandia
     */
    public void cargarOrganismoTransito() {
        LOGGER.debug("ImpugnacionMB::cargarOrganismoTransito()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        impugnacionHolderFL.getConsultaImpugnacionDTO().setCodigoOrganismo(getCodigoOrganismoTransito());
    }

    /**
     * Valida los campos obligatorios
     * 
     * @author giovanni.velandia
     */
    public boolean validarFiltros() {
        LOGGER.debug("ImpugnacionMB::validarFiltros()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        boolean requerido = false;

        // Infractor
        if (impugnacionHolderFL.getConsultaImpugnacionDTO().getTipoDocumentoInfractor() != null
                && impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroDocumentoInfractor() != null) {
            requerido = true;
            // Numero de proceso
        } else if (impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroProceso() != null) {
            requerido = true;
            // Numero de comparendo
        } else if (impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroComparendo() != null) {
            requerido = true;
            // Fechas
        } else if (impugnacionHolderFL.getConsultaImpugnacionDTO().getFechaInicioAperturaProceso() != null
                && impugnacionHolderFL.getConsultaImpugnacionDTO().getFechaFinAperturaProceso() != null) {
            requerido = true;
            // Estado Procesos
        } else if (impugnacionHolderFL.getConsultaImpugnacionDTO().getIdEstadoProceso() != null) {
            requerido = true;
        }

        return requerido;
    }

    /**
     * Meotod que se encarga de hacer la consulta principal de los procesos de impugnacion
     * 
     * @author giovanni.velandia
     */
    public void consultar() {
        LOGGER.debug("ImpugnacionMB::consultar()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        // limpiar persona
        impugnacionHolderFL.setPersonaFiltroDTO(new PersonaDTO());

        // Validacion fecha inicio
        if (impugnacionHolderFL.getConsultaImpugnacionDTO().getFechaFinAperturaProceso() != null) {
            if (impugnacionHolderFL.getConsultaImpugnacionDTO().getFechaInicioAperturaProceso() == null) {
                getFacesContext().addMessage("form-contenido:fechaInicial",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        // Validacion fecha fin
        if (impugnacionHolderFL.getConsultaImpugnacionDTO().getFechaInicioAperturaProceso() != null) {
            if (impugnacionHolderFL.getConsultaImpugnacionDTO().getFechaFinAperturaProceso() == null) {
                getFacesContext().addMessage("form-contenido:fechaFinal",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        // Validacion numero de documento del infractor
        if (impugnacionHolderFL.getConsultaImpugnacionDTO().getTipoDocumentoInfractor() != null) {
            if (impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroDocumentoInfractor() == null) {
                getFacesContext().addMessage("form-contenido:numDocInfrac",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
        }

        // Validacion tipo de documento del infractor
        if (impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroDocumentoInfractor() != null) {
            if (impugnacionHolderFL.getConsultaImpugnacionDTO().getTipoDocumentoInfractor() == null) {
                getFacesContext().addMessage("form-contenido:tipDocInfrac",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }
            consultarPersona();
        }

        boolean respuesta = validarFiltros();
        if (!respuesta) {
            addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_camp_opcional");
            return;
        }

        // Seleccio a nulos
        impugnacionHolderFL.setComparendoSeleccionado(null);
        impugnacionHolderFL.setComparendosSeleccionados(null);

        radicarExpediente = false;
        evaluarExpediente = false;
        realizarFallo = false;
        aprobarFallo = false;
        rechazarFallo = false;
        gestionPruebas = false;
        registrarAcciones = false;
        verDetalle = false;
        evaluarExpedienteContinuacion = false;
        realizarFalloContinuacion = false;
        hojaVida = false;

        List<RespuestaImpugnacionDTO> lstRespuestaImpugnacionDTO = null;

        try {
            lstRespuestaImpugnacionDTO = iRImpugnacion
                    .consultarImpugnacion(impugnacionHolderFL.getConsultaImpugnacionDTO());

            if (lstRespuestaImpugnacionDTO == null || lstRespuestaImpugnacionDTO.isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
                impugnacionHolderFL.setLstComparendos(new ArrayList<RespuestaImpugnacionDTO>(0));
                return;
            } else {
                impugnacionHolderFL.setLstComparendos(lstRespuestaImpugnacionDTO);
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lstRespuestaImpugnacionDTO.size());
            }

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }

    }

    /**
     * Metodo que se encarga de carga los datos para radicar
     * 
     * @author giovanni.velandia
     */
    public void opcionesProceso() {
        LOGGER.debug("ImpugnacionMB::opcionesProceso()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        hojaVida = true;
        if (impugnacionHolderFL.getComparendoSeleccionado().isImpugnable()) {
            radicarExpediente = true;
        } else {
            radicarExpediente = false;
        }

        // Verificamos el proceso para algunas acciones de impugnacion
        if (impugnacionHolderFL.getComparendoSeleccionado().getIdProceso() == null) {
            evaluarExpediente = false;
            realizarFallo = false;
            evaluarExpedienteContinuacion = false;
            gestionPruebas = false;
            realizarFalloContinuacion = false;
            registrarAcciones = false;
            verDetalle = false;
        } else {
            verDetalle = true;

            // Accion evaluar expediente
            if (impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                    .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_IMPUGNADO.getValue())) {
                evaluarExpediente = true;
            } else {
                evaluarExpediente = false;
            }

            // Estas acciones solo aplica para el abogado primera estancia
            if (abogadoUno) {
                // Accion realizar fallo
                if (impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                        .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO.getValue())
                        || impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                                .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO.getValue())) {
                    realizarFallo = true;
                } else {
                    realizarFallo = false;
                }
            }

            // Abogado continuacion
            if (impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                    .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION.getValue())) {
                evaluarExpedienteContinuacion = true;
            } else {
                evaluarExpedienteContinuacion = false;
            }

            if (impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                    .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_PENDIENTE_PRUEBAS.getValue())
                    || impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                            .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_SOLICITUD_PRUEBAS.getValue())) {
                gestionPruebas = true;
            } else {
                gestionPruebas = false;
            }

            // Estas acciones solo aplican para abogado de continuacion
            if (abogadoDos) {
                if (impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                        .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION.getValue())
                        || impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                                .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_RECHAZADO_ACONTINUACION.getValue())
                        || impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                                .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_CIERRE_PRUEBAS.getValue())) {
                    realizarFalloContinuacion = true;
                } else {
                    realizarFalloContinuacion = false;
                }
            }
            // Especialista
            if (impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso()
                    .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_PENDIENTE_GESTION_ESPECIALISTA.getValue())) {
                registrarAcciones = true;
            } else {
                registrarAcciones = false;
            }
        }
    }

    /**
     * Metodo que se encarga de carga los datos para radicar en modo multiple
     * 
     * @author giovanni.velandia
     */
    public void opcionesProcesoMultiple() {
        LOGGER.debug("ImpugnacionMB::opcionesProcesoMultiple()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        if (impugnacionHolderFL.getComparendosSeleccionados() != null
                && !impugnacionHolderFL.getComparendosSeleccionados().isEmpty()) {
            for (RespuestaImpugnacionDTO respuestaImpugnacionDTO : impugnacionHolderFL.getComparendosSeleccionados()) {
                if (respuestaImpugnacionDTO.getIdProceso() == null) {
                    aprobarFallo = false;
                    rechazarFallo = false;
                    verDetalle = false;
                    if (impugnacionHolderFL.getComparendosSeleccionados().size() == 1) {
                        hojaVida = true;
                    } else {
                        hojaVida = false;
                    }
                    break;
                } else {
                    if (respuestaImpugnacionDTO.getIdEstadoProceso()
                            .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_FALLADO.getValue())
                            || respuestaImpugnacionDTO.getIdEstadoProceso()
                                    .equals(EnumEstadoProceso.ECUADOR_IMPUGNACION_FALLADO_ACONTINUACION.getValue())) {
                        aprobarFallo = true;
                        rechazarFallo = true;
                    } else {
                        aprobarFallo = false;
                        rechazarFallo = false;
                    }

                    if (respuestaImpugnacionDTO.getIdProceso() == null) {
                        verDetalle = false;
                        hojaVida = true;
                    }
                    // Verificamos el ver detalle
                    if (impugnacionHolderFL.getComparendosSeleccionados().size() == 1) {
                        verDetalle = true;
                        hojaVida = true;
                    } else {
                        verDetalle = false;
                        hojaVida = false;
                    }
                    break;
                }
            }
        } else {
            aprobarFallo = false;
            rechazarFallo = false;
            verDetalle = false;
            hojaVida = false;
        }
    }

    /**
     * Meotodo que se encarga de radicar expediente
     * 
     * @author giovanni.velandia
     * 
     */
    public void cargarRadicacion() {
        LOGGER.debug("ImpugnacionMB::cargarRadicacion()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);

        tipoParticipantes = new ArrayList<SelectItem>();

        // Ingresar los tipos de participantes menos el infractor
        for (SelectItem selectItem : getCatalogosApp().catTipoParticipante()) {
            if (!selectItem.getValue().equals(EnumTipoParticipante.INFRACTOR.getValue())) {
                tipoParticipantes.add(selectItem);
            }
        }

        // Datos del participante
        // Infractor
        impugnacionFL.getRadicarExpedienteDTO().setParticipanteProcesoDTOs(new ArrayList<ParticipanteProcesoDTO>());
        ParticipanteProcesoDTO participanteProcesoDTO = new ParticipanteProcesoDTO();
        // Tipo participante
        TipoParticipanteDTO tipoParticipanteDTO = new TipoParticipanteDTO();
        tipoParticipanteDTO.setNombre(EnumTipoParticipante.INFRACTOR.toString());
        tipoParticipanteDTO.setId(EnumTipoParticipante.INFRACTOR.getValue());
        participanteProcesoDTO.setTipoParticipante(tipoParticipanteDTO);

        // Persona
        participanteProcesoDTO.setPersona(iRComparendo
                .consultarInfractorComparendo(impugnacionHolderFL.getComparendoSeleccionado().getIdComparendo()));

        // Consulta direcciones de la persona
        DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
        direccionPersonaDTO.setPersona(participanteProcesoDTO.getPersona());
        participanteProcesoDTO.getPersona()
                .setDireccionPersonaList(iRUbicabilidad.consultarDireccionPersona(direccionPersonaDTO));

        // Consulta correo de la persona
        CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
        correoPersonaDTO.setPersona(participanteProcesoDTO.getPersona());
        participanteProcesoDTO.getPersona().setCorreoPersonaList(iRUbicabilidad.consultarCorreos(correoPersonaDTO));

        if (participanteProcesoDTO.getPersona() != null
                && participanteProcesoDTO.getPersona().getCorreoPersonaList() != null
                && !participanteProcesoDTO.getPersona().getCorreoPersonaList().isEmpty()) {
            participanteProcesoDTO.getPersona().setCorreoElectronico(
                    participanteProcesoDTO.getPersona().getCorreoPersonaList().get(0).getCorreoElectronico());
        }

        // cicomparendo
        impugnacionFL.getRadicarExpedienteDTO()
                .setCicomparendo(impugnacionHolderFL.getComparendoSeleccionado().getIdComparendo());

        impugnacionFL.getRadicarExpedienteDTO().getParticipanteProcesoDTOs().add(participanteProcesoDTO);
    }

    /**
     * Permite formatear el mensaje de salida en la confirmacion del registro de la radicacion de la impugnacion
     * 
     * @return String
     */
    public String getMensajeConfirmRadicacion() {
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE_IMPUGNACION).getString("msg_confir_radicacion"),
                impugnacionFL.getRadicarExpedienteDTO().getParticipanteProcesoDTOs().get(0).getProceso()
                        .getNumeroProceso(),
                Utilidades.dateToStringFormatApp(impugnacionFL.getRadicarExpedienteDTO().getParticipanteProcesoDTOs()
                        .get(0).getProceso().getFechaInicio(), true));
    }

    /**
     * Metodo que se encarga de adicioanr un participante a la lista
     * 
     * @author divier.casas
     * 
     */
    public void cargarParticipante(PersonaDTO personaDTO) {
        LOGGER.debug("ImpugnacionMB::cargarParticipante()");
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);

        List<ParticipanteProcesoDTO> lsParticipantes = impugnacionFL.getRadicarExpedienteDTO()
                .getParticipanteProcesoDTOs();

        if (personaDTO != null) {
            boolean existeParticipante = false;
            if (lsParticipantes != null) {
                for (ParticipanteProcesoDTO participante : lsParticipantes) {
                    if (participante.getPersona().getId().equals(personaDTO.getId())) {
                        existeParticipante = true;
                        break;
                    }
                }
            }

            if (!existeParticipante) {
                ParticipanteProcesoDTO participanteProcesoDTO = new ParticipanteProcesoDTO();
                participanteProcesoDTO.setPersona(personaDTO);
                ItemCatalogoDTO itemCatalogoDTO = getCatalogosApp().getItemCatalogoId(EnumCatalogo.TipoParticipante,
                        impugnacionFL.getParticipanteProcesoDTO().getTipoParticipante().getId());

                TipoParticipanteDTO tipoParticipanteDTO = new TipoParticipanteDTO();
                tipoParticipanteDTO.setId(impugnacionFL.getParticipanteProcesoDTO().getTipoParticipante().getId());
                tipoParticipanteDTO.setNombre(itemCatalogoDTO.getNombre());
                participanteProcesoDTO.setTipoParticipante(tipoParticipanteDTO);
                impugnacionFL.getRadicarExpedienteDTO().getParticipanteProcesoDTOs().add(participanteProcesoDTO);
                addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_info_ad_participantes_ok");
            } else {
                addWarningMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_info_ad_participantes");
            }
        } else {
            addWarningMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_info_ad_val_participante");
        }

        impugnacionFL.setParticipanteProcesoDTO(new ParticipanteProcesoDTO());
        impugnacionFL.getParticipanteProcesoDTO().setTipoParticipante(new TipoParticipanteDTO());
    }

    /**
     * Metodo que se encarga de retirar un participante de la lista
     * 
     * @author divier.casas
     * 
     */
    public void retirarParticipante() {
        LOGGER.debug("ImpugnacionMB::retirarParticipante()");
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        List<ParticipanteProcesoDTO> lsPartSeleccionados = impugnacionFL.getRadicarExpedienteDTO()
                .getParticipantesSeleccionados();
        List<ParticipanteProcesoDTO> lsPartSeleccionadosAux = new ArrayList<>();
        lsPartSeleccionadosAux.addAll(lsPartSeleccionados);

        List<ParticipanteProcesoDTO> lsParticipanteProcesoDTO = impugnacionFL.getRadicarExpedienteDTO()
                .getParticipanteProcesoDTOs();

        int indice = 0;
        ParticipanteProcesoDTO participanteDefecto = null;
        if (lsParticipanteProcesoDTO != null) {
            for (ParticipanteProcesoDTO participante : lsParticipanteProcesoDTO) {
                if (participante.getTipoParticipante().getId().equals(EnumTipoParticipante.INFRACTOR.getValue())
                        && indice == 0) {
                    participanteDefecto = participante;
                    break;
                }
                ++indice;
            }
        }
        boolean esEliminado = false;
        for (ParticipanteProcesoDTO partSeleccionado : lsPartSeleccionados) {
            if (!partSeleccionado.getPersona().getId().equals(participanteDefecto.getPersona().getId())) {
                lsParticipanteProcesoDTO.remove(partSeleccionado);
                lsPartSeleccionadosAux.remove(partSeleccionado);
                esEliminado = true;
            }
        }
        impugnacionFL.getRadicarExpedienteDTO().setParticipantesSeleccionados(lsPartSeleccionadosAux);
        impugnacionFL.getRadicarExpedienteDTO().setParticipanteProcesoDTOs(lsParticipanteProcesoDTO);
        if (esEliminado) {
            addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_info_eli_participantes_ok");
        }
    }

    /**
     * Se encarga de validar los datos de la radicacion TRUE datos incorrectos FALSE datos correctos
     * 
     * @author giovanni.velandia
     */
    public boolean validarDatosRadicacion() {
        LOGGER.debug("ImpugnacionMB::validarDatosRadicacion()");
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);

        if (impugnacionFL.getRadicarExpedienteDTO().getMotivacionImpugnacionDTO().getDescripcionHechos() == null) {
            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_falta_motiv_hechos");
            getFacesContext().addMessage("form-radicar:textAreaHechos",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return true;
        }

        return false;
    }

    /**
     * Metodo para radicar el expediente de un comparendo
     * 
     * @author giovanni.velandia
     */
    public boolean registraRadicarExpediente() {
        LOGGER.debug("ImpugnacionMB::registraRadicarExpediente()");

        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);

        if (validarDatosRadicacion()) {
            return false;
        }

        try {

            if (impugnacionFL.getRadicarExpedienteDTO().getMotivacionImpugnacionDTO().getTipoPeticion()
                    .getId() == null) {
                impugnacionFL.getRadicarExpedienteDTO().getMotivacionImpugnacionDTO().setTipoPeticion(null);
            }

            if (impugnacionFL.getRadicarExpedienteDTO().getMotivacionImpugnacionDTO().getOrigenImpugnacion()
                    .getId() == null) {
                impugnacionFL.getRadicarExpedienteDTO().getMotivacionImpugnacionDTO().setOrigenImpugnacion(null);
            }

            impugnacionFL
                    .setRadicarExpedienteDTO(iRImpugnacion.radicarExpediente(impugnacionFL.getRadicarExpedienteDTO()));

            if (impugnacionFL.getRadicarExpedienteDTO().getDocumento() != null) {
                documentoRadicado = new DefaultStreamedContent(
                        new ByteArrayInputStream(impugnacionFL.getRadicarExpedienteDTO().getDocumento()), CONTENT_TYPE,
                        NOMBRE_ARCHIVO);
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgPdfviewer').show();");
            }

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        } finally {
            impugnacionFL.getRadicarExpedienteDTO().setMotivacionImpugnacionDTO(new MotivacionImpugnacionDTO());
            impugnacionFL.getRadicarExpedienteDTO().getMotivacionImpugnacionDTO()
                    .setTipoPeticion(new TipoPeticionDTO());
            impugnacionFL.getRadicarExpedienteDTO().getMotivacionImpugnacionDTO()
                    .setOrigenImpugnacion(new OrigenImpugnacionDTO());
        }

        impugnacionFL.setConfirmarRadicar(true);

        return true;
    }

    /**
     * Se encarga de validar la obligatoriedad de el tipo participante
     * 
     * @author giovanni.velandia
     * @return
     */
    public boolean validarTipoParticipate() {
        LOGGER.debug("ImpugnacionMB::validarTipoParticipate()");
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        if (impugnacionFL.getParticipanteProcesoDTO().getTipoParticipante().getId() == null) {
            getFacesContext().addMessage("form-radicar:tabs_par_mot:selTipoPar",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return false;
        }

        return true;
    }

    /**
     * Metodo que se encarga de llenar los datos del proceso para el comparendo a evaluar despues de radicado
     * 
     * @author giovanni.velandia
     */
    public void cargarProcesoEvaluar() {
        LOGGER.debug("ImpugnacionMB::cargarProcesoEvaluar()");
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        for (RespuestaImpugnacionDTO respuestaImpugnacionDTO : impugnacionHolderFL.getLstComparendos()) {
            if (respuestaImpugnacionDTO.getIdComparendo()
                    .equals(impugnacionFL.getRadicarExpedienteDTO().getCicomparendo())) {
                impugnacionHolderFL.setComparendoSeleccionado(respuestaImpugnacionDTO);
                break;
            }
        }
    }

    /**
     * Metodo que se encarga de realizar la consulta de la persona en los filtros
     * 
     * @author giovanni.velandia
     */
    public void consultarPersona() {
        LOGGER.debug("ImpugnacionMB::consultarPersona()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        // Validacion numero de documento del infractor
        if (impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroDocumentoInfractor() == null) {
            getFacesContext().addMessage("form-contenido:numDocInfrac",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }

        // Validacion tipo de documento del infractor
        if (impugnacionHolderFL.getConsultaImpugnacionDTO().getTipoDocumentoInfractor() == null) {
            getFacesContext().addMessage("form-contenido:tipDocInfrac",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }

        impugnacionHolderFL.setPersonaFiltroDTO(
                iRPersona.consultarPersona(impugnacionHolderFL.getConsultaImpugnacionDTO().getTipoDocumentoInfractor(),
                        impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroDocumentoInfractor()));
    }

    /**
     * Metodo que se encarga de validar si se actualiza la persona con el perfil del auxiliar
     * 
     * @return
     */
    public boolean esPersonaActualizada() {
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        if (impugnacionHolderFL.getConsultaImpugnacionDTO().getTipoDocumentoInfractor() != null
                && impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroDocumentoInfractor() != null) {

            PersonaDTO personaDTO = iRPersona.consultarPersona(getCodigoOrganismoTransito(),
                    impugnacionHolderFL.getConsultaImpugnacionDTO().getTipoDocumentoInfractor(),
                    impugnacionHolderFL.getConsultaImpugnacionDTO().getNumeroDocumentoInfractor());

            if (personaDTO != null) {

                if (!personaDTO.getFuenteInformacion().getId().equals(EnumTipoFuenteInformacion.AXIS.getValue())
                        && !personaDTO.getFuenteInformacion().getId()
                                .equals(EnumTipoFuenteInformacion.COMPARENDO.getValue())
                        && !personaDTO.getFuenteInformacion().getId()
                                .equals(EnumTipoFuenteInformacion.AXIS_EXTERNOS.getValue())) {

                    ValorParametroDTO valorParametroDTO = iRParametro.consultarParametro(
                            EnumParametro.CANT_DIAS_MIN_SOLICI_ACT_UBICABILIDAD, getCodigoOrganismoTransito(), true);
                    Date fechaFinal = Utilidades.sumarDias(personaDTO.getFechaUltimaActualizacion(),
                            valorParametroDTO.getValorParamInt());
                    Date fechaInicial = new Date();
                    if (fechaFinal.compareTo(fechaInicial) == 0 || fechaFinal.compareTo(fechaInicial) == -1) {
                        return true;
                    } else {
                        addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "titulo_persona_actualizada",
                                NOMBRE_BUNDLE_IMPUGNACION, "persona_con_actualizacion",
                                Utilidades.dateToStringFormatApp(personaDTO.getFechaUltimaActualizacion(), false));
                        return false;
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    /**
     * Consulta el detalle del proceso
     * 
     * @return Tru si termina correctamente
     * @author julio.pinzon 2016-06-27
     */
    public boolean consultarDetalleProceso() {
        LOGGER.debug("ImpugnacionMB::consultarDetalleProceso()");
        boolean respuesta = false;
        DetalleProcesoFL detalleProcesoFL = findFlowObject(DetalleProcesoFL.class, DetalleProcesoFL.NOMBRE_BEAN);
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        // Consulta de proceso
        detalleProcesoFL.setProceso(
                iRProceso.consultarProceso(impugnacionHolderFL.getComparendoSeleccionadoDetalle().getIdProceso()));
        if (detalleProcesoFL.getProceso() != null) {
            Long idProceso = impugnacionHolderFL.getComparendoSeleccionadoDetalle().getIdProceso();
            // Consulta de comparendo
            detalleProcesoFL.setComparendo(iRComparendo
                    .consultarComparendo(impugnacionHolderFL.getComparendoSeleccionadoDetalle().getIdComparendo()));
            detalleProcesoFL.setDescripcionInfraccion(detalleProcesoFL.getComparendo().getInfraccion()
                    .getConfiguracionInfraccionList().get(0).getDescripcion());
            // Consulta participantes
            ParticipanteProcesoDTO criterios = new ParticipanteProcesoDTO();
            criterios.setProceso(new ProcesoDTO(idProceso));
            detalleProcesoFL.getProceso()
                    .setParticipantesProcesoList(iRProceso.consultarParticipantesProceso(criterios));
            for (ParticipanteProcesoDTO participante : detalleProcesoFL.getProceso().getParticipantesProcesoList()) {
                // Consulta direcciones del participante
                DireccionPersonaDTO direccionPersonaDTO = new DireccionPersonaDTO();
                direccionPersonaDTO.setPersona(participante.getPersona());
                participante.getPersona()
                        .setDireccionPersonaList(iRUbicabilidad.consultarDireccionPersona(direccionPersonaDTO));
                // Consulta correo del participante
                CorreoPersonaDTO correoPersonaDTO = new CorreoPersonaDTO();
                correoPersonaDTO.setPersona(participante.getPersona());
                participante.getPersona().setCorreoPersonaList(iRUbicabilidad.consultarCorreos(correoPersonaDTO));
            }

            // Consulta seguimiento
            TrazabilidadProcesoDTO trazabilidad = new TrazabilidadProcesoDTO();
            trazabilidad.setProceso(new ProcesoDTO(idProceso));
            detalleProcesoFL.getProceso().setTrazabilidadProcesoList(iRProceso.consultarTrazabilidad(trazabilidad));

            detalleProcesoFL.setFallo(iRImpugnacion.consultarUltimoFalloImpugnacion(idProceso));
            // consulta el historico del fallo
            detalleProcesoFL.setHistoricoFallo(iRImpugnacion.consultarHistroricoFallos(idProceso));

            // Consulta acciones
            detalleProcesoFL.setListaAcciones(new ArrayList<AccionImpugnacionBackDTO>());
            detalleProcesoFL.setListaAcciones(iRImpugnacion
                    .consultarAccionesBack(impugnacionHolderFL.getComparendoSeleccionadoDetalle().getIdProceso()));
            detalleProcesoFL.setAccionSeleccionada(null);

            // Consulta motivacion impugnacion
            detalleProcesoFL.setMotivacionImpugnacionDTO(iRImpugnacion.consultarMotivacionIpugnacion(idProceso));

            List<AdjuntosMotivacionImpugnacionDTO> lsAdjuntoEscrito = new ArrayList<>();
            AdjuntosMotivacionImpugnacionDTO adjuntoEscrito = new AdjuntosMotivacionImpugnacionDTO();
            adjuntoEscrito
                    .setId(detalleProcesoFL.getMotivacionImpugnacionDTO() != null
                            ? (detalleProcesoFL.getMotivacionImpugnacionDTO().getNumeroArchivoEscrito() != null
                                    ? Long.valueOf(
                                            detalleProcesoFL.getMotivacionImpugnacionDTO().getNumeroArchivoEscrito())
                                    : null)
                            : null);
            ArchivoTransportableDTO archivoEscrito = null;
            if (adjuntoEscrito.getId() != null) {
                try {
                    archivoEscrito = iReposArchivoEjb.consultarDocumento(adjuntoEscrito.getId().toString());
                    adjuntoEscrito.setNombreArchivo(archivoEscrito.getNombre());
                    adjuntoEscrito
                            .setFechaCargue(detalleProcesoFL.getMotivacionImpugnacionDTO().getFechaCargueArchivo());
                    lsAdjuntoEscrito.add(adjuntoEscrito);
                    detalleProcesoFL.setLsAdjuntos(lsAdjuntoEscrito);
                } catch (CirculemosAlertaException e) {
                    CirculemosErrorHandler.handleException(e);
                }
            }

            // Consulta las solicitudes
            SolicitudPruebasBackDTO criteriosSolicitud = new SolicitudPruebasBackDTO();
            TrazabilidadProcesoDTO trazabilidadProcesoDTO = new TrazabilidadProcesoDTO();
            trazabilidadProcesoDTO
                    .setProceso(new ProcesoDTO(impugnacionHolderFL.getComparendoSeleccionadoDetalle().getIdProceso()));
            criteriosSolicitud.setTrazabilidadProceso(trazabilidadProcesoDTO);
            List<SolicitudPruebasBackDTO> solicitudes = iRPruebas.consultarSolicitudesProceso(criteriosSolicitud);

            detalleProcesoFL.setSolicitudes(new ArrayList<SolicitudPruebasBackVO>());
            for (SolicitudPruebasBackDTO solicitudPruebasBackDTOBD : solicitudes) {
                SolicitudPruebasBackVO solicitudPruebasBackVO = new SolicitudPruebasBackVO();
                solicitudPruebasBackVO.setSolicitudPruebasBackDTO(solicitudPruebasBackDTOBD);

                if (solicitudPruebasBackDTOBD.getImpulsoPruebas() != null) {
                    for (ImpulsoPruebaDTO impulso : solicitudPruebasBackDTOBD.getImpulsoPruebas()) {
                        if (impulso.getDefinitivo()) {
                            solicitudPruebasBackVO.setImpulsoDefinitivo(true);
                            break;
                        }
                    }
                }
                detalleProcesoFL.getSolicitudes().add(solicitudPruebasBackVO);

            }

            respuesta = true;
        }
        return respuesta;
    }

    /**
     * Consulta el documento del proceso
     * 
     * @author julio.pinzon 2016-06-27
     */
    public void consultarDocumentoDetalle() {
        LOGGER.debug("ImpugnacionMB::consultarDocumentoDetalle()");
        DetalleProcesoFL detalleProcesoFL = findFlowObject(DetalleProcesoFL.class, DetalleProcesoFL.NOMBRE_BEAN);
        try {
            List<Long> documentoList = new ArrayList<>();
            for (DocumentoProcesoDTO documento : detalleProcesoFL.getTrazaSeleccionada().getDocumentos()) {
                documentoList.add(documento.getNumeroDocumento());
            }
            byte[] documento = iRDocumentosCirculemos.consultarDocumentosPDF(documentoList);
            if (documento != null) {
                visualizarDocumentoMB.setVisualizar(true);
                visualizarDocumentoMB.visualizarDocumento(documento, NOMBRE_ARCHIVO_DETALLE, null, null);
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Consulta el documento de la solicitud
     * 
     * @author julio.pinzon 2016-07-08
     */
    public void consultarDocumentoSolicitud() {
        LOGGER.debug("ImpugnacionMB::consultarDocumentoSolicitud()");
        DetalleProcesoFL detalleProcesoFL = findFlowObject(DetalleProcesoFL.class, DetalleProcesoFL.NOMBRE_BEAN);
        try {
            TrazabilidadProcesoDTO traza = new TrazabilidadProcesoDTO(detalleProcesoFL.getSolicitudSeleccionada()
                    .getSolicitudPruebasBackDTO().getTrazabilidadProceso().getId());
            traza.setProceso(new ProcesoDTO(detalleProcesoFL.getProceso().getId()));
            List<TrazabilidadProcesoDTO> trazas = procesoEJB.consultarTrazabilidad(traza);
            if (!trazas.isEmpty() && trazas.get(0).getDocumentos() != null) {
                List<Long> documentoList = new ArrayList<>();
                for (DocumentoProcesoDTO doc : trazas.get(0).getDocumentos()) {
                    documentoList.add(doc.getNumeroDocumento());
                }
                byte[] documento = iRDocumentosCirculemos.consultarDocumentosPDF(documentoList);
                if (documento != null) {
                    visualizarDocumentoMB.setVisualizar(true);
                    visualizarDocumentoMB.visualizarDocumento(documento, NOMBRE_ARCHIVO_SOLICITUD, null, null);
                }
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Consulta la solicitud
     * 
     * @author julio.pinzon 2016-07-08
     */
    public void consultarSolicitud() {
        LOGGER.debug("ImpugnacionMB::consultarDocumentoSolicitud()");
        DetalleProcesoFL detalleProcesoFL = findFlowObject(DetalleProcesoFL.class, DetalleProcesoFL.NOMBRE_BEAN);

        RegistrarPruebaFL registrarPruebaFL = findFlowObject(RegistrarPruebaFL.class, RegistrarPruebaFL.NOMBRE_BEAN);
        registrarPruebaFL.setModoConsulta(true);
        registrarPruebaFL
                .setSolicitudSeleccionada(detalleProcesoFL.getSolicitudSeleccionada().getSolicitudPruebasBackDTO());
    }

    /**
     * evalua expediente back
     * 
     * @author diego.fonseca
     * 
     * @return
     */
    public String evaluarExpediente() {
        LOGGER.debug("ImpugnacionMB::evaluarExpediente()");

        final String ESTADO_NO_PERMITIDO = "ESTADO_NO_PERMITIDO";
        final String INICIALIZAR_REALIZAR_FALLO = "INICIALIZAR_REALIZAR_FALLO";
        final String INICIO = "INICIO";

        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        impugnacionFL.setEvaluarExpedienteEspecialista(false);
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        // Evalua el estado del proceso necesario para realiar la operación
        if (!EnumEstadoProceso.ECUADOR_IMPUGNACION_ENVIADO_ACONTINUACION.getValue()
                .equals(impugnacionHolderFL.getComparendoSeleccionado().getIdEstadoProceso())) {
            addErrorMessage(NOMBRE_BUNDLE, "error_estado_no_permitido");
            return ESTADO_NO_PERMITIDO;
        }

        EvaluarImpugnacionBackDTO evaluarImpugnacionBackDTO = new EvaluarImpugnacionBackDTO();
        evaluarImpugnacionBackDTO.setEnviarEspecialista(impugnacionFL.getEnviarAbogadoEspecialista());
        evaluarImpugnacionBackDTO.setTienePruebas(impugnacionFL.getTienePruebas());

        if (impugnacionFL.getJustificacionEnvio() != null) {
            JustificacionImpugnacionBackDTO justificacionImpugnacionBackDTO = new JustificacionImpugnacionBackDTO();
            justificacionImpugnacionBackDTO.setId(impugnacionFL.getJustificacionEnvio());
            evaluarImpugnacionBackDTO.setJustificacionImpugnacionBack(justificacionImpugnacionBackDTO);
        }

        TrazabilidadProcesoDTO trazabilidadProcesoDTO = new TrazabilidadProcesoDTO();
        ProcesoDTO procesoDTO = new ProcesoDTO();
        procesoDTO.setId(impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());
        trazabilidadProcesoDTO.setProceso(procesoDTO);
        evaluarImpugnacionBackDTO.setTrazabilidadProceso(trazabilidadProcesoDTO);

        try {

            evaluarImpugnacionBackDTO = iRImpugnacion.evaluarExpedienteBack(evaluarImpugnacionBackDTO,
                    impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());

            // Actuliza estado proceso en web
            if (impugnacionFL.getEnviarAbogadoEspecialista().equals(true)) {
                impugnacionHolderFL.getComparendoSeleccionado().setIdEstadoProceso(
                        EnumEstadoProceso.ECUADOR_IMPUGNACION_PENDIENTE_GESTION_ESPECIALISTA.getValue());
            } else if (impugnacionFL.getTienePruebas().equals(true)) {
                impugnacionHolderFL.getComparendoSeleccionado()
                        .setIdEstadoProceso(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION.getValue());
                impugnacionHolderFL.getComparendoSeleccionado()
                        .setIdEstadoProceso(EnumEstadoProceso.ECUADOR_IMPUGNACION_PENDIENTE_PRUEBAS.getValue());
            } else {
                impugnacionHolderFL.getComparendoSeleccionado()
                        .setIdEstadoProceso(EnumEstadoProceso.ECUADOR_IMPUGNACION_EVALUADO_ACONTINUACION.getValue());
                addInfoMessage(NOMBRE_BUNDLE, "msg_proceso_evaluado");
                return INICIALIZAR_REALIZAR_FALLO;
            }
            addInfoMessage(NOMBRE_BUNDLE, "msg_proceso_evaluado");

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
        return INICIO;

    }

    /**
     * Guarda el archivo de escrito
     * 
     * @author divier.casas 2016-11-28
     */
    public void cargarArchivoEscrito(FileUploadEvent event) {
        LOGGER.debug("ImpugnacionMB::cargarArchivoEscrito(FileUploadEvent)");
        final UploadedFile file = event.getFile();
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        final AdjuntosMotivacionImpugnacionDTO adjunto = new AdjuntosMotivacionImpugnacionDTO();
        adjunto.setArchivo(file.getContents());
        adjunto.setNombreArchivo(file.getFileName());
        adjunto.setFechaCargue(UtilFecha.buildCalendar().getTime());
        impugnacionFL.getRadicarExpedienteDTO().setAdjunto(adjunto);
    }

    /**
     * Consulta el documento escrito de la motivacion
     * 
     * @author divier.casas 2016-11-29
     */
    public void consultarDocumentoEscrito() {
        LOGGER.debug("ImpugnacionMB::consultarDocumentoEscrito()");
        DetalleProcesoFL detalleProcesoFL = findFlowObject(DetalleProcesoFL.class, DetalleProcesoFL.NOMBRE_BEAN);
        try {
            if (detalleProcesoFL.getLsAdjuntos() != null) {
                ArchivoTransportableDTO archivoEscrito = null;
                for (AdjuntosMotivacionImpugnacionDTO documentoEscrito : detalleProcesoFL.getLsAdjuntos()) {
                    if (documentoEscrito != null) {
                        archivoEscrito = iReposArchivoEjb.consultarDocumento(documentoEscrito.getId().toString());
                        if (archivoEscrito != null) {
                            visualizarDocumentoMB.setVisualizar(true);
                            visualizarDocumentoMB.visualizarDocumento(archivoEscrito.getContenido(),
                                    NOMBRE_ARCHIVO_ESCRITO, null, null);
                        }
                    }
                }
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public boolean isEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(boolean estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public boolean isFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(boolean fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public List<SelectItem> getEstadosComparendo() {
        return estadosComparendo;
    }

    public void setEstadosComparendo(List<SelectItem> estadosComparendo) {
        this.estadosComparendo = estadosComparendo;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public boolean isRadicarExpediente() {
        return radicarExpediente;
    }

    public void setRadicarExpediente(boolean radicarExpediente) {
        this.radicarExpediente = radicarExpediente;
    }

    public boolean isEvaluarExpediente() {
        return evaluarExpediente;
    }

    public void setEvaluarExpediente(boolean evaluarExpediente) {
        this.evaluarExpediente = evaluarExpediente;
    }

    public boolean isRealizarFallo() {
        return realizarFallo;
    }

    public void setRealizarFallo(boolean realizarFallo) {
        this.realizarFallo = realizarFallo;
    }

    public boolean isSeleccionMultiple() {
        return seleccionMultiple;
    }

    public void setSeleccionMultiple(boolean seleccionMultiple) {
        this.seleccionMultiple = seleccionMultiple;
    }

    public boolean isAprobarFallo() {
        return aprobarFallo;
    }

    public void setAprobarFallo(boolean aprobarFallo) {
        this.aprobarFallo = aprobarFallo;
    }

    public boolean isRechazarFallo() {
        return rechazarFallo;
    }

    public void setRechazarFallo(boolean rechazarFallo) {
        this.rechazarFallo = rechazarFallo;
    }

    public boolean isInfoInfractor() {
        return infoInfractor;
    }

    public void setInfoInfractor(boolean infoInfractor) {
        this.infoInfractor = infoInfractor;
    }

    public boolean isExonerador() {
        return exonerador;
    }

    public void setExonerador(boolean exonerador) {
        this.exonerador = exonerador;
    }

    public boolean isNumProceso() {
        return numProceso;
    }

    public void setNumProceso(boolean numProceso) {
        this.numProceso = numProceso;
    }

    public boolean isNumComparendo() {
        return numComparendo;
    }

    public void setNumComparendo(boolean numComparendo) {
        this.numComparendo = numComparendo;
    }

    public boolean isFechas() {
        return fechas;
    }

    public void setFechas(boolean fechas) {
        this.fechas = fechas;
    }

    public boolean isEstProceso() {
        return estProceso;
    }

    public void setEstProceso(boolean estProceso) {
        this.estProceso = estProceso;
    }

    public boolean isEstComparendo() {
        return estComparendo;
    }

    public void setEstComparendo(boolean estComparendo) {
        this.estComparendo = estComparendo;
    }

    public boolean isAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(boolean auxiliar) {
        this.auxiliar = auxiliar;
    }

    public StreamedContent getDocumentoRadicado() {
        return documentoRadicado;
    }

    public void setDocumentoRadicado(StreamedContent documentoRadicado) {
        this.documentoRadicado = documentoRadicado;
    }

    public StreamedContent getDocumentoSeguimiento() {
        return documentoSeguimiento;
    }

    public void setDocumentoSeguimiento(StreamedContent documentoSeguimiento) {
        this.documentoSeguimiento = documentoSeguimiento;
    }

    public boolean isGestionPruebas() {
        return gestionPruebas;
    }

    public void setGestionPruebas(boolean gestionPruebas) {
        this.gestionPruebas = gestionPruebas;
    }

    public boolean isEvaluarExpedienteContinuacion() {
        return evaluarExpedienteContinuacion;
    }

    public void setEvaluarExpedienteContinuacion(boolean evaluarExpedienteContinuacion) {
        this.evaluarExpedienteContinuacion = evaluarExpedienteContinuacion;
    }

    public boolean isRegistrarAcciones() {
        return registrarAcciones;
    }

    public void setRegistrarAcciones(boolean registrarAcciones) {
        this.registrarAcciones = registrarAcciones;
    }

    public EncabezadoImpugnacionMB getEncabezadoImpugnacion() {
        return encabezadoImpugnacion;
    }

    public void setEncabezadoImpugnacion(EncabezadoImpugnacionMB encabezadoImpugnacion) {
        this.encabezadoImpugnacion = encabezadoImpugnacion;
    }

    public boolean isRealizarFalloContinuacion() {
        return realizarFalloContinuacion;
    }

    public void setRealizarFalloContinuacion(boolean realizarFalloContinuacion) {
        this.realizarFalloContinuacion = realizarFalloContinuacion;
    }

    public boolean isVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle(boolean verDetalle) {
        this.verDetalle = verDetalle;
    }

    public VisualizarDocumentoMB getVisualizarDocumentoMB() {
        return visualizarDocumentoMB;
    }

    public void setVisualizarDocumentoMB(VisualizarDocumentoMB visualizarDocumentoMB) {
        this.visualizarDocumentoMB = visualizarDocumentoMB;
    }

    public boolean isAbogadoUno() {
        return abogadoUno;
    }

    public void setAbogadoUno(boolean abogadoUno) {
        this.abogadoUno = abogadoUno;
    }

    public boolean isAbogadoDos() {
        return abogadoDos;
    }

    public void setAbogadoDos(boolean abogadoDos) {
        this.abogadoDos = abogadoDos;
    }

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

    public boolean isAbogadoEspecialista() {
        return abogadoEspecialista;
    }

    public void setAbogadoEspecialista(boolean abogadoEspecialista) {
        this.abogadoEspecialista = abogadoEspecialista;
    }

    public List<SelectItem> getTipoParticipantes() {
        return tipoParticipantes;
    }

    public void setTipoParticipantes(List<SelectItem> tipoParticipantes) {
        this.tipoParticipantes = tipoParticipantes;
    }

    public boolean isHojaVida() {
        return hojaVida;
    }

    public void setHojaVida(boolean hojaVida) {
        this.hojaVida = hojaVida;
    }

    public Date getFechaActual() {
        return UtilFecha.currentZeroTimeDate();
    }

    public String getTiposArchivosPermitidos() {
        return TIPOS_ARCHIVOS_PERMITIDOS;
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public String getMesErrLimitSize() {
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE).getString("msg_error_tamanio_archivo"), tamanioMaximoArch);
    }

    public String getToolTipDocumento() {
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE).getString("too_inf_documento"), tamanioMaximoArch);
    }

}