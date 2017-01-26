package co.com.datatools.c2.managed_bean.comparendo.proceso.impugnacion;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.CaracteristicaPruebaDTO;
import co.com.datatools.c2.dto.ImpulsoPruebaDTO;
import co.com.datatools.c2.dto.OrigenPruebaDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.ProrrogaPruebaDTO;
import co.com.datatools.c2.dto.SolicitudPruebasBackDTO;
import co.com.datatools.c2.dto.TipoDestinoPruebaImpugDTO;
import co.com.datatools.c2.dto.TipoPruebaDTO;
import co.com.datatools.c2.dto.TrazabilidadProcesoDTO;
import co.com.datatools.c2.enumeraciones.EnumOrigenPrueba;
import co.com.datatools.c2.enumeraciones.EnumTipoDestinoPruebaImpug;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRPruebas;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * MB que se encarga de gestionar la pruebas para un proceso de impugnacion historia de usuario JUR_20
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class GestionarPruebasMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    // Logger
    private static final Logger LOGGER = Logger.getLogger(GestionarPruebasMB.class.getName());

    private static final String NOMBRE_BUNDLE_IMPUGNACION = "labelProcesoImpugnacion";

    @ManagedProperty(value = "#{encabezadoImpugnacionMB}")
    EncabezadoImpugnacionMB encabezadoImpugnacion;

    // Documento solicitud
    private StreamedContent documentoSolicitud;
    private static final String CONTENT_TYPE = "application/pdf";
    private final String NOMBRE_ARCHIVO = "Documento solicitud.pdf";
    private final String NOMBRE_ARCHIVO_CIERRE_PRUEBAS = "Documento cierre pruebas.pdf";

    // Documento
    private StreamedContent documentoCierrePruebas;

    @EJB
    IRPruebas iRPruebas;

    private boolean generarProrroga;
    private boolean registrarPruebas;

    @PostConstruct
    public void init() {
        LOGGER.debug("GestionarPruebasMB::init()");
        documentoSolicitud = null;
        documentoCierrePruebas = null;
    }

    /**
     * Metodo que se encarga de inicializar los datos del encabezado
     * 
     * @author giovanni.velandia
     */
    public boolean inicializarDatos() {
        LOGGER.debug("GestionarPruebasMB::inicializarDatos()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        // obtiene el proceso seleccionado
        if (impugnacionHolderFL.getComparendoSeleccionado() == null
                || impugnacionHolderFL.getComparendoSeleccionado().getIdProceso() == null) {
            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_error_proceso_no_seleccionado");
            return false;
        }
        encabezadoImpugnacion.consultarEncabezado(impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());
        return true;
    }

    /**
     * Se encarga de mostrar el documento apenas cierran las pruebas
     * 
     * @author giovanni.velandia
     */
    public void cargarDocumentoCerrarPruebas() {
        LOGGER.debug("GestionarPruebasMB::cargarDocumentoCerrarPruebas()");
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        impugnacionFL.setConfCierrePruebas(true);
    }

    /**
     * Se encarga de cerrar el popup de confirmacion de documento de cierre de pruebas
     * 
     * @author giovanni.velandia
     */
    public void cierreDocumentoCerrarPruenas() {
        LOGGER.debug("GestionarPruebasMB::cierreDocumentoCerrarPruenas()");
        ImpugnacionFL impugnacionFL = findFlowObject(ImpugnacionFL.class, ImpugnacionFL.NOMBRE_BEAN);
        impugnacionFL.setConfCierrePruebas(false);
    }

    /**
     * Se encarga de abrir y capturar los datos de adicion de pruebas
     * 
     * @author giovanni.velandia
     */
    public void adicionarPrueba() {
        LOGGER.debug("GestionarPruebasMB::adicionarPrueba()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);
        gestionarPruebasFL.setAdicionarPrueba(true);
    }

    /**
     * Se encarga de guardar datos de adicion de pruebas
     * 
     * @author giovanni.velandia
     */
    public void guardarAdicionPruebas() {
        LOGGER.debug("GestionarPruebasMB::guardarAdicionPruebas()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        gestionarPruebasFL.setAdicionarPrueba(false);
        gestionarPruebasFL.setConfirmAdicPrueba(true);
        
        if(gestionarPruebasFL.getSolicitudPruebasBackDTO().getTipoDestinoPruebaImpug().getId() == EnumTipoDestinoPruebaImpug.OTRAS.getValue() && (gestionarPruebasFL.getSolicitudPruebasBackDTO().getDestinoPruebaOtro() == null || gestionarPruebasFL.getSolicitudPruebasBackDTO().getDestinoPruebaOtro().equals(""))){
        	addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_error_destino_no_ingresado");
            return;
        }

        /*
         * Llamar el metodo para guargar la prueba despues se debe consultar al base de datos
         */
        try {
            byte[] docSolicitud = iRPruebas.registrarSolicitudPrueba(gestionarPruebasFL.getSolicitudPruebasBackDTO(),
                    impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());

            if (docSolicitud != null) {
                documentoSolicitud = new DefaultStreamedContent(new ByteArrayInputStream(docSolicitud), CONTENT_TYPE,
                        NOMBRE_ARCHIVO);
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            gestionarPruebasFL.setConfirmAdicPrueba(false);
        }

        consultarSolicitudPruebas();

        gestionarPruebasFL.setSolicitudPruebasBackDTO(new SolicitudPruebasBackDTO());
        gestionarPruebasFL.getSolicitudPruebasBackDTO().setCaracteristicaPrueba(new CaracteristicaPruebaDTO());
        gestionarPruebasFL.getSolicitudPruebasBackDTO().setOrigenPrueba(new OrigenPruebaDTO());
        gestionarPruebasFL.getSolicitudPruebasBackDTO().setTipoPrueba(new TipoPruebaDTO());
        gestionarPruebasFL.getSolicitudPruebasBackDTO().setTipoDestinoPruebaImpug(new TipoDestinoPruebaImpugDTO());
    }

    /**
     * permite consultar las pruebas de un proceso
     */
    public void consultarSolicitudPruebas() {
        LOGGER.debug("GestionarPruebasMB:consultarSolicitudPruebas()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);

        gestionarPruebasFL.setSoliciPruebBackSeleccVO(null);
        generarProrroga = false;
        registrarPruebas = false;

        SolicitudPruebasBackDTO solicitudPruebasBackDTO = new SolicitudPruebasBackDTO();
        TrazabilidadProcesoDTO trazabilidadProcesoDTO = new TrazabilidadProcesoDTO();
        ProcesoDTO procesoDTO = new ProcesoDTO();
        procesoDTO.setId(impugnacionHolderFL.getComparendoSeleccionado().getIdProceso());
        trazabilidadProcesoDTO.setProceso(procesoDTO);
        solicitudPruebasBackDTO.setTrazabilidadProceso(trazabilidadProcesoDTO);

        List<SolicitudPruebasBackDTO> solicitudPruebasBackDTOs = iRPruebas
                .consultarSolicitudesProceso(solicitudPruebasBackDTO);

        gestionarPruebasFL.setSolicitudPruebasBackVOs(new ArrayList<SolicitudPruebasBackVO>());
        for (SolicitudPruebasBackDTO solicitudPruebasBackDTOBD : solicitudPruebasBackDTOs) {
            SolicitudPruebasBackVO solicitudPruebasBackVO = new SolicitudPruebasBackVO();
            solicitudPruebasBackVO.setSolicitudPruebasBackDTO(solicitudPruebasBackDTOBD);

            // verificamos si tiene impulsos definitivos
            if (solicitudPruebasBackDTOBD.getImpulsoPruebas() != null
                    && !solicitudPruebasBackDTOBD.getImpulsoPruebas().isEmpty()) {
                for (ImpulsoPruebaDTO impulsoPruebaDTO : solicitudPruebasBackDTOBD.getImpulsoPruebas()) {
                    if (impulsoPruebaDTO.getDefinitivo()) {
                        solicitudPruebasBackVO.setImpulsoDefinitivo(true);
                        break;
                    }
                }
            }
            gestionarPruebasFL.getSolicitudPruebasBackVOs().add(solicitudPruebasBackVO);
        }

    }

    /**
     * Se encarga de cancelar la captura de la adicion de pruebas
     * 
     * @author giovanni.velandia
     */
    public void cancelarAdicionPruebas() {
        LOGGER.debug("GestionarPruebasMB::cancelarAdicionPruebas()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);

        gestionarPruebasFL.setAdicionarPrueba(false);
    }

    /**
     * Se encarga de cancelar la captura de la prorroga
     * 
     * @author giovanni.velandia
     */
    public void cancelarProrroga() {
        LOGGER.debug("GestionarPruebasMB::cancelarProrroga()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);

        gestionarPruebasFL.setGenerarProrroga(false);
    }

    /**
     * Permite manejar las acciones de la pantalla de gestion de pruebas
     */
    public void opcionesGestionPruebas() {
        LOGGER.debug("GestionarPruebasMB::opcionesGestionPruebas()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);
        if (gestionarPruebasFL.getSoliciPruebBackSeleccVO() != null) {
            // generar prorroga
            if (gestionarPruebasFL.getSoliciPruebBackSeleccVO().getSolicitudPruebasBackDTO().getOrigenPrueba().getId()
                    .equals(EnumOrigenPrueba.CIUDADANO.getValue())) {
                generarProrroga = false;
            } else {
                generarProrroga = true;
            }
            registrarPruebas = true;
        } else {
            generarProrroga = false;
            registrarPruebas = false;
        }

    }

    /**
     * 
     */
    public void generarProrroga() {
        LOGGER.debug("GestionarPruebasMB::cancelarAdicionPruebas()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);
        gestionarPruebasFL.setDiasProrroga(null);
    }

    public void actualizarProrroga() {
        LOGGER.debug("GestionarPruebasMB::actualizarProrroga()");

        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);

        if (gestionarPruebasFL.getDiasProrroga() == null || gestionarPruebasFL.getDiasProrroga() < 1) {

            addErrorMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_dias_mayor_cero");
            return;
        }
        ProrrogaPruebaDTO prorrogaDTO = new ProrrogaPruebaDTO();
        prorrogaDTO.setDiasProrroga(gestionarPruebasFL.getDiasProrroga());
        prorrogaDTO.setSolicitudPruebasBack(gestionarPruebasFL.getSoliciPruebBackSeleccVO()
                .getSolicitudPruebasBackDTO());
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            iRPruebas.registrarProrrogaPrueba(prorrogaDTO, gestionarPruebasFL.getSoliciPruebBackSeleccVO()
                    .getIdProceso());
            context.execute("PF('popUpProrroga').hide();");
            consultarSolicitudPruebas();
            addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_prorroga_generada");

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     *
     */
    public boolean cerrarPrueba() {
        LOGGER.debug("GestionarPruebasMB::cerrarPrueba()");
        ImpugnacionHolderFL impugnacionHolderFL = findFlowObject(ImpugnacionHolderFL.class,
                ImpugnacionHolderFL.NOMBRE_BEAN);
        try {
            byte[] docCierrePruebas = iRPruebas.cerrarPruebas(impugnacionHolderFL.getComparendoSeleccionado()
                    .getIdProceso(), true);

            if (docCierrePruebas != null) {
                documentoCierrePruebas = new DefaultStreamedContent(new ByteArrayInputStream(docCierrePruebas),
                        CONTENT_TYPE, NOMBRE_ARCHIVO_CIERRE_PRUEBAS);
            }

        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        cargarDocumentoCerrarPruebas();
        return true;
    }

    /**
     * Se en carga de confirmar la adicion de las pruebas
     * 
     * @author giovanni.velandia
     */
    public void confirmarAdicionPruebas() {
        LOGGER.debug("GestionarPruebasMB::confirmarAdicionPruebas()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);
        gestionarPruebasFL.setConfirmAdicPrueba(false);
    }

    public EncabezadoImpugnacionMB getEncabezadoImpugnacion() {
        return encabezadoImpugnacion;
    }

    public void setEncabezadoImpugnacion(EncabezadoImpugnacionMB encabezadoImpugnacion) {
        this.encabezadoImpugnacion = encabezadoImpugnacion;
    }

    public StreamedContent getDocumentoSolicitud() {
        return documentoSolicitud;
    }

    public void setDocumentoSolicitud(StreamedContent documentoSolicitud) {
        this.documentoSolicitud = documentoSolicitud;
    }

    public boolean isGenerarProrroga() {
        return generarProrroga;
    }

    public void setGenerarProrroga(boolean generarProrroga) {
        this.generarProrroga = generarProrroga;
    }

    public boolean isRegistrarPruebas() {
        return registrarPruebas;
    }

    public void setRegistrarPruebas(boolean registrarPruebas) {
        this.registrarPruebas = registrarPruebas;
    }

    public StreamedContent getDocumentoCierrePruebas() {
        return documentoCierrePruebas;
    }

    public void setDocumentoCierrePruebas(StreamedContent documentoCierrePruebas) {
        this.documentoCierrePruebas = documentoCierrePruebas;
    }
    
    public boolean isDestinoPruebaOtros(){
    	LOGGER.debug("GestionarPruebasMB::isDestinoPruebaOtros()");
        GestionarPruebasFL gestionarPruebasFL = findFlowObject(GestionarPruebasFL.class, GestionarPruebasFL.NOMBRE_BEAN);
        if(gestionarPruebasFL.getSolicitudPruebasBackDTO().getTipoDestinoPruebaImpug().getId() != null)
        	return gestionarPruebasFL.getSolicitudPruebasBackDTO().getTipoDestinoPruebaImpug().getId().equals(EnumTipoDestinoPruebaImpug.OTRAS.getValue());
        else 
        	return false;
    }

}
