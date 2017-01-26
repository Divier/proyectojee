package co.com.datatools.c2.managed_bean.comparendo.administracion.anulacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.constantes.ExpresionesRegulares;
import co.com.datatools.c2.dto.common.ValorParametroDTO;
import co.com.datatools.c2.dto.comparendo.AnulacionDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoConsultaAnulacionDTO;
import co.com.datatools.c2.dto.comparendo.ConsultaComparendoAnulacionDTO;
import co.com.datatools.c2.enumeracion.EnumParametro;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRComparendo;
import co.com.datatools.c2.negocio.interfaces.IRParametro;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;
import co.com.datatools.util.date.UtilFecha;

/**
 * Manejo del proceso de Anulación
 * 
 * @author diego.fonseca
 * 
 */
@ManagedBean
@SessionScoped
public class AnulacionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(AnulacionMB.class.getName());

    private static final String NOMBRE_BUNDLE_IMPUGNACION = "labelAnulacion";

    @EJB
    IRComparendo iRComparendo;

    @EJB
    IRParametro iRParametro;

    private int diasMaximo;
    private Date fechaAnulacionDiasMaximo;
    private boolean seleccionado;

    @PostConstruct
    public void init() {
        logger.debug("AnulacionMB::init()");
        ValorParametroDTO cantMaximoDiasConsultaAnulacion = iRParametro.consultarParametro(
                EnumParametro.NUM_MAX_DIAS_CONSULTA_ANULACION, getCodigoOrganismoTransito(), true);

        diasMaximo = cantMaximoDiasConsultaAnulacion.getValorParamInt();
    }

    public void calcularRangoConsultaFechaAnulacion() {
        logger.debug("AnulacionMB::calcularRangoConsultaFechaAnulacion()");

        AnulacionHolderFL anulacionHolderFL = findFlowObject(AnulacionHolderFL.class, AnulacionHolderFL.NOMBRE_BEAN);
        fechaAnulacionDiasMaximo = UtilFecha.sumarDias(anulacionHolderFL.getFechaInicialImposicion(), diasMaximo);
    }

    /**
     * @author diego.fonseca consulta las multas qu cumplen los requisitos para ser anulados
     */
    public void consultar() {
        logger.debug("AnulacionMB::consultar()");

        AnulacionHolderFL anulacionHolderFL = findFlowObject(AnulacionHolderFL.class, AnulacionHolderFL.NOMBRE_BEAN);
        anulacionHolderFL.getComparendoConsultaAnulacionDTOList().clear();
        anulacionHolderFL.getComparendoConsultaAnulacionSeleccionados().clear();
        ConsultaComparendoAnulacionDTO consultaComparendoAnulacionDTO = new ConsultaComparendoAnulacionDTO();
        consultaComparendoAnulacionDTO.setCodigoOrganismo(anulacionHolderFL.getCodigoOrganismo());
        consultaComparendoAnulacionDTO.setFechaInicial(anulacionHolderFL.getFechaInicialImposicion());
        consultaComparendoAnulacionDTO.setFechaFinal(anulacionHolderFL.getFechaFinalImposicion());
        consultaComparendoAnulacionDTO.setDireccion(anulacionHolderFL.getDireccionInfraccion());
        try {
            anulacionHolderFL.setComparendoConsultaAnulacionDTOList(iRComparendo
                    .consultarComparendoAnulacion(consultaComparendoAnulacionDTO));
            if (anulacionHolderFL.getComparendoConsultaAnulacionDTOList().isEmpty()) {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            } else {
                CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(anulacionHolderFL
                        .getComparendoConsultaAnulacionDTOList().size());
                seleccionado = false;
            }
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
        }

    }

    /**
     * @author diego.fonseca
     * 
     *         metodo que se encarga de llamar el servicio que realiza la anulación del comparendo
     */
    public boolean anularComparendos() {
        logger.debug("AnulacionMB::anularComparendos()");
        // anulacionFL
        AnulacionFL anulacionFL = findFlowObject(AnulacionFL.class, AnulacionFL.NOMBRE_BEAN);
        AnulacionHolderFL anulacionHolderFL = findFlowObject(AnulacionHolderFL.class, AnulacionHolderFL.NOMBRE_BEAN);
        AnulacionDTO anulacionComparendoDTO = new AnulacionDTO();
        anulacionComparendoDTO.setCorreoSupervisor(anulacionFL.getCorreoInstitucional());
        anulacionComparendoDTO.setFechaCorreo(anulacionFL.getFechaCorreo());
        anulacionComparendoDTO.setJustificacionCorreo(anulacionFL.getJustificacionAnulacion());
        anulacionComparendoDTO.setPrimerNombreFuncionario(anulacionFL.getPrimerNombreAutoridadTransito());
        anulacionComparendoDTO.setSegundoNombreFuncionario(anulacionFL.getSegundoNombreAutoridadTransito());
        anulacionComparendoDTO.setPrimerApellidoFuncionario(anulacionFL.getPrimerApellidoAutoridadTransito());
        anulacionComparendoDTO.setSegundoApellidoFuncionario(anulacionFL.getSegundoApellidoAutoridadTransito());
        anulacionComparendoDTO.setFechaAnulacion(new Date());

        List<Long> comparendos = new ArrayList<>();
        for (ComparendoConsultaAnulacionDTO comparendoConsultaAnulacionDTO : anulacionHolderFL
                .getComparendoConsultaAnulacionSeleccionados()) {
            comparendos.add(comparendoConsultaAnulacionDTO.getCicomparendo());
        }
        try {
            iRComparendo.registrarAnulacion(anulacionComparendoDTO, comparendos);
            addInfoMessage(NOMBRE_BUNDLE_IMPUGNACION, "msg_comparendos_seleccionados_anulados");
            return true;
            // Anulacion exitosa
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
    }

    public Date getFechaActual() {
        logger.debug("AnulacionMB::getFechaActual()");
        return UtilFecha.currentZeroTimeDate();
    }

    public Date getFechaAnulacionDiasMaximo() {
        logger.debug("AnulacionMB::getFechaAnulacionDiasMaximo()");
        return fechaAnulacionDiasMaximo;
    }

    public boolean isSeleccionado() {
        logger.debug("AnulacionMB::isSeleccionado()");
        return seleccionado;
    }

    /**
     * @author diego.fonseca
     * 
     *         metodo que se encarga de habilitar o deshabilitar la opcion de anular segun los comparendos seleccionados
     */
    public void cambiarSeleccion() {
        logger.debug("AnulacionMB::cambiarSeleccion()");
        AnulacionHolderFL anulacionHolderFL = findFlowObject(AnulacionHolderFL.class, AnulacionHolderFL.NOMBRE_BEAN);
        if (anulacionHolderFL.getComparendoConsultaAnulacionSeleccionados().size() > 0) {
            seleccionado = true;
        } else {
            seleccionado = false;
        }
    }

    public String getFormatoCorreo() {
        logger.debug("AnulacionMB::getFormatoCorreo()");
        return ExpresionesRegulares.REGEX_EMAIL;
    }

    public String getFormatoNombre() {
        logger.debug("AnulacionMB::getFormatoNombre()");
        return ExpresionesRegulares.REGEX_SOLO_LETRAS;
    }

}
