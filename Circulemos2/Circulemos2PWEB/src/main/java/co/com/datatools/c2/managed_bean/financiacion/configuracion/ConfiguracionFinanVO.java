package co.com.datatools.c2.managed_bean.financiacion.configuracion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionFinanciacionDTO;

/**
 * Value Object que representa una configuración de financiacion
 * 
 * @author luis.forero
 * 
 */
public class ConfiguracionFinanVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Configuracion de financiacion
     */
    private ConfiguracionFinanciacionDTO configuracionFinanciacionDTO;

    /**
     * Lista que representa las condiciones de financiacion cargadas
     */
    private List<CondicionFinanciacionVO> lstCondicionFinanciacionVOs;

    /**
     * Identifica si la Fecha Inicial es o no menor igual a la actual
     */
    private boolean fecIniMenorIgualActual;

    /**
     * Identifica si los camos son editables o no.
     */
    private boolean camposNoEditables;

    /**
     * Inicializa los atributos de la configuracion VO.
     */
    public ConfiguracionFinanVO() {
        lstCondicionFinanciacionVOs = new ArrayList<CondicionFinanciacionVO>();
        configuracionFinanciacionDTO = new ConfiguracionFinanciacionDTO();
    }

    public ConfiguracionFinanciacionDTO getConfiguracionFinanciacionDTO() {
        return configuracionFinanciacionDTO;
    }

    public void setConfiguracionFinanciacionDTO(ConfiguracionFinanciacionDTO configuracionFinanciacionDTO) {
        this.configuracionFinanciacionDTO = configuracionFinanciacionDTO;
    }

    public List<CondicionFinanciacionVO> getLstCondicionFinanciacionVOs() {
        return lstCondicionFinanciacionVOs;
    }

    public void setLstCondicionFinanciacionVOs(List<CondicionFinanciacionVO> lstCondicionFinanciacionVOs) {
        this.lstCondicionFinanciacionVOs = lstCondicionFinanciacionVOs;
    }

    public boolean isFecIniMenorIgualActual() {
        return fecIniMenorIgualActual;
    }

    public void setFecIniMenorIgualActual(boolean fecIniMenorIgualActual) {
        this.fecIniMenorIgualActual = fecIniMenorIgualActual;
    }

    public boolean isCamposNoEditables() {
        return camposNoEditables;
    }

    public void setCamposNoEditables(boolean camposNoEditables) {
        this.camposNoEditables = camposNoEditables;
    }

}
