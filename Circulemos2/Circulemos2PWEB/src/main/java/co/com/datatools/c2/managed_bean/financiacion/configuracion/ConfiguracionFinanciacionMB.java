package co.com.datatools.c2.managed_bean.financiacion.configuracion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

import co.com.datatools.c2.dto.CatalogoDTO;
import co.com.datatools.c2.dto.CondicionFinanciacionDTO;
import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;
import co.com.datatools.c2.dto.DetalleCantidadCuotaDTO;
import co.com.datatools.c2.dto.DetallePorcentajeCuotaIniciDTO;
import co.com.datatools.c2.dto.EstadoCondicionFinanciacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.ValorCondicionFinanciacionDTO;
import co.com.datatools.c2.dto.VariableCondicionFinanDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.enumeracion.EnumCampoConfFinanciacion;
import co.com.datatools.c2.enumeracion.EnumErrorConfiguracionFinanciacion;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.EnumTipoVariableCondicion;
import co.com.datatools.c2.enumeracion.financiacion.EnumVarConFinFuenteData;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionFinanciacion;
import co.com.datatools.c2.negocio.interfaces.IRFinanciacion;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral.MensajesGeneral;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.util.date.UtilFecha;

/**
 * Managed Bean para manipulacion de consulta de las fonfiguraciones existentes en el sistema.
 * 
 * @author luis.forero
 * 
 */
