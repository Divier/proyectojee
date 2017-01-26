package co.com.datatools.c2.managed_bean.administracion.horario_funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.datatools.c2.dto.eventos.CargoDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioFiltrosDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioRespuestaDTO;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;

public class ConfiguracionHorarioFL extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String NOMBRE_BEAN = "configuracionHorarioFL";

    private List<ConfiguracionHorarioRespuestaDTO> configuracionHorarioRespuestaDTOs;
    private List<ConfiguracionHorarioFiltrosDTO> configuracionHorarioFiltrosDTOs;
    private ConfiguracionHorarioDTO configuracionHorarioDTO;
    private List<SelectItem> selectItemSels;

    public ConfiguracionHorarioFL() {
        configuracionHorarioRespuestaDTOs = new ArrayList<ConfiguracionHorarioRespuestaDTO>();
        configuracionHorarioFiltrosDTOs = new ArrayList<ConfiguracionHorarioFiltrosDTO>();
        configuracionHorarioDTO = new ConfiguracionHorarioDTO();
        configuracionHorarioDTO.setCargoDTO(new CargoDTO());
    }

    public ConfiguracionHorarioDTO getConfiguracionHorarioDTO() {
        return configuracionHorarioDTO;
    }

    public void setConfiguracionHorarioDTO(ConfiguracionHorarioDTO configuracionHorarioDTO) {
        this.configuracionHorarioDTO = configuracionHorarioDTO;
    }

    public List<SelectItem> getSelectItemSels() {
        return selectItemSels;
    }

    public void setSelectItemSels(List<SelectItem> selectItemSels) {
        this.selectItemSels = selectItemSels;
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

}
