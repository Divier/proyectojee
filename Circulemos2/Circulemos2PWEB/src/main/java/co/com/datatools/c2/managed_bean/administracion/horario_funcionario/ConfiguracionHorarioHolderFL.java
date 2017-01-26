package co.com.datatools.c2.managed_bean.administracion.horario_funcionario;

import java.util.ArrayList;
import java.util.List;

import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioFiltrosDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioRespuestaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class ConfiguracionHorarioHolderFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "configuracionHorarioHolderFL";

    private List<ConfiguracionHorarioRespuestaDTO> configuracionHorarioRespuestaDTOs;
    private List<ConfiguracionHorarioFiltrosDTO> configuracionHorarioFiltrosDTOs;
    private ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO;
    private ConfiguracionHorarioRespuestaDTO configuracionHorarioRespuestaSelDTO;

    public ConfiguracionHorarioHolderFL() {
        configuracionHorarioRespuestaDTOs = new ArrayList<ConfiguracionHorarioRespuestaDTO>();
        configuracionHorarioFiltrosDTOs = new ArrayList<ConfiguracionHorarioFiltrosDTO>();
        configuracionHorarioFiltrosDTO = new ConfiguracionHorarioFiltrosDTO();
    }

    public List<ConfiguracionHorarioRespuestaDTO> getConfiguracionHorarioRespuestaDTOs() {
        return configuracionHorarioRespuestaDTOs;
    }

    public void setConfiguracionHorarioRespuestaDTOs(
            List<ConfiguracionHorarioRespuestaDTO> configuracionHorarioRespuestaDTOs) {
        this.configuracionHorarioRespuestaDTOs = configuracionHorarioRespuestaDTOs;
    }

    public List<ConfiguracionHorarioFiltrosDTO> getConfiguracionHorarioFiltrosDTOs() {
        return configuracionHorarioFiltrosDTOs;
    }

    public void setConfiguracionHorarioFiltrosDTOs(
            List<ConfiguracionHorarioFiltrosDTO> configuracionHorarioFiltrosDTOs) {
        this.configuracionHorarioFiltrosDTOs = configuracionHorarioFiltrosDTOs;
    }

    public ConfiguracionHorarioFiltrosDTO getConfiguracionHorarioFiltrosDTO() {
        return configuracionHorarioFiltrosDTO;
    }

    public void setConfiguracionHorarioFiltrosDTO(ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO) {
        this.configuracionHorarioFiltrosDTO = configuracionHorarioFiltrosDTO;
    }

    public ConfiguracionHorarioRespuestaDTO getConfiguracionHorarioRespuestaSelDTO() {
        return configuracionHorarioRespuestaSelDTO;
    }

    public void setConfiguracionHorarioRespuestaSelDTO(
            ConfiguracionHorarioRespuestaDTO configuracionHorarioRespuestaSelDTO) {
        this.configuracionHorarioRespuestaSelDTO = configuracionHorarioRespuestaSelDTO;
    }

}
