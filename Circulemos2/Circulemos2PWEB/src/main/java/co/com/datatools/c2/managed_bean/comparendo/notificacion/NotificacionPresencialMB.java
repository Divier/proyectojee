package co.com.datatools.c2.managed_bean.comparendo.notificacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.NotificacionComparendoDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaNotificacionComparendoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeraciones.EnumTipoNotificacionComparendo;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * ManagedBean que gestiona las paginas de administracion de comparendos Colombia
 * 
 * @author julio.pinzon
 * 
 */
@ManagedBean
@SessionScoped
public class NotificacionPresencialMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(NotificacionPresencialMB.class.getName());

    private static final String NOMBRE_BUNDLE_NOTIFICACION_COMPARENDO = "labelNotificacionComparendo";

    private static final String NOMBRE_ARCHIVO_DESCARGA = "DocumentoNotificacion_";

    private static final String CERO = "0";

    @EJB
    private IRComparendo comparendoEjb;

    @EJB
    private IRParametro parametroEjb;

    @EJB
    private IRRepositorioArchivo iRRepositorioArchivo;

    private StreamedContent streamedContent;

    private String nombreDocumento;

    public NotificacionPresencialMB() {
    }

    @PostConstruct
    public void init() {
        logger.debug("NotificacionPresencialMB::init()");
        NotificacionPresencialHolderFL notificacionPresencialHolderFL = findFlowObject(
                NotificacionPresencialHolderFL.class, NotificacionPresencialHolderFL.NOMBRE_BEAN);
        // Consultar parametros polca
        ValorParametroDTO valor = parametroEjb.consultarParametro(EnumParametro.CODIGO_POLCA,
                getCodigoOrganismoTransito(), true);
        notificacionPresencialHolderFL.setCodigoPolca(valor.getValorParam());
    }

    /**
     * Permite llamar la consulta de comparendos notificados de acuerdo con los filtros de busqueda
     * 
     * @author julio.pinzon
     */
    public void consultar() {
        logger.debug("NotificacionPresencialMB::consultar()");
        try {

            NotificacionPresencialHolderFL notificacionPresencialHolderFL = findFlowObject(
                    NotificacionPresencialHolderFL.class, NotificacionPresencialHolderFL.NOMBRE_BEAN);

            notificacionPresencialHolderFL
                    .setLstComparendos(new ArrayList<ResultadoConsultaNotificacionComparendoDTO>());
            notificacionPresencialHolderFL
                    .setComparendosSeleccionados(new ArrayList<ResultadoConsultaNotificacionComparendoDTO>());

            if (notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO().getTipoDocumentoInfractor() == null
                    && StringUtils.isNotBlank(notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO()
                            .getNumeroDocumentoInfractor())) {
                getFacesContext().addMessage(
                        "form-contenido:selTipoDocumentoInfractor",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }

            if (notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO().getTipoDocumentoInfractor() != null
                    && StringUtils.isBlank(notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO()
                            .getNumeroDocumentoInfractor())) {
                getFacesContext().addMessage(
                        "form-contenido:numeroDocumentoInfractor",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                                CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                                CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
                return;
            }

            notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO().setNumeroComparendo(null);
            if (StringUtils.isNotBlank(notificacionPresencialHolderFL.getCodOrganismoNumeroComparendo())
                    && StringUtils.isNotBlank(notificacionPresencialHolderFL.getNumeroComparendo())) {
                notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO().setNumeroComparendo(
                        notificacionPresencialHolderFL.getCodOrganismoNumeroComparendo()
                                + notificacionPresencialHolderFL.getNumeroComparendo());
            }

            List<ResultadoConsultaNotificacionComparendoDTO> lstComparendoDTO = comparendoEjb
                    .consultarComparendosNotificados(
                            notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO(),
                            EnumTipoNotificacionComparendo.EN_PROCESO_CORREO_CERTIFICADO,
                            EnumTipoNotificacionComparendo.EN_PROCESO_NOTIFICACION_POR_AVISO,
                            EnumTipoNotificacionComparendo.VACIO);

            if (lstComparendoDTO == null || lstComparendoDTO.isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
                notificacionPresencialHolderFL
                        .setLstComparendos(new ArrayList<ResultadoConsultaNotificacionComparendoDTO>(0));
                return;
            } else {
                notificacionPresencialHolderFL.setLstComparendos(lstComparendoDTO);
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lstComparendoDTO.size());
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Metodo encargado de notificar los comparendo seleccionados
     * 
     * @author julio.pinzon
     */
    public void notificar() {
        logger.debug("NotificacionPresencialMB::notificar()");
        NotificacionPresencialHolderFL notificacionPresencialHolderFL = findFlowObject(
                NotificacionPresencialHolderFL.class, NotificacionPresencialHolderFL.NOMBRE_BEAN);
        NotificacionComparendoDTO notificacionComparendo = new NotificacionComparendoDTO();

        // Carga la lista de numeros de comparendos
        List<String> comparendos = new ArrayList<String>();
        for (ResultadoConsultaNotificacionComparendoDTO resultado : notificacionPresencialHolderFL
                .getComparendosSeleccionados()) {
            comparendos.add(resultado.getNumeroComparendo());
        }

        notificacionComparendo.setIdTipoNotificacion(notificacionPresencialHolderFL.getIdTipoNotificacion());
        notificacionComparendo.setFechaNotificacion(notificacionPresencialHolderFL.getFechaNotificacion());
        notificacionComparendo.setComparendos(comparendos);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantesComparendo.FORMATO_FECHA_NOTIFICACION);
            nombreDocumento = NOMBRE_ARCHIVO_DESCARGA + sdf.format(new Date()) + ConstantesComparendo.EXTENSION_PDF;
            streamedContent = new ByteArrayContent(comparendoEjb.notificarComparendos(notificacionComparendo), null,
                    nombreDocumento);
            consultar();
            RequestContext.getCurrentInstance().execute("PF('dlgNotificar').hide();PF('dlgPdfviewer').show();");
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    /**
     * Renueva el valor de la fecha de notificacion
     * 
     * @author julio.pinzon
     */
    public void abrirNotificar() {
        logger.debug("NotificacionPresencialMB::abrirNotificar()");
        NotificacionPresencialHolderFL notificacionPresencialHolderFL = findFlowObject(
                NotificacionPresencialHolderFL.class, NotificacionPresencialHolderFL.NOMBRE_BEAN);
        notificacionPresencialHolderFL.setIdTipoNotificacion(null);
        notificacionPresencialHolderFL.setFechaNotificacion(new Date());
    }

    /**
     * Valor de numero comparendo cambia en consulta, metodo que permite rellenar el numero del comparendo
     * 
     * @author julio.pinzon
     */
    public void onNumeroComparendoChangeConsulta() {
        logger.debug("NotificacionPresencialMB::onNumeroComparendoChangeConsulta()");
        NotificacionPresencialHolderFL notificacionPresencialHolderFL = findFlowObject(
                NotificacionPresencialHolderFL.class, NotificacionPresencialHolderFL.NOMBRE_BEAN);

        if (StringUtils.isNotEmpty(notificacionPresencialHolderFL.getNumeroComparendo())) {
            notificacionPresencialHolderFL.setNumeroComparendo(StringUtils.leftPad(
                    String.valueOf(notificacionPresencialHolderFL.getNumeroComparendo()), 12, CERO));
        }
    }

    /**
     * Organismo de transito cambia consulta
     * 
     * @author julio.pinzon
     */
    public void onCodigoOrganismoChangeConsulta() {
        logger.debug("NotificacionPresencialMB::onCodigoOrganismoChangeConsulta()");
        cambiarCodOrganismoNumeroComparendoConsulta();
    }

    /**
     * Valor de Polca cambia consulta, metodo que permite asignar el organismo asociado al numero del comparendo
     * 
     * @author julio.pinzon
     */
    public void onPolcaChangeConsulta() {
        logger.debug("NotificacionPresencialMB::onPolcaChangeConsulta()");
        cambiarCodOrganismoNumeroComparendoConsulta();
    }

    /**
     * Cambia valor en codigo de comparendo consulta
     */
    private void cambiarCodOrganismoNumeroComparendoConsulta() {
        logger.debug("NotificacionPresencialMB::cambiarCodOrganismoNumeroComparendoConsulta()");
        NotificacionPresencialHolderFL notificacionPresencialHolderFL = findFlowObject(
                NotificacionPresencialHolderFL.class, NotificacionPresencialHolderFL.NOMBRE_BEAN);

        if (notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO().getCodigoOrganismo() == 0) {
            notificacionPresencialHolderFL.setCodOrganismoNumeroComparendo("");
        } else if (notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO().getEsPolca()) {
            notificacionPresencialHolderFL.setCodOrganismoNumeroComparendo(notificacionPresencialHolderFL
                    .getCodigoPolca());
        } else {
            notificacionPresencialHolderFL.setCodOrganismoNumeroComparendo(StringUtils.rightPad(
                    String.valueOf(notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO()
                            .getCodigoOrganismo()), 8, CERO));
        }
    }

    /**
     * Cuando se cambia el numero de documento del infractor
     * 
     * @author julio.pinzon
     */
    public void onTipoDocumentoInfractorChange() {
        logger.debug("NotificacionPresencialMB::onTipoDocumentoInfractorChange()");
        NotificacionPresencialHolderFL notificacionPresencialHolderFL = findFlowObject(
                NotificacionPresencialHolderFL.class, NotificacionPresencialHolderFL.NOMBRE_BEAN);

        notificacionPresencialHolderFL.setRequiereTipoDocInfractor(false);
        if (notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO().getTipoDocumentoInfractor() != null
                || StringUtils.isNotEmpty(notificacionPresencialHolderFL.getConsultaNotificacionComparendoDTO()
                        .getNumeroDocumentoInfractor())) {
            notificacionPresencialHolderFL.setRequiereTipoDocInfractor(true);
        }
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**********************
     * Expresiones regulares
     **********************/

    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }
    /**********************
     * Fin Expresiones regulares
     **********************/

}
