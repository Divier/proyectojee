package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.comparendo.CausalInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfraccionDTO;

@Remote
public interface IRAdministracionInfraccion {

    /**
     * Permite consultar las causales de infraccion para el organismo.
     * 
     * @param codigoOrganismo
     *            : filtro de codigo del organismo de transito
     * @return listado de causales de infracciones para el organismo ingresado
     * @author luis.forero
     */
    public List<CausalInfraccionDTO> consultarCausalesInfraccion(int codigoOrganismo);

    /**
     * Consulta los tipos de sanciones establecidos en un pais
     * 
     * @param idPais
     *            : id del pais a filtrar
     * @return listado de tipos de sancion del id del pais ingresado
     * @author luis.forero
     */
    public List<TipoSancionDTO> consultarTiposSancion(int idPais);

    /**
     * Consulta los tipos de infracción para un pais
     * 
     * @return listado de tipos de infraccion
     * @author luis.forero
     */
    public List<TipoInfraccionDTO> consultarTiposInfraccion();
}
