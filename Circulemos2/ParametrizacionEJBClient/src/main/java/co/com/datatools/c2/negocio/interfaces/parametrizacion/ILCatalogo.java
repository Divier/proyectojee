package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILCatalogo {

    /**
     * @see IRCatalogo#registrarItemCatalogo(String, ItemCatalogoDTO)
     */
    public void registrarItemCatalogo(CatalogoDTO catalogoDTO, List<ItemCatalogoDTO> itemCatalogoDTOs)
            throws CirculemosNegocioException;

    /**
     * @see IRCatalogo#consultarCatalogos(String, String)
     */
    public List<CatalogoDTO> consultarCatalogos(CatalogoDTO catalogoDTO);

    /**
     * @see IRCatalogo#consultarItemsCatalogo(String, ItemCatalogoDTO)
     */
    public List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogo);

    /**
     * @see IRCatalogo#actualizarCatalogo(CatalogoDTO)
     */
    public void actualizarCatalogo(CatalogoDTO catalogoDTO);

    /**
     * @see IRCatalogo#actualizarItemCatalogo(String, ItemCatalogoDTO)
     */
    public void actualizarItemCatalogo(String entidadCatalogo, String paqueteEntidad, ItemCatalogoDTO item)
            throws CirculemosNegocioException;

    /**
     * @see IRCatalogo#eliminarItemCatalogo(String, int)
     */
    public void eliminarItemCatalogo(String entidadCatalogo, String paqueteEntidad, int idItem)
            throws CirculemosNegocioException;

    /**
     * @see IRCatalogo#validarItemsCatalogo(CatalogoDTO, List<ItemCatalogoDTO>)
     */
    public ItemCatalogoDTO validarItemsCatalogo(CatalogoDTO catalogoDTO, List<ItemCatalogoDTO> itemCatalogoDTOs);

}
