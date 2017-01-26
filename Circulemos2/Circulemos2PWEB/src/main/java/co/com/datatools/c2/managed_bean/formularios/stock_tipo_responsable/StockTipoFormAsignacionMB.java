package co.com.datatools.c2.managed_bean.formularios.stock_tipo_responsable;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.formularios.StockTipoResponsableDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.formularios.IRFormulario;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

/**
 * Managed bean que permite controlar las acciones del caso de uso <b>CU_CIR20_DAT_NUM_017 'Administrar Stock por Tipo de Formulario y Tipo de
 * Asignacion'</b>
 * 
 * @author luis.forero (2015-01-30) giovanni.velandia(mod 2015-08-20)
 * 
 */
@ManagedBean
@SessionScoped
public class StockTipoFormAsignacionMB extends AbstractC2ManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(StockTipoFormAsignacionMB.class.getName());

    private static final Class<StockTipoResponsableHolderFL> OBJ_STOCK_TIPO_RESPONSABLE_HOLDER_FL = StockTipoResponsableHolderFL.class;

    private static final String STOCK_TIPO_RESPONSABLE_HOLDER_FL = "stockTipoResponsableHolderFL";

    @EJB
    private IRFormulario iRFormularioEJB;

    public StockTipoFormAsignacionMB() {
    }

    /*
    *//**
     * Ejecuta la consulta
     * 
     * @author luis.forero giovanni.velandia(mod mod 2015-08-20)
     */
    public void consultar() {
        logger.debug(StockTipoFormAsignacionMB.class.getName().concat("::consultar()"));
        final StockTipoResponsableHolderFL stockTipoResponsableHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_RESPONSABLE_HOLDER_FL, STOCK_TIPO_RESPONSABLE_HOLDER_FL);
        if (stockTipoResponsableHolderFL.getStockTipoResponsableDTOs() != null)
            stockTipoResponsableHolderFL.getStockTipoResponsableDTOs().clear();
        List<StockTipoResponsableDTO> lstResponse = iRFormularioEJB
                .consultarStockTipoResponsable(stockTipoResponsableHolderFL.getStockTipoResponsableDTO());
        stockTipoResponsableHolderFL.setStockTipoResponsableDTOs(null);
        if (lstResponse.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            stockTipoResponsableHolderFL.setStockTipoResponsableDTOs(lstResponse);
            // CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(lstResponse.size());
        }

    }

    /**
     * Carga el detalle
     * 
     * @author luis.forero(2015-01-30) giovanni.velandia(mod 2015-08-21)
     */
    public void cargarDetalle() {
        logger.debug(StockTipoFormAsignacionMB.class.getName().concat("::cargarDetalle()"));
        final StockTipoResponsableHolderFL stockTipoResponsableHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_RESPONSABLE_HOLDER_FL, STOCK_TIPO_RESPONSABLE_HOLDER_FL);
        if (stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado() != null) {
            StockTipoResponsableDTO seleccionado = new StockTipoResponsableDTO();
            seleccionado.setCodigoOrganismo(stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado()
                    .getCodigoOrganismo());
            seleccionado.setEstadoStock(stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado()
                    .getEstadoStock());
            seleccionado.setId(stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado().getId());
            seleccionado.setStockMaximo(stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado()
                    .getStockMaximo());
            seleccionado.setStockMaximo(stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado()
                    .getStockMaximo());
            seleccionado.setStockMinimo(stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado()
                    .getStockMinimo());
            seleccionado.setTipoFormulario(stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado()
                    .getTipoFormulario());
            seleccionado.setTipoResponsable(stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado()
                    .getTipoResponsable());
            stockTipoResponsableHolderFL.setStockTipoResponsableCrearDTO(seleccionado);
            stockTipoResponsableHolderFL.setStockTipoResponsableDTOSeleccionado(null);
        } else {
            CirculemosAccesoBundleGeneral.addMensajeNoSeleccionItem();
        }
    }

    /**
     * Lleva a cabo el registro de un stock
     * 
     * @return true si fue exitoso el registro, false de lo contrario.
     * @author luis.forero(2015-01-30) giovanni.velandia(mod 2015-08-20)
     */
    public boolean registrarStock() {
        logger.debug(StockTipoFormAsignacionMB.class.getName().concat("::registrarStock()"));
        final StockTipoResponsableHolderFL stockTipoResponsableHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_RESPONSABLE_HOLDER_FL, STOCK_TIPO_RESPONSABLE_HOLDER_FL);
        try {
            stockTipoResponsableHolderFL.getStockTipoResponsableCrearDTO().setCodigoOrganismo(getOrganismoTransito());
            iRFormularioEJB.registrarStockTipoResponsable(stockTipoResponsableHolderFL
                    .getStockTipoResponsableCrearDTO());
            CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        return true;
    }

    /**
     * Lleva a cabo la actualizacion de un stock
     * 
     * @return true si fue exitoso la actualizacion, false de lo contrario.
     * @author luis.forero(2015-01-30) giovanni.velandia(mod 2015-08-21)
     */
    public boolean actualizarStock() {
        logger.debug(StockTipoFormAsignacionMB.class.getName().concat("::actualizarStock()"));
        final StockTipoResponsableHolderFL stockTipoResponsableHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_RESPONSABLE_HOLDER_FL, STOCK_TIPO_RESPONSABLE_HOLDER_FL);
        try {
            iRFormularioEJB.actualizarStockTipoResponsable(stockTipoResponsableHolderFL
                    .getStockTipoResponsableCrearDTO());
            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }
        return true;
    }

    /**
     * Lleva a cabo la eliminacion de un stock seleccionado
     * 
     * @author luis.forero(2015-01-30) giovanni.velandia(mod 2015-08-21)
     */
    public void eliminarStock() {
        logger.debug(StockTipoFormAsignacionMB.class.getName().concat("::eliminarStock()"));
        final StockTipoResponsableHolderFL stockTipoResponsableHolderFL = findFlowObject(
                OBJ_STOCK_TIPO_RESPONSABLE_HOLDER_FL, STOCK_TIPO_RESPONSABLE_HOLDER_FL);
        if (stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado() != null) {
            iRFormularioEJB.eliminarStockTipoResponsable(stockTipoResponsableHolderFL
                    .getStockTipoResponsableDTOSeleccionado().getId());

            CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
            stockTipoResponsableHolderFL.getStockTipoResponsableDTOs().remove(
                    stockTipoResponsableHolderFL.getStockTipoResponsableDTOSeleccionado());
            stockTipoResponsableHolderFL.setStockTipoResponsableDTOSeleccionado(null);
        } else {
            CirculemosAccesoBundleGeneral.addMensajeNoSeleccionItem();
        }
    }
}
