/**
 * 
 */
package co.com.datatools.c2.managed_bean.formularios.stock_tipo_formulario;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.comun.OrganismoTransitoDTO;
import co.com.datatools.c2.dto.formularios.StockTipoFormularioDTO;
import co.com.datatools.c2.dto.formularios.TipoFormularioDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.formularios.IRAdministracionFormularios;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Managed bean encargado de permitir administrar stocks por tipos de formularios del caso de uso <b>CU_CIR20_DAT_NUM_013 'Administrar Stock por Tipo
 * de Formulario y Organismo de Transito'</b>
 * 
 * @author luis.forero (2015-02-02)
 * 
 */
@ManagedBean
@SessionScoped
public class StockTipoFormularioMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;

    private static final String STOCK_TIPO_FORMULARIO_HOLDER_FL = "stockTipoFormularioHolderFL";

    private static final Class<StockTipoFormularioHolderFL> OBJ_STOCK_TIPO_FORMULARIO_HOLDER_FL = StockTipoFormularioHolderFL.class;

    private static final Logger logger = Logger.getLogger(StockTipoFormularioMB.class.getName());

    @EJB
    private IRAdministracionFormularios administracionFormulariosEJB;

    private OrganismoTransitoDTO organismoTransitoDTO;

    /**
     * 
     */
    public StockTipoFormularioMB() {
        organismoTransitoDTO = getOrganismoTransito();
    }

    /**
     * Ejecuta la accion de consulta con los filtros ingresados
     * 
     * @author luis.forero (2015-02-03)
     */
    public void consultar() {
        logger.debug(StockTipoFormularioMB.class.getName().concat("::consultar()"));
        final StockTipoFormularioHolderFL stockTipoFormularioHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_FORMULARIO_HOLDER_FL, STOCK_TIPO_FORMULARIO_HOLDER_FL);

        StockTipoFormularioDTO stockTipoFormularioDTO = new StockTipoFormularioDTO();
        if (stockTipoFormularioHolderFL.getfIdTipoFormulario() != null) {
            stockTipoFormularioDTO.setTipoFormulario(new TipoFormularioDTO(stockTipoFormularioHolderFL
                    .getfIdTipoFormulario()));
        }

        stockTipoFormularioDTO.setOrganismoTransitoDTO(getOrganismoTransito());
        List<StockTipoFormularioDTO> lstResponse = administracionFormulariosEJB
                .consultarStockTipoFormulario(stockTipoFormularioDTO);
        stockTipoFormularioHolderFL.getLstStockTipoFormularioDTOs().clear();
        stockTipoFormularioHolderFL.setStockTipoFormSeleccionado(null);
        if (!lstResponse.isEmpty()) {

            stockTipoFormularioHolderFL.setLstStockTipoFormularioDTOs(lstResponse);
            // CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lstResponse.size());

        } else {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        }
    }

    /**
     * Lleva a cabo la accion de registro de un stock de formulario
     * 
     * @return true si el registro fue exitoso, false de lo contrario
     * @author luis.forero (2015-02-03)
     */
    public boolean registrarStockTipoFormulario() {
        logger.debug(StockTipoFormularioMB.class.getName().concat("::registrarStockTipoFormulario()"));
        final StockTipoFormularioHolderFL stockTipoFormularioHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_FORMULARIO_HOLDER_FL, STOCK_TIPO_FORMULARIO_HOLDER_FL);
        try {
            stockTipoFormularioHolderFL.getStockTipoFormularioDTO().setOrganismoTransitoDTO(getOrganismoTransito());
            administracionFormulariosEJB.registrarStockTipoFormulario(stockTipoFormularioHolderFL
                    .getStockTipoFormularioDTO());
            CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        return true;
    }

    /**
     * Lleva a cabo la accion de actualizacion de un stock de formulario
     * 
     * 
     * @author luis.forero (2015-02-03)
     */
    public void actualizarStockTipoFormulario() {
        logger.debug(StockTipoFormularioMB.class.getName().concat("::actualizarStockTipoFormulario()"));
        final StockTipoFormularioHolderFL stockTipoFormularioHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_FORMULARIO_HOLDER_FL, STOCK_TIPO_FORMULARIO_HOLDER_FL);
        try {
            administracionFormulariosEJB.actualizarStockTipoFormulario(stockTipoFormularioHolderFL
                    .getStockTipoFormularioDTO());
            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);

        }

    }

    /**
     * Ejecuta la accion de eliminacion de un stock seleccionado en la interfaz
     * 
     * @author luis.forero (2015-02-03)
     */
    public void eliminarStockTipoFormulario() {
        logger.debug(StockTipoFormularioMB.class.getName().concat("::eliminarStockTipoFormulario()"));
        final StockTipoFormularioHolderFL stockTipoFormularioHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_FORMULARIO_HOLDER_FL, STOCK_TIPO_FORMULARIO_HOLDER_FL);
        if (stockTipoFormularioHolderFL.getStockTipoFormSeleccionado() == null) {
            CirculemosAccesoBundleGeneral.addMensajeNoSeleccionItem();
        } else {
            try {
                administracionFormulariosEJB.eliminarStockTipoFormulario(stockTipoFormularioHolderFL
                        .getStockTipoFormSeleccionado().getTipoFormulario().getId());
                CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
                stockTipoFormularioHolderFL.getLstStockTipoFormularioDTOs().remove(
                        stockTipoFormularioHolderFL.getStockTipoFormSeleccionado());
            } catch (CirculemosNegocioException e) {
                CirculemosErrorHandler.handleException(e);
            }
        }

    }

    /**
     * Carga al objeto manejado desde la interfaz de actualizacion los datos del stock seleccionado
     */
    public void cargarStockTipoFormulario() {
        logger.debug(StockTipoFormularioMB.class.getName().concat("::cargarStockTipoFormulario()"));
        final StockTipoFormularioHolderFL stockTipoFormularioHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_FORMULARIO_HOLDER_FL, STOCK_TIPO_FORMULARIO_HOLDER_FL);
        if (stockTipoFormularioHolderFL.getStockTipoFormSeleccionado() == null) {
            CirculemosAccesoBundleGeneral.addMensajeNoSeleccionItem();
        } else {
            stockTipoFormularioHolderFL.setStockTipoFormularioDTO(stockTipoFormularioHolderFL
                    .getStockTipoFormSeleccionado());
        }
    }

    public OrganismoTransitoDTO getOrganismoTransitoDTO() {
        return organismoTransitoDTO;
    }

    public void setOrganismoTransitoDTO(OrganismoTransitoDTO organismoTransitoDTO) {
        this.organismoTransitoDTO = organismoTransitoDTO;
    }
}
