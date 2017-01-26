package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.TipoSancionDTO;
import co.com.datatools.c2.dto.comparendo.CausalInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.TipoInfraccionDTO;

@Local
public interface ILAdministracionInfraccion {

    /**
     * @see IRAdministracionInfraccion#consultarCausalesInfraccion(int)
     */
    public List<CausalInfraccionDTO> consultarCausalesInfraccion(int codigoOrganismo);

    /**
     * @see IRAdministracionInfraccion#consultarTiposSancion(int)
     */
    public List<TipoSancionDTO> consultarTiposSancion(int idPais);

    /**
     * @see IRAdministracionInfraccion#consultarTiposInfraccion()
     */
    public List<TipoInfraccionDTO> consultarTiposInfraccion();
}
