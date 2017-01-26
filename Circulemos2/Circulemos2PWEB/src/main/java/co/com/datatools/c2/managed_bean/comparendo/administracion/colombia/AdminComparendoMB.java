package co.com.datatools.c2.managed_bean.comparendo.administracion.colombia;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.EstadoResolucionDTO;
import co.com.datatools.c2.dto.TipoEvidenciaDTO;
import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPatioDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoResolucionDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesarComparendoDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.configuracion.AsociaComparendoFormularioDTO;
import co.com.datatools.c2.dto.configuracion.AsociaDiasIngresoComparendoDTO;
import co.com.datatools.c2.dto.configuracion.ConfiguracionGradosAlcoholDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.EmpresaVehiculoDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeracion.EnumTipoSancion;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumConfiguracion;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.enumeraciones.EnumEstadoResolucion;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.enumeraciones.EnumTipoComparendo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.managed_bean.comparendo.administracion.AdminComparendoHelper;
import co.com.datatools.c2.managed_bean.comparendo.administracion.ecuador.AdminComparendoEcuadorHolderFL;
import co.com.datatools.c2.managed_bean.formularios.consultas.ConsultarSeguimientoFormularioMB;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRAgente;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRDiaNoHabil;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRPersona;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicular;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicularEmpresa;
import co.com.datatools.c2.negocio.interfaces.IRRegistroVehicularPropietario;
import co.com.datatools.c2.negocio.interfaces.administracion.IRAdministracionComparendo;
import co.com.datatools.c2.negocio.interfaces.administracion.IRInfraccion;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRConfiguracion;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.file.FileValidator;

/**
 * ManagedBean que gestiona las paginas de administracion de comparendos Colombia
 * 
 * @author julio.pinzon
 * 
 */
