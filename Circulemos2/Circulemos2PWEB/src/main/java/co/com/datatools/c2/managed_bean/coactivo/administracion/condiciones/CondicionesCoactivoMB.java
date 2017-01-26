package co.com.datatools.c2.managed_bean.coactivo.administracion.condiciones;

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
import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.CondicionCoactivoDTO;
import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.EstadoCondicionCoactivoDTO;
import co.com.datatools.c2.dto.RespuestaDTO;
import co.com.datatools.c2.dto.ValorCondicionCoactivoDTO;
import co.com.datatools.c2.dto.VariableCondicionCoacDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.enumeracion.EnumRespuestaSistema;
import co.com.datatools.c2.enumeracion.EnumTipoVariableCondicion;
import co.com.datatools.c2.enumeracion.EnumTipoVariableCondicionCoactivo;
import co.com.datatools.c2.enumeraciones.EnumCampoConfiguracionCoactivo;
import co.com.datatools.c2.enumeraciones.EnumErrorConfiguracionCoactivo;
import co.com.datatools.c2.enumeraciones.EnumVarConCoacFuenteData;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracionCoactivo;
import co.com.datatools.c2.negocio.interfaces.IRCoactivo;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.util.Utilidades;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral.MensajesGeneral;
import co.com.datatools.c2.web.util.ConstantesManagedBean;
import co.com.datatools.util.date.UtilFecha;

/**
 * Managed Bean para manipulacion de consulta de las configuraciones de condiciones de coactivo.
 * 
 * @author Dixon.Alvarez
 * 
 */
