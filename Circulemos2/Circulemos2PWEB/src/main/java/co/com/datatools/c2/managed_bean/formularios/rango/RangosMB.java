package co.com.datatools.c2.managed_bean.formularios.rango;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.formularios.ConsultaRangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.FormularioDTO;
import co.com.datatools.c2.dto.formularios.NumeracionFormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * ManagedBean que gestiona las paginas de administracion de rangos de formularios
 * 
 * @author claudia.rodriguez
 * 
 */
@ManagedBean
@SessionScoped
public class RangosMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(RangosMB.class.getName());

    private String extensionesPermitidas = "/(\\.|\\/)(rar|zip|7zip)$/";

    private int tamanioMaximoArch;
    private static final String CONTENT_TYPE = "application/zip";

    @EJB
    private IRAdministracionFormularios administracionFormulariosEjb;

    @EJB
    private IRFormulario formulariosEjb;

    @EJB
    private IRParametro parametroEjb;

    private static final String NOMBRE_BUNDLE_RANGO = "labelRango";

    private UploadedFile documentoAutorizacion;
    private String nombreArchivo;

    public RangosMB() {
    }

    @PostConstruct
    public void init() {
        /*
         * Carga de parametros de sistema
         */
        ValorParametroDTO valTamMaxAdjArch = parametroEjb.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
    }

    /**
     * Maneja el evento de cargar el archivo de autorizacion del rango
     * 
     * @param event
     *            Evento de cargue del archivo
     * @author claudia.rodriguez
     */
    public void cargarArchivo(FileUploadEvent event) {
        logger.info("RangosMB::cargarArchivo");
        UploadedFile archivo = event.getFile();
        setDocumentoAutorizacion(archivo);
        setNombreArchivo(archivo.getFileName());
    }

    /**
     * Invoca al metodo de negocio que consulta los rangos de formularios que cumplan con los parametros ingresados en la interfaz de usuario y que
     * pertenezcan al organismo de transito del usuario logueado en la sesion. Carga los resultados en el dto de flujo para su visualizacion
     * 
     * @author claudia.rodriguez
     */
    public void consultar() {
        logger.debug("RangosMB::consultar()");
        RangoHolderFL rangoHolderFL = findFlowObject(RangoHolderFL.class, RangoHolderFL.NOMBRE_BEAN);
        rangoHolderFL.setRangoSeleccionado(null);

        ConsultaRangoFormularioDTO consultaRangoFormularioDTO = new ConsultaRangoFormularioDTO();
        consultaRangoFormularioDTO.setFechaAutorizacionFinal(rangoHolderFL.getFechaAutorizacionFinal());
        consultaRangoFormularioDTO.setFechaAutorizacionInicial(rangoHolderFL.getFechaAutorizacionInicial());
        // consultaRangoFormularioDTO.setIdEstadoRango(rangoHolderFL.getIdEstadoRango());
        consultaRangoFormularioDTO.setIdTipoFormulario(rangoHolderFL.getIdTipoFormulario());
        consultaRangoFormularioDTO.setNumeroFinal(rangoHolderFL.getNumeroFinal());
        consultaRangoFormularioDTO.setNumeroInicial(rangoHolderFL.getNumeroInicial());
        List<RangoFormularioDTO> resultado;
        try {
            resultado = formulariosEjb.consultarRangoFormulario(consultaRangoFormularioDTO);
            rangoHolderFL.setResultadoConsulta(resultado);
            if (resultado.isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(resultado.size());
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Invoca al metodo de negocio que elimina un rango de formulario
     */
    public void eliminar() {
        logger.debug("RangosMB::eliminar()");
        RangoHolderFL rangoHolderFL = findFlowObject(RangoHolderFL.class, RangoHolderFL.NOMBRE_BEAN);
        try {
            formulariosEjb.eliminarRangoFormulario(rangoHolderFL.getRangoSeleccionado().getId());
            CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
            // Eliminar el rango de lso resultados de consulta
            rangoHolderFL.getResultadoConsulta().remove(rangoHolderFL.getRangoSeleccionado());
            rangoHolderFL.setRangoSeleccionado(null);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }

    }

    /**
     * Carga el detalle del rango seleccionado en la interfaz de usuario en la pagina de edicion de datos, siempre y cuando el rango seleccionado sea
     * editable(es decir tiene estado pendiente por asignar) que no haya detalles
     * 
     * @return true si se cargan los datos del rango para su edición, false si el rango no tiene estado=pendiente por asignar y no puede editarse
     */
    public boolean cargarDetalleRango() {
        logger.debug("RangosMB::cargarDetalleRango()");
        RangoHolderFL rangoHolderFL = findFlowObject(RangoHolderFL.class, RangoHolderFL.NOMBRE_BEAN);
        // Validar que el rango sea editable=con estado Pendiente por asignar
        if (!rangoHolderFL.getRangoSeleccionado().isTieneDetalles()) {
            RangoFL rangoFL = findFlowObject(RangoFL.class, RangoFL.NOMBRE_BEAN);
            rangoFL.setRangoModificar(rangoHolderFL.getRangoSeleccionado());
            if (rangoHolderFL.getRangoSeleccionado().getArchivoAutorizacion() != null) {
                setNombreArchivo(rangoHolderFL.getRangoSeleccionado().getArchivoAutorizacion().getNombre());
            }
            return true;
        } else {
            addErrorMessage(NOMBRE_BUNDLE_RANGO, "msg_rango_no_editable");
            return false;
        }
    }

    /**
     * Invoca al metodo de negocio que realiza la actualizacion de los datos de un rango
     */
    public void modificar() {
        logger.debug("RangosMB::modificar()");
        RangoFL rangoFL = findFlowObject(RangoFL.class, RangoFL.NOMBRE_BEAN);

        if (getDocumentoAutorizacion() != null) {
            ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(getDocumentoAutorizacion().getFileName(),
                    getDocumentoAutorizacion().getContents());
            rangoFL.getRangoModificar().setArchivoAutorizacion(archivo);
        } else {
            rangoFL.getRangoModificar().setArchivoAutorizacion(null);
        }

        try {
            // La numeracion del rango es del organismo de transito del usuario en sesion
            formulariosEjb.actualizarRangoFormulario(rangoFL.getRangoModificar());
            // Actualiza el nombre dado al documento en el gestor
            if (getDocumentoAutorizacion() != null) {
                ConsultaRangoFormularioDTO consulta = new ConsultaRangoFormularioDTO();
                consulta.setIdRango(rangoFL.getRangoModificar().getId());
                List<RangoFormularioDTO> rangos = formulariosEjb.consultarRangoFormulario(consulta);
                if (!rangos.isEmpty() && rangos.get(0).getArchivoAutorizacion() != null) {
                    setNombreArchivo(rangos.get(0).getArchivoAutorizacion().getNombre());
                }
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return;
        } catch (CirculemosAlertaException e) {
            // Error al guardar documento en el repositorio
            addWarningMessage(NOMBRE_BUNDLE_RANGO, "msg_error_guardar_archivo");
        }
        // Mensaje de guardado exitoso
        addInfoMessage(NOMBRE_BUNDLE_RANGO, "msg_rango_guardado");
    }

    /**
     * Invoca al metodo de negocio que registra un nuevo rango de formulario
     * 
     * @return true si el rango fue registrado, de lo contrario false y se visualiza el error q se haya presentado
     */
    public boolean registrar() {
        logger.debug("RangosMB::cargarregistrar()");
        RangoFL rangoFL = findFlowObject(RangoFL.class, RangoFL.NOMBRE_BEAN);

        RangoFormularioDTO rangoFormularioDTO = new RangoFormularioDTO();
        rangoFormularioDTO.setFechaAutorizacion(rangoFL.getFechaAutorizacion());

        if (getDocumentoAutorizacion() != null) {
            ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(getDocumentoAutorizacion().getFileName(),
                    getDocumentoAutorizacion().getContents());
            rangoFormularioDTO.setArchivoAutorizacion(archivo);
        }
        NumeracionFormularioDTO numeracion = new NumeracionFormularioDTO();
        rangoFormularioDTO.setNumeracion(numeracion);
        rangoFormularioDTO.setNumeroFinal(rangoFL.getNumeroFinal());
        rangoFormularioDTO.setNumeroInicial(rangoFL.getNumeroInicial());
        TipoFormularioDTO tipoFormulario = new TipoFormularioDTO();
        tipoFormulario.setId(rangoFL.getIdTipoFormulario());
        rangoFormularioDTO.setTipoFormulario(tipoFormulario);
        rangoFormularioDTO.setCodigoOrganismo(getOrganismoTransito());
        try {
            formulariosEjb.registrarRangoFormulario(rangoFormularioDTO);
            limpiarDocumento();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        } catch (CirculemosAlertaException e) {
            // Error al guardar documento en el repositorio
            addWarningMessage(NOMBRE_BUNDLE_RANGO, "msg_error_guardar_archivo");
        } finally {
            rangoFormularioDTO.setArchivoAutorizacion(null);
        }
        // Mensaje de guardado exitoso
        addInfoMessage(NOMBRE_BUNDLE_RANGO, "msg_rango_guardado");
        return true;
    }

    /**
     * Reconstruye el detalle de todos los numeros de formulario comprendidos en el rango seleccionado para su visualizacion en la interfaz de usuario
     */
    public void verDetalle() {
        logger.debug("RangosMB::verDetalle()");
        RangoHolderFL rangoHolderFL = findFlowObject(RangoHolderFL.class, RangoHolderFL.NOMBRE_BEAN);
        RangoFormularioDTO rangoDetallado = formulariosEjb.consultarDetalleRangoFormulario(rangoHolderFL
                .getRangoSeleccionado().getId());
        // Consultar la configuracion de la numeracion para saber como debe variar cada posicion del numero de formulario
        rangoDetallado.getNumeracion().setDetalleNumeracionList(
                administracionFormulariosEjb.consultarDetalleNumeracionFormulario(rangoDetallado.getNumeracion()
                        .getId()));

        // Construir todos los numeros de formularios
        SortedMap<String, String> formularios = new TreeMap<String, String>();
        String numeroInicial = rangoHolderFL.getRangoSeleccionado().getNumeroInicial();
        String numeroFinal = rangoHolderFL.getRangoSeleccionado().getNumeroFinal();

        List<String> numerosRango = formulariosEjb.listarNumerosRango(numeroInicial, numeroFinal,
                rangoDetallado.getNumeracion());
        // Asumir que todos los numeros posibles dentro del rango estan pendientes por asignar
        for (String numero : numerosRango) {
            formularios.put(numero, EnumEstadoFomulario.PENDIENTE_POR_ASIGNAR.getDescripcion());
        }
        // Combinar todos los numeros posibles en pendiente por asignar con los numeros de formularios que ya estan creados en la base de datos con
        // otro estado, de tal manera que éstos reeemplazan a las llaves ingresadas como pendientes por asignar
        for (FormularioDTO formularioDto : rangoDetallado.getFormularioList()) {
            formularios.put(formularioDto.getNumeroFormulario(), formularioDto.getEstadoFormulario().getNombre());
        }
        rangoHolderFL.setDetalleFormularios(formularios);

    }

    /**
     * Permite la descarga del archivo comprimido que corresponde al documento de autorizacion del rango seleccionado en la interfaz de usuario
     * 
     * @param rango
     *            Rango sobre el cual se reaiza la descarga del documento de autorizacion
     * @return Archivo para la descarga
     */
    public StreamedContent descargarArchivo(RangoFormularioDTO rango) {
        byte[] contenidoArchivo = rango.getArchivoAutorizacion().getContenido();
        String nombre = rango.getArchivoAutorizacion().getNombre();
        return new DefaultStreamedContent(new ByteArrayInputStream(contenidoArchivo), CONTENT_TYPE, nombre);
    }

    public String getExtensionesPermitidas() {
        return extensionesPermitidas;
    }

    public void setExtensionesPermitidas(String extensionesPermitidas) {
        this.extensionesPermitidas = extensionesPermitidas;
    }

    public UploadedFile getDocumentoAutorizacion() {
        return documentoAutorizacion;
    }

    public void setDocumentoAutorizacion(UploadedFile documentoAutorizacion) {
        this.documentoAutorizacion = documentoAutorizacion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void limpiarDocumento() {
        setDocumentoAutorizacion(null);
        setNombreArchivo(null);
    }

    public int getTamanioMaximoArch() {
        return tamanioMaximoArch;
    }

    public void setTamanioMaximoArch(int tamanioMaximoArch) {
        this.tamanioMaximoArch = tamanioMaximoArch;
    }

    public String getMesErrLimitSize() {
        return MessageFormat.format(getBundle(NOMBRE_BUNDLE_RANGO).getString("msg_error_tamanio_archivo"),
                tamanioMaximoArch);
    }

}
