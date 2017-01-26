package co.com.datatools.c2.managed_bean.administracion.horario_funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioFiltrosDTO;
import co.com.datatools.c2.dto.eventos.ConfiguracionHorarioRespuestaDTO;
import co.com.datatools.c2.dto.eventos.DiaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRCargo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Manejo proceso de impugnacion
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class ConfiguracionHorarioMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(ConfiguracionHorarioMB.class.getName());

    private static final String NOMBRE_BUNDLE = "labelHorarioFuncionario";

    @EJB
    private IRCargo iRCargo;

    public ConfiguracionHorarioMB() {
    }

    /**
     * Se encarga de registar una configuracion de horario para un cargo
     * 
     * @author giovanni.velandia
     */
    public void consultar() {
        LOGGER.debug("ConfiguracionHorarioMB::consultar()");
        ConfiguracionHorarioHolderFL configuracionHorarioHolderFL = findFlowObject(ConfiguracionHorarioHolderFL.class,
                ConfiguracionHorarioHolderFL.NOMBRE_BEAN);

        configuracionHorarioHolderFL
                .setConfiguracionHorarioRespuestaDTOs(new ArrayList<ConfiguracionHorarioRespuestaDTO>(0));

        configuracionHorarioHolderFL.setConfiguracionHorarioRespuestaDTOs(iRCargo
                .consultarConfiguracionHoraioDia(configuracionHorarioHolderFL.getConfiguracionHorarioFiltrosDTO()));

        if (configuracionHorarioHolderFL.getConfiguracionHorarioRespuestaDTOs() == null
                || configuracionHorarioHolderFL.getConfiguracionHorarioRespuestaDTOs().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
            return;
        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(
                    configuracionHorarioHolderFL.getConfiguracionHorarioRespuestaDTOs().size());
        }

    }

    /**
     * Se encarga de registar una configuracion de horario para un cargo para el registro
     * 
     * @author giovanni.velandia
     */
    public void consultarConfiguracionHorario() {
        LOGGER.debug("ConfiguracionHorarioMB::consultarConfiguracionHorario()");
        ConfiguracionHorarioFL configuracionHorarioFL = findFlowObject(ConfiguracionHorarioFL.class,
                ConfiguracionHorarioFL.NOMBRE_BEAN);
        ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO = new ConfiguracionHorarioFiltrosDTO();

        // Limpiar consulta
        configuracionHorarioFL.setConfiguracionHorarioRespuestaDTOs(new ArrayList<ConfiguracionHorarioRespuestaDTO>());

        configuracionHorarioFiltrosDTO
                .setIdCargo(configuracionHorarioFL.getConfiguracionHorarioDTO().getCargoDTO().getId());

        if (configuracionHorarioFL.getSelectItemSels() != null
                && !configuracionHorarioFL.getSelectItemSels().isEmpty()) {
            configuracionHorarioFiltrosDTO.setDias(new ArrayList<Integer>());
            for (SelectItem itSelectItem : configuracionHorarioFL.getSelectItemSels()) {
                configuracionHorarioFiltrosDTO.getDias().add(Integer.parseInt(String.valueOf(itSelectItem.getValue())));
            }
        }
        configuracionHorarioFL
                .setConfiguracionHorarioRespuestaDTOs(consultarConfiguracionHorario(configuracionHorarioFiltrosDTO));
    }

    /**
     * Se encarga de registar una configuracion de horario
     * 
     * @param configuracionHorarioFiltrosDTO
     * @return
     */
    private List<ConfiguracionHorarioRespuestaDTO> consultarConfiguracionHorario(
            ConfiguracionHorarioFiltrosDTO configuracionHorarioFiltrosDTO) {
        return iRCargo.consultarConfiguracionHorario(configuracionHorarioFiltrosDTO);
    }

    /**
     * Se encarga de registar una configuracion de horario para un cargo
     * 
     * @author giovanni.velandia
     */
    public boolean registrarConfiguracionHorario() {
        LOGGER.debug("ConfiguracionHorarioMB::registrarConfiguracionHorario()");
        List<ConfiguracionHorarioDTO> configuracionHorarioDTOs = null;
        ConfiguracionHorarioFL configuracionHorarioFL = findFlowObject(ConfiguracionHorarioFL.class,
                ConfiguracionHorarioFL.NOMBRE_BEAN);

        if (configuracionHorarioFL.getSelectItemSels() == null
                || configuracionHorarioFL.getSelectItemSels().isEmpty()) {
            getFacesContext().addMessage("form-contenido:tabla-resultados", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, null, getBundle(NOMBRE_BUNDLE).getString("msg_val_sel_dia")));
            return false;
        }

        configuracionHorarioDTOs = new ArrayList<>();
        for (SelectItem selectItem : configuracionHorarioFL.getSelectItemSels()) {
            ConfiguracionHorarioDTO configuracionHorarioDTO = new ConfiguracionHorarioDTO();
            DiaDTO diaDTO = new DiaDTO();
            diaDTO.setId((Integer) selectItem.getValue());

            // Cargo
            configuracionHorarioDTO.setCargoDTO(configuracionHorarioFL.getConfiguracionHorarioDTO().getCargoDTO());
            // Dia
            configuracionHorarioDTO.setDiaDTO(diaDTO);
            // Fecha inicio vigencia
            configuracionHorarioDTO
                    .setFechaInicio(configuracionHorarioFL.getConfiguracionHorarioDTO().getFechaInicio());
            // Hora Inicio
            // configuracionHorarioDTO.setHoraInicio(configuracionHorarioFL.getConfiguracionHorarioDTO().getHoraInicio());
            // Hora Fin
            // configuracionHorarioDTO.setHoraFin(configuracionHorarioFL.getConfiguracionHorarioDTO().getHoraFin());
            configuracionHorarioDTOs.add(configuracionHorarioDTO);
        }

        try {
            iRCargo.registrarConfiguracionHorario(configuracionHorarioDTOs);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        return true;
    }
}
