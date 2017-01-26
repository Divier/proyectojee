package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.ConfiguracionSoporteDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILAdministracionProcedure {

    /**
     * @see IRAdministracionProcedure#ejecutarProcedure(Integer, String)
     */
    List<Object[]> ejecutarProcedure(Integer idTipoSoporte, String datosEntrada) throws CirculemosNegocioException;

    /**
     * @see IRAdministracionProcedure#consultarConfiguracionProcedure(Integer)
     */
    List<ConfiguracionSoporteDTO> consultarConfiguracionProcedure(Integer idTipoSoporte);
}