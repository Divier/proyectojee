package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.ConfiguracionSoporteDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Remote
public interface IRAdministracionProcedure {

    /**
     * Permite la ejecucion de un procedure
     * 
     * @param idTipoSoporte
     * @param datosEntrada
     * @return
     */
    List<Object[]> ejecutarProcedure(Integer idTipoSoporte, String datosEntrada) throws CirculemosNegocioException;

    /**
     * Permite consulta la configuracion de ejecucion de procedures
     * 
     * @param idTipoSoporte
     * @return
     */
    List<ConfiguracionSoporteDTO> consultarConfiguracionProcedure(Integer idTipoSoporte);
}