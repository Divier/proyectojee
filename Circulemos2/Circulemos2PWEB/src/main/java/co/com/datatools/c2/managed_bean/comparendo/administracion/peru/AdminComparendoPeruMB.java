package co.com.datatools.c2.managed_bean.comparendo.administracion.peru;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;

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

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.EmpresaTransporteDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPatioDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
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
import co.com.datatools.c2.dto.personas.PersonaDTO;
import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.dto.regveh.ConsultaRegistroVehicularDTO;
import co.com.datatools.c2.dto.regveh.PropietarioVehiculoDTO;
import co.com.datatools.c2.dto.regveh.VehiculoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumConfiguracion;
import co.com.datatools.c2.enumeraciones.EnumTipoComparendo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.administracion.AdminComparendoHelper;
import co.com.datatools.c2.managed_bean.formularios.consultas.ConsultarSeguimientoFormularioMB;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.error.EnumProcesamiento;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRAgente;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRDiaNoHabil;
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
import co.com.datatools.util.file.FileValidator;

/**
 * ManagedBean que gestiona las paginas de administracion de comparendos Colombia
 * 
 * @author julio.pinzon
 * 
 */
@ManagedBean
@SessionScoped
public class AdminComparendoPeruMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(AdminComparendoPeruMB.class.getName());

    private static final String NOMBRE_BUNDLE_ADMIN_COMPARENDO = "labelAdminComparendo";
    private static final String NOMBRE_BUNDLE_ADMIN_PAPELETA = "labelAdminPapeleta";

    /**
     * Pasos del wizard del formulario de registro
     */
    private static final String PASO_PROPIETARIO = "propietario";
    private static final String PASO_AGENTE = "agente_transito";
    private static final String PASO_INFRACTOR = "infractor";
    private static final String PASO_VEHICULO = "vehiculo";
    private static final String PASO_OBSERVACIONES = "observaciones";
    private static final Object PASO_EMPRESA = "empresa";

    /**
     * Sufijo
     */
    private static final String SUFIJO_PROPIETARIO = "Propietario";
    private static final String SUFIJO_AGENTE = "AgenteTransito";
    private static final String SUFIJO_INFRACTOR = "Infractor";
    private static final String SUFIJO_EMPRESA = "Empresa";

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
    private IRAdministracionComparendo administracionComparendoEjb;

    @EJB
    private IRDiaNoHabil iRDiaNoHabil;

    private StreamedContent streamedContent;

    public AdminComparendoPeruMB() {
    }

    @PostConstruct
    public void init() {
        logger.debug("AdminComparendoPeruMB::init()");
        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
    }

    /**
     * Permite establecer el estado de control para solicitar o no el organismo de transito
     * 
     * @author divier.casas
     */
    public boolean establecerEstadoDeCampo() {
        logger.debug("AdminComparendoPeruMB::establecerEstadoDeCampo()");
        AdminComparendoPeruHolderFL adminComparendoPeruHolderFL = findFlowObject(AdminComparendoPeruHolderFL.class,
                AdminComparendoPeruHolderFL.NOMBRE_BEAN);
        if (adminComparendoPeruHolderFL.getTipoComparendoPeru().equals(EnumTipoComparendo.PAPELETA.getCodigo())) {
            return true;
        }
        return false;
    }

    /**
     * Permite llamar la consulta de comparendos de acuerdo con los filtros de busqueda
     * 
     * @author divier.casas
     */
    public void consultar() {
        logger.debug("AdminComparendoMB::consultar()");
        try {
            AdminComparendoPeruHolderFL adminComparendoPeruHolderFL = findFlowObject(AdminComparendoPeruHolderFL.class,
                    AdminComparendoPeruHolderFL.NOMBRE_BEAN);

            if (adminComparendoPeruHolderFL.getFechaImposicionInicial() == null
                    && adminComparendoPeruHolderFL.getFechaImposicionFinal() != null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_inicial");
                return;
            }

            if (adminComparendoPeruHolderFL.getFechaImposicionInicial() != null
                    && adminComparendoPeruHolderFL.getFechaImposicionFinal() == null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_fecha_final");
                return;
            }

            if (adminComparendoPeruHolderFL.getFechaImposicionInicial() != null
                    && adminComparendoPeruHolderFL.getFechaImposicionFinal() != null
                    && adminComparendoPeruHolderFL.getFechaImposicionInicial().after(
                            adminComparendoPeruHolderFL.getFechaImposicionFinal())) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_intervalo_fechas");
                return;
            }

            if (adminComparendoPeruHolderFL.getComparendo().getInfractor().getTipoIdentificacion().getId() == null
                    && adminComparendoPeruHolderFL.getComparendo().getInfractor().getNumeroIdentificacion() != null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_infractor");
                return;
            }

            if (adminComparendoPeruHolderFL.getComparendo().getInfractor().getTipoIdentificacion().getId() != null
                    && adminComparendoPeruHolderFL.getComparendo().getInfractor().getNumeroIdentificacion() == null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_infractor");
                return;
            }

            if (adminComparendoPeruHolderFL.getComparendo().getPropietario().getTipoIdentificacion().getId() == null
                    && adminComparendoPeruHolderFL.getComparendo().getPropietario().getNumeroIdentificacion() != null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_propietario");
                return;
            }

            if (adminComparendoPeruHolderFL.getComparendo().getPropietario().getTipoIdentificacion().getId() != null
                    && adminComparendoPeruHolderFL.getComparendo().getPropietario().getNumeroIdentificacion() == null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_propietario");
                return;
            }

            if (adminComparendoPeruHolderFL.getComparendo().getComparendoAgente().getTipoIdentificacionPersona()
                    .getId() == null
                    && adminComparendoPeruHolderFL.getComparendo().getComparendoAgente().getNumeroIdentificacion() != null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_agente");
                return;
            }

            if (adminComparendoPeruHolderFL.getComparendo().getComparendoAgente().getTipoIdentificacionPersona()
                    .getId() != null
                    && adminComparendoPeruHolderFL.getComparendo().getComparendoAgente().getNumeroIdentificacion() == null) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_agente");
                return;
            }

            ConsultaComparendoDTO consultaComparendoDTO = new ConsultaComparendoDTO();

            if (adminComparendoPeruHolderFL.getComparendo().getOrdenComparendoNacional().getOrganismoTransito()
                    .getCodigoOrganismo() != null) {
                consultaComparendoDTO.setCodigoOrganismo(adminComparendoPeruHolderFL.getComparendo()
                        .getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo());
            }

            consultaComparendoDTO.setEsPolca(adminComparendoPeruHolderFL.isPolca());
            consultaComparendoDTO.setIdTipoComparendo(adminComparendoPeruHolderFL.getTipoComparendoPeru());

            if (adminComparendoPeruHolderFL.getNumeroComparendo() != null) {
                consultaComparendoDTO.setNumeroComparendo(adminComparendoPeruHolderFL.getNumeroComparendo()
                        + adminComparendoPeruHolderFL.getSufijoComparendo());
            }

            consultaComparendoDTO.setPlacaVehiculo(adminComparendoPeruHolderFL.getComparendo().getComparendoVehiculo()
                    .getPlacaVehiculo());
            consultaComparendoDTO.setFechaInicioImposicion(adminComparendoPeruHolderFL.getFechaImposicionInicial());
            consultaComparendoDTO.setFechaFinImposicion(adminComparendoPeruHolderFL.getFechaImposicionFinal());
            consultaComparendoDTO.setTipoDocumentoInfractor(adminComparendoPeruHolderFL.getComparendo().getInfractor()
                    .getTipoIdentificacion().getId());
            consultaComparendoDTO.setTipoDocumentoPropietario(adminComparendoPeruHolderFL.getComparendo()
                    .getPropietario().getTipoIdentificacion().getId());
            consultaComparendoDTO.setTipoDocumentoAgente(adminComparendoPeruHolderFL.getComparendo()
                    .getComparendoAgente().getTipoIdentificacionPersona().getId());
            consultaComparendoDTO.setNumeroDocumentoInfractor(adminComparendoPeruHolderFL.getComparendo()
                    .getInfractor().getNumeroIdentificacion());
            consultaComparendoDTO.setNumeroDocumentoPropietario(adminComparendoPeruHolderFL.getComparendo()
                    .getPropietario().getNumeroIdentificacion());
            consultaComparendoDTO.setNumeroDocumentoAgente(adminComparendoPeruHolderFL.getComparendo()
                    .getComparendoAgente().getNumeroIdentificacion());

            List<ComparendoDTO> lstComparendoDTO = comparendoEjb.consultarComparendos(consultaComparendoDTO);

            if (lstComparendoDTO == null || lstComparendoDTO.isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
                adminComparendoPeruHolderFL.setLstComparendos(new ArrayList<ComparendoDTO>(0));
                return;
            } else {
                adminComparendoPeruHolderFL.setLstComparendos(lstComparendoDTO);
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lstComparendoDTO.size());
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Continua a llenar datos especificos del comparendo
     * 
     * @author julio.pinzon
     */
    public void irRegistrar() {
        logger.debug("AdminComparendoMB::irRegistrar()");
        // TODO:Locale de PEru
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);

        adminComparendoFL.setProcesaComparendo(new ProcesaComparendoDTO());
        adminComparendoFL.getProcesaComparendo().setInmovilizado(false);
        adminComparendoFL.getProcesaComparendo().setRetieneLicencia(false);
        adminComparendoFL.getProcesaComparendo().setOrganismoTransito(new OrganismoTransitoDTO());
        adminComparendoFL.getProcesaComparendo().setFechaRecepcion(new Date());
        adminComparendoFL.setEmpresaVehiculo(new ProcesaComparendoPersonaDTO());
        adminComparendoFL.getEmpresaVehiculo().setCodigoTipoPersonaComparendo(
                EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getValue());
        adminComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
        adminComparendoFL.getPropietario().setCodigoTipoPersonaComparendo(
                EnumTipoPersonaComparendo.PROPIETARIO.getValue());
        adminComparendoFL.setInfractor(new ProcesaComparendoPersonaDTO());
        adminComparendoFL.getInfractor().setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR.getValue());
        adminComparendoFL.getProcesaComparendo().setExisteFugaInfractor(false);
        adminComparendoFL.getProcesaComparendo().setRetieneLicencia(false);
        adminComparendoFL.getProcesaComparendo().setNiegaPruebaAlcoholemia(false);
        adminComparendoFL.setPasoActual(PASO_VEHICULO);
        adminComparendoFL.setDetallesProcesamiento(new ArrayList<String>());
        adminComparendoFL.setExisteError(false);
        adminComparendoFL.setMaximoAnio(Calendar.getInstance().get(Calendar.YEAR));

        // Papeleta
        if (adminComparendoFL.getTipoComparendoPeru().equals(EnumTipoComparendo.PAPELETA.getCodigo())) {
            adminComparendoFL.setPapeleta(true);

            // Consultar parametros acta y papeleta
            ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.SUFIJO_PAPELETA,
                    getCodigoOrganismoTransito(), true);
            adminComparendoFL.setSufijoComparendo(valor.getValorParam());
        } else {
            // acta
            adminComparendoFL.setPapeleta(false);
            // Consultar parametros acta y papeleta
            ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.SUFIJO_ACTA_CONTROL,
                    getCodigoOrganismoTransito(), true);
            adminComparendoFL.setSufijoComparendo(valor.getValorParam());
        }
    }

    /**
     * Continua a llenar datos especificos del comparendo
     * 
     * @author julio.pinzon
     */
    public void continuar() {
        logger.debug("AdminComparendoMB::continuar()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);

        boolean errorValidacion = false;
        // Valida que haya encontrado la infraccion
        if (StringUtils.isEmpty(adminComparendoFL.getDescripcionInfraccion())) {
            getFacesContext().addMessage(
                    "form-ingreso:descripcionInfraccion",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }

        // Valida que haya llenado el campo de direccion
        if (StringUtils.isEmpty(adminComparendoFL.getDireccionInfraccionTexto())) {
            getFacesContext().addMessage(
                    "form-ingreso:direccionInfraccion",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }
        // Si hay error muestra los mensajes
        if (errorValidacion) {
            return;
        }
        // Tipo de identificacion para empresa
        adminComparendoFL.setTipoIdentificacionEmpresa(administracionEjb
                .obtenerTipoIdentificacionEmpresaJuridicaPais(getPais().getId()));

        try {
            // Concatena el codigo de organismo al numero de comparendo
            String numeroComparendo = adminComparendoFL.getProcesaComparendo().getNumeroComparendo()
                    + adminComparendoFL.getSufijoComparendo();

            AsociaComparendoFormularioDTO asociaComparendo = new AsociaComparendoFormularioDTO();
            List<String> tiposComparendo = new ArrayList<String>();
            tiposComparendo.add(adminComparendoFL.getConfiguracionInfraccion().getInfraccion().getTipoComparendo()
                    .getId().toString());
            asociaComparendo.setTipoComparendo(tiposComparendo);
            asociaComparendo = configuracionEjb.consultarDatoConfiguracion(
                    EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO.getCodigo(), asociaComparendo);
            /*
             * TODO:Valida que el 'Número de Comparendo' ESTE asignado al Organismo de tránsito seleccionado (FA4).
             */
            // if (asociaComparendo.getTipoFormulario() != null && !asociaComparendo.getTipoFormulario().isEmpty()) {
            // adminComparendoFL.setIdTipoFormulario(Integer.valueOf(asociaComparendo.getTipoFormulario().get(0)));
            // if (!formularioEjb.existeAsignacionOrganismo(numeroComparendo,
            // Integer.valueOf(asociaComparendo.getTipoFormulario().get(0)), adminComparendoFL
            // .getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo())) {
            // addErrorMessage(obtenerNombreBundle(), "msg_error_numero_no_estado_asignado");
            // return;
            // }
            // } else {
            // logger.error("No existe configuracion para :" + EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO);
            // throw new RuntimeException("No existe configuracion para :"
            // + EnumConfiguracion.ASOCIA_TIPOS_COMPARENDO_FORMULARIO);
            // // TODO: Excepcion severa
            // }
            /*
             * Valida que el 'Número de Comparendo' NO exista en el Sistema (FA5).
             */
            if (recibircomparendoEjb.comparendoIngresado(numeroComparendo, adminComparendoFL.getProcesaComparendo()
                    .getOrganismoTransito().getCodigoOrganismo())) {
                addErrorMessage(obtenerNombreBundle(), "msg_error_numero_ya_registrado");
                return;
            }
            /*
             * Valida trae el campo 'número de días' en la configuración 'Cantidad de días para el ingreso de un comparendo' según el: - Organismo de
             * tránsito - Tipo de Comparendo Sumando esta cantidad a la 'Fecha de imposición comparendo' y el resultado NO debe ser MAYOR a la fecha
             * actual del sistema.
             */
            AsociaDiasIngresoComparendoDTO asociaDias = new AsociaDiasIngresoComparendoDTO();
            List<String> mediosImposicion = new ArrayList<String>();
            mediosImposicion.add(String.valueOf(adminComparendoFL.getProcesaComparendo().getCodigoMedioImposicion()));
            asociaDias.setMedioImposicion(mediosImposicion);
            // Ingresamos el organismo de transito
            List<String> codigoOrganismo = new ArrayList<String>();
            codigoOrganismo.add(String.valueOf(adminComparendoFL.getProcesaComparendo().getOrganismoTransito()
                    .getCodigoOrganismo()));
            asociaDias.setCodigoOrganismo(codigoOrganismo);
            asociaDias = configuracionEjb.consultarDatoConfiguracion(
                    EnumConfiguracion.ASOCIA_DIAS_INGRESAR_COMPARENDO.getCodigo(), asociaDias);

            Date fechaMaximaPermitida = iRDiaNoHabil.sumarDiasHabiles(adminComparendoFL.getProcesaComparendo()
                    .getOrganismoTransito().getCodigoOrganismo(), adminComparendoFL.getProcesaComparendo()
                    .getFechaInfraccion(), asociaDias.getDiasLimite().intValue());

            if (Calendar.getInstance().getTime().after(fechaMaximaPermitida)) {
                addErrorMessage(obtenerNombreBundle(), "msg_error_dias_ingreso_vencido");
                return;
            }

            // Verifica que la configuración del 'Código de la infracción' por el caso de uso 'Administrar infracciones' el campo '¿Requiere placa?'
            // es IGUAL A 'SI'
            adminComparendoFL.setPlacaRequerida(adminComparendoFL.getConfiguracionInfraccion().getRequierePlaca());

            // Require infractor
            adminComparendoFL.setRequiereInfractor(adminComparendoFL.getConfiguracionInfraccion()
                    .getInfractorObligatorio() && !adminComparendoFL.getProcesaComparendo().getExisteFugaInfractor());

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

        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);

        adminComparendoFL.setExisteError(false);
        adminComparendoFL.getProcesaComparendo().setProcesaComparendoPersonas(null);
        adminComparendoFL.getProcesaComparendo().setProcesaEvidencias(null);
        adminComparendoFL.getProcesaComparendo().setProcesaDireccionComparendo(null);
        List<ProcesaComparendoPersonaDTO> procesaComparendoPersonas = new ArrayList<ProcesaComparendoPersonaDTO>();
        if (adminComparendoFL.getEmpresaVehiculo() != null) {
            adminComparendoFL.getEmpresaVehiculo().setProcesaDireccion(
                    AdminComparendoHelper.toProcesaDireccionDTO(adminComparendoFL.getDireccionEmpresa()));
            procesaComparendoPersonas.add(adminComparendoFL.getEmpresaVehiculo());
        }
        if (adminComparendoFL.getInfractor() != null) {
            adminComparendoFL.getInfractor().setProcesaDireccion(
                    AdminComparendoHelper.toProcesaDireccionDTO(adminComparendoFL.getDireccionInfractor()));
            procesaComparendoPersonas.add(adminComparendoFL.getInfractor());
        }
        if (adminComparendoFL.getPropietario() != null) {
            adminComparendoFL.getPropietario().setProcesaDireccion(
                    AdminComparendoHelper.toProcesaDireccionDTO(adminComparendoFL.getDireccionPropietario()));
            procesaComparendoPersonas.add(adminComparendoFL.getPropietario());
        }
        adminComparendoFL.getProcesaComparendo().setProcesaComparendoPersonas(procesaComparendoPersonas);
        adminComparendoFL.getProcesaComparendo().setProcesaEvidencias(adminComparendoFL.getProcesaEvidencias());
        adminComparendoFL.getProcesaComparendo().setProcesaDireccionComparendo(
                AdminComparendoHelper.toProcesaDireccionDTO(adminComparendoFL.getDireccionInfraccion()));
        // Polca
        // TODO
        // adminComparendoFL.getProcesaComparendo().setEsPolca(false);
        // Id de la infraccion
        adminComparendoFL.getProcesaComparendo().setIdInfraccion(
                adminComparendoFL.getConfiguracionInfraccion().getInfraccion().getId());

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

                getFacesContext().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", respuestaValidacion
                                .getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento().getDescripcion()));
                return false;
            } else if (respuestaValidacion.getCodigoResultado()
                    .equals(EnumErrorProcesamiento.INCONSISTENTE.getCodigo())) {
                // Errores no bloqueantes
                adminComparendoFL.setExisteError(true);
                adminComparendoFL.setErrorProcesamiento(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString(
                        "msg_recibido_sin_procesar"));
                for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacion
                        .getDetalleProcesamientoDTOs()) {
                    adminComparendoFL.getDetallesProcesamiento().add(
                            detalleProcesamientoDTO.getErrorProcesamiento().getCodigo() + " - "
                                    + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                    logger.info("Error en procesar: "
                            + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                }
                return false;
            } else if (respuestaValidacion.getCodigoResultado().equals(EnumErrorProcesamiento.REGISTRADO.getCodigo())) {
                if (respuestaValidacion.isExisteAlerta()) {
                    for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacion
                            .getDetalleProcesamientoDTOs()) {
                        // Error al guardar documento en el repositorio
                        addWarningMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, detalleProcesamientoDTO
                                .getErrorProcesamiento().getCodigo());
                        logger.info("Alerta en procesar: "
                                + detalleProcesamientoDTO.getErrorProcesamiento().getCodigo());
                    }
                }
                // Mensaje de guardado exitoso
                addInfoMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_satisfactorio");
            }
        }
        return true;
    }

    /**
     * Valor de numero comparendo cambia en consulta, metodo que permite rellenar el numero del comparendo considerando el sufijo aplicado a actas de
     * control
     * 
     * @author divier.casas
     */
    public void onNumeroComparendoChangeConsulta() {
        logger.debug("AdminComparendoMB::onNumeroComparendoChangeConsulta()");
        AdminComparendoPeruHolderFL adminComparendoPeruHolderFL = findFlowObject(AdminComparendoPeruHolderFL.class,
                AdminComparendoPeruHolderFL.NOMBRE_BEAN);
        if (StringUtils.isNotEmpty(adminComparendoPeruHolderFL.getNumeroComparendo())) {
            adminComparendoPeruHolderFL.setNumeroComparendo(StringUtils.leftPad(
                    String.valueOf(adminComparendoPeruHolderFL.getNumeroComparendo()), 10, "0"));
        }
    }

    /**
     * Obtiene mensaje para el caso de papeleta o acta
     * 
     * @param clave
     * @return Mensaje obtenido del bundle
     */
    public String obtenerMensaje(String clave) {
        logger.debug("AdminComparendoMB::obtenerMensaje(String)");
        String mensaje = "";
        try {
            mensaje = getBundle(obtenerNombreBundle()).getString(clave);
        } catch (MissingResourceException e) {
            logger.error("No se encontró mensaje " + e.getMessage());
        }
        return mensaje;
    }

    /**
     * Devuelve el nombre del bundle adecuado para el mensaje
     * 
     * @return
     */
    private String obtenerNombreBundle() {
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        if (adminComparendoFL.isPapeleta()) {
            return NOMBRE_BUNDLE_ADMIN_PAPELETA;
        } else {
            return NOMBRE_BUNDLE_ADMIN_COMPARENDO;
        }
    }

    /**
     * Obtiene mensaje para el caso de papeleta o acta en la consulta
     * 
     * @param clave
     * @return Mensaje obtenido del bundle
     * @author divier.casas
     */
    public String obtenerMensajeConsulta(String clave) {
        logger.debug("AdminComparendoMB::obtenerMensaje(String)");
        String mensaje = "";
        try {
            mensaje = getBundle(obtenerNombreBundleConsulta()).getString(clave);
        } catch (MissingResourceException e) {
            logger.error("No se encontró mensaje " + e.getMessage());
        }
        return mensaje;
    }

    /**
     * Devuelve el nombre del bundle adecuado para el mensaje en la consulta
     * 
     * @return String con el nombre del bundle
     * @author divier.casas
     */
    private String obtenerNombreBundleConsulta() {
        AdminComparendoPeruHolderFL adminComparendoPeruHolderFL = findFlowObject(AdminComparendoPeruHolderFL.class,
                AdminComparendoPeruHolderFL.NOMBRE_BEAN);
        if (adminComparendoPeruHolderFL.getTipoComparendoPeru().equals(EnumTipoComparendo.PAPELETA.getCodigo())) {
            return NOMBRE_BUNDLE_ADMIN_PAPELETA;
        } else {
            return NOMBRE_BUNDLE_ADMIN_COMPARENDO;
        }
    }

    /*****************
     * Eventos encabezado
     *****************/

    /**
     * Valor de numero comparendo cambia
     * 
     * @author julio.pinzon
     */
    public void onNumeroComparendoChange() {
        logger.debug("AdminComparendoMB::onNumeroComparendoChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        if (StringUtils.isNotEmpty(adminComparendoFL.getProcesaComparendo().getNumeroComparendo())) {

            adminComparendoFL.getProcesaComparendo().setNumeroComparendo(
                    StringUtils.leftPad(String.valueOf(adminComparendoFL.getProcesaComparendo().getNumeroComparendo()),
                            11, "0"));
        }
    }

    /**
     * Organismo de transito cambia
     * 
     * @author julio.pinzon
     */
    public void onCodigoOrganismoChange() {
        logger.debug("AdminComparendoMB::onCodigoOrganismoChange()");

        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        if (adminComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !adminComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && adminComparendoFL.getProcesaComparendo().getFechaInfraccion() != null
                && adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo() != null) {
            cambiarInfraccion();
            RequestContext.getCurrentInstance().update("descripcionInfraccion");
        }
    }

    /**
     * Cuando se debe consultar de nuevo la infraccion por cambio en fecha
     * 
     * @author julio.pinzon
     */
    public void onFechaImposicionChange() {
        logger.debug("AdminComparendoMB::onFechaImposicionChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        adminComparendoFL.getProcesaComparendo().setHoraInfraccion(
                adminComparendoFL.getProcesaComparendo().getFechaInfraccion());
        cambiarInfraccion();
    }

    /**
     * Cuando se debe consultar de nuevo la infraccion por cambio en codigo
     */
    public void onCodigoInfraccionChange() {
        logger.debug("AdminComparendoMB::onCodigoInfraccionChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        adminComparendoFL.getProcesaComparendo().setCodigoInfraccion(
                adminComparendoFL.getProcesaComparendo().getCodigoInfraccion().toUpperCase());
        adminComparendoFL.setDescripcionInfraccion(null);
        if (adminComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !adminComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && adminComparendoFL.getProcesaComparendo().getFechaInfraccion() != null
                && adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo() != null) {
            cambiarInfraccion();
        } else {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_faltan_valores_infraccion");
        }

    }

    /**
     * Cambia valor de la infraccion
     */
    private void cambiarInfraccion() {
        logger.debug("AdminComparendoMB::cambiarInfraccion()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        adminComparendoFL.setDescripcionInfraccion(null);
        if (adminComparendoFL.getProcesaComparendo().getCodigoInfraccion() != null
                && !adminComparendoFL.getProcesaComparendo().getCodigoInfraccion().isEmpty()
                && adminComparendoFL.getProcesaComparendo().getFechaInfraccion() != null
                && adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo() != null) {
            ConfiguracionInfraccionDTO configuracionInfraccionDTO = infraccionEjb.consultarInfraccion(adminComparendoFL
                    .getProcesaComparendo().getCodigoInfraccion(), adminComparendoFL.getProcesaComparendo()
                    .getFechaInfraccion());
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
    public void recargarDireccion(DireccionDTO direccion) {
        logger.debug("AdminComparendoMB::recargarDireccion()");
        RequestContext context = RequestContext.getCurrentInstance();
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        if (adminComparendoFL.isEditaDireccionInfraccion()) {
            context.update("form-ingreso:datos-lugar-infraccion");
            adminComparendoFL.setDireccionInfraccion(direccion);
            adminComparendoFL.setEditaDireccionInfraccion(false);
        } else if (adminComparendoFL.isEditaDireccionInfractor()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-infractor");
            adminComparendoFL.setDireccionInfractor(direccion);
            adminComparendoFL.setEditaDireccionInfractor(false);
        } else if (adminComparendoFL.isEditaDireccionEmpresa()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-empresa");
            adminComparendoFL.setDireccionEmpresa(direccion);
            adminComparendoFL.setEditaDireccionEmpresa(false);
        } else if (adminComparendoFL.isEditaDireccionPropietario()) {
            context.update("form-ingreso-detalle-comparendo:datos-direccion-propietario");
            adminComparendoFL.setDireccionPropietario(direccion);
            adminComparendoFL.setEditaDireccionPropietario(false);
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
    public void onPlacaChange() {
        logger.debug("AdminComparendoMB::onPlacaChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);

        // reinicia valores
        reiniciarDatosVehiculo();

        if (StringUtils.isNotBlank(adminComparendoFL.getProcesaComparendo().getPlacaVehiculo())) {
            // Reinicia datos del propietario
            adminComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
            adminComparendoFL.getPropietario().setCodigoTipoPersonaComparendo(
                    EnumTipoPersonaComparendo.PROPIETARIO.getValue());

            adminComparendoFL.getProcesaComparendo().setIdentificacionVehiculo(null);
            /*
             * Consultar la información existente por la 'Placa vehículo' por medio del caso de uso 'Consultar información de registro automotor' y
             * proponerla en los campos que se listan en esta sección ('VEHICULO')
             */
            VehiculoDTO vehiculo = regVehicularEjb.consultarVehiculo(adminComparendoFL.getProcesaComparendo()
                    .getPlacaVehiculo());
            if (vehiculo != null) {
                adminComparendoFL.getProcesaComparendo().setCodigoOrganismoMatriculaVehiculo(
                        vehiculo.getOrganismoTransito().getCodigoOrganismo());
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
                    adminComparendoFL.getProcesaComparendo().setNumeroTarjetaOperacion(
                            vehiculo.getNumeroTarjetaOpera().toString());
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
                 * Consultar la información existente por la 'Placa vehículo' por medio del caso de uso 'Consultar información de registro de
                 * propietarios' donde la 'Fecha de imposición de comparendo' se encuentre entre en un intervalo 'Fecha inicio de propiedad' y 'Fecha
                 * fin de propiedad',
                 */
                ConsultaRegistroVehicularDTO consultaRegistroVehicularDTO = new ConsultaRegistroVehicularDTO();
                consultaRegistroVehicularDTO.setCodigoOrganismo(adminComparendoFL.getProcesaComparendo()
                        .getOrganismoTransito().getCodigoOrganismo());
                consultaRegistroVehicularDTO.setFechaConsulta(adminComparendoFL.getProcesaComparendo()
                        .getFechaInfraccion());
                consultaRegistroVehicularDTO.setPlaca(adminComparendoFL.getProcesaComparendo().getPlacaVehiculo());
                PropietarioVehiculoDTO propietario = regVehicularPropietarioEjb
                        .consultarMayorPropietario(consultaRegistroVehicularDTO);
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

                        adminComparendoFL.setTipoIdJuridicoPropietario(adminComparendoFL.getPropietario()
                                .getIdTipoIdentificacion()
                                .equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId()));
                    }

                }
            }
        }

    }

    /**
     * Reinicia los datos de la pestania de vehiculos
     * 
     * @author julio.pinzon
     */
    private void reiniciarDatosVehiculo() {
        logger.debug("AdminComparendoMB::reiniciarDatosVehiculo()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);

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
    }

    /**
     * Cuando se cambia el tipo de documento de la empresa
     * 
     * @author julio.pinzon
     */
    public void onTipoDocumentoEmpresaChange() {
        logger.debug("AdminComparendoMB::onTipoDocumentoEmpresaChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);

        if (adminComparendoFL.getEmpresaVehiculo().getIdTipoIdentificacion() != null
                && StringUtils.isNotEmpty(adminComparendoFL.getEmpresaVehiculo().getNumeroIdentificacion())) {
            // Para consultar las empresas:
            PersonaJuridicaDTO persona = new PersonaJuridicaDTO();
            TipoIdentificacionPersonaDTO tipoIdentificacion = new TipoIdentificacionPersonaDTO(adminComparendoFL
                    .getEmpresaVehiculo().getIdTipoIdentificacion());
            persona.setTipoIdentificacion(tipoIdentificacion);
            persona.setNumeroIdentificacion(adminComparendoFL.getEmpresaVehiculo().getNumeroIdentificacion());
            EmpresaTransporteDTO empresaTransporteDTO = new EmpresaTransporteDTO();
            empresaTransporteDTO.setPersonaJuridica(persona);
            List<EmpresaTransporteDTO> empresas = administracionComparendoEjb.consultarEmpresaTransporte(
                    adminComparendoFL.getProcesaComparendo().getOrganismoTransito().getCodigoOrganismo(),
                    empresaTransporteDTO);

            if (empresas != null && !empresas.isEmpty()) {
                if (empresas.get(0).getPersonaJuridica() != null) {
                    adminComparendoFL.setEmpresaVehiculo(AdminComparendoHelper.toProcesaComparendoPersonaDTO(persona,
                            adminComparendoFL.getEmpresaVehiculo()));
                    adminComparendoFL.setIdEmpresaTransporte(empresas.get(0).getId());
                    RequestContext.getCurrentInstance().update("form-ingreso-detalle-comparendo:selRutaEmpresa");
                }
            }
        }
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
    public void onTipoDocumentoInfractorChange() {
        logger.debug("AdminComparendoMB::onTipoDocumentoInfractorChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        consultarPersona(adminComparendoFL.getInfractor(), adminComparendoFL.getProcesaComparendo()
                .getOrganismoTransito().getCodigoOrganismo());

        adminComparendoFL.setRequiereTipoDocInfractor(false);
        if (adminComparendoFL.getInfractor().getIdTipoIdentificacion() != null) {
            adminComparendoFL.setRequiereTipoDocInfractor(true);
        }
    }

    /**
     * Cuando se cambia el tipo de documento del infractor
     * 
     * @author julio.pinzon
     */
    public void onNumDocInfractorChange() {
        logger.debug("AdminComparendoMB::onNumDocInfractorChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        consultarPersona(adminComparendoFL.getInfractor(), adminComparendoFL.getProcesaComparendo()
                .getOrganismoTransito().getCodigoOrganismo());

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
    public void onTipoDocumentoPropietarioChange() {
        logger.debug("AdminComparendoMB::onTipoDocumentoPropietarioChange()");

        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        consultarPersona(adminComparendoFL.getPropietario(), adminComparendoFL.getProcesaComparendo()
                .getOrganismoTransito().getCodigoOrganismo());

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
    public void onNumDocPropietarioChange() {
        logger.debug("AdminComparendoMB::onNumDocPropietarioChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        consultarPersona(adminComparendoFL.getPropietario(), adminComparendoFL.getProcesaComparendo()
                .getOrganismoTransito().getCodigoOrganismo());

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
    public void onTipoDocumentoAgenteTransitoChange() {
        logger.debug("AdminComparendoMB::onTipoDocumentoAgenteTransitoChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        // reinicia datos
        reiniciarDatosAgente();

        // Consulta datos agente
        consultarAgente(adminComparendoFL);
    }

    /**
     * Cuando se cambia el tipo de documento del agente transito
     */
    public void onNumDocAgenteTransitoChange() {
        logger.debug("AdminComparendoMB::onNumDocAgenteTransitoChange()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        // reinicia datos
        reiniciarDatosAgente();

        // Consulta datos agente
        consultarAgente(adminComparendoFL);
    }

    /**
     * Consulta el agente
     * 
     * @param adminComparendoFL
     */
    private void consultarAgente(AdminComparendoPeruFL adminComparendoFL) {
        if (StringUtils.isNotEmpty(adminComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente())
                && adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente() != null) {

            AgenteDTO agente = new AgenteDTO();
            // Consultar Persona
            PersonaDTO personaAgente = new PersonaDTO();
            personaAgente.setNumeroIdentificacion(adminComparendoFL.getProcesaComparendo()
                    .getNumeroIdentificacionAgente());
            personaAgente.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
            personaAgente.getTipoIdentificacion().setId(
                    adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente());
            agente.setPersona(personaAgente);

            // Consulta agente
            agente = agenteEjb.consultarAgente(agente).get(0);

            // donde la 'Fecha de imposición comparendo' se encuentre entre la 'Fecha inicial de vigencia' y la 'fecha final de vigencia' del agente a
            // consultar y proponerla en los campos que se listan en esta sección ('AGENTE DE TRÁNSITO'). (FA9)
            if (agente == null
                    || (adminComparendoFL.getProcesaComparendo().getFechaInfraccion()
                            .before(agente.getFechaInicioVigencia()) || (agente.getFechaFinVigencia() != null && adminComparendoFL
                            .getProcesaComparendo().getFechaInfraccion().after(agente.getFechaFinVigencia())))) {
                // FA9
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_debe_existir_agente");
            } else {
                // Datos del agente
                adminComparendoFL.getProcesaComparendo().setIdAgenteTransito(agente.getId());
                adminComparendoFL.getProcesaComparendo().setIdTipoIdentificacionAgente(
                        agente.getPersona().getTipoIdentificacion().getId());
                adminComparendoFL.getProcesaComparendo().setNumeroIdentificacionAgente(
                        agente.getPersona().getNumeroIdentificacion());
                adminComparendoFL.getProcesaComparendo().setNombre1Agente(agente.getPersona().getNombre1());
                adminComparendoFL.getProcesaComparendo().setNombre2Agente(agente.getPersona().getNombre2());
                adminComparendoFL.getProcesaComparendo().setApellido1Agente(agente.getPersona().getApellido1());
                adminComparendoFL.getProcesaComparendo().setApellido2Agente(agente.getPersona().getApellido2());
                adminComparendoFL.setFechaInicioVigenciaAgente(agente.getFechaInicioVigencia());
                adminComparendoFL.setFechaFinVigenciaAgente(agente.getFechaFinVigencia());
                adminComparendoFL.getProcesaComparendo().setPlacaAgente(agente.getPlaca());
            }
        }
    }

    /**
     * Reinicia los datos de la pestania de vehiculos
     * 
     * @author julio.pinzon
     */
    private void reiniciarDatosAgente() {
        logger.debug("AdminComparendoMB::reiniciarDatosVehiculo()");
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);

        // reinicia valores
        adminComparendoFL.getProcesaComparendo().setIdAgenteTransito(null);
        adminComparendoFL.getProcesaComparendo().setNombre1Agente(null);
        adminComparendoFL.getProcesaComparendo().setNombre2Agente(null);
        adminComparendoFL.getProcesaComparendo().setApellido1Agente(null);
        adminComparendoFL.getProcesaComparendo().setApellido2Agente(null);
    }

    /**********************
     * Fin Eventos agente_transito
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
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        boolean errorValidacion = false;
        if (event.getOldStep().equals(PASO_AGENTE) && event.getNewStep().equals(PASO_OBSERVACIONES)) {
            // Valida campos del agente
            ProcesaComparendoPersonaDTO agente = new ProcesaComparendoPersonaDTO();
            agente.setApellido1(adminComparendoFL.getProcesaComparendo().getApellido1Agente());
            agente.setNombre1(adminComparendoFL.getProcesaComparendo().getNombre1Agente());
            agente.setNumeroIdentificacion(adminComparendoFL.getProcesaComparendo().getNumeroIdentificacionAgente());
            agente.setIdTipoIdentificacion(adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente());
            errorValidacion = validarPersona(agente, true, true, SUFIJO_AGENTE, "-");

            /*
             * Valida que la combinación entre 'Tipo de documento del Agente' y 'Número de documento del agente' EXISTE, y la 'Fecha de imposición del
             * comparendo' se encuentra ENTRE 'Fecha de inicio de vigencia agente' y 'Fecha de fin de vigencia agente' (FA7)
             */
            AgenteDTO agenteDTO = new AgenteDTO();
            // Consultar Persona
            PersonaDTO personaAgente = new PersonaDTO();
            personaAgente.setNumeroIdentificacion(adminComparendoFL.getProcesaComparendo()
                    .getNumeroIdentificacionAgente());
            personaAgente.setTipoIdentificacion(new TipoIdentificacionPersonaDTO());
            personaAgente.getTipoIdentificacion().setId(
                    adminComparendoFL.getProcesaComparendo().getIdTipoIdentificacionAgente());
            agenteDTO.setPersona(personaAgente);

            // Consulta agente
            agenteDTO = agenteEjb.consultarAgente(agenteDTO).get(0);

            // donde la 'Fecha de imposición comparendo' se encuentre entre la 'Fecha inicial de vigencia' y la 'fecha final de vigencia' del agente a
            // consultar y proponerla en los campos que se listan en esta sección ('AGENTE DE TRÁNSITO'). (FA9)
            if (agenteDTO == null
                    || (adminComparendoFL.getProcesaComparendo().getFechaInfraccion()
                            .before(agenteDTO.getFechaInicioVigencia()) || (agenteDTO.getFechaFinVigencia() != null && adminComparendoFL
                            .getProcesaComparendo().getFechaInfraccion().after(agenteDTO.getFechaFinVigencia())))) {
                // FA7
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_debe_existir_agente");
                errorValidacion = true;
            }

        } else if (event.getOldStep().equals(PASO_INFRACTOR) && event.getNewStep().equals(PASO_PROPIETARIO)) {
            errorValidacion = validarPersona(adminComparendoFL.getInfractor(), adminComparendoFL.isRequiereInfractor(),
                    adminComparendoFL.isRequiereTipoDocInfractor(), SUFIJO_INFRACTOR,
                    adminComparendoFL.getDireccionInfractorTexto());

            if (!errorValidacion && adminComparendoFL.isEsPropietario()) {
                adminComparendoFL.setPropietario(new ProcesaComparendoPersonaDTO());
                adminComparendoFL.getPropietario().setCodigoTipoPersonaComparendo(
                        EnumTipoPersonaComparendo.PROPIETARIO.getValue());
                adminComparendoFL.getPropietario().setIdTipoIdentificacion(
                        adminComparendoFL.getInfractor().getIdTipoIdentificacion());
                adminComparendoFL.getPropietario().setNumeroIdentificacion(
                        adminComparendoFL.getInfractor().getNumeroIdentificacion());
                adminComparendoFL.getPropietario().setApellido1(adminComparendoFL.getInfractor().getApellido1());
                adminComparendoFL.getPropietario().setApellido2(adminComparendoFL.getInfractor().getApellido2());
                adminComparendoFL.getPropietario().setNombre1(adminComparendoFL.getInfractor().getNombre1());
                adminComparendoFL.getPropietario().setNombre2(adminComparendoFL.getInfractor().getNombre2());
                adminComparendoFL.setRequierePropietario(true);
                adminComparendoFL.getPropietario().setRazonSocial(adminComparendoFL.getInfractor().getRazonSocial());
                adminComparendoFL.setTipoIdJuridicoPropietario(adminComparendoFL.getPropietario()
                        .getIdTipoIdentificacion().equals(adminComparendoFL.getTipoIdentificacionEmpresa().getId()));
            }
        } else if (event.getOldStep().equals(PASO_PROPIETARIO)
                && (event.getNewStep().equals(PASO_EMPRESA) || event.getNewStep().equals(PASO_AGENTE))) {
            errorValidacion = validarPersona(adminComparendoFL.getPropietario(),
                    adminComparendoFL.isRequierePropietario(), adminComparendoFL.isRequierePropietario(),
                    SUFIJO_PROPIETARIO, adminComparendoFL.getDireccionPropietarioTexto());
        } else if (event.getOldStep().equals(PASO_EMPRESA) && event.getNewStep().equals(PASO_AGENTE)) {
            errorValidacion = validarPersona(adminComparendoFL.getEmpresaVehiculo(),
                    adminComparendoFL.isTipoDocEmpresaRequerido(), adminComparendoFL.isTipoDocEmpresaRequerido(),
                    SUFIJO_EMPRESA, adminComparendoFL.getDireccionEmpresaTexto());
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
            boolean requiereTipoDocPersona, String idCampo, String direccion) {
        AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
        boolean errorValidacion = false;
        if (persona.getIdTipoIdentificacion() == null && (requierePersona || requiereTipoDocPersona)) {
            getFacesContext().addMessage(
                    "form-ingreso-detalle-comparendo:selTipoDocumento" + idCampo,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }

        if (StringUtils.isEmpty(persona.getNumeroIdentificacion()) && (requierePersona || requiereTipoDocPersona)) {
            getFacesContext().addMessage(
                    "form-ingreso-detalle-comparendo:numeroDocumento" + idCampo,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }

        if (persona.getCodigoTipoPersonaComparendo() != null
                && !persona.getCodigoTipoPersonaComparendo().equals(
                        EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getValue()) && StringUtils.isEmpty(direccion)
                && (requierePersona || requiereTipoDocPersona)) {
            getFacesContext().addMessage(
                    "form-ingreso-detalle-comparendo:direccion" + idCampo,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            errorValidacion = true;
        }

        if (persona.getIdTipoIdentificacion() != null
                && adminComparendoFL.getTipoIdentificacionEmpresa().getId().equals(persona.getIdTipoIdentificacion())) {
            if (StringUtils.isEmpty(persona.getRazonSocial()) && (requierePersona || requiereTipoDocPersona)) {
                getFacesContext().addMessage(
                        "form-ingreso-detalle-comparendo:nombre" + idCampo,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                errorValidacion = true;
            }
        } else {
            if (StringUtils.isEmpty(persona.getApellido1()) && (requierePersona || requiereTipoDocPersona)) {
                getFacesContext().addMessage(
                        "form-ingreso-detalle-comparendo:primerApellido" + idCampo,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                errorValidacion = true;
            }

            if (StringUtils.isEmpty(persona.getNombre1()) && (requierePersona || requiereTipoDocPersona)) {
                getFacesContext().addMessage(
                        "form-ingreso-detalle-comparendo:primerNombre" + idCampo,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
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
        logger.debug(AdminComparendoPeruMB.class.getName().concat("::cargaArchivoEvidencia()"));
        final UploadedFile file = event.getFile();

        try {
            if (FileValidator.checkFileType(file.getInputstream(), file.getFileName(), ".tiff", ".pdf", ".png", ".jpg")) {

                final ProcesaEvidenciaDTO procesaEvidenciaDTO = new ProcesaEvidenciaDTO();
                final AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                        AdminComparendoPeruFL.NOMBRE_BEAN);

                Integer idTipoEvidencia = adminComparendoFL.getIdTipoEvidencia();
                if (idTipoEvidencia == null) {
                    getFacesContext().addMessage(
                            "form-ingreso-detalle-comparendo:selTipoEvidencia",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                    CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                    CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                    return;
                }
                procesaEvidenciaDTO.setCodigoTipoEvidencia(idTipoEvidencia);
                procesaEvidenciaDTO.setNombreTipoEvidencia(getCatalogosApp().getItemCatalogo(
                        EnumCatalogo.TipoEvidencia, idTipoEvidencia).getLabel());

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

    /**
     * Permite eliminar un documento de las evidencias.
     * 
     * @author luis.forero(2015-10-14)
     * 
     */
    public void eliminarListaDocumentos() {
        final AdminComparendoPeruFL adminComparendoFL = findFlowObject(AdminComparendoPeruFL.class,
                AdminComparendoPeruFL.NOMBRE_BEAN);
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
    public boolean consultarDetalleComparendo() {
        final AdminComparendoPeruHolderFL adminComparendoHolderFL = findFlowObject(AdminComparendoPeruHolderFL.class,
                AdminComparendoPeruHolderFL.NOMBRE_BEAN);

        if (adminComparendoHolderFL.getComparendoSeleccionado() != null) {
            ComparendoDTO detalleComparendo = comparendoEjb.consultarComparendo(adminComparendoHolderFL
                    .getComparendoSeleccionado().getCicomparendo());

            if (detalleComparendo != null) {
                final DetalleComparendoPeruFL detalleComparendoFL = findFlowObject(DetalleComparendoPeruFL.class,
                        DetalleComparendoPeruFL.NOMBRE_BEAN);
                // ENCABEZADO
                detalleComparendoFL.setComparendo(detalleComparendo);
                detalleComparendoFL.setConfiguracionInfraccion(detalleComparendo.getInfraccion()
                        .getConfiguracionInfraccionList().get(0));

                String nomOrganismoTransito = getCatalogosApp().getItemCatalogo(EnumCatalogo.organismo_transito,
                        detalleComparendo.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo())
                        .getLabel();
                detalleComparendoFL.setNomOrganismoTransito(nomOrganismoTransito);

                detalleComparendoFL.setDescripcionInfraccion(detalleComparendo.getInfraccion()
                        .getConfiguracionInfraccionList().get(0).getDescripcion());

                DireccionDTO direccionInfraccion = detalleComparendo.getDireccion();
                detalleComparendoFL.setDireccionInfraccionTexto(direccionInfraccion.toString() + " "
                        + direccionInfraccion.getComplemento());
                // VEHICULO

                detalleComparendoFL.setEmpresaVehiculo(detalleComparendo.getComparendoVehiculo());

                // EMPRESA
                if (detalleComparendo.getEmpresa() != null) {
                    detalleComparendoFL.setEmpresaPersonaVehiculo(detalleComparendo.getEmpresa());
                    if (detalleComparendo.getEmpresa().getDireccion() != null) {
                        detalleComparendoFL.setDireccionEmpresaTexto(detalleComparendo.getEmpresa().getDireccion()
                                .toString());
                    }
                }

                // INFRACTOR
                ComparendoPersonaDTO infractor = detalleComparendo.getInfractor();
                detalleComparendoFL.setInfractor(infractor);

                if (infractor != null) {
                    DireccionDTO dirInfractor = infractor.getDireccion();

                    if (dirInfractor != null) {
                        detalleComparendoFL.setDireccionInfractorTexto(dirInfractor.toString());
                    }
                }

                // PROPIETARIO
                if (detalleComparendo.getPropietario() != null) {
                    detalleComparendoFL.setPropietario(detalleComparendo.getPropietario());

                    if (detalleComparendo.getPropietario().getOrganismoTransito() != null) {
                        detalleComparendoFL.setNomOrgLicenciaTrans(getCatalogosApp().getItemCatalogo(
                                EnumCatalogo.organismo_transito,
                                detalleComparendo.getPropietario().getOrganismoTransito().getCodigoOrganismo())
                                .getLabel());
                    }

                    if (detalleComparendo.getPropietario().getDireccion() != null) {
                        detalleComparendoFL.setDireccionPropietarioTexto(detalleComparendo.getPropietario()
                                .getDireccion().toString());
                    }
                }

                // AGENTE DE TRANSITO
                ComparendoAgenteDTO comparendoAgente = detalleComparendo.getComparendoAgente();
                detalleComparendoFL.setAgente(comparendoAgente);

                // INMOVILIZACION
                ComparendoPatioDTO comparendoPatio = detalleComparendo.getComparendoPatio();
                if (comparendoPatio != null) {
                    detalleComparendoFL.setComparendoPatio(comparendoPatio);

                    DireccionDTO dirPatioInmovili = comparendoPatio.getDireccion();
                    if (dirPatioInmovili != null) {
                        detalleComparendoFL.setDireccionInmovilizacionTexto(dirPatioInmovili.toString());
                    }

                    if (comparendoPatio.getIdPatio() != null) {
                        String nombrePatio = comparendoPatio.getPatio().getNombre();
                        detalleComparendoFL.setNombrePatio(nombrePatio);
                    }
                }

                // EVIDENCIAS
                detalleComparendoFL.setEvidencias(detalleComparendo.getEvidenciaList());

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
     */
    public StreamedContent descargarDocumento(EvidenciaDTO evidenciaDTO) {
        logger.debug(ConsultarSeguimientoFormularioMB.class.getName().concat("::descargarDocumento()"));
        StreamedContent streamedContent = null;
        ArchivoTransportableDTO archivoTransportableDTO;
        try {
            archivoTransportableDTO = repositorioArchivoEJB.consultarDocumento(evidenciaDTO.getIdDocumento());

            streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                    archivoTransportableDTO.getNombre());

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
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

    /**
     * Permite consultar el sufijo de actas o papeletas
     * 
     * @param codigoOrganismo
     * @return sufijo
     * @author divier.casas(2015-10-26)
     */
    public String consultarSufijos() {
        String sufijo = null;
        AdminComparendoPeruHolderFL adminComparendoPeruHolderFL = findFlowObject(AdminComparendoPeruHolderFL.class,
                AdminComparendoPeruHolderFL.NOMBRE_BEAN);
        if (adminComparendoPeruHolderFL.getTipoComparendoPeru().equals(EnumTipoComparendo.PAPELETA.getCodigo())) {
            ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.SUFIJO_PAPELETA,
                    getCodigoOrganismoTransito(), true);
            adminComparendoPeruHolderFL.setSufijoComparendo(valor.getValorParam());
            sufijo = adminComparendoPeruHolderFL.getSufijoComparendo();
        } else if (adminComparendoPeruHolderFL.getTipoComparendoPeru().equals(
                EnumTipoComparendo.ACTA_CONTROL.getCodigo())) {
            ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.SUFIJO_ACTA_CONTROL,
                    getCodigoOrganismoTransito(), true);
            adminComparendoPeruHolderFL.setSufijoComparendo(valor.getValorParam());
            sufijo = adminComparendoPeruHolderFL.getSufijoComparendo();
        }
        return sufijo;
    }

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
}
