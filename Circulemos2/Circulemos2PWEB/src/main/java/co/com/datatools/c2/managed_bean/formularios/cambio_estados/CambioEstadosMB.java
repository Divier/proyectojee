package co.com.datatools.c2.managed_bean.formularios.cambio_estados;

import java.text.MessageFormat;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.dto.formularios.CambioEstadoFormularioDTO;
import co.com.datatools.c2.dto.formularios.ConsultaRangoTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.RangoDTO;
import co.com.datatools.c2.enumeraciones.EnumEstadoFomulario;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.managed_bean.formularios.administracion.AdminFormularioHolderFL;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Se encarga solamente de administrar los estados de devolucion y anulacion de un formulario
 * 
 * @author giovanni.velandia mod(15-09-2015)
 * 
 */
@ManagedBean
@SessionScoped
public class CambioEstadosMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(CambioEstadosMB.class.getName());

    private String extensionesPermitidas = "/(\\.|\\/)(rar|zip|7zip)$/";
    private static final String CONTENT_TYPE = "application/zip";

    @EJB
    private IRFormulario iRFormulario;

    private static final String NOMBRE_BUNDLE_FORM = "labelAdminFormulariosForm";
    private static final String DEVOLUCION = "devolución";
    private static final String ANULACION = "anulación";

    private UploadedFile documentoSoporteUPF;

    public CambioEstadosMB() {

    }

    /**
     * Adiciona un rango para procesar el estado
     * 
     * @author giovanni.velandia
     */
    public void adicionarRango() {
        LOGGER.debug("AdminFormularioMB::adicionarRango()");
        CambioEstadosFL cambioEstadosFL = findFlowObject(CambioEstadosFL.class, CambioEstadosFL.NOMBRE_BEAN);
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        RangoDTO rangoDTO = new RangoDTO(adminFormularioHolderFL.getCambioEstadoSeleccionado().getNumeroInicial(),
                adminFormularioHolderFL.getCambioEstadoSeleccionado().getNumeroFinal());
        cambioEstadosFL.getCambioEstadoFormularioDTO().getListRangoDTO().add(rangoDTO);
        adminFormularioHolderFL.setCantidadFormularios(adminFormularioHolderFL.getCantidadFormularios()
                + adminFormularioHolderFL.getCambioEstadoSeleccionado().getCantidadFormularios());
    }

    /**
     * Adiciona un rango para procesar el estado
     * 
     * @author giovanni.velandia
     */
    public void eliminarRango(RangoDTO rangoDTO) {
        LOGGER.debug("AdminFormularioMB::adicionarRango()");
        CambioEstadosFL cambioEstadosFL = findFlowObject(CambioEstadosFL.class, CambioEstadosFL.NOMBRE_BEAN);
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);

        /*
         * verificar el contador de cantidad de formularios
         */
        ConsultaRangoTipoFormularioDTO consultaRangoTipoFormularioDTO = new ConsultaRangoTipoFormularioDTO();
        consultaRangoTipoFormularioDTO.setCodigoOrganismo(adminFormularioHolderFL.getIdOrganismoTransito());
        consultaRangoTipoFormularioDTO.setIdTipoFormulario(adminFormularioHolderFL.getIdTipoFormulario());
        consultaRangoTipoFormularioDTO.setRangoDTOs(new ArrayList<RangoDTO>());
        consultaRangoTipoFormularioDTO.getRangoDTOs().add(
                new RangoDTO(rangoDTO.getNumeroInicial(), rangoDTO.getNumeroFinal()));
        consultaRangoTipoFormularioDTO.setIdDetalleCambioEstado(adminFormularioHolderFL.getCambioEstadoSeleccionado()
                .getId());

        cambioEstadosFL.getCambioEstadoFormularioDTO().getListRangoDTO().remove(rangoDTO);

        try {
            adminFormularioHolderFL.setCantidadFormularios(adminFormularioHolderFL.getCantidadFormularios()
                    - iRFormulario.calcularFormularios(consultaRangoTipoFormularioDTO));
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            adminFormularioHolderFL.setCantidadFormularios(adminFormularioHolderFL.getCantidadFormularios()
                    - adminFormularioHolderFL.getCambioEstadoSeleccionado().getCantidadFormularios());
        }
    }

    /**
     * Me devuelve la cantida de formularios para todos los intervalos
     * 
     * @author giovanni.velandia
     */
    public void cantidadFormulariosIntervalos() {
        LOGGER.debug("AdminFormularioMB::catidadFormulariosIntervalos()");
        CambioEstadosFL cambioEstadosFL = findFlowObject(CambioEstadosFL.class, CambioEstadosFL.NOMBRE_BEAN);
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);

        int cantidadFormularios = 0;

        ConsultaRangoTipoFormularioDTO consultaRangoTipoFormularioDTO = new ConsultaRangoTipoFormularioDTO();
        consultaRangoTipoFormularioDTO.setCodigoOrganismo(adminFormularioHolderFL.getIdOrganismoTransito());
        consultaRangoTipoFormularioDTO.setIdTipoFormulario(adminFormularioHolderFL.getIdTipoFormulario());
        consultaRangoTipoFormularioDTO.setRangoDTOs(cambioEstadosFL.getCambioEstadoFormularioDTO().getListRangoDTO());
        consultaRangoTipoFormularioDTO.setIdDetalleCambioEstado(adminFormularioHolderFL.getCambioEstadoSeleccionado()
                .getId());
        try {
            cantidadFormularios = iRFormulario.calcularFormularios(consultaRangoTipoFormularioDTO);
            adminFormularioHolderFL.setCantidadFormularios(cantidadFormularios);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Metodo que se encarga de cambiar el estado del formulario
     * 
     * @author giovanni.velandia
     */
    public boolean cambiarEstadoFormularios() {
        LOGGER.debug("AdminFormularioMB::cambiarEstadoFormularios()");
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        CambioEstadosFL cambioEstadosFL = findFlowObject(CambioEstadosFL.class, CambioEstadosFL.NOMBRE_BEAN);

        // Codigo Organismo
        cambioEstadosFL.getCambioEstadoFormularioDTO().setCodigoOrganismo(
                adminFormularioHolderFL.getIdOrganismoTransito());
        // Detalle cambio estado
        cambioEstadosFL.getCambioEstadoFormularioDTO().setIdDetalleCambioEstado(
                adminFormularioHolderFL.getCambioEstadoSeleccionado().getId());

        if (documentoSoporteUPF != null) {

            ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(documentoSoporteUPF.getFileName(),
                    documentoSoporteUPF.getContents());
            cambioEstadosFL.getCambioEstadoFormularioDTO().setArchivoTransportableDTO(archivo);

        }
        int cantidadFormularios = 0;
        try {
            cantidadFormularios = iRFormulario.cambiarEstadoFormularios(cambioEstadosFL.getCambioEstadoFormularioDTO());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        /*
         * Control de mensajes de confirmacion
         */
        String estadoMensaje = "";

        if (cambioEstadosFL.getEstadoFormularioDTO().getId().equals(EnumEstadoFomulario.ANULADO.getValue())) {
            estadoMensaje = ANULACION;
        } else {
            estadoMensaje = DEVOLUCION;
        }
        if (cantidadFormularios > 0) {
            // Mensaje de guardado exitoso
            String mensajeError = MessageFormat.format(getBundle(NOMBRE_BUNDLE_FORM).getString("msg_confg_cambio_est"),
                    estadoMensaje, cantidadFormularios);
            getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensajeError));
        } else {
            // Mensaje de guardado no exitoso
            String mensajeError = MessageFormat.format(
                    getBundle(NOMBRE_BUNDLE_FORM).getString("msg_confg_cambio_est_cero"), estadoMensaje);
            getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensajeError));
        }
        cambioEstadosFL.getCambioEstadoFormularioDTO().setArchivoTransportableDTO(null);
        documentoSoporteUPF = null;
        return true;
    }

    /**
     * limpia los campos para los cancelar
     * 
     * @author giovanni.velandia
     */
    public void limpiarCampos() {
        CambioEstadosFL cambioEstadosFL = findFlowObject(CambioEstadosFL.class, CambioEstadosFL.NOMBRE_BEAN);
        AdminFormularioHolderFL adminFormularioHolderFL = findFlowObject(AdminFormularioHolderFL.class,
                AdminFormularioHolderFL.NOMBRE_BEAN);
        cambioEstadosFL.getCambioEstadoFormularioDTO().setListRangoDTO(new ArrayList<RangoDTO>());
        adminFormularioHolderFL.setCambioEstadoSeleccionado(null);
        documentoSoporteUPF = null;
        cambioEstadosFL.setNombreArchivo(null);
        cambioEstadosFL.setCambioEstadoFormularioDTO(new CambioEstadoFormularioDTO());
        cambioEstadosFL.getCambioEstadoFormularioDTO().setListRangoDTO(new ArrayList<RangoDTO>());
    }

    /**
     * Maneja el evento de cargar el archivo de autorizacion del rango
     * 
     * @param event
     *            Evento de cargue del archivo
     * @author claudia.rodriguez
     */
    public void cargarArchivo(FileUploadEvent event) {
        UploadedFile archivo = event.getFile();
        CambioEstadosFL cambioEstadosFL = findFlowObject(CambioEstadosFL.class, CambioEstadosFL.NOMBRE_BEAN);
        documentoSoporteUPF = archivo;
        cambioEstadosFL.setNombreArchivo(archivo.getFileName());
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public String getExtensionesPermitidas() {
        return extensionesPermitidas;
    }

    public void setExtensionesPermitidas(String extensionesPermitidas) {
        this.extensionesPermitidas = extensionesPermitidas;
    }

    public static String getContentType() {
        return CONTENT_TYPE;
    }

    public UploadedFile getDocumentoSoporteUPF() {
        return documentoSoporteUPF;
    }

    public void setDocumentoSoporteUPF(UploadedFile documentoSoporteUPF) {
        this.documentoSoporteUPF = documentoSoporteUPF;
    }

}
