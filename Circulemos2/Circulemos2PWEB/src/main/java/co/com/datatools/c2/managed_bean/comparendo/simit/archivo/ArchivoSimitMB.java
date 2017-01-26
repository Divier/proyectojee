package co.com.datatools.c2.managed_bean.comparendo.simit.archivo;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jboss.logging.Logger;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

import co.com.datatools.c2.dto.ArchivoNotificacionComparendoDTO;
import co.com.datatools.c2.dto.ArchivoNotificacionSimitDTO;
import co.com.datatools.c2.dto.NotificacionSimitDTO;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.enumeracion.EnumTipoDocumentoEnvioSimit;
import co.com.datatools.c2.enumeraciones.EnumOpcionGeneracionArchivo;
import co.com.datatools.c2.excepciones.CirculemosAlertaException;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.error.ErrorSimit;
import co.com.datatools.c2.negocio.interfaces.IRNotificacionComparendoSimit;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.negocio.interfaces.gestiondocs.IRRepositorioArchivo;
import co.com.datatools.c2.util.ArchivoTransportableDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;

/**
 * CU_CIR20_DAT_SIM_008 Generar por demanda un archivo plano a SIMIT
 * 
 * @author rodrigo.cruz
 */
@ManagedBean
@SessionScoped
public class ArchivoSimitMB extends AbstractC2ManagedBean {

