package co.com.datatools.c2.managed_bean.comparendo.notificacion_correo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.datatools.c2.constantes.ConstantesComparendo;
import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoCargueArchivoNotificacionDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

@ManagedBean
@SessionScoped
public class NotificarComparendoViaCorreoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = -7867864160976607257L;
    private final static Logger logger = Logger.getLogger(NotificarComparendoViaCorreoMB.class.getName());

    private static final String NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL = "notificarComparendoViaCorreoHolderFL";
    private static final Class<NotificarComparendoViaCorreoHolderFL> OBJ_NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL = NotificarComparendoViaCorreoHolderFL.class;

    private int tamanioMaximoArch;
    public static final String TIPOS_ARCHIVOS_PERMITIDOS = "/(\\.|\\/)(xls|xlsx)$/";
    private static final String NOMBRE_BUNDLE_ADMIN_COMPARENDO = "labelAdminComparendo";
    private static final String CARGUE_ACUSE_RECIBO_HOLDER_FL = "cargueAcuseReciboHolderFL";
    private StreamedContent streamedContent;

    @EJB
    private IRComparendo comparendoEjb;
    @EJB
    private IRAdministracion administracionEJB;
    @EJB
    private IRParametro parametroEJB;

    @PostConstruct
    public void init() {
        logger.debug("AdminComparendoMB::init()");
        ValorParametroDTO valTamMaxAdjArch = parametroEJB.consultarParametro(EnumParametro.MAX_ADJUNTAR_ARCHIVO,
                getOrganismoTransito().getCodigoOrganismo(), true);
        tamanioMaximoArch = valTamMaxAdjArch.getValorParamInt();
    }

    private static final String NOMBRE_BUNDLE_NOTIFICACION_CORREO = "labelNotificacionCorreo";

    /**
     * Metodo que utiliza el servicio para consultar los comparendos por notificar
     * 
     */
    public void consultar() {
        logger.debug("NotificarComparendoViaCorreoMB::consultar()");
        NotificarComparendoViaCorreoHolderFL notificarComparendoViaCorreoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL, NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL);
        notificarComparendoViaCorreoHolderFL.setVisible(false);

        if ((notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaInicialImposicion() == null && notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO
                .getFechaFinalImposicion() != null)) {
            notificarComparendoViaCorreoHolderFL.setVisible(false);
            getFacesContext().addMessage(
                    "form-contenido:id3",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));

            return;
        }
        if ((notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaInicialImposicion() != null && notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO
                .getFechaFinalImposicion() == null)) {
            notificarComparendoViaCorreoHolderFL.setVisible(false);
            getFacesContext().addMessage(
                    "form-contenido:id4",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));

            return;
        }

        if (notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaInicialRegistro() != null
                && notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaFinalRegistro() == null) {
            getFacesContext().addMessage(
                    "form-contenido:id6",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }
        if (notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaInicialRegistro() == null
                && notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaFinalRegistro() != null) {
            notificarComparendoViaCorreoHolderFL.setVisible(false);
            getFacesContext().addMessage(
                    "form-contenido:id5",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }

        if (Integer.valueOf(
                notificarComparendoViaCorreoHolderFL.getConsultarComparendosNoNotificadosDTO().getCodigoOrganismo())
                .equals(ConstantesComparendo.CODIGO_OTROS)) {
            addErrorMessage(NOMBRE_BUNDLE_NOTIFICACION_CORREO, "msg_error_organismo");
            return;
        }

        ValorParametroDTO valorParametroDTO = parametroEJB.consultarParametro(EnumParametro.MIN_DIAS_CONSULTA_GENERAL,
                notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getCodigoOrganismo(), true);

        // Calculos para hallar la diferencia entre la fecha inicial y final de imposición
        if (notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaInicialImposicion() != null
                && notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaFinalImposicion() != null) {
            long fechaInicialImposicion = notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO
                    .getFechaInicialImposicion().getTime();
            long fechaFinalImposicion = notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO
                    .getFechaFinalImposicion().getTime();
            long diferencia = fechaFinalImposicion - fechaInicialImposicion;
            long dias = diferencia / (1000 * 60 * 60 * 24);

            if (dias > Long.parseLong(valorParametroDTO.getValorParam())) {
                notificarComparendoViaCorreoHolderFL.setVisible(false);
                addErrorMessage(NOMBRE_BUNDLE_NOTIFICACION_CORREO, "msg_error_rango_fechaInfraccion");
                return;
            }
        }

        // Calculos para hallar la diferencia entre la fecha inicial y final de registro

        if (notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaInicialRegistro() != null
                && notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO.getFechaFinalRegistro() != null) {
            long fechaFinalRegistro = notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO
                    .getFechaInicialRegistro().getTime();
            long fechaIncialRegistro = notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO
                    .getFechaFinalRegistro().getTime();
            long diferenciaRegistro = fechaFinalRegistro - fechaIncialRegistro;
            long diasRegistro = diferenciaRegistro / (1000 * 60 * 60 * 24);

            if (diasRegistro > Long.parseLong(valorParametroDTO.getValorParam())) {
                notificarComparendoViaCorreoHolderFL.setVisible(false);
                addErrorMessage(NOMBRE_BUNDLE_NOTIFICACION_CORREO, "lmsg_error_rango_fechaRegistro");
                return;
            }
        }

        if (notificarComparendoViaCorreoHolderFL.getNumeroComparendo() != null
                && notificarComparendoViaCorreoHolderFL.getCodOrganismoNumeroComparendo() != null) {
            notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO
                    .setNumeroComparendo(notificarComparendoViaCorreoHolderFL.getCodOrganismoNumeroComparendo()
                            + notificarComparendoViaCorreoHolderFL.getNumeroComparendo());
        }

        try {
            int cantidadComparendos = comparendoEjb
                    .consultarCantidadComparendosNoNotificados(notificarComparendoViaCorreoHolderFL.consultarComparendosNoNotificadosDTO);
            notificarComparendoViaCorreoHolderFL.setCantidadDeComparendos(cantidadComparendos);
            if (notificarComparendoViaCorreoHolderFL.getCantidadDeComparendos() == 0) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(notificarComparendoViaCorreoHolderFL
                        .getCantidadDeComparendos());
                notificarComparendoViaCorreoHolderFL.setVisible(true);
            }
        } catch (CirculemosNegocioException e) {
            notificarComparendoViaCorreoHolderFL.setVisible(false);
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }

    }

    public List<SelectItem> consultaOrganismosAsociados() {
        logger.debug("NotificarComparendoViaCorreoMB::consultaOrganismosAsociados()");
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

    public void generarArchivoPlano() {

    }

    /**
     * Cambia valor en codigo de comparendo consulta
     */
    public void cambiarCodOrganismoNumeroComparendoConsulta() {
        logger.debug("NotificarComparendoViaCorreoMB::cambiarCodOrganismoNumeroComparendoConsulta()");
        NotificarComparendoViaCorreoHolderFL notificarComparendoViaCorreoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL, NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL);

        // Consultar parametros polca
        ValorParametroDTO valor = parametroEJB.consultarParametro(EnumParametro.CODIGO_POLCA,
                getCodigoOrganismoTransito(), true);
        notificarComparendoViaCorreoHolderFL.setCodigoPolca(valor.getValorParam());

        if (notificarComparendoViaCorreoHolderFL.isPolca()) {
            notificarComparendoViaCorreoHolderFL.setCodOrganismoNumeroComparendo(notificarComparendoViaCorreoHolderFL
                    .getCodigoPolca());
        } else if (Integer.valueOf(
                notificarComparendoViaCorreoHolderFL.getConsultarComparendosNoNotificadosDTO().getCodigoOrganismo())
                .equals(ConstantesComparendo.CODIGO_OTROS)) {
            notificarComparendoViaCorreoHolderFL.setCodOrganismoNumeroComparendo("");
        } else {
            notificarComparendoViaCorreoHolderFL.setCodOrganismoNumeroComparendo(StringUtils.rightPad(String
                    .valueOf(notificarComparendoViaCorreoHolderFL.getConsultarComparendosNoNotificadosDTO()
                            .getCodigoOrganismo()), 8, "0"));
        }
    }

    public void onNumeroComparendoChangeConsulta() {
        logger.debug("NotificarComparendoViaCorreoMB::onNumeroComparendoChangeConsulta()");
        NotificarComparendoViaCorreoHolderFL notificarComparendoViaCorreoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL, NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL);

        if (StringUtils.isNotEmpty(notificarComparendoViaCorreoHolderFL.getNumeroComparendo())) {
            notificarComparendoViaCorreoHolderFL.setNumeroComparendo(StringUtils.leftPad(
                    String.valueOf(notificarComparendoViaCorreoHolderFL.getNumeroComparendo()), 12, "0"));
        }
    }

    /**
     * Cuando se cambia la fecha de consulta
     * 
     * @author diego.fonseca
     */
    public void onFechaChange() {
        logger.debug("NotificarComparendoViaCorreoMB::onFechaChange()");
        NotificarComparendoViaCorreoHolderFL notificarComparendoViaCorreoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL, NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL);

        notificarComparendoViaCorreoHolderFL.setRequiereFecha(false);
        if (notificarComparendoViaCorreoHolderFL.getConsultarComparendosNoNotificadosDTO().getFechaInicialImposicion() != null
                || notificarComparendoViaCorreoHolderFL.getConsultarComparendosNoNotificadosDTO()
                        .getFechaFinalImposicion() != null) {
            notificarComparendoViaCorreoHolderFL.setRequiereFecha(true);
        }
    }

    /**
     * metodo que utiliza el servicio que notifica los comparendos por correo certificado
     * 
     * @author diego.fonseca
     */
    public void notificar() {
        logger.debug("NotificarComparendoViaCorreoMB::notificar()");

        NotificarComparendoViaCorreoHolderFL notificarComparendoViaCorreoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL, NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL);
        notificarComparendoViaCorreoHolderFL.setVisible(false);
        try {
            comparendoEjb.generarNotificacionCorreo(
                    notificarComparendoViaCorreoHolderFL.getConsultarComparendosNoNotificadosDTO(),
                    notificarComparendoViaCorreoHolderFL.isGeneraDocumento(),
                    notificarComparendoViaCorreoHolderFL.getFecha());
            logger.debug("NotificarComparendoViaCorreoMB::notificar() generaDocumento :"
                    + notificarComparendoViaCorreoHolderFL.isGeneraDocumento());
            if (notificarComparendoViaCorreoHolderFL.isGeneraDocumento()) {
                addInfoMessage(NOMBRE_BUNDLE_NOTIFICACION_CORREO, "msg_creacion_documentos");
            } else {
                addInfoMessage(NOMBRE_BUNDLE_NOTIFICACION_CORREO, "msg_creacion_archivo_plano");
            }
        } catch (CirculemosNegocioException e) {
            // TODO Auto-generated catch block
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        } catch (CirculemosAlertaException ex) {
            CirculemosErrorHandler.handleError(ex.getErrorInfo());
        }
    }

    public void onFechaChangeRegistro() {
        logger.debug("NotificarComparendoViaCorreoMB::onFechaChangeRegistro()");
        NotificarComparendoViaCorreoHolderFL notificarComparendoViaCorreoHolderFL = findFlowObject(
                OBJ_NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL, NOTIFICAR_COMPAARENDO_VIA_CORREO_HOLDER_FL);

        notificarComparendoViaCorreoHolderFL.setRequiereFechaRegistro(false);
        if (notificarComparendoViaCorreoHolderFL.getConsultarComparendosNoNotificadosDTO().getFechaInicialRegistro() != null
                || notificarComparendoViaCorreoHolderFL.getConsultarComparendosNoNotificadosDTO()
                        .getFechaFinalRegistro() != null) {
            notificarComparendoViaCorreoHolderFL.setRequiereFechaRegistro(true);
        }
    }

    // CARGAR ARCHIVO ACUSE DE RECIBO

    public void cargarArchivoAcuse(FileUploadEvent event) {
        logger.debug(NotificarComparendoViaCorreoMB.class.getName().concat("::cargarArchivoAcuse()"));

        CargueAcuseReciboHolderFL cargueHolderFL = findFlowObject(CargueAcuseReciboHolderFL.class,
                CARGUE_ACUSE_RECIBO_HOLDER_FL);
        Integer codigoOrganismo = cargueHolderFL.getCodigoOrganismo();

        if (codigoOrganismo == null) {
            getFacesContext().addMessage(
                    "form-ingreso-cargar:selOneMenOrgTra",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getBundle(
                            CirculemosAccesoBundleGeneral.NOMBRE_BUNDLE_GENERAL).getString(
                            CirculemosAccesoBundleGeneral.MensajesGeneral.val_campo_requerido.toString())));
            return;
        }

        final UploadedFile file = event.getFile();
        final ArchivoTransportableDTO archivo = new ArchivoTransportableDTO(file.getFileName(), file.getContents());

        cargueHolderFL.setArchivo(archivo);
    }

    public void cargarAcuseRecibo() {
        logger.debug(NotificarComparendoViaCorreoMB.class.getName().concat("::cargarAcuseRecibo()"));

        CargueAcuseReciboHolderFL cargueHolderFL = findFlowObject(CargueAcuseReciboHolderFL.class,
                CARGUE_ACUSE_RECIBO_HOLDER_FL);

        try {
            ResultadoCargueArchivoNotificacionDTO resultado = comparendoEjb.cargarArchivoNotificacion(
                    cargueHolderFL.getCodigoOrganismo(), cargueHolderFL.getArchivo());

            cargueHolderFL.setArchivo(resultado.getArchivoRespuesta());
            cargueHolderFL.setMostrarGenerarArchivo(false);
            cargueHolderFL.setMostrarDescargarArchivo(true);
        } catch (CirculemosNegocioException e) {
            cargueHolderFL.setMostrarGenerarArchivo(true);
            cargueHolderFL.setMostrarDescargarArchivo(false);
            CirculemosErrorHandler.handleException(e);
        }
    }

    public StreamedContent descargarDocumento() {
        logger.debug(NotificarComparendoViaCorreoMB.class.getName().concat("::descargarDocumento()"));

        CargueAcuseReciboHolderFL cargueHolderFL = findFlowObject(CargueAcuseReciboHolderFL.class,
                CARGUE_ACUSE_RECIBO_HOLDER_FL);

        streamedContent = new ByteArrayContent(cargueHolderFL.getArchivo().getContenido(), null, cargueHolderFL
                .getArchivo().getNombre());

        cargueHolderFL.setMostrarDescargarArchivo(false);

        return streamedContent;
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
     * Expresiones regulares
     **********************/

    public String getExpresionNumerica() {
        return ExpresionesRegulares.REGEX_NUMERICO_NO_OBLIGATORIO;
    }
    /**********************
     * Fin Expresiones regulares
     **********************/

}
