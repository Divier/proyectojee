package co.com.datatools.c2.negocio.interfaces;

import javax.ejb.Local;

import co.com.datatools.c2.dto.CoactivoDTO;
import co.com.datatools.c2.dto.FalloImpugnacionDTO;
import co.com.datatools.c2.dto.FinanciacionDTO;
import co.com.datatools.c2.dto.LogEjecucionWSDTO;
import co.com.datatools.c2.dto.ProcesoDTO;
import co.com.datatools.c2.dto.comparendo.ComparendoDTO;
import co.com.datatools.c2.excepciones.CirculemosNegocioException;

/**
 * @see IRClienteWSSIMIT
 * 
 * @author julio.pinzon 2016-08-12
 * 
 */
@Local
public interface ILClienteWSAXIS {

    /**
     * @see IRClienteWSAXIS#registrarFinanciacion(FinanciacionDTO)
     */
    public FinanciacionDTO registrarFinanciacion(FinanciacionDTO financiacion) throws CirculemosNegocioException;

    /**
     * @see IRClienteWSAXIS#anularFinanciacion(String)
     */
    public void anularFinanciacion(String numeroFinanciacion) throws CirculemosNegocioException;

    /**
     * @see IRClienteWSAXIS#impugnarComparendo(ComparendoDTO,ProcesoDTO)
     */
    public void impugnarComparendo(ComparendoDTO comparendo, ProcesoDTO impugnacion) throws CirculemosNegocioException;

    /**
     * @see IRClienteWSAXIS#registrarFalloImpugnacion(FalloImpugnacionDTO, ComparendoDTO, Long)
     */
    public Long registrarFalloImpugnacion(FalloImpugnacionDTO fallo, ComparendoDTO comparendoDTO, Long idProceso)
            throws CirculemosNegocioException;

    /**
     * Guardar log de la ejecucion del WS
     * 
     * @param log
     * @author julio.pinzon 2016-08-19
     */
    public void guardarLogWS(LogEjecucionWSDTO log);

    /**
     * @see IRClienteWSAXIS#registarCoactivo(CoactivoDTO)
     */
    public CoactivoDTO registarCoactivo(CoactivoDTO coactivoDTO) throws CirculemosNegocioException;
}
