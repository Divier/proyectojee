package co.com.datatools.c2.managed_bean.formularios.administracion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.ConsultaDetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.DetalleCambioEstadoDTO;
import co.com.datatools.c2.dto.formularios.DocumentoFormularioDTO;
import co.com.datatools.c2.dto.formularios.EstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoDTO;
import co.com.datatools.c2.dto.formularios.RelacionEstadosDTO;
import co.com.datatools.c2.dto.personas.TipoIdentificacionPersonaDTO;
import co.com.datatools.c2.enumeracion.EnumTipoResponsableFormulario;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.formularios.cambio_estados.CambioEstadosFL;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * ManagedBean que gestiona las paginas de administracion de formularios
 * 
 * @author julio.pinzon
 * 
 */
@ManagedBean
@SessionScoped
public class AdminFormularioMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(AdminFormularioMB.class.getName());

    private static final String CONTENT_TYPE = "application/zip";
    private String extensionesPermitidas = "/(\\.|\\/)(rar|zip|7zip)$/";

    @EJB
    private IRAdministracionFormularios administracionFormulariosEjb;

    @EJB
    private IRAdministracion administracionEjb;

    @EJB
    private IRFormulario formulariosEjb;

    @EJB
    private IRRepositorioArchivo iRRepositorioArchivo;

    private UploadedFile documentoSoporte;

    private OrganismoTransitoDTO organismoTransito;
    private static final String NOMBRE_BUNDLE_ADMIN = "labelAdminFormulariosForm";

    public AdminFormularioMB() {
        // Obtener el organismo de transito
        organismoTransito = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
    }

    /**
     * Invoca al metodo de negocio que consulta los formularios que cumplan con los parametros ingresados en la interfaz de usuario y que pertenezcan
     * al organismo de transito del usuario logueado en la sesion. Carga los resultados en el dto de flujo para su visualizacion
     * 
     * @author julio.pinzon
     */
    public void consultar() {
        logger.debug("AdminFormularioMB::consultar()");
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        try {

            adminFormularioHolderFL.setCambioEstadoSeleccionado(null);

            // Valida las fechas
            if (adminFormularioHolderFL.getFechaAplicacionEstadoInicial() == null
                    && adminFormularioHolderFL.getFechaAplicacionEstadoFinal() != null) {
                getFacesContext().addMessage(
                        "form-contenido:fechaAplicacionEstadoInicial",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            } else if (adminFormularioHolderFL.getFechaAplicacionEstadoInicial() != null
                    && adminFormularioHolderFL.getFechaAplicacionEstadoFinal() == null) {
                getFacesContext().addMessage(
                        "form-contenido:fechaAplicacionEstadoFinal",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            } else if (adminFormularioHolderFL.getFechaAplicacionEstadoInicial() != null
                    && adminFormularioHolderFL.getFechaAplicacionEstadoFinal() != null
                    && adminFormularioHolderFL.getFechaAplicacionEstadoInicial().after(
                            adminFormularioHolderFL.getFechaAplicacionEstadoFinal())) {
                addErrorMessage(NOMBRE_BUNDLE_ADMIN, "msg_error_fecha_inicial_mayor");
                return;
            }
            adminFormularioHolderFL.setResultadoConsulta(new ArrayList<DetalleCambioEstadoDTO>());
            // Crear el dto con los parametros de la consulta, minimo se envia el tipo de formulario y el estado de los formularios
            ConsultaDetalleCambioEstadoDTO consultaDetalleCambioEstadoDTO = new ConsultaDetalleCambioEstadoDTO();
            consultaDetalleCambioEstadoDTO.setIdTipoFormulario(adminFormularioHolderFL.getIdTipoFormulario());
            consultaDetalleCambioEstadoDTO.setIdEstado(adminFormularioHolderFL.getIdEstadoFormulario());
            // Agregar los filtros ingresados por el usuario a los parametros de la consulta
            consultaDetalleCambioEstadoDTO.setIdTipoIdentificacion(adminFormularioHolderFL.getIdTipoDocResponsable());
            consultaDetalleCambioEstadoDTO.setNumeroIdentificacion(adminFormularioHolderFL.getNumeroDocResponsable());
            consultaDetalleCambioEstadoDTO.setOrganismoResponsable(adminFormularioHolderFL
                    .getCodigoOrganismoResponsable());
            consultaDetalleCambioEstadoDTO.setFechaDesde(adminFormularioHolderFL.getFechaAplicacionEstadoInicial());
            consultaDetalleCambioEstadoDTO.setFechaHasta(adminFormularioHolderFL.getFechaAplicacionEstadoFinal());
            consultaDetalleCambioEstadoDTO.setCodigoOrganismo(getCodigoOrganismoTransito());
            consultaDetalleCambioEstadoDTO.setIdTipoResponsable(adminFormularioHolderFL.getIdTipoResponsable());

            List<DetalleCambioEstadoDTO> resultados = formulariosEjb
                    .consultarDetalleCambioEstado(consultaDetalleCambioEstadoDTO);
            adminFormularioHolderFL.setResultadoConsulta(resultados);
            adminFormularioHolderFL.setEsConsultar(true);
            if (resultados.isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(resultados.size());
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public void cambioTipoResponsable() {
        logger.debug("AdminFormularioMB::cambioTipoResponsable()");
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        adminFormularioHolderFL.setEmpresa(false);
        adminFormularioHolderFL.setOrganismo(false);
        if (adminFormularioHolderFL.getIdTipoResponsable() != null
                && adminFormularioHolderFL.getIdTipoResponsable().equals(
                        EnumTipoResponsableFormulario.EMPRESA.getValue())) {
            adminFormularioHolderFL.setEmpresa(true);
        }
        if (adminFormularioHolderFL.getIdTipoResponsable() != null
                && adminFormularioHolderFL.getIdTipoResponsable().equals(
                        EnumTipoResponsableFormulario.ORGANISMO_TRANSITO.getValue())) {
            adminFormularioHolderFL.setOrganismo(true);
        }
        if (adminFormularioHolderFL.getIdTipoDocResponsable() == null) {
            // Tipo de identificacion para empresa
            TipoIdentificacionPersonaDTO tipoIdentificacionEmpresa = administracionEjb
                    .obtenerTipoIdentificacionEmpresaJuridicaPais(getPais().getId());
            adminFormularioHolderFL.setIdOrganismoTransito(getCodigoOrganismoTransito());
            adminFormularioHolderFL.setIdTipoDocResponsable(tipoIdentificacionEmpresa.getId());
            adminFormularioHolderFL.setNombreTipoDocResponsable(tipoIdentificacionEmpresa.getNombre());
        }

    }

    /**
     * Se encarga de la logica anulacion de un formulario
     * 
     * @author giovanni.velandia
     */
    public void anularFormulario() {
        logger.debug("AdminFormularioMB::anularFormulario");
        verDetalleCambioEstado();
        CambioEstadosFL cambioEstadosFL = findFlowObject(CambioEstadosFL.class, CambioEstadosFL.NOMBRE_BEAN);
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        // Id Estado formulario
        cambioEstadosFL.getEstadoFormularioDTO().setId(EnumEstadoFomulario.ANULADO.getValue());
        cambioEstadosFL.getCambioEstadoFormularioDTO().setIdEstadoFinal(EnumEstadoFomulario.ANULADO.getValue());
        // Nombre del estado seleccionado
        cambioEstadosFL.getEstadoFormularioDTO().setNombre(EnumEstadoFomulario.ANULADO.getDescripcion());
        // Fecha y hora de aplicacion de estado
        cambioEstadosFL.getCambioEstadoFormularioDTO().setFechaAplicacionEstado(Calendar.getInstance().getTime());

        // Fecha del sistema
        cambioEstadosFL.setFechamaxima(Calendar.getInstance().getTime());

        /*
         * Ingresamos el primer rango de intervalos para los numeros a aplicar el estado
         */
        cambioEstadosFL.getCambioEstadoFormularioDTO().setListRangoDTO(new ArrayList<RangoDTO>());
        RangoDTO rangoDTO = new RangoDTO(adminFormularioHolderFL.getCambioEstadoSeleccionado().getNumeroInicial(),
                adminFormularioHolderFL.getCambioEstadoSeleccionado().getNumeroFinal());
        cambioEstadosFL.getCambioEstadoFormularioDTO().getListRangoDTO().add(rangoDTO);
        adminFormularioHolderFL.setCantidadFormularios(adminFormularioHolderFL.getCambioEstadoSeleccionado()
                .getCantidadFormularios());
    }

    /**
     * Se encarga de la logica de devolucion de un formulario
     * 
     * @author giovanni.velandia
     */
    public void devolverFormulario() {
        logger.debug("AdminFormularioMB::devolverFormulario");
        verDetalleCambioEstado();
        CambioEstadosFL cambioEstadosFL = findFlowObject(CambioEstadosFL.class, CambioEstadosFL.NOMBRE_BEAN);
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);

        // Id Estado formulario
        cambioEstadosFL.getEstadoFormularioDTO().setId(EnumEstadoFomulario.DEVUELTO.getValue());
        cambioEstadosFL.getCambioEstadoFormularioDTO().setIdEstadoFinal(EnumEstadoFomulario.DEVUELTO.getValue());
        // Nombre del estado seleccionado
        cambioEstadosFL.getEstadoFormularioDTO().setNombre(EnumEstadoFomulario.DEVUELTO.getDescripcion());
        // Fecha y hora de aplicacion de estado
        cambioEstadosFL.getCambioEstadoFormularioDTO().setFechaAplicacionEstado(Calendar.getInstance().getTime());

        // Fecha del sistema
        cambioEstadosFL.setFechamaxima(Calendar.getInstance().getTime());

        /*
         * Ingresamos el primer rango de intervalos para los numeros a aplicar el estado
         */
        cambioEstadosFL.getCambioEstadoFormularioDTO().setListRangoDTO(new ArrayList<RangoDTO>());
        RangoDTO rangoDTO = new RangoDTO(adminFormularioHolderFL.getCambioEstadoSeleccionado().getNumeroInicial(),
                adminFormularioHolderFL.getCambioEstadoSeleccionado().getNumeroFinal());
        cambioEstadosFL.getCambioEstadoFormularioDTO().getListRangoDTO().add(rangoDTO);
        adminFormularioHolderFL.setCantidadFormularios(adminFormularioHolderFL.getCambioEstadoSeleccionado()
                .getCantidadFormularios());
    }

    /**
     * Metodo que se encarga de consultar todo el detalle de cambio estado por el id seleccionado en la consulta principal
     * 
     * @author giovanni.velandia
     */
    public void verDetalleCambioEstado() {
        logger.debug("AdminFormularioMB::verDetalleCambioEstado");
        try {
            AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                    AdminFormularioHolderFL.NOMBRE_BEAN);
            adminFormularioHolderFL.setCambioEstadoSeleccionado(formulariosEjb
                    .consultarDetalleCambioEstado(adminFormularioHolderFL.getCambioEstadoSeleccionado().getId()));

            if (adminFormularioHolderFL.getCambioEstadoSeleccionado().getDocumentoFormulario() == null) {
                adminFormularioHolderFL.getCambioEstadoSeleccionado().setDocumentoFormulario(
                        new DocumentoFormularioDTO());
            }

            /*
             * Control del estado para mostrar la informacion para cada tipo responsable
             */
            if (EnumTipoResponsableFormulario.EMPRESA.getValue().equals(
                    adminFormularioHolderFL.getCambioEstadoSeleccionado().getResponsableFormulario()
                            .getTipoResponsable().getId())) {
                adminFormularioHolderFL.getCambioEstadoSeleccionado().setEmpresa(true);
                adminFormularioHolderFL.getCambioEstadoSeleccionado().setOrganismo(false);
            } else {
                adminFormularioHolderFL.getCambioEstadoSeleccionado().setEmpresa(false);
                adminFormularioHolderFL.getCambioEstadoSeleccionado().setOrganismo(true);
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Metodo que se encarga de descargar uun documento soporte
     * 
     * @return StreamedContent
     * @author giovanni.velandia
     */
    public StreamedContent descargarDocumento() {
        logger.debug("AdminFormularioMB::descargarDocumento()");
        StreamedContent streamedContent = null;
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        ArchivoTransportableDTO archivoTransportableDTO;
        try {
            archivoTransportableDTO = iRRepositorioArchivo.consultarDocumento(adminFormularioHolderFL
                    .getCambioEstadoSeleccionado().getDocumentoFormulario().getIdDocumento());

            streamedContent = new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                    archivoTransportableDTO.getNombre());
            // InputStream inputStream = new ByteArrayInputStream(archivoTransportableDTO.getContenido());
            // streamedContent = new DefaultStreamedContent(inputStream);

        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }

        return streamedContent;

    }

    public boolean registrarAsignacion() {
        logger.debug("AdminFormularioMB::registrarAsignacion");

        // Validar cfg relacion estados: de pdte a asignado
        RelacionEstadosDTO relEstDTO = new RelacionEstadosDTO();
        relEstDTO.setEstadoFormularioActual(new EstadoFormularioDTO(EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR
                .getValue()));
        relEstDTO.setEstadoFormularioSiguiente(new EstadoFormularioDTO(EnumEstadoFomulario.ASIGNADO.getValue()));
        List<RelacionEstadosDTO> relacionEstadosList = administracionFormulariosEjb
                .consultarRelacionesEstados(relEstDTO);

        if (relacionEstadosList.isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN, "titulo_ingresar_asignacion", NOMBRE_BUNDLE_ADMIN, "msg_pdte_asignado");
            return false;
        }

        // Validar cfg relacion estados: de devuelto a asignado
        relEstDTO.setEstadoFormularioActual(new EstadoFormularioDTO(EnumEstadoFomulario.DEVUELTO.getValue()));
        relacionEstadosList = administracionFormulariosEjb.consultarRelacionesEstados(relEstDTO);

        if (relacionEstadosList.isEmpty()) {
            addErrorMessage(NOMBRE_BUNDLE_ADMIN, "titulo_ingresar_asignacion", NOMBRE_BUNDLE_ADMIN,
                    "msg_devuelto_asignado");
            return false;
        }

        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        adminFormularioHolderFL.setIdEstadoFormulario(EnumEstadoFomulario.ASIGNADO.getValue());

        return true;
    }

    /**
     * Valida las posibles opciones de los formularios en la consulta
     * 
     * @author giovanni.velandia
     */
    public void validarOpcionesFormularios() {
        logger.debug("AdminFormularioMB::validarOpcionesFormularios()");
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);

        // Estado Anular
        if (adminFormularioHolderFL.getCambioEstadoSeleccionado().getEstadoFormulario().getId()
                .equals(EnumEstadoFomulario.ANULADO.getValue())) {
            adminFormularioHolderFL.setEsAnular(true);
            adminFormularioHolderFL.setEsDevolver(true);
            adminFormularioHolderFL.setEsEditar(true);
        }

        // Estado devolver
        if (adminFormularioHolderFL.getCambioEstadoSeleccionado().getEstadoFormulario().getId()
                .equals(EnumEstadoFomulario.DEVUELTO.getValue())) {
            adminFormularioHolderFL.setEsAnular(true);
            adminFormularioHolderFL.setEsDevolver(true);
            adminFormularioHolderFL.setEsEditar(false);
        }

        // Estado Asignado
        if (adminFormularioHolderFL.getCambioEstadoSeleccionado().getEstadoFormulario().getId()
                .equals(EnumEstadoFomulario.ASIGNADO.getValue())) {
            adminFormularioHolderFL.setEsAnular(false);
            adminFormularioHolderFL.setEsDevolver(false);
            adminFormularioHolderFL.setEsEditar(false);
        }
    }

    /**
     * Permite llevar a cabo la actualizacion de la informacion de una asignacion o una devolucion
     * 
     * @author divier.casas
     */
    public void actualizarInformacionEstado() {
        logger.debug("AdminFormularioMB::actualizarInformacionEstado");
        final AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        ArchivoTransportableDTO archivo = null;

        if (getDocumentoSoporte() != null) {
            archivo = new ArchivoTransportableDTO(getDocumentoSoporte().getFileName(), getDocumentoSoporte()
                    .getContents());
        }

        try {
            formulariosEjb
                    .actualizarDetalleCambioEstado(adminFormularioHolderFL.getCambioEstadoSeleccionado(), archivo);
            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
        archivo = null;
        setDocumentoSoporte(null);
    }

    /**
     * Maneja el evento de cargar el archivo asociado al documento de soporte
     * 
     * @param event
     *            Evento de cargue del archivo
     * @author divier.casas
     */
    public void cargarArchivo(FileUploadEvent event) {
        logger.info("AdminFormularioMB::cargarArchivo");
        UploadedFile archivo = event.getFile();
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        setDocumentoSoporte(archivo);
        adminFormularioHolderFL.setNombreArchivo(archivo.getFileName());
    }

    public String getExtensionesPermitidas() {
        return extensionesPermitidas;
    }

    public void setExtensionesPermitidas(String extensionesPermitidas) {
        this.extensionesPermitidas = extensionesPermitidas;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public UploadedFile getDocumentoSoporte() {
        return documentoSoporte;
    }

    public void setDocumentoSoporte(UploadedFile documentoSoporte) {
        this.documentoSoporte = documentoSoporte;
    }
}
