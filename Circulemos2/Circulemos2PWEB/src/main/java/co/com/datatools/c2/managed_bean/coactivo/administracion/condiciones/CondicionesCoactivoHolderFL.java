package co.com.datatools.c2.managed_bean.coactivo.administracion.condiciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo condiciones_coactivo-flow HU_CIR20_DAT_COAC_001
 * 
 * @author Dixon.Alvarez
 * 
 */
public class CondicionesCoactivoHolderFL extends AbstractC2ManagedBean {

    /**
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo filtro de busqueda de configuracion
     */
    private ConfiguracionCoactivoDTO filtroConfiguracionCoactivo;
    /**
     * Atributo que define el modelo para el manejo de los registros encontrados
     */
    private List<ConfiguracionCoactivoDTO> configuracionCoactivoDataModel;
    /**
     * Atributo para manejar un registro del modelo seleccionado.
     */
    private ConfiguracionCoactivoDTO configuracionSeleccionada;

    /**
     * Atributo el cual identifica el organismo de transito con el cual se encuentra autenticado
     */
    private OrganismoTransitoDTO organismoTransito;

    // ******************************************************************************************************************************************
    // **************************************************** OBJETOS UTILIZADOS EN EL REGISTRO ***************************************************
    // ******************************************************************************************************************************************
    /**
     * Estilo del color de texto que toma por defecto.
     */
    private static final String STYLE_TEXT_LABEL = "color: #000000;";

    /**
     * Objeto de manipulacion para un nuevo registro
     */
    private ConfiguracionCoactivoVO configuracionCoactivoVO;

    /**
     * Objeto que permite identificar un valor de condicion al cual se le está agregando un determinado objeto en las acciones de las tablas de
     * detalle de cantidad cuota y detalle porcentaje de cuota inicial.
     */
    private ValorCondicionCoactivoVO selValorCondicionVO;

    private Date fechaActual;

    private Date minDateFechaFinal;

    public CondicionesCoactivoHolderFL() {
        init();
    }

    public void init() {
        organismoTransito = (OrganismoTransitoDTO) super.findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);

        filtroConfiguracionCoactivo = new ConfiguracionCoactivoDTO();
        filtroConfiguracionCoactivo.setOrganismoTransito(organismoTransito);

        configuracionCoactivoDataModel = new ArrayList<ConfiguracionCoactivoDTO>();
    }

    public ConfiguracionCoactivoDTO getFiltroConfiguracionCoactivo() {
        return filtroConfiguracionCoactivo;
    }

    public void setFiltroConfiguracionCoactivo(ConfiguracionCoactivoDTO filtroConfiguracionCoactivo) {
        this.filtroConfiguracionCoactivo = filtroConfiguracionCoactivo;
    }

    public List<ConfiguracionCoactivoDTO> getConfiguracionCoactivoDataModel() {
        return configuracionCoactivoDataModel;
    }

    public void setConfiguracionCoactivoDataModel(List<ConfiguracionCoactivoDTO> configuracionCoactivoDataModel) {
        this.configuracionCoactivoDataModel = configuracionCoactivoDataModel;
    }

    public ConfiguracionCoactivoDTO getConfiguracionSeleccionada() {
        return configuracionSeleccionada;
    }

    public void setConfiguracionSeleccionada(ConfiguracionCoactivoDTO configuracionSeleccionada) {
        this.configuracionSeleccionada = configuracionSeleccionada;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public ConfiguracionCoactivoVO getConfiguracionCoactivoVO() {
        return configuracionCoactivoVO;
    }

    public void setConfiguracionCoactivoVO(ConfiguracionCoactivoVO configuracionCoactivoVO) {
        this.configuracionCoactivoVO = configuracionCoactivoVO;
    }

    public ValorCondicionCoactivoVO getSelValorCondicionVO() {
        return selValorCondicionVO;
    }

    public void setSelValorCondicionVO(ValorCondicionCoactivoVO selValorCondicionVO) {
        this.selValorCondicionVO = selValorCondicionVO;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Date getMinDateFechaFinal() {
        return minDateFechaFinal;
    }

    public void setMinDateFechaFinal(Date minDateFechaFinal) {
        this.minDateFechaFinal = minDateFechaFinal;
    }

}
