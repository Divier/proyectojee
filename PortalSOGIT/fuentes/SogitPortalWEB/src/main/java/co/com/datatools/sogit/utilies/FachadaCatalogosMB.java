package co.com.datatools.sogit.utilies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoReplicaDTO;

/**
 * Clase de fachada para acceder a los catalogos alojados en el contexto aplicacion
 * 
 * @author felipe.martinez
 */
@ManagedBean
@SessionScoped
public class FachadaCatalogosMB extends AbstractPortalManagedBean {

    private final static Logger LOGGER = Logger.getLogger(FachadaCatalogosMB.class.getName());

    private static final long serialVersionUID = 5238142975714924657L;

    @ManagedProperty(value = "#{inicioAppPortalSogit}")
    private InicioAppPortalSogit inicioAppPortalSogit;

    public static final SelectItemComparator SELECT_ITEM_COMPARATOR = new SelectItemComparator();

    private static String SERVICIO_REST_CATALOGOS = "/FachadaIntegracionWeb/rest/consultarItemsCatalogo/";

    public static class SelectItemComparator implements Comparator<SelectItem>, Serializable {

        private static final long serialVersionUID = 1740776134445747568L;

        @Override
        public int compare(SelectItem selectItem1, SelectItem selectItem2) {
            return selectItem1.getLabel().compareTo(selectItem2.getLabel());
        }
    }

    /**
     * Consulta los itemes de un catalogo
     * 
     * @param EnumCatalogo
     * @author giovanni.velandia
     * @return catalogo listado de selectItems dependiendo el tipo de catalogo
     */
    public List<SelectItem> consultarCatalogos(EnumCatalogo enumCatalogo, Integer idDependencia) {
        LOGGER.debug("consultarCatalogos(EnumCatalogo,Integer)");
        List<SelectItem> itemsCatalogos = inicioAppPortalSogit.getCatalogo(enumCatalogo);

        if (itemsCatalogos == null || itemsCatalogos.isEmpty()) {
            itemsCatalogos = new ArrayList<SelectItem>();
            List<ItemCatalogoReplicaDTO> itemCatalogoDTOs = null;

            /*
             * Validamos si es un catalogo simple o compuesto
             */
            if (idDependencia != null) {
                ItemCatalogoReplicaDTO itemCatalogoReplicaDTO = new ItemCatalogoReplicaDTO();
                itemCatalogoReplicaDTO.setItemCatalogoDependenciaDTO(new ItemCatalogoReplicaDTO());
                itemCatalogoReplicaDTO.getItemCatalogoDependenciaDTO().setId(idDependencia);
                itemCatalogoDTOs = consultarItemsCatalogo(
                        getServer() + SERVICIO_REST_CATALOGOS + enumCatalogo.toString(), itemCatalogoReplicaDTO);
            } else {
                itemCatalogoDTOs = consultarItemsCatalogo(
                        getServer() + SERVICIO_REST_CATALOGOS + enumCatalogo.toString(), null);
            }

            for (ItemCatalogoReplicaDTO itemCatalogoDTO : itemCatalogoDTOs) {
                itemsCatalogos.add(new SelectItem(itemCatalogoDTO.getId(), itemCatalogoDTO.getNombre(),
                        itemCatalogoDTO.getDescripcion()));
            }
            Collections.sort(itemsCatalogos, SELECT_ITEM_COMPARATOR);
            inicioAppPortalSogit.addCatalogo(enumCatalogo, itemsCatalogos);

        }
        return itemsCatalogos;
    }

    /**
     * Metodo que se encarga de consultar los items catalogos
     * 
     * @author giovanni.velandia
     */
    private List<ItemCatalogoReplicaDTO> consultarItemsCatalogo(String url,
            ItemCatalogoReplicaDTO itemCatalogoReplicaDTO) {
        LOGGER.debug("consultarItemsCatalogo(String)");
        List<ItemCatalogoReplicaDTO> itemCatalogoReplicaDTOs = null;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        if (itemCatalogoReplicaDTO == null) {
            itemCatalogoReplicaDTO = new ItemCatalogoReplicaDTO();
        }

        itemCatalogoReplicaDTOs = target.request(MediaType.APPLICATION_XML).post(
                Entity.entity(itemCatalogoReplicaDTO, MediaType.APPLICATION_XML),
                new GenericType<List<ItemCatalogoReplicaDTO>>() {
                });
        return itemCatalogoReplicaDTOs;
    }

    /**
     * Retorna un item catalogo de un <strong>id</strong> existente en el sistema
     * 
     * @param catalogo
     *            - correspondiente al catalogo del cual se va a consultar el item
     * @param id
     *            - Id asignado en el sistema
     * @return <strong>ItemCatalogoDTO</strong> con el objeto correspondiente al <strong>id</strong>
     * @throws <strong>IndexOutOfBounsException</strong>
     *             cuando el <strong>id</strong> del catalogo enviado no existe en el sistema
     */
    public ItemCatalogoReplicaDTO getItemCatalogoId(EnumCatalogo catalogo, Integer id) {
        LOGGER.debug("getItemCatalogoId(EnumCatalogo,Integer)");
        if (id != null) {
            List<ItemCatalogoReplicaDTO> items = consultarItemsCatalogo(
                    getServer() + SERVICIO_REST_CATALOGOS + catalogo.toString(), new ItemCatalogoReplicaDTO(id));
            return items.get(0);
        } else {
            return null;
        }
    }

    /**
     * Permite obtener y/o cargar el catalogo de los posibles tipos de identificacion persona registrados en el sistema.
     * 
     * @author giovanni.velandia
     * @return SelectItems con los posibles Tipo de documento persona
     */
    public List<SelectItem> catTipoIdentificacionPersona() {
        List<SelectItem> itemsCatalogos = consultarCatalogos(EnumCatalogo.TipoIdentificacionPersona, null);
        return itemsCatalogos;
    }

    public InicioAppPortalSogit getInicioAppPortalSogit() {
        return inicioAppPortalSogit;
    }

    public void setInicioAppPortalSogit(InicioAppPortalSogit inicioAppPortalSogit) {
        this.inicioAppPortalSogit = inicioAppPortalSogit;
    }

}
