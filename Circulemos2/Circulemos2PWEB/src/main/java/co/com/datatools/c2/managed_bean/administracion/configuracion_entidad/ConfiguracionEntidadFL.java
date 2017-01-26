package co.com.datatools.c2.managed_bean.administracion.configuracion_entidad;

import co.com.datatools.c2.dto.ConfiguracionEntidadDTO;
import co.com.datatools.c2.dto.TipoEntidadDTO;
import co.com.datatools.c2.dto.comun.EntidadOficioDTO;
import co.com.datatools.util.web.mb.AbstractSwfManagedBean;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class ConfiguracionEntidadFL extends AbstractSwfManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "configuracionEntidadFL";

    private ConfiguracionEntidadDTO configuracionEntidadDTO;

    public ConfiguracionEntidadFL() {
        configuracionEntidadDTO = new ConfiguracionEntidadDTO();
        configuracionEntidadDTO.setTipoEntidadDTO(new TipoEntidadDTO());
        configuracionEntidadDTO.setEntidadOficioDTO(new EntidadOficioDTO());
    }

    public ConfiguracionEntidadDTO getConfiguracionEntidadDTO() {
        return configuracionEntidadDTO;
    }

    public void setConfiguracionEntidadDTO(ConfiguracionEntidadDTO configuracionEntidadDTO) {
        this.configuracionEntidadDTO = configuracionEntidadDTO;
    }
}