    private final static long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ArchivoSimitMB.class.getName());
    private final static String RB_LABEL_SIMIT = "labelSimit";

    private ArchivoSimitFL archivoSimitFL, archivoSimitGenFL;
    private ArchivoSimitHolderFL archivoSimitHolderFL;

    @EJB
    private IRNotificacionComparendoSimit iComparendoSimit;
    @EJB
    private IRParametro iParametro;
    @EJB
    private IRRepositorioArchivo iRepoArchivo;

    private void obtenerVariablesFL() {
        archivoSimitFL = findFlowObject(ArchivoSimitFL.class, "archivoSimitFL");
        archivoSimitGenFL = findFlowObject(ArchivoSimitFL.class, "archivoSimitGenerarFL");
        archivoSimitHolderFL = findFlowObject(ArchivoSimitHolderFL.class, "archivoSimitHolderFL");
    }

    public Date ajustarFechaInicial() {
        ValorParametroDTO valorParam = iParametro.consultarParametro(EnumParametro.MAX_DIAS_CONSULTA,
                getOrganismoTransito().getCodigoOrganismo(), true);
        int maxDias = Integer.valueOf(valorParam.getValorParam());
        return DateUtils.addDays(UtilFecha.buildCalendar().getTime(), -maxDias);
    }

    public void ajustarFechaLimite() {
        obtenerVariablesFL();

        if (archivoSimitFL.getFechaInicial() == null)
            archivoSimitFL.setFechaFinalMin(UtilFecha.resetTime(UtilFecha.buildCalendar()).getTime());
        else
            archivoSimitFL.setFechaFinalMin(archivoSimitFL.getFechaInicial());
    }

    /**
     * @author sergio.torres (11-feb-2016) - Modificacion la fecha cuando es la misma de inicio y fin solo funciona para el dia actual
     */
    public void consultarArchivoSimit() {
        logger.debug("ArchivoSimitEJB::consultarArchivoSimit");
        obtenerVariablesFL();
        archivoSimitHolderFL.setEventoArchivoSimitSel(null);

        archivoSimitFL.setFechaInicial(UtilFecha.resetTime(archivoSimitFL.getFechaInicial()).getTime());
        archivoSimitFL.setFechaFinal(UtilFecha.upsetTime(archivoSimitFL.getFechaFinal()).getTime());
        // Consultar notificaciones
        try {
            List<NotificacionSimitDTO> resultados = iComparendoSimit.consultarNotificacionesSimit(
                    archivoSimitFL.getCodigoOrganismo(), archivoSimitFL.getFechaInicial(),
                    archivoSimitFL.getFechaFinal(), EnumTipoDocumentoEnvioSimit.COMPARENDO.getCodigo());
            archivoSimitHolderFL.getResultados().clear();

            // Crear lista de resultados por archivo
            for (NotificacionSimitDTO resultado : resultados) {
                ArchivoSimitFL archivoSimitFL = new ArchivoSimitFL();
                archivoSimitFL.setId(resultado.hashCode() + resultado.getArchivoNotificacionSimits().hashCode());
                archivoSimitFL.setNotificacionSimit(resultado);
                archivoSimitFL.setUsuario(getLogin());

                for (ArchivoNotificacionSimitDTO archivo : resultado.getArchivoNotificacionSimits()) {
                    archivoSimitFL.setIdDocumento(StringUtils.defaultIfBlank(archivo.getIdDocumento(), null));
                    archivoSimitFL.setNumeroOficio(archivo.getNumeroOficio());
                }

                archivoSimitHolderFL.getResultados().add(archivoSimitFL);
            }

            if (archivoSimitHolderFL.getResultados().isEmpty())
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            else
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(archivoSimitHolderFL.getResultados().size());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public void preGenerarArchivoSimit() {
        logger.debug("ArchivoSimitMB::preGenerarArchivoSimit");
        obtenerVariablesFL();

        archivoSimitGenFL.setNombreTipoDocumentoSimit(EnumTipoDocumentoEnvioSimit.COMPARENDO.getNombre());
        archivoSimitGenFL.setCantidadRegistrosTotal(0);

        try {
            List<ArchivoNotificacionComparendoDTO> archivoSimitList = iComparendoSimit.generarNotificacionComparendo(
                    archivoSimitFL.getCodigoOrganismo(), EnumOpcionGeneracionArchivo.NO_GENERAR_ARCHIVO);

            for (ArchivoNotificacionComparendoDTO archivo : archivoSimitList)
                archivoSimitGenFL.setCantidadRegistrosTotal(archivoSimitGenFL.getCantidadRegistrosTotal()
                        + archivo.getCantidadRegistros());
        } catch (CirculemosNegocioException e) {
            logger.trace(e.getMessage());
            archivoSimitGenFL.setCantidadRegistrosTotal(0);
        }
    }

    public void generarArchivoSimit() {
        logger.debug("ArchivoSimitMB::generarArchivoSimit");
        obtenerVariablesFL();

        try {
            int ctdArchivosAntes = 0, ctdArchivosDespues = 0;
            Date hoy = UtilFecha.resetTime(UtilFecha.buildCalendar()).getTime();

            // Validar cantidad de archivos antes
            List<NotificacionSimitDTO> resultados = iComparendoSimit.consultarNotificacionesSimit(
                    archivoSimitFL.getCodigoOrganismo(), hoy, UtilFecha.buildCalendar().getTime(),
                    EnumTipoDocumentoEnvioSimit.COMPARENDO.getCodigo());

            for (NotificacionSimitDTO resultado : resultados)
                ctdArchivosAntes += resultado.getArchivoNotificacionSimits().size();

            // Generar archivo(s) de notificacion
            iComparendoSimit.registrarArchivoNotificacionComparendo(archivoSimitFL.getCodigoOrganismo());

            // Validar cantidad de archivos despues
            resultados = iComparendoSimit.consultarNotificacionesSimit(archivoSimitFL.getCodigoOrganismo(), hoy,
                    UtilFecha.buildCalendar().getTime(), EnumTipoDocumentoEnvioSimit.COMPARENDO.getCodigo());

            for (NotificacionSimitDTO resultado : resultados)
                ctdArchivosDespues += resultado.getArchivoNotificacionSimits().size();

            if (ctdArchivosDespues > ctdArchivosAntes)
                addInfoMessage(RB_LABEL_SIMIT, "resultado", RB_LABEL_SIMIT, "msg_archivo_registrado");
            else
                throw new CirculemosNegocioException(ErrorSimit.EnumRegistrarNotificacionComparendo.SIM_008002);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }
    }

    public StreamedContent descargarDocumento(String idDocumento) {
        logger.debug("ArchivoSimitMB::descargarDocumento");
        obtenerVariablesFL();

        try {
            ArchivoTransportableDTO archivoTransportableDTO = iRepoArchivo.consultarDocumento(idDocumento);
            archivoSimitHolderFL.setStreamedContent(new ByteArrayContent(archivoTransportableDTO.getContenido(), null,
                    archivoTransportableDTO.getNombre()));
        } catch (CirculemosAlertaException e) {
            CirculemosErrorHandler.handleError(e.getErrorInfo());
        }

        return archivoSimitHolderFL.getStreamedContent();
    }

}