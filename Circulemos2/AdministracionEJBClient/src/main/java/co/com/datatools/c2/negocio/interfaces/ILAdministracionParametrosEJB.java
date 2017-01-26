package co.com.datatools.c2.negocio.interfaces;

import java.util.List;

import javax.ejb.Local;

import co.com.datatools.c2.dto.AdminParametrosDTO;
import co.com.datatools.c2.excepciones.CirculemosRuntimeException;

@Local
public interface ILAdministracionParametrosEJB {

    /**
     * @see IRAdministracionJobEJB#consultarJobs()
     */
    List<AdminParametrosDTO> consultarParametros() throws CirculemosRuntimeException;
}