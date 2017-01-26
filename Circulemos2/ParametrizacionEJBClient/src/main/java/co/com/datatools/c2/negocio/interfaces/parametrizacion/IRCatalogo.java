package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRCatalogo {

    /**
     * Registro un �tem de un cat�logo espec�fico recibiendo por par�metro el nombre de la entidad del mismo y los datos del �tem.
     * 
     * @param entidadCatalogo
     * @param item
     * @return identificador del �tem insertado
     * @author edier.sua <br>
     *         giovanni.velandia (mod 2015-05-14)
     * @throws CirculemosNegocioException
     *             ADM_063001 - No es posible guardar los datos debido a que el valor en Nombre �tem o Sigla �tem, ya se encuentran registrados en el
     *             sistema.
     */
    public void registrarItemCatalogo(CatalogoDTO catalogoDTO, List<ItemCatalogoDTO> itemCatalogoDTOs)
            throws CirculemosNegocioException;

    /**
     * Se encarga de consultar los cat�logos definidos como administrables en el sistema, de acuerdo a los filtros seleccionados. Para consultarlos
     * todos, los filtros se env�an con valor nulo.
     * 
     * @param nombreCatalogo
     * @param codigoCatalogo
     * @return Listado de cat�logos encontrados
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     */
    public List<CatalogoDTO> consultarCatalogos(CatalogoDTO catalogoDTO);

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

    /**
     * Permite actualizar los valores de administraci�n del catalogo
     * 
     * @param catalogoDTO
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     */
    public void actualizarCatalogo(CatalogoDTO catalogoDTO);

    /**
     * Actualizar un �tem de un cat�logo espec�fico registrado en el sistema recibiendo por par�metro el nombre de la entidad del mismo y el objeto
     * con los datos a actualizar
     * 
     * @param entidadCatalogo
     * @param item
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     * @exception CirculemosNegocioException
     *                ADM_063001 - No es posible guardar los datos debido a que el valor en Nombre �tem o Sigla �tem, ya se encuentran registrados en
     *                el sistema.
     */
    public void actualizarItemCatalogo(String entidadCatalogo, String paqueteEntidad, ItemCatalogoDTO item)
            throws CirculemosNegocioException;

    /**
     * Elimina un item del cat�logo especificado por el par�metro entidadCatalogo.
     * 
     * @param entidadCatalogo
     * @param idItem
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     * @exception CirculemosNegocioException
     *                ADM_063002=El item a eliminar se encuentra en uso.
     */
    public void eliminarItemCatalogo(String entidadCatalogo, String paqueteEntidad, int idItem)
            throws CirculemosNegocioException;

    /**
     * Metodo encargado de consultar un catalogo por id
     * 
     * @param idCatalogo
     * @return
     * @author giovanni.velandia
     */
    public CatalogoDTO consultarCatalogoPorId(int idCatalogo);

    /**
     * Se encarga de realizar las validaciones para los catalogos compuestos
     * 
     * @author giovanni.velandia
     * @param catalogoDTO
     * @param itemCatalogoDTOs
     * @return
     */
    public ItemCatalogoDTO validarItemsCatalogo(CatalogoDTO catalogoDTO, List<ItemCatalogoDTO> itemCatalogoDTOs);

}
