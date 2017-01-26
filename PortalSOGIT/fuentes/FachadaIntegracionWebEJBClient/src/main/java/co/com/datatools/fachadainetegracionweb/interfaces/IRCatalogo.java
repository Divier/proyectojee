package co.com.datatools.fachadainetegracionweb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;

@Remote
public interface IRCatalogo {

    /**
     * Consulta los �tems de un cat�logo espec�fico recibiendo por par�metro el nombre de la entidad del mismo y los filtros deseado en el objeto
     * ItemCatalogoDTO, sino se requieren filtros el objeto debe ser nulo. Se debe tener en cuenta el estado de los �tems cuando la consulta no es
     * para administrarlos, sino para usar el cat�logo en alguna otra funcionalidad.
     * 
     * @param entidadCatalogo
     * @param itemCatalogo
     * @return Listado de �tems del cat�logo encontrados
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     */
    public List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogo);

}
