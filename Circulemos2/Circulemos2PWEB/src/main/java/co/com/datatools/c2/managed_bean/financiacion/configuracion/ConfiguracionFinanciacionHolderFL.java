package co.com.datatools.c2.managed_bean.financiacion.configuracion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;
import co.com.datatools.c2.dto.DetalleCantidadCuotaDTO;
import co.com.datatools.c2.dto.DetallePorcentajeCuotaIniciDTO;
import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.ConstantesManagedBean;

/**
 * Objeto que permite manipular objetos para el flujo configuracion-financiaciones-flow CU_CIR20_DAT_FIN_008
 * 
 * @author luis.forero
 * 
 */
public class ConfiguracionFinanciacionHolderFL extends AbstractC2ManagedBean {

    /**
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo filtro de busqueda de configuracion
     */
    private ConfiguracionFinanciacionDTO filConfFinanDTO;
    /**
     * Atributo que define el modelo para el manejo de los registros encontrados
     */
    private List<ConfiguracionFinanciacionDTO> configuracionFinanciacionDataModel;
    /**
     * Atributo para manejar un registro del modelo seleccionado.
     */
    private ConfiguracionFinanciacionDTO selConfFinanDTO;

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
    private ConfiguracionFinanVO configuracionFinanVO;

    /**
     * Objeto que permite identificar un valor de condicion al cual se le está agregando un determinado objeto en las acciones de las tablas de
     * detalle de cantidad cuota y detalle porcentaje de cuota inicial.
     */
    private ValorCondicionVO selValorCondicionVO;

    /**
     * Identifica un registro de la tabla Detalle Cantidad Cuota
     */
    private DetalleCantidadCuotaDTO selDetCantidadCuotaDTO;
    /**
     * Determina si el registro de la tabla Detalle Cantidad Cuota esta siendo editado o ingresado
     */
    private boolean editSelDetCantidadCuota;

    /**
     * Identifica un registro de la tabla de detalle Porcentaje Cuota Inicial
     */
    private DetallePorcentajeCuotaIniciDTO selDetPorcCuoIniDTO;
    /**
     * Determina si el registro de la tabla de la tabla Detalle Porcentaje Cuota Inicial
     */
    private boolean editselDetPorcCuoIni;

    private Date fechaActual;

    private Date minDateFechaFinal;

    public ConfiguracionFinanciacionHolderFL() {
        init();
    }

    public void init() {
        organismoTransito = (OrganismoTransitoDTO) super.findSessionObject(ConstantesManagedBean.CLASE_OBJ_ORGANISMO,
                ConstantesManagedBean.NOMBRE_OBJ_ORGANISMO);

        filConfFinanDTO = new ConfiguracionFinanciacionDTO();
        filConfFinanDTO.setOrganismoTransito(organismoTransito);

        configuracionFinanciacionDataModel = new ArrayList<ConfiguracionFinanciacionDTO>();
    }

    /**
     * Permite inicializar un nuevo detalle de cantidad de cuota.
     */
    public void verAdicionCantidadCuota() {
        selDetCantidadCuotaDTO = new DetalleCantidadCuotaDTO();
        selDetCantidadCuotaDTO.setVariableCondicionFinan(selValorCondicionVO.getVariableCondicionFinanDTO());
    }

    /**
     * Permite editar un determinado registro de la tabla de detalle cantidad cuota.
     */
    public void editarDetCantidadCuota() {
        if (null != selDetCantidadCuotaDTO) {
            editSelDetCantidadCuota = true;
            RequestContext.getCurrentInstance().execute("PF('addDetCantCuoReg').show();");
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Seleccionar un registro de la lista"));
        }
    }

    /**
     * Permite elimnar un determinado registro de la tabla de detalle cantidad cuota.
     */
    public void eliminarDetCantidadCuota() {
        if (null != selDetCantidadCuotaDTO) {
            List<DetalleCantidadCuotaDTO> lstDetalleCantidadCuotaDTOs = selValorCondicionVO.getDetalleCantidadCuotaDM();
            lstDetalleCantidadCuotaDTOs.remove(selDetCantidadCuotaDTO);
            verAdicionCantidadCuota();
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Seleccionar un registro de la lista"));
        }
    }

