package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRFachadaConfiguracion {

    /**
     * se encarga de consultar los datos de la configuracion
     * 
     * @param codigoTipoConfiguracion
     * @param dto
     * @return
     * @throws CirculemosNegocioException
     * @author giovanni.velandia
     * @see IRConfiguracion#consultarDatoConfiguracion(String, Object)
     */
    public <T> T consultarValorConfiguracion(String codigoTipoConfiguracion, T dto) throws CirculemosNegocioException;

    /**
     * Consulta el valor asociado al valor homologable recibido, de acuerdo al origen de homologación.
     * 
     * @param origenHomologacion
     * @param valorHomologable
     * @return Retorna el valor asociado al valor homologable recibido
     * @author julio.pinzon
     * @see IRHomologacion#obtenerValor(Mapeable, String)
     */
    public String obtenerValor(Mapeable origenHomologacion, String valorHomologable);

    /**
     * Consulta los ítems de un catálogo específico recibiendo por parámetro el nombre de la entidad del mismo y los filtros deseado en el objeto
     * ItemCatalogoDTO, sino se requieren filtros el objeto debe ser nulo. Se debe tener en cuenta el estado de los ítems cuando la consulta no es
     * para administrarlos, sino para usar el catálogo en alguna otra funcionalidad.
     * 
     * @param entidadCatalogo
     * @param itemCatalogo
     * @return Listado de ítems del catálogo encontrados
     * @author edier.sua<br>
     *         giovanni.velandia (mod 2015-05-14)<br>
     *         luis.forero (mod 2015-12-04)
     */
    List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogo);
}