@ManagedBean
@SessionScoped
public class AdminComparendoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(AdminComparendoMB.class.getName());

    private static final String NOMBRE_BUNDLE_ADMIN_COMPARENDO = "labelAdminComparendo";

    /**
     * Pasos del wizard del formulario de registro
     */
    private static final String PASO_PROPIETARIO = "propietario";
    private static final String PASO_AGENTE = "agente_transito";
    private static final String PASO_INFRACTOR = "infractor";
    private static final String PASO_TESTIGO = "testigo";
    private static final String PASO_VEHICULO = "vehiculo";
    private static final String PASO_OBSERVACIONES = "observaciones";
    private static final String PASO_EMBRIAGUEZ = "embriaguez";
    private static final String PASO_EVIDENCIAS = "evidencias_fisicas";

    /**
     * Sufijo
     */
    private static final String SUFIJO_PROPIETARIO = "Propietario";
    private static final String SUFIJO_AGENTE = "AgenteTransito";
    private static final String SUFIJO_INFRACTOR = "Infractor";
    private static final String SUFIJO_TESTIGO = "Testigo";

    /**
     * Atributo que permite identificar que tipos de archivos son permitidos
     */
    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(tiff|pdf|png|jpg)$/";

    private int tamanioMaximoArch;

    @EJB
    private IRAdministracion administracionEjb;

    @EJB
    private IRAdministracionFormularios administracionFormularioEjb;

    @EJB
    private IRParametro parametroEjb;

    @EJB
    private IRInfraccion infraccionEjb;

    @EJB
    private IRRegistroVehicular regVehicularEjb;

    @EJB
    private IRRegistroVehicularEmpresa regVehicularEmpresaEjb;

    @EJB
    private IRRegistroVehicularPropietario regVehicularPropietarioEjb;

    @EJB
    private IRAgente agenteEjb;

    @EJB
    private IRFormulario formularioEjb;

    @EJB
    private IRRecibirComparendo recibircomparendoEjb;

    @EJB
    private IRConfiguracion configuracionEjb;

    @EJB
    private IRRecibirComparendo recibirComparendoEjb;

    @EJB
    private IRPersona personaEjb;

    @EJB
    private IRComparendo comparendoEjb;

    @EJB
    private IRRepositorioArchivo repositorioArchivoEJB;

    @EJB
    private IRDiaNoHabil iRDiaNoHabil;

    @EJB
    private IRAdministracionComparendo administracionComparendoEJB;

    @EJB
    private IRDocumentosCirculemos documentosCirculemosEJB;

    private StreamedContent streamedContent;

    // Propietario propuesto por la placa del vehiculo
    private PropietarioVehiculoDTO propietario;

    private List<String> images = new ArrayList<>();

    private String rutaImagenes = null;

    public AdminComparendoMB() {
    }

    @PostConstruct
    public void init() {
        logger.debug("AdminComparendoMB::init()");
        rutaImagenes = parametroEjb.consultarValorParametro(EnumParametro.URL_EVIDENCIAS, getCodigoOrganismoTransito());
        // Validar que el parametro PAIS_INSTALACION sea igual a (pais Colombia) id 47
        ValorParametroDTO valorParametroDTO = parametroEjb.consultarParametro(EnumParametro.PAIS_INSTALACION,
                getOrganismoTransito().getCodigoOrganismo(), true);
        // if (valorParametroDTO.getValorParamInt() != ConstantesComparendo.ID_PAIS_COLOMBIA) {
        if (valorParametroDTO.getValorParamInt() != getPais().getId()) {
            throw new CirculemosRuntimeException("PAIS_INSTALACION no es igual a (pais Colombia) id 47");
        }
        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
    }

    /**
     * Permite llamar la consulta de comparendos de acuerdo con los filtros de busqueda
     * 
     * @author divier.casas
     */
    public void consultar() {
        logger.debug("AdminComparendoMB::consultar()");
        try {
            AdminComparendoHolderFL adminComparendoHolderFL = findFlowObject(AdminComparendoHolderFL.class,
                    AdminComparendoHolderFL.NOMBRE_BEAN);
            adminComparendoHolderFL.setComparendoSeleccionado(null);

            if (adminComparendoHolderFL.getFechaImposicionInicial() == null
                    && adminComparendoHolderFL.getFechaImposicionFinal() != null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_inicial");
                return;
            }

            if (adminComparendoHolderFL.getFechaImposicionInicial() != null
                    && adminComparendoHolderFL.getFechaImposicionFinal() == null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_final");
                return;
            }

            if (adminComparendoHolderFL.getFechaImposicionInicial() != null
                    && adminComparendoHolderFL.getFechaImposicionFinal() != null && adminComparendoHolderFL
                            .getFechaImposicionInicial().after(adminComparendoHolderFL.getFechaImposicionFinal())) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_intervalo_fechas");
                return;
            }

            if (adminComparendoHolderFL.getComparendo().getInfractor().getTipoIdentificacion().getId() == null
                    && adminComparendoHolderFL.getComparendo().getInfractor().getNumeroIdentificacion() != null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_infractor");
                return;
            }

            if (adminComparendoHolderFL.getComparendo().getInfractor().getTipoIdentificacion().getId() != null
                    && adminComparendoHolderFL.getComparendo().getInfractor().getNumeroIdentificacion() == null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_infractor");
                return;
            }

            if (adminComparendoHolderFL.getComparendo().getPropietario().getTipoIdentificacion().getId() == null
                    && adminComparendoHolderFL.getComparendo().getPropietario().getNumeroIdentificacion() != null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_propietario");
                return;
            }

            if (adminComparendoHolderFL.getComparendo().getPropietario().getTipoIdentificacion().getId() != null
                    && adminComparendoHolderFL.getComparendo().getPropietario().getNumeroIdentificacion() == null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_propietario");
                return;
            }

            if (adminComparendoHolderFL.getComparendo().getComparendoAgente().getTipoIdentificacionPersona()
                    .getId() == null
                    && adminComparendoHolderFL.getComparendo().getComparendoAgente()
                            .getNumeroIdentificacion() != null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_agente");
                return;
            }

            if (adminComparendoHolderFL.getComparendo().getComparendoAgente().getTipoIdentificacionPersona()
                    .getId() != null
                    && adminComparendoHolderFL.getComparendo().getComparendoAgente()
                            .getNumeroIdentificacion() == null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_agente");
                return;
            }

            ConsultaComparendoDTO consultaComparendoDTO = new ConsultaComparendoDTO();

            if (adminComparendoHolderFL.getComparendo().getOrdenComparendoNacional().getOrganismoTransito()
                    .getCodigoOrganismo() != null) {
                consultaComparendoDTO.setCodigoOrganismo(adminComparendoHolderFL.getComparendo()
                        .getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
            }

            consultaComparendoDTO.setEsPolca(adminComparendoHolderFL.isPolca());
            consultaComparendoDTO.setIdTipoComparendo(EnumTipoComparendo.COMPARENDO_NACIONAL.getCodigo());

            if (adminComparendoHolderFL.getCodOrganismoNumeroComparendo() != null
                    && adminComparendoHolderFL.getNumeroComparendo() != null) {
                consultaComparendoDTO.setNumeroComparendo(adminComparendoHolderFL.getCodOrganismoNumeroComparendo()
                        + adminComparendoHolderFL.getNumeroComparendo());
            }
            consultaComparendoDTO.setPlacaVehiculo(
                    adminComparendoHolderFL.getComparendo().getComparendoVehiculo().getPlacaVehiculo());
            consultaComparendoDTO.setFechaInicioImposicion(adminComparendoHolderFL.getFechaImposicionInicial());
            consultaComparendoDTO.setFechaFinImposicion(adminComparendoHolderFL.getFechaImposicionFinal());
            consultaComparendoDTO.setTipoDocumentoInfractor(
                    adminComparendoHolderFL.getComparendo().getInfractor().getTipoIdentificacion().getId());
            consultaComparendoDTO.setTipoDocumentoPropietario(
                    adminComparendoHolderFL.getComparendo().getPropietario().getTipoIdentificacion().getId());
            consultaComparendoDTO.setTipoDocumentoAgente(adminComparendoHolderFL.getComparendo().getComparendoAgente()
                    .getTipoIdentificacionPersona().getId());
            consultaComparendoDTO.setNumeroDocumentoInfractor(
                    adminComparendoHolderFL.getComparendo().getInfractor().getNumeroIdentificacion());
            consultaComparendoDTO.setNumeroDocumentoPropietario(
                    adminComparendoHolderFL.getComparendo().getPropietario().getNumeroIdentificacion());
            consultaComparendoDTO.setNumeroDocumentoAgente(
                    adminComparendoHolderFL.getComparendo().getComparendoAgente().getNumeroIdentificacion());

            List<ComparendoDTO> lstComparendoDTO = comparendoEjb.consultarComparendos(consultaComparendoDTO);

            if (lstComparendoDTO == null || lstComparendoDTO.isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
                adminComparendoHolderFL.setLstComparendos(new ArrayList<ComparendoDTO>(0));
                return;
            } else {

                adminComparendoHolderFL.setLstComparendos(lstComparendoDTO);
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lstComparendoDTO.size());
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Concatena la fecha y hora del comparendo
     * 
     * @param comparendoDTO
     *            comparnedo a concatenar datos de fecha y hora imposicion del comparendo.
     * @author luis.forero(mod 2016-03-10)
     */
    public Date concatenarHoraFechaInfraccion(ComparendoDTO comparendoDTO) {
        return UtilFecha.setHoraFecha(comparendoDTO.getFechaInfraccion(), comparendoDTO.getHoraInfraccion());
    }

    /**
     * Continua a llenar datos especificos del comparendo
     * 
     * @author julio.pinzon
     */
    public void irRegistrar() {
        logger.debug("AdminComparendoMB::irRegistrar()");
        AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class, AdminComparendoFL.NOMBRE_BEAN);

        adminComparendoFL.setProcesaComparendo(new ProcesaComparendoDTO());
        adminComparendoFL.getProcesaComparendo().setFechaRecepcion(new Date());
        adminComparendoFL.getProcesaComparendo().setInmovilizado(false);
        adminComparendoFL.getProcesaComparendo().setRetieneLicencia(false);
        adminComparendoFL.getProcesaComparendo().setOrganismoTransito(new OrganismoTransitoDTO());
        adminComparendoFL.setEmpresaVehiculo(new ProcesaComparendoPersonaDTO());
        adminComparendoFL.getEmpresaVehiculo()
                .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getValue());
        adminComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
        adminComparendoFL.getPropietario()
                .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
        adminComparendoFL.setInfractor(new ProcesaComparendoPersonaDTO());
        adminComparendoFL.getInfractor().setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR.getValue());
        adminComparendoFL.setTestigo(new ProcesaComparendoPersonaDTO());
        adminComparendoFL.getTestigo().setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO.getValue());
        adminComparendoFL.getProcesaComparendo().setExisteFugaInfractor(false);
        adminComparendoFL.getProcesaComparendo().setRetieneLicencia(false);
        adminComparendoFL.getProcesaComparendo().setNiegaPruebaAlcoholemia(false);
        adminComparendoFL.setPasoActual(PASO_VEHICULO);
        adminComparendoFL.setDetallesProcesamiento(new ArrayList<String>());
        adminComparendoFL.setExisteError(false);

        // Consultar parametros polca
        ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.CODIGO_POLCA,
                getCodigoOrganismoTransito(), true);
        adminComparendoFL.setCodigoPolca(valor.getValorParam());
        valor = parametroEjb.consultarParametro(EnumParametro.NOMBRE_POLCA, getCodigoOrganismoTransito(), true);
        adminComparendoFL.setNombreEntidadPolca(valor.getValorParam());
    }

    /**
     * Continua a llenar datos especificos del comparendo
     * 
     * @author julio.pinzon
     */
    public void continuar() {
        logger.debug("AdminComparendoMB::continuar()");
        AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class, AdminComparendoFL.NOMBRE_BEAN);

        boolean errorValidacion = false;

        // Valida que la fecha de imposicion no sea mayor a la actual
        if (adminComparendoFL.getProcesaComparendo().getFechaInfraccion()
                .compareTo(adminComparendoFL.getProcesaComparendo().getFechaRecepcion()) > 0) {
            getFacesContext().addMessage("form-ingreso:fechaImposicion", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    null, getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("msg_error_valida_fecha_menor_actual")));
            errorValidacion = true;
        }

        // Valida que haya encontrado la infraccion
        if (StringUtils.isEmpty(adminComparendoFL.getDescripcionInfraccion())) {
            getFacesContext().addMessage("form-ingreso:descripcionInfraccion",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }

        // Valida que haya llenado el campo de direccion
        if (StringUtils.isEmpty(adminComparendoFL.getDireccionInfraccionTexto())) {
            getFacesContext().addMessage("form-ingreso:direccionInfraccion",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }
        // Si hay error muestra los mensajes
        if (errorValidacion) {
            return;
        }
        // Tipo de identificacion para empresa
        adminComparendoFL.setTipoIdentificacionEmpresa(
                administracionEjb.obtenerTipoIdentificacionEmpresaJuridicaPais(getPais().getId()));

        try {
            // Concatena el codigo de organismo al numero de comparendo
            String numeroComparendo = adminComparendoFL.getCodOrganismoNumeroComparendo()
                    + adminComparendoFL.getNumeroComparendo();

            AsociaComparendoFormularioDTO asociaComparendo = new AsociaComparendoFormularioDTO();
            List<String> tiposComparendo = new ArrayList<String>();
            tiposComparendo.add(adminComparendoFL.getConfiguracionInfraccion().getInfraccion().getTipoComparendo()
                    .getId().toString());
            asociaComparendo.setTipoComparendo(tiposComparendo);
            asociaComparendo = configuracionEjb.consultarDatoConfiguracion(
                    EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO.getCodigo(), asociaComparendo);
            // Valida que el campo 'Polca?' ES IGUAL A 'NO'(FA2)
            if (!adminComparendoFL.isPolca()) {
                /*
                 * Valida que el 'Número de comparendo' se encuentre en 'Estado del Formulario' IGUAL a 'Asignado', 'Pendiente por asignar' y se
                 * encuentre asociado al 'organismo de tránsito' seleccionado (paso 4 del flujo básico). (FA3)
                 */
                EnumEstadoFomulario[] enumEstadoFomularios = { EnumEstadoFomulario.ASIGNADO,
                        EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR };

                if (asociaComparendo.getTipoFormulario() != null && !asociaComparendo.getTipoFormulario().isEmpty()) {
                    adminComparendoFL.setIdTipoFormulario(Integer.valueOf(asociaComparendo.getTipoFormulario().get(0)));
                    if (!formularioEjb.existeEstadoFormularioOrganismo(numeroComparendo,
                            Integer.valueOf(asociaComparendo.getTipoFormulario().get(0)),
                            adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo(),
                            enumEstadoFomularios)) {
                        addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_no_estado_asignado");
                        return;
                    }
                } else {
                    logger.error(
                            "No existe configuracion para :" + EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO);
                    throw new RuntimeException(
                            "No existe configuracion para :" + EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO);
                    // TODO: Excepcion severa
                }

            } else {
                // Verifica que no exista el formulario en el sistema cuando es POLCA
                if (formularioEjb.existeFormulario(numeroComparendo,
                        Integer.valueOf(asociaComparendo.getTipoFormulario().get(0)))) {
                    addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_existe_formulario_polca");
                    return;
                }
            }
            /*
             * Valida que el campo 'Número de Comparendo' NO haya sido guardado en 'Estado de Comparendo' IGUAL A 'Registrado' o ingresado como
             * comparendo inconsistente. (FA4)
             */
            if (recibircomparendoEjb.comparendoIngresado(numeroComparendo,
                    adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo())) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_ya_registrado");
                return;
            }
            /*
             * Valida trae el campo 'número de días' en la configuración 'Cantidad de días para el ingreso de un comparendo' según el: - Organismo de
             * tránsito - Tipo de Comparendo Sumando esta cantidad a la 'Fecha de imposición comparendo' y el resultado NO debe ser MAYOR a la fecha
             * actual del sistema. (FA5)
             */
            AsociaDiasIngresoComparendoDTO asociaDias = new AsociaDiasIngresoComparendoDTO();
            List<String> mediosImposicion = new ArrayList<String>();
            mediosImposicion.add(String.valueOf(adminComparendoFL.getProcesaComparendo().getCodigoMedioImposicion()));
            asociaDias.setMedioImposicion(mediosImposicion);
            // Ingresamos el organismo de transito
            List<String> codigoOrganismo = new ArrayList<String>();
            codigoOrganismo.add(String
                    .valueOf(adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo()));
            asociaDias.setCodigoOrganismo(codigoOrganismo);
            asociaDias = configuracionEjb.consultarDatoConfiguracion(
                    EnumConfiguracion.ASOCIA_DIAS_INGRESAR_COMPARENDO.getCodigo(), asociaDias);

            Date fechaMaximaPermitida = iRDiaNoHabil.sumarDiasHabiles(
                    adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo(),
                    adminComparendoFL.getProcesaComparendo().getFechaInfraccion(),
                    asociaDias.getDiasLimite().intValue());

            if (Calendar.getInstance().getTime().after(fechaMaximaPermitida)) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_dias_ingreso_vencido");
                return;
            }

            // Verifica que la configuración del 'Código de la infracción' por el caso de uso 'Administrar infracciones' el campo '¿Requiere placa?'
            // es IGUAL A 'SI'
            adminComparendoFL.setPlacaRequerida(adminComparendoFL.getConfiguracionInfraccion().getRequierePlaca());

            // Require infractor
            adminComparendoFL
                    .setRequiereInfractor(adminComparendoFL.getConfiguracionInfraccion().getInfractorObligatorio()
                            && !adminComparendoFL.getProcesaComparendo().getExisteFugaInfractor());

            // Require embriaguez
            adminComparendoFL
                    .setRequiereEmbriaguez(adminComparendoFL.getConfiguracionInfraccion().getAplicaEmbriaguez());

            // Requiere inmovilizacion
            boolean requiereInmovilizacion = false;
            for (TipoSancionDTO tipoSancion : adminComparendoFL.getConfiguracionInfraccion().getTipoSancionList()) {
                if (EnumTipoSancion.INMOVILIZACION_VEHICULO.getValue().equals(tipoSancion.getId())) {
                    requiereInmovilizacion = true;
                    break;
                }
            }
            adminComparendoFL.setRequiereInmovilizacion(
                    requiereInmovilizacion && !adminComparendoFL.getProcesaComparendo().getExisteFugaInfractor());
            adminComparendoFL.getProcesaComparendo().setInmovilizado(
                    requiereInmovilizacion && !adminComparendoFL.getProcesaComparendo().getExisteFugaInfractor());

            // Require placa agente
            adminComparendoFL.setPlacaAgenteRequerida(false);
            adminComparendoFL.setRequiereAgenteTransito(false);

            // consultar el 'Nombre del Responsable' del 'Número de Comparendo' digitado.
            if (!adminComparendoFL.isPolca()) {
                // Para consultar el responsable de un formulario implementar:
                UnificacionResponsableDTO unificacionResponsableDTO = administracionFormularioEjb
                        .consultarResponsableFormulario(numeroComparendo, adminComparendoFL.getIdTipoFormulario());
                adminComparendoFL.getProcesaComparendo().setIdUnificacionResponsable(unificacionResponsableDTO.getId());
                if (unificacionResponsableDTO.getPersona() != null) {
                    adminComparendoFL.setNombreResponsable(unificacionResponsableDTO.getPersona().getNombreCompleto());
                } else if (unificacionResponsableDTO.getOrganismoTransito() != null) {
                    adminComparendoFL.setNombreResponsable(
                            unificacionResponsableDTO.getOrganismoTransito().getNombreOrganismo());
                }
            } else {
                // Policía de carretera (POLCA)
                adminComparendoFL.getProcesaComparendo().setIdUnificacionResponsable(null);
                adminComparendoFL.getProcesaComparendo()
                        .setNombreResponsable(adminComparendoFL.getNombreEntidadPolca());
                adminComparendoFL.setNombreResponsable(adminComparendoFL.getNombreEntidadPolca());
            }

            adminComparendoFL.setMostrarWizard(true);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Invoca al metodo de negocio que registra un nuevo comparendo
     * 
     * @return true si el rango fue registrado, de lo contrario false y se visualiza el error q se haya presentado
     * @author julio.pinzon
     */
    public boolean registrar() {
        logger.debug("AdminComparendoMB::registrar()");

        AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class, AdminComparendoFL.NOMBRE_BEAN);

        adminComparendoFL.setExisteError(false);
        adminComparendoFL.getProcesaComparendo().setFechaRecepcion(new Date());
        adminComparendoFL.getProcesaComparendo().setProcesaComparendoPersonas(null);
        adminComparendoFL.getProcesaComparendo().setProcesaEvidencias(null);
        adminComparendoFL.getProcesaComparendo().setProcesaDireccionComparendo(null);
        List<ProcesaComparendoPersonaDTO> procesaComparendoPersonas = new ArrayList<ProcesaComparendoPersonaDTO>();
        if (adminComparendoFL.getEmpresaVehiculo() != null) {
            procesaComparendoPersonas.add(adminComparendoFL.getEmpresaVehiculo());
        }
        if (adminComparendoFL.getInfractor() != null) {
            adminComparendoFL.getInfractor().setProcesaDireccion(
                    AdminComparendoHelper.toProcesaDireccionDTO(adminComparendoFL.getDireccionInfractor()));
            procesaComparendoPersonas.add(adminComparendoFL.getInfractor());
        }
        if (adminComparendoFL.getPropietario() != null) {
            procesaComparendoPersonas.add(adminComparendoFL.getPropietario());
        }
        if (adminComparendoFL.getTestigo() != null) {
            adminComparendoFL.getTestigo().setProcesaDireccion(
                    AdminComparendoHelper.toProcesaDireccionDTO(adminComparendoFL.getDireccionTestigo()));
            procesaComparendoPersonas.add(adminComparendoFL.getTestigo());
        }
        adminComparendoFL.getProcesaComparendo().setProcesaComparendoPersonas(procesaComparendoPersonas);
        adminComparendoFL.getProcesaComparendo().setProcesaEvidencias(adminComparendoFL.getProcesaEvidencias());
        adminComparendoFL.getProcesaComparendo().setProcesaDireccionComparendo(
                AdminComparendoHelper.toProcesaDireccionDTO(adminComparendoFL.getDireccionInfraccion()));
        // Concatena para armar el numero de comparendo
        adminComparendoFL.getProcesaComparendo().setNumeroComparendo(
                adminComparendoFL.getCodOrganismoNumeroComparendo() + adminComparendoFL.getNumeroComparendo());
        // Polca
        // TODO polca
        // adminComparendoFL.getProcesaComparendo().setEsPolca(adminComparendoFL.isPolca());
        // Direccion de inmovilizacion
        adminComparendoFL.getProcesaComparendo().setProcesaDireccionPatio(
                AdminComparendoHelper.toProcesaDireccionDTO(adminComparendoFL.getDireccionInmovilizacion()));
        // Id de la infraccion
        adminComparendoFL.getProcesaComparendo()
                .setIdInfraccion(adminComparendoFL.getConfiguracionInfraccion().getInfraccion().getId());

        // Genera DTO de almacenamiento
        ProcesarComparendoDTO procesarComparendoDTO = new ProcesarComparendoDTO();
        procesarComparendoDTO.setEnumProcesamiento(EnumProcesamiento.RECIBIR_COMPARENDO);
        procesarComparendoDTO.setProcesaComparendoDTO(adminComparendoFL.getProcesaComparendo());

        RespuestaValidacionDTO respuestaValidacion = null;
        try {
            respuestaValidacion = recibirComparendoEjb.recibirComparendo(procesarComparendoDTO);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        // Validar respuesta
        if (respuestaValidacion != null) {
            // Validar respuestas de errores
            if (respuestaValidacion.getCodigoResultado().equals(EnumErrorProcesamiento.RECHAZADO.getCodigo())) {
                // Error bloqueante
                logger.info("Error en procesar: " + respuestaValidacion.getCodigoResultado());

                getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", respuestaValidacion
                        .getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento().getDescripcion()));
                return false;
            } else if (respuestaValidacion.getCodigoResultado()
                    .equals(EnumErrorProcesamiento.INCONSISTENTE.getCodigo())) {
                // Errores no bloqueantes
                adminComparendoFL.setExisteError(true);
                adminComparendoFL.setErrorProcesamiento(
                        getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("msg_recibido_sin_procesar"));
                for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacion
                        .getDetalleProcesamientoDTOs()) {
                    adminComparendoFL.getDetallesProcesamiento()
                            .add(detalleProcesamientoDTO.getErrorProcesamiento().getCodigo() + " - "
                                    + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                    logger.info(
                            "Error en procesar: " + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                }
                return false;
            } else if (respuestaValidacion.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getCodigo())) {
                if (respuestaValidacion.isExisteAlerta()) {
                    for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacion
                            .getDetalleProcesamientoDTOs()) {
                        // Error al guardar documento en el repositorio
                        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
                                detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion()));
                        logger.info(
                                "Alerta en procesar: " + detalleProcesamientoDTO.getErrorProcesamiento().getCodigo());
                    }
                }
                // Mensaje de guardado exitoso
                addInfoMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_satisfactorio");
            }
        }
        return true;
    }

    /**
     * Valor de numero comparendo cambia en consulta, metodo que permite rellenar el numero del comparendo
     */
    public void onNumeroComparendoChangeConsulta() {
        logger.debug("AdminComparendoMB::onNumeroComparendoChangeConsulta()");
        AdminComparendoHolderFL adminComparendoHolderFL = findFlowObject(AdminComparendoHolderFL.class,
                AdminComparendoHolderFL.NOMBRE_BEAN);

        if (StringUtils.isNotEmpty(adminComparendoHolderFL.getNumeroComparendo())) {
            adminComparendoHolderFL.setNumeroComparendo(
                    StringUtils.leftPad(String.valueOf(adminComparendoHolderFL.getNumeroComparendo()), 12, "0"));
        }
    }

    /**
     * Valor de Polca cambia consulta, metodo que permite asignar el organismo asociado al numero del comparendo
     * 
     * @author julio.pinzon
     */
    public void onPolcaChangeConsulta() {
        logger.debug("AdminComparendoMB::onPolcaChangeConsulta()");
        cambiarCodOrganismoNumeroComparendoConsulta();
    }

    /*****************
     * Eventos encabezado
     *****************/

    /**
     * Valor de numero comparendo cambia
     * 
     * @author julio.pinzon
     */
    public void onNumeroComparendoChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onNumeroComparendoChange()");
        if (StringUtils.isNotEmpty(adminComparendoFL.getNumeroComparendo())) {
            adminComparendoFL.setNumeroComparendo(
                    StringUtils.leftPad(String.valueOf(adminComparendoFL.getNumeroComparendo()), 12, "0"));
        }
    }

    /**
     * Valor de Polca cambia
     * 
     * @author julio.pinzon
     */
    public void onPolcaChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onPolcaChange()");
        cambiarCodOrganismoNumeroComparendo(adminComparendoFL);
    }

    /**
     * Organismo de transito cambia
     * 
     * @author julio.pinzon
     */
    public void onCodigoOrganismoChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onCodigoOrganismoChange()");
        cambiarCodOrganismoNumeroComparendo(adminComparendoFL);

        if (adminComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !adminComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && adminComparendoFL.getProcesaComparendo().getFechaInfraccion() != null
                && adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo() != null) {
            cambiarInfraccion(adminComparendoFL);
            RequestContext.getCurrentInstance().update("descripcionInfraccion");
        }
    }

    /**
     * Organismo de transito cambia consulta
     * 
     * @author julio.pinzon
     */
    public void onCodigoOrganismoChangeConsulta() {
        logger.debug("AdminComparendoMB::onCodigoOrganismoChangeConsulta()");
        cambiarCodOrganismoNumeroComparendoConsulta();
    }

    /**
     * Cambia valor en codigo de comparendo
     * 
     * @author julio.pinzon
     */
    private void cambiarCodOrganismoNumeroComparendo(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::cambiarCodOrganismoNumeroComparendo()");
        if (adminComparendoFL.isPolca()) {
            adminComparendoFL.setCodOrganismoNumeroComparendo(adminComparendoFL.getCodigoPolca());
        } else if (adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo()
                .equals(ConstantesComparendo.CODIGO_OTROS)) {
            adminComparendoFL.setCodOrganismoNumeroComparendo("");
        } else {
            adminComparendoFL.setCodOrganismoNumeroComparendo(StringUtils.rightPad(
                    String.valueOf(
                            adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo()),
                    8, "0"));
        }
    }

    /**
     * Cambia valor en codigo de comparendo consulta
     */
    private void cambiarCodOrganismoNumeroComparendoConsulta() {
        logger.debug("AdminComparendoMB::cambiarCodOrganismoNumeroComparendoConsulta()");
        AdminComparendoHolderFL adminComparendoHolderFL = findFlowObject(AdminComparendoHolderFL.class,
                AdminComparendoHolderFL.NOMBRE_BEAN);

        // Consultar parametros polca
        ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.CODIGO_POLCA,
                getCodigoOrganismoTransito(), true);
        adminComparendoHolderFL.setCodigoPolca(valor.getValorParam());

        if (adminComparendoHolderFL.isPolca()) {
            adminComparendoHolderFL.setCodOrganismoNumeroComparendo(adminComparendoHolderFL.getCodigoPolca());
        } else if (adminComparendoHolderFL.getComparendo().getOrdenComparendoNacional().getOrganismoTransito()
                .getCodigoOrganismo().equals(ConstantesComparendo.CODIGO_OTROS)) {
            adminComparendoHolderFL.setCodOrganismoNumeroComparendo("");
        } else {
            adminComparendoHolderFL.setCodOrganismoNumeroComparendo(
                    StringUtils.rightPad(String.valueOf(adminComparendoHolderFL.getComparendo()
                            .getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo()), 8, "0"));
        }
    }

    /**
     * Cuando se debe consultar de nuevo la infraccion por cambio en fecha
     * 
     * @author julio.pinzon
     */
    public void onFechaImposicionChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onFechaImposicionChange()");
        adminComparendoFL.getProcesaComparendo()
                .setHoraInfraccion(adminComparendoFL.getProcesaComparendo().getFechaInfraccion());
        cambiarInfraccion(adminComparendoFL);
    }

    /**
     * Cuando se debe consultar de nuevo la infraccion por cambio en codigo
     */
    public void onCodigoInfraccionChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onCodigoInfraccionChange()");
        adminComparendoFL.getProcesaComparendo()
                .setCodigoInfraccion(adminComparendoFL.getProcesaComparendo().getCodigoInfraccion().toUpperCase());
        adminComparendoFL.setDescripcionInfraccion(null);
        if (adminComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !adminComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && adminComparendoFL.getProcesaComparendo().getFechaInfraccion() != null
                && adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo() != null) {
            cambiarInfraccion(adminComparendoFL);
        } else {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_faltan_valores_infraccion");
        }

    }

    /**
     * Cambia valor de la infraccion
     * 
     * @param adminComparendoFL
     */
    private void cambiarInfraccion(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::cambiarInfraccion()");
        adminComparendoFL.setDescripcionInfraccion(null);
        if (adminComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !adminComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && adminComparendoFL.getProcesaComparendo().getFechaInfraccion() != null
                && adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo() != null) {
            ConfiguracionInfraccionDTO configuracionInfraccionDTO = infraccionEjb.consultarInfraccion(
                    adminComparendoFL.getProcesaComparendo().getCodigoInfraccion(),
                    adminComparendoFL.getProcesaComparendo().getFechaInfraccion());
            if (configuracionInfraccionDTO != null) {
                adminComparendoFL.setDescripcionInfraccion(configuracionInfraccionDTO.getDescripcion());
                adminComparendoFL.setConfiguracionInfraccion(configuracionInfraccionDTO);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_no_encuentra_infraccion");
            }
        }
    }

    /**
     * Recarga los campos de direccion
     * 
     * @author julio.pinzon
     */
    public void recargarDireccion(DireccionDTO direccion, AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::recargarDireccion()");
        RequestContext context = RequestContext.getCurrentInstance();
        if (adminComparendoFL.isEditaDireccionInfraccion()) {
            context.update("form-ingreso:datos-lugar-infraccion");
            adminComparendoFL.setDireccionInfraccion(direccion);
            adminComparendoFL.setEditaDireccionInfraccion(false);
        } else if (adminComparendoFL.isEditaDireccionInfractor()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-infractor");
            adminComparendoFL.setDireccionInfractor(direccion);
            adminComparendoFL.setEditaDireccionInfractor(false);
        } else if (adminComparendoFL.isEditaDireccionInmovilizacion()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-inmovilizacion");
            adminComparendoFL.setDireccionInmovilizacion(direccion);
            adminComparendoFL.setEditaDireccionInmovilizacion(false);
        } else if (adminComparendoFL.isEditaDireccionTestigo()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-testigo");
            adminComparendoFL.setDireccionTestigo(direccion);
            adminComparendoFL.setEditaDireccionTestigo(false);
        }
        context.execute("window.scrollTo(0,885);");

    }

    /*****************
     * Fin Eventos encabezado
     *****************/

    /**********************
     * Eventos vehiculo
     **********************/
    /**
     * Cuando se debe consultar los datos del vehiculo
     * 
     * @author julio.pinzon
     */
    public void onPlacaChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onPlacaChange()");

        // reinicia valores
        reiniciarDatosVehiculo(adminComparendoFL);

        if (StringUtils.isNotBlank(adminComparendoFL.getProcesaComparendo().getPlacaVehiculo())) {
            // Reinicia datos del propietario
            adminComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
            adminComparendoFL.getPropietario()
                    .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());

            adminComparendoFL.setPlacaVehiculoDeshabilitado(false);
            adminComparendoFL.setIdVehiculoDeshabilitado(true);
            adminComparendoFL.getProcesaComparendo().setIdentificacionVehiculo(null);
            /*
             * Consultar la información existente por la 'Placa vehículo' por medio del caso de uso 'Consultar información de registro automotor' y
             * proponerla en los campos que se listan en esta sección ('VEHICULO')
             */
            VehiculoDTO vehiculo = regVehicularEjb
                    .consultarVehiculo(adminComparendoFL.getProcesaComparendo().getPlacaVehiculo());
            if (vehiculo != null) {
                adminComparendoFL.getProcesaComparendo()
                        .setCodigoOrganismoMatriculaVehiculo(vehiculo.getOrganismoTransito().getCodigoOrganismo());
                adminComparendoFL.getProcesaComparendo().setNumeroLicenciaTransito(vehiculo.getLicenciaTransito());
                adminComparendoFL.getProcesaComparendo().setNumeroMotor(vehiculo.getNumeroMotor());
                if (vehiculo.getClaseVehiculo() != null) {
                    adminComparendoFL.getProcesaComparendo().setIdClaseVehiculo(vehiculo.getClaseVehiculo().getId());
                }
                if (vehiculo.getModalidad() != null) {
                    adminComparendoFL.getProcesaComparendo().setIdModalidad(vehiculo.getModalidad().getId());
                }
                if (vehiculo.getRadioAccion() != null) {
                    adminComparendoFL.getProcesaComparendo().setIdRadioAccion(vehiculo.getRadioAccion().getId());
                }
                if (vehiculo.getNumeroTarjetaOpera() != null) {
                    adminComparendoFL.getProcesaComparendo()
                            .setNumeroTarjetaOperacion(vehiculo.getNumeroTarjetaOpera().toString());
                }
                if (vehiculo.getTipoServicio() != null) {
                    adminComparendoFL.getProcesaComparendo().setIdTipoServicio(vehiculo.getTipoServicio().getId());
                }
                if (vehiculo.getColor() != null) {
                    adminComparendoFL.getProcesaComparendo().setIdColor(vehiculo.getColor().getId());
                }
                if (vehiculo.getLinea() != null) {
                    adminComparendoFL.getProcesaComparendo().setIdLineaVehiculo(vehiculo.getLinea().getId());
                }
                if (vehiculo.getModelo() != null) {
                    adminComparendoFL.getProcesaComparendo().setModelo(vehiculo.getModelo().toString());
                }

                /*
                 * Consultar la información existente por la 'Placa vehículo' por medio del caso de uso 'Consultar información de registro de empresas
                 * de transporte' donde la 'Fecha de imposición de comparendo' se encuentre entre en un intervalo 'Fecha inicial de vigencia de la
                 * empresa' y 'Fecha final de vigencia de la empresa' y proponerla en los campos que se listan en esta sección
                 */
                EmpresaVehiculoDTO empresa = regVehicularEmpresaEjb.consultarEmpresa(
                        adminComparendoFL.getProcesaComparendo().getPlacaVehiculo(),
                        adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo(),
                        adminComparendoFL.getProcesaComparendo().getFechaInfraccion());
                if (empresa != null
                        && adminComparendoFL.getProcesaComparendo().getFechaInfraccion().after(empresa.getFechaInicio())
                        && (empresa.getFechaFin() == null || adminComparendoFL.getProcesaComparendo()
                                .getFechaInfraccion().before(empresa.getFechaFin()))) {
                    if (empresa.getPersonaJuridica() != null) {
                        adminComparendoFL.setEmpresaVehiculo(AdminComparendoHelper.toProcesaComparendoPersonaDTO(
                                empresa.getPersonaJuridica(), adminComparendoFL.getEmpresaVehiculo()));
                        adminComparendoFL.setTipoDocEmpresaRequerido(true);
                    }
                }

                /*
                 * Consultar la información existente por la 'Placa vehículo' por medio del caso de uso 'Consultar información de registro de
                 * propietarios' donde la 'Fecha de imposición de comparendo' se encuentre entre en un intervalo 'Fecha inicio de propiedad' y 'Fecha
                 * fin de propiedad',
                 */
                ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO = new ConsultaRegistroVehicularDTO();
                consultaRegistroVehicularDTO.setCodigoOrganismo(
                        adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());
                consultaRegistroVehicularDTO
                        .setFechaConsulta(adminComparendoFL.getProcesaComparendo().getFechaInfraccion());
                consultaRegistroVehicularDTO.setPlaca(adminComparendoFL.getProcesaComparendo().getPlacaVehiculo());
                propietario = regVehicularPropietarioEjb.consultarMayorPropietario(consultaRegistroVehicularDTO);
                if (propietario != null
                        && adminComparendoFL.getProcesaComparendo().getFechaInfraccion()
                                .after(propietario.getFechaInicio())
                        && (propietario.getFechaFin() == null || adminComparendoFL.getProcesaComparendo()
                                .getFechaInfraccion().before(propietario.getFechaFin()))) {
                    adminComparendoFL.setTipoIdJuridicoPropietario(false);
                    if (propietario.getPersona() != null) {
                        adminComparendoFL.setPropietario(AdminComparendoHelper.toProcesaComparendoPersonaDTO(
                                propietario.getPersona(), adminComparendoFL.getPropietario()));
                        adminComparendoFL.setRequierePropietario(true);

                        adminComparendoFL.setTipoIdJuridicoPropietario(
                                adminComparendoFL.getPropietario().getIdTipoIdentificacion()
                                        .equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId()));
                    }

                }
            }
        }

    }

    /**
     * Cuando se cambia el numero de identificacion
     * 
     * @author julio.pinzon
     */
    public void onIdentificacionVehiculoChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onIdentificacionVehiculoChange()");

        adminComparendoFL.setPlacaVehiculoDeshabilitado(false);
        adminComparendoFL.setIdVehiculoDeshabilitado(false);

        if (StringUtils.isNotBlank(adminComparendoFL.getProcesaComparendo().getIdentificacionVehiculo())) {
            adminComparendoFL.setPlacaVehiculoDeshabilitado(true);
            adminComparendoFL.setIdVehiculoDeshabilitado(false);
            adminComparendoFL.getProcesaComparendo().setPlacaVehiculo(null);
        }
    }

    /**
     * Reinicia los datos de la pestania de vehiculos
     * 
     * @author julio.pinzon
     */
    private void reiniciarDatosVehiculo(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::reiniciarDatosVehiculo()");

        // reinicia valores
        adminComparendoFL.getProcesaComparendo().setCodigoOrganismoMatriculaVehiculo(null);
        adminComparendoFL.getProcesaComparendo().setIdClaseVehiculo(null);
        adminComparendoFL.getProcesaComparendo().setIdModalidad(null);
        adminComparendoFL.getProcesaComparendo().setIdRadioAccion(null);
        adminComparendoFL.getProcesaComparendo().setNumeroTarjetaOperacion(null);
        adminComparendoFL.getProcesaComparendo().setIdTipoServicio(null);
        adminComparendoFL.getProcesaComparendo().setIdColor(null);
        adminComparendoFL.getProcesaComparendo().setNumeroLicenciaTransito(null);
        adminComparendoFL.getProcesaComparendo().setIdLineaVehiculo(null);
        adminComparendoFL.getProcesaComparendo().setNumeroMotor(null);
        adminComparendoFL.getProcesaComparendo().setModelo(null);

        adminComparendoFL.getEmpresaVehiculo().setNumeroIdentificacion(null);
        adminComparendoFL.getEmpresaVehiculo().setIdTipoIdentificacion(null);
        adminComparendoFL.getEmpresaVehiculo().setRazonSocial(null);

        adminComparendoFL.setPlacaVehiculoDeshabilitado(false);
        adminComparendoFL.setIdVehiculoDeshabilitado(false);

    }

    /**
     * Cuando se cambia el tipo de documento de la empresa
     * 
     * @author julio.pinzon
     */
    public void onTipoDocumentoEmpresaChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onTipoDocumentoEmpresaChange()");
        if (adminComparendoFL.getEmpresaVehiculo().getIdTipoIdentificacion() != null
                || StringUtils.isNotEmpty(adminComparendoFL.getEmpresaVehiculo().getNumeroIdentificacion())) {
            adminComparendoFL.setTipoDocEmpresaRequerido(true);
        } else {
            adminComparendoFL.setTipoDocEmpresaRequerido(false);
        }
    }

    /**********************
     * Fin Eventos vehiculo
     **********************/

    /**********************
     * Eventos infractor
     **********************/
    /**
     * Cuando se cambia el numero de documento del infractor
     * 
     * @author julio.pinzon
     */
    public void onTipoDocumentoInfractorChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onTipoDocumentoInfractorChange()");
        consultarPersona(adminComparendoFL.getInfractor(),
                adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        adminComparendoFL.setRequiereTipoDocInfractor(false);
        adminComparendoFL.setTipoIdJuridicoInfractor(false);
        if (adminComparendoFL.getInfractor().getIdTipoIdentificacion() != null) {
            adminComparendoFL.setRequiereTipoDocInfractor(true);

            // Si es de tipo juridico para que solo muestre la razon social
            if (adminComparendoFL.getInfractor().getIdTipoIdentificacion()
                    .equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                adminComparendoFL.setTipoIdJuridicoInfractor(true);
            }
        }
    }

    /**
     * Cuando se cambia el tipo de documento del infractor
     * 
     * @author julio.pinzon
     */
    public void onNumDocInfractorChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onNumDocInfractorChange()");
        consultarPersona(adminComparendoFL.getInfractor(),
                adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        adminComparendoFL.setRequiereTipoDocInfractor(false);
        if (StringUtils.isNotEmpty(adminComparendoFL.getInfractor().getNumeroIdentificacion())) {
            adminComparendoFL.setRequiereTipoDocInfractor(true);
        }
    }

    /**
     * Consultar persona
     * 
     * @param personaComparendo
     * @param codigoOrganismo
     * @author julio.pinzon
     */
    private void consultarPersona(ProcesaComparendoPersonaDTO personaComparendo, Integer codigoOrganismo) {
        logger.debug("AdminComparendoMB::consultarPersona(ProcesaComparendoPersonaDTO)");
        if (StringUtils.isNotEmpty(personaComparendo.getNumeroIdentificacion())
                && personaComparendo.getIdTipoIdentificacion() != null) {
            PersonaDTO persona = null;
            try {
                persona = personaEjb.consultarPersonaConPrioridad(codigoOrganismo,
                        personaComparendo.getIdTipoIdentificacion(), personaComparendo.getNumeroIdentificacion());
            } catch (CirculemosNegocioException e) {
                logger.error("Error al consultar persona (" + codigoOrganismo + ","
                        + personaComparendo.getIdTipoIdentificacion() + ","
                        + personaComparendo.getNumeroIdentificacion() + ")");
            }
            if (persona != null) {
                personaComparendo = AdminComparendoHelper.toProcesaComparendoPersonaDTO(persona, personaComparendo);
            }
        }
    }

    /**
     * Permite cambiar los datos de propietario en dado caso de que el infractor sea el mismo propietario.
     */
    public void onEsPropietarioInfractorChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("RectificarComparendoMB::onNumDocInfractorChange()");
        if (adminComparendoFL.isEsPropietario()) {
            adminComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
            adminComparendoFL.getPropietario()
                    .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
            adminComparendoFL.getPropietario()
                    .setIdTipoIdentificacion(adminComparendoFL.getInfractor().getIdTipoIdentificacion());
            adminComparendoFL.getPropietario()
                    .setNumeroIdentificacion(adminComparendoFL.getInfractor().getNumeroIdentificacion());
            adminComparendoFL.getPropietario().setApellido1(adminComparendoFL.getInfractor().getApellido1());
            adminComparendoFL.getPropietario().setApellido2(adminComparendoFL.getInfractor().getApellido2());
            adminComparendoFL.getPropietario().setNombre1(adminComparendoFL.getInfractor().getNombre1());
            adminComparendoFL.getPropietario().setNombre2(adminComparendoFL.getInfractor().getNombre2());
            adminComparendoFL.getPropietario().setRazonSocial(adminComparendoFL.getInfractor().getRazonSocial());
            adminComparendoFL.setRequierePropietario(true);
            adminComparendoFL.setTipoIdJuridicoPropietario(adminComparendoFL.getPropietario().getIdTipoIdentificacion()
                    .equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId()));
        } else {
            ProcesaComparendoPersonaDTO procesaPropietarioDTO = new ProcesaComparendoPersonaDTO();
            procesaPropietarioDTO.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
            ComparendoDTO detalleComparendo = adminComparendoFL.getProcesaComparendo().getComparendo();
            if (detalleComparendo != null) {

                ComparendoPersonaDTO propietario = detalleComparendo.getPropietario();
                ComparendoVehiculoDTO comparendoVehiculo = detalleComparendo.getComparendoVehiculo();
                if (propietario != null) {
                    procesaPropietarioDTO = getProcesaComparendoPersonaDTO(propietario);

                    if (comparendoVehiculo != null) {
                        adminComparendoFL.getProcesaComparendo().setCodigoOrganismoLicenciaTransito(
                                comparendoVehiculo.getOrganismoLicenciaTransito() != null
                                        ? comparendoVehiculo.getOrganismoLicenciaTransito().getCodigoOrganismo()
                                        : null);

                        adminComparendoFL.getProcesaComparendo()
                                .setNumeroLicenciaTransito(comparendoVehiculo.getNumeroLicenciaTransito());
                    }
                }
            }
            // Obligatoriedad
            adminComparendoFL.setRequierePropietario(false);
            adminComparendoFL.setTipoIdJuridicoPropietario(false);
            if (procesaPropietarioDTO.getIdTipoIdentificacion() != null) {
                adminComparendoFL.setRequierePropietario(true);

                // Si es de tipo juridico para que solo muestre la razon social
                if (procesaPropietarioDTO.getIdTipoIdentificacion()
                        .equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                    adminComparendoFL.setTipoIdJuridicoPropietario(true);
                }
            }
            adminComparendoFL.setPropietario(procesaPropietarioDTO);
        }
    }

    /**
     * Transforma un ComparendoPersonaDTO a un ProcesaComparendoPersona
     * 
     * @param comparendoPersona
     *            datos del comparendo persona
     * @return retorna una persona procesa comparendo con sus datos correspondientes.
     * @author luis.forero(mod 2016-01-29)
     */
    private ProcesaComparendoPersonaDTO getProcesaComparendoPersonaDTO(ComparendoPersonaDTO comparendoPersona) {
        ProcesaComparendoPersonaDTO procesaInfractor = new ProcesaComparendoPersonaDTO();
        if (comparendoPersona != null) {
            procesaInfractor.setIdTipoIdentificacion(comparendoPersona.getTipoIdentificacion().getId());
            procesaInfractor.setNumeroIdentificacion(comparendoPersona.getNumeroIdentificacion());
            procesaInfractor.setNombre1(comparendoPersona.getNombre1());
            procesaInfractor.setNombre2(comparendoPersona.getNombre2());
            procesaInfractor.setApellido1(comparendoPersona.getApellido1());
            procesaInfractor.setApellido2(comparendoPersona.getApellido2());
            procesaInfractor.setRazonSocial(comparendoPersona.getRazonSocial());

            procesaInfractor.setEdad(comparendoPersona.getEdad());
            procesaInfractor.setEmail(comparendoPersona.getEmail());
            procesaInfractor.setTelefonoFijo(comparendoPersona.getTelefonoFijo());
            procesaInfractor.setTelefonoMovil(comparendoPersona.getTelefonoMovil());

            // Datos licencia del infractor
            procesaInfractor.setCodigoOrganismoLicencia(comparendoPersona.getOrganismoTransito() != null
                    ? comparendoPersona.getOrganismoTransito().getCodigoOrganismo() : null);
            procesaInfractor.setNumeroLicencia(comparendoPersona.getNumeroLicencia());
            procesaInfractor.setIdCategoriaLicenciaCondu(comparendoPersona.getCategoriaLicencia() != null
                    ? comparendoPersona.getCategoriaLicencia().getId() : null);
            procesaInfractor.setFechaExpedicionLicenCondu(comparendoPersona.getFechaExpedicionLicenCondu());
            procesaInfractor.setFechaVencimientoLicenCondu(comparendoPersona.getFechaVencimientoLicenCondu());
        }
        return procesaInfractor;
    }

    /**********************
     * Fin Eventos infractor
     **********************/

    /**********************
     * Eventos propietario
     **********************/
    /**
     * Cuando se cambia el numero de documento del propietario
     * 
     * @author julio.pinzon
     */
    public void onTipoDocumentoPropietarioChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onTipoDocumentoPropietarioChange()");

        consultarPersona(adminComparendoFL.getPropietario(),
                adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        adminComparendoFL.setRequierePropietario(false);
        adminComparendoFL.setTipoIdJuridicoPropietario(false);
        if (adminComparendoFL.getPropietario().getIdTipoIdentificacion() != null) {
            adminComparendoFL.setRequierePropietario(true);

            // Si es de tipo juridico para que solo muestre la razon social
            if (adminComparendoFL.getPropietario().getIdTipoIdentificacion()
                    .equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                adminComparendoFL.setTipoIdJuridicoPropietario(true);
            }
        }
    }

    /**
     * Cuando se cambia el tipo de documento del propietario
     */
    public void onNumDocPropietarioChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onNumDocPropietarioChange()");
        consultarPersona(adminComparendoFL.getPropietario(),
                adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        adminComparendoFL.setRequierePropietario(false);
        if (StringUtils.isNotEmpty(adminComparendoFL.getPropietario().getNumeroIdentificacion())) {
            adminComparendoFL.setRequierePropietario(true);
        }
    }

    /**********************
     * Fin Eventos propietario
     **********************/

    /**********************
     * Eventos agente_transito
     **********************/
    /**
     * Cuando se cambia el numero de documento del agente transito
     */
    public void onTipoDocumentoAgenteTransitoChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onTipoDocumentoAgenteTransitoChange()");
        // reinicia datos
        reiniciarDatosAgente(adminComparendoFL, false);

        // Consulta datos agente
        consultarAgente(adminComparendoFL);
    }

    /**
     * Cuando se cambia el tipo de documento del agente transito
     */
    public void onNumDocAgenteTransitoChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onNumDocAgenteTransitoChange()");
        // reinicia datos
        reiniciarDatosAgente(adminComparendoFL, false);

        // Consulta datos agente
        consultarAgente(adminComparendoFL);
    }

    /**
     * Cuando se cambia la placa del agente transito
     */
    public void onPlacaAgenteChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onPlacaAgenteChange()");

        // reinicia datos
        reiniciarDatosAgente(adminComparendoFL, true);

        consultarAgente(adminComparendoFL);
    }

    /**
     * Consulta el agente
     * 
     * @param adminComparendoFL
     */
    private void consultarAgente(AdminComparendoFL adminComparendoFL) {
        if (StringUtils.isNotEmpty(adminComparendoFL.getProcesaComparendo().getPlacaAgente())
                || (StringUtils.isNotEmpty(adminComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente())
                        && adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() != null)) {

            AgenteDTO agente = new AgenteDTO();
            if (StringUtils.isNotEmpty(adminComparendoFL.getProcesaComparendo().getPlacaAgente())) {
                agente.setPlaca(adminComparendoFL.getProcesaComparendo().getPlacaAgente());
            }

            if (StringUtils.isNotEmpty(adminComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente())
                    && adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() != null) {
                // Consultar Persona
                PersonaDTO personaAgente = new PersonaDTO();
                personaAgente.setNumeroIdentificacion(
                        adminComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente());
                personaAgente.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
                personaAgente.getTipoIdentificacion()
                        .setId(adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente());
                agente.setPersona(personaAgente);
            }
            // Consulta agente
            agente = agenteEjb.consultarAgente(agente).get(0);

            // donde la 'Fecha de imposición comparendo' se encuentre entre la 'Fecha inicial de vigencia' y la 'fecha final de vigencia' del agente a
            // consultar y proponerla en los campos que se listan en esta sección ('AGENTE DE TRÁNSITO'). (FA9)
            if (agente == null || (adminComparendoFL.getProcesaComparendo().getFechaInfraccion()
                    .before(agente.getFechaInicioVigencia())
                    || (agente.getFechaFinVigencia() != null && adminComparendoFL.getProcesaComparendo()
                            .getFechaInfraccion().after(agente.getFechaFinVigencia())))) {
                if (!adminComparendoFL.isPolca()) {
                    // FA9
                    addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_debe_existir_agente");
                }
            } else {
                // Datos del agente
                adminComparendoFL.getProcesaComparendo().setIdAgenteTransito(agente.getId());
                adminComparendoFL.getProcesaComparendo()
                        .setIdTipoIdentificacionAgente(agente.getPersona().getTipoIdentificacion().getId());
                adminComparendoFL.getProcesaComparendo()
                        .setNumeroIdentificacionAgente(agente.getPersona().getNumeroIdentificacion());
                adminComparendoFL.getProcesaComparendo().setNombre1Agente(agente.getPersona().getNombre1());
                adminComparendoFL.getProcesaComparendo().setNombre2Agente(agente.getPersona().getNombre2());
                adminComparendoFL.getProcesaComparendo().setApellido1Agente(agente.getPersona().getApellido1());
                adminComparendoFL.getProcesaComparendo().setApellido2Agente(agente.getPersona().getApellido2());
                adminComparendoFL.getProcesaComparendo().setPlacaAgente(agente.getPlaca());
            }
        }
    }

    /**
     * Reinicia los datos de la pestania de vehiculos
     * 
     * @param adminComparendoFL
     *            identifica el objeto sobre el cual se lleva a cabo el cambio de los datos para reiniciar sus respectiva reinicializacion
     * @param placaChange
     *            identifica si fue o no la placa que cambio en la interfaz
     * @author julio.pinzon<br>
     *         luis.forero(mod 2016-03-22)
     */
    private void reiniciarDatosAgente(AdminComparendoFL adminComparendoFL, boolean placaChange) {
        logger.debug("AdminComparendoMB::reiniciarDatosVehiculo()");

        // reinicia valores
        adminComparendoFL.getProcesaComparendo().setIdAgenteTransito(null);
        adminComparendoFL.getProcesaComparendo().setNombre1Agente(null);
        adminComparendoFL.getProcesaComparendo().setNombre2Agente(null);
        adminComparendoFL.getProcesaComparendo().setApellido1Agente(null);
        adminComparendoFL.getProcesaComparendo().setApellido2Agente(null);

        if (placaChange) {
            adminComparendoFL.getProcesaComparendo().setIdTipoIdentificacionAgente(null);
            adminComparendoFL.getProcesaComparendo().setNumeroComparendo(null);
        } else {
            adminComparendoFL.getProcesaComparendo().setPlacaAgente(null);
        }

        adminComparendoFL.setPlacaAgenteRequerida(false);
        adminComparendoFL.setRequiereAgenteTransito(false);

        if (StringUtils.isNotEmpty(adminComparendoFL.getProcesaComparendo().getPlacaAgente())) {
            adminComparendoFL.setPlacaAgenteRequerida(true);
            adminComparendoFL.setRequiereAgenteTransito(false);
        } else if (StringUtils.isNotEmpty(adminComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente())
                || adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() != null) {
            adminComparendoFL.setPlacaAgenteRequerida(false);
            adminComparendoFL.setRequiereAgenteTransito(true);
        }
    }

    /**********************
     * Fin Eventos agente_transito
     **********************/

    /**********************
     * Eventos testigo
     **********************/
    /**
     * Cuando se cambia el numero de documento del testigo
     * 
     * @author julio.pinzon
     */
    public void onTipoDocumentoTestigoChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onTipoDocumentoTestigoChange()");

        adminComparendoFL.getTestigo().setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO.getValue());
        consultarPersona(adminComparendoFL.getTestigo(),
                adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        adminComparendoFL.setRequiereTestigo(false);
        if (adminComparendoFL.getTestigo().getIdTipoIdentificacion() != null) {
            adminComparendoFL.setRequiereTestigo(true);
        }
    }

    /**
     * Cuando se cambia el tipo de documento del testigo
     * 
     * @author julio.pinzon
     */
    public void onNumDocTestigoChange(AdminComparendoFL adminComparendoFL) {
        logger.debug("AdminComparendoMB::onNumDocTestigoChange()");

        adminComparendoFL.getTestigo().setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO.getValue());
        consultarPersona(adminComparendoFL.getTestigo(),
                adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo());

        adminComparendoFL.setRequiereTestigo(false);
        if (StringUtils.isNotEmpty(adminComparendoFL.getTestigo().getNumeroIdentificacion())) {
            adminComparendoFL.setRequiereTestigo(true);
        }
    }

    /**********************
     * Fin Eventos testigo
     **********************/
    /**
     * Evento cuando cambia la pestaña en el wizard de registro
     * 
     * @param event
     * @return
     * @author julio.pinzon
     */
    public String onFlowProcess(FlowEvent event) {
        logger.debug("AdminComparendoMB::onFlowProcess()");
        AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class, AdminComparendoFL.NOMBRE_BEAN);
        boolean errorValidacion = false;
        if (event.getOldStep().equals(PASO_AGENTE) && event.getNewStep().equals(PASO_OBSERVACIONES)) {
            // Valida campos del agente
            ProcesaComparendoPersonaDTO agente = new ProcesaComparendoPersonaDTO();
            agente.setApellido1(adminComparendoFL.getProcesaComparendo().getApellido1Agente());
            agente.setNombre1(adminComparendoFL.getProcesaComparendo().getNombre1Agente());
            agente.setNumeroIdentificacion(adminComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente());
            agente.setIdTipoIdentificacion(adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente());
            errorValidacion = validarPersona(agente, true, true, SUFIJO_AGENTE);

            if (StringUtils.isEmpty(adminComparendoFL.getProcesaComparendo().getPlacaAgente())
                    && (StringUtils.isEmpty(adminComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente())
                            || adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() == null)) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_debe_digitar_placa_o_id");
                errorValidacion = true;
            } else if (!adminComparendoFL.isPolca()
                    && StringUtils.isEmpty(adminComparendoFL.getProcesaComparendo().getNombre1Agente())) {
                // Valida que haya sido encontrado el agente verificando que el primer nombre no esta vacio
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_debe_existir_agente");
                errorValidacion = true;
            }
        } else if (event.getOldStep().equals(PASO_INFRACTOR) && event.getNewStep().equals(PASO_PROPIETARIO)) {
            errorValidacion = validarPersona(adminComparendoFL.getInfractor(), adminComparendoFL.isRequiereInfractor(),
                    adminComparendoFL.isRequiereTipoDocInfractor(), SUFIJO_INFRACTOR);

            if (!errorValidacion && adminComparendoFL.isEsPropietario()) {
                adminComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
                adminComparendoFL.getPropietario()
                        .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
                adminComparendoFL.getPropietario()
                        .setIdTipoIdentificacion(adminComparendoFL.getInfractor().getIdTipoIdentificacion());
                adminComparendoFL.getPropietario()
                        .setNumeroIdentificacion(adminComparendoFL.getInfractor().getNumeroIdentificacion());
                adminComparendoFL.getPropietario().setApellido1(adminComparendoFL.getInfractor().getApellido1());
                adminComparendoFL.getPropietario().setApellido2(adminComparendoFL.getInfractor().getApellido2());
                adminComparendoFL.getPropietario().setNombre1(adminComparendoFL.getInfractor().getNombre1());
                adminComparendoFL.getPropietario().setNombre2(adminComparendoFL.getInfractor().getNombre2());
                adminComparendoFL.getPropietario().setRazonSocial(adminComparendoFL.getInfractor().getRazonSocial());
                adminComparendoFL.setRequierePropietario(true);
                if (adminComparendoFL.getPropietario().getIdTipoIdentificacion() != null) {
                    if (adminComparendoFL.getPropietario().getIdTipoIdentificacion()
                            .equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId())) {
                        adminComparendoFL.setTipoIdJuridicoPropietario(true);
                    } else {
                        adminComparendoFL.setTipoIdJuridicoPropietario(false);
                    }
                } else {
                    adminComparendoFL.setTipoIdJuridicoPropietario(false);
                }
            } else {
                adminComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
                adminComparendoFL.getPropietario()
                        .setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
                if (propietario != null
                        && adminComparendoFL.getProcesaComparendo().getFechaInfraccion()
                                .after(propietario.getFechaInicio())
                        && (propietario.getFechaFin() == null || adminComparendoFL.getProcesaComparendo()
                                .getFechaInfraccion().before(propietario.getFechaFin()))) {
                    adminComparendoFL.setTipoIdJuridicoPropietario(false);
                    if (propietario.getPersona() != null) {
                        adminComparendoFL.setPropietario(AdminComparendoHelper.toProcesaComparendoPersonaDTO(
                                propietario.getPersona(), adminComparendoFL.getPropietario()));
                        adminComparendoFL.setRequierePropietario(true);

                        adminComparendoFL.setTipoIdJuridicoPropietario(
                                adminComparendoFL.getPropietario().getIdTipoIdentificacion()
                                        .equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId()));
                    }
                }
            }
        } else if (event.getOldStep().equals(PASO_PROPIETARIO) && event.getNewStep().equals(PASO_AGENTE)) {
            errorValidacion = validarPersona(adminComparendoFL.getPropietario(),
                    adminComparendoFL.isRequierePropietario(), adminComparendoFL.isRequierePropietario(),
                    SUFIJO_PROPIETARIO);
        } else if (event.getOldStep().equals(PASO_TESTIGO) && event.getNewStep().equals(PASO_EMBRIAGUEZ)) {
            errorValidacion = validarPersona(adminComparendoFL.getTestigo(), adminComparendoFL.isRequiereTestigo(),
                    adminComparendoFL.isRequiereTestigo(), SUFIJO_TESTIGO);
        } else if (event.getOldStep().equals(PASO_VEHICULO) && event.getNewStep().equals(PASO_INFRACTOR)) {
            errorValidacion = validarPersona(adminComparendoFL.getEmpresaVehiculo(),
                    adminComparendoFL.isTipoDocEmpresaRequerido(), adminComparendoFL.isTipoDocEmpresaRequerido(), "")
                    || errorValidacion;
            if (adminComparendoFL.isPlacaRequerida()
                    && StringUtils.isEmpty(adminComparendoFL.getProcesaComparendo().getPlacaVehiculo())
                    && StringUtils.isEmpty(adminComparendoFL.getProcesaComparendo().getIdentificacionVehiculo())) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_debe_digitar_placa_o_id_vehiculo");
                errorValidacion = true;
            }
        } else if (event.getOldStep().equals(PASO_EMBRIAGUEZ) && event.getNewStep().equals(PASO_EVIDENCIAS)) {
            try {
                if (adminComparendoFL.getProcesaComparendo().getGradoAlcoholemia() != null
                        && adminComparendoFL.getProcesaComparendo().getValorGradoAlcoholemia() != null) {

                    // Consultar configuracion rango milimetros etanol asociado a grado alcoholemia
                    ConfiguracionGradosAlcoholDTO cfgGradosAlcoholDTO = new ConfiguracionGradosAlcoholDTO();
                    cfgGradosAlcoholDTO.setCodigoOrganismo(new ArrayList<String>());
                    cfgGradosAlcoholDTO.getCodigoOrganismo().add(adminComparendoFL.getProcesaComparendo()
                            .getOrganismoTransito().getCodigoOrganismo().toString());
                    cfgGradosAlcoholDTO.setFinPeriodoFecha(null);
                    cfgGradosAlcoholDTO.setGradoAlcohol(
                            new BigDecimal(adminComparendoFL.getProcesaComparendo().getGradoAlcoholemia()));
                    cfgGradosAlcoholDTO
                            .setInicioPeriodoFecha(adminComparendoFL.getProcesaComparendo().getFechaInfraccion());

                    cfgGradosAlcoholDTO = configuracionEjb.consultarDatoConfiguracion(
                            EnumConfiguracion.CONFIG_GRADOS_ALCOHOL.getCodigo(), cfgGradosAlcoholDTO);

                    if (!(adminComparendoFL.getProcesaComparendo().getValorGradoAlcoholemia()
                            .compareTo(cfgGradosAlcoholDTO.getMinimo().intValue()) >= 0
                            && (cfgGradosAlcoholDTO.getMaximo() == null
                                    || adminComparendoFL.getProcesaComparendo().getValorGradoAlcoholemia()
                                            .compareTo(cfgGradosAlcoholDTO.getMaximo().intValue()) <= 0))) {
                        addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_grado_alcoholemia");
                        errorValidacion = true;
                    }
                }
                // Valida que la fecha de prueba de alcoholemia no sea mayor a la actual ni menor a la de imposicion
                if (adminComparendoFL.getProcesaComparendo().getFechaPruebaAlcoholemia() != null && (adminComparendoFL
                        .getProcesaComparendo().getFechaPruebaAlcoholemia()
                        .compareTo(UtilFecha.resetTime(adminComparendoFL.getProcesaComparendo().getFechaRecepcion())
                                .getTime()) > 0
                        || adminComparendoFL.getProcesaComparendo().getFechaPruebaAlcoholemia()
                                .compareTo(UtilFecha
                                        .resetTime(adminComparendoFL.getProcesaComparendo().getFechaInfraccion())
                                        .getTime()) < 0)) {
                    getFacesContext().addMessage("form-ingreso-detalle-comparendo:fechaPrueba",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                    getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO)
                                            .getString("msg_error_valida_fecha_prueba_menor_actual")));
                    errorValidacion = true;
                }
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
                errorValidacion = true;
            }
        }
        if (errorValidacion) {
            adminComparendoFL.setPasoActual(event.getOldStep());
            return event.getOldStep();
        } else {
            adminComparendoFL.setPasoActual(event.getNewStep());
            return event.getNewStep();
        }
    }

    /**
     * Valida las personas y datos obligatorios en cada paso del wizard
     * 
     * @param requierePersona
     * @param requiereTipoDocPersona
     * @return
     * @author julio.pinzon
     */
    private boolean validarPersona(ProcesaComparendoPersonaDTO persona, boolean requierePersona,
            boolean requiereTipoDocPersona, String idCampo) {
        AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class, AdminComparendoFL.NOMBRE_BEAN);
        boolean errorValidacion = false;
        if (persona.getIdTipoIdentificacion() == null && (requierePersona || requiereTipoDocPersona)) {
            getFacesContext().addMessage("form-ingreso-detalle-comparendo:selTipoDocumento" + idCampo,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }

        if (StringUtils.isEmpty(persona.getNumeroIdentificacion()) && (requierePersona || requiereTipoDocPersona)) {
            getFacesContext().addMessage("form-ingreso-detalle-comparendo:numeroDocumento" + idCampo,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }

        if (persona.getIdTipoIdentificacion() != null
                && adminComparendoFL.getTipoIdentificacionEmpresa().getId().equals(persona.getIdTipoIdentificacion())) {
            if (StringUtils.isEmpty(persona.getRazonSocial()) && (requierePersona || requiereTipoDocPersona)) {
                getFacesContext().addMessage("form-ingreso-detalle-comparendo:nombreEmpresa" + idCampo,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                errorValidacion = true;
            }
        } else {
            if (StringUtils.isEmpty(persona.getApellido1()) && (requierePersona || requiereTipoDocPersona)) {
                getFacesContext().addMessage("form-ingreso-detalle-comparendo:primerApellido" + idCampo,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                errorValidacion = true;
            }

            if (StringUtils.isEmpty(persona.getNombre1()) && (requierePersona || requiereTipoDocPersona)) {
                getFacesContext().addMessage("form-ingreso-detalle-comparendo:primerNombre" + idCampo,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                        CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                errorValidacion = true;
            }
        }

        // Si no hay error en la validacion
        if (!errorValidacion && persona.getIdTipoIdentificacion() != null
                && StringUtils.isNotEmpty(persona.getNumeroIdentificacion())) {

            try {
                if (!administracionEjb.validarNumeroDocumento(persona.getNumeroIdentificacion(),
                        persona.getIdTipoIdentificacion(),
                        adminComparendoFL.getProcesaComparendo().getFechaInfraccion())) {
                    getFacesContext().addMessage("form-ingreso-detalle-comparendo:numeroDocumento" + idCampo,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                                    getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO)
                                            .getString("msg_error_valida_numero_identificacion")));
                    errorValidacion = true;
                }
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
                errorValidacion = true;
            }
        }
        return errorValidacion;
    }

    /**
     * Permite cargar un archivo al listado de anexos de la solicitud
     * 
     * @param event
     * @author luis.forero(2015-10-14)
     */
    public void cargaArchivoEvidencia(FileUploadEvent event) {
        logger.debug(AdminComparendoMB.class.getName().concat("::cargaArchivoEvidencia()"));
        final UploadedFile file = event.getFile();

        try {
            if (FileValidator.checkFileType(file.getInputstream(), file.getFileName(), ".tiff", ".pdf", ".png",
                    ".jpg")) {

                final ProcesaEvidenciaDTO procesaEvidenciaDTO = new ProcesaEvidenciaDTO();
                final AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class,
                        AdminComparendoFL.NOMBRE_BEAN);

                Integer idTipoEvidencia = adminComparendoFL.getIdTipoEvidencia();
                if (idTipoEvidencia == null) {
                    getFacesContext().addMessage("form-ingreso-detalle-comparendo:selTipoEvidencia", new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                    return;
                }
                procesaEvidenciaDTO.setCodigoTipoEvidencia(idTipoEvidencia);
                procesaEvidenciaDTO.setNombreTipoEvidencia(
                        getCatalogosApp().getItemCatalogo(EnumCatalogo.TipoEvidencia, idTipoEvidencia).getLabel());

                List<ProcesaEvidenciaDTO> lstAnexoSolicitud = adminComparendoFL.getProcesaEvidencias();
                if (lstAnexoSolicitud == null) {
                    lstAnexoSolicitud = new ArrayList<>();
                }

                final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(),
                        file.getContents());

                procesaEvidenciaDTO.setArchivoTransportable(archivo);
                lstAnexoSolicitud.add(procesaEvidenciaDTO);

                adminComparendoFL.setProcesaEvidencias(lstAnexoSolicitud);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_dato");
            }
        } catch (IOException e) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_dato");
        }

    }

    // ---------- CONSULTAR COMPARENDOS - CARGAR EVIDENCIAS

    public void cargarArchivosEvidencias() {
        final DetalleComparendoFL detalleComparendoFL = findFlowObject(DetalleComparendoFL.class,
                DetalleComparendoFL.NOMBRE_BEAN);

        for (EvidenciaDTO evidenciaDTO : detalleComparendoFL.getEvidencias()) {
            try {
                ArchivoTransportableDTO archivo = repositorioArchivoEJB
                        .consultarDocumento(evidenciaDTO.getIdDocumento());
                evidenciaDTO.setArchivoTransportable(archivo);
            } catch (CirculemosAlertaException e) {
                CirculemosErrorHandler.handleException(e);
            }
        }
    }

    public void cargarEvidencia(FileUploadEvent event) {
        logger.debug(AdminComparendoMB.class.getName().concat("::cargaEvidencia()"));

        final UploadedFile file = event.getFile();
        final EvidenciaDTO evidenciaDTO = new EvidenciaDTO();
        final DetalleComparendoFL detalleComparendoFL = findFlowObject(DetalleComparendoFL.class,
                DetalleComparendoFL.NOMBRE_BEAN);
        final AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class,
                AdminComparendoFL.NOMBRE_BEAN);

        Integer idTipoEvidencia = adminComparendoFL.getIdTipoEvidencia();
        if (idTipoEvidencia == null) {
            getFacesContext().addMessage("form-ingreso:selTipoEvidencia",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            getBundle(CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }

        evidenciaDTO.setTipoEvidencia(new TipoEvidenciaDTO());
        evidenciaDTO.getTipoEvidencia().setId(idTipoEvidencia);
        evidenciaDTO.getTipoEvidencia()
                .setNombre(getCatalogosApp().getItemCatalogo(EnumCatalogo.TipoEvidencia, idTipoEvidencia).getLabel());

        try {
            final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(), file.getContents());
            evidenciaDTO.setArchivoTransportable(archivo);

            String nombreArchivo = comparendoEjb.obtenerNombreEvidencia(evidenciaDTO);
            evidenciaDTO.getArchivoTransportable().setNombre(nombreArchivo);
            evidenciaDTO.setId(nombreArchivo.hashCode());

            List<EvidenciaDTO> lstAnexoSolicitud = detalleComparendoFL.getEvidencias();
            if (lstAnexoSolicitud == null)
                lstAnexoSolicitud = new ArrayList<>();

            lstAnexoSolicitud.add(evidenciaDTO);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public void eliminarEvidencia(EvidenciaDTO evidencia) {
        final DetalleComparendoFL detalleComparendoFL = findFlowObject(DetalleComparendoFL.class,
                DetalleComparendoFL.NOMBRE_BEAN);
        final AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class,
                AdminComparendoFL.NOMBRE_BEAN);

        detalleComparendoFL.getEvidencias().remove(evidencia);
        adminComparendoFL.getIdEvidenciasEliminadas().add(evidencia.getId());
    }

    public void registrarEvidencias() {
        final DetalleComparendoFL detalleComparendoFL = findFlowObject(DetalleComparendoFL.class,
                DetalleComparendoFL.NOMBRE_BEAN);
        final AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class,
                AdminComparendoFL.NOMBRE_BEAN);
        final AdminComparendoHolderFL adminComparendoHolderFL = findFlowObject(AdminComparendoHolderFL.class,
                AdminComparendoHolderFL.NOMBRE_BEAN);

        try {
            comparendoEjb.eliminarEvidenciasComparendo(detalleComparendoFL.getComparendo().getCicomparendo(),
                    adminComparendoFL.getIdEvidenciasEliminadas());
            comparendoEjb.registrarEvidencias(detalleComparendoFL.getComparendo().getCicomparendo(),
                    detalleComparendoFL.getEvidencias());

            limpiarCargueEvidencias();
            adminComparendoHolderFL.setComparendoSeleccionado(null);

            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public void limpiarCargueEvidencias() {
        final AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class,
                AdminComparendoFL.NOMBRE_BEAN);

        adminComparendoFL.getIdEvidenciasEliminadas().clear();
        adminComparendoFL.setIdTipoEvidencia(null);
    }

    public StreamedContent descargarDocumentoEvidencia(EvidenciaDTO evidenciaDTO) {
        descargarDocumento(evidenciaDTO);
        return streamedContent;
    }

    // ---------- CONSULTAR COMPARENDOS - CARGAR EVIDENCIAS

    /**
     * Permite eliminar un documento de las evidencias.
     * 
     * @author luis.forero(2015-10-14)
     * 
     */
    public void eliminarListaDocumentos() {
        final AdminComparendoFL adminComparendoFL = findFlowObject(AdminComparendoFL.class,
                AdminComparendoFL.NOMBRE_BEAN);
        adminComparendoFL.getProcesaEvidencias().remove(adminComparendoFL.getEvidenciaSeleccionada());
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public void setTamanioMaximoArch(int tamanioMaximoArch) {
        this.tamanioMaximoArch = tamanioMaximoArch;
    }

    public String getTiposArchivosPermitidos() {
        return TIPOS_ARCHIVOS_PERMITIDOS;
    }

    public String getMesErrLimitSize() {
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString("msg_error_tamanio_archivo"),
                tamanioMaximoArch);
    }

    /**********************
     * Detalle Comparendo
     **********************/

    /**
     * Lleva a cabo la consulta del detalle de un comparendo seleccionado desde la interfaz de consulta
     * 
     * @return true si encuentra el detalle del comparendo, false de lo contrario.
     * @author luis.forero(2015-10-19)
     */
    public boolean consultarDetalleComparendo(Long cicomparendo) {
        if (cicomparendo != null) {

            final DetalleComparendoFL detalleComparendoFL = findFlowObject(DetalleComparendoFL.class,
                    DetalleComparendoFL.NOMBRE_BEAN);

            // Informacion seguimiento
            try {
                detalleComparendoFL
                        .setSeguimientoComparendoDTO(comparendoEjb.consultarSeguimientoComparendo(cicomparendo));
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            }

            // Informacion comparendo
            ComparendoDTO detalleComparendo = comparendoEjb.consultarComparendo(cicomparendo);

            if (detalleComparendo != null) {
                // ENCABEZADO
                detalleComparendoFL.setComparendo(detalleComparendo);
                detalleComparendoFL.setConfiguracionInfraccion(
                        detalleComparendo.getInfraccion().getConfiguracionInfraccionList().get(0));

                String nomOrganismoTransito = administracionEjb.consultarOrganismoTransito(
                        detalleComparendo.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo())
                        .getNombreOrganismo();
                detalleComparendoFL.setNomOrganismoTransito(nomOrganismoTransito);

                detalleComparendoFL.setDescripcionInfraccion(
                        detalleComparendo.getInfraccion().getConfiguracionInfraccionList().get(0).getDescripcion());

                detalleComparendoFL.setFechaHoraInfraccion(UtilFecha
                        .setHoraFecha(detalleComparendo.getFechaInfraccion(), detalleComparendo.getHoraInfraccion()));

                DireccionDTO direccionInfraccion = detalleComparendo.getDireccion();
                detalleComparendoFL.setDireccionInfraccionTexto(
                        direccionInfraccion.toString() + " " + direccionInfraccion.getComplemento());
                // VEHICULO

                detalleComparendoFL.setEmpresaVehiculo(detalleComparendo.getComparendoVehiculo());

                detalleComparendoFL.setEmpresaPersonaVehiculo(detalleComparendo.getEmpresa());

                // INFRACTOR
                ComparendoPersonaDTO infractor = detalleComparendo.getInfractor();
                detalleComparendoFL.setInfractor(infractor);

                if (infractor != null) {
                    DireccionDTO dirInfractor = detalleComparendo.getInfractor().getDireccion();

                    if (dirInfractor != null) {
                        detalleComparendoFL.setDireccionInfractorTexto(dirInfractor.toString());
                    }

                    if (detalleComparendo.getInfractor().getOrganismoTransito() != null) {
                        detalleComparendoFL.setNomOrgLicenciaInfractor(administracionEjb
                                .consultarOrganismoTransito(
                                        detalleComparendo.getInfractor().getOrganismoTransito().getCodigoOrganismo())
                                .getNombreOrganismo());
                    }
                }

                // PROPIETARIO
                if (detalleComparendo.getPropietario() != null) {
                    detalleComparendoFL.setPropietario(detalleComparendo.getPropietario());

                    if (detalleComparendo.getComparendoVehiculo() != null) {
                        if (detalleComparendo.getComparendoVehiculo().getOrganismoLicenciaTransito() != null) {
                            detalleComparendoFL
                                    .setNomOrgLicenciaTrans(administracionEjb
                                            .consultarOrganismoTransito(detalleComparendo.getComparendoVehiculo()
                                                    .getOrganismoLicenciaTransito().getCodigoOrganismo())
                                            .getNombreOrganismo());
                        }
                    }
                }

                // AGENTE DE TRANSITO
                ComparendoAgenteDTO comparendoAgente = detalleComparendo.getComparendoAgente();
                detalleComparendoFL.setAgente(comparendoAgente);

                if (comparendoAgente != null) {
                    if (detalleComparendo.getTipoAgenteImpositorDTO().getId()
                            .equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
                        detalleComparendoFL.setEntidadAgenteTransito(comparendoAgente.getNombreResponsable());
                    } else {
                        if (comparendoAgente.getUnificacionResponsable() != null) {
                            UnificacionResponsableDTO unificacionResponsable = comparendoAgente
                                    .getUnificacionResponsable();
                            detalleComparendoFL.setEntidadAgenteTransito(unificacionResponsable.getNombre());
                        }
                    }
                }

                // INMOVILIZACION
                ComparendoPatioDTO comparendoPatio = detalleComparendo.getComparendoPatio();
                if (comparendoPatio != null) {
                    detalleComparendoFL.setComparendoPatio(comparendoPatio);

                    DireccionDTO dirPatioInmovili = comparendoPatio.getDireccion();
                    if (dirPatioInmovili != null) {
                        detalleComparendoFL.setDireccionInmovilizacionTexto(dirPatioInmovili.toString());
                    }
                }

                // TESTIGO
                ComparendoPersonaDTO testigo = detalleComparendo.getTestigo();

                if (testigo != null) {
                    detalleComparendoFL.setTestigo(testigo);

                    DireccionDTO dirTestigo = testigo.getDireccion();
                    if (dirTestigo != null)
                        detalleComparendoFL.setDireccionTestigoTexto(dirTestigo.toString());
                }

                // EVIDENCIAS
                images.clear();
                detalleComparendoFL.setEvidencias(detalleComparendo.getEvidenciaList());
                if (detalleComparendo.getEvidenciaList() != null) {
                    for (EvidenciaDTO evidencia : detalleComparendo.getEvidenciaList()) {
                        images.add(rutaImagenes + evidencia.getUrl());
                    }
                }
                return true;
            }

        }

        addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_detalle_no_encontrado");
        return false;
    }

    /**
     * Permite cargar el documento asociado al detalle cambio estado del modulo del repositorio de archivos.
     * 
     * @param evidenciaDTO
     *            detalle cambio estado con el id del documento a ser cargado.
     * 
     * @author luis.forero(2015-10-21)
     * @author mod julio.pinzon(2016-03-14)
     * @author mod divier.casas(2016-09-20)
     */
    public StreamedContent descargarDocumento(EvidenciaDTO evidenciaDTO) {
        logger.debug(ConsultarSeguimientoFormularioMB.class.getName().concat("::descargarDocumento()"));
        StreamedContent streamedContent = null;
        ArchivoTransportableDTO archivoTransportableDTO = new ArchivoTransportableDTO();
        try {

            String url = parametroEjb.consultarValorParametro(EnumParametro.URL_EVIDENCIAS,
                    getCodigoOrganismoTransito());

            // Path pathServer = Paths
            // .get(System.getProperty(ConstantesGestionDocumentos.RUTA_ARCHIVOS_CIRCULEMOS_FILE_SYSTEM));
            Path pathServer = Paths.get(url);
            Path path = pathServer
                    .resolve(evidenciaDTO.getUrl().substring(0, evidenciaDTO.getUrl().lastIndexOf("/") + 1));
            Path pathArchivo = path.resolve(evidenciaDTO.getNombreEvidencia());
            byte[] archivo = Files.readAllBytes(pathArchivo);

            archivoTransportableDTO.setContenido(archivo);
            archivoTransportableDTO.setNombre(evidenciaDTO.getNombreEvidencia());
            streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                    archivoTransportableDTO.getNombre());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return streamedContent;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    /**********************
     * Fin Detalle Comparendo
     **********************/

    /****************************
     * SEGUIMIENTO DEL COMPARENDO
     ****************************/

    /**
     * Permite identificar el evento de cambio de un tab.
     * 
     * @param event
     *            evento de cambio de Tab.
     * @author luis.forero(2016-02-25)
     */
    public void seguimientoComparendo() {
        logger.debug(AdminComparendoMB.class.getName().concat("::seguimientoComparendo()"));

        AdminComparendoHolderFL adminComparendoHolderFL = findFlowObject(AdminComparendoEcuadorHolderFL.class,
                AdminComparendoEcuadorHolderFL.NOMBRE_BEAN);
        if (adminComparendoHolderFL == null) {
            adminComparendoHolderFL = findFlowObject(AdminComparendoEcuadorHolderFL.class,
                    AdminComparendoEcuadorHolderFL.NOMBRE_BEAN);
        }
        Long cicomparendo = adminComparendoHolderFL.getComparendoSeleccionado().getCicomparendo();

        if (adminComparendoHolderFL.getNumComparendo() != null) {
            consultarDetalleComparendo(adminComparendoHolderFL.getNumComparendo());
            consultarResolucionesComparendo(adminComparendoHolderFL.getNumComparendo());
        } else {
            consultarDetalleComparendo(cicomparendo);
            consultarResolucionesComparendo(cicomparendo);
        }

    }

    /**
     * Consulta resoluciones del comparendo
     * 
     * @param cicomparendo
     *            identificador del comparendo
     * @author luis.forero(2016-03-16)
     */
    private void consultarResolucionesComparendo(Long cicomparendo) {
        logger.debug(AdminComparendoMB.class.getName().concat("::consultarResolucionesComparendo(Long)"));
        ComparendoResolucionDTO comparendoResolucion = new ComparendoResolucionDTO();
        comparendoResolucion.setComparendo(new ComparendoDTO(cicomparendo));
        comparendoResolucion.setEstadoResolucion(new EstadoResolucionDTO(EnumEstadoResolucion.VIGENTE.getValue()));

        List<ComparendoResolucionDTO> consultarResoluciones = administracionComparendoEJB
                .consultarResoluciones(comparendoResolucion);

        ResolucionesComparendoFL resolucionesComparendoFL = findFlowObject(ResolucionesComparendoFL.class,
                ResolucionesComparendoFL.NOMBRE_FL);
        resolucionesComparendoFL.setLstComparendoResolucion(consultarResoluciones);

        if (resolucionesComparendoFL.getLstComparendoResolucion().isEmpty()) {
            addInfoMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_info_no_tiene_resoluciones");
        }
    }

    /**
     * Permite llevar a cabo la carga de los datos de una resolucion
     * 
     * @author luis.forero(2016-03-17)
     */
    public void visualizarResolucion() {
        logger.debug(AdminComparendoMB.class.getName().concat("::visualizarResolucion()"));
        ResolucionesComparendoFL resolucionesComparendoFL = findFlowObject(ResolucionesComparendoFL.class,
                ResolucionesComparendoFL.NOMBRE_FL);
        try {
            byte[] resolucion = documentosCirculemosEJB.consultarDocumentosPDF(
                    Arrays.asList(resolucionesComparendoFL.getResolucionSeleccionada().getIdDocumento()));
            if (resolucion != null) {
                streamedContent = new ByteArrayContent(resolucion, null, "nombre.pdf");
                resolucionesComparendoFL.setStreamedContent(streamedContent);
                resolucionesComparendoFL.setMostrarDocResolucion(true);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_resolucion_inexistente");
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Limpia el documento de la resolucion cargado
     */
    public void limpiarDocumentoResolucion() {
        ResolucionesComparendoFL resolucionesComparendoFL = findFlowObject(ResolucionesComparendoFL.class,
                ResolucionesComparendoFL.NOMBRE_FL);
        resolucionesComparendoFL.setStreamedContent(null);
        resolucionesComparendoFL.setMostrarDocResolucion(false);
    }

    /****************************
     * SEGUIMIENTO DEL COMPARENDO
     ****************************/

    /**********************
     * Expresiones regulares
     **********************/
    public String getExpresionPlaca() {
        return ExpresionesRegulares.REGEX_ALFANUMERICO_NO_OBLIGATORIO;
    }

    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }

    public String getExpresionTextos() {
        return ExpresionesRegulares.REGEX_SOLO_LETRAS_AL_MENOS_TRES;
    }

    /**********************
     * Fin Expresiones regulares
     **********************/

    public List<String> getImages() {
        return images;
    }

    public String getRutaImagenes() {
        return rutaImagenes;
    }

}