    /**
     * Permite adicionar a la tabla de detalle cantidad de cuota un nuevo registro para ser editado.
     */
    public void adicionarDetCantidadCuota() {
        if (validarDetCantCuota()) {
            if (!editSelDetCantidadCuota) {
                List<DetalleCantidadCuotaDTO> lstDetCantCuoDTOs = selValorCondicionVO.getDetalleCantidadCuotaDM();
                /*
                 * ingreso de su ultima indexacion para el determinado item.
                 */
                if (lstDetCantCuoDTOs.isEmpty()) {
                    selDetCantidadCuotaDTO.setIndex(1);
                } else {
                    int index = lstDetCantCuoDTOs.get(lstDetCantCuoDTOs.size() - 1).getIndex();
                    selDetCantidadCuotaDTO.setIndex(index + 1);
                }
                /*
                 * ingreso del estilo por defecto
                 */
                selDetCantidadCuotaDTO.setStyleText(STYLE_TEXT_LABEL);
                lstDetCantCuoDTOs.add(selDetCantidadCuotaDTO);
            } else {
                selDetCantidadCuotaDTO.setStyleText(STYLE_TEXT_LABEL);
                editSelDetCantidadCuota = false;
            }

            verAdicionCantidadCuota();

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('addDetCantCuoReg').hide();");
        }

    }

