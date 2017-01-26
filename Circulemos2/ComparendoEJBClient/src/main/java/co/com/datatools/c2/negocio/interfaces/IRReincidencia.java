package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Remote;

import co.com.datatools.c2.dto.comparendo.ConsultaReincidenciaDTO;
import co.com.datatools.c2.dto.comparendo.ResultadoConsultaReincidenciaDTO;
import co.com.datatools.c2.enumeraciones.EnumTipoReincidencia;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * Ofrece los servicios para el manejo de reincidencias de infractor.
 * 
 */
@Remote
public interface IRReincidencia {

    /**
     * Retorna un {@link ResultadoConsultaReincidenciaDTO} con el listado de los distintos comparendos de reincidencia de la persona recibida como
     * parametro. Consultando en distintas fuentes configuradas, siempre realiza la consulta en C2. Acorde al tipo de reincidencia a consultar tiene
     * en cuenta o no la infraccion, p. ej.: {@link EnumTipoReincidencia#EMBRIAGUEZ}, la infraccion del comparendo debe ser de embriaguez y el numero
     * de comparendo distinto. {@link EnumTipoReincidencia#GENERAL} solo tiene en cuenta que el numero de comparendo sea distinto.
     * 
     * @param consultaReincidencia
     *            Datos del infractor y tipo de reincidencia.
     * @return ResultadoConsultaReincidenciaDTO con el listado de los distintos comparendos de reincidencia de la persona recibida como parametro.
     * @author rodrigo.cruz
     * @throws CirculemosNegocioException 
     */
    ResultadoConsultaReincidenciaDTO consultarReincidencias(ConsultaReincidenciaDTO consultaReincidencia) throws CirculemosNegocioException;

}
