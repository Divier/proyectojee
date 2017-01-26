package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.AdminParametrosDTO;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;

@Remote
public interface IRAdministracionParametrosEJB {

    /**
     * Retorna el listado de Parametros
     * 
     * @return Una Lista DTO de los parametros que son editables
     * @author divier.casas
     */
    List<AdminParametrosDTO> consultarParametros() throws CirculemosRuntimeException;


}