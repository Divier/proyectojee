package co.com.datatools.c2.managed_bean.administracion.catalogo_compuesto;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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

/**
 * Se encarga de la administracion de los catalogos compuestos
 * 
 * @author giovanni.velandia
 * 
 */
@ManagedBean
@SessionScoped
public class CatalogoCompuestoMB extends AbstractC2ManagedBean {

    private static final long serialVersionUID = 1L;
    // Logger
    private static final Logger LOGGER = Logger.getLogger(CatalogoCompuestoMB.class.getName());
    // Variables de objetos spring web flow
    private static final String CATALOGO_COMPUESTO_HOLDER_FL = "catalogoCompuestoHolderFL";
    private static final String CATALOGO_COMPUESTO_FL = "catalogoCompuestoFL";

    private static final String BUNDLE_CATALOGO_COMPUESTO = "labelCatalogoCompuesto";

    // Inyeccion EJB
    @EJB
    private IRCatalogo iRCatalogo;

    /**
     * Metodo encargado de consultar todos los catalogos compuestos del sistema
     */
    public void consultarCatalogosCompuestos() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::consultarCatalogosCompuestos()"));

        final CatalogoCompuestoHolderFL catalogoCompuestoHolderFL = findFlowObject(CatalogoCompuestoHolderFL.class,
                CATALOGO_COMPUESTO_HOLDER_FL);

        List<CatalogoDTO> catalogoDTOs = new ArrayList<CatalogoDTO>();

        catalogoCompuestoHolderFL.getFiltroCatalogoDTO().setIdCatalogoDependencia(true);

        catalogoDTOs = iRCatalogo.consultarCatalogos(catalogoCompuestoHolderFL.getFiltroCatalogoDTO());

