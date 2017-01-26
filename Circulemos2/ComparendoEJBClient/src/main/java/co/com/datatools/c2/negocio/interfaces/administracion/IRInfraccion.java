package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.InfraccionDTO;
import co.com.datatools.c2.dto.parametrizacion.ItemCatalogoDTO;

/**
 * Permite exponer los servicios necesarios para administrar las infracciones en el sistema de informacion.
 * 
 * @author luis.forero
 * 
 */
@Remote
public interface IRInfraccion {

    /**
     * Consulta una infraccion de un organismo de transito apartir del codigoAlfanumerico en una fecha determinada
     * 
     * @param codigoAlfanumerico
     *            codigo alfanumerico de la infraccion a buscar
     * @param fechaVigencia
     *            fecha de vigencia de la infraccion
     * @return infraccion encontrada, null si no encuentra
     */
    ConfiguracionInfraccionDTO consultarInfraccion(String codigoAlfanumerico, Date fechaVigencia);

    /**
     * Consulta una infraccion apartir del codigoAlfanumerico
     * 
     * @param codigoAlfanumerico
     *            codigo alfanumerico de la infraccion a buscar
     * @return infraccion encontrada, null si no encuentra
     * @author luis.forero(2015-11-19)
     */
    InfraccionDTO consultarInfraccion(String codigoAlfanumerico);

    /**
     * Servicio expuesto para acceder a los codigos de infracciones almacenados en el sistem
     * 
     * @return Listado de infracciones por codigo, null safe
     */
    List<ItemCatalogoDTO> consultarCodigosInfraccion();
}
