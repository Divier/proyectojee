package co.com.datatools.c2.managed_bean.comparendo.notificacion_aviso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaAvisosNotificacionDTO;
import co.com.datatools.c2.dto.comparendo.DocumentoAvisoGeneradoDTO;
import co.com.datatools.c2.dto.comparendo.NotificacionAvisoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRDocumentosCirculemos;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * 
 * @author diego.fonseca COM_053
 */

@ManagedBean
@SessionScoped
public class NotificarComparendoPorEdictoMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 7442409430402657898L;

    private final static Logger logger = Logger.getLogger(NotificarComparendoPorEdictoMB.class.getName());

    @EJB
    private IRAdministracion administracionEJB;
    @EJB
    private IRComparendo comparendoEJB;

    @EJB
    private IRDocumentosCirculemos documentosCirculemosEJB;

    @EJB
    private IRParametro parametroEJB;

    private StreamedContent streamedContent;

    private static final String NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL = "notificarComparendoPorEdictoHolderFL";
    private static final Class<NotificarComparendoPorEdictoHolderFL> OBJ_NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL = NotificarComparendoPorEdictoHolderFL.class;

    private static final String NOMBRE_BUNDLE_NOTIFICACION_AVISO = "labelNotificacionAviso";

    private static final String NOMBRE_ARCHIVO_DESCARGA = "Documento";

    /**
     * 
     * metodo que consulta los organismos de tránsito
     */
    public List<SelectItem> consultaOrganismosAsociados() {
        logger.debug("NotificarComparendoPorEdictoMB::consultaOrganismosAsociados()");
        List<SelectItem> itemsCatalogos;
        itemsCatalogos = new ArrayList<SelectItem>();
        OrganismoTransitoDTO organismoTransitoDTOSesion = findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);
        Integer codigoOrganismo = organismoTransitoDTOSesion.getCodigoOrganismo();
        List<OrganismoTransitoDTO> organismoTransitoDTO = administracionEJB
                .consultarOrganismosAsociados(codigoOrganismo);
        if (organismoTransitoDTO.size() > 0) {
            for (OrganismoTransitoDTO organismoTransito : organismoTransitoDTO) {
                itemsCatalogos.add(new SelectItem(organismoTransito.getCodigoOrganismo(), organismoTransito
                        .getCodigoOrganismo() + " - " + organismoTransito.getNombreOrganismo()));
            }
        }
        return itemsCatalogos;
    }

    /**
     * metodo que llama al servicio que consulta los edictos generados
     */
    public void consultar() {
        logger.debug("NotificarComparendoPorEdictoMB::consultar()");

        NotificarComparendoPorEdictoHolderFL notificarComparendoPorEdictoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL, NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL);
        notificarComparendoPorEdictoHolderFL.setVisible(false);
        List<NotificacionAvisoDTO> lista = new ArrayList<>();
        notificarComparendoPorEdictoHolderFL.setNotificacionAvisoList(lista);
        notificarComparendoPorEdictoHolderFL.setNotificacionAvisoSel(null);
        if ((notificarComparendoPorEdictoHolderFL.getFechaInicialGeneracion() == null && notificarComparendoPorEdictoHolderFL
                .getFechaFinalGeneracion() != null)) {
            getFacesContext().addMessage(
                    "form-contenido:id3",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));

            return;
        }

        if ((notificarComparendoPorEdictoHolderFL.getFechaInicialGeneracion() != null && notificarComparendoPorEdictoHolderFL
                .getFechaFinalGeneracion() == null)) {
            getFacesContext().addMessage(
                    "form-contenido:id4",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));

            return;
        }

        ValorParametroDTO valorParametroDTO = parametroEJB.consultarParametro(
                EnumParametro.CANT_MEDIA_DIAS_REALIZAR_CONSULTA,
                notificarComparendoPorEdictoHolderFL.getCodigoOrganismo(), true);

        if (notificarComparendoPorEdictoHolderFL.getFechaInicialGeneracion() != null
                && notificarComparendoPorEdictoHolderFL.getFechaFinalGeneracion() != null) {
            int dias = Utilidades.diasDiferencia(notificarComparendoPorEdictoHolderFL.getFechaInicialGeneracion(),
                    notificarComparendoPorEdictoHolderFL.getFechaFinalGeneracion());
            if (dias > Long.parseLong(valorParametroDTO.getValorParam())) {
                addErrorMessage(NOMBRE_BUNDLE_NOTIFICACION_AVISO, "msg_error_rango_fechaGeneracion");
                return;
            }
        }

        ConsultaAvisosNotificacionDTO consultaAvisosNotificacionDTO = new ConsultaAvisosNotificacionDTO();
        consultaAvisosNotificacionDTO.setCodigoOrganismo(notificarComparendoPorEdictoHolderFL.getCodigoOrganismo());
        consultaAvisosNotificacionDTO.setFechaInicialGeneracion(notificarComparendoPorEdictoHolderFL
                .getFechaInicialGeneracion());
        consultaAvisosNotificacionDTO.setFechaFinalGeneracion(notificarComparendoPorEdictoHolderFL
                .getFechaFinalGeneracion());

        List<NotificacionAvisoDTO> notificacionAvisoList = new ArrayList<>();
        try {
            notificacionAvisoList = comparendoEJB.consultarAvisosNotificacion(consultaAvisosNotificacionDTO);
            notificarComparendoPorEdictoHolderFL.setNotificacionAvisoList(notificacionAvisoList);
        } catch (CirculemosNegocioException e) {
            // TODO Auto-generated catch block
            CirculemosErrorHandler.handleException(e);
        }
        if (notificacionAvisoList.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(notificacionAvisoList.size());
        }

    }

    /**
     * metodo para el cambio de obligatoriedad de la fecha
     */
    public void onFechaChange() {
        logger.debug("NotificarComparendoPorEdictoMB::onFechaChange()");
        NotificarComparendoPorEdictoHolderFL notificarComparendoPorEdictoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL, NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL);

        notificarComparendoPorEdictoHolderFL.setRequiereFecha(false);
        if (notificarComparendoPorEdictoHolderFL.getFechaInicialGeneracion() != null
                || notificarComparendoPorEdictoHolderFL.getFechaFinalGeneracion() != null) {
            notificarComparendoPorEdictoHolderFL.setRequiereFecha(true);
        }

    }

    /**
     * metodo que llama el servicio que genera el edicto de notificación
     */
    public void generarEdictoNotificacion() {
        logger.debug("NotificarComparendoPorEdictoMB::generarEdictoNotificacion");
        NotificarComparendoPorEdictoHolderFL notificarComparendoPorEdictoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL, NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL);
        notificarComparendoPorEdictoHolderFL.setVisible(false);
        List<NotificacionAvisoDTO> lista = new ArrayList<>();
        notificarComparendoPorEdictoHolderFL.setNotificacionAvisoList(lista);
        notificarComparendoPorEdictoHolderFL.setNotificacionAvisoSel(null);
        try {
            DocumentoAvisoGeneradoDTO documentoAvisoGeneradoDTO = comparendoEJB.generarAvisoNotificacion(
                    notificarComparendoPorEdictoHolderFL.getCodigoOrganismo(), true);
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantesComparendo.FORMATO_FECHA_NOTIFICACION);
            String nombreDocumento = NOMBRE_ARCHIVO_DESCARGA + sdf.format(new Date())
                    + ConstantesComparendo.EXTENSION_PDF;
            streamedContent = null;
            streamedContent = new ByteArrayContent(documentoAvisoGeneradoDTO.getArchivoTransportableDTO()
                    .getContenido(), null, nombreDocumento);
            if (streamedContent != null) {
                notificarComparendoPorEdictoHolderFL.setVisible(true);
            }
            addInfoMessage(NOMBRE_BUNDLE_NOTIFICACION_AVISO, "msg_generacion_edicto");
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        } catch (CirculemosAlertaException e) {
            // TODO Auto-generated catch block
            CirculemosErrorHandler.handleException(e);
        }
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    /**
     * 
     * @param id
     * 
     *            metodo que visualiza el documento PDF y utiliza el servicio consultarDocumentos de la caja negra recibe como parametro el id del
     *            documento
     */
    public void visualizarDocumento(Long id) {
        logger.debug("NotificarComparendoPorEdictoMB::visualizarDocumento(Long)");
        NotificarComparendoPorEdictoHolderFL notificarComparendoPorEdictoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL, NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL);
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantesComparendo.FORMATO_FECHA_NOTIFICACION);
            String nombreDocumento = NOMBRE_ARCHIVO_DESCARGA + sdf.format(new Date())
                    + ConstantesComparendo.EXTENSION_PDF;
            streamedContent = null;
            streamedContent = new ByteArrayContent(documentosCirculemosEJB.consultarDocumentosPDF(ids), null,
                    nombreDocumento);
            if (streamedContent != null) {
                notificarComparendoPorEdictoHolderFL.setVisible(true);
            }
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * metodo que llama al servicio que notifica los comparendos disponibles por aviso
     */
    public void notificar() {
        logger.debug("NotificarComparendoPorEdictoMB::notificar");
        NotificarComparendoPorEdictoHolderFL notificarComparendoPorEdictoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL, NOTIFICAR_COMPAARENDO_POR_EDICTO_HOLDER_FL);
        notificarComparendoPorEdictoHolderFL.setVisible(false);
        List<NotificacionAvisoDTO> lista = new ArrayList<>();
        notificarComparendoPorEdictoHolderFL.setNotificacionAvisoList(lista);
        notificarComparendoPorEdictoHolderFL.setNotificacionAvisoSel(null);
        try {
            int comparendosNotificados = 0;
            comparendosNotificados = comparendoEJB.notificarComparendosAviso(notificarComparendoPorEdictoHolderFL
                    .getCodigoOrganismo());
            if (comparendosNotificados != 0) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(comparendosNotificados);
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

}