package co.com.datatools.fachadainetegracionweb.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoDTO;

@Local
public interface ILCatalogo {

    /**
     * @see IRCatalogo#consultarItemsCatalogo(String, ItemCatalogoDTO)
     */
    public List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogo);

}
