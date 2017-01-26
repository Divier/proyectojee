package co.com.datatools.c2.managed_bean.comparendo.rectificacion.ecuador;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.TipoEvidenciaDTO;
import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.CampoRectificaComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoAgenteDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPatioDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoVehiculoDTO;
import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.DetalleProcesamientoDTO;
import co.com.datatools.c2.dto.comparendo.EvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.MedioImposicionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoPersonaDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaComparendoRectificadoDTO;
import co.com.datatools.c2.dto.comparendo.ProcesaEvidenciaDTO;
import co.com.datatools.c2.dto.comparendo.RespuestaValidacionDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfractorDTO;
import co.com.datatools.c2.dto.comun.DireccionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.UnificacionResponsableDTO;
import co.com.datatools.c2.enumeracion.EnumActividad;
import co.com.datatools.c2.enumeracion.EnumCampoEntidad;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoPersonaComparendo;
import co.com.datatools.c2.enumeracion.EnumTipoSancion;
import co.com.datatools.c2.enumeraciones.EnumCatalogo;
import co.com.datatools.c2.enumeraciones.EnumTipoAgenteImpositor;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.comparendo.administracion.AdminComparendoHelper;
import co.com.datatools.c2.managed_bean.formularios.consultas.ConsultarSeguimientoFormularioMB;
import co.com.datatools.c2.negocio.error.EnumErrorProcesamiento;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.IRRecibirComparendo;
import co.com.datatools.c2.negocio.interfaces.administracion.IRInfraccion;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.c2.web.util.comparendo.ComparendosUtil;
import co.com.datatools.util.date.UtilFecha;
import co.com.datatools.util.file.FileValidator;

/**
 * Permite llevar controlar las acciones realizadas para el caso de uso <b>Rectificar Comparendo CU_CIR20_DAT_COM_014</b>
 * 
 * @author luis.forero(2016-01-28)
 * 
 */
