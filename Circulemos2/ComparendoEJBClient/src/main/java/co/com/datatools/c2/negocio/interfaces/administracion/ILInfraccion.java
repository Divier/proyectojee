package co.com.datatools.c2.negocio.interfaces.administracion;

import java.util.Date;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.ConfiguracionInfraccionDTO;
import co.com.datatools.c2.dto.comparendo.InfraccionDTO;

@Local
public interface ILInfraccion {

    /**
     * @see IRInfraccion#consultarInfraccion(String, Date)
     */
    ConfiguracionInfraccionDTO consultarInfraccion(String codigoAlfanumerico, Date fechaVigencia);

    /**
     * @see IRInfraccion#consultarInfraccion(String)
     */
    InfraccionDTO consultarInfraccion(String codigoAlfanumerico);
}