@ManagedBean
@SessionScoped
public class CondicionesCoactivoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(CondicionesCoactivoMB.class.getName());

    private static final String CONDICIONES_COACTIVO_HOLDER_FL = "condicionesCoactivoHolderFL";
    private static final String BUNDLE_CONDICIONES_COACTIVO = "labelCondicionesCoactivo";

    /**
     * Estilo del color de texto que toma por defecto.
     */
    // private static final String STYLE_TEXT_LABEL = "color: #000000;";

    /**
     * EJB para consulta y manejo de logica de las configuraciones de condiciones de coactivo
     */
    @EJB
    private IRAdministracionCoactivo iRAdministracionCoactivo;
    /**
     * EJB para consulta de variables y valores de condiciones de coactivo
     */
    @EJB
    private IRCoactivo iRCoactivo;

    @EJB
    private IRCatalogo iRCatalogo;

    public CondicionesCoactivoMB() {
        super();
    }

    /**
     * inicializa elementos del mb
     */
    @PostConstruct
    public void ini() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::ini()"));
        CondicionesCoactivoHolderFL condicionesHolderFL = super.findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);
        condicionesHolderFL = new CondicionesCoactivoHolderFL();
        condicionesHolderFL.init();
    }

    /**
     * lleva a cabo la consulta de configuraciones de condiciones de coactivo existentes con respecto a los filtros ingresados
     */
    public void consultarCondicionesCoactivo() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::consultarCondicionesCoactivo()"));

        CondicionesCoactivoHolderFL condicionesHolderFL = super.findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        List<ConfiguracionCoactivoDTO> configuracioncoactivo = iRAdministracionCoactivo
                .consultarConfiguracionCoactivo(condicionesHolderFL.getFiltroConfiguracionCoactivo(), false);
        condicionesHolderFL.setConfiguracionCoactivoDataModel(configuracioncoactivo);

        if (condicionesHolderFL.getConfiguracionCoactivoDataModel() == null
                || condicionesHolderFL.getConfiguracionCoactivoDataModel().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return;
        } else {
            CirculemosAccesoBundleGeneral
                    .addMensajeResultadoConsulta(condicionesHolderFL.getConfiguracionCoactivoDataModel().size());
        }
        condicionesHolderFL.setConfiguracionSeleccionada(null);
    }

    /**
     * lleva a cabo la validacion de redireccionamiento a la interfaz de registro de una configuracion de coactivo si hay o no una configuracion de
     * coactivo abierta
     * 
     * @return true / false
     */
    public boolean irRegistrarConfiguracionCoactivo() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::irRegistrarConfiguracionCoactivo()"));

        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);
        ConfiguracionCoactivoDTO confCoactivoDTO = new ConfiguracionCoactivoDTO();
        confCoactivoDTO.setOrganismoTransito(confCoactivoHolderFL.getOrganismoTransito());

        ConfiguracionCoactivoDTO configuracionExistente = iRAdministracionCoactivo
                .consultarExistenciaConfCoactivoSinCerrar(confCoactivoDTO);
        if (configuracionExistente != null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    EnumErrorConfiguracionCoactivo.ERROR_CONFIGURACION_COACTIVO_SIN_CERRAR.toString(),
                                    EnumErrorConfiguracionCoactivo.ERROR_CONFIGURACION_COACTIVO_SIN_CERRAR
                                            .getDescripcion() + " Nombre Configuración: "
                                            + configuracionExistente.getNombre()));
            return false;
        }
        cargarFormulario();
        confCoactivoHolderFL.setFechaActual(new Date());

        return true;
    }

    /**
     * Carga los campos del formulario que van a ser utilizados.
     */
    public void cargarFormulario() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::cargarFormulario()"));

        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        ConfiguracionCoactivoVO configuracionCoactivoVO = new ConfiguracionCoactivoVO();
        try {
            List<CondicionCoactivoDTO> lstCondicionCoactivoDTOs = iRAdministracionCoactivo
                    .consultarCondicionesCoactivo(getCodigoOrganismoTransito());
            int indexCount = 0;
            for (CondicionCoactivoDTO condicionCoactivoDTO : lstCondicionCoactivoDTOs) {
                CondicionCoactivoVO condicionCoactivoVO = new CondicionCoactivoVO();
                condicionCoactivoVO.setCondicionCoactivoDTO(condicionCoactivoDTO);
                /*
                 * Se ingresa el estado de la condicion de coactivo al VO para luego armar el listado de las condiciones dentro de la configuracion
                 * que van a quedar inactivas.
                 */
                EstadoCondicionCoactivoDTO estadoCondicionCoactivoDTO = new EstadoCondicionCoactivoDTO();
                estadoCondicionCoactivoDTO.setCondicionCoactivo(condicionCoactivoDTO);
                estadoCondicionCoactivoDTO.setActivo(true);
                condicionCoactivoVO.setEstadoCondicionCoactivoDTO(estadoCondicionCoactivoDTO);

                /*
                 * Se consultan las variables de cada condicion y se inicializan sus valores.
                 */
                List<VariableCondicionCoacDTO> lstVarCondicion = iRAdministracionCoactivo
                        .consultarVariablesCondicionCoactivo(condicionCoactivoDTO, null);

                condicionCoactivoVO.setLstValorCondicionVOs(new ArrayList<ValorCondicionCoactivoVO>());
                for (VariableCondicionCoacDTO variableCondicionCoacDTO : lstVarCondicion) {
                    ValorCondicionCoactivoVO valorCondicionVO = new ValorCondicionCoactivoVO();
                    valorCondicionVO.setVariableCondicionCoactivoDTO(variableCondicionCoacDTO);
                    valorCondicionVO.setIndex(indexCount++);
                    construccionCatalogo(variableCondicionCoacDTO);
                    EnumTipoVariableCondicionCoactivo tipoVariableCondicion = Utilidades.buscarElemEnum(
                            EnumTipoVariableCondicionCoactivo.class,
                            variableCondicionCoacDTO.getTipoVariableCoactivo().getCodigo());
                    switch (tipoVariableCondicion) {
                    case COMBO_BOX_SELECCION_MULTIPLE:
                        valorCondicionVO.setLstValoresEscogidos(new ArrayList<String>());
                        valorCondicionVO.setLstNombresValores(new ArrayList<String>());
                        valorCondicionVO
                                .setCatalogo(construccionCatalogo(variableCondicionCoacDTO.getListCatalogoDTO()));
                        valorCondicionVO.setTipSeleccionMultiple(true);
                        break;
                    case COMBO_BOX_SELECCION_UNICA:
                        valorCondicionVO.setValorCondicionCoactivoDTO(new ValorCondicionCoactivoDTO());
                        valorCondicionVO
                                .setCatalogo(construccionCatalogo(variableCondicionCoacDTO.getListCatalogoDTO()));
                        valorCondicionVO.setTipSeleccionUnica(true);
                        break;
                    default:
                        valorCondicionVO.setValorCondicionCoactivoDTO(new ValorCondicionCoactivoDTO());
                        valorCondicionVO
                                .setTipEntero(tipoVariableCondicion.equals(EnumTipoVariableCondicionCoactivo.ENTERO));
                        valorCondicionVO
                                .setTipReal(tipoVariableCondicion.equals(EnumTipoVariableCondicionCoactivo.REAL));
                        valorCondicionVO.setTipBooleano(
                                tipoVariableCondicion.equals(EnumTipoVariableCondicionCoactivo.BOOLEANO));
                        valorCondicionVO
                                .setTipCadena(tipoVariableCondicion.equals(EnumTipoVariableCondicionCoactivo.CADENA));
                        valorCondicionVO
                                .setTipFecha(tipoVariableCondicion.equals(EnumTipoVariableCondicionCoactivo.FECHA));
                        break;
                    }
                    condicionCoactivoVO.getLstValorCondicionVOs().add(valorCondicionVO);
                }
                configuracionCoactivoVO.getLstCondicionCoactivoVOs().add(condicionCoactivoVO);

            }
        } catch (Exception e) {
            logger.error("ERROR - RegistrarConfiguracionCoactivo:" + e.getMessage());
        }
        confCoactivoHolderFL.setConfiguracionCoactivoVO(configuracionCoactivoVO);
    }

    /**
     * Permite llevar a cabo la eliminacion de una configuracion de coactivo de la interfaz
     */
    public void eliminarConfiguracionCoactivo() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::eliminarConfiguracionCoactivo()"));

        CondicionesCoactivoHolderFL condicionesHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);
        ConfiguracionCoactivoDTO selConfiguracionCoactivoDTO = condicionesHolderFL.getConfiguracionSeleccionada();
        if (selConfiguracionCoactivoDTO != null) {
            RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> response = iRAdministracionCoactivo
                    .eliminarConfiguracionCoactivo(selConfiguracionCoactivoDTO);
            if (response.getRespuesta().equals(EnumRespuestaSistema.OK)) {
                List<ConfiguracionCoactivoDTO> lstConfiguracionCoactivoDTOs = condicionesHolderFL
                        .getConfiguracionCoactivoDataModel();
                lstConfiguracionCoactivoDTOs.remove(selConfiguracionCoactivoDTO);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        response.getRespuesta().toString(), response.getDescripcionRespuesta()));
                condicionesHolderFL.setConfiguracionSeleccionada(null);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        response.getRespuesta().toString(), response.getDescripcionRespuesta()));
            }
        } else {
            super.addErrorMessage(BUNDLE_CONDICIONES_COACTIVO, "err_seleccionar_registro");
        }
    }

    /**
     * Permite redireccionar a una configuracion de coactivo
     */
    public boolean irActualizarConfiguaracionCoactivo() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::irActualizarConfiguaracionCoactivo()"));

        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        ConfiguracionCoactivoDTO selConfCoactivoDTO = confCoactivoHolderFL.getConfiguracionSeleccionada();
        confCoactivoHolderFL.setFechaActual(new Date());
        confCoactivoHolderFL.setMinDateFechaFinal(selConfCoactivoDTO.getFechaInicial());
        if (selConfCoactivoDTO != null) {
            cargarFormulario();
            cargarConfiguracionCoactivo();
            cargarValoresAFormulario();

            String confUtilizada = null;
            // Consulta si la configuracion ya fue utilizada para un coactivo
            List<CoactivoDTO> coactivoDTOs = iRAdministracionCoactivo
                    .consultarCoactivosXConfiguracion(selConfCoactivoDTO);
            if (!coactivoDTOs.isEmpty()) {
                confUtilizada = selConfCoactivoDTO.getNombre();
                confCoactivoHolderFL.setMinDateFechaFinal(coactivoDTOs.get(0).getProceso().getFechaInicio());
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
     * Permite visualizar el detalle de una configuracion de coactivo.
     * 
     */
    public void visualizarDetalle() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::visualizarDetalle()"));

        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);
        ConfiguracionCoactivoDTO configuracionSeleccionada = confCoactivoHolderFL.getConfiguracionSeleccionada();
        if (configuracionSeleccionada != null) {
            cargarFormulario();
            cargarConfiguracionCoactivo();
            cargarValoresAFormulario();
        } else {
            super.addErrorMessage(BUNDLE_CONDICIONES_COACTIVO, "err_seleccionar_registro");
        }
    }

    public void cargarConfiguracionCoactivo() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::cargarConfiguracionCoactivo()"));

        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        ConfiguracionCoactivoDTO configuracionCoactivoDTO = confCoactivoHolderFL.getConfiguracionSeleccionada();
        configuracionCoactivoDTO.setOrganismoTransito(confCoactivoHolderFL.getOrganismoTransito());
        List<ConfiguracionCoactivoDTO> lst = iRAdministracionCoactivo
                .consultarConfiguracionCoactivo(configuracionCoactivoDTO, false);
        if (!lst.isEmpty()) {
            configuracionCoactivoDTO = lst.get(0);
            ConfiguracionCoactivoVO configuracionCoactivoVO = confCoactivoHolderFL.getConfiguracionCoactivoVO();
            configuracionCoactivoVO.setConfiguracionCoactivoDTO(new ConfiguracionCoactivoDTO());
            configuracionCoactivoVO.getConfiguracionCoactivoDTO()
                    .setOrganismoTransito(configuracionCoactivoDTO.getOrganismoTransito());
            configuracionCoactivoVO.getConfiguracionCoactivoDTO().setNombre(configuracionCoactivoDTO.getNombre());
            configuracionCoactivoVO.getConfiguracionCoactivoDTO().setId(configuracionCoactivoDTO.getId());
            configuracionCoactivoVO.getConfiguracionCoactivoDTO()
                    .setFechaInicial(configuracionCoactivoDTO.getFechaInicial());
            configuracionCoactivoVO.getConfiguracionCoactivoDTO()
                    .setFechaFinal(configuracionCoactivoDTO.getFechaFinal());
            boolean esMenorIgual = configuracionCoactivoDTO.getFechaInicial()
                    .compareTo(UtilFecha.currentZeroTimeDate()) <= 0;
            configuracionCoactivoVO.setFecIniMenorIgualActual(esMenorIgual);
            confCoactivoHolderFL.setConfiguracionSeleccionada(configuracionCoactivoDTO);

        }

    }

    /**
     * 
     * @param confUtilizada
     *            : Si el campo no es nulo los campos de la interfaz no podran ser editados excepto el de su fecha de cierre de configuracion de
     *            coactivo para llevar a cabo su cierre. En caso de que sea false no
     */
    public void camposNoEditables(String confUtilizada) {
        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);
        confCoactivoHolderFL.getConfiguracionCoactivoVO().setCamposNoEditables(false);
        if (null != confUtilizada) {
            confCoactivoHolderFL.getConfiguracionCoactivoVO().setCamposNoEditables(true);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            super.getBundle(BUNDLE_CONDICIONES_COACTIVO).getString("wrn_configuracion_utilizada"),
                            confUtilizada));
            RequestContext.getCurrentInstance().update(":messages");
        } else {
            confCoactivoHolderFL.getConfiguracionCoactivoVO().equals(false);
        }
    }

    /**
     * Carga los valores encontrados en la configuracion consultada en el formulario.
     * 
     * @author Dixon.Alvarez
     */
    private void cargarValoresAFormulario() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::cargarValoresAFormulario()"));

        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        ConfiguracionCoactivoDTO configuracionCoactivoDTO = confCoactivoHolderFL.getConfiguracionSeleccionada();
        /*
         * Se lleva a cabo la carga de cada uno de los valores.
         */
        for (ValorCondicionCoactivoDTO valorCondicionCoactivoDTO : configuracionCoactivoDTO
                .getLstValorCondicionCoactivo()) {
            for (CondicionCoactivoVO condCoactivoVO : confCoactivoHolderFL.getConfiguracionCoactivoVO()
                    .getLstCondicionCoactivoVOs()) {
                boolean encontro = false;
                for (ValorCondicionCoactivoVO valCondVO : condCoactivoVO.getLstValorCondicionVOs()) {
                    if (valCondVO.getVariableCondicionCoactivoDTO().getId()
                            .equals(valorCondicionCoactivoDTO.getVariableCondicionCoac().getId())) {
                        encontro = true;
                        iniciarValorCondicionVO(valCondVO, valorCondicionCoactivoDTO);
                    }
                }
                if (encontro) {
                    break;
                }
            }
        }
        ConfiguracionCoactivoVO configuracionCoactivoVO = confCoactivoHolderFL.getConfiguracionCoactivoVO();

        /*
         * Se lleva a cabo la asignacion de los estados de cada una de las condiciones.
         */
        for (EstadoCondicionCoactivoDTO estadoCondCoactivoDTO : configuracionCoactivoDTO
                .getLstEstadoCondicionCoactivo()) {
            for (CondicionCoactivoVO condVO : configuracionCoactivoVO.getLstCondicionCoactivoVOs()) {
                if (condVO.getEstadoCondicionCoactivoDTO().getCondicionCoactivo().getCodigo()
                        .equals(estadoCondCoactivoDTO.getCondicionCoactivo().getCodigo())) {
                    condVO.setEstadoCondicionCoactivoDTO(estadoCondCoactivoDTO);
                    break;
                }
            }
        }
    }

    /**
     * Evalua que tipo de dato es y lo asigna a su respectivo campo para la carga de valores en la interfaz
     * 
     * @param valorCondicionVO
     * @param valorCondicionCoactivoDTO
     * @throws CirculemosNegocioException
     * 
     * @author Dixon.Alvarez
     */
    private void iniciarValorCondicionVO(ValorCondicionCoactivoVO valorCondicionVO,
            ValorCondicionCoactivoDTO valorCondicionCoactivoDTO) throws CirculemosRuntimeException {
        boolean valorCondAIngresar = true;
        if (valorCondicionVO.isTipBooleano()) {
            valorCondicionVO.setValBooleano(new Boolean(valorCondicionCoactivoDTO.getValor()));
        } else if (valorCondicionVO.isTipCadena()) {
            valorCondicionVO.setValCadena(valorCondicionCoactivoDTO.getValor());
            return;
        } else if (valorCondicionVO.isTipEntero()) {
            valorCondicionVO.setValInteger(Integer.parseInt(valorCondicionCoactivoDTO.getValor()));
        } else if (valorCondicionVO.isTipFecha()) {
            Date valFecha = Utilidades.stringToDateFormatApp(valorCondicionCoactivoDTO.getValor(), false);
            valorCondicionVO.setValFecha(valFecha);
        } else if (valorCondicionVO.isTipReal()) {
            valorCondicionVO.setValDouble(new BigDecimal(valorCondicionCoactivoDTO.getValor()));
        } else if (valorCondicionVO.isTipSeleccionMultiple()) {
            valorCondicionVO.getLstValoresEscogidos().add(valorCondicionCoactivoDTO.getValor());
            valorCondicionVO.getLstNombresValores().add(
                    extraccionNombreCatalogo(valorCondicionCoactivoDTO.getValor(), valorCondicionVO.getCatalogo()));
            valorCondAIngresar = false;
        }

        else if (valorCondicionVO.isTipSeleccionUnica()) {
            valorCondicionVO.getValorCondicionCoactivoDTO().setValor(valorCondicionCoactivoDTO.getValor());
        }

        if (valorCondAIngresar) {
            valorCondicionVO.setValorCondicionCoactivoDTO(valorCondicionCoactivoDTO);
        }
    }

    /**
     * Construye catalogo apartir de un listado
     * 
     * @param variableCondicionCoactivoDTO
     *            variable con la fuente de informacion de la que se obtienen los catalogos.
     */
    private void construccionCatalogo(VariableCondicionCoacDTO variableCondicionCoactivoDTO) {
        if (variableCondicionCoactivoDTO.getTipoVariableCoactivo()
                .getCodigo() == EnumTipoVariableCondicion.COMBO_BOX_SELECCION_UNICA.getCodigo()
                || variableCondicionCoactivoDTO.getTipoVariableCoactivo()
                        .getCodigo() == EnumTipoVariableCondicion.COMBO_BOX_SELECCION_MULTIPLE.getCodigo()) {

            /*
             * Se identifica si la variable va a traer unos valores determinados de un listado definidos.(Se identifican separando cada uno de los
             * items de la lista con',' y el nombre y valor respectivamente separandolos con ':')
             */
            if (variableCondicionCoactivoDTO.getFuenteData()
                    .contains(EnumVarConCoacFuenteData.CHAR_SEPARATOR_ITEMS.getValue())
                    || variableCondicionCoactivoDTO.getFuenteData()
                            .contains(EnumVarConCoacFuenteData.CHAR_SEPARATOR_VALUES.getValue())) {
                String[] items = variableCondicionCoactivoDTO.getFuenteData()
                        .split(EnumVarConCoacFuenteData.CHAR_SEPARATOR_ITEMS.getValue());
                for (String item : items) {
                    String[] values = item.split(EnumVarConCoacFuenteData.CHAR_SEPARATOR_VALUES.getValue());
                    CatalogoDTO itemDTO = new CatalogoDTO(values[0], values[1]);
                    variableCondicionCoactivoDTO.getListCatalogoDTO().add(itemDTO);
                }
            }
            /*
             * Se identifica si la variable va a consultar un catalogo del sistema o van a cargarcen desde el field determinando.
             */
            else {
                ItemCatalogoDTO filtro = new ItemCatalogoDTO();
                filtro.setActivo(true);
                List<ItemCatalogoDTO> itemsCatalogo = iRCatalogo
                        .consultarItemsCatalogo(variableCondicionCoactivoDTO.getFuenteData(), filtro);
                for (ItemCatalogoDTO itemCatalogoDTO : itemsCatalogo) {
                    CatalogoDTO itemDTO = new CatalogoDTO(itemCatalogoDTO.getNombre(), itemCatalogoDTO.getId());
                    variableCondicionCoactivoDTO.getListCatalogoDTO().add(itemDTO);
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
     * Lleva a cabo el registro de la configuracion de coactivo en el sistema
     */
    public boolean guardarConfiguracionCoactivo() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::guardarConfiguracionCoactivo()"));
        boolean guardado = false;

        limpiarValidacionesCampos();

        ConfiguracionCoactivoDTO configuracionCoactivoDTO = construirConfiguracionCoactivoDTO();
        if (null != configuracionCoactivoDTO) {
            configuracionCoactivoDTO.setOrganismoTransito(findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                    ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO));
            try {
                HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> response = iRAdministracionCoactivo
                        .registrarConfiguracionCoactivo(configuracionCoactivoDTO);
                guardado = procesarRespuestaRegistro(response);
            } catch (Exception e) {
                logger.error("ERROR - " + CondicionesCoactivoMB.class.getName() + "::guardarConfiguracionCoactivo(): "
                        + e.getMessage());
                super.addErrorMessage(BUNDLE_CONDICIONES_COACTIVO, "err_registro_conf_coactivo");
            }
        }
        return guardado;
    }

    /**
     * Lleva a cabo la modificacion de una configuracion de coactivo en el sistema
     */
    public boolean actualizarConfiguracionCoactivo() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::actualizarConfiguracionCoactivo()"));

        boolean actualizado = true;

        limpiarValidacionesCampos();

        ConfiguracionCoactivoDTO configuracionCoactivoDTO = construirConfiguracionCoactivoDTO();
        if (null != configuracionCoactivoDTO) {
            configuracionCoactivoDTO.setOrganismoTransito(super.findSessionObject(
                    ConstantesManagedBean.CLASE_OBJ_ORGANISMO, ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO));
            try {
                HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> response = iRAdministracionCoactivo
                        .modificarConfiguracionCoactivo(configuracionCoactivoDTO);
                actualizado = procesarRespuestaRegistro(response);
            } catch (Exception e) {
                logger.error("ERROR - " + CondicionesCoactivoMB.class.getName() + "::actualizarConfiguracionCoactivo: "
                        + e.getMessage());

                super.addErrorMessage(BUNDLE_CONDICIONES_COACTIVO, "err_actualizar_conf_coactivo");
                actualizado = false;
            }
        }
        return actualizado;
    }

    /**
     * Limpia de la interfaz todos los estilos y validaciones fallidas.
     */
    private void limpiarValidacionesCampos() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::limpiarValidacionesCampos()"));

        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        ConfiguracionCoactivoVO configuracionCoactivoVO = confCoactivoHolderFL.getConfiguracionCoactivoVO();
        configuracionCoactivoVO.getConfiguracionCoactivoDTO().getLstEstadoCondicionCoactivo().clear();
        configuracionCoactivoVO.getConfiguracionCoactivoDTO().getLstValorCondicionCoactivo().clear();
        for (CondicionCoactivoVO condicionCoactivoVO : configuracionCoactivoVO.getLstCondicionCoactivoVOs()) {
            for (ValorCondicionCoactivoVO valorCondicionVO : condicionCoactivoVO.getLstValorCondicionVOs()) {
                valorCondicionVO.setValorValidado(true);
            }
        }
    }

    /**
     * Se extraen de los objectos VOs los datos recogidos de la interfaz para ser enviados a la capa de negocio en su respectivo DTO.
     * 
     * @return {@link ConfiguracionCoactivoDTO}
     */
    private ConfiguracionCoactivoDTO construirConfiguracionCoactivoDTO() {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::construirConfiguracionCoactivoDTO()"));

        CondicionesCoactivoHolderFL condicionesHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        ConfiguracionCoactivoVO configuracionCoactivoVO = condicionesHolderFL.getConfiguracionCoactivoVO();
        ConfiguracionCoactivoDTO configuracionCoactivoDTO = configuracionCoactivoVO.getConfiguracionCoactivoDTO();

        /*
         * Se recorrre cada una de las condiciones para la extraccion de los valores y la asociacion respectiva de los datos con la configuracion de
         * coactivo
         */
        for (CondicionCoactivoVO condicionVO : configuracionCoactivoVO.getLstCondicionCoactivoVOs()) {
            if (condicionVO.getEstadoCondicionCoactivoDTO().getActivo()) {
                for (ValorCondicionCoactivoVO valCondicionVO : condicionVO.getLstValorCondicionVOs()) {
                    // Se lleva a cabo la asignacion de los campos que no corresponden a un unico valor como son los tipos de tablas y seleccion
                    // multiple.
                    if (valCondicionVO.isTipSeleccionMultiple()) {
                        List<ValorCondicionCoactivoDTO> valoresAsignados = asignarValoresSeleccionMultiple(
                                valCondicionVO.getLstValoresEscogidos(),
                                valCondicionVO.getVariableCondicionCoactivoDTO());
                        configuracionCoactivoDTO.getLstValorCondicionCoactivo().addAll(valoresAsignados);

                    }
                    // Para todos los campos que corresponden a un unico valor se lleva a cabo la extracción de sus datos respectivos.
                    if (!valCondicionVO.isTipSeleccionMultiple()) {
                        ValorCondicionCoactivoDTO valorCondicionCoactivoDTO = valCondicionVO
                                .getValorCondicionCoactivoDTO();
                        String valStr = null;
                        // Se extrae el valor ingresado asignandolo a su respectivo campo
                        if ((valStr = getValueStringfrom(valCondicionVO)) != null) {
                            valorCondicionCoactivoDTO.setValor(valStr);
                        }
                        // Se lleva a cabo la asignacion de la variable a la que pertenece el valor
                        valorCondicionCoactivoDTO
                                .setVariableCondicionCoac(valCondicionVO.getVariableCondicionCoactivoDTO());
                        configuracionCoactivoDTO.getLstValorCondicionCoactivo().add(valorCondicionCoactivoDTO);
                    }

                }
            }
            configuracionCoactivoDTO.getLstEstadoCondicionCoactivo().add(condicionVO.getEstadoCondicionCoactivoDTO());
        }

        return configuracionCoactivoDTO;
    }

    /**
     * Permite llevar a cabo la asignacion y extraccion de los valores de seleccion multiple para actualizar con respecto a los anteriormente
     * seleccionados.
     * 
     * @param valoresSeleccionados
     * @param variableCondicionCoactivoDTO
     * @return List<ValorCondicionCoactivoDTO>
     */
    private List<ValorCondicionCoactivoDTO> asignarValoresSeleccionMultiple(List<String> valoresSeleccionados,
            VariableCondicionCoacDTO variableCondicionCoactivoDTO) {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("::asignarValoresSeleccionMultiple()"));

        CondicionesCoactivoHolderFL condicionesHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        /**
         * Se extraen los valores que antes había en la lista.
         */
        List<ValorCondicionCoactivoDTO> listAnterior = new ArrayList<ValorCondicionCoactivoDTO>();
        for (ValorCondicionCoactivoDTO valorCondicionCoactivoDTO : condicionesHolderFL.getConfiguracionCoactivoVO()
                .getConfiguracionCoactivoDTO().getLstValorCondicionCoactivo()) {
            if (valorCondicionCoactivoDTO.getVariableCondicionCoac().getId()
                    .equals(variableCondicionCoactivoDTO.getId())) {
                listAnterior.add(valorCondicionCoactivoDTO);
            }
        }

        /**
         * Se remueven de la lista anterior todos aquellos valores que no hallan sido seleccionados.
         */
        List<ValorCondicionCoactivoDTO> listNueva = new ArrayList<ValorCondicionCoactivoDTO>();
        listNueva.addAll(listAnterior);
        for (ValorCondicionCoactivoDTO valCondicionAnterior : listAnterior) {
            boolean encontrado = false;
            for (String valor : valoresSeleccionados) {
                if (valCondicionAnterior.getValor().equals(valor)) {
                    valoresSeleccionados.remove(valor);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                listNueva.remove(valCondicionAnterior);
            }
        }

        /**
         * Se agregan los nuevos valores
         */
        for (String valor : valoresSeleccionados) {
            ValorCondicionCoactivoDTO valorCondicionCoactivoDTO = new ValorCondicionCoactivoDTO();
            valorCondicionCoactivoDTO.setVariableCondicionCoac(variableCondicionCoactivoDTO);
            valorCondicionCoactivoDTO.setValor(valor);
            listNueva.add(valorCondicionCoactivoDTO);
        }

        return listNueva;
    }

    /**
     * Extrae el valor representado como cadena.
     * 
     * @param valorCondicionVO
     * @return String
     */
    private String getValueStringfrom(ValorCondicionCoactivoVO valorCondicionVO) {
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
            HashMap<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> response) {

        boolean registroExitoso = true;
        limpiarValidacionesCampos();
        for (Map.Entry<Object, RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo>> entrada : response
                .entrySet()) {
            RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respDTO = entrada.getValue();
            if (respDTO.getRespuesta().equals(EnumRespuestaSistema.ERRORES)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                CirculemosAccesoBundleGeneral.getStringGeneral(MensajesGeneral.title_transaction_ko),
                                construccionMensajeError(entrada.getKey(), respDTO)));
                registroExitoso = false;
            }

        }

        return registroExitoso;

    }

    /**
     * Construye el mensaje de error que se muestra en la interfaz con el determinado campo y su descripcion de error.
     * 
     * @param object
     *            : not null. Objeto el cual se encuentra fallando.(ConfiguracionCoactivoDTO, ValorCondicionCoactivoDTO)
     * @param respuestaDTO
     *            : not null.
     * @return String : Mensaje
     */
    private String construccionMensajeError(Object object,
            RespuestaDTO<EnumCampoConfiguracionCoactivo, EnumErrorConfiguracionCoactivo> respuestaDTO) {
        StringBuilder mensaje = new StringBuilder();

        if (object instanceof ConfiguracionCoactivoDTO) {
            ConfiguracionCoactivoDTO confCoactivoDTOResp = (ConfiguracionCoactivoDTO) object;
            mensaje.append("\"" + confCoactivoDTOResp.getNombre() + "\" ");
            mensaje.append(respuestaDTO.getDescripcionRespuesta() + ".\n");
        }
        if (object instanceof ValorCondicionCoactivoDTO) {
            ValorCondicionCoactivoDTO valCondCoactivoDTOResp = (ValorCondicionCoactivoDTO) object;
            mensaje.append(
                    "Valor del campo \"" + valCondCoactivoDTOResp.getVariableCondicionCoac().getNombre() + "\" ");
            mensaje.append(respuestaDTO.getDescripcionRespuesta() + ".\n");
            invalidarCampo(valCondCoactivoDTOResp.getVariableCondicionCoac().getId());
        }
        return mensaje.toString();
    }

    /**
     * LLeva a cabo la aplicacion de los estilos para los campos no validados. En dado caso que el index de alguna de las listas no sea nula esta
     * tomara el estilo de la variable condicion que no fue validada para el determinado registro en la interfaz.
     * 
     * @param idVariableCondicionCoactivo
     */
    private void invalidarCampo(int idVariableCondicionCoactivo) {
        logger.debug(CondicionesCoactivoMB.class.getName().concat("invalidarCampo()"));

        CondicionesCoactivoHolderFL confCoactivoHolderFL = findFlowObject(CondicionesCoactivoHolderFL.class,
                CONDICIONES_COACTIVO_HOLDER_FL);

        ConfiguracionCoactivoVO configuracionCoactivoVO = confCoactivoHolderFL.getConfiguracionCoactivoVO();
        for (CondicionCoactivoVO condicionCoactivoVO : configuracionCoactivoVO.getLstCondicionCoactivoVOs()) {
            for (ValorCondicionCoactivoVO valorCondicionVO : condicionCoactivoVO.getLstValorCondicionVOs()) {
                if (valorCondicionVO.getVariableCondicionCoactivoDTO().getId() == idVariableCondicionCoactivo) {
                    valorCondicionVO.setValorValidado(false);
                    break;
                }

            }

        }

    }

}
