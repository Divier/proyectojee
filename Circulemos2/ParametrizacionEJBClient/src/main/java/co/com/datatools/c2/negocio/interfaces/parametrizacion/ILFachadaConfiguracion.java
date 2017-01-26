package co.com.datatools.c2.negocio.interfaces.parametrizacion;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILFachadaConfiguracion {

    /**
     * @see IRConfiguracion#consultarDatoConfiguracion(String, Object)
     */
    public <T> T consultarValorConfiguracion(String codigoTipoConfiguracion, T dto) throws CirculemosNegocioException;

    /**
     * @see IRConfiguracion#obtenerValor(Mapeable, String)
     */
    public String obtenerValor(Mapeable origenHomologacion, String valorHomologable);

    /**
     * @see IRFachadaConfiguracion#consultarItemsCatalogo(String, ItemCatalogoDTO)
     */
    List<ItemCatalogoDTO> consultarItemsCatalogo(String entidadCatalogo, ItemCatalogoDTO itemCatalogo);
}
