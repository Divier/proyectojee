package co.com.datatools.fachadainetegracionweb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;

@Remote
public interface IRCatalogo {

    /**
     * Consulta los ítems de un catálogo específico recibiendo por parámetro el nombre de la entidad del mismo y los filtros deseado en el objeto
     * ItemCatalogoDTO, sino se requieren filtros el objeto debe ser nulo. Se debe tener en cuenta el estado de los ítems cuando la consulta no es
     * para administrarlos, sino para usar el catálogo en alguna otra funcionalidad.
     * 
     * @param entidadCatalogo
     * @param itemCatalogo
     * @return Listado de ítems del catálogo encontrados
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     */
    public List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogo);

}
