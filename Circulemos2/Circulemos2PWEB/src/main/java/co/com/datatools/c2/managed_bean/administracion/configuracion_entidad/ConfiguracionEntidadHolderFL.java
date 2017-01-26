package co.com.datatools.c2.managed_bean.administracion.configuracion_entidad;

import java.util.List;

import co.com.datatools.c2.dto.ConfiguracionEntidadDTO;
import co.com.datatools.c2.dto.TipoEntidadDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class ConfiguracionEntidadHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "configuracionEntidadHolderFL";

    private ConfiguracionEntidadDTO configuracionEntidadDTO;
    private List<ConfiguracionEntidadDTO> configuracionEntidadDTOs;
    private ConfiguracionEntidadDTO configuracionEntidadSelDTO;

    public ConfiguracionEntidadHolderFL() {
        configuracionEntidadDTO = new ConfiguracionEntidadDTO();
        configuracionEntidadDTO.setTipoEntidadDTO(new TipoEntidadDTO());
    }

    public ConfiguracionEntidadDTO getConfiguracionEntidadDTO() {
        return configuracionEntidadDTO;
    }

    public void setConfiguracionEntidadDTO(ConfiguracionEntidadDTO configuracionEntidadDTO) {
        this.configuracionEntidadDTO = configuracionEntidadDTO;
    }

    public List<ConfiguracionEntidadDTO> getConfiguracionEntidadDTOs() {
        return configuracionEntidadDTOs;
    }

    public void setConfiguracionEntidadDTOs(List<ConfiguracionEntidadDTO> configuracionEntidadDTOs) {
        this.configuracionEntidadDTOs = configuracionEntidadDTOs;
    }

    public ConfiguracionEntidadDTO getConfiguracionEntidadSelDTO() {
        return configuracionEntidadSelDTO;
    }

    public void setConfiguracionEntidadSelDTO(ConfiguracionEntidadDTO configuracionEntidadSelDTO) {
        this.configuracionEntidadSelDTO = configuracionEntidadSelDTO;
    }

}
