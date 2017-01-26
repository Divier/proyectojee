package co.com.datatools.c2.managed_bean.coactivo.administracion.condiciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionCoactivoDTO;

/**
 * Value Object que representa una configuración de coactivo
 * 
 * @author Dixon.Alvarez
 * 
 */
public class ConfiguracionCoactivoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Configuracion de coactivo
     */
    private ConfiguracionCoactivoDTO configuracionCoactivoDTO;

    /**
     * Lista que representa las condiciones de coactivo cargadas
     */
    private List<CondicionCoactivoVO> lstCondicionCoactivoVOs;

    /**
     * Identifica si la Fecha Inicial es o no menor igual a la actual
     */
    private boolean fecIniMenorIgualActual;

    /**
     * Identifica si los campos son editables o no.
     */
    private boolean camposNoEditables;

    /**
     * Inicializa los atributos de la configuracion VO.
     */
    public ConfiguracionCoactivoVO() {
        lstCondicionCoactivoVOs = new ArrayList<CondicionCoactivoVO>();
        configuracionCoactivoDTO = new ConfiguracionCoactivoDTO();
    }

    public ConfiguracionCoactivoDTO getConfiguracionCoactivoDTO() {
        return configuracionCoactivoDTO;
    }

    public void setConfiguracionCoactivoDTO(ConfiguracionCoactivoDTO configuracionCoactivoDTO) {
        this.configuracionCoactivoDTO = configuracionCoactivoDTO;
    }

    public List<CondicionCoactivoVO> getLstCondicionCoactivoVOs() {
        return lstCondicionCoactivoVOs;
    }

    public void setLstCondicionCoactivoVOs(List<CondicionCoactivoVO> lstCondicionCoactivoVOs) {
        this.lstCondicionCoactivoVOs = lstCondicionCoactivoVOs;
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
