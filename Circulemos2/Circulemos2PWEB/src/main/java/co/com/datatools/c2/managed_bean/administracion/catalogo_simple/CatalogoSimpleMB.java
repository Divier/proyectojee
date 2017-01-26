package co.com.datatools.c2.managed_bean.administracion.catalogo_simple;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;
import co.com.datatools.c2.negocio.interfaces.parametrizacion.IRCatalogo;
import co.com.datatools.c2.web.util.AbstractC2ManagedBean;
import co.com.datatools.c2.web.util.CirculemosAccesoBundleGeneral;
import co.com.datatools.c2.web.util.CirculemosErrorHandler;

@ManagedBean
@SessionScoped
public class CatalogoSimpleMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(CatalogoSimpleMB.class.getName());
    // Variables de objetos spring web flow
    private static final String CATALOGO_SIMPLE_HOLDER_FL = "catalogoSimpleHolderFL";
    private static final String CATALOGO_SIMPLE_FL = "catalogoSimpleFL";

    // Inyeccion EJB
    @EJB
    private IRCatalogo iRCatalogo;

    /**
     * Metodo encargado de consultar todos los catalogos simples del sistema
     */
    public void consultarCatalogosSimples() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::consultarCatalogosSimples()"));

        final CatalogoSimpleHolderFL catalogoSimpleHolderFL = findFlowObject(CatalogoSimpleHolderFL.class,
                CATALOGO_SIMPLE_HOLDER_FL);

        List<CatalogoDTO> catalogoDTOs = new ArrayList<CatalogoDTO>();

        catalogoSimpleHolderFL.getFiltroCatalogoDTO().setIdCatalogoDependencia(false);

        catalogoDTOs = iRCatalogo.consultarCatalogos(catalogoSimpleHolderFL.getFiltroCatalogoDTO());

        catalogoSimpleHolderFL.setCatalogoDTOs(new ArrayList<CatalogoDTO>());
        if (catalogoDTOs.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            catalogoSimpleHolderFL.setCatalogoDTOs(catalogoDTOs);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(catalogoDTOs.size());
        }

    }

    /**
     * Consulta el detalle de los catalogos simples
     */
    public void verDetalle() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::verDetalle()"));

        final CatalogoSimpleHolderFL catalogoSimpleHolderFL = findFlowObject(CatalogoSimpleHolderFL.class,
                CATALOGO_SIMPLE_HOLDER_FL);
        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);

        if (catalogoSimpleHolderFL.getSeleccionCatalogoDTO() != null) {
            CatalogoDTO catalogoDTO = iRCatalogo.consultarCatalogoPorId(catalogoSimpleHolderFL
                    .getSeleccionCatalogoDTO().getId());
            catalogoSimpleFL.setTituloEditar(catalogoDTO.getNombre());
            catalogoSimpleFL.setCatalogoDTO(catalogoDTO);
        }
    }

    /**
     * Metodo encargado de actualizar una categoria simple
     * 
     * @return
     */
    public boolean actualizarCaterogiaSimple() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::actualizarCategoriaSimple()"));

        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);

        CatalogoDTO catalogoDTO = catalogoSimpleFL.getCatalogoDTO();
        if (catalogoDTO != null) {
            iRCatalogo.actualizarCatalogo(catalogoDTO);
            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo encargado de consultar todos los items para un catalogo simple para el detalle
     */
    public void verDetalleItemsCatalogoSimple() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::verDetalleItemsCatalogoSimple()"));

        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);
        List<ItemCatalogoDTO> itemCatalogoDTOs = new ArrayList<ItemCatalogoDTO>();

        /*
         * Manejo del estado
         */
        boolean estado = false;
        if (StringUtils.isNotBlank(catalogoSimpleFL.getTipoEstadoItem())) {
            estado = convertirEstadoBoolean(catalogoSimpleFL.getTipoEstadoItem());
            catalogoSimpleFL.getFiltroItemCatalogoDTO().setActivo(estado);
        } else {
            catalogoSimpleFL.getFiltroItemCatalogoDTO().setActivo(null);
        }

        itemCatalogoDTOs = iRCatalogo.consultarItemsCatalogo(catalogoSimpleFL.getCatalogoDTO().getNombreEntidad(),
                catalogoSimpleFL.getFiltroItemCatalogoDTO());

        catalogoSimpleFL.setItemCatalogoDTOs(new ArrayList<ItemCatalogoDTO>());
        if (!itemCatalogoDTOs.isEmpty()) {
            catalogoSimpleFL.setItemCatalogoDTOs(itemCatalogoDTOs);
        }

    }

    /**
     * Metodo encargado de consultar todos los items para un catalogo simple
     */
    public void consultarItemsCatalogoSimple() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::consultarItemsCatalogoSimple()"));

        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);
        List<ItemCatalogoDTO> itemCatalogoDTOs = new ArrayList<ItemCatalogoDTO>();

        /*
         * Manejo del estado
         */
        boolean estado = false;
        if (StringUtils.isNotBlank(catalogoSimpleFL.getTipoEstadoItem())) {
            estado = convertirEstadoBoolean(catalogoSimpleFL.getTipoEstadoItem());
            catalogoSimpleFL.getFiltroItemCatalogoDTO().setActivo(estado);
        } else {
            catalogoSimpleFL.getFiltroItemCatalogoDTO().setActivo(null);
        }

        itemCatalogoDTOs = iRCatalogo.consultarItemsCatalogo(catalogoSimpleFL.getCatalogoDTO().getNombreEntidad(),
                catalogoSimpleFL.getFiltroItemCatalogoDTO());

        catalogoSimpleFL.setItemCatalogoDTOs(new ArrayList<ItemCatalogoDTO>());
        if (itemCatalogoDTOs.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            catalogoSimpleFL.setItemCatalogoDTOs(itemCatalogoDTOs);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(itemCatalogoDTOs.size());
        }

    }

    /**
     * Convertir la variable string en un boolean
     */
    private boolean convertirEstadoBoolean(String estado) {
        return Boolean.valueOf(estado);
    }

    /**
     * Adiciona un item catalogo simple
     */
    public boolean registrarItemCatalogoSimple() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::registrarItemCatalogoSimple()"));

        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);

        /*
         * Manejo del estado
         */
        boolean estado = false;
        if (StringUtils.isNotBlank(catalogoSimpleFL.getRegistroTipoEstadoItem())) {
            estado = Boolean.valueOf(catalogoSimpleFL.getRegistroTipoEstadoItem());
            catalogoSimpleFL.getItemCatalogoDTO().setActivo(estado);
        } else {
            catalogoSimpleFL.getItemCatalogoDTO().setActivo(null);
            return false;
        }

        try {
            List<ItemCatalogoDTO> itemCatalogoDTOs = new ArrayList<ItemCatalogoDTO>();
            itemCatalogoDTOs.add(catalogoSimpleFL.getItemCatalogoDTO());
            iRCatalogo.registrarItemCatalogo(catalogoSimpleFL.getCatalogoDTO(), itemCatalogoDTOs);
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();

        return true;
    }

    /**
     * Consulta el detalle del item catalogos simples
     */
    public void verDetalleItemCatalogoSimple() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::verDetalleItemCatalogoSimple()"));

        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);

        /*
         * Manejo del estado
         */
        if (catalogoSimpleFL.getSeleccionItemCatalogoDTO().getActivo()) {
            catalogoSimpleFL.setActTipoEstadoItem("true");
        } else {
            catalogoSimpleFL.setActTipoEstadoItem("false");
        }
    }

    /**
     * Actualiza un item catalogo simple
     */
    public boolean actualizarItemCatalogoSimple() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::actualizarItemCatalogoSimple()"));

        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);

        /*
         * Manejo del estado
         */
        boolean estado = false;
        if (StringUtils.isNotBlank(catalogoSimpleFL.getActTipoEstadoItem())) {
            estado = Boolean.valueOf(catalogoSimpleFL.getActTipoEstadoItem());
            catalogoSimpleFL.getSeleccionItemCatalogoDTO().setActivo(estado);
        } else {
            catalogoSimpleFL.getSeleccionItemCatalogoDTO().setActivo(null);
            return false;
        }

        try {
            iRCatalogo.actualizarItemCatalogo(catalogoSimpleFL.getCatalogoDTO().getNombreEntidad(), catalogoSimpleFL
                    .getCatalogoDTO().getPaqueteEntidad(), catalogoSimpleFL.getSeleccionItemCatalogoDTO());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
        return true;
    }

    /**
     * Permite eliminar un item catalogo simple
     */
    public boolean eliminarItemCatalogoSimple() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::eliminarItemCatalogoSimple()"));

        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);

        try {
            iRCatalogo.eliminarItemCatalogo(catalogoSimpleFL.getCatalogoDTO().getNombreEntidad(), catalogoSimpleFL
                    .getCatalogoDTO().getPaqueteEntidad(), catalogoSimpleFL.getSeleccionItemCatalogoDTO().getId());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
        return true;

    }

    /**
     * Limpia campos de la logica de negocio
     */
    public void limpiarCampos() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::limpiarCampos()"));

        final CatalogoSimpleFL catalogoSimpleFL = findFlowObject(CatalogoSimpleFL.class, CATALOGO_SIMPLE_FL);
        final CatalogoSimpleHolderFL catalogoSimpleHolderFL = findFlowObject(CatalogoSimpleHolderFL.class,
                CATALOGO_SIMPLE_HOLDER_FL);

        catalogoSimpleHolderFL.setSeleccionCatalogoDTO(null);
        catalogoSimpleFL.setSeleccionItemCatalogoDTO(null);
        catalogoSimpleFL.setItemCatalogoDTOs(new ArrayList<ItemCatalogoDTO>());
        catalogoSimpleFL.setItemCatalogoDTO(new ItemCatalogoDTO());
        catalogoSimpleFL.setRegistroTipoEstadoItem("");
    }

    /**
     * Limpia campos de la logica de negocio de catalogo
     */
    public void limpiarCamposCatalogo() {
        LOGGER.debug(CatalogoSimpleMB.class.getName().concat("::limpiarCamposCatalogo()"));

        final CatalogoSimpleHolderFL catalogoSimpleHolderFL = findFlowObject(CatalogoSimpleHolderFL.class,
                CATALOGO_SIMPLE_HOLDER_FL);

        catalogoSimpleHolderFL.setSeleccionCatalogoDTO(null);
        catalogoSimpleHolderFL.setCatalogoDTOs(new ArrayList<CatalogoDTO>());
    }
}
