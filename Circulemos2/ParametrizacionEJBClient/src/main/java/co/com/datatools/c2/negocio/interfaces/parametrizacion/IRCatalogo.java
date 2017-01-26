package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.parametrizacion.CatalogoDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRCatalogo {

    /**
     * Registro un ítem de un catálogo específico recibiendo por parámetro el nombre de la entidad del mismo y los datos del ítem.
     * 
     * @param entidadCatalogo
     * @param item
     * @return identificador del ítem insertado
     * @author edier.sua <br>
     *         giovanni.velandia (mod 2015-05-14)
     * @throws CirculemosNegocioException
     *             ADM_063001 - No es posible guardar los datos debido a que el valor en Nombre ítem o Sigla ítem, ya se encuentran registrados en el
     *             sistema.
     */
    public void registrarItemCatalogo(CatalogoDTO catalogoDTO, List<ItemCatalogoDTO> itemCatalogoDTOs)
            throws CirculemosNegocioException;

    /**
     * Se encarga de consultar los catálogos definidos como administrables en el sistema, de acuerdo a los filtros seleccionados. Para consultarlos
     * todos, los filtros se envían con valor nulo.
     * 
     * @param nombreCatalogo
     * @param codigoCatalogo
     * @return Listado de catálogos encontrados
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     */
    public List<CatalogoDTO> consultarCatalogos(CatalogoDTO catalogoDTO);

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

    /**
     * Permite actualizar los valores de administración del catalogo
     * 
     * @param catalogoDTO
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     */
    public void actualizarCatalogo(CatalogoDTO catalogoDTO);

    /**
     * Actualizar un ítem de un catálogo específico registrado en el sistema recibiendo por parámetro el nombre de la entidad del mismo y el objeto
     * con los datos a actualizar
     * 
     * @param entidadCatalogo
     * @param item
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)
     * @exception CirculemosNegocioException
     *                ADM_063001 - No es posible guardar los datos debido a que el valor en Nombre ítem o Sigla ítem, ya se encuentran registrados en
     *                el sistema.
     */
    public void actualizarItemCatalogo(String entidadCatalogo, String paqueteEntidad, ItemCatalogoDTO item)
            throws CirculemosNegocioException;

    /**
     * Elimina un item del catálogo especificado por el parámetro entidadCatalogo.
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
