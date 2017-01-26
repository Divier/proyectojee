package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.comparendo.ConsultaReincidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaReincidenciaDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

@Local
public interface ILReincidencia {

    /**
     * @see IRReincidencia#consultarReincidencias(ConsultaReincidenciaDTO)
     */
    ResultadoConsultaReincidenciaDTO consultarReincidencias(ConsultaReincidenciaDTO consultaReincidencia)
            throws CirculemosNegocioException;

}
