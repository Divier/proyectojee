package co.com.datatools.fachadainetegracionweb.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.fachadainetegracionweb.dto.ItemCatalogoReplicaDTO;

@Remote
public interface IRFachadaAdministracion {

    /**
     * Metodo que se encarga de consultar los items catalogos de circulemos
     * 
     * @author giovanni.velandia
     * @return
     */
    public List<ItemCatalogoReplicaDTO> consultarItemsCatalogo(String entidadCatalogo,
            ItemCatalogoReplicaDTO itemCatalogoReplicaDTO);

    /**
     * Metodo encargado de consultar el valor del parametro de la tabla parametro de la base de datos del aplicativo C2 este valor se usa para
     * parametrizacion del sistema
     * 
     * @param enumParametro
     * @return valor del campo valor_parametro_defecto de la tabla parametro
     */
    public String consultarValorParametro(Integer parametro);

    /**
     * Metodo encargado de consultar el valor del parametro de la tabla parametro de la base de datos del aplicativo C2 este valor se usa para
     * parametrizacion del sistema para cada organismo de transito
     * 
     * @param enumParametro
     * @return valor del campo valor_parametro_defecto de la tabla parametro
     * @author giovanni.velandia
     */
    public String consultarValorParametro(Integer parametro, Integer codigoOrgansimo);
}