@ManagedBean
@SessionScoped
public class ConfiguracionFinanciacionMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConfiguracionFinanciacionMB.class.getName());

    private static final String CONFIGURACION_FINANCIACION_HOLDER_FL = "configuracionFinanciacionHolderFL";
    private static final String BUNDLE_CONFIGURACION_FINANCIACION = "labelConfFinanciaciones";

    /**
     * Estilo del color de texto que toma por defecto.
     */
    private static final String STYLE_TEXT_LABEL = "color: #000000;";

    /**
     * EJB para consulta y manejo de logica de las configuraciones de financiacion
     */
    @EJB
    private IRAdministracionFinanciacion iRAdministracionFinanciacion;
    /**
     * EJB para consulta de variables y valores de condiciones de configuracion de financiacion
     */
    @EJB
    private IRFinanciacion iRFinanciacion;

    @EJB
    private IRCatalogo iRCatalogo;

    public ConfiguracionFinanciacionMB() {
        super();
    }

    /**
     * inicializa elementos del mb
     */
    @PostConstruct
    public void ini() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::ini()"));
        ConfiguracionFinanciacionHolderFL confFinHolderFL = super.findFlowObject(
                ConfiguracionFinanciacionHolderFL.class, CONFIGURACION_FINANCIACION_HOLDER_FL);
        confFinHolderFL = new ConfiguracionFinanciacionHolderFL();
        confFinHolderFL.init();
    }

    /**
     * lleva a cabo la consulta de configuraciones de finaciaciones existentes conrespecto a los filtros ingresados
     */
    public void consultarConfiguracionFinanciacion() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::consultarConfiguracionFinanciacion()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = super.findFlowObject(
                ConfiguracionFinanciacionHolderFL.class, CONFIGURACION_FINANCIACION_HOLDER_FL);

        List<ConfiguracionFinanciacionDTO> configuracionFinanciacion = iRAdministracionFinanciacion
                .consultarConfiguracionFinanciacion(confFinHolderFL.getFilConfFinanDTO(), false);

        confFinHolderFL.setConfiguracionFinanciacionDataModel(configuracionFinanciacion);

        if (confFinHolderFL.getConfiguracionFinanciacionDataModel() == null
                || confFinHolderFL.getConfiguracionFinanciacionDataModel().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return;
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(confFinHolderFL
                    .getConfiguracionFinanciacionDataModel().size());
        }
    }

    /**
     * lleva a cabo la validacion de redireccionamiento a la interfaz de registro de una configuracion de financiacion si hay o no una financiacion
     * abierta
     * 
     * @return true / false
     */
    public boolean irRegistrarConfFinanciacion() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::irRegistrarConfFinanciacion()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);
        ConfiguracionFinanciacionDTO confFinanDTO = new ConfiguracionFinanciacionDTO();
        confFinanDTO.setOrganismoTransito(confFinHolderFL.getOrganismoTransito());
        Map<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> response = iRAdministracionFinanciacion
                .validarRegistroConfiguracionFinanciacion(confFinanDTO);

        for (Map.Entry<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> entrada : response
                .entrySet()) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respuestaDTO = entrada
                    .getValue();
            // TODO FIX cambio en el objeto respuesta de DTO
            if (respuestaDTO.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, respuestaDTO.getNombreRespuesta(), respuestaDTO
                                .getDescripcionRespuesta()));
                return false;
            }
        }

        cargarFormulario();

        return true;
    }

    /**
     * Carga los campos del formulario que van a ser utilizados.
     */
    public void cargarFormulario() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::cargarFormularioRegistro()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        ConfiguracionFinanVO configuracionFinanVO = new ConfiguracionFinanVO();
        try {
            List<CondicionFinanciacionDTO> lstCondicionFinanciacionDTOs = iRAdministracionFinanciacion
                    .consultarCondicionFinanciacion(getCodigoOrganismoTransito());
            int indexCount = 0;
            for (CondicionFinanciacionDTO condicionFinanciacionDTO : lstCondicionFinanciacionDTOs) {
                CondicionFinanciacionVO condicionFinanciacionVO = new CondicionFinanciacionVO();
                condicionFinanciacionVO.setCondicionFinanciacionDTO(condicionFinanciacionDTO);
                /*
                 * Se ingresa el estado de la condicion de financiacion al VO para luego armar el listado de las condiciones dentro de la
                 * configuracion que van a quedar inactivas.
                 */
                EstadoCondicionFinanciacionDTO estadoCondicionFinanciacionDTO = new EstadoCondicionFinanciacionDTO();
                estadoCondicionFinanciacionDTO.setCondicionFinanciacion(condicionFinanciacionDTO);
                estadoCondicionFinanciacionDTO.setActivo(true);
                condicionFinanciacionVO.setEstadoCondicionFinanciacionDTO(estadoCondicionFinanciacionDTO);

                /*
                 * Se consultan las variables de cada condicion y se inicializan sus valores.
                 */
                List<VariableCondicionFinanDTO> lstVarCondicion = iRFinanciacion
                        .consultarVariablesCondicionFinanciacion(condicionFinanciacionDTO);
                condicionFinanciacionVO.setLstValorCondicionVOs(new ArrayList<ValorCondicionVO>());
                for (VariableCondicionFinanDTO variableCondicionFinanDTO : lstVarCondicion) {
                    ValorCondicionVO valorCondicionVO = new ValorCondicionVO();
                    valorCondicionVO.setVariableCondicionFinanDTO(variableCondicionFinanDTO);
                    valorCondicionVO.setIndex(indexCount++);
                    construccionCatalogo(variableCondicionFinanDTO);
                    EnumTipoVariableCondicion tipoVariableCondicion = Utilidades.buscarElemEnum(
                            EnumTipoVariableCondicion.class, variableCondicionFinanDTO.getTipoVariableFinanciacion()
                                    .getCodigo());
                    switch (tipoVariableCondicion) {
                    case COMBO_BOX_SELECCION_MULTIPLE:
                        valorCondicionVO.setLstValoresEscogidos(new ArrayList<String>());
                        valorCondicionVO.setLstNombresValores(new ArrayList<String>());
                        valorCondicionVO.setCatalogo(construccionCatalogo(variableCondicionFinanDTO
                                .getListCatalogoDTO()));
                        valorCondicionVO.setTipSeleccionMultiple(true);
                        break;
                    case COMBO_BOX_SELECCION_UNICA:
                        valorCondicionVO.setValorCondicionFinanciacionDTO(new ValorCondicionFinanciacionDTO());
                        valorCondicionVO.setCatalogo(construccionCatalogo(variableCondicionFinanDTO
                                .getListCatalogoDTO()));
                        valorCondicionVO.setTipSeleccionUnica(true);
                        break;
                    case CANTIDAD_CUOTAS:
                        valorCondicionVO.setDetalleCantidadCuotaDM(new ArrayList<DetalleCantidadCuotaDTO>());
                        valorCondicionVO.setTipTablaDetCantCuota(true);
                        break;
                    case PORCENTAJE_CUOTA_INICIAL:
                        valorCondicionVO
                                .setDetallePorcentajeCuotaIniciDM(new ArrayList<DetallePorcentajeCuotaIniciDTO>());
                        valorCondicionVO.setTipTablaDetPorcCuotaInici(true);
                        break;
                    default:
                        valorCondicionVO.setValorCondicionFinanciacionDTO(new ValorCondicionFinanciacionDTO());
                        valorCondicionVO.setTipEntero(tipoVariableCondicion.equals(EnumTipoVariableCondicion.ENTERO));
                        valorCondicionVO.setTipReal(tipoVariableCondicion.equals(EnumTipoVariableCondicion.REAL));
                        valorCondicionVO.setTipBooleano(tipoVariableCondicion
                                .equals(EnumTipoVariableCondicion.BOOLEANO));
                        valorCondicionVO.setTipCadena(tipoVariableCondicion.equals(EnumTipoVariableCondicion.CADENA));
                        valorCondicionVO.setTipFecha(tipoVariableCondicion.equals(EnumTipoVariableCondicion.FECHA));
                        break;
                    }
                    condicionFinanciacionVO.getLstValorCondicionVOs().add(valorCondicionVO);
                }
                configuracionFinanVO.getLstCondicionFinanciacionVOs().add(condicionFinanciacionVO);

            }
        } catch (CirculemosNegocioException e) {
            logger.error("ERROR - RegistrarConfiguracionFinanciacionMB:" + e.getMessage());
        } catch (Exception e) {
            logger.error("ERROR - RegistrarConfiguracionFinanciacionMB:" + e.getMessage());
        }
        confFinHolderFL.setConfiguracionFinanVO(configuracionFinanVO);
    }

    /**
     * Permite llevar a cabo la eliminacion de una financiación de la interfaz
     * 
     * @author luis.forero
     */
    public void eliminarConfFinanciacion() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::eliminarConfFinanciacion()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);
        ConfiguracionFinanciacionDTO selConfFinanDTO = confFinHolderFL.getSelConfFinanDTO();
        if (selConfFinanDTO != null) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> response = iRAdministracionFinanciacion
                    .eliminarConfiguracionFinanciacion(selConfFinanDTO);
            if (response.getRespuesta().equals(EnumRespuestaSistema.OK)) {
                List<ConfiguracionFinanciacionDTO> lstConfiguracionFinanciacionDTOs = confFinHolderFL
                        .getConfiguracionFinanciacionDataModel();
                lstConfiguracionFinanciacionDTOs.remove(selConfFinanDTO);
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, response.getRespuesta().toString(), response
                                .getDescripcionRespuesta()));
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getRespuesta().toString(),
                                procesarRespuesta(response)));
            }
        } else {
            super.addErrorMessage(BUNDLE_CONFIGURACION_FINANCIACION, "err_seleccionar_registro");
        }
    }

    /**
     * Construye el mensaje de respuesta que retorna el negocio.
     * 
     * @author luis.forero
     * @param response
     * @return String : mensaje arrojado en la respuesta.
     */
    private String procesarRespuesta(
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> response) {
        StringBuilder mensaje = new StringBuilder();

        mensaje.append(response.getDescripcionRespuesta() + ": \n");
        // TODO FIX cambio en el objeto respuesta de DTO
        // for (ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> errDTO : response
        // .getErrorEntidad()) {
        // mensaje.append(errDTO.getEnumCampoEntidad().toString() + ". ");
        // List<EnumErrorConfiguracionFinanciacion> listaEnumsErrores = errDTO.getListaEnumsErrores();
        // for (EnumErrorConfiguracionFinanciacion enumErrorConfiguracionFinanciacion : listaEnumsErrores) {
        // mensaje.append(enumErrorConfiguracionFinanciacion.getDescripcion() + " ");
        // }
        // }

        return mensaje.toString();
    }

    /**
     * Permite redireccionar a la configuracion de una financiacion
     */
    public boolean irActualizarConfFinanciacion() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::irActualizarConfFinanciacion()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        ConfiguracionFinanciacionDTO selConfFinanDTO = confFinHolderFL.getSelConfFinanDTO();
        confFinHolderFL.setFechaActual(new Date());
        confFinHolderFL.setMinDateFechaFinal(selConfFinanDTO.getFechaInicial());
        if (selConfFinanDTO != null) {
            // validarConfiguracionEditable
            HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> response = iRAdministracionFinanciacion
                    .validarModificacionFinanciacion(selConfFinanDTO);
            String confUtilizada = null;
            for (Map.Entry<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> entry : response
                    .entrySet()) {
                RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respuestaDTO = entry
                        .getValue();
                if (respuestaDTO.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                    // TODO FIX cambio en el objeto respuesta de DTO
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, respuestaDTO.getNombreRespuesta(),
                                    respuestaDTO.getDescripcionRespuesta()));
                    return false;
                }
            }

            cargarFormulario();
            cargarConfiguracionFinanciacion();
            cargarValoresAFormulario();

            FinanciacionDTO financiacionDTO = iRAdministracionFinanciacion
                    .configuracionFinanciacionAsociadaFinanciacion(selConfFinanDTO);
            if (financiacionDTO != null) {
                confUtilizada = selConfFinanDTO.getNombre();
                confFinHolderFL.setMinDateFechaFinal(financiacionDTO.getProceso().getFechaInicio());
            }
            camposNoEditables(confUtilizada);

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Seleccione un registro de la lista."));
            return false;
        }
        return true;
    }

    /**
     * Permite visualizar el detalle de una configuracion de financiacion.
     * 
     */
    public void visualizarDetalle() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::visualizarDetalle()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);
        ConfiguracionFinanciacionDTO selConfFinanDTO = confFinHolderFL.getSelConfFinanDTO();
        if (selConfFinanDTO != null) {
            cargarFormulario();
            cargarConfiguracionFinanciacion();
            cargarValoresAFormulario();
        } else {
            super.addErrorMessage(BUNDLE_CONFIGURACION_FINANCIACION, "err_seleccionar_registro");
        }
    }

    public void cargarConfiguracionFinanciacion() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::cargarConfiguracionFinanciacion()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        ConfiguracionFinanciacionDTO configuracionFinanciacionDTO = confFinHolderFL.getSelConfFinanDTO();
        configuracionFinanciacionDTO.setOrganismoTransito(confFinHolderFL.getOrganismoTransito());
        List<ConfiguracionFinanciacionDTO> lst = iRAdministracionFinanciacion.consultarConfiguracionFinanciacion(
                configuracionFinanciacionDTO, false);
        if (!lst.isEmpty()) {
            configuracionFinanciacionDTO = lst.get(0);
            ConfiguracionFinanVO configuracionFinanVO = confFinHolderFL.getConfiguracionFinanVO();
            configuracionFinanVO.setConfiguracionFinanciacionDTO(new ConfiguracionFinanciacionDTO());
            configuracionFinanVO.getConfiguracionFinanciacionDTO().setOrganismoTransito(
                    configuracionFinanciacionDTO.getOrganismoTransito());
            configuracionFinanVO.getConfiguracionFinanciacionDTO().setNombre(configuracionFinanciacionDTO.getNombre());
            configuracionFinanVO.getConfiguracionFinanciacionDTO().setId(configuracionFinanciacionDTO.getId());
            configuracionFinanVO.getConfiguracionFinanciacionDTO().setFechaInicial(
                    configuracionFinanciacionDTO.getFechaInicial());
            configuracionFinanVO.getConfiguracionFinanciacionDTO().setFechaFinal(
                    configuracionFinanciacionDTO.getFechaFinal());
            boolean esMenorIgual = configuracionFinanciacionDTO.getFechaInicial().compareTo(
                    UtilFecha.currentZeroTimeDate()) <= 0;
            configuracionFinanVO.setFecIniMenorIgualActual(esMenorIgual);
            confFinHolderFL.setSelConfFinanDTO(configuracionFinanciacionDTO);

        }

    }

    /**
     * 
     * @param confUtilizada
     *            : Si el campo no es nulo los campos de la interfaz no podran ser editados exepto el de su fecha de cierre de configuracion de
     *            financiacion para llevar a cabo su cierre. En caso de que sea false no
     */
    public void camposNoEditables(String confUtilizada) {
        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);
        confFinHolderFL.getConfiguracionFinanVO().setCamposNoEditables(false);
        if (null != confUtilizada) {
            confFinHolderFL.getConfiguracionFinanVO().setCamposNoEditables(true);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, super.getBundle(BUNDLE_CONFIGURACION_FINANCIACION)
                            .getString("wrn_configuracion_utilizada"), confUtilizada));
            RequestContext.getCurrentInstance().update(":messages");
        } else {
            confFinHolderFL.getConfiguracionFinanVO().equals(false);
        }
    }

    /**
     * Carga los valores encontrados en la configuracion consultada en el formulario.
     * 
     * @author luis.forero
     */
    private void cargarValoresAFormulario() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::cargarValoresAFormulario()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        ConfiguracionFinanciacionDTO configuracionFinanciacionDTO = confFinHolderFL.getSelConfFinanDTO();
        /*
         * Se lleva a cabo la carga de cada uno de los valores.
         */
        for (ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO : configuracionFinanciacionDTO
                .getLstValorCondicionFinanciacion()) {
            for (CondicionFinanciacionVO condFinanVO : confFinHolderFL.getConfiguracionFinanVO()
                    .getLstCondicionFinanciacionVOs()) {
                boolean encontro = false;
                for (ValorCondicionVO valCondVO : condFinanVO.getLstValorCondicionVOs()) {
                    if (valCondVO.getVariableCondicionFinanDTO().getId()
                            .equals(valorCondicionFinanciacionDTO.getVariableCondicionFinan().getId())) {
                        encontro = true;
                        iniciarValorCondicionVO(valCondVO, valorCondicionFinanciacionDTO);
                    }
                }
                if (encontro) {
                    break;
                }
            }
        }
        ConfiguracionFinanVO configuracionFinanVO = confFinHolderFL.getConfiguracionFinanVO();
        /*
         * Se lleva a cabo la insercion de los detalles.
         */
        for (CondicionFinanciacionVO condFinanVO : configuracionFinanVO.getLstCondicionFinanciacionVOs()) {
            boolean valorDetalleCantidadCuota = false;
            boolean valorDetallePorcentajeCuota = false;
            for (ValorCondicionVO valCondVO : condFinanVO.getLstValorCondicionVOs()) {
                if (!valorDetalleCantidadCuota
                        && valCondVO.getVariableCondicionFinanDTO().getTipoVariableFinanciacion().getCodigo() == EnumTipoVariableCondicion.CANTIDAD_CUOTAS
                                .getCodigo()) {
                    valorDetalleCantidadCuota = true;
                    List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuotaDTOs = valCondVO.getDetalleCantidadCuotaDM();
                    lstDetalleCantidadCuotaDTOs.addAll(configuracionFinanciacionDTO.getLstDetalleCantidadCuota());
                    for (int i = 0; i < lstDetalleCantidadCuotaDTOs.size(); i++) {
                        lstDetalleCantidadCuotaDTOs.get(i).setIndex(i + 1);
                    }
                } else if (!valorDetallePorcentajeCuota
                        && valCondVO.getVariableCondicionFinanDTO().getTipoVariableFinanciacion().getCodigo() == EnumTipoVariableCondicion.PORCENTAJE_CUOTA_INICIAL
                                .getCodigo()) {
                    valorDetallePorcentajeCuota = true;
                    List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaIniciDTOs = valCondVO
                            .getDetallePorcentajeCuotaIniciDM();
                    lstDetallePorcentajeCuotaIniciDTOs.addAll(configuracionFinanciacionDTO
                            .getLstDetallePorcentajeCuotaInici());
                    for (int i = 0; i < lstDetallePorcentajeCuotaIniciDTOs.size(); i++) {
                        lstDetallePorcentajeCuotaIniciDTOs.get(i).setIndex(i + 1);
                    }
                }
                if (valorDetalleCantidadCuota && valorDetallePorcentajeCuota) {
                    break;
                }
            }
            if (valorDetalleCantidadCuota && valorDetallePorcentajeCuota) {
                break;
            }
        }
        /*
         * Se lleva a cabo la asignacion de los estados de cada una de las condiciones.
         */
        for (EstadoCondicionFinanciacionDTO estadoCondFinanDTO : configuracionFinanciacionDTO
                .getLstEstadoCondicionFinanciacion()) {
            for (CondicionFinanciacionVO condVO : configuracionFinanVO.getLstCondicionFinanciacionVOs()) {
                if (condVO.getEstadoCondicionFinanciacionDTO().getCondicionFinanciacion().getCodigo()
                        .equals(estadoCondFinanDTO.getCondicionFinanciacion().getCodigo())) {
                    condVO.setEstadoCondicionFinanciacionDTO(estadoCondFinanDTO);
                    break;
                }
            }
        }
    }

    /**
     * Evalua que tipo de dato es y lo asigna a su respectivo campo para la carga de valores en la interfaz
     * 
     * @author luis.forero
     * @param valorCondicionVO
     * @param valorCondicionFinanciacionDTO
     * @throws CirculemosNegocioException
     */
    private void iniciarValorCondicionVO(ValorCondicionVO valorCondicionVO,
            ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO) throws CirculemosRuntimeException {
        boolean valorCondAIngresar = true;
        if (valorCondicionVO.isTipBooleano()) {
            valorCondicionVO.setValBooleano(new Boolean(valorCondicionFinanciacionDTO.getValor()));
        }
        if (valorCondicionVO.isTipCadena()) {
            valorCondicionVO.setValCadena(valorCondicionFinanciacionDTO.getValor());
            return;
        }
        if (valorCondicionVO.isTipEntero()) {
            valorCondicionVO.setValInteger(Integer.parseInt(valorCondicionFinanciacionDTO.getValor()));
        }
        if (valorCondicionVO.isTipFecha()) {
            Date valFecha = Utilidades.stringToDateFormatApp(valorCondicionFinanciacionDTO.getValor(), false);
            valorCondicionVO.setValFecha(valFecha);
        }
        if (valorCondicionVO.isTipReal()) {
            valorCondicionVO.setValDouble(new BigDecimal(valorCondicionFinanciacionDTO.getValor()));
        }
        if (valorCondicionVO.isTipSeleccionMultiple()) {
            valorCondicionVO.getLstValoresEscogidos().add(valorCondicionFinanciacionDTO.getValor());
            valorCondicionVO.getLstNombresValores().add(
                    extraccionNombreCatalogo(valorCondicionFinanciacionDTO.getValor(), valorCondicionVO.getCatalogo()));
            valorCondAIngresar = false;
        }

        if (valorCondicionVO.isTipSeleccionUnica()) {
            valorCondicionVO.getValorCondicionFinanciacionDTO().setValor(valorCondicionFinanciacionDTO.getValor());
        }

        if (valorCondAIngresar) {
            valorCondicionVO.setValorCondicionFinanciacionDTO(valorCondicionFinanciacionDTO);
        }
    }

    /**
     * Construye catalo apartir de un listado
     * 
     * @param variableCondicionFinanDTO
     *            variable con la fuente de informacion de la que se obtienen los catalogos.
     */
    private void construccionCatalogo(VariableCondicionFinanDTO variableCondicionFinanDTO) {
        if (variableCondicionFinanDTO.getTipoVariableFinanciacion().getCodigo() == EnumTipoVariableCondicion.COMBO_BOX_SELECCION_UNICA
                .getCodigo()
                || variableCondicionFinanDTO.getTipoVariableFinanciacion().getCodigo() == EnumTipoVariableCondicion.COMBO_BOX_SELECCION_MULTIPLE
                        .getCodigo()) {

            /*
             * Se identifica si la variable va a traer unos valores determinados de un listado definidos.(Se identifican separando cada uno de los
             * items de la lista con',' y el nombre y valor respectivamente separandolos con ':')
             */
            if (variableCondicionFinanDTO.getFuenteData().contains(
                    EnumVarConFinFuenteData.CHAR_SEPARATOR_ITEMS.getValue())
                    || variableCondicionFinanDTO.getFuenteData().contains(
                            EnumVarConFinFuenteData.CHAR_SEPARATOR_VALUES.getValue())) {
                String[] items = variableCondicionFinanDTO.getFuenteData().split(
                        EnumVarConFinFuenteData.CHAR_SEPARATOR_ITEMS.getValue());
                for (String item : items) {
                    String[] values = item.split(EnumVarConFinFuenteData.CHAR_SEPARATOR_VALUES.getValue());
                    CatalogoDTO itemDTO = new CatalogoDTO(values[0], values[1]);
                    variableCondicionFinanDTO.getListCatalogoDTO().add(itemDTO);
                }
            }
            /*
             * Se identifica si la variable va a consultar un catalogo del sistema o van a cargarcen desde el field determinando.
             */
            else {
                ItemCatalogoDTO filtro = new ItemCatalogoDTO();
                filtro.setActivo(true);
                List<ItemCatalogoDTO> itemsCatalogo = iRCatalogo.consultarItemsCatalogo(
                        variableCondicionFinanDTO.getFuenteData(), filtro);
                for (ItemCatalogoDTO itemCatalogoDTO : itemsCatalogo) {
                    CatalogoDTO itemDTO = new CatalogoDTO(itemCatalogoDTO.getNombre(), itemCatalogoDTO.getId());
                    variableCondicionFinanDTO.getListCatalogoDTO().add(itemDTO);
                }
            }
        }

    }

    /**
     * Construye catalo apartir de un listado
     * 
     * @param lstItemsCatalogo
     *            variable con el listado de los items del catalogo definido.
     * @return mapa del catalogo manejado desde la interfaz.
     */
    private Map<String, String> construccionCatalogo(List<CatalogoDTO> lstItemsCatalogo) {
        Map<String, String> catalogo = new HashMap<String, String>();
        for (CatalogoDTO catalogoDTO : lstItemsCatalogo) {
            catalogo.put(catalogoDTO.getNombre(), catalogoDTO.getValor());
        }
        return catalogo;
    }

    /**
     * Lleva a cabo el registro de la configuracion de financiacion en el sistema
     */
    public boolean guardarConfiguracionFinanciacion() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::guardarConfiguracionFinanciacion()"));
        boolean guardado = false;

        limpiarValidacionesCampos();
        if (validarListado()) {
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO = construirConfiguracionFinanciacionDTO();
            if (null != configuracionFinanciacionDTO) {
                configuracionFinanciacionDTO.setOrganismoTransito(findSessionObject(
                        ConstantesManagedBean.CLASE_OBJ_ORGANISMO, ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO));
                try {
                    HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> response = iRAdministracionFinanciacion
                            .registrarConfiguracionFinanciacion(configuracionFinanciacionDTO);
                    guardado = procesarRespuestaRegistro(response);
                } catch (Exception e) {
                    logger.error("ERROR - " + ConfiguracionFinanciacionMB.class.getName()
                            + " - METODO guardarConfiguracionFinanciacion: " + e.getMessage());
                    super.addErrorMessage(BUNDLE_CONFIGURACION_FINANCIACION, "err_registro_conf_finan");
                }

            }

        }

        return guardado;
    }

    /**
     * Lleva a cabo el registro de la configuracion de financiacion en el sistema
     */
    public boolean actualizarConfiguracionFinanciacion() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::guardarConfiguracionFinanciacion()"));

        boolean guardado = true;

        limpiarValidacionesCampos();
        if (validarListado()) {
            ConfiguracionFinanciacionDTO configuracionFinanciacionDTO = construirConfiguracionFinanciacionDTO();
            if (null != configuracionFinanciacionDTO) {
                configuracionFinanciacionDTO.setOrganismoTransito(super.findSessionObject(
                        ConstantesManagedBean.CLASE_OBJ_ORGANISMO, ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO));
                try {
                    HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> response = iRAdministracionFinanciacion
                            .modificarConfiguracionFinanciacion(configuracionFinanciacionDTO);
                    guardado = procesarRespuestaRegistro(response);
                } catch (Exception e) {
                    logger.error("ERROR - " + ConfiguracionFinanciacionMB.class.getName()
                            + " - METODO actualizarConfiguracionFinanciacion: " + e.getMessage());

                    super.addErrorMessage(BUNDLE_CONFIGURACION_FINANCIACION, "err_actualizar_conf_finan");
                    guardado = false;
                }

            }

        }

        return guardado;
    }

    /**
     * Limpia de la interfaz todos los estilos y validaciones fallidas.
     */
    private void limpiarValidacionesCampos() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::limpiarValidacionesCampos()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        ConfiguracionFinanVO configuracionFinanVO = confFinHolderFL.getConfiguracionFinanVO();
        configuracionFinanVO.getConfiguracionFinanciacionDTO().getLstEstadoCondicionFinanciacion().clear();
        configuracionFinanVO.getConfiguracionFinanciacionDTO().getLstValorCondicionFinanciacion().clear();
        for (CondicionFinanciacionVO condicionFinanciacionVO : configuracionFinanVO.getLstCondicionFinanciacionVOs()) {
            for (ValorCondicionVO valorCondicionVO : condicionFinanciacionVO.getLstValorCondicionVOs()) {
                valorCondicionVO.setValorValidado(true);
                if (valorCondicionVO.getDetalleCantidadCuotaDM() != null) {
                    List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuotaDTOs = valorCondicionVO
                            .getDetalleCantidadCuotaDM();
                    for (DetalleCantidadCuotaDTO dto : lstDetalleCantidadCuotaDTOs) {
                        dto.setStyleText(STYLE_TEXT_LABEL);
                    }
                }
            }
        }
    }

    /**
     * Se extraen de los objectos VOs los datos recogidos de la interfaz para ser enviados a la capa de negocio en su respectivo DTO.
     * 
     * @return {@link ConfiguracionFinanciacionDTO}
     */
    private ConfiguracionFinanciacionDTO construirConfiguracionFinanciacionDTO() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::construirConfiguracionFinanciacionDTO()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        ConfiguracionFinanVO configuracionFinanVO = confFinHolderFL.getConfiguracionFinanVO();
        ConfiguracionFinanciacionDTO configuracionFinanciacionDTO = configuracionFinanVO
                .getConfiguracionFinanciacionDTO();

        /*
         * Se recorrre cada una de las condiciones para la extraccion de los valores y la asociacion respectiva de los datos con la configuracion de
         * financiacion
         */
        for (CondicionFinanciacionVO condicionVO : configuracionFinanVO.getLstCondicionFinanciacionVOs()) {
            if (condicionVO.getEstadoCondicionFinanciacionDTO().getActivo()) {
                for (ValorCondicionVO valCondicionVO : condicionVO.getLstValorCondicionVOs()) {
                    // Se lleva a cabo la asignacion de los campos que no corresponden a un unico valor como son los tipos de tablas y seleccion
                    // multiple.
                    if (valCondicionVO.isTipSeleccionMultiple()) {
                        List<ValorCondicionFinanciacionDTO> valoresAsignados = asignarValoresSeleccionMultiple(
                                valCondicionVO.getLstValoresEscogidos(), valCondicionVO.getVariableCondicionFinanDTO());
                        configuracionFinanciacionDTO.getLstValorCondicionFinanciacion().addAll(valoresAsignados);

                    } else if (valCondicionVO.isTipTablaDetCantCuota()) {
                        List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuotaDTOs = valCondicionVO
                                .getDetalleCantidadCuotaDM();
                        configuracionFinanciacionDTO.setLstDetalleCantidadCuota(lstDetalleCantidadCuotaDTOs);
                    } else if (valCondicionVO.isTipTablaDetPorcCuotaInici()) {
                        List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaIniciDTOs = valCondicionVO
                                .getDetallePorcentajeCuotaIniciDM();
                        configuracionFinanciacionDTO
                                .setLstDetallePorcentajeCuotaInici(lstDetallePorcentajeCuotaIniciDTOs);
                    }
                    // Para todos los campos que corresponden a un unico valor se lleva a cabo la extracción de sus datos respectivos.
                    if (!valCondicionVO.isTipSeleccionMultiple() && !valCondicionVO.isTipTablaDetCantCuota()
                            && !valCondicionVO.isTipTablaDetPorcCuotaInici()) {
                        ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO = valCondicionVO
                                .getValorCondicionFinanciacionDTO();
                        String valStr = null;
                        // Se extrae el valor ingresado asignandolo a su respectivo campo
                        if ((valStr = getValueStringfrom(valCondicionVO)) != null) {
                            valorCondicionFinanciacionDTO.setValor(valStr);
                        }
                        // Se lleva a cabo la asignacion de la variable a la que pertenece el valor
                        valorCondicionFinanciacionDTO.setVariableCondicionFinan(valCondicionVO
                                .getVariableCondicionFinanDTO());
                        configuracionFinanciacionDTO.getLstValorCondicionFinanciacion().add(
                                valorCondicionFinanciacionDTO);
                    }

                }
            }
            configuracionFinanciacionDTO.getLstEstadoCondicionFinanciacion().add(
                    condicionVO.getEstadoCondicionFinanciacionDTO());
        }

        return configuracionFinanciacionDTO;
    }

    /**
     * Valida si los listados de detalles se encuentran vacios
     * 
     * @author luis.forero
     * @return boolean
     */
    private boolean validarListado() {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::validarListado()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        boolean lstCantCuotEncontrada = false;
        boolean lstPorcCuotEncontrada = false;
        ConfiguracionFinanVO configuracionFinanVO = confFinHolderFL.getConfiguracionFinanVO();
        for (CondicionFinanciacionVO condicionVO : configuracionFinanVO.getLstCondicionFinanciacionVOs()) {
            if (condicionVO.getEstadoCondicionFinanciacionDTO().getActivo()) {
                for (ValorCondicionVO valCondicionVO : condicionVO.getLstValorCondicionVOs()) {
                    if (valCondicionVO.isTipTablaDetCantCuota()) {
                        List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuotaDTOs = valCondicionVO
                                .getDetalleCantidadCuotaDM();
                        if (lstDetalleCantidadCuotaDTOs.isEmpty()) {
                            super.addErrorMessage(BUNDLE_CONFIGURACION_FINANCIACION, "err_tit_lista_vacia",
                                    BUNDLE_CONFIGURACION_FINANCIACION, "err_list_det_cant_cuotas");
                            valCondicionVO.setValorValidado(false);
                            return false;
                        }
                        lstCantCuotEncontrada = true;
                    }
                    if (valCondicionVO.isTipTablaDetPorcCuotaInici()) {
                        List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaIniciDTOs = valCondicionVO
                                .getDetallePorcentajeCuotaIniciDM();
                        if (lstDetallePorcentajeCuotaIniciDTOs.isEmpty()) {
                            super.addErrorMessage(BUNDLE_CONFIGURACION_FINANCIACION, "err_tit_lista_vacia",
                                    BUNDLE_CONFIGURACION_FINANCIACION, "err_list_det_porcentaje");
                            valCondicionVO.setValorValidado(false);
                            return false;
                        }
                        lstPorcCuotEncontrada = true;
                    }
                    if (lstCantCuotEncontrada && lstPorcCuotEncontrada) {
                        break;
                    }
                }
            }
            if (lstCantCuotEncontrada && lstPorcCuotEncontrada) {
                break;
            }

        }
        return true;
    }

    /**
     * Permite llevar a cabo la asignacion y extraccion de los valores de seleccion multiple para actualizar con respecto a los anteriormente
     * seleccionados.
     * 
     * @param valoresSeleccionados
     * @param variableCondicionFinanDTO
     * @return List<ValorCondicionFinanciacionDTO>
     */
    private List<ValorCondicionFinanciacionDTO> asignarValoresSeleccionMultiple(List<String> valoresSeleccionados,
            VariableCondicionFinanDTO variableCondicionFinanDTO) {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("::asignarValoresSeleccionMultiple()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        /**
         * Se extraen los valores que antes había en la lista.
         */
        List<ValorCondicionFinanciacionDTO> listAnterior = new ArrayList<ValorCondicionFinanciacionDTO>();
        for (ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO : confFinHolderFL.getConfiguracionFinanVO()
                .getConfiguracionFinanciacionDTO().getLstValorCondicionFinanciacion()) {
            if (valorCondicionFinanciacionDTO.getVariableCondicionFinan().getId()
                    .equals(variableCondicionFinanDTO.getId())) {
                listAnterior.add(valorCondicionFinanciacionDTO);
            }
        }

        /**
         * Se remueven de la lista anterior todos aquellos valores que no hallan sido seleccionados.
         */
        List<ValorCondicionFinanciacionDTO> listNueva = new ArrayList<ValorCondicionFinanciacionDTO>();
        listNueva.addAll(listAnterior);
        for (ValorCondicionFinanciacionDTO valCondFinanAnt : listAnterior) {
            boolean encontrado = false;
            for (String valor : valoresSeleccionados) {
                if (valCondFinanAnt.getValor().equals(valor)) {
                    valoresSeleccionados.remove(valor);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                listNueva.remove(valCondFinanAnt);
            }
        }

        /**
         * Se agregan los nuevos valores
         */
        for (String valor : valoresSeleccionados) {
            ValorCondicionFinanciacionDTO valorCondicionFinanciacionDTO = new ValorCondicionFinanciacionDTO();
            valorCondicionFinanciacionDTO.setVariableCondicionFinan(variableCondicionFinanDTO);
            valorCondicionFinanciacionDTO.setValor(valor);
            listNueva.add(valorCondicionFinanciacionDTO);
        }

        return listNueva;
    }

    /**
     * Extrae el valor representado como cadena.
     * 
     * @author luis.forero
     * @param valorCondicionVO
     * @return String
     */
    private String getValueStringfrom(ValorCondicionVO valorCondicionVO) {
        String value = null;
        if (valorCondicionVO.isTipBooleano()) {
            return String.valueOf(valorCondicionVO.getValBooleano());
        }
        if (valorCondicionVO.isTipCadena()) {
            return valorCondicionVO.getValCadena();
        }
        if (valorCondicionVO.isTipEntero()) {
            return String.valueOf(valorCondicionVO.getValInteger());
        }
        if (valorCondicionVO.isTipFecha()) {
            return Utilidades.dateToStringFormatApp(valorCondicionVO.getValFecha(), false);
        }
        if (valorCondicionVO.isTipReal()) {
            return valorCondicionVO.getValDouble().toString();
        }
        return value;
    }

    /**
     * Retorna el valor tomado de un determinado catalogo para asignar a un campo.
     * 
     * @author luis.forero
     * @param value
     *            : valor que toma del respectivo catalogo
     * @param catalogo
     *            : catalogo con todas sus opciones.
     * @return String : Valor del respectivo catalogo
     */
    private String extraccionNombreCatalogo(String value, Map<String, String> catalogo) {
        String nombre = "";
        for (Map.Entry<String, String> keyCat : catalogo.entrySet()) {
            String valueCat = keyCat.getValue();
            if (valueCat.equals(value)) {
                return keyCat.getKey();
            }
        }
        return nombre;
    }

    /**
     * Procasamiento de respuesta arrojada por el sistema.
     * 
     * @param response
     */
    private boolean procesarRespuestaRegistro(
            HashMap<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> response) {

        boolean registroExitoso = true;
        limpiarValidacionesCampos();
        for (Map.Entry<Object, RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion>> entrada : response
                .entrySet()) {
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respDTO = entrada.getValue();
            if (respDTO.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                FacesContext.getCurrentInstance().addMessage(
                        "confFin",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, CirculemosAccesoBundleGeneral
                                .getStringGeneral(MensajesGeneral.title_transaction_ko), construccionMensajeError(
                                entrada.getKey(), respDTO)));
                registroExitoso = false;
            }

        }

        return registroExitoso;

    }

    /**
     * Construye el mensaje de error que se muestra en la interfaz con el determinado campo y su descripcion de error.
     * 
     * @param object
     *            : not null. Objeto el cual se encuentra fallando.(ConfiguracionFinanciacionDTO, ValorCondicionFinanciacionDTO,
     *            DetalleCantidadCuotaDTO, DetallePorcentajeCuotaIniciDTO)
     * @param respuestaDTO
     *            : not null.
     * @return String : Mensaje
     */
    private String construccionMensajeError(Object object,
            RespuestaDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> respuestaDTO) {
        StringBuilder mensaje = new StringBuilder();

        if (object instanceof ConfiguracionFinanciacionDTO) {
            ConfiguracionFinanciacionDTO confFinanDTOResp = (ConfiguracionFinanciacionDTO) object;
            mensaje.append("Configuración Financiación \"" + confFinanDTOResp.getNombre() + "\" ");
            mensaje.append(respuestaDTO.getNombreRespuesta() + ": \n");
            // TODO FIX cambio en el objeto respuesta de DTO
            // for (ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> errDTO : respuestaDTO
            // .getErrorEntidad()) {
            // mensaje.append(errDTO.getEnumCampoEntidad().toString() + ": ");
            // for (EnumErrorConfiguracionFinanciacion enumErr : errDTO.getListaEnumsErrores()) {
            // mensaje.append(enumErr.getDescripcion() + ". ");
            // }
            // mensaje.append("\n");
            // }
        }
        if (object instanceof ValorCondicionFinanciacionDTO) {
            ValorCondicionFinanciacionDTO valCondFinanDTOResp = (ValorCondicionFinanciacionDTO) object;
            mensaje.append("Valor del campo \"" + valCondFinanDTOResp.getVariableCondicionFinan().getNombre() + "\" ");
            mensaje.append(respuestaDTO.getNombreRespuesta() + ": ");
            mensaje.append(respuestaDTO.getDescripcionRespuesta() + ".\n");
            invalidarCampo(valCondFinanDTOResp.getVariableCondicionFinan().getId(), null, null);
        }
        if (object instanceof DetalleCantidadCuotaDTO) {
            DetalleCantidadCuotaDTO detCantCuotDTOResp = (DetalleCantidadCuotaDTO) object;
            mensaje.append("Detalle Cantidad ");
            mensaje.append(respuestaDTO.getNombreRespuesta() + ": \n");
            // TODO FIX cambio en el objeto respuesta de DTO
            // for (ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> errDTO : respuestaDTO
            // .getErrorEntidad()) {
            // mensaje.append(errDTO.getEnumCampoEntidad().toString() + ": ");
            // for (EnumErrorConfiguracionFinanciacion enumErr : errDTO.getListaEnumsErrores()) {
            // mensaje.append(enumErr.getDescripcion() + ". ");
            // }
            // mensaje.append("\n");
            // }
            invalidarCampo(detCantCuotDTOResp.getVariableCondicionFinan().getId(), detCantCuotDTOResp.getIndex(), null);
        }
        if (object instanceof DetallePorcentajeCuotaIniciDTO) {
            DetallePorcentajeCuotaIniciDTO detPorcCuotIniDTOResp = (DetallePorcentajeCuotaIniciDTO) object;
            mensaje.append("Detalle Cantidad ");
            mensaje.append(respuestaDTO.getNombreRespuesta() + ": \n");
            // TODO FIX cambio en el objeto respuesta de DTO
            // for (ErrorEntidadDTO<EnumCampoConfFinanciacion, EnumErrorConfiguracionFinanciacion> errDTO : respuestaDTO
            // .getErrorEntidad()) {
            // mensaje.append(errDTO.getEnumCampoEntidad().toString() + ": ");
            // for (EnumErrorConfiguracionFinanciacion enumErr : errDTO.getListaEnumsErrores()) {
            // mensaje.append(enumErr.getDescripcion() + ". ");
            // }
            // mensaje.append("\n");
            // }
            invalidarCampo(detPorcCuotIniDTOResp.getVariableCondicionFinan().getId(), null,
                    detPorcCuotIniDTOResp.getIndex());
        }

        return mensaje.toString();
    }

    /**
     * LLeva a cabo la aplicacion de los estilos para los campos no validados. En dado caso que el index de alguna de las listas no sea nula esta
     * tomara el estilo de la variable condicion que no fue validada para el determinado registro en la interfaz.
     * 
     * @param idVariableCondicionFinanciacion
     * @param indexDetalleCantidadCuota
     * @param indexDetallePorcentajeCuotaInicial
     */
    private void invalidarCampo(int idVariableCondicionFinanciacion, Integer indexDetalleCantidadCuota,
            Integer indexDetallePorcentajeCuotaInicial) {
        logger.debug(ConfiguracionFinanciacionMB.class.getName().concat("invalidarCampo()"));

        ConfiguracionFinanciacionHolderFL confFinHolderFL = findFlowObject(ConfiguracionFinanciacionHolderFL.class,
                CONFIGURACION_FINANCIACION_HOLDER_FL);

        ConfiguracionFinanVO configuracionFinanVO = confFinHolderFL.getConfiguracionFinanVO();
        for (CondicionFinanciacionVO condicionFinanciacionVO : configuracionFinanVO.getLstCondicionFinanciacionVOs()) {
            for (ValorCondicionVO valorCondicionVO : condicionFinanciacionVO.getLstValorCondicionVOs()) {
                if (valorCondicionVO.getVariableCondicionFinanDTO().getId() == idVariableCondicionFinanciacion) {
                    valorCondicionVO.setValorValidado(false);
                    if (indexDetalleCantidadCuota != null) {
                        List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuotaDTOs = valorCondicionVO
                                .getDetalleCantidadCuotaDM();
                        for (DetalleCantidadCuotaDTO detCantCuoDTO : lstDetalleCantidadCuotaDTOs) {
                            if (detCantCuoDTO.getIndex() == indexDetalleCantidadCuota) {
                                detCantCuoDTO.setStyleText(valorCondicionVO.getStyleTextLabel());
                                break;
                            }

                        }

                    }

                    if (indexDetallePorcentajeCuotaInicial != null) {
                        List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaIniciDTOs = valorCondicionVO
                                .getDetallePorcentajeCuotaIniciDM();
                        for (DetallePorcentajeCuotaIniciDTO detPorcCuoIniciDTO : lstDetallePorcentajeCuotaIniciDTOs) {
                            if (detPorcCuoIniciDTO.getIndex() == indexDetallePorcentajeCuotaInicial) {
                                detPorcCuoIniciDTO.setStyleText(valorCondicionVO.getStyleTextLabel());
                                break;
                            }

                        }

                    }

                    break;
                }

            }

        }

    }

}