    /**
     * Permite validar el detalle de cantidad de cuotas.
     * 
     * @return validacion
     */
    private boolean validarDetCantCuota() {
        boolean validacion = true;
        if (selDetCantidadCuotaDTO.getValorMinimoFinanciar()
                .compareTo(selDetCantidadCuotaDTO.getValorMaximoFinanciar()) > 0) {
            FacesContext.getCurrentInstance().addMessage(
                    "formAddDetCantCuoReg:txtValMaxFinanDCC",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Valor Máximo a Financiar debe ser Mayor o igual a Valor Mínimo a Financiar"));
            validacion = false;
        }
        if (selDetCantidadCuotaDTO.getCantidadMaximaCouta() < selDetCantidadCuotaDTO.getCantidadMinimaCoutas()) {
            FacesContext.getCurrentInstance().addMessage(
                    "formAddDetCantCuoReg:txtCantMaxCuotDCC",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Cantidad Máxima de Cuotas debe ser mayor o igual a Cantidad Mínima de Cuotas"));
            validacion = false;
        }
        return validacion;
    }

    /**
     * Permite inicializar el objeto que manipula la interfaz.
     */
    public void verAdicionDetPorcCuoIni() {
        selDetPorcCuoIniDTO = new DetallePorcentajeCuotaIniciDTO();
        selDetPorcCuoIniDTO.setVariableCondicionFinan(selValorCondicionVO.getVariableCondicionFinanDTO());
    }

    /**
     * Permite editar un determinado registro de la tabla de detalle porcentaje de cuota inicial
     * 
     */
    public void editarDetPorcCuoIni() {
        if (null != selDetPorcCuoIniDTO) {
            editselDetPorcCuoIni = true;
            RequestContext.getCurrentInstance().execute("PF('addDetPorcCuoIniReg').show();");
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Seleccionar un registro de la lista"));
        }
    }

    /**
     * Permite eliminar un determinado registro de la taba de detalle porcentaje de cuota inicial
     */
    public void eliminarDetPorcCuoIni() {
        if (null != selDetPorcCuoIniDTO) {
            List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaIniciDTOs = selValorCondicionVO
                    .getDetallePorcentajeCuotaIniciDM();
            lstDetallePorcentajeCuotaIniciDTOs.remove(selDetPorcCuoIniDTO);
            verAdicionDetPorcCuoIni();
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Seleccionar un registro de la lista"));
        }
    }

    /**
     * Permite adicionar a la tabla de detalle de porcentaje cuota inicial.
     */
    public void adicionarDetPorcCuoIni() {
        if (validarDetPorcCuotIni()) {
            if (!editselDetPorcCuoIni) {
                List<DetallePorcentajeCuotaIniciDTO> lstDetallePorcentajeCuotaIniciDTOs = selValorCondicionVO
                        .getDetallePorcentajeCuotaIniciDM();
                /*
                 * ingreso de su ultima indexacion para el determinado item.
                 */
                if (lstDetallePorcentajeCuotaIniciDTOs.isEmpty()) {
                    selDetPorcCuoIniDTO.setIndex(1);
                } else {
                    int index = lstDetallePorcentajeCuotaIniciDTOs.get(lstDetallePorcentajeCuotaIniciDTOs.size() - 1)
                            .getIndex();
                    selDetPorcCuoIniDTO.setIndex(index + 1);
                }
                /*
                 * ingreso del estilo por defecto
                 */
                selDetPorcCuoIniDTO.setStyleText(STYLE_TEXT_LABEL);
                lstDetallePorcentajeCuotaIniciDTOs.add(selDetPorcCuoIniDTO);
            } else {
                selDetPorcCuoIniDTO.setStyleText(STYLE_TEXT_LABEL);
                editselDetPorcCuoIni = false;
            }

            verAdicionDetPorcCuoIni();

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('addDetPorcCuoIniReg').hide();");
        }
    }

    /**
     * Valida los campos agregados desde el popUp
     * 
     * @return boolean
     */
    private boolean validarDetPorcCuotIni() {
        boolean validacion = true;
        if (selDetPorcCuoIniDTO.getValorMaximoFinanciar().compareTo(selDetPorcCuoIniDTO.getValorMinimoFinanciar()) < 0) {
            FacesContext.getCurrentInstance().addMessage(
                    "formAddDetPorcCuoReg:txtValMaxFinanDPCI",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Valor Máximo a financiar no puede ser menor al Valor Mínimo."));
            validacion = false;
        }
        if (selDetPorcCuoIniDTO.getPorcentajeCuotaInicial().compareTo(BigDecimal.ZERO) <= 0
                || selDetPorcCuoIniDTO.getPorcentajeCuotaInicial().compareTo(new BigDecimal(100)) > 0) {
            FacesContext.getCurrentInstance().addMessage(
                    "formAddDetPorcCuoReg:txtPorcFinanDPCI",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Valor del Porcentaje debe de estar entre el rango de 1-100."));
            validacion = false;
        }
        return validacion;
    }

    public ConfiguracionFinanciacionDTO getFilConfFinanDTO() {
        return filConfFinanDTO;
    }

    public void setFilConfFinanDTO(ConfiguracionFinanciacionDTO filConfFinanDTO) {
        this.filConfFinanDTO = filConfFinanDTO;
    }

    public List<ConfiguracionFinanciacionDTO> getConfiguracionFinanciacionDataModel() {
        return configuracionFinanciacionDataModel;
    }

    public void setConfiguracionFinanciacionDataModel(
            List<ConfiguracionFinanciacionDTO> configuracionFinanciacionDataModel) {
        this.configuracionFinanciacionDataModel = configuracionFinanciacionDataModel;
    }

    public ConfiguracionFinanciacionDTO getSelConfFinanDTO() {
        return selConfFinanDTO;
    }

    public void setSelConfFinanDTO(ConfiguracionFinanciacionDTO selConfFinanDTO) {
        this.selConfFinanDTO = selConfFinanDTO;
    }

    public OrganismoTransitoDTO getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransitoDTO organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public ConfiguracionFinanVO getConfiguracionFinanVO() {
        return configuracionFinanVO;
    }

    public void setConfiguracionFinanVO(ConfiguracionFinanVO configuracionFinanVO) {
        this.configuracionFinanVO = configuracionFinanVO;
    }

    public ValorCondicionVO getSelValorCondicionVO() {
        return selValorCondicionVO;
    }

    public void setSelValorCondicionVO(ValorCondicionVO selValorCondicionVO) {
        this.selValorCondicionVO = selValorCondicionVO;
    }

    public DetalleCantidadCuotaDTO getSelDetCantidadCuotaDTO() {
        return selDetCantidadCuotaDTO;
    }

    public void setSelDetCantidadCuotaDTO(DetalleCantidadCuotaDTO selDetCantidadCuotaDTO) {
        this.selDetCantidadCuotaDTO = selDetCantidadCuotaDTO;
    }

    public boolean isEditSelDetCantidadCuota() {
        return editSelDetCantidadCuota;
    }

    public void setEditSelDetCantidadCuota(boolean editSelDetCantidadCuota) {
        this.editSelDetCantidadCuota = editSelDetCantidadCuota;
    }

    public DetallePorcentajeCuotaIniciDTO getSelDetPorcCuoIniDTO() {
        return selDetPorcCuoIniDTO;
    }

    public void setSelDetPorcCuoIniDTO(DetallePorcentajeCuotaIniciDTO selDetPorcCuoIniDTO) {
        this.selDetPorcCuoIniDTO = selDetPorcCuoIniDTO;
    }

    public boolean isEditselDetPorcCuoIni() {
        return editselDetPorcCuoIni;
    }

    public void setEditselDetPorcCuoIni(boolean editselDetPorcCuoIni) {
        this.editselDetPorcCuoIni = editselDetPorcCuoIni;
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