        catalogoCompuestoHolderFL.setCatalogoDTOs(new ArrayList<CatalogoDTO>());
        if (catalogoDTOs.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            catalogoCompuestoHolderFL.setCatalogoDTOs(catalogoDTOs);
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsulta(catalogoDTOs.size());
        }
    }

    /**
     * Consulta el detalle de los catalogos compuestos
     */
    public void verDetalle() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::verDetalle()"));

        final CatalogoCompuestoHolderFL catalogoCompuestoHolderFL = findFlowObject(CatalogoCompuestoHolderFL.class,
                CATALOGO_COMPUESTO_HOLDER_FL);
        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);

        if (catalogoCompuestoHolderFL.getSeleccionCatalogoDTO() != null) {
            CatalogoDTO catalogoDTO = iRCatalogo.consultarCatalogoPorId(catalogoCompuestoHolderFL
                    .getSeleccionCatalogoDTO().getId());
            catalogoCompuestoFL.setTituloEditar(catalogoDTO.getNombre());
            catalogoCompuestoFL.setCatalogoDTO(catalogoDTO);
        }

    }

    /**
     * Consulta el detalle para los itmes
     */
    public void verDetalleItem() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::verDetalleItem()"));

        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);

        if (catalogoCompuestoFL.getSeleccionItemCatalogoDTO() != null) {
            List<ItemCatalogoDTO> itemCatalogoDTOs = iRCatalogo.consultarItemsCatalogo(catalogoCompuestoFL
                    .getCatalogoDTO().getNombreEntidad(), catalogoCompuestoFL.getSeleccionItemCatalogoDTO());
            if (itemCatalogoDTOs != null && !itemCatalogoDTOs.isEmpty()) {
                catalogoCompuestoFL.setSeleccionItemCatalogoBDDTO(itemCatalogoDTOs.get(0));
            }

            /*
             * Cargamos las dependencias
             */
            catalogoCompuestoFL.setItemCatalogoDepenDTOs(new ArrayList<ItemCatalogoDTO>());
            cargarDependencias();
            int temp = 0;
            for (CatalogoDTO catalogoDTO : catalogoCompuestoFL.getCatalogoDepenDTOs()) {
                ItemCatalogoDTO itemCatalogoDTO = null;

                if (temp == 0) {
                    itemCatalogoDTO = catalogoCompuestoFL.getSeleccionItemCatalogoBDDTO()
                            .getItemCatalogoDependenciaDTO();
                } else {
                    List<ItemCatalogoDTO> itemCatalogoDBDDTOs = iRCatalogo.consultarItemsCatalogo(
                            catalogoDTO.getNombreEntidad(), itemCatalogoDTO);
                    for (ItemCatalogoDTO itemCatalogoBDDTO2 : itemCatalogoDBDDTOs) {
                        itemCatalogoDTO = itemCatalogoBDDTO2;
                    }
                }
                itemCatalogoDTO.setCatalogoDTO(catalogoDTO);
                catalogoCompuestoFL.getItemCatalogoDepenDTOs().add(itemCatalogoDTO);
            }
        }
    }

    /**
     * Metodo encargado de actualizar una categoria compuestos
     * 
     * @return
     */
    public boolean actualizarCatalogoCompuesto() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::actualizarCatalogoCompuesto()"));

        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);

        CatalogoDTO catalogoDTO = catalogoCompuestoFL.getCatalogoDTO();
        if (catalogoDTO != null) {
            iRCatalogo.actualizarCatalogo(catalogoDTO);
            CirculemosAccesoBundleGeneral.addMensajeRegistroActualizado();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo encargado de consultar todos los items para un catalogo compuesto para el detalle
     */
    public void verDetalleItemsCatalogoCompuesto() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::verDetalleItemsCatalogoCompuesto()"));

        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);
        List<ItemCatalogoDTO> itemCatalogoDTOs = new ArrayList<ItemCatalogoDTO>();

        /*
         * Manejo del estado
         */
        boolean estado = false;
        if (StringUtils.isNotBlank(catalogoCompuestoFL.getTipoEstadoItem())) {
            estado = convertirEstadoBoolean(catalogoCompuestoFL.getTipoEstadoItem());
            catalogoCompuestoFL.getFiltroItemCatalogoDTO().setActivo(estado);
        } else {
            catalogoCompuestoFL.getFiltroItemCatalogoDTO().setActivo(null);
        }

        itemCatalogoDTOs = iRCatalogo.consultarItemsCatalogo(catalogoCompuestoFL.getCatalogoDTO().getNombreEntidad(),
                catalogoCompuestoFL.getFiltroItemCatalogoDTO());

        catalogoCompuestoFL.setItemCatalogoDTOs(new ArrayList<ItemCatalogoDTO>());
        if (!itemCatalogoDTOs.isEmpty()) {
            catalogoCompuestoFL.setItemCatalogoDTOs(itemCatalogoDTOs);
        }
    }

    /**
     * Metodo encargado de consultar todos los items para un catalogo compuesto
     */
    public void consultarItemsCatalogoCompuesto() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::consultarItemsCatalogoSimple()"));

        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);
        List<ItemCatalogoDTO> itemCatalogoDTOs = new ArrayList<ItemCatalogoDTO>();

        /*
         * Manejo del estado
         */
        boolean estado = false;
        if (StringUtils.isNotBlank(catalogoCompuestoFL.getTipoEstadoItem())) {
            estado = convertirEstadoBoolean(catalogoCompuestoFL.getTipoEstadoItem());
            catalogoCompuestoFL.getFiltroItemCatalogoDTO().setActivo(estado);
        } else {
            catalogoCompuestoFL.getFiltroItemCatalogoDTO().setActivo(null);
        }

        itemCatalogoDTOs = iRCatalogo.consultarItemsCatalogo(catalogoCompuestoFL.getCatalogoDTO().getNombreEntidad(),
                catalogoCompuestoFL.getFiltroItemCatalogoDTO());

        catalogoCompuestoFL.setItemCatalogoDTOs(new ArrayList<ItemCatalogoDTO>());
        if (itemCatalogoDTOs.isEmpty()) {
            CirculemosAccesoBundleGeneral.addMensajeResultadoConsultaVacio();
        } else {
            catalogoCompuestoFL.setItemCatalogoDTOs(itemCatalogoDTOs);
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
     * Limpia campos de la logica de negocio
     */
    public void limpiarCampos() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::limpiarCampos()"));
        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);

        catalogoCompuestoFL.setSeleccionItemCatalogoDTO(null);
        catalogoCompuestoFL.setItemCatalogoDTOs(new ArrayList<ItemCatalogoDTO>());
        catalogoCompuestoFL.setItemCatalogoDTO(new ItemCatalogoDTO());
        catalogoCompuestoFL.setRegistroTipoEstadoItem("");
    }

    /**
     * Limpia campos de la logica de negocio de catalogo
     */
    public void limpiarCamposCatalogo() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::limpiarCamposCatalogo()"));

        final CatalogoCompuestoHolderFL catalogoCompuestoHolderFL = findFlowObject(CatalogoCompuestoHolderFL.class,
                CATALOGO_COMPUESTO_HOLDER_FL);

        catalogoCompuestoHolderFL.setSeleccionCatalogoDTO(null);
        catalogoCompuestoHolderFL.setCatalogoDTOs(new ArrayList<CatalogoDTO>());
    }

    /**
     * Metodo que se encarga de cargar las dependecias de un catalogo en forma ciclica hasta llegar al ultimo nivel y encontrar un catalogo simple
     * este metodo resuelve el registro y el detalle de los items
     */
    public void cargarDependencias() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::cargarDependencias() "));

        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);
        final CatalogoCompuestoHolderFL catalogoCompuestoHolderFL = findFlowObject(CatalogoCompuestoHolderFL.class,
                CATALOGO_COMPUESTO_HOLDER_FL);
        catalogoCompuestoFL.setRegistroTipoEstadoItem("true");

        catalogoCompuestoFL.setCatalogoDepenDTOs(new ArrayList<CatalogoDTO>());

        /*
         * Manejo de dependencias dinamicas
         */
        List<CatalogoDTO> catalogoDTOs = new ArrayList<CatalogoDTO>();
        boolean esCompuesto = catalogoCompuestoHolderFL.getSeleccionCatalogoDTO().getIdCatalogoDependencia();
        int codigoCatalogo = catalogoCompuestoHolderFL.getSeleccionCatalogoDTO().getCatalogoDependenciaDTO().getId();
        int contador = 0;
        while (esCompuesto) {
            CatalogoDTO catalogoDTO = iRCatalogo.consultarCatalogoPorId(codigoCatalogo);
            if (contador == 0) {
                catalogoDTO.setEsDependeciaFinal(true);
                consultarCatalogosGeneral(catalogoDTO.getNombreEntidad());
                catalogoCompuestoFL.setNombreDependencia(catalogoDTO.getNombre());
            } else {
                catalogoDTO.setEsDependenciaNormal(true);
            }

            /*
             * Valores parametros
             */
            if (catalogoDTO.getIdCatalogoDependencia() != null) {
                esCompuesto = catalogoDTO.getIdCatalogoDependencia();
                codigoCatalogo = catalogoDTO.getCatalogoDependenciaDTO().getId();
            } else {
                esCompuesto = false;
            }

            /*
             * Agregamos a la lista de catalogo las dependencias
             */
            catalogoDTOs.add(catalogoDTO);
            contador++;
        }

        /*
         * Maneja las dependencias de sus padres
         */
        if (contador > 1) {
            catalogoDTOs.get(contador - 1).setEsDependenciaNormal(false);
            catalogoDTOs.get(contador - 1).setEsDependeciaInicial(true);
        }

        /*
         * Se encarga de ordenar la lista en forma acendente
         */
        for (int x = catalogoDTOs.size() - 1; x >= 0; x--) {
            catalogoCompuestoFL.getCatalogoDepenDTOs().add(catalogoDTOs.get(x));
        }
    }

    /**
     * Metodo para el manejo de las dependencias
     * 
     * @param nombreEntidad
     * @return
     */
    public void consultarCatalogosGeneral(String nombreEntidad) {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::consultarCatalogosGeneral()"));
        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);
        catalogoCompuestoFL.setItemCatalogoPickLisBDDTOs(iRCatalogo.consultarItemsCatalogo(nombreEntidad, null));
    }

    /**
     * Se encarga de cargar lista de los items configurados con su catalogo y item para el manejo de dependencias
     */
    public String cargarCatalogosConfiguracion() {
        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);
        for (ItemCatalogoDTO itemCatalogoDTO : catalogoCompuestoFL.getItemCatalogoPickLisDTOs()) {
            ItemCatalogoDTO itemCatalogoGrupoDTO = new ItemCatalogoDTO();
            // Datos generales del catalogo
            itemCatalogoGrupoDTO.setActivo(catalogoCompuestoFL.getItemCatalogoDTO().getActivo());
            itemCatalogoGrupoDTO.setCodigo(catalogoCompuestoFL.getItemCatalogoDTO().getCodigo());
            itemCatalogoGrupoDTO.setDescripcion(catalogoCompuestoFL.getItemCatalogoDTO().getDescripcion());
            itemCatalogoGrupoDTO.setId(catalogoCompuestoFL.getItemCatalogoDTO().getId());
            itemCatalogoGrupoDTO.setNombre(catalogoCompuestoFL.getItemCatalogoDTO().getNombre());
            itemCatalogoGrupoDTO.setSigla(catalogoCompuestoFL.getItemCatalogoDTO().getSigla());

            /*
             * Manejo del estado
             */
            boolean estado = false;
            if (StringUtils.isNotBlank(catalogoCompuestoFL.getRegistroTipoEstadoItem())) {
                estado = Boolean.valueOf(catalogoCompuestoFL.getRegistroTipoEstadoItem());
                itemCatalogoGrupoDTO.setActivo(estado);
            } else {
                itemCatalogoGrupoDTO.setActivo(null);
                return null;
            }

            // Datos de las dependencias
            itemCatalogoGrupoDTO.setItemCatalogoDependenciaDTO(itemCatalogoDTO);
            catalogoCompuestoFL.getItemCatalogoGrupoDTOs().add(itemCatalogoGrupoDTO);
        }
        ItemCatalogoDTO itemCatalogoDTO = iRCatalogo.validarItemsCatalogo(catalogoCompuestoFL.getCatalogoDTO(),
                catalogoCompuestoFL.getItemCatalogoGrupoDTOs());
        if (itemCatalogoDTO != null) {
            String mensajeError = MessageFormat.format(
                    getBundle(BUNDLE_CATALOGO_COMPUESTO).getString("error_items_encontrado"), itemCatalogoDTO
                            .getItemCatalogoDependenciaDTO().getNombre());
            getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensajeError));
            catalogoCompuestoFL.setItemCatalogoGrupoDTOs(new ArrayList<ItemCatalogoDTO>());
            return null;
        }
        return null;
    }

    /**
     * Adiciona un item catalogo compuesto
     */
    public boolean registrarItemCatalogoCompuesto() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::registrarItemCatalogoCompuesto()"));

        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);

        try {
            iRCatalogo.registrarItemCatalogo(catalogoCompuestoFL.getCatalogoDTO(),
                    catalogoCompuestoFL.getItemCatalogoGrupoDTOs());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        CirculemosAccesoBundleGeneral.addMensajeRegistroCreado();

        return true;
    }

    /**
     * Consulta el detalle del item catalogos compuestos
     */
    public void verDetalleItemCatalogoCompuesto() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::verDetalleItemCatalogoCompuesto()"));

        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);

        /*
         * Manejo del estado
         */
        if (catalogoCompuestoFL.getSeleccionItemCatalogoDTO().getActivo()) {
            catalogoCompuestoFL.setActTipoEstadoItem("true");
        } else {
            catalogoCompuestoFL.setActTipoEstadoItem("false");
        }
    }

    /**
     * Permite eliminar un item catalogo compuesto
     */
    public boolean eliminarItemCatalogoCompuesto() {
        LOGGER.debug(CatalogoCompuestoMB.class.getName().concat("::eliminarItemCatalogoCompuesto()"));

        final CatalogoCompuestoFL catalogoCompuestoFL = findFlowObject(CatalogoCompuestoFL.class, CATALOGO_COMPUESTO_FL);

        try {
            iRCatalogo.eliminarItemCatalogo(catalogoCompuestoFL.getCatalogoDTO().getNombreEntidad(),
                    catalogoCompuestoFL.getCatalogoDTO().getPaqueteEntidad(), catalogoCompuestoFL
                            .getSeleccionItemCatalogoDTO().getId());
        } catch (CirculemosNegocioException e) {
            CirculemosErrorHandler.handleException(e);
            return false;
        }

        CirculemosAccesoBundleGeneral.addMensajeRegistroEliminado();
        return true;

    }

}