@ManagedBean
@SessionScoped
public class RectificarComparendoEcuadorMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(RectificarComparendoEcuadorMB.class.getName());

    private static final String NOMBRE_BUNDLE_ADMIN_COMPARENDO = "labelAdminComparendo";

    /**
     * Atributo que permite identificar que tipos de archivos son permitidos
     */
    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(tiff|pdf|png|jpg)$/";

    private int tamanioMaximoArch;

    @EJB
    private IRAdministracion administracionEjb;

    @EJB
    private IRParametro parametroEjb;

    @EJB
    private IRInfraccion infraccionEjb;

    @EJB
    private IRRecibirComparendo recibirComparendoEjb;

    @EJB
    private IRComparendo comparendoEjb;

    @EJB
    private IRRepositorioArchivo repositorioArchivoEJB;

    private StreamedContent streamedContent;

    private String formatoFechaCompleto;

    @javax.annotation.PostConstruct
    public void init() {
        logger.debug("RectificarComparendoEcuadorMB::init()");
        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
    }

    /**
     * Invoca al metodo de negocio que registra un nuevo comparendo
     * 
     * @return true si el rango fue registrado, de lo contrario false y se visualiza el error q se haya presentado
     * @author luis.forero(2016-02-03)
     */
    public boolean guardarRectificacionComparendo() {
        logger.debug("RectificarComparendoEcuadorMB::guardarRectificacionComparendo()");

        RectificaComparendoEcuadorFL rectificaComparendoEcuadorFL = findFlowObject(RectificaComparendoEcuadorFL.class,
                RectificaComparendoEcuadorFL.NOMBRE_BEAN);

        rectificaComparendoEcuadorFL.setExisteError(false);
        rectificaComparendoEcuadorFL.getProcesaComparendo().setFechaRecepcion(
                rectificaComparendoEcuadorFL.getFechaRegistro());
        rectificaComparendoEcuadorFL.getProcesaComparendo().setHoraInfraccion(
                rectificaComparendoEcuadorFL.getProcesaComparendo().getFechaInfraccion());
        List<ProcesaComparendoPersonaDTO> procesaComparendoPersonas = new ArrayList<ProcesaComparendoPersonaDTO>();
        ProcesaComparendoPersonaDTO empresaVehiculo = rectificaComparendoEcuadorFL.getEmpresaVehiculo();
        if (empresaVehiculo != null) {
            empresaVehiculo.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.EMPRESA_TRANSPORTE.getValue());
            procesaComparendoPersonas.add(empresaVehiculo);
        }
        ProcesaComparendoPersonaDTO infractor = rectificaComparendoEcuadorFL.getInfractor();
        if (infractor != null) {
            infractor.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR.getValue());
            infractor.setProcesaDireccion(AdminComparendoHelper.toProcesaDireccionDTO(rectificaComparendoEcuadorFL
                    .getDireccionInfractor()));
            procesaComparendoPersonas.add(infractor);
        }
        ProcesaComparendoPersonaDTO propietario = rectificaComparendoEcuadorFL.getPropietario();
        if (propietario != null) {
            propietario.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
            procesaComparendoPersonas.add(propietario);
        }
        ProcesaComparendoPersonaDTO testigo = rectificaComparendoEcuadorFL.getTestigo();
        if (testigo != null) {
            testigo.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO.getValue());
            testigo.setProcesaDireccion(AdminComparendoHelper.toProcesaDireccionDTO(rectificaComparendoEcuadorFL
                    .getDireccionTestigo()));
            procesaComparendoPersonas.add(testigo);
        }
        rectificaComparendoEcuadorFL.getProcesaComparendo().setProcesaComparendoPersonas(procesaComparendoPersonas);
        rectificaComparendoEcuadorFL.getProcesaComparendo().setProcesaEvidencias(
                rectificaComparendoEcuadorFL.getProcesaEvidencias());
        rectificaComparendoEcuadorFL.getProcesaComparendo().setProcesaDireccionComparendo(
                AdminComparendoHelper.toProcesaDireccionDTO(rectificaComparendoEcuadorFL.getDireccionInfraccion()));
        // Direccion de inmovilizacion
        rectificaComparendoEcuadorFL.getProcesaComparendo().setProcesaDireccionPatio(
                AdminComparendoHelper.toProcesaDireccionDTO(rectificaComparendoEcuadorFL.getDireccionInmovilizacion()));
        // Id de la infraccion
        rectificaComparendoEcuadorFL.getProcesaComparendo().setIdInfraccion(
                rectificaComparendoEcuadorFL.getConfiguracionInfraccion().getInfraccion().getId());

        if (!validarCampos(rectificaComparendoEcuadorFL)) {
            return false;
        }

        // Se lleva a cabo la identificacion de los cambios efectuados para procesar el comparendo rectificado
        ProcesaComparendoRectificadoDTO comparendoRectificadoDTO = RectificarComparendoEcuadorUtil
                .procesarCamposCambiados(
                        (ProcesaComparendoRectificadoDTO) rectificaComparendoEcuadorFL.getProcesaComparendo(),
                        rectificaComparendoEcuadorFL, getFormatoFechaCompleto());
        RespuestaValidacionDTO respuestaValidacion = null;

        /*
         * Este caso solo aplicara en caso de que sea una sustitucion en el momento de ingresar una nueva rectificacion tener en cuenta que se debe
         * contemplar esta logica
         */
        boolean esSustitucion = false;
        if (comparendoRectificadoDTO.getCampoRectificaComparendoDTOs() != null
                && !comparendoRectificadoDTO.getCampoRectificaComparendoDTOs().isEmpty()) {
            for (CampoRectificaComparendoDTO campoRectificaComparendoDTO : comparendoRectificadoDTO
                    .getCampoRectificaComparendoDTOs()) {
                if (campoRectificaComparendoDTO.getCampoEntidad().getCodigo()
                        .equals(EnumCampoEntidad.TIPO_DOCUMENTO_INFRACTOR.getValue())
                        || campoRectificaComparendoDTO.getCampoEntidad().getCodigo()
                                .equals(EnumCampoEntidad.NUMERO_DOCUMENTO_INFRACTOR.getValue())) {
                    esSustitucion = true;
                }
            }
        } else {
            addInfoMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_rectificacion");
            return false;
        }
        try {
            if (esSustitucion) {
                respuestaValidacion = recibirComparendoEjb.rectificarComparendo(comparendoRectificadoDTO);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_sustitucion");
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        // Validar respuesta
        if (respuestaValidacion != null) {

            // Se realiza la validacion de errores por pestania para mostrarle al ususario
            rectificaComparendoEcuadorFL.setRespuestaValidacionVO(ComparendosUtil
                    .validarProcesaComparendo(respuestaValidacion));

            rectificaComparendoEcuadorFL.setDetallesProcesamiento(new ArrayList<String>());
            // Validar respuestas de errores
            EnumErrorProcesamiento errorProcesamiento = Utilidades.buscarElemEnum(EnumErrorProcesamiento.class,
                    respuestaValidacion.getCodigoResultado());
            switch (errorProcesamiento) {
            case RECHAZADO:
                // Error bloqueante
                logger.info("Error en procesar: " + respuestaValidacion.getCodigoResultado());
                getFacesContext().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", respuestaValidacion
                                .getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento().getDescripcion()));
                return false;
            case INCONSISTENTE:
                // Errores no bloqueantes
                rectificaComparendoEcuadorFL.setExisteError(true);
                rectificaComparendoEcuadorFL.setErrorProcesamiento(getBundle(NOMBRE_BUNDLE_ADMIN_COMPARENDO).getString(
                        "msg_inconsistente"));
                for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacion
                        .getDetalleProcesamientoDTOs()) {
                    rectificaComparendoEcuadorFL.getDetallesProcesamiento().add(
                            detalleProcesamientoDTO.getErrorProcesamiento().getCodigo() + " - "
                                    + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                    logger.info("Error en procesar: "
                            + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                }
                return false;
            case REGISTRADO:
                if (respuestaValidacion.isExisteAlerta()) {
                    for (DetalleProcesamientoDTO detalleProcesamientoDTO : respuestaValidacion
                            .getDetalleProcesamientoDTOs()) {
                        // Error al guardar documento en el repositorio
                        addWarningMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, detalleProcesamientoDTO
                                .getErrorProcesamiento().getCodigo());
                        logger.info("Alerta en procesar: "
                                + detalleProcesamientoDTO.getErrorProcesamiento().getDescripcion());
                    }
                }
                // Mensaje de guardado exitoso
                addInfoMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_rectificacion_satisfactoria");
                break;
            default:
                break;
            }
        }

        return true;
    }

    /**
     * Valida los datos del formulario
     * 
     * @param RectificaComparendoEcuadorFL
     *            datos a validar
     * @return true si fue correctamente validado, false de lo contrario.
     */
    private boolean validarCampos(RectificaComparendoEcuadorFL rectificaComparendoEcuadorFL) {
        boolean validado = true;
        ProcesaComparendoPersonaDTO infractor = rectificaComparendoEcuadorFL.getInfractor();
        if (infractor != null) {
            if (infractor.getIdTipoIdentificacion() != null && StringUtils.isBlank(infractor.getNumeroIdentificacion())) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_numero_doc_infractor");
                validado = false;
            }
            if (infractor.getIdTipoIdentificacion() == null
                    && StringUtils.isNotBlank(infractor.getNumeroIdentificacion())) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_doc_infractor");
                validado = false;
            }
        }
        return validado;
    }

    /**
     * Permite extraer el formato de fecha completo con el cual se esta trabajando
     * 
     * @return formato de fecha completo dfinido desde los parametros web
     * @author luis.forero(2016-02-08)
     */
    private String getFormatoFechaCompleto() {
        if (formatoFechaCompleto == null) {
            formatoFechaCompleto = getBundle(ConstantesManagedBean.C2Bundles.NOMBRE_BUNDLE_WEB_PARAM).getString(
                    ConstantesManagedBean.ParamsWeb.FORMATO_COMPLETO_FECHA);
        }
        return formatoFechaCompleto;
    }

    /**
     * Permite cargar un archivo al listado de anexos de la solicitud
     * 
     * @param event
     * @author luis.forero(2015-10-14)
     */
    public void cargaArchivoEvidencia(FileUploadEvent event) {
        logger.debug(RectificarComparendoEcuadorMB.class.getName().concat("::cargaArchivoEvidencia()"));
        final UploadedFile file = event.getFile();

        try {
            if (FileValidator.checkFileType(file.getInputstream(), file.getFileName(), ".tiff", ".pdf", ".png", ".jpg")) {

                final ProcesaEvidenciaDTO procesaEvidenciaDTO = new ProcesaEvidenciaDTO();
                final RectificaComparendoEcuadorFL rectificaComparendoEcuadorFL = findFlowObject(
                        RectificaComparendoEcuadorFL.class, RectificaComparendoEcuadorFL.NOMBRE_BEAN);

                Integer idTipoEvidencia = rectificaComparendoEcuadorFL.getIdTipoEvidencia();
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

                List<ProcesaEvidenciaDTO> lstAnexoSolicitud = rectificaComparendoEcuadorFL.getProcesaEvidencias();
                if (lstAnexoSolicitud == null) {
                    lstAnexoSolicitud = new ArrayList<>();
                }

                final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(),
                        file.getContents());

                procesaEvidenciaDTO.setArchivoTransportable(archivo);
                lstAnexoSolicitud.add(procesaEvidenciaDTO);

                rectificaComparendoEcuadorFL.setProcesaEvidencias(lstAnexoSolicitud);
            } else {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_dato");
            }
        } catch (IOException e) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_tipo_dato");
        }

    }

    // ---------- CONSULTAR COMPARENDOS - CARGAR EVIDENCIAS
    public void cargarArchivosEvidencias(RectificaComparendoEcuadorFL detalleComparendoFL) {
        for (EvidenciaDTO evidenciaDTO : detalleComparendoFL.getEvidencias()) {
            try {
                ArchivoTransportableDTO archivo = repositorioArchivoEJB.consultarDocumento(evidenciaDTO
                        .getIdDocumento());
                evidenciaDTO.setArchivoTransportable(archivo);
            } catch (CirculemosAlertaException e) {
                CirculemosErrorHandler.handleException(e);
            }
        }
    }

    public void cargarEvidencia(FileUploadEvent event) {
        logger.debug(RectificarComparendoEcuadorMB.class.getName().concat("::cargaEvidencia()"));

        final UploadedFile file = event.getFile();
        final EvidenciaDTO evidenciaDTO = new EvidenciaDTO();
        final RectificaComparendoEcuadorFL rectificaComparendoEcuadorFL = findFlowObject(
                RectificaComparendoEcuadorFL.class, RectificaComparendoEcuadorFL.NOMBRE_BEAN);

        Integer idTipoEvidencia = rectificaComparendoEcuadorFL.getIdTipoEvidencia();
        if (idTipoEvidencia == null) {
            getFacesContext().addMessage(
                    "form-ingreso:selTipoEvidencia",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }

        evidenciaDTO.setTipoEvidencia(new TipoEvidenciaDTO());
        evidenciaDTO.getTipoEvidencia().setId(idTipoEvidencia);
        evidenciaDTO.getTipoEvidencia().setNombre(
                getCatalogosApp().getItemCatalogo(EnumCatalogo.TipoEvidencia, idTipoEvidencia).getLabel());

        try {
            final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(), file.getContents());
            evidenciaDTO.setArchivoTransportable(archivo);

            String nombreArchivo = comparendoEjb.obtenerNombreEvidencia(evidenciaDTO);
            evidenciaDTO.getArchivoTransportable().setNombre(nombreArchivo);
            evidenciaDTO.setId(nombreArchivo.hashCode());

            List<EvidenciaDTO> lstAnexoSolicitud = rectificaComparendoEcuadorFL.getEvidencias();
            if (lstAnexoSolicitud == null)
                lstAnexoSolicitud = new ArrayList<>();

            lstAnexoSolicitud.add(evidenciaDTO);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
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
    public void eliminarListaDocumentos(RectificaComparendoEcuadorFL rectificaComparendoEcuadorFL) {
        rectificaComparendoEcuadorFL.getProcesaEvidencias().remove(
                rectificaComparendoEcuadorFL.getEvidenciaSeleccionada());
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
     * @author luis.forero(2016-01-29)
     */
    public boolean consultarDetalleComparendo(Long cicomparendo) {
        if (cicomparendo != null) {
            ComparendoDTO detalleComparendo = comparendoEjb.consultarComparendo(cicomparendo);

            if (detalleComparendo != null) {
                RectificaComparendoEcuadorFL rectificaComparendoEcuadorFL = findFlowObject(
                        RectificaComparendoEcuadorFL.class, RectificaComparendoEcuadorFL.NOMBRE_BEAN);
                extraerDetalleRectificarComparendoFL(detalleComparendo, rectificaComparendoEcuadorFL);

                return true;
            }

        }

        addErrorMessage(NOMBRE_BUNDLE_ADMIN_COMPARENDO, "msg_error_detalle_no_encontrado");
        return false;
    }

    private void extraerDetalleRectificarComparendoFL(ComparendoDTO detalleComparendo,
            RectificaComparendoEcuadorFL rectificaComparendoEcuadorFL) {
        // ********************************************************************************
        // ******************************* ENCABEZADO *************************************
        // ********************************************************************************
        ProcesaComparendoRectificadoDTO procesaComparendo = new ProcesaComparendoRectificadoDTO();
        procesaComparendo.setNumeroComparendo(new String(detalleComparendo.getOrdenComparendoNacional()
                .getNumeroComparendo()));
        rectificaComparendoEcuadorFL.setProcesaComparendo(procesaComparendo);
        rectificaComparendoEcuadorFL.getProcesaComparendo().setComparendo(detalleComparendo);

        // Nmero factura axis
        rectificaComparendoEcuadorFL.getProcesaComparendo().setIdFacturaAxis(detalleComparendo.getIdFacturaAxis());

        rectificaComparendoEcuadorFL
                .setFechaRegistro(new java.util.Date(detalleComparendo.getFechaRegistro().getTime()));
        String nomOrganismoTransito = administracionEjb.consultarOrganismoTransito(
                detalleComparendo.getOrdenComparendoNacional().getOrganismoTransito().getCodigoOrganismo())
                .getNombreOrganismo();
        rectificaComparendoEcuadorFL.setNomOrganismoTransito(nomOrganismoTransito);

        procesaComparendo.setIdTipoAgenteImpositor(detalleComparendo.getTipoAgenteImpositorDTO().getId());
        procesaComparendo.setIdActividad(EnumActividad.SUSTITUCION.getValue());

        procesaComparendo.setOrganismoTransito(detalleComparendo.getOrdenComparendoNacional().getOrganismoTransito()
                .clone());
        MedioImposicionComparendoDTO medioImposicion = detalleComparendo.getMedioImposicion();
        if (medioImposicion != null) {
            procesaComparendo.setCodigoMedioImposicion(medioImposicion.getId() != null ? new Integer(medioImposicion
                    .getId()) : null);
        }
        // procesaComparendo.setEsPolca(new Boolean(detalleComparendo.getEsPolca()));
        // rectificaComparendoEcuadorFL.setPolca(procesaComparendo.getEsPolca());
        procesaComparendo.setFechaInfraccion(UtilFecha.setHoraFecha(detalleComparendo.getFechaInfraccion(),
                detalleComparendo.getHoraInfraccion()));
        procesaComparendo.setHoraInfraccion(procesaComparendo.getHoraInfraccion());

        procesaComparendo.setExisteFugaInfractor(new Boolean(detalleComparendo.getExisteFugaInfractor()));

        procesaComparendo.setCodigoInfraccion(new String(detalleComparendo.getInfraccion().getCodigo()));
        ConfiguracionInfraccionDTO configuracionInfraccion = infraccionEjb.consultarInfraccion(detalleComparendo
                .getInfraccion().getCodigo(), detalleComparendo.getFechaInfraccion());
        rectificaComparendoEcuadorFL.setConfiguracionInfraccion(configuracionInfraccion);
        procesaComparendo.setFechaRecepcion(detalleComparendo.getFechaRegistro() != null ? new java.util.Date(
                detalleComparendo.getFechaRegistro().getTime()) : null);
        rectificaComparendoEcuadorFL.setDescripcionInfraccion(new String(configuracionInfraccion.getDescripcion()));

        // Verifica que la configuración del 'Código de la infracción' por el caso de uso 'Administrar infracciones' el campo '¿Requiere
        // placa?'
        // es IGUAL A 'SI'
        rectificaComparendoEcuadorFL.setPlacaRequerida(configuracionInfraccion.getRequierePlaca());

        // Require infractor
        rectificaComparendoEcuadorFL.setRequiereInfractor(configuracionInfraccion.getInfractorObligatorio()
                && !procesaComparendo.getExisteFugaInfractor());

        // Require embriaguez
        rectificaComparendoEcuadorFL.setRequiereEmbriaguez(configuracionInfraccion.getAplicaEmbriaguez());

        // Requiere inmovilizacion
        boolean requiereInmovilizacion = false;
        for (TipoSancionDTO tipoSancion : configuracionInfraccion.getTipoSancionList()) {
            if (EnumTipoSancion.INMOVILIZACION_VEHICULO.getValue().equals(tipoSancion.getId())) {
                requiereInmovilizacion = true;
                break;
            }
        }
        rectificaComparendoEcuadorFL.setRequiereInmovilizacion(requiereInmovilizacion
                && !procesaComparendo.getExisteFugaInfractor());
        rectificaComparendoEcuadorFL.getProcesaComparendo().setInmovilizado(
                requiereInmovilizacion && !procesaComparendo.getExisteFugaInfractor());

        DireccionDTO direccionInfraccion = detalleComparendo.getDireccion().clone();
        rectificaComparendoEcuadorFL.setDireccionInfraccion(direccionInfraccion);
        rectificaComparendoEcuadorFL.setDireccionInfraccionTexto(direccionInfraccion.toString() + " "
                + direccionInfraccion.getComplemento());

        // Tipo de identificacion para empresa
        rectificaComparendoEcuadorFL.setTipoIdentificacionEmpresa(administracionEjb
                .obtenerTipoIdentificacionEmpresaJuridicaPais(getPais().getId()));

        // ********************************************************************************
        // ******************************* VEHICULO ***************************************
        // ********************************************************************************
        ComparendoVehiculoDTO comparendoVehiculo = detalleComparendo.getComparendoVehiculo();
        if (comparendoVehiculo != null) {
            comparendoVehiculo = comparendoVehiculo.clone();
            procesaComparendo.setPlacaVehiculo(comparendoVehiculo.getPlacaVehiculo());
            procesaComparendo.setIdentificacionVehiculo(comparendoVehiculo.getIdentificacionVehiculo());
            OrganismoTransitoDTO organismoMatriVehic = comparendoVehiculo.getOrganismoMatriVehic();
            if (organismoMatriVehic != null) {
                String nomOrganismoMatriculaTransito = administracionEjb.consultarOrganismoTransito(
                        organismoMatriVehic.getCodigoOrganismo()).getNombreOrganismo();
                rectificaComparendoEcuadorFL.setNomOrganismoMatriculaVehiculo(nomOrganismoMatriculaTransito);
                procesaComparendo.setCodigoOrganismoMatriculaVehiculo(organismoMatriVehic.getCodigoOrganismo());
            }
            procesaComparendo.setIdTipoServicio(comparendoVehiculo.getTipoServicio() != null ? comparendoVehiculo
                    .getTipoServicio().getId() : null);
            procesaComparendo.setIdClaseVehiculo(comparendoVehiculo.getClaseVehiculo() != null ? comparendoVehiculo
                    .getClaseVehiculo().getId() : null);
            procesaComparendo.setIdRadioAccion(comparendoVehiculo.getRadioAccion() != null ? comparendoVehiculo
                    .getRadioAccion().getId() : null);
            procesaComparendo.setIdModalidad(comparendoVehiculo.getModalidad() != null ? comparendoVehiculo
                    .getModalidad().getId() : null);
            procesaComparendo
                    .setCodigoTipoTransPasajero(comparendoVehiculo.getTipoTransPasajero() != null ? comparendoVehiculo
                            .getTipoTransPasajero().getId() : null);
            procesaComparendo.setNumeroTarjetaOperacion(comparendoVehiculo.getNumeroTarjetaOperacion());

            rectificaComparendoEcuadorFL.setPlacaVehiculoDeshabilitado(false);
            rectificaComparendoEcuadorFL.setIdVehiculoDeshabilitado(false);

            if (StringUtils.isNotBlank(rectificaComparendoEcuadorFL.getProcesaComparendo().getIdentificacionVehiculo())) {
                rectificaComparendoEcuadorFL.setPlacaVehiculoDeshabilitado(true);
                rectificaComparendoEcuadorFL.setIdVehiculoDeshabilitado(false);
                rectificaComparendoEcuadorFL.getProcesaComparendo().setPlacaVehiculo(null);
            } else if (StringUtils.isNotBlank(rectificaComparendoEcuadorFL.getProcesaComparendo().getPlacaVehiculo())) {
                rectificaComparendoEcuadorFL.setPlacaVehiculoDeshabilitado(false);
                rectificaComparendoEcuadorFL.setIdVehiculoDeshabilitado(true);
                rectificaComparendoEcuadorFL.getProcesaComparendo().setIdentificacionVehiculo(null);
            }

        }
        ComparendoPersonaDTO empresa = detalleComparendo.getEmpresa();
        ProcesaComparendoPersonaDTO empresaVehiculo = new ProcesaComparendoPersonaDTO();
        if (empresa != null) {
            empresa = empresa.clone();
            empresaVehiculo.setIdTipoIdentificacion(empresa.getTipoIdentificacion().getId());
            empresaVehiculo.setNumeroIdentificacion(empresa.getNumeroIdentificacion());
            empresaVehiculo.setRazonSocial(empresa.getRazonSocial());
        }
        rectificaComparendoEcuadorFL.setEmpresaVehiculo(empresaVehiculo);

        // ********************************************************************************
        // ******************************* INFRACTOR **************************************
        // ********************************************************************************
        ComparendoPersonaDTO infractor = detalleComparendo.getInfractor();
        ComparendoPersonaDTO propietario = detalleComparendo.getPropietario();
        if (infractor != null) {
            infractor = infractor.clone();
            ProcesaComparendoPersonaDTO procesaInfractor = RectificarComparendoEcuadorUtil
                    .getProcesaComparendoPersonaDTO(infractor);
            procesaInfractor.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.INFRACTOR.getValue());

            TipoInfractorDTO tipoInfractor = detalleComparendo.getTipoInfractor();
            if (tipoInfractor != null) {
                procesaComparendo.setCodigoTipoInfractor(tipoInfractor.getId() != null ? new Integer(tipoInfractor
                        .getId()) : null);
            }

            // Datos direccion infractor
            DireccionDTO dirInfractor = infractor.getDireccion();
            if (dirInfractor != null) {
                rectificaComparendoEcuadorFL.setDireccionInfractorTexto(dirInfractor.toString());
                rectificaComparendoEcuadorFL.setDireccionInfractor(dirInfractor);
            }

            // determinacion si el infractor es el mismo propietario
            if (propietario != null
                    && procesaInfractor.getIdTipoIdentificacion().equals(propietario.getTipoIdentificacion().getId())
                    && procesaInfractor.getNumeroIdentificacion().equals(propietario.getNumeroIdentificacion())) {
                rectificaComparendoEcuadorFL.setEsPropietario(true);
            } else {
                rectificaComparendoEcuadorFL.setEsPropietario(false);
            }

            // obligatoriedad de campos
            rectificaComparendoEcuadorFL.setRequiereTipoDocInfractor(false);
            rectificaComparendoEcuadorFL.setTipoIdJuridicoInfractor(false);
            if (procesaInfractor.getIdTipoIdentificacion() != null) {
                rectificaComparendoEcuadorFL.setRequiereTipoDocInfractor(true);

                // Si es de tipo juridico para que solo muestre la razon social
                if (procesaInfractor.getIdTipoIdentificacion().equals(
                        rectificaComparendoEcuadorFL.getTipoIdentificacionEmpresa().getId())) {
                    rectificaComparendoEcuadorFL.setTipoIdJuridicoInfractor(true);
                }
            }
            rectificaComparendoEcuadorFL.setInfractor(procesaInfractor);
        }

        // ********************************************************************************
        // ****************************** PROPIETARIO *************************************
        // ********************************************************************************
        ProcesaComparendoPersonaDTO procesaPropietarioDTO = new ProcesaComparendoPersonaDTO();
        procesaPropietarioDTO.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.PROPIETARIO.getValue());
        if (propietario != null) {
            procesaPropietarioDTO = RectificarComparendoEcuadorUtil.getProcesaComparendoPersonaDTO(propietario.clone());

            if (comparendoVehiculo != null) {
                procesaComparendo
                        .setCodigoOrganismoLicenciaTransito(comparendoVehiculo.getOrganismoLicenciaTransito() != null ? comparendoVehiculo
                                .getOrganismoLicenciaTransito().getCodigoOrganismo() : null);

                procesaComparendo.setNumeroLicenciaTransito(comparendoVehiculo.getNumeroLicenciaTransito());
            }

            if (procesaComparendo.getCodigoOrganismoLicenciaTransito() != null) {
                String nomOrganismoLicencia = administracionEjb.consultarOrganismoTransito(
                        procesaComparendo.getCodigoOrganismoLicenciaTransito()).getNombreOrganismo();
                rectificaComparendoEcuadorFL.setNomOrganismoLicencia(nomOrganismoLicencia);
            }

            // Obligatoriedad
            rectificaComparendoEcuadorFL.setRequierePropietario(false);
            rectificaComparendoEcuadorFL.setTipoIdJuridicoPropietario(false);
            if (procesaPropietarioDTO.getIdTipoIdentificacion() != null) {
                rectificaComparendoEcuadorFL.setRequierePropietario(true);

                // Si es de tipo juridico para que solo muestre la razon social
                if (procesaPropietarioDTO.getIdTipoIdentificacion().equals(
                        rectificaComparendoEcuadorFL.getTipoIdentificacionEmpresa().getId())) {
                    rectificaComparendoEcuadorFL.setTipoIdJuridicoPropietario(true);
                }
            }

        }
        rectificaComparendoEcuadorFL.setPropietario(procesaPropietarioDTO);

        // ********************************************************************************
        // ****************************** AGENTE DE TRANSITO ******************************
        // ********************************************************************************
        if (detalleComparendo.getComparendoAgente() != null) {
            ComparendoAgenteDTO comparendoAgente = detalleComparendo.getComparendoAgente().clone();

            // reinicia valores
            procesaComparendo.setIdAgenteTransito(comparendoAgente.getAgente() != null ? comparendoAgente.getAgente()
                    .getId() : null);
            procesaComparendo.setPlacaAgente(comparendoAgente.getPlaca());
            procesaComparendo
                    .setIdTipoIdentificacionAgente(comparendoAgente.getTipoIdentificacionPersona() != null ? comparendoAgente
                            .getTipoIdentificacionPersona().getId() : null);
            procesaComparendo.setNumeroIdentificacionAgente(comparendoAgente.getNumeroIdentificacion());
            procesaComparendo.setNombre1Agente(comparendoAgente.getNombre1());
            procesaComparendo.setNombre2Agente(comparendoAgente.getNombre2());
            procesaComparendo.setApellido1Agente(comparendoAgente.getApellido1());
            procesaComparendo.setApellido2Agente(comparendoAgente.getApellido2());

            if (detalleComparendo.getTipoAgenteImpositorDTO().getId().equals(EnumTipoAgenteImpositor.POLCA.getValue())) {
                procesaComparendo.setNombreResponsable(comparendoAgente.getNombreResponsable());
            } else {
                UnificacionResponsableDTO unificacionResponsable = comparendoAgente.getUnificacionResponsable();
                if (unificacionResponsable != null) {
                    procesaComparendo.setNombreResponsable(unificacionResponsable.getNombre());
                    procesaComparendo.setIdUnificacionResponsable(unificacionResponsable.getId());
                }
            }

            procesaComparendo.setObservacionesAgente(comparendoAgente.getObservacionesAgente());
        }

        rectificaComparendoEcuadorFL.setPlacaAgenteRequerida(false);
        rectificaComparendoEcuadorFL.setRequiereAgenteTransito(false);

        if (StringUtils.isNotEmpty(rectificaComparendoEcuadorFL.getProcesaComparendo().getPlacaAgente())) {
            rectificaComparendoEcuadorFL.setPlacaAgenteRequerida(true);
            rectificaComparendoEcuadorFL.setRequiereAgenteTransito(false);
        } else if (StringUtils.isNotEmpty(rectificaComparendoEcuadorFL.getProcesaComparendo()
                .getNumeroIdentificacionAgente())
                || rectificaComparendoEcuadorFL.getProcesaComparendo().getIdTipoIdentificacionAgente() != null) {
            rectificaComparendoEcuadorFL.setPlacaAgenteRequerida(false);
            rectificaComparendoEcuadorFL.setRequiereAgenteTransito(true);
        }

        // ********************************************************************************
        // ********************************* INMOVILIZACION *******************************
        // ********************************************************************************
        ComparendoPatioDTO comparendoPatio = detalleComparendo.getComparendoPatio();
        if (comparendoPatio != null) {
            comparendoPatio = comparendoPatio.clone();
            procesaComparendo.setNumeroPatio(comparendoPatio.getNumeroPatio());
            procesaComparendo.setNombrePatio(comparendoPatio.getNombre());
            procesaComparendo.setNumeroGrua(comparendoPatio.getNumeroGrua());
            procesaComparendo.setPlacaGrua(comparendoPatio.getPlacaGrua());
            procesaComparendo.setConsecutivoInmovilizacion(comparendoPatio.getConsecutivoAsignadoGrua());

            DireccionDTO dirPatioInmovili = comparendoPatio.getDireccion();
            if (dirPatioInmovili != null) {
                rectificaComparendoEcuadorFL.setDireccionInmovilizacionTexto(dirPatioInmovili.toString());
                rectificaComparendoEcuadorFL.setDireccionInmovilizacion(dirPatioInmovili);
            }
        }

        // ********************************************************************************
        // ********************************* TESTIGO **************************************
        // ********************************************************************************
        ComparendoPersonaDTO testigo = detalleComparendo.getTestigo();
        ProcesaComparendoPersonaDTO procesaTestigo = new ProcesaComparendoPersonaDTO();
        procesaTestigo.setCodigoTipoPersonaComparendo(EnumTipoPersonaComparendo.TESTIGO.getValue());
        if (testigo != null) {
            testigo = testigo.clone();
            procesaTestigo = RectificarComparendoEcuadorUtil.getProcesaComparendoPersonaDTO(testigo);

            rectificaComparendoEcuadorFL.setRequiereTestigo(false);
            if (procesaTestigo.getIdTipoIdentificacion() != null) {
                rectificaComparendoEcuadorFL.setRequiereTestigo(true);
            }

            DireccionDTO dirTestigo = testigo.getDireccion();
            if (dirTestigo != null) {
                rectificaComparendoEcuadorFL.setDireccionTestigoTexto(dirTestigo.toString());
                rectificaComparendoEcuadorFL.setDireccionTestigo(dirTestigo);
            }
        }
        rectificaComparendoEcuadorFL.setTestigo(procesaTestigo);

        // ********************************************************************************
        // ********************************* EMBRIAGUEZ ***********************************
        // ********************************************************************************
        procesaComparendo
                .setNiegaPruebaAlcoholemia(detalleComparendo.getNiegaPruebaAlcoholemia() != null ? new Boolean(
                        detalleComparendo.getNiegaPruebaAlcoholemia()) : null);
        procesaComparendo.setGradoAlcoholemia(detalleComparendo.getGradoAlcoholemia() != null ? new Integer(
                detalleComparendo.getGradoAlcoholemia().getValor()) : null);
        procesaComparendo
                .setFechaPruebaAlcoholemia(detalleComparendo.getFechaPruebaAlcoholemia() != null ? new java.util.Date(
                        detalleComparendo.getFechaPruebaAlcoholemia().getTime()) : null);
        procesaComparendo
                .setNumeroPruebaAlcoholemia(detalleComparendo.getNumeroPruebaAlcoholemia() != null ? new String(
                        detalleComparendo.getNumeroPruebaAlcoholemia()) : null);
        procesaComparendo.setValorGradoAlcoholemia(detalleComparendo.getValorGradoAlcoholemia() != null ? new Integer(
                detalleComparendo.getValorGradoAlcoholemia()) : null);
        procesaComparendo.setNumeroReincidencia(detalleComparendo.getNumeroReincidencia() != null ? new Integer(
                detalleComparendo.getNumeroReincidencia()) : null);
        procesaComparendo.setNumeroCitacion(detalleComparendo.getNumeroCitacion());
        procesaComparendo.setIdEstadoComparendo(detalleComparendo.getEstadoComparendo().getId());

        // ********************************************************************************
        // ********************************* EVIDENCIAS ***********************************
        // ********************************************************************************
        List<EvidenciaDTO> evidenciaList = detalleComparendo.getEvidenciaList();
        if (evidenciaList != null && !evidenciaList.isEmpty()) {
            rectificaComparendoEcuadorFL.setEvidencias(evidenciaList);
        }
    }

    /**
     * Permite cargar el documento asociado al detalle cambio estado del modulo del repositorio de archivos.
     * 
     * @param evidenciaDTO
     *            detalle cambio estado con el id del documento a ser cargado.
     * 
     * @author luis.forero(2015-10-21)
     */
    public void descargarDocumento(EvidenciaDTO evidenciaDTO) {
        logger.debug(ConsultarSeguimientoFormularioMB.class.getName().concat("::descargarDocumento()"));
        ArchivoTransportableDTO archivoTransportableDTO;
        try {
            archivoTransportableDTO = repositorioArchivoEJB.consultarDocumento(evidenciaDTO.getIdDocumento());

            streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                    archivoTransportableDTO.getNombre());

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }
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

    public boolean validaRectificaComparendo(Long cicomparendo) {

        RespuestaValidacionDTO respuestaValidacion = recibirComparendoEjb.validarRectificaComparendo(cicomparendo);

        // Validar respuesta
        if (respuestaValidacion != null) {
            // Validar respuestas de errores
            if (respuestaValidacion.getCodigoResultado() != null
                    && respuestaValidacion.getCodigoResultado().equals(EnumErrorProcesamiento.RECHAZADO.getCodigo())) {
                // Error bloqueante
                logger.info("Error en procesar: " + respuestaValidacion.getCodigoResultado());

                getFacesContext().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", respuestaValidacion
                                .getDetalleProcesamientoDTOs().get(0).getErrorProcesamiento().getDescripcion()));
                return false;
            }
        }
        return true;
    }

    /**
     * Permite identificar si existen o no errores al momento de la rectificacion para ser mostrados.
     * 
     * @return true si debe mostrar false de lo contrario
     */
    public boolean mostrarPopUpErrores() {
        RectificaComparendoEcuadorFL rectificaComparendoEcuadorFL = findFlowObject(RectificaComparendoEcuadorFL.class,
                RectificaComparendoEcuadorFL.NOMBRE_BEAN);
        return rectificaComparendoEcuadorFL.isExisteError();
    }
}
