package co.com.datatools.c2.managed_bean.administracion.configuracion_entidad;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.ConfiguracionEntidadDTO;
import co.com.datatools.c2.dto.TipoEntidadDTO;
import co.com.datatools.c2.dto.comun.EntidadOficioDTO;
import co.com.datatools.c2.enumeracion.EnumMunicipio;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.IRAdministracion;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * 
 * @author giovanni.velandia
 *
 */
@ManagedBean
@SessionScoped
public class ConfiguracionEntidadMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(ConfiguracionEntidadMB.class.getName());

    @EJB
    private IRAdministracion iRAdministracion;

    /**
     * Consulta configuracion entidad
     * 
     * @author giovanni.velandia
     */
    public void consultarConfiguracionEntidad() {
        LOGGER.debug("ConfiguracionEntidadMB::consultarConfiguracionEntidad()");
        ConfiguracionEntidadHolderFL configuracionEntidadHolderFL = findFlowObject(ConfiguracionEntidadHolderFL.class,
                ConfiguracionEntidadHolderFL.NOMBRE_BEAN);
        limpiarCampos();

        configuracionEntidadHolderFL.setConfiguracionEntidadSelDTO(null);
        configuracionEntidadHolderFL.setConfiguracionEntidadDTOs(new ArrayList<ConfiguracionEntidadDTO>());

        configuracionEntidadHolderFL.setConfiguracionEntidadDTOs(iRAdministracion
                .consultarConfiguracionEntidad(configuracionEntidadHolderFL.getConfiguracionEntidadDTO()));

        if (configuracionEntidadHolderFL.getConfiguracionEntidadDTOs() == null
                || configuracionEntidadHolderFL.getConfiguracionEntidadDTOs().isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            CirculemosAccesoBundleGeneral
                    .addMensajeResultadoConsulta(configuracionEntidadHolderFL.getConfiguracionEntidadDTOs().size());
        }
    }

    /**
     * @author giovanni.velandia
     */
    public void limpiarCampos() {
        LOGGER.debug("ConfiguracionEntidadMB::limpiarCampos()");
        ConfiguracionEntidadFL configuracionEntidadFL = findFlowObject(ConfiguracionEntidadFL.class,
                ConfiguracionEntidadFL.NOMBRE_BEAN);
        configuracionEntidadFL.setConfiguracionEntidadDTO(new ConfiguracionEntidadDTO());
        configuracionEntidadFL.getConfiguracionEntidadDTO().setTipoEntidadDTO(new TipoEntidadDTO());
        configuracionEntidadFL.getConfiguracionEntidadDTO().setEntidadOficioDTO(new EntidadOficioDTO());
    }

    /**
     * Metodo que se encarga del registro de una configuracion
     * 
     * @author giovanni.velandia
     */
    public boolean registrarConfiguracionEntidad() {
        LOGGER.debug("ConfiguracionEntidadMB::registrarConfiguracionEntidad()");
        ConfiguracionEntidadFL configuracionEntidadFL = findFlowObject(ConfiguracionEntidadFL.class,
                ConfiguracionEntidadFL.NOMBRE_BEAN);
        try {
            iRAdministracion.registrarConfiguracionEntidad(configuracionEntidadFL.getConfiguracionEntidadDTO());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        limpiarCampos();
        CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();
        return true;
    }

    /**
     * Metodo que se encarga de la actulizacion de una configuracion
     * 
     * @author giovanni.velandia
     */
    public boolean actualizarConfiguracionEntidad() {
        LOGGER.debug("ConfiguracionEntidadMB::actualizarConfiguracionEntidad()");
        ConfiguracionEntidadFL configuracionEntidadFL = findFlowObject(ConfiguracionEntidadFL.class,
                ConfiguracionEntidadFL.NOMBRE_BEAN);
        iRAdministracion.actualizarConfiguracionEntidad(configuracionEntidadFL.getConfiguracionEntidadDTO());
        limpiarCampos();
        CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
        return true;
    }

    /**
     * Se encarga de mostrar el detalle de una configuracion seleccionada
     * 
     * @author giovanni.velandia}
     */
    public void detalleConfiguracionEntidad() {
        LOGGER.debug("ConfiguracionEntidadMB::detalleConfiguracionEntidad()");
        ConfiguracionEntidadFL configuracionEntidadFL = findFlowObject(ConfiguracionEntidadFL.class,
                ConfiguracionEntidadFL.NOMBRE_BEAN);
        ConfiguracionEntidadHolderFL configuracionEntidadHolderFL = findFlowObject(ConfiguracionEntidadHolderFL.class,
                ConfiguracionEntidadHolderFL.NOMBRE_BEAN);
        configuracionEntidadFL.setConfiguracionEntidadDTO(iRAdministracion
                .consultarConfiguracionEntidad(configuracionEntidadHolderFL.getConfiguracionEntidadSelDTO().getId()));
    }

    /**
     * Se encarga de consultar ua ciudad (Para este caso la ciudad de gayaquil)
     * 
     * @author giovanni.velandia
     */
    public void consultarMunicipioGuayaquil() {
        LOGGER.debug("ConfiguracionEntidadMB::consultarMunicipioGuayaquil()");
        ConfiguracionEntidadFL configuracionEntidadFL = findFlowObject(ConfiguracionEntidadFL.class,
                ConfiguracionEntidadFL.NOMBRE_BEAN);
        configuracionEntidadFL.getConfiguracionEntidadDTO()
                .setMunicipioDTO(iRAdministracion.consultarMunicipio(EnumMunicipio.GUAYAQUIL.getId()));
    }
}
