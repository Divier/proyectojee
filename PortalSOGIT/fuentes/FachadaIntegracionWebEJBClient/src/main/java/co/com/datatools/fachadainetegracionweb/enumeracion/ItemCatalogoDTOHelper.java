package co.com.datatools.fachadainetegracionweb.enumeracion;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;
import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoReplicaDTO;

/**
 * 
 * @author giovanni.velandia
 *
 */
public class ItemCatalogoDTOHelper {

    /**
     * @author giovanni.velandia
     * @param itemCatalogoReplicaDTO
     * @return
     */
    public static ItemCatalogoDTO toItemCatalogoDTO(ItemCatalogoReplicaDTO itemCatalogoReplicaDTO) {
        ItemCatalogoDTO itemCatalogoDTO = new ItemCatalogoDTO();
        itemCatalogoDTO.setActivo(itemCatalogoReplicaDTO.isActivo());
        itemCatalogoDTO.setCodigo(itemCatalogoReplicaDTO.getCodigo());
        itemCatalogoDTO.setDescripcion(itemCatalogoReplicaDTO.getDescripcion());
        itemCatalogoDTO.setId(itemCatalogoReplicaDTO.getId());
        itemCatalogoDTO.setNombre(itemCatalogoReplicaDTO.getNombre());
        itemCatalogoDTO.setSigla(itemCatalogoReplicaDTO.getSigla());

        // Item de dependecia
        if (itemCatalogoReplicaDTO.getItemCatalogoDependenciaDTO() != null) {
            itemCatalogoDTO.setItemCatalogoDependenciaDTO(
                    toItemCatalogoDTO(itemCatalogoReplicaDTO.getItemCatalogoDependenciaDTO()));
        }

        return itemCatalogoDTO;
    }

    /**
     * @author giovanni.velandia
     * @param itemCatalogoDTO
     * @return
     */
    public static ItemCatalogoReplicaDTO toItemCatalogoReplicaDTO(ItemCatalogoDTO itemCatalogoDTO) {
        ItemCatalogoReplicaDTO itemCatalogoReplicaDTO = new ItemCatalogoReplicaDTO();
        itemCatalogoReplicaDTO.setActivo(itemCatalogoDTO.getActivo());
        itemCatalogoReplicaDTO.setCodigo(itemCatalogoDTO.getCodigo());
        itemCatalogoReplicaDTO.setDescripcion(itemCatalogoDTO.getDescripcion());
        itemCatalogoReplicaDTO.setId(itemCatalogoDTO.getId());
        itemCatalogoReplicaDTO.setNombre(itemCatalogoDTO.getNombre());
        itemCatalogoReplicaDTO.setSigla(itemCatalogoDTO.getSigla());

        // Item de dependecia
        if (itemCatalogoDTO.getItemCatalogoDependenciaDTO() != null) {
            itemCatalogoReplicaDTO.setItemCatalogoDependenciaDTO(
                    toItemCatalogoReplicaDTO(itemCatalogoDTO.getItemCatalogoDependenciaDTO()));
        }

        return itemCatalogoReplicaDTO;
    }
}
